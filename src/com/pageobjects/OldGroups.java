package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Accounts;
import com.innroad.inncenter.webelements.Elements_AdvanceGroups;
import com.innroad.inncenter.webelements.Elements_OldGroups;

public class OldGroups {

	public String numberAsString;

	public static Logger oldGroupsLogger = Logger.getLogger("OldGroups");

	public void createGroupaccount(WebDriver driver, String MarketingSegment, String GroupReferral,
			String NumberofNights, String GroupAccountName, String GroupFirstName, String GroupLastName,
			String GroupPhn, String GroupAddress, String GroupCity, String Groupstate, String GroupPostale,
			String Groupscountry) throws InterruptedException {

		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		oldGroup.GroupsNewAccount.click();
		Wait.wait10Second();
		Wait.explicit_wait_xpath(OR.Verify_New_Group_Account, driver);
		new Select(oldGroup.MarketingSegment).selectByVisibleText(MarketingSegment);
		new Select(oldGroup.GroupReferral).selectByVisibleText(GroupReferral);
		oldGroup.oldGroup_startdate.click();
		oldGroup.oldGroup_Today.click();
		oldGroup.oldGroup_numberofNights.sendKeys(NumberofNights);
		long timestamp = System.currentTimeMillis();
		numberAsString = Long.toString(timestamp);
		System.out.println("timeStamp :" + timestamp);
		oldGroup.oldGroup_AccountNumber.clear();
		oldGroup.oldGroup_AccountNumber.sendKeys(numberAsString);
		oldGroup.AccountFirstName.sendKeys(GroupAccountName);
		oldGroup.GroupFirstName.sendKeys(GroupFirstName);
		oldGroup.GroupLastName.sendKeys(GroupLastName);
		oldGroup.GroupPhn.sendKeys(GroupPhn);
		oldGroup.GroupAddress.sendKeys(GroupAddress);
		oldGroup.GroupCity.sendKeys(GroupCity);
		new Select(oldGroup.Groupstate).selectByVisibleText(Groupstate);
		oldGroup.GroupPostale.sendKeys(GroupPostale);
		new Select(oldGroup.Groupscountry).selectByVisibleText(Groupscountry);
		Wait.wait5Second();
		oldGroup.Mailinginfo.click();

	}

	public void SaveAdvGroup(WebDriver driver) {
		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		oldGroup.GroupSave.click();		
	}


	public void SaveAdvGroupAfterSave(WebDriver driver) throws InterruptedException {
		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		oldGroup.GroupSave.click();
		Wait.wait10Second();
		try{
			String continue1="//button[contains(text(),'Continue')]";
			if(driver.findElements(By.xpath(continue1)).size()>0){
				Wait.WaitForElement(driver, continue1);
				driver.findElement(By.xpath(continue1)).click();
			}
		}catch(Exception e){

		}

	}


	public void NavigateRoomBlock(WebDriver driver,ArrayList test_steps) {
		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		oldGroup.Navigate_Room_Block.click();
		test_steps.add("Navigate to room block");
		Wait.explicit_wait_xpath(OR.RoomBlockPage, driver);
	}

	public void oldRoomBlock(WebDriver driver, String Adults) {
		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		oldGroup.oldGroup_Adults.clear();
		oldGroup.oldGroup_Adults.sendKeys(Adults);
		oldGroup.old_Groups_Click_Search.click();
	}

	// Update automatically Updated Rooms
	public void updatedAutomaticallyAssignedRooms(WebDriver driver, String UpdatedBlockedRoom,String RoomClassName)
			throws InterruptedException {
		Elements_OldGroups AdvGroup = new Elements_OldGroups(driver);
		List<WebElement> BlockedRooms = driver.findElements(By.xpath("//b[contains(text(),'Blocked')]/../../../../../tbody/tr"));

		Utility.app_logs.info(BlockedRooms.size());
		Wait.wait5Second();
		for (int i = 1; i <= BlockedRooms.size(); i=i+1) {

			if(driver.findElement(By.xpath("//b[contains(text(),'Blocked')]/../../../../../tbody/tr["+i+"]")).isDisplayed()){

				String roomClass=driver.findElement(By.xpath("//b[contains(text(),'Blocked')]/../../../../../tbody/tr["+i+"]/td[7]/nobr/a")).getText();

				String cell="//b[contains(text(),'Blocked')]/../../../../../tbody/tr["+i+"]/td[5]/nobr";
				Wait.WaitForElement(driver, cell);
				driver.findElement(By.xpath(cell)).click();
				String input="//b[contains(text(),'Blocked')]/../../../../../tbody/tr["+i+"]/td[5]/input";

				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(input)));
				Wait.wait2Second();
				if(!roomClass.equalsIgnoreCase(RoomClassName)){
					driver.findElement(By.xpath(input)).sendKeys(Keys.chord(Keys.CONTROL, "a"), "0");
					Wait.wait2Second();
				}else{
					driver.findElement(By.xpath(input)).sendKeys(Keys.chord(Keys.CONTROL, "a"), UpdatedBlockedRoom);
					Wait.wait2Second();
				}
			}
		}
	}
	//b[contains(text(),'Blocked')]/../../../../../tbody/tr[3]/td[5]
	// Select Room class for block

	public void SetBlockRoomForSelectedRoomclass(WebDriver driver, String EnterBlockedcount, String RoomClassName)
			throws InterruptedException {
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		List<WebElement> GetRoomclassNames = AdvGroup.GetRoomclasses;
		Utility.app_logs.info("GetRoomclassNames" + GetRoomclassNames.size());
		for (int i = 0; i < GetRoomclassNames.size(); i++) {
			if (GetRoomclassNames.get(i).getText().equals(RoomClassName)) {
				int index = i + 1;
				driver.findElement(By.xpath(
						"//tbody[@data-bind='foreach: serverData.objListRoomClass']//tr[" + index + "]/td[3]/input"))
				.sendKeys(Keys.chord(Keys.CONTROL, "a"), EnterBlockedcount);
				Wait.wait5Second();

				break;
			}
		}

	}

	public void BlockRoomForSelectedRoomclass(WebDriver driver, String EnterBlockedcount, String RoomClassName)
			throws InterruptedException {
		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		List<WebElement> GetroomAvialbleCountsize = oldGroup.GetNumberofclasses;
		System.out.println("GetroomAvialbleCount" + GetroomAvialbleCountsize.size());
		for (int i = 0; i < GetroomAvialbleCountsize.size(); i++) {
			// int index = i+1;
			int results = Integer.parseInt(GetroomAvialbleCountsize.get(i).getText());

			// Wait.wait3Second();
			double GetNightlyRate1, GetTotalRate1;

			if (results >= 2) {
				// Wait.wait3Second();
				try {
					String GetNightlyRate = driver
							.findElement(By.xpath("//*[@id='MainContentxucRateQuotexUwgRateQuote_rc_0_" + i + "_7']"))
							.getText();
					GetNightlyRate1 = Double.parseDouble(GetNightlyRate);
					System.out.println("GetNightlyRate1  " + GetNightlyRate1);
					String GetTotalRate = driver
							.findElement(By.xpath("//*[@id='MainContentxucRateQuotexUwgRateQuote_rc_0_" + i + "_8']"))
							.getText();
					GetTotalRate1 = Double.parseDouble(GetTotalRate);
				} catch (Exception ex) {
					// checking if i value become equal to
					// GetroomAvialbleCountsize then resetting the i value to 0
					// to restart the loop until we hit the cell
					if (i + 1 == GetroomAvialbleCountsize.size()) {
						i = 0;
					}
					continue;
				}
				System.out.println("GetTotalRate1  " + GetTotalRate1);
				if (GetNightlyRate1 > 0 && GetTotalRate1 > 0) {
					System.out.println("results " + results);
					System.out.println("i " + i);
					WebElement d = driver
							.findElement(By.xpath(".//*[@id='MainContentxucRateQuotexUwgRateQuote_rc_0_" + i + "_4']"));
					Actions actions = new Actions(driver);
					actions.moveToElement(d);
					actions.click();
					actions.sendKeys(EnterBlockedcount);
					actions.build().perform();
					Wait.wait5Second();
					System.out.println("i out of box " + i);
					break;
				}
			}
		}

	}

	public void SearchGroupAccount(WebDriver driver) {

		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		oldGroup.oldGroups_Click_Groups.click();
		Wait.explicit_wait_xpath(OR.oldgroups_Verify_GroupSearchpage, driver);
		oldGroup.oldGroup_AccountSearch_Accountnum.sendKeys(numberAsString);
		oldGroup.oldGroups_Account_ClickGo.click();
		String GetAccountNumber = oldGroup.oldGroups_Account_VerifyAccountNumber.getText();
		// Assert.assertEquals(GetAccountNumber, numberAsString);
	}

	public void SearchBookicon(WebDriver driver) {
		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		oldGroup.oldGroup_SearchBookicon.click();
		Wait.explicit_wait_xpath(OR.oldGroup_Verify_GroupPickPage, driver);
	}

	public void pickfromRoominglist(WebDriver driver, String FirstName, String LastName) throws InterruptedException {
		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		oldGroup.oldGroup_Click_Roominglistbutton.click();
		Wait.explicit_wait_xpath(OR.oldGroup_Verify_Roominglistpopup, driver);
		oldGroup.oldGroups_Enter_FirstName_Roominglistpopup.sendKeys(FirstName);
		oldGroup.oldGroups_Enter_LastName_Roominglistpoup.sendKeys(LastName);
		new Select(oldGroup.oldGroups_Select_Roomclass_Roominglistpoup).selectByIndex(1);
		Wait.wait3Second();
		new Select(oldGroup.oldGroups_Select_RoomNo_Roominglistpopup).selectByIndex(1);
		Wait.wait3Second();
		oldGroup.oldGroups_Select_Pickupbutton_Roominglistpopup.click();
		Wait.explicit_wait_xpath(OR.oldGroup_Verify_Roominglistsummary, driver);
		Wait.explicit_wait_xpath(OR.oldgroup_Verify_grid, driver);
		oldGroup.oldGroup_Click_Close_Roominglistsummary.click();

	}

	public void createNewGroupaccount(WebDriver driver, String MarketingSegment, String GroupReferral,
			String NumberofNights, String GroupAccountName, String GroupFirstName, String GroupLastName,
			String GroupPhn, String GroupAddress, String GroupCity, String Groupstate, String GroupPostale,
			String Groupscountry) throws InterruptedException {

		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		oldGroup.GroupsNewAccount.click();
		Wait.wait10Second();
		Wait.explicit_wait_xpath(OR.Verify_New_Group_Account, driver);
		Wait.explicit_wait_visibilityof_webelement_600(oldGroup.MarketingSegment, driver);
		// System.out.println(oldGroup.MarketingSegment.getAttribute("value"));
		new Select(oldGroup.MarketingSegment).selectByVisibleText(MarketingSegment);
		new Select(oldGroup.GroupReferral).selectByVisibleText(GroupReferral);
		// oldGroup.oldGroup_startdate.click();
		// oldGroup.oldGroup_Today.click();
		// oldGroup.oldGroup_numberofNights.sendKeys(NumberofNights);
		long timestamp = System.currentTimeMillis();
		numberAsString = Long.toString(timestamp);
		oldGroup.oldGroup_AccountNumber.clear();
		oldGroup.oldGroup_AccountNumber.sendKeys(numberAsString);
		oldGroup.AccountFirstName.sendKeys(GroupAccountName);
		oldGroup.GroupFirstName.sendKeys(GroupFirstName);
		oldGroup.GroupLastName.sendKeys(GroupLastName);
		oldGroup.GroupPhn.sendKeys(GroupPhn);
		oldGroup.GroupAddress.sendKeys(GroupAddress);
		oldGroup.GroupCity.sendKeys(GroupCity);
		new Select(oldGroup.Groupstate).selectByVisibleText(Groupstate);
		oldGroup.GroupPostale.sendKeys(GroupPostale);
		new Select(oldGroup.Groupscountry).selectByVisibleText(Groupscountry);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", oldGroup.Mailinginfo);
		Wait.wait2Second();
		oldGroup.Mailinginfo.click();

	}

	public void createNewBlockItem(WebDriver driver,ArrayList test_steps, String BlockName) throws InterruptedException {
		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		Wait.WaitForElement(driver, OR.oldGroup_NewBlock);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", oldGroup.oldGroup_NewBlock);
		test_steps.add("Clicking on new Block");
		// oldGroup.Click_New_Block_button.click();
		Wait.wait5Second();
		WebElement e=driver.findElement(By.xpath(("//iframe[@id='dialog-body0']")));
		driver.switchTo().frame(e);
		Wait.WaitForElement(driver, OR.oldGroup_NewBlock_RoomBlobkDetailsPOpUp);
		assertTrue(oldGroup.oldGroup_NewBlock_RoomBlobkDetailsPOpUp.isDisplayed(), "Create Block Popup not opened");
		oldGroup.oldGroup_NewBlock_BlockName.sendKeys(BlockName);
		test_steps.add("Enter block name : "+BlockName);
		assertTrue(oldGroup.oldGroup_NewBlock_BlockName.isDisplayed(), "Block Name text box isn't present");
		assertTrue(oldGroup.oldGroup_NewBlock_Save.isDisplayed(), "Save button isn't present");
		assertTrue(oldGroup.oldGroup_NewBlock_Cancel.isDisplayed(), "Cancel Button isn't present");
		oldGroup.oldGroup_NewBlock_Save.click();
		Wait.wait2Second();
		driver.switchTo().defaultContent();
		assertTrue(oldGroup.RateQuotePage.isDisplayed(), "Rate Quote Page isn't opened");

	}

	public void EnterNewBlockDetails(WebDriver driver, String Nights) throws InterruptedException {

		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		oldGroup.Select_Arrival_Date_Groups.click();
		oldGroup.Click_Today_Arrival_Groups.click();
		oldGroup.Enter_No_of_Nigts.clear();
		oldGroup.Enter_No_of_Nigts.sendKeys(Nights);
		oldGroup.Click_Search_Group.click();
		Wait.wait2Second();
		assertTrue(oldGroup.RoomClassNameSearchResult.isDisplayed(),
				"Room Class Name,Min Avail,Max Block,Avg rate isn't displayed");

	}

	public void CreateBlock(WebDriver driver) throws InterruptedException {

		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		oldGroup.Click_Create_Block.click();
		oldGroup.ReleaseNote_OkBtn.click();
		assertTrue(oldGroup.RoomblockDetailedPage.isDisplayed(), "Roomblock deatailed page isn't displayed");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", oldGroup.EditBtn);
		assertTrue(oldGroup.EditBtn.isDisplayed(), "Edit Button isn't displayed");
		assertTrue(oldGroup.DeleteBtn.isDisplayed(), "Delete Button isn't displayed");
		assertTrue(oldGroup.SelectRoomsBtn.isDisplayed(), "Select Rooms Button isn't displayed");
		assertTrue(oldGroup.RoomListingBtn.isDisplayed(), "Room Listing Button isn't displayed");
		// Testing
		WebElement PickUpCount = driver.findElement(By.xpath("//*[@id='row0JQGrid']/div[7]/div"));
		String PickUpCountVaule = PickUpCount.getText();
		System.out.println(PickUpCountVaule);

	}

	public void SelectRoomListing(WebDriver driver) throws InterruptedException {

		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		String roomingList="(//input[@id='btnRoomingList'])[3]";
		Wait.WaitForElement(driver, roomingList);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath(roomingList)));
		driver.findElement(By.xpath(roomingList)).click();
		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		assertTrue(oldGroup.RoomingList_PickUpRooms.isDisplayed(), "Rooming List pop up isn't opened");
	}

	public String EnterRoomListingPickUpDetails(WebDriver driver, String FName, String LName, ArrayList test_steps) throws InterruptedException {

		test_steps.add("************************** Pickup from Roomling list ********************************");
		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		oldGroup.oldGroups_Enter_FirstName_Roominglistpopup.clear();
		oldGroup.oldGroups_Enter_FirstName_Roominglistpopup.sendKeys(FName);
		oldGroup.oldGroups_Enter_LastName_Roominglistpoup.clear();
		oldGroup.oldGroups_Enter_LastName_Roominglistpoup.sendKeys(LName);
		new Select(driver.findElement(By.xpath("//select[@id='dgRoomingList_drpRoomClassName_0']"))).selectByIndex(1);
		new Select(driver.findElement(By.xpath("//select[@id='dgRoomingList_drpRoomNo_0']"))).selectByIndex(1);
		oldGroup.oldGroup_RoomingList_Amount.clear();
		oldGroup.oldGroup_RoomingList_Amount.sendKeys("10");
		Wait.wait2Second();
		Wait.WaitForElement(driver, OR.oldGroup_RoomingList_Pickup);
		oldGroup.oldGroup_RoomingList_Pickup.click();
		Wait.wait5Second();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("dialog-body1")));
		String res="//table[@id='dgRoomingList']/tbody/tr[2]/td[1]";
		Wait.WaitForElement(driver,res);
		String reservation=driver.findElement(By.xpath(res)).getText();
		test_steps.add("Created reservation from roomling list : "+reservation);
		String name="//table[@id='dgRoomingList']/tbody/tr[2]/td[2]/a";
		Wait.WaitForElement(driver,name);
		String GuestName=driver.findElement(By.xpath(name)).getText();
		Assert.assertEquals(GuestName.trim(), "TBD");
		test_steps.add("Guest Name in reservation  from roomling list : "+GuestName);
		Wait.WaitForElement(driver, OR.oldGroup_RoomingList_Close);
		oldGroup.oldGroup_RoomingList_Close.click();
		driver.switchTo().defaultContent();
		Wait.wait10Second();
		String navres="//a[contains(text(),'Reservations')]";
		Wait.WaitForElement(driver, navres);
		driver.findElement(By.xpath(navres)).click();
		test_steps.add("Navigate to reservation");
		Wait.wait10Second();
		ReservationSearch rs = new ReservationSearch();
		rs.basicSearch_ResNumber(driver, reservation);
		String status="//span[contains(text(),'GroupBlocked')]";
		Wait.WaitForElement(driver,status);
		String resStatus=driver.findElement(By.xpath(status)).getText();
		Assert.assertEquals("Reservation Status : GroupBlocked", "GroupBlocked", resStatus.trim());
		test_steps.add("Reservation Status : "+resStatus);
		return reservation;
	}

	public void CloseRoomListingPickUpSummary(WebDriver driver) throws InterruptedException {

		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		oldGroup.oldGroup_Click_Close_Roominglistsummary.click();
		Wait.wait2Second();
		assertTrue(oldGroup.RoomblockDetailedPage.isDisplayed(), "Roomblock deatailed page isn't Closed");

	}

	public void NavigateReservationGroupAccount(WebDriver driver) {
		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		oldGroup.Navigate_ReservationGroupAccount.click();
		assertTrue(oldGroup.old_Groups_ReservationGroupAccountPage.isDisplayed(),
				"Reservation Group account Page isn't Displayed");
	}

	public void VerifyDetailsReservationGroupAccount(WebDriver driver, String FName, String LName) {
		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", oldGroup.ReservationgroupAccount_List_GuestName);
		String GuestName = oldGroup.ReservationgroupAccount_List_GuestName.getText();
		System.out.println(GuestName);
		assertTrue(GuestName.equals(FName + " " + LName), "Guest Name isn't same as saved");
	}

	public void ChangeAccountNumber(WebDriver driver, String AccountNumber) throws InterruptedException {

		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		oldGroup.oldGroup_AccountNumber.clear();
		oldGroup.oldGroup_AccountNumber.sendKeys(AccountNumber);
		Wait.wait3Second();
		String selectedOption = oldGroup.oldGroup_AccountNumber.getAttribute("value");
		assertEquals(AccountNumber, selectedOption);

	}

	public void select_ArrivalAndDepartDates(WebDriver driver,ArrayList test_steps, String StartDate,String EndDate){
		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		Wait.WaitForElement(driver, OR.oldGroup_ArrivalDate);
		oldGroup.oldGroup_ArrivalDate.click();
		test_steps.add("Clicking on Arrival date calender");
		String month=Utility.get_MonthYear(StartDate);
		String day=Utility.getDay(StartDate);
		String monthYear="//table[@class='datepicker-table-condensed table-condensed']/thead/tr[2]/th[2]";

		int i=0;
		String label;
		String dateTest="//td[text()='"+day+"']";
		while(i<20){
			label=driver.findElement(By.xpath(monthYear)).getText();
			if(label.equalsIgnoreCase(month)){
				Wait.WaitForElement(driver, dateTest);
				driver.findElement(By.xpath(dateTest)).click();
				test_steps.add("Selecting Arrival date : "+StartDate);
				break;
			}else{
				String Calender_Right="//i[@class='icon-arrow-right']";
				Wait.WaitForElement(driver, Calender_Right);
				driver.findElement(By.xpath(Calender_Right)).click();
			}
		}

		Wait.WaitForElement(driver, OR.oldGroup_DepartDate);
		oldGroup.oldGroup_DepartDate.click();
		test_steps.add("Clicking on darart date calender");
		month=Utility.get_MonthYear(EndDate);
		day=Utility.getDay(EndDate);
		monthYear="//table[@class='datepicker-table-condensed table-condensed']/thead/tr[2]/th[2]";

		i=0;
		dateTest="//td[text()='"+day+"']";
		while(i<20){
			label=driver.findElement(By.xpath(monthYear)).getText();
			if(label.equalsIgnoreCase(month)){
				Wait.WaitForElement(driver, dateTest);
				driver.findElement(By.xpath(dateTest)).click();
				test_steps.add("Selecting Depart date : "+EndDate);
				break;
			}else{
				String Calender_Right="//i[@class='icon-arrow-right']";
				Wait.WaitForElement(driver, Calender_Right);
				driver.findElement(By.xpath(Calender_Right)).click();
			}
		}
	}


	public void Enter_BlockDetails(WebDriver driver,ArrayList test_steps, String Audults,String RatePlan,String Children){

		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		Wait.WaitForElement(driver, OR.oldGroup_NewBlock_Adults);
		oldGroup.oldGroup_NewBlock_Adults.sendKeys(Audults);
		test_steps.add("Enter Number of Adults : "+Audults);

		Wait.WaitForElement(driver, OR.oldGroup_NewBlock_RatePlan);
		new Select((oldGroup.oldGroup_NewBlock_RatePlan)).selectByVisibleText(RatePlan);
		test_steps.add("Select The rateplan : "+RatePlan);

		Wait.WaitForElement(driver, OR.oldGroup_NewBlock_Children);
		oldGroup.oldGroup_NewBlock_Children.sendKeys(Children);
		test_steps.add("Enter Number of Childrens : "+Children);

		Wait.WaitForElement(driver, OR.oldGroup_NewBlock_Search);
		oldGroup.oldGroup_NewBlock_Search.click();
		test_steps.add("Clicking on search");
	}

	public void selectPagination(WebDriver driver,ArrayList test_steps){
		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", oldGroup.oldGroup_Pagination);
		Wait.WaitForElement(driver, OR.oldGroup_Pagination);
		new Select(oldGroup.oldGroup_Pagination).selectByVisibleText("All");
	}


	public void open_Account(WebDriver driver,ArrayList test_steps,String AccountName) throws InterruptedException{
		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		String group="//a[contains(text(),'"+AccountName+"')]/../following-sibling::td[6]/input";
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(group)));
		Wait.wait3Second();
		Wait.WaitForElement(driver, group);
		driver.findElement(By.xpath(group)).click();
		test_steps.add("Open the account : "+AccountName);
	}

	public void click_BookingIcon(WebDriver driver,ArrayList test_steps,String RoomClass){
		test_steps.add("************************** Booking from Book Icon ********************************");
		String book="(//a[contains(text(),'"+RoomClass+"')])[2]/../../following-sibling::td[7]//input";
		Wait.WaitForElement(driver, book);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(book)));
		driver.findElement(By.xpath(book)).click();
		test_steps.add("Clicking on book icon ");
	}

	public void open_GroupAccount(WebDriver driver,ArrayList test_steps,String AccountName){
		Elements_OldGroups oldGroup = new Elements_OldGroups(driver);
		String group="//a[contains(text(),'"+AccountName+"')]";
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(group)));

		Wait.WaitForElement(driver, group);
		driver.findElement(By.xpath(group)).click();
		test_steps.add("Open the account : "+AccountName);
	}

	public void searchGroupReservation(WebDriver driver,ArrayList getTest_Steps, String reservation){
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		WebDriverWait wait = new WebDriverWait(driver,90);
		wait.until(ExpectedConditions.visibilityOf(group.Group_Reservation_Search_Reservation));
		group.Group_Reservation_Search_Reservation.sendKeys(reservation);
		getTest_Steps.add("Enter Reservation id : "+reservation);
		oldGroupsLogger.info("Enter Reservation id : "+reservation);	

		String includeBlockres="//input[@id='MainContent_ucReservationControl1_chkAdvGroupBlockReservation']";
		Wait.WaitForElement(driver, includeBlockres);
		if(!driver.findElement(By.xpath(includeBlockres)).isSelected()){
			driver.findElement(By.xpath(includeBlockres)).click();
		}
		
		Wait.WaitForElement(driver, OR.Group_Reservation_Search_Go);
		group.Group_Reservation_Search_Go.click();
		getTest_Steps.add("Click on Go");
		oldGroupsLogger.info("Click on Go");	

		String loc="//td[contains(text(),'"+reservation+"')]";
		Wait.WaitForElement(driver, loc);
		if(driver.findElements(By.xpath(loc)).size()>0){
			getTest_Steps.add("Reservation found in group reservation tab");
			oldGroupsLogger.info("Reservation found in group reservation tab");
		}else{
			getTest_Steps.add("Reservation not found in group reservation tab");
			oldGroupsLogger.info("Reservation not found in group reservation tab");
		}
	}

	public void clear_StartDate(WebDriver driver){
		String clear="//img[@title='Clear Start date']";
		Wait.WaitForElement(driver, clear);
		driver.findElement(By.xpath(clear)).click();
	}

	public void click_Go(WebDriver driver){

		String go="//input[@id='MainContent_btnGoSearch']";
		Wait.WaitForElement(driver, go);
		driver.findElement(By.xpath(go)).click();
	}

	public void select_CashGroupPaymentMethod(WebDriver driver,ArrayList test_steps){
		Elements_OldGroups group = new Elements_OldGroups(driver);
		Wait.WaitForElement(driver, OR.oldGroup_PaymentMethod);
		new Select(group.oldGroup_PaymentMethod).selectByVisibleText("Cash");
		test_steps.add("Set group Payment Method as : Cash");
	}


}
