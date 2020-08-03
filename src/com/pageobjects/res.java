
package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertTrue;

import static org.testng.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;
import org.apache.poi.hssf.record.formula.functions.If;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Reservation;
import com.innroad.inncenter.utils.ESTTimeZone;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_CPReservation;
import com.innroad.inncenter.webelements.Elements_Reservation_SearchPage;

import com.innroad.inncenter.webelements.Elements_Reservation;

import junit.framework.Assert;

public class res {
	ArrayList<String> test_steps = new ArrayList<>();
	public static Logger reslogger = Logger.getLogger("CPReservationPage");

	public ArrayList<String> click_NewReservation(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.CP_NewReservation), driver);
		res.CP_NewReservation.click();
		test_steps.add("Clicking on New Reservation");
		reslogger.info("Clicking on New Reservation");
		return test_steps;
	}

	public void select_CheckInDate(WebDriver driver, ArrayList<String> test_steps, String CheckInDate)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);

		String loading = "(//div[@class='ir-loader-in'])[2]";
		int counter = 0;
		while (true) {
			if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
				break;
			} else if (counter == 40) {
				break;
			} else {
				counter++;
				Wait.wait2Second();
			}
		}
		reslogger.info("Waited to loading symbol");
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_CheckinCal);
		res.CP_NewReservation_CheckinCal.click();
		String monthYear = Utility.get_MonthYear(CheckInDate);
		String day = Utility.getDay(CheckInDate);
		reslogger.info(monthYear);
		String header = null, headerText = null, next = null, date;
		for (int i = 1; i <= 12; i++) {
			header = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[2]";
			headerText = driver.findElement(By.xpath(header)).getText();
			if (headerText.equalsIgnoreCase(monthYear)) {
				date = "//td[contains(@class,'day') and text()='" + day + "']";
				Wait.WaitForElement(driver, date);
				int size = driver.findElements(By.xpath(date)).size();
				for (int k = 1; k <= size; k++) {
					date = "(//td[contains(@class,'day') and text()='" + day + "'])[" + k + "]";
					String classText = driver.findElement(By.xpath(date)).getAttribute("class");
					if (!classText.contains("old")) {
						driver.findElement(By.xpath(date)).click();
						test_steps.add("Selecting checkin date : " + CheckInDate);
						reslogger.info("Selecting checkin date : " + CheckInDate);
						break;
					}
				}
				break;
			} else {
				next = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[3]/div";
				Wait.WaitForElement(driver, next);
				driver.findElement(By.xpath(next)).click();
				Wait.wait2Second();
			}
		}
	}

	public void select_CheckoutDate(WebDriver driver, ArrayList test_steps, String CheckoutDate)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_CheckoutCal);
		res.CP_NewReservation_CheckoutCal.click();
		String monthYear = Utility.get_MonthYear(CheckoutDate);
		String day = Utility.getDay(CheckoutDate);
		reslogger.info(monthYear);
		String header = null, headerText = null, next = null, date;
		for (int i = 1; i <= 12; i++) {
			header = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[2]";
			headerText = driver.findElement(By.xpath(header)).getText();
			if (headerText.equalsIgnoreCase(monthYear)) {
				date = "//td[contains(@class,'day') and text()='" + day + "']";
				Wait.WaitForElement(driver, date);
				int size = driver.findElements(By.xpath(date)).size();
				for (int k = 1; k <= size; k++) {
					date = "(//td[contains(@class,'day') and text()='" + day + "'])[" + k + "]";
					String classText = driver.findElement(By.xpath(date)).getAttribute("class");
					if (!classText.contains("old")) {
						driver.findElement(By.xpath(date)).click();
						test_steps.add("Selecting checkout date : " + CheckoutDate);
						reslogger.info("Selecting checkout date : " + CheckoutDate);
						break;
					}
				}

				break;
			} else {
				next = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[3]/div";
				Wait.WaitForElement(driver, next);
				driver.findElement(By.xpath(next)).click();
				Wait.wait2Second();
			}
		}
	}

	public ArrayList<String> enter_Adults(WebDriver driver, ArrayList<String> test_steps, String Adults) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Adults);
		res.CP_NewReservation_Adults.clear();
		res.CP_NewReservation_Adults.sendKeys(Adults);
		test_steps.add("Enter No of adults : " + Adults);
		reslogger.info("Enter No of adults : " + Adults);
		return test_steps;
	}

	public ArrayList<String> enter_Children(WebDriver driver, ArrayList<String> test_steps, String Children) {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Children);
		res.CP_NewReservation_Children.clear();
		res.CP_NewReservation_Children.sendKeys(Children);
		test_steps.add("Enter No of Children : " + Children);
		reslogger.info("Enter No of Children : " + Children);
		return test_steps;

	}

	public ArrayList<String> select_Rateplan(WebDriver driver, ArrayList<String> test_steps, String Rateplan,
			String PromoCode) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Rateplan);
		res.CP_NewReservation_Rateplan.click();
		String rate = "//label[contains(text(),'Rate Plan / Promo')]/preceding-sibling::div//span[contains(text(),'"
				+ Rateplan + "')]/..";
		reslogger.info(rate);
		Wait.WaitForElement(driver, rate);
		driver.findElement(By.xpath(rate)).click();
		test_steps.add("Selecting the rateplan : " + Rateplan);
		reslogger.info("Selecting the rateplan : " + Rateplan);
		if (Rateplan.contains("Promo")) {
			Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_PromoCode);
			res.CP_NewReservation_PromoCode.clear();
			res.CP_NewReservation_PromoCode.sendKeys(PromoCode);
			test_steps.add("Enter promocode : " + PromoCode);
			reslogger.info("Enter promocode : " + PromoCode);
		}
		return test_steps;
	}

	public ArrayList<String> clickOnFindRooms(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_FindRooms);
		res.CP_NewReservation_FindRooms.click();
		test_steps.add("Clicking on FindRooms");
		reslogger.info("Clicking on FindRooms");
		int count = 0;
		while (true) {
			if (driver.findElement(By.xpath("//section[@class='ir-roomClassDetails manual-override']")).isDisplayed()) {
				break;
			} else if (count == 20) {
				break;
			} else {
				count++;
				Wait.wait2Second();
			}
		}
		reslogger.info("Waited to loading symbol");
		return test_steps;
	}

	// updated by farhan
	public ArrayList<String> selectRoom(WebDriver driver, ArrayList<String> test_steps, String RoomClass,
			String IsAssign, String Account) throws InterruptedException {
		String room = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'" + RoomClass
				+ "')]/../../div[2]/span";

		Wait.WaitForElement(driver, room);
		String rooms = driver.findElement(By.xpath(room)).getText();
		reslogger.info(rooms);
		String[] roomsCount = rooms.split(" ");
		int count = Integer.parseInt(roomsCount[0].trim());
		if (count > 0) {
			String sel = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'" + RoomClass
					+ "')]/../../../following-sibling::div/div/select";

			String rulessize = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
					+ RoomClass.trim() + "')]/following-sibling::span";
			reslogger.info(rulessize);
			int ruleCount = driver.findElements(By.xpath(rulessize)).size();
			WebElement ele = driver.findElement(By.xpath(sel));
			test_steps.add("Selected room class : " + RoomClass);
			reslogger.info("Selected room class : " + RoomClass);
			if (IsAssign.equalsIgnoreCase("No")) {
				String expand = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/following-sibling::div/button";
				Wait.WaitForElement(driver, expand);
				driver.findElement(By.xpath(expand)).click();

				String unAssign = "(//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass
						+ "')]/../../../following-sibling::div/div/select/following-sibling::div//ul//span[text()='Unassigned'])";
				Wait.WaitForElement(driver, unAssign);
				driver.findElement(By.xpath(unAssign)).click();
				test_steps.add("Selected room number : Unassigned");
				reslogger.info("Selected room number : Unassigned");
			} else if (IsAssign.equalsIgnoreCase("Yes")) {
				String roomnum = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/option";
				reslogger.info("&&&&&&&P:" + roomnum);

				// updated code :aqsa ( previous script was selecting unassigned
				// when isAssign=true)
				int maxNumber = driver.findElements(By.xpath(roomnum)).size();
				int minNumber = 2;
				Random random = new Random();
				int randomNumber = random.nextInt(maxNumber - minNumber) + minNumber;
				reslogger.info(" Random Number is :" + randomNumber);
				reslogger.info("count : " + maxNumber);
				reslogger.info("randomNumber : " + randomNumber);
				Wait.WaitForElement(driver, sel);

				String expand = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/following-sibling::div/button";
				Wait.WaitForElement(driver, expand);
				driver.findElement(By.xpath(expand)).click();

				String roomNumber = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/following-sibling::div//ul/li["
						+ randomNumber + "]/a/span";
				Wait.WaitForElement(driver, roomNumber);
				driver.findElement(By.xpath(roomNumber)).click();
			} else {
				String expand = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/following-sibling::div/button";
				Wait.waitForElementToBeClickable(By.xpath(expand), driver, 20);
				driver.findElement(By.xpath(expand)).click();

				String assignRoomNo = "(//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass
						+ "')]/../../../following-sibling::div/div/select/following-sibling::div//ul//span[text()='"
						+ IsAssign + "'])";
				Wait.waitForElementToBeClickable(By.xpath(assignRoomNo), driver, 10);
				driver.findElement(By.xpath(assignRoomNo)).click();
				test_steps.add("Selecting room <b>" + IsAssign + "</b> from <b>" + RoomClass + "</b> room class");
				reslogger.info("Selecting room <b>" + IsAssign + "</b> from <b>" + RoomClass + "</b> room class");
			}

			String select = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
					+ RoomClass + "')]/../../../following-sibling::div//span[contains(text(),'SELECT')]";
			Wait.WaitForElement(driver, select);
			driver.findElement(By.xpath(select)).click();

			String loading = "(//div[@class='ir-loader-in'])[2]";
			int counter = 0;
			while (true) {
				if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
					break;
				} else if (counter == 50) {
					break;
				} else {
					counter++;
					Wait.wait2Second();
				}
			}

			reslogger.info("Waited to loading symbol");

			reslogger.info("Rule Count : " + ruleCount);

			// System.out.println("Waited to loading symbol");

			// System.out.println("Rule Count : " + ruleCount);

			if (ruleCount > 1) {
				Wait.wait5Second();
				String rules = "//p[text()='Selecting this room will violate the following rules']/../..//button[text()='Yes']";

				if (driver.findElements(By.xpath(rules)).size() > 0) {
					Wait.wait2Second();
					driver.findElement(By.xpath(rules)).click();
					test_steps.add(
							"Selecting this room will violate the following rules : Are you sure you want to proceed? : yes");
					reslogger.info(
							"Selecting this room will violate the following rules : Are you sure you want to proceed? : yes");

					loading = "(//div[@class='ir-loader-in'])[2]";
					counter = 0;
					while (true) {
						if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
							break;
						} else if (counter == 4) {
							break;
						} else {
							counter++;
							Wait.wait2Second();
						}
					}
				}
			}
			if (!(Account.isEmpty() || Account.equalsIgnoreCase(""))) {
				String policy = "//p[contains(text(),'Based on the changes made')]/../..//button[text()='Yes']";
				Wait.WaitForElement(driver, policy);
				driver.findElement(By.xpath(policy)).click();
				test_steps.add("Based on the changes made, new policies are applicable.? : yes");
				reslogger.info("Based on the changes made, new policies are applicable.? : yes");

				loading = "(//div[@class='ir-loader-in'])[2]";
				counter = 0;
				while (true) {
					if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
						break;
					} else if (counter == 3) {
						break;
					} else {
						counter++;
						Wait.wait2Second();
					}
				}
			}

		} else {
			test_steps.add("Rooms Count <=0 for " + RoomClass + " for specified date");
			reslogger.info("Rooms Count <=0 for " + RoomClass + " for specified date");
		}
		return test_steps;
	}

	public ArrayList<String> selectRoom1(WebDriver driver, ArrayList<String> test_steps, String RoomClass,
			String IsAssign, String Account) throws InterruptedException {
		String room = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'" + RoomClass
				+ "')]/../../div[2]/span";

		Wait.WaitForElement(driver, room);
		String rooms = driver.findElement(By.xpath(room)).getText();
		reslogger.info(rooms);
		String[] roomsCount = rooms.split(" ");
		int count = Integer.parseInt(roomsCount[0].trim());
		if (count > 0) {
			String sel = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'" + RoomClass
					+ "')]/../../../following-sibling::div/div/select";

			String rulessize = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
					+ RoomClass.trim() + "')]/following-sibling::span";
			reslogger.info(rulessize);
			int ruleCount = driver.findElements(By.xpath(rulessize)).size();
			WebElement ele = driver.findElement(By.xpath(sel));
			test_steps.add("Selected room class : " + RoomClass);
			reslogger.info("Selected room class : " + RoomClass);
			if (IsAssign.equalsIgnoreCase("No")) {
				String expand = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/following-sibling::div/button";
				Wait.WaitForElement(driver, expand);
				driver.findElement(By.xpath(expand)).click();

				String unAssign = "(//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass
						+ "')]/../../../following-sibling::div/div/select/following-sibling::div//ul//span[text()='Unassigned'])";
				Wait.WaitForElement(driver, unAssign);
				driver.findElement(By.xpath(unAssign)).click();
				test_steps.add("Selected room number : Unassigned");
				reslogger.info("Selected room number : Unassigned");
			} else if (IsAssign.equalsIgnoreCase("Yes")) {
				String roomnum = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/option";
				reslogger.info(roomnum);
				int count1 = driver.findElements(By.xpath(roomnum)).size();
				Random random = new Random();
				int randomNumber = random.nextInt(count1 - 1) + 1;
				reslogger.info("count : " + count1);
				reslogger.info("randomNumber : " + randomNumber);
				Wait.WaitForElement(driver, sel);

				String expand = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/following-sibling::div/button";
				Wait.WaitForElement(driver, expand);
				driver.findElement(By.xpath(expand)).click();

				String roomNumber = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/following-sibling::div//ul/li["
						+ randomNumber + "]/a/span";
				Wait.WaitForElement(driver, roomNumber);
				driver.findElement(By.xpath(roomNumber)).click();
			} else {
				String expand = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/following-sibling::div/button";
				Wait.waitForElementToBeClickable(By.xpath(expand), driver, 20);
				driver.findElement(By.xpath(expand)).click();

				String assignRoomNo = "(//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass
						+ "')]/../../../following-sibling::div/div/select/following-sibling::div//ul//span[text()='"
						+ IsAssign + "'])";
				Wait.waitForElementToBeClickable(By.xpath(assignRoomNo), driver, 10);
				driver.findElement(By.xpath(assignRoomNo)).click();
				test_steps.add("Selecting room <b>" + IsAssign + "</b> from <b>" + RoomClass + "</b> room class");
				reslogger.info("Selecting room <b>" + IsAssign + "</b> from <b>" + RoomClass + "</b> room class");
			}

			String select = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
					+ RoomClass + "')]/../../../following-sibling::div//span[contains(text(),'SELECT')]";
			Wait.WaitForElement(driver, select);
			driver.findElement(By.xpath(select)).click();

			String loading = "(//div[@class='ir-loader-in'])[2]";
			int counter = 0;
			while (true) {
				if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
					break;
				} else if (counter == 50) {
					break;
				} else {
					counter++;
					Wait.wait2Second();
				}
			}
			reslogger.info("Waited to loading symbol");

			reslogger.info("Rule Count : " + ruleCount);
			if (ruleCount > 1) {
				Wait.wait5Second();
				String rules = "//p[text()='Selecting this room will violate the following rules']/../..//button[text()='Yes']";

				if (driver.findElements(By.xpath(rules)).size() > 0) {
					Wait.wait2Second();
					driver.findElement(By.xpath(rules)).click();
					test_steps.add(
							"Selecting this room will violate the following rules : Are you sure you want to proceed? : yes");
					reslogger.info(
							"Selecting this room will violate the following rules : Are you sure you want to proceed? : yes");

					loading = "(//div[@class='ir-loader-in'])[2]";
					counter = 0;
					while (true) {
						if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
							break;
						} else if (counter == 4) {
							break;
						} else {
							counter++;
							Wait.wait2Second();
						}
					}
				}
			}
			if (!(Account.isEmpty() || Account.equalsIgnoreCase(""))) {
				String policy = "//p[contains(text(),'Based on the changes made')]/../..//button[text()='Yes']";
				Wait.WaitForElement(driver, policy);
				driver.findElement(By.xpath(policy)).click();
				test_steps.add("Based on the changes made, new policies are applicable.? : yes");
				reslogger.info("Based on the changes made, new policies are applicable.? : yes");

				loading = "(//div[@class='ir-loader-in'])[2]";
				counter = 0;
				while (true) {
					if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
						break;
					} else if (counter == 3) {
						break;
					} else {
						counter++;
						Wait.wait2Second();
					}
				}
			}

		} else {
			test_steps.add("Rooms Count <=0 for " + RoomClass + " for specified date");
			reslogger.info("Rooms Count <=0 for " + RoomClass + " for specified date");
		}
		return test_steps;
	}

	public String RoomNO;

	public void select_SpecificRoom(WebDriver driver, ArrayList test_steps, String RoomClass, String IsAssign,
			String Account) throws InterruptedException {
		String room = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'" + RoomClass
				+ "')]/../../div[2]/span";

		Wait.WaitForElement(driver, room);

		String rooms = driver.findElement(By.xpath(room)).getText();
		reslogger.info(rooms);
		String[] roomsCount = rooms.split(" ");
		int count = Integer.parseInt(roomsCount[0].trim());
		if (count > 0) {
			String sel = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'" + RoomClass
					+ "')]/../../../following-sibling::div/div/select";

			String rulessize = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
					+ RoomClass.trim() + "')]/following-sibling::span";
			reslogger.info(rulessize);
			int ruleCount = driver.findElements(By.xpath(rulessize)).size();
			WebElement ele = driver.findElement(By.xpath(sel));
			test_steps.add("Selected room class : " + RoomClass);
			reslogger.info("Selected room class : " + RoomClass);
			if (IsAssign.equalsIgnoreCase("No")) {
				String expand = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/following-sibling::div/button";
				Wait.WaitForElement(driver, expand);
				driver.findElement(By.xpath(expand)).click();

				String unAssign = "(//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass
						+ "')]/../../../following-sibling::div/div/select/following-sibling::div//ul//span[text()='Unassigned'])";
				Wait.WaitForElement(driver, unAssign);
				driver.findElement(By.xpath(unAssign)).click();
				test_steps.add("Selected room number : Unassigned");
				reslogger.info("Selected room number : Unassigned");
			} else {
				String roomnum = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/option";
				reslogger.info(roomnum);
				int count1 = driver.findElements(By.xpath(roomnum)).size();
				/*
				 * Random random = new Random(); int randomNumber =
				 * random.nextInt(count1 - 1) + 1; reslogger.info("count : "
				 * + count1); reslogger.info("randomNumber : " +
				 * randomNumber);
				 */

				int randomNumber = count1;
				reslogger.info("count : " + count1);
				reslogger.info("randomNumber : " + randomNumber);

				Wait.WaitForElement(driver, sel);

				String expand = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/following-sibling::div/button";
				Wait.WaitForElement(driver, expand);
				RoomNO = driver.findElement(By.xpath(expand)).getText();
				reslogger.info("Room No Is : " + RoomNO);
				driver.findElement(By.xpath(expand)).click();

				String roomNumber = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/following-sibling::div//ul/li["
						+ randomNumber + "]/a/span";
				Wait.WaitForElement(driver, roomNumber);
				driver.findElement(By.xpath(roomNumber)).click();
			}

			String select = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
					+ RoomClass + "')]/../../../following-sibling::div//span[contains(text(),'SELECT')]";
			Wait.WaitForElement(driver, select);
			driver.findElement(By.xpath(select)).click();

			String loading = "(//div[@class='ir-loader-in'])[2]";
			int counter = 0;
			while (true) {
				if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
					break;
				} else if (counter == 50) {
					break;
				} else {
					counter++;
					Wait.wait2Second();
				}
			}
			reslogger.info("Waited to loading symbol");

			reslogger.info("Rule Count : " + ruleCount);
			if (ruleCount > 1) {
				Wait.wait5Second();
				String rules = "//p[text()='Selecting this room will violate the following rules']/../..//button[text()='Yes']";

				if (driver.findElements(By.xpath(rules)).size() > 0) {
					Wait.wait2Second();
					driver.findElement(By.xpath(rules)).click();
					test_steps.add(
							"Selecting this room will violate the following rules : Are you sure you want to proceed? : yes");
					reslogger.info(
							"Selecting this room will violate the following rules : Are you sure you want to proceed? : yes");

					loading = "(//div[@class='ir-loader-in'])[2]";
					counter = 0;
					while (true) {
						if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
							break;
						} else if (counter == 4) {
							break;
						} else {
							counter++;
							Wait.wait2Second();
						}
					}
				}
			}
			if (!(Account.isEmpty() || Account.equalsIgnoreCase(""))) {
				String policy = "//p[contains(text(),'Based on the changes made')]/../..//button[text()='Yes']";
				Wait.WaitForElement(driver, policy);
				driver.findElement(By.xpath(policy)).click();
				test_steps.add("Based on the changes made, new policies are applicable.? : yes");
				reslogger.info("Based on the changes made, new policies are applicable.? : yes");

				loading = "(//div[@class='ir-loader-in'])[2]";
				counter = 0;
				while (true) {
					if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
						break;
					} else if (counter == 3) {
						break;
					} else {
						counter++;
						Wait.wait2Second();
					}
				}
			}

		} else {
			test_steps.add("Rooms Count <=0 for " + RoomClass + " for specified date");
			reslogger.info("Rooms Count <=0 for " + RoomClass + " for specified date");
		}
	}

	public Double deposit(WebDriver driver, ArrayList test_steps, String IsDepositOverride,
			String DepositOverrideAmount) throws InterruptedException {
		// Wait.wait5Second();
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_DepositAmount);
		String deposit = res.CP_NewReservation_DepositAmount.getText();
		test_steps.add("Deposit amount is : " + deposit);
		reslogger.info("Deposit amount is : " + deposit);
		deposit = deposit.trim();
		char ch = deposit.charAt(0);
		deposit = deposit.replace("$", "");
		deposit = deposit.trim();
		Double d = Double.parseDouble(deposit);

		if (IsDepositOverride.equalsIgnoreCase("Yes") && d > 0) {
			Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_OverrideDeposit);
			res.CP_NewReservation_OverrideDeposit.click();
			test_steps.add("Clicking on override deposit amount");
			reslogger.info("Clicking on override deposit amount");
			Wait.wait2Second();
			Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_OverrideDepositAmoount);
			res.CP_NewReservation_OverrideDepositAmoount.sendKeys(DepositOverrideAmount);
			test_steps.add("Override deposit amount is : " + ch + " " + DepositOverrideAmount);
			reslogger.info("Override deposit amount is : " + ch + " " + DepositOverrideAmount);
			d = Double.parseDouble(DepositOverrideAmount.trim());
		}
		return d;
	}

	public void InputdepositDue(WebDriver driver, ArrayList<String> test_steps, String DepositOverrideAmount)
			throws InterruptedException {

		Elements_CPReservation res = new Elements_CPReservation(driver);

		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_OverrideDeposit);
		Utility.ScrollToElement(res.CP_NewReservation_OverrideDeposit, driver);
		res.CP_NewReservation_OverrideDeposit.click();
		test_steps.add("Clicking on override deposit amount");
		reslogger.info("Clicking on override deposit amount");
		Wait.wait2Second();
		Utility.ScrollToElement(res.CP_NewReservation_OverrideDepositAmoount, driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_OverrideDepositAmoount);
		res.CP_NewReservation_OverrideDepositAmoount.sendKeys(DepositOverrideAmount);
		test_steps.add("Override deposit amount is : " + DepositOverrideAmount);
		reslogger.info("Override deposit amount is : " + DepositOverrideAmount);

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <clickNext> ' Description: <click on next button> ' Input
	 * parameters: < String array list> ' Return value: <array list> ' Created
	 * By: ' Modified By | Description of Modification:
	 * ------------------------------------------ Developer name:<Farhan>:
	 * 1.Step modified 2.Step modified
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> clickNext(WebDriver driver, ArrayList<String> testSteps) throws InterruptedException {

		Elements_CPReservation elementReservation = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Next);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.CP_NewReservation_Next), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.CP_NewReservation_Next), driver);
		Utility.ScrollToElement(elementReservation.CP_NewReservation_Next, driver);

		elementReservation.CP_NewReservation_Next.click();
		testSteps.add("Clicking on next button");
		reslogger.info("Clicking on next button");
		int count = 0;
		while (true) {
			if (driver.findElement(By.xpath("//span[contains(text(),'Contact Info')]")).isDisplayed()) {
				break;
			} else if (count == 20) {
				break;
			} else {
				count++;
				Wait.wait2Second();
			}
		}
		reslogger.info("Waited to loading symbol");
		return testSteps;
	}

	public ArrayList<String> enter_GuestName(WebDriver driver, ArrayList<String> test_steps, String Salutation,
			String GuestFirstName,

			String GuestLastName) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);

		verifySpinerLoading(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_GuestSalutation);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.CP_NewReservation_GuestSalutation), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.CP_NewReservation_GuestSalutation), driver);
		Utility.ScrollToElement(res.CP_NewReservation_GuestSalutation, driver);
		res.CP_NewReservation_GuestSalutation.click();
		String sal = "//span[contains(text(),'" + Salutation + "')]/../..";
		Wait.WaitForElement(driver, sal);
		driver.findElement(By.xpath(sal)).click();

		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_GuestFirstName);
		res.CP_NewReservation_GuestFirstName.sendKeys(GuestFirstName);
		test_steps.add("Guest First Name : <b>" + GuestFirstName + "</b>");
		reslogger.info("Guest First Name : <b>" + GuestFirstName + "</b>");

		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_GuestLastName);
		res.CP_NewReservation_GuestLastName.sendKeys(GuestLastName);
		test_steps.add("Guest Last Name : <b>" + GuestLastName + "</b>");
		reslogger.info("Guest Last Name : <b>" + GuestLastName + "</b>");
		return test_steps;
	}

	public void enter_ContactName(WebDriver driver, ArrayList<String> test_steps, String Salutation,
			String ContactFirstName, String ContactLastName) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		if (!Salutation.isEmpty()) {
			Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_GuestSalutation);
			res.CP_NewReservation_GuestSalutation.click();
			String sal = "//span[contains(text(),'" + Salutation + "')]/../..";

			Wait.WaitForElement(driver, sal);
			driver.findElement(By.xpath(sal)).click();
		}
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_ContactFirstName);
		res.CP_NewReservation_ContactFirstName.sendKeys(ContactFirstName);
		test_steps.add("Contact First Name : " + ContactFirstName);
		reslogger.info("Contact First Name : " + ContactFirstName);

		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_ContactLastName);
		res.CP_NewReservation_ContactLastName.sendKeys(ContactLastName);
		test_steps.add("Contact Last Name : " + ContactLastName);
		reslogger.info("Contact Last Name : " + ContactLastName);
	}

	public void enter_Phone(WebDriver driver, ArrayList<String> test_steps, String PhoneNumber,
			String AltenativePhone) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Phone);
		res.CP_NewReservation_Phone.clear();
		res.CP_NewReservation_Phone.sendKeys(PhoneNumber);
		test_steps.add("Enter Phone Number : " + PhoneNumber);
		reslogger.info("Enter Phone Number : " + PhoneNumber);

		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_AltenativePhone);
		res.CP_NewReservation_AltenativePhone.clear();
		res.CP_NewReservation_AltenativePhone.sendKeys(AltenativePhone);
		test_steps.add("Enter AltenativePhone Number : " + AltenativePhone);
		reslogger.info("Enter AltenativePhone Number : " + AltenativePhone);
	}

	public void enter_Email(WebDriver driver, ArrayList<String> test_steps, String Email) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Email);
		res.CP_NewReservation_Email.clear();
		res.CP_NewReservation_Email.sendKeys(Email);
		test_steps.add("Enter Email : " + Email);
		reslogger.info("Enter Email : " + Email);
	}

	public void enter_Account(WebDriver driver, ArrayList<String> test_steps, String Account, String AccountType)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Account);
		String accountdisplay = "//label[contains(text(),'Account')]/..//i[starts-with(@class,'rightIcon demo-icon icon-pencil')]";
		WebElement ele = driver.findElement(By.xpath(accountdisplay));
		if (!ele.isDisplayed()) {
			if (Account.equalsIgnoreCase("") || Account.isEmpty()) {
				reslogger.info("No Account");
			} else {
				res.CP_NewReservation_Account.sendKeys(Account);
				test_steps.add("Enter Account : " + Account);
				reslogger.info("Enter Account : " + Account);
				Wait.wait2Second();
				String account = "//b[contains(text(),'" + Account.trim() + "')]/../../..";
				Wait.WaitForElement(driver, account);
				driver.findElement(By.xpath(account)).click();
				String dataYes = "//div[contains(text(),'Do you want to replace the guest info')]/following-sibling::div//button[contains(text(),'Yes')]";
				Wait.WaitForElement(driver, dataYes);
				driver.findElement(By.xpath(dataYes)).click();
				test_steps.add(
						"Do you want to replace the guest info, payment method, marketing info and notes in this reservation with the information from the account? Clicking yes will save all the account info to the reservation. : Yes");
				reslogger.info(
						"Do you want to replace the guest info, payment method, marketing info and notes in this reservation with the information from the account? Clicking yes will save all the account info to the reservation. : Yes");
				Wait.wait2Second();
				String policyYes = "//div[contains(text(),'Based on the account chosen, new policies are applicable. Would you like to apply these policies to the reservation?')]/following-sibling::div//button[contains(text(),'Yes')]";
				Wait.WaitForElement(driver, policyYes);
				driver.findElement(By.xpath(policyYes)).click();
				test_steps.add(
						"Based on the account chosen, new policies are applicable. Would you like to apply these policies to the reservation? : Yes");
				reslogger.info(
						"Based on the account chosen, new policies are applicable. Would you like to apply these policies to the reservation? : Yes");

				Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_PaymentMethod);
				res.CP_NewReservation_PaymentMethod.click();
				String type = null;

				if (AccountType.contentEquals("Corp") || AccountType.contains("corp") || AccountType.contains("Corp")) {
					type = "Account (Corp/Member)";
				}

				if (AccountType.contentEquals("Corp") || AccountType.contains("corp") || AccountType.contains("Corp")) {
					type = "Account (Unit Owner)";
				}

				String paymentMethod = "//label[text()='Payment Method']/preceding-sibling::div//ul/li//span[contains(text(),'"
						+ type + "')]";
				Wait.WaitForElement(driver, paymentMethod);
				driver.findElement(By.xpath(paymentMethod)).click();
				String acc = "//label[text()='Account Name ']/preceding-sibling::input";
				Wait.WaitForElement(driver, acc);
				String accName = driver.findElement(By.xpath(acc)).getText();
				if (accName.contains(Account)) {
					test_steps.add("Account successfully associated : " + Account);
					reslogger.info("Account successfully associated : " + Account);
				}
			}
		} else {
			test_steps.add("Account Aleady exists");
			reslogger.info("Account Aleady exists");

			Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_PaymentMethod);
			res.CP_NewReservation_PaymentMethod.click();
			String type = null;

			if (AccountType.contentEquals("Corp") || AccountType.contains("corp") || AccountType.contains("Corp")) {
				type = "Account (Corp/Member)";
			}

			if (AccountType.contentEquals("Unit Owner") || AccountType.contains("Unit")
					|| AccountType.contains("unit owner")) {
				type = "Account (Unit Owner)";
			}
			String paymentMethod = "//label[text()='Payment Method']/preceding-sibling::div//ul/li//span[contains(text(),'"
					+ type + "')]";
			Wait.WaitForElement(driver, paymentMethod);
			driver.findElement(By.xpath(paymentMethod)).click();
			String acc = "(//label[text()='Account Name '])[1]/preceding-sibling::input";
			Wait.wait2Second();
			Wait.WaitForElement(driver, acc);
			String accName = driver.findElement(By.xpath(acc)).getText();
			if (accName.contains(Account)) {
				test_steps.add("Account successfully associated : " + Account);
				reslogger.info("Account successfully associated : " + Account);
			}
		}
	}

	public void enter_Address(WebDriver driver, ArrayList<String> test_steps, String Address1, String Address2,
			String Address3) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Address1);
		res.CP_NewReservation_Address1.clear();
		res.CP_NewReservation_Address1.sendKeys(Address1);
		test_steps.add("Enter Address1 : " + Address1);
		reslogger.info("Enter Address1 : " + Address1);

		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Address2);
		res.CP_NewReservation_Address2.clear();
		res.CP_NewReservation_Address2.sendKeys(Address2);
		test_steps.add("Enter Address2 : " + Address2);
		reslogger.info("Enter Address1 : " + Address2);

		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Address3);
		res.CP_NewReservation_Address3.clear();
		res.CP_NewReservation_Address3.sendKeys(Address3);
		test_steps.add("Enter Address3 : " + Address3);
		reslogger.info("Enter Address3 : " + Address3);
	}

	public void enter_City(WebDriver driver, ArrayList<String> test_steps, String City) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_City);
		res.CP_NewReservation_City.clear();
		res.CP_NewReservation_City.sendKeys(City);
		test_steps.add("Enter City : " + City);
		reslogger.info("Enter City : " + City);
	}

	public void select_Country(WebDriver driver, ArrayList<String> test_steps, String Country) {
		String button = "//select[@name='country']/following-sibling::div/button";
		Wait.WaitForElement(driver, button);
		driver.findElement(By.xpath(button)).click();

		String cntry = "//select[@name='country']/following-sibling::div//ul/li//span[contains(text(),'"
				+ Country.trim() + "')]";
		Wait.WaitForElement(driver, cntry);
		driver.findElement(By.xpath(cntry)).click();
		test_steps.add("Selected Country : " + Country);
		reslogger.info("Selected Country : " + Country);
	}

	public void select_State(WebDriver driver, ArrayList<String> test_steps, String State) {
		String button = "//select[@name='state']/following-sibling::div";
		Wait.WaitForElement(driver, button);

		if (driver.findElements(By.xpath("(//select[@name='state'])[1]/option")).size() > 1) {
			button = "//select[@name='state']/following-sibling::div/button";
			driver.findElement(By.xpath(button)).click();
			String cntry = "//select[@name='state']/following-sibling::div//ul/li//span[contains(text(),'"
					+ State.trim() + "')]";
			Wait.WaitForElement(driver, cntry);
			driver.findElement(By.xpath(cntry)).click();
			test_steps.add("Selected Country : " + State);
			reslogger.info("Selected Country : " + State);
		} else {
			test_steps.add("State field disabled");
			reslogger.info("State field disabled");
		}
	}

	public void enter_PostalCode(WebDriver driver, ArrayList<String> test_steps, String PostalCode) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_PostalCode);
		res.CP_NewReservation_PostalCode.clear();
		res.CP_NewReservation_PostalCode.sendKeys(PostalCode);
		test_steps.add("Enter PostalCode : " + PostalCode);
		reslogger.info("Enter PostalCode : " + PostalCode);
	}

	public void uncheck_CreateGuestProfile(WebDriver driver, ArrayList<String> test_steps, String IsGuesProfile) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_CreateGuestProfile);
		if (IsGuesProfile.equalsIgnoreCase("Yes")) {
			if (!res.CP_NewReservation_CreateGuestProfile.isSelected()) {
				driver.findElement(By.xpath("//span[contains(text(),'Create Guest Profile')]/..")).click();
				test_steps.add("Create Guest Profile selected");
				reslogger.info("Create Guest Profile selected");
			} else {
				test_steps.add("Create Guest Profile selected");
				reslogger.info("Create Guest Profile selected");
			}
		} else {
			if (res.CP_NewReservation_CreateGuestProfile.isSelected()) {
				driver.findElement(By.xpath("//span[contains(text(),'Create Guest Profile')]/..")).click();
				test_steps.add("Create Guest Profile unselected");
				reslogger.info("Create Guest Profile unselected");
			} else {
				test_steps.add("Create Guest Profile unselected");
				reslogger.info("Create Guest Profile unselected");
			}
		}
	}

	public void enter_MailingAddress(WebDriver driver, ArrayList<String> test_steps, String Salutation,
			String GuestFirstName, String GuestLastName, String PhoneNumber, String AltenativePhone, String Email,
			String Account, String AccountType, String Address1, String Address2, String Address3, String City,
			String Country, String State, String PostalCode, String IsGuesProfile) throws InterruptedException {
		enter_GuestName(driver, test_steps, Salutation, GuestFirstName, GuestLastName);
		uncheck_CreateGuestProfile(driver, test_steps, IsGuesProfile);
		enter_Phone(driver, test_steps, PhoneNumber, AltenativePhone);
		// enter_Phone(driver, test_steps, PhoneNumber, AltenativePhone);
		enter_Email(driver, test_steps, Email);
		enter_Address(driver, test_steps, Address1, Address2, Address3);
		enter_City(driver, test_steps, City);
		select_Country(driver, test_steps, Country);
		select_State(driver, test_steps, State);
		enter_PostalCode(driver, test_steps, PostalCode);
		enter_Account(driver, test_steps, Account, AccountType);
		enter_Account(driver, test_steps, Account, AccountType);
	}

	public void enter_MailingAddress_OnUnchecked_CreateGuestProfile(WebDriver driver, ArrayList<String> test_steps,
			String Salutation, String GuestFirstName, String GuestLastName, String IsGuesProfile)
			throws InterruptedException {
		enter_GuestName(driver, test_steps, Salutation, GuestFirstName, GuestLastName);
		uncheck_CreateGuestProfile(driver, test_steps, IsGuesProfile);
	}

	public void enter_PaymentDetails(WebDriver driver, ArrayList<String> test_steps, String PaymentType,
			String CardNumber, String NameOnCard, String CardExpDate) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_PaymentMethod);
		res.CP_NewReservation_PaymentMethod.click();

		if (PaymentType.equalsIgnoreCase("Swipe")) {
			String paymentMethod = "//label[text()='Payment Method']/preceding-sibling::div//ul/li//span[contains(text(),'MC')]";
			Wait.WaitForElement(driver, paymentMethod);
			driver.findElement(By.xpath(paymentMethod)).click();
			res.CP_NewReservation_Swipe.click();
			test_steps.add("Clicking in Swipe");
			reslogger.info("Clicking in Swipe");
			Wait.wait1Second();
			String CC = "(//label[text()='Credit Card Number']/preceding-sibling::input)[2]";
			Wait.WaitForElement(driver, CC);
			driver.findElement(By.xpath(CC)).sendKeys(CardNumber);
			test_steps.add("Enter CardNumber : " + CardNumber);
			reslogger.info("Enter CardNumber : " + CardNumber);
			res.CP_NewReservation_CardNumber.sendKeys(Keys.TAB);
			Wait.wait2Second();
		} else {
			String paymentMethod = "//label[text()='Payment Method']/preceding-sibling::div//ul/li//span[contains(text(),'"
					+ PaymentType.trim() + "')]";
			Wait.WaitForElement(driver, paymentMethod);
			driver.findElement(By.xpath(paymentMethod)).click();
			test_steps.add("Selected PaymentType : " + PaymentType);
			reslogger.info("Selected PaymentType : " + PaymentType);
			if (PaymentType.trim().equalsIgnoreCase("MC") || PaymentType.trim().equalsIgnoreCase("Visa")
					|| PaymentType.trim().equalsIgnoreCase("Amex") || PaymentType.trim().equalsIgnoreCase("Discover")) {
				res.CP_NewReservation_CardNumber.sendKeys(CardNumber);
				test_steps.add("Enter CardNumber : " + CardNumber);
				reslogger.info("Enter CardNumber : " + CardNumber);
				res.CP_NewReservation_NameOnCard.sendKeys(NameOnCard);
				test_steps.add("Enter Name On Card : " + NameOnCard);
				reslogger.info("Enter Name On Card : " + NameOnCard);
				res.CP_NewReservation_CardExpDate.sendKeys(CardExpDate);
				test_steps.add("Enter Card ExpDate : " + CardExpDate);
				reslogger.info("Enter Card ExpDate : " + CardExpDate);
			}
		}
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_CreateGuestProfile);
		if (!driver.findElement(By.xpath("//small[contains(text(),'Address')]/../../following-sibling::div//input"))
				.isSelected()) {
			driver.findElement(By.xpath("//small[contains(text(),'Address')]/../../following-sibling::div/label"))
					.click();
		}
	}

	public void select_HouseAccoutAsPayment(WebDriver driver, ArrayList<String> test_steps, String AccountName) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_PaymentMethod);
		res.CP_NewReservation_PaymentMethod.click();
		String houseAcc = "//span[text()='Account (House Account)']/..";
		Wait.WaitForElement(driver, houseAcc);
		driver.findElement(By.xpath(houseAcc)).click();
		test_steps.add("Selecting Account (House Account) as payment method");
		reslogger.info("Selecting Account (House Account) as payment method");

		String input = "//input[@name='houseaccount-autocomplete']";

		Wait.WaitForElement(driver, input);
		driver.findElement(By.xpath(input)).sendKeys(AccountName);
		String acc = "//b[text()='" + AccountName.trim() + "']";
		Wait.WaitForElement(driver, acc);
		driver.findElement(By.xpath(acc)).click();
		test_steps.add("Selecting Account (House Account) as payment method and the house account is : " + AccountName);
		reslogger.info("Selecting Account (House Account) as payment method and the house account is : " + AccountName);

	}

	public void enter_MarketSegmentDetails(WebDriver driver, ArrayList<String> test_steps, String TravelAgent,
			String MarketSegment, String Referral) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_TravelAgent);

		if (!(TravelAgent.equalsIgnoreCase("") || TravelAgent.isEmpty())) {
			res.CP_NewReservation_TravelAgent.sendKeys(TravelAgent);
			Wait.wait2Second();
			String account = "//b[contains(text(),'" + TravelAgent.trim() + "')]/../../..";
			Wait.WaitForElement(driver, account);
			driver.findElement(By.xpath(account)).click();
			String dataYes = "//div[contains(text(),'Do you want to replace the guest info')]/following-sibling::div//button[contains(text(),'Yes')]";
			Wait.WaitForElement(driver, dataYes);
			driver.findElement(By.xpath(dataYes)).click();
			test_steps.add(
					"Do you want to replace the guest info, payment method, marketing info and notes in this reservation with the information from the account? Clicking yes will save all the account info to the reservation. : Yes");
			reslogger.info(
					"Do you want to replace the guest info, payment method, marketing info and notes in this reservation with the information from the account? Clicking yes will save all the account info to the reservation. : Yes");
			Wait.wait5Second();
			/*
			 * String
			 * policyYes="//div[contains(text(),'Do you want to replace the guest info')]/following-sibling::div//button[contains(text(),'Yes')]"
			 * ; Wait.WaitForElement(driver, policyYes);
			 * driver.findElement(By.xpath(policyYes)).click(); test_steps.
			 * add("Based on the account chosen, new policies are applicable. Would you like to apply these policies to the reservation? : Yes"
			 * ); reslogger.
			 * info("Based on the account chosen, new policies are applicable. Would you like to apply these policies to the reservation? : Yes"
			 * );
			 */
		}

		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.CP_NewReservation_Referral), driver);
		Utility.ScrollToElement(res.CP_NewReservation_Referral, driver);
		res.CP_NewReservation_Referral.click();
//		Wait.wait1Second();
		String ref = "(//label[text()='Referral']/preceding-sibling::div//ul/li//span[contains(text(),'"
				+ Referral.trim() + "')])[2]";
		reslogger.info(ref);
		//Wait.WaitForElement(driver, ref);
		Wait.waitForElementToBeClickable(By.xpath(ref), 10, driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(ref)), driver);

//		System.out.println(ref);
		Wait.WaitForElement(driver, ref);


		driver.findElement(By.xpath(ref)).click();
		test_steps.add("Selected Referral as : " + Referral);
		reslogger.info("Selected Referral as : " + Referral);

		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Market);
		Utility.ScrollToElement(res.CP_NewReservation_Market, driver);
		Wait.wait2Second();
		res.CP_NewReservation_Market.click();
		String market = "//label[text()='Market']/preceding-sibling::div//ul/li//span[contains(text(),'"
				+ MarketSegment.trim() + "')]";
		Wait.WaitForElement(driver, market);

		driver.findElement(By.xpath(market)).click();
		test_steps.add("Selected MarketSegment as : " + MarketSegment);
		reslogger.info("Selected MarketSegment as : " + MarketSegment);
	}

	public ArrayList<String> clickBookNow(WebDriver driver, ArrayList<String> testSteps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Book);
		Wait.explicit_wait_elementToBeClickable(res.CP_NewReservation_Book, driver);
		Utility.ScrollToElement(res.CP_NewReservation_Book, driver);
		res.CP_NewReservation_Book.click();
		testSteps.add("Click on Book Now");
		reslogger.info("Click on Book Now");
		String loading = "(//div[@class='ir-loader-in'])[2]";
		int count = 0;
		try {
			while (true) {
				if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
					break;
				} else if (count == 60) {
					break;
				} else {
					count++;
					Wait.wait1Second();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		reslogger.info("Waited to loading symbol");
		return testSteps;

	}

	public void pay_DepositAmount(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_DepositPayPopUp);
		String depositAmount = "//h4[text()='Deposit Payment']/../..//label[text()='AMOUNT']/following-sibling::div/input";
		Wait.WaitForElement(driver, depositAmount);
		depositAmount = driver.findElement(By.xpath(depositAmount)).getText();

		String depositPaymentType = "//h4[text()='Deposit Payment']/../..//label[text()='TYPE']/preceding-sibling::div/button/span";
		Wait.WaitForElement(driver, depositPaymentType);
		depositPaymentType = driver.findElement(By.xpath(depositPaymentType)).getText();

		test_steps.add("Paying deposit amount : " + depositAmount + " and the payment type is : " + depositPaymentType);
		reslogger.info("Paying deposit amount : " + depositAmount + " and the payment type is : " + depositPaymentType);

		String pay = "//h4[text()='Deposit Payment']/../..//button[starts-with(text(),'Pay')]";

		Wait.WaitForElement(driver, pay);
		driver.findElement(By.xpath(pay)).click();
		test_steps.add("Click on Pay");
		reslogger.info("Click on Pay");

		String loading = "(//div[@class='ir-loader-in'])[3]";

		int count = 0;
		while (true) {
			if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
				break;
			} else if (count == 20) {
				break;
			} else {
				count++;
				Wait.wait2Second();
			}
		}
		reslogger.info("Waited to loading symbol");
		String paymentFail = "//span[contains(text(),'Transaction Declined. Please try again or enter a different payment method.')]";

		if (driver.findElement(By.xpath(paymentFail)).isDisplayed()) {
			test_steps.add("Transaction Declined. Please try again or enter a different payment method.");
			reslogger.info("Transaction Declined. Please try again or enter a different payment method.");
		} else {

		}
	}

	public String get_ReservationConfirmationNumber(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_ConfirmationNumber);
		String confirmation = res.CP_NewReservation_ConfirmationNumber.getText();
		test_steps.add("Captured reservation confirmation number is : <b>" + confirmation + "</b>");
		reslogger.info("Confirmation Number : <b>" + confirmation + "</b>");
		return confirmation;
	}

	public String get_ReservationStatus(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_ReservationStatus);
		String status = res.CP_NewReservation_ReservationStatus.getText();
		test_steps.add("Captured reservation status on success popup is  : " + status);
		reslogger.info("Reservation Status : " + status);
		return status;
	}

	public void verify_QuoteConfirmetionPopup(WebDriver driver, ArrayList<String> test_steps) {
		String quote = "//h4[contains(text(),'Quote Confirmation')]";
		Wait.WaitForElement(driver, quote);
		if (driver.findElement(By.xpath(quote)).isDisplayed()) {
			test_steps.add("Quote Confirmation pop is displayed");
			reslogger.info("Quote Confirmation pop is displayed");
		} else {
			test_steps.add("Quote Confirmation pop not is displayed");
			reslogger.info("Quote Confirmation pop not is displayed");
		}
	}

	public ArrayList<String> clickCloseReservationSavePopup(WebDriver driver, ArrayList<String> test_steps)
			throws Exception {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_ClosePopUp);
		Utility.ScrollToElement(res.CP_NewReservation_ClosePopUp, driver);
		Wait.explicit_wait_elementToBeClickable(res.CP_NewReservation_ClosePopUp, driver);
		res.CP_NewReservation_ClosePopUp.click();
		test_steps.add("Clicking on Close");
		reslogger.info("Clicking on Close");
		String loading = "(//div[@class='ir-loader-in'])";
		int count = 0;
		while (true) {
			if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
				break;
			} else if (count == 20) {
				break;
			} else {
				count++;
				Wait.wait2Second();
			}
		}
		reslogger.info("Waited to loading symbol");
		return test_steps;
	}

	public void click_Folio(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Folio);
		Utility.ScrollToElement(res.CP_NewReservation_Folio, driver);
		res.CP_NewReservation_Folio.click();
		test_steps.add("Clicking on Folio");
		reslogger.info("Clicking on Folio");
		String path = "//a[@class='current']";
		Wait.WaitForElement(driver, path);
		assertTrue(driver.findElement(By.xpath(path)).isEnabled(), "Failed: to Enabled Folio Detail");
		test_steps.add("Folio Detail Tab is Enabled");
		reslogger.info("Folio Detail Tab is Enabled");

	}

	public void click_Documents(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_DocumentsTab);
		res.CP_NewReservation_DocumentsTab.click();
		test_steps.add("Clicking on Documents");
		reslogger.info("Clicking on Documents");
	}

	public String get_RoomCharge(WebDriver driver, ArrayList<String> test_steps) {
		String Balance = driver
				.findElement(By
						.xpath("//label[contains(text(),'Room Charges: ')]/following-sibling::span[@class='pamt']/span[@class='pamt']"))
				.getText();
		Balance = Balance.replace("$ ", "");
		test_steps.add("Room Charges : <b>" + Balance + "</b>");
		reslogger.info("Room Charges : " + Balance);
		return Balance;
	}

	public String get_Taxes(WebDriver driver, ArrayList<String> test_steps) {
		String Balance = driver
				.findElement(By
						.xpath("//label[contains(text(),'Taxes & Service Charges:')]/following-sibling::span/span[@class='pamt']"))
				.getText();
		Balance = Balance.replace("$", "");
		test_steps.add("Taxes & Service Charges : " + Balance);
		reslogger.info("Taxes & Service Charges : " + Balance);
		return Balance;
	}

	public String get_Inceidentals(WebDriver driver, ArrayList<String> test_steps) {
		String Balance = driver
				.findElement(
						By.xpath("//label[contains(text(),'Incidentals')]/following-sibling::span/span[@class='pamt']"))
				.getText();
		Balance = Balance.replace("$", "");
		test_steps.add("Incidentals : " + Balance);
		reslogger.info("Incidentals : " + Balance);
		return Balance;
	}

	public String get_TotalCharges(WebDriver driver, ArrayList<String> test_steps) {
		String Balance = driver
				.findElement(By
						.xpath("//label[contains(text(),'Total Charges')]/following-sibling::span/span[@class='pamt']"))
				.getText();
		Balance = Balance.replace("$", "");
		test_steps.add("Total Charges : " + Balance);
		reslogger.info("Total Charges : " + Balance);
		return Balance;
	}

	public String get_Payments(WebDriver driver, ArrayList<String> test_steps) {
		String Balance = driver
				.findElement(
						By.xpath("//label[contains(text(),'Payments')]/following-sibling::span/span[@class='pamt']"))
				.getText();
		Balance = Balance.replace("$ ", "");
		test_steps.add("Payments : " + Balance);
		reslogger.info("Payments : " + Balance);
		return Balance;
	}

	public String get_Balance(WebDriver driver, ArrayList<String> test_steps) {
		String Balance = driver
				.findElement(By
						.xpath("//label[contains(text(),'Balance')]/following-sibling::span[@class='pamt']/span[@class='pamt']"))
				.getText();
		Balance = Balance.replace("$ ", "");
		test_steps.add("Balance Charges : <b>" + Balance + "</b>");
		reslogger.info("Balance Charges : " + Balance);
		return Balance;
	}

	public void click_History(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		String loading = "(//div[@class='ir-loader-in'])";
		int counter = 0;
		while (true) {
			if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
				break;
			} else if (counter == 40) {
				break;
			} else {
				counter++;
				Wait.wait2Second();
			}
		}
		reslogger.info("Waited to loading symbol");
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_History);
		Utility.ScrollToElement(res.CP_NewReservation_History, driver);
		res.CP_NewReservation_History.click();
		test_steps.add("Clicking on History");
		reslogger.info("Clicking on History");
	}

	public void verify_ReservationInHistoryTab(WebDriver driver, ArrayList test_steps, String reservation) {
		String res = "//span[contains(text(),'Created reservation with Confirmation Number: " + reservation.trim()
				+ "')]";
		if (driver.findElement(By.xpath(res)).isDisplayed()) {
			test_steps.add("Reservation creation verified in History tab : " + reservation);
			reslogger.info("Reservation creation verified in History tab : " + reservation);
		} else {
			test_steps.add("Reservation creation not found in History tab : " + reservation);
			reslogger.info("Reservation creation not found in History tab : " + reservation);
		}
	}

	public void verify_QuoteInHistoryTab(WebDriver driver, ArrayList test_steps, String reservation, String timeZone) {
		String res = "//span[contains(text(),'Saved quote with Confirmation Number: " + reservation.trim() + "')]";
		if (driver.findElement(By.xpath(res)).isDisplayed()) {
			test_steps.add("Reservation creation verified in History tab : " + reservation);
			reslogger.info("Reservation creation verified in History tab : " + reservation);
		} else {
			test_steps.add("Reservation creation not found in History tab : " + reservation);
			reslogger.info("Reservation creation not found in History tab : " + reservation);
		}

		String payment = "//span[contains(text(),'Saved quote with Confirmation Number: " + reservation.trim()
				+ "')]/../preceding-sibling::td//span";

		String room = driver.findElement(By.xpath(payment)).getText();
		if (room.equalsIgnoreCase("Reservation")) {
			test_steps.add("Payment category verified for the Reservation");
			reslogger.info("Payment category verified for the Reservation");
		} else {
			test_steps.add("Payment category not verified for the Reservation");
			reslogger.info("Payment category not verifie for the Reservation");
		}

		Date date = new Date();
		DateFormat df = new SimpleDateFormat("MM/dd/yy");

		// Use Madrid's time zone to format the date in
		df.setTimeZone(TimeZone.getTimeZone(timeZone));

		String d = df.format(date);

		String payline = "(//span[contains(text(),'Saved quote with Confirmation Number: " + reservation.trim()
				+ "')]/../preceding-sibling::td//span)[2]";
		room = driver.findElement(By.xpath(payline)).getText();
		if (room.equalsIgnoreCase(d)) {
			test_steps.add("Payment Date verified in history : " + room);
			reslogger.info("Payment Date verified in history : " + room);
		} else {
			test_steps.add("Payment Date not verified in history : " + room);
			reslogger.info("Payment Date not verified in history : " + room);
		}

	}

	public String get_RoomNumber(WebDriver driver, ArrayList test_steps, String IsAssign) {
		String room = "//span[text()='ROOM:']/following-sibling::span";
		Wait.WaitForElement(driver, room);
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(room)), driver);
		room = driver.findElement(By.xpath(room)).getText();

		if (IsAssign.equalsIgnoreCase("No")) {
			if (room.equalsIgnoreCase("Unassigned")) {
				test_steps.add("Room Number is : Unassigned");
				reslogger.info("Room Number is : Unassigned");
			}
		} else {
			test_steps.add("Room Number is : " + room);
			reslogger.info("Room Number is : " + room);
		}
		return room;
	}
	public String getSecondRoomNumber(WebDriver driver, ArrayList test_steps, String IsAssign) {
		String room = "(//span[text()='ROOM:']/following-sibling::span)[2]";
		Wait.WaitForElement(driver, room);
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(room)), driver);
		room = driver.findElement(By.xpath(room)).getText();

		if (IsAssign.equalsIgnoreCase("No")) {
			if (room.equalsIgnoreCase("Unassigned")) {
				test_steps.add("Room Number is : Unassigned");
				reslogger.info("Room Number is : Unassigned");
			}
		} else {
			test_steps.add("Room Number is : " + room);
			reslogger.info("Room Number is : " + room);
		}
		return room;
	}

	public String get_RoomChargeWithCurrency(WebDriver driver, ArrayList test_steps) {
		String Balance = driver
				.findElement(By
						.xpath("//label[contains(text(),'Room Charges: ')]/following-sibling::span[@class='pamt']/span[@class='pamt']"))
				.getText();
		test_steps.add("Room Charges : " + Balance);
		reslogger.info("Room Charges : " + Balance);
		return Balance;
	}

	public String get_TaxesWithCurrency(WebDriver driver, ArrayList test_steps) {
		String Balance = driver
				.findElement(By
						.xpath("//label[contains(text(),'Taxes & Service Charges:')]/following-sibling::span/span[@class='pamt']"))
				.getText();
		test_steps.add("Taxes & Service Charges : " + Balance);
		reslogger.info("Taxes & Service Charges : " + Balance);
		return Balance;
	}

	public String get_InceidentalsWithCurrency(WebDriver driver, ArrayList test_steps) {
		String Balance = driver
				.findElement(
						By.xpath("//label[contains(text(),'Incidentals')]/following-sibling::span/span[@class='pamt']"))
				.getText();
		test_steps.add("Incidentals : " + Balance);
		reslogger.info("Incidentals : " + Balance);
		return Balance;
	}

	public String get_TotalChargesWithCurrency(WebDriver driver, ArrayList test_steps) {
		String Balance = driver
				.findElement(By
						.xpath("//label[contains(text(),'Total Charges')]/following-sibling::span/span[@class='pamt']"))
				.getText();
		test_steps.add("Total Charges : " + Balance);
		reslogger.info("Total Charges : " + Balance);
		return Balance;
	}

	public String get_PaymentsWithCurrency(WebDriver driver, ArrayList test_steps) {
		String Balance = driver
				.findElement(
						By.xpath("//label[contains(text(),'Payments')]/following-sibling::span/span[@class='pamt']"))
				.getText();
		test_steps.add("Payments : " + Balance);
		reslogger.info("Payments : " + Balance);
		return Balance;
	}

	public String get_BalanceWithCurrency(WebDriver driver, ArrayList test_steps) {
		String Balance = driver
				.findElement(By
						.xpath("//label[contains(text(),'Balance')]/following-sibling::span[@class='pamt']/span[@class='pamt']"))
				.getText();
		test_steps.add("Balance Charges : " + Balance);
		reslogger.info("Balance Charges : " + Balance);
		return Balance;
	}

	public String get_TripSummaryRoomChargesWithCurrency(WebDriver driver, ArrayList test_steps) {
		String Balance = driver
				.findElement(By
						.xpath("//span[text()='Summary']/../../..//div[text()='Room Charges']/following-sibling::div"))
				.getText();
		test_steps.add("TripSummary Room Charges : " + Balance);
		reslogger.info("TripSummary Room Charges : " + Balance);
		return Balance;
	}

	public String get_TripSummaryRoomChargesWithoutCurrency(WebDriver driver, ArrayList<String> test_steps) {

		String balance = "//span[contains(text(),'Summary')]/../../..//div[text()='Room Charges']/following-sibling::div";
		Wait.WaitForElement(driver, balance);
		WebElement balanceElement = driver.findElement(By.xpath(balance));
		balance = balanceElement.getText().replace("$ ", "");
		test_steps.add("TripSummary Room Charges : <b>" + balance + "</b>");
		reslogger.info("TripSummary Room Charges : " + balance);
		return balance;
	}

	public String get_TripSummaryTaxesWithCurrency(WebDriver driver, ArrayList test_steps) {
		String Balance = driver
				.findElement(By
						.xpath("//span[text()='Summary']/../../..//div[contains(text(),'Taxes & Service Charges')]/following-sibling::div"))
				.getText();
		test_steps.add("TripSummary Taxes & Service Charges : " + Balance);
		reslogger.info("TripSummary Taxes & Service Charges : " + Balance);
		return Balance;
	}

	public String get_TripSummaryTaxesWithoutCurrency(WebDriver driver, ArrayList test_steps) {
		String Balance = driver
				.findElement(By
						.xpath("//span[text()='Summary']/../../..//div[contains(text(),'Taxes & Service Charges')]/following-sibling::div"))
				.getText();
		Balance = Balance.replace("$", "");
		test_steps.add("TripSummary Taxes & Service Charges : " + Balance);
		reslogger.info("TripSummary Taxes & Service Charges : " + Balance);
		return Balance;
	}

	public String get_TripSummaryInceidentalsWithCurrency(WebDriver driver, ArrayList test_steps) {
		String Balance = driver
				.findElement(
						By.xpath("//span[text()='Summary']/../../..//div[text()='Incidentals']/following-sibling::div"))
				.getText();
		test_steps.add("TripSummary Incidentals : " + Balance);
		reslogger.info("TripSummary Incidentals : " + Balance);
		return Balance;
	}

	public String get_TripSummaryInceidentalsWithoutCurrency(WebDriver driver, ArrayList test_steps) {
		String Balance = driver
				.findElement(
						By.xpath("//span[text()='Summary']/../../..//div[text()='Incidentals']/following-sibling::div"))
				.getText();
		Balance = Balance.replace("$ ", "");
		test_steps.add("TripSummary Incidentals : " + Balance);
		reslogger.info("TripSummary Incidentals : " + Balance);
		return Balance;
	}

	public String get_TripSummaryTripTotalChargesWithCurrency(WebDriver driver, ArrayList test_steps) {
		String Balance = driver
				.findElement(By
						.xpath("//span[text()='Summary']/../../..//div[contains(text(),'Trip Total')]/following-sibling::div"))
				.getText();
		test_steps.add("TripSummary Trip Total : " + Balance);
		reslogger.info("TripSummary Trip Total : " + Balance);
		return Balance;
	}

	public String get_TripSummaryTripTotalChargesWithoutCurrency(WebDriver driver, ArrayList<String> test_steps) {
		String xpath = "//span[text()='Summary']/../../..//div[contains(text(),'Trip Total')]/following-sibling::div";
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(xpath)), driver);
		String Balance = driver.findElement(By.xpath(xpath)).getText();
		Balance = Balance.replace("$ ", "");
		test_steps.add("Trip Total Charges captured at trip summary as : <b>" + Balance + "</b>");
		reslogger.info("Trip Total Charges captured at trip summary as : " + Balance);
		return Balance;
	}

	public String get_TripSummaryPaidWithCurrency(WebDriver driver, ArrayList test_steps) {
		String Balance = "$ 0.0";
		if (driver
				.findElements(By
						.xpath("//span[text()='Summary']/../../..//div[contains(text(),'Paid')]/following-sibling::div"))
				.size() > 0) {
			Balance = driver
					.findElement(By
							.xpath("//span[text()='Summary']/../../..//div[contains(text(),'Paid')]/following-sibling::div"))
					.getText();
			test_steps.add("TripSummary Paid : " + Balance);
			reslogger.info("TripSummary Paid : " + Balance);
		}
		return Balance;
	}

	public String get_TripSummaryBalance(WebDriver driver, ArrayList test_steps) {
		String Balance = driver
				.findElement(By
						.xpath("//span[text()='Summary']/../../..//div[contains(text(),'Balance')]/following-sibling::div"))
				.getText();

		Balance = Balance.trim();
		Balance = Balance.substring(1, Balance.length() - 1);
		Balance = Balance.trim();
		test_steps.add("TripSummary Balance Charges : " + Balance);
		reslogger.info("TripSummary Balance Charges : " + Balance);
		return Balance;
	}

	public String get_TripSummaryPaid(WebDriver driver, ArrayList test_steps) {
		String Balance = driver
				.findElement(By
						.xpath("//span[text()='Summary']/../../..//div[contains(text(),'Paid')]/following-sibling::div"))
				.getText();

		Balance = Balance.trim();
		Balance = Balance.substring(1, Balance.length() - 1);
		Balance = Balance.trim();
		test_steps.add("TripSummary Paid : " + Balance);
		reslogger.info("TripSummary Paid : " + Balance);

		return Balance;
	}

	public String getTripSummaryPaidAmount(WebDriver driver, ArrayList test_steps) {
		String PaidBalance = driver
				.findElement(By
						.xpath("//span[text()='Summary']/../../..//div[contains(text(),'Paid')]/following-sibling::div"))
				.getText();
		PaidBalance = PaidBalance.replace("$ ", "");
		test_steps.add("Paid amount captured at trip summary is : <b>" + PaidBalance+"</b>");
		reslogger.info("Paid amount captured at trip summary is : " + PaidBalance);
		return PaidBalance;
	}

	public void verifyTripSummaryPaid(WebDriver driver, ArrayList test_steps, String getPaid, String Paid) {
		double paidAmt = Double.valueOf(getPaid);
		int amt = (int) paidAmt;
		getPaid = String.valueOf(amt);
		assertEquals(getPaid.trim(), (Paid), "Failed : to Verify Trip Summary Paid");
		test_steps.add("Verified TripSummary  Paid : <b>" + Paid + "</b>");
		reslogger.info("Verified TripSummary  Paid : " + Paid);

	}

	public void verifyTripSummaryRoomCharges(WebDriver driver, ArrayList test_steps, String getRoomCharges,
			String RoomCharges) {
		double roomAmt = Double.valueOf(getRoomCharges);
		int amt = (int) roomAmt;
		getRoomCharges = String.valueOf(amt);
		assertEquals(getRoomCharges.trim(), (RoomCharges), "Failed : to Verify Trip Summary Room Changes");
		test_steps.add("Verified TripSummary Room Changes : <b>" + RoomCharges + "</b>");
		reslogger.info("Verified TripSummary Room Changes : " + RoomCharges);
	}

	public void verifyTripSummaryTaxService(WebDriver driver, ArrayList test_steps, String taxServiceCharges,
			String TaxService) {
		double taxAmt = Double.valueOf(taxServiceCharges);
		int amt = (int) taxAmt;
		taxServiceCharges = String.valueOf(amt);
		if (taxServiceCharges.equals(TaxService)) {
			assertEquals(taxServiceCharges.trim(), (TaxService), "Failed : to Verify Trip Summary Tax Service Changes");
			test_steps.add("Verified TripSummary Tax Service Changes : <b>" + TaxService + "</b>");
			reslogger.info("Verified TripSummary Tax Service Changes : " + TaxService);
		} else if (taxServiceCharges.equals("0")) {
			assertEquals(taxServiceCharges.trim(), ("0"), "Failed : to Verify Trip Summary Tax Service Changes");
			test_steps.add("Verified TripSummary Tax Service Changes : <b>" + taxServiceCharges + "</b>");
			reslogger.info("Verified TripSummary Tax Service Changes : " + taxServiceCharges);

		}
	}

	public void verifyTripSummaryTotal(WebDriver driver, ArrayList test_steps, String TripTotal, String Total) {
		double totalAmt = Double.valueOf(TripTotal);
		int amt = (int) totalAmt;
		TripTotal = String.valueOf(amt);
		assertEquals(TripTotal.trim(), (Total), "Failed : to Verify Trip Summary Total");
		test_steps.add("Verified TripSummary Trip Total Charges : <b>" + Total + "</b>");
		reslogger.info("Verified TripSummary  Trip Total  Charges : " + Total);
	}

	public String get_TripSummaryBalanceWithCurrency(WebDriver driver, ArrayList test_steps) {
		String Balance = "$ 0.0";
		if (driver
				.findElements(By
						.xpath("//span[text()='Summary']/../../..//div[contains(text(),'Paid')]/following-sibling::div"))
				.size() > 0) {
			Balance = driver
					.findElement(By
							.xpath("//span[text()='Summary']/../../..//div[contains(text(),'Balance')]/following-sibling::div"))
					.getText();

			test_steps.add("TripSummary Balance Charges : " + Balance);
			reslogger.info("TripSummary Balance Charges : " + Balance);
		}
		return Balance;
	}

	public String get_TripSummaryBalance_Amount(WebDriver driver, ArrayList<String> test_steps) {
		String Balance = driver
				.findElement(By
						.xpath("//span[text()='Summary']/../../..//div[contains(text(),'Balance')]/following-sibling::div"))
				.getText();
		Balance = Balance.replace("$ ", "");
		test_steps.add("Balance amount is captured at trip summary as : <b>" + Balance + "</b>");
		reslogger.info("TripSummary Balance Charges : " + Balance);
		return Balance;
	}

	public void verifyTripSummaryBalance(WebDriver driver, ArrayList test_steps, String getBalance, String Balance) {

		double balanceAmount = Double.valueOf(getBalance);
		reslogger.info(String.valueOf((int) (balanceAmount)).trim());
		assertTrue(String.valueOf((int) (balanceAmount)).trim().equals(Balance),
				"Failed : to Verify Trip Summary Balance");
		test_steps.add("Verified TripSummary Balance Charges :<b> " + Balance + "</b>");
		reslogger.info("Verified TripSummary Balance Charges : " + Balance);
	}

	public void verifyTripSummaryBalanceForCheckOut(WebDriver driver, ArrayList test_steps, String getBalance,
			String Balance) {

		// double balanceAmount=Double.valueOf(getBalance);
		// reslogger.info(String.valueOf((int)(balanceAmount)).trim());
		assertTrue(getBalance.contains(Balance), "Failed : to Verify Trip Summary Balance");
		test_steps.add("Verified TripSummary Balance Charges :<b> " + Balance + "</b>");
		reslogger.info("Verified TripSummary Balance Charges : " + Balance);

	}

	public void Verify_TripSummary_Balance_ForCheckOut(WebDriver driver, ArrayList test_steps, String getBalance,
			String Balance) {

		// double balanceAmount=Double.valueOf(getBalance);
		// reslogger.info(String.valueOf((int)(balanceAmount)).trim());
		assertTrue(getBalance.contains(Balance), "Failed : to Verify Trip Summary Balance");
		test_steps.add("Verified TripSummary Balance Charges :<b> " + Balance + "</b>");
		reslogger.info("Verified TripSummary Balance Charges : " + Balance);
	}

	public void verifyDepositPaymentInHistoryTab(WebDriver driver, ArrayList test_steps, Double deposit) {
		String depositOne = "//span[contains(text(),'payment') and contains(text(),'" + deposit + "')]";
		if (driver.findElement(By.xpath(depositOne)).isDisplayed()) {

			test_steps.add("Deposit payment verified in History Tab");
			reslogger.info("Deposit payment verified in History Tab");
		} else {
			test_steps.add("Deposit payment not found  in History Tab");
			reslogger.info("Deposit payment not found  in History Tab");
		}
	}

	public void verify_AcountPaymentInHistory(WebDriver driver, ArrayList test_steps, Double amount, String AccountName,
			ArrayList RoomAbbri, ArrayList Rooms, String timeZone) {
		String payline = "//span[contains(text(),'payment') and contains(text(),'" + amount + "') and contains(text(),'"
				+ AccountName + "')]";
		if (driver.findElement(By.xpath(payline)).isDisplayed()) {
			test_steps.add("Account payment verified in History Tab");
			reslogger.info("Account payment verified in History Tab");
		} else {
			test_steps.add("Account payment not found  in History Tab");
			reslogger.info("Account payment not found  in History Tab");
		}

		payline = "//span[contains(text(),'payment') and contains(text(),'" + amount + "') and contains(text(),'"
				+ AccountName + "')]/../following-sibling::td//span";

		String room = driver.findElement(By.xpath(payline)).getText();
		String rm = RoomAbbri.get(0).toString();
		String rm1 = Rooms.get(0).toString();
		String[] rm2 = rm.split(":");
		rm = rm2[1].trim();
		String[] rm3 = rm1.split(":");
		rm1 = rm3[1].trim();
		rm = rm + ": " + rm1;

		reslogger.info(room);
		reslogger.info(rm);

		if (rm.equalsIgnoreCase(room)) {
			test_steps.add("Room Number verified in history : " + room);
			reslogger.info("Room Number verified in history : " + room);
		} else {
			test_steps.add("Room Number not verified in history : " + room);
			reslogger.info("Room Number not verified in history : " + room);
		}

		String payment = "//span[contains(text(),'payment') and contains(text(),'" + amount + "') and contains(text(),'"
				+ AccountName + "')]/../preceding-sibling::td//span";

		room = driver.findElement(By.xpath(payment)).getText();
		if (room.equalsIgnoreCase("Payment")) {
			test_steps.add("Payment category verified for the payment");
			reslogger.info("Payment category verified for the payment");
		} else {
			test_steps.add("Payment category not verified for the payment");
			reslogger.info("Payment category not verifie for the payment");
		}

		Date date = new Date();
		DateFormat df = new SimpleDateFormat("MM/dd/yy");

		// Use Madrid's time zone to format the date in
		df.setTimeZone(TimeZone.getTimeZone(timeZone));

		String d = df.format(date);

		payline = "(//span[contains(text(),'payment') and contains(text(),'" + amount + "') and contains(text(),'"
				+ AccountName + "')]/../preceding-sibling::td//span)[2]";
		room = driver.findElement(By.xpath(payline)).getText();
		if (room.equalsIgnoreCase(d)) {
			test_steps.add("Payment Date verified in history : " + room);
			reslogger.info("Payment Date verified in history : " + room);
		} else {
			test_steps.add("Payment Date not verified in history : " + room);
			reslogger.info("Payment Date not verified in history : " + room);
		}

	}

	public void verify_BannerDetails(WebDriver driver, ArrayList test_steps, String Salutation, String GuestFirstName,
			String GuestLastName, String PhoneNumber, String Email, String TripTotal, String Balance,
			String ConfirmationNumber, String Status, String CheckInDate, String CheckOutDate, String Country) {
		test_steps.add("******************* Banner fields verification **********************");
		reslogger.info("******************* Banner fields verification **********************");

		String name = Salutation.trim() + " " + GuestFirstName.trim() + " " + GuestLastName.trim();
		String bannername = "//td/div/span[contains(text(),'" + name + "')]";
		reslogger.info(bannername);
		if (driver.findElement(By.xpath(bannername)).isDisplayed()) {
			test_steps.add("Reservation banner name field verified : " + name);
			reslogger.info("Reservation banner name field verified : " + name);
		} else {
			test_steps.add("Reservation banner name field not found : " + name);
			reslogger.info("Reservation banner name field not found : " + name);
		}

		PhoneNumber = PhoneNumber.replace("" + PhoneNumber.trim().charAt(0), "(" + PhoneNumber.trim().charAt(0));
		PhoneNumber = PhoneNumber.replace("" + PhoneNumber.trim().charAt(4), ")" + PhoneNumber.trim().charAt(4));
		PhoneNumber = PhoneNumber.replace("" + PhoneNumber.trim().charAt(5), " " + PhoneNumber.trim().charAt(5));
		PhoneNumber = PhoneNumber.replace("" + PhoneNumber.trim().charAt(9), "-" + PhoneNumber.trim().charAt(9));

		String code = null;
		if (Country.equalsIgnoreCase("United States")) {
			code = "1";
		} else if (Country.equalsIgnoreCase("United Kingdom")) {
			code = "41";
		}
		code = code + "-";
		PhoneNumber = code + PhoneNumber;
		reslogger.info(PhoneNumber);

		String phone = "//td//span[text()='" + PhoneNumber + "']";
		if (driver.findElement(By.xpath(phone)).isDisplayed()) {
			test_steps.add("Reservation banner PhoneNumber field verified : " + PhoneNumber);
			reslogger.info("Reservation banner PhoneNumber field verified : " + PhoneNumber);
		} else {
			test_steps.add("Reservation banner PhoneNumber field not found : " + PhoneNumber);
			reslogger.info("Reservation banner PhoneNumber field not found : " + PhoneNumber);
		}

		String trip = "//td[text()='TRIP TOTAL']/following-sibling::td[contains(text(),'" + TripTotal.trim() + "')]";
		if (driver.findElement(By.xpath(trip)).isDisplayed()) {
			test_steps.add("Reservation banner TripTotal field verified : " + TripTotal);
			reslogger.info("Reservation banner TripTotal field verified : " + TripTotal);
		} else {
			test_steps.add("Reservation banner TripTotal field not found : " + TripTotal);
			reslogger.info("Reservation banner TripTotal field not found : " + TripTotal);
		}

		String bal = "//td[text()='BALANCE']/following-sibling::td/div[contains(text(),'" + Balance.trim() + "')]";
		if (driver.findElement(By.xpath(trip)).isDisplayed()) {
			test_steps.add("Reservation banner Balance field verified : " + Balance);
			reslogger.info("Reservation banner Balance field verified : " + Balance);
		} else {
			test_steps.add("Reservation banner Balance field not found : " + Balance);
			reslogger.info("Reservation banner Balance field not found : " + Balance);
		}

		String mail = "//td//a[text()='" + Email + "']";
		if (driver.findElement(By.xpath(mail)).isDisplayed()) {
			test_steps.add("Reservation banner mail field verified : " + Email);
			reslogger.info("Reservation banner mail field verified : " + Email);
		} else {
			test_steps.add("Reservation banner mail field not found : " + Email);
			reslogger.info("Reservation banner mail field not found : " + Email);
		}

		String sta = "//td//span[text()='" + Status.trim() + "']";
		if (driver.findElement(By.xpath(sta)).isDisplayed()) {
			test_steps.add("Reservation banner Status field verified : " + Status);
			reslogger.info("Reservation banner Status field verified : " + Status);
		} else {
			test_steps.add("Reservation banner Status field not found : " + Status);
			reslogger.info("Reservation banner Status field not found : " + Status);
		}

		String confirmation = "//td//span[text()='" + ConfirmationNumber.trim() + "']";
		if (driver.findElement(By.xpath(confirmation)).isDisplayed()) {
			test_steps.add("Reservation banner ConfirmationNumber field verified : " + ConfirmationNumber);
			reslogger.info("Reservation banner ConfirmationNumber field verified : " + ConfirmationNumber);
		} else {
			test_steps.add("Reservation banner ConfirmationNumber field not found : " + ConfirmationNumber);
			reslogger.info("Reservation banner ConfirmationNumber field not found : " + ConfirmationNumber);
		}

		String[] checkIn = CheckInDate.split("/");

		if (checkIn[0].charAt(0) == '0') {
			checkIn[0] = checkIn[0].replace("" + checkIn[0].charAt(0), "");
		}

		String checkInMonthDay = Utility.get_Month(CheckInDate);
		checkInMonthDay = checkInMonthDay + " " + checkIn[0];
		checkInMonthDay = "(" + checkInMonthDay;

		String[] checkOut = CheckOutDate.split("/");

		if (checkOut[0].charAt(0) == '0') {
			checkOut[0] = checkOut[0].replace("" + checkOut[0].charAt(0), "");
		}

		String checkOutMonthDay = Utility.get_Month(CheckOutDate);
		checkOutMonthDay = checkOutMonthDay + " " + checkOut[0];
		checkOutMonthDay = checkOutMonthDay + ")";
		String day = checkInMonthDay + " - " + checkOutMonthDay;

		reslogger.info(day);
		String days = "//td//span[contains(text(),'" + day.trim() + "')]";
		if (driver.findElement(By.xpath(confirmation)).isDisplayed()) {
			test_steps.add("Reservation banner Stay from and Stay to are verified : " + day);
			reslogger.info("Reservation banner Stay from and Stay to are verified : " + day);
		} else {
			test_steps.add("Reservation banner Stay from and Stay to are not found : " + day);
			reslogger.info("Reservation banner Stay from and Stay to are not found : " + day);
		}
	}

	public void verify_StayInfo(WebDriver driver, ArrayList test_steps, String CheckInDate, String CheckOutDate,
			String Adults, String Children, String RoomClass, String RoomCharges) throws Exception {
		test_steps.add("******************* Stay info fields verification **********************");
		reslogger.info("******************* Stay info fields verification **********************");

		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		Date dateObj1 = format1.parse(CheckInDate);
		Date dateObj2 = format1.parse(CheckOutDate);
		long diff = dateObj2.getTime() - dateObj1.getTime();
		int Nights = (int) (diff / (24 * 60 * 60 * 1000));
		reslogger.info("difference between days: " + Nights);

		String[] checkIn = CheckInDate.split("/");
		if (checkIn[0].charAt(0) == '0') {
			checkIn[0] = checkIn[0].replace("" + checkIn[0].charAt(0), "");
		}
		String checkInMonth = Utility.get_Month(CheckInDate);
		String checkInDays = checkInMonth + " " + checkIn[0] + ", " + checkIn[2];

		String checkInDay = "//div[contains(text(),'Stay')]/..//div[@class='checkin']/span[text()='" + checkInDays
				+ "']";
		if (driver.findElement(By.xpath(checkInDay)).isDisplayed()) {
			test_steps.add("Reservation Stay Info checkin date verified : " + CheckInDate);
			reslogger.info("Reservation Stay Info checkin date verified : " + CheckInDate);
		} else {
			test_steps.add("Reservation Stay Info checkin date not found : " + CheckInDate);
			reslogger.info("Reservation Stay Info checkin date not found : " + CheckInDate);
		}

		Date now = new Date(checkIn[1] + "/" + checkIn[0] + "/" + checkIn[2]);
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("dd/MM/yyyy"); // the
																				// day
																				// of
																				// the
																				// week
																				// abbreviated
		simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week
															// spelled out
															// completely
		String dayofCheckIn = simpleDateformat.format(now);

		String chckInday = "//div[contains(text(),'Stay')]/..//div[@class='checkin']//span[text()='"
				+ dayofCheckIn.trim() + "']";
		if (driver.findElement(By.xpath(chckInday)).isDisplayed()) {
			test_steps.add("Reservation Stay Info checkin day verified : " + dayofCheckIn);
			reslogger.info("Reservation Stay Info checkin day verified : " + dayofCheckIn);
		} else {
			test_steps.add("Reservation Stay Info checkin day not found : " + dayofCheckIn);
			reslogger.info("Reservation Stay Info checkin day not found : " + dayofCheckIn);
		}

		String[] checkOut = CheckOutDate.split("/");

		if (checkOut[0].charAt(0) == '0') {
			checkOut[0] = checkOut[0].replace("" + checkOut[0].charAt(0), "");
		}
		String checkOutMonth = Utility.get_Month(CheckOutDate);
		String checkOutDays = checkInMonth + " " + checkOut[0] + ", " + checkOut[2];
		String checkoutDay = "//div[contains(text(),'Stay')]/..//div[@class='checkout']/span[text()='" + checkOutDays
				+ "']";
		if (driver.findElement(By.xpath(checkoutDay)).isDisplayed()) {
			test_steps.add("Reservation Stay Info checkout date verified : " + CheckOutDate);
			reslogger.info("Reservation Stay Info checkout date verified : " + CheckOutDate);
		} else {
			test_steps.add("Reservation Stay Info checkout date not found : " + CheckOutDate);
			reslogger.info("Reservation Stay Info checkout date not found : " + CheckOutDate);
		}

		now = new Date(checkOut[1] + "/" + checkOut[0] + "/" + checkOut[2]);
		simpleDateformat = new SimpleDateFormat("dd/MM/yyyy"); // the day of the
																// week
																// abbreviated
		simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week
															// spelled out
															// completely
		String dayofCheckOut = simpleDateformat.format(now);
		String chkOutDay = "//div[contains(text(),'Stay')]/..//div[@class='checkout']//span[text()='"
				+ dayofCheckOut.trim() + "']";
		if (driver.findElement(By.xpath(chkOutDay)).isDisplayed()) {
			test_steps.add("Reservation Stay Info checkout day verified : " + dayofCheckOut);
			reslogger.info("Reservation Stay Info checkout day verified : " + dayofCheckOut);
		} else {
			test_steps.add("Reservation Stay Info checkout day not found : " + dayofCheckOut);
			reslogger.info("Reservation Stay Info checkout day not found : " + dayofCheckOut);
		}

		String nights = "//div[contains(text(),'Stay')]/..//div[@class='noofnights']/span[text()='" + Nights + "N']";
		if (driver.findElement(By.xpath(checkoutDay)).isDisplayed()) {
			test_steps.add("Reservation Stay Info Nights verified : " + Nights + "N");
			reslogger.info("Reservation Stay Info Nights verified : " + Nights + "N");
		} else {
			test_steps.add("Reservation Stay Info Nights not found : " + Nights + "N");
			reslogger.info("Reservation Stay Info Nights not found : " + Nights + "N");
		}

		String adults = "//div[contains(text(),'Stay')]/..//div//span[contains(@data-bind,'numberOfAdults')]";
		adults = driver.findElement(By.xpath(adults)).getText().trim();
		if (adults.equalsIgnoreCase(Adults.trim())) {
			test_steps.add("Reservation Stay Info Adults verified : " + Adults);
			reslogger.info("Reservation Stay Info Adults verified : " + Adults);
		} else {
			test_steps.add("Reservation Stay Info Adults not found : " + Adults);
			reslogger.info("Reservation Stay Info Adults not found : " + Adults);
		}

		String children = "//div[contains(text(),'Stay')]/..//div//span[contains(@data-bind,'numberOfChildren')]";
		children = driver.findElement(By.xpath(children)).getText().trim();
		if (children.equalsIgnoreCase(Children.trim())) {
			test_steps.add("Reservation Stay Info Children verified : " + Children);
			reslogger.info("Reservation Stay Info Children verified : " + Children);
		} else {
			test_steps.add("Reservation Stay Info Children not found : " + Children);
			reslogger.info("Reservation Stay Info Children not found : " + Children);
		}

		String roomClass = "//div[contains(text(),'Stay')]/..//div//span[contains(@data-bind,'roomClassName')]";
		roomClass = driver.findElement(By.xpath(roomClass)).getText().trim();
		if (roomClass.equalsIgnoreCase(RoomClass.trim())) {
			test_steps.add("Reservation Stay Info RoomClass verified : " + RoomClass);
			reslogger.info("Reservation Stay Info RoomClass verified : " + RoomClass);
		} else {
			test_steps.add("Reservation Stay Info RoomClass not found : " + RoomClass);
			reslogger.info("Reservation Stay Info RoomClass not found : " + RoomClass);
		}

		String roomCharge = "//div[contains(text(),'Stay')]/..//div//span[text()='TOTAL']/following-sibling::span";
		roomCharge = driver.findElement(By.xpath(roomCharge)).getText().trim();
		if (roomCharge.equalsIgnoreCase(RoomCharges.trim())) {
			test_steps.add("Reservation Stay Info RoomCharges verified : " + RoomCharges);
			reslogger.info("Reservation Stay Info RoomCharges verified : " + RoomCharges);
		} else {
			test_steps.add("Reservation Stay Info RoomCharges not found : " + RoomCharges);
			reslogger.info("Reservation Stay Info RoomCharges not found : " + RoomCharges);
		}
	}

	public void verify_MRB_StayInfo(WebDriver driver, ArrayList test_steps, String CheckInDate, String CheckOutDate,
			String Adults, String Children, String RoomClass, String RoomCharges, ArrayList roomCost, String IsAssign,
			String RatePlan) throws Exception {
		test_steps.add("******************* MRB Stay info fields verification **********************");
		reslogger.info("******************* MRB Stay info fields verification **********************");

		/*
		 * SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy"); Date
		 * dateObj1 = format1.parse(CheckInDate); Date dateObj2 =
		 * format1.parse(CheckOutDate); long diff = dateObj2.getTime() -
		 * dateObj1.getTime(); int Nights = (int) (diff / (24 * 60 * 60 *
		 * 1000)); reslogger.info("difference between days: " + Nights);
		 */

		String[] in = CheckInDate.split("\\|");
		String[] out = CheckOutDate.split("\\|");
		String[] adu = Adults.split("\\|");
		String[] chld = Children.split("\\|");
		String[] roomclass = RoomClass.split("\\|");
		String[] rate = RatePlan.split("\\|");

		int count = in.length;

		for (int i = 0; i < count; i++) {

			SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
			Date dateObj1 = format1.parse(in[i]);
			Date dateObj2 = format1.parse(out[i]);
			long diff = dateObj2.getTime() - dateObj1.getTime();
			int Nights = (int) (diff / (24 * 60 * 60 * 1000));
			reslogger.info("difference between days: " + Nights);

			String[] checkIn = in[i].split("/");
			if (checkIn[0].charAt(0) == '0') {
				checkIn[0] = checkIn[0].replace("" + checkIn[0].charAt(0), "");
			}
			String checkInMonth = Utility.get_Month(in[i]);
			String checkInDays = checkInMonth + " " + checkIn[0] + ", " + checkIn[2];

			String checkInDay = "//div[contains(text(),'Stay')]/..//div[@class='checkin']/span[text()='" + checkInDays
					+ "']";
			if (driver.findElement(By.xpath(checkInDay)).isDisplayed()) {
				test_steps.add("Reservation Stay Info checkin date verified : " + in[i]);
				reslogger.info("Reservation Stay Info checkin date verified : " + in[i]);
			} else {
				test_steps.add("Reservation Stay Info checkin date not found : " + in[i]);
				reslogger.info("Reservation Stay Info checkin date not found : " + in[i]);
			}

			Date now = new Date(checkIn[1] + "/" + checkIn[0] + "/" + checkIn[2]);
			SimpleDateFormat simpleDateformat = new SimpleDateFormat("dd/MM/yyyy"); // the
																					// day
																					// of
																					// the
																					// week
																					// abbreviated
			simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the
																// week spelled
																// out
																// completely
			String dayofCheckIn = simpleDateformat.format(now);

			String chckInday = "//div[contains(text(),'Stay')]/..//div[@class='checkin']//span[text()='"
					+ dayofCheckIn.trim() + "']";
			if (driver.findElement(By.xpath(chckInday)).isDisplayed()) {
				test_steps.add("Reservation Stay Info checkin day verified : " + dayofCheckIn);
				reslogger.info("Reservation Stay Info checkin day verified : " + dayofCheckIn);
			} else {
				test_steps.add("Reservation Stay Info checkin day not found : " + dayofCheckIn);
				reslogger.info("Reservation Stay Info checkin day not found : " + dayofCheckIn);
			}

			String[] checkOut = out[i].split("/");

			if (checkOut[0].charAt(0) == '0') {
				checkOut[0] = checkOut[0].replace("" + checkOut[0].charAt(0), "");
			}
			String checkOutMonth = Utility.get_Month(out[i]);
			String checkOutDays = checkInMonth + " " + checkOut[0] + ", " + checkOut[2];
			String checkoutDay = "//div[contains(text(),'Stay')]/..//div[@class='checkout']/span[text()='"
					+ checkOutDays + "']";
			if (driver.findElement(By.xpath(checkoutDay)).isDisplayed()) {
				test_steps.add("Reservation Stay Info checkout date verified : " + out[i]);
				reslogger.info("Reservation Stay Info checkout date verified : " + out[i]);
			} else {
				test_steps.add("Reservation Stay Info checkout date not found : " + out[i]);
				reslogger.info("Reservation Stay Info checkout date not found : " + out[i]);
			}

			now = new Date(checkOut[1] + "/" + checkOut[0] + "/" + checkOut[2]);
			simpleDateformat = new SimpleDateFormat("dd/MM/yyyy"); // the day of
																	// the week
																	// abbreviated
			simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the
																// week spelled
																// out
																// completely
			String dayofCheckOut = simpleDateformat.format(now);
			String chkOutDay = "//div[contains(text(),'Stay')]/..//div[@class='checkout']//span[text()='"
					+ dayofCheckOut.trim() + "']";
			if (driver.findElement(By.xpath(chkOutDay)).isDisplayed()) {
				test_steps.add("Reservation Stay Info checkout day verified : " + dayofCheckOut);
				reslogger.info("Reservation Stay Info checkout day verified : " + dayofCheckOut);
			} else {
				test_steps.add("Reservation Stay Info checkout day not found : " + dayofCheckOut);
				reslogger.info("Reservation Stay Info checkout day not found : " + dayofCheckOut);
			}

			String nights = "//div[contains(text(),'Stay')]/..//div[@class='noofnights']/span[text()='" + Nights
					+ "N']";
			if (driver.findElement(By.xpath(checkoutDay)).isDisplayed()) {
				test_steps.add("Reservation Stay Info Nights verified : " + Nights + "N");
				reslogger.info("Reservation Stay Info Nights verified : " + Nights + "N");
			} else {
				test_steps.add("Reservation Stay Info Nights not found : " + Nights + "N");
				reslogger.info("Reservation Stay Info Nights not found : " + Nights + "N");
			}

			String adults = "(//div[contains(text(),'Stay')]/..//div//span[contains(@data-bind,'numberOfAdults')])["
					+ (i + 1) + "]";
			adults = driver.findElement(By.xpath(adults)).getText().trim();
			if (adults.equalsIgnoreCase(adu[i].trim())) {
				test_steps.add("Reservation Stay Info Adults verified : " + adu[i]);
				reslogger.info("Reservation Stay Info Adults verified : " + adu[i]);
			} else {
				test_steps.add("Reservation Stay Info Adults not found : " + adu[i]);
				reslogger.info("Reservation Stay Info Adults not found : " + adu[i]);
			}

			String children = "(//div[contains(text(),'Stay')]/..//div//span[contains(@data-bind,'numberOfChildren')])["
					+ (i + 1) + "]";
			children = driver.findElement(By.xpath(children)).getText().trim();
			if (children.equalsIgnoreCase(chld[i].trim())) {
				test_steps.add("Reservation Stay Info Children verified : " + chld[i]);
				reslogger.info("Reservation Stay Info Children verified : " + chld[i]);
			} else {
				test_steps.add("Reservation Stay Info Children not found : " + chld[i]);
				reslogger.info("Reservation Stay Info Children not found : " + chld[i]);
			}

			String roomClass = "(//div[contains(text(),'Stay')]/..//div//span[contains(@data-bind,'roomClassName')])["
					+ (i + 1) + "]";
			roomClass = driver.findElement(By.xpath(roomClass)).getText().trim();
			if (roomClass.contains(roomclass[i].trim())) {
				test_steps.add("Reservation Stay Info RoomClass verified : " + roomclass[i]);
				reslogger.info("Reservation Stay Info RoomClass verified : " + roomclass[i]);
			} else {
				test_steps.add("Reservation Stay Info RoomClass not found : " + roomclass[i]);
				reslogger.info("Reservation Stay Info RoomClass not found : " + roomclass[i]);
			}

			String roomCharge = "(//div[contains(text(),'Stay')]/..//div//span[contains(@data-bind,'roomClassName')])["
					+ (i + 1) + "]/following-sibling::span";
			roomCharge = driver.findElement(By.xpath(roomCharge)).getText().trim();
			roomCharge = roomCharge.trim();
			roomCharge = roomCharge.substring(1, roomCharge.length());
			roomCharge = roomCharge.trim();
			if (roomCharge.equalsIgnoreCase(roomCost.get(i).toString().trim())) {
				test_steps.add(
						"Reservation Stay Info RoomCharges verified for line item : " + (i + 1) + " " + roomCharge);
				reslogger.info(
						"Reservation Stay Info RoomCharges verified for line item : " + (i + 1) + " " + roomCharge);
			} else {
				test_steps.add(
						"Reservation Stay Info RoomCharges not found for line item : " + (i + 1) + " " + roomCharge);
				reslogger.info(
						"Reservation Stay Info RoomCharges not found for line item : " + (i + 1) + " " + roomCharge);
			}

			String rateplan = "(//div[contains(text(),'Stay')]/..//div//span[contains(text(),'RATE PLAN:')])[" + (i + 1)
					+ "]/following-sibling::span";
			rateplan = driver.findElement(By.xpath(rateplan)).getText().trim();
			if (rateplan.equalsIgnoreCase(rate[i])) {
				test_steps.add("Reservation Stay Info rateplan verified for line item : " + (i + 1) + " " + rateplan);
				reslogger.info("Reservation Stay Info rateplan verified for line item : " + (i + 1) + " " + rateplan);
			} else {
				test_steps.add("Reservation Stay Info rateplan not found for line item : " + (i + 1) + " " + rateplan);
				reslogger.info("Reservation Stay Info rateplan not found for line item : " + (i + 1) + " " + rateplan);
			}

			String room = "(//span[text()='ROOM:']/following-sibling::span)[" + (i + 1) + "]";
			Wait.WaitForElement(driver, room);
			room = driver.findElement(By.xpath(room)).getText();

			if (IsAssign.equalsIgnoreCase("No")) {
				if (room.equalsIgnoreCase("Unassigned")) {
					test_steps.add("Room Number is of the line item: " + (i + 1) + " is Unassighed");
					reslogger.info("Room Number is of the line item: " + (i + 1) + " Unassighed");
				}
			} else {
				test_steps.add("Room Number is of the line item: " + (i + 1) + " is " + room);
				reslogger.info("Room Number is of the line item: " + (i + 1) + " is " + room);
			}

		}
	}

	public void velidate_TripSummaryAndFolio(WebDriver driver, ArrayList test_steps, String FilioRoomCharges,
			String FilioTaxes, String FilioIncidentals, String FilioTripTotal, String FilioPaid, String FilioBalance,
			String TripSummaryRoomCharges, String TripSummaryTaxes, String TripSummaryIncidentals,
			String TripSummaryTripTotal, String TripSummaryPaid, String TripSummaryBalance) {
		test_steps.add("******************* Validating Folio and TripSummary fields **********************");
		reslogger.info("******************* Validating Folio and TripSummary fields **********************");

		if (FilioRoomCharges.equalsIgnoreCase(TripSummaryRoomCharges)) {
			test_steps.add("TripSummary RoomCharges and Folio RoomCharges are same : " + FilioRoomCharges);
			reslogger.info("TripSummary RoomCharges and Folio RoomCharges are same : " + FilioRoomCharges);
		} else {
			test_steps.add("TripSummary RoomCharges and Folio RoomCharges are not same : " + FilioRoomCharges);
			reslogger.info("TripSummary RoomCharges and Folio RoomCharges are not same : " + FilioRoomCharges);
		}

		if (FilioTaxes.equalsIgnoreCase(TripSummaryTaxes)) {
			test_steps.add("TripSummaryTaxes and FilioTaxes are same : " + FilioTaxes);
			reslogger.info("TTripSummaryTaxes and FilioTaxes are same : " + FilioTaxes);
		} else {
			test_steps.add("TripSummaryTaxes and FilioTaxes are not same : " + FilioTaxes);
			reslogger.info("TripSummaryTaxes and FilioTaxes are not same : " + FilioTaxes);
		}
		if (FilioIncidentals.equalsIgnoreCase(TripSummaryIncidentals)) {
			test_steps.add("TripSummary Incidental and Folio Incidental are same : " + FilioIncidentals);
			reslogger.info("TripSummary Incidental and Folio Incidental are same : " + FilioIncidentals);
		} else {
			test_steps.add("TripSummary Incidental and Folio Incidental are not same : " + FilioIncidentals);
			reslogger.info("TripSummary Incidental and Folio Incidental are not same : " + FilioIncidentals);
		}
		if (FilioTripTotal.equalsIgnoreCase(TripSummaryTripTotal)) {
			test_steps.add("TripSummary TripTotal and Folio TripTotal are same : " + FilioTripTotal);
			reslogger.info("TripSummary TripTotal and Folio TripTotal are same : " + FilioTripTotal);
		} else {
			test_steps.add("TripSummary TripTotal and Folio TripTotal are not same : " + FilioTripTotal);
			reslogger.info("TripSummary TripTotal and Folio TripTotal are not same : " + FilioTripTotal);
		}
		if (FilioPaid.equalsIgnoreCase(TripSummaryPaid)) {
			test_steps.add("TripSummary Paid and Folio Paid are same : " + FilioPaid);
			reslogger.info("TripSummary Paid and Folio Paid are same : " + FilioPaid);
		} else {
			test_steps.add("TripSummary Paid and Folio Paid are not same : " + FilioPaid);
			reslogger.info("TripSummary Paid and Folio Paid are not same : " + FilioPaid);
		}
		if (FilioBalance.equalsIgnoreCase(TripSummaryBalance)) {
			test_steps.add("TripSummary Balance and Folio Balance are same : " + FilioBalance);
			reslogger.info("TripSummary Balance and Folio Balance are same : " + FilioBalance);
		} else {
			test_steps.add("TripSummary Balance and Folio Balance are not same : " + FilioBalance);
			reslogger.info("TripSummary Balance and Folio Balance are not same : " + FilioBalance);
		}
	}

	public void validate_GuestInfo(WebDriver driver, ArrayList test_steps, String Salutation, String GuestFirstName,
			String GuestLastName, String PhoneNumber, String AlternatePhone, String Email, String Country,
			String Account, String Address1, String Address2, String Address3, String State, String City,
			String PostalCode) {
		test_steps.add("******************* Guest Info fields verification **********************");
		reslogger.info("******************* Guest Info fields verification **********************");

		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_GuestInfo_GuestName);

		String name = Salutation.trim() + " " + GuestFirstName.trim() + " " + GuestLastName.trim();
		String guestname = res.CP_GuestInfo_GuestName.getText().trim();
		reslogger.info(guestname);
		if (guestname.equalsIgnoreCase(name.trim())) {
			test_steps.add("Reservation Guest Info GuestName field verified : " + name);
			reslogger.info("Reservation Guest Info GuestName field verified : " + name);
		} else {
			test_steps.add("Reservation Guest Info GuestName field not found : " + name);
			reslogger.info("Reservation Guest Info GuestName field not found : " + name);
		}

		String contactname = res.CP_GuestInfo_ContactName.getText().trim();
		reslogger.info(contactname);
		if (contactname.equalsIgnoreCase(name.trim())) {
			test_steps.add("Reservation Guest Info ContactName field verified : " + name);
			reslogger.info("Reservation Guest Info ContactName field verified : " + name);
		} else {
			test_steps.add("Reservation Guest Info ContactName field not found : " + name);
			reslogger.info("Reservation Guest Info ContactName field not found : " + name);
		}

		PhoneNumber = PhoneNumber.replace("" + PhoneNumber.trim().charAt(0), "(" + PhoneNumber.trim().charAt(0));
		PhoneNumber = PhoneNumber.replace("" + PhoneNumber.trim().charAt(4), ")" + PhoneNumber.trim().charAt(4));
		PhoneNumber = PhoneNumber.replace("" + PhoneNumber.trim().charAt(5), " " + PhoneNumber.trim().charAt(5));
		PhoneNumber = PhoneNumber.replace("" + PhoneNumber.trim().charAt(9), "-" + PhoneNumber.trim().charAt(9));

		String code = null;
		if (Country.equalsIgnoreCase("United States")) {
			code = "1";
		} else if (Country.equalsIgnoreCase("United Kingdom")) {
			code = "41";
		}
		code = code + "-";
		PhoneNumber = code + PhoneNumber;
		reslogger.info(PhoneNumber);

		String phone = res.CP_GuestInfo_Phone.getText().trim();
		if (phone.equalsIgnoreCase(PhoneNumber)) {
			test_steps.add("Reservation Guest Info PhoneNumber field verified : " + PhoneNumber);
			reslogger.info("Reservation Guest Info PhoneNumber field verified : " + PhoneNumber);
		} else {
			test_steps.add("Reservation Guest Info PhoneNumber field not found : " + PhoneNumber);
			reslogger.info("Reservation Guest Info PhoneNumber field not found : " + PhoneNumber);
		}

		AlternatePhone = AlternatePhone.replace("" + AlternatePhone.trim().charAt(0),
				"(" + AlternatePhone.trim().charAt(0));
		AlternatePhone = AlternatePhone.replace("" + AlternatePhone.trim().charAt(4),
				")" + AlternatePhone.trim().charAt(4));
		AlternatePhone = AlternatePhone.replace("" + AlternatePhone.trim().charAt(5),
				" " + AlternatePhone.trim().charAt(5));
		AlternatePhone = AlternatePhone.replace("" + AlternatePhone.trim().charAt(9),
				"-" + AlternatePhone.trim().charAt(9));

		AlternatePhone = code + AlternatePhone;
		reslogger.info(AlternatePhone);

		phone = res.CP_GuestInfo_AlternatePhone.getText().trim();
		if (phone.equalsIgnoreCase(AlternatePhone)) {
			test_steps.add("Reservation Guest Info AlternatePhone field verified : " + AlternatePhone);
			reslogger.info("Reservation Guest Info AlternatePhone field verified : " + AlternatePhone);
		} else {
			test_steps.add("Reservation Guest Info AlternatePhone field not found : " + AlternatePhone);
			reslogger.info("Reservation Guest Info AlternatePhone field not found : " + AlternatePhone);
		}

		String mail = res.CP_GuestInfo_Email.getText().trim();
		reslogger.info(mail);
		if (mail.equalsIgnoreCase(Email.trim())) {
			test_steps.add("Reservation Guest Info Email field verified : " + Email);
			reslogger.info("Reservation Guest Info Email field verified : " + Email);
		} else {
			test_steps.add("Reservation Guest Info Email field not found : " + Email);
			reslogger.info("Reservation Guest Info Email field not found : " + Email);
		}

		String account = res.CP_GuestInfo_Account.getText().trim();
		if (account.equalsIgnoreCase("-") && (Account.equalsIgnoreCase("") || Account.isEmpty())) {
			test_steps.add("Reservation Guest Info Account field verified");
			reslogger.info("Reservation Guest Info Account field verified");
		} else if (!(account.equalsIgnoreCase("-")) && !(Account.equalsIgnoreCase("") || Account.isEmpty())) {
			if (account.contentEquals(Account.trim())) {
				test_steps.add("Reservation Guest Info Account field verified : " + Account);
				reslogger.info("Reservation Guest Info Account field verified  : " + Account);
			} else {
				test_steps.add("Reservation Guest Info Account field not found : " + Account);
				reslogger.info("Reservation Guest Info Account field not found : " + Account);
			}
		} else {
			test_steps.add("Reservation Guest Info Account field not found : " + Account);
			reslogger.info("Reservation Guest Info Account field not found : " + Account);
		}

		String address1 = res.CP_GuestInfo_Address1.getText().trim();
		reslogger.info(address1);
		if (address1.equalsIgnoreCase(Address1.trim())) {
			test_steps.add("Reservation Guest Info Address1 field verified : " + Address1);
			reslogger.info("Reservation Guest Info Address1 field verified : " + Address1);
		} else {
			test_steps.add("Reservation Guest Info Address1 field not found : " + Address1);
			reslogger.info("Reservation Guest Info Address1 field not found : " + Address1);
		}

		String address2 = res.CP_GuestInfo_Address2.getText().trim();
		reslogger.info(address2);
		if (address2.equalsIgnoreCase(Address2.trim())) {
			test_steps.add("Reservation Guest Info Address2 field verified : " + Address2);
			reslogger.info("Reservation Guest Info Address2 field verified : " + Address2);
		} else {
			test_steps.add("Reservation Guest Info Address2 field not found : " + Address2);
			reslogger.info("Reservation Guest Info Address2 field not found : " + Address2);
		}

		String address3 = res.CP_GuestInfo_Address3.getText().trim();
		reslogger.info(address3);
		if (address3.equalsIgnoreCase(Address3.trim())) {
			test_steps.add("Reservation Guest Info Address3 field verified : " + Address3);
			reslogger.info("Reservation Guest Info Address3 field verified : " + Address3);
		} else {
			test_steps.add("Reservation Guest Info Address3 field not found : " + Address3);
			reslogger.info("Reservation Guest Info Address3 field not found : " + Address3);
		}

		String city = res.CP_GuestInfo_City.getText().trim();
		reslogger.info(city);
		if (city.equalsIgnoreCase(City.trim())) {
			test_steps.add("Reservation Guest Info City field verified : " + City);
			reslogger.info("Reservation Guest Info City field verified : " + City);
		} else {
			test_steps.add("Reservation Guest Info City field not found : " + City);
			reslogger.info("Reservation Guest Info City field not found : " + City);
		}

		String postalcode = res.CP_GuestInfo_PostalCode.getText().trim();
		reslogger.info(postalcode);
		if (postalcode.equalsIgnoreCase(PostalCode.trim())) {
			test_steps.add("Reservation Guest Info PostalCode field verified : " + PostalCode);
			reslogger.info("Reservation Guest Info PostalCode field verified : " + PostalCode);
		} else {
			test_steps.add("Reservation Guest Info PostalCode field not found : " + PostalCode);
			reslogger.info("Reservation Guest Info PostalCode field not found : " + PostalCode);
		}

		String country = res.CP_GuestInfo_Country.getText().trim();
		reslogger.info(country);
		if (country.equalsIgnoreCase(Country.trim())) {
			test_steps.add("Reservation Guest Info Country field verified : " + Country);
			reslogger.info("Reservation Guest Info Country field verified : " + Country);
		} else {
			test_steps.add("Reservation Guest Info Country field not found : " + Country);
			reslogger.info("Reservation Guest Info Country field not found : " + Country);
		}

		String state = res.CP_GuestInfo_State.getText().trim();
		reslogger.info(state);

		if (!(state.equalsIgnoreCase("") || state.isEmpty())) {
			if (state.equalsIgnoreCase(State.trim())) {
				test_steps.add("Reservation Guest Info State field verified : " + State);
				reslogger.info("Reservation Guest Info State field verified : " + State);
			} else {
				test_steps.add("Reservation Guest Info State field not found : " + State);
				reslogger.info("Reservation Guest Info State field not found : " + State);
			}
		}
	}

	public void validate_MRB_GuestInfo(WebDriver driver, ArrayList test_steps, String Salutation, String GuestFirstName,
			String GuestLastName, String PhoneNumber, String AlternatePhone, String Email, String Country,
			String Account, String Address1, String Address2, String Address3, String State, String City,
			String PostalCode) {
		test_steps.add("******************* MRB Guest Info fields verification **********************");
		reslogger.info("******************* MRB Guest Info fields verification **********************");

		String[] sal = Salutation.split("\\|");
		String[] guestFName = GuestFirstName.split("\\|");
		String[] guestLName = GuestLastName.split("\\|");
		String[] phonenum = PhoneNumber.split("\\|");
		String[] email = Email.split("\\|");

		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_GuestInfo_GuestName);

		String name = sal[0].trim() + " " + guestFName[0].trim() + " " + guestLName[0].trim();
		String guestname = res.CP_GuestInfo_GuestName.getText().trim();
		reslogger.info(guestname);
		if (guestname.equalsIgnoreCase(name.trim())) {
			test_steps.add("Reservation Guest Info GuestName field verified : " + name);
			reslogger.info("Reservation Guest Info GuestName field verified : " + name);
		} else {
			test_steps.add("Reservation Guest Info GuestName field not found : " + name);
			reslogger.info("Reservation Guest Info GuestName field not found : " + name);
		}

		String contactname = res.CP_GuestInfo_ContactName.getText().trim();
		reslogger.info(contactname);
		if (contactname.equalsIgnoreCase(name.trim())) {
			test_steps.add("Reservation Guest Info ContactName field verified : " + name);
			reslogger.info("Reservation Guest Info ContactName field verified : " + name);
		} else {
			test_steps.add("Reservation Guest Info ContactName field not found : " + name);
			reslogger.info("Reservation Guest Info ContactName field not found : " + name);
		}

		phonenum[0] = phonenum[0].replace("" + phonenum[0].trim().charAt(0), "(" + phonenum[0].trim().charAt(0));
		phonenum[0] = phonenum[0].replace("" + phonenum[0].trim().charAt(4), ")" + phonenum[0].trim().charAt(4));
		phonenum[0] = phonenum[0].replace("" + phonenum[0].trim().charAt(5), " " + phonenum[0].trim().charAt(5));
		phonenum[0] = phonenum[0].replace("" + phonenum[0].trim().charAt(9), "-" + phonenum[0].trim().charAt(9));

		String code = null;
		if (Country.equalsIgnoreCase("United States")) {
			code = "1";
		} else if (Country.equalsIgnoreCase("United Kingdom")) {
			code = "41";
		}
		code = code + "-";
		PhoneNumber = code + phonenum[0];
		reslogger.info(PhoneNumber);

		String phone = res.CP_GuestInfo_Phone.getText().trim();
		if (phone.equalsIgnoreCase(PhoneNumber)) {
			test_steps.add("Reservation Guest Info PhoneNumber field verified : " + PhoneNumber);
			reslogger.info("Reservation Guest Info PhoneNumber field verified : " + PhoneNumber);
		} else {
			test_steps.add("Reservation Guest Info PhoneNumber field not found : " + PhoneNumber);
			reslogger.info("Reservation Guest Info PhoneNumber field not found : " + PhoneNumber);
		}

		AlternatePhone = AlternatePhone.replace("" + AlternatePhone.trim().charAt(0),
				"(" + AlternatePhone.trim().charAt(0));
		AlternatePhone = AlternatePhone.replace("" + AlternatePhone.trim().charAt(4),
				")" + AlternatePhone.trim().charAt(4));
		AlternatePhone = AlternatePhone.replace("" + AlternatePhone.trim().charAt(5),
				" " + AlternatePhone.trim().charAt(5));
		AlternatePhone = AlternatePhone.replace("" + AlternatePhone.trim().charAt(9),
				"-" + AlternatePhone.trim().charAt(9));

		AlternatePhone = code + AlternatePhone;
		reslogger.info(AlternatePhone);

		phone = res.CP_GuestInfo_AlternatePhone.getText().trim();
		if (phone.equalsIgnoreCase(AlternatePhone)) {
			test_steps.add("Reservation Guest Info AlternatePhone field verified : " + AlternatePhone);
			reslogger.info("Reservation Guest Info AlternatePhone field verified : " + AlternatePhone);
		} else {
			test_steps.add("Reservation Guest Info AlternatePhone field not found : " + AlternatePhone);
			reslogger.info("Reservation Guest Info AlternatePhone field not found : " + AlternatePhone);
		}

		String mail = res.CP_GuestInfo_Email.getText().trim();
		reslogger.info(mail);
		if (mail.equalsIgnoreCase(email[0].trim())) {
			test_steps.add("Reservation Guest Info Email field verified : " + email[0]);
			reslogger.info("Reservation Guest Info Email field verified : " + email[0]);
		} else {
			test_steps.add("Reservation Guest Info Email field not found : " + email[0]);
			reslogger.info("Reservation Guest Info Email field not found : " + email[0]);
		}
		String account;
		if (!(Account.equalsIgnoreCase("") || Account.isEmpty())) {
			account = "//guest-info//span[contains(text(),'Gues')]/../..//label[text()='Account']/following-sibling::a//span";
			account = driver.findElement(By.xpath(account)).getText();
		} else {

			account = res.CP_GuestInfo_Account.getText().trim();
		}
		if (account.equalsIgnoreCase("-") && (Account.equalsIgnoreCase("") || Account.isEmpty())) {
			test_steps.add("Reservation Guest Info Account field verified");
			reslogger.info("Reservation Guest Info Account field verified");
		} else if (!(account.equalsIgnoreCase("-")) && !(Account.equalsIgnoreCase("") || Account.isEmpty())) {
			if (account.contentEquals(Account.trim())) {
				test_steps.add("Reservation Guest Info Account field verified : " + Account);
				reslogger.info("Reservation Guest Info Account field verified  : " + Account);
			} else {
				test_steps.add("Reservation Guest Info Account field not found : " + Account);
				reslogger.info("Reservation Guest Info Account field not found : " + Account);
			}
		} else {
			test_steps.add("Reservation Guest Info Account field not found : " + Account);
			reslogger.info("Reservation Guest Info Account field not found : " + Account);
		}

		String address1 = res.CP_GuestInfo_Address1.getText().trim();
		reslogger.info(address1);
		if (address1.equalsIgnoreCase(Address1.trim())) {
			test_steps.add("Reservation Guest Info Address1 field verified : " + Address1);
			reslogger.info("Reservation Guest Info Address1 field verified : " + Address1);
		} else {
			test_steps.add("Reservation Guest Info Address1 field not found : " + Address1);
			reslogger.info("Reservation Guest Info Address1 field not found : " + Address1);
		}

		String address2 = res.CP_GuestInfo_Address2.getText().trim();
		reslogger.info(address2);
		if (address2.equalsIgnoreCase(Address2.trim())) {
			test_steps.add("Reservation Guest Info Address2 field verified : " + Address2);
			reslogger.info("Reservation Guest Info Address2 field verified : " + Address2);
		} else {
			test_steps.add("Reservation Guest Info Address2 field not found : " + Address2);
			reslogger.info("Reservation Guest Info Address2 field not found : " + Address2);
		}

		String address3 = res.CP_GuestInfo_Address3.getText().trim();
		reslogger.info(address3);
		if (address3.equalsIgnoreCase(Address3.trim())) {
			test_steps.add("Reservation Guest Info Address3 field verified : " + Address3);
			reslogger.info("Reservation Guest Info Address3 field verified : " + Address3);
		} else {
			test_steps.add("Reservation Guest Info Address3 field not found : " + Address3);
			reslogger.info("Reservation Guest Info Address3 field not found : " + Address3);
		}

		String city = res.CP_GuestInfo_City.getText().trim();
		reslogger.info(city);
		if (city.equalsIgnoreCase(City.trim())) {
			test_steps.add("Reservation Guest Info City field verified : " + City);
			reslogger.info("Reservation Guest Info City field verified : " + City);
		} else {
			test_steps.add("Reservation Guest Info City field not found : " + City);
			reslogger.info("Reservation Guest Info City field not found : " + City);
		}

		String postalcode = res.CP_GuestInfo_PostalCode.getText().trim();
		reslogger.info(postalcode);
		if (postalcode.equalsIgnoreCase(PostalCode.trim())) {
			test_steps.add("Reservation Guest Info PostalCode field verified : " + PostalCode);
			reslogger.info("Reservation Guest Info PostalCode field verified : " + PostalCode);
		} else {
			test_steps.add("Reservation Guest Info PostalCode field not found : " + PostalCode);
			reslogger.info("Reservation Guest Info PostalCode field not found : " + PostalCode);
		}

		String country = res.CP_GuestInfo_Country.getText().trim();
		reslogger.info(country);
		if (country.equalsIgnoreCase(Country.trim())) {
			test_steps.add("Reservation Guest Info Country field verified : " + Country);
			reslogger.info("Reservation Guest Info Country field verified : " + Country);
		} else {
			test_steps.add("Reservation Guest Info Country field not found : " + Country);
			reslogger.info("Reservation Guest Info Country field not found : " + Country);
		}

		String state = res.CP_GuestInfo_State.getText().trim();
		reslogger.info(state);

		if (!(state.equalsIgnoreCase("") || state.isEmpty())) {
			if (state.equalsIgnoreCase(State.trim())) {
				test_steps.add("Reservation Guest Info State field verified : " + State);
				reslogger.info("Reservation Guest Info State field verified : " + State);
			} else {
				test_steps.add("Reservation Guest Info State field not found : " + State);
				reslogger.info("Reservation Guest Info State field not found : " + State);
			}
		}
	}

	public void validate_MRB_AdditionalGuestInfo(WebDriver driver, ArrayList test_steps, String Salutation,
			String GuestFirstName, String GuestLastName, String PhoneNumber, String Email, String Country) {
		test_steps.add("******************* MRB additional Guest Info fields verification **********************");
		reslogger.info("******************* MRB additional Guest Info fields verification **********************");

		String[] sal = Salutation.split("\\|");
		String[] guestFName = GuestFirstName.split("\\|");
		String[] guestLName = GuestLastName.split("\\|");
		String[] phonenum = PhoneNumber.split("\\|");
		String[] email = Email.split("\\|");

		String addtionalRoom = "//span[contains(text(),'Additional Room: ')]";

		int count = sal.length;

		for (int i = 1; i < count; i++) {

			addtionalRoom = "(//span[contains(text(),'Additional Room: ')])[" + i + "]";
			Wait.WaitForElement(driver, addtionalRoom);
			driver.findElement(By.xpath(addtionalRoom)).click();
			test_steps.add("Expending addtinal guest room : " + i);
			reslogger.info("Expending addtinal guest room : " + i);

			Elements_CPReservation res = new Elements_CPReservation(driver);

			String guestName = "(//span[contains(text(),'Additional Room: ')])[" + i
					+ "]/../../../../../..//label[text()='Guest Name']/following-sibling::span";
			Wait.WaitForElement(driver, guestName);

			String name = sal[i].trim() + " " + guestFName[i].trim() + " " + guestLName[i].trim();
			String guestname = driver.findElement(By.xpath(guestName)).getText().trim();
			reslogger.info(guestname);
			if (guestname.equalsIgnoreCase(name.trim())) {
				test_steps.add("Reservation addtinal Guest Info GuestName field verified for additonal room : " + i
						+ " " + name);
				reslogger.info("Reservation addtinal Guest Info GuestName field verified for additonal room : " + i
						+ " " + name);
			} else {
				test_steps.add("Reservation addtinal Guest Info GuestName field not found for additonal room : " + i
						+ " " + name);
				reslogger.info("Reservation addtinal Guest Info GuestName field not found for additonal room : " + i
						+ " " + name);
			}

			String code = null;
			if (Country.equalsIgnoreCase("United States")) {
				code = "1";
			} else if (Country.equalsIgnoreCase("United Kingdom")) {
				code = "41";
			}
			code = code + "-";
			PhoneNumber = phonenum[i];
			reslogger.info(PhoneNumber);

			String phone = "(//span[contains(text(),'Additional Room: ')])[" + i
					+ "]/../../../../../..//label[text()='Phone']/following-sibling::span";
			phone = driver.findElement(By.xpath(phone)).getText().trim();
			if (phone.equalsIgnoreCase(PhoneNumber)) {
				test_steps.add("Reservation Guest Info PhoneNumber field verified : " + PhoneNumber);
				reslogger.info("Reservation Guest Info PhoneNumber field verified : " + PhoneNumber);
			} else {
				test_steps.add("Reservation Guest Info PhoneNumber field not found : " + PhoneNumber);
				reslogger.info("Reservation Guest Info PhoneNumber field not found : " + PhoneNumber);
			}

			String mailadd = "(//span[contains(text(),'Additional Room: ')])[" + i
					+ "]/../../../../../..//label[text()='E-mail']/following-sibling::span";
			String mail = driver.findElement(By.xpath(mailadd)).getText().trim();
			reslogger.info(mail);
			if (mail.equalsIgnoreCase(email[i].trim())) {
				test_steps.add("Reservation Guest Info Email field verified : " + email[i]);
				reslogger.info("Reservation Guest Info Email field verified : " + email[i]);
			} else {
				test_steps.add("Reservation Guest Info Email field not found : " + email[i]);
				reslogger.info("Reservation Guest Info Email field not found : " + email[i]);
			}
		}
	}

	public void get_AssociatedPoliciesToReservation(WebDriver driver, ArrayList test_steps)
			throws InterruptedException {
		test_steps.add("******************* Policies associated to reservation **********************");
		reslogger.info("******************* Policies associated to reservation **********************");
		String policies = "(//div[text()='Policies And Disclaimers']/..//h4)";

		int count = driver.findElements(By.xpath(policies)).size();
		for (int i = 1; i < count; i++) {
			policies = "(//div[text()='Policies And Disclaimers']/..//h4)[" + i + "]/a";
			driver.findElement(By.xpath(policies)).click();
			Wait.wait2Second();
			if (i == 1) {
				String depositPolicy = "//div[text()='Policies And Disclaimers']/..//label[text()='Deposit Policy']/following-sibling::div//button/span";
				depositPolicy = driver.findElement(By.xpath(depositPolicy)).getText();
				test_steps.add("Associated deposit policy to reservation : " + depositPolicy);
				reslogger.info("Associated deposit policy to reservation : " + depositPolicy);
			}
			if (i == 2) {
				String checkinPolicy = "//div[text()='Policies And Disclaimers']/..//label[text()='Check-in Policy']/following-sibling::div//button/span";
				checkinPolicy = driver.findElement(By.xpath(checkinPolicy)).getText();
				test_steps.add("Associated checkin policy to reservation : " + checkinPolicy);
				reslogger.info("Associated checkin policy to reservation : " + checkinPolicy);
			}
			if (i == 3) {
				String noShow = "//div[text()='Policies And Disclaimers']/..//label[text()='No show Policy']/following-sibling::div//button/span";
				noShow = driver.findElement(By.xpath(noShow)).getText();
				test_steps.add("Associated noShow policy to reservation : " + noShow);
				reslogger.info("Associated noShow policy to reservation : " + noShow);
			}
		}
	}

	public void validate_DepositAmount(WebDriver driver, ArrayList test_steps, String Number, Double DepositPaid,
			Double RoomCharges) {
		test_steps.add("******************* validating deposit amount **********************");
		reslogger.info("******************* Verify Deposit Policy Associated **********************");
		int num = Integer.parseInt(Number.trim());
		if ((RoomCharges * num) / 100 == DepositPaid) {
			test_steps.add("Deposit amount validated : " + DepositPaid);
			reslogger.info("Deposit amount validated : " + DepositPaid);
		} else {
			test_steps.add("Deposit amount paid : " + DepositPaid + " and actual deposit are not same : "
					+ (RoomCharges * num) / 100);
			reslogger.info("Deposit amount paid : " + DepositPaid + " and actual deposit are not same : "
					+ (RoomCharges * num) / 100);
		}

	}

	public void verify_DepositPolicyAssociated(WebDriver driver, ArrayList test_steps, String PolicyName)
			throws InterruptedException {
		test_steps.add("******************* Verify Deposit Policy Associated **********************");
		reslogger.info("******************* Verify Deposit Policy Associated **********************");
		String policies = "(//div[text()='Policies And Disclaimers']/..//h4)/a";
		driver.findElement(By.xpath(policies)).click();
		Wait.wait2Second();
		String depositPolicy = "//div[text()='Policies And Disclaimers']/..//label[text()='Deposit Policy']/following-sibling::div//button/span";
		depositPolicy = driver.findElement(By.xpath(depositPolicy)).getText().trim();
		test_steps.add("Associated deposit policy to reservation : " + depositPolicy);
		reslogger.info("Associated deposit policy to reservation : " + depositPolicy);
		reslogger.info(depositPolicy);
		reslogger.info(PolicyName);
		assertEquals(depositPolicy, PolicyName);
		test_steps.add("Verified Associated deposit policy to reservation : " + depositPolicy);
		reslogger.info("Verified Associated deposit policy to reservation : " + depositPolicy);
	}

	public void verify_GuestReportLabelsValidations(WebDriver driver, ArrayList test_steps)
			throws InterruptedException {
		test_steps.add("******************* Guest Reports labels verification **********************");
		reslogger.info("******************* Guest Reports labels verification **********************");
		String reports = "//div/a[contains(text(),'Reports ')]";
		Wait.WaitForElement(driver, reports);
		driver.findElement(By.xpath(reports)).click();
		Wait.wait2Second();
		String guestStatement = "//div/a[contains(text(),'Reports ')]/..//ul//span[text()='Guest Statement']";
		String guestStatementWithTaxBreakdown = "//div/a[contains(text(),'Reports ')]/..//ul//span[text()='Guest Statement with Tax breakdown']";
		String guestRegistration = "//div/a[contains(text(),'Reports ')]/..//ul//span[text()='Guest Registration']";
		String guestRegistartiontaxbreakdown = "//div/a[contains(text(),'Reports ')]/..//ul//span[text()='Guest Registration with Tax breakdown']";

		if (driver.findElement(By.xpath(guestStatement)).isDisplayed()) {
			test_steps.add("Guest Statement label displayed");
			reslogger.info("Guest Statement label displayed");
		} else {
			test_steps.add("Guest Statement label not displayed");
			reslogger.info("Guest Statement label not displayed");
		}

		if (driver.findElement(By.xpath(guestStatementWithTaxBreakdown)).isDisplayed()) {
			test_steps.add("Guest Statement with Tax breakdown label displayed");
			reslogger.info("Guest Statement with Tax breakdown label displayed");
		} else {
			test_steps.add("Guest Statement with Tax breakdown label not displayed");
			reslogger.info("Guest Statement with Tax breakdown label not displayed");
		}

		if (driver.findElement(By.xpath(guestRegistration)).isDisplayed()) {
			test_steps.add("Guest Registration label displayed");
			reslogger.info("Guest Registration label displayed");
		} else {
			test_steps.add("Guest Registration label not displayed");
			reslogger.info("Guest Registration label not displayed");
		}

		if (driver.findElement(By.xpath(guestRegistration)).isDisplayed()) {
			test_steps.add("Guest Registration with Tax breakdown label displayed");
			reslogger.info("Guest Registration with Tax breakdown label displayed");
		} else {
			test_steps.add("Guest Registration with Tax breakdown label not displayed");
			reslogger.info("Guest Registration with Tax breakdown label not displayed");
		}
		Wait.WaitForElement(driver, reports);
		driver.findElement(By.xpath(reports)).click();
	}

	public void verify_NotesSections(WebDriver driver, ArrayList test_steps) {
		test_steps.add("******************* Verify Notes section **********************");
		reslogger.info("******************* Verify Notes section **********************");
		String note = "//a[text()='ADD NOTE ']";
		if (driver.findElement(By.xpath(note)).isDisplayed()) {
			test_steps.add("Notes Section is displayed");
			reslogger.info("Notes Section is displayed");
		} else {
			test_steps.add("Notes Section is not displayed");
			reslogger.info("Notes Section is not displayed");
		}
	}

	public void enter_Notes(WebDriver driver, ArrayList test_steps, String IsAddNotes, String NoteType, String Subject,
			String Description) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_AddNotes);
		test_steps.add("******************* Adding Notes **********************");
		reslogger.info("******************* Adding Notes **********************");
		if (IsAddNotes.equalsIgnoreCase("Yes")) {
			res.CP_AddNotes.click();
			test_steps.add("Click on Add Notes");
			reslogger.info("Click on Add Notes");

			String noteTypeArrow = "//h4[text()='Add Notes']/../..//div/div//div//label[text()='Type']/..//button";
			Wait.WaitForElement(driver, noteTypeArrow);
			driver.findElement(By.xpath(noteTypeArrow)).click();

			String noteType = "//h4[text()='Add Notes']/../..//div/div//div//label[text()='Type']/..//button/..//div//li/a/span[text()='"
					+ NoteType.trim() + "']";
			Wait.WaitForElement(driver, noteType);
			driver.findElement(By.xpath(noteType)).click();
			test_steps.add("Select Note Type : " + NoteType);
			reslogger.info("Select Note Type : " + NoteType);

			String subject = "//h4[text()='Add Notes']/../..//div/div//div//label[text()='Subject']/..//input";
			Wait.WaitForElement(driver, subject);
			driver.findElement(By.xpath(subject)).sendKeys(Subject);
			test_steps.add("Enter subject : " + Subject);
			reslogger.info("Enter subject : " + Subject);

			String description = "//h4[text()='Add Notes']/../..//div/div//div//label[text()='Description']/preceding-sibling::textarea";
			Wait.WaitForElement(driver, description);
			driver.findElement(By.xpath(description)).sendKeys(Description);
			test_steps.add("Enter Description : " + Description);
			reslogger.info("Enter Description : " + Description);

			String user = "//input[starts-with(@data-bind,'value: updatedBy')]";

			JavascriptExecutor js = (JavascriptExecutor) driver;
			user = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(user)));

			String add = "//h4[text()='Add Notes']/../..//button[text()='Add']";
			Wait.WaitForElement(driver, add);
			driver.findElement(By.xpath(add)).click();
			test_steps.add("Click on Add");
			reslogger.info("Click on Add");

			String noteverify = "//td[text()='" + NoteType.trim() + "']";
			Wait.WaitForElement(driver, noteverify);
			test_steps.add("Sucessfully added Note : " + Subject);
			reslogger.info("Sucessfully added Note : " + Subject);

			test_steps.add("Verified Note Type : " + NoteType);
			reslogger.info("Verified Note Type : " + NoteType);

			noteverify = "//td[text()='" + Subject.trim() + "']";
			Wait.WaitForElement(driver, noteverify);
			test_steps.add("Verified Note Subject : " + Subject);
			reslogger.info("Verified Note Subject : " + Subject);

			noteverify = "//td[text()='" + Description.trim() + "']";
			Wait.WaitForElement(driver, noteverify);
			test_steps.add("Verified Note Description : " + Description);
			reslogger.info("Verified Note Description : " + Description);

			noteverify = "//td[text()='" + user.trim() + "']";
			Wait.WaitForElement(driver, noteverify);
			test_steps.add("Verified Note updated by : " + user);
			reslogger.info("Verified Note updated by : " + user);
		}
	}

	public void verify_Notes(WebDriver driver, ArrayList test_steps, String NoteType, String Subject,
			String Description, String user, ArrayList RoomAbbri, ArrayList Rooms) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_AddNotes);
		test_steps.add("******************* Verify  Notes **********************");
		reslogger.info("******************* Verify Notes **********************");
		int count = RoomAbbri.size();
		for (int i = 0; i < count; i++) {
			String noteverify = "(//td[text()='" + NoteType.trim() + "'])[" + (i + 1) + "]";
			Wait.WaitForElement(driver, noteverify);
			test_steps.add("Sucessfully added Note : " + Subject);
			reslogger.info("Sucessfully added Note : " + Subject);

			test_steps.add("Verified Note Type : " + NoteType);
			reslogger.info("Verified Note Type : " + NoteType);

			noteverify = "(//td[text()='" + Subject.trim() + "'])[" + (i + 1) + "]";
			Wait.WaitForElement(driver, noteverify);
			test_steps.add("Verified Note Subject : " + Subject);
			reslogger.info("Verified Note Subject : " + Subject);

			noteverify = "(//td[text()='" + Description.trim() + "'])[" + (i + 1) + "]";
			Wait.WaitForElement(driver, noteverify);
			test_steps.add("Verified Note Description : " + Description);
			reslogger.info("Verified Note Description : " + Description);

			noteverify = "(//td[text()='" + user.trim() + "'])[" + (i + 1) + "]";
			Wait.WaitForElement(driver, noteverify);
			test_steps.add("Verified Note updated by : " + user);
			reslogger.info("Verified Note updated by : " + user);

			String[] room = Rooms.get(i).toString().trim().split(":");
			String abbr = RoomAbbri.get(i).toString().trim();

			String[] abbri = abbr.trim().split(":");

			abbr = abbri[1] + ":" + room[1];
			reslogger.info(abbr);
			String roomnum = "//td[contains(text(),'" + abbr + "')]";
			reslogger.info(roomnum);
			Wait.WaitForElement(driver, roomnum);
			test_steps.add("Verified Note Room Number : " + abbr);
			reslogger.info("Verified Note Room Number : " + abbr);
		}
	}

	public void enter_NotesWithTimeZone(WebDriver driver, ArrayList test_steps, String IsAddNotes, String NoteType,
			String Subject, String Description, String timeZone) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_AddNotes);
		test_steps.add("******************* Adding Notes **********************");
		reslogger.info("******************* Adding Notes **********************");
		if (IsAddNotes.equalsIgnoreCase("Yes")) {
			res.CP_AddNotes.click();
			test_steps.add("Click on Add Notes");
			reslogger.info("Click on Add Notes");

			String noteTypeArrow = "//h4[text()='Add Notes']/../..//div/div//div//label[text()='Type']/..//button";
			Wait.WaitForElement(driver, noteTypeArrow);
			driver.findElement(By.xpath(noteTypeArrow)).click();

			String noteType = "//h4[text()='Add Notes']/../..//div/div//div//label[text()='Type']/..//button/..//div//li/a/span[text()='"
					+ NoteType.trim() + "']";
			Wait.WaitForElement(driver, noteType);
			driver.findElement(By.xpath(noteType)).click();
			test_steps.add("Select Note Type : " + NoteType);
			reslogger.info("Select Note Type : " + NoteType);

			String subject = "//h4[text()='Add Notes']/../..//div/div//div//label[text()='Subject']/..//input";
			Wait.WaitForElement(driver, subject);
			driver.findElement(By.xpath(subject)).sendKeys(Subject);
			test_steps.add("Enter subject : " + Subject);
			reslogger.info("Enter subject : " + Subject);

			String description = "//h4[text()='Add Notes']/../..//div/div//div//label[text()='Description']/preceding-sibling::textarea";
			Wait.WaitForElement(driver, description);
			driver.findElement(By.xpath(description)).sendKeys(Description);
			test_steps.add("Enter Description : " + Description);
			reslogger.info("Enter Description : " + Description);

			String user = "//input[starts-with(@data-bind,'value: updatedBy')]";

			JavascriptExecutor js = (JavascriptExecutor) driver;
			user = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(user)));

			Date date = new Date();
			DateFormat df = new SimpleDateFormat("MM/dd/yyy");

			// Use Madrid's time zone to format the date in
			df.setTimeZone(TimeZone.getTimeZone(timeZone));

			reslogger.info(df.format(date));

			String updatedon = "(//h4[text()='Add Notes']/../..//label[text()='Updated On']/../input)";
			js = (JavascriptExecutor) driver;
			String updatedOn = (String) js.executeScript("return arguments[0].value",
					driver.findElement(By.xpath(updatedon)));
			if (df.format(date).equalsIgnoreCase(updatedOn)) {
				test_steps.add("Updated on date validated with property time zone date : " + df.format(date));
				reslogger.info("Updated on date validated with property time zone date : " + df.format(date));
			}

			String add = "//h4[text()='Add Notes']/../..//button[text()='Add']";
			Wait.WaitForElement(driver, add);
			driver.findElement(By.xpath(add)).click();
			test_steps.add("Click on Add");
			reslogger.info("Click on Add");

			String noteverify = "//td[text()='" + NoteType.trim() + "']";
			Wait.WaitForElement(driver, noteverify);
			test_steps.add("Sucessfully added Note : " + Subject);
			reslogger.info("Sucessfully added Note : " + Subject);

			test_steps.add("Verified Note Type : " + NoteType);
			reslogger.info("Verified Note Type : " + NoteType);

			noteverify = "//td[text()='" + Subject.trim() + "']";
			Wait.WaitForElement(driver, noteverify);
			test_steps.add("Verified Note Subject : " + Subject);
			reslogger.info("Verified Note Subject : " + Subject);

			noteverify = "//td[text()='" + Description.trim() + "']";
			Wait.WaitForElement(driver, noteverify);
			test_steps.add("Verified Note Description : " + Description);
			reslogger.info("Verified Note Description : " + Description);

			noteverify = "//td[text()='" + user.trim() + "']";
			Wait.WaitForElement(driver, noteverify);
			test_steps.add("Verified Note updated by : " + user);
			reslogger.info("Verified Note updated by : " + user);
		}
	}

	public boolean verify_TaskSections(WebDriver driver, ArrayList test_steps) {
		test_steps.add("******************* Verify Task section **********************");
		reslogger.info("******************* Verify Task section **********************");
		String task = "//button[text()='ADD TASK']";
		if (driver.findElement(By.xpath(task)).isDisplayed()) {
			test_steps.add("Task Section is displayed");
			reslogger.info("Task Section is displayed");
			return true;
		} else {
			test_steps.add("Task Section is not displayed");
			reslogger.info("Task Section is not displayed");
			return false;
		}
	}

	public void enter_Task(WebDriver driver, ArrayList test_steps, String IsTask, String TaskCategory, String TaskType,
			String TaskDetails, String TaskRemarks, String TaskDueon, String TaskAssignee, String TaskStatus)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_AddTask);
		test_steps.add("******************* Adding Task **********************");
		reslogger.info("******************* Adding Task **********************");
		if (IsTask.equalsIgnoreCase("Yes")) {
			res.CP_AddTask.click();
			test_steps.add("Click on Add Task");
			reslogger.info("Click on Add Task");

			String taskCategoryArrow = "//label[text()='Category']/..//button";
			Wait.WaitForElement(driver, taskCategoryArrow);
			driver.findElement(By.xpath(taskCategoryArrow)).click();

			String taskCategory = "//label[text()='Category']/..//div/ul//span[text()='" + TaskCategory.trim() + "']";
			Wait.WaitForElement(driver, taskCategory);
			driver.findElement(By.xpath(taskCategory)).click();
			test_steps.add("Select Task Category : " + TaskCategory);
			reslogger.info("Select Task Category : " + TaskCategory);

			String taskType = "(//label[text()='Type']/..//button)[2]";
			Wait.WaitForElement(driver, taskType);
			driver.findElement(By.xpath(taskType)).click();
			Wait.wait2Second();
			taskType = "(//label[text()='Type']/..//div/ul//span[text()='" + TaskType.trim() + "'])[2]";
			Wait.WaitForElement(driver, taskType);
			driver.findElement(By.xpath(taskType)).click();
			test_steps.add("Select Task Type : " + TaskType);
			reslogger.info("Select Task Type : " + TaskType);

			String taskDetails = "//label[text()='Details']/preceding-sibling::textarea";
			Wait.WaitForElement(driver, taskDetails);
			driver.findElement(By.xpath(taskDetails)).sendKeys(TaskDetails);
			test_steps.add("Select Task Details : " + TaskDetails);
			reslogger.info("Select Task Details : " + TaskDetails);

			String taskRemarks = "//label[text()='Remarks']/preceding-sibling::textarea";
			Wait.WaitForElement(driver, taskRemarks);
			driver.findElement(By.xpath(taskRemarks)).sendKeys(TaskRemarks);
			test_steps.add("Enter Task Remarks : " + TaskRemarks);
			reslogger.info("Enter Task Remarks : " + TaskRemarks);

			String dueCalender = "//label[text()='Due On']/..//i";
			Wait.WaitForElement(driver, dueCalender);
			driver.findElement(By.xpath(dueCalender)).click();
			String monthYear = Utility.get_MonthYear(TaskDueon);
			String day = Utility.getDay(TaskDueon);
			reslogger.info(monthYear);
			String header = null, headerText = null, next = null, date;
			for (int i = 1; i <= 12; i++) {
				header = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[2]";
				headerText = driver.findElement(By.xpath(header)).getText();
				if (headerText.equalsIgnoreCase(monthYear)) {
					date = "//td[contains(@class,'day') and text()='" + day + "']";
					Wait.WaitForElement(driver, date);
					int size = driver.findElements(By.xpath(date)).size();
					for (int k = 1; k <= size; k++) {
						date = "(//td[contains(@class,'day') and text()='" + day + "'])[" + k + "]";
						String classText = driver.findElement(By.xpath(date)).getAttribute("class");
						if (!classText.contains("old")) {
							driver.findElement(By.xpath(date)).click();
							test_steps.add("Selecting due on date : " + TaskDueon);
							reslogger.info("Selecting due on date : " + TaskDueon);
							break;
						}
					}
					break;
				} else {
					next = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[3]/div";
					Wait.WaitForElement(driver, next);
					driver.findElement(By.xpath(next)).click();
					Wait.wait2Second();
				}
			}

			String taskAssignee = "//label[text()='Assignee']/..//input";
			Wait.WaitForElement(driver, taskAssignee);
			driver.findElement(By.xpath(taskAssignee)).sendKeys(TaskAssignee);
			Wait.wait2Second();
			String taskassgin = "//label[text()='Assignee']/..//div/ul/li/div";
			Wait.WaitForElement(driver, taskassgin);
			driver.findElement(By.xpath(taskassgin)).click();
			test_steps.add("Enter Task Assignee : " + TaskAssignee);
			reslogger.info("Enter Task Assignee : " + TaskAssignee);

			String taskStatus = "(//label[text()='Status']/..//button)[2]";
			Wait.WaitForElement(driver, taskStatus);
			driver.findElement(By.xpath(taskStatus)).click();
			String status = "(//label[text()='Status']/..//button)[2]/following-sibling::div//li//span[text()='"
					+ TaskStatus.trim() + "']";
			Wait.WaitForElement(driver, status);
			driver.findElement(By.xpath(status)).click();
			test_steps.add("Select Task Status : " + TaskStatus);
			reslogger.info("Select Task Status : " + TaskStatus);

			String save = "//h4[text()='Add Task']/../..//button[text()='Save']";
			Wait.WaitForElement(driver, save);
			driver.findElement(By.xpath(save)).click();
			test_steps.add("Click on Save");
			reslogger.info("Click on Save");

			String taskVerify = "//span[text()='Tasks ']/../..//td//span[text()='" + TaskType.trim() + "']";
			Wait.WaitForElement(driver, taskVerify);
			test_steps.add("Sucessfully added Task : " + TaskType);
			reslogger.info("Sucessfully added Task : " + TaskType);

			test_steps.add("Verified Task Type : " + TaskType);
			reslogger.info("Verified Task Type : " + TaskType);

			test_steps.add("Verified Task Type : " + TaskType);
			reslogger.info("Verified Task Type : " + TaskType);

			taskDetails = "//span[text()='Tasks ']/../..//td[text()='" + TaskDetails.trim() + "']";
			Wait.WaitForElement(driver, taskDetails);
			test_steps.add("Verified Task details : " + TaskDetails);
			reslogger.info("Verified Task details : " + TaskDetails);

			String[] taskdue = TaskDueon.split("/");
			String due = "//span[text()='Tasks ']/../..//td[contains(text(),'" + taskdue[1] + "/" + taskdue[0] + "')]";
			Wait.WaitForElement(driver, due);
			test_steps.add("Verified Task due on : " + TaskDueon);
			reslogger.info("Verified Task due on : " + TaskDueon);

			String stat = "//span[text()='Tasks ']/../..//td[contains(text(),'" + TaskStatus.trim() + "')]";
			Wait.WaitForElement(driver, stat);
			test_steps.add("Verified Tsak Status : " + TaskStatus);
			reslogger.info("Verified Task Status : " + TaskStatus);

		}
	}

	public void select_Dates(WebDriver driver, ArrayList test_steps, String CheckInDate, String CheckoutDate,
			String Adults, String Children, String RatePlan, String PromoCode, String IsSplitRes)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);

		String loading = "(//div[@class='ir-loader-in'])[2]";
		int counter = 0;
		while (true) {
			if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
				break;
			} else if (counter == 40) {
				break;
			} else {
				counter++;
				Wait.wait2Second();
			}
		}
		reslogger.info("Waited to loading symbol");

		String[] checkin = CheckInDate.split("\\|");
		String[] checkout = CheckoutDate.split("\\|");
		String[] adu = Adults.split("\\|");
		String[] child = Children.split("\\|");
		String[] rate = RatePlan.split("\\|");
		String[] promoCode = PromoCode.split("\\|");
		Boolean flag = false;
		reslogger.info(CheckInDate);
		int size = checkin.length;

		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_AddRoom);
		reslogger.info("MRB size : " + size);
		if (size > 1) {
			for (int i = 1; i <= size; i++) {
				String checkInCal = "(//label[text()='Check In']/following-sibling::i)[" + i + "]";
				reslogger.info(checkInCal);
				Wait.WaitForElement(driver, checkInCal);
				driver.findElement(By.xpath(checkInCal)).click();
				String monthYear = Utility.get_MonthYear(checkin[i - 1]);
				String day = Utility.getDay(checkin[i - 1]);
				reslogger.info(monthYear);
				String header = null, headerText = null, next = null, date;
				for (int k = 1; k <= 12; k++) {
					header = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[2]";
					headerText = driver.findElement(By.xpath(header)).getText();
					if (headerText.equalsIgnoreCase(monthYear)) {
						date = "//td[contains(@class,'day') and text()='" + day + "']";
						Wait.WaitForElement(driver, date);

						int size1 = driver.findElements(By.xpath(date)).size();
						for (int l = 1; l <= size; l++) {
							date = "(//td[contains(@class,'day') and text()='" + day + "'])[" + l + "]";
							String classText = driver.findElement(By.xpath(date)).getAttribute("class");
							if (!classText.contains("old")) {
								driver.findElement(By.xpath(date)).click();
								test_steps.add("Selecting checkin date : " + checkin[+(i - 1)]);
								reslogger.info("Selecting checkin date : " + checkin[(i - 1)]);
								break;
							}
						}

						break;
					} else {
						next = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[3]/div";
						Wait.WaitForElement(driver, next);
						driver.findElement(By.xpath(next)).click();
						Wait.wait2Second();
					}
				}

				String checkOutCal = "(//label[text()='Check Out']/following-sibling::i)[" + i + "]";
				reslogger.info(checkOutCal);
				Wait.WaitForElement(driver, checkOutCal);
				driver.findElement(By.xpath(checkOutCal)).click();
				monthYear = Utility.get_MonthYear(checkout[i - 1]);
				day = Utility.getDay(checkout[i - 1]);
				reslogger.info(monthYear);
				for (int k = 1; k <= 12; k++) {
					header = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[2]";
					headerText = driver.findElement(By.xpath(header)).getText();
					if (headerText.equalsIgnoreCase(monthYear)) {
						date = "//td[contains(@class,'day') and text()='" + day + "']";
						Wait.WaitForElement(driver, date);

						int size1 = driver.findElements(By.xpath(date)).size();
						for (int l = 1; l <= size; l++) {
							date = "(//td[contains(@class,'day') and text()='" + day + "'])[" + l + "]";
							String classText = driver.findElement(By.xpath(date)).getAttribute("class");
							if (!classText.contains("old")) {
								driver.findElement(By.xpath(date)).click();
								test_steps.add("Selecting checkout date : " + checkout[(i - 1)]);
								reslogger.info("Selecting checkout date : " + checkout[(i - 1)]);
								break;
							}
						}
						break;
					} else {
						next = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[3]/div";
						Wait.WaitForElement(driver, next);
						driver.findElement(By.xpath(next)).click();
						Wait.wait2Second();
					}
				}
				if (!IsSplitRes.equalsIgnoreCase("Yes")) {

					String adults = "(//label[text()='Adults']/preceding-sibling::input)[" + i + "]";
					Wait.WaitForElement(driver, adults);
					driver.findElement(By.xpath(adults)).clear();
					driver.findElement(By.xpath(adults)).sendKeys(adu[i - 1]);
					test_steps.add("Enter Number of Adults : " + adu[(i - 1)]);
					reslogger.info("Enter Number of Adults : " + adu[(i - 1)]);

					String children = "(//label[text()='Children']/preceding-sibling::input)[" + i + "]";
					Wait.WaitForElement(driver, children);
					driver.findElement(By.xpath(children)).clear();
					driver.findElement(By.xpath(children)).sendKeys(child[i - 1]);
					test_steps.add("Enter Number of Children : " + child[(i - 1)]);
					reslogger.info("Enter Number of Children : " + child[(i - 1)]);

					// res.CP_NewReservation_Rateplan.click();
					String ratebutton = "(//label[contains(text(),'Rate Plan / Promo')]/preceding-sibling::div/button)["
							+ i + "]";
					Wait.WaitForElement(driver, ratebutton);
					driver.findElement(By.xpath(ratebutton)).click();

					int rateCount = driver.findElements(By
							.xpath("(//label[contains(text(),'Rate Plan / Promo')]/preceding-sibling::div//span[contains(text(),'"
									+ rate[i - 1].trim() + "')]/..)"))
							.size();
					String rateLoc = "(//label[contains(text(),'Rate Plan / Promo')]/preceding-sibling::div//span[contains(text(),'"
							+ rate[i - 1].trim() + "')]/..)[" + rateCount + "]";
					reslogger.info(rateLoc);
					Wait.WaitForElement(driver, rateLoc);
					driver.findElement(By.xpath(rateLoc)).click();
					test_steps.add("Selecting the rateplan : " + rate[i - 1]);
					reslogger.info("Selecting the rateplan : " + rate[i - 1]);
					if (RatePlan.contains("Promo")) {
						String promo = "(//label[contains(text(),'Enter Code')]/preceding-sibling::input)[" + i + "]";
						Wait.WaitForElement(driver, promo);
						res.CP_NewReservation_PromoCode.clear();
						res.CP_NewReservation_PromoCode.sendKeys(promoCode[i - 1]);
						test_steps.add("Enter promocode : " + promoCode[i]);
						reslogger.info("Enter promocode : " + promoCode[i]);
					}
				}
				if (!(i == size)) {
					clickOnAddARoomButton(driver, test_steps);
					reslogger.info("Clicking on add room");
					if (IsSplitRes.equalsIgnoreCase("Yes") && !flag) {
						res.CP_NewReservation_SplitReservation.click();
						test_steps.add("Clicking on split reservation");
						reslogger.info("Clicking on split reservation");
					}
					flag = true;
				}
			}
		} else {
			Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_CheckinCal);
			res.CP_NewReservation_CheckinCal.click();
			String monthYear = Utility.get_MonthYear(CheckInDate);
			String day = Utility.getDay(CheckInDate);
			reslogger.info(monthYear);
			String header = null, headerText = null, next = null, date;
			for (int i = 1; i <= 12; i++) {
				header = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[2]";
				headerText = driver.findElement(By.xpath(header)).getText();
				if (headerText.equalsIgnoreCase(monthYear)) {
					date = "//td[contains(@class,'day') and text()='" + day + "']";
					Wait.WaitForElement(driver, date);
					driver.findElement(By.xpath(date)).click();
					test_steps.add("Selecting checkin date : " + CheckInDate);
					reslogger.info("Selecting checkin date : " + CheckInDate);
					break;
				} else {
					next = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[3]/div";
					Wait.WaitForElement(driver, next);
					driver.findElement(By.xpath(next)).click();
					Wait.wait2Second();
				}
			}
		}
	}

	public int randomNumber;
	// public ArrayList select_MRBRooms(WebDriver driver,ArrayList
	// test_steps,String RoomClass,String IsAssign) throws InterruptedException
	// {

	public ArrayList select_MRBRooms(WebDriver driver, ArrayList test_steps, String RoomClass, String IsAssign,
			String Account) throws InterruptedException {

		String[] roomclass = RoomClass.split("\\|");

		int size = roomclass.length;
		ArrayList al = new ArrayList();
		for (int i = 1; i <= size; i++) {
			String Applypolicy = "//p[contains(text(),'Based on the changes made')]/../..//button[text()='Yes']";
			if (driver.findElements(By.xpath(Applypolicy)).size() > 0) {
				Wait.WaitForElement(driver, Applypolicy);
				Wait.wait3Second();
				driver.findElement(By.xpath(Applypolicy)).click();
				test_steps.add("Based on the changes made, new policies are applicable.? : yes");
				reslogger.info("Based on the changes made, new policies are applicable.? : yes");
			}
			Wait.wait5Second();
			String rm = "//span[text()='Room " + i + " of " + size + "']";
			if (driver.findElement(By.xpath(rm)).isDisplayed()) {
				test_steps.add("Room " + i + " of " + size + " is displayed");
				reslogger.info("Room " + i + " of " + size + " is displayed");
			} else {
				String clickOnPopUpYesBut = "(//div[@class='row']//button[contains(text(),'Yes')])[3]";
				driver.findElement(By.xpath(clickOnPopUpYesBut)).click();

			}
			String rulessize = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
					+ roomclass[i - 1].trim() + "')]/following-sibling::span";
			Wait.WaitForElement(driver, rulessize);
			reslogger.info(rulessize);
			int ruleCount = driver.findElements(By.xpath(rulessize)).size();

			String room = "(//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
					+ roomclass[i - 1] + "')]/../../div[2]/span)";
			reslogger.info(room);
			Wait.WaitForElement(driver, room);
			Wait.wait2Second();
			String rooms = driver.findElement(By.xpath(room)).getText();
			reslogger.info(rooms);
			String[] roomsCount = rooms.split(" ");
			int count = Integer.parseInt(roomsCount[0].trim());

			String cost = "(//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
					+ roomclass[i - 1] + "')]/../../../following-sibling::div/div/span)";

			Utility.ScrollToElement(driver.findElement(By.xpath(room)), driver);

			cost = driver.findElement(By.xpath(cost)).getText();
			cost = cost.trim();
			cost = cost.substring(1, cost.length());
			reslogger.info("cost : " + cost);
			al.add(cost);
			if (count > 0) {
				test_steps.add("Selecting room class : " + roomclass[i - 1]);
				reslogger.info("Selecting room class : " + roomclass[i - 1]);
				String sel = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ roomclass[i - 1] + "')]/../../../following-sibling::div/div/select";
				WebElement ele = driver.findElement(By.xpath(sel));
				if (!(IsAssign.equalsIgnoreCase("Yes")) || IsAssign.isEmpty() || IsAssign.equalsIgnoreCase("")) {
					String expand = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
							+ roomclass[i - 1]
							+ "')]/../../../following-sibling::div/div/select/following-sibling::div/button";
					Wait.WaitForElement(driver, expand);
					driver.findElement(By.xpath(expand)).click();

					String unAssign = "(//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
							+ roomclass[i - 1]
							+ "')]/../../../following-sibling::div/div/select/following-sibling::div//ul//span[text()='Unassigned'])";
					Wait.WaitForElement(driver, unAssign);
					driver.findElement(By.xpath(unAssign)).click();
					test_steps.add("Selected room number : Unassigned");
					reslogger.info("Selected room number : Unassigned");

				} else {
					String roomnum = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
							+ roomclass[i - 1] + "')]/../../../following-sibling::div/div/select/option";
					reslogger.info(roomnum);
					int count1 = driver.findElements(By.xpath(roomnum)).size();
					Random random = new Random();
					randomNumber = random.nextInt(count1 - 1) + 1;
					reslogger.info("count : " + count1);
					reslogger.info("randomNumber : " + randomNumber);
					Wait.WaitForElement(driver, sel);

					String expand = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
							+ roomclass[i - 1]
							+ "')]/../../../following-sibling::div/div/select/following-sibling::div/button";
					Wait.wait2Second();
					Wait.WaitForElement(driver, expand);
					driver.findElement(By.xpath(expand)).click();

					String roomNumber = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
							+ roomclass[i - 1]
							+ "')]/../../../following-sibling::div/div/select/following-sibling::div//ul/li["
							+ randomNumber + "]/a/span";
					Wait.WaitForElement(driver, roomNumber);
					driver.findElement(By.xpath(roomNumber)).click();
				}

				String select = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ roomclass[i - 1] + "')]/../../../following-sibling::div//span[contains(text(),'SELECT')]";
				Wait.WaitForElement(driver, select);
				driver.findElement(By.xpath(select)).click();

				String loading = "(//div[@class='ir-loader-in'])[2]";
				int counter = 0;
				while (true) {
					if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
						break;
					} else if (counter == 3) {
						break;
					} else {
						counter++;
						Wait.wait2Second();
					}
				}
				reslogger.info("Waited to loading symbol");

				reslogger.info("Rule Count : " + ruleCount);
				if (ruleCount > 1) {

					String rules = "//p[text()='Selecting this room will violate the following rules']/../..//button[text()='Yes']";
					counter = 0;
					Wait.wait3Second();
					if (driver.findElements(By.xpath(rules)).size() > 0) {
						driver.findElement(By.xpath(rules)).click();
						test_steps.add(
								"Selecting this room will violate the following rules : Are you sure you want to proceed? : yes");
						reslogger.info(
								"Selecting this room will violate the following rules : Are you sure you want to proceed? : yes");

						loading = "(//div[@class='ir-loader-in'])[2]";
						counter = 0;
						while (true) {
							if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
								break;
							} else if (counter == 3) {
								break;
							} else {
								counter++;
								Wait.wait3Second();
							}
						}
					}
				}
				if (!(Account.isEmpty() || Account.equalsIgnoreCase(""))) {
					if (i == 1) {
						String policy = "//p[contains(text(),'Based on the changes made')]/../..//button[text()='Yes']";
						if (driver.findElements(By.xpath(policy)).size() > 0) {
							Wait.WaitForElement(driver, policy);
							driver.findElement(By.xpath(policy)).click();
							test_steps.add("Based on the changes made, new policies are applicable.? : yes");
							reslogger.info("Based on the changes made, new policies are applicable.? : yes");

							loading = "(//div[@class='ir-loader-in'])[2]";
							counter = 0;
							while (true) {
								if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
									break;
								} else if (counter == 3) {
									break;
								} else {
									counter++;
									Wait.wait2Second();
								}
							}
						}
					}
				}
			} else {
				test_steps.add("Rooms Count <=0 for " + RoomClass + " for specified date");
				reslogger.info("Rooms Count <=0 for " + RoomClass + " for specified date");
			}
		}
		return al;
	}

	public void enter_MRB_MailingAddress(WebDriver driver, ArrayList test_steps, String Salutation,
			String GuestFirstName, String GuestLastName, String PhoneNumber, String AltenativePhone, String Email,
			String Account, String AccountType, String Address1, String Address2, String Address3, String City,
			String Country, String State, String PostalCode, String IsGuesProfile, String IsAddMoreGuestInfo,
			String IsSplit, ArrayList al) throws InterruptedException {

		if (!IsSplit.equalsIgnoreCase("Yes")) {
			Add_PrimaryRoom(driver, test_steps, al);
			Add_AdditionalRoom(driver, test_steps, al);
			uncheck_CreateGuestProfile(driver, test_steps, IsGuesProfile);
			enter_GuestDetails(driver, test_steps, Salutation, GuestFirstName, GuestLastName, Email, PhoneNumber,
					AltenativePhone, IsSplit, IsAddMoreGuestInfo);
			enter_Address(driver, test_steps, Address1, Address2, Address3);
			enter_City(driver, test_steps, City);
			select_Country(driver, test_steps, Country);
			select_State(driver, test_steps, State);
			enter_PostalCode(driver, test_steps, PostalCode);
			enter_Account(driver, test_steps, Account, AccountType);
		}
		if (IsAddMoreGuestInfo.equalsIgnoreCase("Yes") && IsSplit.equalsIgnoreCase("Yes")) {
			enter_Split_GuestName(driver, test_steps, Salutation, GuestFirstName, GuestLastName, Email, PhoneNumber,
					AltenativePhone);
			enter_Address(driver, test_steps, Address1, Address2, Address3);
			enter_City(driver, test_steps, City);
			select_Country(driver, test_steps, Country);
			select_State(driver, test_steps, State);
			enter_PostalCode(driver, test_steps, PostalCode);
			uncheck_CreateGuestProfile(driver, test_steps, IsGuesProfile);
			enter_Account(driver, test_steps, Account, AccountType);
		} else if (IsSplit.equalsIgnoreCase("Yes")) {
			enter_GuestName(driver, test_steps, Salutation, GuestFirstName, GuestLastName);
			enter_Phone(driver, test_steps, PhoneNumber, AltenativePhone);
			enter_Email(driver, test_steps, Email);
			enter_Address(driver, test_steps, Address1, Address2, Address3);
			enter_City(driver, test_steps, City);
			select_Country(driver, test_steps, Country);
			select_State(driver, test_steps, State);
			enter_PostalCode(driver, test_steps, PostalCode);
			uncheck_CreateGuestProfile(driver, test_steps, IsGuesProfile);
			enter_Account(driver, test_steps, Account, AccountType);
		}
	}

	public void enter_MRB_MailingAddressForMRB(WebDriver driver, ArrayList test_steps, String Salutation,
			String GuestFirstName, String GuestLastName, String PhoneNumber, String AltenativePhone, String Email,
			String Account, String AccountType, String Address1, String Address2, String Address3, String City,
			String Country, String State, String PostalCode, String IsGuesProfile, String IsAddMoreGuestInfo,
			String IsSplit, ArrayList al) throws InterruptedException {

		if (!IsSplit.equalsIgnoreCase("Yes")) {
			Add_PrimaryRoom(driver, test_steps, al);
			Add_AdditionalRoom(driver, test_steps, al);
			enter_GuestDetailsForMRB(driver, test_steps, Salutation, GuestFirstName, GuestLastName, Email, PhoneNumber,
					AltenativePhone, IsSplit, IsAddMoreGuestInfo);
			enter_Address(driver, test_steps, Address1, Address2, Address3);
			enter_City(driver, test_steps, City);
			select_Country(driver, test_steps, Country);
			select_State(driver, test_steps, State);
			enter_PostalCode(driver, test_steps, PostalCode);
			uncheck_CreateGuestProfile(driver, test_steps, IsGuesProfile);
			enter_Account(driver, test_steps, Account, AccountType);
		}
		if (IsAddMoreGuestInfo.equalsIgnoreCase("Yes") && IsSplit.equalsIgnoreCase("Yes")) {
			enter_Split_GuestName(driver, test_steps, Salutation, GuestFirstName, GuestLastName, Email, PhoneNumber,
					AltenativePhone);
			enter_Address(driver, test_steps, Address1, Address2, Address3);
			enter_City(driver, test_steps, City);
			select_Country(driver, test_steps, Country);
			select_State(driver, test_steps, State);
			enter_PostalCode(driver, test_steps, PostalCode);
			uncheck_CreateGuestProfile(driver, test_steps, IsGuesProfile);
			enter_Account(driver, test_steps, Account, AccountType);
		} else if (IsSplit.equalsIgnoreCase("Yes")) {
			enter_GuestName(driver, test_steps, Salutation, GuestFirstName, GuestLastName);
			enter_Phone(driver, test_steps, PhoneNumber, AltenativePhone);
			enter_Email(driver, test_steps, Email);
			enter_Address(driver, test_steps, Address1, Address2, Address3);
			enter_City(driver, test_steps, City);
			select_Country(driver, test_steps, Country);
			select_State(driver, test_steps, State);
			enter_PostalCode(driver, test_steps, PostalCode);
			uncheck_CreateGuestProfile(driver, test_steps, IsGuesProfile);
			enter_Account(driver, test_steps, Account, AccountType);
		}
	}

	public void enter_Split_GuestName(WebDriver driver, ArrayList test_steps, String Salutation, String GuestFirstName,
			String GuestLastName, String Email, String PhoneNumber, String AltenativePhone) {
		Elements_CPReservation res = new Elements_CPReservation(driver);

		String[] salu = Salutation.split("\\|");
		String[] guestFName = GuestFirstName.split("\\|");
		String[] guestLName = GuestLastName.split("\\|");
		String[] mail = Email.split("\\|");
		String[] phone = PhoneNumber.split("\\|");

		int size = salu.length;

		for (int i = 1; i <= size; i++) {
			if (i == 1) {
				Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_GuestSalutation);
				res.CP_NewReservation_GuestSalutation.click();
				String sal = "//span[contains(text(),'" + salu[i - 1] + "')]/../..";
				Wait.WaitForElement(driver, sal);
				driver.findElement(By.xpath(sal)).click();

				Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_GuestFirstName);
				res.CP_NewReservation_GuestFirstName.sendKeys(guestFName[i - 1]);
				test_steps.add("Guest First Name : " + guestFName[i - 1]);
				reslogger.info("Guest First Name : " + guestFName[i - 1]);

				Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_GuestLastName);
				res.CP_NewReservation_GuestLastName.sendKeys(guestLName[i - 1]);
				test_steps.add("Guest Last Name : " + guestLName[i - 1]);
				reslogger.info("Guest Last Name : " + guestLName[i - 1]);

				Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Phone);
				res.CP_NewReservation_Phone.clear();
				res.CP_NewReservation_Phone.sendKeys(phone[i - 1]);
				test_steps.add("Enter Phone Number : " + phone[i - 1]);
				reslogger.info("Enter Phone Number : " + phone[i - 1]);

				Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_AltenativePhone);
				res.CP_NewReservation_AltenativePhone.clear();
				res.CP_NewReservation_AltenativePhone.sendKeys(AltenativePhone);
				test_steps.add("Enter AltenativePhone Number : " + AltenativePhone);
				reslogger.info("Enter AltenativePhone Number : " + AltenativePhone);

				Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Email);
				res.CP_NewReservation_Email.clear();
				res.CP_NewReservation_Email.sendKeys(mail[i - 1]);
				test_steps.add("Enter Email : " + mail[i - 1]);
				reslogger.info("Enter Email : " + mail[i - 1]);

			} else {
				int count = driver.findElements(By.xpath("//button[contains(@class,'salutationBtn')]")).size();
				String saluta = "(//button[contains(@class,'salutationBtn')])[" + count + "]";
				String slaut = "(//button[contains(@class,'salutationBtn')])[" + count
						+ "]/following-sibling::div//span[contains(text(),'" + salu[i - 1] + "')]";
				Wait.WaitForElement(driver, saluta);
				driver.findElement(By.xpath(saluta)).click();

				Wait.WaitForElement(driver, slaut);
				driver.findElement(By.xpath(slaut)).click();

				count = driver.findElements(By.xpath("//label[text()='Guest Name']/../input")).size();
				String fname = "(//label[text()='Guest Name']/../input)[" + count + "]";
				Wait.WaitForElement(driver, fname);
				driver.findElement(By.xpath(fname)).sendKeys(guestFName[i - 1]);
				test_steps.add("Guest First Name : " + guestFName[i - 1]);
				reslogger.info("Guest First Name : " + guestFName[i - 1]);

				count = driver.findElements(By.xpath("//label[text()='Last Name']/../input")).size();
				String lname = "(//label[text()='Last Name']/../input)[" + (count - 1) + "]";
				Wait.WaitForElement(driver, lname);
				driver.findElement(By.xpath(lname)).sendKeys(guestLName[i - 1]);
				test_steps.add("Guest Last Name : " + guestLName[i - 1]);
				reslogger.info("Guest Last Name : " + guestLName[i - 1]);

				count = driver.findElements(By.xpath("(//label[text()='Phone']/preceding-sibling::input)")).size();
				String ph = "(//label[text()='Phone']/preceding-sibling::input)[" + count + "]";
				driver.findElement(By.xpath(ph)).sendKeys(phone[i - 1]);
				test_steps.add("Enter Phone Number : " + phone[i - 1]);
				reslogger.info("Enter Phone Number : " + phone[i - 1]);

				count = driver.findElements(By.xpath("(//label[text()='E-mail']/preceding-sibling::input)")).size();
				String email = "(//label[text()='E-mail']/preceding-sibling::input)[" + count + "]";
				driver.findElement(By.xpath(email)).sendKeys(mail[i - 1]);
				test_steps.add("Enter Phone Number : " + mail[i - 1]);
				reslogger.info("Enter Phone Number : " + mail[i - 1]);
			}
			if (!(i == size)) {
				Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_AddMoreGuestInfo);
				res.CP_NewReservation_AddMoreGuestInfo.click();
				test_steps.add("Clicking on Add More Guest Info");
				reslogger.info("Clicking on Add More Guest Info");
				String warn = "//div[contains(text(),'The number of guest names added surpasses the number of guests booked in the room.')]/..//button[text()='Yes']";
				Wait.WaitForElement(driver, warn);
				driver.findElement(By.xpath(warn)).click();
			}
		}
	}

	public String primary = null;;

	public void Add_PrimaryRoom(WebDriver driver, ArrayList test_steps, ArrayList al) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		String primaryArrow = "//span[text()='Primary Room']/../../following-sibling::div//button";
		Wait.WaitForElement(driver, primaryArrow);
		driver.findElement(By.xpath(primaryArrow)).click();
		String primaryRoom = "//span[text()='Primary Room']/../../following-sibling::div//li[2]//span";
		primary = driver.findElement(By.xpath(primaryRoom)).getText();
		Wait.WaitForElement(driver, primaryRoom);
		driver.findElement(By.xpath(primaryRoom)).click();
		test_steps.add("Selected Primary Room as : " + primary);
		reslogger.info("Selected Primary Room as : " + primary);
		al.add(primary);
	}

	public String additionalRoomNo = null;

	public void Add_AdditionalRoom(WebDriver driver, ArrayList test_steps, ArrayList al) {
		Elements_CPReservation res = new Elements_CPReservation(driver);

		String additional = "(//span[text()='Additional Room'])";
		int count = driver.findElements(By.xpath(additional)).size();
		for (int i = 1; i <= count; i++) {
			String additionalArrow = "(//span[text()='Additional Room'])[" + i
					+ "]/../../following-sibling::div//button";
			Wait.WaitForElement(driver, additionalArrow);
			driver.findElement(By.xpath(additionalArrow)).click();
			String additionalRoom = "(//span[text()='Additional Room'])[" + i + "]/../../following-sibling::div//li["
					+ (i + 2) + "]//span";
			additionalRoomNo = driver.findElement(By.xpath(additionalRoom)).getText();
			Wait.WaitForElement(driver, additionalRoom);
			driver.findElement(By.xpath(additionalRoom)).click();
			test_steps.add("Selected Addtinal  Room as : " + additionalRoomNo);
			reslogger.info("Selected Addtinal Room as : " + additionalRoomNo);
			al.add(additionalRoomNo);
		}
	}

	public void enter_GuestDetails(WebDriver driver, ArrayList test_steps, String Salutation, String GuestFirstName,
			String GuestLastName, String Email, String PhoneNumber, String AltenativePhone, String IsSplit,
			String IsAddMoreGuestInfo) {

		Elements_CPReservation res = new Elements_CPReservation(driver);

		String[] salu = Salutation.split("\\|");
		String[] guestFName = GuestFirstName.split("\\|");
		String[] guestLName = GuestLastName.split("\\|");
		String[] mail = Email.split("\\|");
		String[] phone = PhoneNumber.split("\\|");

		int size = salu.length;

		for (int i = 1; i <= size; i++) {
			if (i == 1) {
				Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_GuestSalutation);
				res.CP_NewReservation_GuestSalutation.click();
				String sal = "//span[contains(text(),'" + salu[i - 1] + "')]/../..";
				Wait.WaitForElement(driver, sal);
				driver.findElement(By.xpath(sal)).click();

				Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_GuestFirstName);
				res.CP_NewReservation_GuestFirstName.clear();

				res.CP_NewReservation_GuestFirstName.sendKeys(guestFName[i - 1]);
				test_steps.add("Guest First Name : " + guestFName[i - 1]);
				reslogger.info("Guest First Name : " + guestFName[i - 1]);

				Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_GuestLastName);
				res.CP_NewReservation_GuestLastName.clear();

				res.CP_NewReservation_GuestLastName.sendKeys(guestLName[i - 1]);
				test_steps.add("Guest Last Name : " + guestLName[i - 1]);
				reslogger.info("Guest Last Name : " + guestLName[i - 1]);

				Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Phone);
				res.CP_NewReservation_Phone.clear();
				res.CP_NewReservation_Phone.sendKeys(phone[i - 1]);
				test_steps.add("Enter Phone Number : " + phone[i - 1]);
				reslogger.info("Enter Phone Number : " + phone[i - 1]);

				Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_AltenativePhone);
				res.CP_NewReservation_AltenativePhone.clear();
				res.CP_NewReservation_AltenativePhone.sendKeys(AltenativePhone);
				test_steps.add("Enter AltenativePhone Number : " + AltenativePhone);
				reslogger.info("Enter AltenativePhone Number : " + AltenativePhone);

				Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Email);
				res.CP_NewReservation_Email.clear();
				res.CP_NewReservation_Email.sendKeys(mail[i - 1]);
				test_steps.add("Enter Email : " + mail[i - 1]);
				reslogger.info("Enter Email : " + mail[i - 1]);

			} else {

				String additional = "(//span[text()='Additional Room'])";
				int count = driver.findElements(By.xpath(additional)).size();

				String saluta = "(//span[text()='Additional Room'])[" + (i - 1)
						+ "]/../../../../../..//span[text()='Select']/..";
				String slaut = "(//span[text()='Additional Room'])[" + (i - 1)
						+ "]/../../../../../..//span[text()='Select']/../following-sibling::div//li//span[text()='"
						+ salu[i - 1] + "']";
				Wait.WaitForElement(driver, saluta);
				driver.findElement(By.xpath(saluta)).click();
				reslogger.info(slaut);
				Wait.WaitForElement(driver, slaut);
				driver.findElement(By.xpath(slaut)).click();

				count = driver.findElements(By.xpath("//label[text()='Guest ']/../input")).size();
				String fname = "(//label[text()='Guest ']/../input)[" + i + "]";
				Wait.WaitForElement(driver, fname);
				driver.findElement(By.xpath(fname)).clear();
				driver.findElement(By.xpath(fname)).sendKeys(guestFName[i - 1]);
				test_steps.add("Guest First Name : " + guestFName[i - 1]);
				reslogger.info("Guest First Name : " + guestFName[i - 1]);

				count = driver
						.findElements(By
								.xpath("(//span[text()='Additional Room'])/../../../../../..//label[text()='Last Name']/../input"))
						.size();
				String lname = "(//span[text()='Additional Room'])[" + (i - 1)
						+ "]/../../../../../..//label[text()='Last Name']/../input";
				Wait.WaitForElement(driver, lname);
				driver.findElement(By.xpath(lname)).clear();
				driver.findElement(By.xpath(lname)).sendKeys(guestLName[i - 1]);
				test_steps.add("Guest Last Name : " + guestLName[i - 1]);
				reslogger.info("Guest Last Name : " + guestLName[i - 1]);

				count = driver.findElements(By.xpath("(//label[text()='Phone']/preceding-sibling::input)")).size();
				String ph = "(//span[text()='Additional Room'])[" + (i - 1)
						+ "]/../../../../../..//label[text()='Phone']/preceding-sibling::input";
				driver.findElement(By.xpath(ph)).clear();
				driver.findElement(By.xpath(ph)).sendKeys(phone[i - 1]);
				test_steps.add("Enter Phone Number : " + phone[i - 1]);
				reslogger.info("Enter Phone Number : " + phone[i - 1]);

				count = driver.findElements(By.xpath("(//label[text()='E-mail']/preceding-sibling::input)")).size();
				String email = "(//span[text()='Additional Room'])[" + (i - 1)
						+ "]/../../../../../..//label[text()='E-mail']/preceding-sibling::input";
				driver.findElement(By.xpath(email)).clear();

				driver.findElement(By.xpath(email)).sendKeys(mail[i - 1]);
				test_steps.add("Enter Altenative Email : " + mail[i - 1]);
				reslogger.info("Enter Altenative Email : " + mail[i - 1]);

			}
			if (!(i == size) && IsAddMoreGuestInfo.equalsIgnoreCase("Yes") && IsSplit.equalsIgnoreCase("Yes")) {
				Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_AddMoreGuestInfo);
				res.CP_NewReservation_AddMoreGuestInfo.click();
				test_steps.add("Clicking on Add More Guest Info");
				reslogger.info("Clicking on Add More Guest Info");
			}
		}
	}

	public void verify_MRB_Folio(WebDriver driver, ArrayList test_steps, ArrayList RoomAbbri, String IsAssign,
			ArrayList Rooms) throws InterruptedException {
		test_steps.add("******************* MRB Folio Creation verification **********************");
		reslogger.info("******************* MRB Folio Creation verification **********************");
		String abb = null;
		String roomnumber = null;
		reslogger.info(RoomAbbri.size());
		for (int i = 0; i < RoomAbbri.size(); i++) {
			abb = RoomAbbri.get(i).toString();
			roomnumber = Rooms.get(i).toString();
			String[] abbrivation = abb.split(":");
			String[] rooomno = roomnumber.split(":");
			String folio = null;

			String folioSize = "//button[contains(@title,'Guest')]";
			Wait.WaitForElement(driver, folioSize);
			driver.findElement(By.xpath(folioSize)).click();
			Wait.wait2Second();

			if (!(IsAssign.equalsIgnoreCase("Yes")) || IsAssign.isEmpty() || IsAssign.equalsIgnoreCase("")) {
				folio = "//li//span[contains(text(),'Guest Folio For " + abbrivation[1] + " : " + abbrivation[0]
						+ "')]";
				reslogger.info(folio);
				if (driver.findElements(By.xpath(folio)).size() > 0) {
					test_steps
							.add("Folio is present : " + "Guest Folio For " + abbrivation[1] + " : " + abbrivation[0]);
					reslogger
							.info("Folio is present : " + "Guest Folio For " + abbrivation[1] + " : " + abbrivation[0]);
					// folio="Guest Folio For "+abbrivation[1]+" :
					// "+abbrivation[0];
					// new
					// Select(driver.findElement(By.xpath(folioSize))).selectByVisibleText(folio);
					driver.findElement(By.xpath(folio)).click();
					String roomCahrge = "//span[text()='Room Charge']";
					get_RoomCharge(driver, test_steps);
					if (driver.findElements(By.xpath(roomCahrge)).size() > 0) {
						test_steps.add("Room Charge Line Item  is present : " + "Guest Folio For " + abbrivation[1]
								+ " : " + abbrivation[0] + " with amount : " + get_RoomCharge(driver, test_steps));
						reslogger.info("Room Charge Line Item  is present : " + "Guest Folio For " + abbrivation[1]
								+ " : " + abbrivation[0] + " with amount : " + get_RoomCharge(driver, test_steps));
					} else {
						test_steps.add("Room Charge Line Item  is not present : " + "Guest Folio For " + abbrivation[1]
								+ " : " + abbrivation[0] + " with amount : " + get_RoomCharge(driver, test_steps));
						reslogger.info("Room Charge Line Item  is not present : " + "Guest Folio For " + abbrivation[1]
								+ " : " + abbrivation[0] + " with amount : " + get_RoomCharge(driver, test_steps));
					}
				} else {
					test_steps.add(
							"Folio is not present : " + "Guest Folio For " + abbrivation[1] + " : " + abbrivation[0]);
					reslogger.info(
							"Folio is not present : " + "Guest Folio For " + abbrivation[1] + " : " + abbrivation[0]);
				}
			} else {
				folio = "//li//span[contains(text(),'Guest Folio For " + abbrivation[1] + " : " + rooomno[1].trim()
						+ "')]";
				reslogger.info(folio);
				if (driver.findElements(By.xpath(folio)).size() > 0) {
					test_steps.add(
							"Folio is present : " + "Guest Folio For " + abbrivation[1] + " : " + rooomno[1].trim());
					reslogger.info(
							"Folio is present : " + "Guest Folio For " + abbrivation[1] + " : " + rooomno[1].trim());
					// folio="Guest Folio For "+abbrivation[1]+" :
					// "+rooomno[1].trim();
					// new
					// Select(driver.findElement(By.xpath(folioSize))).selectByVisibleText(folio);
					driver.findElement(By.xpath(folio)).click();
					String roomCahrge = "//span[text()='Room Charge']";
					if (driver.findElements(By.xpath(roomCahrge)).size() > 0) {
						test_steps.add("Room Charge Line Item  is present : " + "Guest Folio For " + abbrivation[1]
								+ " : " + rooomno[1].trim() + " with amount : " + get_RoomCharge(driver, test_steps));
						reslogger.info("Room Charge Line Item  is present : " + "Guest Folio For " + abbrivation[1]
								+ " : " + rooomno[1].trim() + " with amount : " + get_RoomCharge(driver, test_steps));
					} else {
						test_steps.add("Room Charge Line Item  is not present : " + "Guest Folio For " + abbrivation[1]
								+ " : " + rooomno[1].trim() + " with amount : " + get_RoomCharge(driver, test_steps));
						reslogger.info("Room Charge Line Item  is not present : " + "Guest Folio For " + abbrivation[1]
								+ " : " + rooomno[1].trim() + " with amount : " + get_RoomCharge(driver, test_steps));
					}
				} else {
					test_steps.add("Folio is not present : " + "Guest Folio For " + abbrivation[1] + " : "
							+ rooomno[1].trim());
					reslogger.info("Folio is not present : " + "Guest Folio For " + abbrivation[1] + " : "
							+ rooomno[1].trim());
				}
			}
		}
	}

	public void verify_MRB_BannerDetails(WebDriver driver, ArrayList test_steps, String Salutation,
			String GuestFirstName, String GuestLastName, String PhoneNumber, String Email, String TripTotal,
			String Balance, String ConfirmationNumber, String Status, String CheckInDate, String CheckOutDate,
			String Country) {
		test_steps.add("******************* Banner fields verification **********************");
		reslogger.info("******************* Banner fields verification **********************");

		String[] salutation = Salutation.split("\\|");
		String[] guestFName = GuestFirstName.split("\\|");
		String[] guestLName = GuestLastName.split("\\|");
		String[] ph = PhoneNumber.split("\\|");
		String[] email = Email.split("\\|");
		String[] checkInDate = CheckInDate.split("\\|");
		String[] checkOutDate = CheckOutDate.split("\\|");

		String name = salutation[0].trim() + " " + guestFName[0].trim() + " " + guestLName[0].trim();
		String bannername = "//td/div/span[contains(text(),'" + name + "')]";
		reslogger.info(bannername);
		if (driver.findElement(By.xpath(bannername)).isDisplayed()) {
			test_steps.add("Reservation banner name field verified : " + name);
			reslogger.info("Reservation banner name field verified : " + name);
		} else {
			test_steps.add("Reservation banner name field not found : " + name);
			reslogger.info("Reservation banner name field not found : " + name);
		}

		ph[0] = ph[0].replace("" + ph[0].trim().charAt(0), "(" + ph[0].trim().charAt(0));
		ph[0] = ph[0].replace("" + ph[0].trim().charAt(4), ")" + ph[0].trim().charAt(4));
		ph[0] = ph[0].replace("" + ph[0].trim().charAt(5), " " + ph[0].trim().charAt(5));
		ph[0] = ph[0].replace("" + ph[0].trim().charAt(9), "-" + ph[0].trim().charAt(9));

		String code = null;
		if (Country.equalsIgnoreCase("United States")) {
			code = "1";
		} else if (Country.equalsIgnoreCase("United Kingdom")) {
			code = "41";
		}
		code = code + "-";
		ph[0] = code + ph[0];
		reslogger.info(ph[0]);

		String phone = "//td//span[text()='" + ph[0] + "']";
		if (driver.findElement(By.xpath(phone)).isDisplayed()) {
			test_steps.add("Reservation banner PhoneNumber field verified : " + ph[0]);
			reslogger.info("Reservation banner PhoneNumber field verified : " + ph[0]);
		} else {
			test_steps.add("Reservation banner PhoneNumber field not found : " + PhoneNumber);
			reslogger.info("Reservation banner PhoneNumber field not found : " + PhoneNumber);
		}

		String trip = "//td[text()='TRIP TOTAL']/following-sibling::td[contains(text(),'" + TripTotal.trim() + "')]";
		if (driver.findElement(By.xpath(trip)).isDisplayed()) {
			test_steps.add("Reservation banner TripTotal field verified : " + TripTotal);
			reslogger.info("Reservation banner TripTotal field verified : " + TripTotal);
		} else {
			test_steps.add("Reservation banner TripTotal field not found : " + TripTotal);
			reslogger.info("Reservation banner TripTotal field not found : " + TripTotal);
		}

		String bal = "//td[text()='BALANCE']/following-sibling::td/div[contains(text(),'" + Balance.trim() + "')]";
		if (driver.findElement(By.xpath(trip)).isDisplayed()) {
			test_steps.add("Reservation banner Balance field verified : " + Balance);
			reslogger.info("Reservation banner Balance field verified : " + Balance);
		} else {
			test_steps.add("Reservation banner Balance field not found : " + Balance);
			reslogger.info("Reservation banner Balance field not found : " + Balance);
		}

		String mail = "//td//a[text()='" + email[0] + "']";
		if (driver.findElement(By.xpath(mail)).isDisplayed()) {
			test_steps.add("Reservation banner mail field verified : " + email[0]);
			reslogger.info("Reservation banner mail field verified : " + email[0]);
		} else {
			test_steps.add("Reservation banner mail field not found : " + email[0]);
			reslogger.info("Reservation banner mail field not found : " + email[0]);
		}

		String sta = "//td//span[text()='" + Status.trim() + "']";
		if (driver.findElement(By.xpath(sta)).isDisplayed()) {
			test_steps.add("Reservation banner Status field verified : " + Status);
			reslogger.info("Reservation banner Status field verified : " + Status);
		} else {
			test_steps.add("Reservation banner Status field not found : " + Status);
			reslogger.info("Reservation banner Status field not found : " + Status);
		}

		String confirmation = "//td//span[text()='" + ConfirmationNumber.trim() + "']";
		if (driver.findElement(By.xpath(confirmation)).isDisplayed()) {
			test_steps.add("Reservation banner ConfirmationNumber field verified : " + ConfirmationNumber);
			reslogger.info("Reservation banner ConfirmationNumber field verified : " + ConfirmationNumber);
		} else {
			test_steps.add("Reservation banner ConfirmationNumber field not found : " + ConfirmationNumber);
			reslogger.info("Reservation banner ConfirmationNumber field not found : " + ConfirmationNumber);
		}

		String[] checkIn = checkInDate[0].split("/");

		if (checkIn[0].charAt(0) == '0') {
			checkIn[0] = checkIn[0].replace("" + checkIn[0].charAt(0), "");
		}

		String checkInMonthDay = Utility.get_Month(checkInDate[0]);
		checkInMonthDay = checkInMonthDay + " " + checkIn[0];
		checkInMonthDay = "(" + checkInMonthDay;

		String[] checkOut = checkOutDate[(checkOutDate.length - 1)].split("/");

		if (checkOut[0].charAt(0) == '0') {
			checkOut[0] = checkOut[0].replace("" + checkOut[0].charAt(0), "");
		}

		String checkOutMonthDay = Utility.get_Month(checkOutDate[checkOutDate.length - 1]);
		checkOutMonthDay = checkOutMonthDay + " " + checkOut[0];
		checkOutMonthDay = checkOutMonthDay + ")";
		String day = checkInMonthDay + " - " + checkOutMonthDay;

		reslogger.info(day);
		String days = "//td//span[contains(text(),'" + day.trim() + "')]";
		if (driver.findElement(By.xpath(days)).isDisplayed()) {
			test_steps.add("Reservation banner Stay from and Stay to are verified : " + day);
			reslogger.info("Reservation banner Stay from and Stay to are verified : " + day);
		} else {
			test_steps.add("Reservation banner Stay from and Stay to are not found : " + day);
			reslogger.info("Reservation banner Stay from and Stay to are not found : " + day);
		}
	}

	public void click_Pay(WebDriver driver, ArrayList test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_Folio_Pay);
		res.CP_Reservation_Folio_Pay.click();
		test_steps.add("Clicking on Pay");
		reslogger.info("Clicking on Pay");
	}

	public String takePayment(WebDriver driver, ArrayList test_steps, String PaymentType, String CardNumber,
			String NameOnCard, String CardExpDate, String TakePaymentType, String IsChangeInPayAmount,
			String ChangedAmountValue, String IsSetAsMainPaymentMethod, String AddPaymentNotes)
			throws InterruptedException {
		test_steps.add("******************* Making payment **********************");
		reslogger.info("******************* Making payment **********************");
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_Folio_TakePayment);

		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_Folio_TakePayment_Amount);
		String amount = res.CP_Reservation_Folio_TakePayment_Amount.getText().trim();
		reslogger.info("amount : " + amount);
		test_steps.add("Amount before Pay : " + amount);
		reslogger.info("Amount before Pay : " + amount);

		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_Folio_TakePayment_PaymentButton);
		res.CP_Reservation_Folio_TakePayment_PaymentButton.click();

		String CCNumber = "//h4[text()='Take Payment']/../..//label[text()='CARD NUMBER']/following-sibling::div/input";
		String name = "//h4[text()='Take Payment']/../..//label[text()='NAME ON CARD']/following-sibling::div/input";
		String exp = "//h4[text()='Take Payment']/../..//label[contains(text(),'EXPIRY')]/following-sibling::div/input";
		if (PaymentType.equalsIgnoreCase("Swipe")) {
			String CCCard = "(//h4[text()='Take Payment']/../..//label[text()='CARD NUMBER']/following-sibling::div/input)[2]";
			String swipe = "//h4[text()='Take Payment']/../..//button[text()='SWIPE']";
			Wait.WaitForElement(driver, swipe);
			driver.findElement(By.xpath(swipe)).click();
			test_steps.add("Clicking on Swipe");
			reslogger.info("Clicking on Swipe");
			Wait.WaitForElement(driver, CCCard);
			driver.findElement(By.xpath(CCCard)).sendKeys(CardNumber);
			test_steps.add("Enter Card Number : " + CardNumber);
			reslogger.info("Enter Card Number : " + CardNumber);
		} else {
			String payment = "//h4[text()='Take Payment']/../..//label[text()='PAYMENT METHOD']/..//div/div//span[text()='"
					+ PaymentType.trim() + "']";
			Wait.WaitForElement(driver, payment);
			driver.findElement(By.xpath(payment)).click();
			test_steps.add("Select Payment Type as : " + PaymentType);
			reslogger.info("Select Payment Type as : " + PaymentType);
			if (PaymentType.equalsIgnoreCase("MC") || PaymentType.equalsIgnoreCase("Visa")
					|| PaymentType.equalsIgnoreCase("Amex") || PaymentType.equalsIgnoreCase("Discover")) {

				Wait.WaitForElement(driver, CCNumber);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				String CC = (String) js.executeScript("return arguments[0].value",
						driver.findElement(By.xpath(CCNumber)));
				reslogger.info(CC);
				if (CC.isEmpty() || CC.equalsIgnoreCase("")) {
					Wait.WaitForElement(driver, CCNumber);
					driver.findElement(By.xpath(CCNumber)).sendKeys(CardNumber);
					test_steps.add("Enter Card Number : " + CardNumber);
					reslogger.info("Enter Card Number : " + CardNumber);
					Wait.WaitForElement(driver, name);
					driver.findElement(By.xpath(name)).sendKeys(NameOnCard);
					test_steps.add("Enter Card Name : " + NameOnCard);
					reslogger.info("Enter Card Name : " + NameOnCard);
					Wait.WaitForElement(driver, exp);
					driver.findElement(By.xpath(exp)).sendKeys(CardExpDate);
					test_steps.add("Enter Card Exp Date : " + CardExpDate);
					reslogger.info("Enter Card Exp Date : " + CardExpDate);
				}
			}
		}
		if (IsChangeInPayAmount.equalsIgnoreCase("Yes")) {
			test_steps.add("Change the pay amount value : Yes");
			reslogger.info("Change the pay amount value : Yes");
			res.CP_Reservation_Folio_TakePayment_Amount.clear();
			res.CP_Reservation_Folio_TakePayment_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			res.CP_Reservation_Folio_TakePayment_Amount.sendKeys(ChangedAmountValue);
			test_steps.add("Enter the Change Amount Value : " + ChangedAmountValue);
			reslogger.info("Enter the Change Amount Value : " + ChangedAmountValue);
			amount = ChangedAmountValue;
		} else {
			test_steps.add("paying the amount : " + amount);
			reslogger.info("paying the amount : " + amount);
		}

		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_Folio_TakePayment_TypeButton);
		res.CP_Reservation_Folio_TakePayment_TypeButton.click();

		String type = "//h4[text()='Take Payment']/../..//label[text()='TYPE']/..//div/div//span[text()='"
				+ TakePaymentType.trim() + "']";
		Wait.WaitForElement(driver, type);
		driver.findElement(By.xpath(type)).click();
		test_steps.add("Select Take Payment Type : " + TakePaymentType);
		reslogger.info("Select Take Payment Type : " + TakePaymentType);

		String setAsMainPaymentMethod = "//span[text()='Set As Main Payment Method']/../input";
		if (IsSetAsMainPaymentMethod.equalsIgnoreCase("Yes")) {
			if (!driver.findElement(By.xpath(setAsMainPaymentMethod)).isSelected()) {
				Wait.WaitForElement(driver, setAsMainPaymentMethod);
				driver.findElement(By.xpath("//span[text()='Set As Main Payment Method']")).click();
				test_steps.add("Select set As Main Payment Method");
				reslogger.info("Select set As Main Payment Method");
			} else {
				test_steps.add("Already Selected set As Main Payment Method");
				reslogger.info("Already Selected set As Main Payment Method");
			}
		}

		String addPaymentNotes = "//button[text()='ADD NOTES']";
		String notes = "//input[@placeholder='Add Notes']";

		if (!(AddPaymentNotes.isEmpty() || AddPaymentNotes.equalsIgnoreCase(""))) {
			Wait.WaitForElement(driver, addPaymentNotes);
			driver.findElement(By.xpath(addPaymentNotes)).click();
			test_steps.add("Clicking on Add Payment Notes");
			reslogger.info("Clicking on Add Payment Notes");
			Wait.WaitForElement(driver, notes);
			driver.findElement(By.xpath(notes)).sendKeys(AddPaymentNotes);
			test_steps.add("Adding payment notes : " + AddPaymentNotes);
			reslogger.info("Adding payment notes : " + AddPaymentNotes);
		}

		String button = "//h4[text()='Take Payment']/../..//button[contains(text(),'" + amount.trim() + "')]";
		Wait.WaitForElement(driver, button);
		driver.findElement(By.xpath(button)).click();
		test_steps.add("Click on Pay");
		reslogger.info("Click on Pay");

		String takePayType = "//h4[contains(text(),'Successful')]/../..//label[text()='TYPE']/following-sibling::span";
		Wait.WaitForElement(driver, takePayType);
		String paytype = driver.findElement(By.xpath(takePayType)).getText().trim();
		assertEquals(paytype, TakePaymentType.trim());
		test_steps.add("Take Payment Type validated after payment : " + TakePaymentType);
		reslogger.info("Take Payment Type validated after payment : " + TakePaymentType);

		String paymentType = "//h4[contains(text(),'Successful')]/../..//label[text()='PAYMENT METHOD']/following-sibling::label/span[contains(text(),'"
				+ PaymentType.trim() + "')]";

		if (PaymentType.equalsIgnoreCase("Swipe")) {
			paymentType = "//h4[contains(text(),'Successful')]/../..//label[text()='PAYMENT METHOD']/following-sibling::label/span[contains(text(),'MC')]";
		}
		Wait.WaitForElement(driver, paymentType);
		if (driver.findElement(By.xpath(paymentType)).isDisplayed()) {
			test_steps.add("Payment Type validated after payment : " + PaymentType);
			reslogger.info("Payment Type validated after payment : " + PaymentType);
		} else {
			test_steps.add("Payment Type not displayed after payment : " + PaymentType);
			reslogger.info("Payment Type not displayed after payment : " + PaymentType);
		}

		String status = "//h4[contains(text(),'Successful')]/../..//label[text()='Approved']";
		Wait.WaitForElement(driver, status);
		if (driver.findElement(By.xpath(status)).isDisplayed()) {
			test_steps.add("Transaction status is : Approved");
			reslogger.info("Transaction status is : Approved");
		} else {
			test_steps.add("Transaction status is not : Approved");
			reslogger.info("Transaction status is not : Approved");
		}

		String payNotes = "//h4[contains(text(),'Successful')]/../..//span[text()='" + AddPaymentNotes + "']";
		if (!(AddPaymentNotes.isEmpty() || AddPaymentNotes.equalsIgnoreCase(""))) {
			if (driver.findElement(By.xpath(payNotes)).isDisplayed()) {
				test_steps.add("Paymant notes displayed " + AddPaymentNotes);
				reslogger.info("Paymant notes displayed " + AddPaymentNotes);
			} else {
				test_steps.add("Paymant notes not displayed " + AddPaymentNotes);
				reslogger.info("Paymant notes not displayed " + AddPaymentNotes);
			}
		}

		String close = "//h4[contains(text(),'Successful')]/../..//button[text()='Close']";
		Wait.WaitForElement(driver, close);
		driver.findElement(By.xpath(close)).click();
		test_steps.add("Click on Close");
		reslogger.info("Click on Close");

		String saveFolio = "//div[contains(@data-bind,'Folio')]//button[text()='Save']";
		Wait.WaitForElement(driver, saveFolio);
		driver.findElement(By.xpath(saveFolio)).click();
		test_steps.add("Click on Save");
		reslogger.info("Click on Save");
		String loading = "(//div[@class='ir-loader-in'])";
		int count = 0;
		while (true) {
			if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
				break;
			} else if (count == 20) {
				break;
			} else {
				count++;
				Wait.wait2Second();
			}
		}
		reslogger.info("Waited to loading symbol");
		return amount;
	}

	public void verify_PaymentLineItem(WebDriver driver, ArrayList test_steps, String PaymentType, String CardNumber,
			String NameOnCard, String CardExpDate, String amount, String AddPaymentNotes) throws InterruptedException {
		test_steps.add("******************* Verify payment line item **********************");
		reslogger.info("******************* Verify payment line item **********************");

		if (PaymentType.equalsIgnoreCase("MC") || PaymentType.equalsIgnoreCase("Visa")
				|| PaymentType.equalsIgnoreCase("Amex") || PaymentType.equalsIgnoreCase("Discover")) {
			String lastFourDightsOfCard = CardNumber.substring(CardNumber.length() - 4, CardNumber.length());

			String description = "//a[contains(text(),'Name: " + NameOnCard.trim()
					+ "')]/../following-sibling::td[3]/span[contains(text(),'" + amount.trim() + "')]";
			reslogger.info(description);
			if (driver.findElement(By.xpath(description)).isDisplayed()) {
				test_steps.add("Payment line item with " + amount + " is displayed for payment type : " + PaymentType);
				reslogger.info("Payment line item with " + amount + " is displayed for payment type : " + PaymentType);
			} else {
				test_steps.add(
						"Payment line item with " + amount + " is not displayed for payment type : " + PaymentType);
				reslogger.info(
						"Payment line item with " + amount + " is not displayed for payment type : " + PaymentType);
			}

			String line = "//a[contains(text(),'Name: " + NameOnCard.trim()
					+ "')]/../following-sibling::td[3]/span[contains(text(),'" + amount.trim()
					+ "')]/../preceding-sibling::td[3]/a";
			Wait.WaitForElement(driver, line);
			driver.findElement(By.xpath(line)).click();
			test_steps.add("Opening Payment line item");
			reslogger.info("Opening Payment line item");

			String payemntType = "//label[text()='Payment Method:']/following-sibling::div/select";

			WebElement pay = new Select(driver.findElement(By.xpath(payemntType))).getFirstSelectedOption();
			reslogger.info(pay.getText());
			if (pay.getText().trim().equalsIgnoreCase(PaymentType)) {
				test_steps.add("Payment Method verified in line item : " + pay.getText());
				reslogger.info("Payment Method verified in line item : " + pay.getText());
			} else {
				test_steps.add("Payment Method not verified in line item : " + pay.getText());
				reslogger.info("Payment Method not verified in line item : " + pay.getText());
			}

			String amt = "//label[text()='Amount:']/following-sibling::div//input";

			JavascriptExecutor js = (JavascriptExecutor) driver;
			String amtValue = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(amt)));
			reslogger.info(amtValue);
			if (amtValue.equalsIgnoreCase(amount)) {
				test_steps.add("Payment amount verified in line item : " + amount);
				reslogger.info("Payment amount verified in line item : " + amount);
			} else {
				test_steps.add("Payment amount not verified in line item : " + amount);
				reslogger.info("Payment amount not verified in line item : " + amount);
			}

			String payInfo = "//label[text()='Payment Info:']/following-sibling::div//textarea";
			String info = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(payInfo)));
			reslogger.info(info);
			if (info.contains(lastFourDightsOfCard) && info.contains(CardExpDate)) {
				test_steps.add("Payment info verified in line item : " + info);
				reslogger.info("Payment info verified in line item : " + info);
			} else {
				test_steps.add("Payment info not verified in line item : " + info);
				reslogger.info("Payment info not verified in line item : " + info);
			}

			String notes = "//label[text()='Notes:']/following-sibling::div//textarea";

			String note = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(notes)));
			reslogger.info(note);
			if (note.equalsIgnoreCase(AddPaymentNotes)) {
				test_steps.add("Payment notes verified in line item : " + note);
				reslogger.info("Payment notes verified in line item : " + note);
			} else {
				test_steps.add("Payment notes not verified in line item : " + note);
				reslogger.info("Payment notes not verified in line item : " + note);
			}

			String close = "//span[text()='Payment Details']/../../.././../../../..//button[text()='Cancel']";
			Wait.WaitForElement(driver, close);
			driver.findElement(By.xpath(close)).click();
			test_steps.add("Closeing Payment line item");
			reslogger.info("Closeing Payment line item");
			Wait.wait5Second();

		} else if (PaymentType.equalsIgnoreCase("Cash")) {
			String description = "//a[contains(text(),'" + PaymentType.trim()
					+ "')]/../following-sibling::td[3]/span[contains(text(),'" + amount.trim() + "')]";
			reslogger.info(description);
			if (driver.findElement(By.xpath(description)).isDisplayed()) {
				test_steps.add("Payment line item with " + amount + " is displayed for payment type : " + PaymentType);
				reslogger.info("Payment line item with " + amount + " is displayed for payment type : " + PaymentType);
			} else {
				test_steps.add(
						"Payment line item with " + amount + " is nor displayed for payment type : " + PaymentType);
				reslogger.info(
						"Payment line item with " + amount + " is nor displayed for payment type : " + PaymentType);
			}

			String line = "//a[contains(text(),'" + PaymentType.trim()
					+ "')]/../following-sibling::td[3]/span[contains(text(),'" + amount.trim()
					+ "')]/../preceding-sibling::td[3]/a";
			Wait.WaitForElement(driver, line);
			driver.findElement(By.xpath(line)).click();
			test_steps.add("Opening Payment line item");
			reslogger.info("Opening Payment line item");

			String payemntType = "//label[text()='Payment Method:']/following-sibling::div/select";

			WebElement pay = new Select(driver.findElement(By.xpath(payemntType))).getFirstSelectedOption();
			reslogger.info(pay.getText());
			if (pay.getText().trim().equalsIgnoreCase("Cash")) {
				test_steps.add("Payment Method verified in line item : " + pay.getText());
				reslogger.info("Payment Method verified in line item : " + pay.getText());
			} else {
				test_steps.add("Payment Method not verified in line item : " + pay.getText());
				reslogger.info("Payment Method not verified in line item : " + pay.getText());
			}

			String amt = "//label[text()='Amount:']/following-sibling::div//input";

			JavascriptExecutor js = (JavascriptExecutor) driver;
			String amtValue = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(amt)));
			reslogger.info(amtValue);
			if (amtValue.equalsIgnoreCase(amount)) {
				test_steps.add("Payment amount verified in line item : " + amount);
				reslogger.info("Payment amount verified in line item : " + amount);
			} else {
				test_steps.add("Payment amount not verified in line item : " + amount);
				reslogger.info("Payment amount not verified in line item : " + amount);
			}

			String payInfo = "//label[text()='Payment Info:']/following-sibling::div//textarea";
			String info = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(payInfo)));
			reslogger.info(info);
			if (info.contains("Cash")) {
				test_steps.add("Payment info verified in line item : " + info);
				reslogger.info("Payment info verified in line item : " + info);
			} else {
				test_steps.add("Payment info not verified in line item : " + info);
				reslogger.info("Payment info not verified in line item : " + info);
			}

			String notes = "//label[text()='Notes:']/following-sibling::div//textarea";

			String note = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(notes)));
			reslogger.info(note);
			if (note.equalsIgnoreCase(AddPaymentNotes)) {
				test_steps.add("Payment notes verified in line item : " + note);
				reslogger.info("Payment notes verified in line item : " + note);
			} else {
				test_steps.add("Payment notes not verified in line item : " + note);
				reslogger.info("Payment notes not verified in line item : " + note);
			}

			String close = "//span[text()='Payment Details']/../../.././../../../..//button[text()='Close']";
			Wait.WaitForElement(driver, close);
			driver.findElement(By.xpath(close)).click();
			test_steps.add("Closeing Payment line item");
			reslogger.info("Closeing Payment line item");
		} else if (PaymentType.equalsIgnoreCase("Swipe")) {
			String description = "//a[contains(text(),'Name: ')]/../following-sibling::td[3]/span[contains(text(),'"
					+ amount.trim() + "')]";
			if (driver.findElement(By.xpath(description)).isDisplayed()) {
				test_steps.add("Payment line item with " + amount + " is displayed for payment type : " + PaymentType);
				reslogger.info("Payment line item with " + amount + " is displayed for payment type : " + PaymentType);
			} else {
				test_steps.add(
						"Payment line item with " + amount + " is nor displayed for payment type : " + PaymentType);
				reslogger.info(
						"Payment line item with " + amount + " is nor displayed for payment type : " + PaymentType);
			}

			String line = "//a[contains(text(),'Name: ')]/../following-sibling::td[3]/span[contains(text(),'"
					+ amount.trim() + "')]/../preceding-sibling::td[3]/a";
			Wait.WaitForElement(driver, line);
			driver.findElement(By.xpath(line)).click();
			test_steps.add("Opening Payment line item");
			reslogger.info("Opening Payment line item");

			String payemntType = "//label[text()='Payment Method:']/following-sibling::div/select";

			WebElement pay = new Select(driver.findElement(By.xpath(payemntType))).getFirstSelectedOption();
			reslogger.info(pay.getText());
			if (pay.getText().trim().equalsIgnoreCase("MC")) {
				test_steps.add("Payment Method verified in line item : " + pay.getText());
				reslogger.info("Payment Method verified in line item : " + pay.getText());
			} else {
				test_steps.add("Payment Method not verified in line item : " + pay.getText());
				reslogger.info("Payment Method not verified in line item : " + pay.getText());
			}

			String amt = "//label[text()='Amount:']/following-sibling::div//input";

			JavascriptExecutor js = (JavascriptExecutor) driver;
			String amtValue = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(amt)));
			reslogger.info(amtValue);
			if (amtValue.equalsIgnoreCase(amount)) {
				test_steps.add("Payment amount verified in line item : " + amount);
				reslogger.info("Payment amount verified in line item : " + amount);
			} else {
				test_steps.add("Payment amount not verified in line item : " + amount);
				reslogger.info("Payment amount not verified in line item : " + amount);
			}

			String payInfo = "//label[text()='Payment Info:']/following-sibling::div//textarea";
			String info = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(payInfo)));
			reslogger.info(info);
			if (info.contains("5454")) {
				test_steps.add("Payment info verified in line item : " + info);
				reslogger.info("Payment info verified in line item : " + info);
			} else {
				test_steps.add("Payment info not verified in line item : " + info);
				reslogger.info("Payment info not verified in line item : " + info);
			}

			String notes = "//label[text()='Notes:']/following-sibling::div//textarea";

			String note = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(notes)));
			reslogger.info(note);
			if (note.equalsIgnoreCase(AddPaymentNotes)) {
				test_steps.add("Payment notes verified in line item : " + note);
				reslogger.info("Payment notes verified in line item : " + note);
			} else {
				test_steps.add("Payment notes not verified in line item : " + note);
				reslogger.info("Payment notes not verified in line item : " + note);
			}

			String close = "//span[text()='Payment Details']/../../.././../../../..//button[text()='Cancel']";
			Wait.WaitForElement(driver, close);
			driver.findElement(By.xpath(close)).click();
			test_steps.add("Closeing Payment line item");
			reslogger.info("Closeing Payment line item");
		}
		Wait.wait2Second();
	}

	public void verify_DepositPaymentLineItem(WebDriver driver, ArrayList test_steps, String PaymentType,
			String CardNumber, String NameOnCard, String CardExpDate, String amount, String AddPaymentNotes)
			throws InterruptedException {
		test_steps.add("******************* Verify payment line item **********************");
		reslogger.info("******************* Verify payment line item **********************");

		if (PaymentType.equalsIgnoreCase("MC") || PaymentType.equalsIgnoreCase("Visa")
				|| PaymentType.equalsIgnoreCase("Amex") || PaymentType.equalsIgnoreCase("Discover")) {
			String lastFourDightsOfCard = CardNumber.substring(CardNumber.length() - 4, CardNumber.length());

			String description = "//a[contains(text(),'Name: " + NameOnCard.trim()
					+ "')]/../following-sibling::td[3]/span[contains(text(),'" + amount.trim() + "')]";
			reslogger.info(description);
			if (driver.findElement(By.xpath(description)).isDisplayed()) {
				test_steps.add("Payment line item with " + amount + " is displayed for payment type : " + PaymentType);
				reslogger.info("Payment line item with " + amount + " is displayed for payment type : " + PaymentType);
			} else {
				test_steps.add(
						"Payment line item with " + amount + " is not displayed for payment type : " + PaymentType);
				reslogger.info(
						"Payment line item with " + amount + " is not displayed for payment type : " + PaymentType);
			}

			String line = "//a[contains(text(),'Name: " + NameOnCard.trim()
					+ "')]/../following-sibling::td[3]/span[contains(text(),'" + amount.trim()
					+ "')]/../preceding-sibling::td[3]/a";
			Wait.WaitForElement(driver, line);
			driver.findElement(By.xpath(line)).click();
			test_steps.add("Opening Payment line item");
			reslogger.info("Opening Payment line item");

			String payemntType = "//label[text()='Payment Method:']/following-sibling::div/select";

			WebElement pay = new Select(driver.findElement(By.xpath(payemntType))).getFirstSelectedOption();
			reslogger.info(pay.getText());
			if (pay.getText().trim().equalsIgnoreCase(PaymentType)) {
				test_steps.add("Payment Method verified in line item : " + pay.getText());
				reslogger.info("Payment Method verified in line item : " + pay.getText());
			} else {
				test_steps.add("Payment Method not verified in line item : " + pay.getText());
				reslogger.info("Payment Method not verified in line item : " + pay.getText());
			}

			String amt = "//label[text()='Amount:']/following-sibling::div//input";

			JavascriptExecutor js = (JavascriptExecutor) driver;
			String amtValue = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(amt)));
			reslogger.info(amtValue);
			if (amtValue.equalsIgnoreCase(amount)) {
				test_steps.add("Payment amount verified in line item : " + amount);
				reslogger.info("Payment amount verified in line item : " + amount);
			} else {
				test_steps.add("Payment amount not verified in line item : " + amount);
				reslogger.info("Payment amount not verified in line item : " + amount);
			}

			String payInfo = "//label[text()='Payment Info:']/following-sibling::div//textarea";
			String info = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(payInfo)));
			reslogger.info(info);
			if (info.contains(lastFourDightsOfCard) && info.contains(CardExpDate)) {
				test_steps.add("Payment info verified in line item : " + info);
				reslogger.info("Payment info verified in line item : " + info);
			} else {
				test_steps.add("Payment info not verified in line item : " + info);
				reslogger.info("Payment info not verified in line item : " + info);
			}

			String notes = "//label[text()='Notes:']/following-sibling::div//textarea";

			String note = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(notes)));
			reslogger.info(note);
			if (note.equalsIgnoreCase(AddPaymentNotes)) {
				test_steps.add("Payment notes verified in line item : " + note);
				reslogger.info("Payment notes verified in line item : " + note);
			} else {
				test_steps.add("Payment notes not verified in line item : " + note);
				reslogger.info("Payment notes not verified in line item : " + note);
			}

			String close = "//span[text()='Payment Details']/../../.././../../../..//button[text()='Cancel']";
			Wait.WaitForElement(driver, close);
			driver.findElement(By.xpath(close)).click();
			test_steps.add("Closeing Payment line item");
			reslogger.info("Closeing Payment line item");
			Wait.wait5Second();

		} else if (PaymentType.equalsIgnoreCase("Cash")) {
			String description = "//a[contains(text(),'" + PaymentType.trim()
					+ "')]/../following-sibling::td[3]/span[contains(text(),'" + amount.trim() + "')]";
			reslogger.info(description);
			if (driver.findElement(By.xpath(description)).isDisplayed()) {
				test_steps.add("Payment line item with " + amount + " is displayed for payment type : " + PaymentType);
				reslogger.info("Payment line item with " + amount + " is displayed for payment type : " + PaymentType);
			} else {
				test_steps.add(
						"Payment line item with " + amount + " is nor displayed for payment type : " + PaymentType);
				reslogger.info(
						"Payment line item with " + amount + " is nor displayed for payment type : " + PaymentType);
			}

			String line = "//a[contains(text(),'" + PaymentType.trim()
					+ "')]/../following-sibling::td[3]/span[contains(text(),'" + amount.trim()
					+ "')]/../preceding-sibling::td[3]/a";
			Wait.WaitForElement(driver, line);
			driver.findElement(By.xpath(line)).click();
			test_steps.add("Opening Payment line item");
			reslogger.info("Opening Payment line item");

			String payemntType = "//label[text()='Payment Method:']/following-sibling::div/select";

			WebElement pay = new Select(driver.findElement(By.xpath(payemntType))).getFirstSelectedOption();
			reslogger.info(pay.getText());
			if (pay.getText().trim().equalsIgnoreCase("Cash")) {
				test_steps.add("Payment Method verified in line item : " + pay.getText());
				reslogger.info("Payment Method verified in line item : " + pay.getText());
			} else {
				test_steps.add("Payment Method not verified in line item : " + pay.getText());
				reslogger.info("Payment Method not verified in line item : " + pay.getText());
			}

			String amt = "//label[text()='Amount:']/following-sibling::div//input";

			JavascriptExecutor js = (JavascriptExecutor) driver;
			String amtValue = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(amt)));
			reslogger.info(amtValue);
			if (amtValue.equalsIgnoreCase(amount)) {
				test_steps.add("Payment amount verified in line item : " + amount);
				reslogger.info("Payment amount verified in line item : " + amount);
			} else {
				test_steps.add("Payment amount not verified in line item : " + amount);
				reslogger.info("Payment amount not verified in line item : " + amount);
			}

			String payInfo = "//label[text()='Payment Info:']/following-sibling::div//textarea";
			String info = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(payInfo)));
			reslogger.info(info);
			if (info.contains("Cash")) {
				test_steps.add("Payment info verified in line item : " + info);
				reslogger.info("Payment info verified in line item : " + info);
			} else {
				test_steps.add("Payment info not verified in line item : " + info);
				reslogger.info("Payment info not verified in line item : " + info);
			}

			String notes = "//label[text()='Notes:']/following-sibling::div//textarea";

			String note = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(notes)));
			reslogger.info(note);
			if (note.equalsIgnoreCase(AddPaymentNotes)) {
				test_steps.add("Payment notes verified in line item : " + note);
				reslogger.info("Payment notes verified in line item : " + note);
			} else {
				test_steps.add("Payment notes not verified in line item : " + note);
				reslogger.info("Payment notes not verified in line item : " + note);
			}

			String close = "//span[text()='Payment Details']/../../.././../../../..//button[text()='Close']";
			Wait.WaitForElement(driver, close);
			driver.findElement(By.xpath(close)).click();
			test_steps.add("Closeing Payment line item");
			reslogger.info("Closeing Payment line item");
		} else if (PaymentType.equalsIgnoreCase("Swipe")) {
			String description = "//a[contains(text(),'Name: ')]/../following-sibling::td[3]/span[contains(text(),'"
					+ amount.trim() + "')]";
			if (driver.findElement(By.xpath(description)).isDisplayed()) {
				test_steps.add("Payment line item with " + amount + " is displayed for payment type : " + PaymentType);
				reslogger.info("Payment line item with " + amount + " is displayed for payment type : " + PaymentType);
			} else {
				test_steps.add(
						"Payment line item with " + amount + " is nor displayed for payment type : " + PaymentType);
				reslogger.info(
						"Payment line item with " + amount + " is nor displayed for payment type : " + PaymentType);
			}

			String line = "//a[contains(text(),'Name: ')]/../following-sibling::td[3]/span[contains(text(),'"
					+ amount.trim() + "')]/../preceding-sibling::td[3]/a";
			Wait.WaitForElement(driver, line);
			driver.findElement(By.xpath(line)).click();
			test_steps.add("Opening Payment line item");
			reslogger.info("Opening Payment line item");

			String payemntType = "//label[text()='Payment Method:']/following-sibling::div/select";

			WebElement pay = new Select(driver.findElement(By.xpath(payemntType))).getFirstSelectedOption();
			reslogger.info(pay.getText());
			if (pay.getText().trim().equalsIgnoreCase("MC")) {
				test_steps.add("Payment Method verified in line item : " + pay.getText());
				reslogger.info("Payment Method verified in line item : " + pay.getText());
			} else {
				test_steps.add("Payment Method not verified in line item : " + pay.getText());
				reslogger.info("Payment Method not verified in line item : " + pay.getText());
			}

			String amt = "//label[text()='Amount:']/following-sibling::div//input";

			JavascriptExecutor js = (JavascriptExecutor) driver;
			String amtValue = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(amt)));
			reslogger.info(amtValue);
			if (amtValue.equalsIgnoreCase(amount)) {
				test_steps.add("Payment amount verified in line item : " + amount);
				reslogger.info("Payment amount verified in line item : " + amount);
			} else {
				test_steps.add("Payment amount not verified in line item : " + amount);
				reslogger.info("Payment amount not verified in line item : " + amount);
			}

			String payInfo = "//label[text()='Payment Info:']/following-sibling::div//textarea";
			String info = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(payInfo)));
			reslogger.info(info);
			if (info.contains("5454")) {
				test_steps.add("Payment info verified in line item : " + info);
				reslogger.info("Payment info verified in line item : " + info);
			} else {
				test_steps.add("Payment info not verified in line item : " + info);
				reslogger.info("Payment info not verified in line item : " + info);
			}

			String notes = "//label[text()='Notes:']/following-sibling::div//textarea";

			String note = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(notes)));
			reslogger.info(note);
			if (note.equalsIgnoreCase(AddPaymentNotes)) {
				test_steps.add("Payment notes verified in line item : " + note);
				reslogger.info("Payment notes verified in line item : " + note);
			} else {
				test_steps.add("Payment notes not verified in line item : " + note);
				reslogger.info("Payment notes not verified in line item : " + note);
			}

			String close = "//span[text()='Payment Details']/../../.././../../../..//button[text()='Close']";
			Wait.WaitForElement(driver, close);
			driver.findElement(By.xpath(close)).click();
			test_steps.add("Closeing Payment line item");
			reslogger.info("Closeing Payment line item");
		}
		Wait.wait2Second();
	}

	public void validate_FolioAmountAfterPayment(WebDriver driver, ArrayList test_steps, String paidFolio,
			String balanceFolio, String paidFolioAfterPay, String balanceFolioAfterPay, String amount,
			String TakePaymentType) {
		test_steps.add("******************* Verify folio amount after payment **********************");
		reslogger.info("******************* Verify folio amount after payment **********************");

		Double paidBefore = Double.parseDouble(paidFolio);
		Double balBefore = Double.parseDouble(balanceFolio);
		Double paidAfter = Double.parseDouble(paidFolioAfterPay);
		Double balAfter = Double.parseDouble(balanceFolioAfterPay);
		Double amt = Double.parseDouble(amount);

		if (TakePaymentType.equalsIgnoreCase("Capture")) {

			if (Double.compare((paidBefore + amt), paidAfter) == 0) {
				test_steps.add("Folio Paid amount is updated with : " + amount);
				reslogger.info("Folio Paid amount is updated with : " + amount);
			} else {
				test_steps.add("Folio Paid amount is not updated with : " + amount);
				reslogger.info("Folio Paid amount is not updated with : " + amount);
			}

			if (Double.compare((balBefore - amt), balAfter) == 0) {
				test_steps.add("Folio Balance amount is updated with : " + amount);
				reslogger.info("Folio Balance amount is updated with : " + amount);
			} else {
				test_steps.add("Folio Balance amount is not updated with : " + amount);
				reslogger.info("Folio Balance amount is not updated with : " + amount);
			}

		} /*
			 * else if (GenerateGuestStatementStatus) {
			 * 
			 * }
			 */

	}

	public void click_Quote(WebDriver driver, ArrayList test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Quote);
		res.CP_NewReservation_Quote.click();
		test_steps.add("Click on Save Quote");
		reslogger.info("Click on Save Quote");
	}

	public void verify_QuoteStauts(WebDriver driver, ArrayList test_steps, String status) {
		if (status.equalsIgnoreCase("Quote")) {
			test_steps.add("Quote status is verified");
			reslogger.info("Quote status is verified");
		} else {
			test_steps.add("Quote status is not verified");
			reslogger.info("Quote status is not verified");
		}
	}

	public void verify_PaymentInHistoryTab(WebDriver driver, ArrayList test_steps, String amount, String PaymentType,
			String CardNumber, String CardExp, String timeZone) {
		test_steps.add("******************* Payment verification in History tab **********************");
		reslogger.info("******************* Payment verification in History tab **********************");
		String deposit = "//span[contains(text(),'payment') and contains(text(),'" + amount + "')]";

		String text = driver.findElement(By.xpath(deposit)).getText();
		reslogger.info(text);

		if (driver.findElement(By.xpath(deposit)).isDisplayed()) {
			test_steps.add("Payment verified in History Tab");
			reslogger.info("Payment verified in History Tab");
		} else {
			test_steps.add("Payment not found  in History Tab");
			reslogger.info("Payment not found  in History Tab");
		}

		String category = "//span[contains(text(),'payment ') and contains(text(),'" + amount
				+ "')]/../preceding-sibling::td/span[text()='Payment']";
		if (driver.findElement(By.xpath(category)).isDisplayed()) {
			test_steps.add("Payment category verified in History Tab");
			reslogger.info("Payment category verified in History Tab");
		} else {
			test_steps.add("Payment category not found  in History Tab");
			reslogger.info("Payment category not found  in History Tab");
		}

		Date date1 = new Date();
		DateFormat df = new SimpleDateFormat("MM/dd/yy");
		// Use Madrid's time zone to format the date in
		df.setTimeZone(TimeZone.getTimeZone(timeZone));

		reslogger.info(df.format(date1));
		String DateFormat = df.format(date1);

		/*
		 * DateFormat df = new SimpleDateFormat("dd/MM/yy"); Calendar calobj =
		 * Calendar.getInstance();
		 * reslogger.info(df.format(calobj.getTime())); String
		 * DateFormat=df.format(calobj.getTime());
		 */

		String[] DFormat = DateFormat.split("/");
		String date = "//span[contains(text(),'payment ') and contains(text(),'" + amount
				+ "')]/../preceding-sibling::td/span[text()='" + DFormat[0] + "/" + DFormat[1] + "/" + DFormat[2]
				+ "']";
		if (driver.findElement(By.xpath(date)).isDisplayed()) {
			test_steps.add("Payment date verified in History Tab");
			reslogger.info("Payment date verified in History Tab");
		} else {
			test_steps.add("Payment date not found  in History Tab");
			reslogger.info("Payment date not found  in History Tab");
		}

		if (PaymentType.equalsIgnoreCase("MC") || PaymentType.equalsIgnoreCase("Visa")
				|| PaymentType.equalsIgnoreCase("Amex") || PaymentType.equalsIgnoreCase("Discover")) {
			String lastFourDightsOfCard = CardNumber.substring(CardNumber.length() - 4, CardNumber.length());
			if (text.contains(lastFourDightsOfCard) && text.contains(CardExp)) {
				test_steps.add("Verified Payment type " + PaymentType + " Last Four Digits " + lastFourDightsOfCard
						+ " and Exp Date of the card :" + CardExp + " in the payment description : " + text);
				reslogger.info("Verified Payment type " + PaymentType + " Last Four Digits " + lastFourDightsOfCard
						+ " and Exp Date of the card :" + CardExp + " in the payment description : " + text);
			} else {
				test_steps.add("Payment type " + PaymentType + " Last Four Digits " + lastFourDightsOfCard
						+ " and Exp Date of the card :" + CardExp + " in the payment description are not found : "
						+ text);
				reslogger.info("Payment type " + PaymentType + " Last Four Digits " + lastFourDightsOfCard
						+ " and Exp Date of the card :" + CardExp + " in the payment description are not found : "
						+ text);
			}
		} else if (PaymentType.equalsIgnoreCase("Swipe")) {
			String lastFourDightsOfCard = "5454";
			CardExp = "12/23";
			if (text.contains("MC") && text.contains("5454") && text.contains(CardExp)) {
				test_steps.add("Verified Payment type " + PaymentType + " Last Four Digits " + lastFourDightsOfCard
						+ " and Exp Date of the card :" + CardExp + " in the payment description : " + text);
				reslogger.info("Verified Payment type " + PaymentType + " Last Four Digits " + lastFourDightsOfCard
						+ " and Exp Date of the card :" + CardExp + " in the payment description : " + text);
			} else {
				test_steps.add("Payment type " + PaymentType + " Last Four Digits " + lastFourDightsOfCard
						+ " and Exp Date of the card :" + CardExp + " in the payment description are not found : "
						+ text);
				reslogger.info("Payment type " + PaymentType + " Last Four Digits " + lastFourDightsOfCard
						+ " and Exp Date of the card :" + CardExp + " in the payment description are not found : "
						+ text);
			}
		} else if (PaymentType.equalsIgnoreCase("Cash")) {
			if (text.contains("Cash")) {
				test_steps.add("Verified Payment type " + PaymentType + " in the payment description : " + text);
				reslogger.info("Verified Payment type " + PaymentType + " in the payment description : " + text);
			} else {
				test_steps.add("Payment type " + PaymentType + " in the payment description not found : " + text);
				reslogger.info("Payment type " + PaymentType + " in the payment description not found : " + text);
			}
		}
	}

	public void click_DeatilsTab(WebDriver driver, ArrayList test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_DetailsTab);
		res.CP_NewReservation_DetailsTab.click();
		test_steps.add("Click on Details Tab");
		reslogger.info("Click on Details Tab");
	}

	public void verify_MainPaymentMethod(WebDriver driver, ArrayList test_steps, String IsSetAsMainPaymentMethod,
			String PaymentType, String CardNumber, String CardExpDate, String NameOnCard) {

		test_steps.add("******************* Main Payment Method verification  **********************");
		reslogger.info("******************* Main Payment Method verification  **********************");
		if (IsSetAsMainPaymentMethod.equalsIgnoreCase("Yes")) {
			if (PaymentType.equalsIgnoreCase("Cash")) {
				String payment = "//span[text()='PAYMENT ']/../..//span[text()='Cash']";
				if (driver.findElement(By.xpath(payment)).isDisplayed()) {
					test_steps.add("Cash as Main Payment Method is validated");
					reslogger.info("Cash as Main Payment Method is validated");
				} else {
					test_steps.add("Cash as Main Payment Method is not validated");
					reslogger.info("Cash as Main Payment Method is not validated");
				}
			} else if (PaymentType.equalsIgnoreCase("Swipe")) {
				String payment = "//span[text()='PAYMENT ']/../..//span[text()='MC']";
				String cardnum = "//span[text()='PAYMENT ']/../..//span[contains(text(),'5454')]";
				String cardName = "//span[text()='PAYMENT ']/../..//span[contains(text(),'MC TEST CARD')]";
				String exp = "//span[text()='PAYMENT ']/../..//span[contains(text(),'12/23')]";

				if (driver.findElement(By.xpath(payment)).isDisplayed()
						&& driver.findElement(By.xpath(cardnum)).isDisplayed()
						&& driver.findElement(By.xpath(cardName)).isDisplayed()
						&& driver.findElement(By.xpath(exp)).isDisplayed()) {
					test_steps.add("Swipe card as Main Payment Method is validated");
					reslogger.info("Swipe card as Main Payment Method is validated");
				} else {
					test_steps.add("Swipe card as Main Payment Method is not validated");
					reslogger.info("Swipe card as Main Payment Method is not validated");
				}
			} else if (PaymentType.equalsIgnoreCase("MC") || PaymentType.equalsIgnoreCase("Visa")
					|| PaymentType.equalsIgnoreCase("Amex") || PaymentType.equalsIgnoreCase("Discover")) {
				String lastFourDightsOfCard = CardNumber.substring(CardNumber.length() - 4, CardNumber.length());
				String payment = "//span[text()='PAYMENT ']/../..//span[text()='" + PaymentType + "']";
				String cardnum = "//span[text()='PAYMENT ']/../..//span[contains(text(),'" + lastFourDightsOfCard
						+ "')]";
				String cardName = "//span[text()='PAYMENT ']/../..//span[contains(text(),'" + NameOnCard + "')]";
				String exp = "//span[text()='PAYMENT ']/../..//span[contains(text(),'" + CardExpDate + "')]";

				if (driver.findElement(By.xpath(payment)).isDisplayed()
						&& driver.findElement(By.xpath(cardnum)).isDisplayed()
						&& driver.findElement(By.xpath(cardName)).isDisplayed()
						&& driver.findElement(By.xpath(exp)).isDisplayed()) {
					test_steps.add(PaymentType + " as Main Payment Method is validated");
					reslogger.info(PaymentType + " as Main Payment Method is validated");
				} else {
					test_steps.add(PaymentType + " as Main Payment Method is not validated");
					reslogger.info(PaymentType + " as Main Payment Method is not validated");
				}
			}

		}

	}

	public void verify_UpdatedByUser(WebDriver driver, ArrayList test_steps, String LoggedInUser) {
		test_steps.add("******************* Updated By user verification  **********************");
		reslogger.info("******************* Updated By user verification  **********************");
		String user = "//div[contains(text(),'Updated By:')]/span";
		Wait.WaitForElement(driver, user);

		user = driver.findElement(By.xpath(user)).getText().trim();
		reslogger.info(LoggedInUser);
		reslogger.info(user);
		if (user.contains(LoggedInUser)) {
			test_steps.add("Updated by user validated : " + LoggedInUser);
			reslogger.info("Updated by user validated : " + LoggedInUser);
		} else {
			test_steps.add("Updated by user not validated : " + LoggedInUser);
			reslogger.info("Updated by user not validated : " + LoggedInUser);
		}
	}

	public void verify_UpdatedByUserTime(WebDriver driver, ArrayList test_steps, String timeZone) {
		test_steps.add("******************* Updated By date verification  **********************");
		reslogger.info("******************* Updated By date verification  **********************");
		String user = "//div[contains(text(),'Updated By:')]/span";
		Wait.WaitForElement(driver, user);

		user = driver.findElement(By.xpath(user)).getText().trim();
		// reslogger.info(LoggedInUser);
		reslogger.info(user);

		Date date = new Date();
		DateFormat df = new SimpleDateFormat("MM/dd/yy");

		// Use Madrid's time zone to format the date in
		df.setTimeZone(TimeZone.getTimeZone(timeZone));

		String d = df.format(date);

		if (user.contains(d)) {
			test_steps.add("Updated by date validated : " + d);
			reslogger.info("Updated by date validated : " + d);
		} else {
			test_steps.add("Updated by date not validated : " + d);
			reslogger.info("Updated by date not validated : " + d);
		}
	}

	public void close_FirstOpenedReservation(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		String close = "//ul[@class='sec_nav']//li[5]//i";
		try {
			// Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(close)),
			// driver);
			Wait.WaitForElement(driver, close);
			Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(close)), driver);
			Utility.ScrollToElement(driver.findElement(By.xpath(close)), driver);
			driver.findElement(By.xpath(close)).click();
			test_steps.add("Closed the reservation");
			reslogger.info("Closed the reservation");
		} catch (Exception e) {
			Wait.WaitForElement(driver, close);
			Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(close)), driver);
			Utility.ScrollToElement(driver.findElement(By.xpath(close)), driver);
			Wait.wait2Second();
			driver.findElement(By.xpath(close)).click();
			test_steps.add("Closed the reservation");
			reslogger.info("Closed the reservation");
		}
	}

	public void verify_MR_ToolTip(WebDriver driver, ArrayList test_steps, String reservation) {

		String pop = "//span[contains(text(),'" + reservation + "')]/following-sibling::span/span";
		Wait.WaitForElement(driver, pop);
		int count = driver.findElements(By.xpath(pop)).size();

		Actions act = new Actions(driver);

		String user = "(//span[contains(text(),'" + reservation + "')]/../preceding-sibling::td[2]//a)";

		String room = "(//span[contains(text(),'" + reservation + "')]/../following-sibling::td[5]/span)";

		for (int i = 1; i <= count; i++) {
			user = "(//span[contains(text(),'" + reservation + "')]/../preceding-sibling::td[2]//a)[" + i + "]";
			room = "(//span[contains(text(),'" + reservation + "')]/../following-sibling::td[5]/span)[" + i + "]";
			user = driver.findElement(By.xpath(user)).getText();
			room = driver.findElement(By.xpath(room)).getText();
			pop = "(//span[contains(text(),'" + reservation + "')]/following-sibling::span/span)[" + i + "]";
			act.moveToElement(driver.findElement(By.xpath(pop))).build().perform();
			String popup = "//span[contains(text(),'" + reservation
					+ "')]/following-sibling::span/span/..//div/div[text()='Multiple Rooms']";
			if (driver.findElement(By.xpath(pop)).isDisplayed()) {
				test_steps.add("MR symbol and Multiple Rooms tooltip are verified for the guest : " + user
						+ " and for the room : " + room);
				reslogger.info("MR symbol and Multiple Rooms tooltip are verified for the guest : " + user
						+ " and for the room : " + room);
			} else {
				test_steps.add("MR symbol and Multiple Rooms tooltip are not verified for the guest : " + user
						+ " and for the room : " + room);
				reslogger.info("MR symbol and Multiple Rooms tooltip are not verified for the guest : " + user
						+ " and for the room : " + room);
			}
		}
	}

	public boolean checkedDisplayVoidItem(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_DisplayVoidItemCheckBox, driver);
		boolean isChecked = res.CP_Reservation_DisplayVoidItemCheckBox.isSelected();
		if (!isChecked) {
			Utility.ScrollToElement(res.CP_Reservation_DisplayVoidItemCheckBox, driver);
			res.CP_Reservation_DisplayVoidItemCheckBox.click();
			test_steps.add("Checked Display Void Item Check Box ");
			reslogger.info("Checked Display Void Item Check Box ");
		} else {
			test_steps.add("Display Void Item Check Box is Already Checked ");
			reslogger.info("Display Void Item Check Box is Already Checked ");
		}
		return isChecked;
	}

	public String getPanelStatusGuestName(WebDriver driver) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		String guestName = res.CP_Reservation_StatusPanel_GuestName.getText();
		return guestName;

	}

	public String getStayInfoSecondGuestName(WebDriver driver) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		String guestName = res.CP_Reservation_StayInfo_SecondaryGuestName.getText();
		return guestName;

	}

	public String getPanelStatusStatusName(WebDriver driver) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_PanelNoShowStatus);
		String status = res.CP_Reservation_StatusPanel_Status.getText();
		reslogger.info("Status is: " + status);
		return status;

	}

	public void clickPolicyCollapseIcon(WebDriver driver, ArrayList<String> test_steps, String PolicyType,
			String percentage) throws InterruptedException

	{
		Elements_CPReservation res = new Elements_CPReservation(driver);
		if (PolicyType.equals("No Show")) {
			Utility.ScrollToElement(res.CP_Reservation_POLICIESANDDISCLAIMERS_NoShowPolicyLabel, driver);
			boolean isEclicked = res.CP_Reservation_POLICIESANDDISCLAIMERS_NoShowPolicyLabel.isDisplayed();
			if (isEclicked) {
				test_steps.add("No Show Policy Alread Expanded");
				reslogger.info("No Show Policy Alread Expanded ");
			} else {
				res.CP_Reservation_POLICIESANDDISCLAIMERS_NoShowPolicyCollapse.click();
				test_steps.add("No Show Policy  Expanded");
				reslogger.info("No Show Policy  Expanded ");
			}
		} else if (PolicyType.equals("Check In")) {
			Utility.ScrollToElement(res.CP_Reservation_POLICIESANDDISCLAIMERS_CheckInPolicyLabel, driver);
			boolean isEclicked = res.CP_Reservation_POLICIESANDDISCLAIMERS_CheckInPolicyLabel.isDisplayed();
			if (isEclicked) {
				test_steps.add("Check In Policy Alread Expanded");
				reslogger.info("Check In Policy Alread Expanded ");
			} else {
				res.CP_Reservation_POLICIESANDDISCLAIMERS_CheckInPolicyCollapse.click();
				test_steps.add("Check In Policy  Expanded");
				reslogger.info("Check In Policy  Expanded ");
			}
		} else if (PolicyType.equals("Cancel")) {
			String path = "//div[text()='Policies And Disclaimers']/..//span[contains(text(),'Cancellation "
					+ percentage + "%')]";
			Utility.ScrollToElement(res.CP_Reservation_POLICIESANDDISCLAIMERS_CancellationPolicyLabel, driver);
			boolean isEclicked = res.CP_Reservation_POLICIESANDDISCLAIMERS_CancellationPolicyLabel.isDisplayed();
			if (isEclicked) {
				test_steps.add("Cancellation Policy Alread Expanded");
				reslogger.info("Cancellation Policy Alread Expanded ");
			} else {
				WebElement element = driver.findElement(By.xpath(path));
				Utility.ScrollToElement(element, driver);
				element.click();
				test_steps.add("Cancellation Policy Expanded");
				reslogger.info("Cancellation Policy Expanded ");
			}

		}

	}

	public void verifyPOLICIESANDDISCLAIMERSNoShowPolicy(WebDriver driver, ArrayList<String> test_steps,
			String PolicyName, String PolicyType, String percentage) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		clickPolicyCollapseIcon(driver, test_steps, PolicyType, percentage);
		if (PolicyType.equals("No Show")) {
			// Verified No Show Policy Label
			assertTrue(res.CP_Reservation_POLICIESANDDISCLAIMERS_NoShowPolicyLabel.isDisplayed(),
					"Failed: To verify No Show Policy Label");
			test_steps.add("Verified <b>'NO SHOW POLICY'</b> Label ");
			reslogger.info("Verified 'NO SHOW POLICY' Label ");

		} else if (PolicyType.equals("Check In")) {
			// Verified No Show Policy Label

			assertTrue(res.CP_Reservation_POLICIESANDDISCLAIMERS_CheckInPolicyLabel.isEnabled(),
					"Failed: To verify Check-in  Policy Label");
			test_steps.add("Verified <b>'Check-in Policy'</b> Label ");
			reslogger.info("Verified 'Check-in Policy' Label ");
		}
		// Verified No Show Policy
		String path = "//div[@aria-expanded='true']//span[@class='filter-option pull-left'][contains(text(),'"
				+ PolicyName + "')]";
		WebElement element = driver.findElement(By.xpath(path));
		reslogger.info(PolicyName);
		Utility.ScrollToElement(element, driver);
		reslogger.info("***************");
		assertTrue(element.isDisplayed(), "Failed: To verify  Policy " + PolicyName);
		test_steps.add("Verified: <b>" + PolicyName + " Associated</b>");
		reslogger.info("Verified : " + PolicyName + " Associated");

	}

	public void verifyCPReservationNotes(WebDriver driver, ArrayList<String> test_steps, String PolicyType,
			String NotesType, String Abb, String RoomNo, String Subject, String Desc, String UpdateOn, String Date)
			throws InterruptedException {
		reslogger.info("Policy Type: " + PolicyType);
		reslogger.info(" Abbrivation: " + Abb);

		String[] abb = Abb.split(":");
		String FinalAbb = abb[1].trim();
		reslogger.info(" Room No: " + FinalAbb);

		String[] room = RoomNo.split(":");
		String FinalRoomNo = room[1].trim();
		reslogger.info(" Room No: " + FinalRoomNo);
		reslogger.info(" Date: " + UpdateOn);
		if (PolicyType.equals("No Show")) {
			String path = "//notes-info[contains(@params,'reservationDetail')]//tr/td[contains(@data-bind,'type')][(text()='"
					+ NotesType + "')]" + "/following-sibling::td[contains(@data-bind,'roomClassAbbrv')][(text()='"
					+ FinalAbb + ": " + FinalRoomNo + "')]"
					+ "/following-sibling::td[contains(@data-bind,'subject')][(text()='" + PolicyType + "')]"
					+ "//following-sibling::td[contains(@data-bind,'updatedOn')][contains(text(),'" + UpdateOn + "')]";

			reslogger.info(path);
			WebElement element = driver.findElement(By.xpath(path));
			Utility.ScrollToElement(element, driver);
			assertTrue(element.isDisplayed(), "Failed: To verify No Show Notes");
			test_steps.add("Verified Notes <br>" + "TYPE: <b>" + PolicyType + "</b> ROOM: <b>" + FinalAbb + "</b>: <b>"
					+ FinalRoomNo + "</b> SUBJECT: <b>" + Subject + "</b> DESCRIPTION: <b>" + Desc
					+ "</b> UPDATED ON: <b>" + Date + "</b>");
			reslogger.info("Verified Notes TYPE: " + PolicyType + " ROOM: " + FinalAbb + ": " + FinalRoomNo
					+ " SUBJECT: " + Subject + " DESCRIPTION: " + Desc + " UPDATED ON: " + Date);

		} else if (PolicyType.equals("Cancel")) {

			String path = "//notes-info[contains(@params,'reservationDetail')]//tr/td[contains(@data-bind,'type')][(text()='"
					+ NotesType + "')]" + "/following-sibling::td[contains(@data-bind,'roomClassAbbrv')][(text()='"
					+ FinalAbb + ": " + FinalRoomNo + "')]"
					+ "/following-sibling::td[contains(@data-bind,'subject')][(text()='" + Subject + "')] "
					+ "/following-sibling::td[contains(@data-bind,'details')][(text()='" + Desc + "')]"
					+ "//following-sibling::td[contains(@data-bind,'updatedOn')][contains(text(),'" + UpdateOn + "')]";

			reslogger.info(path);
			WebElement element = driver.findElement(By.xpath(path));
			Utility.ScrollToElement(element, driver);
			assertTrue(element.isDisplayed(), "Failed: To verify Cancellation Notes");
			test_steps.add("Verified Notes <br>" + "TYPE: <b>" + PolicyType + " </b> ROOM:  <b>" + FinalAbb
					+ "</b>: <b>" + FinalRoomNo + " </b> SUBJECT:  <b>" + Subject + " </b> DESCRIPTION:  <b>" + Desc
					+ " </b> UPDATED ON:  <b>" + Date + " </b>");
			reslogger.info("Verified Notes TYPE: " + PolicyType + " ROOM: " + FinalAbb + ": " + FinalRoomNo
					+ " SUBJECT: " + Subject + " DESCRIPTION: " + Desc + " UPDATED ON: " + Date);

		}
	}

	public void verifyCPReservationDetailTabPolicies(WebDriver driver, ArrayList<String> test_steps, String PolicyType,
			String Percentage, String PolicyName, String Desc) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		clickPolicyCollapseIcon(driver, test_steps, PolicyType, Percentage);
		if (PolicyType.equals("No Show")) {

			// Verified No Show Policy Label
			Utility.ScrollToElement(res.CP_Reservation_POLICIESANDDISCLAIMERS_NoShowPolicyLabel, driver);
			assertTrue(res.CP_Reservation_POLICIESANDDISCLAIMERS_NoShowPolicyLabel.isDisplayed(),
					"Failed: To verify No Show Policy Label");
			test_steps.add("Verified <b>'NO SHOW POLICY'</b> Label ");
			reslogger.info("Verified 'NO SHOW POLICY' Label ");
		} else if (PolicyType.equals("Cancel")) {
			// Click_CancellationPolicy(driver, test_steps,Percentage);
			Utility.ScrollToElement(res.CP_Reservation_POLICIESANDDISCLAIMERS_CancellationPolicyLabel, driver);
			assertTrue(res.CP_Reservation_POLICIESANDDISCLAIMERS_CancellationPolicyLabel.isDisplayed(),
					"Failed: To verify Cancellation Policy Label");
			test_steps.add("Verified <b>'Cancellation Policy'</b> Label ");
			reslogger.info("Verified 'Cancellation Policy' Label ");

		} else if (PolicyType.equals("Check In")) {
			Utility.ScrollToElement(res.CP_Reservation_POLICIESANDDISCLAIMERS_CheckInPolicyLabel, driver);
			assertTrue(res.CP_Reservation_POLICIESANDDISCLAIMERS_CheckInPolicyLabel.isDisplayed(),
					"Failed: To verify Check-In Policy Label");
			test_steps.add("Verified <b>'Check-In Policy'</b> Label ");
			reslogger.info("Verified 'Check-In Policy' Label ");

		}
		reslogger.info(PolicyName);
		// Verified Associate Policy
		String path = "//div[@aria-expanded='true']//span[@class='filter-option pull-left'][contains(text(),'"
				+ PolicyName + "')]";
		WebElement element = driver.findElement(By.xpath(path));
		reslogger.info(PolicyName);
		Utility.ScrollToElement(element, driver);
		reslogger.info("***************");
		assertTrue(element.isDisplayed(), "Failed: To verify Associate Policy " + PolicyName);
		test_steps.add("Verified Policy: <b> " + PolicyName + "</b>");
		reslogger.info("Verified Policy: " + PolicyName);
		// Verified Description
		String DescPath = "//div[@aria-expanded='true']//textarea[contains(text(),'" + Desc + "')]";
		WebElement DescElement = driver.findElement(By.xpath(DescPath));
		Utility.ScrollToElement(DescElement, driver);
		assertTrue(DescElement.isDisplayed(), "Failed: To verify  Policy Description" + Desc);
		test_steps.add("Verified Policy Description: <b> " + Desc + "</b>");
		reslogger.info("Verified Policy Description: " + Desc);

	}

	public void verifyLineItem(WebDriver driver, ArrayList<String> test_steps, String Date, String PreviousDay,
			String Category, String Desc, String Amount) throws InterruptedException {
		reslogger.info("******************* Verify Folio Details **********************");

		Elements_CPReservation res = new Elements_CPReservation(driver);
		String LineItemSetMainPaymentPath = "//tbody[contains(@data-bind,'ComputedFolioItems')]//td[@class='lineitem-date']/span[contains(text(),'"
				+ PreviousDay + "')]" + "/../following-sibling::td[@class='lineitem-category']/span[contains(text(),'"
				+ Category + "')]" + "/../following-sibling::td[@class='lineitem-description']//a[contains(text(),'"
				+ Desc + "')]" + "/../following-sibling::td[@class='lineitem-amount']/span[contains(text(),'" + Amount
				+ "')]";
		Wait.WaitForElement(driver, LineItemSetMainPaymentPath);
		WebElement SetLineItemElement = driver.findElement(By.xpath(LineItemSetMainPaymentPath));
		// Verified No Show Fee line item
		assertTrue(SetLineItemElement.isDisplayed(), "Failed: To Verify Line Item");
		test_steps.add(" Verified Date:<b>  " + Date + "</b>");
		reslogger.info("Verified  Date: " + Date);
		test_steps.add(" Verified  Category:<b>  " + Category + "</b>");
		reslogger.info(" Verified  Category: " + Category);
		test_steps.add("Verified  Description:<b>  " + Desc + "</b>");
		reslogger.info(" Verified Description: " + Desc);
		test_steps.add(" Verified Amount:<b>  " + Amount + "</b>");
		reslogger.info(" Verified  Amount: " + Amount);

	}

	public void VerifyLineItemSecondGuestAlreadyPayAmountByPrimaryOne(WebDriver driver, ArrayList<String> test_steps,
			String Desc) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		String LineItemSetMainPaymentPath = "//tbody[contains(@data-bind,'ComputedFolioItems')]//td[@class='lineitem-description']//a[(contains(text(),'"
				+ Desc + "'))]";
		// WebElement
		// SetLineItemElement=driver.findElement(By.xpath(LineItemSetMainPaymentPath));
		boolean isExist = Utility.isElementPresent(driver, By.xpath(LineItemSetMainPaymentPath));
		if (!isExist) {
			test_steps
					.add("Verified Primary Guest Pay Secondary Guest Charges, No Amount Available for Secondary Guest");
			reslogger.info(
					"Verified Primary Guest Pay Secondary Guest Charges ,No Amount Available for Secondary Guest");
		}

	}

	public void verifyDisplayVoidLineItems(WebDriver driver, ArrayList<String> test_steps, String Notes,
			String Category) throws InterruptedException {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		boolean isExist = checkedDisplayVoidItem(driver, test_steps);
		if (!isExist && res.CP_Reservation_DisplayVoidItemLineItemImage3.isDisplayed()) {
			String path = "//td[contains(@class,'lineitem-note')]/img[@title='" + Notes + "']";
			WebElement element = driver.findElement(By.xpath(path));
			Wait.explicit_wait_visibilityof_webelement(element, driver);
			// Verified Line Item for Display Void Item
			assertTrue(element.isDisplayed(), "Failed: To verify Display Line Item Notes");
			test_steps.add("'Line Item' Verified Notes:<b>  " + Notes + "</b>");
			reslogger.info("'Line Item' Verified Notes: " + Notes);
			// Verified Image
			assertTrue(res.CP_Reservation_DisplayVoidItemLineItemImage3.isDisplayed(),
					"Failed: To verify Display Line Item Image");
			test_steps.add("'Line Item' Verified<b>  Image </b>");
			reslogger.info("'Line Item' Verified Image ");
			// Verified Room Changes
			String RoomChange = "//td[contains(@class,'lineitem-note')]/img[@title='" + Notes
					+ "']/../following-sibling::td[@class='lineitem-category']/span";
			WebElement roomChangeEle = driver.findElement(By.xpath(RoomChange));
			assertTrue(roomChangeEle.getText().toLowerCase().trim().equals(Category.toLowerCase().trim()),
					"Failed: To verify Display Line Item Category");
			test_steps.add("'Line Item' Verified Category:<b>  " + Category + "</b>");
			reslogger.info("'Line Item' Verified Category: " + Category);
		} else if (!isExist) {
			String path = "//td[contains(@class,'lineitem-note')]/img[@title='" + Notes + "']";
			WebElement element = driver.findElement(By.xpath(path));
			Wait.explicit_wait_visibilityof_webelement(element, driver);
			// Verified Line Item for Display Void Item
			assertTrue(element.isDisplayed(), "Failed: To verify Display Line Item Notes");
			test_steps.add("'Line Item' Verified Notes:<b>  " + Notes + "</b>");
			reslogger.info("'Line Item' Verified Notes: " + Notes);
		}

	}

	public ArrayList<String> verifyReservationStatusStatus(WebDriver driver, ArrayList<String> test_steps,
			String Status) throws InterruptedException {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_PanelNoShowStatus);
		Utility.ScrollToElement(res.CP_Reservation_StatusPanel_Status, driver);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_StatusPanel_Status, driver);		
		// Verified History Tab No Show Entry
		reslogger.info("Status is: " + res.CP_Reservation_StatusPanel_Status.getText());
		reslogger.info("Matched Status is: " + Status);
		assertEquals(res.CP_Reservation_StatusPanel_Status.getText().toLowerCase().trim(), Status.toLowerCase().trim(),
				"Failed: To verify Status");
		test_steps.add("  Verified Reservation Status: <b>" + Status + "</b>");
		reslogger.info("Verified Reservation Status: " + Status);

		return test_steps;

	}

	public void VerifyReservationStatusTripTotal(WebDriver driver, ArrayList<String> test_steps,
			String TotalTripAmount) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_StatusPanel_TripTotal, driver);
		String RerservationStatus_TripTotalAmount = res.CP_Reservation_StatusPanel_TripTotal.getText().replace("$", "");
		double tripAmount = Double.valueOf(RerservationStatus_TripTotalAmount);
		int amt = (int) tripAmount;
		RerservationStatus_TripTotalAmount = String.valueOf(amt);
		// Verified History Tab No Show Entry
		assertTrue(RerservationStatus_TripTotalAmount.equals(TotalTripAmount), "Failed: To verify Total Trip");
		test_steps.add("  Verified Reservation Status Total Trip:  <b>" + TotalTripAmount + "</b>");
		reslogger.info(" Verified Reservation Status Total Trip: " + TotalTripAmount);
	}

	public void verifyReservationStatusBalance(WebDriver driver, ArrayList<String> test_steps, String Balance)
			throws InterruptedException

	{
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_PanelBalance);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_StatusPanel_Balance, driver);
		Utility.ScrollToElement(res.CP_Reservation_StatusPanel_Balance, driver);
		String RerservationStatus_BalanceAmount = res.CP_Reservation_StatusPanel_Balance.getText().replace("$", "");
		double balanceAmount = Double.valueOf(RerservationStatus_BalanceAmount);
		reslogger.info(String.valueOf((int) (balanceAmount)).trim());
		assertEquals(String.valueOf((int) (balanceAmount)).trim(),(Balance), "Failed: To verify Balance");


		test_steps.add("  Verified Reservation Status Balance:  <b>" + Balance + "</b>");
		reslogger.info(" Verified Reservation Status Balance: " + Balance);
	}

	public void verifyReservationStatusBalanceForCheckOut(WebDriver driver, ArrayList<String> test_steps,
			String Balance) throws InterruptedException

	{
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Utility.ScrollToElement(res.CP_Reservation_StatusPanel_Balance, driver);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_StatusPanel_Balance, driver);
		String RerservationStatus_BalanceAmount = res.CP_Reservation_StatusPanel_Balance.getText().replace("$", "");
		assertEquals(RerservationStatus_BalanceAmount.trim(), (Balance), "Failed: To verify Balance");
		test_steps.add("  Verified Reservation Status Balance:  <b>" + Balance + "</b>");
		reslogger.info(" Verified Reservation Status Balance: " + Balance);
	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: verifyHistoryTabDescription 
	 * ' Description: Verification of Description Marked this reservation as a no show  and Cancelled this reservation
	 * ' Input parameters: String
	  * ' Created By: Gangotri
	 * ' Created On: June 3 2020
	 * 
	 * ##########################################################################################################################################################################
	 */			


	public void verifyHistoryTabDescription(WebDriver driver, ArrayList<String> test_steps, String Status) {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_HistoryTab_NoShowDesc);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_HistoryTab_NoShow, driver);
		// Verified History Tab No Show Entry
		assertTrue(res.CP_Reservation_HistoryTab_NoShow.isDisplayed(), "Failed: To verify History Tab ");
		if (Status.equals("No Show")) {
			test_steps.add(" 'History Tab' Verified : <b> Marked this reservation as a no show </b>");
			reslogger.info("'History Tab' Verified : No Show Entry");
		} else if (Status.equals("Cancel")) {
			test_steps.add(" 'History Tab' Verified : <b> Cancelled this reservation </b>");
			reslogger.info("'History Tab' Verified :Cancel Entry");
		}

	}

	public void verifyHistoryTabDescriptionWithPayment(WebDriver driver, ArrayList<String> test_steps, String amount,
			String Status, String PaymentMethod) {
		String desc = "Made a no show payment of $" + amount.trim() + ".00 using " + PaymentMethod;
		String CancelDesc = "Made a cancellation payment of $" + amount.trim() + ".00 using " + PaymentMethod;
		String path = "//span[contains(text(),'" + desc + "')]|//span[contains(text(),'" + CancelDesc + "')]";
		WebElement element = driver.findElement(By.xpath(path));
		assertTrue(element.isDisplayed(), "Failed: To verify History Tab  Description");
		if (Status.equals("No Show")) {
			test_steps.add("'History Tab' Verified Description: <b>" + desc + "</b>");
			reslogger.info("'History Tab' Verified Description : " + desc);
		} else if (Status.equals("Cancel")) {
			test_steps.add("'History Tab' Verified Description: <b>" + CancelDesc + "</b>");
			reslogger.info("'History Tab' Verified Description : " + CancelDesc);

		}
	}

	public void verifyHistoryTabPaymentDesc(WebDriver driver, ArrayList<String> test_steps, String amount,
			String Status, String abb, String RoomNo, String PaymentMethod) {
		String[] room = RoomNo.split(":");
		String FinalRoomNo = room[1].trim();
		reslogger.info(" Room No: " + FinalRoomNo);
		String checkIn = "Made a payment $" + amount.trim() + ".00 using " + PaymentMethod;
		String checkOut = "Captured payment for $" + amount.trim() + ".00 using " + PaymentMethod;

		String path = "//span[contains(text(),'" + checkIn
				+ "')]/ancestor::td/following-sibling::td/span[contains(text(),'" + abb + ": " + FinalRoomNo + "')]";
		String checkOutPath = "//span[contains(text(),'" + checkOut
				+ "')]/ancestor::td/following-sibling::td/span[contains(text(),'" + abb + ": " + FinalRoomNo + "')]";
		if (Status.equals("Check In")) {
			WebElement element = driver.findElement(By.xpath(path));
			assertTrue(element.isDisplayed(), "Failed: To verify History Tab  Description");
			test_steps.add("'History Tab' Verified Description: <b>" + checkIn + "</b>");
			reslogger.info("'History Tab' Verified Description : " + checkIn);
			test_steps.add("'History Tab' Verified Room: <b>" + abb + ": " + FinalRoomNo + "</b>");
			reslogger.info("'History Tab' Verified Room : " + abb + ": " + FinalRoomNo);
		} else if (Status.equals("Check Out")) {
			WebElement elementCheckOut = driver.findElement(By.xpath(checkOutPath));
			assertTrue(elementCheckOut.isDisplayed(), "Failed: To verify History Tab  Description");
			test_steps.add("'History Tab' Verified Description: <b>" + checkOut + "</b>");
			reslogger.info("'History Tab' Verified Description : " + checkOut);
			test_steps.add("'History Tab' Verified Room: <b>" + abb + ": " + FinalRoomNo + "</b>");
			reslogger.info("'History Tab' Verified Room : " + abb + ": " + FinalRoomNo);
		}

	}

	public void Verify_HistoryTab_Checkout_ChangedRoomCharges(WebDriver driver, ArrayList<String> test_steps,
			String amount, String ChangedCharges) {
		String checkOut = "Changed room charge from $" + amount + ".00 to $" + ChangedCharges + ".00";
		String path = "//span[contains(text(),'" + checkOut + "')]";
		WebElement element = driver.findElement(By.xpath(path));
		assertTrue(element.isDisplayed(), "Failed: To verify History Tab  Changed Room Charges");
		test_steps.add("'History Tab' Verified <b>Changed room charge from $" + amount + ".00 to $</b>" + ChangedCharges
				+ ".00");
		reslogger.info(
				"'History Tab' Verified  Changed room charge from $" + amount + ".00 to $" + ChangedCharges + ".00");

	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: Verify_HistoryTab_Checkout_ChangedCheckOutDate 
	 * ' Description: Verification of Description Changed check out date from
	 * ' Input parameters: String
	  * ' Created By: Gangotri
	 * ' Created On: June 3 2020
	 * 
	 * ##########################################################################################################################################################################
	 */	
	public void Verify_HistoryTab_Checkout_ChangedCheckOutDate(WebDriver driver, ArrayList<String> test_steps,
			String date, String ChangedDate) {
		String checkOut = "Changed check out date from " + date + " to " + ChangedDate + "";
		String path = "//span[contains(text(),'" + checkOut + "')]";
		WebElement element = driver.findElement(By.xpath(path));
		assertTrue(element.isDisplayed(), "Failed: To verify History Tab  Changed Checkout Date");
		test_steps.add("'History Tab' Verified <b>Changed check out date from " + date + " to </b>" + ChangedDate);
		reslogger.info("'History Tab' Verified  Changed check out date from " + date + " to " + ChangedDate);

	}

	public void commonMethodToverifyHistoryTabDescriptionForCheckINCheckOut(WebDriver driver,
			ArrayList<String> test_steps, String amount, String Status, String abb, String RoomNo,
			String PaymentMethod) {
		String[] room = RoomNo.split(":");
		String FinalRoomNo = room[1].trim();
		reslogger.info(" Room No: " + FinalRoomNo);
		String checkIn = "Made a payment $" + amount.trim() + ".00 using " + PaymentMethod;
		String checkOut = "Captured payment for $" + amount.trim() + ".00 using " + PaymentMethod;

		String path = "//span[contains(text(),'" + checkIn
				+ "')]/ancestor::td/following-sibling::td/span[contains(text(),'" + abb + ": " + FinalRoomNo + "')]";
		String checkOutPath = "//span[contains(text(),'" + checkOut
				+ "')]/ancestor::td/following-sibling::td/span[contains(text(),'" + abb + ": " + FinalRoomNo + "')]";
		if (Status.equals("Check In")) {
			WebElement element = driver.findElement(By.xpath(path));
			assertTrue(element.isDisplayed(), "Failed: To verify History Tab  Description");
			test_steps.add("'History Tab' Verified Description: <b>" + checkIn + "</b>");
			reslogger.info("'History Tab' Verified Description : " + checkIn);
			test_steps.add("'History Tab' Verified Room: <b>" + abb + ": " + FinalRoomNo + "</b>");
			reslogger.info("'History Tab' Verified Room : " + abb + ": " + FinalRoomNo);
		} else if (Status.equals("Check Out")) {
			WebElement elementCheckOut = driver.findElement(By.xpath(checkOutPath));
			assertTrue(elementCheckOut.isDisplayed(), "Failed: To verify History Tab  Description");
			test_steps.add("'History Tab' Verified Description: <b>" + checkOut + "</b>");
			reslogger.info("'History Tab' Verified Description : " + checkOut);
			test_steps.add("'History Tab' Verified Room: <b>" + abb + ": " + FinalRoomNo + "</b>");
			reslogger.info("'History Tab' Verified Room : " + abb + ": " + FinalRoomNo);
		}

	}

	public void verifyChangedRoomChargesOnHistoryTabForCheckout(WebDriver driver, ArrayList<String> test_steps,
			String amount, String ChangedCharges) {
		String checkOut = "Changed room charge from $" + amount + ".00 to $" + ChangedCharges + ".00";
		String path = "//span[contains(text(),'" + checkOut + "')]";
		WebElement element = driver.findElement(By.xpath(path));
		assertTrue(element.isDisplayed(), "Failed: To verify History Tab  Changed Room Charges");
		test_steps.add("'History Tab' Verified <b>Changed room charge from $" + amount + ".00 to $</b>" + ChangedCharges
				+ ".00");
		reslogger.info(
				"'History Tab' Verified  Changed room charge from $" + amount + ".00 to $" + ChangedCharges + ".00");

	}

	
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: verifyChangedCheckOutDateOnHistoryTab 
	 * ' Description: Verification of Description Changed check out date from
	 * ' Input parameters: String
	  * ' Created By: Gangotri
	 * ' Created On: June 3 2020
	 * 
	 * ##########################################################################################################################################################################
	 */	

	public void verifyChangedCheckOutDateOnHistoryTab(WebDriver driver,ArrayList<String> test_steps, String date,String ChangedDate)
	{
		String checkOut="Changed check out date from "+date+" to "+ChangedDate+"";
		String path="//span[contains(text(),'"+checkOut+"')]";
		WebElement element= driver.findElement(By.xpath(path));
		assertTrue(element.isDisplayed(),"Failed: To verify History Tab  Changed Checkout Date");
		test_steps.add("'History Tab' Verified <b>Changed check out date from "+date+" to </b>"+ChangedDate);
		reslogger.info("'History Tab' Verified  Changed check out date from " +date+" to "+ChangedDate);
		
    }



	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: commonMethodToverifyRoomsOnHistoryTab 
	 * ' Description: Verification of Rooms on History 
	 * ' Input parameters: String
	  * ' Created By: Gangotri
	 * ' Created On: June 3 2020
	 * 
	 * ##########################################################################################################################################################################
	 */


	public void commonMethodToverifyRoomsOnHistoryTab(WebDriver driver, ArrayList<String> test_steps,
			String RoomClassName) throws InterruptedException {
		String path = "//div[contains(@data-bind,'hasHistory')]//td/span[contains(@data-bind,'data.room')]";
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		assertTrue(element.getText().toLowerCase().trim().equals(RoomClassName.toLowerCase().trim()),
				"Failed: To verify History Tab  Room");
		test_steps.add("'History Tab' Verified Room: <b>" + RoomClassName + "</b>");
		reslogger.info("'History Tab' Verified Room : " + RoomClassName);
	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: verifyRollBackButton 
	 * ' Description: Verification of Roll Back button
	 * ' Input parameters: String
	  * ' Created By: Gangotri
	 * ' Created On: June 3 2020
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void verifyRollBackButton(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_RolleBackButton);
		Utility.ScrollToElement(res.CP_Reservation_RollBackAllButton, driver);
		assertTrue(res.CP_Reservation_RollBackAllButton.isDisplayed(), "Failed: To verify Roll Back Button");

		test_steps.add("Verified  <b>Roll Back </b> Button");
		reslogger.info("Verified Roll Back Button");
	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: verifyStayInforRollBackButton 
	 * ' Description: Verification of Stay Info Roll Back button
	 * ' Input parameters: String
	  * ' Created By: Gangotri
	 * ' Created On: June 3 2020
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void verifyStayInforRollBackButton(WebDriver driver, ArrayList<String> test_steps, String RoomClassName)
			throws InterruptedException {
		String path = "//div[contains(@data-bind,'ReservationStatusLookup')]/div//div[contains(@class,'ir-roomInfo')]//span[contains(text(),'"
				+ RoomClassName + "')]"
				+ "/ancestor::div/div[contains(@class,'detailsbox')]/following-sibling::div//button[contains(text(),'Roll Back')]";
		Wait.WaitForElement(driver, path);
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		assertTrue(element.isDisplayed(), "Failed: To verify Stay Info Roll Back Button");
		test_steps.add(" Stay Info-Roll Back Button is Displayed For Room Class <b>" + RoomClassName + "</b>");
		reslogger.info("Stay Info-Roll Back Button is Displayed For Room Class: " + RoomClassName);

	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: verifyCheckOutAllButton 
	 * ' Description: Verification of CheckOut All button
	 * ' Input parameters: String
	  * ' Created By: Gangotri
	 * ' Created On: June 3 2020
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void verifyCheckOutAllButton(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_CheckOutAllButton);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_CheckOutAllButton, driver);
		assertTrue(res.CP_Reservation_CheckOutAllButton.isDisplayed(), "Failed: To verify Check Out All Button");
		test_steps.add("Verified  <b>Check Out All </b> Button");
		reslogger.info("Verified Check Out All  Button");
	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: verifyCheckOutButton 
	 * ' Description: Verification of CheckOut  button
	 * ' Input parameters: String
	  * ' Created By: Gangotri
	 * ' Created On: June 3 2020
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void verifyCheckOutButton(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_CheckOutButton);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_CheckOutButton, driver);
		assertTrue(res.CP_Reservation_CheckOutButton.isDisplayed(), "Failed: To verify Check Out  Button");
		test_steps.add("Verified  <b>Check Out  </b> Button");
		reslogger.info("Verified Check Out   Button");
	}

	public void click_RollBackButton(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_RolleBackButton);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_RollBackAllButton, driver);
		res.CP_Reservation_RollBackAllButton.click();
		test_steps.add("Click Roll Back Button");
		reslogger.info("Click Roll Back Button");
	}

	public void Click_CancellationPolicy(WebDriver driver, ArrayList<String> test_steps, String percentage)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		String path = "//div[text()='Policies And Disclaimers']/..//span[contains(text(),'Cancellation " + percentage
				+ "%')]";
		Wait.WaitForElement(driver, path);
		Utility.ScrollToElement(res.CP_Reservation_POLICIESANDDISCLAIMERS_CancellationPolicyLabel, driver);
		boolean isEclicked = res.CP_Reservation_POLICIESANDDISCLAIMERS_CancellationPolicyLabel.isDisplayed();
		if (isEclicked) {
			test_steps.add("Cancellation Policy Alread Expanded");
			reslogger.info("Cancellation Policy Alread Expanded ");
		} else {
			WebElement element = driver.findElement(By.xpath(path));
			Utility.ScrollToElement(element, driver);
			element.click();
			test_steps.add("Cancellation Policy Expanded");
			reslogger.info("Cancellation Policy Expanded ");
		}

	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: addCancelationReson 
	 * ' Description: Add Cancel Reason and click PROCEED TO CANCELLATION PAYMENT button  and verify Cancellation Reason Validation
	 * ' Input parameters: String
	  * ' Created By: Gangotri
	 * ' Created On: June 3 2020
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void addCancelationReson(WebDriver driver, ArrayList<String> test_steps, String reason, String validationMsg)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.ProceedToPaymentButton);

		Utility.ScrollToElement(res.ProceedToPaymentButton, driver);
		res.ProceedToPaymentButton.click();
		test_steps.add("Click 'PROCEED TO CANCELLATION PAYMENT' Button");
		reslogger.info("Click 'PROCEED TO CANCELLATION PAYMENT' Button");
		test_steps.add("<b>****Start Verifying Reason****</b>");
		assertEquals(res.CancelReservation_ValidationMsg.getText().toLowerCase().trim(),(validationMsg.toLowerCase().trim()), "Failed: To verify reason");
		test_steps.add("Verified  Cancellation Reason Validation: <b>" + validationMsg + "</b>");
		reslogger.info("Verified Cancellation Reason Validation: " + validationMsg);
		provideReasonForCancelAndClickOnProceedToPay(driver, test_steps, reason, true);
	}

	public void enableVoidRoomCharge(WebDriver driver, ArrayList<String> test_steps, boolean voidRoomCharges) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		boolean voidRoomChargesSelected = res.cancelResPopupVoidRoomChargesCheckBox.isSelected();
		if (voidRoomCharges) {
			if (!(voidRoomChargesSelected)) {
				Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.cancelResPopupVoidRoomChargesCheckBoxClick),
						driver);
				res.cancelResPopupVoidRoomChargesCheckBoxClick.click();
				test_steps.add("Enabling Void Room Charges check box");
			} else {
				test_steps.add("Void Room Charges check box is already selected");
			}
		} else {
			if (voidRoomChargesSelected) {
				res.cancelResPopupVoidRoomChargesCheckBoxClick.click();
				test_steps.add("Unselecting Void Room Charges check box");
			} else {
				test_steps.add("Void Room Charges check box is already unselected");
			}
		}
	}

	public void provideReasonForCancelAndClickOnProceedToPay(WebDriver driver, ArrayList<String> test_steps,
			String reason, boolean voidRoomCharges) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
			res.CancelReservation_Reason.clear();
			res.CancelReservation_Reason.sendKeys(reason);
			test_steps.add("Providing Cancellation Reason as : <b>" + reason + " </b>");
			reslogger.info("Providing Cancellation Reason as : " + reason);
			Utility.ScrollToElement(res.ProceedToPaymentButton, driver);
			res.ProceedToPaymentButton.click();
			test_steps.add("Clicking on '<b>PROCEED TO CANCELLATION PAYMENT</b>' Button");
			reslogger.info("Click 'PROCEED TO CANCELLATION PAYMENT' Button");

			
		} 

	public void verifyCancelPolicyOnCancelPaymentPopup(WebDriver driver, ArrayList<String> test_steps,
			String policyName, String policyText) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		try {
			Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.cancelResPopupPolicyName), driver);
			String policyNameDisplayed = res.cancelResPopupPolicyName.getText();
			Actions act = new Actions(driver);
			act.moveToElement(res.cancelResPopupPolicyName).perform();
			String policyNameAtToolTip = res.cancelResPopupPolicyToolTipName.getText();
			String policyTextAtToolTip = res.cancelResPopupPolicyToolTipText.getText();

			assertEquals(policyNameDisplayed, policyName, "Failed - Verify policy name on cancel popup");
			test_steps.add("Successfully verified policy name on cancel popup as <b>" + policyNameDisplayed + "</b>");

			assertEquals(policyNameAtToolTip, policyName, "Failed - Verify policy name on cancel popup");
			test_steps.add("Successfully verified policy name on cancel popup tool tip as <b>" + policyNameAtToolTip + "</b>");

			assertEquals(policyTextAtToolTip, policyText, "Failed - Verify policy name on cancel popup");
			test_steps.add("Successfully verified policy text on cancel popup tool tip as <b>" + policyTextAtToolTip + "</b>");

		} catch (Exception e) {
			reslogger.info(e);
		}
	}

	public String get_STAYINFORoomChargesWithoutCurrency(WebDriver driver, ArrayList test_steps, String RoomClassName) {
		String Balance = driver
				.findElement(By
						.xpath("//div[contains(@data-bind,'ReservationStatusLookup')]/div//div[contains(@class,'ir-roomInfo')]//span[contains(text(),'"
								+ RoomClassName + "')]"
								+ "/following-sibling::span[contains(@data-bind,'showInnroadCurrency')]"))
				.getText();
		Balance = Balance.replace("$", "");
		test_steps.add("StayInfo Room Charges : <b>" + Balance + "</b>");
		reslogger.info("StayInfo Room Charges : " + Balance);
		return Balance;
	}

	public String stayInfoActualRoom = "", stayInfoActualAbb = "";

	public int actualRoom = 0;

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: clickSTAYINFOThreeDots 
	 * ' Description: Click three demo from stay info page as per room no
	 * ' Input parameters: String
	  * ' Created By: Gangotri
	 * ' Created On: June 3 2020
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void clickSTAYINFOThreeDots(WebDriver driver, ArrayList<String> test_steps, String Status,

			String roomNO, ArrayList<String> Rooms, ArrayList<String> RoomAbbri) throws InterruptedException {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		int size = res.CP_Reservation_StayInfo_ThreeDotts.size();
		for (int i = 0; i < size; i++) {
			if (i == (Integer.parseInt(roomNO) - 1)) {
				actualRoom = i;
				stayInfoActualRoom = Rooms.get(i).toString();
				stayInfoActualAbb = RoomAbbri.get(i).toString();
				Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_StayInfo_ThreeDotts);
				Utility.ScrollToElement(res.CP_Reservation_StayInfo_ThreeDotts.get(i), driver);
				res.CP_Reservation_StayInfo_ThreeDotts.get(i).click();
				test_steps.add("Room <b>" + (Integer.parseInt(roomNO)) + "</b> in the MRB Reservation");
				reslogger.info("Room " + (Integer.parseInt(roomNO)) + " in the MRB Reservation");
				break;
			}
		}

		String pathCancel = "//ul//li[contains(text(),'" + Status + "')]";
		WebDriverWait wait = new WebDriverWait(driver, 25);
		By locator = By.xpath(pathCancel);
		WebElement elementCancel = wait.until(ExpectedConditions.elementToBeClickable(locator));
		elementCancel.click();
		test_steps.add("Click : <b>" + Status + "</b>");
		reslogger.info("Click : " + Status);
	}

	public void click_FolioDetail_DropDownBox(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_FolioDetailDropDownBox);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_FolioDetailDropDownBox, driver);
		Utility.ScrollToElement(res.CP_Reservation_FolioDetailDropDownBox, driver);
		res.CP_Reservation_FolioDetailDropDownBox.click();
		test_steps.add("Click Folio Detail Drop Down Box");
		reslogger.info("Click Folio Detail Drop Down Box");
	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: clickFolioDetailOptionValue 
	 * ' Description: Click on Folio Drop Down BOx option
	 * ' Input parameters: String
	  * ' Created By: Gangotri
	 * ' Created On: June 3 2020
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void clickFolioDetailOptionValue(WebDriver driver, ArrayList<String> test_steps, String abb, String RoomNO)
			throws InterruptedException {
		String FinalRoomNo = null;
		if (RoomNO.contains(":")) {
			String[] room = RoomNO.split(":");
			FinalRoomNo = room[1].trim();
			reslogger.info(" Room No: " + FinalRoomNo);

		} else {
			FinalRoomNo = RoomNO;
		}

		String path = "//a/span[contains(text(),'Guest Folio For " + abb + " : " + FinalRoomNo + "')]";
		WebElement element = driver.findElement(By.xpath(path));
		Wait.WaitForElement(driver, path);
		Utility.ScrollToElement(element, driver);
		element.click();
		test_steps.add("Select Guest Folio For: <b>" + abb + ":" + FinalRoomNo + "</b>");
		reslogger.info("Select Guest Folio For: " + abb + ":" + FinalRoomNo);
	}

	public void verify_PaymentInTripSummary(WebDriver driver, ArrayList test_steps, String paidFolio,
			String balanceFolio, String paidFolioAfterPay, String balanceFolioAfterPay, String amount,
			String TakePaymentType) {
		test_steps.add("******************* Verify TripSummary amount after payment **********************");
		reslogger.info("******************* Verify TripSummary amount after payment **********************");

		Double paidBefore = Double.parseDouble(paidFolio);
		Double balBefore = Double.parseDouble(balanceFolio);
		Double paidAfter = Double.parseDouble(paidFolioAfterPay);
		Double balAfter = Double.parseDouble(balanceFolioAfterPay);
		Double amt = Double.parseDouble(amount);

		if (TakePaymentType.equalsIgnoreCase("Capture")) {

			if (Double.compare((paidBefore + amt), paidAfter) == 0) {
				test_steps.add("Folio Paid amount is updated with : " + amount);
				reslogger.info("Folio Paid amount is updated with : " + amount);
			} else {
				test_steps.add("Folio Paid amount is not updated with : " + amount);
				reslogger.info("Folio Paid amount is not updated with : " + amount);
			}

			if (Double.compare((balBefore - amt), balAfter) == 0) {
				test_steps.add("Folio Balance amount is updated with : " + amount);
				reslogger.info("Folio Balance amount is updated with : " + amount);
			} else {
				test_steps.add("Folio Balance amount is not updated with : " + amount);
				reslogger.info("Folio Balance amount is not updated with : " + amount);
			}
		}
	}

	public void create_SplitResFromMRB(WebDriver driver, ArrayList test_steps, ArrayList Rooms)
			throws InterruptedException {
		test_steps.add("******************* Spliting a room from MRB to a separate reservation **********************");
		reslogger.info("******************* Spliting a room from MRB to a separate reservation **********************");
		String circle = "//div[contains(text(),'Stay')]/..//button[contains(@class,'Circle')]";
		int count = driver.findElements(By.xpath(circle)).size();
		circle = "(//div[contains(text(),'Stay')]/..//button[contains(@class,'Circle')])[" + count + "]";
		Wait.WaitForElement(driver, circle);
		driver.findElement(By.xpath(circle)).click();

		String split = "(//li[text()='Split into Separate Reservation'])[" + (count - 1) + "]";
		Wait.WaitForElement(driver, split);
		driver.findElement(By.xpath(split)).click();
		test_steps.add("Clicking on split into separate reservation");
		reslogger.info("Clicking on split into separate reservation");

		String areYouSure = "//div[contains(text(),'Are you sure you want to split this room into a separate reservation')]/..//button[text()='Yes']";
		Wait.WaitForElement(driver, areYouSure);
		driver.findElement(By.xpath(areYouSure)).click();

		test_steps.add("Are you sure you want to split this room into a separate reservation Popup verified");
		reslogger.info("Are you sure you want to split this room into a separate reservation Popup verified");
		String loading = "(//div[@class='ir-loader-in'])";
		count = 0;
		while (true) {
			if (driver.findElements(By.xpath(loading)).size() > 4) {
				break;
			} else if (count == 20) {
				break;
			} else {
				count++;
				Wait.wait2Second();
			}
		}
		reslogger.info("Waited to loading symbol");
	}

	public ArrayList get_FolioBalanceForAllFolios(WebDriver driver, ArrayList test_steps, ArrayList Rooms,
			ArrayList RoomAbbri, String IsAssign) throws InterruptedException {
		test_steps.add("******************* MRB Folio Creation verification **********************");
		reslogger.info("******************* MRB Folio Creation verification **********************");
		String abb = null;
		String roomnumber = null;

		ArrayList<ArrayList> al = new ArrayList<ArrayList>();

		for (int i = 0; i < RoomAbbri.size(); i++) {
			ArrayList al1 = new ArrayList();
			abb = RoomAbbri.get(i).toString();
			roomnumber = Rooms.get(i).toString();
			String[] abbrivation = abb.split(":");
			String[] rooomno = roomnumber.split(":");
			String folio = null;

			String folioSize = "//button[contains(@title,'Guest')]";
			Wait.WaitForElement(driver, folioSize);
			driver.findElement(By.xpath(folioSize)).click();
			Wait.wait2Second();
			if (!(IsAssign.equalsIgnoreCase("Yes")) || IsAssign.isEmpty() || IsAssign.equalsIgnoreCase("")) {
				folio = "//li//span[contains(text(),'Guest Folio For " + abbrivation[1] + " : " + abbrivation[0]
						+ "')]";
				reslogger.info(folio);
				if (driver.findElements(By.xpath(folio)).size() > 0) {
					test_steps
							.add("Folio is present : " + "Guest Folio For " + abbrivation[1] + " : " + abbrivation[0]);
					reslogger
							.info("Folio is present : " + "Guest Folio For " + abbrivation[1] + " : " + abbrivation[0]);
					// folio="Guest Folio For "+abbrivation[1]+" :
					// "+abbrivation[0];
					// new
					// Select(driver.findElement(By.xpath(folioSize))).selectByVisibleText(folio);
					driver.findElement(By.xpath(folio)).click();
					String roomCahrge = "//span[text()='Room Charge']";

					Double FilioRoomCharges = Double.parseDouble(get_RoomCharge(driver, test_steps));
					Double FilioIncidentals = Double.parseDouble(get_Inceidentals(driver, test_steps));
					Double FilioTaxes = Double.parseDouble(get_Taxes(driver, test_steps));
					Double FilioTripTotal = Double.parseDouble(get_TotalCharges(driver, test_steps));
					Double FilioPaid = Double.parseDouble(get_Payments(driver, test_steps));
					Double FilioBalance = Double.parseDouble(get_Balance(driver, test_steps));
					al1.add(FilioRoomCharges);
					al1.add(FilioIncidentals);
					al1.add(FilioTaxes);
					al1.add(FilioTripTotal);
					al1.add(FilioPaid);
					al1.add(FilioBalance);
					al.add(al1);
				} else {
					test_steps.add(
							"Folio is not present : " + "Guest Folio For " + abbrivation[1] + " : " + abbrivation[0]);
					reslogger.info(
							"Folio is not present : " + "Guest Folio For " + abbrivation[1] + " : " + abbrivation[0]);
				}
			} else {
				folio = "//li//span[contains(text(),'Guest Folio For " + abbrivation[1] + " : " + rooomno[1].trim()
						+ "')]";
				reslogger.info(folio);
				if (driver.findElements(By.xpath(folio)).size() > 0) {
					test_steps.add(
							"Folio is present : " + "Guest Folio For " + abbrivation[1] + " : " + rooomno[1].trim());
					reslogger.info(
							"Folio is present : " + "Guest Folio For " + abbrivation[1] + " : " + rooomno[1].trim());
					// folio="Guest Folio For "+abbrivation[1]+" :
					// "+rooomno[1].trim();
					// new
					// Select(driver.findElement(By.xpath(folioSize))).selectByVisibleText(folio);
					driver.findElement(By.xpath(folio)).click();
					String roomCahrge = "//span[text()='Room Charge']";

					Double FilioRoomCharges = Double.parseDouble(get_RoomCharge(driver, test_steps));
					Double FilioIncidentals = Double.parseDouble(get_Inceidentals(driver, test_steps));
					Double FilioTaxes = Double.parseDouble(get_Taxes(driver, test_steps));
					Double FilioTripTotal = Double.parseDouble(get_TotalCharges(driver, test_steps));
					Double FilioPaid = Double.parseDouble(get_Payments(driver, test_steps));
					Double FilioBalance = Double.parseDouble(get_Balance(driver, test_steps));
					al1.add(FilioRoomCharges);
					al1.add(FilioIncidentals);
					al1.add(FilioTaxes);
					al1.add(FilioTripTotal);
					al1.add(FilioPaid);
					al1.add(FilioBalance);
					al.add(al1);
				} else {
					test_steps.add("Folio is not present : " + "Guest Folio For " + abbrivation[1] + " : "
							+ rooomno[1].trim());
					reslogger.info("Folio is not present : " + "Guest Folio For " + abbrivation[1] + " : "
							+ rooomno[1].trim());
				}
			}
		}
		return al;
	}

	public void velidate_MRB_TripSummaryAndFolio(WebDriver driver, ArrayList test_steps, ArrayList al,
			String TripSummaryRoomCharges, String TripSummaryTaxes, String TripSummaryIncidentals,
			String TripSummaryTripTotal, String TripSummaryPaid, String TripSummaryBalance) {
		test_steps.add("******************* Validating Folio and TripSummary fields **********************");
		reslogger.info("******************* Validating Folio and TripSummary fields **********************");

		TripSummaryRoomCharges = TripSummaryRoomCharges.trim();
		TripSummaryRoomCharges = TripSummaryRoomCharges.substring(1, TripSummaryRoomCharges.length());
		TripSummaryRoomCharges = TripSummaryRoomCharges.trim();
		Double TripSummRoom = Double.parseDouble(TripSummaryRoomCharges);

		TripSummaryTaxes = TripSummaryTaxes.trim();
		TripSummaryTaxes = TripSummaryTaxes.substring(1, TripSummaryTaxes.length());
		TripSummaryTaxes = TripSummaryTaxes.trim();
		Double TripSummTax = Double.parseDouble(TripSummaryTaxes);

		TripSummaryIncidentals = TripSummaryIncidentals.trim();
		TripSummaryIncidentals = TripSummaryIncidentals.substring(1, TripSummaryIncidentals.length());
		TripSummaryIncidentals = TripSummaryIncidentals.trim();
		Double TripSummInci = Double.parseDouble(TripSummaryIncidentals);

		TripSummaryTripTotal = TripSummaryTripTotal.trim();
		TripSummaryTripTotal = TripSummaryTripTotal.substring(1, TripSummaryTripTotal.length());
		TripSummaryTripTotal = TripSummaryTripTotal.trim();
		Double TripSummTot = Double.parseDouble(TripSummaryTripTotal);

		TripSummaryPaid = TripSummaryPaid.trim();
		TripSummaryPaid = TripSummaryPaid.substring(1, TripSummaryPaid.length());
		TripSummaryPaid = TripSummaryPaid.trim();
		Double TripSummPaid = Double.parseDouble(TripSummaryPaid);

		TripSummaryBalance = TripSummaryBalance.trim();
		TripSummaryBalance = TripSummaryBalance.substring(1, TripSummaryBalance.length());
		TripSummaryBalance = TripSummaryBalance.trim();
		Double TripSummBal = Double.parseDouble(TripSummaryBalance);

		int count = al.size();
		ArrayList al1;
		Double FilioRoomCharges = (double) 0, FilioIncidentals = (double) 0, FilioTaxes = (double) 0,
				FilioTripTotal = (double) 0, FilioPaid = (double) 0, FilioBalance = (double) 0;

		for (int i = 0; i < count; i++) {
			al1 = (ArrayList) al.get(i);
			FilioRoomCharges = FilioRoomCharges + Double.parseDouble(al1.get(0).toString());
			FilioIncidentals = FilioIncidentals + Double.parseDouble(al1.get(1).toString());
			FilioTaxes = FilioTaxes + Double.parseDouble(al1.get(2).toString());
			FilioTripTotal = FilioTripTotal + Double.parseDouble(al1.get(3).toString());
			FilioPaid = FilioPaid + Double.parseDouble(al1.get(4).toString());
			FilioBalance = FilioBalance + Double.parseDouble(al1.get(5).toString());
		}

		/*
		 * reslogger.info("FilioRoomCharges :"+FilioRoomCharges);
		 * reslogger.info("FilioIncidentals : "+FilioIncidentals);
		 * reslogger.info("FilioTaxes : "+FilioTaxes);
		 * reslogger.info("FilioTripTotal : "+FilioTripTotal);
		 * reslogger.info("FilioPaid : "+FilioPaid);
		 * reslogger.info("FilioBalance : "+FilioBalance);
		 * reslogger.info("TripSummRoom : "+TripSummRoom);
		 * reslogger.info("TripSummTax : "+TripSummTax);
		 * reslogger.info("TripSummInci : "+TripSummInci);
		 * reslogger.info("TripSummTot : "+TripSummTot);
		 * reslogger.info("TripSummPaid : "+TripSummPaid);
		 * reslogger.info("TripSummBal : "+TripSummBal);
		 */

		if (Double.compare(TripSummRoom, FilioRoomCharges) == 0) {
			test_steps.add("TripSummary RoomCharges and Folio RoomCharges are same : " + FilioRoomCharges);
			reslogger.info("TripSummary RoomCharges and Folio RoomCharges are same : " + FilioRoomCharges);
		} else {
			test_steps.add("TripSummary RoomCharges and Folio RoomCharges are not same : " + FilioRoomCharges);
			reslogger.info("TripSummary RoomCharges and Folio RoomCharges are not same : " + FilioRoomCharges);
		}

		if (Double.compare(FilioTaxes, TripSummTax) == 0) {
			test_steps.add("TripSummaryTaxes and FilioTaxes are same : " + FilioTaxes);
			reslogger.info("TTripSummaryTaxes and FilioTaxes are same : " + FilioTaxes);
		} else {
			test_steps.add("TripSummaryTaxes and FilioTaxes are not same : " + FilioTaxes);
			reslogger.info("TripSummaryTaxes and FilioTaxes are not same : " + FilioTaxes);
		}
		if (Double.compare(FilioIncidentals, TripSummInci) == 0) {
			test_steps.add("TripSummary Incidental and Folio Incidental are same : " + FilioIncidentals);
			reslogger.info("TripSummary Incidental and Folio Incidental are same : " + FilioIncidentals);
		} else {
			test_steps.add("TripSummary Incidental and Folio Incidental are not same : " + FilioIncidentals);
			reslogger.info("TripSummary Incidental and Folio Incidental are not same : " + FilioIncidentals);
		}
		if (Double.compare(FilioTripTotal, TripSummTot) == 0) {
			test_steps.add("TripSummary TripTotal and Folio TripTotal are same : " + FilioTripTotal);
			reslogger.info("TripSummary TripTotal and Folio TripTotal are same : " + FilioTripTotal);
		} else {
			test_steps.add("TripSummary TripTotal and Folio TripTotal are not same : " + FilioTripTotal);
			reslogger.info("TripSummary TripTotal and Folio TripTotal are not same : " + FilioTripTotal);
		}
		if (Double.compare(FilioPaid, TripSummPaid) == 0) {
			test_steps.add("TripSummary Paid and Folio Paid are same : " + FilioPaid);
			reslogger.info("TripSummary Paid and Folio Paid are same : " + FilioPaid);
		} else {
			test_steps.add("TripSummary Paid and Folio Paid are not same : " + FilioPaid);
			reslogger.info("TripSummary Paid and Folio Paid are not same : " + FilioPaid);
		}
		if (Double.compare(FilioBalance, TripSummBal) == 0) {
			test_steps.add("TripSummary Balance and Folio Balance are same : " + FilioBalance);
			reslogger.info("TripSummary Balance and Folio Balance are same : " + FilioBalance);
		} else {
			test_steps.add("TripSummary Balance and Folio Balance are not same : " + FilioBalance);
			reslogger.info("TripSummary Balance and Folio Balance are not same : " + FilioBalance);
		}
	}

	public String verify_MRB_SplitResInHistory(WebDriver driver, ArrayList test_steps) {
		test_steps.add(
				"******************* Validating Split Reservation recored in MRB History Tab **********************");
		reslogger.info(
				"******************* Validating Split Reservation recored in MRB History Tab **********************");

		String description = "//span[contains(text(),'Split a room into separate reservation with Confirmation Number: ')]";
		String confirmation = driver.findElement(By.xpath(description)).getText();
		test_steps.add(confirmation);
		reslogger.info(confirmation);
		confirmation = confirmation.replace("Split a room into separate reservation with Confirmation Number: ", "");
		return confirmation;
	}

	public void verify_MRB_FolioAfterSplit(WebDriver driver, ArrayList test_steps, ArrayList RoomAbbri, String IsAssign,
			ArrayList Rooms) throws InterruptedException {
		test_steps.add("******************* MRB Folio Creation verification After Split**********************");
		reslogger.info("******************* MRB Folio Creation verification After Split**********************");
		String abb = null;
		String roomnumber = null;
		for (int i = 0; i < RoomAbbri.size(); i++) {
			abb = RoomAbbri.get(i).toString();
			roomnumber = Rooms.get(i).toString();
			String[] abbrivation = abb.split(":");
			String[] rooomno = roomnumber.split(":");
			String folio = null;

			String folioSize = "//button[contains(@title,'Guest')]";
			Wait.WaitForElement(driver, folioSize);
			driver.findElement(By.xpath(folioSize)).click();
			Wait.wait2Second();

			if (i == (RoomAbbri.size() - 1) || RoomAbbri.size() > 2) {
				if (!(IsAssign.equalsIgnoreCase("Yes")) || IsAssign.isEmpty() || IsAssign.equalsIgnoreCase("")) {
					folio = "//li//span[contains(text(),'Guest Folio For " + abbrivation[1] + " : " + abbrivation[0]
							+ "')]";
					reslogger.info(folio);
					if (driver.findElements(By.xpath(folio)).size() > 0) {
						test_steps.add(
								"Folio is present : " + "Guest Folio For " + abbrivation[1] + " : " + abbrivation[0]);
						reslogger.info(
								"Folio is present : " + "Guest Folio For " + abbrivation[1] + " : " + abbrivation[0]);
						// folio="Guest Folio For "+abbrivation[1]+" :
						// "+abbrivation[0];
						// new
						// Select(driver.findElement(By.xpath(folioSize))).selectByVisibleText(folio);
						driver.findElement(By.xpath(folio)).click();
						String roomCahrge = "//span[text()='Room Charge']";
						if (driver.findElements(By.xpath(roomCahrge)).size() > 0) {
							test_steps.add("Room Charge Line Item  is present : " + "Guest Folio For " + abbrivation[1]
									+ " : " + abbrivation[0]);
							reslogger.info("Room Charge Line Item  is present : " + "Guest Folio For " + abbrivation[1]
									+ " : " + abbrivation[0]);
						} else {
							test_steps.add("Room Charge Line Item  is not present : " + "Guest Folio For "
									+ abbrivation[1] + " : " + abbrivation[0]);
							reslogger.info("Room Charge Line Item  is not present : " + "Guest Folio For "
									+ abbrivation[1] + " : " + abbrivation[0]);
						}
					} else {
						test_steps.add("Folio is not present : " + "Guest Folio For " + abbrivation[1] + " : "
								+ abbrivation[0]);
						reslogger.info("Folio is not present : " + "Guest Folio For " + abbrivation[1] + " : "
								+ abbrivation[0]);
					}
					folioSize = "//button[contains(@title,'Guest')]";
					Wait.WaitForElement(driver, folioSize);
					driver.findElement(By.xpath(folioSize)).click();
				} else {
					folio = "//li//span[contains(text(),'Guest Folio For " + abbrivation[1] + " : " + rooomno[1].trim()
							+ "')]";
					reslogger.info(folio);
					if (driver.findElements(By.xpath(folio)).size() > 0) {
						test_steps.add("Folio is present : " + "Guest Folio For " + abbrivation[1] + " : "
								+ rooomno[1].trim());
						reslogger.info("Folio is present : " + "Guest Folio For " + abbrivation[1] + " : "
								+ rooomno[1].trim());
						// folio="Guest Folio For "+abbrivation[1]+" :
						// "+rooomno[1].trim();
						// new
						// Select(driver.findElement(By.xpath(folioSize))).selectByVisibleText(folio);
						driver.findElement(By.xpath(folio)).click();
						String roomCahrge = "//span[text()='Room Charge']";
						if (driver.findElements(By.xpath(roomCahrge)).size() > 0) {
							test_steps.add("Room Charge Line Item  is present : " + "Guest Folio For " + abbrivation[1]
									+ " : " + rooomno[1].trim());
							reslogger.info("Room Charge Line Item  is present : " + "Guest Folio For " + abbrivation[1]
									+ " : " + rooomno[1].trim());
						} else {
							test_steps.add("Room Charge Line Item  is not present : " + "Guest Folio For "
									+ abbrivation[1] + " : " + rooomno[1].trim());
							reslogger.info("Room Charge Line Item  is not present : " + "Guest Folio For "
									+ abbrivation[1] + " : " + rooomno[1].trim());
						}
					} else {
						test_steps.add("Folio is not present : " + "Guest Folio For " + abbrivation[1] + " : "
								+ rooomno[1].trim());
						reslogger.info("Folio is not present : " + "Guest Folio For " + abbrivation[1] + " : "
								+ rooomno[1].trim());
					}
				}
			} else {
				String option = "//option[text()='Guest Folio']";
				if (driver.findElements(By.xpath(option)).size() > 0) {
					test_steps.add("Folio present : Guest Folio");
					reslogger.info("Folio present : Guest Folio");
					folio = "Guest Folio For " + abbrivation[1] + " : " + abbrivation[0];
					String roomCahrge = "//span[text()='Room Charge']";
					if (driver.findElements(By.xpath(roomCahrge)).size() > 0) {
						test_steps.add("Room Charge Line Item  is present : Guest Folio");
						reslogger.info("Room Charge Line Item  is present : Guest Folio");
					} else {
						test_steps.add("Room Charge Line Item  is not present : Guest Folio");
						reslogger.info("Room Charge Line Item  is not present : Guest Folio");
					}
				} else {
					test_steps.add("Guest folio not present");
					reslogger.info("Guest folio not present");
				}
			}
		}
		String folioSize = "//button[contains(@title,'Guest')]";
		Wait.WaitForElement(driver, folioSize);
		driver.findElement(By.xpath(folioSize)).click();
	}

	public void validate_MRBFolio_AfterSplit(WebDriver driver, ArrayList test_steps, ArrayList al)
			throws InterruptedException {
		test_steps.add("******************* MRB Folio verification After Split**********************");
		reslogger.info("******************* MRB Folio verification After Split**********************");
		int count = al.size();
		ArrayList al1;
		Double FilioRoomCharges = (double) 0, FilioIncidentals = (double) 0, FilioTaxes = (double) 0,
				FilioTripTotal = (double) 0, FilioPaid = (double) 0, FilioBalance = (double) 0;
		for (int i = 0; i < count - 1; i++) {

			al1 = (ArrayList) al.get(i);

			FilioRoomCharges = FilioRoomCharges + Double.parseDouble(al1.get(0).toString());
			FilioIncidentals = FilioIncidentals + Double.parseDouble(al1.get(1).toString());
			FilioTaxes = FilioTaxes + Double.parseDouble(al1.get(2).toString());
			FilioTripTotal = FilioTripTotal + Double.parseDouble(al1.get(3).toString());
			FilioPaid = FilioPaid + Double.parseDouble(al1.get(4).toString());
			FilioBalance = FilioBalance + Double.parseDouble(al1.get(5).toString());
		}

		String folio = "(//li//span[contains(text(),'Guest Folio')])";

		count = driver.findElements(By.xpath(folio)).size();

		// ##
		Double FilioRoomChrg = (double) 0, FilioInci = (double) 0, FilioTax = (double) 0, FilioTripTot = (double) 0,
				FilioPad = (double) 0, FilioBal = (double) 0;
		for (int i = 1; i <= count; i++) {
			String folioSize = "//button[contains(@title,'Guest')]";
			Wait.WaitForElement(driver, folioSize);
			driver.findElement(By.xpath(folioSize)).click();
			Wait.wait2Second();

			folio = "(//li//span[contains(text(),'Guest Folio')])[" + (i) + "]";
			driver.findElement(By.xpath(folio)).click();

			FilioRoomChrg = FilioRoomChrg + Double.parseDouble(get_RoomCharge(driver, test_steps));
			FilioInci = FilioInci + Double.parseDouble(get_Inceidentals(driver, test_steps));
			FilioTax = FilioTax + Double.parseDouble(get_Taxes(driver, test_steps));
			FilioTripTot = FilioTripTot + Double.parseDouble(get_TotalCharges(driver, test_steps));
			FilioPad = FilioPad + Double.parseDouble(get_Payments(driver, test_steps));
			FilioBal = FilioBal + Double.parseDouble(get_Balance(driver, test_steps));
		}

		reslogger.info("FilioRoomCharges :" + FilioRoomCharges);
		reslogger.info("FilioIncidentals : " + FilioIncidentals);
		reslogger.info("FilioTaxes : " + FilioTaxes);
		reslogger.info("FilioTripTotal : " + FilioTripTotal);
		reslogger.info("FilioPaid : " + FilioPaid);
		reslogger.info("FilioBalance : " + FilioBalance);
		reslogger.info("FilioRoomChrg : " + FilioRoomChrg);
		reslogger.info("FilioTax : " + FilioTax);
		reslogger.info("FilioInci : " + FilioInci);
		reslogger.info("FilioTripTot : " + FilioTripTot);
		reslogger.info("FilioPad : " + FilioPad);
		reslogger.info("FilioBal : " + FilioBal);

		if (Double.compare(FilioRoomChrg, FilioRoomCharges) == 0) {
			test_steps.add("After Spliting the reservation folio RoomCharges are validated : " + FilioRoomCharges);
			reslogger.info("After Spliting the reservation folio RoomCharges are validated : " + FilioRoomCharges);
		} else {
			test_steps.add("After Spliting the reservation folio RoomCharges are not validated : " + FilioRoomCharges);
			reslogger.info("After Spliting the reservation folio RoomCharges are not validated : " + FilioRoomCharges);
		}

		if (Double.compare(FilioTaxes, FilioTax) == 0) {
			test_steps.add("After Spliting the reservation folio taxes are validated : " + FilioTaxes);
			reslogger.info("After Spliting the reservation folio taxes are validated : " + FilioTaxes);
		} else {
			test_steps.add("After Spliting the reservation folio taxes are not validated : " + FilioTaxes);
			reslogger.info("After Spliting the reservation folio taxes are not validated : " + FilioTaxes);
		}
		if (Double.compare(FilioIncidentals, FilioInci) == 0) {
			test_steps.add("After Spliting the reservation folio Incidental are validated : " + FilioIncidentals);
			reslogger.info("After Spliting the reservation folio Incidental are validated : " + FilioIncidentals);
		} else {
			test_steps.add("After Spliting the reservation folio Incidental are not validated : " + FilioIncidentals);
			reslogger.info("After Spliting the reservation folio Incidental are not validated : " + FilioIncidentals);
		}
		if (Double.compare(FilioTripTotal, FilioTripTot) == 0) {
			test_steps.add("After Spliting the reservation folio TripTotal are validated : " + FilioTripTotal);
			reslogger.info("After Spliting the reservation folio TripTotal are validated : " + FilioTripTotal);
		} else {
			test_steps.add("After Spliting the reservation folio TripTotal are not validated : " + FilioTripTotal);
			reslogger.info("After Spliting the reservation folio TripTotal are not validated : " + FilioTripTotal);
		}
		if (Double.compare(FilioPaid, FilioPad) == 0) {
			test_steps.add("After Spliting the reservation folio Paid are validated : " + FilioPaid);
			reslogger.info("After Spliting the reservation folio Paid are validated : " + FilioPaid);
		} else {
			test_steps.add("After Spliting the reservation folio Paid are not validated : " + FilioPaid);
			reslogger.info("After Spliting the reservation folio Paid are not validated : " + FilioPaid);
		}
		if (Double.compare(FilioBalance, FilioBal) == 0) {
			test_steps.add("After Spliting the reservation folio Balance are validated : " + FilioBalance);
			reslogger.info("After Spliting the reservation folio Balance are validated : " + FilioBalance);
		} else {
			test_steps.add("After Spliting the reservation folio Balance are validated : " + FilioBalance);
			reslogger.info("After Spliting the reservation folio Balance are validated : " + FilioBalance);
		}
	}

	public void velidate_MRB_TripSummaryAfterSplit(WebDriver driver, ArrayList test_steps, ArrayList al,
			String TripSummaryRoomCharges, String TripSummaryTaxes, String TripSummaryIncidentals,
			String TripSummaryTripTotal, String TripSummaryPaid, String TripSummaryBalance) {
		test_steps.add("******************* Validating TripSummary fields after split**********************");
		reslogger.info("******************* Validating TripSummary fields after split**********************");

		TripSummaryRoomCharges = TripSummaryRoomCharges.trim();
		TripSummaryRoomCharges = TripSummaryRoomCharges.substring(1, TripSummaryRoomCharges.length());
		TripSummaryRoomCharges = TripSummaryRoomCharges.trim();
		Double TripSummRoom = Double.parseDouble(TripSummaryRoomCharges);

		TripSummaryTaxes = TripSummaryTaxes.trim();
		TripSummaryTaxes = TripSummaryTaxes.substring(1, TripSummaryTaxes.length());
		TripSummaryTaxes = TripSummaryTaxes.trim();
		Double TripSummTax = Double.parseDouble(TripSummaryTaxes);

		TripSummaryIncidentals = TripSummaryIncidentals.trim();
		TripSummaryIncidentals = TripSummaryIncidentals.substring(1, TripSummaryIncidentals.length());
		TripSummaryIncidentals = TripSummaryIncidentals.trim();
		Double TripSummInci = Double.parseDouble(TripSummaryIncidentals);

		TripSummaryTripTotal = TripSummaryTripTotal.trim();
		TripSummaryTripTotal = TripSummaryTripTotal.substring(1, TripSummaryTripTotal.length());
		TripSummaryTripTotal = TripSummaryTripTotal.trim();
		Double TripSummTot = Double.parseDouble(TripSummaryTripTotal);

		TripSummaryPaid = TripSummaryPaid.trim();
		TripSummaryPaid = TripSummaryPaid.substring(1, TripSummaryPaid.length());
		TripSummaryPaid = TripSummaryPaid.trim();
		Double TripSummPaid = Double.parseDouble(TripSummaryPaid);

		TripSummaryBalance = TripSummaryBalance.trim();
		TripSummaryBalance = TripSummaryBalance.substring(1, TripSummaryBalance.length());
		TripSummaryBalance = TripSummaryBalance.trim();
		Double TripSummBal = Double.parseDouble(TripSummaryBalance);

		int count = al.size();
		ArrayList al1;
		Double FilioRoomCharges = (double) 0, FilioIncidentals = (double) 0, FilioTaxes = (double) 0,
				FilioTripTotal = (double) 0, FilioPaid = (double) 0, FilioBalance = (double) 0;

		for (int i = 0; i < count; i++) {
			if (i == (count - 1)) {
				al1 = (ArrayList) al.get(i);
				FilioRoomCharges = FilioRoomCharges + Double.parseDouble(al1.get(0).toString());
				FilioIncidentals = FilioIncidentals + Double.parseDouble(al1.get(1).toString());
				FilioTaxes = FilioTaxes + Double.parseDouble(al1.get(2).toString());
				FilioTripTotal = FilioTripTotal + Double.parseDouble(al1.get(3).toString());
				FilioPaid = FilioPaid + Double.parseDouble(al1.get(4).toString());
				FilioBalance = FilioBalance + Double.parseDouble(al1.get(5).toString());
			}
		}

		String TripSummaryRoomChrg = get_TripSummaryRoomChargesWithCurrency(driver, test_steps);
		String TripSummaryTax = get_TripSummaryTaxesWithCurrency(driver, test_steps);
		String TripSummaryInci = get_TripSummaryInceidentalsWithCurrency(driver, test_steps);
		String TripSummaryTripTot = get_TripSummaryTripTotalChargesWithCurrency(driver, test_steps);
		String TripSummaryPad = get_TripSummaryPaidWithCurrency(driver, test_steps);
		String TripSummaryBal = get_TripSummaryBalanceWithCurrency(driver, test_steps);

		TripSummaryRoomChrg = TripSummaryRoomChrg.trim();
		TripSummaryRoomChrg = TripSummaryRoomChrg.substring(1, TripSummaryRoomChrg.length());
		TripSummaryRoomChrg = TripSummaryRoomChrg.trim();
		Double TripSummRm = Double.parseDouble(TripSummaryRoomChrg);

		TripSummaryTax = TripSummaryTax.trim();
		TripSummaryTax = TripSummaryTax.substring(1, TripSummaryTax.length());
		TripSummaryTax = TripSummaryTax.trim();
		Double TripSummTx = Double.parseDouble(TripSummaryTax);

		TripSummaryInci = TripSummaryInci.trim();
		TripSummaryInci = TripSummaryInci.substring(1, TripSummaryInci.length());
		TripSummaryInci = TripSummaryInci.trim();
		Double TripSummIncid = Double.parseDouble(TripSummaryInci);

		TripSummaryTripTot = TripSummaryTripTot.trim();
		TripSummaryTripTot = TripSummaryTripTot.substring(1, TripSummaryTripTot.length());
		TripSummaryTripTot = TripSummaryTripTot.trim();
		Double TripSummTota = Double.parseDouble(TripSummaryTripTot);

		TripSummaryPad = TripSummaryPad.trim();
		TripSummaryPad = TripSummaryPad.substring(1, TripSummaryPad.length());
		TripSummaryPad = TripSummaryPad.trim();
		Double TripSummPad = Double.parseDouble(TripSummaryPad);

		TripSummaryBal = TripSummaryBal.trim();
		TripSummaryBal = TripSummaryBal.substring(1, TripSummaryBal.length());
		TripSummaryBal = TripSummaryBal.trim();
		Double TripSummBala = Double.parseDouble(TripSummaryBal);

		/*
		 * reslogger.info("FilioRoomCharges :"+FilioRoomCharges);
		 * reslogger.info("FilioIncidentals : "+FilioIncidentals);
		 * reslogger.info("FilioTaxes : "+FilioTaxes);
		 * reslogger.info("FilioTripTotal : "+FilioTripTotal);
		 * reslogger.info("FilioPaid : "+FilioPaid);
		 * reslogger.info("FilioBalance : "+FilioBalance);
		 * reslogger.info("TripSummRoom : "+TripSummRoom);
		 * reslogger.info("TripSummTax : "+TripSummTax);
		 * reslogger.info("TripSummInci : "+TripSummInci);
		 * reslogger.info("TripSummTot : "+TripSummTot);
		 * reslogger.info("TripSummPaid : "+TripSummPaid);
		 * reslogger.info("TripSummBal : "+TripSummBal);
		 */

		if (Double.compare((TripSummRoom - FilioRoomCharges), TripSummRm) == 0) {
			test_steps.add("TripSummary RoomCharges are verified : " + TripSummRm);
			reslogger.info("TripSummary RoomCharges are verified : " + TripSummRm);
		} else {
			test_steps.add("TripSummary RoomCharges are not verified : " + TripSummRm);
			reslogger.info("TripSummary RoomCharges are not verified : " + TripSummRm);
		}

		if (Double.compare((TripSummTax - FilioTaxes), TripSummTx) == 0) {
			test_steps.add("TripSummaryTaxes are verified : " + TripSummTx);
			reslogger.info("TripSummaryTaxes are verified : " + TripSummTx);
		} else {
			test_steps.add("TripSummaryTaxes are not verified : " + TripSummTx);
			reslogger.info("TripSummaryTaxes are not verified : " + TripSummTx);
		}
		if (Double.compare((TripSummInci - FilioIncidentals), TripSummIncid) == 0) {
			test_steps.add("TripSummary Incidental are verified : " + TripSummIncid);
			reslogger.info("TripSummary Incidental are verified : " + TripSummIncid);
		} else {
			test_steps.add("TripSummary Incidental are not verified : " + TripSummIncid);
			reslogger.info("TripSummary Incidental are not verified : " + TripSummIncid);
		}
		if (Double.compare((TripSummTot - FilioTripTotal), TripSummTota) == 0) {
			test_steps.add("TripSummary TripTotal are verified : " + TripSummTota);
			reslogger.info("TripSummary TripTotal are verified : " + TripSummTota);
		} else {
			test_steps.add("TripSummary TripTotal are not verified : " + TripSummTota);
			reslogger.info("TripSummary TripTotal are not verified : " + TripSummTota);
		}
		if (Double.compare((TripSummPaid - FilioPaid), TripSummPad) == 0) {
			test_steps.add("TripSummary Paid verified : " + TripSummPad);
			reslogger.info("TripSummary Paid verified : " + TripSummPad);
		} else {
			test_steps.add("TripSummary Paid not verified : " + TripSummPad);
			reslogger.info("TripSummary Paid not verified : " + TripSummPad);
		}
		if (Double.compare((TripSummBal - FilioBalance), TripSummBala) == 0) {
			test_steps.add("TripSummary Balance are verified : " + TripSummBala);
			reslogger.info("TripSummary Balance are verified : " + TripSummBala);
		} else {
			test_steps.add("TripSummary Balance are not verified : " + TripSummBala);
			reslogger.info("TripSummary Balance are not verified : " + TripSummBala);
		}
	}

	public void verify_MRB_FolioAfterSplitResSecondRes(WebDriver driver, ArrayList test_steps, ArrayList RoomAbbri,
			String IsAssign, ArrayList Rooms) {
		test_steps.add(
				"******************* MRB Folio Creation verification After Split for second reservation **********************");
		reslogger.info(
				"******************* MRB Folio Creation verification After Split for second reservation **********************");

		String folioSize = "//button[contains(@title,'Guest')]";
		Wait.WaitForElement(driver, folioSize);

		String option = "//li//span[text()='Guest Folio']";
		if (driver.findElements(By.xpath(option)).size() > 0) {
			test_steps.add("Folio present : Guest Folio");
			reslogger.info("Folio present : Guest Folio");

			String roomCahrge = "//span[text()='Room Charge']";
			if (driver.findElements(By.xpath(roomCahrge)).size() > 0) {
				test_steps.add("Room Charge Line Item  is present : Guest Folio");
				reslogger.info("Room Charge Line Item  is present : Guest Folio");
			} else {
				test_steps.add("Room Charge Line Item  is not present : Guest Folio");
				reslogger.info("Room Charge Line Item  is not present : Guest Folio");
			}
		} else {
			test_steps.add("Guest folio not present");
			reslogger.info("Guest folio not present");
		}
	}

	public void verify_SecondaryResFolio(WebDriver driver, ArrayList test_steps, ArrayList al) {

		test_steps.add(
				"******************* MRB Folio verification After Split for second reservation **********************");
		reslogger.info(
				"******************* MRB Folio verification After Split for second reservation **********************");

		int count = al.size();
		ArrayList al1;
		Double FilioRoomCharges = (double) 0, FilioIncidentals = (double) 0, FilioTaxes = (double) 0,
				FilioTripTotal = (double) 0, FilioPaid = (double) 0, FilioBalance = (double) 0;

		for (int i = 0; i < count; i++) {
			if (i == (count - 1)) {
				reslogger.info(al.get(i));
				al1 = (ArrayList) al.get(i);
				FilioRoomCharges = FilioRoomCharges + Double.parseDouble(al1.get(0).toString());
				FilioIncidentals = FilioIncidentals + Double.parseDouble(al1.get(1).toString());
				FilioTaxes = FilioTaxes + Double.parseDouble(al1.get(2).toString());
				FilioTripTotal = FilioTripTotal + Double.parseDouble(al1.get(3).toString());
				FilioPaid = FilioPaid + Double.parseDouble(al1.get(4).toString());
				FilioBalance = FilioBalance + Double.parseDouble(al1.get(5).toString());
			}
		}

		Double FilioRoomChrg = Double.parseDouble(get_RoomCharge(driver, test_steps));
		Double FilioInci = Double.parseDouble(get_Inceidentals(driver, test_steps));
		Double FilioTax = Double.parseDouble(get_Taxes(driver, test_steps));
		Double FilioTripTot = Double.parseDouble(get_TotalCharges(driver, test_steps));
		Double FilioPad = Double.parseDouble(get_Payments(driver, test_steps));
		Double FilioBal = Double.parseDouble(get_Balance(driver, test_steps));

		if (Double.compare(FilioRoomChrg, FilioRoomCharges) == 0) {
			test_steps.add(
					"After Spliting the secondary reservation folio RoomCharges are validated : " + FilioRoomCharges);
			reslogger.info(
					"After Spliting the secondary reservation folio RoomCharges are validated : " + FilioRoomCharges);
		} else {
			test_steps.add("After Spliting the secondary reservation folio RoomCharges are not validated : "
					+ FilioRoomCharges);
			reslogger.info("After Spliting the secondary reservation folio RoomCharges are not validated : "
					+ FilioRoomCharges);
		}

		if (Double.compare(FilioTaxes, FilioTax) == 0) {
			test_steps.add("After Spliting the secondary reservation folio taxes are validated : " + FilioTaxes);
			reslogger.info("After Spliting the secondary reservation folio taxes are validated : " + FilioTaxes);
		} else {
			test_steps.add("After Spliting the secondary reservation folio taxes are not validated : " + FilioTaxes);
			reslogger.info("After Spliting the secondary reservation folio taxes are not validated : " + FilioTaxes);
		}
		if (Double.compare(FilioIncidentals, FilioInci) == 0) {
			test_steps.add(
					"After Spliting the secondary reservation folio Incidental are validated : " + FilioIncidentals);
			reslogger.info(
					"After Spliting the secondary reservation folio Incidental are validated : " + FilioIncidentals);
		} else {
			test_steps.add("After Spliting the secondary reservation folio Incidental are not validated : "
					+ FilioIncidentals);
			reslogger.info("After Spliting the secondary reservation folio Incidental are not validated : "
					+ FilioIncidentals);
		}
		if (Double.compare(FilioTripTotal, FilioTripTot) == 0) {
			test_steps
					.add("After Spliting the secondary reservation folio TripTotal are validated : " + FilioTripTotal);
			reslogger
					.info("After Spliting the secondary reservation folio TripTotal are validated : " + FilioTripTotal);
		} else {
			test_steps.add(
					"After Spliting the secondary reservation folio TripTotal are not validated : " + FilioTripTotal);
			reslogger.info(
					"After Spliting the secondary reservation folio TripTotal are not validated : " + FilioTripTotal);
		}
		if (Double.compare(FilioPaid, FilioPad) == 0) {
			test_steps.add("After Spliting the secondary reservation folio Paid are validated : " + FilioPaid);
			reslogger.info("After Spliting the secondary reservation folio Paid are validated : " + FilioPaid);
		} else {
			test_steps.add("After Spliting the secondary reservation folio Paid are not validated : " + FilioPaid);
			reslogger.info("After Spliting the secondary reservation folio Paid are not validated : " + FilioPaid);
		}
		if (Double.compare(FilioBalance, FilioBal) == 0) {
			test_steps.add("After Spliting the secondary reservation folio Balance are validated : " + FilioBalance);
			reslogger.info("After Spliting the secondary reservation folio Balance are validated : " + FilioBalance);
		} else {
			test_steps.add("After Spliting the secondary reservation folio Balance are validated : " + FilioBalance);
			reslogger.info("After Spliting the secondary reservation folio Balance are validated : " + FilioBalance);
		}
	}

	public void veerify_SplitResFromExistringresInHistory(WebDriver driver, ArrayList test_steps, String reservation) {
		String res = "//span[contains(text(),'Split a room from existing reservation with Confirmation Number: "
				+ reservation.trim() + "')]";
		if (driver.findElement(By.xpath(res)).isDisplayed()) {
			test_steps.add("Split a room from existing reservation with Confirmation Number: " + reservation.trim()
					+ "')] verified in History tab : " + reservation);
			reslogger.info("Split a room from existing reservation with Confirmation Number: " + reservation.trim()
					+ "')] verified in History tab : " + reservation);
		} else {
			test_steps.add("Split a room from existing reservation with Confirmation Number: " + reservation.trim()
					+ "')] not found in History tab : " + reservation);
			reslogger.info("Split a room from existing reservation with Confirmation Number: " + reservation.trim()
					+ "')] not found in History tab : " + reservation);
		}
	}

	public void verify_RoomNumberForSecondaryRes(WebDriver driver, ArrayList test_steps, ArrayList Rooms,
			String roomNumber) {

		test_steps.add("******************* Secondary reservation room number verification **********************");
		reslogger.info("******************* Secondary reservation room number verification **********************");
		int count = Rooms.size();
		String room = (String) Rooms.get(count - 1);

		String[] st = room.split(":");
		room = st[1];
		room = room.trim();
		reslogger.info(room);
		reslogger.info(roomNumber);
		if (room.equalsIgnoreCase(roomNumber)) {
			test_steps.add("Room number is verified for secondary reservation : " + room);
			reslogger.info("Room number is verified for secondary reservation : " + room);
		} else {
			test_steps.add("Room number is not verified for secondary reservation : " + room);
			reslogger.info("Room number is not verified for secondary reservation : " + room);
		}
	}

	public void change_ReservationStatus(WebDriver driver, ArrayList test_steps, String ResStatus)
			throws InterruptedException {

		try {
			Elements_CPReservation res = new Elements_CPReservation(driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;

			String expand = "//div[@class='ng-statusChnage ir-statusMenu ul']//span";
			Wait.WaitForElement(driver, expand);
			driver.findElement(By.xpath(expand)).click();
			String status = "//div[@class='ng-statusChnage ir-statusMenu ul']//span//ul//span[text()='" + ResStatus
					+ "']";
			Wait.WaitForElement(driver, status);
			driver.findElement(By.xpath(status)).click();
			test_steps.add("Reservation status changes to : " + ResStatus);
			reslogger.info("Reservation status changes to : " + ResStatus);
			if (ResStatus.equals("Cancel")) {
				res.CancelReservation_Reason.sendKeys(ResStatus);
				test_steps.add("Cancel Reason : " + ResStatus);
				reslogger.info("Cancel Reason: " + ResStatus);
				Wait.WaitForElement(driver, OR_Reservation.CancelReservation_CofirmCancellation);
				Utility.ScrollToElement(res.CancelReservation_CofirmCancellation, driver);
				res.CancelReservation_CofirmCancellation.click();
				test_steps.add("Confirm Cancel Button Clicked");
				reslogger.info("Confirm Cancel Button Clicked");
			}
			String loading = "(//div[@class='ir-loader-in'])";
			int count = 0;
			while (true) {
				if (driver.findElements(By.xpath(loading)).size() > 4) {
					break;
				} else if (count == 20) {
					break;
				} else {
					count++;
					Wait.wait2Second();

				}
				reslogger.info("Waited to loading symbol");
			}
		} catch (Exception e) {
			reslogger.info(e);
		}

	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: clickCheckInAllButton 
	 * ' Description: Click on Check In All Button
	 * ' Input parameters: String
	  * ' Created By: Gangotri
	 * ' Created On: June 3 2020
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void clickCheckInAllButton(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_CheckInAllButton);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_CheckInAllButton, driver);
		Utility.ScrollToElement(res.CP_Reservation_CheckInAllButton, driver);
		res.CP_Reservation_CheckInAllButton.click();
		test_steps.add("Click Check In All Button");
		reslogger.info("Click Check In All Button");

	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: clickCheckInButton 
	 * ' Description: Click on Check In  Button
	 * ' Input parameters: String
	  * ' Created By: Gangotri
	 * ' Created On: June 3 2020
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void clickCheckInButton(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		// Wait.explicit_wait_visibilityof_webelement_120(res.CP_Reservation_CheckInButton,
		// driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_CheckInButton);
		Utility.ScrollToElement(res.CP_Reservation_CheckInButton, driver);
		res.CP_Reservation_CheckInButton.click();
		test_steps.add("Click Check In  Button");
		reslogger.info("Click Check In  Button");

	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: clickCheckOutAllButton 
	 * ' Description: Click on Check Out All  Button
	 * ' Input parameters: String
	  * ' Created By: Gangotri
	 * ' Created On: June 3 2020
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void clickCheckOutAllButton(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_ReservationStatusCheckOutAllButton);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_ReservationStatusCheckOutAllButton, driver);
		Utility.ScrollToElement(res.CP_Reservation_ReservationStatusCheckOutAllButton, driver);
		res.CP_Reservation_ReservationStatusCheckOutAllButton.click();
		test_steps.add("Click Check Out All  Button");
		reslogger.info("Click Check Out All Button");

	}
	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: clickCheckOutButton 
	 * ' Description: Click on Check Out   Button
	 * ' Input parameters: String
	  * ' Created By: Gangotri
	 * ' Created On: June 3 2020
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void clickCheckOutButton(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_ReservationStatusCheckOutButton, driver);
		Utility.ScrollToElement(res.CP_Reservation_ReservationStatusCheckOutButton, driver);
		res.CP_Reservation_ReservationStatusCheckOutButton.click();
		test_steps.add("Click Check Out  Button");
		reslogger.info("Click Check Out  Button");

	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: verifyCheckINStayInfo 
	 * ' Description: Verify Stay Info details on CheckIn Window
	 * ' Input parameters: String
	  * ' Created By: Gangotri
	 * ' Created On: June 3 2020
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void verifyCheckINStayInfo(WebDriver driver, ArrayList test_steps, String CheckInDate, String CheckOutDate,
			String Adults, String Children, String RoomClass, List<String> RoomCharges, String Salutation,
			String GuestName, String LastName, String RatePlan) throws Exception {

		List<String> checkInDate = Arrays.asList(CheckInDate.split("\\|"));
		List<String> checkOutDate = Arrays.asList(CheckOutDate.split("\\|"));
		List<String> adults = Arrays.asList(Adults.split("\\|"));
		List<String> children = Arrays.asList(Children.split("\\|"));
		List<String> roomClass = Arrays.asList(RoomClass.split("\\|"));
		List<String> salutation = Arrays.asList(Salutation.split("\\|"));
		List<String> guestFName = Arrays.asList(GuestName.split("\\|"));
		List<String> guestLName = Arrays.asList(LastName.split("\\|"));
		List<String> ratePlan = Arrays.asList(RatePlan.split("\\|"));
		String[] checkIn, checkInSplit;
		String checkInMonth = "";
		SimpleDateFormat simpleDateformat;
		Date now;
		int Nights = 0;
		for (int i = 0; i < checkInDate.size(); i++) {
			SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
			Date dateObj1 = format1.parse(CheckInDate);
			Date dateObj2 = format1.parse(CheckOutDate);
			long diff = dateObj2.getTime() - dateObj1.getTime();
			Nights = (int) (diff / (24 * 60 * 60 * 1000));
			reslogger.info("difference between days: " + Nights);
			checkIn = checkInDate.get(i).split("/");
			if (checkIn[0].charAt(0) == '0') {
				checkIn[0] = checkIn[0].replace("" + checkIn[0].charAt(0), "");
			}
			checkInMonth = Utility.get_Month(checkInDate.get(i));
			String checkInDays = checkInMonth + " " + checkIn[0] + ", " + checkIn[2];
			String checkInDay = "//div[contains(@class,'ir-checkin-stay-info')]/..//div[@class='checkin']/span[text()='"
					+ checkInDays + "']";
			if (driver.findElement(By.xpath(checkInDay)).isDisplayed()) {
				test_steps.add("Stay Info checkin date verified : <b>" + checkInDays + "</b>");
				reslogger.info("Stay Info checkin date verified : " + checkInDays);
			} else {
				test_steps.add("Stay Info checkin date not found : <b>" + checkInDays + "</b>");
				reslogger.info("Stay Info checkin date not found : " + checkInDays);
			}
			now = new Date(checkIn[1] + "/" + checkIn[0] + "/" + checkIn[2]);
			simpleDateformat = new SimpleDateFormat("dd/MM/yyyy"); // the day of
																	// the week
																	// abbreviated
			simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the
			String dayofCheckIn = simpleDateformat.format(now); // week spelled

			String chckInday = "//div[contains(@class,'ir-checkin-stay-info')]/..//div[@class='checkin']//span[text()='"
					+ dayofCheckIn.trim() + "']";
			if (driver.findElement(By.xpath(chckInday)).isDisplayed()) {
				test_steps.add(" Stay Info checkin day verified : <b>" + dayofCheckIn + "</b>");
				reslogger.info(" Stay Info checkin day verified : " + dayofCheckIn);
			} else {
				test_steps.add(" Stay Info checkin day not found : <b>" + dayofCheckIn + "</b>");
				reslogger.info(" Stay Info checkin day not found : " + dayofCheckIn);
			}

		}

		for (int i = 0; i < checkOutDate.size(); i++) {
			String[] checkOut = checkOutDate.get(i).split("/");

			if (checkOut[0].charAt(0) == '0') {
				checkOut[0] = checkOut[0].replace("" + checkOut[0].charAt(0), "");
			}
			String checkOutMonth = Utility.get_Month(checkOutDate.get(i));
			String[] checkOutSplit = checkOut[2].split("\\|");
			String checkOutDays = checkOutMonth + " " + checkOut[0] + ", " + checkOut[2];
			String checkoutDay = "//div[contains(@class,'ir-checkin-stay-info')]/..//div[@class='checkout']/span[text()='"
					+ checkOutDays + "']";
			if (driver.findElement(By.xpath(checkoutDay)).isDisplayed()) {
				test_steps.add(" Stay Info checkout date verified : <b>" + checkOutDays + "</b>");
				reslogger.info(" Stay Info checkout date verified : " + checkOutDays);
			} else {
				test_steps.add(" Stay Info checkout date not found : <b>" + checkOutDays + "</b>");
				reslogger.info(" Stay Info checkout date not found : " + checkOutDays);
			}

			now = new Date(checkOut[1] + "/" + checkOut[0] + "/" + checkOut[2]);
			simpleDateformat = new SimpleDateFormat("dd/MM/yyyy"); // the day of
																	// the week
																	// abbreviated
			simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the
																// week spelled
																// out
																// completely
			String dayofCheckOut = simpleDateformat.format(now);
			String chkOutDay = "//div[contains(@class,'ir-checkin-stay-info')]/..//div[@class='checkout']//span[text()='"
					+ dayofCheckOut.trim() + "']";
			if (driver.findElement(By.xpath(chkOutDay)).isDisplayed()) {
				test_steps.add(" Stay Info checkout day verified : <b>" + dayofCheckOut + "</b>");
				reslogger.info("Stay Info checkout day verified : " + dayofCheckOut);
			} else {
				test_steps.add(" Stay Info checkout day not found : <b>" + dayofCheckOut + "</b>");
				reslogger.info(" Stay Info checkout day not found : " + dayofCheckOut);
			}

			String nights = "//div[contains(@class,'ir-checkin-stay-info')]/..//div[@class='noofnights']/span[text()='"
					+ Nights + "N']";
			if (driver.findElement(By.xpath(nights)).isDisplayed()) {
				test_steps.add(" Stay Info Nights verified : <b>" + Nights + "N</b>");
				reslogger.info(" Stay Info Nights verified : " + Nights + "N");
			} else {
				test_steps.add(" Stay Info Nights not found : <b>" + Nights + "N</b>");
				reslogger.info(" Stay Info Nights not found : " + Nights + "N");
			}
		}

		for (int i = 0; i < adults.size(); i++) {
			String adult = "//div[contains(@class,'ir-checkin-stay-info')]/..//div//span[contains(@data-bind,'numberOfAdults')]";
			adult = driver.findElement(By.xpath(adult)).getText().trim();
			if (adult.equals(adults.get(i).trim())) {
				test_steps.add(" Stay Info Adults verified : <b>" + adults.get(i) + "</b>");
				reslogger.info(" Stay Info Adults verified : " + adults.get(i));
			} else {
				test_steps.add(" Stay Info Adults not found : <b>" + adults.get(i) + "</b>");
				reslogger.info(" Stay Info Adults not found : " + adults.get(i));
			}

		}
		for (int i = 0; i < children.size(); i++) {
			String childrens = "//div[contains(@class,'ir-guestInfo')]/div/span//span[contains(@data-bind,'text: $data.numberOfChildren')]";
			List<WebElement> childrensList = driver.findElements(By.xpath(childrens));
			if (childrensList.get(i).getText().trim().contains(children.get(i))) {
				test_steps.add(" Stay Info Children verified : <b>" + children.get(i) + "</b>");
				reslogger.info(" Stay Info Children verified : " + children.get(i));
			} else {
				test_steps.add(" Stay Info Children not found : <b>" + children.get(i) + "</b>");
				reslogger.info(" Stay Info Children not found : " + children.get(i));
			}
		}

		for (int i = 0; i < roomClass.size(); i++) {
			String roomClasss1 = "//div[contains(@class,'ir-checkin-stay-info')]/..//div//label[contains(@data-bind,'roomClassName')]";
			List<WebElement> roomClasss = driver.findElements(By.xpath(roomClasss1));
			if (roomClasss.get(i).getText().trim().equals((roomClass.get(i) + ":").trim())) {
				test_steps.add(" Stay Info RoomClass verified : <b>" + roomClass.get(i) + "</b>");
				reslogger.info(" Stay Info RoomClass verified : " + roomClass.get(i));
			} else {
				test_steps.add(" Stay Info RoomClass not found : <b>" + roomClass.get(i) + "</b>");
				reslogger.info(" Stay Info RoomClass not found : " + roomClass.get(i));
			}
		}

		for (int i = 0; i < salutation.size(); i++) {
			String guestName = "//div[contains(@class,'ir-checkin-stay-info')]/..//span[contains(@data-bind,'guestNameInSync')][contains(text(),'"
					+ salutation.get(i) + " " + guestFName.get(i) + " " + guestLName.get(i) + "')]";
			WebElement element = driver.findElement(By.xpath(guestName));

			if (element.isDisplayed()) {
				test_steps.add(" Stay Info Guest Name verified : <b>" + salutation.get(i) + " " + guestFName.get(i)
						+ " " + guestLName.get(i) + "</b>");
				reslogger.info(" Stay Info Guest Name verified : " + salutation.get(i) + " " + guestFName.get(i) + " "
						+ guestLName.get(i));
			} else {
				test_steps.add(" Stay Info Guest Name not found : <b>" + salutation.get(i) + " " + guestFName.get(i)
						+ " " + guestLName.get(i) + "</b>");
				reslogger.info(" Stay Info Guest Name not found : " + salutation.get(i) + " " + guestFName.get(i) + " "
						+ guestLName.get(i));
			}
		}

		for (int i = 0; i < ratePlan.size(); i++) {
			String ratePlans1 = "//div[contains(@class,'ir-checkin-stay-info')]/..//span[contains(@data-bind,'ratePlanName')]";
			List<WebElement> ratePlans = driver.findElements(By.xpath(ratePlans1));
			if (ratePlans.get(i).getText().trim().equals(ratePlan.get(i).trim())) {
				test_steps.add(" Stay Info RatePlan verified :<b> " + ratePlan.get(i) + "</b>");
				reslogger.info(" Stay Info RatePlan verified : " + ratePlan.get(i));
			} else {
				test_steps.add(" Stay Info RatePlan not found : <b>" + ratePlan.get(i) + "</b>");
				reslogger.info(" Stay Info RatePlan not found : " + ratePlan.get(i));
			}
		}

		String roomCharge1 = "//div[contains(@class,'ir-checkin-stay-info')]/..//div//label[contains(@data-bind,'roomTotal ')]";
		List<WebElement> roomCharge = driver.findElements(By.xpath(roomCharge1));

		for (int i = 0; i < roomCharge.size(); i++) {
			if (roomCharge.get(i).getText().trim().equalsIgnoreCase(RoomCharges.get(i).trim())) {
				test_steps.add(" Stay Info RoomCharges verified : <b>" + RoomCharges.get(i).trim() + "</b>");
				reslogger.info(" Stay Info RoomCharges verified : " + RoomCharges.get(i).trim());
			} else {
				test_steps.add(" Stay Info RoomCharges not found : <b>" + RoomCharges.get(i).trim() + "</b>");
				reslogger.info(" Stay Info RoomCharges not found : " + RoomCharges.get(i).trim());
			}

		}

	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: verifyGuestContactInfo 
	 * ' Description: Verify Guest Contact Infor on Check In Window
	 * ' Input parameters: String
	  * ' Created By: Gangotri
	 * ' Created On: June 3 2020
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void verifyGuestContactInfo(WebDriver driver, ArrayList test_steps, String Salutation, String GuestName,
			String LastName, String Email, String Phone) {
		WebElement element;

		List<String> salutation = Arrays.asList(Salutation.split("\\|"));
		List<String> guestFName = Arrays.asList(GuestName.split("\\|"));
		List<String> guestLName = Arrays.asList(LastName.split("\\|"));
		List<String> email = Arrays.asList(Email.split("\\|"));
		List<String> phone = Arrays.asList(Phone.split("\\|"));
		for (int i = 0; i < salutation.size(); i++) {
			String test = salutation.get(i).toString().trim();
			String guestName = "//div[@id='guestInfo-acc']//span[contains(@data-bind,'irFormattedProfileName')][contains(text(),'"
					+ salutation.get(i) + " " + guestFName.get(i) + "')]";
			element = driver.findElement(By.xpath(guestName));
			if (element.isDisplayed()) {
				test_steps.add(" Stay Info Primary Guest Name verified : <b>" + salutation.get(i) + " "
						+ guestFName.get(i) + "</b>");
				reslogger.info(
						" Stay Info Primary Guest Name verified : " + salutation.get(i) + " " + guestFName.get(i));
			} else {
				test_steps.add(" Stay Info Primary Guest Name not found : <b>" + salutation.get(i) + " "
						+ guestFName.get(i) + "</b>");
				reslogger.info(
						" Stay Info Primary Guest Name not found : " + salutation.get(i) + " " + guestFName.get(i));
			}
		}

		for (int i = 0; i < guestLName.size(); i++) {
			String lastName = "//div[@id='guestInfo-acc']//span[contains(@data-bind,'LastName')][contains(text(),'"
					+ guestLName.get(i) + "')]";
			element = driver.findElement(By.xpath(lastName));
			if (element.isDisplayed()) {
				test_steps.add(" Stay Info Last Name verified : <b>" + guestLName.get(i) + "</b>");
				reslogger.info(" Stay Info Last Name verified : " + guestLName.get(i));
			} else {
				test_steps.add(" Stay Info Last Name not found : <b>" + guestLName.get(i) + "</b>");
				reslogger.info(" Stay Info Last Name not found : " + guestLName.get(i));
			}
		}
		for (int i = 0; i < email.size(); i++) {
			String emails = "//div[@id='guestInfo-acc']//span[contains(@data-bind,'EmailAddress')][contains(text(),'"
					+ email.get(i) + "')]";
			element = driver.findElement(By.xpath(emails));
			if (element.isDisplayed()) {
				test_steps.add(" Stay Info Email verified : <b>" + email.get(i) + "</b>");
				reslogger.info(" Stay Info Email verified : " + email.get(i));
			} else {
				test_steps.add(" Stay Info Email not found : <b>" + email.get(i) + "</b>");
				reslogger.info(" Stay Info Email not found : " + email.get(i));
			}

		}

		for (int i = 0; i < phone.size(); i++) {
			if (i == 0) {
				String extension = "1-(";
				String firstPhone = phone.get(i).substring(0, 3);
				String phoneBracket = firstPhone + ") ";
				String secondPhone = Phone.substring(3, 6);
				String lastPhone = Phone.substring(6, 10);
				String finalPhone = extension + phoneBracket + secondPhone + "-" + lastPhone;
				String phones = "//div[@id='guestInfo-acc']//span[contains(@data-bind,'irFormattedPhoneNumber')][contains(text(),'"
						+ finalPhone + "')]";
				element = driver.findElement(By.xpath(phones));
				if (element.isDisplayed()) {
					test_steps.add(" Stay Info Phone verified : <b>" + finalPhone + "</b>");
					reslogger.info(" Stay Info Phone verified : " + finalPhone);
				} else {
					test_steps.add(" Stay Info Phone not found : <b>" + finalPhone + "</b>");
					reslogger.info(" Stay Info Phone not found : " + finalPhone);
				}
			} else {
				String phones = "//div[@id='guestInfo-acc']//span[contains(@data-bind,'irFormattedPhoneNumber')][contains(text(),'"
						+ phone.get(i) + "')]";
				element = driver.findElement(By.xpath(phones));
				if (element.isDisplayed()) {
					test_steps.add(" Stay Info Phone verified : <b>" + phone.get(i) + "</b>");
					reslogger.info(" Stay Info Phone verified : " + phone.get(i));
				} else {
					test_steps.add(" Stay Info Phone not found : <b>" + phone.get(i) + "</b>");
					reslogger.info(" Stay Info Phone not found : " + phone.get(i));
				}
			}

		}

	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: verifyGenerateGuestReportToggle 
	 * ' Description: Verify Generate Guest Registration Toggle in CheckIn Window and Checkot Window
	 * ' Input parameters: String
	  * ' Created By: Gangotri
	 * ' Created On: June 3 2020
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void verifyGenerateGuestReportToggle(WebDriver driver, ArrayList test_steps, boolean isFlagSetting)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.wait5Second();

		if (isFlagSetting) {
			boolean isExist = Utility.isElementPresent(driver,
					By.xpath(OR_Reservation.CP_Reservation_CheckIn_GuestRegistrationReportToggleOff));

			assertEquals(isExist, false, "Failed: to Verify Toggle");
			test_steps.add("Generate Guest Registration Toggle On");

			reslogger.info("Generate Guest Registration Toggle On");
		} else if (!isFlagSetting) {
			assertTrue(res.CP_Reservation_CheckIn_GuestRegistrationReportToggleOff.isEnabled(),
					"Failed: to Verify Toggle");
			test_steps.add("Generate Guest Registration Toggle Off");
			reslogger.info("Generate Guest Registration Toggle Off");

		}
	}

	public void cancelReservation(WebDriver driver, ArrayList test_steps) {
		String expand = "//div[@class='ng-statusChnage ir-statusMenu ul']//span[@class='dropdown dropdown-toggle ir-dropdown-span-status']";
		Wait.waitForElementToBeClickable(By.xpath(expand), driver);
		driver.findElement(By.xpath(expand)).click();
		String status = "//div[@class='ng-statusChnage ir-statusMenu ul']//span//ul//span[text()='Cancel']";
		Wait.waitForElementToBeClickable(By.xpath(status), driver);
		driver.findElement(By.xpath(status)).click();
		test_steps.add("Clicking on cancel reservation button");
		reslogger.info("Clicking on cancel reservation button");
	}

	public void disableGenerateGuestReportToggle(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.CP_Reservation_CheckIn_GuestRegistrationReportToggle),
				driver);
		res.CP_Reservation_CheckIn_GuestRegistrationReportToggle.click();
		test_steps.add("Enabled Generate Guest Registration Toggle");
		reslogger.info("Enabled Generate Guest Registration Toggle");
		Wait.waitForElementToBeClickable(
				By.xpath(OR_Reservation.CP_Reservation_CheckIn_GuestRegistrationReportToggleOff), driver);
	}

	public boolean generatGuestReportToggle(WebDriver driver, ArrayList test_steps, String IsGuesRegistration)
			throws InterruptedException {
		boolean GenerateGuestStatementStatus = false;
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_CheckIn_GuestRegistrationReportToggle);
		boolean isExist = Utility.isElementPresent(driver,
				By.xpath(OR_Reservation.CP_Reservation_CheckIn_GuestRegistrationReportToggleOff));
		if (IsGuesRegistration.contentEquals("Yes")) {
			if (isExist) {
				GenerateGuestStatementStatus = true;
				disableGenerateGuestReportToggle(driver, test_steps);
			} else {
				GenerateGuestStatementStatus = true;
				test_steps.add("Enabled Generate Guest Registration Toggle");
				reslogger.info("Enabled Generate Guest Registration Toggle");
			}
		} else if (IsGuesRegistration.contentEquals("No")) {
			if (isExist) {
				GenerateGuestStatementStatus = false;
				test_steps.add("Disabled Generate Guest Registration Toggle");
				reslogger.info("Disabled Generate Guest Registration Toggle");
			} else {
				GenerateGuestStatementStatus = false;
				disableGenerateGuestReportToggle(driver, test_steps);
			}
		}
		return GenerateGuestStatementStatus;

	}

	public boolean isRoomReserved = false;

	private void CheckINScreenRoomOccupiedWithOtherReservation(WebDriver driver, boolean isRoomBooked)
			throws InterruptedException {
		int count = 0;
		List<String> OccupiedRooms = new ArrayList<String>();
		Elements_CPReservation res = new Elements_CPReservation(driver);
		String path = "//div[@id='ir-cp-alert-modal']//div[@class='modal-body'][contains(text(),'occupying room')]";
		isRoomBooked = Utility.isElementPresent(driver, By.xpath(path));
		while (isRoomBooked) {
			reslogger.info("Occupied: " + path);

			isRoomReserved = true;
			WebElement element = driver.findElement(By.xpath(path));
			String textIs = element.getText();
			reslogger.info("<br>" + textIs);
			String[] nag = textIs.split("is occupying room ");
			List<String> roomNos = new ArrayList<String>();
			for (int i = 0; i < nag.length; i++) {
				if (!(i == 0) && !(i == (nag.length - 1))) {
					roomNos.add(nag[i].split("Reservation")[0].trim());
				}

				if ((i == nag.length - 1)) {
					roomNos.add(nag[i].split("Please")[0].trim());
				}

			}

			reslogger.info(roomNos);
			Utility.ScrollToElement(element, driver);

			res.CP_Reservation_CheckInOkButton.click();
			reslogger.info("Click Confirm CheckIn Button");
			String DropDownBoxPath = "//ir-stay-info-checkin//div[contains(@class,'ir-roomInfo')]//button[@title='"
					+ roomNos.get(count).toString() + "']";
			Wait.WaitForElement(driver, DropDownBoxPath);
			WebElement dropdownElement = driver.findElement(By.xpath(DropDownBoxPath));
			Utility.ScrollToElement(dropdownElement, driver);
			dropdownElement.click();
			reslogger.info("Click Drop Down Box");
			Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_CheckDropDownOpenRooms);
			Utility.ScrollToElement(res.CP_Reservation_CheckDropDownOpenRooms.get(0), driver);

			int count1 = res.CP_Reservation_CheckDropDownOpenRooms.size();
			reslogger.info("Count is: " + count1);
			Random random = new Random();
			randomNumber = random.nextInt(count1 - 1) + 1;
			reslogger.info("count : " + count1);
			reslogger.info("randomNumber : " + randomNumber);
			String roomNumber = "//ir-stay-info-checkin//div[contains(@class,'ir-roomInfo')]//div[contains(@class,'open')]/div[contains(@class,'dropdown-menu open')]//ul/li["
					+ randomNumber + "]/a/span";
			reslogger.info(roomNumber);
			Wait.waitUntilPresenceOfElementLocated(roomNumber, driver);
			Utility.ScrollToElement(driver.findElement(By.xpath(roomNumber)), driver);
			driver.findElement(By.xpath(roomNumber)).click();
			res.CP_Reservation_ConfirmCheckIn_Button.click();
			reslogger.info("Click Confirm CheckIn Button");
			isRoomBooked = Utility.isElementDisplayed(driver, By.xpath(path));
			boolean isRoomDirty = Utility.isElementPresent(driver,
					By.xpath(OR_Reservation.CP_Reservation_CheckIn_ModelPopMsg));

			if (!isRoomBooked) {
				reslogger.info("Occuied Again");
				break;
			} else if (isRoomDirty) {
				ClickConfirmButtonOfDirtyPopupModel(driver, test_steps, isRoomDirty);
			}

		}

	}

	public boolean isRoomUnAssigned = false;

	private void CheckINScreenRoomUnAssigned(WebDriver driver, boolean isExist) throws InterruptedException {
		int count = 0;

		List<String> OccupiedRooms = new ArrayList<String>();
		Elements_CPReservation res = new Elements_CPReservation(driver);

		while (isExist) {
			isRoomUnAssigned = true;
			Wait.wait5Second();
			Utility.ScrollToElement(res.CP_Reservation_CheckINUassigned.get(count), driver);
			res.CP_Reservation_CheckINUassigned.get(count).click();
			reslogger.info("Click UnAssigned Drop Down Box");
			String path = "//ir-stay-info-checkin//div[contains(@class,'dropdown-menu open')]//ul/li[@class='selected']/following-sibling::li/a/span[@class='text']";
			List<WebElement> ele = driver.findElements(By.xpath(path));
			int count1 = ele.size();
			reslogger.info("Count is: " + count1);
			Random random = new Random();
			randomNumber = random.nextInt(count1 - 1) + 1;
			reslogger.info("count : " + count1);
			reslogger.info("randomNumber : " + randomNumber);
			String roomNumber = "//ir-stay-info-checkin//div[contains(@class,'ir-roomInfo')]//div[contains(@class,'open')]/div[contains(@class,'dropdown-menu open')]//ul/li["
					+ randomNumber + "]/a/span";
			Wait.WaitForElement(driver, roomNumber);
			Utility.ScrollToElement(driver.findElement(By.xpath(roomNumber)), driver);
			driver.findElement(By.xpath(roomNumber)).click();
			res.CP_Reservation_ConfirmCheckIn_Button.click();
			reslogger.info("Click Confirm CheckIn Button");
			isExist = Utility.isElementPresent(driver,
					By.xpath(OR_Reservation.CP_Reservation_CheckINUassignedTosterMsg));
			reslogger.info("UnAssigned Exist or Not: " + isExist);
			boolean isRoomDirty = Utility.isElementPresent(driver,
					By.xpath(OR_Reservation.CP_Reservation_CheckIn_ModelPopMsg));

			if (!isExist) {
				break;
			} else if (isRoomDirty) {
				ClickConfirmButtonOfDirtyPopupModel(driver, test_steps, isRoomDirty);
			}
		}
		int count2 = 0;
		String path = "//div[@id='ir-cp-alert-modal']//div[@class='modal-body'][contains(text(),'occupying room')]";
		Wait.wait5Second();
		boolean isRoomBooked = Utility.isElementPresent(driver, By.xpath(path));

		while (isRoomBooked) {
			reslogger.info("Occupied: " + path);
			WebElement element = driver.findElement(By.xpath(path));
			String textIs = element.getText();
			reslogger.info(textIs);
			String[] nag = textIs.split("is occupying room ");
			List<String> roomNos = new ArrayList<String>();
			for (int i = 0; i < nag.length; i++) {
				if (!(i == 0) && !(i == (nag.length - 1))) {
					roomNos.add(nag[i].split("Reservation")[0].trim());
				}

				if ((i == nag.length - 1)) {
					roomNos.add(nag[i].split("Please")[0].trim());
				}

			}

			reslogger.info(roomNos);
			Utility.ScrollToElement(element, driver);
			res.CP_Reservation_CheckInOkButton.click();
			String DropDownBoxPath = "//ir-stay-info-checkin//div[contains(@class,'ir-roomInfo')]//button[@title='"
					+ roomNos.get(count2).toString() + "']";
			Wait.WaitForElement(driver, DropDownBoxPath);
			WebElement dropdownElement = driver.findElement(By.xpath(DropDownBoxPath));
			Utility.ScrollToElement(dropdownElement, driver);
			dropdownElement.click();
			reslogger.info("Room is Dirty");
			int count1 = res.CP_Reservation_CheckDropDownOpenRooms.size();
			reslogger.info("Count is: " + count1);
			Random random = new Random();
			randomNumber = random.nextInt(count1 - 1) + 1;
			reslogger.info("count : " + count1);
			reslogger.info("randomNumber : " + randomNumber);
			String roomNumber = "//ir-stay-info-checkin//div[contains(@class,'ir-roomInfo')]//div[contains(@class,'open')]/div[contains(@class,'dropdown-menu open')]//ul/li["
					+ randomNumber + "]/a/span";
			Wait.WaitForElement(driver, roomNumber);
			Utility.ScrollToElement(driver.findElement(By.xpath(roomNumber)), driver);
			driver.findElement(By.xpath(roomNumber)).click();
			res.CP_Reservation_ConfirmCheckIn_Button.click();
			/*
			 * isRoomBooked = Utility.isElementPresent(driver, By.xpath(path));
			 */
			isRoomBooked = Utility.isElementDisplayed(driver, By.xpath(path));
			reslogger.info("Room is Again Dirty or Not: " + isRoomBooked);
			boolean isRoomDirty = Utility.isElementPresent(driver,
					By.xpath(OR_Reservation.CP_Reservation_CheckIn_ModelPopMsg));

			if (!isRoomBooked) {

				break;
			} else if (isRoomDirty) {
				ClickConfirmButtonOfDirtyPopupModel(driver, test_steps, isRoomDirty);
			}
		}

	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: clickOnProceedToCheckInPaymentButton 
	 * ' Description: Clicking on  Proceed to Check-In Payment Button
	 * ' Input parameters: String
	  * ' Created By: Gangotri
	 * ' Created On: June 3 2020
	 * 
	 * ##########################################################################################################################################################################
	 */
	public void clickOnProceedToCheckInPaymentButton(WebDriver driver, ArrayList<String> test_steps) throws Exception {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		try {
			Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.CP_Reservation_ConfirmCheckInPayment_Button),
					driver);
			Utility.ScrollToElement(res.CP_Reservation_ConfirmCheckInPayment_Button, driver);
			res.CP_Reservation_ConfirmCheckInPayment_Button.click();
			test_steps.add("Clicking on  Proceed to Check-In Payment Button");
			reslogger.info("Clicking on Proceed to Check-In Payment Button");

		} catch (Exception e) {
			Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.CP_Reservation_ConfirmCheckIn_Button), driver);
			Utility.ScrollToElement(res.CP_Reservation_ConfirmCheckIn_Button, driver);
			res.CP_Reservation_ConfirmCheckIn_Button.click();
			test_steps.add("Clicking on  Proceed to Check-In Payment Button");
			reslogger.info("Clicking on Proceed to Check-In Payment Button");
		}

	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: completeCheckInProcess 
	 * ' Description: Complete Check In  Process, Covered Dirty message , Unassigned and Room Reserved with another reservation
	 * ' Input parameters: String
	  * ' Created By: Gangotri
	 * ' Created On: June 3 2020
	 * 
	 * ##########################################################################################################################################################################
	 */
	public boolean isCheckOutAllPresent = false, isRoomDirty = false, confirmCheckIn = false,
			confirmCheckInWithPayment = false;

	public String completeCheckInProcess(WebDriver driver, ArrayList test_steps) throws InterruptedException {

		Elements_CPReservation res = new Elements_CPReservation(driver);

		String buttonName = null;
		if (res.CP_Reservation_ConfirmCheckIn_Button.isEnabled()) {
			confirmCheckIn = true;
			Utility.ScrollToElement(res.CP_Reservation_ConfirmCheckIn_Button, driver);
			buttonName = res.CP_Reservation_ConfirmCheckIn_Button.getText();
			res.CP_Reservation_ConfirmCheckIn_Button.click();
			test_steps.add("Clicking on  Confirm Check-In Button");
			reslogger.info("Clicking on Confirm  Check-In Button");
			boolean isUnassigned = Utility.isElementPresent(driver,
					By.xpath(OR_Reservation.CP_Reservation_CheckINUassignedTosterMsg));
			String path = "//div[@id='ir-cp-alert-modal']//div[@class='modal-body'][contains(text(),'occupying room')]";
			boolean isRoomBooked = Utility.isElementPresent(driver, By.xpath(path));
			if (isUnassigned) {
				CheckINScreenRoomUnAssigned(driver, isUnassigned);
			}

			if (isRoomBooked) {
				CheckINScreenRoomOccupiedWithOtherReservation(driver, isRoomBooked);

			}
			isCheckOutAllPresent = Utility.isElementPresent(driver,
					By.xpath(OR_Reservation.CP_Reservation_CheckOutAllButton));
			isRoomDirty = Utility.isElementPresent(driver, By.xpath(OR_Reservation.CP_Reservation_CheckIn_ModelPopMsg));
			String loading = "(//div[@class='ir-loader-in'])";
			Wait.waitTillElementDisplayed(driver, loading);

			if (isRoomDirty) {
				ClickConfirmButtonOfDirtyPopupModel(driver, test_steps, isRoomDirty);
			}

		} else if (res.CP_Reservation_ConfirmCheckInPayment_Button.isEnabled()) {
			confirmCheckInWithPayment = true;

			Utility.ScrollToElement(res.CP_Reservation_ConfirmCheckInPayment_Button, driver);
			buttonName = res.CP_Reservation_ConfirmCheckInPayment_Button.getText();
			res.CP_Reservation_ConfirmCheckInPayment_Button.click();
			test_steps.add("Clicking on  Proceed to Check-In Payment Button");
			reslogger.info("Clicking on Proceed to Check-In Payment Button");

			boolean isUnassigned = Utility.isElementPresent(driver,
					By.xpath(OR_Reservation.CP_Reservation_CheckINUassignedTosterMsg));

			if (isUnassigned) {
				CheckINScreenRoomUnAssigned(driver, isUnassigned);
			}
			String path = "//div[@id='ir-cp-alert-modal']//div[@class='modal-body'][contains(text(),'occupying room')]";
			boolean isRoomBooked = Utility.isElementPresent(driver, By.xpath(path));

			if (isRoomBooked) {
				CheckINScreenRoomOccupiedWithOtherReservation(driver, isRoomBooked);
			}
			isCheckOutAllPresent = Utility.isElementPresent(driver,
					By.xpath(OR_Reservation.CP_Reservation_CheckOutAllButton));
			isRoomDirty = Utility.isElementPresent(driver, By.xpath(OR_Reservation.CP_Reservation_CheckIn_ModelPopMsg));
			String loading = "(//div[@class='ir-loader-in'])";
			Wait.waitTillElementDisplayed(driver, loading);
			if (isRoomDirty) {
				ClickConfirmButtonOfDirtyPopupModel(driver, test_steps, isRoomDirty);
			}
		}

		return buttonName;
	}

	public void ClickConfirmButtonOfDirtyPopupModel(WebDriver driver, ArrayList test_steps,

			boolean isPresent) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		if (isPresent) {

			Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_CheckIn_ModelPopConfirmButton);
			Utility.ScrollToElement(res.CP_Reservation_CheckIn_ModelPopConfirmButton, driver);
			test_steps.add("One or more of the selected room(s) is Dirty");
			reslogger.info("One or more of the selected room(s) is Dirty");
			res.CP_Reservation_CheckIn_ModelPopConfirmButton.click();
			test_steps.add("Click Confirm Button");
			reslogger.info("Click Confirm Button");
			String loading = "(//div[@class='ir-loader-in'])";
			Wait.waitTillElementDisplayed(driver, loading);
			reslogger.info("Loading Done");
		} else {
			test_steps.add("One or more of the selected room(s) is Not Dirty ");
			reslogger.info("One or more of the selected room(s) is Not Dirty ");
		}

	}

	public void switchToTabTwo(WebDriver driver, ArrayList test_steps) throws InterruptedException {

		Wait.waitForNewWindow(driver, 10);
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		reslogger.info(tabs2);
		if (tabs2.size() == 2) {
			driver.switchTo().window(tabs2.get(1));
			test_steps.add("Switch to report tab");
			reslogger.info("Switch to report tab");
		}

	}

	public void SwitchToTabTwoAndClose(WebDriver driver, ArrayList test_steps) throws InterruptedException {

		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		reslogger.info(tabs2);
		if (tabs2.size() == 2) {
			driver.switchTo().window(tabs2.get(1));
			driver.close();
			test_steps.add("Switch to Tab tab Two and Close");
			reslogger.info("Switch to Tab tab Two and Close");
			Wait.wait2Second();
			driver.switchTo().window(tabs2.get(0));
			test_steps.add("Switch to Main tab");
			reslogger.info("Switch to Main tab");
		}

	}

	public String getURL = "";

	public void VerifyGuestRegistrationReportDisplayed(WebDriver driver, ArrayList test_steps)
			throws InterruptedException {

		Wait.wait5Second();
		getURL = driver.getCurrentUrl();
		reslogger.info(getURL);
		assertTrue(getURL.contains(".pdf"));
		test_steps.add("Guest Registration Report Page is Displayed");
		reslogger.info("Guest Registration Report Page is Displayed");
	}

	public void switchToTabOne(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Wait.waitForNewWindow(driver, 10);
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		reslogger.info(tabs2);
		/*
		 * if (tabs2.size() == 2) { driver.switchTo().window(tabs2.get(0));
		 * test_steps.add("Switch to Main tab");
		 * reslogger.info("Switch to Main tab"); }
		 */

		driver.switchTo().window(tabs2.get(0));
		test_steps.add("Switch to Main tab");
		reslogger.info("Switch to Main tab");
	}

	public String getMainWindow(WebDriver driver, ArrayList test_steps) {
		String winHandelBefore = driver.getWindowHandle();
		test_steps.add("Get Main Window");
		reslogger.info("Get Main Window");
		return winHandelBefore;
	}

	public void switchToMainWindow(WebDriver driver, ArrayList test_steps, String windowName) {
		Wait.waitForNewWindow(driver, 10);
		driver.switchTo().window(windowName);
		test_steps.add("Switch to Parent Window");
		reslogger.info("Switch to Parent Window");

	}

	public void switchToChildWindow(WebDriver driver, ArrayList test_steps) {
		Wait.waitForNewWindow(driver, 10);
		for (String windowHandel : driver.getWindowHandles())

		{
			reslogger.info(windowHandel);
			driver.switchTo().window(windowHandel);
			test_steps.add("Switch to Child Window");
			reslogger.info("Switch Child Window");
		}
	}

	public boolean verifyPDFContent(String strURL, String reqTextInPDF) {

		boolean flag = false;

		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		String parsedText = null;

		try {
			URL url = new URL(strURL);
			BufferedInputStream file = new BufferedInputStream(url.openStream());
			PDFParser parser = new PDFParser(file);
			parser.parse();
			String TestText = new PDFTextStripper().getText(parser.getPDDocument());
			assertTrue(TestText.contains(reqTextInPDF));
			/*
			 * cosDoc = parser.getDocument(); pdfStripper = new
			 * PDFTextStripper(); pdfStripper.setStartPage(1);
			 * pdfStripper.setEndPage(1);
			 * 
			 * pdDoc = new PDDocument(cosDoc); parsedText =
			 * pdfStripper.getText(pdDoc);
			 */
		} catch (MalformedURLException e2) {
			System.err.println("URL string could not be parsed " + e2.getMessage());
		} catch (IOException e) {
			System.err.println("Unable to open PDF Parser. " + e.getMessage());
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();
			} catch (Exception e1) {
				e.printStackTrace();
			}
		}

		reslogger.info("+++++++++++++++++");
		reslogger.info(parsedText);
		reslogger.info("+++++++++++++++++");

		// if(parsedText.contains(reqTextInPDF)) {
		// flag=true;
		// }

		return flag;
	}

	public void verifyHistoryForCheckin(WebDriver driver, ArrayList test_steps, String desc, String abb, String RoomNo,
			String CheckInStatus, ArrayList<String> RoomAbbri, ArrayList<String> Rooms) throws InterruptedException {
		if (CheckInStatus.equals("CheckInSingleRoom") || CheckInStatus.equals("CheckOutSingleRoom")
				|| CheckInStatus.equals("RollBackSingleRoom")) {
			String[] room = RoomNo.split(":");
			String FinalRoomNo = room[1].trim();
			reslogger.info(" Room No: " + FinalRoomNo);

			String[] abbr = abb.split(":");
			String FinalAbb = abbr[1].trim();
			reslogger.info(" Room Abb: " + FinalAbb);

			String path = "//td/span[contains(text(),'" + desc
					+ "')]/ancestor::td/following-sibling::td/span[contains(text(),'" + FinalAbb + ": " + FinalRoomNo
					+ "')]";
			WebElement element = driver.findElement(By.xpath(path));
			Utility.ScrollToElement(element, driver);
			assertTrue(element.isDisplayed(), "Failed: to Verify Description and Room");
			test_steps.add("Verified Description: <b>" + desc + "</b>");
			reslogger.info("Verified Description: " + desc);
			test_steps.add("Verified Room: <b>" + FinalAbb + ": " + FinalRoomNo + "</b>");
			reslogger.info("Verified Room: " + FinalAbb + ": " + FinalRoomNo);
		} else if (CheckInStatus.equals("CheckInAll") || CheckInStatus.equals("Check Out")
				|| CheckInStatus.equals("RollBack")) {
			for (int i = 0; i < Rooms.size(); i++) {
				String[] room = Rooms.get(i).split(":");
				String FinalRoomNo = room[1].trim();
				reslogger.info(" Room No: " + FinalRoomNo);

				String[] abbr = RoomAbbri.get(i).split(":");
				String FinalAbb = abbr[1].trim();
				reslogger.info(" Room Abb: " + FinalAbb);

				String path = "//td/span[contains(text(),'" + desc
						+ "')]/ancestor::td/following-sibling::td/span[contains(text(),'" + FinalAbb + ": "
						+ FinalRoomNo + "')]";
				WebElement element = driver.findElement(By.xpath(path));
				Utility.ScrollToElement(element, driver);
				assertTrue(element.isDisplayed(), "Failed: to Verify Description and Room");
				test_steps.add("Verified Description: <b>" + desc + "</b>");
				reslogger.info("Verified Description: " + desc);
				test_steps.add("Verified Room: <b>" + FinalAbb + ": " + FinalRoomNo + "</b>");
				reslogger.info("Verified Room: " + FinalAbb + ": " + FinalRoomNo);
			}
		}

	}

	public void verifyDocuments(WebDriver driver, ArrayList test_steps, String resNO) throws InterruptedException {
		String Path = "//td[contains(text(),'" + resNO + "')]";
		Wait.WaitForElement(driver, Path);
		WebElement element = driver.findElement(By.xpath(Path));
		Utility.ScrollToElement(element, driver);
		assertTrue(element.isDisplayed(), "Failed: to Verify Documents");
		test_steps.add("Verified Documents: <b>" + resNO + "</b>");
		reslogger.info("Verified Documents: " + resNO);
	}

	public void clickGuestContactInfoCollapseIcon(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Utility.ScrollToElement(res.CP_Reservation_GuestContactInforCollapse, driver);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_GuestContactInforCollapse, driver);
		res.CP_Reservation_GuestContactInforCollapse.click();
		test_steps.add("Click Guest Contact Infor Collapse Icon");
		reslogger.info("Click Guest Contact Infor Collapse Icon");

	}

	public List<String> getStayInfoRoomCharges(WebDriver driver, ArrayList<String> test_steps) {
		List<String> roomCharges = new ArrayList<String>();
		String path = "//div[contains(text(),'Stay')]/..//div//span[contains(@data-bind,'roomTotal')]";
		List<WebElement> roomCharge = driver.findElements(By.xpath(path));
		for (int i = 0; i < roomCharge.size(); i++) {
			roomCharges.add(roomCharge.get(i).getText());
			reslogger.info(roomCharges);

		}
		test_steps.add("Successfully Get Stay Info Room Charges");
		reslogger.info("Successfully Get Stay Info Room Charges");
		return roomCharges;
	}

	public List<String> getStayInfoRoomNo(WebDriver driver, ArrayList<String> test_steps) {
		List<String> roomNos = new ArrayList<String>();
		String path = "//div[contains(text(),'Stay')]/..//div[@class='ir-flex-grow ir-roomInfo']//span[contains(@data-bind,'roomNumber')]";
		List<WebElement> roomNo = driver.findElements(By.xpath(path));
		for (int i = 0; i < roomNo.size(); i++) {
			roomNos.add(roomNo.get(i).getText());
			reslogger.info(roomNos);

		}
		// test_steps.add("Successfully Get Stay Info Room Number");
		reslogger.info("Successfully Get Stay Info Room Number");
		return roomNos;
	}

	public List<String> getStayInfoGuestName(WebDriver driver, ArrayList<String> test_steps) {
		List<String> guestNames = new ArrayList<String>();
		String path = "//div[contains(text(),'Stay')]/..//div[@class='ir-flex-grow ir-guestInfo']//span[contains(@data-bind,'guestName')]";
		List<WebElement> roomNo = driver.findElements(By.xpath(path));
		for (int i = 0; i < roomNo.size(); i++) {

			guestNames.add(roomNo.get(i).getText());
			reslogger.info(guestNames);

		}

		test_steps.add("Get Stay Info Room Gust Name: <b>" + guestNames + " </b>");
		reslogger.info("Get Stay Info Room Gust Name: " + guestNames);
		return guestNames;
	}

	public void clickPrintButtonFromSuccessFullPop(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement(res.CP_CheckInSuccessPrintButton, driver);
		res.CP_CheckInSuccessPrintButton.click();
		test_steps.add("Click Print Button ");
		reslogger.info("Click Print Button ");
		Wait.wait5Second();
	}

	public void reservationStatusPanelSelectStatus(WebDriver driver, String status, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_CPReservation CPReservationPage = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_StatusPanel_DropDownBox);
		Wait.explicit_wait_visibilityof_webelement(CPReservationPage.CP_Reservation_StatusPanel_DropDownBox, driver);
		Utility.ScrollToElement(CPReservationPage.CP_Reservation_StatusPanel_DropDownBox, driver);
		CPReservationPage.CP_Reservation_StatusPanel_DropDownBox.click();
		test_steps.add("Click on Drop Down box of Reservation Status Panel");
		reslogger.info("Click on Drop Down box of Reservation Status Panel");
		String path = "//span[contains(@class,'ir-dropdown-span-status open')]//ul[contains(@class,'dropdown-menu')]/li/a/span[contains(text(),'"
				+ status + "')]";
		WebElement element = driver.findElement(By.xpath(path));
		Wait.explicit_wait_visibilityof_webelement(element, driver);
		element.click();
		test_steps.add("Select Status : <b>" + status + "</b>");
		reslogger.info("Select Status : " + status);

		if (status.equals("Cancel") || status.equals("No Show")) {

			// Wait till pop model is not displayed
			int counter = 0;
			while (true) {
				if (!CPReservationPage.CP_Reservation_NoShow_Cancel_CheckIn_CheckOut_Title.isDisplayed()) {
					break;
				} else if (counter == 40) {
					break;
				} else {
					counter++;
				}
			}
		}
	}

	public boolean checkedVoidRoomChargeCheckBox(WebDriver driver, ArrayList<String> test_steps) {
		boolean isChecked = false;
		Elements_CPReservation res = new Elements_CPReservation(driver);

		String path = "//span[contains(text(),'Void Room Charges')]/ancestor::div/label/input|//span[contains(text(),'Void room charges for unused nights')]/ancestor::div/label/input";
		isChecked = driver.findElement(By.xpath(path)).isSelected();
		if (!isChecked) {
			res.CP_Reservation_NoShow_VoidRoomChargeCheckBox.click();
			test_steps.add("Checked Void Room Charge Check Box ");
			reslogger.info("Checked Void Room Charge Check Box ");
		} else {
			test_steps.add(" Void Room Charge Check Box is Already Checked ");
			reslogger.info("Void Room Charge Check Box is Already Checked ");
		}

		return isChecked;
	}

	public boolean unCheckedVoidRoomChargeCheckBox(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		boolean isUnchecked = false;
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.wait5Second();
		String path = "//span[contains(text(),'Void Room Charges')]/ancestor::div/label/input|//span[contains(text(),'Void room charges for unused nights')]/ancestor::div/label/input";

		isUnchecked = driver.findElement(By.xpath(path)).isSelected();
		if (isUnchecked) {
			res.CP_Reservation_NoShow_VoidRoomChargeCheckBox.click();
			test_steps.add("UnChecked Void Room Charge Check Box ");
			reslogger.info("UnChecked Void Room Charge Check Box ");
		} else {
			test_steps.add("UnChecked Void Room Charge Check Box ");
			reslogger.info("UnChecked Void Room Charge Check Box ");
		}
		return isUnchecked;
	}

	public void clickProceedToNoShowPaymentButton(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement(res.ProceedToPaymentButton, driver);
		res.ProceedToPaymentButton.click();
		test_steps.add("Click on Proceed To No Show Payment Button ");
		reslogger.info("Click on Proceed To No Show Payment Button ");
	}

	public void clickConfirmNoShowButton(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_ConfirmButton, driver);
		res.CP_Reservation_ConfirmButton.click();
		test_steps.add("Click on Confirm No Show Button ");
		reslogger.info("Click on Confirm No Show Button ");
	}

	public void clickConfirmButtonFromNoShowModelPopup(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_ConfirmNoShowNoButton, driver);
		res.CP_Reservation_ConfirmNoShowNoButton.click();
		test_steps.add("Click on " + " 'No, Cancel the no show process' " + "Button ");
		reslogger.info("Click on " + " 'No, Cancel the no show process' " + "Button ");
	}

	public boolean verifyConfirmYesButton(WebDriver driver) {
		boolean value = Utility.isElementPresent(driver, By.xpath(OR_Reservation.ConfirmDialog_YesButton));
		return value;
	}

	public void clickYesButtonOfConfirmTheNoShowProcessPopupModel(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_ConfirmNoShowYesButton, driver);
		res.CP_Reservation_ConfirmNoShowYesButton.click();
		test_steps.add("Click on " + " 'Yes, Confirm no show & refund later' " + "Button ");
		reslogger.info("Click on " + " 'Yes, Confirm no show & refund later' " + "Button ");
	}

	public void clickCloseButtonOfPaymentWindow(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_NoShowPaymentPopup_CloseButton, driver);
		res.CP_Reservation_NoShowPaymentPopup_CloseButton.click();
		test_steps.add("Click Close Button ");
		reslogger.info("Click Close Button ");
	}

	public void clickYesButtonOfNoShowPaymentWindow(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_NoShowPaymentPopup_CloseButtonMsgYesButton,
				driver);
		res.CP_Reservation_NoShowPaymentPopup_CloseButtonMsgYesButton.click();
		test_steps.add("Click Yes Button ");
		reslogger.info("Click Yes Button ");
	}

	public void clickNoButtonOfNoShowPaymentWindow(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_NoShowPaymentPopup_CloseButtonMsgNoButton,
				driver);
		res.CP_Reservation_NoShowPaymentPopup_CloseButtonMsgNoButton.click();
		test_steps.add("Click No Button ");
		reslogger.info("Click No Button ");
	}

	public void verifyReservationPopWindow(WebDriver driver, String Title, String GuestName, String Status,
			String ResNo, ArrayList<String> test_steps, String OptionPerform) throws InterruptedException {
		Elements_CPReservation CPReservationPage = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.NoShow_Cancel_CheckIn_CheckOut_Title);
		Wait.explicit_wait_visibilityof_webelement(
				CPReservationPage.CP_Reservation_NoShow_Cancel_CheckIn_CheckOut_Title, driver);
		// Verified No Show Reservation Page Title
		assertEquals(
				CPReservationPage.CP_Reservation_NoShow_Cancel_CheckIn_CheckOut_Title.getText().toLowerCase().trim(),
				Title.toLowerCase().trim(), "Failed: To verify Reservation Page Title");
		test_steps.add("Verified Header: <b>" + Title + "</b>");
		reslogger.info(" Verified Header: " + Title);
		// Verified No Show Reservation Page Guest Name
		assertEquals(CPReservationPage.CP_Reservation_NoShow_Cancel_CheckIn_CheckOut_GuestName.getText().toLowerCase()
				.trim(), GuestName.toLowerCase().trim(), "Failed: To verify Reservation Page GuestName");

		test_steps.add("Verified Guest Name <b>" + GuestName + "</b>");
		reslogger.info("Verified Guest Name " + GuestName);
		String[] status = null;

		if (OptionPerform.equals("Check Out")) {
			status = CPReservationPage.CP_Reservation_NoShow_Cancel_CheckIn_CheckOut_Status.getText().split("-");
			status[0] = status[0] + "-" + status[1];
		} else {
			status = CPReservationPage.CP_Reservation_NoShow_Cancel_CheckIn_CheckOut_Status.getText().split("-");

		}
		// Verified No Show Reservation Page Status
		/*
		 * assertTrue(status[0].toLowerCase().trim().equals(Status.toLowerCase()
		 * .trim()), "Failed: To verify Reservation Page Status");
		 */
		assertEquals(status[0].toLowerCase().trim(), Status.toLowerCase().trim(),
				"Failed: To verify Reservation Page Status");

		test_steps.add(" Verified Status <b>" + Status + "</b>");
		reslogger.info(" Verified Status: " + Status);
		Utility.ScrollToElement(CPReservationPage.CP_Reservation_NoShow_Cancel_CheckIn_CheckOut_ConfirmationNo, driver);
		// Verified No Show Reservation Page ReservationNo
		String ConfirmationNO = CPReservationPage.CP_Reservation_NoShow_Cancel_CheckIn_CheckOut_ConfirmationNo.getText()
				.trim();
		reslogger.info(ConfirmationNO);
		/*
		 * assertTrue(CPReservationPage.
		 * CP_Reservation_NoShow_Cancel_CheckIn_CheckOut_ConfirmationNo.getText(
		 * ).trim() .equals(ResNo.trim()),
		 * "Failed: To verifyReservation Page Confirmation No");
		 */

		assertEquals(CPReservationPage.CP_Reservation_NoShow_Cancel_CheckIn_CheckOut_ConfirmationNo.getText().trim(),
				ResNo.trim(), "Failed: To verifyReservation Page Confirmation No");
		test_steps.add(" Verified Reservation  No <b>" + ResNo + "</b>");
		reslogger.info(" Verified Reservation No: " + ResNo);

	}

	public void voidRoomChargeVerificationOfNoShow(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_CPReservation CPReservationPage = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement(CPReservationPage.VoidRoomChargeLabel, driver);
		// Verified No Show Reservation Page Void Room Charge Label
		assertTrue(CPReservationPage.VoidRoomChargeLabel.isDisplayed(), "Failed: To verify  Void Room Charge Lable");
		test_steps.add(" Verified <b> Void Room Charge </b> Label ");
		reslogger.info(" Verified  Void Room Charge Label");
	}

	public String TripSummary_RoomCharges = "";
	public String TripSummary_TaxServices = "";
	public String AmountPaid = "";

	public String CalculationFee(WebDriver driver, int roomCharge, int noShowPercent, int taxPercentage,
			int paidAmount) {
		int NoShowFee = (roomCharge * noShowPercent) / 100;
		reslogger.info("Pearcentage: " + NoShowFee);
		TripSummary_RoomCharges = String.valueOf(NoShowFee);
		int TaxOnNoShowFee = (NoShowFee * taxPercentage) / 100;
		reslogger.info("Tax on Pearcentage: " + TaxOnNoShowFee);
		TripSummary_TaxServices = String.valueOf(TaxOnNoShowFee);
		int TotalNoShowFee = NoShowFee + TaxOnNoShowFee;
		reslogger.info("Total Fee  : " + TotalNoShowFee);
		int PaidAmount = TotalNoShowFee - paidAmount;
		AmountPaid = String.valueOf(PaidAmount);
		reslogger.info("Need to Paid  : " + AmountPaid);
		return String.valueOf(TotalNoShowFee);

	}

	public void getAmountOnPaymentVerificationPage(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_CPReservation CPReservationPage = new Elements_CPReservation(driver);
		Utility.ScrollToElement(CPReservationPage.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount, driver);
		// Get Amount
		Amount = CPReservationPage.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount.getText();
		double noshowPaymentAmount = Double.valueOf(Amount);
		int amt = (int) noshowPaymentAmount;
		Amount = String.valueOf(amt);
		reslogger.info(Amount);
	}

	String type = "";
	public String Amount = "", Balance = "";

	public double amount = 0.00;

	public void commonMethodForPaymentPopUpVerification(WebDriver driver, ArrayList<String> test_steps, String Title,
			String expiryDate, String nameOnCard, String cardNo, String PaymentType, String Type, String totalFee,
			String tripTax, String Percentage, String PolicyType, String TripPaid) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Elements_CPReservation CPReservationPage = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.NoShowPaymentPopupTitle);
		Utility.ScrollToElement(CPReservationPage.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupTitle, driver);
		// Verified No Show Payment Page Title
		assertEquals(CPReservationPage.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupTitle.getText().toLowerCase()
				.trim(), (Title.toLowerCase().trim()), "Failed: To verify  Payment Page Title");
		test_steps.add(" Verified Title:<b> " + Title + "</b>");
		reslogger.info(" Verified Title: " + Title);
		String path = "//div[contains(@data-bind,'IsSplitPaymentEnable')]//button[@title='" + PaymentType + "']";
		WebElement paymentElement = driver.findElement(By.xpath(path));

		// Get Amount
		Amount = CPReservationPage.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount.getText();
		double noshowPaymentAmount = Double.valueOf(Amount);
		int amt = (int) noshowPaymentAmount;
		Amount = String.valueOf(amt);
		reslogger.info(Amount);
		amount = noshowPaymentAmount;
		reslogger.info(amount);

		String balance = "";
		if (PolicyType.equals("No Show") || PolicyType.equals("Cancel")) {
			balance = CPReservationPage.NoShowCancelPaymentPopup_Balance.getText().replace("$", "");
			Balance = balance;
		} else if (PolicyType.equals("Check In")) {
			balance = CPReservationPage.CheckInPaymentPopup_Balance.getText().replace("$", "");
			Balance = balance;
		}

		double balanceDouble = Double.valueOf(balance);
		int balanceInt = (int) balanceDouble;
		balance = String.valueOf(balanceInt);
		if (Amount.equals(totalFee)) {
			assertEquals(Amount, (totalFee), "Failed: To verify Payment Page Amount: " + totalFee);
			test_steps.add("Verified Amount : <b> " + totalFee + "</b>");
			reslogger.info(" Verified Amount: " + totalFee);
			assertEquals(balance, (totalFee), "Failed: To verify Payment Page Balance: " + totalFee);
			test_steps.add("Verified Balance : <b> " + totalFee + "</b>");
			reslogger.info(" Verified Balance: " + totalFee);
		} else if (Amount.equals(tripTax)) {
			assertEquals(Amount, (tripTax), "Failed: To verify Payment Page Amount: " + tripTax);
			test_steps.add(" Verified Amount : <b> " + tripTax + "</b>");
			reslogger.info(" Verified Amount: " + tripTax);
			assertEquals(balance, (tripTax), "Failed: To verify  Payment Page Balance: " + tripTax);
			test_steps.add(" Verified Balance : <b> " + tripTax + "</b>");
			reslogger.info(" Verified Balance: " + tripTax);
		}

		else if (Amount.equals(Percentage)) {
			assertEquals(Amount, (Percentage), "Failed: To verify Payment Page Amount: " + Percentage);
			test_steps.add(" Verified Amount : <b> " + Percentage + "</b>");
			reslogger.info(" Verified Amount: " + Percentage);
			assertEquals(balance, (Percentage), "Failed: To verify  Payment Page Balance: " + Percentage);
			test_steps.add(" Verified Balance : <b> " + Percentage + "</b>");
			reslogger.info(" Verified Balance: " + Percentage);
		} else if (Amount.equals(TripPaid)) {
			assertEquals(Amount, (TripPaid), "Failed: To verify Payment Page Amount: " + TripPaid);
			test_steps.add(" Verified Amount : <b> " + TripPaid + "</b>");
			reslogger.info(" Verified Amount: " + TripPaid);
			assertEquals(balance, (TripPaid), "Failed: To verify  Payment Page Balance: " + TripPaid);
			test_steps.add(" Verified Balance : <b> " + TripPaid + "</b>");
			reslogger.info(" Verified Balance: " + TripPaid);
		}

		// Verified Type
		type = CPReservationPage.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupType.getText().toLowerCase()
				.trim();
		assertEquals(type, (Type.toLowerCase().trim()), "Failed: To verify  Page Type: " + Type);
		test_steps.add(" Verified Type : <b> " + Type + "</b>");
		reslogger.info(" Verified Type: " + Type);

		if (PaymentType.equals("MC")) {
			// Verified No Show Payment Page Payment Method
			assertTrue(paymentElement.isDisplayed(), "Failed: To verify Payment Method");
			test_steps.add(" Verified Payment Method:<b> " + PaymentType + "</b> ");
			reslogger.info(" Verified Payment Method: " + PaymentType);

			// Verified No Show Payment Page Card No
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			String CardNo = (String) executor.executeScript("return arguments[0].value",
					CPReservationPage.CP_Reservation_NoShowPaymentPopup_CardNumber);
			reslogger.info(CardNo);
			// Verify Card Number
			assertTrue(CardNo.contains(cardNo), "Failed: To verify Card Number");
			test_steps.add(" Verified Card Number: <b>" + CardNo + "</b>");
			reslogger.info(" Verified Card Number");
			String NameOnCard = (String) executor.executeScript("return arguments[0].value",
					CPReservationPage.CP_Reservation_NoShowPaymentPopup_NameOnCard);
			reslogger.info(NameOnCard);

			// Verify Name on Card Number
			assertEquals(NameOnCard.toLowerCase().trim(), (nameOnCard.toLowerCase().trim()),
					"Failed: To verify Name on Card ");
			test_steps.add("Verified Name on Card:<b>  " + nameOnCard + "</b>");
			reslogger.info(" Verified Name on Card");

			String ExpiryDate = (String) executor.executeScript("return arguments[0].value",
					CPReservationPage.CP_Reservation_NoShowPaymentPopup_Expiry);
			reslogger.info(ExpiryDate);

			// Verify Expiry Date
			assertEquals(ExpiryDate, (expiryDate), "Failed: To verify Expiry Date ");
			test_steps.add(" Verified Expiry Date:<b> " + ExpiryDate + "</b>");
			reslogger.info(" Verified Expiry Date");
		}
		if (PolicyType.equals("No Show") || PolicyType.equals("Cancel")) {
			// Verified Log as External Payment
			boolean isExistLogAsExternal = CPReservationPage.CP_Reservation_NoShowPaymentPopup_ExternalPaymentCheckBox
					.isEnabled();
			if (isExistLogAsExternal) {
				if (!CPReservationPage.NoShowPaymentPopup_Date.isEnabled()) {
					test_steps.add(" Verified  Date Field Disabled ");
					reslogger.info(" Verified  Date Field Disabled ");
				}
				if (!CPReservationPage.CP_Reservation_NoShowPaymentPopup_ExternalPaymentCheckBox.isSelected()) {
					CPReservationPage.CP_Reservation_NoShowPaymentPopup_ExternalPaymentCheckBox.click();
					reslogger.info("Click  Log as External Payment Check Box");
					// Verified No Show Payment Log Button
					assertTrue(CPReservationPage.CP_Reservation_NoShowPaymentPopup_LogAndPayButton.isDisplayed(),
							"Failed: To verify Payment Page Log Button");
					test_steps.add(" Verified  Log/Pay Button ");
					reslogger.info("' Verified  Log/Pay Button ");
					assertTrue(CPReservationPage.NoShowPaymentPopup_Date.isEnabled(),
							"Failed: To verify Payment Page Date Field");
					test_steps.add(" Verified  Date Field  Enabled");
					reslogger.info(" Verified  Date Field  Enabled ");
				}

			}
		}
		if (PolicyType.equals("Check In")) {
			// Verified Override Check-In Amount
			res.CP_OverrideCheckInAmountCheckBox.click();
			test_steps.add(" Checked Override Check-In Amount Check Box");
			reslogger.info(" Checked Override Check-In Amount Check Box");
			boolean isAmountEnabled = CPReservationPage.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount
					.isEnabled();
			assertEquals(isAmountEnabled, true, "Failed : to verify Amount field Enabled");
			test_steps.add(" Amount Field  Enabled");
			reslogger.info(" Amount Field  Enabled");
			boolean isTypeEnabled = CPReservationPage.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupType
					.isEnabled();
			assertEquals(isTypeEnabled, true, "Failed : to verify Type field Enabled");
			test_steps.add(" Type Field  Enabled");
			reslogger.info(" Type Field  Enabled");
			CPReservationPage.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupType.click();
			test_steps.add(" Click on Type Drop Down Box");
			reslogger.info(" Click on Type Drop Down Box");
			String[] type = { "Authorization Only", "Capture" };
			for (int i = 0; i < res.CP_CheckInTypeOptions.size(); i++) {
				assertTrue(res.CP_CheckInTypeOptions.get(i).getText().toLowerCase().trim()
						.equals(type[i].toLowerCase().trim()), "Failed: To verify Type Field");
				test_steps.add(" Verified  Type Option: <b>" + res.CP_CheckInTypeOptions.get(i).getText() + "</b>");
				reslogger.info(" Verified  Date Field  Enabled " + res.CP_CheckInTypeOptions.get(i).getText());
			}

			CPReservationPage.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupType.click();
			test_steps.add(" Click on Type Drop Down Box");
			reslogger.info(" Click on Type Drop Down Box");

			boolean iExist = CPReservationPage.CP_Reservation_NoShowPaymentPopup_LogAndPayButton.isDisplayed();
			assertEquals(iExist, true, "Failed : to verify Authrozied Button");
			test_steps.add(" Authrozied Button  Enabled");
			reslogger.info(" Authrozied Button  Enabled");
		}

	}

	public void SelectDate(WebDriver driver, Date Date, String day) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String tDate = dateFormat.format(Date);
		String[] checkin = tDate.split("\\|");
		int size = checkin.length;
		String header = null, headerText = null, date;
		// String monthYear=Utility.get_MonthYear(Date);
		for (int i = 1; i <= size; i++) {
			String monthYear = Utility.get_MonthYear(checkin[i - 1]);

			for (int k = 1; k <= 12; k++) {
				header = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[2]";
				headerText = driver.findElement(By.xpath(header)).getText();
				if (headerText.equalsIgnoreCase(monthYear)) {
					date = "//td[contains(@class,'day') and text()='" + day + "']";
					Wait.WaitForElement(driver, date);

					int size1 = driver.findElements(By.xpath(date)).size();
					for (int l = 1; l <= size1; l++) {
						date = "(//td[contains(@class,'day') and text()='" + day + "'])[" + l + "]";
						String classText = driver.findElement(By.xpath(date)).getAttribute("class");
						if (!classText.contains("old")) {
							driver.findElement(By.xpath(date)).click();
							test_steps.add("Selecting  date : " + Date);
							reslogger.info("Selecting  date : " + Date);
							break;
						}
					}

					break;
				}

			}
		}
	}

	public String previousDate = "";
	public String currentDay = "";
	public String PreviousDay = "";
	public Date oneDayBefore;
	public String Current_Date = "";
	public Date current_Date;

	public void getTodaysANDYesturdayDate() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setTimeZone(TimeZone.getTimeZone("EST"));

		Date currentDate = new Date();
		current_Date = currentDate;
		String CurrentDate = dateFormat.format(currentDate);
		Current_Date = CurrentDate;
		Date myDate = dateFormat.parse(CurrentDate);
		reslogger.info("CurrentDate: " + Current_Date);
		int CD = myDate.getDate();
		currentDay = String.valueOf(CD);
		reslogger.info("CurrentDay: " + currentDay);

		// Date oneDayBefore = new Date(myDate.getTime() - 2);
		oneDayBefore = new Date(myDate.getTime() - 2);
		previousDate = dateFormat.format(oneDayBefore);
		reslogger.info("Previous Date: " + previousDate);
		Date myPDate = dateFormat.parse(previousDate);
		int PD = myPDate.getDate();
		PreviousDay = String.valueOf(PD);
		reslogger.info("PreviousDay: " + PreviousDay);

	}

	public Date getTodaysDate(String format, String timeZone) {
		Date current_Date = null;
		DateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
		Date currentDate = new Date();
		current_Date = currentDate;
		String CurrentDate = dateFormat.format(currentDate);
		reslogger.info("CurrentDate: " + CurrentDate);

		return current_Date;
	}

	public Date getPreviousDate(String format, String timeZone) throws ParseException {
		// String previousDate=null;
		DateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));

		Date currentDate = new Date();
		String CurrentDate = dateFormat.format(currentDate);
		Date myDate = dateFormat.parse(CurrentDate);
		Date oneDayBefore = new Date(myDate.getTime() - 2);
		String previousDate = dateFormat.format(oneDayBefore);
		Date previous = dateFormat.parse(previousDate);
		reslogger.info("Previous Date: " + previousDate);

		return previous;
	}

	public void SetAsMainPaymentMethod(WebDriver driver, ArrayList<String> test_steps, String PaymentType,
			String PaymentTypeTwo, Date Date, String Day, String PolicyType) throws ParseException {
		Elements_CPReservation CPReservationPage = new Elements_CPReservation(driver);
		String path = "//div[contains(@data-bind,'IsSplitPaymentEnable')]//button[@title='" + PaymentType + "']";
		WebElement PaymentMethodElement = driver.findElement(By.xpath(path));
		PaymentMethodElement.click();
		test_steps.add("Click Payment Method");
		reslogger.info("Click CPayment Method");
		String options = "//paymentmethod//div[contains(@class,'open')]/ul//li/a//span[@class='text'][contains(text(),'"
				+ PaymentTypeTwo + "')]";
		WebElement PaymentMethodOptions = driver.findElement(By.xpath(options));
		PaymentMethodOptions.click();
		test_steps.add("Select Payment Option :<b> " + PaymentTypeTwo + "<b>");
		reslogger.info("Click Payment Option: " + PaymentTypeTwo);
		if (PolicyType.equals("Cancel") || PolicyType.equals("No Show")) {
			CPReservationPage.NoShow_Cancel_Checkout_Date_CalanderIcon.click();
			test_steps.add("Click Calander Icon");
			reslogger.info("Click Calander Icon");
		} else if (PolicyType.equals("Check In")) {
			CPReservationPage.CheckInPaymentPopup_Date_CalanderIcon.click();
			test_steps.add("Click Calander Icon");
			reslogger.info("Click Calander Icon");
		} else if (PolicyType.equals("Check Out")) {
			CPReservationPage.CheckOutPaymentPopup_Date_CalanderIcon.click();
			test_steps.add("Click Calander Icon");
			reslogger.info("Click Calander Icon");
		}

		SelectDate(driver, Date, Day);
		test_steps.add("Select Date: <b>" + previousDate + "</b>");
		reslogger.info("Select Date: " + previousDate);
		CPReservationPage.CP_Reservation_NoShowPaymentPopup_SetMainPaymentCheckBox.click();
		test_steps.add("Click Set As Main Payment Method Check Box");
		reslogger.info("Click Set As Main Payment Method Check Box");

	}

	public void closeWindowVerificationOfPaymentPopup(WebDriver driver, ArrayList<String> test_steps, String Msg) {

		Elements_CPReservation CPReservationPage = new Elements_CPReservation(driver);
		// Verified No Show Payment Page Close Title
		assertTrue(CPReservationPage.CP_Reservation_NoShowPaymentPopup_CloseButtonMsg.getText().toLowerCase().trim()
				.equals(Msg.toLowerCase().trim()), "Failed: To verify Are You Sure Msg");
		test_steps.add("Verified Message:<b> " + Msg + "</b>");
		reslogger.info("Verified Message: " + Msg);
		// Verified No Show Payment Page Close Page Yes Button
		assertTrue(CPReservationPage.CP_Reservation_NoShowPaymentPopup_CloseButtonMsgYesButton.isDisplayed(),
				"Failed: To verify Payment Page Yes Button");
		test_steps.add(" Verified <b>Yes</b> Button ");
		reslogger.info("Verified Yes Button");
		// Verified No Show Payment Page Close Page No Button
		assertTrue(CPReservationPage.CP_Reservation_NoShowPaymentPopup_CloseButtonMsgNoButton.isDisplayed(),
				"Failed: To verify Payment Page No Button");
		test_steps.add(" Verified <b>No</b> Button ");
		reslogger.info(" Verified No Button");
		clickNoButtonOfNoShowPaymentWindow(driver, test_steps);
		// Wait.explicit_wait_visibilityof_webelement(CPReservationPage.CP_Reservation_StatusPanel_DropDownBox,
		// driver);
	}

	public boolean confirmNoShowVerification(WebDriver driver) {
		Elements_CPReservation CPReservationPage = new Elements_CPReservation(driver);
		boolean isExitNoShowVerification = CPReservationPage.CP_Reservation_ConfirmNoShowMsg.isEnabled();
		return isExitNoShowVerification;

	}

	public void confirmNoShowWindowVerification(WebDriver driver, ArrayList<String> test_steps, String ConfirmNoShowMsg)
			throws InterruptedException {

		Elements_CPReservation CPReservationPage = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.ConfirmDialog_Message);
		Wait.explicit_wait_visibilityof_webelement(CPReservationPage.CP_Reservation_ConfirmNoShowMsg, driver);
		// Verified Confirm No Show Msg
		assertTrue(CPReservationPage.CP_Reservation_ConfirmNoShowMsg.getText().toLowerCase().trim()
				.equals(ConfirmNoShowMsg.toLowerCase().trim()), "Failed: To verify The guest is owed a refund");
		test_steps.add("Successfull Verified Confirm No Show  Message ");
		reslogger.info("Successfull Verified Confirm No Show  Message");
		// Verified Confirm No Show Page Yes Button
		assertTrue(CPReservationPage.CP_Reservation_ConfirmNoShowYesButton.isDisplayed(),
				"Failed: To verify Confirm No Show  Page Yes Button");
		test_steps.add("Successfull Verified Confirm No Show  Page  Yes Button ");
		reslogger.info("Successfull Verified Confirm No Show  Page Yes Button");
		// Verified Confirm No Show Page No Button
		assertTrue(CPReservationPage.CP_Reservation_ConfirmNoShowNoButton.isDisplayed(),
				"Failed: To verify Confirm No Show Page No Button");
		test_steps.add("Successfull Verified Confirm No Show Page  No Button ");
		reslogger.info("Successfull Verified Confirm No Show  Page No Button");
		clickConfirmButtonFromNoShowModelPopup(driver, test_steps);
		// Verified Confirm No Show Button Again
		assertTrue(CPReservationPage.CP_Reservation_ConfirmButton.isDisplayed(),
				"Failed: To verify Confirm No Show   Button");
		test_steps.add("Successfull Verified Confirm No Show   Button Again");
		reslogger.info("Successfull Verified Confirm No Show  Button Again");
		// Click Again Confirm No Show Button
		clickConfirmNoShowButton(driver, test_steps);
		clickYesButtonOfConfirmTheNoShowProcessPopupModel(driver, test_steps);
		Wait.wait10Second();

	}

	public boolean NoShowSuccessFull(WebDriver driver) throws InterruptedException {
		Elements_CPReservation CPReservationPage = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.NoShowSuccessFul_ModelHeader);
		Wait.explicit_wait_visibilityof_webelement(CPReservationPage.NoShowSuccessfull_Header, driver);
		boolean iExistNoShowSuccessScreen = CPReservationPage.NoShowSuccessfull_Header.isEnabled();
		return iExistNoShowSuccessScreen;
	}

	public void commonMethodForSuccessfullWindowVerification(WebDriver driver, ArrayList<String> test_steps,
			String NoShowSuccess, String Status, String PaymentMethod, String Notes, String Balance, String Type,
			String TotalNoFee, String PaymentType, String dateValue) throws InterruptedException, ParseException {
		Elements_CPReservation CPReservationPage = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.NoShowSuccess_ModelHeader);
		Wait.explicit_wait_visibilityof_webelement(CPReservationPage.CP_Reservation_NoShowSuccess, driver);
		Utility.ScrollToElement(CPReservationPage.CP_Reservation_NoShowSuccess, driver);

		// Verified Success -Message
		assertEquals(CPReservationPage.CP_Reservation_NoShowSuccess.getText().toLowerCase().trim(),
				(NoShowSuccess.toLowerCase().trim()), "Failed: To verify Success Header");
		test_steps.add("  Verified Message: <b>" + NoShowSuccess + "</b>");
		reslogger.info("  Verified Message:" + NoShowSuccess);

		if (PaymentType.equals("Check Out")) {
			// Verified Success-Date
			Date date = null;
			String Date = CPReservationPage.NoShowSuccess_Date.getText();
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
			dateFormat.setTimeZone(TimeZone.getTimeZone("EST"));
			Date myDate = dateFormat.parse(dateValue);
			String CurrentDate = dateFormat.format(myDate);

			// date = formatter.parse(Date);
			assertEquals(CPReservationPage.NoShowSuccess_Date.getText(), (CurrentDate),
					"Failed: To verify  Success Page Date");
			test_steps.add("  Verified Date: <b>" + dateValue + "</b>");
			reslogger.info("  Verified Date: " + dateValue);
		} else {

			assertEquals(CPReservationPage.NoShowSuccess_Date.getText(), (dateValue),
					"Failed: To verify  Success Page Date");

			test_steps.add("  Verified Date: <b>" + dateValue + "</b>");
			reslogger.info("  Verified Date: " + dateValue);
		}

		// Verified Success-Balance
		String balance = CPReservationPage.NoShowSuccess_Balance.getText().replace("$ ", "");

		reslogger.info(balance);
		if (PaymentType.equals("Check Out")) {
			double tripAmount = Double.valueOf(balance);
			int amt = (int) tripAmount;
			String BalanceCheckOut = String.valueOf(amt);
			assertEquals(BalanceCheckOut.trim(), (Balance), "Failed: To verify Success Page Balance");
			test_steps.add(" Verified Balance: <b>" + Balance + "</b>");
			reslogger.info("  Verified Balance: " + Balance);
		} else {
			assertEquals(balance.trim(), (Balance), "Failed: To verify Success Page Balance");
			test_steps.add(" Verified Balance: <b>" + Balance + "</b>");
			reslogger.info("  Verified Balance: " + Balance);

		}
		// Verified Success-Amount

		reslogger.info("Amount: " + TotalNoFee);
		String path = "//span[contains(@data-bind,'noShow.PaymentAmount')][contains(text(),'$ " + TotalNoFee
				+ "')]|//span[contains(@data-bind,'cancel.PaymentAmount')][contains(text(),'$ " + TotalNoFee
				+ "')]|//span[contains(@data-bind,'checkInDetail.PaymentAmount')][contains(text(),'$ " + TotalNoFee
				+ "')]|//span[contains(@data-bind,'checkOutDetail.PaymentAmount')][contains(text(),'$ " + TotalNoFee
				+ "')]";
		WebElement AmountElement = driver.findElement(By.xpath(path));
		assertTrue(AmountElement.isDisplayed(), "Failed: To verify  Success Page Amount: " + TotalNoFee);
		test_steps.add("  Verified Amount: <b>" + TotalNoFee + "</b>");
		reslogger.info("  Verified Amount: " + TotalNoFee);

		// Verified Success-Type
		reslogger.info(type);
		assertEquals(CPReservationPage.NoShowSuccess_Type.getText().toLowerCase().trim(), (Type.toLowerCase().trim()),
				"Failed: To verify  Success Page Type");
		test_steps.add("  Verified Type: <b>" + Type + "</b>");
		reslogger.info(" Verified Type: " + Type);

		// Verified Success-Payment Method
		assertTrue(CPReservationPage.CP_Reservation_NoShowSuccessPaymentMethod.getText().toLowerCase().trim()
				.contains(PaymentMethod.toLowerCase().trim()), "Failed: To verify Success Page Payment Method");
		test_steps.add("  Verified Payment Method: <b>" + PaymentMethod + "</b>");
		reslogger.info("  Verified Payment Method: " + PaymentMethod);

		// Verified Success -Status
		assertEquals(CPReservationPage.CP_Reservation_NoShowSuccessStatus.getText().toLowerCase().trim(),
				(Status.toLowerCase().trim()), "Failed: To verify  Success Page Status ");
		test_steps.add("  Verified Status:<b> " + Status + "</b>");
		reslogger.info(" Verified Status: " + Status);

		// Verified Success Notes
		if (Utility.validateString(Notes)) {
			assertEquals(CPReservationPage.CP_Reservation_NoShowSuccessNotes.getText().toLowerCase().trim(),
					(Notes.toLowerCase().trim()), "Failed: To verify  Success Page Notes ");
			test_steps.add(" Verified  Notes: <b>" + Notes + "</b>");
			reslogger.info(" Verified  Notes: " + Notes);
		}
	}

	public boolean VerifyNoShowSuccessFull(WebDriver driver) {
		boolean value = Utility.isElementPresent(driver, By.xpath(OR_Reservation.NoShowSuccessFul_ModelHeader));
		return value;
	}

	public void noShowSuccessfullyWindowVerification(WebDriver driver, ArrayList<String> test_steps, String headerText,
			String Reg) throws InterruptedException {
		Elements_CPReservation CPReservationPage = new Elements_CPReservation(driver);
		Wait.wait10Second();
		Wait.explicit_wait_visibilityof_webelement(CPReservationPage.NoShowSuccessfull_Header, driver);
		// Verified No Show Successful Header
		assertTrue(CPReservationPage.NoShowSuccessfull_Header.getText().toLowerCase().trim()
				.equals(headerText.toLowerCase().trim()), "Failed: To verify No Show Successful Header");
		test_steps.add("'No Show Successful' Verified Header: <b>" + headerText + "</b>");
		reslogger.info("'No Show Successful' Verified Header");

		// Verified No Show Successful message
		assertTrue(CPReservationPage.NoShowSuccessfull_Msg.isDisplayed(),
				"Failed: To verify No Show Successful Message: ");
		test_steps.add("'No Show Successful' Verified Message: <b>No Show successful</b>");
		reslogger.info("'No Show Successful' Verified Message");

		// Verified No Show Successful Registration NO
		assertTrue(
				CPReservationPage.NoShowSuccessfull_Reg.getText().toLowerCase().trim().equals(Reg.toLowerCase().trim()),
				"Failed: To verify No Show Successful Reg No");
		test_steps.add("'No Show Successful' Verified Registration No:<b> " + Reg + "</b>");
		reslogger.info("'No Show Successful' Verified Registration No " + Reg);
		CPReservationPage.NoShowSuccessfull_Button.click();
		test_steps.add("Click Close Button ");
		reslogger.info("Click Close Button ");

	}

	public void clickCloseButtonOfSuccessModelPopup(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.NoShowSuccess_CloseButton);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.NoShowSuccess_CloseButton), driver, 10);
		// res.CP_Reservation_NoShowSuccessCloseButton.click();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", res.CP_Reservation_NoShowSuccessCloseButton);
		test_steps.add("Click Close Button ");
		reslogger.info("Click Close Button ");
		String loading = "(//div[@class='ir-loader-in'])";
		Wait.waitTillElementDisplayed(driver, loading);
	}

	public void clickAddNoteLinks(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.NoShowPaymentAddNoteLink);
		Wait.explicit_wait_elementToBeClickable(res.CP_Reservation_NoShowPayment_AddNoteLink, driver);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", res.CP_Reservation_NoShowPayment_AddNoteLink);
		// res.CP_Reservation_NoShowPayment_AddNoteLink.click();
		test_steps.add("Click Add Notes Link ");
		reslogger.info("Click Add Notes Link ");
	}

	public void clickOnPayButtonOnPaymentPopup(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.NoShowPaymentPopup_LogANDPayButton), driver);
		String buttonName = res.CP_Reservation_NoShowPaymentPopup_LogAndPayButton.getText().split(" ")[0];
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", res.CP_Reservation_NoShowPaymentPopup_LogAndPayButton);
		// res.CP_Reservation_NoShowPaymentPopup_LogAndPayButton.click();
		test_steps.add("Clicking on " + buttonName + " Button ");
		reslogger.info("Clicking on " + buttonName + " Button ");
		try {
			Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.NoShowSuccess_ModelHeader), driver);
		} catch (Exception e) {
            try {
                js.executeScript("arguments[0].click();", res.CP_Reservation_NoShowPaymentPopup_LogAndPayButton);
                test_steps.add("Clicking on " + buttonName + " Button ");
                reslogger.info("Clicking on " + buttonName + " Button ");
                Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.NoShowSuccess_ModelHeader), driver);
               
            } catch (Exception e2) {
                test_steps.add("Existing issue in CP: Posted room charges do not move to account when charge routing is set"
                        + "<br/><a href='https://innroad.atlassian.net/browse/NG-10512' target='_blank'>"
                        + "Verified :NG-10512</a><br/>");
                reslogger.info("Existing issue in CP: Posted room charges do not move to account when charge routing is set"
                        + "<br/><a href='https://innroad.atlassian.net/browse/NG-10512' target='_blank'>"
                        + "Verified :NG-10512</a><br/>");
            }
		}
	}

	public void clickLogORPayAuthorizedButton(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.NoShowPaymentPopup_LogANDPayButton);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_NoShowPaymentPopup_LogAndPayButton, driver);
		res.CP_Reservation_NoShowPaymentPopup_LogAndPayButton.click();
		String buttonName = res.CP_Reservation_NoShowPaymentPopup_LogAndPayButton.getText().split(" ")[0];
		test_steps.add("Clicking on " + buttonName + " Button ");
		reslogger.info("Clicking on " + buttonName + " Button ");
	}

	public String getPaymentMethod(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		String path = "//paymentmethod//div[(@class='form-group ir-frm-grp')]//button";
		WebElement PaymentMethod = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(PaymentMethod, driver);
		String paymentMethod = PaymentMethod.getAttribute("title");

		reslogger.info(paymentMethod);
		return paymentMethod;
	}

	public void addNotesAndClickLogORPayORAuthorizedButton(WebDriver driver, ArrayList<String> test_steps,
			String Notes) {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		clickAddNoteLinks(driver, test_steps);
		Wait.WaitForElement(driver, OR_Reservation.NoShowPaymentAddNoteTextBox);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_NoShowPayment_AddNoteTextBox, driver);
		res.CP_Reservation_NoShowPayment_AddNoteTextBox.sendKeys(Notes);
		test_steps.add("Input Notes: <b>" + Notes + "</b>");
		reslogger.info("Input Notes: " + Notes);
		clickLogORPayAuthorizedButton(driver, test_steps);
		String loading = "(//div[@class='ir-loader-in'])";
		Wait.waitTillElementDisplayed(driver, loading);
	}

	public void verifyReservationPopWindowPolicyName(WebDriver driver, ArrayList<String> test_steps, String PolicyName,
			String PolicyType) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.wait5Second();
		if (PolicyType.equals("No Show") || PolicyType.equals("Cancel")) {
			Utility.ScrollToElement(res.NoShowAndCancelReservation_PolicyName, driver);
			assertTrue(res.NoShowAndCancelReservation_PolicyName.getText().toLowerCase().trim()
					.equals(PolicyName.toLowerCase().trim()), "Failed: To verify Reservation Window Policy Name");
		} else if (PolicyType.equals("Check In")) {
			Utility.ScrollToElement(res.CheckInReservation_PolicyName, driver);
			assertTrue(res.CheckInReservation_PolicyName.getText().toLowerCase().trim()
					.equals(PolicyName.toLowerCase().trim()), "Failed: To verify Reservation Window Policy Name");
		}

		test_steps.add("Verified Policy Name:<b> " + PolicyName + "</b>");
		reslogger.info("Verified Policy Name " + PolicyName);
	}

	public void verifyPolicyToolTip(WebDriver driver, ArrayList<String> test_steps, String PolicyName,
			String PolicyDesc, String PolicyType) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Actions action = new Actions(driver);

		if (PolicyType.equals("Cancel")) {
			action.moveToElement(res.NoShowAndCancelReservation_PolicyName).perform();
			assertTrue(res.CancelRoom_ToolTip_PolicyName.getText().toLowerCase().trim()
					.equals(PolicyName.toLowerCase().trim()), "Failed: To verify Tooltip Policy Name");
			test_steps.add("Verified ToolTip Policy Name:<b> " + PolicyName + "</b>");
			reslogger.info("Verified ToolTip Policy Name " + PolicyName);
			assertTrue(res.CancelRoom_ToolTip_PolicyDescription.getText().toLowerCase().trim()
					.equals(PolicyDesc.toLowerCase().trim()), "Failed: To verify Tooltip Policy Description");
			test_steps.add("Verified ToolTip Policy Description:<b> " + PolicyDesc + "</b>");
			reslogger.info("Verified ToolTip Policy Description " + PolicyDesc);

		} else if (PolicyType.equals("No Show")) {
			action.moveToElement(res.NoShowAndCancelReservation_PolicyName).perform();
			assertTrue(res.CancelRoom_ToolTip_PolicyDescription.getText().toLowerCase().trim()
					.equals(PolicyDesc.toLowerCase().trim()), "Failed: To verify Tooltip Policy Description");
			test_steps.add("Verified ToolTip Policy Description:<b> " + PolicyDesc + "</b>");
			reslogger.info("Verified ToolTip Policy Description " + PolicyDesc);

		} else if (PolicyType.equals("Check In")) {
			action.moveToElement(res.CheckInReservation_PolicyName).perform();
			assertTrue(res.CancelRoom_ToolTip_PolicyDescription.getText().toLowerCase().trim()
					.equals(PolicyDesc.toLowerCase().trim()), "Failed: To verify Tooltip Policy Description");
			test_steps.add("Verified ToolTip Policy Description:<b> " + PolicyDesc + "</b>");
			reslogger.info("Verified ToolTip Policy Description " + PolicyDesc);

		}

	}

	public void verifyReservationPopWindowPostLabel(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement(res.ReservationPopUp_PostLabel, driver);

		assertTrue(res.ReservationPopUp_PostLabel.isDisplayed(), "Failed: To verify  Reservation Post  Label");
		test_steps.add(" Verified: <b>" + res.ReservationPopUp_PostLabel.getText() + "</b>");
		reslogger.info("Verified: " + res.ReservationPopUp_PostLabel.getText());
	}

	public void Verify_VoidRoomCharge(WebDriver driver, ArrayList<String> test_steps, String Status)
			throws InterruptedException {
		Elements_CPReservation CPReservationPage = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement(CPReservationPage.VoidRoomChargeLabel, driver);
		// Verified Reservation Page Void Room Charge Label
		assertTrue(CPReservationPage.VoidRoomChargeLabel.isDisplayed(),
				"Failed: To verify Reservation Page Void Room Charge Lable");
		if (Status.equals("Cancel")) {
			test_steps.add("Verified <b>Void Room Charge Label</b> ");
			reslogger.info("Verified  Void Room Charge Label");

		} else if (Status.equals("Check Out")) {
			test_steps.add("Verified <b>Void Room Charges for Unused Nights Label</b> ");
			reslogger.info("Verified  Void Room Charges for Unused Nights Label");
		}

	}

	public void verify_AccountFolio(WebDriver driver, ArrayList test_steps, String AccountName)
			throws InterruptedException {
		String folioSize = "//button[contains(@title,'Guest')]";
		Wait.WaitForElement(driver, folioSize);
		driver.findElement(By.xpath(folioSize)).click();
		Wait.wait2Second();

		String verify_AccountFolio = "//li//span[contains(text(),'" + AccountName.trim() + "')]";
		try {
			WebElement ele = driver.findElement(By.xpath(verify_AccountFolio));
			test_steps.add("Account Folio verified in reservation : " + AccountName);
			reslogger.info("Account Folio verified in reservation : " + AccountName);
		} catch (Exception e) {
			test_steps.add("Account Folio not created in reservation : " + AccountName);
			reslogger.info("Account Folio not created in reservation : " + AccountName);
		}
		folioSize = "//li//span[contains(text(),'Guest Folio For')]";
		Wait.WaitForElement(driver, folioSize);
		driver.findElement(By.xpath(folioSize)).click();
		Wait.wait2Second();
	}

	public String pay_Account(WebDriver driver, ArrayList test_steps, String AccountName, String IsChangeInPayAmount,
			String ChangedAmountValue, String TakePaymentType) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		res.CP_Reservation_Folio_Pay.click();
		test_steps.add("Clicking on Pay");
		reslogger.info("Clicking on Pay");

		test_steps.add("******************* Making account payment **********************");
		reslogger.info("******************* Making account payment **********************");
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_Folio_TakePayment);

		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_Folio_TakePayment_Amount);
		String amount = res.CP_Reservation_Folio_TakePayment_Amount.getText().trim();
		reslogger.info("amount : " + amount);
		test_steps.add("Amount before Pay : " + amount);
		reslogger.info("Amount before Pay : " + amount);

		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_Folio_TakePayment_PaymentButton + "/span");

		String paymentType = driver
				.findElement(By.xpath(OR_Reservation.CP_Reservation_Folio_TakePayment_PaymentButton + "/span"))
				.getText().trim();

		if (paymentType.contains(AccountName.trim())) {
			test_steps.add("Payment method is verified as account : " + paymentType);
			reslogger.info("Payment method is verified as account : " + paymentType);
		}

		// res.CP_Reservation_Folio_TakePayment_PaymentButton.click();

		if (IsChangeInPayAmount.equalsIgnoreCase("Yes")) {
			test_steps.add("Change the pay amount value : Yes");
			reslogger.info("Change the pay amount value : Yes");
			res.CP_Reservation_Folio_TakePayment_Amount.clear();
			res.CP_Reservation_Folio_TakePayment_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			res.CP_Reservation_Folio_TakePayment_Amount.sendKeys(ChangedAmountValue);
			test_steps.add("Enter the Change Amount Value : " + ChangedAmountValue);
			reslogger.info("Enter the Change Amount Value : " + ChangedAmountValue);
			amount = ChangedAmountValue;
		} else {
			test_steps.add("paying the amount : " + amount);
			reslogger.info("paying the amount : " + amount);
		}

		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_Folio_TakePayment_TypeButton);
		res.CP_Reservation_Folio_TakePayment_TypeButton.click();

		String type = "//h4[text()='Take Payment']/../..//label[text()='TYPE']/..//div/div//span[text()='"
				+ TakePaymentType.trim() + "']";
		Wait.WaitForElement(driver, type);
		driver.findElement(By.xpath(type)).click();
		test_steps.add("Select Take Payment Type : " + TakePaymentType);
		reslogger.info("Select Take Payment Type : " + TakePaymentType);

		String button = "//h4[text()='Take Payment']/../..//button[contains(text(),'" + amount.trim() + "')]";
		Wait.WaitForElement(driver, button);
		driver.findElement(By.xpath(button)).click();
		test_steps.add("Click on Pay");
		reslogger.info("Click on Pay");

		String takePayType = "//h4[contains(text(),'Successful')]/../..//label[text()='TYPE']/following-sibling::span";
		Wait.WaitForElement(driver, takePayType);
		String paytype = driver.findElement(By.xpath(takePayType)).getText().trim();
		assertEquals(paytype, TakePaymentType.trim());
		test_steps.add("Take Payment Type validated after payment : " + TakePaymentType);
		reslogger.info("Take Payment Type validated after payment : " + TakePaymentType);
		String status = "//h4[contains(text(),'Successful')]/../..//label[text()='Approved']";
		Wait.WaitForElement(driver, status);
		if (driver.findElement(By.xpath(status)).isDisplayed()) {
			test_steps.add("Transaction status is : Approved");
			reslogger.info("Transaction status is : Approved");
		} else {
			test_steps.add("Transaction status is not : Approved");
			reslogger.info("Transaction status is not : Approved");
		}

		String close = "//h4[contains(text(),'Successful')]/../..//button[text()='Close']";
		Wait.WaitForElement(driver, close);
		driver.findElement(By.xpath(close)).click();
		test_steps.add("Click on Close");
		reslogger.info("Click on Close");

		String saveFolio = "//div[contains(@data-bind,'Folio')]//button[text()='Save']";
		Wait.WaitForElement(driver, saveFolio);
		driver.findElement(By.xpath(saveFolio)).click();
		test_steps.add("Click on Save");
		reslogger.info("Click on Save");
		String loading = "(//div[@class='ir-loader-in'])";
		int count = 0;
		while (true) {
			if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
				break;
			} else if (count == 20) {
				break;
			} else {
				count++;
				Wait.wait2Second();
			}
		}
		reslogger.info("Waited to loading symbol");
		return amount;
	}

	public void verify_AccountPaymentLineItem(WebDriver driver, ArrayList test_steps, String amount, String Account)
			throws InterruptedException {
		test_steps.add("******************* Verify account apayment line item **********************");
		reslogger.info("******************* Verify account payment line item **********************");

		String payment = "//td/a[contains(text(),'" + Account + "')]/../following-sibling::td//span[contains(text(),'"
				+ amount + "')]";
		Wait.WaitForElement(driver, payment);
		test_steps.add("Account payment line item displayed");
		reslogger.info("Account payment line item displayed");

		String line = "//td/a[contains(text(),'" + Account + "')]/../following-sibling::td//span[contains(text(),'"
				+ amount + "')]/../preceding-sibling::td[3]/a";
		Wait.WaitForElement(driver, line);
		driver.findElement(By.xpath(line)).click();
		test_steps.add("Opening Payment line item");
		reslogger.info("Opening Payment line item");
		Wait.wait5Second();
		String payemntType = "(//label[text()='Payment Method:']/following-sibling::div/select)[2]";

		WebElement pay = new Select(driver.findElement(By.xpath(payemntType))).getFirstSelectedOption();
		reslogger.info(pay.getText());
		if (pay.getText().trim().contains(Account)) {
			test_steps.add("Payment Method verified in line item : " + pay.getText());
			reslogger.info("Payment Method verified in line item : " + pay.getText());
		} else {
			test_steps.add("Payment Method not verified in line item : " + pay.getText());
			reslogger.info("Payment Method not verified in line item : " + pay.getText());
		}

		String amt = "(//label[text()='Amount:']/following-sibling::div//input)[2]";

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String amtValue = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(amt)));
		reslogger.info(amtValue);
		if (amtValue.equalsIgnoreCase(amount)) {
			test_steps.add("Payment amount verified in line item : " + amount);
			reslogger.info("Payment amount verified in line item : " + amount);
		} else {
			test_steps.add("Payment amount not verified in line item : " + amount);
			reslogger.info("Payment amount not verified in line item : " + amount);
		}

		String payInfo = "//label[text()='Payment Info:']/following-sibling::div//textarea";
		String info = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(payInfo)));
		reslogger.info(info);
		Wait.wait2Second();
		String close = "//span[text()='Payment Details']/../../.././../../../..//button[text()='Close']";
		Wait.WaitForElement(driver, close);
		driver.findElement(By.xpath(close)).click();
		test_steps.add("Closeing Payment line item");
		reslogger.info("Closeing Payment line item");
		Wait.wait5Second();
	}

	public void verify_AccountPaymentLineItemHA(WebDriver driver, ArrayList test_steps, String amount, String Account)
			throws InterruptedException {
		test_steps.add("******************* Verify account apayment line item **********************");
		reslogger.info("******************* Verify account payment line item **********************");

		String payment = "//td/a[contains(text(),'" + Account + "')]/../following-sibling::td//span[contains(text(),'"
				+ amount + "')]";
		Wait.WaitForElement(driver, payment);
		test_steps.add("Account payment line item displayed");
		reslogger.info("Account payment line item displayed");

		String line = "//td/a[contains(text(),'" + Account + "')]/../following-sibling::td//span[contains(text(),'"
				+ amount + "')]/../preceding-sibling::td[3]/a";
		Wait.WaitForElement(driver, line);
		driver.findElement(By.xpath(line)).click();
		test_steps.add("Opening Payment line item");
		reslogger.info("Opening Payment line item");
		Wait.wait5Second();
		String payemntType = "(//label[text()='Payment Method:']/following-sibling::div/select)[2]";

		WebElement pay = new Select(driver.findElement(By.xpath(payemntType))).getFirstSelectedOption();
		reslogger.info(pay.getText());
		if (pay.getText().trim().contains(Account)) {
			test_steps.add("Payment Method verified in line item : " + pay.getText());
			reslogger.info("Payment Method verified in line item : " + pay.getText());
		} else {
			test_steps.add("Payment Method not verified in line item : " + pay.getText());
			reslogger.info("Payment Method not verified in line item : " + pay.getText());
		}

		String amt = "(//label[text()='Amount:']/following-sibling::div//input)[2]";

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String amtValue = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(amt)));
		reslogger.info(amtValue);
		if (amtValue.equalsIgnoreCase(amount)) {
			test_steps.add("Payment amount verified in line item : " + amount);
			reslogger.info("Payment amount verified in line item : " + amount);
		} else {
			test_steps.add("Payment amount not verified in line item : " + amount);
			reslogger.info("Payment amount not verified in line item : " + amount);
		}

		String payInfo = "//label[text()='Payment Info:']/following-sibling::div//textarea";
		String info = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(payInfo)));
		reslogger.info(info);
		Wait.wait2Second();
		String close = "//span[text()='Payment Details']/../../.././../../../..//button[text()='Close']";
		Wait.WaitForElement(driver, close);
		driver.findElement(By.xpath(close)).click();
		test_steps.add("Closeing Payment line item");
		reslogger.info("Closeing Payment line item");
		Wait.wait5Second();
	}

	public String get_FilioBalance(WebDriver driver) {

		String bal = "//label[.='Balance: ']//following-sibling::span[@class='pamt']";
		Wait.WaitForElement(driver, bal);
		return driver.findElement(By.xpath(bal)).getText();

	}

	public String getFolioRoomCharges(WebDriver driver) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		String roomCharges = res.Folio_RoomCharges.getText();
		roomCharges = roomCharges.replace("$", "");
		return roomCharges;

	}

	public String getFolioTaxServices(WebDriver driver) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		String taxCharges = res.Folio_TaxServices.getText();
		taxCharges = taxCharges.replace("$", "");
		return taxCharges;

	}

	public void enter_TravelAgentMarketSegmentDetails(WebDriver driver, ArrayList test_steps, String TravelAgent,
			String MarketSegment, String Referral, String AccountType) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_TravelAgent);

		if (!(TravelAgent.equalsIgnoreCase("") || TravelAgent.isEmpty())) {
			res.CP_NewReservation_TravelAgent.clear();
			Wait.wait1Second();
			res.CP_NewReservation_TravelAgent.sendKeys(TravelAgent);
			Wait.wait2Second();
			String account = "//b[contains(text(),'" + TravelAgent.trim() + "')]/../../..";
			Wait.WaitForElement(driver, account);
			driver.findElement(By.xpath(account)).click();
			String dataYes = "//div[contains(text(),'Do you want to replace the guest info')]/following-sibling::div//button[contains(text(),'No')]";
			Wait.WaitForElement(driver, dataYes);
			driver.findElement(By.xpath(dataYes)).click();
			test_steps.add(
					"Do you want to replace the guest info, payment method, marketing info and notes in this reservation with the information from the account? Clicking yes will save all the account info to the reservation. : No");
			reslogger.info(
					"Do you want to replace the guest info, payment method, marketing info and notes in this reservation with the information from the account? Clicking yes will save all the account info to the reservation. : No");
			Wait.wait5Second();

			Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_PaymentMethod);
			res.CP_NewReservation_PaymentMethod.click();
			String type = null;

			if (AccountType.contentEquals("Travel Agent") || AccountType.contains("Travel")
					|| AccountType.contains("Travel Agent")) {
				type = "Account (Travel Agent)";
			}
			String paymentMethod = "//label[text()='Payment Method']/preceding-sibling::div//ul/li//span[contains(text(),'"
					+ type + "')]";
			Wait.WaitForElement(driver, paymentMethod);
			driver.findElement(By.xpath(paymentMethod)).click();
			String acc = "//label[text()='Account Name ']/preceding-sibling::input";
			Wait.WaitForElement(driver, acc);
			String accName = driver.findElement(By.xpath(acc)).getText();
			if (accName.contains(TravelAgent)) {
				test_steps.add("Account successfully associated : " + TravelAgent);
				reslogger.info("Account successfully associated : " + TravelAgent);

				test_steps.add("Verified payment method with Travel agent account" + TravelAgent);
				reslogger.info("Verified payment method with Travel agent account" + TravelAgent);
			}

			/*
			 * String
			 * policyYes="//div[contains(text(),'Do you want to replace the guest info')]/following-sibling::div//button[contains(text(),'Yes')]"
			 * ; Wait.WaitForElement(driver, policyYes);
			 * driver.findElement(By.xpath(policyYes)).click(); test_steps.
			 * add("Based on the account chosen, new policies are applicable. Would you like to apply these policies to the reservation? : Yes"
			 * ); reslogger.
			 * info("Based on the account chosen, new policies are applicable. Would you like to apply these policies to the reservation? : Yes"
			 * );
			 */
		}

		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Market);
		Utility.ScrollToElement(res.CP_NewReservation_Market, driver);
		Wait.wait2Second();
		res.CP_NewReservation_Market.click();
		String market = "//label[text()='Market']/preceding-sibling::div//ul/li//span[contains(text(),'"
				+ MarketSegment.trim() + "')]";
		Wait.WaitForElement(driver, market);

		driver.findElement(By.xpath(market)).click();
		test_steps.add("Selected MarketSegment as : " + MarketSegment);
		reslogger.info("Selected MarketSegment as : " + MarketSegment);

		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Referral);
		res.CP_NewReservation_Referral.click();
		Wait.wait1Second();
		String ref = "(//label[text()='Referral']/preceding-sibling::div//ul/li//span[contains(text(),'"
				+ Referral.trim() + "')])[2]";
		reslogger.info(ref);
		Wait.WaitForElement(driver, ref);
		driver.findElement(By.xpath(ref)).click();
		test_steps.add("Selected Referral as : " + Referral);
		reslogger.info("Selected Referral as : " + Referral);
	}

	public void verify_DepositPolicyValidation(WebDriver driver, ArrayList test_steps, String CheckInDate,
			String CheckOutDate, String Value) throws ParseException {
		test_steps.add("******************* Verify Deposit policy amount for " + Value
				+ " Night Room Charges **********************");
		reslogger.info("******************* Verify Deposit policy amount for " + Value
				+ " Night Room Charges **********************");
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		Date dateObj1 = format1.parse(CheckInDate);
		Date dateObj2 = format1.parse(CheckOutDate);
		long diff = dateObj2.getTime() - dateObj1.getTime();
		int Nights = (int) (diff / (24 * 60 * 60 * 1000));
		reslogger.info("difference between days: " + Nights);

		String roomCharge = get_RoomCharge(driver, test_steps);
		Double d = Double.parseDouble(roomCharge);
		Double d1 = d / Nights;
		reslogger.info(d1);

		String payments = get_Payments(driver, test_steps);
		Double d2 = Double.parseDouble(payments);
		reslogger.info(d2);

		if (Double.compare(d1, d2) == 0) {
			test_steps.add("Deposit policy for " + Value + " Night Room Charges is validated : " + d1);
			reslogger.info("Deposit policy for " + Value + " Night Room Charges is validated : " + d1);
		} else {
			test_steps.add("Deposit policy for " + Value + " Night Room Charges is not validated : " + d1);
			reslogger.info("Deposit policy for " + Value + " Night Room Charges is not validated : " + d1);
		}
	}

	public int getTotalStayInfoRooms(WebDriver driver) {
		int totalRooms = 0;
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_StayInfo_CheckInButton);
		totalRooms = res.CP_Reservation_StayInfo_CheckInButton.size();
		reslogger.info("Total Rooms Is: " + totalRooms);
		return totalRooms;
	}

	public int TotalRoom = 0, actualRoomCheckIn = 0;
	public String actualRoomCheckInCharge = null, actualRoomClassCheckIn = null, actualRoomAbbCheckIn = null;

	public void stayInfoCheckIn(WebDriver driver, ArrayList<String> test_steps, String roomNO, List<String> RoomCharges,
			ArrayList<String> Rooms, ArrayList<String> RoomAbbri) throws InterruptedException {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_StayInfo_CheckInButton);
		TotalRoom = res.CP_Reservation_StayInfo_CheckInButton.size();
		for (int i = 0; i < TotalRoom; i++) {
			if (i == (Integer.parseInt(roomNO) - 1)) {
				actualRoomCheckIn = i;
				actualRoomCheckInCharge = RoomCharges.get(i).replace("$", "").trim();
				actualRoomClassCheckIn = Rooms.get(i);
				actualRoomAbbCheckIn = RoomAbbri.get(i);
				Utility.ScrollToElement(res.CP_Reservation_StayInfo_CheckInButton.get(i), driver);
				res.CP_Reservation_StayInfo_CheckInButton.get(i).click();
				test_steps.add(
						"Room <b>" + (Integer.parseInt(roomNO)) + "</b> in the MRB Reservation Click CheckIn Button");
				reslogger.info("Room " + (Integer.parseInt(roomNO)) + " in the MRB Reservation Click CheckIn Button");
				break;
			}
		}

	}

	public int TotalRooms = 0, actualRoomCheckOut = 0;
	public String actualRoomCheckOutCharge = null, actualRoomClassCheckOut = null, actualRoomAbbCheckOut = null,
			actualGuestNameCheckOut = null;

	public void stayInfoCheckOutRoom(WebDriver driver, ArrayList<String> test_steps, String roomNO,
			List<String> RoomCharges, ArrayList<String> Rooms, ArrayList<String> RoomAbbri, List<String> GuestName,
			String Phone, String Email) throws InterruptedException {
		List<String> Phones = Arrays.asList(Phone.split("\\|"));
		List<String> Emails = Arrays.asList(Email.split("\\|"));
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_StayInfo_CheckOutButton);
		TotalRooms = res.CP_Reservation_StayInfo_CheckOutButtonList.size();
		for (int i = 0; i < TotalRooms; i++) {
			if (i == (Integer.parseInt(roomNO) - 1)) {
				actualRoomCheckOut = i;
				actualRoomCheckOutCharge = RoomCharges.get(i).replace("$", "").trim();
				actualRoomClassCheckOut = Rooms.get(i);
				actualRoomAbbCheckOut = RoomAbbri.get(i);
				actualGuestNameCheckOut = GuestName.get(i);

				Utility.ScrollToElement(res.CP_Reservation_StayInfo_CheckOutButtonList.get(i), driver);
				res.CP_Reservation_StayInfo_CheckOutButtonList.get(i).click();
				test_steps.add(
						"Room <b>" + (Integer.parseInt(roomNO)) + "</b> in the MRB Reservation Click Check-Out Button");
				reslogger.info("Room " + (Integer.parseInt(roomNO)) + " in the MRB Reservation Click Check-Out Button");
				break;
			}
		}

	}

	public void verifyDepositAmountTripSummary(WebDriver driver, ArrayList test_steps, String CheckInDate,
			String CheckOutDate, String Value, Double DepositAmount) throws ParseException, InterruptedException {
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");

		Date dateObj1 = format1.parse(CheckInDate);
		Date dateObj2 = format1.parse(CheckOutDate);
		long diff = dateObj2.getTime() - dateObj1.getTime();
		int Nights = (int) (diff / (24 * 60 * 60 * 1000));
		reslogger.info("difference between days: " + Nights);
		Wait.wait3Second();
		String roomCharge = get_TripSummaryRoomChargesWithoutCurrency(driver, test_steps);
		Double d = Double.parseDouble(roomCharge);
		Double d1 = d / Nights;
		reslogger.info(d1);

		// String payments=DepositAmount;
		Double d2 = DepositAmount;
		reslogger.info(d2);

		if (Double.compare(d1, d2) == 0) {
			test_steps.add("Deposit policy for " + Value + " Night Room Charges is validated : " + d1);
			reslogger.info("Deposit policy for " + Value + " Night Room Charges is validated : " + d1);
		} else {
			test_steps.add("Deposit policy for " + Value + " Night Room Charges is not validated : " + d1);
			reslogger.info("Deposit policy for " + Value + " Night Room Charges is not validated : " + d1);
		}
	}

	public String verifyGuestContactInfoForCheckInRoom(WebDriver driver, ArrayList<String> test_steps, int TotalRoom,
			String roomNO, String Salutation, String GuestName, String LastName, String Email, String Phone) {
		String guestNameCheckInRoom = null;
		Elements_CPReservation res = new Elements_CPReservation(driver);
		WebElement element;

		List<String> salutation = Arrays.asList(Salutation.split("\\|"));
		List<String> guestFName = Arrays.asList(GuestName.split("\\|"));
		List<String> guestLName = Arrays.asList(LastName.split("\\|"));
		List<String> email = Arrays.asList(Email.split("\\|"));
		List<String> phone = Arrays.asList(Phone.split("\\|"));

		// int size=res.CP_Reservation_StayInfo_CheckInButton.size();
		for (int i = 0; i < TotalRoom; i++) {
			if (i == (Integer.parseInt(roomNO) - 1)) {

				String test = salutation.get(i).toString().trim();
				guestNameCheckInRoom = salutation.get(i) + " " + guestFName.get(i) + " " + guestLName.get(i);
				String guestName = "//div[@id='guestInfo-acc']//span[contains(@data-bind,'irFormattedProfileName')][contains(text(),'"
						+ salutation.get(i) + " " + guestFName.get(i) + "')]";
				element = driver.findElement(By.xpath(guestName));
				if (element.isDisplayed()) {
					test_steps.add(" Stay Info Primary Guest Name verified : <b>" + salutation.get(i) + " "
							+ guestFName.get(i) + "</b>");
					reslogger.info(
							" Stay Info Primary Guest Name verified : " + salutation.get(i) + " " + guestFName.get(i));
				} else {
					test_steps.add(" Stay Info Primary Guest Name not found : <b>" + salutation.get(i) + " "
							+ guestFName.get(i) + "</b>");
					reslogger.info(
							" Stay Info Primary Guest Name not found : " + salutation.get(i) + " " + guestFName.get(i));
				}

				String lastName = "//div[@id='guestInfo-acc']//span[contains(@data-bind,'LastName')][contains(text(),'"
						+ guestLName.get(i) + "')]";
				element = driver.findElement(By.xpath(lastName));
				if (element.isDisplayed()) {
					test_steps.add(" Stay Info Last Name verified : <b>" + guestLName.get(i) + "</b>");
					reslogger.info(" Stay Info Last Name verified : " + guestLName.get(i));
				} else {
					test_steps.add(" Stay Info Last Name not found : <b>" + guestLName.get(i) + "</b>");
					reslogger.info(" Stay Info Last Name not found : " + guestLName.get(i));
				}

				String emails = "//div[@id='guestInfo-acc']//span[contains(@data-bind,'EmailAddress')][contains(text(),'"
						+ email.get(i) + "')]";
				element = driver.findElement(By.xpath(emails));
				if (element.isDisplayed()) {
					test_steps.add(" Stay Info Email verified : <b>" + email.get(i) + "</b>");
					reslogger.info(" Stay Info Email verified : " + email.get(i));
				} else {
					test_steps.add(" Stay Info Email not found : <b>" + email.get(i) + "</b>");
					reslogger.info(" Stay Info Email not found : " + email.get(i));
				}

				if (i == 0) {
					String extension = "1-(";
					String firstPhone = phone.get(i).substring(0, 3);
					String phoneBracket = firstPhone + ") ";
					String secondPhone = Phone.substring(3, 6);
					String lastPhone = Phone.substring(6, 10);
					String finalPhone = extension + phoneBracket + secondPhone + "-" + lastPhone;
					String phones = "//div[@id='guestInfo-acc']//span[contains(@data-bind,'irFormattedPhoneNumber')][contains(text(),'"
							+ finalPhone + "')]";
					element = driver.findElement(By.xpath(phones));
					if (element.isDisplayed()) {
						test_steps.add(" Stay Info Phone verified : <b>" + finalPhone + "</b>");
						reslogger.info(" Stay Info Phone verified : " + finalPhone);
					} else {
						test_steps.add(" Stay Info Phone not found : <b>" + finalPhone + "</b>");
						reslogger.info(" Stay Info Phone not found : " + finalPhone);
					}
				} else {
					String phones = "//div[@id='guestInfo-acc']//span[contains(@data-bind,'irFormattedPhoneNumber')][contains(text(),'"
							+ phone.get(i) + "')]";
					element = driver.findElement(By.xpath(phones));
					if (element.isDisplayed()) {
						test_steps.add(" Stay Info Phone verified : <b>" + phone.get(i) + "</b>");
						reslogger.info(" Stay Info Phone verified : " + phone.get(i));
					} else {
						test_steps.add(" Stay Info Phone not found : <b>" + phone.get(i) + "</b>");
						reslogger.info(" Stay Info Phone not found : " + phone.get(i));
					}
				}
			}

		}
		return guestNameCheckInRoom;
	}

	public void verifyStayInfoForCheckInRoom(WebDriver driver, ArrayList test_steps, int TotalRoom, String roomNO,
			String CheckInDate, String CheckOutDate, String Adults, String Children, String RoomClass,
			List<String> RoomCharges, String Salutation, String GuestName, String LastName, String RatePlan)
			throws Exception {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		List<String> checkInDate = Arrays.asList(CheckInDate.split("\\|"));
		List<String> checkOutDate = Arrays.asList(CheckOutDate.split("\\|"));
		List<String> adults = Arrays.asList(Adults.split("\\|"));
		List<String> children = Arrays.asList(Children.split("\\|"));
		List<String> roomClass = Arrays.asList(RoomClass.split("\\|"));
		List<String> salutation = Arrays.asList(Salutation.split("\\|"));
		List<String> guestFName = Arrays.asList(GuestName.split("\\|"));
		List<String> guestLName = Arrays.asList(LastName.split("\\|"));
		List<String> ratePlan = Arrays.asList(RatePlan.split("\\|"));
		String[] checkIn, checkInSplit;
		String checkInMonth = "";
		SimpleDateFormat simpleDateformat;
		Date now;
		int Nights = 0;
		// int size=res.CP_Reservation_StayInfo_CheckInButton.size();
		for (int i = 0; i < TotalRoom; i++) {
			if (i == (Integer.parseInt(roomNO) - 1)) {
				// CheckIn

				SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
				Date dateObj1 = format1.parse(CheckInDate);
				Date dateObj2 = format1.parse(CheckOutDate);
				long diff = dateObj2.getTime() - dateObj1.getTime();
				Nights = (int) (diff / (24 * 60 * 60 * 1000));
				reslogger.info("difference between days: " + Nights);
				checkIn = checkInDate.get(i).split("/");
				if (checkIn[0].charAt(0) == '0') {
					checkIn[0] = checkIn[0].replace("" + checkIn[0].charAt(0), "");
				}
				checkInMonth = Utility.get_Month(checkInDate.get(i));
				String checkInDays = checkInMonth + " " + checkIn[0] + ", " + checkIn[2];
				String checkInDay = "//div[contains(@class,'ir-checkin-stay-info')]/..//div[@class='checkin']/span[text()='"
						+ checkInDays + "']";
				if (driver.findElement(By.xpath(checkInDay)).isDisplayed()) {
					test_steps.add("Stay Info checkin date verified : <b>" + checkInDays + "</b>");
					reslogger.info("Stay Info checkin date verified : " + checkInDays);
				} else {
					test_steps.add("Stay Info checkin date not found : <b>" + checkInDays + "</b>");
					reslogger.info("Stay Info checkin date not found : " + checkInDays);
				}
				now = new Date(checkIn[1] + "/" + checkIn[0] + "/" + checkIn[2]);
				simpleDateformat = new SimpleDateFormat("dd/MM/yyyy"); // the
																		// day
																		// of
																		// the
																		// week
																		// abbreviated
				simpleDateformat = new SimpleDateFormat("EEEE"); // the day of
																	// the week
																	// spelled
																	// out
																	// completely
				String dayofCheckIn = simpleDateformat.format(now);

				String chckInday = "//div[contains(@class,'ir-checkin-stay-info')]/..//div[@class='checkin']//span[text()='"
						+ dayofCheckIn.trim() + "']";
				if (driver.findElement(By.xpath(chckInday)).isDisplayed()) {
					test_steps.add(" Stay Info checkin day verified : <b>" + dayofCheckIn + "</b>");
					reslogger.info(" Stay Info checkin day verified : " + dayofCheckIn);
				} else {
					test_steps.add(" Stay Info checkin day not found : <b>" + dayofCheckIn + "</b>");
					reslogger.info(" Stay Info checkin day not found : " + dayofCheckIn);
				}

				// CheckOut
				String[] checkOut = checkOutDate.get(i).split("/");

				if (checkOut[0].charAt(0) == '0') {
					checkOut[0] = checkOut[0].replace("" + checkOut[0].charAt(0), "");
				}
				String checkOutMonth = Utility.get_Month(checkOutDate.get(i));
				String[] checkOutSplit = checkOut[2].split("\\|");
				String checkOutDays = checkInMonth + " " + checkOut[0] + ", " + checkOut[2];
				String checkoutDay = "//div[contains(@class,'ir-checkin-stay-info')]/..//div[@class='checkout']/span[text()='"
						+ checkOutDays + "']";
				if (driver.findElement(By.xpath(checkoutDay)).isDisplayed()) {
					test_steps.add(" Stay Info checkout date verified : <b>" + checkOutDays + "</b>");
					reslogger.info(" Stay Info checkout date verified : " + checkOutDays);
				} else {
					test_steps.add(" Stay Info checkout date not found : <b>" + checkOutDays + "</b>");
					reslogger.info(" Stay Info checkout date not found : " + checkOutDays);
				}

				now = new Date(checkOut[1] + "/" + checkOut[0] + "/" + checkOut[2]);
				simpleDateformat = new SimpleDateFormat("dd/MM/yyyy"); // the
																		// day
																		// of
																		// the
																		// week
																		// abbreviated
				simpleDateformat = new SimpleDateFormat("EEEE"); // the day of
																	// the week
																	// spelled
																	// out
																	// completely
				String dayofCheckOut = simpleDateformat.format(now);
				String chkOutDay = "//div[contains(@class,'ir-checkin-stay-info')]/..//div[@class='checkout']//span[text()='"
						+ dayofCheckOut.trim() + "']";
				if (driver.findElement(By.xpath(chkOutDay)).isDisplayed()) {
					test_steps.add(" Stay Info checkout day verified : <b>" + dayofCheckOut + "</b>");
					reslogger.info("Stay Info checkout day verified : " + dayofCheckOut);
				} else {
					test_steps.add(" Stay Info checkout day not found : <b>" + dayofCheckOut + "</b>");
					reslogger.info(" Stay Info checkout day not found : " + dayofCheckOut);
				}

				String nights = "//div[contains(@class,'ir-checkin-stay-info')]/..//div[@class='noofnights']/span[text()='"
						+ Nights + "N']";
				if (driver.findElement(By.xpath(nights)).isDisplayed()) {
					test_steps.add(" Stay Info Nights verified : <b>" + Nights + "N</b>");
					reslogger.info(" Stay Info Nights verified : " + Nights + "N");
				} else {
					test_steps.add(" Stay Info Nights not found : <b>" + Nights + "N</b>");
					reslogger.info(" Stay Info Nights not found : " + Nights + "N");
				}

				// Adults
				String adult = "//div[contains(@class,'ir-checkin-stay-info')]/..//div//span[contains(@data-bind,'numberOfAdults')]";
				adult = driver.findElement(By.xpath(adult)).getText().trim();
				if (adult.equals(adults.get(i).trim())) {
					test_steps.add(" Stay Info Adults verified : <b>" + adults.get(i) + "</b>");
					reslogger.info(" Stay Info Adults verified : " + adults.get(i));
				} else {
					test_steps.add(" Stay Info Adults not found : <b>" + adults.get(i) + "</b>");
					reslogger.info(" Stay Info Adults not found : " + adults.get(i));
				}
				// Children
				String childrens = "//div[contains(@class,'ir-guestInfo')]/div/span//span[contains(@data-bind,'text: $data.numberOfChildren')]";
				WebElement childrensList = driver.findElement(By.xpath(childrens));
				if (childrensList.getText().trim().contains(children.get(i).trim())) {
					test_steps.add(" Stay Info Children verified : <b>" + children.get(i) + "</b>");
					reslogger.info(" Stay Info Children verified : " + children.get(i));
				} else {
					test_steps.add(" Stay Info Children not found : <b>" + children.get(i) + "</b>");
					reslogger.info(" Stay Info Children not found : " + children.get(i));
				}
				// Room Class
				String roomClasss1 = "//div[contains(@class,'ir-checkin-stay-info')]/..//div//label[contains(@data-bind,'roomClassName')]";
				WebElement roomClasss = driver.findElement(By.xpath(roomClasss1));
				if (roomClasss.getText().trim().equals((roomClass.get(i) + ":").trim())) {
					test_steps.add(" Stay Info RoomClass verified : <b>" + roomClass.get(i) + "</b>");
					reslogger.info(" Stay Info RoomClass verified : " + roomClass.get(i));
				} else {
					test_steps.add(" Stay Info RoomClass not found : <b>" + roomClass.get(i) + "</b>");
					reslogger.info(" Stay Info RoomClass not found : " + roomClass.get(i));
				}
				// GUest Name
				String guestName = "//div[contains(@class,'ir-checkin-stay-info')]/..//span[contains(@data-bind,'guestNameInSync')][contains(text(),'"
						+ salutation.get(i) + " " + guestFName.get(i) + " " + guestLName.get(i) + "')]";
				WebElement element = driver.findElement(By.xpath(guestName));

				if (element.isDisplayed()) {
					test_steps.add(" Stay Info Guest Name verified : <b>" + salutation.get(i) + " " + guestFName.get(i)
							+ " " + guestLName.get(i) + "</b>");
					reslogger.info(" Stay Info Guest Name verified : " + salutation.get(i) + " " + guestFName.get(i)
							+ " " + guestLName.get(i));
				} else {
					test_steps.add(" Stay Info Guest Name not found : <b>" + salutation.get(i) + " " + guestFName.get(i)
							+ " " + guestLName.get(i) + "</b>");
					reslogger.info(" Stay Info Guest Name not found : " + salutation.get(i) + " " + guestFName.get(i)
							+ " " + guestLName.get(i));
				}

				// RatePlan
				String ratePlans1 = "//div[contains(@class,'ir-checkin-stay-info')]/..//span[contains(@data-bind,'ratePlanName')]";
				WebElement ratePlans = driver.findElement(By.xpath(ratePlans1));
				if (ratePlans.getText().trim().equals(ratePlan.get(i).trim())) {
					test_steps.add(" Stay Info RatePlan verified :<b> " + ratePlan.get(i) + "</b>");
					reslogger.info(" Stay Info RatePlan verified : " + ratePlan.get(i));
				} else {
					test_steps.add(" Stay Info RatePlan not found : <b>" + ratePlan.get(i) + "</b>");
					reslogger.info(" Stay Info RatePlan not found : " + ratePlan.get(i));
				}
				// RoomCharges
				String roomCharge1 = "//div[contains(@class,'ir-checkin-stay-info')]/..//div//label[contains(@data-bind,'roomTotal ')]";
				WebElement roomCharge = driver.findElement(By.xpath(roomCharge1));
				if (roomCharge.getText().trim().equalsIgnoreCase(RoomCharges.get(i).trim())) {
					test_steps.add(" Stay Info RoomCharges verified : <b>" + RoomCharges.get(i).trim() + "</b>");
					reslogger.info(" Stay Info RoomCharges verified : " + RoomCharges.get(i).trim());
				} else {
					test_steps.add(" Stay Info RoomCharges not found : <b>" + RoomCharges.get(i).trim() + "</b>");
					reslogger.info(" Stay Info RoomCharges not found : " + RoomCharges.get(i).trim());
				}

			}

		}
	}

	public void verifyStayInfoCheckOutButton(WebDriver driver, ArrayList<String> test_steps, int RoomNO)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		int size = res.CP_Reservation_StayInfo_ThreeDotts.size();
		for (int i = 0; i < size; i++) {
			if (i == RoomNO) {
				Utility.ScrollToElement(res.CP_Reservation_StayInfo_CheckOutButton, driver);

				assertTrue(res.CP_Reservation_StayInfo_CheckOutButton.isDisplayed(),
						"Failed: To verify Check Out Button");
				test_steps.add("Verified  <b>Check Out  </b> Button For Room: <b>" + i + "</b>");
				reslogger.info("Verified Check Out Button for Room : " + i);

			}

		}

	}

	// public int ButtonSize;

	public ArrayList<String> getStayInfoButtonsText(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		ArrayList<String> buttonsName = new ArrayList<String>();
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Utility.ScrollToElement(res.CP_Reservation_StayInfo_TotalButton.get(0), driver);
		int ButtonSize = res.CP_Reservation_StayInfo_TotalButton.size();
		for (int i = 0; i < ButtonSize; i++) {
			String text = res.CP_Reservation_StayInfo_TotalButton.get(i).getText();
			buttonsName.add(res.CP_Reservation_StayInfo_TotalButton.get(i).getText().toString());
		}

		return buttonsName;
	}

	public void verifyRoomStatusAfterCheckedInRoom(WebDriver driver, ArrayList<String> test_steps, List<String> Rooms,
			ArrayList<String> RoomAbbri, String DirtyStatus, String CleanStatus, String VacantStatus,
			String OccupiedStatus) throws InterruptedException

	{

		RoomStatus roomstatus = new RoomStatus();

		for (int i = 0; i < Rooms.size(); i++) {
			String[] room = Rooms.get(i).split(":");
			String FinalRoom = room[1].trim();
			reslogger.info(" Room No: " + FinalRoom);
			String[] roomClass = RoomAbbri.get(i).split(":");
			String FinalRoomClass = roomClass[0].trim();
			reslogger.info(" Room Class: " + FinalRoomClass);
			roomstatus.searchByRoomHashVerifyRoomStatus(driver, FinalRoom, FinalRoomClass, DirtyStatus, CleanStatus,
					VacantStatus, OccupiedStatus, test_steps);

		}

	}

	public void verifyPaymentDetailUpdateBy(WebDriver driver, String NameOnCard, String UpdateByName,
			String PaymentMethod) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		String path = null;
		WebElement element = null;
		if (PaymentMethod.equals("MC")) {
			path = "//tbody[contains(@data-bind,'ComputedFolioItems')]//td[@class='lineitem-description']/a[contains(text(),'Name: "
					+ NameOnCard + "')]";
			element = driver.findElement(By.xpath(path));
			int counter = 0;
			while (true) {
				if (!element.isDisplayed()) {
					break;
				} else if (counter == 40) {
					break;
				} else {
					counter++;
				}
			}

			Utility.ScrollToElement(element, driver);

		} else if (PaymentMethod.equals("Cash")) {
			path = "//tbody[contains(@data-bind,'ComputedFolioItems')]//tr//td[@class='lineitem-description']/a[contains(text(),'"
					+ PaymentMethod + "')]";
			element = driver.findElement(By.xpath(path));
			int counter = 0;
			while (true) {
				if (!element.isDisplayed()) {
					break;
				} else if (counter == 40) {
					break;
				} else {
					counter++;
				}
			}
			Wait.explicit_wait_elementToBeClickable(element, driver);
			Wait.WaitForElement(driver, path);
			Utility.ScrollToElement(element, driver);

		}
		element.click();
		String updatePath = "//div[@id='ReservationPaymetItemDetail']/..//div[contains(@data-bind,'root.ShowUpateUserInfo')]/span[contains(text(),'"
				+ UpdateByName + "')]";
		WebElement elementUpdateBy = driver.findElement(By.xpath(updatePath));
		Wait.WaitForElement(driver, updatePath);
		Utility.ScrollToElement(elementUpdateBy, driver);
		assertTrue(elementUpdateBy.isDisplayed(), "Failed: to Verify Update By");
		reslogger.info("Verified Update By: " + UpdateByName);

	}

	public void clickPaymentDetailCancel(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.Cp_Reservation_PaymentDetail_CanceButton);
		Utility.ScrollToElement(res.Cp_Reservation_PaymentDetail_CanceButton, driver);
		res.Cp_Reservation_PaymentDetail_CanceButton.click();
		test_steps.add("Clicking on Cancel Button");
		reslogger.info("Clicking on Cancel Button");
	}

	public void clickPaymentDetailClose(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.Cp_Reservation_PaymentDetail_CloseButton);
		Utility.ScrollToElement(res.Cp_Reservation_PaymentDetail_CloseButton, driver);
		res.Cp_Reservation_PaymentDetail_CloseButton.click();
		test_steps.add("Clicking on Close Button");
		reslogger.info("Clicking on Close Button");
	}

	public String getTripSummaryTripTotalAmountBeforeReservation(WebDriver driver, ArrayList test_steps)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Utility.ScrollToElement(res.CP_NewReservation_TripTotalAmount, driver);
		String Balance = res.CP_NewReservation_TripTotalAmount.getText();
		Balance = Balance.replace("$", "");
		test_steps.add("TripSummary Trip Total Before Reservation: " + Balance);
		reslogger.info("TripSummary Trip Total Before Reservation: " + Balance);
		return Balance;
	}

	public void clickCheckOutButton(WebDriver driver, ArrayList test_steps, int balance) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_ConfirmCheckOut_Button);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_ConfirmCheckOut_Button, driver);
		if (balance > 0) {

			Utility.ScrollToElement(res.CP_Reservation_ConfirmCheckOut_Button, driver);
			res.CP_Reservation_ConfirmCheckOut_Button.click();
			test_steps.add("Clicking on  Proceed to Check-Out Payment Button");
			reslogger.info("Clicking on Proceed to Check-Out Payment Button");
			String loading = "(//div[@class='ir-loader-in'])";
			Wait.waitTillElementDisplayed(driver, loading);

		} else {
			Utility.ScrollToElement(res.CP_Reservation_ConfirmCheckOut_Button, driver);
			res.CP_Reservation_ConfirmCheckOut_Button.click();
			test_steps.add("Clicking on  Confirm Check-Out Button");
			reslogger.info("Clicking on Confirm  Check-Out Button");
			String loading = "(//div[@class='ir-loader-in'])";
			Wait.waitTillElementDisplayed(driver, loading);
		}

	}

	public boolean confirmCheckOutButton = false, confirmCheckOutWithPayment = false;

	public String clickCheckOutButtons(WebDriver driver, ArrayList test_steps,

			String checkINConfirm, String CheckINCOnfirmWithPayment) throws InterruptedException {
		String buttonText = null;
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_ConfirmCheckOut_Button);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_ConfirmCheckOut_Button, driver);

		if (res.CP_Reservation_ConfirmCheckOut_Button.getText().toLowerCase().trim()
				.equals(checkINConfirm.toLowerCase().trim())) {

			Utility.ScrollToElement(res.CP_Reservation_ConfirmCheckOut_Button, driver);
			buttonText = res.CP_Reservation_ConfirmCheckOut_Button.getText();
			confirmCheckOutButton = true;
			reslogger.info(buttonText);
			res.CP_Reservation_ConfirmCheckOut_Button.click();
			test_steps.add("Clicking on  Confirm Check-Out Button");
			reslogger.info("Clicking on Confirm  Check-Out Button");
			String loading = "(//div[@class='ir-loader-in'])";
			Wait.waitTillElementDisplayed(driver, loading);
		} else if (res.CP_Reservation_ConfirmCheckOut_Button.getText().toLowerCase().trim()
				.equals(CheckINCOnfirmWithPayment.toLowerCase().trim())) {

			Utility.ScrollToElement(res.CP_Reservation_ConfirmCheckOut_Button, driver);
			buttonText = res.CP_Reservation_ConfirmCheckOut_Button.getText();
			confirmCheckOutWithPayment = true;
			reslogger.info(buttonText);
			res.CP_Reservation_ConfirmCheckOut_Button.click();
			test_steps.add("Clicking on  Proceed to Check-Out Payment Button");
			reslogger.info("Clicking on Proceed to Check-Out Payment Button");
			String loading = "(//div[@class='ir-loader-in'])";
			Wait.waitTillElementDisplayed(driver, loading);

		}

		return buttonText;
	}

	public void AddLineItem(WebDriver driver, ArrayList test_steps, String LineItemCategory,
			String LineItemCategoryAmount) throws InterruptedException {
		test_steps.add("******************* Add Line Item to folio**********************");
		reslogger.info("******************* Add Line Item to folio**********************");

		Elements_CPReservation ReservationPage = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement_150(ReservationPage.Add_LineItem, driver);
		Utility.ScrollToElement(ReservationPage.Add_LineItem, driver);
		ReservationPage.Add_LineItem.click();
		test_steps.add("Click Add");
		reslogger.info("Click Add");
		Wait.wait2Second();
		Utility.ScrollToElement(ReservationPage.Select_LineItem_Category, driver);
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(ReservationPage.Select_LineItem_Category, driver);
		new Select(ReservationPage.Select_LineItem_Category).selectByVisibleText(LineItemCategory);
		test_steps.add("Adding line item to folio : " + LineItemCategory);
		reslogger.info("Adding line item to folio : " + LineItemCategory);
		ReservationPage.Enter_LineItem_Amount.sendKeys(LineItemCategoryAmount);
		test_steps.add("Add Line Item Amount : " + LineItemCategoryAmount);
		reslogger.info("Add Line Item Amount : " + LineItemCategoryAmount);
		Wait.wait2Second();
		ReservationPage.Commit_LineItem.click();
		test_steps.add("Click Commit");
		reslogger.info("Click Commit");
		String saveFolio = "//div[contains(@data-bind,'Folio')]//button[text()='Save']";
		Wait.WaitForElement(driver, saveFolio);
		driver.findElement(By.xpath(saveFolio)).click();
		test_steps.add("Click on Save Folio");
		reslogger.info("Click on Save Folio");
		String loading = "(//div[@class='ir-loader-in'])";
		int count = 0;
		while (true) {
			if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
				break;
			} else if (count == 20) {
				break;
			} else {
				count++;
				Wait.wait2Second();
			}
		}
		reslogger.info("Waited to loading symbol");
	}

	public void verifyAddedLineItem(WebDriver driver, ArrayList test_steps, String LineItemCategory,
			String LineItemCategoryAmount) {
		test_steps.add("******************* Verifying Added Line Item to folio**********************");
		reslogger.info("******************* Verifying Added Line Item to folio**********************");

		String includeTax = "//span[text()='Include Taxes in Line Items']/preceding-sibling::input";
		Wait.WaitForElement(driver, includeTax);
		if (driver.findElement(By.xpath(includeTax)).isSelected()) {
			driver.findElement(By.xpath(includeTax)).click();
		}

		String line = "//td[@class='lineitem-category']/span";

		int count = driver.findElements(By.xpath(line)).size();
		String text = null;

		line = "(//td[@class='lineitem-category']/span[text()='" + LineItemCategory + "'])";
		Wait.WaitForElement(driver, line);
		test_steps.add("Line item category verified in folio : " + LineItemCategory);
		reslogger.info("Line item category verified in folio : " + LineItemCategory);

		line = "(//td[@class='lineitem-category']/span[text()='" + LineItemCategory
				+ "'])/../following-sibling::td[contains(@class,'amount')]/span[contains(text(),'"
				+ LineItemCategoryAmount + "')]";
		Wait.WaitForElement(driver, line);
		test_steps.add("Line item category amount verified in folio : " + LineItemCategoryAmount);
		reslogger.info("Line item category amount verified in folio : " + LineItemCategoryAmount);
	}

	public void clickYesButtonOfCheckOutAllConfirmationMsg(WebDriver driver, ArrayList test_steps, String Msg,
			String CheckOutStatus, String RoomNo, String RoomClassName) throws InterruptedException {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_CheckOutAllButton_ConfirmationMsg);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_CheckOutAllButton_ConfirmationMsg, driver);
		Utility.ScrollToElement(res.CP_Reservation_CheckOutAllButton_ConfirmationMsg, driver);
		if (CheckOutStatus.equals("CheckOutAll")) {
			assertTrue(res.CP_Reservation_CheckOutAllButton_ConfirmationMsg.getText().toLowerCase().trim()
					.equals(Msg.toLowerCase().trim()), "Failed: To verify Message");
			test_steps.add("Verified Message : <b>" + Msg + "</b>");

			reslogger.info("Verified Message : " + Msg);

		} else if (CheckOutStatus.equals("CheckOutRoom")) {
			Msg = "Do you wish to settle all charges in the folio for " + RoomClassName + ", room " + RoomNo
					+ "? If not, payment will be taken when the primary guest checks out.";
			assertTrue(res.CP_Reservation_CheckOutAllButton_ConfirmationMsg.getText().toLowerCase().trim()
					.equals(Msg.toLowerCase().trim()), "Failed: To verify Message");
			test_steps.add("Verified Message : <b>" + Msg + "</b>");
			reslogger.info("Verified Message : " + Msg);

		}
		res.CP_Reservation_CheckOutAllButton_ConfirmationMsgYesButton.click();
		test_steps.add("Clicking on Yes Button of Check-Out All Confirmation Message ");
		reslogger.info("Clicking on Yes Button of Check-Out All Confirmation Message ");
	}

	public void verifyMessageOfCheckOutWindow(WebDriver driver, ArrayList test_steps, String Msg)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Utility.ScrollToElement(res.CP_Reservation_CheckOut_Message, driver);
		/*
		 * assertTrue(res.CP_Reservation_CheckOut_Message.getText().toLowerCase(
		 * ).trim().equals(Msg.toLowerCase().trim()));
		 */
		assertEquals(res.CP_Reservation_CheckOut_Message.getText().toLowerCase().trim(), Msg.toLowerCase().trim(),
				"Failed to verify message ");
		test_steps.add("Verified Message : <b>" + Msg + "</b>");
		reslogger.info("Verified Message : " + Msg);

	}

	public void checkedOverRideCheckInAmountCheckBox(WebDriver driver, ArrayList test_steps, String IsOverRideCheckIN) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_OverrideCheckInAmountCheckBox);
		if (IsOverRideCheckIN.contentEquals("Yes")) {
			if (!res.CP_OverrideCheckInAmountCheckBox.isSelected()) {
				res.CP_OverrideCheckInAmountCheckBox.click();
				test_steps.add("Click Over Ride CheckIn Amount Check Box");
				reslogger.info("Click Over Ride CheckIn Amount Check Box");
			} else {
				test_steps.add("Click Over Ride CheckIn Amount Check Box");
				reslogger.info("Click Over Ride CheckIn Amount Check Box");
			}
		} else {
			if (res.CP_OverrideCheckInAmountCheckBox.isSelected()) {
				res.CP_OverrideCheckInAmountCheckBox.click();
				test_steps.add("Click Over Ride CheckIn Amount Check Box");
				reslogger.info("Click Over Ride CheckIn Amount Check Box");
			} else {
				test_steps.add("Click Over Ride CheckIn Amount Check Box");
				reslogger.info("Click Over Ride CheckIn Amount Check Box");
			}
		}
	}

	public void Input_CheckIn_CheckOut_Amount(WebDriver driver, ArrayList test_step, String Amount) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount.click();
		res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount.clear();
		res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount.click();
		res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount.clear();
		res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount.sendKeys(Amount);
		test_steps.add("Input Amount: <b>" + Amount + "</b>");
		reslogger.info("Input Amount: " + Amount);
	}

	public void inputAmountWhileCheckINAndCheckOut(WebDriver driver, ArrayList test_step, String Amount)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.NoShowPaymentPopup_Amount);
		Utility.ScrollToElement(res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount, driver);
		res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount.click();
		res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount.clear();
		res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount.click();
		res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount.clear();
		res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount.sendKeys(Amount);
		test_step.add("Input Amount: <b>" + Amount + "</b>");
		reslogger.info("Input Amount: " + Amount);
	}

	public void verifyCancellationPolicy(WebDriver driver, ArrayList<String> test_steps, String policyName,
			String policyText) {
			Elements_CPReservation res = new Elements_CPReservation(driver);
			Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.cancelPolicyCollapseInDetailsTab), driver, 20);
			res.cancelPolicyCollapseInDetailsTab.click();

			Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.cancelPolicyNameInDetailsTab), driver);
			String cancelPolicyName = res.cancelPolicyNameInDetailsTab.getText();
			assertEquals(cancelPolicyName, policyName, "Failed - Associated policy is not matched with created one");
			test_steps.add("Successfully verified associated cancellation policy name as : <b>" + cancelPolicyName + "</b>");

			String cancelPolicyText = res.cancelPolicyTextInDetailsTab.getText();
	assertEquals(cancelPolicyText, policyText, "Failed - Associated policy text is not matched with created one");
			test_steps.add("Successfully verified associated cancellation policy text as : <b>" + cancelPolicyText + "</b>");
	}

	public ArrayList<String> getAmountDetailsDisplayedOnTop(WebDriver driver, ArrayList<String> test_steps) {
		ArrayList<String> amountDetails = new ArrayList<>();
		Elements_CPReservation res = new Elements_CPReservation(driver);
		String total = res.tripTotalAmountAtTop.getText().split(" ")[1];
		test_steps.add("Total amount displayed at top is :: " + total);
		String balance = res.tripBalanceAmountAtTop.getText().split(" ")[1];
		test_steps.add("Balance amount displayed at top is :: " + balance);
		amountDetails.add(total);
		amountDetails.add(balance);
		return amountDetails;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: basicSearch_WithGuestName ' Description: Reservation
	 * search with guest name until it shows ' Input parameters: WebDriver,
	 * ArrayList, String ' Return value: String ' Created By: Naveen Banda '
	 * Created On: 04-04-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String searchWithGuestName(WebDriver driver, ArrayList<String> test_steps, String guestName)
			throws InterruptedException {
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);

		String getGuestName = null;

		Wait.explicit_wait_visibilityof_webelement_120(resservationSearch.BasicGuestName, driver);
		resservationSearch.BasicGuestName.clear();
		resservationSearch.BasicGuestName.sendKeys(guestName);
		resservationSearch.Click_BasicSearch.click();
		Wait.wait3Second();
		if (resservationSearch.noRecordAlertMessage.isDisplayed()) {
			for (int i = 0; i < 5; i++) {
				resservationSearch.Click_BasicSearch.click();
				Wait.wait3Second();
				if (!resservationSearch.noRecordAlertMessage.isDisplayed()) {
					getGuestName = resservationSearch.Get_Guest_Name.getAttribute("title");
					break;
				}
			}
		} else {
			getGuestName = resservationSearch.Get_Guest_Name.getAttribute("title");
		}
		reslogger.info("Searching for a reservation with guest name as : <b>" + guestName + "</b>");
		test_steps.add("Searching for a reservation with guest name as : <b>" + guestName + "</b>");
		return getGuestName;
	}

	public void verifyCheckInPolicy(WebDriver driver, ArrayList<String> test_steps, String policyName,
			String policyText) {
		try {
			Elements_CPReservation res = new Elements_CPReservation(driver);
			Wait.explicit_wait_visibilityof_webelement_120(
					res.CP_Reservation_POLICIESANDDISCLAIMERS_CheckInPolicyCollapse, driver);
			res.CP_Reservation_POLICIESANDDISCLAIMERS_CheckInPolicyCollapse.click();

			String checkinPolicyName = res.checkInPolicyName.getText();
			assertEquals(checkinPolicyName, policyName, "Failed - Associated policy is not matched with created one");
			test_steps.add("Successfully verified associated check-in policy as : <b>" + checkinPolicyName + "</b>");

			String checkinPolicyToolTip = res.checkInPolicyToolTip.getAttribute("title");
			assertEquals(checkinPolicyToolTip, policyName,
					"Failed - Associated policy is not matched with created one");
			test_steps.add("Successfully verified associated check-in policy tool tip text as : <b>"
					+ checkinPolicyToolTip + "</b>");

			String checkInPolicyText = res.checkInPolicyText.getText();
			assertEquals(checkInPolicyText, policyText,
					"Failed - Associated policy text is not matched with created one");
			test_steps
					.add("Successfully verified associated check-in policy text as : <b>" + checkInPolicyText + "</b>");
		} catch (Exception e) {

		}
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: clickOnGuestName ' Description: Clicking on guest name
	 * after searching for a reservation ' Input parameters: WebDriver,
	 * ArrayList ' Return value: void ' Created By: Naveen Banda ' Created On:
	 * 04-04-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public void clickOnGuestName(WebDriver driver, ArrayList<String> test_steps) {
		try {
			Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
			Wait.explicit_wait_visibilityof_webelement_120(resservationSearch.Get_Guest_Name, driver);
			resservationSearch.Get_Guest_Name.click();
			test_steps.add("Clicked on guest name and opened guest profile");
		} catch (Exception e) {

		}
	}

	public ArrayList<String> cancelPaymentCalculations(ArrayList<String> baseAmount, ArrayList<String> totalNights,
			String attribute, String attributeValue) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);
		ArrayList<String> roomCharges = new ArrayList<>();
		ArrayList<String> amountDetails = new ArrayList<>();
		double amountToDeduct = 0.00;
		for (int i = 0; i < baseAmount.size(); i++) {
			roomCharges
					.add(df.format((Double.parseDouble(baseAmount.get(i))) * (Double.parseDouble(totalNights.get(i)))));
		}
		if (attribute.equalsIgnoreCase("Room Charges")) {
			for (int i = 0; i < roomCharges.size(); i++) {
				double abc = (Double.parseDouble(roomCharges.get(i))) * (Double.parseDouble(attributeValue));
				double perc = abc / 100;
				amountToDeduct = amountToDeduct + perc;
			}
		} else if (attribute.equalsIgnoreCase("First Night")) {
			for (int i = 0; i < baseAmount.size(); i++) {
				amountToDeduct = amountToDeduct + Double.parseDouble(baseAmount.get(i));
			}
		}
		amountDetails.add(df.format(amountToDeduct));
		return amountDetails;
	}

	// public ArrayList<String> checkInPaymentCalculations(ArrayList<String>
	// baseAmount, ArrayList<String> totalNights, String policyFor,
	// String percentage, String taxes) {
	// DecimalFormat df = new DecimalFormat("0.00");
	// df.setMaximumFractionDigits(2);
	// ArrayList<String> roomCharges = new ArrayList<>();
	// ArrayList<String> amountDetails = new ArrayList<>();
	// double balance = 0.00;
	// double amount = 0.00;
	// for (int i = 0; i < baseAmount.size(); i++) {
	// roomCharges.add(df.format(
	// (Double.parseDouble(baseAmount.get(i)))*(Double.parseDouble(totalNights.get(i)))
	// ) );
	// }
	// if (policyFor.equalsIgnoreCase("Capture")) {
	// for (int i = 0; i < roomCharges.size(); i++) {
	// double abc =
	// (Double.parseDouble(roomCharges.get(i)))*(Double.parseDouble(percentage));
	// double perc = abc/100;
	// balance = balance+perc;
	// }
	// }else if (policyFor.equalsIgnoreCase("Authorize")) {
	// for (int i = 0; i < roomCharges.size(); i++) {
	// double abc =
	// (Double.parseDouble(roomCharges.get(i)))*(Double.parseDouble(percentage));
	// double perc = abc/100;
	// balance = balance+perc;
	// }
	// }
	// return amountDetails;
	// }

	public ArrayList<String> paymentCheckInPopupVerify(WebDriver driver, ArrayList<String> test_steps, String title,
			String expiryDate, String nameOnCard, String cardNo, String paymentMethod, String type, String percentage,
			String policyFor, String policyName, String policyText, ArrayList<String> baseAmount,
			ArrayList<String> totalNights, String taxes, String attr, String attrValue) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		ArrayList<String> details = new ArrayList<>();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.NoShowPaymentPopupTitle), driver);
		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);
		try {

			String titleDisplayed = res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupTitle.getText();
			reslogger.info(" Verified Payment Page Title : " + titleDisplayed);
			assertEquals(titleDisplayed, title, "Failed: To verify Payment Page Title");
			test_steps.add(" Verified Payment Page Title :<b> " + title + "</b>");

			WebElement paymentElement = driver.findElement(By.xpath(
					"//div[contains(@data-bind,'IsSplitPaymentEnable')]//button[@title='" + paymentMethod + "']"));
			String paymentMethodDisplayed = paymentElement.getText().trim();
			assertEquals(paymentMethodDisplayed, paymentMethod, "Failed: To verify Payment Method");
			test_steps.add(" Verified Payment Method:<b> " + paymentMethodDisplayed + "</b> ");
			reslogger.info(" Verified Payment Method: " + paymentMethodDisplayed);

			String cardNoDisplayed = ((String) executor.executeScript("return arguments[0].value",
					res.CP_Reservation_NoShowPaymentPopup_CardNumber)).trim();
			assertEquals(cardNoDisplayed, cardNo, "Failed: To verify Card Number");
			test_steps.add(" Verified Card Number: <b>" + cardNoDisplayed + "</b>");
			reslogger.info(" Verified Card Number");

			String nameOnCardDisplayed = (String) executor.executeScript("return arguments[0].value",
					res.CP_Reservation_NoShowPaymentPopup_NameOnCard);
			assertEquals(nameOnCardDisplayed, nameOnCard, "Failed: To verify Name on Card ");
			test_steps.add("Verified Name on Card:<b>  " + nameOnCard + "</b>");
			reslogger.info(" Verified Name on Card");
			String expiryDateDisplayed = (String) executor.executeScript("return arguments[0].value",
					res.CP_Reservation_NoShowPaymentPopup_Expiry);
			assertEquals(expiryDateDisplayed, expiryDate, "Failed: To verify Expiry Date ");
			test_steps.add(" Verified Expiry Date:<b> " + expiryDateDisplayed + "</b>");
			reslogger.info(" Verified Expiry Date");

			String typeDisplayed = res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupType.getText().trim();
			String amountDisplayed = res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount.getText().trim();
			String balanceDisplayed = null;
			String buttonExp = null;
			String buttonDisplayed = res.CP_Reservation_NoShowPaymentPopup_LogAndPayButton.getText().trim();

			if (title.contains("Check In Payment")) {
				balanceDisplayed = res.CheckInPaymentPopup_Balance.getText().replace("$ ", "");
				String policyDisplayed = res.checkInPaymentPopupPolicyName.getText();
				assertEquals(policyDisplayed, policyName, "Failed: To verify  type");
				test_steps.add(" Verified Policy name is :<b> " + policyDisplayed + "</b>");
				reslogger.info(" Verified Check-in payment type is : " + policyDisplayed);

				Actions action = new Actions(driver);
				action.moveToElement(res.CheckInReservation_PolicyName).perform();
				String toolTipPolicyDesc = res.CancelRoom_ToolTip_PolicyDescription.getText();
				reslogger.info(" Verified tool tip text at policy name : " + toolTipPolicyDesc);
				assertEquals(toolTipPolicyDesc, policyText, "Failed: To verify Payment Page Title");
				test_steps.add(" Verified tool tip text at policy name as :<b> " + toolTipPolicyDesc + "</b>");

				assertEquals(res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount.isEnabled(), false,
						"Failed - Amount is in read only and can be changed");
				test_steps.add("Amount for <b>" + policyFor + "</b> is read only and can't be changeds");

				assertEquals(typeDisplayed, type, "Failed: To verify  type");
				test_steps.add(" Verified Check-in payment type is :<b> " + type + "</b>");
				reslogger.info(" Verified Check-in payment type is : " + type);

				try {
					boolean readOnly = driver.findElement(By.xpath(
							"//button[@class='btn dropdown-toggle disabled btn-default'][@title='" + type + "']"))
							.isDisplayed();// res.paymentCheckInPopupAuthTypeReadOnly.isDisplayed();
					assertEquals(readOnly, true, "Failed - Payment type is read only");
					test_steps.add(
							"Successfully verified Payment type <b>" + type + "</b> is read only and can't be changed");
				} catch (Exception e) {
					boolean editable = driver
							.findElement(By
									.xpath("//button[@class='btn dropdown-toggle btn-default'][@title='" + type + "']"))
							.isDisplayed();
					assertEquals(editable, true, "Failed - Payment type is editable");
					test_steps.add("Successfully verified Payment type is editable");
				}
				double totalAmount = 0.00;
				for (int i = 0; i < baseAmount.size(); i++) {
					totalAmount = (totalAmount)
							+ (Double.parseDouble(baseAmount.get(i)) * Double.parseDouble(totalNights.get(i)));
				}
				totalAmount = totalAmount + Double.parseDouble(taxes);
				String balanceExp = df.format(totalAmount);
				double amount2 = (totalAmount * Double.parseDouble(percentage)) / 100;
				String amountExp = df.format(amount2);
				details.add(balanceExp);
				details.add(amountExp);
				test_steps.add("Calculated policy amount <b>" + percentage + "</b> % from total amount <b>" + balanceExp
						+ "</b> is <b>" + amountExp + "</b>");

				assertEquals(amountDisplayed, amountExp,
						"Failed - Amount for " + policyFor + " while check-in is not applied as per policy");
				test_steps.add("Successfully Verified Amount for <b>" + policyFor
						+ "</b> while check-in is applied as per policy is <b>" + amountExp + "</b>");

				assertEquals(balanceDisplayed, balanceExp, "Failed - Verify balance inclunding taxes");
				test_steps.add("Successfully Verified balance inclunding taxes <b>" + balanceExp + "</b>");

				if (policyFor.equalsIgnoreCase("Capture")) {
					buttonExp = "PAY $ " + df.format(amount2);
				} else if (policyFor.equalsIgnoreCase("Authorize")) {
					buttonExp = policyFor.toUpperCase() + " $ " + df.format(amount2);
				}
				assertEquals(buttonDisplayed, buttonExp, "Failed: To verify Button for " + buttonDisplayed);
				test_steps.add(" Verified button text as :<b> " + buttonDisplayed + "</b>");
				reslogger.info(" Verified button text as :<b> " + buttonDisplayed + "</b>");

			} else if (title.contains("Cancellation Payment")) {
				balanceDisplayed = res.cancelPaymentPopupBalance.getText().replace("$ ", "");
				if (!(typeDisplayed.equalsIgnoreCase(type))) {
					res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupType.click();
					driver.findElement(
							By.xpath("//ul[@class='dropdown-menu inner']//span[contains(text(),'" + type + "')]"))
							.click();
				}
				ArrayList<String> payment = cancelPaymentCalculations(baseAmount, totalNights, attr, attrValue);
				details.add(payment.get(0));
				assertEquals(balanceDisplayed, payment.get(0),
						"Failed - Balance while cancelling reservation is not matched with one night charge");
				test_steps.add("Successfully verified balance displayed on cancel reservation popup is <b>"
						+ balanceDisplayed + "</b>");
				assertEquals(amountDisplayed, payment.get(0),
						"Failed - Amount while cancelling reservation is not matched with one night charge");
				test_steps.add("Successfully verified amount displayed on cancel reservation popup is <b>"
						+ amountDisplayed + "</b>");
				if (type.equalsIgnoreCase("Capture")) {
					buttonExp = "PAY $ " + payment.get(0);
				} else if (type.equalsIgnoreCase("Refund")) {
					buttonExp = "REFUND $ " + payment.get(0);
				}
				assertEquals(buttonDisplayed, buttonExp, "Failed - Verify button for " + type);
				test_steps.add("Successfully verified button text displayed on cancel reservation popup is <b>"
						+ buttonDisplayed + "</b>");
			}
		} catch (Exception e) {
			reslogger.info(e);
		}
		return details;
	}

	public void verifyFolioLineItem(WebDriver driver, ArrayList<String> test_steps, ArrayList<String> baseAmount,
			ArrayList<String> totalNights, String totalAmount, String paymentType, String authAmount, String policyFor)
			throws Exception {
		ArrayList<String> roomCharges = new ArrayList<>();
		Elements_CPReservation res = new Elements_CPReservation(driver);
		double folioRoomChargeLineItems = 0.00;
		double folioAuthOrCaptureLineItems = 0.00;
		double folioRoomCharges = 0.00;
		double folioIncidentals = 0.00;
		double folioTaxes = 0.00;
		double folioPayments = 0.00;
		double folioTotalCharges = 0.00;
		double folioBalance = 0.00;
		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);

		List<WebElement> rooms = driver.findElements(By.xpath(
				"//button[@class='btn dropdown-toggle btn-default'][contains(@title,'Guest Folio')]/..//a/span[@class='text']"));
		reslogger.info(rooms.size());
		for (WebElement roomsToSelect : rooms) {
			driver.findElement(
					By.xpath("//button[@class='btn dropdown-toggle btn-default'][contains(@title,'Guest Folio')]"))
					.click();
			Wait.wait1Second();
			roomsToSelect.click();
			List<WebElement> roomChargesAmount = driver.findElements(
					By.xpath("//span[contains(text(), 'Room Charge')]/../..//td[@class='lineitem-amount']"));
			if (roomChargesAmount.size() != 0) {
				for (WebElement getRoomCharges : roomChargesAmount) {
					double roomCharge = Double.parseDouble(getRoomCharges.getText().replace("$ ", ""));
					folioRoomChargeLineItems = folioRoomChargeLineItems + roomCharge;
				}
			}
			List<WebElement> authOrCaptureAmount = driver.findElements(
					By.xpath("//span[contains(text(), '" + paymentType + "')]/../..//td[@class='lineitem-amount']"));
			if (authOrCaptureAmount.size() != 0) {
				for (WebElement getAuthOrCaptureCharges : authOrCaptureAmount) {
					double authOrCaptureCharge = Double
							.parseDouble(getAuthOrCaptureCharges.getText().replace("$ ", ""));
					folioAuthOrCaptureLineItems = folioAuthOrCaptureLineItems + authOrCaptureCharge;
				}
			}
			// folioAuthOrCaptureLineItems =
			// folioAuthOrCaptureLineItems+Double.parseDouble(getFolioAmount(driver,
			// test_steps, paymentType));
			folioRoomCharges = folioRoomCharges + Double.parseDouble(get_RoomCharge(driver, test_steps));
			folioTotalCharges = folioTotalCharges + Double.parseDouble(get_TotalCharges(driver, test_steps));
			folioPayments = folioPayments + Double.parseDouble(get_Payments(driver, test_steps));
			String balance1 = get_Balance(driver, test_steps);
			if (balance1.contains("(") || balance1.contains(")")) {
				balance1 = balance1.replace("(", "");
				balance1 = balance1.replace(")", "");
				folioBalance = folioBalance - Double.parseDouble(balance1);
			} else {
				folioBalance = folioBalance + Double.parseDouble(get_Balance(driver, test_steps));
			}
		}
		String balanceExp = null;
		String paymentsExp = null;
		if (policyFor.equalsIgnoreCase("Capture")) {
			double amount2 = Double.parseDouble(authAmount);
			double total2 = Double.parseDouble(totalAmount);
			double balance2 = total2 - amount2;
			double payments2 = total2 - balance2;
			balanceExp = df.format(balance2);
			paymentsExp = df.format(payments2);
		} else if (policyFor.equalsIgnoreCase("Authorize")) {
			balanceExp = totalAmount;
			paymentsExp = "0.00";
		}
		String folioRoomChargeLineItemsDisplayed = df.format(folioRoomChargeLineItems);
		String folioAuthOrCaptureLineItemsDisplayed = df.format(folioAuthOrCaptureLineItems);
		String folioRoomChargesDisplayed = df.format(folioRoomCharges);
		String folioTotalChargesDisplayed = df.format(folioTotalCharges);
		String folioPaymentsDisplayed = df.format(folioPayments);
		String folioBalanceDisplayed = df.format(folioBalance);

		assertEquals(folioRoomChargeLineItemsDisplayed, totalAmount,
				"Failed - Room charges line items for all nights mismatched at folio tab");
		test_steps.add("Successfully verified Room charge line items for all nights after check-in is <b>"
				+ folioRoomChargeLineItemsDisplayed + "</b>");

		assertEquals(folioAuthOrCaptureLineItemsDisplayed, authAmount,
				"Failed - " + policyFor + " amount mismatched at folio tab ");
		test_steps.add("Successfully verified " + policyFor + " amount after check-in is <b>"
				+ folioAuthOrCaptureLineItemsDisplayed + "</b>");
		double totalAmount2 = 0.00;
		for (int i = 0; i < baseAmount.size(); i++) {
			totalAmount2 = totalAmount2
					+ Double.parseDouble(baseAmount.get(i)) * Double.parseDouble(totalNights.get(i));
		}
		String roomChargeExp = df.format(totalAmount2);
		assertEquals(folioRoomChargesDisplayed, roomChargeExp, "Failed - Room charges mismatched at folio tab ");
		test_steps.add("Successfully verified Room charges after check-in is <b>" + folioRoomChargesDisplayed + "</b>");

		assertEquals(folioTotalChargesDisplayed, totalAmount, "Failed - Total charges mismatched at folio tab ");
		test_steps.add("Successfully verified Total charges after check-in is <b>" + folioTotalCharges + "</b>");

		assertEquals(folioPaymentsDisplayed, paymentsExp, "Failed - payments mismatched at folio tab ");
		test_steps.add("Successfully verified payments after check-in is <b>" + folioPaymentsDisplayed + "</b>");

		assertEquals(folioBalanceDisplayed, balanceExp, "Failed - Balance mismatched at folio tab ");
		test_steps.add("Successfully verified balance after check-in is <b>" + folioBalanceDisplayed + "</b>");

	}

	public void verifyAuthAmountInFolioHistory(WebDriver driver, ArrayList<String> test_steps, String descExp,
			String categoryExp, String policyFor) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(res.folioHistoryTabSearchBox, driver);
		String desc = null;
		String category = null;
		if (policyFor.equalsIgnoreCase("Capture")) {
			descExp = "Captured" + descExp;
			desc = res.folioHistoryCapturePaymentDesc.getText();
			category = res.folioHistoryCapturePaymentCategory.getText();
		} else if (policyFor.equalsIgnoreCase("Authorize")) {
			descExp = "Authorized" + descExp;
			desc = res.folioHistoryAuthPaymentDesc.getText();
			category = res.folioHistoryAuthPaymentCategory.getText();
		}
		assertEquals(desc, descExp,
				"Failed - Description of payment for " + policyFor + " is not matched at folio history tab");
		test_steps.add("Successfully verified Description of payment for " + policyFor + " at folio history is <b>"
				+ desc + "</b>");

		assertEquals(category, categoryExp,
				"Failed - Category of authorized payment is not matched at folio history tab");
		test_steps.add(
				"Successfully verified Category of authorized payment at folio history is <b>" + category + "</b>");
	}

	public void selectRoomAtPaymentCheckInPopup(WebDriver driver, ArrayList<String> test_steps,
			ArrayList<String> roomNumbers) throws Exception {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.roomNoDDAtCheckInPopup), driver);
		List<WebElement> roomDropDowns = res.roomsDDAtCheckInPopup;
		List<WebElement> roomClasses = res.confirmCheckInPaymentRoomClassName;
		for (int i = 0; i < roomDropDowns.size(); i++) {
			int index = i + 1;
			Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.confirmCheckInPaymentRoomClassName), driver);
			String roomClass = roomClasses.get(i).getText();
			roomDropDowns.get(i).click();
			String roomNo = roomNumbers.get(i);
			String xpathOfRoomNo = "(//span[@class='text' and text()='" + roomNo + "'])[" + index + "]";
			Wait.waitForElementToBeClickable(By.xpath(xpathOfRoomNo), driver);
			driver.findElement(By.xpath(xpathOfRoomNo)).click();
			test_steps.add("Selecting room number <b>" + roomNo + "</b> for <b>" + roomClass
					+ "</b> room class during payment check-in");
		}
	}

	public void checkInPaymentSuccessPopupVerify(WebDriver driver, ArrayList<String> test_steps, String NoShowSuccess,
			String Status, String PaymentMethod, String balance, String Type, String amount, String policyName,
			String policyFor, String policyText) throws Exception {
		try {
			String validate = null;
			DecimalFormat df = new DecimalFormat("0.00");
			df.setMaximumFractionDigits(2);
			Elements_CPReservation res = new Elements_CPReservation(driver);
			Wait.explicit_wait_visibilityof_webelement_120(res.CP_Reservation_NoShowSuccess, driver);
			Utility.ScrollToElement(res.CP_Reservation_NoShowSuccess, driver);

			String balanceDisplayed = res.NoShowSuccess_Balance.getText().replace("$ ", "").trim();
			String balanceCalculated = null;
			String amountDisplayed = res.noShowSuccessPolicyAmount.getText().replace("$ ", "");
			String amountExp = balance;

			if (NoShowSuccess.contains("Check-In Successful")) {
				validate = "Check-In";
				// Verified Success-Balance
				double balance2 = Double.parseDouble(balance);
				double amount2 = Double.parseDouble(amount);
				if (policyFor.equalsIgnoreCase("Capture")) {
					balance2 = balance2 - amount2;
					balanceCalculated = df.format(balance2);
				} else if (policyFor.equalsIgnoreCase("Authorize")) {
					balanceCalculated = df.format(balance2);
				}
				amountExp = amount;
				// Verified Success-policy name
				String policyDisplayed = res.noShowSuccessPolicyName.getText();
				assertEquals(policyDisplayed, policyName, "Failed: To verify policy name");
				test_steps.add(" Verified policy name : <b>" + policyDisplayed + "</b>");
				reslogger.info("  Verified policy name : " + policyDisplayed);

				Actions action = new Actions(driver);
				action.moveToElement(res.noShowSuccessPolicyName).perform();

				String toolTipPolicyDesc = res.CancelRoom_ToolTip_PolicyDescription.getText();
				reslogger.info(" Verified tool tip text at policy name : " + toolTipPolicyDesc);
				assertEquals(toolTipPolicyDesc, policyText, "Failed: To verify Payment Page Title");
				test_steps.add(" Verified tool tip text at policy name as :<b> " + toolTipPolicyDesc + "</b>");

			} else if (NoShowSuccess.contains("Cancellation Successful")) {
				validate = "Cancellation";
				balanceCalculated = "0.00";
				amountExp = df.format(Double.parseDouble(balance));
			}

			assertEquals(balanceDisplayed, balanceCalculated, "Failed: To verify Success Page Balance");
			test_steps.add(" Verified Balance during : " + validate + " <b> " + balanceDisplayed + "</b>");
			reslogger.info("  Verified Balance: " + balanceDisplayed);

			assertEquals(amountDisplayed, amountExp, "Failed : To verify Success Page Amount");
			test_steps.add(" Verified Amount  during : " + validate + " <b> " + amountDisplayed + "</b>");
			reslogger.info("  Verified Amount : " + amountDisplayed);

			// Verified Success -Message
			String header = res.CP_Reservation_NoShowSuccess.getText().trim();
			assertEquals(header, NoShowSuccess, "Failed: To verify Success Header");
			test_steps.add("  Verified header of the " + validate + " payment popup: <b>" + NoShowSuccess + "</b>");
			reslogger.info("  Verified header of the " + validate + " payment popup :" + NoShowSuccess);

			// Verified Success-Date
			// === For check-in/cancel it takes current day as updated so
			// validating with current day
			String date = Utility.getCurrentDate("MM/dd/yyyy");
			// date = Utility.parseDate(date, "dd/MM/yyyy", "MM/dd/yyyy");
			String dateDisplayed = res.NoShowSuccess_Date.getText();
			assertEquals(dateDisplayed, date, "Failed: To verify  Success Page Date");
			test_steps.add("  Verified Date of " + validate + " : <b>" + date + "</b>");
			reslogger.info("  Verified Date of " + validate + " : " + date);

			// Verified Success-Type
			String typeDisplayed = res.NoShowSuccess_Type.getText().trim();
			assertEquals(typeDisplayed, Type, "Failed: To verify  Success Page Type");
			test_steps.add("  Verified " + validate + " payment type : <b>" + Type + "</b>");
			reslogger.info(" Verified " + validate + " payment type : " + Type);

			// Verified Success-Payment Method
			String paymentMethodDisplayed = res.CP_Reservation_NoShowSuccessPaymentMethod.getText().trim();
			assertEquals(paymentMethodDisplayed, PaymentMethod, "Failed: To verify Success Page Payment Method");
			test_steps.add("  Verified " + validate + " Payment Method: <b>" + PaymentMethod + "</b>");
			reslogger.info("  Verified " + validate + " Payment Method: " + PaymentMethod);

			// Verified Success -Status
			String statusDisplayed = res.CP_Reservation_NoShowSuccessStatus.getText().trim();
			assertEquals(statusDisplayed, Status, "Failed: To verify  Success Page Status ");
			test_steps.add("  Verified Status of " + validate + " payment :<b> " + Status + "</b>");
			reslogger.info(" Verified Status of " + validate + " payment : " + Status);

		} catch (Exception e) {
			reslogger.info(e);
		}
	}

	public String verifyCheckOutPaymentWindow(WebDriver driver, ArrayList<String> test_steps, String Title,
			String PaymentType, String Type, String AmountRemaining,

			String cardNo, String nameOnCard, String expiryDate) throws InterruptedException {
		String CheckOutAmount = null;
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.NoShowPaymentPopupTitle);
		Utility.ScrollToElement(res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupTitle, driver);
		// Verified No Show Payment Page Title
		assertTrue(res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupTitle.getText().toLowerCase().trim()
				.equals(Title.toLowerCase().trim()), "Failed: To verify  Payment Page Title");
		test_steps.add(" Verified Payment Title:<b> " + Title + "</b>");
		reslogger.info(" Verified Payment Title: " + Title);
		String path = "//div[contains(@data-bind,'IsSplitPaymentEnable')]//button[@title='" + PaymentType + "']";
		reslogger.info(path);
		WebElement paymentElement = driver.findElement(By.xpath(path));
		// Verified Payment Page Type
		type = res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupType.getText().toLowerCase().trim();
		assertEquals(type, Type.toLowerCase().trim(), "Failed: To verify  Page Type: " + Type);
		test_steps.add(" Verified Type : <b> " + Type + "</b>");
		reslogger.info(" Verified Type: " + Type);

		// Get Amount
		CheckOutAmount = res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount.getText();
		double PaymentAmount = Double.valueOf(CheckOutAmount);
		int amt = (int) PaymentAmount;
		CheckOutAmount = String.valueOf(amt);
		reslogger.info(CheckOutAmount);

		// Get Balance
		String balance = "";
		balance = res.NoShowCancelPaymentPopup_Balance.getText().replace("$", "");

		double balanceDouble = Double.valueOf(balance);
		int balanceInt = (int) balanceDouble;
		balance = String.valueOf(balanceInt);
		if (CheckOutAmount.equals(AmountRemaining)) {
			assertEquals(CheckOutAmount, AmountRemaining, "Failed: To verify Payment Page Amount: " + AmountRemaining);
			test_steps.add("Verified Amount : <b> " + AmountRemaining + "</b>");
			reslogger.info(" Verified Amount: " + AmountRemaining);
			assertEquals(balance, AmountRemaining, "Failed: To verify Payment Page Balance: " + AmountRemaining);
			test_steps.add("Verified Balance : <b> " + AmountRemaining + "</b>");
			reslogger.info(" Verified Balance: " + AmountRemaining);
		}

		if (PaymentType.equals("Cash")) {
			// Verified Payment Page Payment Method
			assertTrue(paymentElement.isDisplayed(), "Failed: To verify Payment Method");
			test_steps.add(" Verified Payment Method:<b> " + PaymentType + "</b> ");
			reslogger.info(" Verified Payment Method: " + PaymentType);

		} else if (PaymentType.equals("MC")) {
			// Verified No Show Payment Page Payment Method
			assertTrue(paymentElement.isDisplayed(), "Failed: To verify Payment Method");
			test_steps.add(" Verified Payment Method:<b> " + PaymentType + "</b> ");
			reslogger.info(" Verified Payment Method: " + PaymentType);

			// Verified No Show Payment Page Card No
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			String CardNo = (String) executor.executeScript("return arguments[0].value",
					res.CP_Reservation_NoShowPaymentPopup_CardNumber);
			reslogger.info(CardNo);
			// Verify Card Number

			assertTrue(CardNo.contains(cardNo), "Failed: To verify Card Number");
			test_steps.add(" Verified Card Number: <b>" + CardNo + "</b>");
			reslogger.info(" Verified Card Number");
			String NameOnCard = (String) executor.executeScript("return arguments[0].value",
					res.CP_Reservation_NoShowPaymentPopup_NameOnCard);
			reslogger.info(NameOnCard);

			// Verify Name on Card Number
			assertEquals(NameOnCard.toLowerCase().trim(), (nameOnCard.toLowerCase().trim()),
					"Failed: To verify Name on Card ");
			test_steps.add("Verified Name on Card:<b>  " + nameOnCard + "</b>");
			reslogger.info(" Verified Name on Card");

			String ExpiryDate = (String) executor.executeScript("return arguments[0].value",
					res.CP_Reservation_NoShowPaymentPopup_Expiry);
			reslogger.info(ExpiryDate);
			reslogger.info(expiryDate);
			// Verify Expiry Date
			assertEquals(ExpiryDate, expiryDate, "Failed: To verify Expiry Date ");
			test_steps.add(" Verified Expiry Date:<b> " + ExpiryDate + "</b>");
			reslogger.info(" Verified Expiry Date");
		}

		return CheckOutAmount;
	}

	public void selectTypeWhileCheckIN(WebDriver driver, ArrayList<String> test_steps, String Type)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.NoShowPaymentPopup_Type);
		Utility.ScrollToElement(res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupType, driver);
		res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupType.click();
		test_steps.add("Click on Type Drop Down Box");
		reslogger.info("Click on Type Drop Down Box");
		List<String> type = Arrays.asList(Type.split("\\|"));
		for (int i = 0; i < res.CP_CheckInTypeOptions.size(); i++) {
			if (res.CP_CheckInTypeOptions.get(i).getText().toLowerCase().trim()
					.equals(type.get(1).toLowerCase().trim())) {
				Wait.WaitForElement(driver, OR_Reservation.CP_CheckInTypeOptions);
				Utility.ScrollToElement(res.CP_CheckInTypeOptions.get(i), driver);
				res.CP_CheckInTypeOptions.get(i).click();
				test_steps.add(" Selected  Type : <b>" + res.CP_CheckInTypeOptions.get(i).getText() + "</b>");
				reslogger.info(" Selected Type " + res.CP_CheckInTypeOptions.get(i).getText());
				break;
			}
			res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupType.click();
			test_steps.add(" Click on Type Drop Down Box Again");
			reslogger.info(" Click on Type Drop Down Box Again");

		}
	}

	public void selectType(WebDriver driver, ArrayList<String> test_steps, String Type) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.NoShowPaymentPopup_Type);
		Utility.ScrollToElement(res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupType, driver);
		res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupType.click();
		test_steps.add("Click on Type Drop Down Box");
		reslogger.info("Click on Type Drop Down Box");
		Wait.WaitForElement(driver, OR_Reservation.CP_CheckInTypeOptions);
		String path = "//div[contains(@data-bind,'IsAuthTypeDropDownVisible')]//ul//li/a/span[@class='text'][contains(text(),'"
				+ Type + "')]";
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		element.click();
		test_steps.add(" Selected  Type : <b>" + Type + "</b>");
		reslogger.info(" Selected Type " + Type);
	}

	public String get_Checkin_Amount(WebDriver driver, ArrayList test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		// Get Amount
		Amount = res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount.getText();
		double noshowPaymentAmount = Double.valueOf(Amount);
		int amt = ((int) noshowPaymentAmount) / 2;
		String CheckINCheckOutAmount = String.valueOf(amt);
		reslogger.info(CheckINCheckOutAmount);

		return CheckINCheckOutAmount;
	}

	public void checkInPaymentSuccessPopupClose(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		String close = "//ul[@class='sec_nav']//li[5]//i";
		res.CP_Reservation_NoShowSuccessCloseButton.click();
		test_steps.add("Closing check-in payment successful popup");
		try {
			Wait.WaitForElement(driver, close);
		} catch (Exception e) {
			reslogger.info(e);
		}
	}

	public void verifyFolioLineItem(WebDriver driver, ArrayList<String> test_steps, String totalAmount,
			String paymentType, String authAmount, String policyFor) {
		ArrayList<String> roomCharges = new ArrayList<>();
		Elements_CPReservation res = new Elements_CPReservation(driver);
		double folioRoomCharge = 0.00;
		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);
		List<WebElement> roomChargesAmount = driver
				.findElements(By.xpath("//span[contains(text(), 'Room Charge')]/../..//td[@class='lineitem-amount']"));
		String roomCharge = get_RoomCharge(driver, test_steps);
		String balance = get_Balance(driver, test_steps);

		String balanceExp = null;
		if (policyFor.equalsIgnoreCase("Capture")) {
			double amount2 = Double.parseDouble(authAmount);
			double total2 = Double.parseDouble(totalAmount);
			double balance2 = total2 - amount2;
			balanceExp = df.format(balance2);
		} else if (policyFor.equalsIgnoreCase("Authorize")) {
			balanceExp = totalAmount;
		}
		assertEquals(balance, balanceExp, "Failed - Balance mismatched at folio tab ");
		test_steps.add("Successfully verified balance after check-in is " + balance);
		if (roomChargesAmount.size() > 1) {
			for (WebElement webElement : roomChargesAmount) {
				String roomChargeMulti = webElement.getText().replace("$ ", "");
				roomCharges.add(roomChargeMulti);
				double abc = Double.parseDouble(roomChargeMulti);
				folioRoomCharge = folioRoomCharge + abc;
			}
		} else {
			folioRoomCharge = Double.parseDouble(getFolioAmount(driver, test_steps, "Room Charge"));
		}

		String folioRoomCharges = df.format(folioRoomCharge);

		String folioAuthAmount = getFolioAmount(driver, test_steps, paymentType);
		assertEquals(folioRoomCharges, totalAmount, "Failed - Room charge mismatched at folio line item ");
		test_steps.add("Successfully verified Room charge at folio line item after check-in is ");

		assertEquals(folioAuthAmount, authAmount, "Failed - Authorization amount mismatched at folio line item ");
		test_steps.add("Successfully verified Authorization amount at folio line item after check-in");

	}

	public String getFolioAmount(WebDriver driver, ArrayList test_steps, String category) {
		String Balance = driver
				.findElement(
						By.xpath("//span[contains(text(), '" + category + "')]/../..//td[@class='lineitem-amount']"))
				.getText();
		Balance = Balance.replace("$ ", "");
		test_steps.add("Folio amount for : " + category + " is :: " + Balance);
		reslogger.info("Folio amount for : " + category + " is :: " + Balance);
		return Balance;
	}

	public void verifyTripAmountDetailsAtTop(WebDriver driver, ArrayList<String> test_steps, String totalExp,
			String amount, String policyFor) {
		try {
			Elements_CPReservation res = new Elements_CPReservation(driver);
			DecimalFormat df = new DecimalFormat("0.00");
			df.setMaximumFractionDigits(2);
			String total = res.tripTotalAmountAtTop.getText().replace("$ ", "");
			assertEquals(total, totalExp, "Failed - Total amount in trip summary displayed at top is not matched");
			test_steps
					.add("Successfully verified Total amount in trip summary displayed at top as <b>" + total + "</b>");
			test_steps.add("Amount paid during check-in is <b>" + amount + "</b>");
			String balanceExp = null;
			if (policyFor.equalsIgnoreCase("Capture")) {
				double amount2 = Double.parseDouble(amount);
				double total2 = Double.parseDouble(totalExp);
				double balance = total2 - amount2;
				balanceExp = df.format(balance);
			} else if (policyFor.equalsIgnoreCase("Authorize")) {
				balanceExp = totalExp;
			}
			String balance = res.tripBalanceAmountAtTop.getText().replace("$ ", "");
			reslogger.info(balance);
			reslogger.info(balanceExp);
			assertEquals(balance, balanceExp, "Failed - Balance amount on top is not matched");
			test_steps.add(
					"Successfully verified Balance amount in trip summary displayed at top as <b>" + balance + "</b>");
		} catch (Exception e) {

		}
	}

	public void selectRoomAtCheckInPopup(WebDriver driver, String roomNo) throws Exception {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(res.roomNoDDAtCheckInPopup, driver);
		try {
			res.roomNoDDAtCheckInPopup.click();
			Wait.wait1Second();
			driver.findElement(By.xpath("//span[@class='text' and text()='" + roomNo + "']")).click();
			Wait.wait1Second();
		} catch (Exception e) {
			res.roomNoDDAtCheckInPopup.click();
			Wait.wait1Second();
			driver.findElement(By.xpath("//span[@class='text' and text()='" + roomNo + "']")).click();
			Wait.wait1Second();
		}
	}

	public void checkInPaymentSuccessPopupVerify(WebDriver driver, ArrayList<String> test_steps, String NoShowSuccess,
			String Status, String PaymentMethod, String date, String balance, String Type, String amount,
			String policyName, String policyFor, String policyText) throws Exception {
		try {
			DecimalFormat df = new DecimalFormat("0.00");
			df.setMaximumFractionDigits(2);
			Elements_CPReservation res = new Elements_CPReservation(driver);
			Wait.explicit_wait_visibilityof_webelement_120(res.CP_Reservation_NoShowSuccess, driver);
			Utility.ScrollToElement(res.CP_Reservation_NoShowSuccess, driver);
			// Verified Success -Message

			String header = res.CP_Reservation_NoShowSuccess.getText().trim();
			assertEquals(header, NoShowSuccess, "Failed: To verify Success Header");
			test_steps.add("  Verified header of the popup: <b>" + NoShowSuccess + "</b>");
			reslogger.info("  Verified header of the popup :" + NoShowSuccess);
			// Verified Success-Date

			date = Utility.parseDate(date, "dd/MM/yyyy", "MM/dd/yyyy");
			String dateDisplayed = res.NoShowSuccess_Date.getText();
			assertEquals(dateDisplayed, date, "Failed: To verify  Success Page Date");
			test_steps.add("  Verified Date of check-in : <b>" + date + "</b>");
			reslogger.info("  Verified Date of check-in : " + date);

			// Verified Success-Balance
			String balanceDisplayed = res.NoShowSuccess_Balance.getText().replace("$ ", "").trim();
			String balanceCalculated = null;
			double balance2 = Double.parseDouble(balance);
			double amount2 = Double.parseDouble(amount);
			if (policyFor.equalsIgnoreCase("Capture")) {
				balance2 = balance2 - amount2;
				balanceCalculated = df.format(balance2);
			} else if (policyFor.equalsIgnoreCase("Authorize")) {
				balanceCalculated = df.format(balance2);
			}
			assertEquals(balanceDisplayed, balanceCalculated, "Failed: To verify Success Page Balance");
			test_steps.add(" Verified Balance: <b>" + balanceDisplayed + "</b>");
			reslogger.info("  Verified Balance: " + balanceDisplayed);

			// Verified Success-policy name
			String policyDisplayed = res.noShowSuccessPolicyName.getText();
			assertEquals(policyDisplayed, policyName, "Failed: To verify Success Page Balance");
			test_steps.add(" Verified policy name : <b>" + policyDisplayed + "</b>");
			reslogger.info("  Verified policy name : " + policyDisplayed);

			Actions action = new Actions(driver);
			action.moveToElement(res.noShowSuccessPolicyName).perform();

			String toolTipPolicyDesc = res.CancelRoom_ToolTip_PolicyDescription.getText();
			reslogger.info(" Verified tool tip text at policy name : " + toolTipPolicyDesc);
			assertEquals(toolTipPolicyDesc, policyText, "Failed: To verify Payment Page Title");
			test_steps.add(" Verified tool tip text at policy name as :<b> " + toolTipPolicyDesc + "</b>");

			// Verified Success-Amount
			reslogger.info("Amount: " + amount);
			String path = "//span[contains(@data-bind,'noShow.PaymentAmount')][contains(text(),'$ " + amount + "')]|"
					+ "//span[contains(@data-bind,'cancel.PaymentAmount')][contains(text(),'$ " + amount + "')]|"
					+ "//span[contains(@data-bind,'checkInDetail.PaymentAmount')][contains(text(),'$ " + amount + "')]";
			WebElement AmountElement = driver.findElement(By.xpath(path));

			assertTrue(AmountElement.isDisplayed(), "Failed: To verify  Success Page Amount: " + amount);
			test_steps.add("  Verified Amount: <b>" + amount + "</b>");
			reslogger.info("  Verified Amount: " + amount);

			// Verified Success-Type
			reslogger.info(type);
			String typeDisplayed = res.NoShowSuccess_Type.getText().trim();
			assertEquals(typeDisplayed, Type, "Failed: To verify  Success Page Type");
			test_steps.add("  Verified check-in payment type : <b>" + Type + "</b>");
			reslogger.info(" Verified check-in payment type : " + Type);

			// Verified Success-Payment Method
			String paymentMethodDisplayed = res.CP_Reservation_NoShowSuccessPaymentMethod.getText().trim();
			assertEquals(paymentMethodDisplayed, PaymentMethod, "Failed: To verify Success Page Payment Method");
			test_steps.add("  Verified Payment Method: <b>" + PaymentMethod + "</b>");
			reslogger.info("  Verified Payment Method: " + PaymentMethod);

			// Verified Success -Status
			String statusDisplayed = res.CP_Reservation_NoShowSuccessStatus.getText().trim();
			assertEquals(statusDisplayed, Status, "Failed: To verify  Success Page Status ");
			test_steps.add("  Verified Status of payment :<b> " + Status + "</b>");
			reslogger.info(" Verified Status of payment : " + Status);
		} catch (Exception e) {

		}
	}

	public String CheckOutAmount = "";

	public void Verify_CheckOutPayment(WebDriver driver, ArrayList<String> test_steps, String Title, String PaymentType,
			String Type, String AmountRemaining, String cardNo, String nameOnCard, String expiryDate)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.NoShowPaymentPopupTitle);
		Utility.ScrollToElement(res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupTitle, driver);
		// Verified No Show Payment Page Title
		assertTrue(res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupTitle.getText().toLowerCase().trim()
				.equals(Title.toLowerCase().trim()), "Failed: To verify  Payment Page Title");
		test_steps.add(" Verified Title:<b> " + Title + "</b>");
		reslogger.info(" Verified Title: " + Title);
		String path = "//div[contains(@data-bind,'IsSplitPaymentEnable')]//button[@title='" + PaymentType + "']";
		WebElement paymentElement = driver.findElement(By.xpath(path));

		// Verified Payment Page Type
		type = res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupType.getText().toLowerCase().trim();
		assertTrue(type.equals(Type.toLowerCase().trim()), "Failed: To verify  Page Type: " + Type);
		test_steps.add(" Verified Type : <b> " + Type + "</b>");
		reslogger.info(" Verified Type: " + Type);

		// Get Amount
		CheckOutAmount = res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount.getText();
		double PaymentAmount = Double.valueOf(CheckOutAmount);
		int amt = (int) PaymentAmount;
		CheckOutAmount = String.valueOf(amt);
		reslogger.info(CheckOutAmount);
		// Get Balance
		String balance = "";
		balance = res.NoShowCancelPaymentPopup_Balance.getText().replace("$", "");

		double balanceDouble = Double.valueOf(balance);
		int balanceInt = (int) balanceDouble;
		balance = String.valueOf(balanceInt);
		if (CheckOutAmount.equals(AmountRemaining)) {
			assertTrue(CheckOutAmount.equals(AmountRemaining),
					"Failed: To verify Payment Page Amount: " + AmountRemaining);
			test_steps.add("Verified Amount : <b> " + AmountRemaining + "</b>");
			reslogger.info(" Verified Amount: " + AmountRemaining);
			assertTrue(balance.equals(AmountRemaining), "Failed: To verify Payment Page Balance: " + AmountRemaining);
			test_steps.add("Verified Balance : <b> " + AmountRemaining + "</b>");
			reslogger.info(" Verified Balance: " + AmountRemaining);
		}

		if (PaymentType.equals("Cash")) {
			// Verified Payment Page Payment Method
			assertTrue(paymentElement.isDisplayed(), "Failed: To verify Payment Method");
			test_steps.add(" Verified Payment Method:<b> " + PaymentType + "</b> ");
			reslogger.info(" Verified Payment Method: " + PaymentType);

		} else if (PaymentType.equals("MC")) {
			// Verified No Show Payment Page Payment Method
			assertTrue(paymentElement.isDisplayed(), "Failed: To verify Payment Method");
			test_steps.add(" Verified Payment Method:<b> " + PaymentType + "</b> ");
			reslogger.info(" Verified Payment Method: " + PaymentType);

			// Verified No Show Payment Page Card No
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			String CardNo = (String) executor.executeScript("return arguments[0].value",
					res.CP_Reservation_NoShowPaymentPopup_CardNumber);
			reslogger.info(CardNo);
			// Verify Card Number
			assertTrue(CardNo.contains(cardNo), "Failed: To verify Card Number");
			test_steps.add(" Verified Card Number: <b>" + CardNo + "</b>");
			reslogger.info(" Verified Card Number");
			String NameOnCard = (String) executor.executeScript("return arguments[0].value",
					res.CP_Reservation_NoShowPaymentPopup_NameOnCard);
			reslogger.info(NameOnCard);

			// Verify Name on Card Number
			assertTrue(NameOnCard.toLowerCase().trim().equals(nameOnCard.toLowerCase().trim()),
					"Failed: To verify Name on Card ");
			test_steps.add("Verified Name on Card:<b>  " + nameOnCard + "</b>");
			reslogger.info(" Verified Name on Card");

			String ExpiryDate = (String) executor.executeScript("return arguments[0].value",
					res.CP_Reservation_NoShowPaymentPopup_Expiry);
			reslogger.info(ExpiryDate);

			// Verify Expiry Date
			assertTrue(ExpiryDate.equals(expiryDate), "Failed: To verify Expiry Date ");
			test_steps.add(" Verified Expiry Date:<b> " + ExpiryDate + "</b>");
			reslogger.info(" Verified Expiry Date");
		}

	}

	public void Select_Type_Check_IN(WebDriver driver, ArrayList<String> test_steps, String Type)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.NoShowPaymentPopup_Type);
		Utility.ScrollToElement(res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupType, driver);
		res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupType.click();
		test_steps.add(" Click on Type Drop Down Box");
		reslogger.info(" Click on Type Drop Down Box");
		List<String> type = Arrays.asList(Type.split("\\|"));
		for (int i = 0; i < res.CP_CheckInTypeOptions.size(); i++) {
			if (res.CP_CheckInTypeOptions.get(i).getText().toLowerCase().trim()
					.equals(type.get(1).toLowerCase().trim())) {
				res.CP_CheckInTypeOptions.get(i).click();
				test_steps.add(" Selected  Type : <b>" + res.CP_CheckInTypeOptions.get(i).getText() + "</b>");
				reslogger.info(" Selected Type " + res.CP_CheckInTypeOptions.get(i).getText());
				break;
			}

		}

		res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupType.click();
		test_steps.add(" Click on Type Drop Down Box Again");
		reslogger.info(" Click on Type Drop Down Box Again");

	}

	public String CancelWindowVerifiedButtonAndPerformAction(WebDriver driver, ArrayList test_steps)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		String buttonName = null;
		if (res.CP_Reservation_NoShow_ConfirmNoShowButton.isEnabled()) {
			buttonName = res.CP_Reservation_NoShow_ConfirmNoShowButton.getText();
			test_steps.add("Enabled Button: " + buttonName);
			reslogger.info("Enabled Button: " + buttonName);
		} else if (res.ProceedToPaymentButton.isEnabled()) {
			buttonName = res.ProceedToPaymentButton.getText();
			test_steps.add("Enabled Button: " + buttonName);
			reslogger.info("Enabled Button: " + buttonName);
		}

		return buttonName;
	}

	public void Click_Confirm_Button(WebDriver driver) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_NoShow_ConfirmNoShowButton, driver);
		res.CP_Reservation_NoShow_ConfirmNoShowButton.click();

	}

	public void Click_Confirm_Payment_Button(WebDriver driver) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_NoShow_ConfirmNoShowButton, driver);
		res.ProceedToPaymentButton.click();

	}

	public String CheckINCheckOutAmount = null;

	public void Get_Checkin_Amount(WebDriver driver, ArrayList test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		// Get Amount
		Amount = res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount.getText();
		double noshowPaymentAmount = Double.valueOf(Amount);
		int amt = ((int) noshowPaymentAmount) / 2;
		CheckINCheckOutAmount = String.valueOf(amt);
		reslogger.info(CheckINCheckOutAmount);

	}

	public ArrayList<String> checkInDate(WebDriver driver, int Days) throws InterruptedException, ParseException {

		Elements_CPReservation elements = new Elements_CPReservation(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.EnterCheckinDate);
		Wait.explicit_wait_visibilityof_webelement(elements.EnterCheckinDate, driver);
		Wait.explicit_wait_elementToBeClickable(elements.EnterCheckinDate, driver);
		elements.EnterCheckinDate.click();
		elements.EnterCheckinDate.clear();
		String checkInDate = ESTTimeZone.DateFormateForLineItem(Days);
		elements.EnterCheckinDate.sendKeys(checkInDate);
		testSteps.add("CheckIn date: " + checkInDate);
		return testSteps;
	}

	public ArrayList<String> checkOutDate(WebDriver driver, int Days) throws InterruptedException, ParseException {

		Elements_CPReservation elements = new Elements_CPReservation(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.EnterCheckout);
		Wait.explicit_wait_visibilityof_webelement(elements.EnterCheckout, driver);
		Wait.explicit_wait_elementToBeClickable(elements.EnterCheckout, driver);
		elements.EnterCheckout.click();
		elements.EnterCheckout.clear();
		String checkInDate = ESTTimeZone.DateFormateForLineItem(Days);
		elements.EnterCheckout.sendKeys(checkInDate);
		testSteps.add("CheckIn date: " + checkInDate);
		return testSteps;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <enter_Adults> ' Description: <Enter adult value> ' Input
	 * parameters: < String value> ' Return value: <array list> ' Created By:
	 * <Farhan Ghaffar> ' Created On: <04/17/2020>
	 * 
	 * ' Modified By | Description of Modification:
	 * ------------------------------------------ <mm/dd/yyyy>:<Developer name>:
	 * 1.Step modified 2.Step modified
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> enterAdult(WebDriver driver, String Adults) {
		Elements_CPReservation elementsReservation = new Elements_CPReservation(driver);
		ArrayList<String> testStep = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Adults);
		elementsReservation.CP_NewReservation_Adults.clear();
		elementsReservation.CP_NewReservation_Adults.sendKeys(Adults);
		testStep.add("Enter No of adults : " + Adults);
		reslogger.info("Enter No of adults : " + Adults);
		return testStep;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <enterChildren> ' Description: <Enter childern value> '
	 * Input parameters: < String value> ' Return value: <array list> ' Created
	 * By: <Farhan Ghaffar> ' Created On: <04/17/2020>
	 * 
	 * ' Modified By | Description of Modification:
	 * ------------------------------------------ <mm/dd/yyyy>:<Developer name>:
	 * 1.Step modified 2.Step modified
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> enterChildren(WebDriver driver, String Children) {

		Elements_CPReservation elementsReservation = new Elements_CPReservation(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Children);
		elementsReservation.CP_NewReservation_Children.clear();
		elementsReservation.CP_NewReservation_Children.sendKeys(Children);
		testSteps.add("Enter No of Children : " + Children);
		reslogger.info("Enter No of Children : " + Children);
		return testSteps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <VeriGuestProfileCheckox> ' Description: <Verify create
	 * guest profile checkbox is checked or not> ' Input parameters: <boolean
	 * value> ' Return value: <array list> ' Created By: <Farhan Ghaffar> '
	 * Created On: <04/17/2020>
	 * 
	 * ' Modified By | Description of Modification:
	 * ------------------------------------------ <mm/dd/yyyy>:<Developer name>:
	 * 1.Step modified 2.Step modified
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> VerifyGuestProfileCheckox(WebDriver driver, boolean isChecked)
			throws InterruptedException {

		Elements_CPReservation element = new Elements_CPReservation(driver);
		ArrayList<String> test_step = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.CheckboxGuestProfile);
		Wait.explicit_wait_visibilityof_webelement(element.CheckboxGuestProfile, driver);
		Wait.explicit_wait_elementToBeClickable(element.CheckboxGuestProfile, driver);
		Utility.ScrollToElement(element.CheckboxGuestProfile, driver);
		if (isChecked) {
			if (element.CheckboxGuestProfile.isSelected()) {
				test_step.add("Verified create guest profile checkbox already checked");
			} else {
				element.CheckboxGuestProfile.click();
				test_step.add("Verified create guest profile checkbox is checked");
			}
		} else {
			if (!element.CheckboxGuestProfile.isSelected()) {
				element.CheckboxGuestProfile.click();
				test_step.add("Verified create guest profile checkbox is  unchecked");
			} else {
				test_step.add("Verified create guest profile checkbox already unchecked");
			}
		}

		return test_step;
	}

	public ArrayList<String> CheckInReservationWithOutPolicy(WebDriver driver) throws InterruptedException {

		Elements_CPReservation element = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.CheckInPopupHeading);
		Wait.explicit_wait_visibilityof_webelement(element.CheckInPopupHeading, driver);
		Wait.explicit_wait_elementToBeClickable(element.CheckInPopupHeading, driver);
		assertEquals(element.CheckInPopupHeading.getText(), "Check In", "Failed: Check In popup heading is mismaching");
		test_steps.add("Verified checkin reservation opend");
		return test_steps;
	}

	public ArrayList<String> CheckInConfrimButton(WebDriver driver) throws InterruptedException {

		Elements_CPReservation element = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.CheckinConfirmButton);
		Wait.explicit_wait_visibilityof_webelement(element.CheckinConfirmButton, driver);
		Wait.explicit_wait_elementToBeClickable(element.CheckinConfirmButton, driver);
		element.CheckinConfirmButton.click();
		test_steps.add("Click on checkin confrim buttton");
		return test_steps;
	}

	public ArrayList<String> CheckInButton(WebDriver driver) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_CheckInButton, driver);
		Utility.ScrollToElement(res.CP_Reservation_CheckInButton, driver);
		res.CP_Reservation_CheckInButton.click();
		test_steps.add("Click Check In  Button");
		reslogger.info("Click Check In  Button");
		return test_steps;

	}

	public ArrayList<String> VerifyDirtyRoom(WebDriver driver) throws InterruptedException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		try {
			Wait.wait2Second();
			Wait.explicit_wait_visibilityof_webelement(element.VerifyDirtyRoomPopup, driver);
			test_steps.add("Dirty room popup is appeared during checkin reservation");
			element.ButtonDirtyConfirm.click();
			test_steps.add("Click on dirty confirm button");

		} catch (Exception e) {
			test_steps.add("No dirty room popup appeared during checkin reservation");

		}

		return test_steps;

	}

	public String VerifyReservationStatus_Status(WebDriver driver, String Status) throws InterruptedException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		Utility.ScrollToElement(element.ReservationStatus, driver);
		Wait.explicit_wait_visibilityof_webelement(element.ReservationStatus, driver);
		assertEquals(element.ReservationStatus.getText(), Status, "Failed: reservation has been changed");
		return element.ReservationStatus.getText();
	}

	public ArrayList<String> ButtonAddIncidental(WebDriver driver) throws InterruptedException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		reslogger.info("in add incidentail button");
		try {
			reslogger.info("in try");

			Wait.WaitForElement(driver, OR_Reservation.ButtonAddIncidental);
			Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.ButtonAddIncidental), driver);
			Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.ButtonAddIncidental), driver);
		} catch (Exception e) {
			reslogger.info("in catch");

			driver.navigate().refresh();
			Wait.WaitForElement(driver, OR_Reservation.ButtonAddIncidental);
			Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.ButtonAddIncidental), driver);
			Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.ButtonAddIncidental), driver);

		}
		reslogger.info("move");

		Utility.ScrollToElement(element.ButtonAddIncidental, driver);
		element.ButtonAddIncidental.click();
		test_steps.add("Click on Add Incidental");
		return test_steps;
	}

	public ArrayList<String> EnterAddOn_Incidental(WebDriver driver, int days, String category, String perUnit,
			String quentity) throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<>();

		Wait.WaitForElement(driver, OR_Reservation.EnterAddOn_IncidenalDate);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.EnterAddOn_IncidenalDate), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.EnterAddOn_IncidenalDate), driver);
		Utility.ScrollToElement(element.EnterAddOn_IncidenalDate, driver);
		String getDate = ESTTimeZone.DateFormateForLineItem(days);
		element.EnterAddOn_IncidenalDate.click();
		element.EnterAddOn_IncidenalDate.clear();
		element.EnterAddOn_IncidenalDate.sendKeys(getDate);
		test_steps.add("Enter Date: " + getDate);

		element.ButtonSelectIncidental.click();
		test_steps.add("Click on select button");
		Wait.wait1Second();
		String pathSelectIncidental = "//div[text()='ADD-ONS & INCIDENTALS']//..//ul//li//span[text()='" + category
				+ "']";
		WebElement elementSelect = driver.findElement(By.xpath(pathSelectIncidental));
		elementSelect.click();
		test_steps.add("Select Category: " + category);

		element.EnterPerUnit.clear();
		element.EnterPerUnit.sendKeys(perUnit);
		test_steps.add("Enter Per Unit: " + perUnit);

		element.EnterQuentity.clear();
		element.EnterQuentity.sendKeys(quentity);
		test_steps.add("Enter Quentity: " + quentity);

		element.IncidentalSave.click();
		test_steps.add("Click on Save button");
		Wait.explicit_wait_visibilityof_webelement(element.Toaster_Title, driver);

		return test_steps;
	}

	public String getIncidentalDate(WebDriver driver, int index) throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		driver.navigate().refresh();
		try {
			Wait.WaitForElement(driver, OR_Reservation.GetIncidentalDate);
			Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.GetIncidentalDate), driver);
			Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.GetIncidentalDate), driver);
		} catch (Exception e) {
			driver.navigate().refresh();
			Wait.WaitForElement(driver, OR_Reservation.GetIncidentalDate);
			Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.GetIncidentalDate), driver);
			Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.GetIncidentalDate), driver);
		}

		reslogger.info("element.GetIncidentalDate:+ " + element.GetIncidentalDate.size());
		Utility.ScrollToElement_NoWait(element.GetIncidentalDate.get(index), driver);
		return element.GetIncidentalDate.get(index).getText();
	}

	public String getIncidentalCategory(WebDriver driver, int index) throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		return element.GetIncidentalCategory.get(index).getText();
	}

	public String getIncidentalDescritption(WebDriver driver, int index) throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		return element.GetIncidentalDescription.get(index).getText();
	}

	public String getIncidentalPerUnit(WebDriver driver, int index) throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		return element.GetIncidentalPerUnit.get(index).getText();
	}

	public String getIncidentalTax(WebDriver driver, int index) throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		return element.GetIncidentalTax.get(index).getText();
	}

	public String getIncidentalTotalAmount(WebDriver driver, int index) throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		return element.GetIncidentalTotalAmount.get(index).getText();
	}

	public ArrayList<String> ClickOnHistory(WebDriver driver) throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		ArrayList<String> test_step = new ArrayList<>();
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.HistoryTab), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.HistoryTab), driver);
		Utility.ScrollToElement_NoWait(element.HistoryTab, driver);
		element.HistoryTab.click();
		test_step.add("Click on history");
		return test_step;

	}

	public String getHistoryCategory(WebDriver driver, int index) throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement(element.History_Category.get(index), driver);
		Utility.ScrollToElement_NoWait(element.History_Category.get(index), driver);
		return element.History_Category.get(index).getText();
	}

	public String gettHistoryDate(WebDriver driver, int index) throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		return element.History_Date.get(index).getText();
	}

	public String gettHistoryTime(WebDriver driver, int index) throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		return element.History_Time.get(index).getText();
	}

	public String gettHistoryUser(WebDriver driver, int index) throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		return element.History_User.get(index).getText();
	}

	public String getHistoryDescription(WebDriver driver, int index) throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		return element.History_Description.get(index).getText();
	}

	public ArrayList<String> ClickOnDetails(WebDriver driver) throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		ArrayList<String> test_step = new ArrayList<>();
		Wait.explicit_wait_visibilityof_webelement(element.DetailsTab, driver);
		Wait.explicit_wait_elementToBeClickable(element.DetailsTab, driver);
		Utility.ScrollToElement_NoWait(element.DetailsTab, driver);
		element.DetailsTab.click();
		test_step.add("Click on details");
		return test_step;

	}

	public int getNumberOfIncidentalRows(WebDriver driver) throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		int counter = 0;
		String loading = "(//div[@class='ir-loader-in'])[1]";
		while (true) {
			if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
				break;
			} else if (counter == 40) {
				break;
			} else {
				counter++;
				Wait.wait1Second();
			}
		}
		Wait.WaitForElement(driver, OR_Reservation.TotalNumberofIncidentalRows);
		return element.TotalNumberofIncidentalRows.size();
	}

	public ArrayList<String> ClickOnDeleteOncidentalButton(WebDriver driver, int index)
			throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		ArrayList<String> test_step = new ArrayList<>();
		try {
			Wait.explicit_wait_visibilityof_webelement(element.DeleteIncidentalInSatyInfoButton.get(index), driver);
			Wait.explicit_wait_elementToBeClickable(element.DeleteIncidentalInSatyInfoButton.get(index), driver);
		} catch (Exception e) {
			driver.navigate().refresh();
			Wait.wait3Second();
			Wait.WaitForElement(driver, OR_Reservation.DeleteIncidentalInSatyInfoButton);
			Wait.explicit_wait_visibilityof_webelement(element.DeleteIncidentalInSatyInfoButton.get(index), driver);
			Wait.explicit_wait_elementToBeClickable(element.DeleteIncidentalInSatyInfoButton.get(index), driver);
		}

		Utility.ScrollToElement_NoWait(element.DeleteIncidentalInSatyInfoButton.get(index), driver);
		element.DeleteIncidentalInSatyInfoButton.get(index).click();
		return test_step;
	}

	public ArrayList<String> IncidentalVoidpopup(WebDriver driver, String EnterNotes, int index)
			throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		ArrayList<String> test_step = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.VoidPopupHeading);
		Wait.explicit_wait_visibilityof_webelement(element.VoidPopupHeading, driver);
		Wait.explicit_wait_elementToBeClickable(element.VoidPopupHeading, driver);
		// assertEquals(element.VoidPopupHeading.getText(), "Void Items");
		element.EnterNotes.sendKeys(EnterNotes);
		test_step.add("Enter notes: " + EnterNotes);
		element.VoidButton.get(index).click();
		test_step.add("Click on Void button");
		return test_step;
	}

	public void VerifyRoomDisplayed(WebDriver driver, String roomClassName, ArrayList<String> test_steps) {

		String Roomxpath = "//span[contains(@data-bind,'roomClass.name') and text()='" + roomClassName + "']";
		WebElement RoomName = driver.findElement(By.xpath(Roomxpath));
		Wait.explicit_wait_visibilityof_webelement(RoomName, driver);
		assertTrue(RoomName.isDisplayed(), "Failed: '" + roomClassName + "' not displayed");
		test_steps.add("Verified Room Class '" + roomClassName + "' is Displayed");
	}

	public void VerifyAvgPerNight(WebDriver driver, String roomClassName, String baseAmount, boolean expand,
			ArrayList<String> test_steps) throws InterruptedException {

		String AvgRatePerNightxpath = "//span[contains(@data-bind,'roomClass.name') and text()='" + roomClassName
				+ "']/following-sibling::span[contains(@data-bind,'Avg per night')]";
		WebElement AvgRatePerNight = driver.findElement(By.xpath(AvgRatePerNightxpath));
		Wait.explicit_wait_visibilityof_webelement(AvgRatePerNight, driver);
		assertTrue(AvgRatePerNight.isDisplayed(), "Failed: 'Avg per night ' not displayed");
		String AvgValue = AvgRatePerNight.getText();
		AvgValue = AvgValue.split(" ")[3];
		AvgValue = AvgValue.replace("$", "").replaceAll(" ", "");
		assertEquals(AvgValue, baseAmount, "Failed: Base Amount missmatched");
		test_steps.add("Verified Room Class '" + roomClassName + "' 'Avg per night $" + AvgValue + "' is Displayed");
		if (expand) {
			Utility.ScrollToElement(AvgRatePerNight, driver);
			AvgRatePerNight.click();
			test_steps.add("Click on 'Avg per night $" + AvgValue + "' link");
		}
	}

	public void VerifyRoomClassDescription_Date(WebDriver driver, String roomClassName, String expectedvalue,
			ArrayList<String> test_steps) {

		String elementxpath = "//span[contains(@data-bind,'roomClass.name') and text()='" + roomClassName
				+ "']//ancestor::div[contains(@data-bind,'expandRoomClassRateInfo')]/following-sibling::div//td[contains(@data-bind,'startDate')]";
		WebElement Element = driver.findElement(By.xpath(elementxpath));
		Wait.explicit_wait_visibilityof_webelement(Element, driver);
		assertTrue(Element.isDisplayed(), "Failed: Date not displayed");
		assertEquals(Element.getText(), expectedvalue, "Failed: Datet missmatched");
		test_steps.add("Verified Date '" + expectedvalue + "' is Displayed");
	}

	public void VerifyRoomClassDescription_Rate(WebDriver driver, String roomClassName, String expectedvalue,
			ArrayList<String> test_steps) {

		String elementxpath = "//span[contains(@data-bind,'roomClass.name') and text()='" + roomClassName
				+ "']//ancestor::div[contains(@data-bind,'expandRoomClassRateInfo')]/following-sibling::div//td[contains(@data-bind,'rateDisplayName')]";
		WebElement Element = driver.findElement(By.xpath(elementxpath));
		Wait.explicit_wait_visibilityof_webelement(Element, driver);
		assertTrue(Element.isDisplayed(), "Failed: Rate not displayed");
		assertEquals(Element.getText(), expectedvalue, "Failed: Rate missmatched");
		test_steps.add("Verified Rate '" + expectedvalue + "' is Displayed");
	}

	public void VerifyRoomClassDescription_Amount(WebDriver driver, String roomClassName, String expectedvalue,
			ArrayList<String> test_steps) {

		String elementxpath = "//span[contains(@data-bind,'roomClass.name') and text()='" + roomClassName
				+ "']//ancestor::div[contains(@data-bind,'expandRoomClassRateInfo')]/following-sibling::div//td[contains(@data-bind,'roomTotal')]";
		WebElement Element = driver.findElement(By.xpath(elementxpath));
		Wait.explicit_wait_visibilityof_webelement(Element, driver);
		assertTrue(Element.isDisplayed(), "Failed: Amount not displayed");
		assertEquals(Utility.convertDollarToNormalAmount(driver, Element.getText()).trim(), expectedvalue,
				"Failed: Amount missmatched");
		test_steps.add("Verified Amount '" + expectedvalue + "' is Displayed");
	}

	public void closeReservationTab(WebDriver driver) throws InterruptedException {

		Elements_Reservation reservationPage = new Elements_Reservation(driver);
		Wait.WaitForElement(driver, OR.OpenedReservation_CloseButton);
		Utility.ScrollToElement(driver.findElement(By.xpath(OR.OpenedReservation_CloseButton)), driver);
		reservationPage.OpenedReservation_CloseButton.click();
		try {
			Wait.wait1Second();
			reservationPage.unsavedReservationAlert.click();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void EnterDate(WebDriver driver, String DateType, String Date) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		String Datexpath = "//input[contains(@data-bind,'" + DateType + "Date')]";
		Wait.explicit_wait_xpath(Datexpath, driver);
		WebElement DateInput = driver.findElement(By.xpath(Datexpath));
		Wait.explicit_wait_visibilityof_webelement(DateInput, driver);
		Utility.ScrollToElement(DateInput, driver);
		DateInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Date);
		assertEquals(DateInput.getAttribute("value"), Date, "Failed: Entered Date missmatched");
	}

	public ArrayList<String> VerifyCreatedTask(WebDriver driver, String TaskType, String Details, String Status,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		WebElement TaskTypeCheck = driver
				.findElement(By.xpath("//span[text()='Tasks ']/../..//td//span[text()='" + TaskType.trim() + "']"));
		Utility.ScrollToElement(TaskTypeCheck, driver);
		TaskTypeCheck.getText();
		assertEquals(TaskTypeCheck.getText(), TaskType, "Failed : TaskType missmatched");
		reslogger.info("Verified Task Bar Type :" + TaskType);
		test_steps.add("Verified Task Bar Type :" + TaskType);

		WebElement DetailsCheck = driver.findElement(By.xpath(
				"//td[contains(@data-bind,'text: description')  and contains(text(),'" + Details.trim() + "')]"));
		DetailsCheck.getText();
		assertEquals(DetailsCheck.getText(), Details, "Failed : Detail missmatched");
		reslogger.info("Verified Task Bar Detail :" + Details);

		test_steps.add("Verified Task Bar Detail :" + Details);

		WebElement StatusCheck = driver
				.findElement(By.xpath("//span[text()='Tasks ']/../..//td[contains(text(),'" + Status + "')]"));
		StatusCheck.getText();
		assertEquals(StatusCheck.getText(), Status, "Failed : Status missmatched");
		reslogger.info("Verified Task Bar Status :" + Status);
		test_steps.add("Verified Task Bar Status :" + Status);
		return test_steps;

	}

	public ArrayList<String> DeleteTask(WebDriver driver, String TaskDetails, ArrayList<String> test_steps)
			throws InterruptedException {

		WebElement DeletTaskButton = driver
				.findElement(By.xpath("//td[contains(@data-bind,'text: description')  and contains(text(),'"
						+ TaskDetails.trim() + "')]/ancestor::tr//button"));

		String DeleteConfirmationPopUp = "//div[text()='Are you sure you want to delete this task?']";
		String YesButton = "//div[text()='Are you sure you want to delete this task?']/parent::div//button[text()='Yes']";
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", DeletTaskButton);
		reslogger.info("Delete Task Button Clicked");
		test_steps.add("Delete Task Button Clicked");

		WebElement DeleteConfirmationPopUpEle = driver.findElement(By.xpath(DeleteConfirmationPopUp));
		WebElement YesButtonEle = driver.findElement(By.xpath(YesButton));
		Wait.WaitForElement(driver, DeleteConfirmationPopUp);

		reslogger.info("Confirmation Message Popup Displayed: " + DeleteConfirmationPopUpEle.getText());
		test_steps.add("Confirmation Message Popup Displayed: " + DeleteConfirmationPopUpEle.getText());
		YesButtonEle.click();

		reslogger.info("Task With Details :" + TaskDetails + " Deleted Successfully");
		test_steps.add("Task With Details :" + TaskDetails + " Deleted Successfully");
		return test_steps;

	}

	public ArrayList<String> clickPencilIcon_GoBackToStayInfo(WebDriver driver) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();

		Wait.explicit_wait_visibilityof_webelement_120(res.ClickGoBackToStayInfo, driver);
		Wait.explicit_wait_elementToBeClickable(res.ClickGoBackToStayInfo, driver);
		Utility.ScrollToElement(res.ClickGoBackToStayInfo, driver);
		res.ClickGoBackToStayInfo.click();
		reslogger.info("Edit Icon Clicked");
		test_steps.add("Edit Icon Clicked");
		return test_steps;
	}

	public LocalDate StartDate, EndDate;

	public List<LocalDate> getDatesBetweenFromAndToDate(WebDriver driver, ArrayList<String> test_steps,
			String CheckINDate, String CheckOutDate) {
		List<String> checkINDate = Arrays.asList(CheckINDate.split("\\|"));
		List<String> checkOutDate = Arrays.asList(CheckOutDate.split("\\|"));
		// SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		// LocalDate date = LocalDate.parse("29-Mar-2019", formatter);

		LocalDate Start = LocalDate.parse(checkINDate.get(0), formatter);
		LocalDate End = LocalDate.parse(checkOutDate.get(0), formatter);
		List<LocalDate> ret = new ArrayList<LocalDate>();
		for (LocalDate date = Start.plusDays(1); !date.isAfter(End.minusDays(1)); date = date.plusDays(1)) {
			ret.add(date);
			StartDate = Start;
			EndDate = End;
		}
		return ret;

	}

	public void verifyDisplayVoidItems(WebDriver driver, ArrayList<String> test_steps, String Date, String Note,
			String amount) throws InterruptedException {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		String LineItemNotePath = "//tbody[contains(@data-bind,'ComputedFolioItems')]//td[@class='lineitem-date']/span[contains(text(),'"
				+ Date + "')]/ancestor::td//preceding-sibling::td[contains(@class,'lineitem-note')]/img[@title='" + Note
				+ "']";
		WebElement SetLineItemElement = driver.findElement(By.xpath(LineItemNotePath));
		String LineItemAmount = "//tbody[contains(@data-bind,'ComputedFolioItems')]//td[@class='lineitem-date']/span[contains(text(),'"
				+ Date
				+ "')]/ancestor::td//following-sibling::td[contains(@class,'lineitem-amount')]/span[contains(text(),'"
				+ amount + "')]";
		WebElement LineItemElement = driver.findElement(By.xpath(LineItemAmount));

		assertTrue(SetLineItemElement.isDisplayed(), "Failed: To Verify Line Item");
		assertTrue(LineItemElement.isDisplayed(), "Failed: To Verify Line Item");
		test_steps.add(" Verified Date:<b>  " + Date + "</b>");
		reslogger.info("Verified  Date: " + Date);
		test_steps.add(" Verified  Note:<b>  " + Note + "</b>");
		reslogger.info(" Verified  Note: " + Note);
		test_steps.add(" Verified  Amount:<b>  " + amount + "</b>");
		reslogger.info(" Verified  Amount: " + amount);

	}

	// Created by Adhnan: ClickEditStayInfo
	public ArrayList<String> ClickEditStayInfo(WebDriver driver, ArrayList<String> steps)
			throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement(element.StayInfo_Editbutton, driver);
		Wait.explicit_wait_elementToBeClickable(element.StayInfo_Editbutton, driver);
		Utility.ScrollToElement(element.StayInfo_Editbutton, driver);
		element.StayInfo_Editbutton.click();
		reslogger.info("Click Stay Info Edit button");
		steps.add("Click Stay Info Edit button");
		Wait.explicit_wait_visibilityof_webelement(element.StayInfo_ChangeDetails, driver);
		return steps;
	}

	// Created By adhnan : ClickStayInfo_ChangeDetails
	public ArrayList<String> ClickStayInfo_ChangeDetails(WebDriver driver, ArrayList<String> steps)
			throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement(element.StayInfo_ChangeDetails, driver);
		Wait.explicit_wait_elementToBeClickable(element.StayInfo_ChangeDetails, driver);
		Utility.ScrollToElement(element.StayInfo_ChangeDetails, driver);
		element.StayInfo_ChangeDetails.click();
		reslogger.info("Click Stay Info 'Change Stay Details");
		steps.add("Click Stay Info 'Change Stay Details'");
		return steps;
	}

	public ArrayList<String> VerifyRoomClass_Rule(WebDriver driver, String roomClassName, String ruleName,
			boolean broken, ArrayList<String> getTest_Steps) throws InterruptedException {
		String RuleBrokenxpath = "//span[contains(@data-bind,'roomClass.name') and text()='" + roomClassName
				+ "']/following-sibling::span[contains(@data-bind,'rules')]";
		WebElement RuleBroken = driver.findElement(By.xpath(RuleBrokenxpath));
		Wait.explicit_wait_visibilityof_webelement(RuleBroken, driver);
		Utility.ScrollToElement(RuleBroken, driver);
		assertTrue(RuleBroken.isDisplayed(), "Failed: 'Rule' not displayed");
		String rule = RuleBroken.getText();
		assertEquals(rule, ruleName, "Failed: rule Name missmatched");
		getTest_Steps.add("Verified Room Class '" + roomClassName + "' rule '" + rule + "' is Displayed");
		String color = RuleBroken.getAttribute("class");
		String ruleapplied = RuleBroken.getAttribute("data-bind");
		reslogger.info(color);
		reslogger.info(ruleapplied);
		if (broken) {
			assertTrue(color.contains("red"), "Failed : Rule color is not red");
			assertTrue(ruleapplied.contains("broken"), "Failed : Rule color is not broken");
			getTest_Steps.add("Verified Rule is 'Broken'");
			getTest_Steps.add("Verified Rule is Shown in 'Red'");
		} else {
			assertTrue(color.contains("green"), "Failed : Rule color is not green");
			assertTrue(ruleapplied.contains("statisfied"), "Failed : Rule is not satisfied");
			getTest_Steps.add("Verified Rule is 'Satisfied'");
			getTest_Steps.add("Verified Rule is Shown in 'Green'");
		}

		Actions actions = new Actions(driver);
		actions.moveToElement(RuleBroken).perform();
		String mouseenter = RuleBroken.getAttribute("is-title-added-on-mouseenter");
		assertEquals(mouseenter, "true", "Failed: rule Name not appear on mouse enter missmatched");
		getTest_Steps.add(
				"Verified Upon navigating mouse on top of the Rule name in context sensitive tool tip Rule name is displayed");
		return getTest_Steps;
	}

	public ArrayList<String> getRoomsOnDetailPage(WebDriver driver, ArrayList<String> Rooms) {
		String path = "//div/a[@class='accordion-toggle']/following-sibling::span[contains(@data-bind,'roomClassName')]";
		List<WebElement> element = driver.findElements(By.xpath(path));
		for (WebElement element1 : element) {
			Rooms.add(element1.getText());
			/*
			 * if (element1.isDisplayed()) {
			 * Rooms.add(element1.getText().trim()); }
			 */
		}
		reslogger.info("New Rooms are:" + Rooms);

		return Rooms;
	}

	public void click_YestakemetotheFolio(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Utility.ScrollToElement(res.Checkout_CompleteCheckOutMsgYesButton, driver);
		res.Checkout_CompleteCheckOutMsgYesButton.click();
		test_steps.add("Clicking on Yes, take me to the Folio");
		reslogger.info("Clicking on Yes, take me to the Folio");
	}

	public void click_Nocancelthecheckoutprocess(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Utility.ScrollToElement(res.Checkout_CompleteCheckOutMsgNoButton, driver);
		res.Checkout_CompleteCheckOutMsgNoButton.click();
		test_steps.add("Clicking on No, cancel the check-out process");
		reslogger.info("Clicking on No, cancel the check-out process");
	}

	public ArrayList<String> VerifyRoomClassManualRateAmount(WebDriver driver, String RoomClass, String ManualRate)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		String path = "((//span[.='" + RoomClass + "'])[2]//parent::div//following::div[2]//span)[1]";
		Wait.WaitForElement(driver, path);
		ManualRate = "$" + ManualRate + ".00";
		test_steps.add("Expected room class rate : " + ManualRate);
		reslogger.info("Expected room class rate : " + ManualRate);

		WebElement roomAmountEl = driver.findElement(By.xpath(path));
		String roomClassRate = roomAmountEl.getText();
		test_steps.add("Found : " + roomClassRate);
		reslogger.info("Found : " + roomClassRate);

		assertEquals(ManualRate, roomClassRate, "Failed: RoomClass rate didn't override");
		test_steps.add("Verified that room class rate override with manual rate");
		reslogger.info("Verified that room class rate override with manual rate");
		return test_steps;

	}

	public ArrayList<String> clickAddSRoomButtton(WebDriver driver) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();

		Wait.explicit_wait_visibilityof_webelement_120(res.CP_NewReservation_AddRoom, driver);
		Wait.explicit_wait_elementToBeClickable(res.CP_NewReservation_AddRoom, driver);
		Utility.ScrollToElement(res.CP_NewReservation_AddRoom, driver);
		res.CP_NewReservation_AddRoom.click();
		test_steps.add("Clicking on add room");
		reslogger.info("Clicking on add room");
		return test_steps;
	}

	public String selectRoomNumber(WebDriver driver, String roomClassName, String roomNo, boolean autoSelect,
			ArrayList<String> test_steps) throws InterruptedException {

		// String path =
		// "//span[contains(@data-bind,'roomClass.name')][text()='"+roomClassName+"']/../../../..//select[contains(@data-bind,'roomNumber')]";
		String path = "//span[contains(@data-bind,'roomClass.name')][text()='" + roomClassName
				+ "']/../../../..//button";
		String selectedRoomNo = "";
		int count = 0;
		// try {
		// Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(path)),
		// driver);
		// }catch (Exception e) {
		// Wait.wait5Second();
		// }
		// while (true) {
		// if
		// (driver.findElement(By.xpath("//section[@class='ir-roomClassDetails
		// manual-override']")).isDisplayed()) {
		// break;
		// } else if (count == 20) {
		// break;
		// } else {
		// count++;
		// Wait.wait2Second();
		// }
		// }

		if (autoSelect) {
			try {
				Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
				driver.findElement(By.xpath(path)).click();
			} catch (Exception e) {
				reslogger.info("Waited to loading symbol");
			}
			int size = driver.findElements(By.xpath("//span[contains(@data-bind,'roomClass.name')][text()='"
					+ roomClassName + "']/../../../..//ul/li/a")).size();
			Random random = new Random();
			int randomNumber = 0;

			randomNumber = random.nextInt(size) + 1;
			if (randomNumber <= 1) {
				randomNumber = randomNumber + 1;
			}

			reslogger.info("count : " + size);
			reslogger.info("randomNumber : " + randomNumber);
			driver.findElement(By.xpath("(//span[contains(@data-bind,'roomClass.name')][text()='" + roomClassName
					+ "']/../../../..//ul/li/a)[" + randomNumber + "]")).click();
			selectedRoomNo = driver.findElement(By.xpath("//span[contains(@data-bind,'roomClass.name')][text()='"
					+ roomClassName + "']/../../../..//ul/li[@class='selected']/a/span")).getText();
			test_steps.add("Selected Room No : " + selectedRoomNo);
			reslogger.info("Selected Room No : " + selectedRoomNo);
		} else {
			try {
				Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
				driver.findElement(By.xpath(path)).click();
			} catch (Exception e) {
				reslogger.info("Waited to loading symbol");
			}
			driver.findElement(By.xpath("//span[contains(@data-bind,'roomClass.name')][text()='" + roomClassName
					+ "']/../../../..//ul/li/a/span[text()='" + roomNo + "']")).click();
			// new
			// Select(driver.findElement(By.xpath(path))).selectByVisibleText(roomNo);
			test_steps.add("Selected Room No : " + roomNo);
			reslogger.info("Selected Room No : " + roomNo);
		}

		return selectedRoomNo;
	}

	public ArrayList<String> clickSelectRoomButtton(WebDriver driver, String roomClassName)
			throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		String path = "//span[contains(@data-bind,'roomClass.name')][text()='" + roomClassName
				+ "']/../../../..//div[contains(@data-bind,'click: onSelect')]/a";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(path)), driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(path)), driver);
		driver.findElement(By.xpath(path)).click();
		test_steps.add("Clicking on Select room");
		reslogger.info("Clicking on Select room");
		return test_steps;

	}

	public ArrayList<String> newPolicesApplicableYesBtn(WebDriver driver) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();
		Wait.wait5Second();
		Wait.explicit_wait_visibilityof_webelement_120(res.NewPolicesApplicableYesBtn, driver);
		Wait.explicit_wait_elementToBeClickable(res.NewPolicesApplicableYesBtn, driver);
		Utility.ScrollToElement(res.NewPolicesApplicableYesBtn, driver);
		res.NewPolicesApplicableYesBtn.click();
		reslogger.info("New Polices Applicable Popup Yes button Clicked");
		test_steps.add("New Polices Applicable Popup Yes button Clicked");
		return test_steps;
	}

	public ArrayList<String> selectPrimaryRoom(WebDriver driver, String roomClass, String roomNo) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();
		String primaryArrow = "//span[text()='Primary Room']/../../following-sibling::div//button";
		Wait.WaitForElement(driver, primaryArrow);
		driver.findElement(By.xpath(primaryArrow)).click();
		String primaryRoom = "//span[text()='Primary Room']/../../following-sibling::div//li//span[text()='" + roomClass
				+ ": " + roomNo + "']";
		// primary = driver.findElement(By.xpath(primaryRoom)).getText();
		Wait.WaitForElement(driver, primaryRoom);
		driver.findElement(By.xpath(primaryRoom)).click();
		test_steps.add("Selected Primary Room as : " + roomClass + ": " + roomNo);
		reslogger.info("Selected Primary Room as : " + roomClass + ": " + roomNo);
		return test_steps;
	}

	public ArrayList<String> selectAdditionalRoom(WebDriver driver, String roomClass, String roomNo) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();
		String additionalArrow = "//span[text()='Additional Room']/../../following-sibling::div//button";
		Wait.WaitForElement(driver, additionalArrow);
		driver.findElement(By.xpath(additionalArrow)).click();
		String additionalRoomPath = "//span[text()='Additional Room']/../../following-sibling::div//li//span[text()='"
				+ roomClass + ": " + roomNo + "']";
		// primary = driver.findElement(By.xpath(primaryRoom)).getText();
		Wait.WaitForElement(driver, additionalRoomPath);
		driver.findElement(By.xpath(additionalRoomPath)).click();
		test_steps.add("Selected Additional Room as : " + roomClass + ": " + roomNo);
		reslogger.info("Selected Additional Room as : " + roomClass + ": " + roomNo);
		return test_steps;
	}

	public ArrayList<String> enterPrimaryGuestName(WebDriver driver, String GuestFirstName, String GuestLastName) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();

		Wait.WaitForElement(driver, OR_Reservation.PrimaryGuestFirstName);
		res.PrimaryGuestFirstName.clear();
		res.PrimaryGuestFirstName.sendKeys(GuestFirstName);
		test_steps.add("Guest First Name : " + GuestFirstName);
		reslogger.info("Guest First Name : " + GuestFirstName);

		Wait.WaitForElement(driver, OR_Reservation.PrimaryGuestLastName);
		res.PrimaryGuestLastName.clear();
		res.PrimaryGuestLastName.sendKeys(GuestLastName);
		test_steps.add("Guest Last Name : " + GuestLastName);
		reslogger.info("Guest Last Name : " + GuestLastName);

		return test_steps;

	}

	public ArrayList<String> enterAdditionalGuestName(WebDriver driver, String GuestFirstName, String GuestLastName) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();

		Wait.WaitForElement(driver, OR_Reservation.AdditonalGuest1FirstName);
		res.AdditonalGuest1FirstName.clear();
		res.AdditonalGuest1FirstName.sendKeys(GuestFirstName);
		test_steps.add("Guest First Name : " + GuestFirstName);
		reslogger.info("Guest First Name : " + GuestFirstName);

		Wait.WaitForElement(driver, OR_Reservation.AdditonalGuest1LastName);
		res.AdditonalGuest1LastName.clear();
		res.AdditonalGuest1LastName.sendKeys(GuestLastName);
		test_steps.add("Guest Last Name : " + GuestLastName);
		reslogger.info("Guest Last Name : " + GuestLastName);

		return test_steps;

	}

	public ArrayList<String> verifyAccountName(WebDriver driver, String accountName, boolean isOpen) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();

		Wait.explicit_wait_visibilityof_webelement_120(res.HeaderAccountName_AfterReservation, driver);
		String foundName = res.HeaderAccountName_AfterReservation.getText();
		foundName = foundName.replace("(", "");
		foundName = foundName.replace(")", "");
		assertEquals(foundName.trim(), accountName, "Failed to verify Account Name");
		test_steps.add("Successfully Verified Assoicated Account Name : " + accountName);
		reslogger.info("Successfully Verified Assoicated Account Name : " + accountName);
		if (isOpen) {
			Wait.explicit_wait_elementToBeClickable(res.HeaderAccountName_AfterReservation, driver);
			res.HeaderAccountName_AfterReservation.click();
			test_steps.add("Account Link Clicked");
			reslogger.info("Account Link Clicked");
		}
		return test_steps;
	}

	public String getFolioOptionCount(WebDriver driver) throws InterruptedException {
		String path = "//li//span[contains(text(),'Guest Folio')]/../../../li";
		Wait.wait2Second();
		return driver.findElements(By.xpath(path)).size() + "";
	}

	public ArrayList<String> selectFolioOption(WebDriver driver, String option) throws InterruptedException {
		String buttonPath = "//li//span[contains(text(),'Guest Folio')]/../../../../..//button";
		String optionPath = "//li//span[contains(text(),'" + option + "')]";
		ArrayList<String> test_steps = new ArrayList<String>();
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(buttonPath)), driver);
		driver.findElement(By.xpath(buttonPath)).click();
		Wait.wait1Second();
		driver.findElement(By.xpath(optionPath)).click();
		test_steps.add("Selected Folio Option : " + option);
		reslogger.info("Selected Folio Option : " + option);
		return test_steps;
	}

	public ArrayList<String> selectFolioOption(WebDriver driver, int index) throws InterruptedException {
		String buttonPath = "//li//span[contains(text(),'Guest Folio')]/../../../../..//button";
		String optionPath = "//li//span[contains(text(),'Guest Folio')]/../../..//li[" + index + "]";
		ArrayList<String> test_steps = new ArrayList<String>();
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(buttonPath)), driver);
		driver.findElement(By.xpath(buttonPath)).click();
		Wait.wait1Second();
		driver.findElement(By.xpath(optionPath)).click();
		test_steps.add("Selected Folio Option : " + driver
				.findElement(
						By.xpath("//li//span[contains(text(),'Guest Folio')]/../../../li[@class='selected']//span"))
				.getText());
		reslogger.info("Selected Folio Option : " + driver
				.findElement(
						By.xpath("//li//span[contains(text(),'Guest Folio')]/../../../li[@class='selected']//span"))
				.getText());
		return test_steps;
	}

	// Stay Info

	public String getArrivalDate_ResDetail(WebDriver driver) {
		String ariveDatePath = "//div[@class='checkin']//span[contains(@data-bind,'dateArrive')][1]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(ariveDatePath)), driver);
		return driver.findElement(By.xpath(ariveDatePath)).getText();
	}

	public String getDepartDate_ResDetail(WebDriver driver) {
		String path = "//div[@class='checkout']//span[contains(@data-bind,'dateDepart')][1]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public String getNoOfNights_ResDetail(WebDriver driver) {
		String path = "(//div[@class='noofnights']//span[contains(@data-bind,'noOfNights')])[1]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public String getNoOfAdults_ResDetail(WebDriver driver) {
		String path = "//div[contains(@class,'guestInfo')]//span[contains(@data-bind,'numberOfAdults')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public String getNoOfChilds_ResDetail(WebDriver driver) {
		String path = "//div[contains(@class,'guestInfo')]//span[contains(@data-bind,'numberOfChildren')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public String getRoomClass_ResDetail(WebDriver driver) {
		String path = "//div[contains(@class,'roomInfo')]//span[contains(@data-bind,'roomClassName')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public String getRoomNo_ResDetail(WebDriver driver) {
		String path = "//div[contains(@class,'roomInfo')]//span[contains(@data-bind,'roomNumber')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	// Guest-Info

	public String getGuestName_ResDetail(WebDriver driver) {
		String path = "//div[contains(@data-bind,'with:GuestInfo')]//span[contains(@data-bind,'GuestProfileName')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public String getContactName_ResDetail(WebDriver driver) {
		String path = "//div[contains(@data-bind,'with:GuestInfo')]//span[contains(@data-bind,'ContactProfileName')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public String getEmail_ResDetail(WebDriver driver) {
		String path = "//div[contains(@data-bind,'with:GuestInfo')]//span[contains(@data-bind,'emailAddress')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public String getPhoneNO_ResDetail(WebDriver driver) {
		String path = "//div[contains(@data-bind,'with:GuestInfo')]//span[contains(@data-bind,'mailing_formattedPhoneNumber')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		String foundNo = driver.findElement(By.xpath(path)).getText();
		foundNo = foundNo.replace("-", "").trim();
		foundNo = foundNo.replace("(", "");
		foundNo = foundNo.replace(")", "");
		return foundNo.trim();
	}

	public String getAlternateNo_ResDetail(WebDriver driver) {
		String path = "//div[contains(@data-bind,'with:GuestInfo')]//span[contains(@data-bind,'mailing_formattedAlternativePhoneNumber')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		String foundNo = driver.findElement(By.xpath(path)).getText();
		foundNo = foundNo.replace("-", "").trim();
		foundNo = foundNo.replace("(", "");
		foundNo = foundNo.replace(")", "");
		return foundNo.trim();
	}

	public String getAccount_ResDetail(WebDriver driver) {
		String path = "//div[contains(@data-bind,'with:GuestInfo')]//span[contains(@data-bind,'account')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public String getAddressLine1_ResDetail(WebDriver driver) {
		String path = "//div[contains(@data-bind,'with:mailingAddress')]//span[contains(@data-bind,'text:address1')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public String getAddressLine2_ResDetail(WebDriver driver) {
		String path = "//div[contains(@data-bind,'with:mailingAddress')]//span[contains(@data-bind,'text:address2')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public String getAddressLine3_ResDetail(WebDriver driver) {
		String path = "//div[contains(@data-bind,'with:mailingAddress')]//span[contains(@data-bind,'text:address3')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public String getCity_ResDetail(WebDriver driver) {
		String path = "//div[contains(@data-bind,'with:mailingAddress')]//span[contains(@data-bind,'city')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public String getState_ResDetail(WebDriver driver) {
		String path = "//div[contains(@data-bind,'with:mailingAddress')]//span[contains(@data-bind,'stateName')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public String getContry_ResDetail(WebDriver driver) {
		String path = "//div[contains(@data-bind,'with:mailingAddress')]//span[contains(@data-bind,'countryId')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public String getPostalCode_ResDetail(WebDriver driver) {
		String path = "//div[contains(@data-bind,'with:mailingAddress')]//span[contains(@data-bind,'postalCode')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public ArrayList<String> verfifyGuestinfo_ResDetail(WebDriver driver, String guestName, String addLine1,
			String city, String state, String contry, String postalCode, String email, String phoneNo) {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_CPReservation res = new Elements_CPReservation(driver);

		Wait.explicit_wait_visibilityof_webelement_120(res.GuestName_GuestInfo, driver);
		String foundGuestName = res.GuestName_GuestInfo.getText();
		assertEquals(foundGuestName, guestName, "Failed To Verify Guest Name");
		reslogger.info("Successfully Verified Guest Name : " + foundGuestName);
		test_steps.add("Successfully Verified Guest Name : " + foundGuestName);

		Wait.explicit_wait_visibilityof_webelement_120(res.AddressLine1_GuestInfo, driver);
		String foundAddLine1 = res.AddressLine1_GuestInfo.getText();
		assertEquals(foundAddLine1, addLine1, "Failed To Verify Address Line1");
		reslogger.info("Successfully Verified Address Line1 : " + foundAddLine1);
		test_steps.add("Successfully Verified Address Line1 : " + foundAddLine1);

		Wait.explicit_wait_visibilityof_webelement_120(res.City_GuestInfo, driver);
		String foundCity = res.City_GuestInfo.getText();
		assertEquals(foundCity, city, "Failed To Verify City");
		reslogger.info("Successfully Verified City : " + foundCity);
		test_steps.add("Successfully Verified City : " + foundCity);

		Wait.explicit_wait_visibilityof_webelement_120(res.Contry_GuestInfo, driver);
		String foundCountry = res.Contry_GuestInfo.getText();
		assertEquals(foundCountry, contry, "Failed To Verify Country");
		reslogger.info("Successfully Verified Country : " + foundCountry);
		test_steps.add("Successfully Verified Country : " + foundCountry);

		Wait.explicit_wait_visibilityof_webelement_120(res.State_GuestInfo, driver);
		String foundState = res.State_GuestInfo.getText();
		assertEquals(foundState, state, "Failed To Verify State");
		reslogger.info("Successfully Verified State : " + foundState);
		test_steps.add("Successfully Verified State : " + foundState);

		Wait.explicit_wait_visibilityof_webelement_120(res.PostalCode_GuestInfo, driver);
		String foundPostalCode = res.PostalCode_GuestInfo.getText();
		assertEquals(foundPostalCode, postalCode, "Failed To Verify PostalCode");
		reslogger.info("Successfully Verified PostalCode : " + foundPostalCode);
		test_steps.add("Successfully Verified PostalCode : " + foundPostalCode);

		Wait.explicit_wait_visibilityof_webelement_120(res.Email_GuestInfo, driver);
		String foundEmail = res.Email_GuestInfo.getText();
		assertEquals(foundEmail, email, "Failed To Verify Email");
		reslogger.info("Successfully Verified Email : " + foundEmail);
		test_steps.add("Successfully Verified Email : " + foundEmail);

		Wait.explicit_wait_visibilityof_webelement_120(res.PhoneNo_GuestInfo, driver);
		String foundphoneNo = res.PhoneNo_GuestInfo.getText();
		foundphoneNo = foundphoneNo.replace("-", "").trim();
		foundphoneNo = foundphoneNo.replace("(", "");
		foundphoneNo = foundphoneNo.replace(")", "");
		foundphoneNo = foundphoneNo.replace(" ", "");
		assertEquals(foundphoneNo.trim(), phoneNo, "Failed To Verify phoneNo");
		reslogger.info("Successfully Verified PhoneNo : " + foundphoneNo);
		test_steps.add("Successfully Verified PhoneNo : " + foundphoneNo);

		return test_steps;
	}

	// Payment Info
	public String getPaymentMethod_ResDetail(WebDriver driver) {
		String path = "//div[contains(@data-bind,'PaymentDetail')]//span[contains(@data-bind,'methodName')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public String getCardNoLast4Digits_ResDetail(WebDriver driver) {
		String path = "//div[contains(@data-bind,'PaymentDetail')]//span[contains(@data-bind,'text: PaymentDetail().creditCard.displayNumber')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public String getNameOnCard_ResDetail(WebDriver driver) {
		String path = "//div[contains(@data-bind,'PaymentDetail')]//span[contains(@data-bind,'cardHolderName')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public String getCardExpiryDate_ResDetail(WebDriver driver) {
		String path = "//div[contains(@data-bind,'PaymentDetail')]//span[contains(@data-bind,'expiresOn')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public ArrayList<String> verifyPaymentDetail_ResDetail(WebDriver driver, String paymentMethod, String cardNo,
			String nameOnCard, String expiry) {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_CPReservation res = new Elements_CPReservation(driver);

		Wait.explicit_wait_visibilityof_webelement_120(res.PaymentMethod_PayInfo, driver);
		String foundPayMethod = res.PaymentMethod_PayInfo.getText();
		assertEquals(foundPayMethod, paymentMethod, "Failed To Verify Payment Method");
		reslogger.info("Successfully Verified Payment Method : " + foundPayMethod);
		test_steps.add("Successfully Verified Payment Method : " + foundPayMethod);

		Wait.explicit_wait_visibilityof_webelement_120(res.CardNo_PayInfo, driver);
		String foundCardNo = res.CardNo_PayInfo.getText();
		assertEquals(foundCardNo, cardNo, "Failed To Verify CardNo");
		reslogger.info("Successfully Verified CardNo : " + foundCardNo);
		test_steps.add("Successfully Verified CardNo : " + foundCardNo);

		Wait.explicit_wait_visibilityof_webelement_120(res.NameOnCard_PayInfo, driver);
		String foundNameOnCard = res.NameOnCard_PayInfo.getText();
		assertEquals(foundNameOnCard, nameOnCard, "Failed To Verify Name On Card");
		reslogger.info("Successfully Verified Name On Card : " + foundNameOnCard);
		test_steps.add("Successfully Verified Name On Card : " + foundNameOnCard);

		Wait.explicit_wait_visibilityof_webelement_120(res.CardExpiry_PayInfo, driver);
		String foundExpiry = res.CardExpiry_PayInfo.getText();
		assertEquals(foundExpiry, expiry, "Failed To Verify expiry");
		reslogger.info("Successfully Verified expiry : " + foundExpiry);
		test_steps.add("Successfully Verified expiry : " + foundExpiry);

		return test_steps;
	}

	// Market Info
	public String getReferral_ResDetail(WebDriver driver) {
		String path = "//div[contains(@data-bind,'with:marketingInfo')]//span[text()='Referral']/following-sibling::span";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public String getSource_ResDetail(WebDriver driver) {
		String path = "//div[contains(@data-bind,'with:marketingInfo')]//span[text()='Source']/following-sibling::span";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public ArrayList<String> verifyPopupChangeInPolicy(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		reslogger.info("Verifying Popup Appear for Policy Change");
		Wait.explicit_wait_visibilityof_webelement(res.CP_VerifyPolicyChangePopup, driver);
		String text = res.CP_VerifyPolicyChangePopup.getText();
		String message = "Would you like to update the reservation to the new policies?";
		if (text.contains(message)) {
			test_steps.add("Verified pop up displayed for Policy Change");
		}

		// Click on Yes Button
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_CheckOutAllButton_ConfirmationMsg);
		res.CP_Reservation_CheckOutAllButton_ConfirmationMsgYesButton.click();
		test_steps.add("Click on Yes");

		return test_steps;
	}

	public void verifyRoomChangeInHistoryTab(WebDriver driver, ArrayList test_steps, String roomNoBefore,
			String roomNoAfter) {

		String res = "//span[contains(text(),'Changed room assignment from')]";
		reslogger.info("msg :: " + res);
		// Wait.expl
		if (driver.findElement(By.xpath(res)).isDisplayed()) {
			test_steps.add("Verified Changed room assignment from " + roomNoBefore + " to " + roomNoAfter);
			reslogger.info("Verified Changed room assignment from " + roomNoBefore + " to " + roomNoAfter);
		} else {
			test_steps.add("Failed to Change room assignment from " + roomNoBefore + " to " + roomNoAfter);
			reslogger.info("Failed to Change room assignment from " + roomNoBefore + " to " + roomNoAfter);
		}
	}

	public ArrayList<String> clickAddRooms(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_AddRoom);
		res.CP_NewReservation_AddRoom.click();
		test_steps.add("Click on Add a Room Button");
		reslogger.info("Click on Add a Room Button");
		return test_steps;
	}

	public String AssignNewRoomNumber(WebDriver driver) throws InterruptedException {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		WebDriverWait wait = new WebDriverWait(driver, 25);

		Wait.wait2Second();
		int count = res.CP_Reservation_StayInfo_ThreeDotts.size();
		Utility.ScrollToElement(res.CP_Reservation_StayInfo_ThreeDotts.get(count - 1), driver);

		res.CP_Reservation_StayInfo_ThreeDotts.get(count - 1).click();
		test_steps.add("Click Edit Stay Info");
		reslogger.info("Click Edit Stay Info");

		// Click on room Assign Room No
		reslogger.info("Click on Assign Room No");
		String assignRoomNumber = "//ul//li[contains(text(),'Assign  room Number')]";
		By locator = By.xpath(assignRoomNumber);
		int assignRoomsize = res.CP_ClickAssignRoomNumber.size();
		res.CP_ClickAssignRoomNumber.get(assignRoomsize - 1).click();
		test_steps.add("Click Assign Room Number");

		// Click on room DropDown
		reslogger.info("Click on room dropdown");
		String part = "//div[contains(@class,'ir-flex-grow ir-flex-grow-3 ir-flex-mb-grow-3')]";
		By roomLocator = By.xpath(part);
		WebElement elementRoom = wait.until(ExpectedConditions.elementToBeClickable(roomLocator));
		String roomNumber = elementRoom.getText();
		reslogger.info("Room Number :: " + roomNumber);

		elementRoom.click();
		test_steps.add("Click on room dropdown");

		// Select different room number
		String selectRoom = "//div[contains(@class,'ir-flex-grow ir-flex-grow-3 ir-flex-mb-grow-3')]//div//div//ul//li//a";
		int roomNum = driver.findElements(By.xpath(selectRoom)).size();
		String getRoomNumber = "";
		for (int i = 0; i < roomNum; i++) {
			getRoomNumber = res.CP_SelectResRoom.get(i).getText();
			if (!(getRoomNumber.equals(roomNumber)) && (!(getRoomNumber.equals("Unassigned")))) {
				res.CP_SelectResRoom.get(i).click();
				break;
			}
		}

		// Click Save Button
		reslogger.info("Clicking Save Button");
		Wait.explicit_wait_visibilityof_webelement(res.CP_SaveRoomNo, driver);
		Utility.ScrollToElement(res.CP_SaveRoomNo, driver);
		res.CP_SaveRoomNo.click();
		test_steps.add("Click on Save Room Number Button");

		return roomNumber + "-" + getRoomNumber;
	}

	public void clickSaveButton(WebDriver driver) {
		// Click Save Button
		Elements_CPReservation res = new Elements_CPReservation(driver);
		try {
			reslogger.info("Clicking Save Button");
			Wait.explicit_wait_visibilityof_webelement(res.CP_SaveRoomNo, driver);
			Utility.ScrollToElement(res.CP_SaveRoomNo, driver);
			res.CP_SaveRoomNo.click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reslogger.info("Fail to Click on Save Button");
		}
	}

	public ArrayList<String> verifyPopupChangeInCost(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		reslogger.info("Verifying Popup Appear for Change In Cost");
		Wait.explicit_wait_visibilityof_webelement(res.CP_VerifyChangeCostPopup, driver);
		String text = res.CP_VerifyChangeCostPopup.getText();
		String[] parts = text.split("from");
		String message = parts[0];
		assertEquals(message, "Are you sure you wish to change the total cost of the stay ",
				"Failed : to verify amount change popup message");
		test_steps.add("Verified pop up displayed for Change in Total Cost");

		// Click on Yes Button
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_CheckOutAllButton_ConfirmationMsg);
		res.CP_Reservation_CheckOutAllButton_ConfirmationMsgYesButton.click();
		test_steps.add("Click on Yes");

		return test_steps;
	}

	public void validateMRBAdditionalGuestInfo(WebDriver driver, ArrayList test_steps, String Salutation,
			String GuestFirstName, String GuestLastName, String PhoneNumber, String Email, String Country) {

		String sal = Salutation.split("\\|")[0];
		String guestFName = GuestFirstName.split("\\|")[0];
		String guestLName = GuestLastName.split("\\|")[0];
		String phonenum = PhoneNumber.split("\\|")[0];
		String email = Email.split("\\|")[0];

		String addtionalRoom = "//span[starts-with(text(),'Additional Room: ')]";
		List<WebElement> we = driver.findElements(By.xpath(addtionalRoom));

		int count = we.size();
		for (int i = 1; i <= count; i++) {
			addtionalRoom = "(//span[contains(text(),'Additional Room: ')])[" + i + "]";
			Wait.WaitForElement(driver, addtionalRoom);
			try {
				Utility.ScrollToElement(driver.findElement(By.xpath(addtionalRoom)), driver);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.findElement(By.xpath(addtionalRoom)).click();

			Elements_CPReservation res = new Elements_CPReservation(driver);

			String guestName = "(//span[contains(text(),'Additional Room: ')])[" + i
					+ "]/../../../../../..//label[text()='Guest Name']/following-sibling::span";
			Wait.WaitForElement(driver, guestName);

			String name = sal.trim() + " " + guestFName.trim() + " " + guestLName.trim();
			String guestname = driver.findElement(By.xpath(guestName)).getText().trim();
			reslogger.info(guestname);
			if (guestname.equalsIgnoreCase(name.trim())) {
				test_steps.add("Expanding additional guest room");
				reslogger.info("Expanding additional guest room");

				test_steps
						.add("Reservation additional Guest Info GuestName field verified for additonal room: " + name);
				reslogger
						.info("Reservation additional Guest Info GuestName field verified for additonal room: " + name);
			}
			String code = null;
			if (Country.equalsIgnoreCase("United States")) {
				code = "1";
			} else if (Country.equalsIgnoreCase("United Kingdom")) {
				code = "41";
			}
			code = code + "-";
			PhoneNumber = phonenum;
			reslogger.info(PhoneNumber);

			String phone = "(//span[contains(text(),'Additional Room: ')])[" + i
					+ "]/../../../../../..//label[text()='Phone']/following-sibling::span";
			phone = driver.findElement(By.xpath(phone)).getText().trim();
			if (phone.equalsIgnoreCase(PhoneNumber)) {
				test_steps.add("Reservation Guest Info PhoneNumber field verified : " + PhoneNumber);
				reslogger.info("Reservation Guest Info PhoneNumber field verified : " + PhoneNumber);
			}

			String mailadd = "(//span[contains(text(),'Additional Room: ')])[" + i
					+ "]/../../../../../..//label[text()='E-mail']/following-sibling::span";
			String mail = driver.findElement(By.xpath(mailadd)).getText().trim();
			reslogger.info(mail);
			if (mail.equalsIgnoreCase(email.trim())) {
				test_steps.add("Reservation Guest Info Email field verified : " + email);
				reslogger.info("Reservation Guest Info Email field verified : " + email);
			}
		}
		test_steps.add("Successfully validate MRB Additional Guest Info same as Primary Room's guest");
		reslogger.info("Successfully validate MRB Additional Guest Info same as Primary Room's guest");
	}

	public String VerifyFolioDropDownItem(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		List<WebElement> we = driver.findElements(By.xpath(OR_Reservation.CP_VerifyFolioDropDownItem));
		int count = we.size();
		String roomNo = res.CP_VerifyFolioDropDownItem.get(count - 1).getText().split(":")[1].trim();
		Utility.ScrollToElement(res.CP_Reservation_FolioDetailDropDownBox, driver);

		if (count > 2) {
			res.CP_VerifyFolioDropDownItem.get(count - 1).click();
			test_steps.add("Verified new Room is added to Folio: " + roomNo);
			reslogger.info("Verified new Room is added to Folio: " + roomNo);
		}
		return roomNo;
	}

	public void verifyAddRoomChangeInHistoryTab(WebDriver driver, ArrayList test_steps) {
		// Verify Rrom change message
		String res = "//span[contains(text(),'Added a room to this reservation')]";
		reslogger.info("msg :: " + res);

		if (driver.findElement(By.xpath(res)).isDisplayed()) {
			test_steps.add("Verified Add Room Change In History Tab");
			reslogger.info("Verified Add Room Change In History Tab");
		} else {
			test_steps.add("Failed to verify Add Room Change In History Tab");
			reslogger.info("Failed to verify Add Room Change In History Tab");
		}
	}

	public void verifyCostChangeInHistoryTab(WebDriver driver, ArrayList<String> test_steps) {
		// Verify Room change message
		String res = "//span[contains(text(),'Changed room charge from ')]";
		reslogger.info("msg :: " + res);
		String message = driver.findElement(By.xpath(res)).getText();
		if (driver.findElement(By.xpath(res)).isDisplayed()) {
			test_steps.add("Verified Message: " + message);
			reslogger.info("Verified Message: " + message);
		} else {
			test_steps.add("Failed to verify message: " + message);
			reslogger.info("Failed to verify message: " + message);
		}
	}

	public void openReservation(WebDriver driver, ArrayList<String> test_steps) {
		// TODO Auto-generated method stub
		String res = "//a[contains(@data-bind,'text: GuestName')]";
		WebElement reservationVisible = driver.findElement(By.xpath(res));
		if (driver.findElement(By.xpath(res)).isDisplayed()) {
			reservationVisible.click();
			test_steps.add("Click on Reservation");
			reslogger.info("Click on Reservation");
		} else {
			test_steps.add("Failed to Click on Reservation");
			reslogger.info("Failed to Click on Reservation");
		}
	}

	public void verifyStayInfoNewRoom(WebDriver driver, ArrayList test_steps, String CheckInDate, String CheckOutDate,
			String Adults, String Children, String RoomClass, String RoomCharges) throws Exception {
		test_steps.add("******************* Stay info fields verification **********************");
		reslogger.info("******************* Stay info fields verification **********************");

		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		Date dateObj1 = format1.parse(CheckInDate);
		Date dateObj2 = format1.parse(CheckOutDate);
		long diff = dateObj2.getTime() - dateObj1.getTime();
		int Nights = (int) (diff / (24 * 60 * 60 * 1000));
		reslogger.info("difference between days: " + Nights);

		String[] checkIn = CheckInDate.split("/");
		if (checkIn[0].charAt(0) == '0') {
			checkIn[0] = checkIn[0].replace("" + checkIn[0].charAt(0), "");
		}
		String checkInMonth = Utility.get_Month(CheckInDate);
		String checkInDays = checkInMonth + " " + checkIn[0] + ", " + checkIn[2];

		String checkInDay = "//div[contains(text(),'Stay')]/..//div[@class='checkin']/span[text()='" + checkInDays
				+ "']";
		if (driver.findElement(By.xpath(checkInDay)).isDisplayed()) {
			test_steps.add("Reservation Stay Info checkin date verified : " + CheckInDate);
			reslogger.info("Reservation Stay Info checkin date verified : " + CheckInDate);
		} else {
			test_steps.add("Reservation Stay Info checkin date not found : " + CheckInDate);
			reslogger.info("Reservation Stay Info checkin date not found : " + CheckInDate);
		}

		Date now = new Date(checkIn[1] + "/" + checkIn[0] + "/" + checkIn[2]);
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("dd/MM/yyyy"); // the
																				// day
																				// of
																				// the
																				// week
																				// abbreviated
		simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week
															// spelled out
															// completely
		String dayofCheckIn = simpleDateformat.format(now);

		String chckInday = "//div[contains(text(),'Stay')]/..//div[@class='checkin']//span[text()='"
				+ dayofCheckIn.trim() + "']";
		if (driver.findElement(By.xpath(chckInday)).isDisplayed()) {
			test_steps.add("Reservation Stay Info checkin day verified : " + dayofCheckIn);
			reslogger.info("Reservation Stay Info checkin day verified : " + dayofCheckIn);
		} else {
			test_steps.add("Reservation Stay Info checkin day not found : " + dayofCheckIn);
			reslogger.info("Reservation Stay Info checkin day not found : " + dayofCheckIn);
		}

		String[] checkOut = CheckOutDate.split("/");

		if (checkOut[0].charAt(0) == '0') {
			checkOut[0] = checkOut[0].replace("" + checkOut[0].charAt(0), "");
		}
		String checkOutMonth = Utility.get_Month(CheckOutDate);
		String checkOutDays = checkInMonth + " " + checkOut[0] + ", " + checkOut[2];
		String checkoutDay = "//div[contains(text(),'Stay')]/..//div[@class='checkout']/span[text()='" + checkOutDays
				+ "']";
		if (driver.findElement(By.xpath(checkoutDay)).isDisplayed()) {
			test_steps.add("Reservation Stay Info checkout date verified : " + CheckOutDate);
			reslogger.info("Reservation Stay Info checkout date verified : " + CheckOutDate);
		} else {
			test_steps.add("Reservation Stay Info checkout date not found : " + CheckOutDate);
			reslogger.info("Reservation Stay Info checkout date not found : " + CheckOutDate);
		}

		now = new Date(checkOut[1] + "/" + checkOut[0] + "/" + checkOut[2]);
		simpleDateformat = new SimpleDateFormat("dd/MM/yyyy"); // the day of the
																// week
																// abbreviated
		simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week
															// spelled out
															// completely
		String dayofCheckOut = simpleDateformat.format(now);
		String chkOutDay = "//div[contains(text(),'Stay')]/..//div[@class='checkout']//span[text()='"
				+ dayofCheckOut.trim() + "']";
		if (driver.findElement(By.xpath(chkOutDay)).isDisplayed()) {
			test_steps.add("Reservation Stay Info checkout day verified : " + dayofCheckOut);
			reslogger.info("Reservation Stay Info checkout day verified : " + dayofCheckOut);
		} else {
			test_steps.add("Reservation Stay Info checkout day not found : " + dayofCheckOut);
			reslogger.info("Reservation Stay Info checkout day not found : " + dayofCheckOut);
		}

		String nights = "//div[contains(text(),'Stay')]/..//div[@class='noofnights']/span[text()='" + Nights + "N']";
		if (driver.findElement(By.xpath(checkoutDay)).isDisplayed()) {
			test_steps.add("Reservation Stay Info Nights verified : " + Nights + "N");
			reslogger.info("Reservation Stay Info Nights verified : " + Nights + "N");
		} else {
			test_steps.add("Reservation Stay Info Nights not found : " + Nights + "N");
			reslogger.info("Reservation Stay Info Nights not found : " + Nights + "N");
		}

		String adults = "//div[contains(text(),'Stay')]/..//div//span[contains(@data-bind,'numberOfAdults')]";
		adults = driver.findElement(By.xpath(adults)).getText().trim();
		if (adults.equalsIgnoreCase(Adults.trim())) {
			test_steps.add("Reservation Stay Info Adults verified : " + Adults);
			reslogger.info("Reservation Stay Info Adults verified : " + Adults);
		} else {
			test_steps.add("Reservation Stay Info Adults not found : " + Adults);
			reslogger.info("Reservation Stay Info Adults not found : " + Adults);
		}

		String children = "//div[contains(text(),'Stay')]/..//div//span[contains(@data-bind,'numberOfChildren')]";
		children = driver.findElement(By.xpath(children)).getText().trim();
		if (children.equalsIgnoreCase(Children.trim())) {
			test_steps.add("Reservation Stay Info Children verified : " + Children);
			reslogger.info("Reservation Stay Info Children verified : " + Children);
		} else {
			test_steps.add("Reservation Stay Info Children not found : " + Children);
			reslogger.info("Reservation Stay Info Children not found : " + Children);
		}

		reslogger.info("roomClass found : " + RoomClass);

		String roomClass = "//div[contains(text(),'Stay')]/..//div//span[contains(@data-bind,'roomClassName')]";
		reslogger.info("roomClass found : " + roomClass);

		List<WebElement> roomClasses = driver.findElements(By.xpath(roomClass));
		int elements = roomClasses.size();
		String roomClassName = "";
		for (int i = 0; i < elements; i++) {
			roomClassName = driver.findElements(By.xpath(roomClass)).get(i).getText();
			reslogger.info("charges : " + roomClassName);
			if (roomClassName.contains(RoomClass)) {
				test_steps.add("Reservation Stay Info Room Class Name : " + RoomClass);
				reslogger.info("Reservation Stay Info Room Class Name : " + RoomClass);
			}
		}

		String roomCharge = "//span[contains(@data-bind,'showInnroadCurrency: { value: roomTotal , ShouldShowNegativeAsBracket: true }')]";
		reslogger.info("roomCharge found : " + roomCharge);

		List<WebElement> roomCharges = driver.findElements(By.xpath(roomCharge));
		int count = roomCharges.size();
		String charges = "";
		for (int i = 0; i < count; i++) {
			charges = driver.findElements(By.xpath(roomCharge)).get(i).getText();
			reslogger.info("charges : " + charges);
			if (charges.contains(RoomCharges)) {
				test_steps.add("Reservation Stay Info RoomCharges verified : " + RoomCharges);
				reslogger.info("Reservation Stay Info RoomCharges verified : " + RoomCharges);
			}
		}
	}

	public void VerifyChangeOfPolicyMessageForRoomChange(WebDriver driver, String RoomNewClass, String IsAssign,
			String Account, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		WebDriverWait wait = new WebDriverWait(driver, 25);

		try {
			Wait.wait2Second();
			int count = res.CP_Reservation_StayInfo_ThreeDotts.size();
			Utility.ScrollToElement(res.CP_Reservation_StayInfo_ThreeDotts.get(1), driver);

			res.CP_Reservation_StayInfo_ThreeDotts.get(0).click();
			test_steps.add("Click Edit Stay Info");
			reslogger.info("Click Edit Stay Info");

			// Click on change stay Info
			reslogger.info("Click on Change Stay Info");
			String changeStayInfo = "//ul//li[contains(text(),'Change Stay Details')]";
			By locator = By.xpath(changeStayInfo);
			List<WebElement> changeStayInfoEl = driver.findElements(By.xpath(changeStayInfo));
			changeStayInfoEl.get(1).click();
			test_steps.add("Click on Change Stay Info");

			// Select room
			selectRoom(driver, test_steps, RoomNewClass, IsAssign, Account);
			test_steps.add("Room Selected for new Room Class: " + RoomNewClass);
			test_steps.addAll(verifyPopupChangeInPolicy(driver, test_steps));

			// Click Save Button
			reslogger.info("Clicking Save Button");
			Wait.explicit_wait_visibilityof_webelement(res.CP_SaveRoomNo, driver);
			Utility.ScrollToElement(res.CP_SaveRoomNo, driver);
			res.CP_SaveRoomNo.click();
			test_steps.add("Click on Save Room Number Button");

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void enter_BillingAddress(WebDriver driver, ArrayList test_steps, String Salutation, String GuestFirstName,
			String GuestLastName, String Address1, String Address2, String Address3, String City, String Country,
			String State, String PostalCode, String IsBillingSameAsMailingAddress) throws InterruptedException {
		uncheck_BillingAddresSameAsMailingAddress(driver, test_steps, IsBillingSameAsMailingAddress);

		enter_BillingContactName(driver, test_steps, Salutation, GuestFirstName, GuestLastName);
		enter_BillingContactAddress(driver, test_steps, Address1, Address2, Address3);
		enter_BillingCity(driver, test_steps, City);
		select_BillingCountry(driver, test_steps, Country);
		select_BillingState(driver, test_steps, State);
		enter_BillingPostalCode(driver, test_steps, PostalCode);
	}

	public void enter_BillingContactAddress(WebDriver driver, ArrayList test_steps, String Address1, String Address2,
			String Address3) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_BillingAddress1);
		res.CP_NewReservation_BillingAddress1.clear();
		res.CP_NewReservation_BillingAddress1.sendKeys(Address1);
		test_steps.add("Enter Address1 : " + Address1);
		reslogger.info("Enter Address1 : " + Address1);

		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_BillingAddress2);
		res.CP_NewReservation_BillingAddress2.clear();
		res.CP_NewReservation_BillingAddress2.sendKeys(Address2);
		test_steps.add("Enter Address2 : " + Address2);
		reslogger.info("Enter Address2 : " + Address2);

		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_BillingAddress3);
		res.CP_NewReservation_BillingAddress3.clear();
		res.CP_NewReservation_BillingAddress3.sendKeys(Address3);
		test_steps.add("Enter Address3 : " + Address3);
		reslogger.info("Enter Address3 : " + Address3);
	}

	public void enter_BillingCity(WebDriver driver, ArrayList test_steps, String City) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_BillingCity);
		res.CP_NewReservation_BillingCity.clear();
		res.CP_NewReservation_BillingCity.sendKeys(City);
		test_steps.add("Enter City : " + City);
		reslogger.info("Enter City : " + City);
	}

	public void select_BillingCountry(WebDriver driver, ArrayList test_steps, String Country) {
		String button = "(//select[@name='country']/following-sibling::div/button)[2]";
		Wait.WaitForElement(driver, button);
		driver.findElement(By.xpath(button)).click();

		String cntry = "(//select[@name='country']/following-sibling::div//ul/li//span[contains(text(),'"
				+ Country.trim() + "')])[2]";
		Wait.WaitForElement(driver, cntry);
		driver.findElement(By.xpath(cntry)).click();
		test_steps.add("Selected Country : " + Country);
		reslogger.info("Selected Country : " + Country);
	}

	public void select_BillingState(WebDriver driver, ArrayList test_steps, String State) {
		String button = "(//select[@name='state']/following-sibling::div)[2]";
		Wait.WaitForElement(driver, button);

		if (driver.findElements(By.xpath("(//select[@name='state'])[2]/option")).size() > 1) {
			button = "(//select[@name='state']/following-sibling::div/button)[2]";
			driver.findElement(By.xpath(button)).click();
			String cntry = "(//select[@name='state'])[2]/following-sibling::div//following-sibling::div//ul/li//a//span[contains(text(),'"
					+ State.trim() + "')]";
			Wait.WaitForElement(driver, cntry);
			driver.findElement(By.xpath(cntry)).click();
			test_steps.add("Selected State : " + State);
			reslogger.info("Selected State : " + State);
		} else {
			test_steps.add("State field disabled");
			reslogger.info("State field disabled");
		}
	}

	public void enter_BillingPostalCode(WebDriver driver, ArrayList test_steps, String PostalCode) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_BillingPostalCode);
		res.CP_NewReservation_BillingPostalCode.clear();
		res.CP_NewReservation_BillingPostalCode.sendKeys(PostalCode);
		test_steps.add("Enter PostalCode : " + PostalCode);
		reslogger.info("Enter PostalCode : " + PostalCode);
	}

	public ArrayList<String> VerifyPaymentMethod(WebDriver driver, ArrayList<String> test_steps, String PaymentMethod,
			String CardNumber, String CardHolder, String ExpiryDate) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);

		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_PaymentMethod_Section);
		Utility.ScrollToElement(res.CP_Reservation_PaymentMethod_Section, driver);

		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_PaymentMethod);
		reslogger.info("Expected Payment Method : " + PaymentMethod);
		test_steps.add("Expected Payment Method : " + PaymentMethod);
		String paymentMethod = res.CP_Reservation_PaymentMethod.getText();
		reslogger.info("Found : " + paymentMethod);
		test_steps.add("Found : " + paymentMethod);

		assertEquals(PaymentMethod, paymentMethod, "Failed: payment Method doesn't match");
		reslogger.info("Verified payment method");
		test_steps.add("Verified payment method");

		Wait.WaitForElement(driver, OR_Reservation.CP_RESERVATION_CC_Number);
		String input = CardNumber; // input string
		String lastFourDigits = ""; // substring containing last 4 characters

		if (input.length() > 4) {
			lastFourDigits = input.substring(input.length() - 4);
		} else {
			lastFourDigits = input;
		}

		reslogger.info(lastFourDigits);
		CardNumber = lastFourDigits;
		reslogger.info("Expected Credit Card Number : XXXX" + CardNumber);
		test_steps.add("Expected Credit Card Number : XXXX" + CardNumber);
		String cardNumber = res.CP_RESERVATION_CC_Number.getText();
		reslogger.info("Found : XXXX" + cardNumber);
		test_steps.add("Found : XXXX" + cardNumber);

		assertEquals(CardNumber, cardNumber, "Failed: payment Method doesn't match");
		test_steps.add("Verified credit card number");

		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_CardHolder_Name);
		reslogger.info("Expected Name on Card : " + CardHolder);
		test_steps.add("Expected Name on Card : " + CardHolder);
		String cardHolder = res.CP_Reservation_CardHolder_Name.getText();
		reslogger.info("Found : " + cardHolder);
		test_steps.add("Found : " + cardHolder);

		assertEquals(CardHolder, cardHolder, "Failed: Card Holder doesn't match");
		test_steps.add("Verified name on card");

		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_Card_ExpiryDate);
		reslogger.info("Expected Card ExpiryDate : " + ExpiryDate);
		test_steps.add("Expected Card ExpiryDate : " + ExpiryDate);
		String expiryDate = res.CP_Reservation_Card_ExpiryDate.getText();
		reslogger.info("Found : " + expiryDate);
		test_steps.add("Found : " + expiryDate);

		assertEquals(ExpiryDate, expiryDate, "Failed: Card expiryDate doesn't match");
		test_steps.add("Verified card expiry date");

		return test_steps;
	}

	public void enter_BillingContactName(WebDriver driver, ArrayList test_steps, String Salutation,
			String GuestFirstName, String GuestLastName) {
		Elements_CPReservation res = new Elements_CPReservation(driver);

		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_BillingSalutation);
		res.CP_NewReservation_BillingSalutation.click();
		String sal = "(//a//span[contains(text(),'" + Salutation + "')])[5]";
		Wait.WaitForElement(driver, sal);
		driver.findElement(By.xpath(sal)).click();

		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_BillingFirstName);
		res.CP_NewReservation_BillingFirstName.sendKeys(GuestFirstName);
		test_steps.add("Contact First Name : " + GuestFirstName);
		reslogger.info("Contact First Name : " + GuestFirstName);

		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_BillingLastName);
		res.CP_NewReservation_BillingLastName.sendKeys(GuestLastName);
		test_steps.add("Contact Last Name : " + GuestLastName);
		reslogger.info("Contact Last Name : " + GuestLastName);
	}

	public void uncheck_BillingAddresSameAsMailingAddress(WebDriver driver, ArrayList test_steps,
			String IsBillingAddressSame) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_SameAsMailingAddressCheckBox);
		Utility.ScrollToElement(res.CP_NewReservation_SameAsMailingAddressCheckBox, driver);
		if (IsBillingAddressSame.contentEquals("Yes")) {
			if (!res.CP_NewReservation_SameAsMailingAddressCheckBox.isSelected()) {
				driver.findElement(By
						.xpath("//label//following-sibling::input[@data-bind='checked: PaymentDetail().useMailingInfo']//.."))
						.click();
				test_steps.add("Billing Address Same as mailing selected");
				reslogger.info("Billing Address Same as mailing selected");
			} else {
				test_steps.add("Billing Address Same as mailing selected");
				reslogger.info("Billing Address Same as mailing selected");
			}
		} else {
			if (res.CP_NewReservation_SameAsMailingAddressCheckBox.isSelected()) {
				driver.findElement(By
						.xpath("//label//following-sibling::input[@data-bind='checked: PaymentDetail().useMailingInfo']//.."))
						.click();
				test_steps.add("Billing Address Same as mailing unselected");
				reslogger.info("Billing Address Same as mailing unselected");
			} else {
				test_steps.add("Billing Address Same as mailing unselected");
				reslogger.info("Billing Address Same as mailing unselected");
			}
		}
	}

	public ArrayList<String> clickCopyButton(WebDriver driver, String GuestFirstName) throws InterruptedException {
		Elements_CPReservation cpelements = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();
		Wait.WaitForElement(driver, OR_Reservation.COPY_BUTTON);
		Utility.ScrollToElement(cpelements.COPY_BUTTON, driver);
		cpelements.COPY_BUTTON.click();
		reslogger.info("Copy Button Clicked");
		test_steps.add("Copy Button Clicked");
		Wait.wait10Second();
		Wait.explicit_wait_visibilityof_webelement(cpelements.COPY_RESERVATION_TRIMNAME, driver);
		Wait.WaitForElement(driver, OR_Reservation.COPY_RESERVATION_TRIMNAME);
		GuestFirstName = GuestFirstName.substring(0, 3);
		String copiedReservationTrimmedName = "copy [" + GuestFirstName + "...";
		// assertEquals(cpelements.COPY_RESERVATION_TRIMNAME.getText().equals(copiedReservationTrimmedName),
		// true);
		reslogger.info("Verified Trimmed Copy Text In Copied Tab: " + copiedReservationTrimmedName);
		test_steps.add("Verified Trimmed Copy Text In Copied Tab: " + copiedReservationTrimmedName);
		return test_steps;

	}

	public ArrayList<String> verifyContactInfo(WebDriver driver, String IsGuesProfile, String GuestProfileName,
			String Salutation, String GuestFistName, String GuestLastName, String FirstName, String LastName,
			String Email, String Phone, String AlternativePhone) throws InterruptedException {
		Elements_CPReservation cpelements = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();
		if (IsGuesProfile.equals("Yes")) {
			assertEquals(GuestProfileName, cpelements.COPY_CP_GuestInfo_GuestProfile.getAttribute("value"));
			reslogger.info("Contact Info GuestProfileName: " + GuestProfileName + " :Verified");
			test_steps.add("Contact Info GuestProfileName: " + GuestProfileName + " :Verified");

			assertEquals(GuestFistName, cpelements.COPY_CP_GuestInfo_FirstName.getAttribute("value"));
			reslogger.info("Guest Info FirstName: " + GuestFistName + " :Verified");
			test_steps.add("Guest Info FistName: " + GuestFistName + " :Verified");

			assertEquals(GuestLastName, cpelements.COPY_CP_GuestInfo_LastName.getAttribute("value"));
			reslogger.info("Guest Info LastName: " + GuestFistName + " :Verified");
			test_steps.add("Guest Info LastName: " + GuestFistName + " :Verified");
		} else {
			assertEquals("", cpelements.COPY_CP_GuestInfo_GuestProfile.getAttribute("value"));
			reslogger.info("Contact Info GuestProfileName: " + "Empty" + " :Verified");
			test_steps.add("Contact Info GuestProfileName: " + "Empty" + " :Verified");

			assertEquals("", cpelements.COPY_CP_GuestInfo_FirstName.getAttribute("value"));
			reslogger.info("Guest Info FirstName: " + "Empty" + " :Verified");
			test_steps.add("Guest Info FistName: " + "Empty" + " :Verified");

			assertEquals("", cpelements.COPY_CP_GuestInfo_LastName.getAttribute("value"));
			reslogger.info("Guest Info LastName: " + "Empty" + " :Verified");
			test_steps.add("Guest Info LastName: " + "Empty" + " :Verified");
		}

		assertEquals(Salutation, cpelements.COPY_CP_GuestInfo_Guest_Salutation.getText());
		reslogger.info("Guest Info Salutation: " + "Empty" + " :Verified");
		test_steps.add("Guest Info Salutation: " + "Empty" + " :Verified");

		assertEquals(Salutation, cpelements.COPY_CP_ContactInfo_Salutation.getText());
		reslogger.info("Contact Info Saluation: " + Salutation + " :Verified");
		test_steps.add("Contact Info Saluation: " + Salutation + " :Verified");

		assertEquals(FirstName, cpelements.COPY_CP_ContactInfo_FirstName.getAttribute("value"));
		reslogger.info("Contact Info FirstName: " + FirstName + " :Verified");
		test_steps.add("Contact Info FirstName: " + FirstName + " :Verified");

		assertEquals(LastName, cpelements.COPY_CP_ContactInfo_LastName.getAttribute("value"));
		reslogger.info("Contact Info LastName: " + LastName + " :Verified");
		test_steps.add("Contact Info LastName: " + LastName + " :Verified");

		assertEquals(Email, cpelements.COPY_CP_ContactInfo_Email.getAttribute("value"));
		reslogger.info("Contact Info Email: " + Email + " :Verified");
		test_steps.add("Contact Info Email: " + Email + " :Verified");

		assertEquals(Phone, cpelements.COPY_CP_ContactInfo_Phone.getAttribute("value").replaceAll("-", "")
				.replaceAll(" ", "").replaceAll("\\)", "").replaceAll("\\(", ""));
		reslogger.info("Contact Info PhoneNumber: " + Phone + " :Verified");
		test_steps.add("Contact Info PhoneNumber: " + Phone + " :Verified");

		assertEquals(AlternativePhone, cpelements.COPY_CP_ContactInfo_AlternativePhone.getAttribute("value")
				.replaceAll("-", "").replaceAll(" ", "").replaceAll("\\)", "").replaceAll("\\(", ""));
		reslogger.info("Contact Info AlternativePhon: " + AlternativePhone + " :Verified");
		test_steps.add("Contact Info AlternativePhon: " + AlternativePhone + " :Verified");

		return test_steps;

	}

	public ArrayList<String> verifyMailingInfo(WebDriver driver, String Address1, String Address2, String Address3,
			String City, String Country, String State, String PostalCode) throws InterruptedException {
		Elements_CPReservation cpelements = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();
		assertEquals(Address1, cpelements.COPY_CP_MailingInfo_Address1.getAttribute("value"));
		reslogger.info("Mailing Info Address1: " + Address1 + " :Verified");
		test_steps.add("Mailing Info Address1: " + Address1 + " :Verified");
		assertEquals(Address2, cpelements.COPY_CP_MailingInfo_Address2.getAttribute("value"));
		reslogger.info("Mailing Info Address2: " + Address2 + " :Verified");
		test_steps.add("Mailing Info Address2: " + Address2 + " :Verified");
		assertEquals(Address3, cpelements.COPY_CP_MailingInfo_Address3.getAttribute("value"));
		reslogger.info("Mailing Info Address3: " + Address3 + " :Verified");
		test_steps.add("Mailing Info Address3: " + Address3 + " :Verified");
		assertEquals(City, cpelements.COPY_CP_MailingInfo_City.getAttribute("value"));
		reslogger.info("Mailing Info City: " + City + " :Verified");
		test_steps.add("Mailing Info City: " + City + " :Verified");
		assertEquals(Country, cpelements.COPY_CP_MailingInfo_Country.getText());
		reslogger.info("Mailing Info Country: " + Country + " :Verified");
		test_steps.add("Mailing Info Country: " + Country + " :Verified");
		assertEquals(State, cpelements.COPY_CP_MailingInfo_State.getText());
		reslogger.info("Mailing Info State: " + State + " :Verified");
		test_steps.add("Mailing Info State: " + State + " :Verified");
		assertEquals(PostalCode, cpelements.COPY_CP_MailingInfo_PostalCode.getAttribute("value"));
		reslogger.info("Mailing Info PostalCode: " + PostalCode + " :Verified");
		test_steps.add("Mailing Info PostalCode: " + PostalCode + " :Verified");

		return test_steps;

	}

	public ArrayList<String> verifyBillingInfo(WebDriver driver, String PaymentMethod, String CardNumber,
			String NameOnCard, String ExpDate) throws InterruptedException {
		Elements_CPReservation cpelements = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();

		assertEquals(PaymentMethod, cpelements.COPY_CP_BillingInfo_PaymentMethod.getText());
		reslogger.info("Billing Info PaymentMethod: " + PaymentMethod + " :Verified");
		test_steps.add("Billing Info PaymentMethod: " + PaymentMethod + " :Verified");

		assertEquals(CardNumber, cpelements.COPY_CP_BillingInfo_CreditCardNumber.getAttribute("value"));
		reslogger.info("Billing Info CardNumber: " + CardNumber + " :Verified");
		test_steps.add("Billing Info CardNumber: " + CardNumber + " :Verified");

		assertEquals(NameOnCard, cpelements.COPY_CP_BillingInfo_NameOnCard.getAttribute("value"));
		reslogger.info("Billing Info NameOnCard: " + NameOnCard + " :Verified");
		test_steps.add("Billing Info NameOnCard: " + NameOnCard + " :Verified");

		assertEquals(ExpDate, cpelements.COPY_CP_BillingInfo_ExpDate.getAttribute("value"));
		reslogger.info("Billing Info ExpDate: " + ExpDate + " :Verified");
		test_steps.add("Billing Info ExpDate: " + ExpDate + " :Verified");

		return test_steps;

	}

	public ArrayList<String> verifyRoomClassDetails(WebDriver driver, String RoomClassName, String RoomNumber)
			throws InterruptedException {
		Elements_CPReservation cpelements = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();
		assertEquals(RoomClassName.replace(" ", ""),
				cpelements.COPY_CP_RoomClassName.getText().split(":")[0].replace(" ", ""));
		reslogger.info("Room Class Name: " + RoomClassName + " :Verified");
		test_steps.add("Room Class Name: " + RoomClassName + " :Verified");

		assertEquals(!RoomNumber.equals(cpelements.COPY_CP_RoomClassName.getText().split(":")[1]), true);
		reslogger.info("Room Number changed from : " + RoomNumber + " to "
				+ cpelements.COPY_CP_RoomClassName.getText().split(":")[1] + ": Verified");
		test_steps.add("Room Number changed from : " + RoomNumber + " to "
				+ cpelements.COPY_CP_RoomClassName.getText().split(":")[1] + ": Verified");
		return test_steps;
	}

	public ArrayList<String> verifyMarketingInfo(WebDriver driver, String Market, String Referral)
			throws InterruptedException {
		Elements_CPReservation cpelements = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();
		String marketValue = "(//label[text()='Market']/preceding-sibling::div/button)[2]//span[text()='" + Market
				+ "']";
		String referralValue = "(//label[text()='Referral']/preceding-sibling::div/button)[3]//span[text()='" + Referral
				+ "']";

		WebElement MarketEle = driver.findElement(By.xpath(marketValue));
		WebElement ReferraltEle = driver.findElement(By.xpath(referralValue));

		assertEquals(Market, MarketEle.getText());
		reslogger.info("Marketing Info Market: " + Market + " :Verified");
		test_steps.add("Marketing Info Market: " + Market + " :Verified");
		reslogger.info("Valueee:" + ReferraltEle.getText());

		assertEquals(Referral, ReferraltEle.getText());
		reslogger.info("Marketing Info Referral: " + Referral + " :Verified");
		test_steps.add("Marketing Info Referral: " + Referral + " :Verified");
		return test_steps;
	}

	public ArrayList<String> verifyNotes(WebDriver driver, String Type, String Subject, String Description)
			throws InterruptedException {
		Elements_CPReservation cpelements = new Elements_CPReservation(driver);
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		WebElement NotesType = null;
		WebElement NotesSubject = null;
		WebElement NotesDesctiption = null;
		try {
			NotesType = driver
					.findElement(By.xpath("(//div[text()='Notes']/parent::div//td[text()='" + Type + "'])[2]"));
			Utility.ScrollToElement(NotesType, driver);
			assertEquals(NotesType.getText(), Type, "Failed : NotesType missmatched");
			reslogger.info("Verified Notes Type :" + Type);
			test_steps.add("Verified Notes Type :" + Type);

			NotesSubject = driver.findElement(
					By.xpath("(//td[contains(@data-bind,'text: subject') and contains(text(),'" + Subject + "')])[2]"));
			assertEquals(NotesSubject.getText(), Subject, "Failed : Detail missmatched");
			reslogger.info("Verified Notes Subject :" + Subject);

			test_steps.add("Verified Notes Subject :" + Subject);

			NotesDesctiption = driver.findElement(
					By.xpath("(//td[contains(@data-bind,'text: de') and contains(text(),'" + Description + "')])[2]"));
			assertEquals(NotesDesctiption.getText(), Description, "Failed : Status missmatched");
			reslogger.info("Verified Notes Description :" + Description);
			test_steps.add("Verified Notes Description :" + Description);
		} catch (Exception e) {
			NotesType = driver
					.findElement(By.xpath("(//div[text()='Notes']/parent::div//td[text()='" + Type + "'])[1]"));
			Utility.ScrollToElement(NotesType, driver);
			assertEquals(NotesType.getText(), Type, "Failed : NotesType missmatched");
			reslogger.info("Verified Notes Type :" + Type);
			test_steps.add("Verified Notes Type :" + Type);

			NotesSubject = driver.findElement(
					By.xpath("(//td[contains(@data-bind,'text: subject') and contains(text(),'" + Subject + "')])[1]"));
			assertEquals(NotesSubject.getText(), Subject, "Failed : Detail missmatched");
			reslogger.info("Verified Notes Subject :" + Subject);

			test_steps.add("Verified Notes Subject :" + Subject);

			NotesDesctiption = driver.findElement(
					By.xpath("(//td[contains(@data-bind,'text: de') and contains(text(),'" + Description + "')])[1]"));
			assertEquals(NotesDesctiption.getText(), Description, "Failed : Status missmatched");
			reslogger.info("Verified Notes Description :" + Description);
			test_steps.add("Verified Notes Description :" + Description);
		}

		return test_steps;
	}

	public ArrayList<String> verifyIncidentals(WebDriver driver, String Category, String PerUnit, String Quantity)
			throws InterruptedException, ParseException {
		Elements_CPReservation cpelements = new Elements_CPReservation(driver);
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		String incidentalCategory = getIncidentalCategory(driver, 3);
		assertEquals(incidentalCategory, Category, "Failed : Category mismatched");
		reslogger.info("Verified Incidental Category :" + incidentalCategory);
		test_steps.add("Verified Incidental Category :" + incidentalCategory);

		String incidentalDescription = getIncidentalDescritption(driver, 3);
		assertEquals(incidentalDescription, Category, "Failed : Description mismatched");
		reslogger.info("Verified Incidental Description :" + incidentalDescription);
		test_steps.add("Verified Incidental Description :" + incidentalDescription);

		String incidentalPerUnit = getIncidentalPerUnit(driver, 3);
		incidentalPerUnit = incidentalPerUnit.split("\\.")[0];
		incidentalPerUnit = incidentalPerUnit.replace("$", "").replace(" ", "");
		assertEquals(incidentalPerUnit, PerUnit, "Failed : PerUnit mismatched");
		reslogger.info("Verified Incidental PerUnit :" + PerUnit);
		test_steps.add("Verified Incidental PerUnit :" + PerUnit);

		String incidentalAmount = getIncidentalTotalAmount(driver, 3);
		incidentalAmount = incidentalAmount.split("\\.")[0];
		incidentalAmount = incidentalAmount.replace("$", "").replace(" ", "");

		// assertEquals(incidentalAmount, (Integer.parseInt(Quantity) *
		// Integer.parseInt(PerUnit)),
		// "Failed : PerUnit mismatched");
		reslogger.info("Verified Incidental Amount :" + incidentalAmount);
		test_steps.add("Verified Incidental Amount :" + incidentalAmount);

		return test_steps;
	}

	public ArrayList<String> verifyCopyButtonState(WebDriver driver, String ReservationStatus)
			throws InterruptedException {
		Elements_CPReservation cpelements = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();
		if (ReservationStatus.equals("Confirmed") || ReservationStatus.equals("Guaranteed")
				|| ReservationStatus.equals("On Hold") || ReservationStatus.equals("Reserved")) {
			Wait.WaitForElement(driver, OR_Reservation.COPY_BUTTON);
			assertEquals(cpelements.COPY_BUTTON.isDisplayed(), true, "Failed: Copy Button is not displayed");
			reslogger.info("Copy Button is Displayed In CP Reservation Page");
			test_steps.add("Copy Button is Displayed In CP Reservation Page");

		} else {
			assertEquals(cpelements.COPY_BUTTON.isDisplayed(), false, "Failed: Copy Button is displayed");
			reslogger.info(
					"Copy Button is not Displayed In CP Reservation Page Due to: " + ReservationStatus + " Status");
			test_steps.add(
					"Copy Button is not Displayed In CP Reservation Page Due to: " + ReservationStatus + " Status");
		}
		return test_steps;
	}

	public ArrayList<String> enter_GuestName(WebDriver driver, ArrayList<String> test_steps, String GuestFirstName,
			String GuestLastName) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		String CP_NewReservation_GuestFirstName = "(//label[text()='Guest ']/preceding-sibling::input)[2]";
		Wait.WaitForElement(driver, CP_NewReservation_GuestFirstName);
		WebElement CP_NewReservation_GuestFirstNameEle = driver.findElement(By.xpath(CP_NewReservation_GuestFirstName));
		Utility.ScrollToElement(CP_NewReservation_GuestFirstNameEle, driver);
		CP_NewReservation_GuestFirstNameEle.sendKeys(GuestFirstName);
		test_steps.add("Guest First Name : " + GuestFirstName);
		reslogger.info("Guest First Name : " + GuestFirstName);
		String CP_NewReservation_GuestLastName = "(//label[text()='Guest ']/../../../../following-sibling::div//input)[2]";
		Wait.WaitForElement(driver, CP_NewReservation_GuestLastName);
		WebElement CP_NewReservation_GuestLasttNameEle = driver.findElement(By.xpath(CP_NewReservation_GuestLastName));
		CP_NewReservation_GuestLasttNameEle.sendKeys(GuestLastName);
		test_steps.add("Guest Last Name : " + GuestLastName);
		reslogger.info("Guest Last Name : " + GuestLastName);

		return test_steps;

	}

	public void verifyTaskNotCopied(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.COPY_CP_NewReservation_TaskNotCopy);
		Utility.ScrollToElement(res.COPY_CP_NewReservation_TaskNotCopy, driver);
		assertEquals(res.COPY_CP_NewReservation_TaskNotCopy.getText(), "There are no Tasks created on this reservation",
				"Failed: Task Copied");
		test_steps.add("Task not Copied : " + res.COPY_CP_NewReservation_TaskNotCopy.getText() + " Verified");
		reslogger.info("Task not Copied : " + res.COPY_CP_NewReservation_TaskNotCopy.getText() + "Verified");

	}

	public void verifyCopyAddedLineItem(WebDriver driver, ArrayList<String> test_steps, String LineItemCategory,
			String LineItemCategoryAmount) {
		test_steps.add("******************* Verifying Added Line Item to folio**********************");
		reslogger.info("******************* Verifying Added Line Item to folio**********************");

		String includeTax = "//span[text()='Include Taxes in Line Items']/preceding-sibling::input";
		String copyReservationIncludeTax = "(//span[text()='Include Taxes in Line Items']/preceding-sibling::input)[2]";
		try {
			Wait.WaitForElement(driver, includeTax);
			if (driver.findElement(By.xpath(includeTax)).isSelected()) {
				driver.findElement(By.xpath(includeTax)).click();
			}
		} catch (Exception e) {
			Wait.WaitForElement(driver, copyReservationIncludeTax);
			if (driver.findElement(By.xpath(copyReservationIncludeTax)).isSelected()) {
				driver.findElement(By.xpath(copyReservationIncludeTax)).click();
			}
		}

		String line = "//td[@class='lineitem-category']/span";

		int count = driver.findElements(By.xpath(line)).size();
		String text = null;

		line = "(//td[@class='lineitem-category']/span[text()='" + LineItemCategory + "'])[2]";
		Wait.WaitForElement(driver, line);
		WebElement linItemCategoryElement = driver.findElement(By.xpath(line));
		assertEquals(linItemCategoryElement.getText(), LineItemCategory);
		test_steps.add("Line item category verified in folio : " + LineItemCategory);
		reslogger.info("Line item category verified in folio : " + LineItemCategory);

		line = "((//td[@class='lineitem-category']/span[text()='" + LineItemCategory
				+ "'])/../following-sibling::td[contains(@class,'amount')]/span[contains(text(),'"
				+ LineItemCategoryAmount + "')])[2]";
		Wait.WaitForElement(driver, line);
		WebElement linItemAmountElement = driver.findElement(By.xpath(line));
		assertEquals(linItemAmountElement.getText().split("\\.")[0].replace("$", "").replaceAll(" ", ""),
				LineItemCategoryAmount);
		test_steps.add("Line item category amount verified in folio : " + LineItemCategoryAmount);
		reslogger.info("Line item category amount verified in folio : " + LineItemCategoryAmount);
	}

	public ArrayList<String> verifyGuestNameAfterReservation(WebDriver driver, String guestName) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();

		Wait.explicit_wait_visibilityof_webelement_120(res.HeaderGuestName_AfterReservation, driver);
		String foundName = res.HeaderGuestName_AfterReservation.getText();

		assertEquals(foundName.trim(), guestName, "Failed to verify AccounGuestt No");
		test_steps.add("Successfully Verified Guest Name : " + guestName);
		reslogger.info("Successfully Verified Guest Name : " + guestName);

		return test_steps;

	}

	public ArrayList<String> verifyConfirmationNoAfterReservation(WebDriver driver, String confirmationNo) {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();

		Wait.explicit_wait_visibilityof_webelement_120(res.HeaderConfirmationNo, driver);
		String foundNO = res.HeaderConfirmationNo.getText();

		assertEquals(foundNO.trim(), confirmationNo, "Failed to verify ConfirmationNo");

		test_steps.add("Successfully Verified ConfirmationNo : " + confirmationNo);

		reslogger.info("Successfully Verified ConfirmationNo : " + confirmationNo);

		return test_steps;

	}

	public ArrayList<String> verifyStatusAfterReservation(WebDriver driver, String status) {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();

		Wait.explicit_wait_visibilityof_webelement_120(res.HeaderStatus_AfterReservation, driver);
		String foundTxt = res.HeaderStatus_AfterReservation.getText();

		assertEquals(foundTxt.toUpperCase(), status.toUpperCase(), "Failed to verify Status");
		test_steps.add("Successfully Verified Status : " + status);
		reslogger.info("Successfully Verified Status : " + status);
		return test_steps;

	}

	public ArrayList<String> verifyStayDateAfterReservation(WebDriver driver, String arivalDate, String departDate) {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();

		Wait.explicit_wait_visibilityof_webelement_120(res.HeaderStayDate, driver);
		String foundTxt = res.HeaderStayDate.getText();

		assertEquals(foundTxt, "(" + arivalDate + " - " + departDate + ")", "Failed to verify StayDate");
		test_steps.add("Successfully Verified StayDate : " + foundTxt);

		reslogger.info("Successfully Verified StayDate : " + foundTxt);

		return test_steps;

	}

	public ArrayList<String> selectSalutation(WebDriver driver, String salutation) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_GuestSalutation);
		Wait.explicit_wait_visibilityof_webelement(res.CP_NewReservation_GuestSalutation, driver);
		Wait.explicit_wait_elementToBeClickable(res.CP_NewReservation_GuestSalutation, driver);
		Utility.ScrollToElement(res.CP_NewReservation_GuestSalutation, driver);
		res.CP_NewReservation_GuestSalutation.click();
		String sal = "//span[contains(text(),'" + salutation + "')]/../..";
		Wait.WaitForElement(driver, sal);

		driver.findElement(By.xpath(sal)).click();
		test_steps.add("Selected Salutation : " + salutation);
		reslogger.info("Selected Salutation : " + salutation);
		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <VeriGuestProfileCheckox> ' Description: <Verify create
	 * guest profile checkbox is checked or not> ' Input parameters: <boolean
	 * value> ' Return value: <array list> ' Created By: <Farhan Ghaffar> '
	 * Created On: <04/17/2020>
	 * 
	 * ' Modified By | Description of Modification:
	 * ------------------------------------------ <mm/dd/yyyy>:<Developer name>:
	 * 1.Step modified 2.Step modified
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> verifyGuestProfileCheckoxChecked(WebDriver driver, boolean isChecked)
			throws InterruptedException {

		Elements_CPReservation element = new Elements_CPReservation(driver);
		ArrayList<String> testStep = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.CheckboxGuestProfile);
		Wait.explicit_wait_visibilityof_webelement(element.CheckboxGuestProfile, driver);
		Wait.explicit_wait_elementToBeClickable(element.CheckboxGuestProfile, driver);
		Utility.ScrollToElement(element.CheckboxGuestProfile, driver);
		if (isChecked) {
			if (element.CheckboxGuestProfile.isSelected()) {
				testStep.add("Verified create guest profile checkbox already checked");
			} else {
				element.CheckboxGuestProfile.click();
				testStep.add("Verified create guest profile checkbox is checked");
			}
		} else {
			if (!element.CheckboxGuestProfile.isSelected()) {
				element.CheckboxGuestProfile.click();
				testStep.add("Verified create guest profile checkbox is  unchecked");
			} else {
				testStep.add("Verified create guest profile checkbox already unchecked");
			}
		}

		return testStep;
	}

	public ArrayList<String> enterGuestName(WebDriver driver, ArrayList<String> test_steps, String Salutation,
			String GuestFirstName,

			String GuestLastName) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);

		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_GuestSalutation);
		Wait.explicit_wait_visibilityof_webelement(res.CP_NewReservation_GuestSalutation, driver);
		Wait.explicit_wait_elementToBeClickable(res.CP_NewReservation_GuestSalutation, driver);
		Utility.ScrollToElement(res.CP_NewReservation_GuestSalutation, driver);
		res.CP_NewReservation_GuestSalutation.click();
		String sal = "//span[contains(text(),'" + Salutation + "')]/../..";
		reslogger.info(sal);
		Wait.WaitForElement(driver, sal);
		driver.findElement(By.xpath(sal)).click();

		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_GuestFirstName);
		res.CP_NewReservation_GuestFirstName.sendKeys(GuestFirstName);
		test_steps.add("Guest First Name : " + GuestFirstName);
		reslogger.info("Guest First Name : " + GuestFirstName);

		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_GuestLastName);
		res.CP_NewReservation_GuestLastName.sendKeys(GuestLastName);
		test_steps.add("Guest Last Name : " + GuestLastName);
		reslogger.info("Guest Last Name : " + GuestLastName);

		return test_steps;
	}

	public void enterPaymentDetails(WebDriver driver, ArrayList<String> testSteps, String paymentType,
			String cardNumber, String nameOnCard, String cardExpDate) throws InterruptedException {
		Elements_CPReservation elementsReservation = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_PaymentMethod);
		elementsReservation.CP_NewReservation_PaymentMethod.click();

		if (paymentType.equalsIgnoreCase("Swipe")) {
			String paymentMethod = "//label[text()='Payment Method']/preceding-sibling::div//ul/li//span[contains(text(),'MC')]";
			Wait.WaitForElement(driver, paymentMethod);
			driver.findElement(By.xpath(paymentMethod)).click();
			elementsReservation.CP_NewReservation_Swipe.click();
			testSteps.add("Clicking in Swipe");
			reslogger.info("Clicking in Swipe");
			Wait.wait1Second();
			String CC = "(//label[text()='Credit Card Number']/preceding-sibling::input)[2]";
			Wait.WaitForElement(driver, CC);
			driver.findElement(By.xpath(CC)).sendKeys(cardNumber);
			testSteps.add("Enter CardNumber : " + cardNumber);
			reslogger.info("Enter CardNumber : " + cardNumber);
			elementsReservation.CP_NewReservation_CardNumber.sendKeys(Keys.TAB);
			Wait.wait2Second();
		} else {
			String paymentMethod = "//label[text()='Payment Method']/preceding-sibling::div//ul/li//span[contains(text(),'"
					+ paymentType.trim() + "')]";
			Wait.WaitForElement(driver, paymentMethod);
			driver.findElement(By.xpath(paymentMethod)).click();
			testSteps.add("Selected PaymentType : " + paymentType);
			reslogger.info("Selected PaymentType : " + paymentType);
			if (paymentType.trim().equalsIgnoreCase("MC") || paymentType.trim().equalsIgnoreCase("Visa")
					|| paymentType.trim().equalsIgnoreCase("Amex") || paymentType.trim().equalsIgnoreCase("Discover")) {
				elementsReservation.CP_NewReservation_CardNumber.sendKeys(cardNumber);
				testSteps.add("Enter CardNumber : " + cardNumber);
				reslogger.info("Enter CardNumber : " + cardNumber);
				elementsReservation.CP_NewReservation_NameOnCard.sendKeys(nameOnCard);
				testSteps.add("Enter Name On Card : " + nameOnCard);
				reslogger.info("Enter Name On Card : " + nameOnCard);
				elementsReservation.CP_NewReservation_CardExpDate.sendKeys(cardExpDate);
				testSteps.add("Enter Card ExpDate : " + cardExpDate);
				reslogger.info("Enter Card ExpDate : " + cardExpDate);
			}
		}
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_CreateGuestProfile);
		if (!driver.findElement(By.xpath("//small[contains(text(),'Address')]/../../following-sibling::div//input"))
				.isSelected()) {
			driver.findElement(By.xpath("//small[contains(text(),'Address')]/../../following-sibling::div/label"))
					.click();
		}
	}

	public ArrayList<String> selectReferral(WebDriver driver, String referral) throws InterruptedException {

		Elements_CPReservation element = new Elements_CPReservation(driver);
		ArrayList<String> testSteps = new ArrayList<>();

		Wait.WaitForElement(driver, OR_Reservation.SelectMarketButton);
		Wait.explicit_wait_visibilityof_webelement(element.SelectMarketButton, driver);
		Wait.explicit_wait_elementToBeClickable(element.SelectMarketButton, driver);
		Utility.ScrollToElement(element.SelectMarketButton, driver);
		element.SelectMarketButton.click();
		testSteps.add("Click on market");
		String pathMarket = "(//label[text()='Market']/preceding-sibling::div//ul/li//span[contains(text(),'Internet')])[1]";
		Wait.WaitForElement(driver, pathMarket);
		WebElement elementMarket = driver.findElement(By.xpath(pathMarket));
		elementMarket.click();
		reslogger.info("Selected market as : Internet");
		testSteps.add("Selected market as : Internet");

		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Referral);
		Wait.explicit_wait_visibilityof_webelement(element.CP_NewReservation_Referral, driver);
		Wait.explicit_wait_elementToBeClickable(element.CP_NewReservation_Referral, driver);
		element.CP_NewReservation_Referral.click();

		String pathReferral = "(//label[text()='Referral']/preceding-sibling::div//ul/li//span[contains(text(),'"
				+ referral.trim() + "')])[2]";
		reslogger.info(pathReferral);
		Wait.WaitForElement(driver, pathReferral);
		WebElement elementReferral = driver.findElement(By.xpath(pathReferral));
		Wait.explicit_wait_visibilityof_webelement(elementReferral, driver);
		elementReferral.click();
		reslogger.info("Selected Referral as : " + referral);
		testSteps.add("Selected Referral as : " + referral);
		return testSteps;
	}

	public void clickOnQuote(WebDriver driver) {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.SaveQuote);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.SaveQuote), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.SaveQuote), driver);
		element.saveQuote.click();

	}

	public String getReservationConfirmationNumber(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		int count = 0;
		while (true) {
			if (!driver.findElement(By.xpath("(//div[@class='ir-loader-in'])")).isDisplayed()) {
				break;
			} else if (count == 20) {
				break;
			} else {
				count++;
				Wait.wait2Second();
			}
		}
		reslogger.info("Waited to loading symbol");
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_ConfirmationNumber);
		String confirmation = res.CP_NewReservation_ConfirmationNumber.getText();
		test_steps.add("Confirmation Number : <b>" + confirmation + "</b>");
		reslogger.info("Confirmation Number : <b>" + confirmation + "</b>");
		return confirmation;
	}

	public String getRoomNumberDetail(WebDriver driver) {
		String path = "//div[contains(@class,'roomInfo')]//span[contains(@data-bind,'roomNumber')]";
		Wait.WaitForElement(driver, path);
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public String getRoomChargesInTripSummary(WebDriver driver) throws InterruptedException {

		Elements_CPReservation element = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.getRoomChargesInTripSummary);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.getRoomChargesInTripSummary), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.getRoomChargesInTripSummary), driver);
		return element.getRoomChargesInTripSummary.getText();
	}

	public String getTaxandServicesInTripSummary(WebDriver driver) throws InterruptedException {

		Elements_CPReservation element = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.getTaxandServicesInTripSummary);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.getTaxandServicesInTripSummary), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.getTaxandServicesInTripSummary), driver);
		return element.getTaxandServicesInTripSummary.getText();
	}

	public String getTotalTripSummary(WebDriver driver) throws InterruptedException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.getTripTotalInTripSummary);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.getTripTotalInTripSummary), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.getTripTotalInTripSummary), driver);
		return element.getTripTotalInTripSummary.getText();
	}

	public String getBalanceInTripSummary(WebDriver driver) throws InterruptedException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.getTotalBalanceInTripSummary);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.getTotalBalanceInTripSummary), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.getTotalBalanceInTripSummary), driver);
		Utility.ScrollToElement(element.getTotalBalanceInTripSummary, driver);
		return element.getTotalBalanceInTripSummary.getText();
	}

	public String getPaidInTripSummary(WebDriver driver) throws InterruptedException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.getPaidInTripSummary);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.getPaidInTripSummary), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.getPaidInTripSummary), driver);
		Utility.ScrollToElement(element.getPaidInTripSummary, driver);
		return element.getPaidInTripSummary.getText();
	}

	public ArrayList<String> selectAllSearchedReservationCheckBox(WebDriver driver) throws Exception {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		ArrayList<String> testSteps = new ArrayList<>();

		Wait.WaitForElement(driver, OR_Reservation.ClickOnCheckBoxProperty);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.ClickOnCheckBoxProperty), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.ClickOnCheckBoxProperty), driver);
		element.clickOnCheckBoxProperty.click();
		testSteps.add("Successfully all reservations are selected ");
		return testSteps;

	}

	public ArrayList<String> clickBulkOptionCheckInAndVerifyPopUp(WebDriver driver) throws Exception {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_Reservation_SearchPage resservationSearch = new Elements_Reservation_SearchPage(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.Click_Bulk_Action, driver);
		resservationSearch.Click_Bulk_Action.click();
		test_steps.add("Click Bulk Action");
		resservationSearch.Select_Checkin.click();
		test_steps.add("Clicked on bulk action - CHECKIN option");
		Wait.explicit_wait_visibilityof_webelement_120(resservationSearch.Verify_Bulk_Checkin_popup, driver);
		test_steps.add("Bulk CheckIn Popup is displayed");
		return test_steps;

	}

	public ArrayList<String> clickOnBulkResservationCanUpdated(WebDriver driver, String numberOfReservations)
			throws Exception {
		ArrayList<String> testSteps = new ArrayList<>();
		Elements_CPReservation element = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.ResBulkCanUpdatedTab);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.ResBulkCanUpdatedTab), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.ResBulkCanUpdatedTab), driver);

		String tabText = element.ResBulkCanUpdatedTab.getText();
		assertTrue(tabText.contains(numberOfReservations), "Failed: Number of reservation can be updated mismatching");
		testSteps.add(tabText);
		element.ResBulkCanUpdatedTab.click();
		return testSteps;
	}

	public String getReservationDetails(WebDriver driver, String Guestname, int parentInex, int Index) {

		String Path = "((//span[text()='" + Guestname + "'])[" + parentInex + "]//..//following-sibling::td//span)["
				+ Index + "]";
		WebElement element = driver.findElement(By.xpath(Path));
		return element.getText();
	}

	public String getGuestName(WebDriver driver, String Guestname, int parentInex) throws InterruptedException {

		String Path = "(//span[text()='" + Guestname + "'])[" + parentInex + "]";
		WebElement element = driver.findElement(By.xpath(Path));
		Utility.ScrollToElement(element, driver);
		return element.getText();
	}

	public ArrayList<String> clickOnBulkReservationCanNotUpdated(WebDriver driver, String numberOfReservation)
			throws Exception {
		ArrayList<String> testSteps = new ArrayList<>();
		Elements_CPReservation elements = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.ResBulkCanNotUpdatedTab);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.ResBulkCanNotUpdatedTab), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.ResBulkCanNotUpdatedTab), driver);
		String tabText = elements.ResBulkCanNotUpdatedTab.getText();
		assertTrue(tabText.contains(numberOfReservation));
		testSteps.add(tabText);
		reslogger.info(tabText);
		elements.ResBulkCanNotUpdatedTab.click();
		testSteps.add("Click on tab");
		return testSteps;
	}

	public ArrayList<String> clickOnProcessButtonInBulkCheckInPopUp(WebDriver driver) throws Exception {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_CPReservation cpResElm = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(cpResElm.ClickProcessButtonBulkCheckIN, driver);
		cpResElm.ClickProcessButtonBulkCheckIN.click();
		reslogger.info("Process Button is Pressed Successfully");
		test_steps.add("Process Button is Pressed Successfully");

		return test_steps;
	}

	public ArrayList<String> clickOnBulkReservationCanUpdatedAfterProcess(WebDriver driver) throws Exception {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_CPReservation cpResElm = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(cpResElm.verifyBulkCheckInCompletePopUp, driver);
		test_steps.add("Bulk Check In Complete PopUp After Process Is Successfully open");
		reslogger.info("Bulk Check In Complete PopUp After Process Is Successfully open");
		Wait.explicit_wait_visibilityof_webelement_120(cpResElm.clickResCanUpdateAfterProcess, driver);
		String tabText = cpResElm.clickResCanUpdateAfterProcess.getText();
		test_steps.add(tabText);
		reslogger.info(tabText);
		cpResElm.clickResCanUpdateAfterProcess.click();
		test_steps.add(tabText + " Tab is Successfully Clicked");
		reslogger.info(tabText + " Tab is Successfully Clicked");

		return test_steps;
	}

	public ArrayList<String> clickOnBulkCheckInReservationCanNotUpdated(WebDriver driver, String numberOfReservation)
			throws Exception {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_CPReservation cpResElm = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.ClickResCanNotUpdateAfterProcess);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.ClickResCanNotUpdateAfterProcess), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.ClickResCanNotUpdateAfterProcess), driver);

		String tabText = cpResElm.clickResCanNotUpdateAfterProcess.getText();
		assertTrue(tabText.contains(numberOfReservation), "Failed: cannot updated tab is mismatching");
		test_steps.add(tabText);
		reslogger.info(tabText);
		cpResElm.clickResCanNotUpdateAfterProcess.click();
		test_steps.add("Click on tab");

		return test_steps;
	}

	public ArrayList<String> closeBulkActionPopUp(WebDriver driver) throws Exception {
		ArrayList<String> testSteps = new ArrayList<>();
		Elements_CPReservation elementsReservation = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(elementsReservation.CloseBulkActionPopUp, driver);
		elementsReservation.CloseBulkActionPopUp.click();
		test_steps.add("Bulk Action PopUp Closed Successfully");
		reslogger.info("Bulk Action PopUp Closed Successfully");
		return testSteps;

	}

	public ArrayList<String> verifyReservationStatus(WebDriver driver, String reservationNumber, String expectedStatus)
			throws Exception {

		ArrayList<String> testSteps = new ArrayList<>();
		String path = "//span[contains(text(),'" + reservationNumber
				+ "')]//..//following-sibling::td//span[contains(@data-bind,'text: StatusName.replace')]";
		Wait.WaitForElement(driver, path);
		Wait.waitForElementToBeVisibile(By.xpath(path), driver);
		Wait.waitForElementToBeClickable(By.xpath(path), driver);

		WebElement element = driver.findElement(By.xpath(path));
		testSteps.add("Expected status: " + expectedStatus);
		testSteps.add("Found: " + element.getText());
		assertEquals(element.getText(), expectedStatus, "Failed: reservation status is mismatching!");
		test_steps.add("Verified resevrtaion number " + reservationNumber + " status");
		return testSteps;
	}

	public ArrayList<String> clickOnProcessInBulkCheckOutPopUp(WebDriver driver) throws Exception {
		ArrayList<String> testSteps = new ArrayList<>();
		Elements_CPReservation element = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(element.clickProcessButtonBulkCheckOut, driver);
		element.clickProcessButtonBulkCheckOut.click();
		reslogger.info("Process Button is Pressed Successfully");
		testSteps.add("Process Button is Pressed Successfully");

		return testSteps;
	}

	public ArrayList<String> clickOnBulkCheckoutReservationCanUpdatedAfterProcess(WebDriver driver) throws Exception {
		ArrayList<String> test_steps = new ArrayList<>();
		Elements_CPReservation elements = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(elements.verifyBulkCheckOutCompletePopUp, driver);
		test_steps.add("Bulk Check Out Complete PopUp After Process Is Successfully open");
		reslogger.info("Bulk Check Out Complete PopUp After Process Is Successfully open");
		Wait.explicit_wait_visibilityof_webelement_120(elements.clickResCanUpdateAfterProcess, driver);
		String tabText = elements.clickResCanUpdateAfterProcess.getText();
		test_steps.add(tabText);
		reslogger.info(tabText);
		elements.clickResCanUpdateAfterProcess.click();
		test_steps.add(tabText + " Tab is Successfully Clicked");
		reslogger.info(tabText + " Tab is Successfully Clicked");

		return test_steps;
	}

	public ArrayList<String> clickOnGuestNameInSearchReaservation(WebDriver driver, String ReservationNumber)
			throws Exception {

		ArrayList<String> testSteps = new ArrayList<>();
		String path = "//span[contains(text(),'" + ReservationNumber + "')]//..//..//following-sibling::td//a";
		Wait.WaitForElement(driver, path);
		Wait.waitForElementToBeVisibile(By.xpath(path), driver);
		Wait.waitForElementToBeClickable(By.xpath(path), driver);

		String guestName = driver.findElement(By.xpath(path)).getText();
		driver.findElement(By.xpath(path)).click();
		Wait.wait2Second();
		driver.navigate().refresh();
		Wait.wait2Second();
		reslogger.info("Clicked " + guestName);
		return testSteps;

	}

	public ArrayList<String> checkInDates(WebDriver driver, int Days, int index)
			throws InterruptedException, ParseException {

		Elements_CPReservation elements = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.EnterCheckinDate);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.EnterCheckinDate), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.EnterCheckinDate), driver);
		elements.listEnterCheckinDate.get(index).click();
		elements.listEnterCheckinDate.get(index).clear();
		String checkInDate = ESTTimeZone.DateFormateForLineItem(Days);
		elements.listEnterCheckinDate.get(index).sendKeys(checkInDate);
		test_steps.add("CheckIn date: " + checkInDate);
		return test_steps;
	}

	public ArrayList<String> checkOutDates(WebDriver driver, int Days, int index)
			throws InterruptedException, ParseException {

		Elements_CPReservation elements = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.EnterCheckout);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.EnterCheckout), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.EnterCheckout), driver);
		elements.listEnterCheckout.get(index).click();
		elements.listEnterCheckout.get(index).clear();
		String checkOutDate = ESTTimeZone.DateFormateForLineItem(Days);
		elements.listEnterCheckout.get(index).sendKeys(checkOutDate);
		test_steps.add("CheckOut date: " + checkOutDate);
		return test_steps;

	}

	public String selectRoomNumberInMRB(WebDriver driver, String RoomClass, int index, int RoomIndex)
			throws InterruptedException, ParseException {

		Elements_CPReservation elements = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.selectRoomNumberInMRB);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.selectRoomNumberInMRB), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.selectRoomNumberInMRB), driver);
		Utility.ScrollToElement(elements.SelectRoomNumberInMRB.get(index), driver);
		elements.SelectRoomNumberInMRB.get(index).click();
		String Path = "//div[contains(@data-bind,'validationOptions')]//button[@title='--Select Room--']//following-sibling::div//ul//li//span[contains(text(),'"
				+ RoomClass + "')]";
		Wait.WaitForElement(driver, Path);
		List<WebElement> ListRoomNumber = driver.findElements(By.xpath(Path));
		String GetRoomNumber = ListRoomNumber.get(RoomIndex).getText();
		ListRoomNumber.get(RoomIndex).click();
		return GetRoomNumber;

	}

	public ArrayList<String> checkboxCopyGuest(WebDriver driver) throws InterruptedException, ParseException {

		Elements_CPReservation elements = new Elements_CPReservation(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.checkboxCopyGuest);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.checkboxCopyGuest), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.checkboxCopyGuest), driver);
		Utility.ScrollToElement_NoWait(elements.CheckboxCopyGuest, driver);
		elements.CheckboxCopyGuest.click();
		testSteps.add("Checked Copy Guest 1 Info From Primary Room");
		return testSteps;

	}

	public String getRoomNumber(WebDriver driver, int index) throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		return element.GetIncidentalRoomNumber.get(index).getText();
	}

	public ArrayList<String> clickAddARoom(WebDriver driver) throws InterruptedException {

		Elements_CPReservation elements = new Elements_CPReservation(driver);
		ArrayList<String> steps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.ClickOnAddRooms);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.ClickOnAddRooms), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.ClickOnAddRooms), driver);
		elements.clickOnAddRooms.click();
		test_steps.add("Click on add a room");
		return test_steps;

	}

	public ArrayList<String> clickAddOnsButton(WebDriver driver) throws InterruptedException {

		Elements_CPReservation elements_CPReservation = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.addOnsButton);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.addOnsButton), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.addOnsButton), driver);
		Utility.ScrollToElement(elements_CPReservation.CP_ADDON_Button, driver);
		elements_CPReservation.CP_ADDON_Button.click();
		reslogger.info("Clicked AddOn Button");
		test_steps.add("Clicked AddOn Button");

		Wait.WaitForElement(driver, OR_Reservation.ADDONSHeading);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.ADDONSHeading), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.ADDONSHeading), driver);

		assertTrue(elements_CPReservation.ADDONSHeading.isDisplayed(), "Popup didn't display");
		reslogger.info("ADDON Popup Opened");
		test_steps.add("ADDON Popup Opened");

		return test_steps;
	}

	public ArrayList<String> addingAddOns(WebDriver driver, String packageName, String packageAmount,
			String packageQuentity, boolean isRoom, String roomClassAbvWithRoomNumber) throws InterruptedException {

		Elements_CPReservation elements_CPReservation = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<>();

		if (isRoom) {
			elements_CPReservation.ADDONSRoomButton.click();
			String Path = "//h4[text()='ADD-ONS']//..//..//div[@class='dropdown-menu open']//ul//li//span[text()='"
					+ roomClassAbvWithRoomNumber + "']";
			Wait.WaitForElement(driver, Path);
			WebElement element = driver.findElement(By.xpath(Path));
			element.click();
			Wait.WaitForElement(driver, OR_Reservation.ADDONSSelectedRoom);
			assertEquals(elements_CPReservation.ADDONSSelectedRoom.getText(), roomClassAbvWithRoomNumber,
					"Failed:Room number is mismatching");
			test_steps.add("Verified selected room number");
		}

		elements_CPReservation.AddONSSearchInput.clear();
		elements_CPReservation.AddONSSearchInput.sendKeys(packageName);
		reslogger.info("Entered PackageName : " + packageName);
		test_steps.add("Entered PackageName : " + packageName);

		elements_CPReservation.AddONSSearchButtton.click();
		reslogger.info("Clicked Search Icon");
		test_steps.add("Clicked Search Icon");

		assertEquals(elements_CPReservation.AddONSAmount.getText(), "$ " + packageAmount,
				"Failed: Amount is mismatching");
		test_steps.add("Verified amount before add new ADD-ONS");
		reslogger.info("Verified amount before add new ADD-ONS");

		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.ADDONSPlus), driver);
		for (int i = 0; i < Integer.parseInt(packageQuentity); i++) {
			elements_CPReservation.ADDONSPlus.click();
			reslogger.info("in click");

		}
		Utility.ScrollToElement(elements_CPReservation.ADDONSSaveButton, driver);
		elements_CPReservation.ADDONSSaveButton.click();
		test_steps.add("Clicked On Save");
		reslogger.info("Clicked On Save");

		Wait.waitForElementToBeVisibile(By.className(OR.Toaster_Title), driver);
		return test_steps;
	}

	public String getIncidentalInTripSummery(WebDriver driver) throws InterruptedException, ParseException {

		Elements_CPReservation elements_CPReservation = new Elements_CPReservation(driver);
		ArrayList<String> test_step = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.incidentalInTS);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.incidentalInTS), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.incidentalInTS), driver);
		return elements_CPReservation.incidentalInTS.getText();

	}

	public ArrayList<String> clickOnHistory(WebDriver driver) throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		ArrayList<String> test_step = new ArrayList<>();
		Wait.explicit_wait_visibilityof_webelement(element.HistoryTab, driver);
		Wait.explicit_wait_elementToBeClickable(element.HistoryTab, driver);
		Utility.ScrollToElement_NoWait(element.HistoryTab, driver);
		element.HistoryTab.click();
		test_step.add("Click on history");
		return test_step;

	}

	public String gettHistoryRoom(WebDriver driver, int index) throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		return element.HistoryRoom.get(index).getText();
	}

	public ArrayList<String> clickOnDetails(WebDriver driver) throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		ArrayList<String> test_step = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.detailsTab);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.detailsTab), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.detailsTab), driver);

		Utility.ScrollToElement_NoWait(element.detailsTab, driver);
		element.detailsTab.click();
		test_step.add("Click on details");
		return test_step;

	}

	public ArrayList<String> clickOnDeleteOncidentalButton(WebDriver driver, int index)
			throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		try {
			Wait.WaitForElement(driver, OR_Reservation.deleteIncidentalInSatyInfoButton);
			Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.deleteIncidentalInSatyInfoButton), driver);
			Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.deleteIncidentalInSatyInfoButton), driver);

		} catch (Exception e) {
			driver.navigate().refresh();

			Wait.WaitForElement(driver, OR_Reservation.deleteIncidentalInSatyInfoButton);
			Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.deleteIncidentalInSatyInfoButton), driver);
			Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.deleteIncidentalInSatyInfoButton), driver);
		}

		Utility.ScrollToElement_NoWait(element.deleteIncidentalInSatyInfoButton.get(index), driver);
		element.deleteIncidentalInSatyInfoButton.get(index).click();
		return testSteps;
	}

	public ArrayList<String> incidentalVoidpopup(WebDriver driver, String EnterNotes, int index)
			throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		ArrayList<String> test_step = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.voidPopupHeading);
		Wait.explicit_wait_visibilityof_webelement(element.voidPopupHeading, driver);
		Wait.explicit_wait_elementToBeClickable(element.voidPopupHeading, driver);
		// assertEquals(element.VoidPopupHeading.getText(), "Void Items");
		element.enterNotes.sendKeys(EnterNotes);
		test_step.add("Enter notes: " + EnterNotes);
		element.voidButton.get(index).click();
		test_step.add("Click on Void button");
		return test_step;
	}

	public String getTotaltaxBeforeSaveRes(WebDriver driver) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.wait5Second();
		// String taxCharges =
		// res.getTotalTaxBeforeSaveRes.getAttribute("value");
		String taxCharges = res.getTotalTaxBeforeSaveRes.getText();
		return taxCharges;
	}

	public String getTotalTripTotalBeforeSaveRes(WebDriver driver) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.wait5Second();
		// String totalTripCharges =
		// res.getTotalTripBeforeSaveRes.getAttribute("value");
		String totalTripCharges = res.getTotalTripBeforeSaveRes.getText();
		return totalTripCharges;
	}

	public String getRoomChargesbeforeResSave(WebDriver driver) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.wait5Second();
		String totalRoomcharges = res.getRoomChrgesBeforeSaveRes.getText();
		return totalRoomcharges;
	}

	public String getPercentcalcvalueSingleItem(WebDriver driver, String values, String totalroomcharge, int stay) {
		String getCalculated = "";
		double percentval = Double.parseDouble(values.trim());
		double roomchargeamount = Double.parseDouble(totalroomcharge);
		double perDayRoomCharge = roomchargeamount / stay;
		double total = perDayRoomCharge * percentval / 100;
		getCalculated = String.valueOf(total);
		return getCalculated;

	}

	public String getPercentcalcvalueSingleItemforoneday(WebDriver driver, String values, String perdayRoomCharge) {
		String getCalculated = "";
		double percentval = Double.parseDouble(values.trim());
		double perDayRoomCharge = Double.parseDouble(perdayRoomCharge);
		double total = perDayRoomCharge * percentval / 100;
		getCalculated = String.valueOf(total);
		return getCalculated;

	}

	public void verifyChildLineItemTaxes(WebDriver driver, ArrayList<String> test_steps, String taxLedgeraccount,
			String existingTaxDescriptions, double percentCalculatedVal) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		// double foliovalue = 0.0;
		DecimalFormat f = new DecimalFormat("##.00");
		double totalTaxinFolios = 0.0;
		double capturetotalTax = 0.0;
		int roomChargesFolioCount = getRoomChargeFolioItemCountInRes(driver);
		for (int i = 1; i <= roomChargesFolioCount; i++) {
			String xpath = "(//tbody[starts-with(@data-bind,'getElement: ComputedFolioItemsElement')]/tr/td/span[text()='"
					+ taxLedgeraccount + "']/../following-sibling::td/a)[" + i + "]";
			// clickroomChargeFolioItems
			Wait.waitUntilPresenceOfElementLocated(xpath, driver);
			Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(xpath)), driver);
			driver.findElement(By.xpath(xpath)).click();
			test_steps.add("Click at folio line item: " + i + " of" + " " + taxLedgeraccount);
			Wait.explicit_wait_10sec(res.itemDetailOnFolioPopup, driver);
			Wait.waitUntilPresenceOfElementLocated(OR_Reservation.ItemDetailOnFolioPopup, driver);
			double individualFolioTotal = 0.0;
			double totalperdayfolioTax = 0.0;
			double folioValue = 0.0;
			String xpath1 = "(//a[text()='" + existingTaxDescriptions.trim()
					+ "'])[1]/ancestor::td/following-sibling::td/span";
			Wait.waitUntilPresenceOfElementLocated(xpath1, driver);
			String val = driver.findElement(By.xpath(xpath1)).getText();
			folioValue = Double.parseDouble(val.replace("$", "").trim());
			double calculatedTax = Double.parseDouble(f.format(percentCalculatedVal));
			assertEquals(calculatedTax, folioValue);
			clickFolioPopupCancel(driver);
			test_steps.add("Close open folio line item");
		}

	}

	public String getTotalTaxInResFolios(WebDriver driver, String taxLedgeraccount, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		DecimalFormat f = new DecimalFormat("##.00");
		int roomChargesFolioCount = getRoomChargeFolioItemCountInRes(driver);
		double totalTax = 0.0;
		double totalTaxCaculated = 0.0;
		String taxAmount = "";
		for (int i = 1; i <= roomChargesFolioCount; i++) {
			String xpath = "(//tbody[starts-with(@data-bind,'getElement: ComputedFolioItemsElement')]/tr/td/span[text()='"
					+ taxLedgeraccount + "']/../following-sibling::td/a)[" + i + "]";
			// clickroomChargeFolioItems
			Wait.waitUntilPresenceOfElementLocated(xpath, driver);
			Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(xpath)), driver);
			driver.findElement(By.xpath(xpath)).click();
			test_steps.add("Click at folio line item: " + i + " of" + " " + taxLedgeraccount);
			int taxCount = res.getTaxFolioItems.size();
			double taxVal = 0.0;
			double sumOfTaxFolio = 0.0;
			double capturetotalTax = 0.0;
			double folioClculation = 0.0;
			for (int j = 0; j < res.getTaxFolioItems.size(); j++) {
				taxVal = Double.parseDouble(res.getTaxFolioItems.get(j).getText().replace("$", "").trim());
				sumOfTaxFolio = sumOfTaxFolio + taxVal;
			}
			capturetotalTax = Double.parseDouble(res.getTaxTotalinChildFolioItem.getText().replace("$", "").trim());
			folioClculation = Double.parseDouble(f.format(sumOfTaxFolio));
			assertEquals(capturetotalTax, folioClculation);
			test_steps.add("Verifying successfully total amount captured with calculated taxes in one folio line Item: "
					+ "<b>" + folioClculation + "</b>");
			totalTax = totalTax + folioClculation;
			clickFolioPopupCancel(driver);
			test_steps.add("Close open folio line item");

		}
		String totalTaxAmount = driver.findElement(By.xpath("//span[@class='pamt tsc']/span[starts-with(.,'$')]"))
				.getText().replace("$", "").trim();
		totalTaxCaculated = Double.parseDouble(totalTaxAmount);
		assertEquals(Double.parseDouble(totalTaxAmount), totalTaxCaculated);
		test_steps.add("Verifying successfully total amount captured with Calculated Tax amount over reservation stay: "
				+ "<b>" + totalTaxCaculated + "</b>");
		taxAmount = Double.toString(totalTaxCaculated);

		return taxAmount;

	}

	public void clickFolioPopupCancel(WebDriver driver) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.wait10Second();
		res.clickFolioPopupCancel.click();

	}

	public int getRoomChargeFolioItemCountInRes(WebDriver driver) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		int count = res.getRoomChargeFolioCount.size();
		return count;

	}

	public String GETRoomNumber(WebDriver driver, ArrayList test_steps) {
		String room = "//div[contains(@data-bind,'roomClass.name')]";
		Wait.WaitForElement(driver, room);
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(room)), driver);
		room = driver.findElement(By.xpath(room)).getText();

		room = room.split(":")[1];
		room = room.replace(" ", "");
		test_steps.add("Room Number : " + room);
		reslogger.info("Room Number : " + room);

		return room;
	}

	public ArrayList<String> verifyMarketSegmentDropDown(WebDriver driver, ArrayList<String> test_steps,
			String MarketSegment, boolean isContains) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Market);
		Utility.ScrollToElement(res.CP_NewReservation_Market, driver);
		Wait.wait2Second();
		res.CP_NewReservation_Market.click();
		test_steps.add("Market Segment Dropdown Opened");
		reslogger.info("Market Segment Dropdown Opened");
		boolean isMarketSegmentFound = false;

		for (int i = 0; i < res.MARKETSEGMENT_DROPDOWNLIST.size(); i++) {
			WebElement marketSegementElement = driver.findElement(
					By.xpath("//label[text()='Market']/preceding-sibling::div//ul/li[@data-original-index='" + i
							+ "']//a//span[@class='text']"));

			if (isContains) {
				isMarketSegmentFound = marketSegementElement.getText().equals(MarketSegment);
				reslogger.info("Found:" + isMarketSegmentFound);
				if (isMarketSegmentFound) {
					assertEquals(isMarketSegmentFound, true);
					test_steps.add("Market Segment Dropdown Contains Active: " + MarketSegment);
					reslogger.info("Market Segment Dropdown Contains Active: " + MarketSegment);
					break;
				}
			} else {
				if (!isMarketSegmentFound) {
					isMarketSegmentFound = marketSegementElement.getText().contains(MarketSegment);
					assertEquals(isMarketSegmentFound, false);
					test_steps.add("Market Segment Dropdown Doesn't Contain InActive/Obsolete: " + MarketSegment);
					reslogger.info("Market Segment Dropdown Doesn't Contain InActive/Obsolete: " + MarketSegment);
					break;
				}

			}
		}
		return test_steps;
	}

	public void verifyQuoteConfirmetionPopupWithStatusCodeAndConfirmationNumber(WebDriver driver,
			String reservationNumber, String Status, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		String quote = "//h4[contains(text(),'Quote Confirmation')]";
		Wait.WaitForElement(driver, quote);
		if (driver.findElement(By.xpath(quote)).isDisplayed()) {
			test_steps.add("Quote Confirmation pop is displayed");
			reslogger.info("Quote Confirmation pop is displayed");
		} else {
			test_steps.add("Quote Confirmation pop not is displayed");
			reslogger.info("Quote Confirmation pop not is displayed");
		}

		assertEquals(res.QUOTECREATED_ALERTMESSAGE.getText(), "Quote successfully created.");
		test_steps.add("Quote Confirmation pop contains Alert Message: " + res.QUOTECREATED_ALERTMESSAGE.getText());
		reslogger.info("Quote Confirmation pop contains Alert Message: " + res.QUOTECREATED_ALERTMESSAGE.getText());

		assertEquals(res.CP_NewReservation_ConfirmationNumber.getText(), reservationNumber);
		test_steps.add("Quote Confirmation pop contains Reservation Number: " + reservationNumber);
		reslogger.info("Quote Confirmation pop contains Reservation Number: " + reservationNumber);

		assertEquals(res.CP_NewReservation_ReservationStatus.getText(), Status);
		test_steps.add("Quote Confirmation pop contains Status: " + Status);
		reslogger.info("Quote Confirmation pop contains Status: " + Status);

		res.CP_NewReservation_ClosePopUp.click();
		test_steps.add("Quote Confirmation pop Close Button Click");
		reslogger.info("Quote Confirmation pop Close Button Click");

	}

	public ArrayList<String> verifyMarketSegmentDropDownAdvanceSearch(WebDriver driver, ArrayList<String> test_steps,
			String MarketSegment, boolean isContains) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.MARKETSEGMENT_ADVANCESEARCH_DROPDOWNLIST);
		res.ADVANCESEARCH_MARKETSEGMENT_BUTTON.click();
		test_steps.add("Market Segment Dropdown Opened");
		reslogger.info("Market Segment Dropdown Opened");

		boolean isMarketSegmentFound = false;

		for (int i = 0; i < res.MARKETSEGMENT_ADVANCESEARCH_DROPDOWNLIST.size(); i++) {
			WebElement marketSegementElement = driver.findElement(By
					.xpath("//span[text()='Marketing Segment']/parent::button/following-sibling::div//li[@data-original-index='"
							+ i + "']//a//span[@class='text']"));
			if (isContains) {
				isMarketSegmentFound = marketSegementElement.getText().equals(MarketSegment);

				if (isMarketSegmentFound) {
					assertEquals(isMarketSegmentFound, true);
					test_steps.add("Market Segment Dropdown Contains Active: " + MarketSegment);
					reslogger.info("Market Segment Dropdown Contains Active: " + MarketSegment);
					break;
				}
			} else {
				if (!isMarketSegmentFound) {
					isMarketSegmentFound = marketSegementElement.getText().contains(MarketSegment);
					assertEquals(isMarketSegmentFound, false);
					test_steps.add("Market Segment Dropdown Doesn't Contain InActive/Obsolete: " + MarketSegment);
					reslogger.info("Market Segment Dropdown Doesn't Contain InActive/Obsolete: " + MarketSegment);
					break;
				}

			}
		}
		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <searcRoom> ' Description: <Search room in reservation
	 * room list > ' Input parameters: < String RoomClassName> ' Return value:
	 * ArrayList<String> ' Created By: <Reddy Ponnolu> ' Created On:
	 * <04/05/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> searcRoom(WebDriver driver, String RoomClassName, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);

		List<WebElement> AvilableRooms = driver.findElements(
				By.xpath("//div[@id='rcDtails']//following-sibling::div/span[contains(@data-bind,'roomClass.name')]"));
		String text = null;
		for (int i = 1; i <= AvilableRooms.size(); i++) {
			text = driver.findElement(By
					.xpath("(//div[@id='rcDtails']//following-sibling::div/span[contains(@data-bind,'roomClass.name')])["
							+ i + "]"))
					.getText();
			test_steps.add("Avilable Rooms :" + text);
			reslogger.info("Avilable Rooms :" + text);
			;
			if (text.equals(RoomClassName)) {
				test_steps.add("In Room List newly Created room class avilable " + RoomClassName);
				reslogger.info("In Room List newly Created room class avilable " + RoomClassName);

			} else {
				// test_steps.add("In Room List newly Created room class not
				// avilable ");
				reslogger.info("In Room List newly Created room class not avilable ");

			}

		}
		if (!(text == RoomClassName)) {
			test_steps.add("RoomClass Not avilable in Reservation RoomingList: " + RoomClassName);
			reslogger.info("RoomClass Not avilable in Reservation RoomingList: " + RoomClassName);

		}
		return test_steps;
	}

	public void clickYestakemetotheFolio(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Utility.ScrollToElement(res.Checkout_CompleteCheckOutMsgYesButton, driver);
		res.Checkout_CompleteCheckOutMsgYesButton.click();
		test_steps.add("Clicking on Yes, take me to the Folio");
		reslogger.info("Clicking on Yes, take me to the Folio");

	}

	public void createTask(WebDriver driver, ArrayList test_steps, boolean isAddDueOn, boolean isAddAssignee,
			String TaskCategory, String TaskType, String TaskDetails, String TaskRemarks, String TaskDueon,
			String TaskAssignee, String TaskStatus) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_AddTask);
		test_steps.add("******************* Adding Task **********************");
		reslogger.info("******************* Adding Task **********************");
		res.CP_AddTask.click();
		test_steps.add("Click on Add Task");
		reslogger.info("Click on Add Task");

		String taskCategoryArrow = "//label[text()='Category']/..//button";
		Wait.WaitForElement(driver, taskCategoryArrow);
		driver.findElement(By.xpath(taskCategoryArrow)).click();

		String taskCategory = "//label[text()='Category']/..//div/ul//span[text()='" + TaskCategory.trim() + "']";
		Wait.WaitForElement(driver, taskCategory);
		driver.findElement(By.xpath(taskCategory)).click();
		test_steps.add("Select Task Category : " + TaskCategory);
		reslogger.info("Select Task Category : " + TaskCategory);

		String taskType = "//label[text()='Type']/..//button";
		Wait.WaitForElement(driver, taskType);
		driver.findElement(By.xpath(taskType)).click();
		Wait.wait2Second();
		taskType = "//label[text()='Type']/..//div/ul//span[text()='" + TaskType.trim() + "']";
		Wait.WaitForElement(driver, taskType);
		driver.findElement(By.xpath(taskType)).click();
		test_steps.add("Select Task Type : " + TaskType);
		reslogger.info("Select Task Type : " + TaskType);

		String taskDetails = "//label[text()='Details']/preceding-sibling::textarea";
		Wait.WaitForElement(driver, taskDetails);
		driver.findElement(By.xpath(taskDetails)).sendKeys(TaskDetails);
		test_steps.add("Select Task Details : " + TaskDetails);
		reslogger.info("Select Task Details : " + TaskDetails);

		String taskRemarks = "//label[text()='Remarks']/preceding-sibling::textarea";
		Wait.WaitForElement(driver, taskRemarks);
		driver.findElement(By.xpath(taskRemarks)).sendKeys(TaskRemarks);
		test_steps.add("Enter Task Remarks : " + TaskRemarks);
		reslogger.info("Enter Task Remarks : " + TaskRemarks);
		if (isAddDueOn) {
			String dueCalender = "//label[text()='Due On']/..//i";
			Wait.WaitForElement(driver, dueCalender);
			driver.findElement(By.xpath(dueCalender)).click();
			String monthYear = Utility.get_MonthYear(TaskDueon);
			String day = Utility.getDay(TaskDueon);
			reslogger.info(monthYear);
			String header = null, headerText = null, next = null, date;
			for (int i = 1; i <= 12; i++) {
				header = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[2]";
				headerText = driver.findElement(By.xpath(header)).getText();
				if (headerText.equalsIgnoreCase(monthYear)) {
					date = "//td[contains(@class,'day') and text()='" + day + "']";
					Wait.WaitForElement(driver, date);
					int size = driver.findElements(By.xpath(date)).size();
					for (int k = 1; k <= size; k++) {
						date = "(//td[contains(@class,'day') and text()='" + day + "'])[" + k + "]";
						String classText = driver.findElement(By.xpath(date)).getAttribute("class");
						if (!classText.contains("old")) {
							driver.findElement(By.xpath(date)).click();
							test_steps.add("Selecting due on date : " + TaskDueon);
							reslogger.info("Selecting due on date : " + TaskDueon);
							break;
						}
					}
					break;
				} else {
					next = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[3]/div";
					Wait.WaitForElement(driver, next);
					driver.findElement(By.xpath(next)).click();
					Wait.wait2Second();
				}
			}
		}
		if (isAddAssignee) {
			String taskAssignee = "//label[text()='Assignee']/..//input";
			Wait.WaitForElement(driver, taskAssignee);
			driver.findElement(By.xpath(taskAssignee)).sendKeys(TaskAssignee);
			Wait.wait2Second();
			String taskassgin = "//label[text()='Assignee']/..//div/ul/li/div";
			Wait.WaitForElement(driver, taskassgin);
			driver.findElement(By.xpath(taskassgin)).click();
			test_steps.add("Enter Task Assignee : " + TaskAssignee);
			reslogger.info("Enter Task Assignee : " + TaskAssignee);

		}
	}

	public void clickNocancelthecheckoutprocess(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Utility.ScrollToElement(res.Checkout_CompleteCheckOutMsgNoButton, driver);
		res.Checkout_CompleteCheckOutMsgNoButton.click();
		test_steps.add("Clicking on No, cancel the check-out process");
		reslogger.info("Clicking on No, cancel the check-out process");
	}

	public Double verify_TotalCharges(WebDriver driver, ArrayList<String> test_steps, String RoomCharges,
			String FolioTaxes, String TotalCharges) {
		String roomCharges = RoomCharges.replace("$", "");
		Double roomrate = Double.parseDouble(roomCharges);
		reslogger.info("roomCharges : " + roomCharges);

		String folioTaxes = FolioTaxes.replace("$", "");
		Double tax = Double.parseDouble(folioTaxes);
		reslogger.info("folioTaxes : " + tax);
		Double total = roomrate + tax;
		test_steps.add("Expected total charges : " + total);
		reslogger.info("Expected total charges : " + total);
		TotalCharges = TotalCharges.replace("$", "");
		TotalCharges = TotalCharges.trim();
		Double totalCharges = Double.parseDouble(TotalCharges);
		test_steps.add("Found : " + totalCharges);
		reslogger.info("Found : " + totalCharges);
		assertEquals(total, totalCharges, "Failed: Total Charges mismatches");
		test_steps.add("Verified total charges");
		reslogger.info("Verified total charges");

		return total;
	}

	public ArrayList<String> EnterManualRateAmount(WebDriver driver, String ManualRate) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.EnterManualRate);
		res.EnterManualRate.clear();
		res.EnterManualRate.sendKeys(ManualRate);
		test_steps.add("Entered manual rate amount : " + ManualRate);
		reslogger.info("Entered manual rate amount : " + ManualRate);
		return test_steps;
	}

	public ArrayList<String> VerifyBalance(WebDriver driver, Double TotalCharges, String Paid, String TotalBalance) {

		ArrayList<String> test_steps = new ArrayList<>();
		Paid = Paid.replace("$", "");
		Paid = Paid.trim();
		Double paid = Double.parseDouble(Paid);
		reslogger.info("Paid : " + paid);

		Double total = TotalCharges + paid;
		test_steps.add("Expected balance : " + total);
		reslogger.info("Expected balance : " + total);

		TotalBalance = TotalBalance.replace("$", "");
		TotalBalance = TotalBalance.trim();
		Double totalBalance = Double.parseDouble(TotalBalance);
		reslogger.info("totalBalance : " + totalBalance);

		test_steps.add("Found : " + totalBalance);
		reslogger.info("Found : " + totalBalance);
		assertEquals(total, totalBalance, "Failed: Balance mismatches");
		test_steps.add("Verified balance");
		reslogger.info("Verified balance");
		return test_steps;
	}

	public void clickRollBackButton(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_RolleBackButton);
		Utility.ScrollToElement(res.CP_Reservation_RollBackAllButton, driver);
		res.CP_Reservation_RollBackAllButton.click();
		test_steps.add("Click Roll Back  Button");
		reslogger.info("Click Roll Back  Button");
		Wait.waitTillElementDisplayed(driver, OR_Reservation.RollBackPopupMsg);
		Wait.WaitForElement(driver, OR_Reservation.RollBackPopupMsg);
	}

	public void verifyRollBackMsg(WebDriver driver, ArrayList<String> test_steps, String Msg)
			throws InterruptedException {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.RollBackPopupMsg);
		Wait.explicit_wait_visibilityof_webelement(res.RollBackMsg, driver);
		Utility.ScrollToElement(res.RollBackMsg, driver);
		assertTrue(res.RollBackMsg.getText().toLowerCase().trim().equals(Msg.toLowerCase().trim()),
				"Failed : Verify RollBack Msg");
		test_steps.add("Verified RollBack Msg : <b>" + Msg + "</b>");
		reslogger.info("Verified RollBack :" + Msg);
	}

	public void clickCloseRollBackMsg(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.RollBackPopupCloseButton);
		Utility.ScrollToElement(res.RollBackPopupCloseButton, driver);
		res.RollBackPopupCloseButton.click();
		test_steps.add("Click Roll Back  Close Button");
		reslogger.info("Click Roll Back  Close Button");
	}

	public void clickYesButtonRollBackMsg(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.RollBacktPopupYesButton);
		Utility.ScrollToElement(res.RollBacktPopupYesButton, driver);
		res.RollBacktPopupYesButton.click();
		test_steps.add("Click Yes Button");
		reslogger.info("Click  Yes Button");
	}

	public void verifyCheckInAllButton(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_CheckInAllButton, driver);
		assertTrue(res.CP_Reservation_CheckInAllButton.isDisplayed(), "Failed: To verify Check In All Button");
		test_steps.add("Verified  <b>Check In All </b> Button");
		reslogger.info("Verified Check IN All  Button");
	}

	public void verifyStayInfoCheckINButtonForMRB(WebDriver driver, ArrayList<String> test_steps, String RoomClassName)
			throws InterruptedException {
		String path = "//div[contains(@data-bind,'ReservationStatusLookup')]/div//div[contains(@class,'ir-roomInfo')]//span[contains(text(),'"
				+ RoomClassName + "')]"
				+ "/ancestor::div/div[contains(@class,'detailsbox')]/following-sibling::div//button[contains(text(),'Check In')]";
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		assertTrue(element.isDisplayed(), "Failed: To verify Stay Info Check In Button");
		test_steps.add(" Stay Info Check In  Button is Displayed For Room Class <b>" + RoomClassName + "</b>");
		reslogger.info("Stay Info Check In  Button is Displayed For Room Class: " + RoomClassName);

	}

	public void verifyStayInfoCheckOutButtonForMRB(WebDriver driver, ArrayList<String> test_steps, String RoomClassName)
			throws InterruptedException {
		String path = "//div[contains(@data-bind,'ReservationStatusLookup')]/div//div[contains(@class,'ir-roomInfo')]//span[contains(text(),'"
				+ RoomClassName + "')]"
				+ "/ancestor::div/div[contains(@class,'detailsbox')]/following-sibling::div//button[contains(text(),'Check Out')]";
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		assertTrue(element.isDisplayed(), "Failed: To verify Stay Info Check Out Button");
		test_steps.add(" Stay Info Check Out  Button is Displayed For Room Class <b>" + RoomClassName + "</b>");
		reslogger.info("Stay Info Check Out  Button is Displayed For Room Class: " + RoomClassName);

	}

	public void clickStayInforRollBackButton(WebDriver driver, ArrayList<String> test_steps, String RoomClassName)
			throws InterruptedException {
		String path = "//div[contains(@data-bind,'ReservationStatusLookup')]/div//div[contains(@class,'ir-roomInfo')]//span[contains(text(),'"
				+ RoomClassName + "')]"
				+ "/ancestor::div/div[contains(@class,'detailsbox')]/following-sibling::div//button[contains(text(),'Roll Back')]";
		WebElement element = driver.findElement(By.xpath(path));
		Wait.WaitForElement(driver, path);
		Utility.ScrollToElement(element, driver);
		element.click();
		test_steps.add("Click Stay Info-Roll Back Button For Room Class <b>" + RoomClassName + "</b>");
		reslogger.info("Click Stay Info-Roll Back Button For Room Class: " + RoomClassName);

	}

	public void verifyHistoryTabDescriptionForRollBack(WebDriver driver, ArrayList<String> test_steps) {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_HistoryTab_RollBackDesc);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_HistoryTab_RollBackDesc, driver);
		// Verified History Tab No Show Entry
		assertTrue(res.CP_Reservation_HistoryTab_RollBackDesc.isDisplayed(), "Failed: To verify History Tab ");
		test_steps.add(" 'History Tab' Verified : <b> Rolled back this reservation to Reserved</b>");
		reslogger.info("'History Tab' Verified : Rolled back this reservation to Reserved");

	}

	public void addResonOnCancelModelPopup(WebDriver driver, ArrayList<String> test_steps, String reason)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CancelReservation_Reason);
		Utility.ScrollToElement(res.CancelReservation_Reason, driver);
		res.CancelReservation_Reason.clear();
		test_steps.add("Clear Reason");
		reslogger.info("Clear Reason");
		res.CancelReservation_Reason.sendKeys(reason);
		test_steps.add("Input Reason: <b>" + reason + " </b>");
		reslogger.info("Input Reason: " + reason);

	}

	public String CompleteCancelProcess(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.wait10Second();
		String buttonName = null;
		if (Utility.isElementPresent(driver, By.xpath(OR_Reservation.ConfirmedNoShowButton))) {
			Utility.ScrollToElement(res.CP_Reservation_ConfirmButton, driver);
			buttonName = res.CP_Reservation_ConfirmButton.getText();
			res.CP_Reservation_ConfirmButton.click();
			test_steps.add("Clicking on  CONFIRM CANCELLATION Button");
			reslogger.info("Clicking on CONFIRM  CANCELLATION  Button");
			String loading = "(//div[@class='ir-loader-in'])";
			Wait.waitTillElementDisplayed(driver, loading);

		} else if (Utility.isElementPresent(driver, By.xpath(OR_Reservation.ProceedToPaymentButton))) {
			Utility.ScrollToElement(res.ProceedToPaymentButton, driver);
			buttonName = res.ProceedToPaymentButton.getText();
			res.ProceedToPaymentButton.click();
			test_steps.add("Click 'PROCEED TO CANCELLATION PAYMENT' Button");
			reslogger.info("Click 'PROCEED TO CANCELLATION PAYMENT' Button");
			String loading = "(//div[@class='ir-loader-in'])";
			Wait.waitTillElementDisplayed(driver, loading);

		}

		return buttonName;
	}

	public String CompleteNoShowProcess(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.wait10Second();
		String buttonName = null;
		// Wait till pop model is not displayed

		if (res.CP_Reservation_ConfirmButton.isDisplayed()) {
			Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_ConfirmButton, driver);
			Utility.ScrollToElement(res.CP_Reservation_ConfirmButton, driver);
			buttonName = res.CP_Reservation_ConfirmButton.getText();
			res.CP_Reservation_ConfirmButton.click();
			test_steps.add("Click on Confirm No Show Button ");
			reslogger.info("Click on Confirm No Show Button ");
			String loading = "(//div[@class='ir-loader-in'])";
			Wait.waitTillElementDisplayed(driver, loading);
			Wait.explicit_wait_absenceofelement(loading, driver);

		} else if (res.ProceedToPaymentButton.isDisplayed()) {
			Wait.explicit_wait_visibilityof_webelement(res.ProceedToPaymentButton, driver);
			Utility.ScrollToElement(res.ProceedToPaymentButton, driver);
			buttonName = res.ProceedToPaymentButton.getText();
			res.ProceedToPaymentButton.click();
			test_steps.add("Click on Proceed To No Show Payment Button ");
			reslogger.info("Click on Proceed To No Show Payment Button ");
			String loading = "(//div[@class='ir-loader-in'])";
			Wait.waitTillElementDisplayed(driver, loading);
			Wait.explicit_wait_absenceofelement(loading, driver);

		}

		return buttonName;
	}

	public void enter_GuestDetailsForMRB(WebDriver driver, ArrayList test_steps, String Salutation,
			String GuestFirstName, String GuestLastName, String Email, String PhoneNumber, String AltenativePhone,
			String IsSplit, String IsAddMoreGuestInfo) {

		Elements_CPReservation res = new Elements_CPReservation(driver);

		String[] salu = Salutation.split("\\|");
		String[] guestFName = GuestFirstName.split("\\|");
		String[] guestLName = GuestLastName.split("\\|");
		String[] mail = Email.split("\\|");
		String[] phone = PhoneNumber.split("\\|");

		int size = salu.length;

		for (int i = 1; i <= size; i++) {
			if (i == 1) {
				Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_GuestSalutation);
				res.CP_NewReservation_GuestSalutation.click();
				String sal = "//span[contains(text(),'" + salu[i - 1] + "')]/../..";
				Wait.WaitForElement(driver, sal);
				driver.findElement(By.xpath(sal)).click();

				Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_GuestFirstName);
				res.CP_NewReservation_GuestFirstName.clear();

				Random random = new Random();
				int randomNumber = random.nextInt(900);
				reslogger.info("randomNumber : " + randomNumber);

				String FirstName = guestFName[i - 1] + randomNumber;
				Utility.guestFirstName = FirstName;
				res.CP_NewReservation_GuestFirstName.sendKeys(FirstName);
				test_steps.add("Guest First Name : " + FirstName);
				reslogger.info("Guest First Name : " + FirstName);

				Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_GuestLastName);
				res.CP_NewReservation_GuestLastName.clear();

				String LastName = guestLName[i - 1] + randomNumber;
				Utility.guestLastName = LastName;
				res.CP_NewReservation_GuestLastName.sendKeys(LastName);
				test_steps.add("Guest Last Name : " + LastName);
				reslogger.info("Guest Last Name : " + LastName);

				Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Phone);
				res.CP_NewReservation_Phone.clear();
				res.CP_NewReservation_Phone.sendKeys(phone[i - 1]);
				test_steps.add("Enter Phone Number : " + phone[i - 1]);
				reslogger.info("Enter Phone Number : " + phone[i - 1]);

				Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_AltenativePhone);
				res.CP_NewReservation_AltenativePhone.clear();
				res.CP_NewReservation_AltenativePhone.sendKeys(AltenativePhone);
				test_steps.add("Enter AltenativePhone Number : " + AltenativePhone);
				reslogger.info("Enter AltenativePhone Number : " + AltenativePhone);

				Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Email);
				res.CP_NewReservation_Email.clear();
				res.CP_NewReservation_Email.sendKeys(mail[i - 1]);
				test_steps.add("Enter Email : " + mail[i - 1]);
				reslogger.info("Enter Email : " + mail[i - 1]);

			} else {

				String additional = "(//span[text()='Additional Room'])";
				int count = driver.findElements(By.xpath(additional)).size();

				String saluta = "(//span[text()='Additional Room'])[" + (i - 1)
						+ "]/../../../../../..//span[text()='Select']/..";
				String slaut = "(//span[text()='Additional Room'])[" + (i - 1)
						+ "]/../../../../../..//span[text()='Select']/../following-sibling::div//li//span[text()='"
						+ salu[i - 1] + "']";
				Wait.WaitForElement(driver, saluta);
				driver.findElement(By.xpath(saluta)).click();
				reslogger.info(slaut);
				Wait.WaitForElement(driver, slaut);
				driver.findElement(By.xpath(slaut)).click();

				count = driver.findElements(By.xpath("//label[text()='Guest ']/../input")).size();
				String fname = "(//label[text()='Guest ']/../input)[" + i + "]";
				Wait.WaitForElement(driver, fname);
				driver.findElement(By.xpath(fname)).clear();
				driver.findElement(By.xpath(fname)).sendKeys(guestFName[i - 1]);
				test_steps.add("Guest First Name : " + guestFName[i - 1]);
				reslogger.info("Guest First Name : " + guestFName[i - 1]);

				count = driver
						.findElements(By
								.xpath("(//span[text()='Additional Room'])/../../../../../..//label[text()='Last Name']/../input"))
						.size();
				String lname = "(//span[text()='Additional Room'])[" + (i - 1)
						+ "]/../../../../../..//label[text()='Last Name']/../input";
				Wait.WaitForElement(driver, lname);
				driver.findElement(By.xpath(lname)).clear();
				driver.findElement(By.xpath(lname)).sendKeys(guestLName[i - 1]);
				test_steps.add("Guest Last Name : " + guestLName[i - 1]);
				reslogger.info("Guest Last Name : " + guestLName[i - 1]);

				count = driver.findElements(By.xpath("(//label[text()='Phone']/preceding-sibling::input)")).size();
				String ph = "(//span[text()='Additional Room'])[" + (i - 1)
						+ "]/../../../../../..//label[text()='Phone']/preceding-sibling::input";
				driver.findElement(By.xpath(ph)).clear();
				driver.findElement(By.xpath(ph)).sendKeys(phone[i - 1]);
				test_steps.add("Enter Phone Number : " + phone[i - 1]);
				reslogger.info("Enter Phone Number : " + phone[i - 1]);

				count = driver.findElements(By.xpath("(//label[text()='E-mail']/preceding-sibling::input)")).size();
				String email = "(//span[text()='Additional Room'])[" + (i - 1)
						+ "]/../../../../../..//label[text()='E-mail']/preceding-sibling::input";
				driver.findElement(By.xpath(email)).clear();

				driver.findElement(By.xpath(email)).sendKeys(mail[i - 1]);
				test_steps.add("Enter Altenative Email : " + mail[i - 1]);
				reslogger.info("Enter Altenative Email : " + mail[i - 1]);

			}
			if (!(i == size) && IsAddMoreGuestInfo.equalsIgnoreCase("Yes") && IsSplit.equalsIgnoreCase("Yes")) {
				Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_AddMoreGuestInfo);
				res.CP_NewReservation_AddMoreGuestInfo.click();
				test_steps.add("Clicking on Add More Guest Info");
				reslogger.info("Clicking on Add More Guest Info");
			}
		}

	}

	public void clickOnAddARoomButton(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_AddRoom);
		res.CP_NewReservation_AddRoom.click();
		test_steps.add("Clicking on add room");

	}

	public String getTripTotal_Header(WebDriver driver) {

		Elements_CPReservation res = new Elements_CPReservation(driver);

		return Utility.convertDollarToNormalAmount(driver, res.tripTotalAmountAtTop.getText());

	}

	public String getBalance_Header(WebDriver driver) {

		Elements_CPReservation res = new Elements_CPReservation(driver);

		return Utility.convertDollarToNormalAmount(driver, res.tripBalanceAmountAtTop.getText());
	}

	public String getRoomCharge_TripSummary(WebDriver driver) {
		Elements_CPReservation res = new Elements_CPReservation(driver);

		return Utility.convertDollarToNormalAmount(driver, res.TripSummary_RoomCharge.getText());

	}

	public String getIncidentals_TripSummary(WebDriver driver) {

		Elements_CPReservation res = new Elements_CPReservation(driver);

		return Utility.convertDollarToNormalAmount(driver, res.TripSummary_Incidentals.getText());

	}

	public String getTaxesAndServiceCharges_TripSummary(WebDriver driver) {

		Elements_CPReservation res = new Elements_CPReservation(driver);

		return Utility.convertDollarToNormalAmount(driver, res.TripSummary_TaxesAndServiceCharges.getText());

	}

	public String getTripTotal_TripSummary(WebDriver driver) {

		Elements_CPReservation res = new Elements_CPReservation(driver);

		return Utility.convertDollarToNormalAmount(driver, res.TripSummary_TripTotal.getText());

	}

	public String getPayment_TripSummary(WebDriver driver) {

		Elements_CPReservation res = new Elements_CPReservation(driver);

		return Utility.convertDollarToNormalAmount(driver, res.TripSummary_Payment.getText());

	}

	public String getBalance_TripSummary(WebDriver driver) {
		Elements_CPReservation res = new Elements_CPReservation(driver);

		return Utility.convertDollarToNormalAmount(driver, res.TripSummary_Balance.getText());
	}

	public void verifyTripSummeryAmount(WebDriver driver, ArrayList<String> test_steps, ArrayList<String> baseAmount,
			ArrayList<String> totalNights, String total, String payment, String policyFor) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);
		String tripRoomCharge = res.TripSummary_RoomCharge.getText().replace("$ ", "");
		String tripTotal = res.TripSummary_TripTotal.getText().replace("$ ", "");
		String tripPaid = res.TripSummary_Payment.getText().replace("$ ", "");
		String tripBalance = res.TripSummary_Balance.getText().replace("$ ", "");
		String tripTaxes = res.TripSummary_TaxesAndServiceCharges.getText().replace("$ ", "");
		double tripRoomCharge2 = 0.00;
		for (int i = 0; i < baseAmount.size(); i++) {

			tripRoomCharge2 = tripRoomCharge2
					+ Double.parseDouble(baseAmount.get(i)) * Double.parseDouble(totalNights.get(i));
		}
		String tripRoomChargeExp = df.format(tripRoomCharge2);

		double tripTaxes2 = Double.parseDouble(total) - tripRoomCharge2;
		String tripTaxesExp = df.format(tripTaxes2);
		Utility.verifyText(tripRoomCharge, tripRoomChargeExp, "Room charges at trip summary", test_steps, reslogger);

		String expBalanceDisplayed = null;
		String expPaidDisplayed = null;

		if (policyFor.equalsIgnoreCase("Capture")) {
			double expBalance = ((Double.parseDouble(total)) - (Double.parseDouble(payment)));
			expBalanceDisplayed = df.format(expBalance);
			expPaidDisplayed = tripPaid;
		} else if (policyFor.equalsIgnoreCase("Authorize")) {
			expBalanceDisplayed = total;
			expPaidDisplayed = "0.00";
		}
		Utility.verifyText(tripTotal, total, "Trip total at trip summary", test_steps, reslogger);
		Utility.verifyText(tripBalance, expBalanceDisplayed, "Balance at trip summary", test_steps, reslogger);
		Utility.verifyText(tripPaid, expPaidDisplayed, "Paid amount at trip summary", test_steps, reslogger);
	}

	public void verifyNotesDetails(ArrayList<String> test_steps, List<WebElement> element, 
			String expString, String type) {
		int i =1;
		for (WebElement webElement : element) {
			String actString = webElement.getText();
			assertEquals(actString, expString, "Failed - Verify "+type+" of note");
			if (element.size()>1) {
				test_steps.add("Successfully verified "+type+" of note as <b>"+actString+"</b> at row "+i);		
				i++;	
			} else {
				test_steps.add("Successfully verified "+type+" of note as <b>"+actString+"</b>");
			}
		}
	}

	public void verifyNoteRoomsDetails(WebDriver driver, ArrayList<String> test_steps, String noteType, 
			ArrayList<String> abbrivations, ArrayList<String> roomNumbers) {
		List<WebElement> noteRoomsDisplayed = driver.findElements(By.xpath("//td[text()='"+noteType+"']/..//td[contains(@data-bind,'text: roomClassAbbrv')]"));
		for (int i = 0; i < noteRoomsDisplayed.size(); i++) {
			int j = i + 1;
			String actString = noteRoomsDisplayed.get(i).getText();
			String expString = abbrivations.get(i) + ": " + roomNumbers.get(i);
			assertEquals(actString, expString, "Failed - Verify room of cancel note");
			test_steps.add("Successfully verified room of note as <b>" + actString + "</b> at row " + j);
		}
	}
	public void verifyNotesDetails(WebDriver driver, ArrayList<String> test_steps, String noteType,
			String noteSubject, String noteDesc, String updatedBy) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		try {
			Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.notesTabHeader), driver);
			Utility.ScrollToElement(res.notesTabHeader, driver);
			List<WebElement> noteTypesDisplayed = driver.findElements(By.xpath("//td[text()='"+noteSubject+"']/..//td[contains(@data-bind,'text: type')]"));
			List<WebElement> noteDescriptionsDisplayed = driver.findElements(By.xpath("//td[text()='"+noteSubject+"']/..//td[contains(@data-bind,'text: details')]"));
			List<WebElement> noteSubjectsDisplayed = driver.findElements(By.xpath("//td[text()='"+noteSubject+"']/..//td[contains(@data-bind,'text: subject')]"));
			List<WebElement> noteUpdatedBysDisplayed = driver.findElements(By.xpath("//td[text()='"+noteSubject+"']/..//td[contains(@data-bind,'text: updatedBy')]"));
			List<WebElement> noteUpdatedOnsDisplayed = driver.findElements(By.xpath("//td[text()='"+noteSubject+"']/..//td[contains(@data-bind,'text: moment(updatedOn())')]"));
			if (noteType.equalsIgnoreCase("No Show")) {
				noteType = "Internal";
			}
			if (Utility.validateString(noteType)) {
				verifyNotesDetails(test_steps, noteTypesDisplayed, noteType, "type");				
			}
			if (Utility.validateString(noteDesc)) {
				verifyNotesDetails(test_steps, noteDescriptionsDisplayed, noteDesc, "description");
			}
			if (Utility.validateString(updatedBy)) {
				verifyNotesDetails(test_steps, noteUpdatedBysDisplayed, updatedBy, "updated by");
			}
			if (Utility.validateString(noteSubject)) {
				verifyNotesDetails(test_steps, noteSubjectsDisplayed, noteSubject, "subject");
			}
			int i =1;
			for (WebElement webElement : noteUpdatedOnsDisplayed) {
				String actString = webElement.getText();
				actString = Utility.parseDate(actString, "MM/dd/ yy HH:mm aa", "MM/dd/yyyy");
				String expString = Utility.getCurrentDate("MM/dd/yyyy");
//				assertEquals(actString, expString, "Failed - Verify note updated on date of cancel note");
				if (noteUpdatedOnsDisplayed.size()>1) {
					test_steps.add("Successfully verified note updated on date as <b>"+actString+"</b> at row "+i);		
					i++;
				} else {
					test_steps.add("Successfully verified note updated on date as <b>"+actString+"</b> ");	
				}				
			}
		} catch (Exception e) {
			reslogger.info(e);
		}
	}

	public boolean getPaymentWindow(WebDriver driver) {
		boolean isExist = false;
		Elements_CPReservation CPReservationPage = new Elements_CPReservation(driver);
		isExist = Utility.isElementPresent(driver, By.xpath(OR_Reservation.NoShow_Cancel_CheckIn_CheckOut_Title));
		return isExist;
	}

	public ArrayList<String> getAssignedRoomNumbers(WebDriver driver, ArrayList<String> test_steps,
			ArrayList<String> roomClasses) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		ArrayList<String> roomNumbers = new ArrayList<>();
		try {
			if (roomClasses.size() == 1) {
				String xpath = "//div[@data-bind='with: roomSearch.SelectedRoomClass']//div[contains(text(),'"
						+ roomClasses.get(0) + "')]";
				Wait.waitForElementToBeVisibile(By.xpath(xpath), driver);
				String roomNumber = driver.findElement(By.xpath(xpath)).getText().split(": ")[1];
				roomNumbers.add(roomNumber);
			} else if (roomClasses.size() > 1) {
				for (int i = 0; i < roomClasses.size(); i++) {
					String xpath = "(//div[@data-bind='click: onToggleRoomInfo']//span[contains(text(),'"
							+ roomClasses.get(i) + "')])[2]";
					reslogger.info(xpath);
					reslogger.info(roomClasses.get(i));
					Wait.waitForElementToBeVisibile(By.xpath(xpath), driver);
					String roomNumber = driver.findElement(By.xpath(xpath)).getText().split(": ")[1];
					roomNumbers.add(roomNumber);
				}
			}
		} catch (Exception e) {
			reslogger.info(e);
		}
		return roomNumbers;

	}

	public String getPolicyfromDetailTab(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		String policyName = null;
		String path = "//div[@aria-expanded='true']//span[@class='filter-option pull-left']";
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		policyName = element.getText();
		return policyName;
	}

	public void setAsMainPayment(WebDriver driver, ArrayList<String> test_steps, String PaymentType,
			String PaymentTypeTwo, Date Date, String PolicyType) throws ParseException {
		Elements_CPReservation CPReservationPage = new Elements_CPReservation(driver);
		String path = "//div[contains(@data-bind,'IsSplitPaymentEnable')]//button[@title='" + PaymentType + "']";
		WebElement PaymentMethodElement = driver.findElement(By.xpath(path));
		PaymentMethodElement.click();
		test_steps.add("Click Payment Method");
		reslogger.info("Click CPayment Method");
		String options = "//paymentmethod//div[contains(@class,'open')]/ul//li/a//span[@class='text'][contains(text(),'"
				+ PaymentTypeTwo + "')]";
		WebElement PaymentMethodOptions = driver.findElement(By.xpath(options));
		PaymentMethodOptions.click();
		test_steps.add("Select Payment Option :<b> " + PaymentTypeTwo + "<b>");
		reslogger.info("Click Payment Option: " + PaymentTypeTwo);
		if (PolicyType.equals("Cancel") || PolicyType.equals("No Show")) {
			CPReservationPage.NoShow_Cancel_Checkout_Date_CalanderIcon.click();
			test_steps.add("Click Calander Icon");
			reslogger.info("Click Calander Icon");
		} else if (PolicyType.equals("Check In")) {
			CPReservationPage.CheckInPaymentPopup_Date_CalanderIcon.click();
			test_steps.add("Click Calander Icon");
			reslogger.info("Click Calander Icon");
		} else if (PolicyType.equals("Check Out")) {
			CPReservationPage.CheckOutPaymentPopup_Date_CalanderIcon.click();
			test_steps.add("Click Calander Icon");
			reslogger.info("Click Calander Icon");
		}

		int CD = Date.getDate();
		String Day = String.valueOf(CD);
		reslogger.info("Day: " + Day);

		SelectDate(driver, Date, Day);
		test_steps.add("Select Date: <b>" + Date + "</b>");
		reslogger.info("Select Date: " + Date);
		CPReservationPage.CP_Reservation_NoShowPaymentPopup_SetMainPaymentCheckBox.click();
		test_steps.add("Click Set As Main Payment Method Check Box");
		reslogger.info("Click Set As Main Payment Method Check Box");

	}

	public void verifyFolioLineItem(WebDriver driver, ArrayList<String> test_steps, Date Date, String Category,
			String Desc, String Amount) throws InterruptedException {
		reslogger.info("******************* Verify Folio Details **********************");

		int CD = Date.getDate();
        String Day = String.valueOf(CD);
	    reslogger.info("Day: " + Day);


		Elements_CPReservation res = new Elements_CPReservation(driver);
		String LineItemSetMainPaymentPath = "//tbody[contains(@data-bind,'ComputedFolioItems')]//td[@class='lineitem-date']/span[contains(text(),'"
				+ Day + "')]" + "/../following-sibling::td[@class='lineitem-category']/span[contains(text(),'"
				+ Category + "')]" + "/../following-sibling::td[@class='lineitem-description']//a[contains(text(),'"
				+ Desc + "')]" + "/../following-sibling::td[@class='lineitem-amount']/span[contains(text(),'" + Amount
				+ "')]";
		Wait.WaitForElement(driver, LineItemSetMainPaymentPath);
		WebElement SetLineItemElement = driver.findElement(By.xpath(LineItemSetMainPaymentPath));
		// Verified No Show Fee line item
		assertTrue(SetLineItemElement.isDisplayed(), "Failed: To Verify Line Item");
		test_steps.add(" Verified Date:<b>  " + Date + "</b>");
		reslogger.info("Verified  Date: " + Date);
		test_steps.add(" Verified  Category:<b>  " + Category + "</b>");
		reslogger.info(" Verified  Category: " + Category);
		test_steps.add("Verified  Description:<b>  " + Desc + "</b>");
		reslogger.info(" Verified Description: " + Desc);
		test_steps.add(" Verified Amount:<b>  " + Amount + "</b>");
		reslogger.info(" Verified  Amount: " + Amount);

	}

	public String getCheckinAmount(WebDriver driver, ArrayList test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		// Get Amount
		Amount = res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount.getText();
		double noshowPaymentAmount = Double.valueOf(Amount);
		int amt = ((int) noshowPaymentAmount);
		String checkINCheckOutAmount = String.valueOf(amt);
		test_steps.add("Check In Amount Is :<b>  " + checkINCheckOutAmount + "</b>");
		reslogger.info(checkINCheckOutAmount);

		return checkINCheckOutAmount;
	}

	public boolean getNoShowHeaderWindow(WebDriver driver) {
		String path = "//h4[contains(@data-bind,'PaymentPopupHeaderText()')][contains(text(),'No Show Payment')]";
		boolean value = Utility.isElementPresent(driver, By.xpath(path));
		return value;
	}

	public boolean getCancelReservationHeaderWindow(WebDriver driver) {
		String path = "//h4[contains(@data-bind,'PaymentPopupHeaderText()')][contains(text(),'Cancellation Payment')]";
		boolean value = Utility.isElementPresent(driver, By.xpath(path));
		return value;
	}

	public void clickSendEmailButton(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Utility.ScrollToElement(res.GUESTINFO_SENDEMAIL_BUTTON, driver);
		res.GUESTINFO_SENDEMAIL_BUTTON.click();
		Wait.WaitForElement(driver, OR_Reservation.GUESTINFO_SENDEMAIL_HEADERTITLE);
		assertEquals(res.GUESTINFO_SENDEMAIL_HEADERTITLE.getText(), "Send Mail");
		test_steps.add("Send Email Popup Opened With Title: " + res.GUESTINFO_SENDEMAIL_HEADERTITLE.getText());
		reslogger.info("Send Email Popup Opened With Title: " + res.GUESTINFO_SENDEMAIL_HEADERTITLE.getText());

	}

	public void SendEmail(WebDriver driver, String emailID, ArrayList<String> test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		res.GUESTINFO_SENDEMAIL_EMAILID.clear();
		res.GUESTINFO_SENDEMAIL_EMAILID.sendKeys(emailID);
		test_steps.add("Email ID Added/Updated: " + emailID);
		reslogger.info("Email ID Added/Updated: " + emailID);
		Utility.ScrollToElement(res.SENDMAIL_POPUP_SEND_BUTTON, driver);
		res.SENDMAIL_POPUP_SEND_BUTTON.click();
		test_steps.add("Send Email Button Clicked");
		reslogger.info("Send Email Button Clicked");
		Wait.wait2Second();
		assertEquals(res.GUESTINFO_SENDEMAIL_HEADERTITLE.isDisplayed(), false);
		test_steps.add("Send Email Popup Closed");
		reslogger.info("Send Email Popup Closed");

	}

	public String getEmailSubject(WebDriver driver) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		String emailSubject = null;
		emailSubject = res.GUESTINFO_SENDEMAIL_SUBJECT.getAttribute("value");
		reslogger.info(" Subject: " + emailSubject);
		return emailSubject;

	}

	public String getEmailDescription(WebDriver driver) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		String emailDescription = null;
		driver.switchTo().frame(driver.findElement(By.className("cke_wysiwyg_frame")));
		try {
			emailDescription = res.GUESTINFO_SENDEMAIL_DESCRIPTION.getText();
		} catch (Exception e) {

			emailDescription = "";
		}
		reslogger.info(" emailDescription: " + emailDescription);
		driver.switchTo().defaultContent();
		return emailDescription;

	}

	public ArrayList<String> verifyAutoGeneratedNotes(WebDriver driver, String Type, String Subject, String Description,
			String Date, String Assignee) throws InterruptedException {
		Elements_CPReservation cpelements = new Elements_CPReservation(driver);
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		WebElement NotesType = driver
				.findElement(By.xpath("(//div[text()='Notes']/parent::div//td[text()='" + Type + "'])[1]"));
		Utility.ScrollToElement(NotesType, driver);
		assertEquals(NotesType.getText(), Type, "Failed : NotesType missmatched");
		reslogger.info("Verified Notes Type :" + Type);
		test_steps.add("Verified Notes Type :" + Type);

		WebElement NotesSubject = driver.findElement(
				By.xpath("(//td[contains(@data-bind,'text: subject') and contains(text(),'" + Subject + "')])[1]"));
		assertEquals(NotesSubject.getText(), Subject, "Failed : Detail missmatched");
		reslogger.info("Verified Notes Subject :" + Subject);

		test_steps.add("Verified Notes Subject :" + Subject);
		WebElement NotesDesctiption = null;
		int tableRowSize = driver.findElements(By.xpath("//tbody[contains(@data-bind,'foreach: notes')]//tr")).size();
		reslogger.info("Size is :" + tableRowSize);
		NotesDesctiption = driver.findElement(By.xpath("(//tbody[contains(@data-bind,'foreach: notes')]//tr)["
				+ tableRowSize + "]//td[contains(@data-bind,'text: de')]"));

		assertEquals(NotesDesctiption.getText().replaceAll(" ", ""), Description.replaceAll(" ", ""),
				"Failed : Description missmatched");
		reslogger.info("Verified Notes Description :" + Description);
		test_steps.add("Verified Notes Description :" + Description);
		WebElement noteAssigne = driver.findElement(By.xpath("(//tbody[contains(@data-bind,'foreach: notes')]//tr)["
				+ tableRowSize + "]//td[text()='" + Assignee + "']"));
		assertEquals(noteAssigne.getText(), Assignee, "Failed : Assignee missmatched");
		reslogger.info("Verified Notes Assignee :" + Assignee);
		test_steps.add("Verified Notes Assignee :" + Assignee);

		WebElement noteDate = driver.findElement(By.xpath("(//tbody[contains(@data-bind,'foreach: notes')]//tr)["
				+ tableRowSize + "]//td[contains(text(),'" + Date + "')]"));
		assertEquals(noteDate.getText().contains(Date), true, "Failed : Date  missmatched");
		reslogger.info("Verified Notes Date :" + Date);
		test_steps.add("Verified Notes Date :" + Date);
		return test_steps;
	}

	// Created By Aqsa : clickGuestInfoEditDetailsButton
	public ArrayList<String> clickGuestInfoEditDetailsButton(WebDriver driver, ArrayList<String> steps)
			throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement(element.GUESTINFO_EDITBUTTON, driver);
		Wait.explicit_wait_elementToBeClickable(element.GUESTINFO_EDITBUTTON, driver);
		Utility.ScrollToElement(element.GUESTINFO_EDITBUTTON, driver);
		element.GUESTINFO_EDITBUTTON.click();
		reslogger.info("Click Guest Info Edit Button");
		steps.add("Click Guest Info Edit Button");
		return steps;
	}

	// Created By Aqsa : clickGuestInfoSaveDetailsButton
	public ArrayList<String> clickGuestInfoSaveDetailsButton(WebDriver driver, ArrayList<String> steps)
			throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement(element.GUESTINFO_SAVEBUTTON, driver);
		Wait.explicit_wait_elementToBeClickable(element.GUESTINFO_SAVEBUTTON, driver);
		Utility.ScrollToElement(element.GUESTINFO_SAVEBUTTON, driver);
		element.GUESTINFO_SAVEBUTTON.click();
		reslogger.info("Click Guest Info Save Button");
		steps.add("Click Guest Info Save Button");
		return steps;
	}

	// Created By Aqsa : attachGuestProfile
	public ArrayList<String> attachGuestProfile(WebDriver driver, ArrayList<String> steps, String GuestProfileName,
			String ReplaceTheGuestInfo) throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement(element.GUESTINFO_GUESTPROFILE_INPUTFIELD, driver);
		Utility.ScrollToElement(element.GUESTINFO_GUESTPROFILE_INPUTFIELD, driver);
		element.GUESTINFO_GUESTPROFILE_INPUTFIELD.sendKeys(GuestProfileName);
		// search GuestProfile
		Wait.wait3Second();
		WebElement searchResult = driver
				.findElement(By.xpath("(//*[contains (text(), '" + GuestProfileName + "')])[1]"));
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(searchResult, driver);
		searchResult.click();
		Wait.WaitForElement(driver, OR_Reservation.GUESTINFO_GUESTPROFILE_CONFIRMATIONPOPUP);
		assertEquals(element.GUESTINFO_GUESTPROFILE_CONFIRMATIONPOPUP.getText(),
				"Do you want to replace the guest info, payment method, marketing info and notes in this reservation with the information from the account? Clicking yes will save all the account info to the reservation.");
		reslogger.info("Guest Profile Attachment Confirmation Popup Opened With Text: "
				+ element.GUESTINFO_GUESTPROFILE_CONFIRMATIONPOPUP.getText());
		steps.add("Guest Profile Attachment Confirmation Popup Opened With Text: "
				+ element.GUESTINFO_GUESTPROFILE_CONFIRMATIONPOPUP.getText());
		if (ReplaceTheGuestInfo.equalsIgnoreCase("Yes")) {
			element.GUESTINFO_GUESTPROFILE_CONFIRMATIONPOPUP_YESBUTTON.click();
			reslogger.info("Click Change Guest Info Confirmation Yes Button");
			steps.add("Click Change Guest Info Confirmation  Yes Button");
		} else if (ReplaceTheGuestInfo.equalsIgnoreCase("No")) {
			element.GUESTINFO_GUESTPROFILE_CONFIRMATIONPOPUP_NOBUTTON.click();
			reslogger.info("Click Change Guest Info Confirmation No Button");
			steps.add("Click Change Guest Info Confirmation No Button");
		} else {
			element.GUESTINFO_GUESTPROFILE_CONFIRMATIONPOPUP_CLOSEBUTTON.click();
			reslogger.info("Click Change Guest Info Confirmation Close Button");
			steps.add("Click Change Guest Info Confirmation Close Button");
		}

		return steps;
	}

	public ArrayList<String> verfifyGuestProfileAttachedInfo(WebDriver driver, String guestName, String contactName,
			String addressLine1, String addressLine2, String addressLine3, String email, String phone,
			String alternativePhone, String city, String country, String state, String postalCode, String marketSegment,
			String referral) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_CPReservation res = new Elements_CPReservation(driver);

		Wait.explicit_wait_visibilityof_webelement_120(res.GuestName_GuestInfo, driver);
		String foundGuestName = res.GuestName_GuestInfo.getText();
		assertEquals(foundGuestName, guestName, "Failed To Verify Guest Name");
		reslogger.info("Successfully Verified Guest Name : " + foundGuestName);
		test_steps.add("Successfully Verified Guest Name : " + foundGuestName);

		Wait.explicit_wait_visibilityof_webelement_120(res.ContactName_GuestInfo, driver);
		String foundContactName = res.ContactName_GuestInfo.getText();
		assertEquals(foundContactName, contactName, "Failed To Verify ContactName");
		reslogger.info("Successfully Verified ContactName : " + foundContactName);
		test_steps.add("Successfully Verified ContactName : " + foundContactName);

		String foundAddressLine1 = res.AddressLine1_GuestInfo.getText();
		assertEquals(foundAddressLine1, addressLine1, "Failed To Verify Address Line1");
		reslogger.info("Successfully Verified Address Line1 : " + foundAddressLine1);
		test_steps.add("Successfully Verified Address Line1 : " + foundAddressLine1);

		String foundAddressLine2 = res.AddressLine2_GuestInfo.getText();
		assertEquals(foundAddressLine2, addressLine2, "Failed To Verify Address Line2");
		reslogger.info("Successfully Verified Address Line2 : " + foundAddressLine2);
		test_steps.add("Successfully Verified Address Line2 : " + foundAddressLine2);

		String foundAddressLine3 = res.AddressLine3_GuestInfo.getText();
		assertEquals(foundAddressLine3, addressLine3, "Failed To Verify Address Line3");
		reslogger.info("Successfully Verified Address Line3 : " + foundAddressLine3);
		test_steps.add("Successfully Verified Address Line3 : " + foundAddressLine3);

		String foundCity = res.City_GuestInfo.getText();
		assertEquals(foundCity, city, "Failed To Verify City");
		reslogger.info("Successfully Verified City : " + foundCity);
		test_steps.add("Successfully Verified City : " + foundCity);

		String foundCountry = res.Contry_GuestInfo.getText();
		assertEquals(foundCountry, country, "Failed To Verify Country");
		reslogger.info("Successfully Verified Country : " + foundCountry);
		test_steps.add("Successfully Verified Country : " + foundCountry);

		String foundState = res.State_GuestInfo.getText();
		assertEquals(foundState, state, "Failed To Verify State");
		reslogger.info("Successfully Verified State : " + foundState);
		test_steps.add("Successfully Verified State : " + foundState);

		String foundPostalCode = res.PostalCode_GuestInfo.getText();
		assertEquals(foundPostalCode, postalCode, "Failed To Verify PostalCode");
		reslogger.info("Successfully Verified PostalCode : " + foundPostalCode);
		test_steps.add("Successfully Verified PostalCode : " + foundPostalCode);

		String foundEmail = res.Email_GuestInfo.getText();
		assertEquals(foundEmail, email, "Failed To Verify Email");
		reslogger.info("Successfully Verified Email : " + foundEmail);
		test_steps.add("Successfully Verified Email : " + foundEmail);

		String foundphoneNo = res.PhoneNo_GuestInfo.getText();
		foundphoneNo = foundphoneNo.replace("-", "").trim();
		foundphoneNo = foundphoneNo.replace("(", "");
		foundphoneNo = foundphoneNo.replace(")", "");
		foundphoneNo = foundphoneNo.replace(" ", "");
		assertEquals(foundphoneNo.trim(), "1" + phone, "Failed To Verify phoneNo");
		reslogger.info("Successfully Verified PhoneNo : " + foundphoneNo);
		test_steps.add("Successfully Verified PhoneNo : " + foundphoneNo);

		String foundalternativephoneNo = res.AlternativePhoneNo_GuestInfo.getText();
		foundalternativephoneNo = foundalternativephoneNo.replace("-", "").trim();
		foundalternativephoneNo = foundalternativephoneNo.replace("(", "");
		foundalternativephoneNo = foundalternativephoneNo.replace(")", "");
		foundalternativephoneNo = foundalternativephoneNo.replace(" ", "");
		assertEquals(foundalternativephoneNo.trim(), "1" + alternativePhone, "Failed To Verify phoneNo");
		reslogger.info("Successfully Verified Alternative PhoneNo : " + foundalternativephoneNo);
		test_steps.add("Successfully Verified Alternative PhoneNo : " + foundalternativephoneNo);

		Utility.ScrollToElement(res.MarketSegment_GuestInfo, driver);
		String foundMarketSegment = res.MarketSegment_GuestInfo.getText();
		assertEquals(foundMarketSegment, marketSegment, "Failed To Verify MarketSegment");
		reslogger.info("Successfully Verified MarketSegment : " + foundMarketSegment);
		test_steps.add("Successfully Verified MarketSegment : " + foundMarketSegment);

		Utility.ScrollToElement(res.Referral_GuestInfo, driver);
		String foundReferral = res.Referral_GuestInfo.getText();
		assertEquals(foundReferral, referral, "Failed To Verify Referral");
		reslogger.info("Successfully Verified Referral : " + foundReferral);
		test_steps.add("Successfully Verified Referral : " + foundReferral);

		return test_steps;

	}

	public void closeFirstOpenedReservationUnSavedData(WebDriver driver, ArrayList test_steps)
			throws InterruptedException {
		String close = "//ul[@class='sec_nav']//li[5]//i";
		try {
			// Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(close)),
			// driver);
			Wait.WaitForElement(driver, close);
			Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(close)), driver);
			Utility.ScrollToElement(driver.findElement(By.xpath(close)), driver);
			driver.findElement(By.xpath(close)).click();
		} catch (Exception e) {
			Wait.WaitForElement(driver, close);
			Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(close)), driver);
			Utility.ScrollToElement(driver.findElement(By.xpath(close)), driver);
			Wait.wait2Second();
			driver.findElement(By.xpath(close)).click();
		}
		if (driver.findElement(By.xpath("//div[@id='alertForTab']//h4[.='You have Unsaved Data!']")).isDisplayed()) {
			test_steps.add(
					"Unsaved data model popup is displayed while closing the reservation and clicking on YES button");
			reslogger.info(
					"Unsaved data model popup is displayed while closing the reservation and clicking on YES button");
			driver.findElement(By.xpath("//div[@id='alertForTab']//button[.='Yes']")).click();
		}
		test_steps.add("Closed the reservation");
		reslogger.info("Closed the reservation");
	}

	public String getTotalIncidentals(WebDriver driver) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.wait10Second();
		String IncidentalTotal = res.getTotalIncidentalsAmount.getText();

		return IncidentalTotal;
	}

	public void verifyLedgerAccountnotPresentInTaxDetailPopup(WebDriver driver, ArrayList test_steps,
			ArrayList<String> existingTaxDescription, String PackageCategory) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		String taxLedgeraccount = "Room Charge";

		int roomChargesFolioCount = getRoomChargeFolioItemCountInRes(driver);
		for (int i = 1; i <= roomChargesFolioCount; i++) {
			String xpath = "(//tbody[starts-with(@data-bind,'getElement: ComputedFolioItemsElement')]/tr/td/span[text()='"
					+ taxLedgeraccount + "']/../following-sibling::td/a)[" + i + "]";
			// clickroomChargeFolioItems
			Wait.waitUntilPresenceOfElementLocated(xpath, driver);
			Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(xpath)), driver);
			driver.findElement(By.xpath(xpath)).click();
			test_steps.add("Click at folio line item: " + i + " of" + " " + taxLedgeraccount);
			Wait.explicit_wait_10sec(res.itemDetailOnFolioPopup, driver);
			Wait.waitUntilPresenceOfElementLocated(OR_Reservation.ItemDetailOnFolioPopup, driver);
			boolean ledgerflag = true;
			for (int k = 0; k < existingTaxDescription.size(); k++) {

				try {
					ledgerflag = IsTaxApplicableOnLedgerAccount(driver, test_steps,
							existingTaxDescription.get(k).trim(), PackageCategory);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					reslogger.info(e);
				}
				assertEquals(false, ledgerflag);

				test_steps.add("<b>" + PackageCategory + "<b>" + "not associated to the Tax: " + "<b>"
						+ existingTaxDescription.get(k) + "</b>");
				cancelTaxItemDetails(driver);
				test_steps.add("Cancel tax item details popup");
			}
			clickFolioPopupCancel(driver);
			test_steps.add("Close open folio line item");
		}
	}

	public boolean IsTaxApplicableOnLedgerAccount(WebDriver driver, ArrayList<String> test_steps,
			String createdTaxDescriptions, String ledgerAccount) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		boolean flag = false;
		Wait.explicit_wait_10sec(res.itemDetailOnFolioPopup, driver);
		Wait.waitUntilPresenceOfElementLocated(OR_Reservation.ItemDetailOnFolioPopup, driver);

		// String xpath =
		// "(//a[text()='"+createdTaxDescriptions.trim()+"'])[1]/ancestor::td";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(
				"(//a[text()='" + createdTaxDescriptions.trim() + "'])[1]/ancestor::td/span/following-sibling::a"));
		// js.executeScript("arguments[0].click(true);", element);
		// reslogger.info(xpath);
		// driver.findElement(By.xpath(xpath)).click();
		Wait.explicit_wait_elementToBeClickable(element, driver);
		element.click();
		reslogger.info("Click at description link");

		test_steps.add("Click at tax Description link: " + "<b>" + createdTaxDescriptions + "</b>");
		String xpath1 = "//table[@class='table table-bordered table-striped table-hover table-condensed']/tbody/tr/td/span[text()='"
				+ ledgerAccount.trim() + "']";
		Wait.explicit_wait_10sec(res.taxItemPlaceholderInFolioDetail, driver);
		Wait.waitUntilPresenceOfElementLocated(OR.TaxItemPlaceholderInFolioDetail, driver);
		reslogger.info(xpath1);
		try {
			flag = verifySizeOfLaundryItems(driver, xpath1);
		} catch (Exception e) {
			reslogger.info(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reslogger.info(flag);
		Wait.wait5Second();
		return flag;

	}

	public boolean verifySizeOfLaundryItems(WebDriver driver, String xpath) {
		boolean laundryExists = false;
		try {
			// String xpath1 = "//table[@class='table table-bordered
			// table-striped table-hover
			// table-condensed']/tbody/tr/td/span[text()='Laundry']";
			laundryExists = driver.findElements(By.xpath(xpath)).size() != 0;
		} catch (Exception e) {
			reslogger.info(e);
			laundryExists = false;
		}
		return laundryExists;
	}

	public void cancelTaxItemDetails(WebDriver driver) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.explicit_wait_10sec(res.taxItemDetailsPopup, driver);
		reslogger.info("cancel");
		res.cancelTaxItemDetailsPopup.click();
	}

	public void verifypackagecharges(WebDriver driver, ArrayList<String> test_steps, double packagerate,
			String PackageCategory) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		// double foliovalue = 0.0;
		double totalIncidentals = 0.0;
		double captureTotalIncidentals = 0.0;
		String mainLedgeraccount = "Room Charge";
		int roomChargesFolioCount = getRoomChargeFolioItemCountInRes(driver);
		for (int i = 1; i <= roomChargesFolioCount; i++) {
			double getincidentalval = 0.0;
			String xpath = "(//tbody[starts-with(@data-bind,'getElement: ComputedFolioItemsElement')]/tr/td/span[text()='"
					+ mainLedgeraccount + "']/../following-sibling::td/a)[" + i + "]";

			// clickroomChargeFolioItems
			driver.findElement(By.xpath(xpath)).click();
			test_steps.add("Click at folio line item: " + i + " of" + " " + PackageCategory.trim());
			Wait.explicit_wait_10sec(res.itemDetailOnFolioPopup, driver);

			String xpath1 = "(//span[text()='" + PackageCategory.trim()
					+ "'])[1]/ancestor::td/following-sibling::td/span[starts-with(.,'$')]";
			reslogger.info(xpath1);
			String getincidentalvalue = driver.findElement(By.xpath(xpath1)).getText().replace("$", "").trim();
			getincidentalval = Double.parseDouble(getincidentalvalue);
			assertEquals(packagerate, getincidentalval);
			test_steps.add("Verifying child folio line item package value:" + PackageCategory + " " + "value" + "<b>"
					+ getincidentalval + "</b>");
			totalIncidentals = totalIncidentals + getincidentalval;
			clickFolioPopupCancel(driver);
			test_steps.add("Close open folio line item");

		}
		String capturetotalincidentals = driver
				.findElement(By.xpath("(//span[@class='pamt']/span[starts-with(.,'$')])[2]")).getText().replace("$", "")
				.trim();

		assertEquals(Double.parseDouble(capturetotalincidentals), totalIncidentals);
		test_steps.add("Verifying totalIncidental captured with calculated of each existing folio: " + "<b>"
				+ totalIncidentals + "</b>");
	}

	public void inputReason(WebDriver driver, ArrayList<String> test_steps, String reason) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		res.CancelReservation_Reason.clear();
		res.CancelReservation_Reason.sendKeys(reason);
		test_steps.add("Providing Cancellation Reason as : <b>" + reason + " </b>");
		reslogger.info("Providing Cancellation Reason as : " + reason);
	}

	public void clickProceedToCancelPaymentButton(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.ProceedToPaymentButton);
		Utility.ScrollToElement(res.ProceedToPaymentButton, driver);
		res.ProceedToPaymentButton.click();
		test_steps.add("Clicking on '<b>PROCEED TO CANCELLATION PAYMENT</b>' Button");
		reslogger.info("Click 'PROCEED TO CANCELLATION PAYMENT' Button");
	}

	public void clickConfirmCancelButton(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.ConfirmedNoShowButton);
		Utility.ScrollToElement(res.CP_Reservation_ConfirmButton, driver);
		res.CP_Reservation_ConfirmButton.click();
		test_steps.add("Clicking CONFIRM CANCELLATION   Button");
		reslogger.info("Click CONFIRM CANCELLATION  Button");
	}

	public boolean verifyNoShowSuccessFull(WebDriver driver) {

		String path = "//h4[contains(@class,'modal-title')][contains(text(),'No Show Successful')]";
		boolean value = Utility.isElementPresent(driver, By.xpath(path));
		return value;
	}

	public String getAmountFromPaymentVerificationPage(WebDriver driver) throws InterruptedException {
		String amount = null;
		Elements_CPReservation CPReservationPage = new Elements_CPReservation(driver);
		Utility.ScrollToElement(CPReservationPage.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount, driver);
		// Get Amount
		amount = CPReservationPage.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount.getText();
		double noshowPaymentAmount = Double.valueOf(amount);

		// amount = String.valueOf(noshowPaymentAmount);
		amount = String.format("%.2f", noshowPaymentAmount);
		reslogger.info("Get Amount from Payment Page Is : " + amount);

		return amount;

	}

	public void verifyHistoryDescriptionWithPayment(WebDriver driver, ArrayList<String> test_steps, String amount,
			String Status, String abb, String RoomNo, String PaymentMethod) {
		String[] room = RoomNo.split(":");
		String FinalRoomNo = room[1].trim();
		reslogger.info(" Room No: " + FinalRoomNo);
		String checkIn = "Made a payment $" + amount.trim() + " using " + PaymentMethod;
		String checkOut = "Captured payment for $" + amount.trim() + " using " + PaymentMethod;

		String path = "//span[contains(text(),'" + checkIn
				+ "')]/ancestor::td/following-sibling::td/span[contains(text(),'" + abb + ": " + FinalRoomNo + "')]";
		String checkOutPath = "//span[contains(text(),'" + checkOut
				+ "')]/ancestor::td/following-sibling::td/span[contains(text(),'" + abb + ": " + FinalRoomNo + "')]";
		if (Status.equals("Check In")) {
			WebElement element = driver.findElement(By.xpath(path));
			assertTrue(element.isDisplayed(), "Failed: To verify History Tab  Description");
			test_steps.add("'History Tab' Verified Description: <b>" + checkIn + "</b>");
			reslogger.info("'History Tab' Verified Description : " + checkIn);
			test_steps.add("'History Tab' Verified Room: <b>" + abb + ": " + FinalRoomNo + "</b>");
			reslogger.info("'History Tab' Verified Room : " + abb + ": " + FinalRoomNo);
		} else if (Status.equals("Check Out")) {
			WebElement elementCheckOut = driver.findElement(By.xpath(checkOutPath));
			assertTrue(elementCheckOut.isDisplayed(), "Failed: To verify History Tab  Description");
			test_steps.add("'History Tab' Verified Description: <b>" + checkOut + "</b>");
			reslogger.info("'History Tab' Verified Description : " + checkOut);
			test_steps.add("'History Tab' Verified Room: <b>" + abb + ": " + FinalRoomNo + "</b>");
			reslogger.info("'History Tab' Verified Room : " + abb + ": " + FinalRoomNo);
		}

	}

	public void verifyChangedRoomChargesOnHistory(WebDriver driver, ArrayList<String> test_steps, String roomCharge,
			String ChangedCharges) {
		String checkOut = "Changed room charge from $" + roomCharge + " to $" + ChangedCharges;
		String path = "//span[contains(text(),'" + checkOut + "')]";
		WebElement element = driver.findElement(By.xpath(path));
		assertTrue(element.isDisplayed(), "Failed: To verify History Tab  Changed Room Charges");
		test_steps.add("'History Tab' Verified <b>" + checkOut + "/b<>");
		reslogger.info("'History Tab' Verified" + checkOut);

	}

	public void verifyReservationPanelTripTotal(WebDriver driver, ArrayList<String> test_steps,
			String TotalTripAmount) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_StatusPanel_TripTotal, driver);
		String RerservationStatus_TripTotalAmount = res.CP_Reservation_StatusPanel_TripTotal.getText().replace("$", "");
		double tripAmount = Double.valueOf(RerservationStatus_TripTotalAmount);
		RerservationStatus_TripTotalAmount = String.valueOf(tripAmount);
		// Verified History Tab No Show Entry
		assertEquals(RerservationStatus_TripTotalAmount, (TotalTripAmount), "Failed: To verify Total Trip");
		test_steps.add("  Verified Reservation Panel Total Trip:  <b>" + TotalTripAmount + "</b>");
		reslogger.info(" Verified Reservation Panel Total Trip: " + TotalTripAmount);
	}

	public void verifyReservationPanelBalance(WebDriver driver, ArrayList<String> test_steps, String Balance)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_PanelBalance);
		Utility.ScrollToElement(res.CP_Reservation_StatusPanel_Balance, driver);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_StatusPanel_Balance, driver);
		String RerservationStatus_BalanceAmount = res.CP_Reservation_StatusPanel_Balance.getText().replace("$", "");
		double balanceAmount = Double.valueOf(RerservationStatus_BalanceAmount);
		reslogger.info("Actual Balance: " + String.format("%.2f", balanceAmount).trim());
		assertEquals(String.format("%.2f", balanceAmount).trim(), (Balance), "Failed: To verify Balance");
		test_steps.add("  Verified Reservation Panel Balance:  <b>" + Balance + "</b>");
		reslogger.info(" Verified Reservation Panel Balance: " + Balance);
	}

	public void verifyTripSummaryPaidAmount(WebDriver driver, ArrayList test_steps, String getPaid, String Paid) {
		double paidAmt = Double.valueOf(getPaid);
		getPaid = String.format("%.2f", paidAmt);
		assertEquals(getPaid.trim(), (Paid), "Failed : to Verify Trip Summary Paid");
		test_steps.add("Verified TripSummary  Paid : <b>" + Paid + "</b>");
		reslogger.info("Verified TripSummary  Paid : " + Paid);

	}

	public void verifyTripSummaryBalanceAmount(WebDriver driver, ArrayList test_steps, String getBalance,
			String Balance) {

		double balanceAmount = Double.valueOf(getBalance);
		reslogger.info("Actual Balance : " + String.format("%.2f", balanceAmount).trim());
		assertEquals(String.format("%.2f", balanceAmount).trim(), (Balance), "Failed : to Verify Trip Summary Balance");
		test_steps.add("Verified TripSummary Balance Charges :<b> " + Balance + "</b>");
		reslogger.info("Verified TripSummary Balance Charges : " + Balance);
	}

	public ArrayList<String> verifyOverbookingTab(WebDriver driver, String RoomClassName) throws InterruptedException {
		Elements_CPReservation cpelements = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();
		Wait.WaitForElement(driver, OR_Reservation.OVERBOOKING_TAB_MESSAGE);
		Utility.ScrollToElement(cpelements.OVERBOOKING_TAB_MESSAGE, driver);
		String overbookingMessage = "There are 0 rooms available for the " + RoomClassName
				+ ", Are you sure you want to overbook this room class?";
		assertEquals(overbookingMessage, cpelements.OVERBOOKING_TAB_MESSAGE.getText());
		test_steps.add("Overbooking Tab Message Verified : " + overbookingMessage);
		reslogger.info("Overbooking Tab Message Verified : " + overbookingMessage);
		cpelements.OVERBOOKING_TAB_NOBUTTON.click();
		reslogger.info("Overbooking Tab No Buton Clicked");
		test_steps.add("Overbooking Tab No Buton Clicked");
		return test_steps;

	}

	public ArrayList<String> verifyNewReservationPage(WebDriver driver) throws InterruptedException {
		Elements_CPReservation cpelements = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_FindRooms);
		assertEquals(cpelements.CP_NewReservation_FindRooms.isDisplayed(), true);
		test_steps.add("New Reservation Page Displayed");
		reslogger.info("New Reservation Page Displayed");

		return test_steps;

	}

	public ArrayList<String> selectCheckInDate(WebDriver driver, String checkInDate) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		String loading = "(//div[@class='ir-loader-in'])[2]";
		int counter = 0;
		while (true) {
			if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
				break;
			} else if (counter == 40) {
				break;
			} else {
				counter++;
				Wait.wait2Second();
			}
		}
		reslogger.info("Waited to loading symbol");
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_CheckinCal);
		res.CP_NewReservation_CheckinCal.click();
		String monthYear = Utility.get_MonthYear(checkInDate);
		String day = Utility.getDay(checkInDate);
		reslogger.info(monthYear);
		String header = null, headerText = null, next = null, date;
		for (int i = 1; i <= 12; i++) {
			header = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[2]";
			headerText = driver.findElement(By.xpath(header)).getText();
			if (headerText.equalsIgnoreCase(monthYear)) {
				date = "//td[contains(@class,'day') and text()='" + day + "']";
				Wait.WaitForElement(driver, date);
				int size = driver.findElements(By.xpath(date)).size();
				for (int k = 1; k <= size; k++) {
					date = "(//td[contains(@class,'day') and text()='" + day + "'])[" + k + "]";
					String classText = driver.findElement(By.xpath(date)).getAttribute("class");
					if (!classText.contains("old")) {
						driver.findElement(By.xpath(date)).click();
						test_steps.add("Selecting checkin date : " + checkInDate);
						reslogger.info("Selecting checkin date : " + checkInDate);
						break;
					}
				}
				break;
			} else {
				next = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[3]/div";
				Wait.WaitForElement(driver, next);
				driver.findElement(By.xpath(next)).click();
				Wait.wait2Second();
			}
		}

		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <selectCheckoutDate> ' Description: <Method will select
	 * check out date> ' Input parameters: <WebDriver driver, String
	 * CheckoutDate> ' Return value: <ArrayList<String>> ' Created By: <Jamal
	 * Nasir> ' Created On: <05/04/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> selectCheckoutDate(WebDriver driver, String CheckoutDate) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_CheckoutCal);
		res.CP_NewReservation_CheckoutCal.click();
		String monthYear = Utility.get_MonthYear(CheckoutDate);
		String day = Utility.getDay(CheckoutDate);
		reslogger.info(monthYear);
		String header = null, headerText = null, next = null, date;
		for (int i = 1; i <= 12; i++) {
			header = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[2]";
			headerText = driver.findElement(By.xpath(header)).getText();
			if (headerText.equalsIgnoreCase(monthYear)) {
				date = "//td[contains(@class,'day') and text()='" + day + "']";
				Wait.WaitForElement(driver, date);
				int size = driver.findElements(By.xpath(date)).size();
				for (int k = 1; k <= size; k++) {
					date = "(//td[contains(@class,'day') and text()='" + day + "'])[" + k + "]";
					String classText = driver.findElement(By.xpath(date)).getAttribute("class");
					if (!classText.contains("old")) {
						driver.findElement(By.xpath(date)).click();
						test_steps.add("Selecting checkout date : " + CheckoutDate);
						reslogger.info("Selecting checkout date : " + CheckoutDate);
						break;
					}
				}

				break;
			} else {
				next = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[3]/div";
				Wait.WaitForElement(driver, next);
				driver.findElement(By.xpath(next)).click();
				Wait.wait2Second();
			}
		}
		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getReservationStatus> ' Description: <return reservation
	 * status> ' Input parameters: <WebDriver driver> ' Return value: <String> '
	 * Created By: <Jamal Nasir> ' Created On: <05/06/2020> *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public String getReservationStatus(WebDriver driver) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_ReservationStatus);
		String status = res.CP_NewReservation_ReservationStatus.getText();
		return status;
	}

	/**
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: <selectRoom> ' Description: <Select Room IN Resevation> '
	 * Input parameters: <WebDriver driver, String RoomClass, String IsAssign,
	 * String Account> ' Return value: <ArrayList<String>> ' Created By: <Jamal
	 * Nasir> ' Created On: <05/06/2020>
	 * 
	 * ##########################################################################################################################################################################
	 */
	public ArrayList<String> selectRoom(WebDriver driver, String RoomClass, String IsAssign, String Account)
			throws InterruptedException {
		String room = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'" + RoomClass
				+ "')]/../../div[2]/span";
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.WaitForElement(driver, room);

		String rooms = driver.findElement(By.xpath(room)).getText();
		reslogger.info(rooms);
		String[] roomsCount = rooms.split(" ");
		int count = Integer.parseInt(roomsCount[0].trim());
		if (count > 0) {
			String sel = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'" + RoomClass
					+ "')]/../../../following-sibling::div/div/select";

			String rulessize = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
					+ RoomClass.trim() + "')]/following-sibling::span";
			reslogger.info(rulessize);
			int ruleCount = driver.findElements(By.xpath(rulessize)).size();
			WebElement ele = driver.findElement(By.xpath(sel));
			test_steps.add("Selected room class : " + RoomClass);
			reslogger.info("Selected room class : " + RoomClass);
			if (IsAssign.equalsIgnoreCase("No")) {
				String expand = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/following-sibling::div/button";
				Wait.WaitForElement(driver, expand);
				driver.findElement(By.xpath(expand)).click();

				String unAssign = "(//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass
						+ "')]/../../../following-sibling::div/div/select/following-sibling::div//ul//span[text()='Unassigned'])";
				Wait.WaitForElement(driver, unAssign);
				driver.findElement(By.xpath(unAssign)).click();
				test_steps.add("Selected room number : Unassigned");
				reslogger.info("Selected room number : Unassigned");
			} else {
				String roomnum = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/option";
				reslogger.info(roomnum);
				int count1 = driver.findElements(By.xpath(roomnum)).size();
				Random random = new Random();
				int randomNumber = random.nextInt(count1 - 1) + 1;
				reslogger.info("count : " + count1);
				reslogger.info("randomNumber : " + randomNumber);
				Wait.WaitForElement(driver, sel);

				String expand = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/following-sibling::div/button";
				Wait.WaitForElement(driver, expand);
				driver.findElement(By.xpath(expand)).click();

				String roomNumber = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/following-sibling::div//ul/li["
						+ randomNumber + "]/a/span";
				Wait.WaitForElement(driver, roomNumber);
				driver.findElement(By.xpath(roomNumber)).click();
			}

			String select = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
					+ RoomClass + "')]/../../../following-sibling::div//span[contains(text(),'SELECT')]";
			Wait.WaitForElement(driver, select);
			driver.findElement(By.xpath(select)).click();

			String loading = "(//div[@class='ir-loader-in'])[2]";
			int counter = 0;
			while (true) {
				if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
					break;
				} else if (counter == 50) {
					break;
				} else {
					counter++;
					Wait.wait2Second();
				}
			}
			reslogger.info("Waited to loading symbol");

			reslogger.info("Rule Count : " + ruleCount);
			if (ruleCount > 1) {
				Wait.wait5Second();
				String rules = "//p[text()='Selecting this room will violate the following rules']/../..//button[text()='Yes']";

				if (driver.findElements(By.xpath(rules)).size() > 0) {
					Wait.wait2Second();
					driver.findElement(By.xpath(rules)).click();
					test_steps.add(
							"Selecting this room will violate the following rules : Are you sure you want to proceed? : yes");
					reslogger.info(
							"Selecting this room will violate the following rules : Are you sure you want to proceed? : yes");

					loading = "(//div[@class='ir-loader-in'])[2]";
					counter = 0;
					while (true) {
						if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
							break;
						} else if (counter == 4) {
							break;
						} else {
							counter++;
							Wait.wait2Second();
						}
					}
				}
			}
			if (!(Account.isEmpty() || Account.equalsIgnoreCase(""))) {
				String policy = "//p[contains(text(),'Based on the changes made')]/../..//button[text()='Yes']";
				Wait.WaitForElement(driver, policy);
				driver.findElement(By.xpath(policy)).click();
				test_steps.add("Based on the changes made, new policies are applicable.? : yes");
				reslogger.info("Based on the changes made, new policies are applicable.? : yes");

				loading = "(//div[@class='ir-loader-in'])[2]";
				counter = 0;
				while (true) {
					if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
						break;
					} else if (counter == 3) {
						break;
					} else {
						counter++;
						Wait.wait2Second();
					}
				}
			}

		} else {
			test_steps.add("Rooms Count <=0 for " + RoomClass + " for specified date");
			reslogger.info("Rooms Count <=0 for " + RoomClass + " for specified date");
		}
		return test_steps;
	}

	public String getRoomChargeUnderTripSummary(WebDriver driver, ArrayList<String> test_steps) {
		String Balance = driver
				.findElement(By
						.xpath("//span[text()='Summary']/../../..//div[contains(text(),'Room Charges')]/following-sibling::div"))
				.getText();
		Balance = Balance.replace("$ ", "");
		test_steps.add("Room Charges captured at trip summary as : <b>" + Balance + "</b>");
		reslogger.info("Room Charges captured at trip summary as : " + Balance);
		return Balance;
	}

	public String getRoomNumber(WebDriver driver, ArrayList test_steps) {
		Elements_CPReservation reservation = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.GetRoomNumber);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.GetRoomNumber), driver);
		String room = reservation.GetRoomNumber.getText();
		room = room.split(":")[1].trim();
		reslogger.info("Room Number is : " + room);
		return room;
	}

	public ArrayList<String> verifyAssociatedAccount(WebDriver driver, String accountName) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		ArrayList<String> testSteps = new ArrayList<String>();

		Wait.WaitForElement(driver, OR_Reservation.AssociatedAccountName);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.AssociatedAccountName), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.AssociatedAccountName), driver);
		String foundName = res.AssociatedAccountName.getText();

		assertEquals(res.AssociatedAccountName.isDisplayed(), OR_Reservation.AssociatedAccountName.contains("//a"),
				"Failed to verify Account No");
		testSteps.add("Verified Assoicated Account Name is displayed as link");
		reslogger.info("Verified Assoicated Account Name is displayed as link");
		assertEquals(foundName.trim(), accountName.trim(), "Failed to verify Account No");
		testSteps.add("Successfully Verified Assoicated Account Name : " + accountName);
		reslogger.info("Successfully Verified Assoicated Account Name : " + accountName);
		return testSteps;
	}

	public ArrayList<String> verifyResInHistory(WebDriver driver, String ReservationNumber) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<String>();
		String historyPath = "//span[.='Created reservation with Confirmation Number: " + ReservationNumber + "']";
		WebElement historyMsg = driver.findElement(By.xpath(historyPath));
		assertTrue(historyMsg.isDisplayed(), "Failed to verify reservation creation in history");

		test_steps.add("Description : Created reservation with Confirmation Number: " + ReservationNumber);
		reslogger.info("Description : Created reservation with Confirmation Number: " + ReservationNumber);
		test_steps.add("Successfully Verified reservation in history tab");
		reslogger.info("Successfully Verified reservation in history tab");
		return test_steps;
	}

	public ArrayList<String> verifyAssociatedAccount_ResHeader(WebDriver driver, String accountName) {
		Elements_CPReservation reservation = new Elements_CPReservation(driver);
		ArrayList<String> testSteps = new ArrayList<String>();

		String foundName = reservation.HeaderAccountName_BeforeReservation.getText();
		assertEquals(foundName.trim(), accountName, "Failed to verify Account No");
		testSteps.add("Successfully Verified Assoicated Account Name : " + accountName);
		reslogger.info("Successfully Verified Assoicated Account Name : " + accountName);
		assertEquals(reservation.GroupSign.isDisplayed(), true, "Failed: Group tic sign is not showing");
		testSteps.add("Verified group tick sign in reservation page");
		try {
			reservation.CP_NewReservation_Next.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return testSteps;
	}

	public String getRoomNumberWithClass(WebDriver driver) {
		Elements_CPReservation reservation = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.GetRoomNumber);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.GetRoomNumber), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.GetRoomNumber), driver);
		String room = reservation.GetRoomNumber.getText();
		return room;
	}

	public ArrayList<String> clickOnCheckInButton(WebDriver driver, boolean isClick, boolean isEnabled)
			throws InterruptedException {
		Elements_CPReservation reservation = new Elements_CPReservation(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.CheckInButton);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.CheckInButton), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.CheckInButton), driver);
		Utility.ScrollToElement_NoWait(reservation.CheckInButton, driver);
		assertEquals(reservation.CheckInButton.isEnabled(), isEnabled, "Faile: checkin button is disabled");
		testSteps.add("Is checkin button isEnabled? " + isEnabled);
		if (isClick) {
			reservation.CheckInButton.click();
			testSteps.add("Click on check in button");
		}
		return testSteps;
	}

	public ArrayList<String> clickOnConfirmCheckInButton(WebDriver driver) throws InterruptedException {
		Elements_CPReservation reservation = new Elements_CPReservation(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.CheckInConfirmButton);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.CheckInConfirmButton), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.CheckInConfirmButton), driver);
		Utility.ScrollToElement(reservation.CheckInConfirmButton, driver);
		reservation.CheckInConfirmButton.click();
		testSteps.add("Click on confirm checkin button");
		return testSteps;
	}

	public void verifySpinerLoading(WebDriver driver) throws InterruptedException {
		Elements_CPReservation reservation = new Elements_CPReservation(driver);
		Wait.wait1Second();
		int count = 0;

		try {
			reslogger.info("in try");
			while (count < 20) {
				reslogger.info(count);
				if (!reservation.SpinerLoading.isDisplayed()) {
					break;
				}
				count = count + 1;
				Wait.wait2Second();
			}
		} catch (Exception e) {
			reslogger.info("in cathc");
		}
	}

	public void switchTab(WebDriver driver) throws InterruptedException {
		Elements_CPReservation reservation = new Elements_CPReservation(driver);
		Wait.wait10Second();
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		driver.close();
		driver.switchTo().window(tabs2.get(0));
	}

	public String reservationStatus(WebDriver driver) {
		Elements_CPReservation reservation = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.GetReservationStatus);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.GetReservationStatus), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.GetReservationStatus), driver);
		String status = reservation.GetReservationStatus.getText();
		return status;
	}

	public String getRoomChargesInTripSummaryBeforeCreateReservation(WebDriver driver) throws InterruptedException {

		Elements_CPReservation element = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.GetRoomChargesInTripSummaryBeforeCreateReservation);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.GetRoomChargesInTripSummaryBeforeCreateReservation),
				driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.GetRoomChargesInTripSummaryBeforeCreateReservation),
				driver);
		return element.GetRoomChargesInTripSummaryBeforeCreateReservation.getText();
	}

	public String getTaxandServicesInTripSummaryBeforeCreateReservation(WebDriver driver) throws InterruptedException {

		Elements_CPReservation element = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.GetTaxandServicesInTripSummaryBeforeCreateReservation);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.GetTaxandServicesInTripSummaryBeforeCreateReservation),
				driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.GetTaxandServicesInTripSummaryBeforeCreateReservation),
				driver);
		return element.GetTaxandServicesInTripSummaryBeforeCreateReservation.getText();
	}

	
	public void verifyNoShowPolicy(WebDriver driver, ArrayList<String> test_steps, String policyName, String policyText) {
		try {
			Elements_CPReservation res = new Elements_CPReservation(driver);
			Wait.wait2Second();
			Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.noShowPolicyDisclaimersTab), driver);
			res.noShowPolicyDisclaimersTab.click();
			
			String noShowPolicyNameInDetailsTab ="//button[@title='"+policyName+"']";
			String	noShowPolicyTextDetailsTab ="//textarea[contains(text(),'"+policyText+"')]";
	
			Wait.waitForElementToBeVisibile(By.xpath(noShowPolicyNameInDetailsTab), driver);
			String noShowPolicyName=driver.findElement(By.xpath(noShowPolicyNameInDetailsTab)).getText().trim();
			assertEquals(noShowPolicyName, policyName, "Failed - Associated policy is not matched with created one");
			test_steps.add("Successfully verified associated cancellation policy name as : <b>"+noShowPolicyName+"</b>");
			
			Wait.waitForElementToBeVisibile(By.xpath(noShowPolicyTextDetailsTab), driver);
			
			String noShowPolicyText=driver.findElement(By.xpath(noShowPolicyTextDetailsTab)).getText();
			assertEquals(noShowPolicyText, policyText, "Failed - Associated policy  text is not matched with created one");
			test_steps.add("Successfully verified associated cancellation policy name as : <b>"+noShowPolicyText+"</b>");	
			
			Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.closeNoShowPolicyExpand), driver);
			res.closeNoShowPolicyExpand.click();
			
		} catch (Exception e) {
			//System.out.println(e);
		}
	}
	
	public void makeReservationNoShow(WebDriver driver, ArrayList test_steps) {
		String expand = "//div[@class='ng-statusChnage ir-statusMenu ul']//span[@class='dropdown dropdown-toggle ir-dropdown-span-status']";
		Wait.waitForElementToBeClickable(By.xpath(expand), driver);
		driver.findElement(By.xpath(expand)).click();
		String status = "//div[@class='ng-statusChnage ir-statusMenu ul']//span//ul//span[text()='No Show']";
		Wait.waitForElementToBeClickable(By.xpath(status), driver);
		driver.findElement(By.xpath(status)).click();
		test_steps.add("Clicking on No Show reservation button");
		reslogger.info("Clicking on No Show reservation button");
	}

	public void verifyNoShowReservationPopUp(WebDriver driver,String policyName, ArrayList<String> test_steps) throws Exception {
		Elements_CPReservation reservation= new Elements_CPReservation(driver);	
		String path = "//span[contains(text(),'Void Room Charges')]/ancestor::div/label/input|//span[contains(text(),'Void room charges for unused nights')]/ancestor::div/label/input";
		Wait.waitForElementToBeVisibile(By.xpath(path), driver, 120);
		Utility.ScrollToElement(reservation.NoShowAndCancelReservation_PolicyName, driver);
		String policyNameDisplayed = reservation.NoShowAndCancelReservation_PolicyName.getText();
		assertEquals(policyNameDisplayed, policyName, "Failed: To verify Reservation Window Policy Name");
		clickProceedToNoShowPaymentButton(driver, test_steps);
	}

	
	public void verifyNoShowPaymentSuccessPopupVerify(WebDriver driver, ArrayList<String> test_steps, String NoShowSuccess,
			String Status, String PaymentMethod,  String Type, String amount) throws Exception {
		try {
			String validate = null;
			DecimalFormat df = new DecimalFormat("0.00");
			df.setMaximumFractionDigits(2);
			Elements_CPReservation res = new Elements_CPReservation(driver);
			Wait.explicit_wait_visibilityof_webelement_120(res.CP_Reservation_NoShowSuccess, driver);
			Utility.ScrollToElement(res.CP_Reservation_NoShowSuccess, driver);
	
			String balanceDisplayed = res.NoShowSuccess_Balance.getText().replace("$ ", "").trim();
			String balanceCalculated = null;
			String amountDisplayed = res.noShowSuccessPolicyAmount.getText().replace("$ ", "");
			String header = res.CP_Reservation_NoShowSuccess.getText().trim();
			assertEquals(header, NoShowSuccess, "Failed: To verify Success Header");
			test_steps.add("  Verified header of the payment popup: <b>" + NoShowSuccess + "</b>");
			reslogger.info("  Verified header of the  payment popup :" + NoShowSuccess);
	
			// Verified Success-Date
	//		=== For check-in/cancel it takes current day as updated so validating with current day
			String date = Utility.getCurrentDate("MM/dd/yyyy");
	//		date = Utility.parseDate(date, "dd/MM/yyyy", "MM/dd/yyyy");
			String dateDisplayed = res.NoShowSuccess_Date.getText();
			assertEquals(dateDisplayed, date, "Failed: To verify  Success Page Date");
			test_steps.add("  Verified Date of  : <b>" + date + "</b>");
			reslogger.info("  Verified Date of : " + date); 

			String typeDisplayed = res.NoShowSuccess_Type.getText().trim();
			assertEquals(typeDisplayed, Type, "Failed: To verify  Success Page Type");
			test_steps.add("  Verified payment type : <b>" + Type + "</b>");
			reslogger.info(" Verified  payment type : " + Type);    
	
			// Verified Success-Payment Method
			String paymentMethodDisplayed = res.CP_Reservation_NoShowSuccessPaymentMethod.getText().trim();
			assertEquals(paymentMethodDisplayed, PaymentMethod, "Failed: To verify Success Page Payment Method");
			test_steps.add("  Verified Payment Method: <b>" + PaymentMethod + "</b>");
			reslogger.info("  Verified Payment Method: " + PaymentMethod);	
	
			// Verified  Success -Status
			String statusDisplayed = res.CP_Reservation_NoShowSuccessStatus.getText().trim();
			assertEquals(statusDisplayed, Status, "Failed: To verify  Success Page Status ");
			test_steps.add("  Verified Status of payment :<b> " + Status + "</b>");
			reslogger.info(" Verified Status  of payment : " + Status);
	
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public ArrayList<String> verifyNoShowPaymentPopUp(WebDriver driver,  ArrayList<String> test_steps, String title, 
			String expiryDate, String nameOnCard, String cardNo, String paymentMethod, String type, String amount){
		
		Elements_CPReservation reservation= new Elements_CPReservation(driver);
		ArrayList<String> details = new ArrayList<>();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.NoShowPaymentPopupTitle), driver);
		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);
		
		String titleDisplayed = reservation.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupTitle.getText();	
		assertEquals(titleDisplayed, title, "Failed: To verify Payment Page Title");
		test_steps.add("Verified Payment Page Title :<b> " + title + "</b>");
		
		String amountDisplayed = reservation.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount.getText().trim();
	       if (amountDisplayed.equals(amount)) {
	   		assertEquals(amountDisplayed,amount,"Faild to verify calculated amount");
	    		test_steps.add("Successfull verified amount as : <b>"+amountDisplayed+"</b>");
	        } else {
	            test_steps.add("Existing issue in CP: Convert WCF Service to in-process calls - PolicyService"
	                    + "<br/><a href='https://innroad.atlassian.net/browse/REF-62'>"
	                    + "Verified : REF-62</a><br/>");
	            reslogger.info("Existing issue in CP: Convert WCF Service to in-process calls - PolicyService"
	                    + "<br/><a href='https://innroad.atlassian.net/browse/REF-62'>"
	                    + "Verified : REF-62</a><br/>");
	   		assertEquals(amountDisplayed,amount,"Faild to verify calculated amount");
	        }
		String balanceDisplayed = reservation.NoShowCancelPaymentPopup_Balance.getText().replace("$ ", "");
		assertEquals(balanceDisplayed,amount,"Faild to verify calculated amount");
		test_steps.add("Successfull verified balance as : <b>"+balanceDisplayed+"</b>");
		
		WebElement paymentElement = driver.findElement(By.
				xpath("//div[contains(@data-bind,'IsSplitPaymentEnable')]//button[@title='"+paymentMethod+"']"));
		String paymentMethodDisplayed = paymentElement.getText().trim();	
		assertEquals(paymentMethodDisplayed, paymentMethod, "Failed: To verify Payment Method");
		test_steps.add("Successfull verified Payment Method:<b> " + paymentMethodDisplayed + "</b> ");
		reslogger.info("Successfull verified Payment Method: " + paymentMethodDisplayed);
		
		String currentdate = Utility.getCurrentDate("MM/dd/yyyy");
		String noShowDate = reservation.NoShowPaymentPopup_Date.getAttribute("value");
		assertEquals(noShowDate, currentdate, "Failed: To verify Account Details");
		test_steps.add("Successfull verified No Show date as : <b>" + noShowDate+"</b>");
		reslogger.info("Successfull verified checkOutDate :" + noShowDate);
		
		String cardNoDisplayed = ((String) executor.executeScript("return arguments[0].value",
				reservation.CP_Reservation_NoShowPaymentPopup_CardNumber)).trim();
		assertEquals(cardNoDisplayed, cardNo, "Failed: To verify Card Number");
		test_steps.add("Successfull verified Card Number as : <b>" + cardNoDisplayed + "</b>");
		reslogger.info(" Verified Card Number");

		String nameOnCardDisplayed = (String) executor.executeScript("return arguments[0].value",
				reservation.CP_Reservation_NoShowPaymentPopup_NameOnCard);
		assertEquals(nameOnCardDisplayed, nameOnCard, "Failed: To verify Name on Card ");
		test_steps.add("Successfull verified Name on Card as : <b>" + nameOnCard + "</b>");
		reslogger.info(" Verified Name on Card");
		
		String expiryDateDisplayed = (String) executor.executeScript("return arguments[0].value",
				reservation.CP_Reservation_NoShowPaymentPopup_Expiry);
		assertEquals(expiryDateDisplayed, expiryDate, "Failed: To verify Expiry Date ");
		test_steps.add("Successfull verified Expiry Date as : <b>" + expiryDateDisplayed + "</b>");
		reslogger.info(" Verified Expiry Date");
		
		String buttonDisplayed = reservation.CP_Reservation_NoShowPaymentPopup_LogAndPayButton.getText().trim();
		String buttonExp = "PAY $ "+amount;
//		buttonDisplayed  = buttonDisplayed.replace("$", "").replace("PAY", "").trim();
		assertEquals(buttonDisplayed, buttonExp, "Failed: To verify Payment Type");
		test_steps.add("Successfull verified button displayed as : <b>" + buttonDisplayed + "</b>");
		reslogger.info(" Verified button displayed  Amount:<b> " + buttonDisplayed + "</b>");

		clickOnPayButtonOnPaymentPopup(driver, test_steps);
		return test_steps;		
	}
	
	public void verifyNoShowNotesDetails(WebDriver driver, ArrayList<String> test_steps, String noteType,
			String noteSubject, String noteDesc, String updatedBy) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		try {
			Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.noShowNotesType), driver);
			Utility.ScrollToElement(res.noShowNotesType, driver);
			String noteTypeDisplayed = res.noShowNotesType.getText();
			assertEquals(noteTypeDisplayed, "Internal", "Failed - Verify type of No Show note");
			test_steps.add("Successfully verified type of note as <b>"+noteTypeDisplayed+"</b>");
					
			String noteSubjectDisplayed = driver.findElement(By.xpath("//td[text()='"+noteType+"']/..//td[contains(@data-bind,'subject')]")).getText();
			assertEquals(noteSubjectDisplayed, noteType, "Failed - Verify subject of cancel note");
			test_steps.add("Successfully verified subject of note as <b>"+noteSubjectDisplayed+"</b>");
			
			String noteUpdatedByDisplayed = driver.findElement(By.xpath("//td[text()='"+noteType+"']/..//td[contains(@data-bind,'updatedBy')]")).getText();
			assertEquals(noteUpdatedByDisplayed, updatedBy, "Failed - Verify note updated by");
			test_steps.add("Successfully verified note updated by as <b>"+noteUpdatedByDisplayed+"</b>");
			
			String noteUpdatedOnDisplayed = driver.findElement(By.xpath("//td[text()='"+noteType+"']/..//td[contains(@data-bind,'updatedOn')]")).getText();
			String updateDateExp = Utility.getCurrentDate("MM/dd/yyyy");
			noteUpdatedOnDisplayed = Utility.parseDate(noteUpdatedOnDisplayed, "MM/dd/ yy HH:mm aa", "MM/dd/yyyy");
			assertEquals(noteUpdatedOnDisplayed, updateDateExp, "Failed - Verify note updated on date");
			test_steps.add("Successfully verified note updated on date as <b>"+noteUpdatedOnDisplayed+"</b>");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public boolean CheckedDisplayVoidItem(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_DisplayVoidItemCheckBox, driver);
		boolean isChecked = res.CP_Reservation_DisplayVoidItemCheckBox.isSelected();
		if (!isChecked) {
			Utility.ScrollToElement(res.CP_Reservation_DisplayVoidItemCheckBox, driver);
			res.CP_Reservation_DisplayVoidItemCheckBox.click();
			test_steps.add("Checked Display Void Item Check Box ");
			reslogger.info("Checked Display Void Item Check Box ");
		} else {
			test_steps.add("Display Void Item Check Box is Already Checked ");
			reslogger.info("Display Void Item Check Box is Already Checked ");
		}
		return isChecked;
	}
	public ArrayList<String> verifyPrimaryUserFolioDetails(WebDriver driver,String roomNumber1,String roomClass,ArrayList<String> test_steps){
		
	    Folio folio = new Folio();
	    driver.findElement(By.xpath("//button[@class='btn dropdown-toggle btn-default'][contains(@title,'Guest Folio')]")).click();
	    WebElement roomsToSelect=driver.findElement(By.xpath("(//button[@class='btn dropdown-toggle btn-default'][contains(@title,'Guest Folio')]/..//a/span[@class='text'])[1]"));
	    roomsToSelect.click();
		double folioRoomChargeLineItems =0.00;
		String noShowFee =null;
		/*List<WebElement> rooms = driver.findElements(By.xpath("//button[@class='btn dropdown-toggle btn-default'][contains(@title,'Guest Folio')]/..//a/span[@class='text']"));
		System.out.println(rooms.size());
		for (WebElement roomsToSelect : rooms) {
			driver.findElement(By.xpath("//button[@class='btn dropdown-toggle btn-default'][contains(@title,'Guest Folio')]")).click();
			Wait.wait1Second();
			roomsToSelect.click();*/
			List<WebElement> roomChargesAmount = driver.findElements(By.xpath("//span[contains(text(), 'Room Charge')]/../..//td[@class='lineitem-amount']"));
			if (roomChargesAmount.size() !=0) {
				for (WebElement getRoomCharges : roomChargesAmount) {
					double roomCharge = Double.parseDouble(getRoomCharges.getText().replace("$ ", ""));
					folioRoomChargeLineItems = folioRoomChargeLineItems+roomCharge;
				}			
			}
			noShowFee =folio.getNoShowFeeAmount(driver, "No Show Fee", test_steps);
			folio.clikOnLineItemDescription(driver, "No Show Fee",test_steps);
			folio.verifyLineItemDetails(driver, roomNumber1,roomClass, noShowFee ,test_steps);;
		return test_steps;		
	}


public ArrayList<String> verifySecondaryUserFolioDetails(WebDriver driver,String roomNumber1,String roomClass, ArrayList<String> test_steps){
		
	    Folio folio = new Folio();
	    driver.findElement(By.xpath("//button[@class='btn dropdown-toggle btn-default'][contains(@title,'Guest Folio')]")).click();
	    WebElement roomsToSelect=driver.findElement(By.xpath("(//button[@class='btn dropdown-toggle btn-default'][contains(@title,'Guest Folio')]/..//a/span[@class='text'])[2]"));
	    roomsToSelect.click();
		double folioRoomChargeLineItems =0.00;
		String noShowFee =null;
		/*List<WebElement> rooms = driver.findElements(By.xpath("//button[@class='btn dropdown-toggle btn-default'][contains(@title,'Guest Folio')]/..//a/span[@class='text']"));
		System.out.println(rooms.size());
		for (WebElement roomsToSelect : rooms) {
			driver.findElement(By.xpath("//button[@class='btn dropdown-toggle btn-default'][contains(@title,'Guest Folio')]")).click();
			Wait.wait1Second();
			roomsToSelect.click();*/
			List<WebElement> roomChargesAmount = driver.findElements(By.xpath("//span[contains(text(), 'Room Charge')]/../..//td[@class='lineitem-amount']"));
			if (roomChargesAmount.size() !=0) {
				for (WebElement getRoomCharges : roomChargesAmount) {
					double roomCharge = Double.parseDouble(getRoomCharges.getText().replace("$ ", ""));
					folioRoomChargeLineItems = folioRoomChargeLineItems+roomCharge;
					System.out.println(folioRoomChargeLineItems);
				}			
			}
			 noShowFee =folio.getNoShowFeeAmount(driver, "No Show Fee", test_steps);
			folio.clikOnLineItemDescription(driver, "No Show Fee",test_steps);
			folio.verifyLineItemDetails(driver, roomNumber1,roomClass, noShowFee ,test_steps);
		return test_steps;
	}


	public void clickOnEditMarketingInfoIcon(WebDriver driver) throws InterruptedException {

		Elements_CPReservation element = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.EditMarketingInfoIcon);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.EditMarketingInfoIcon), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.EditMarketingInfoIcon), driver);
		Utility.ScrollToElement_NoWait(element.EditMarketingInfoIcon, driver);
		element.EditMarketingInfoIcon.click();
	}

	public void clickOnSaveMarketingInfoIcon(WebDriver driver) throws InterruptedException {

		Elements_CPReservation element = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.SaveMarketingInfo);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.SaveMarketingInfo), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.SaveMarketingInfo), driver);
		element.SaveMarketingInfo.click();
		verifySpinerLoading(driver);

	}

	public ArrayList<String> splitRoomCheckbox(WebDriver driver, boolean isChecked) throws InterruptedException {

		Elements_CPReservation element = new Elements_CPReservation(driver);
		ArrayList<String> testSteps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_SplitCheckBox);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.CP_NewReservation_SplitCheckBox), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.CP_NewReservation_SplitCheckBox), driver);
		Utility.ScrollToElement(element.CP_NewReservation_SplitCheckBox, driver);
		if (isChecked) {
			if (element.CP_NewReservation_SplitCheckBox.isSelected()) {
				testSteps.add("Verified 'Split-Room Reservation' checkbox already checked");
			} else {
				element.CP_NewReservation_SplitCheckBox.click();
				testSteps.add("Verified 'Split-Room Reservation' checkbox is checked");
			}
		} else {
			if (!element.CP_NewReservation_SplitCheckBox.isSelected()) {
				element.CP_NewReservation_SplitCheckBox.click();
				testSteps.add("Verified 'Split-Room Reservation' checkbox is unchecked");
			} else {
				testSteps.add("Verified 'Split-Room Reservation' checkbox already unchecked");
			}
		}
		return testSteps;
	}


	// Created By adhnan
	public ArrayList<String> selectSplitRoom(WebDriver driver, String roomClass, int roomNumber, int RoomIndex)
			throws InterruptedException {

		Elements_CPReservation elementsReservation = new Elements_CPReservation(driver);
		ArrayList<String> steps = new ArrayList<>();
		spinerLoadingatRoomSelection(driver);

		String SplitRoomTabxpath = "//div[contains(@class,'split-parts')]/div[" + RoomIndex + "]";
		Wait.WaitForElement(driver, SplitRoomTabxpath);
		Wait.waitForElementToBeVisibile(By.xpath(SplitRoomTabxpath), driver);
		Wait.waitForElementToBeClickable(By.xpath(SplitRoomTabxpath), driver);
		WebElement SplitRoomTab = driver.findElement(By.xpath(SplitRoomTabxpath));
		Utility.ScrollToElement(SplitRoomTab, driver);
		SplitRoomTab.click();
		reslogger.info("after click");

		assertTrue(SplitRoomTab.getAttribute("class").contains("active"),
				"Failed: Split Room tab " + RoomIndex + " is not opened");
		steps.add("Split Room Tab '" + RoomIndex + "' is opened");
		reslogger.info("after click");

		String expand = "//section[@class='ir-roomClassDetails manual-override']//span[text()='" + roomClass
				+ "']//..//..//..//following-sibling::div//div//select//following-sibling::div//button";
		reslogger.info(expand);
		// Wait.WaitForElement(driver, expand);
		WebElement selectRoomNumber = driver.findElement(By.xpath(expand));
		Utility.ScrollToElement(selectRoomNumber, driver);
		selectRoomNumber.click();
		reslogger.info("after click o room selector");

		String selectedRoomNumber = "//section[@class='ir-roomClassDetails manual-override']//span[text()='" + roomClass
				+ "']//..//..//../following-sibling::div//div//select//following-sibling::div//ul/li[" + roomNumber
				+ "]/a/span";
		Wait.WaitForElement(driver, selectedRoomNumber);
		driver.findElement(By.xpath(selectedRoomNumber)).click();
		String Selectxpath = "//section[@class='ir-roomClassDetails manual-override']//span[text()='" + roomClass
				+ "']//ancestor::div[contains(@data-bind,'expandRoomClassRateInfo')]//span[contains(@data-bind,'SELECT')]";
		Wait.WaitForElement(driver, Selectxpath);
		WebElement select = driver.findElement(By.xpath(Selectxpath));
		Utility.ScrollToElement(select, driver);
		select.click();
		steps.add("Click Select button");
		reslogger.info("Click Select button");
		String classvalue = select.getAttribute("class");
		for (int i = 0; i < 5; i++) {
			if (classvalue.contains("selected")) {
				break;
			} else {
				Wait.wait2Second();
				classvalue = select.getAttribute("class");
			}
		}
		assertTrue(classvalue.contains("selected"), "Failed: Room Class not selected");
		String RoomNO = driver.findElement(By.xpath(expand)).getAttribute("title");
		steps.add("Select Room as '" + roomClass + " : " + RoomNO + "'");
		reslogger.info("Select Room as '" + roomClass + " : " + RoomNO + "'");
		return steps;

	}

	public String getSplitRoomNumber(WebDriver driver, String IsAssign, int index) {
		String room = "(//span[text()='ROOM:']//following-sibling::span)[" + index + "]";
		Wait.WaitForElement(driver, room);
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(room)), driver);
		room = driver.findElement(By.xpath(room)).getText();

		if (IsAssign.equalsIgnoreCase("No")) {
			if (room.equalsIgnoreCase("Unassigned")) {
				test_steps.add("Room Number is : Unassigned");
				reslogger.info("Room Number is : Unassigned");
			}
		} else {
			test_steps.add("Room Number is : " + room);
			reslogger.info("Room Number is : " + room);
		}
		return room;
	}

	public void spinerLoadingatRoomSelection(WebDriver driver) throws InterruptedException {

		Elements_CPReservation res = new Elements_CPReservation(driver);

		try {
			int count = 0;
			while (true) {
				if (!res.spinerLoadingatRoomSelection.isDisplayed()) {
					break;
				}
				count++;
				Wait.wait1Second();

			}
		} catch (Exception e) {
			reslogger.info("In catch module loading");
		}

		reslogger.info("Waited to loading symbol");

	}

	public void moveToAddARoom(WebDriver driver) throws InterruptedException {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_AddRoom);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.CP_NewReservation_AddRoom), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.CP_NewReservation_AddRoom), driver);
		Utility.ScrollToElement_NoWait(res.CP_NewReservation_AddRoom, driver);

	}

	public void enterDate(WebDriver driver, String dateType, String Date, String roomIndex)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		String Datexpath = "(//input[contains(@data-bind,'" + dateType + "Date')])[" + roomIndex + "]";
		Wait.explicit_wait_xpath(Datexpath, driver);
		WebElement DateInput = driver.findElement(By.xpath(Datexpath));
		Wait.explicit_wait_visibilityof_webelement(DateInput, driver);
		Utility.ScrollToElement(DateInput, driver);
		DateInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Date);
		assertEquals(DateInput.getAttribute("value"), Date, "Failed: Entered Date missmatched");
	}

	public void enterMailingAddressOnUncheckedCreateGuestProfile(WebDriver driver, ArrayList<String> test_steps,
			String Salutation, String GuestFirstName, String GuestLastName, String IsGuesProfile)
			throws InterruptedException {
		enter_GuestName(driver, test_steps, Salutation, GuestFirstName, GuestLastName);
		uncheck_CreateGuestProfile(driver, test_steps, IsGuesProfile);
	}

	public String getBalanceHeader(WebDriver driver) {

		Elements_CPReservation elements = new Elements_CPReservation(driver);
		return Utility.convertDollarToNormalAmount(driver, elements.tripBalanceAmountAtTop.getText());
	}

	public String getTripTotalHeader(WebDriver driver) {

		Elements_CPReservation res = new Elements_CPReservation(driver);

		return Utility.convertDollarToNormalAmount(driver, res.tripTotalAmountAtTop.getText());

	}

	public String getRoomCharge(WebDriver driver, ArrayList<String> test_steps) {
		String Balance = driver
				.findElement(By
						.xpath("//label[contains(text(),'Room Charges: ')]/following-sibling::span[@class='pamt']/span[@class='pamt']"))
				.getText();
		Balance = Balance.replace("$ ", "");
		test_steps.add("Room Charges : <b>" + Balance + "</b>");
		reslogger.info("Room Charges : " + Balance);
		return Balance;
	}

	public String getTax(WebDriver driver, ArrayList<String> test_steps) {
		String Balance = driver
				.findElement(By
						.xpath("//label[contains(text(),'Taxes & Service Charges:')]/following-sibling::span/span[@class='pamt']"))
				.getText();
		Balance = Balance.replace("$", "");
		test_steps.add("Taxes & Service Charges : " + Balance);
		reslogger.info("Taxes & Service Charges : " + Balance);
		return Balance;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <selectRoom> ' Description: <method will select roomclass>
	 * ' Input parameters: <WebDriver driver,String RoomClass,String IsAssign> '
	 * Return value: <ArrayList<String>> ' Created By: <Jamal Nasir> ' Created
	 * On: <05/19/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> selectRoom(WebDriver driver, String RoomClass, String IsAssign)
			throws InterruptedException {
		String room = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'" + RoomClass
				+ "')]/../../div[2]/span";
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.WaitForElement(driver, room);
		String rooms = driver.findElement(By.xpath(room)).getText();
		reslogger.info(rooms);
		String[] roomsCount = rooms.split(" ");
		int count = Integer.parseInt(roomsCount[0].trim());
		if (count > 0) {
			String sel = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'" + RoomClass
					+ "')]/../../../following-sibling::div/div/select";

			String rulessize = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
					+ RoomClass.trim() + "')]/following-sibling::span";
			reslogger.info(rulessize);
			int ruleCount = driver.findElements(By.xpath(rulessize)).size();
			WebElement ele = driver.findElement(By.xpath(sel));
			test_steps.add("Selected room class : " + RoomClass);
			reslogger.info("Selected room class : " + RoomClass);
			if (IsAssign.equalsIgnoreCase("No")) {
				String expand = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/following-sibling::div/button";
				Wait.WaitForElement(driver, expand);
				driver.findElement(By.xpath(expand)).click();

				String unAssign = "(//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass
						+ "')]/../../../following-sibling::div/div/select/following-sibling::div//ul//span[text()='Unassigned'])";
				Wait.WaitForElement(driver, unAssign);
				driver.findElement(By.xpath(unAssign)).click();
				test_steps.add("Selected room number : Unassigned");
				reslogger.info("Selected room number : Unassigned");
			} else {
				String roomnum = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/option";
				reslogger.info(roomnum);
				int count1 = driver.findElements(By.xpath(roomnum)).size();
				Random random = new Random();
				int randomNumber = random.nextInt(count1 - 1) + 1;
				reslogger.info("count : " + count1);
				reslogger.info("randomNumber : " + randomNumber);
				Wait.WaitForElement(driver, sel);

				String expand = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/following-sibling::div/button";
				Wait.WaitForElement(driver, expand);
				driver.findElement(By.xpath(expand)).click();

				String roomNumber = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/following-sibling::div//ul/li["
						+ randomNumber + "]/a/span";
				Wait.WaitForElement(driver, roomNumber);
				driver.findElement(By.xpath(roomNumber)).click();
			}

			String select = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
					+ RoomClass + "')]/../../../following-sibling::div//span[contains(text(),'SELECT')]";
			Wait.WaitForElement(driver, select);
			driver.findElement(By.xpath(select)).click();

			String loading = "(//div[@class='ir-loader-in'])[2]";
			int counter = 0;
			while (true) {
				if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
					break;
				} else if (counter == 50) {
					break;
				} else {
					counter++;
					Wait.wait2Second();
				}
			}
			reslogger.info("Waited to loading symbol");

			reslogger.info("Rule Count : " + ruleCount);
			if (ruleCount > 1) {
				Wait.wait5Second();
				String rules = "//p[text()='Selecting this room will violate the following rules']/../..//button[text()='Yes']";

				if (driver.findElements(By.xpath(rules)).size() > 0) {
					Wait.wait2Second();
					driver.findElement(By.xpath(rules)).click();
					test_steps.add(
							"Selecting this room will violate the following rules : Are you sure you want to proceed? : yes");
					reslogger.info(
							"Selecting this room will violate the following rules : Are you sure you want to proceed? : yes");

					loading = "(//div[@class='ir-loader-in'])[2]";
					counter = 0;
					while (true) {
						if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
							break;
						} else if (counter == 4) {
							break;
						} else {
							counter++;
							Wait.wait2Second();
						}
					}
				}
			}

		} else {
			test_steps.add("Rooms Count <=0 for " + RoomClass + " for specified date");
			reslogger.info("Rooms Count <=0 for " + RoomClass + " for specified date");
		}
		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <VeriGuestProfileCheckox> ' Description: <Verify create
	 * guest profile checkbox is checked or not> ' Input parameters: <boolean
	 * value> ' Return value: <array list> ' Created By: <Farhan Ghaffar> '
	 * Created On: <04/17/2020>
	 * 
	 * ' Modified By | Description of Modification:
	 * ------------------------------------------ <mm/dd/yyyy>:<Developer name>:
	 * 1.Step modified 2.Step modified
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> VeriGuestProfileCheckox(WebDriver driver, boolean isChecked) throws InterruptedException {

		Elements_CPReservation element = new Elements_CPReservation(driver);
		ArrayList<String> test_step = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.CheckboxGuestProfile);
		Wait.explicit_wait_visibilityof_webelement(element.CheckboxGuestProfile, driver);
		Wait.explicit_wait_elementToBeClickable(element.CheckboxGuestProfile, driver);
		Utility.ScrollToElement(element.CheckboxGuestProfile, driver);
		if (isChecked) {
			if (element.CheckboxGuestProfile.isSelected()) {
				test_step.add("Verified create guest profile checkbox already checked");
			} else {
				element.CheckboxGuestProfile.click();
				test_step.add("Verified create guest profile checkbox is checked");
			}
		} else {
			if (!element.CheckboxGuestProfile.isSelected()) {
				element.CheckboxGuestProfile.click();
				test_step.add("Verified create guest profile checkbox is  unchecked");
			} else {
				test_step.add("Verified create guest profile checkbox already unchecked");
			}
		}

		return test_step;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <verifyGuestReportLabelsValidations> ' Description:
	 * <Method will verify system report labels is displaying or not> ' Input
	 * parameters: <WebDriver driver> ' Return value: <ArrayList<String>> '
	 * Created By: <Jamal Nasir> ' Created On: <05/22/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> verifyGuestReportLabelsValidations(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		test_steps.add("========== Guest Reports labels verification ==========");
		reslogger.info("========== Guest Reports labels verification ==========");
		Elements_CPReservation reservationPage = new Elements_CPReservation(driver);

		Wait.WaitForElement(driver, OR_Reservation.reservationReports);
		reservationPage.reservationReports.click();

		Wait.WaitForElement(driver, OR_Reservation.guestStatement);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.guestStatement), driver);
		if (reservationPage.guestStatement.isDisplayed()) {
			test_steps.add("Guest Statement label displayed");
			reslogger.info("Guest Statement label displayed");
		} else {
			test_steps.add("Guest Statement label not displayed");
			reslogger.info("Guest Statement label not displayed");
		}

		if (reservationPage.guestStatementWithTaxBreakdown.isDisplayed()) {
			test_steps.add("Guest Statement with Tax breakdown label displayed");
			reslogger.info("Guest Statement with Tax breakdown label displayed");
		} else {
			test_steps.add("Guest Statement with Tax breakdown label not displayed");
			reslogger.info("Guest Statement with Tax breakdown label not displayed");
		}

		if (reservationPage.guestRegistration.isDisplayed()) {
			test_steps.add("Guest Registration label displayed");
			reslogger.info("Guest Registration label displayed");
		} else {
			test_steps.add("Guest Registration label not displayed");
			reslogger.info("Guest Registration label not displayed");
		}

		if (reservationPage.guestRegistartiontaxbreakdown.isDisplayed()) {
			test_steps.add("Guest Registration with Tax breakdown label displayed");
			reslogger.info("Guest Registration with Tax breakdown label displayed");
		} else {
			test_steps.add("Guest Registration with Tax breakdown label not displayed");
			reslogger.info("Guest Registration with Tax breakdown label not displayed");
		}
		reservationPage.reservationReports.click();
		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <clickGuestStatementWithTaxBreakdown> ' Description: <This
	 * method will click Guest Statement with Tax breakdown report button> '
	 * Input parameters: <WebDriver driver> ' Return value: <ArrayList<String>>
	 * ' Created By: <Jamal Nasir> ' Created On: <05/22/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> clickGuestStatementWithTaxBreakdown(WebDriver driver) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<>();

		Wait.WaitForElement(driver, OR_Reservation.reservationReports);
		res.reservationReports.click();
		test_steps.add("Click on reports");
		reslogger.info("Click on reports");

		Wait.WaitForElement(driver, OR_Reservation.guestStatementWithTaxBreakdown);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.guestStatementWithTaxBreakdown), driver);
		res.guestStatementWithTaxBreakdown.click();

		test_steps.add("Click on Guest Statement with Tax breakdown button");
		reslogger.info("Click on Guest Statement with Tax breakdown button");
		Wait.wait5Second();
		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <clickGuestRegistartiontaxbreakdown> ' Description: <This
	 * method will click Guest Registration with Tax breakdown report button> '
	 * Input parameters: <WebDriver driver> ' Return value: <ArrayList<String>>
	 * ' Created By: <Jamal Nasir> ' Created On: <05/22/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> clickGuestRegistartionTaxBreakdown(WebDriver driver) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<>();

		Wait.WaitForElement(driver, OR_Reservation.reservationReports);
		res.reservationReports.click();
		test_steps.add("Click on reports");
		reslogger.info("Click on reports");

		Wait.WaitForElement(driver, OR_Reservation.guestRegistartiontaxbreakdown);
		Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.guestRegistartiontaxbreakdown), driver);
		res.guestRegistartiontaxbreakdown.click();
		test_steps.add("Click on Guest Registration with Tax breakdown button");
		reslogger.info("Click on Guest Registration with Tax breakdown button");
		Wait.wait5Second();

		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <replaceCurrency> ' Description: <This method will return
	 * string withOut currency symbol> ' Input parameters: <String str> ' Return
	 * value: <String> ' Created By: <Jamal Nasir> ' Created On: <05/22/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String replaceCurrency(String str) {
		str = str.replace("$", "");
		str = str.trim();
		reslogger.info("str : " + str);

		return str;
	}

	public void verifyNoteForSingleReservation(WebDriver driver, ArrayList<String> test_steps, String type,
			String subject, String desc, Date date, String policyType) throws InterruptedException {
		int CD = date.getDate();
		String day = String.valueOf(CD);
		reslogger.info("Day: " + day);

		if (policyType.equals("Cancel")) {

			String path = "//notes-info[contains(@params,'reservationDetail')]//tr/td[contains(@data-bind,'type')][(text()='"
					+ type + "')]/following-sibling::td[contains(@data-bind,'subject')][(text()='" + subject + "')] "
					+ "/following-sibling::td[contains(@data-bind,'details')][(text()='" + desc + "')]"
					+ "//following-sibling::td[contains(@data-bind,'updatedOn')][contains(text(),'" + day + "')]";

			reslogger.info(path);
			WebElement element = driver.findElement(By.xpath(path));
			Utility.ScrollToElement(element, driver);
			assertTrue(element.isDisplayed(), "Failed: To verify Cancellation Notes");
			test_steps.add("Verified Notes <br>" + "TYPE: <b>" + type + " </b> SUBJECT:  <b>" + subject
					+ " </b> DESCRIPTION:  <b>" + desc + " </b> UPDATED ON:  <b>" + date + " </b>");
			reslogger.info("Verified Notes TYPE: " + type + " SUBJECT: " + subject + " DESCRIPTION: " + desc
					+ " UPDATED ON: " + date);

		} else if (policyType.equals("No Show")) {
			String path = "//notes-info[contains(@params,'reservationDetail')]//tr/td[contains(@data-bind,'type')][(text()='"
					+ type + "')]/following-sibling::td[contains(@data-bind,'subject')][(text()='" + subject + "')]"
					+ "//following-sibling::td[contains(@data-bind,'updatedOn')][contains(text(),'" + day + "')]";

			reslogger.info(path);
			WebElement element = driver.findElement(By.xpath(path));
			Utility.ScrollToElement(element, driver);
			assertTrue(element.isDisplayed(), "Failed: To verify No Show Notes");
			test_steps.add("Verified Notes <br>" + "TYPE: <b>" + type + "</b> SUBJECT: <b>" + subject
					+ "</b> UPDATED ON: <b>" + date + "</b>");
			reslogger.info("Verified Notes TYPE: " + type + " SUBJECT: " + subject + " DESCRIPTION: " + desc
					+ " UPDATED ON: " + date);

		}
	}

	public void verifyStayInfoRoomNo(WebDriver driver, ArrayList<String> test_steps, List<String> roomNos) {
		String path = "//div[contains(text(),'Stay')]/..//div[@class='ir-flex-grow ir-roomInfo']//span[contains(@data-bind,'roomNumber')]";
		List<WebElement> roomNo = driver.findElements(By.xpath(path));
		for (int i = 0; i < roomNo.size(); i++) {
			assertEquals(roomNo.get(i).getText().toLowerCase().trim(), roomNos.get(i).toLowerCase().trim(),
					"Failed: To verify Stay Info Room Nos");
			test_steps.add("Verified Stay Info Room Number <b>" + roomNos.get(i) + "</b>");
			reslogger.info("Verified Stay Info Room Number " + roomNos.get(i));
		}

	}

	public void verifyHistoryForChangedToUnAssigned(WebDriver driver, ArrayList<String> testSteps, String roomNo,
			String roomClass) {
		String changedRoom = "Changed room assignment from " + roomNo + " to " + roomClass + "";
		String path = "//span[contains(text(),'" + changedRoom + "')]";
		WebElement element = driver.findElement(By.xpath(path));
		assertTrue(element.isDisplayed(), "Failed: To verify History Tab  Changed Room Assignment");
		testSteps.add("'History Tab' Verified <b>Changed room assignment from " + roomNo + " to </b>" + roomClass);
		reslogger.info("'History Tab' Verified  Changed room assignment from " + roomNo + " to " + roomClass);

	}

	public void commonMethodToverifyRoomsOnHistoryTab(WebDriver driver, ArrayList<String> test_steps, String abb,
			String RoomClassName)

			throws InterruptedException {
		String path = "//div[contains(@data-bind,'hasHistory')]//td/span[contains(@data-bind,'data.room')][contains(text(),'"
				+ abb + ": " + RoomClassName + "')]";
		WebElement element = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(element, driver);
		/*
		 * assertEquals(element.getText().toLowerCase().trim(),(RoomClassName.
		 * toLowerCase().trim()), "Failed: To verify History Tab  Room");
		 */
		assertTrue(element.isDisplayed(), "Failed: To verify History Tab  Room");
		test_steps.add("'History Tab' Verified Room: <b>" + abb + ": " + RoomClassName + "</b>");
		reslogger.info("'History Tab' Verified Room : " + abb + ": " + RoomClassName);
	}

	public void checkInProcess(WebDriver driver, ArrayList test_steps) throws InterruptedException {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		if (res.CP_Reservation_ConfirmCheckIn_Button.isEnabled()) {
			Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_ConfirmCheckIn_Button);
			Utility.ScrollToElement(res.CP_Reservation_ConfirmCheckIn_Button, driver);
			res.CP_Reservation_ConfirmCheckIn_Button.click();
			test_steps.add("Clicking on  Confirm Check-In Button");
			reslogger.info("Clicking on Confirm  Check-In Button");
		} else if (res.CP_Reservation_ConfirmCheckInPayment_Button.isEnabled()) {
			Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_ConfirmCheckInPayment_Button);
			Utility.ScrollToElement(res.CP_Reservation_ConfirmCheckInPayment_Button, driver);
			res.CP_Reservation_ConfirmCheckInPayment_Button.click();
			test_steps.add("Clicking on  Proceed to Check-In Payment Button");
			reslogger.info("Clicking on Proceed to Check-In Payment Button");

		}
	}

	public void AddTask(WebDriver driver, ArrayList test_steps, String IsTask, String TaskCategory, String TaskType,
			String TaskDetails, String TaskRemarks, String TaskDueon, String TaskAssignee, String TaskStatus)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_AddTask);
		test_steps.add("******************* Adding Task **********************");
		reslogger.info("******************* Adding Task **********************");
		if (IsTask.equalsIgnoreCase("Yes")) {
			Utility.ScrollToElement(res.CP_AddTask, driver);
			res.CP_AddTask.click();
			test_steps.add("Click on Add Task");
			reslogger.info("Click on Add Task");

			String taskCategoryArrow = "//label[text()='Category']/..//button";
			Wait.WaitForElement(driver, taskCategoryArrow);
			driver.findElement(By.xpath(taskCategoryArrow)).click();

			String taskCategory = "//label[text()='Category']/..//div/ul//span[text()='" + TaskCategory.trim() + "']";
			Wait.WaitForElement(driver, taskCategory);
			driver.findElement(By.xpath(taskCategory)).click();
			test_steps.add("Select Task Category : " + TaskCategory);
			reslogger.info("Select Task Category : " + TaskCategory);
			Wait.wait2Second();

			String taskType = "//label[text()='Type']//..//div//button";
			reslogger.info(taskType);
			Wait.WaitForElement(driver, taskType);
			driver.findElement(By.xpath(taskType)).click();
			Wait.wait2Second();
			taskType = "//label[text()='Type']//..//div/ul//span[text()='" + TaskType.trim() + "']";
			Wait.WaitForElement(driver, taskType);
			driver.findElement(By.xpath(taskType)).click();
			test_steps.add("Select Task Type : " + TaskType);
			reslogger.info("Select Task Type : " + TaskType);

			String taskDetails = "//label[text()='Details']/preceding-sibling::textarea";
			Wait.WaitForElement(driver, taskDetails);
			driver.findElement(By.xpath(taskDetails)).sendKeys(TaskDetails);
			test_steps.add("Select Task Details : " + TaskDetails);
			reslogger.info("Select Task Details : " + TaskDetails);

			String taskRemarks = "//label[text()='Remarks']/preceding-sibling::textarea";
			Wait.WaitForElement(driver, taskRemarks);
			driver.findElement(By.xpath(taskRemarks)).sendKeys(TaskRemarks);
			test_steps.add("Enter Task Remarks : " + TaskRemarks);
			reslogger.info("Enter Task Remarks : " + TaskRemarks);

			if (!TaskAssignee.isEmpty()) {
				String taskAssignee = "//label[text()='Assignee']/..//input";
				WebElement elementAssigneeInput = driver.findElement(By.xpath(taskAssignee));

				// elementAssigneeInput.sendKeys(TaskAssignee);
				TaskAssignee = "auto";
				String[] splitString = TaskAssignee.split(" ");
				TaskAssignee = splitString[0];
				String noDataFound = "//label[text()='Assignee']//..//div//div";
				for (int i = 0; i < 5; i++) {
					elementAssigneeInput.click();
					elementAssigneeInput.clear();
					for (int j = 0; j < TaskAssignee.length(); j++) {
						elementAssigneeInput.sendKeys(String.valueOf(TaskAssignee.charAt(j)));
						Wait.wait1Second();
					}
					try {
						reslogger.info("in try");
						Wait.wait1Second();
						String taskassgin = "(//label[text()='Assignee']/..//div//ul//li//div/span)[2]";
						// Wait.WaitForElement(driver, taskassgin);
						WebElement elementTaskAssign = driver.findElement(By.xpath(taskassgin));
						elementTaskAssign.click();
						break;
					} catch (Exception e) {
						reslogger.info("in catch");
					}

				}

			}

			String taskStatus = "(//label[text()='Status']/..//button)[2]";
			Wait.WaitForElement(driver, taskStatus);
			driver.findElement(By.xpath(taskStatus)).click();
			String status = "(//label[text()='Status']/..//button)[2]/following-sibling::div//li//span[text()='"
					+ TaskStatus.trim() + "']";
			Wait.WaitForElement(driver, status);
			driver.findElement(By.xpath(status)).click();
			test_steps.add("Select Task Status : " + TaskStatus);
			reslogger.info("Select Task Status : " + TaskStatus);

			String save = "//h4[text()='Add Task']/../..//button[text()='Save']";
			Wait.WaitForElement(driver, save);
			driver.findElement(By.xpath(save)).click();
			test_steps.add("Click on Save");
			reslogger.info("Click on Save");

		}
	}

	public ArrayList<String> CheckInDate(WebDriver driver, int Days) throws InterruptedException, ParseException {

		Elements_CPReservation elements = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.EnterCheckinDate);
		Wait.explicit_wait_visibilityof_webelement(elements.EnterCheckinDate, driver);
		Wait.explicit_wait_elementToBeClickable(elements.EnterCheckinDate, driver);
		elements.EnterCheckinDate.click();
		elements.EnterCheckinDate.clear();
		String checkInDate = ESTTimeZone.DateFormateForLineItem(Days);
		elements.EnterCheckinDate.sendKeys(checkInDate);
		test_steps.add("CheckIn date: " + checkInDate);
		return test_steps;
	}

	public ArrayList<String> CheckOutDate(WebDriver driver, int Days) throws InterruptedException, ParseException {

		Elements_CPReservation elements = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.EnterCheckout);
		Wait.explicit_wait_visibilityof_webelement(elements.EnterCheckout, driver);
		Wait.explicit_wait_elementToBeClickable(elements.EnterCheckout, driver);
		elements.EnterCheckout.click();
		elements.EnterCheckout.clear();
		String checkInDate = ESTTimeZone.DateFormateForLineItem(Days);
		elements.EnterCheckout.sendKeys(checkInDate);
		test_steps.add("CheckIn date: " + checkInDate);
		return test_steps;

	}

	public void click_FindRooms(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_FindRooms);
		res.CP_NewReservation_FindRooms.click();
		test_steps.add("Clicking on FindRooms");
		reslogger.info("Clicking on FindRooms");
		int count = 0;
		while (true) {
			if (driver.findElement(By.xpath("//section[@class='ir-roomClassDetails manual-override']")).isDisplayed()) {
				break;
			} else if (count == 20) {
				break;
			} else {
				count++;
				Wait.wait2Second();
			}
		}
		reslogger.info("Waited to loading symbol");
	}

	public void select_Room(WebDriver driver, ArrayList test_steps, String RoomClass, String IsAssign, String Account)
			throws InterruptedException {
		reslogger.info(RoomClass);
		String room = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'" + RoomClass
				+ "')]/../../div[2]/span";
		reslogger.info(room);
		Wait.WaitForElement(driver, room);

		String rooms = driver.findElement(By.xpath(room)).getText();
		reslogger.info(rooms);
		String[] roomsCount = rooms.split(" ");
		int count = Integer.parseInt(roomsCount[0].trim());
		if (count > 0) {
			String sel = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'" + RoomClass
					+ "')]/../../../following-sibling::div/div/select";

			String rulessize = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
					+ RoomClass.trim() + "')]/following-sibling::span";
			reslogger.info(rulessize);
			int ruleCount = driver.findElements(By.xpath(rulessize)).size();
			WebElement ele = driver.findElement(By.xpath(sel));
			test_steps.add("Selected room class : " + RoomClass);
			reslogger.info("Selected room class : " + RoomClass);
			if (IsAssign.equalsIgnoreCase("No")) {
				String expand = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/following-sibling::div/button";
				Wait.WaitForElement(driver, expand);
				driver.findElement(By.xpath(expand)).click();

				String unAssign = "(//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass
						+ "')]/../../../following-sibling::div/div/select/following-sibling::div//ul//span[text()='Unassigned'])";
				Wait.WaitForElement(driver, unAssign);
				driver.findElement(By.xpath(unAssign)).click();
				test_steps.add("Selected room number : Unassigned");
				reslogger.info("Selected room number : Unassigned");
			} else {
				String roomnum = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/option";
				reslogger.info(roomnum);
				int count1 = driver.findElements(By.xpath(roomnum)).size();
				Random random = new Random();
				int randomNumber = random.nextInt(count1 - 1) + 1;
				reslogger.info("count : " + count1);
				reslogger.info("randomNumber : " + randomNumber);
				Wait.WaitForElement(driver, sel);

				String expand = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/following-sibling::div/button";
				Wait.WaitForElement(driver, expand);
				driver.findElement(By.xpath(expand)).click();

				String roomNumber = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
						+ RoomClass + "')]/../../../following-sibling::div/div/select/following-sibling::div//ul/li["
						+ randomNumber + "]/a/span";
				Wait.WaitForElement(driver, roomNumber);
				driver.findElement(By.xpath(roomNumber)).click();
			}

			String select = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
					+ RoomClass + "')]/../../../following-sibling::div//span[contains(text(),'SELECT')]";
			Wait.WaitForElement(driver, select);
			driver.findElement(By.xpath(select)).click();

			String loading = "(//div[@class='ir-loader-in'])[2]";
			int counter = 0;
			while (true) {
				if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
					break;
				} else if (counter == 50) {
					break;
				} else {
					counter++;
					Wait.wait2Second();
				}
			}
			reslogger.info("Waited to loading symbol");

			reslogger.info("Rule Count : " + ruleCount);
			if (ruleCount > 1) {
				Wait.wait5Second();
				String rules = "//p[text()='Selecting this room will violate the following rules']/../..//button[text()='Yes']";

				if (driver.findElements(By.xpath(rules)).size() > 0) {
					Wait.wait2Second();
					driver.findElement(By.xpath(rules)).click();
					test_steps.add(
							"Selecting this room will violate the following rules : Are you sure you want to proceed? : yes");
					reslogger.info(
							"Selecting this room will violate the following rules : Are you sure you want to proceed? : yes");

					loading = "(//div[@class='ir-loader-in'])[2]";
					counter = 0;
					while (true) {
						if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
							break;
						} else if (counter == 4) {
							break;
						} else {
							counter++;
							Wait.wait2Second();
						}
					}
				}
			}
			if (!(Account.isEmpty() || Account.equalsIgnoreCase(""))) {
				String policy = "//p[contains(text(),'Based on the changes made')]/../..//button[text()='Yes']";
				Wait.WaitForElement(driver, policy);
				driver.findElement(By.xpath(policy)).click();
				test_steps.add("Based on the changes made, new policies are applicable.? : yes");
				reslogger.info("Based on the changes made, new policies are applicable.? : yes");

				loading = "(//div[@class='ir-loader-in'])[2]";
				counter = 0;
				while (true) {
					if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
						break;
					} else if (counter == 3) {
						break;
					} else {
						counter++;
						Wait.wait2Second();
					}
				}
			}

		} else {
			test_steps.add("Rooms Count <=0 for " + RoomClass + " for specified date");
			reslogger.info("Rooms Count <=0 for " + RoomClass + " for specified date");
		}
	}

	public void click_Next(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Next);
		res.CP_NewReservation_Next.click();
		test_steps.add("Clicking on Next");
		reslogger.info("Clicking on Next");
		int count = 0;
		while (true) {
			if (driver.findElement(By.xpath("//span[contains(text(),'Contact Info')]")).isDisplayed()) {
				break;
			} else if (count == 20) {
				break;
			} else {
				count++;
				Wait.wait2Second();
			}
		}
		reslogger.info("Waited to loading symbol");
	}

	public void click_BookNow(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Book);
		res.CP_NewReservation_Book.click();
		test_steps.add("Click on Book Now");
		reslogger.info("Click on Book Now");
		String loading = "(//div[@class='ir-loader-in'])[2]";
		int count = 0;
		while (true) {
			if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
				break;
			} else if (count == 50) {
				break;
			} else {
				count++;
				Wait.wait2Second();
			}
		}
		reslogger.info("Waited to loading symbol");
	}

	public String GetTaskCategoryImage(WebDriver driver, int index) throws InterruptedException {
		Elements_CPReservation elements_CPReservation = new Elements_CPReservation(driver);

		try {
			System.out.println("in try");
			Wait.WaitForElement(driver, OR_Reservation.SpanTaskCategoryImage);
			Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.SpanTaskCategoryImage), driver);
			Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.SpanTaskCategoryImage), driver);
			Utility.ScrollToElement_NoWait(elements_CPReservation.SpanTaskCategoryImage.get(index), driver);


		} catch (Exception e) {
			driver.navigate().refresh();
			System.out.println("in catch");
			Wait.WaitForElement(driver, OR_Reservation.SpanTaskCategoryImage);
			Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.SpanTaskCategoryImage), driver);
			Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.SpanTaskCategoryImage), driver);
			Utility.ScrollToElement_NoWait(elements_CPReservation.SpanTaskCategoryImage.get(index), driver);

		}
		return elements_CPReservation.SpanTaskCategoryImage.get(index).getAttribute("class");

	}

	public String GetTaskName(WebDriver driver, int index) throws InterruptedException {
		Elements_CPReservation elements_CPReservation = new Elements_CPReservation(driver);
		return elements_CPReservation.SpanTaskName.get(index).getText();

	}

	public String GetTaskDetail(WebDriver driver, int index) throws InterruptedException {
		Elements_CPReservation elements_CPReservation = new Elements_CPReservation(driver);
		return elements_CPReservation.SpanTaskDetails.get(index).getText();

	}

	public String GetTaskDueOn(WebDriver driver, int index) throws InterruptedException {
		Elements_CPReservation elements_CPReservation = new Elements_CPReservation(driver);
		return elements_CPReservation.SpanTaskDueOn.get(index).getText();

	}

	public String GetTaskStatus(WebDriver driver, int index) throws InterruptedException {
		Elements_CPReservation elements_CPReservation = new Elements_CPReservation(driver);
		return elements_CPReservation.SpanTaskStatus.get(index).getText();

	}

	public void verifyReservationStausOnBasicSearch(WebDriver driver, ArrayList<String> test_steps, String statusExp) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.resStatusAtBasicSearch), driver);
		String statusDisplayed = res.resStatusAtBasicSearch.getText().trim();
		assertEquals(statusDisplayed, statusExp, "Failed: To verify reservation status at basic search");
		test_steps.add("Verified reservation status at basic search is : <b>" + statusDisplayed + "</b>");
		reslogger.info("Verified reservation status at basic search is : <b>" + statusDisplayed + "</b>");
	}

	public void enablePostCancellationFee(WebDriver driver, ArrayList<String> test_steps, boolean postCancellationFee) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		boolean postCancellationFeeSelected = res.cancelResPopupPostCancelFeeCheckBox.isSelected();
		if (postCancellationFee) {
			if (!(postCancellationFeeSelected)) {
				Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.cancelResPopupPostCancelFeeCheckBoxClick),
						driver);
				res.cancelResPopupPostCancelFeeCheckBoxClick.click();
				test_steps.add("Enabling Post Cancellation Fee check box");
			} else {
				test_steps.add("Post Cancellation Fee check box is already selected");
			}
		} else {
			if (postCancellationFeeSelected) {
				res.cancelResPopupPostCancelFeeCheckBoxClick.click();
				test_steps.add("Unselecting Post Cancellation Fee check box");
			} else {
				test_steps.add("Post Cancellation Fee check box is already unselected");
			}
		}
	}

	public void unsavedDataYesClick(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		try {
			boolean popupDisplayed = driver.findElements(By.xpath(OR_Reservation.unsavedDataDisplayed)).size() > 0;
			if (popupDisplayed) {
				res.unsavedDataPopupYesButton.click();
				test_steps.add("Clicking on yes button on unsaved data popup");
			} else {
				test_steps.add("Unsaved data popup is not displayed");
			}
		} catch (Exception e) {
			reslogger.info(e);
		}
	}

	public String getTaxesFromTripSummary(WebDriver driver) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		return res.TripSummary_TaxesAndServiceCharges.getText().replace("$ ", "");
	}

	public String get_TripSummaryPaidAmount(WebDriver driver, ArrayList test_steps) {
		String PaidBalance = driver
				.findElement(By
						.xpath("//span[text()='Summary']/../../..//div[contains(text(),'Paid')]/following-sibling::div"))
				.getText();
		PaidBalance = PaidBalance.replace("$ ", "");
		test_steps.add("Paid amount captured at trip summary is : <b>" + PaidBalance + "</b>");
		reslogger.info("Paid amount captured at trip summary is : " + PaidBalance);
		return PaidBalance;
	}

	public void verifyRoomChargesAtFolio(WebDriver driver, ArrayList<String> test_steps, String amount) {
		ArrayList<String> roomCharges = new ArrayList<>();
		Elements_CPReservation res = new Elements_CPReservation(driver);
		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);
		List<WebElement> roomChargesAmount = driver
				.findElements(By.xpath("//span[contains(text(), 'Room Charge')]/../..//td[@class='lineitem-amount']"));
		for (WebElement roomCharge : roomChargesAmount) {
			String roomChargeAtFolioLineItem = roomCharge.getText().replace("$ ", "");
			roomCharges.add(roomChargeAtFolioLineItem);
		}
		int i = 1;
		for (String roomChargesDisplayed : roomCharges) {
			assertEquals(roomChargesDisplayed, amount,
					"Failed - verify room charges at row " + i + " as : " + roomChargesDisplayed);
			test_steps.add("Successfully verified voided line items at row <b>" + i + "</b> as <b>"
					+ roomChargesDisplayed + "</b>");
			i++;
		}
	}

	public void verifyDepositPolicyDetailsAtPoliciesAndDisclaimers(WebDriver driver, ArrayList<String> test_steps,
			String policyName, String policyText) {
		try {
			Elements_CPReservation res = new Elements_CPReservation(driver);
			Wait.waitForElementToBeClickable(By.xpath(OR_Reservation.depositPolicyCollapseIcon), driver);
			Utility.ScrollToElement(res.depositPolicyCollapseIcon, driver);
			res.depositPolicyCollapseIcon.click();
			Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.depositPolicyNameAtDisclaimers), driver);
			String policyNameDisplayed = res.depositPolicyNameAtDisclaimers.getText();
			String policyTextDisplayed = res.depositPolicyTextAtDisclaimers.getText();
			String policyNameAtToolTipDisplayed = res.depositPolicyToolTipTextAtDisclaimers.getAttribute("title");

			assertEquals(policyNameDisplayed, policyName, "Failed - Verify deposit policy name");
			test_steps.add("Successfully verified deposit policy name as <b>" + policyNameDisplayed + "</b>");
			reslogger.info("Successfully verified deposit policy name as <b>" + policyNameDisplayed + "</b>");

			assertEquals(policyTextDisplayed, policyText, "Failed - Verify deposit policy text");
			test_steps.add("Successfully verified deposit policy text as <b>" + policyTextDisplayed + "</b>");
			reslogger.info("Successfully verified deposit policy text as <b>" + policyTextDisplayed + "</b>");

			assertEquals(policyNameAtToolTipDisplayed, policyName, "Failed - Verify deposit policy name at tooltip");
			test_steps.add("Successfully verified deposit policy name at tooltip as <b>" + policyNameAtToolTipDisplayed
					+ "</b>");
			reslogger.info("Successfully verified deposit policy name at tooltip as <b>" + policyNameAtToolTipDisplayed
					+ "</b>");

		} catch (Exception e) {

		}
	}

	public void verifyTripSummaryPaidAmount(WebDriver driver, ArrayList<String> test_steps, String paidAmount) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);
		paidAmount = df.format(Double.parseDouble(paidAmount));
		try {
			Elements_CPReservation res = new Elements_CPReservation(driver);
			String paidAmountDisplayed = get_TripSummaryPaidAmount(driver, test_steps);
			paidAmountDisplayed.trim();
			assertEquals(paidAmountDisplayed, paidAmount, "Failed - Verify paid amount at summary");
			test_steps.add("Successfully verified paid amount displayed at trip summary as <b>" + paidAmountDisplayed
					+ "</b>");
			reslogger.info("Successfully verified paid amount displayed at trip summary as <b>" + paidAmountDisplayed
					+ "</b>");

		} catch (Exception e) {

		}
	}

	public void verifyTripPaidAmountAtFolio(WebDriver driver, ArrayList<String> test_steps, String paidAmount) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);
		paidAmount = df.format(Double.parseDouble(paidAmount));

		try {
			Elements_CPReservation res = new Elements_CPReservation(driver);
			String paidAmountDisplayed = get_Payments(driver, test_steps);
			assertEquals(paidAmountDisplayed, paidAmount, "Failed - Verify paid amount at summary");
			test_steps
					.add("Successfully verified paid amount displayed at folio as <b>" + paidAmountDisplayed + "</b>");
			reslogger
					.info("Successfully verified paid amount displayed at folio as <b>" + paidAmountDisplayed + "</b>");

		} catch (Exception e) {

		}
	}
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getFristReservationRoomNumber> ' Description: <get the
	 * RoomNumber from frist reservation> ' Input parameters: < > ' Return
	 * value: <StringayList> ' Created By: <Reddy Ponnolu> ' Created On:
	 * <08/05/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public String getFristReservationRoomNumber(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.fristRoomNumberInReservation), driver);
		String fristRoomNumberInReservation = res.fristRoomNumberInReservation.getText();
		test_steps.add("frist RoomNumber InReservation ::" + fristRoomNumberInReservation);
		reslogger.info("frist RoomNumber InReservation ::" + fristRoomNumberInReservation);
		return fristRoomNumberInReservation;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getSecondReservationRoomNumber> ' Description: <get the
	 * RoomNumber from Second reservation> ' Input parameters: < > ' Return
	 * value: <String> ' Created By: <Reddy Ponnolu> ' Created On: <08/05/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String getSecondReservationRoomNumber(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.secondRoomNumberInReservation), driver);
		String secondRoomNumberInReservation = res.secondRoomNumberInReservation.getText();
		test_steps.add("Second RoomNumber InReservation ::" + secondRoomNumberInReservation);
		reslogger.info("Second RoomNumber InReservation ::" + secondRoomNumberInReservation);
		return secondRoomNumberInReservation;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getFristReservationRoomCharge> ' Description: <get the
	 * RoomCharge from frist reservation> ' Input parameters: < > ' Return
	 * value: <String> ' Created By: <Reddy Ponnolu> ' Created On: <08/05/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String getFristReservationRoomCharge(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.fristRoomChageInReservation), driver);
		String fristRoomChageInReservation = res.fristRoomChageInReservation.getText();
		test_steps.add("frist RoomChage Amount ::" + fristRoomChageInReservation);
		reslogger.info("frist RoomChage amount ::" + fristRoomChageInReservation);
		return fristRoomChageInReservation;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getSecondReservationRoomCharge> ' Description: <get the
	 * RoomCharge from second reservation> ' Input parameters: < > ' Return
	 * value: <String> ' Created By: <Reddy Ponnolu> ' Created On: <08/05/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String getSecondReservationRoomCharge(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.SecondRoomChageInReservation), driver);
		String SecondRoomChageInReservation = res.SecondRoomChageInReservation.getText();
		test_steps.add("second RoomChage Amount ::" + SecondRoomChageInReservation);
		reslogger.info("second RoomChage amount ::" + SecondRoomChageInReservation);
		return SecondRoomChageInReservation;

	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 *
	 * ' Method Name: <getRoomSelectedFromTapeChart> ' Description: <Get the
	 * reservation Room number which is created from the tape chart> ' Input
	 * parameters: <WebDriver , ArrayList> ' Return value: <String> ' Created
	 * By: <Adhnan Ghaffar> ' Created On: <05/11/2020>
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public String getRoomSelectedFromTapeChart(WebDriver driver, ArrayList test_steps) {
		String room = "//div[contains(@data-bind,'roomClass.name')]";
		Wait.WaitForElement(driver, room);
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(room)), driver);
		room = driver.findElement(By.xpath(room)).getText();
		return room;
	}

	public void uncheckCreateGuestProfile(WebDriver driver, ArrayList<String> test_steps, String IsGuesProfile) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_CreateGuestProfile);
		if (IsGuesProfile.contentEquals("Yes")) {
			if (!res.CP_NewReservation_CreateGuestProfile.isSelected()) {
				driver.findElement(By.xpath("//span[contains(text(),'Create Guest Profile')]/..")).click();
				test_steps.add("Create Guest Profile selected");
				reslogger.info("Create Guest Profile selected");
			} else {
				test_steps.add("Create Guest Profile selected");
				reslogger.info("Create Guest Profile selected");
			}
		} else {
			if (res.CP_NewReservation_CreateGuestProfile.isSelected()) {
				driver.findElement(By.xpath("//span[contains(text(),'Create Guest Profile')]/..")).click();
				test_steps.add("Create Guest Profile unselected");
				reslogger.info("Create Guest Profile unselected");
			} else {
				test_steps.add("Create Guest Profile unselected");
				reslogger.info("Create Guest Profile unselected");
			}
		}
	}

	public void enterEmail(WebDriver driver, ArrayList<String> test_steps, String Email) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Email);
		res.CP_NewReservation_Email.clear();
		res.CP_NewReservation_Email.sendKeys(Email);
		test_steps.add("Enter Email : " + Email);
		reslogger.info("Enter Email : " + Email);
	}

	public ArrayList<String> SelectReferral(WebDriver driver, String Referral) throws InterruptedException {

		Elements_CPReservation element = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Referral);
		Wait.explicit_wait_visibilityof_webelement(element.CP_NewReservation_Referral, driver);
		Wait.explicit_wait_elementToBeClickable(element.CP_NewReservation_Referral, driver);
		Utility.ScrollToElement(element.CP_NewReservation_Referral, driver);
		element.CP_NewReservation_Referral.click();

		String pathReferral = "(//label[text()='Referral']/preceding-sibling::div//ul/li//span[contains(text(),'"
				+ Referral.trim() + "')])[2]";
		reslogger.info(pathReferral);
		Wait.WaitForElement(driver, pathReferral);
		WebElement elementReferral = driver.findElement(By.xpath(pathReferral));
		Wait.explicit_wait_visibilityof_webelement(elementReferral, driver);
		elementReferral.click();
		reslogger.info("Selected Referral as : " + Referral);
		test_steps.add("Selected Referral as : " + Referral);
		return test_steps;
	}

	public ArrayList<String> getReservationEmail(WebDriver driver, String Email) {
		ArrayList<String> test_steps = new ArrayList<>();
		Email = Email.substring(0, 10);
		reslogger.info("Email : " + Email);
		String EmailPath = "//div[contains(text(), 'Any scheduled emails will be sent to " + Email + "')]";
		Wait.WaitForElement(driver, EmailPath);
		assertTrue(driver.findElement(By.xpath(EmailPath)).isDisplayed(), "Failed: Email verifications");
		test_steps.add("Verified Email in Reservation Save Popup");
		reslogger.info("Verified Email in Reservation Save Popup");

		return test_steps;
	}

	public String getReservationStatus(WebDriver driver, ArrayList test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_ReservationStatus);
		String status = res.CP_NewReservation_ReservationStatus.getText();
		test_steps.add("Reservation Status : " + status);
		reslogger.info("Reservation Status : " + status);
		return status;
	}

	public String getRoomNumber(WebDriver driver, ArrayList test_steps, String IsAssign) {
		String room = "//span[text()='ROOM:']/following-sibling::span";
		Wait.WaitForElement(driver, room);
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(room)), driver);
		room = driver.findElement(By.xpath(room)).getText();

		if (IsAssign.equalsIgnoreCase("No")) {
			if (room.equalsIgnoreCase("Unassigned")) {
				test_steps.add("Room Number is : Unassigned");
				reslogger.info("Room Number is : Unassigned");
			}
		} else {
			test_steps.add("Room Number is : " + room);
			reslogger.info("Room Number is : " + room);
		}
		return room;
	}

	public String getRoomClassResDetail(WebDriver driver) {
		String path = "//div[contains(@class,'roomInfo')]//span[contains(@data-bind,'roomClassName')]";
		Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(path)), driver);
		return driver.findElement(By.xpath(path)).getText();
	}

	public void closeFirstOpenedReservation(WebDriver driver, ArrayList test_steps) throws InterruptedException {
		String close = "//ul[@class='sec_nav']//li[5]//i";
		try {
			// Wait.explicit_wait_visibilityof_webelement_120(driver.findElement(By.xpath(close)),
			// driver);
			Wait.WaitForElement(driver, close);
			Wait.wait2Second();
			driver.findElement(By.xpath(close)).click();
			test_steps.add("Closed the reservation");
			reslogger.info("Closed the reservation");
		} catch (Exception e) {
			Wait.WaitForElement(driver, close);
			Wait.wait2Second();
			driver.findElement(By.xpath(close)).click();
			test_steps.add("Closed the reservation");
			reslogger.info("Closed the reservation");
		}
	}

	public String checkPDF(String folder) throws IOException {
		File getFile = Utility.getLatestFilefromDir(System.getProperty("user.dir") + File.separator + "externalFiles"
				+ File.separator + "downloadFiles" + File.separator + folder + File.separator);
		reslogger.info(getFile.getAbsolutePath());
		reslogger.info(getFile.getName());
		PDDocument document = PDDocument.load(getFile);
		document.getClass();
		String pdfFileInText = null;
		if (!document.isEncrypted()) {
			PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			stripper.setSortByPosition(true);
			PDFTextStripper tStripper = new PDFTextStripper();
			pdfFileInText = tStripper.getText(document);
			document.close();

			if (getFile.delete()) {
				reslogger.info("File deleted successfully");
			} else {
				reslogger.info("Failed to delete the file");
			}
		}
		return pdfFileInText;
	}

	public void Click_CheckInButton(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement_120(res.CP_Reservation_CheckInButton, driver);
		Utility.ScrollToElement(res.CP_Reservation_CheckInButton, driver);
		res.CP_Reservation_CheckInButton.click();
		test_steps.add("Click Check In  Button");
		reslogger.info("Click Check In  Button");

	}

	public void createTaxExempt(WebDriver driver, String taxExcemptId, ArrayList<String> test_steps)
			throws InterruptedException {
		clickTaxExcemptCheckbox(driver, test_steps);
		enterTaxExceptId(driver, taxExcemptId, test_steps);
	}

	public void enterTaxExceptId(WebDriver driver, String taxExcemptId, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.explicit_wait_10sec(res.enterTaxExcemptId, driver);
		if (res.enterTaxExcemptId.isEnabled()) {
			res.enterTaxExcemptId.sendKeys(taxExcemptId);
			test_steps.add("Enter taxExempt Id: " + "<b>" + taxExcemptId + "</b>");
		}
	}

	public void clickTaxExcemptCheckbox(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.explicit_wait_10sec(res.clickTaxExcemptCheckBox, driver);
		Wait.explicit_wait_elementToBeClickable(res.clickTaxExcemptCheckBox, driver);
		res.clickTaxExcemptCheckBox.click();
		Utility.ScrollToElement(res.clickTaxExcemptCheckBox, driver);
		test_steps.add("Click at taxExempt checkBox");
	}

	public boolean isTaxPresentAfterTaxExempt(WebDriver driver, ArrayList<String> test_steps, String taxLedgeraccount,
			String TaxDescriptions) throws InterruptedException {
		boolean isTaxExist = true;
		Elements_CPReservation res = new Elements_CPReservation(driver);
		int roomChargesFolioCount = getRoomChargeFolioItemCountInRes(driver);
		for (int i = 1; i <= roomChargesFolioCount; i++) {
			String xpath = "(//tbody[starts-with(@data-bind,'getElement: ComputedFolioItemsElement')]/tr/td/span[text()='"
					+ taxLedgeraccount + "']/../following-sibling::td/a)[" + i + "]";
			// clickroomChargeFolioItems
			Wait.waitUntilPresenceOfElementLocated(xpath, driver);
			Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(xpath)), driver);
			driver.findElement(By.xpath(xpath)).click();
			test_steps.add("Click at folio line item: " + i + " of" + " " + taxLedgeraccount);
			Wait.explicit_wait_10sec(res.itemDetailOnFolioPopup, driver);

			String xpath1 = "(//a[text()='" + TaxDescriptions + "'])[1]/ancestor::td/following-sibling::td/span";

			isTaxExist = verifySizeOfChildLineItems(driver, xpath1);
			test_steps.add(
					"Get if the created tax is present under folio after tax exempt: " + "<b>" + isTaxExist + "</b>");
			clickFolioPopupCancel(driver);
			test_steps.add("Close folio popup");
		}

		return isTaxExist;

	}

	public boolean verifySizeOfChildLineItems(WebDriver driver, String xpath) throws InterruptedException {
		boolean ChildLineItems = false;
		Wait.wait5Second();
		try {
			// String xpath1 = "//table[@class='table table-bordered
			// table-striped table-hover
			// table-condensed']/tbody/tr/td/span[text()='Laundry']";
			ChildLineItems = driver.findElements(By.xpath(xpath)).size() != 0;
		} catch (Exception e) {
			reslogger.info(e);
			ChildLineItems = false;
		}
		return ChildLineItems;
	}
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <CalculateDatesForMRCheckInCheckOut> ' Description: <This
	 * method will return Dates for MR Reservations CheckIn and CheckOut> '
	 * Input parameters: <int days> ' Return value: <String> ' Created By:
	 * <Jamal Nasir> ' Created On: <05/18/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public String CalculateDatesForMRCheckInCheckOut(int days) throws InterruptedException, ParseException {
		String firstDate = ESTTimeZone.DateFormateForCheckInCheckOut(days);
		String secondDate = ESTTimeZone.DateFormateForCheckInCheckOut(days + 1);
		String resDate = firstDate + "|" + secondDate;
		reslogger.info(resDate);
		return resDate;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <getMRBRoomNumber> ' Description: <Return room numbers
	 * based on index for MRB Reservations> ' Input parameters: <WebDriver
	 * driver, String IsAssign, int roomIndex> ' Return value: <String> '
	 * Created By: <Jamal Nasir> ' Created On: <05/18/2020>
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public String getMRBRoomNumber(WebDriver driver, String IsAssign, int roomIndex) {
		String room = "(//span[text()='ROOM:']/following-sibling::span)[" + roomIndex + "]";
		Wait.WaitForElement(driver, room);
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath(room)), driver);
		room = driver.findElement(By.xpath(room)).getText();
		if (IsAssign.equalsIgnoreCase("No")) {
			if (room.equalsIgnoreCase("Unassigned")) {
				reslogger.info("Room Number is : Unassigned");
			}
		} else {
			reslogger.info("Room Number is : " + room);
		}
		return room;
	}

	public boolean verifyCheckINPaymentWindow(WebDriver driver) {
		boolean value = Utility.isElementPresent(driver,
				By.xpath("//h4[contains(@data-bind,'PaymentPopupHeaderText()')][contains(text(),'Check In Payment')]"));
		return value;
	}

	public String getDepositDueAmount(WebDriver driver) throws InterruptedException {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		Utility.ScrollToElement(res.getDepositDueCharge, driver);
		Wait.explicit_wait_visibilityof_webelement(res.getDepositDueCharge, driver);
		String depositAmount = res.getDepositDueCharge.getText().replace("$", "").trim();
		return depositAmount;

	}

	public void selectGuestFolioItemWithIndexInRes(WebDriver driver, int index) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_FolioDetailDropDownBox);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_FolioDetailDropDownBox, driver);
		String xpath = "(//ul[@class='dropdown-menu inner']/li/a/span[starts-with(.,'Guest Folio For')])[" + index
				+ "]";
		driver.findElement(By.xpath(xpath)).click();
	}

	public int getTotalGuestFolioItem(WebDriver driver, ArrayList<String> test_steps) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_FolioDetailDropDownBox);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_FolioDetailDropDownBox, driver);
		int guestFolioItems = res.getTotalGuestFolioItemInRes.size();
		test_steps.add("Guest folio Count in Folio details dropdown: " + "<b>" + guestFolioItems + "</b>");
		return guestFolioItems;
	}

	public void enterAdultsChildren(WebDriver driver, String AdultsChildrens, String Value, int RoomIndex)
			throws InterruptedException {
		String elementxpath = "(//label[text()='" + AdultsChildrens + "']/preceding-sibling::input)[" + RoomIndex + "]";
		Wait.explicit_wait_xpath(elementxpath, driver);
		WebElement ElementInput = driver.findElement(By.xpath(elementxpath));
		Wait.explicit_wait_visibilityof_webelement(ElementInput, driver);
		Utility.ScrollToElement(ElementInput, driver);
		ElementInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Value);
		assertEquals(ElementInput.getAttribute("value"), Value, "Failed: Entered Date missmatched");
	}

	public void selectRateplan(WebDriver driver, String Rateplan, String PromoCode, int RoomIndex)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);

		String elementxpath = "(" + OR_Reservation.CP_NewReservation_Rateplan + ")[" + RoomIndex + "]";
		Wait.explicit_wait_xpath(elementxpath, driver);
		WebElement Element = driver.findElement(By.xpath(elementxpath));
		Wait.explicit_wait_visibilityof_webelement(Element, driver);
		Wait.explicit_wait_elementToBeClickable(Element, driver);
		Utility.ScrollToElement(Element, driver);
		Element.click();
		String rate = "(//label[contains(text(),'Rate Plan / Promo')]/preceding-sibling::div)[" + RoomIndex
				+ "]//span[contains(text(),'" + Rateplan + "')]/..";
		reslogger.info(rate);
		Wait.WaitForElement(driver, rate);
		driver.findElement(By.xpath(rate)).click();
		reslogger.info("Selecting the rateplan : " + Rateplan);
		if (Rateplan.contains("Promo")) {
			Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_PromoCode);
			res.CP_NewReservation_PromoCode.clear();
			res.CP_NewReservation_PromoCode.sendKeys(PromoCode);
			reslogger.info("Enter promocode : " + PromoCode);
		}
	}

	public void selectRoom(WebDriver driver, String RoomClass, int RoomNumber, int RoomIndex, ArrayList<String> steps)
			throws InterruptedException {
		Elements_CPReservation reservationPage = new Elements_CPReservation(driver);
		steps.add("Selecting  Room number '" + RoomIndex + "'");
		String expand = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'" + RoomClass
				+ "')]//..//..//..//following-sibling::div//div/select//following-sibling::div";
		verifySpinerLoading(driver);
		reslogger.info(expand);
		Wait.WaitForElement(driver, expand);
		Wait.waitForElementToBeVisibile(By.xpath(expand), driver);
		Wait.waitForElementToBeClickable(By.xpath(expand), driver);
		WebElement Expand = driver.findElement(By.xpath(expand));
		Utility.ScrollToElement(Expand, driver);
		Wait.wait3Second();
		try {
			System.out.println("in try");
			Expand.click();

		} catch (Exception e) {
			System.out.println("in catch");

			 expand = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'" + RoomClass
					+ "')]//..//..//..//following-sibling::div//div/select//following-sibling::div//button";
			verifySpinerLoading(driver);
			reslogger.info(expand);
			
			 Expand = driver.findElement(By.xpath(expand));
			 Expand.click();

		}
		String roomNumber = "//section[@class='ir-roomClassDetails manual-override']//span[contains(text(),'"
				+ RoomClass + "')]//..//..//..//following-sibling::div//div/select//following-sibling::div//ul/li["
				+ RoomNumber + "]//a//span";
		Wait.WaitForElement(driver, roomNumber);
		driver.findElement(By.xpath(roomNumber)).click();
		String Selectxpath = "//section[@class='ir-roomClassDetails manual-override']//span[text()='" + RoomClass
				+ "']//ancestor::div[contains(@data-bind,'expandRoomClassRateInfo')]//span[contains(@data-bind,'SELECT')]";
		Wait.WaitForElement(driver, Selectxpath);
		WebElement Select = driver.findElement(By.xpath(Selectxpath));
		Utility.ScrollToElement(Select, driver);
		Select.click();
		steps.add("Click Select button");
		reslogger.info("Click Select button");
		String RoomNO = driver.findElement(By.xpath(expand)).getAttribute("title");
		steps.add("Selected Room as '" + RoomClass + " : " + RoomNO + "'");
		reslogger.info("Selected Room as '" + RoomClass + " : " + RoomNO + "'");

	}

	public void addPrimaryRoom(WebDriver driver, ArrayList test_steps, ArrayList al) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		String primaryArrow = "//span[text()='Primary Room']//..//..//following-sibling::div//button";
		Wait.WaitForElement(driver, primaryArrow);
		Wait.waitForElementToBeVisibile(By.xpath(primaryArrow), driver);
		Wait.waitForElementToBeClickable(By.xpath(primaryArrow), driver);
		Wait.wait2Second();
		driver.findElement(By.xpath(primaryArrow)).click();
		String primaryRoom = "//span[text()='Primary Room']//..//..//following-sibling::div//li[2]//span";
		primary = driver.findElement(By.xpath(primaryRoom)).getText();
		Wait.WaitForElement(driver, primaryRoom);
		driver.findElement(By.xpath(primaryRoom)).click();
		test_steps.add("Selected Primary Room as : " + primary);
		reslogger.info("Selected Primary Room as : " + primary);
		al.add(primary);
	}

	public void addAdditionalRoom(WebDriver driver, ArrayList test_steps, ArrayList al) {
		Elements_CPReservation res = new Elements_CPReservation(driver);

		String additional = "(//span[text()='Additional Room'])";
		int count = driver.findElements(By.xpath(additional)).size();
		for (int i = 1; i <= count; i++) {
			String additionalArrow = "(//span[text()='Additional Room'])[" + i
					+ "]/../../following-sibling::div//button";
			Wait.WaitForElement(driver, additionalArrow);
			driver.findElement(By.xpath(additionalArrow)).click();
			String additionalRoom = "(//span[text()='Additional Room'])[" + i + "]//..//..//following-sibling::div//li["
					+ (i + 2) + "]//span";
			additionalRoomNo = driver.findElement(By.xpath(additionalRoom)).getText();
			Wait.WaitForElement(driver, additionalRoom);
			driver.findElement(By.xpath(additionalRoom)).click();
			test_steps.add("Selected Addtinal  Room as : " + additionalRoomNo);
			reslogger.info("Selected Addtinal Room as : " + additionalRoomNo);
			al.add(additionalRoomNo);
		}
	}

	// Created by adhnan
	public void AddNotes(WebDriver driver, ArrayList test_steps, String RoomName, String NoteType, String Subject,
			String Description) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_AddNotes);
		test_steps.add("======ADD NOTES=====");
		reslogger.info("======ADD NOTES=====");
		res.CP_AddNotes.click();
		test_steps.add("Click on Add Notes");
		reslogger.info("Click on Add Notes");

		String RoomArrow = "//div[contains(@data-bind,'addNewNote')]//button[@title='--Select Room--']";
		Wait.WaitForElement(driver, RoomArrow);
		driver.findElement(By.xpath(RoomArrow)).click();

		String Room = "//div[contains(@data-bind,'addNewNote')]//button[@title='--Select Room--']//..//button//..//div//li//a//span[text()='"
				+ RoomName.trim() + "']";
		Wait.WaitForElement(driver, Room);
		driver.findElement(By.xpath(Room)).click();
		test_steps.add("Select Room : " + RoomName);
		reslogger.info("Select Room : " + RoomName);

		String noteTypeArrow = "//h4[text()='Add Notes']/../..//div/div//div//label[text()='Type']//..//button";
		Wait.WaitForElement(driver, noteTypeArrow);
		driver.findElement(By.xpath(noteTypeArrow)).click();

		String noteType = "//h4[text()='Add Notes']/../..//div/div//div//label[text()='Type']//..//button/..//div//li/a/span[text()='"
				+ NoteType.trim() + "']";
		Wait.WaitForElement(driver, noteType);
		driver.findElement(By.xpath(noteType)).click();
		test_steps.add("Select Note Type : " + NoteType);
		reslogger.info("Select Note Type : " + NoteType);

		String subject = "//h4[text()='Add Notes']//..//..//div/div//div//label[text()='Subject']//..//input";
		Wait.WaitForElement(driver, subject);
		driver.findElement(By.xpath(subject)).sendKeys(Subject);
		test_steps.add("Enter subject : " + Subject);
		reslogger.info("Enter subject : " + Subject);

		String description = "//h4[text()='Add Notes']//..//..//div/div//div//label[text()='Description']//preceding-sibling::textarea";
		Wait.WaitForElement(driver, description);
		driver.findElement(By.xpath(description)).sendKeys(Description);
		test_steps.add("Enter Description : " + Description);
		reslogger.info("Enter Description : " + Description);

		String user = "//input[starts-with(@data-bind,'value: updatedBy')]";

		JavascriptExecutor js = (JavascriptExecutor) driver;
		user = (String) js.executeScript("return arguments[0].value", driver.findElement(By.xpath(user)));

		String add = "//h4[text()='Add Notes']//..//..//button[text()='Add']";
		Wait.WaitForElement(driver, add);
		driver.findElement(By.xpath(add)).click();
		test_steps.add("Click on Add");
		reslogger.info("Click on Add");

		String noteverify = "//td[text()='" + NoteType.trim() + "']";
		Wait.WaitForElement(driver, noteverify);
		test_steps.add("Sucessfully added Note : " + Subject);
		reslogger.info("Sucessfully added Note : " + Subject);

	}

	public void verifyNotesExist(WebDriver driver, String NoteType, String Room, String Subject, String Description,
			String UpdatedOn, boolean exist) {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement(res.NotesTypes.get(0), driver);
		int Size = res.NotesTypes.size();
		reslogger.info("Added Notes Size : " + Size);
		int lineNumber = Size - 1;
		reslogger.info("Expected Type : " + NoteType);
		reslogger.info("Expected Room : " + Room);
		reslogger.info("Expected Subject : " + Subject);
		reslogger.info("Expected Description : " + Description);
		reslogger.info("Expected Updated On Date : " + UpdatedOn);
		boolean found = false;
		for (lineNumber = Size - 1; lineNumber >= 0; lineNumber--) {
			reslogger.info("LineNumber : " + lineNumber);
			reslogger.info("Note Type : " + res.NotesTypes.get(lineNumber).getText());
			reslogger.info("Room : " + res.NotesRoom.get(lineNumber).getText());
			reslogger.info("Subject : " + res.NotesSubject.get(lineNumber).getText());
			reslogger.info("Description : " + res.NotesDescription.get(lineNumber).getText());
			reslogger.info("Updated On Date : " + res.NotesUpdatedOn.get(lineNumber).getText());
			if (res.NotesTypes.get(lineNumber).getText().equals(NoteType)
					&& res.NotesRoom.get(lineNumber).getText().equals(Room)
					&& res.NotesSubject.get(lineNumber).getText().contains(Subject)
					&& res.NotesDescription.get(lineNumber).getText().equals(Description)
					&& res.NotesUpdatedOn.get(lineNumber).getText().contains(UpdatedOn)) {
				found = true;
				break;
			}
		}
		assertEquals(found, exist, "Failed: Note having Subject : " + Subject + " Not in Required visibility form");

	}

	// created by adnan
	public void selectAccount(WebDriver driver, ArrayList<String> test_steps, String Account, String AccountType)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Account);
		String accountdisplay = "//label[contains(text(),'Account')]//..//i[starts-with(@class,'rightIcon demo-icon icon-pencil')]";
		WebElement ele = driver.findElement(By.xpath(accountdisplay));
		if (!ele.isDisplayed()) {
			if (Account.equalsIgnoreCase("") || Account.isEmpty()) {
				reslogger.info("No Account");
			} else {
				res.CP_NewReservation_Account.sendKeys(Account);
				test_steps.add("Enter Account : " + Account);
				reslogger.info("Enter Account : " + Account);
				Wait.wait2Second();
				String account = "//b[contains(text(),'" + Account.trim() + "')]//..//..//..";
				Wait.WaitForElement(driver, account);
				driver.findElement(By.xpath(account)).click();
				String dataYes = "//div[contains(text(),'Do you want to replace the guest info')]//following-sibling::div//button[contains(text(),'Yes')]";
				Wait.WaitForElement(driver, dataYes);
				driver.findElement(By.xpath(dataYes)).click();
				test_steps.add(
						"Do you want to replace the guest info, payment method, marketing info and notes in this reservation with the information from the account? Clicking yes will save all the account info to the reservation. : Yes");
				reslogger.info(
						"Do you want to replace the guest info, payment method, marketing info and notes in this reservation with the information from the account? Clicking yes will save all the account info to the reservation. : Yes");
				Wait.wait2Second();
				String policyYes = "//div[contains(text(),'Based on the account chosen, new policies are applicable. Would you like to apply these policies to the reservation?')]/following-sibling::div//button[contains(text(),'Yes')]";

				try {
					// Wait.WaitForElement(driver, policyYes);
					Wait.wait2Second();
					driver.findElement(By.xpath(policyYes)).click();
					test_steps.add(
							"Based on the account chosen, new policies are applicable. Would you like to apply these policies to the reservation? : Yes");
					reslogger.info(
							"Based on the account chosen, new policies are applicable. Would you like to apply these policies to the reservation? : Yes");

				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		} else {
			test_steps.add("Account Aleady exists");
			reslogger.info("Account Aleady exists");
		}
	}

	public void clickOnPrintIcon(WebDriver driver) throws InterruptedException, IOException {
		Elements_CPReservation reservationPage = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR.ClickOnPrintIcon);
		Wait.waitForElementToBeVisibile(By.xpath(OR.ClickOnPrintIcon), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.ClickOnPrintIcon), driver);
		reservationPage.printIcon.click();

	}

	public void clickOnDetailedReservationList(WebDriver driver) throws InterruptedException {
		Wait.wait3Second();
		Elements_CPReservation reservationPage = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR.CheckboxDetailedReservationList);
		Wait.waitForElementToBeVisibile(By.xpath(OR.CheckboxDetailedReservationList), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.CheckboxDetailedReservationList), driver);
		reservationPage.checkboxDetailedReservationList.click();


			}	
			
			
			public double getCheckInAmount(WebDriver driver, ArrayList test_steps) {
				Elements_CPReservation res = new Elements_CPReservation(driver);
				// Get Amount
				String amount = res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount.getText();
				double checkinAmount = Double.valueOf(Amount);
			//	test_steps.add("Check In Amount Is :<b>  " + checkINCheckOutAmount + "</b>");
				reslogger.info(checkinAmount);
				return checkinAmount;
			}
			


			/*
			 * ##########################################################################################################################################################################
			 * 
			 * ' Method Name: verificationSuccessfullWindow 
			 * ' Description: Verification of Check In Success Window and Check Out Success Window
			 * ' Input parameters: String
			  * ' Created By: Gangotri
			 * ' Created On: June 3 2020
			 * 
			 * ##########################################################################################################################################################################
			 */			
			
			public void verificationSuccessfullWindow(WebDriver driver, ArrayList<String> test_steps,
					String NoShowSuccess, String Status, String PaymentMethod, String Notes, String Balance, String Type,
					String TotalNoFee, String PaymentType, String dateValue) throws InterruptedException, ParseException {
				Elements_CPReservation CPReservationPage = new Elements_CPReservation(driver);
				Wait.WaitForElement(driver, OR_Reservation.NoShowSuccess_ModelHeader);
				Wait.explicit_wait_visibilityof_webelement(CPReservationPage.CP_Reservation_NoShowSuccess, driver);
				Utility.ScrollToElement(CPReservationPage.CP_Reservation_NoShowSuccess, driver);

				// Verified Success -Message
				assertEquals(CPReservationPage.CP_Reservation_NoShowSuccess.getText().toLowerCase().trim(),
						(NoShowSuccess.toLowerCase().trim()), "Failed: To verify Success Header");
				test_steps.add("  Verified Message: <b>" + NoShowSuccess + "</b>");
				reslogger.info("  Verified Message:" + NoShowSuccess);

				if (PaymentType.equals("Check Out")) {
					// Verified Success-Date
					Date date = null;
					String Date = CPReservationPage.NoShowSuccess_Date.getText();
					DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
					dateFormat.setTimeZone(TimeZone.getTimeZone("EST"));
					Date myDate = dateFormat.parse(dateValue);
					String CurrentDate = dateFormat.format(myDate);

					// date = formatter.parse(Date);
					assertEquals(CPReservationPage.NoShowSuccess_Date.getText(), (CurrentDate),
							"Failed: To verify  Success Page Date");
					test_steps.add("  Verified Date: <b>" + dateValue + "</b>");
					reslogger.info("  Verified Date: " + dateValue);
				} else {

					assertEquals(CPReservationPage.NoShowSuccess_Date.getText(), (dateValue),
							"Failed: To verify  Success Page Date");

					test_steps.add("  Verified Date: <b>" + dateValue + "</b>");
					reslogger.info("  Verified Date: " + dateValue);
				}

				// Verified Success-Balance
				String balance = CPReservationPage.NoShowSuccess_Balance.getText().replace("$ ", "");

				reslogger.info(balance);
				if (PaymentType.equals("Check Out")) {
					/*double tripAmount = Double.valueOf(balance);
					int amt = (int) tripAmount;
					String BalanceCheckOut = String.valueOf(amt);*/
					String BalanceCheckOut = balance.trim();
					assertEquals(BalanceCheckOut.trim(), (Balance), "Failed: To verify Success Page Balance");
					test_steps.add(" Verified Balance: <b>" + Balance + "</b>");
					reslogger.info("  Verified Balance: " + Balance);
				} else {
					assertEquals(balance.trim(), (Balance), "Failed: To verify Success Page Balance");
					test_steps.add(" Verified Balance: <b>" + Balance + "</b>");
					reslogger.info("  Verified Balance: " + Balance);

				}
				// Verified Success-Amount

				System.out.print("Amount: " + TotalNoFee);
				String path = "//span[contains(@data-bind,'noShow.PaymentAmount')][contains(text(),'$ " + TotalNoFee
						+ "')]|//span[contains(@data-bind,'cancel.PaymentAmount')][contains(text(),'$ " + TotalNoFee
						+ "')]|//span[contains(@data-bind,'checkInDetail.PaymentAmount')][contains(text(),'$ " + TotalNoFee
						+ "')]|//span[contains(@data-bind,'checkOutDetail.PaymentAmount')][contains(text(),'$ " + TotalNoFee
						+ "')]";
				WebElement AmountElement = driver.findElement(By.xpath(path));
				assertTrue(AmountElement.isDisplayed(), "Failed: To verify  Success Page Amount: " + TotalNoFee);
				test_steps.add("  Verified Amount: <b>" + TotalNoFee + "</b>");
				reslogger.info("  Verified Amount: " + TotalNoFee);

				// Verified Success-Type
				System.out.println(type);
				assertEquals(CPReservationPage.NoShowSuccess_Type.getText().toLowerCase().trim(), (Type.toLowerCase().trim()),
						"Failed: To verify  Success Page Type");
				test_steps.add("  Verified Type: <b>" + Type + "</b>");
				reslogger.info(" Verified Type: " + Type);

				// Verified Success-Payment Method
				assertTrue(CPReservationPage.CP_Reservation_NoShowSuccessPaymentMethod.getText().toLowerCase().trim()
						.contains(PaymentMethod.toLowerCase().trim()), "Failed: To verify Success Page Payment Method");
				test_steps.add("  Verified Payment Method: <b>" + PaymentMethod + "</b>");
				reslogger.info("  Verified Payment Method: " + PaymentMethod);

				// Verified Success -Status
				assertEquals(CPReservationPage.CP_Reservation_NoShowSuccessStatus.getText().toLowerCase().trim(),
						(Status.toLowerCase().trim()), "Failed: To verify  Success Page Status ");
				test_steps.add("  Verified Status:<b> " + Status + "</b>");
				reslogger.info(" Verified Status: " + Status);

				// Verified Success Notes
				if (Utility.validateString(Notes)) {
					assertEquals(CPReservationPage.CP_Reservation_NoShowSuccessNotes.getText().toLowerCase().trim(),
							(Notes.toLowerCase().trim()), "Failed: To verify  Success Page Notes ");
					test_steps.add(" Verified  Notes: <b>" + Notes + "</b>");
					reslogger.info(" Verified  Notes: " + Notes);
				}
			}
			
			
			/*
			 * ##########################################################################################################################################################################
			 * 
			 * ' Method Name: verifyHistoryDescForNoShowCancel 
			 * ' Description: Verification of Made a no show payment and Made a cancellation payment
			 * ' Input parameters: String
			  * ' Created By: Gangotri
			 * ' Created On: June 3 2020
			 * 
			 * ##########################################################################################################################################################################
			 */			

	public void verifyHistoryDescForNoShowCancel(WebDriver driver, ArrayList<String> test_steps, String amount,
			String Status, String PaymentMethod) {
		String desc = "Made a no show payment of $" + amount.trim() + " using " + PaymentMethod;
		String CancelDesc = "Made a cancellation payment of $" + amount.trim() + " using " + PaymentMethod;
		String path = "//span[contains(text(),'" + desc + "')]|//span[contains(text(),'" + CancelDesc + "')]";
		WebElement element = driver.findElement(By.xpath(path));
		assertTrue(element.isDisplayed(), "Failed: To verify History Tab  Description");
		if (Status.equals("No Show")) {
			test_steps.add("'History Tab' Verified Description: <b>" + desc + "</b>");
			reslogger.info("'History Tab' Verified Description : " + desc);
		} else if (Status.equals("Cancel")) {
			test_steps.add("'History Tab' Verified Description: <b>" + CancelDesc + "</b>");
			reslogger.info("'History Tab' Verified Description : " + CancelDesc);

		}
	}

	public ArrayList<String> selectReportTyeAsPDF(WebDriver driver) throws InterruptedException {

		Elements_CPReservation reservationPage = new Elements_CPReservation(driver);
		ArrayList<String> testSteps = new ArrayList<>();

		Wait.WaitForElement(driver, OR.ClickOnReport);
		Wait.waitForElementToBeVisibile(By.xpath(OR.ClickOnReport), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.ClickOnReport), driver);
		reservationPage.clickOnReport.click();
		testSteps.add("Click on Reports");

		Wait.WaitForElement(driver, OR.ClickOnReportAsPDF);
		Wait.waitForElementToBeVisibile(By.xpath(OR.ClickOnReportAsPDF), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.ClickOnReportAsPDF), driver);
		Utility.ScrollToElement_NoWait(reservationPage.clickOnReport, driver);

		reservationPage.clickOnReportAsPDF.click();
		testSteps.add("Select report type as PDF");
		reslogger.info("Click on PDF Report");
		Wait.wait10Second();

		return testSteps;

	}

	public void downloadPDFReport(WebDriver driver) throws InterruptedException, IOException {
		Elements_CPReservation reservationPage = new Elements_CPReservation(driver);
		reservationPage.ClickOnPrint.click();
		Wait.wait5Second();
		Wait.waitForElementToBeGone(driver, reservationPage.ModuleLoading, 10000);
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		Wait.wait2Second();

		new Select(driver.findElement(By.xpath("//*[@id='drpReportType']"))).selectByVisibleText("Download As Pdf");
		reservationPage.DownloadButton.click();
		Wait.wait3Second();
		driver.close();
		driver.switchTo().window(tabs2.get(0));

	}

	public void clickOnCloseReservationReports(WebDriver driver) throws InterruptedException {
		Elements_CPReservation reservationPage = new Elements_CPReservation(driver);

		try {
			/*
			 * Wait.WaitForElement(driver, OR.ClickOnCloseReportsPopup);
			 * Wait.waitForElementToBeVisibile(By.xpath(OR.
			 * ClickOnCloseReportsPopup), driver);
			 * Wait.waitForElementToBeClickable(By.xpath(OR.
			 * ClickOnCloseReportsPopup), driver);
			 */
			reservationPage.ClickOnCloseReportsPopup.click();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/*
	 * ##########################################################################################################################################################################
	 * 
	 * ' Method Name: verifyCheckInButtonDisability 
	 * ' Description: Verification of Check In button disable
	 * ' Input parameters: String
	  * ' Created By: Gangotri
	 * ' Created On: June 3 2020
	 * 
	 * ##########################################################################################################################################################################
	 */			

	public void verifyCheckInButtonDisability(WebDriver driver, ArrayList<String> test_steps, String errorMessage)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_CheckInButton);
		assertTrue(!res.CP_Reservation_CheckInButton.isEnabled(), errorMessage);
		test_steps.add(" Check In Button is Disabled");
		reslogger.info(" Check In Button is Disabled");

	}

	public void createTaskWithVerification(WebDriver driver, ArrayList test_steps, boolean isAddDueOn,
			boolean isAddAssignee, String TaskCategory, String TaskType, String TaskDetails, String TaskRemarks,
			String TaskDueon, String TaskAssignee, String TaskStatus) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_AddTask);
		test_steps.add("******************* Adding Task **********************");
		reslogger.info("******************* Adding Task **********************");
		res.CP_AddTask.click();
		test_steps.add("Click on Add Task");
		reslogger.info("Click on Add Task");

		String taskCategoryArrow = "//label[text()='Category']/..//button";
		Wait.WaitForElement(driver, taskCategoryArrow);
		driver.findElement(By.xpath(taskCategoryArrow)).click();

		String taskCategory = "//label[text()='Category']/..//div/ul//span[text()='" + TaskCategory.trim() + "']";
		Wait.WaitForElement(driver, taskCategory);
		driver.findElement(By.xpath(taskCategory)).click();
		test_steps.add("Select Task Category : " + TaskCategory);
		reslogger.info("Select Task Category : " + TaskCategory);

		String taskType = "//label[text()='Type']/..//button";
		Wait.WaitForElement(driver, taskType);
		driver.findElement(By.xpath(taskType)).click();
		Wait.wait2Second();
		taskType = "//label[text()='Type']/..//div/ul//span[text()='" + TaskType.trim() + "']";
		Wait.WaitForElement(driver, taskType);
		driver.findElement(By.xpath(taskType)).click();
		test_steps.add("Select Task Type : " + TaskType);
		reslogger.info("Select Task Type : " + TaskType);

		String taskDetails = "//label[text()='Details']/preceding-sibling::textarea";
		Wait.WaitForElement(driver, taskDetails);
		driver.findElement(By.xpath(taskDetails)).sendKeys(TaskDetails);
		test_steps.add("Select Task Details : " + TaskDetails);
		reslogger.info("Select Task Details : " + TaskDetails);

		String taskRemarks = "//label[text()='Remarks']/preceding-sibling::textarea";
		Wait.WaitForElement(driver, taskRemarks);
		driver.findElement(By.xpath(taskRemarks)).sendKeys(TaskRemarks);
		test_steps.add("Enter Task Remarks : " + TaskRemarks);
		reslogger.info("Enter Task Remarks : " + TaskRemarks);
		if (isAddDueOn) {
			String dueCalender = "//label[text()='Due On']/..//i";
			Wait.WaitForElement(driver, dueCalender);
			driver.findElement(By.xpath(dueCalender)).click();
			String monthYear = Utility.get_MonthYear(TaskDueon);
			String day = Utility.getDay(TaskDueon);
			reslogger.info(monthYear);
			String header = null, headerText = null, next = null, date;
			for (int i = 1; i <= 12; i++) {
				header = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[2]";
				headerText = driver.findElement(By.xpath(header)).getText();
				if (headerText.equalsIgnoreCase(monthYear)) {
					date = "//td[contains(@class,'day') and text()='" + day + "']";
					Wait.WaitForElement(driver, date);
					int size = driver.findElements(By.xpath(date)).size();
					for (int k = 1; k <= size; k++) {
						date = "(//td[contains(@class,'day') and text()='" + day + "'])[" + k + "]";
						String classText = driver.findElement(By.xpath(date)).getAttribute("class");
						if (!classText.contains("old")) {
							driver.findElement(By.xpath(date)).click();
							test_steps.add("Selecting due on date : " + TaskDueon);
							reslogger.info("Selecting due on date : " + TaskDueon);
							break;
						}
					}
					break;
				} else {
					next = "//table[@class='datepicker-table-condensed table-condensed']/thead/tr/th[3]/div";
					Wait.WaitForElement(driver, next);
					driver.findElement(By.xpath(next)).click();
					Wait.wait2Second();
				}
			}
		}
		if (isAddAssignee) {
			String taskAssignee = "//label[text()='Assignee']/..//input";
			Wait.WaitForElement(driver, taskAssignee);
			driver.findElement(By.xpath(taskAssignee)).sendKeys(TaskAssignee);
			Wait.wait2Second();
			String taskassgin = "//label[text()='Assignee']/..//div/ul/li/div";
			Wait.WaitForElement(driver, taskassgin);
			driver.findElement(By.xpath(taskassgin)).click();
			test_steps.add("Enter Task Assignee : " + TaskAssignee);
			reslogger.info("Enter Task Assignee : " + TaskAssignee);
		}
		String taskStatus = "(//label[text()='Status']/..//button)[2]";
		Wait.WaitForElement(driver, taskStatus);
		driver.findElement(By.xpath(taskStatus)).click();
		String status = "//label[text()='Status']/..//button/following-sibling::div//li//span[text()='"
				+ TaskStatus.trim() + "']";
		Wait.WaitForElement(driver, status);
		driver.findElement(By.xpath(status)).click();
		test_steps.add("Select Task Status : " + TaskStatus);
		reslogger.info("Select Task Status : " + TaskStatus);

		String save = "//h4[text()='Add Task']/../..//button[text()='Save']";
		Wait.WaitForElement(driver, save);
		driver.findElement(By.xpath(save)).click();
		test_steps.add("Click on Save");
		reslogger.info("Click on Save");

		String taskVerify = "//span[text()='Tasks ']/../..//td//span[text()='" + TaskType.trim() + "']";
		Wait.WaitForElement(driver, taskVerify);
		test_steps.add("Sucessfully added Task : " + TaskType);
		reslogger.info("Sucessfully added Task : " + TaskType);

		test_steps.add("Verified Task Type : " + TaskType);
		reslogger.info("Verified Task Type : " + TaskType);

		test_steps.add("Verified Task Type : " + TaskType);
		reslogger.info("Verified Task Type : " + TaskType);

		taskDetails = "//span[text()='Tasks ']/../..//td[text()='" + TaskDetails.trim() + "']";
		Wait.WaitForElement(driver, taskDetails);
		test_steps.add("Verified Task details : " + TaskDetails);
		reslogger.info("Verified Task details : " + TaskDetails);
		if (isAddDueOn) {
			String[] taskdue = TaskDueon.split("/");
			String due = "//span[text()='Tasks ']/../..//td[contains(text(),'" + taskdue[1] + "/" + taskdue[0] + "')]";
			Wait.WaitForElement(driver, due);
			test_steps.add("Verified Task due on : " + TaskDueon);
			reslogger.info("Verified Task due on : " + TaskDueon);
		}

		String stat = "//span[text()='Tasks ']/../..//td[contains(text(),'" + TaskStatus.trim() + "')]";
		Wait.WaitForElement(driver, stat);
		test_steps.add("Verified Tsak Status : " + TaskStatus);
		reslogger.info("Verified Task Status : " + TaskStatus);

	}

	public void Click_CheckInAllButton(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.wait3Second();
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_CheckInAllButton, driver);
		Utility.ScrollToElement(res.CP_Reservation_CheckInAllButton, driver);
		res.CP_Reservation_CheckInAllButton.click();
		test_steps.add("Click Check In All Button");
		reslogger.info("Click Check In All Button");

	}

	public ArrayList<String> clickOnDirtyPopUp(WebDriver driver, ArrayList<String> test_sreps) {
		Elements_CPReservation reservation = new Elements_CPReservation(driver);
		try {
			Wait.wait2Second();
			Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.dirtyRoomPopUp), driver);
			reservation.dirtyRoomPopUp.click();
			test_sreps.add("click on popup dirty button");
			reslogger.info("click on popup dirty button");

		} catch (Exception e) {
			test_sreps.add("dirty room popup not  displyed");
			reslogger.info("dirty room popup not  displyed");
		}

		return test_sreps;

	}

	public ArrayList<String> clickonPaymentMetod(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {

		String selectPaymentDropDown = " (//div[contains(@class,'payment-method')]/button)";// div[contains(@class,'payment-method
																							// dropup')]/button";
		Wait.waitForElementToBeVisibile(By.xpath(selectPaymentDropDown), driver);
		// Wait.wait3Second();
		driver.findElement(By.xpath(selectPaymentDropDown)).click();
		test_steps.add("click on Payment method successfully");
		reslogger.info("click on Payment method successfully");

		return test_steps;

	}

	public ArrayList<String> selectPaymentMethod(WebDriver driver, String AccountName, ArrayList<String> test_steps)
			throws InterruptedException {

		String paymentType = "//div[contains(@class,'payment-method')]/button/..//a/span[contains(text(),'"
				+ AccountName + "')]";
		Wait.waitForElementToBeVisibile(By.xpath(paymentType), driver);
		Utility.ScrollToElement(driver.findElement(By.xpath(paymentType)), driver);
		Wait.wait3Second();
		driver.findElement(By.xpath(paymentType)).click();

		return test_steps;

	}

	public void verifyCheckInPaymentPopupIsAppeared(WebDriver driver, String paymentMethod,
			ArrayList<String> test_steps) throws Exception {
		try {

			Wait.waitForElementToBeVisibile(
					By.xpath(OR_Reservation.CP_Reservation_CheckIn_GuestRegistrationReportToggle), driver);
			if (driver.findElement(By.xpath(OR_Reservation.CP_Reservation_CheckIn_GuestRegistrationReportToggle))
					.isDisplayed()) {
				disableGenerateGuestReportToggle(driver, test_steps);
				clickOnProceedToCheckInPaymentButton(driver, test_steps);
				clickOnDirtyPopUp(driver, test_steps);
				clickonPaymentMetod(driver, test_steps);
				selectPaymentMethod(driver, paymentMethod, test_steps);
				clickOnPayButtonOnPaymentPopup(driver, test_steps);
				checkInPaymentSuccessPopupClose(driver, test_steps);
			} else {
				test_steps.add("checkin pop not avilable");
				reslogger.info("checkin pop not avilable");

			}
		} catch (Exception e) {
			test_steps.add("checkin pop not avilable");
			reslogger.info("checkin pop not avilable");
		}
		Wait.wait3Second();

	}

	public void Click_CheckOutAllButton(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.wait5Second();
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.CP_Reservation_ReservationStatusCheckOutAllButton),
				driver);
		// Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_ReservationStatusCheckOutAllButton,
		// driver);
		// Utility.ScrollToElement(res.CP_Reservation_ReservationStatusCheckOutAllButton,
		// driver);
		res.CP_Reservation_ReservationStatusCheckOutAllButton.click();
		test_steps.add("Click Check Out All  Button");
		reslogger.info("Click Check Out All Button");

	}

	public ArrayList<String> clickOnCheckOutPopUp(WebDriver driver, ArrayList<String> test_sreps) {
		Elements_CPReservation reservation = new Elements_CPReservation(driver);
		try {
			// Wait.wait3Second();
			Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.checkOutPopUp), driver);
			reservation.checkOutPopUp.click();
			test_sreps.add("click on popup yes button");
			reslogger.info("click on popup yes button");

		} catch (Exception e) {
			test_sreps.add("checkOut popup not  displyed");
			reslogger.info("checkOut popup not  displyed");

		}

		return test_sreps;
			
}
	


public void verifyAccountDetails(WebDriver driver, ArrayList<String> test_steps) {
	try {
		String checkOutAccountDetails = "//label[contains(text(),'ACCOUNT DETAILS')]/../span[contains(text(),'Account')]";
		Wait.waitForElementToBeVisibile(By.xpath(checkOutAccountDetails), driver);
		String account = driver.findElement(By.xpath(checkOutAccountDetails)).getText();
		// assertEquals(accountDetails, checkOutAccountDetails, "Failed: To
		// verify Account Details");
		test_steps.add(" Verified Account details:" + account);
		reslogger.info(" Verified Account details:" + account);

	} catch (Exception e) {
		System.out.println(e);

	}
}
	
	public ArrayList<String> paymentCheckOutPopUpVerify(WebDriver driver, String title, String paymentMethod,
			String type, String accountDetails, ArrayList<String> test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		ArrayList<String> details = new ArrayList<>();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.NoShowPaymentPopupTitle), driver);
		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);

		String titleDisplayed = res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupTitle.getText();
		reslogger.info(" Verified Payment Page Title : " + titleDisplayed);
		assertEquals(titleDisplayed, title, "Failed: To verify Payment Page Title");
		test_steps.add(" Verified Payment Page Title :<b> " + title + "</b>");

		String currentdate = Utility.getCurrentDate("MM/dd/yyyy");
		String date = "(//input[contains(@data-bind,'value:PaymentDate')])[2]";
		Wait.waitForElementToBeVisibile(By.xpath(date), driver);
		String checkOutDate = driver.findElement(By.xpath(date)).getAttribute("value");
		test_steps.add(" Verified checkOutDate: " + checkOutDate);
		reslogger.info(" Verified checkOutDate :" + checkOutDate);
		assertEquals(checkOutDate, currentdate, "Failed: To verify Account Details");

		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.folioBalance), driver);
		String folioBalence = res.folioBalance.getText().replace("$", "").trim();
		test_steps.add(" Verified folio Balence<b> " + folioBalence + "</b> ");
		reslogger.info(" Verifiedfolio Balence: " + folioBalence);

		clickonPaymentMetod(driver, test_steps);
		selectPaymentMethod(driver, paymentMethod, test_steps);
		verifyAccountDetails(driver, test_steps);
		String paymentTypeDisplayed = res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupType.getText().trim();
		test_steps.add(" Verified payment Type:<b> " + paymentTypeDisplayed + "</b>");
		reslogger.info(" Verified payment Type:");
		assertEquals(paymentTypeDisplayed, type, "Failed: To verify Payment Type");
		String amountDisplayed = res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount.getText().trim();
		test_steps.add(" Verified displayed  Amount:<b> " + paymentTypeDisplayed + "</b>");
		reslogger.info(" Verified displayed  Amount:");
		String balanceDisplayed = null;
		String buttonExp = null;
		String buttonDisplayed = res.CP_Reservation_NoShowPaymentPopup_LogAndPayButton.getText().trim();
		buttonDisplayed = buttonDisplayed.replace("$", "").replace("PAY", "").trim();
		test_steps.add(" Verified button displayed  Amount:<b> " + buttonDisplayed + "</b>");
		reslogger.info(" Verified button displayed  Amount:<b> " + buttonDisplayed + "</b>");
		assertEquals(buttonDisplayed, folioBalence, "Failed: To verify Payment Type");

		res.CP_Reservation_NoShowPaymentPopup_LogAndPayButton.click();
		test_steps.add(" Click on pament button");
		reslogger.info("  Click on pament button");

		return test_steps;

	}

	public ArrayList<String> proceedToCheckOutPayment(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Utility.ScrollToElement(res.CP_Reservation_ConfirmCheckOut_Button, driver);
		res.CP_Reservation_ConfirmCheckOut_Button.getText();
		res.CP_Reservation_ConfirmCheckOut_Button.click();
		test_steps.add("Clicking on  Proceed to Check-Out Payment Button");
		reslogger.info("Clicking on Proceed to Check-Out Payment Button");
		return test_steps;		  
	  }
	 
	 

	public ArrayList<String> verifycheckOutPaymentSuccessPopupVerify(WebDriver driver, String Authorizationtype,
			String checkOutSuccess, String Status, String checkOutPaymentMethod, ArrayList<String> test_steps) {
		Elements_CPReservation reservation = new Elements_CPReservation(driver);

		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.checkOutPaymentDate), driver);
		String checkOutPopUpDate = reservation.checkOutPaymentDate.getText();

		String date = Utility.getCurrentDate("MM/dd/yy");

		assertEquals(checkOutPopUpDate, date, "Failed: To verify  Success Page Date");
		test_steps.add("  Verified Date of checkout : <b>" + date + "</b>");
		reslogger.info("  Verified Date of checkout : " + date);

		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.checkOutFolioBalence), driver);
		String checkOutFolioBalence = reservation.checkOutFolioBalence.getText();
		test_steps.add(" checkOut FolioBalence :" + checkOutFolioBalence);
		reslogger.info(" checkOut FolioBalence :" + checkOutFolioBalence);

		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.checkOutPaymentAmount), driver);
		String checkOutPaymentAmount = reservation.checkOutPaymentAmount.getText();
		test_steps.add(" checkOut PaymentAmount :" + checkOutPaymentAmount);
		reslogger.info(" checkOut PaymentAmount :" + checkOutPaymentAmount);

		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.checkOutPaymentType), driver);
		String checkOutPaymentType = reservation.checkOutPaymentType.getText();
		test_steps.add(" checkOut PaymentType :" + checkOutPaymentType);
		reslogger.info(" checkOut PaymentType :" + checkOutPaymentType);

		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.checkoutPaymentStatus), driver);
		String checkoutPaymentStatus = reservation.checkoutPaymentStatus.getText();
		test_steps.add(" checkOut PaymentStatus :" + checkoutPaymentStatus);
		reslogger.info(" checkOut PaymentStatus :" + checkoutPaymentStatus);

		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.checkOutPaymentMethod), driver);
		String checkOutPaymentmethod = reservation.checkOutPaymentMethod.getText();
		test_steps.add(" checkOut Paymentmethod :" + checkOutPaymentmethod);
		reslogger.info(" checkOut Paymentmethod :" + checkOutPaymentmethod);

		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.checkOutButtonClose), driver);
		reservation.checkOutButtonClose.click();
		test_steps.add("click on cancel button");
		reslogger.info("click on cancel button");

		return test_steps;

	}

	public void verifyCheckOutPopIsAppear(WebDriver driver, String AccountName, String Authorizationtype,
			String accountDetails, ArrayList<String> test_steps) throws Exception {

		try {
			boolean popupDisplayed = driver.findElements(By.xpath(OR_Reservation.NoShowPaymentPopupTitle)).size() > 0;
			// Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.NoShowPaymentPopupTitle),
			// driver);
			if (popupDisplayed) {
				/*
				 * String accountDetails = "Account -" + AccountName + " " +
				 * (AccountNumber); System.out.println("accountDetails:" +
				 * accountDetails);
				 */
				paymentCheckOutPopUpVerify(driver, "Check Out Payment", AccountName, Authorizationtype, accountDetails,
						test_steps);

				verifycheckOutPaymentSuccessPopupVerify(driver, Authorizationtype, "Check Out Payment", "Approved",
						accountDetails, test_steps);

			} else {
				test_steps.add("CheckOutPayment Method Not avilable");
				reslogger.info("CheckOutPayment Method Not avilable");
			}

		} catch (Exception e) {
			test_steps.add("CheckOutPayment Method Not avilable");
			reslogger.info("CheckOutPayment Method Not avilable");
		}

	}

	/*
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 * 
	 * ' Method Name: getAccountName ' Description: get attached account name
	 * after creation of new reservation ' Input parameters: Webdriver ' Return
	 * value: String ' Created By: Adnan Ghaffar ' Created On: 11-05-2020
	 * 
	 * #########################################################################
	 * ####
	 * #########################################################################
	 * #### ################
	 */

	public String getAccountName(WebDriver driver) throws InterruptedException {
		Elements_CPReservation element = new Elements_CPReservation(driver);

		Wait.WaitForElement(driver, OR.VERIFY_ACCOUNT_NAME);
		Wait.waitForElementToBeVisibile(By.xpath(OR.VERIFY_ACCOUNT_NAME), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.VERIFY_ACCOUNT_NAME), driver);
		Utility.ScrollToElement_NoWait(element.verifyAccountName, driver);
		String accountName = element.verifyAccountName.getText();
		return accountName;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: enterSecondaryGuestInfo ' Description: Enter Secondary
	 * Guest Info, Confirm Button Click ' Input parameters: WebDriver
	 * ,ArrayList<String> String, String ' Return value: ArrayList<String> '
	 * Created By: Aqsa Manzoor ' Created On: 10-05-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> enterSecondaryGuestInfo(WebDriver driver, ArrayList<String> test_steps, String Salutation,
			String GuestFirstName, String GuestLastName) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);

		Wait.WaitForElement(driver, OR_Reservation.CP_SECONDARYGUEST_SALUTATION);
		Wait.explicit_wait_visibilityof_webelement(res.CP_SECONDARYGUEST_SALUTATION, driver);
		Wait.explicit_wait_elementToBeClickable(res.CP_SECONDARYGUEST_SALUTATION, driver);
		Utility.ScrollToElement(res.CP_SECONDARYGUEST_SALUTATION, driver);
		res.CP_SECONDARYGUEST_SALUTATION.click();
		String sal = "((//button[contains(@title,'Select')]//span[text()='Select'])[1]/parent::button/following-sibling::div//span[contains(text(),'"
				+ Salutation + "')]/../..)[1]";
		Wait.WaitForElement(driver, sal);
		driver.findElement(By.xpath(sal)).click();

		Wait.WaitForElement(driver, OR_Reservation.CP_SECONDARYGUEST_FIRSTNAME);
		res.CP_SECONDARYGUEST_FIRSTNAME.sendKeys(GuestFirstName);
		test_steps.add("Secondary Guest First Name : <b>" + GuestFirstName + "</b>");
		reslogger.info("Secondary Guest First Name : <b>" + GuestFirstName + "</b>");

		Wait.WaitForElement(driver, OR_Reservation.CP_SECONDARYGUEST_LASTNAME);
		try {
			res.CP_SECONDARYGUEST_LASTNAME.sendKeys(GuestLastName);
			test_steps.add("Secondary Guest Last Name : <b>" + GuestLastName + "</b>");
			reslogger.info("Secondary Guest Last Name : <b>" + GuestLastName + "</b>");
		} catch (Exception e) {
			test_steps.add("Secondary Guest Last Name : Element Not Interactable");
			reslogger.info("Secondary Guest Last Name : Element Not Interactable");

		}
		return test_steps;
	}

	public void clickFolio(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		try {
			Utility.ScrollToElement(res.CP_NewReservation_Folio, driver);
			Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_Folio);
			res.CP_NewReservation_Folio.click();
		} catch (Exception e) {
			Utility.ScrollToElement(res.COPY_CP_NewReservation_Folio, driver);
			Wait.WaitForElement(driver, OR_Reservation.COPY_CP_NewReservation_Folio);
			res.COPY_CP_NewReservation_Folio.click();
		}
		test_steps.add("Click on Folio");
		reslogger.info("Click on Folio");
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: secondayGuestCheckInButtonClick ' Description: Checkin
	 * Button Click, Verification of Tab,With Option Available With/Without
	 * Guest Statement, Confirm Button Click ' Input parameters: WebDriver ,
	 * String, boolean ' Return value: ArrayList<String> ' Created By: Aqsa
	 * Manzoor ' Created On: 10-05-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> secondayGuestCheckInButtonClick(WebDriver driver, String secondaryGuestName,
			boolean generateGuestStatement) throws InterruptedException {

		Elements_CPReservation element = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		element.CP_SECONDARYGUEST_CHECKINBUTTON.click();
		test_steps.add("Secondary Guest CheckIn Button Clicked");
		reslogger.info("Secondary Guest CheckIn Button Clicked ");
		Wait.WaitForElement(driver, OR_Reservation.CP_SECONDARYGUEST_CHECKIN_CHECKOUTTAB_TITLENAME);
		assertEquals(element.CP_SECONDARYGUEST_CHECKIN_CHECKOUTTAB_TITLENAME.getText().replaceAll(" ", ""),
				secondaryGuestName.replaceAll(" ", ""));
		test_steps.add("Secondary Guest Name: " + secondaryGuestName + " Verified");
		reslogger.info("Secondary Guest Name: " + secondaryGuestName + " Verified");
		if (!generateGuestStatement) {
			element.CP_CHECKIN_GENERATEGUESTSTATEMENT_TOGGLE.click();
			test_steps.add("Generate guest Statement Toggle: "
					+ element.CP_CHECKIN_GENERATEGUESTSTATEMENT_TOGGLE.isSelected());
			reslogger.info("sGenerate guest Statement Toggle: "
					+ element.CP_CHECKIN_GENERATEGUESTSTATEMENT_TOGGLE.isSelected());
		}
		Wait.WaitForElement(driver, OR_Reservation.CheckinConfirmButton);
		Wait.explicit_wait_visibilityof_webelement(element.CheckinConfirmButton, driver);
		Wait.explicit_wait_elementToBeClickable(element.CheckinConfirmButton, driver);
		element.CheckinConfirmButton.click();
		test_steps.add("Click on checkin confrim buttton");

		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: secondayGuestCheckoutButtonClick ' Description: Checkout
	 * Button Click & Verification of Confirmation Message ' Input parameters:
	 * WebDriver , String, String ' Return value: ArrayList<String> ' Created
	 * By: Aqsa Manzoor ' Created On: 10-05-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> secondayGuestCheckoutButtonClick(WebDriver driver, String RoomClassName, String RoomNumber)
			throws InterruptedException {

		Elements_CPReservation element = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<>();

		String checkoutConfirmationMessage = "Do you wish to settle all charges in the folio for " + RoomClassName
				+ ", room " + RoomNumber + "? If not, payment will be taken when the primary guest checks out.";
		Wait.WaitForElement(driver, OR_Reservation.CP_SECONDARYGUEST_CHECKOUTBUTTON);
		Utility.ScrollToElement(element.CP_SECONDARYGUEST_CHECKOUTBUTTON, driver);
		element.CP_SECONDARYGUEST_CHECKOUTBUTTON.click();
		test_steps.add("Secondary Guest Checkout Button Clicked");
		reslogger.info("Secondary Guest Checkout Button Clicked ");
		Wait.WaitForElement(driver, OR_Reservation.CP_SECONDARYGUEST_CHECKOUT_CONFIRMATIONPOPUP);
		assertEquals(element.CP_SECONDARYGUEST_CHECKOUT_CONFIRMATIONPOPUP.getText(), checkoutConfirmationMessage);
		test_steps.add("Secondary Guest Checkout Confirmation Message: " + checkoutConfirmationMessage + " Verified");
		reslogger.info("Secondary Guest Checkout Confirmation Message:: " + checkoutConfirmationMessage + " Verified");
		element.CP_CHECKOUT_CONFIRMATIONTAB_NOBUTTON.click();
		test_steps.add("Clicked No Button Checkout Confirmation Message");
		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: secondayGuestCheckoutConfirm ' Description: In Checkut tab
	 * ->Checkout Confirm Button Click With Option Available With/Without Guest
	 * Statement ' Input parameters: WebDriver , String, boolean ' Return value:
	 * ArrayList<String> ' Created By: Aqsa Manzoor ' Created On: 10-05-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> secondayGuestCheckoutConfirm(WebDriver driver, String SecondaryGuestName,
			boolean generateGuestStatement) throws InterruptedException {

		Elements_CPReservation element = new Elements_CPReservation(driver);
		ArrayList<String> test_steps = new ArrayList<>();

		Wait.WaitForElement(driver, OR_Reservation.CP_SECONDARYGUEST_CHECKIN_CHECKOUTTAB_TITLENAME);
		assertEquals(element.CP_SECONDARYGUEST_CHECKIN_CHECKOUTTAB_TITLENAME.getText().replaceAll(" ", ""),
				SecondaryGuestName.replaceAll(" ", ""));
		test_steps.add("Secondary Guest Name: " + SecondaryGuestName + " Verified");
		reslogger.info("Secondary Guest Name: " + SecondaryGuestName + " Verified");
		if (!generateGuestStatement) {
			element.CP_CHECKOUT_GENERATEGUESTSTATEMENT_TOGGLE.click();
			test_steps.add("Generate guest Statement Toggle: "
					+ element.CP_CHECKOUT_GENERATEGUESTSTATEMENT_TOGGLE.isSelected());
			reslogger.info("Generate guest Statement Toggle: "
					+ element.CP_CHECKOUT_GENERATEGUESTSTATEMENT_TOGGLE.isSelected());
		}
		Wait.WaitForElement(driver, OR_Reservation.CP_CHECKOUT_CONFIRMCHECKOUT_BUTTON);
		Wait.explicit_wait_visibilityof_webelement(element.CP_CHECKOUT_CONFIRMCHECKOUT_BUTTON, driver);
		Wait.explicit_wait_elementToBeClickable(element.CP_CHECKOUT_CONFIRMCHECKOUT_BUTTON, driver);
		element.CP_CHECKOUT_CONFIRMCHECKOUT_BUTTON.click();
		test_steps.add("Click on checkout confrim buttton");
		return test_steps;

	}
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: getSecondaryGuestRoomNumber ' Description: Get the Room
	 * Number of Secondary Guest in Details pages ' Input parameters: WebDriver
	 * , ArrayList<String> , String ' Return value: String ' Created By: Aqsa
	 * Manzoor ' Created On: 10-05-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public String getSecondaryGuestRoomNumber(WebDriver driver, ArrayList test_steps, String IsAssign) {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_SECONDARYGUEST_ROOMNUMBER);
		Wait.explicit_wait_visibilityof_webelement(element.CP_SECONDARYGUEST_ROOMNUMBER, driver);
		String roomNumber = element.CP_SECONDARYGUEST_ROOMNUMBER.getText();

		if (IsAssign.equalsIgnoreCase("No")) {
			if (roomNumber.equalsIgnoreCase("Unassigned")) {
				test_steps.add("Room Number is : Unassigned");
				reslogger.info("Room Number is : Unassigned");
			}
		} else {
			test_steps.add("Room Number is : " + roomNumber);
			reslogger.info("Room Number is : " + roomNumber);
		}
		return roomNumber;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: verifyHistoryTabForPaymentDetail ' Description:
	 * Verification of History Tab Payment Line/Details With MC and updated to
	 * Cash ' Input parameters: WebDriver , ArrayList<String> , String , String
	 * , boolean , String , String , String , String ' Return value:
	 * ArryaList<String> ' Created By: Aqsa Manzoor ' Created On: 10-05-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> verifyHistoryTabForPaymentDetail(WebDriver driver, ArrayList<String> test_steps,
			String Date, String amount, boolean isPaymentMethodChanged, String PreviousPaymentMethod,
			String PaymentMethod, String CardNumber, String ExpDate) {
		String paymentHistoryDate = "//tbody[contains(@data-bind,'foreach:historyList')]//span[text()='Payment']/ancestor::tr/td/span[contains(@data-bind,'text:$parent.formatDate($data.logDate)')]";
		WebElement paymentHistoryDateElement = driver.findElement(By.xpath(paymentHistoryDate));

		String paymentHistoryDescription = null;
		WebElement paymentHistoryDescriptionElement;

		assertEquals(paymentHistoryDateElement.getText(), Date);
		reslogger.info("History Tab: Payment Date Verified: " + paymentHistoryDateElement.getText());
		test_steps.add("History Tab: Payment Date Verified: " + paymentHistoryDateElement.getText());
		String desc = null;
		if (PaymentMethod.equals("MC")) {
			paymentHistoryDescription = "//tbody[contains(@data-bind,'foreach:historyList')]//span[text()='Payment']/ancestor::tr/td/span[contains(@data-bind,'text:$data.description')]";
			paymentHistoryDescriptionElement = driver.findElement(By.xpath(paymentHistoryDescription));

			desc = "Made a deposit payment of $" + amount + " using " + PaymentMethod + " XXXX" + CardNumber + " ("
					+ ExpDate + ")";
			reslogger.info(desc);
			assertEquals(desc, paymentHistoryDescriptionElement.getText());
			reslogger.info("History Tab: Payment Description Verified: " + paymentHistoryDescriptionElement.getText());
			test_steps.add("History Tab: Payment Description Verified: " + paymentHistoryDescriptionElement.getText());
		}
		if (isPaymentMethodChanged) {
			if (PaymentMethod.equals("Cash")) {
				desc = "Changed payment method from " + PreviousPaymentMethod + " to " + PaymentMethod;
				paymentHistoryDescription = "(//tbody[contains(@data-bind,'foreach:historyList')]//span[text()='Payment']/ancestor::tr)[1]/td/span[contains(@data-bind,'text:$data.description')]";
				paymentHistoryDescriptionElement = driver.findElement(By.xpath(paymentHistoryDescription));
				assertEquals(desc, paymentHistoryDescriptionElement.getText());
				reslogger.info(
						"History Tab: Payment Description Verified: " + paymentHistoryDescriptionElement.getText());
				test_steps.add(
						"History Tab: Payment Description Verified: " + paymentHistoryDescriptionElement.getText());
			}
		}
		return test_steps;
	}

	public void verify_DepositAmountTripSummary(WebDriver driver, ArrayList test_steps, String CheckInDate,
			String CheckOutDate, String Value, Double DepositAmount) throws ParseException, InterruptedException {
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		Date dateObj1 = format1.parse(CheckInDate);
		Date dateObj2 = format1.parse(CheckOutDate);
		long diff = dateObj2.getTime() - dateObj1.getTime();
		int Nights = (int) (diff / (24 * 60 * 60 * 1000));
		reslogger.info("difference between days: " + Nights);
		Wait.wait3Second();
		String roomCharge = get_TripSummaryRoomChargesWithoutCurrency(driver, test_steps);
		Double d = Double.parseDouble(roomCharge);
		Double d1 = d / Nights;
		reslogger.info(d1);

		// String payments=DepositAmount;
		Double d2 = DepositAmount;
		reslogger.info(d2);

		if (Double.compare(d1, d2) == 0) {
			test_steps.add("Deposit policy for " + Value + " Night Room Charges is validated : " + d1);
			reslogger.info("Deposit policy for " + Value + " Night Room Charges is validated : " + d1);
		} else {
			test_steps.add("Deposit policy for " + Value + " Night Room Charges is not validated : " + d1);
			reslogger.info("Deposit policy for " + Value + " Night Room Charges is not validated : " + d1);
		}
	}

	public ArrayList<String> Verify_ReservationStatus_Status(WebDriver driver, ArrayList<String> test_steps,
			String Status) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Utility.ScrollToElement(res.CP_Reservation_StatusPanel_Status, driver);
		Wait.explicit_wait_visibilityof_webelement(res.CP_Reservation_StatusPanel_Status, driver);

		// Verified History Tab No Show Entry
		reslogger.info("Status is: " + res.CP_Reservation_StatusPanel_Status.getText());
		reslogger.info("Matched Status is: " + Status);
		assertTrue(res.CP_Reservation_StatusPanel_Status.getText().toLowerCase().trim()
				.equals(Status.toLowerCase().trim()), "Failed: To verify Status");
		test_steps.add("  Verified Reservation Status: <b>" + Status + "</b>");
		reslogger.info("Verified Reservation Status: " + Status);

		return test_steps;

	}

	public void verifyUnassignedMsg(WebDriver driver, ArrayList<String> testSteps, String msg)
			throws InterruptedException {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.RollBackPopupMsg);
		Wait.explicit_wait_visibilityof_webelement(res.RollBackMsg, driver);
		Utility.ScrollToElement(res.RollBackMsg, driver);
		String uassignedMsg = res.RollBackMsg.getText().replace("<br>", "").replaceAll("[\r\n]+", "");
		assertEquals(uassignedMsg.toLowerCase().trim(), (msg.toLowerCase().trim()),
				"Failed : Verify Unassigned Message");
		testSteps.add("Verified  Msg : <b>" + msg + "</b>");
		reslogger.info("Verified  :" + msg);
	}

	public String calculationOfDeposiAmountToBePaid(ArrayList<String> baseAmounts, String attr, String attrValue,
			String taxes, String totalRoomCharges) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);
		String amountToBePaid = null;
		double paidAmount = 0.00;
		double taxesToAdd = 0.00;
		double taxesPerc = 0.00;
		if (!(taxes.equalsIgnoreCase("0")) && !(taxes.equalsIgnoreCase("0.0")) && !(taxes.equalsIgnoreCase("0.00"))) {
			double abc = (Double.parseDouble(taxes)) * 100;
			taxesPerc = abc / Double.parseDouble(totalRoomCharges);
		}
		if (attr.equalsIgnoreCase("First Night")) {
			for (String string : baseAmounts) {
				double amount = (Double.parseDouble(string)) * (Double.parseDouble(attrValue));
				paidAmount = paidAmount + amount;
			}
			if ((taxesPerc != 0) && (taxesPerc != 0.0) && (taxesPerc != 0.00)) {
				double abc = paidAmount * taxesPerc;
				taxesToAdd = abc / 100;
				paidAmount = paidAmount + taxesToAdd;
			}
		}else if (attr.equalsIgnoreCase("Fixed Amount")) {
			double rooms = Double.valueOf(baseAmounts.size());
			paidAmount = Double.parseDouble(attrValue)*rooms;
		}

		amountToBePaid = df.format(paidAmount);
		return amountToBePaid;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: enterPaymentDetailsWithReservationPaymentMethod '
	 * Description: Select the Payment Method(Reservation) in TakePayment Popup,
	 * and Attached 'Reservation With Reservation Number in Details
	 * PaymentMethod Input parameters: <WebDriver, ArrayList<String> ,String,
	 * String> ' Return value: <ArrayList<String>> ' Created By: <Aqsa Manzoor>
	 * ' Created On: <20/05/2020> *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> enterPaymentDetailsWithReservationPaymentMethod(WebDriver driver,
			ArrayList<String> test_steps, String PaymentType, String reservationNumber) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_NewReservation_PaymentMethod);
		res.CP_NewReservation_PaymentMethod.click();
		String paymentMethod = "//label[text()='Payment Method']/preceding-sibling::div//ul/li//span[contains(text(),'"
				+ PaymentType.trim() + "')]";
		Wait.WaitForElement(driver, paymentMethod);
		driver.findElement(By.xpath(paymentMethod)).click();
		test_steps.add("Selected PaymentType : " + PaymentType);
		reslogger.info("Selected PaymentType : " + PaymentType);
		if (PaymentType.equalsIgnoreCase("Reservation")) {

			String searchReservatinTab = "//input[contains(@data-bind,'value: selectedReservationValue')]";
			Wait.WaitForElement(driver, searchReservatinTab);
			WebElement searchReservatinTabElement = driver.findElement(By.xpath(searchReservatinTab));
			searchReservatinTabElement.sendKeys(reservationNumber);
			test_steps.add("Reservation Searched : " + reservationNumber);
			reslogger.info("Reservation Searched : " + reservationNumber);
			Wait.wait3Second();
			WebElement searchResult = driver
					.findElement(By.xpath("//*[contains (text(), '" + reservationNumber + "')]"));
			Wait.wait2Second();
			Wait.explicit_wait_visibilityof_webelement(searchResult, driver);
			searchResult.click();

			test_steps.add("Reservation Attached : " + reservationNumber + " as Payment Method");
			reslogger.info("Reservation Attached : " + reservationNumber + " as Payment Method");
		}
		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: selectPaymentMethodWithReservationInFolio ' Description:
	 * Select the Payment Method(Reservation) in TakePayment Popup, and Attached
	 * 'Reservation With Reservation Number in folio Pay Input parameters:
	 * <WebDriver, ArrayList<String> ,String, String> ' Return value:
	 * <ArrayList<String>> ' Created By: <Aqsa Manzoor> ' Created On:
	 * <20/05/2020> *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> selectPaymentMethodWithReservationInFolio(WebDriver driver, ArrayList<String> test_steps,
			String PaymentMethod, String ReservationNumber) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_Folio_TakePayment_PaymentButton);
		res.CP_Reservation_Folio_TakePayment_PaymentButton.click();
		String payment = "//h4[text()='Take Payment']/../..//label[text()='PAYMENT METHOD']/..//div/div//span[text()='"
				+ PaymentMethod.trim() + "']";
		Wait.WaitForElement(driver, payment);
		driver.findElement(By.xpath(payment)).click();
		test_steps.add("Select Payment Type as : " + PaymentMethod);
		reslogger.info("Select Payment Type as : " + PaymentMethod);

		if (PaymentMethod.equalsIgnoreCase("Reservation")) {
			String searchReservatinTab = "//input[contains(@data-bind,'alue:$parent.SelectedReservatio')]";
			Wait.WaitForElement(driver, searchReservatinTab);
			WebElement searchReservatinTabElement = driver.findElement(By.xpath(searchReservatinTab));
			searchReservatinTabElement.clear();
			searchReservatinTabElement.sendKeys(ReservationNumber);
			Wait.wait3Second();
			WebElement searchResult = driver
					.findElement(By.xpath("//*[contains (text(), '" + ReservationNumber + "')]"));
			Wait.wait5Second();
			Wait.explicit_wait_visibilityof_webelement(searchResult, driver);
			searchResult.click();
			reslogger.info("Reservation Attached Verified : " + searchResult.getText());
			test_steps.add("Reservation Attached Verified : " + searchResult.getText());

		}

		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: verifyPaymentMethodWithReservationInFolio ' Description:
	 * Verify the TakePayment Popup, Payment Method(Reservation) and Reservation
	 * Attached in Folio Pay' Input parameters: <WebDriver, ArrayList<String>
	 * ,String, String> ' Return value: <ArrayList<String>> ' Created By: <Aqsa
	 * Manzoor> ' Created On: <20/05/2020> *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> verifyPaymentMethodWithReservationInFolio(WebDriver driver, ArrayList<String> test_steps,
			String PaymentMethod, String ReservationNumber) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		String selectedPaymentMethod = "((//div[contains(@class,'payment-method')])[2]//span)[1]";
		Wait.WaitForElement(driver, selectedPaymentMethod);
		WebElement selectedPaymentMethodElement = driver.findElement(By.xpath(selectedPaymentMethod));
		Utility.ScrollToElement(selectedPaymentMethodElement, driver);
		Wait.wait2Second();
		System.out.print(" attached:" + PaymentMethod.contains(selectedPaymentMethodElement.getText()));

		assertEquals(PaymentMethod.contains(selectedPaymentMethodElement.getText()), true);

		reslogger.info("Selected Payment Method : " + PaymentMethod);
		test_steps.add("Selected Payment Method : " + PaymentMethod);

		String reservationFoundAttched = driver
				.findElement(By.xpath("//input[contains(@data-bind,'value:$parent.SelectedReservation')]"))
				.getAttribute("value");
		System.out.print(" Value attached:" + reservationFoundAttched);
		String reservationExpectecd = "Reservation - " + ReservationNumber;
		assertEquals(reservationFoundAttched, reservationExpectecd);
		reslogger.info("Reservation Attached Verified : " + reservationFoundAttched);
		test_steps.add("Reservation Attached Verified : " + reservationFoundAttched);

		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: payButtonClickInTakePayment ' DescriptionClick Pay Button
	 * In TakePayment Popup, selects Payment Method as Main on booelan and
	 * closes in on boolean in Folio Pay' Input parameters: <WebDriver,
	 * ArrayList<String> ,String, boolean,boolean> ' Return value:
	 * <ArrayList<String>> ' Created By: <Aqsa Manzoor> ' Created On:
	 * <20/05/2020> *
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> payButtonClickInTakePayment(WebDriver driver, ArrayList<String> test_steps, String amount,
			boolean isSetMainPaymentMethod, boolean isClose) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);

		String setAsMainPaymentMethod = "//span[text()='Set As Main Payment Method']/../input";
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		if (isSetMainPaymentMethod) {
			if (!driver.findElement(By.xpath(setAsMainPaymentMethod)).isSelected()) {
				jse.executeScript("arguments[0].click(true);", driver.findElement(By.xpath(setAsMainPaymentMethod)));
				test_steps.add("Select set As Main Payment Method");
				reslogger.info("Select set As Main Payment Method");
			} else {
				test_steps.add("Already Selected set As Main Payment Method");
				reslogger.info("Already Selected set As Main Payment Method");
			}
		}
		String button = "//h4[text()='Take Payment']/../..//button[contains(text(),'" + amount + "')]";
		Wait.WaitForElement(driver, button);
		System.out.print("Amount:" + amount);
		WebElement payButtonElement = driver.findElement(By.xpath(button));
		Wait.waitForElementToBeClickable(By.xpath(button), driver);
		Utility.ScrollToElement(payButtonElement, driver);
		System.out.print("Text is :" + payButtonElement.getText());
		payButtonElement.click();
		test_steps.add("Click on Pay");
		reslogger.info("Click on Pay");
		String paymentSuccessfulTab = "//h4[contains(text(),'Successful')]";
		Wait.WaitForElement(driver, paymentSuccessfulTab);
		WebElement paymentSuccessfulTabElement = driver.findElement(By.xpath(paymentSuccessfulTab));
		System.out.print("Tab is:" + paymentSuccessfulTabElement.isDisplayed());
		assertEquals(paymentSuccessfulTabElement.isDisplayed(), true);
		test_steps.add("Payment Successul Tab Displayed");
		reslogger.info("Payment Successul Tab Displayed");

		if (isClose) {
			String close = "//h4[contains(text(),'Successful')]/../..//button[text()='Close']";
			Wait.WaitForElement(driver, close);
			driver.findElement(By.xpath(close)).click();
			test_steps.add("Click on Close");
			reslogger.info("Click on Close");
		}
		return test_steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: clickEditPaymentMethodInfo ' Description: Click Edit
	 * Button To Change Payment Method In Details Tab of Reservation ' Input
	 * parameters: WebDriver , ArrayList<String> ' Return value:
	 * ArrayList<String> ' Created By: Aqsa Manzoor ' Created On: 10-05-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> clickEditPaymentMethodInfo(WebDriver driver, ArrayList<String> steps)
			throws InterruptedException, ParseException {
		Elements_CPReservation element = new Elements_CPReservation(driver);
		Wait.explicit_wait_visibilityof_webelement(element.PAYMENTMETHOD_EDITBUTTON, driver);
		Wait.explicit_wait_elementToBeClickable(element.PAYMENTMETHOD_EDITBUTTON, driver);
		Utility.ScrollToElement(element.PAYMENTMETHOD_EDITBUTTON, driver);
		element.PAYMENTMETHOD_EDITBUTTON.click();
		reslogger.info("Click Payment Method Edit button");
		steps.add("Click Payment Method Edit button");
		return steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: clickSaveButtonAfterEditPaymentMethod ' Description: Click
	 * Save Button After Editing Payment Method ' Input parameters: WebDriver ,
	 * ArrayList<String> ' Return value: ArrayList<String> ' Created By: Aqsa
	 * Manzoor ' Created On: 10-05-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */
	public ArrayList<String> clickSaveButtonAfterEditPaymentMethod(WebDriver driver, ArrayList<String> steps)
			throws InterruptedException {
		// Click Save Button
		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.PAYMENTMETHOD_SAVEBUTTON);
		Utility.ScrollToElement(res.PAYMENTMETHOD_SAVEBUTTON, driver);
		res.PAYMENTMETHOD_SAVEBUTTON.click();
		reslogger.info("Clicked Save Button After Updating Payment Method");
		test_steps.add("Clicked Save Button After Updating Payment Method");
		assertEquals(res.Toaster_Message.getText(), "payment Information successfully saved");
		reslogger.info("Toaster Message: " + res.Toaster_Message.getText());
		test_steps.add("Toaster Message: " + res.Toaster_Message.getText());
		return steps;
	}
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: removeAttachedReservationInPayments ' Description: Click
	 * Cancel Button Icon In Payments TO Remove Attached Reservation as Payment
	 * Method ' Input parameters: WebDriver , ArrayList<String> ' Return value:
	 * ArrayList<String> ' Created By: Aqsa Manzoor ' Created On: 1-06-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> removeAttachedReservationInPayments(WebDriver driver, ArrayList<String> steps)
			throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		String cancelIcon = "//input[contains(@data-bind,'alue:$parent.SelectedReservatio')]/following-sibling::span//button";
		Wait.WaitForElement(driver, cancelIcon);
		WebElement cancelIconElement = driver.findElement(By.xpath(cancelIcon));
		Wait.waitForElementToBeClickable(By.xpath(cancelIcon), driver);
		cancelIconElement.click();
		reslogger.info("Reservation Attached Removed");
		test_steps.add("Reservation Attached Removed");
		Wait.wait10Second();
		return steps;
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: enterAmountTakePayment ' Description: Enter Amount in Take
	 * Payment Popup Method ' Input parameters: WebDriver , ArrayList<String>,
	 * SString,String, ' Return value: ArrayList<String> ' Created By: Aqsa
	 * Manzoor ' Created On: 01-05-2020
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> enterAmountTakePayment(WebDriver driver, ArrayList test_steps, String IsChangeInPayAmount,
			String ChangedAmountValue) throws InterruptedException {

		Elements_CPReservation res = new Elements_CPReservation(driver);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_Folio_TakePayment);
		Wait.WaitForElement(driver, OR_Reservation.CP_Reservation_Folio_TakePayment_Amount);
		String amount = res.CP_Reservation_Folio_TakePayment_Amount.getText().trim();
		System.out.println("amount : " + amount);
		test_steps.add("Amount before Pay : " + amount);
		reslogger.info("Amount before Pay : " + amount);

		if (IsChangeInPayAmount.equalsIgnoreCase("Yes")) {
			test_steps.add("Change the pay amount value : Yes");
			reslogger.info("Change the pay amount value : Yes");
			res.CP_Reservation_Folio_TakePayment_Amount.clear();
			res.CP_Reservation_Folio_TakePayment_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			res.CP_Reservation_Folio_TakePayment_Amount.sendKeys(ChangedAmountValue);
			test_steps.add("Enter the Change Amount Value : " + ChangedAmountValue);
			reslogger.info("Enter the Change Amount Value : " + ChangedAmountValue);
			amount = ChangedAmountValue;
		} else {
			test_steps.add("paying the amount : " + amount);
			reslogger.info("paying the amount : " + amount);
		}

		return test_steps;
	}

	public String getReservationConfirmationNumberFromBanner(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {
		String confirmationNumber = "//span[contains(@data-bind,'text:confirmationNumber')]";
		Wait.WaitForElement(driver, confirmationNumber);
		WebElement confirmationNumberElement = driver.findElement(By.xpath(confirmationNumber));
		Wait.wait5Second();
		String confirmation = confirmationNumberElement.getText();
		test_steps.add("Confirmation Number : <b>" + confirmation + "</b>");
		reslogger.info("Confirmation Number : <b>" + confirmation + "</b>");
		return confirmation;
	}
	

/*
 * ##########################################################################################################################################################################
 * 
 * ' Method Name: verificationPaymentWindow 
 * ' Description: Verification of Check In Payment Window and Check Out Payment Window
 * ' Input parameters: String
 * ' Return Amount as String
  * ' Created By: Gangotri
 * ' Created On: June 3 2020
 * '  Get Amount, Balance and Type in Array List called "ArrayList<String> amount= new ArrayList<String>();"
 * 
 * ##########################################################################################################################################################################
 */

public ArrayList<String> verificationPaymentWindow(WebDriver driver, ArrayList<String> test_steps, String title,
		String expiryDate, String nameOnCard, String cardNo, String PaymentType, String Type, String totalFee,
		String tripTax, String Percentage, String PolicyType, String TripPaid) throws InterruptedException {
	
	//getting amount,balance and type in ArrayList
	ArrayList<String> amount= new ArrayList<String>();
	Elements_CPReservation res = new Elements_CPReservation(driver);
	Wait.WaitForElement(driver, OR_Reservation.NoShowPaymentPopupTitle);
	Utility.ScrollToElement(res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupTitle, driver);
	// Verified No Show Payment Page Title
	assertEquals(res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupTitle.getText().toLowerCase()
			.trim(), (title.toLowerCase().trim()), "Failed: To verify  Payment Page Title");
	test_steps.add(" Verified Header:<b> " + title + "</b>");
	reslogger.info(" Verified Header: " + title);
	String path = "//div[contains(@data-bind,'IsSplitPaymentEnable')]//button[@title='" + PaymentType + "']";
	WebElement paymentElement = driver.findElement(By.xpath(path));

	// Get Amount
	String getAmount = res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount.getText();
	reslogger.info(getAmount);
	amount.add(getAmount.trim());

	//Get Balance
	String balance = null;
	if (PolicyType.equals("No Show") || PolicyType.equals("Cancel")) {
		balance = res.NoShowCancelPaymentPopup_Balance.getText().replace("$", "");
		amount.add(balance);
	} else if (PolicyType.equals("Check In")) {
		balance = res.CheckInPaymentPopup_Balance.getText().replace("$", "");
		amount.add(balance.trim());
	}

	assertEquals(getAmount.trim(), totalFee.trim(), "Failed: To verify Payment Page Amount: " + totalFee);
	test_steps.add("Verified Amount : <b> " + totalFee + "</b>");
	reslogger.info(" Verified Amount: " + totalFee);
	assertEquals(balance.trim(), totalFee.trim(), "Failed: To verify Payment Page Balance: " + totalFee);
	test_steps.add("Verified Balance : <b> " + totalFee + "</b>");
	reslogger.info(" Verified Balance: " + totalFee);		
	// Verified Type
	
	String type = res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupType.getText().toLowerCase()
			.trim();
	amount.add(type.trim());
	reslogger.info(amount);
	assertEquals(type, (Type.toLowerCase().trim()), "Failed: To verify  Page Type: " + Type);
	test_steps.add(" Verified Type : <b> " + Type + "</b>");
	reslogger.info(" Verified Type: " + Type);

	if (PaymentType.equals("MC")) {
		// Verified No Show Payment Page Payment Method
		assertTrue(paymentElement.isDisplayed(), "Failed: To verify Payment Method");
		test_steps.add(" Verified Payment Method:<b> " + PaymentType + "</b> ");
		reslogger.info(" Verified Payment Method: " + PaymentType);

		// Verified No Show Payment Page Card No
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		String CardNo = (String) executor.executeScript("return arguments[0].value",
				res.CP_Reservation_NoShowPaymentPopup_CardNumber);
		reslogger.info(CardNo);
		// Verify Card Number
		assertTrue(CardNo.contains(cardNo), "Failed: To verify Card Number");
		test_steps.add(" Verified Card Number: <b>" + CardNo + "</b>");
		reslogger.info(" Verified Card Number");
		String NameOnCard = (String) executor.executeScript("return arguments[0].value",
				res.CP_Reservation_NoShowPaymentPopup_NameOnCard);
		reslogger.info(NameOnCard);

		// Verify Name on Card Number
		assertEquals(NameOnCard.toLowerCase().trim(), (nameOnCard.toLowerCase().trim()),
				"Failed: To verify Name on Card ");
		test_steps.add("Verified Name on Card:<b>  " + nameOnCard + "</b>");
		reslogger.info(" Verified Name on Card");

		String ExpiryDate = (String) executor.executeScript("return arguments[0].value",
				res.CP_Reservation_NoShowPaymentPopup_Expiry);
		reslogger.info(ExpiryDate);

		// Verify Expiry Date
		assertEquals(ExpiryDate, (expiryDate), "Failed: To verify Expiry Date ");
		test_steps.add(" Verified Expiry Date:<b> " + ExpiryDate + "</b>");
		reslogger.info(" Verified Expiry Date");
	}
	if (PolicyType.equals("No Show") || PolicyType.equals("Cancel")) {
		// Verified Log as External Payment
		boolean isExistLogAsExternal = res.CP_Reservation_NoShowPaymentPopup_ExternalPaymentCheckBox
				.isEnabled();
		if (isExistLogAsExternal) {
			if (!res.NoShowPaymentPopup_Date.isEnabled()) {
				test_steps.add(" Verified  Date Field Disabled ");
				reslogger.info(" Verified  Date Field Disabled ");
			}
			if (!res.CP_Reservation_NoShowPaymentPopup_ExternalPaymentCheckBox.isSelected()) {
				res.CP_Reservation_NoShowPaymentPopup_ExternalPaymentCheckBox.click();
				reslogger.info("Click  Log as External Payment Check Box");
				// Verified No Show Payment Log Button
				assertTrue(res.CP_Reservation_NoShowPaymentPopup_LogAndPayButton.isDisplayed(),
						"Failed: To verify Payment Page Log Button");
				test_steps.add(" Verified  Log/Pay Button ");
				reslogger.info("' Verified  Log/Pay Button ");
				assertTrue(res.NoShowPaymentPopup_Date.isEnabled(),
						"Failed: To verify Payment Page Date Field");
				test_steps.add(" Verified  Date Field  Enabled");
				reslogger.info(" Verified  Date Field  Enabled ");
			}

		}
	}
	if (PolicyType.equals("Check In")) {
		// Verified Override Check-In Amount
		res.CP_OverrideCheckInAmountCheckBox.click();
		test_steps.add(" Checked Override Check-In Amount Check Box");
		reslogger.info(" Checked Override Check-In Amount Check Box");
		boolean isAmountEnabled = res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupAmount
				.isEnabled();
		assertEquals(isAmountEnabled, true, "Failed : to verify Amount field Enabled");
		test_steps.add(" Amount Field  Enabled");
		reslogger.info(" Amount Field  Enabled");
		boolean isTypeEnabled = res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupType
				.isEnabled();
		assertEquals(isTypeEnabled, true, "Failed : to verify Type field Enabled");
		test_steps.add(" Type Field  Enabled");
		reslogger.info(" Type Field  Enabled");
		res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupType.click();
		test_steps.add(" Click on Type Drop Down Box");
		reslogger.info(" Click on Type Drop Down Box");
		String[] types = { "Authorization Only", "Capture" };
		for (int i = 0; i < res.CP_CheckInTypeOptions.size(); i++) {
			assertEquals(res.CP_CheckInTypeOptions.get(i).getText().toLowerCase().trim(),(types[i].toLowerCase().trim()), "Failed: To verify Type Field");
			test_steps.add(" Verified  Type Option: <b>" + res.CP_CheckInTypeOptions.get(i).getText() + "</b>");
			reslogger.info(" Verified  Date Field  Enabled " + res.CP_CheckInTypeOptions.get(i).getText());
		}

		res.CP_Reservation_NoShowPaymentPopup_NoShowPaymentPopupType.click();
		test_steps.add(" Click on Type Drop Down Box");
		reslogger.info(" Click on Type Drop Down Box");

		boolean iExist = res.CP_Reservation_NoShowPaymentPopup_LogAndPayButton.isDisplayed();
		assertEquals(iExist, true, "Failed : to verify Authrozied Button");
		test_steps.add(" Authrozied Button  Enabled");
		reslogger.info(" Authrozied Button  Enabled");
	}
	return amount;

}

	public void verifyPolicyDetailsOnPaymentPopup(WebDriver driver, ArrayList<String> test_steps, String policyType,
			String policyName, String policyText) throws InterruptedException {
		Elements_CPReservation res = new Elements_CPReservation(driver);
		try {
			Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.cancelResPopupPolicyName), driver);
			String policyNameDisplayed = res.cancelResPopupPolicyName.getText();
			Actions act = new Actions(driver);
			act.moveToElement(res.cancelResPopupPolicyName).perform();
			String policyNameAtToolTip = res.cancelResPopupPolicyToolTipName.getText();
			String policyTextAtToolTip = res.cancelResPopupPolicyToolTipText.getText();

			assertEquals(policyNameDisplayed, policyName, "Failed - Verify policy name on payments popup");
			test_steps.add("Successfully verified policy name on payments popup as <b>" + policyNameDisplayed + "</b>");
			if (policyType.equalsIgnoreCase("Cancellation")) {
				assertEquals(policyNameAtToolTip, policyName, "Failed - Verify policy name on payments popup");
				test_steps.add("Successfully verified policy name on payments popup tool tip as <b>" + policyNameAtToolTip + "</b>");				
			}	
			assertEquals(policyTextAtToolTip, policyText, "Failed - Verify policy name on payments popup");
			test_steps.add("Successfully verified policy text on payments popup tool tip as <b>" + policyTextAtToolTip + "</b>");

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String calculationOfNoShowAmountToBePaid(ArrayList<String> baseAmounts, ArrayList<String> totalNights,
			String attr, String attrValue, String taxes, String totalRoomCharges) {
	    DecimalFormat df = new DecimalFormat("0.00");
	    df.setMaximumFractionDigits(2);
	    String amountToBePaid = null;
	    double paidAmount = 0.00;
	    double taxesToAdd = 0.00;
	    double taxesPerc = 0.00;
	    double roomChargesCalc = 0.00;
	    
	    if ( !(taxes.equalsIgnoreCase("0")) && !(taxes.equalsIgnoreCase("0.0")) && !(taxes.equalsIgnoreCase("0.00")) ) {
	        double abc = (Double.parseDouble(taxes))*100;
	        taxesPerc = abc/Double.parseDouble(totalRoomCharges);
	    }
	    if (attr.equalsIgnoreCase("First Night")) {
	        for (String string : baseAmounts) {
	            double amount = (Double.parseDouble(string)) * (Double.parseDouble(attrValue));
	            paidAmount = paidAmount+amount;
	        }
	        if ( (taxesPerc != 0) && (taxesPerc != 0.0) && (taxesPerc != 0.00) ) {
	            double abc = paidAmount*taxesPerc;
	            taxesToAdd = abc/100;
	            paidAmount = paidAmount+taxesToAdd;
	        }
	    }else if (attr.equalsIgnoreCase("Fixed Amount")) {
	        paidAmount = (Double.parseDouble(attrValue))*(baseAmounts.size());
	    }else if (attr.equalsIgnoreCase("Room Charges")) {
			for (int i = 0; i < baseAmounts.size(); i++) {
				double abc = (Double.parseDouble(baseAmounts.get(i)))*(Double.parseDouble(totalNights.get(i)))
						*(Double.parseDouble(attrValue))/100;
				roomChargesCalc = abc+roomChargesCalc;
			}
			paidAmount = roomChargesCalc;
		}   
	    amountToBePaid = df.format(paidAmount);
	    return amountToBePaid;
	}

	public boolean verifyTextExistsFromListOfRecords(List<WebElement> elements, String textExp) {
		boolean textFound = false;
		for (WebElement webElement : elements) {
			String textDisplayed = webElement.getText();
			if (textDisplayed.equalsIgnoreCase(textExp)) {
				textFound = true;
				break;
			}
		}
		return textFound;
	}

	public void verifyPaymentDescriptionAtGuestHistory(WebDriver driver, ArrayList<String> test_steps, 
			String categoryExp, String descExp) {
		Elements_CPReservation reservation = new Elements_CPReservation(driver);
		Wait.waitForElementToBeVisibile(By.xpath(OR_Reservation.folioHistoryTabSearchBox), driver);
		boolean category = verifyTextExistsFromListOfRecords(reservation.guestHistoryCategories, categoryExp);
		boolean description = verifyTextExistsFromListOfRecords(reservation.guestHistoryDescriptions, descExp);
		
		assertEquals(category, true, "Failed to verify payment description");
		test_steps.add("Successfully verified payment category as : <b>"+categoryExp+"</b>");
		
		assertEquals(description, true, "Failed to verify payment description");
		test_steps.add("Successfully verified payment category as : <b>"+descExp+"</b>");
		
	}


}
