package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Accounts;
import com.innroad.inncenter.webelements.Elements_AdvanceGroups;
import com.innroad.inncenter.webelements.Elements_Groups;
import com.innroad.inncenter.webelements.Elements_Reservation;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AdvGroups {

	public static Logger advGroupsLogger = Logger.getLogger("AdvGroups");

	public String createGroupaccount(WebDriver driver,String MarketingSegment, String GroupReferral,
			String GroupAccountName, String GroupFirstName, String GroupLastName, String GroupPhn, String GroupAddress,
			String GroupCity, String Groupstate, String GroupPostale, String Groupscountry, ArrayList getTest_Steps)
					throws InterruptedException {

		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);

		// AdvGroup.GroupsNewAccount.click();

		String random=Utility.generateRandomString();
		GroupAccountName=GroupAccountName+random;

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement btnNewAccount = AdvGroup.GroupsNewAccount;
		js.executeScript("arguments[0].click();", btnNewAccount);

		Wait.explicit_wait_visibilityof_webelement_600(AdvGroup.MarketingSegment, driver);
		new Select(AdvGroup.MarketingSegment).selectByVisibleText(MarketingSegment);
		getTest_Steps.add("Selecting group marketing segment : "+MarketingSegment);
		new Select(AdvGroup.GroupReferral).selectByVisibleText(GroupReferral);
		getTest_Steps.add("Selecting group referral : "+GroupReferral);
		AdvGroup.AccountFirstName.sendKeys(GroupAccountName);
		AdvGroup.GroupFirstName.sendKeys(GroupFirstName);
		getTest_Steps.add("Selecting group FirstName : "+GroupFirstName);
		AdvGroup.GroupLastName.sendKeys(GroupLastName);
		getTest_Steps.add("Selecting group LastName : "+GroupLastName);
		AdvGroup.GroupPhn.sendKeys(GroupPhn);
		getTest_Steps.add("Selecting group Phone  : "+GroupPhn);
		AdvGroup.GroupAddress.sendKeys(GroupAddress);
		getTest_Steps.add("Selecting group address  : "+GroupAddress);
		AdvGroup.GroupCity.sendKeys(GroupCity);
		getTest_Steps.add("Selecting group city  : "+GroupCity);
		new Select(AdvGroup.Groupstate).selectByVisibleText(Groupstate);
		getTest_Steps.add("Selecting group state  : "+Groupstate);
		AdvGroup.GroupPostale.sendKeys(GroupPostale);
		getTest_Steps.add("Selecting group postal  : "+GroupPostale);
		new Select(AdvGroup.Groupscountry).selectByVisibleText(Groupscountry);
		getTest_Steps.add("Selecting group country  : "+Groupscountry);
		Wait.wait2Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		// AdvGroup.Mailinginfo.click();
		WebElement mailingInfoCB = AdvGroup.Mailinginfo;
		jse.executeScript("arguments[0].scrollIntoView(true);", mailingInfoCB);
		jse.executeScript("arguments[0].click();", mailingInfoCB);

		return GroupAccountName;
	}

	public String GetAccountNumbers(WebDriver driver) {
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		String Accnum = AdvGroup.GroupAccountNumber.getAttribute("value");
		return Accnum;
	}

	public void SaveAdvGroup(WebDriver driver) {
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		AdvGroup.GroupSave.click();
		String validateError="//div[@id='MainContent_dispSumm']";
			
	}
	
	public void SaveAdvGroupValidate(WebDriver driver,ArrayList test_steps) throws InterruptedException {
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		AdvGroup.GroupSave.click();
		String validateError="//div[@id='MainContent_dispSumm']";
		Wait.wait10Second();
		if(driver.findElements(By.xpath(validateError)).size()>0){
			test_steps.add("All fields marked with an * are required fields");
		}else{
			test_steps.add("Saved the group");
		}
	}
	

	public void EnterBlockName(WebDriver driver,ArrayList getTest_Steps, String BlockName) {
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);

		// AdvGroup.Click_New_Block_button.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement btnNewBlock = AdvGroup.Click_New_Block_button;
		js.executeScript("arguments[0].click();", btnNewBlock);
		getTest_Steps.add("Clicking on new block");

		Wait.explicit_wait_xpath(OR.Verify_Block_Details_Popup, driver);
		AdvGroup.Enter_Block_Name.sendKeys(BlockName);
		getTest_Steps.add("Enter block name : "+BlockName);
		AdvGroup.Click_Ok.click();
		getTest_Steps.add("Clicking on OK");
		Wait.explicit_wait_xpath(OR.Verify_Rate_Quote, driver);

	}

	public void SearchGroupCriteria(WebDriver driver, ArrayList getTest_Steps,String NumberofNights) throws InterruptedException {
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		AdvGroup.Select_Arrival_Date_Groups.click();
		AdvGroup.Click_Today_Arrival_Groups.click();
		getTest_Steps.add("Clicking on Today");
		AdvGroup.Enter_No_of_Nigts.sendKeys(NumberofNights);
		getTest_Steps.add("Enter Number of Nights : "+NumberofNights);
		AdvGroup.Click_Search_Group.click();
		getTest_Steps.add("Clicking on Search");
		Wait.explicit_wait_xpath(OR.Verify_Room_Class_Grid_Groups, driver);
		assertTrue(driver.findElement(By.xpath(OR.Room_Class_Grid)).isDisplayed(),"Failed: Room Class Grid Group is not Displayed");
		Wait.wait2Second();

	}

	

	// Select Room class for block

	public void BlockRoomForSelectedRoomclass(WebDriver driver,ArrayList getTest_Steps, String EnterBlockedcount, String RoomClassName)
			throws InterruptedException {
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		List<WebElement> GetRoomclassNames = AdvGroup.GetRoomclasses;
		advGroupsLogger.info("GetRoomclassNames" + GetRoomclassNames.size());
		for (int i = 0; i < GetRoomclassNames.size(); i++) {
			if (GetRoomclassNames.get(i).getText().equals(RoomClassName)) {
				int index = i + 1;
				driver.findElement(By.xpath("//tbody[@data-bind='foreach: serverData.objListRoomClass']//tr[" + index + "]/td[3]/input")).sendKeys(Keys.chord(Keys.CONTROL, "a"), EnterBlockedcount);
				getTest_Steps.add("Enter Blockedcount : "+EnterBlockedcount);
				Wait.wait1Second();
				break;
			}else{
				int index = i + 1;
				driver.findElement(By.xpath("//tbody[@data-bind='foreach: serverData.objListRoomClass']//tr[" + index + "]/td[3]/input")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "0");
				Wait.wait1Second();
			}
		}

	}


		public boolean ClickBookicon(WebDriver driver, ArrayList getTest_Steps,String RoomClassName, boolean flag) throws InterruptedException {
		getTest_Steps.add( "********************************* Booking from blue button ********************************");
		Wait.wait2Second();
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		Wait.wait1Second();
		List<WebElement> RoomCount = AdvGroup.CountofRooms;
		advGroupsLogger.info("RoomCount" + RoomCount.size());
		for (int i = 0; i < RoomCount.size(); i++) {
			if (RoomCount.get(i).getText().equals(RoomClassName)) {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@id='row" + i + "JQGrid']//div[@class='book']")));
				jse.executeScript("window.scrollBy(0,150);");
				Wait.wait2Second();
				driver.findElement(By.xpath("//div[@id='row" + i + "JQGrid']//div[@class='book']")).click();
				Wait.wait2Second();
				flag=true;
				break;
			}else if(i==(RoomCount.size()-1)){
				getTest_Steps.add("NO blue booking buttons");
				break;
			}
		}
		return flag;
	}

	public boolean ClickYellowicon(WebDriver driver, ArrayList getTest_Steps,boolean flag) throws InterruptedException {
		getTest_Steps.add("********************************* Booking from yellow button ********************************");
		Wait.wait3Second();
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		Wait.wait1Second();
		List<WebElement> RoomCount = AdvGroup.CountofRooms;
		advGroupsLogger.info("RoomCount" + RoomCount.size());
		for (int i = 0; i < RoomCount.size(); i++) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,150);");
			if(driver.findElements(By.xpath("//div[@id='row" + i + "JQGrid']//div[@class='bookyellow']")).size()>0){
				driver.findElement(By.xpath("//div[@id='row" + i + "JQGrid']//div[@class='bookyellow']")).click();
				Wait.wait2Second();
				flag=true;
				break;
			}else if(i==(RoomCount.size()-1)){
				advGroupsLogger.info("NO yellow booking buttons");
				getTest_Steps.add("NO Yellow booking buttons");
				break;
			}
		}
		return flag;
	}

	public boolean Clickredicon(WebDriver driver, ArrayList getTest_Steps,boolean flag) throws InterruptedException {
		getTest_Steps.add( "********************************* Booking from red button ********************************");
		Wait.wait3Second();
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		Wait.wait1Second();
		List<WebElement> RoomCount = AdvGroup.CountofRooms;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,150);");
		advGroupsLogger.info("RoomCount" + RoomCount.size());
		
		
		
		
		/*for (int i = 0; i < RoomCount.size(); i++) {
			jse.executeScript("window.scrollBy(0,20);");
			if(driver.findElements(By.xpath("//div[@id='row" + i + "JQGrid']//div[@class='bookred']")).size()>0){
				driver.findElement(By.xpath("//div[@id='row" + i + "JQGrid']//div[@class='bookred']")).click();
				Wait.wait2Second();
				flag=true;
				break;
			}else if(i==(RoomCount.size()-1)){
				advGroupsLogger.info("NO red booking buttons");
				break;
			}
		}*/
		
		
		Wait.wait5Second();
		
		if(driver.findElements(By.xpath("//div[@class='bookred']")).size()>0){
			jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@class='bookred']")));
			Wait.wait2Second();
			jse.executeScript("window.scrollBy(0,-40)");
			Wait.wait2Second();
			driver.findElement(By.xpath("//div[@class='bookred']")).click();
			Wait.wait2Second();
			flag=true;
		}
	
		
		return flag;
	}



	public void NavigateRoomBlock(WebDriver driver,ArrayList getTest_Steps) {
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		AdvGroup.Navigate_Room_Block.click();
		getTest_Steps.add("Clicking on Room block");
		Wait.explicit_wait_xpath(OR.Verifyroom_block_content, driver);
	}

	public String PickupFromRoomingList(WebDriver driver, String FirstName, String LastName, String RoomClassName,
			String Amount, String PaymentMethod) throws InterruptedException {

		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String PickupCount = "//a[contains(text(),'" + RoomClassName
				+ "')]//ancestor::div[@role='gridcell']//preceding-sibling::div[2]/div";
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(PickupCount)));
		String countBefore = driver.findElement(By.xpath(PickupCount)).getText();
		AdvGroup.Rooming_List.click();
		System.out.println("Click Rooming List");
		driver.switchTo().frame("dialog-body0");
		System.out.println("Switch Frame: body0");
		Wait.wait3Second();
		Wait.explicit_wait_xpath(OR.Rooming_List_Popup, driver);
		assertTrue(AdvGroup.Rooming_List_Popup.isDisplayed(), "Failed: Rooming List Popup is not displaying");

		AdvGroup.RoomingList_FirstName.sendKeys(FirstName);
		AdvGroup.RoomingList_LastName.sendKeys(LastName);

		new Select(AdvGroup.RoomingList_RoomClass).selectByVisibleText(RoomClassName);
		// new Select(AdvGroup.RoomingList_RoomNumber).selectByIndex(1);
		// AdvGroup.RoomingList_Amount.sendKeys(Amount);
		AdvGroup.RoomingList_BillingInfo.click();
		driver.switchTo().defaultContent();
		System.out.println("Switch Frame: default");
		driver.switchTo().frame("dialog-body1");
		System.out.println("Switch Frame: body1");
		Wait.waitUntilPresenceOfElementLocated(OR.BillingInfo_Popup, driver);
		new Select(AdvGroup.BillingInfo_PaymentMethod).selectByVisibleText(PaymentMethod);
		Wait.wait2Second();
		AdvGroup.BillingInfo_Save.click();
		driver.switchTo().defaultContent();
		System.out.println("Switch Frame Default");
		driver.switchTo().frame("dialog-body0");
		System.out.println("Switch Frame: body0");
		// assertTrue(AdvGroup.BillingInfo_Tick.isDisplayed(),"Failed: Billing
		// info");
		jse.executeScript("arguments[0].scrollIntoView(true);", AdvGroup.RoomingList_Pickup);
		AdvGroup.RoomingList_Pickup.click();

		driver.switchTo().defaultContent();
		System.out.println("Switch Frame Default");
		driver.switchTo().frame("dialog-body1");
		System.out.println("Switch Frame: body1");
		Wait.explicit_wait_xpath(OR.RoomingListSummary, driver);
		assertTrue(AdvGroup.RoomingListSummary.isDisplayed(), "Failed: Rooming List Summary");
		// assertTrue(false);
		String resNumber = AdvGroup.GeneratedReservationNumber.getText();
		System.out.println("Reservation Number : " + resNumber);
		AdvGroup.RoomingListSummary_Close.click();
		driver.switchTo().defaultContent();
		System.out.println("Switch Frame Default");
		Wait.wait5Second();
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(PickupCount)));
		String countAfter = driver.findElement(By.xpath(PickupCount)).getText();
		System.out.println(countBefore + "  " + countAfter);
		assertTrue(Integer.parseInt(countAfter) == Integer.parseInt(countBefore) + 1,
				"Failed: Pickup Count is not Increased");

		return resNumber;
	}

	public void VerifyCreatedReservation(WebDriver driver, String resNumber, String AccountName, String FirstName,
			String LastName) {

		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", AdvGroup.Groups_ReservationsTab);
		AdvGroup.Groups_ReservationsTab.click();

		Wait.explicit_wait_visibilityof_webelement_350(AdvGroup.ReservationNumbers, driver);
		Wait.waitUntilPresenceOfElementLocated(OR.Groups_ReservationPage, driver);
		jse.executeScript("arguments[0].scrollIntoView(true);", AdvGroup.ReservationNumbers);
		int size = driver.findElements(By.xpath(OR.ReservationNumbers)).size();
		System.out.println("Reservation list Size is " + size);
		boolean found = false;
		for (int i = 1; i <= size; i++) {
			String ResNumberPath = "(" + OR.ReservationNumbers + ")[" + i + "]";
			String resNUM = driver.findElement(By.xpath(ResNumberPath)).getText();
			if (resNUM.replaceAll("\\s", "").equals(resNumber.replaceAll("\\s", ""))) {
				found = true;
				String Guestname = "(" + OR.ReservationGuest + ")[" + i + "]";
				assertTrue(driver.findElement(By.xpath(Guestname)).getText().contains(FirstName + " " + LastName),
						"Failed: GuestName Missmatched");

				/*
				 * Wait.waitUntilPresenceOfElementLocated(OR.
				 * ReservationDetailPage);
				 * assertTrue(AdvGroup.ReservationDetailPage_GuestName.getText()
				 * .contains(GuestName),"Failed: Account name");
				 * assertTrue(AdvGroup.ReservationDetailPage_Account.getText().
				 * contains(AccountName),"Failed: Account name");
				 * AdvGroup.ReservationDetailPage_Close.click();
				 */break;
			}
		}
		assertTrue(found, "Failed: Reservation " + resNumber + " not found");
	}

	public void ClickCreateBlock1(WebDriver driver) {
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		Utility.ElementFinderUntillNotShow(By.xpath(OR.Click_Create_Block1), driver);
		AdvGroup.Click_Create_Block1.click();
		Wait.explicit_wait_xpath(OR.Verify_Block_Details_Popup, driver);

	}

	public void ClickEditBlock(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", AdvGroup.RoomDetailsPage_EditButton);
		driver.manage().window().maximize();
		driver.switchTo().frame("MainContent_Iframe_accountpicker");
		assertTrue(AdvGroup.RoomDetailsPage_BlockDetails.isDisplayed(), "Failed:Block Details not displayed");
		assertTrue(AdvGroup.BlockDetailsPage_RoomBlockAttiribute.isDisplayed(),
				"Failed:Room Block Attributes not displayed");

	}

	public void ClickBlockOptionsButton(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		AdvGroup.BlockDetailsPage_BlockOptionsButton.click();
		assertTrue(AdvGroup.BlockDetailsPage_ChargeRouting.isDisplayed(),
				"Failed:Charge Routing Details not displayed");
	}

	public void ClickPreviewFolioButton(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		AdvGroup.BlockDetailsPage_PreviewFolioButton.click();
		assertTrue(AdvGroup.BlockDetailsPage_LineItems.isDisplayed(), "Failed:Line item Details not displayed");

	}

	public void ClickYellowBookIcon(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		Utility.ScrollToElement(AdvGroup.YellowBookIcon.get(0), driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", AdvGroup.YellowBookIcon.get(0));
		Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load_2, driver);

	}

	public void ClickRedBookIcon(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		Utility.ScrollToElement(AdvGroup.RedBookIcon.get(0), driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", AdvGroup.RedBookIcon.get(0));
		Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load_2, driver);

	}

	public void ClickBlueBookIcon(WebDriver driver) throws InterruptedException {
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Utility.ScrollToElement(AdvGroup.BlueBookIcon.get(0), driver);
		jse.executeScript("arguments[0].click();", AdvGroup.BlueBookIcon.get(0));
		Wait.explicit_wait_xpath(OR.New_Reservation_Page_Load_2, driver);

	}

	public void ChangeAccountNumber(WebDriver driver, String AccountNumber) throws InterruptedException {

		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		String selectedOption = null;
		Wait.wait5Second();
		//		try {
		AdvGroup.GroupAccountNumber.clear();
		AdvGroup.GroupAccountNumber.sendKeys(AccountNumber);
		Wait.wait3Second();
		selectedOption = AdvGroup.GroupAccountNumber.getAttribute("value");
		System.out.println(selectedOption);
		Assert.assertEquals(AccountNumber, selectedOption);

	}

	public void groupSearch(WebDriver driver,ArrayList getTest_Steps, String groupName){
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		WebDriverWait wait = new WebDriverWait(driver,90);
		wait.until(ExpectedConditions.visibilityOf(group.Group_Search_GroupName));
		group.Group_Search_GroupName.sendKeys(groupName);

		getTest_Steps.add("Enter Group Name : "+groupName);
		advGroupsLogger.info("Enter Group Name : "+groupName);	

		Wait.WaitForElement(driver, OR.Group_Search_Go);
		group.Group_Search_Go.click();
		getTest_Steps.add("Click on Go");
		advGroupsLogger.info("Click on Go");	

		String loc="//a[contains(text(),'"+groupName.trim()+"')]";
		Wait.WaitForElement(driver, loc);
		driver.findElement(By.xpath(loc)).click();
		getTest_Steps.add("Opening the Group");
		advGroupsLogger.info("Opening the Group");

	}

	public void searchGroupReservation(WebDriver driver,ArrayList getTest_Steps, String reservation){
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		WebDriverWait wait = new WebDriverWait(driver,90);
		wait.until(ExpectedConditions.visibilityOf(group.Group_Reservation_Search_Reservation));
		group.Group_Reservation_Search_Reservation.sendKeys(reservation);
		getTest_Steps.add("Enter Reservation id : "+reservation);
		advGroupsLogger.info("Enter Reservation id : "+reservation);	

		Wait.WaitForElement(driver, OR.Group_Reservation_Search_Go);
		group.Group_Reservation_Search_Go.click();
		getTest_Steps.add("Click on Go");
		advGroupsLogger.info("Click on Go");	

		String loc="//td[contains(text(),'"+reservation+"')]";
		Wait.WaitForElement(driver, loc);
		if(driver.findElements(By.xpath(loc)).size()>0){
			getTest_Steps.add("Reservation found in group reservation tab");
			advGroupsLogger.info("Reservation found in group reservation tab");
		}else{
			getTest_Steps.add("Reservation not found in group reservation tab");
			advGroupsLogger.info("Reservation not found in group reservation tab");
		}
	}

	public void click_GroupsReservationTab(WebDriver driver,ArrayList getTest_Steps){
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		Wait.WaitForElement(driver, OR.Groups_ReservationsTab);
		group.Groups_ReservationsTab.click();
		getTest_Steps.add("Click on New Reservation Tab in Group");
		advGroupsLogger.info("Click on New Reservation Tab in Group");
	}
	
	public void click_RoomingList(WebDriver driver, ArrayList getTest_Steps) throws InterruptedException{
		getTest_Steps.add("********************************* Rooming list ********************************");
		Elements_AdvanceGroups AdvGroup= new Elements_AdvanceGroups(driver);
		Wait.wait5Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,200)");
		Wait.WaitForElement(driver, OR.Group_Click_Roominglistbutton);
		AdvGroup.Group_Click_Roominglistbutton.click();
		getTest_Steps.add("Click on Rooming List");
	}
	
	public void enter_pickupdetails(WebDriver driver,ArrayList getTest_Steps) throws InterruptedException{
		Wait.wait5Second();
		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		Elements_AdvanceGroups AdvGroup= new Elements_AdvanceGroups(driver);
		Wait.WaitForElement(driver, OR.oldGroup_Verify_Roominglistpopup);

		String loc="//table[@id='dgRoomingList']/tbody/tr";

		int numberofRooms=driver.findElements(By.xpath(loc)).size();
		String fName,lName,roomclass,room,amount,billinginfobutton;
		String paymentMethod="//select[@id='drpBilling_TypeID']";
		String paymentMethodSave="//input[@id='btnSave']";
		String close="//input[@id='btnClose']";
		for(int i=2;i<=numberofRooms;i++){
			driver.switchTo().defaultContent();
			Wait.wait2Second();
			driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
			fName="//table[@id='dgRoomingList']/tbody/tr["+i+"]/td[1]/input";
			lName="//table[@id='dgRoomingList']/tbody/tr["+i+"]/td[2]/input";
			roomclass="//table[@id='dgRoomingList']/tbody/tr["+i+"]/td[5]/select";
			room="//table[@id='dgRoomingList']/tbody/tr["+i+"]/td[6]/select";
			amount="//table[@id='dgRoomingList']/tbody/tr["+i+"]/td[7]/input";
			billinginfobutton="//table[@id='dgRoomingList']/tbody/tr["+i+"]/td[8]/input";

			Wait.WaitForElement(driver, fName);
			driver.findElement(By.xpath(fName)).sendKeys("Test res Fname"+i);
			getTest_Steps.add("Test res Fname"+i);
			Wait.WaitForElement(driver, lName);
			driver.findElement(By.xpath(lName)).sendKeys("Test res Lname"+i);
			getTest_Steps.add("Test res Lname"+i);
			Wait.WaitForElement(driver, roomclass);
			new Select(driver.findElement(By.xpath(roomclass))).selectByIndex(1);
			getTest_Steps.add("Select the room class");
			Wait.WaitForElement(driver, room);
			new Select(driver.findElement(By.xpath(room))).selectByIndex(i-1);
			getTest_Steps.add("selecting the room");
			Wait.WaitForElement(driver, amount);
			driver.findElement(By.xpath(amount)).sendKeys(Keys.chord(Keys.CONTROL, "a"),"200");
			getTest_Steps.add("Enter amount 200");
			Wait.WaitForElement(driver, billinginfobutton);
			driver.findElement(By.xpath(billinginfobutton)).click();
			getTest_Steps.add("Click on billing info button");
			Wait.wait2Second();
			driver.switchTo().defaultContent();
			Wait.wait2Second();
			driver.switchTo().frame(driver.findElement(By.id("dialog-body1")));
			Wait.WaitForElement(driver, paymentMethod);
			new Select(driver.findElement(By.xpath(paymentMethod))).selectByVisibleText("Cash");
			getTest_Steps.add("Select payment method as Cash");
			Wait.WaitForElement(driver, paymentMethodSave);
			driver.findElement(By.xpath(paymentMethodSave)).click();
			getTest_Steps.add("Click on Save");
			Wait.wait2Second();
			driver.switchTo().defaultContent();

		}
		driver.switchTo().defaultContent();
		Wait.wait2Second();
		driver.switchTo().frame(driver.findElement(By.id("dialog-body0")));
		Wait.WaitForElement(driver, OR.Groups_Select_Pickupbutton_Roominglistpopup);
		AdvGroup.Groups_Select_Pickupbutton_Roominglistpopup.click();
		getTest_Steps.add("Click on Pickup");
		driver.switchTo().defaultContent();
		ArrayList al = new ArrayList();

		String resLoc="//table[@id='dgRoomingList']/tbody/tr";
		Wait.wait5Second();
		
		while(true){
			if(!driver.findElement(By.xpath("//div[@id='InnerFreezePane']")).isDisplayed()){
				System.out.println("in if");
			break;
			}else{
				System.out.println("Waiting for frame");
				Wait.wait5Second();
			}
		}
		Wait.wait5Second();
		Wait.WaitForElement_ID(driver, "dialog-body1");
		driver.switchTo().frame(driver.findElement(By.id("dialog-body1")));
		
		for(int i=2;i<=numberofRooms;i++){
			resLoc="//table[@id='dgRoomingList']/tbody/tr["+i+"]/td[1]";	
			Wait.WaitForElement(driver, resLoc);
			al.add(driver.findElement(By.xpath(resLoc)).getText());
			getTest_Steps.add("Created reservation : "+driver.findElement(By.xpath(resLoc)).getText());	
		}
		
		Wait.WaitForElement(driver, close);
		driver.findElement(By.xpath(close)).click();
		getTest_Steps.add("Click on Close");
		Wait.wait40Second();
		driver.switchTo().defaultContent();
		if(driver.findElements(By.xpath("//span[contains(text(),'Picked up %: 100%')]")).size()>0){
			getTest_Steps.add("Pickup from rooming list done successfully");	
		}
	}
	public void click_GroupNewReservation(WebDriver driver,ArrayList<String> test_steps){
		Elements_Groups group = new Elements_Groups(driver);
		Wait.WaitForElement(driver, OR.Verify_New_Reservation_Enabled);
		group.Verify_New_Reservation_Enabled.click();
		test_steps.add("Click on New Reservation in Group");
		advGroupsLogger.info("Click on New Reservation in Group");
	}	
	
	public void navigateFolio(WebDriver driver,ArrayList<String> test_steps) {
		Elements_Groups group = new Elements_Groups(driver);

		WebDriverWait wait = new WebDriverWait(driver,90);
		wait.until(ExpectedConditions.visibilityOf(group.Group_Folio));
		Wait.WaitForElement(driver, OR.Group_Folio);
		group.Group_Folio.click();
		test_steps.add("Click on Group Folio");
		advGroupsLogger.info("Click on Group Folio");

	}
	
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <selectRoomInGroupBlock> 
	 * ' Description: <Select room and find avilableRooms,blocks,Avarage rate > 
	 * ' Input parameters: < ',' separated parameters type>   ( int, int, String )
	 * ' Return value: ArrayList<String> 
	 * ' Created By: <Reddy Ponnolu>
	 * ' Created On:  <04/05/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	
	public ArrayList<String> selectRoomInGroupBlock(WebDriver driver,String RoomClassName,ArrayList<String> test_steps) throws InterruptedException{
		String groupBlockMinAvilableRooms="//span[@title='"+RoomClassName+"']/../..//following-sibling::span[contains(@data-bind,'A1_avail')]";
		String groupBlockmaxBlock ="//span[@title='"+RoomClassName+"']/../..//following-sibling::input[contains(@data-bind,'A1_block')]";
		String groupBlockAVgRate="//span[@title='"+RoomClassName+"']/../..//following-sibling::input[contains(@data-bind,'A1_price')]";	
		Utility.ScrollToEnd(driver);
		Wait.waitForElementToBeVisibile(By.xpath("//span[@title='"+RoomClassName+"']/../..//following-sibling::span[contains(@data-bind,'A1_avail')]"),driver);
		String avilableRooms = driver.findElement(By.xpath(groupBlockMinAvilableRooms)).getText();
		test_steps.add("group Block MinAvilable Rooms are:"+avilableRooms);
		advGroupsLogger.info("group Block MinAvilable Rooms are:"+avilableRooms);
		
		Wait.waitForElementToBeVisibile(By.xpath("//span[@title='"+RoomClassName+"']/../..//following-sibling::input[contains(@data-bind,'A1_block')]"),driver);
		driver.findElement(By.xpath(groupBlockmaxBlock)).click();
		Wait.wait2Second();
		String blocks = driver.findElement(By.xpath(groupBlockmaxBlock)).getAttribute("value");
		test_steps.add("group Block Max Block are:"+blocks);
		advGroupsLogger.info("group Block Max Block:"+blocks);
		
		Wait.waitForElementToBeVisibile(By.xpath("//span[@title='"+RoomClassName+"']/../..//following-sibling::input[contains(@data-bind,'A1_price')]"),driver);
		String blockRate = driver.findElement(By.xpath(groupBlockAVgRate)).getAttribute("value");
		test_steps.add("Group Block average  Rate:"+blockRate);
		advGroupsLogger.info("Group Block average  Rate:"+blockRate);
		
		
		return test_steps;
		
	}
	
	public ArrayList<String> searchGroupCriteriaWithOutBlackDates(WebDriver driver,String NumberofNights,ArrayList<String>test_steps) throws InterruptedException{
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		AdvGroup.Select_Arrival_Date_Groups.click();
		String onlyDate=Utility.GeetingNextWeek(driver);
		int nextDayDate=Integer.parseInt(onlyDate);
		nextDayDate++;
		advGroupsLogger.info("Next Week Next Day is "+nextDayDate+"th");
		test_steps.add("Next Week Next Day is "+nextDayDate+"th");
		AdvGroup.Select_Arrival_Date_Groups.click();
		//RateQuotePage.Click_Arrive_DatePicker.click();
		test_steps.add("Clicked on DatePicker");
		String cellPath="//div[@class='datepicker-days']//td[contains(text(),'"+nextDayDate+"')]";
		WebElement Next_Date=driver.findElement(By.xpath(cellPath));
		Next_Date.click();
		advGroupsLogger.info("Next Week Next day "+nextDayDate+"th is Selected");
		test_steps.add("Next Week Next day "+nextDayDate+"th is Selected");
		AdvGroup.Enter_No_of_Nigts.sendKeys(NumberofNights);
		test_steps.add("Enter Number of Nights : "+NumberofNights);
		AdvGroup.Click_Search_Group.click();
		test_steps.add("Clicking on Search");
		//Wait.explicit_wait_xpath(OR.Verify_Room_Class_Grid_Groups, driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR.Verify_Room_Class_Grid_Groups), driver);
		assertTrue(driver.findElement(By.xpath(OR.Room_Class_Grid)).isDisplayed(),"Failed: Room Class Grid Group is not Displayed");
		Wait.wait2Second();
		return test_steps;
		
	}
	public ArrayList<String> updatedAutomaticallyAssignedRooms(WebDriver driver, String UpdatedBlockedRoom)
			throws InterruptedException {
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		List<WebElement> BlockedRooms = AdvGroup.GetBlockedRowsize;
		advGroupsLogger.info(BlockedRooms.size());

		for (int i = 0; i < BlockedRooms.size(); i++) {
			String BlockedRoomsCount = BlockedRooms.get(i).getAttribute("value");
			int BlockedRoomsvalues = Integer.parseInt(BlockedRoomsCount);
			if (BlockedRoomsvalues > 0) {
				int index = i + 1;
				driver.findElement(By.xpath(
						"//tbody[@data-bind='foreach: serverData.objListRoomClass']//tr[" + index + "]/td[3]/input"))
						.sendKeys(Keys.chord(Keys.CONTROL, "a"), UpdatedBlockedRoom);
				testSteps.add("Enter UpdatedBlockedRoom : " + UpdatedBlockedRoom);
			} else {
				int index = i + 1;
				driver.findElement(By.xpath(
						"//tbody[@data-bind='foreach: serverData.objListRoomClass']//tr[" + index + "]/td[3]/input"))
						.sendKeys(Keys.chord(Keys.CONTROL, "a"), "0");
			}
		}
		return testSteps;
	}

	// Select Room class for block

	public ArrayList<String> BlockRoomForSelectedRoomclass(WebDriver driver, String enterBlockedcount,
			String roomClassName) throws InterruptedException {
		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		List<WebElement> GetRoomclassNames = AdvGroup.GetRoomclasses;
		advGroupsLogger.info("GetRoomclassNames" + GetRoomclassNames.size());
		for (int i = 0; i < GetRoomclassNames.size(); i++) {
			if (GetRoomclassNames.get(i).getText().equals(roomClassName)) {
				int index = i + 1;
				driver.findElement(By.xpath(
						"//tbody[@data-bind='foreach: serverData.objListRoomClass']//tr[" + index + "]/td[3]/input"))
						.sendKeys(Keys.chord(Keys.CONTROL, "a"), enterBlockedcount);
				testSteps.add("Enter Blockedcount : " + enterBlockedcount + " for Room Class : " + roomClassName);
				break;
			} else {
				int index = i + 1;
				driver.findElement(By.xpath(
						"//tbody[@data-bind='foreach: serverData.objListRoomClass']//tr[" + index + "]/td[3]/input"))
						.sendKeys(Keys.chord(Keys.CONTROL, "a"), "0");
			}
		}
		return testSteps;

	}

	public ArrayList<String> ClickCreateBlock(WebDriver driver, ArrayList<String> testSteps)
			throws InterruptedException {

		Elements_AdvanceGroups AdvGroup = new Elements_AdvanceGroups(driver);
		Wait.WaitForElement(driver, OR.Click_Create_Block);
		AdvGroup.Click_Create_Block.click();
		testSteps.add("Clicking on create block");

		if(driver.findElements(By.xpath(OR.Click_Continue_Block_Night)).size()>0)
		{
			AdvGroup.Click_Continue_Block_Night.click();
			testSteps.add("Clicking on continue");
		}
		
		Wait.WaitForElement(driver, OR.Verify_Release_Date_Popup);
		AdvGroup.Click_Ok_On_Rel_Popup.click();
		testSteps.add("Clicking on Release pop up");
		Wait.WaitForElement(driver, OR.Verifyroom_block_content);
		return testSteps;

	}
	
	public ArrayList<String> clickOnGroupsReservationTab(WebDriver driver, ArrayList<String> testSteps) {
		Elements_AdvanceGroups group = new Elements_AdvanceGroups(driver);
		Wait.WaitForElement(driver, OR.Groups_ReservationsTab);
		Wait.waitForElementToBeVisibile(By.xpath(OR.Groups_ReservationsTab), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Groups_ReservationsTab), driver);
		group.Groups_ReservationsTab.click();
		testSteps.add("Click on new reservation tab in group");
		advGroupsLogger.info("Click on new reservation tab in group");
		return testSteps;
	}


}
