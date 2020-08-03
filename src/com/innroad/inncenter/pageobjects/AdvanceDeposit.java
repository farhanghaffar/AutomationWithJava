package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class AdvanceDeposit {

	public static Logger accLogger = Logger.getLogger("AdvanceDeposit");

	public ArrayList<String> setTrnDate(WebDriver driver, String to, String from) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		String goBtnPath = "//*[@id='MainContent_btnGenreport']";

		Wait.explicit_wait_xpath(goBtnPath, driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(goBtnPath)), driver);

		driver.findElement(By.xpath(goBtnPath)).click();
		WebElement fromDate = driver.findElement(By.id("MainContent_txtFromDate"));
		WebElement toDate = driver.findElement(By.id("MainContent_txtToDate"));

		Wait.explicit_wait_visibilityof_webelement(fromDate, driver);
		Wait.explicit_wait_visibilityof_webelement(toDate, driver);

		Wait.wait2Second();
		toDate.clear();
		toDate.sendKeys(to);
		accLogger.info("Transaction To Date Changed : " + to);
		test_steps.add("Transaction To Date Changed : " + to);
		Wait.wait2Second();
		fromDate.clear();
		fromDate.sendKeys(from);
		fromDate.click();
		accLogger.info("Transaction From Date Changed : " + from);
		test_steps.add("Transaction From Date Changed : " + from);

		return test_steps;
	}

	public ArrayList<String> changeReportType(WebDriver driver, String type) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		String path = "//*[@id='MainContent_rbtRptType']//label[.='" + type + "']/preceding-sibling::input";

		WebElement reportType = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(reportType, driver);
		reportType.click();

		assertTrue(reportType.isSelected(), "Failed: Report Type " + type + " is not Selected");
		accLogger.info("Report Type Changed : " + type);
		test_steps.add("Report Type Changed : " + type);
		return test_steps;
	}

	public ArrayList<String> changeUser(WebDriver driver, String user) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();

		String path = "//*[@id='MainContent_rblstUsers']//label[.='" + user + "']/preceding-sibling::input";

		WebElement User = driver.findElement(By.xpath(path));
		Utility.ScrollToElement(User, driver);
		User.click();

		assertTrue(User.isSelected(), "Failed USER : " + user + " is not Selected");
		accLogger.info("User Changed : " + user);
		test_steps.add("User Changed : " + user);
		return test_steps;
	}

	public ArrayList<String> click_GoButton(WebDriver driver) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		String goBtnPath = "//*[@id='MainContent_btnGenreport']";

		Wait.explicit_wait_xpath(goBtnPath, driver);
		Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(goBtnPath)), driver);

		driver.findElement(By.xpath(goBtnPath)).click();
		accLogger.info("GO Button Clicked");
		test_steps.add("GO Button Clicked");

		 try{
//			 Wait.explicit_wait_xpath("//*[@id='MainContent_lblErrorMessage']",
//					 driver);
			 assertFalse(driver.findElement(By.id("MainContent_lblErrorMessage")).isDisplayed(),
					 "Failed: Kindly Change Search Criteria");
		 }catch (Exception e) {
			 System.out.println("Searched Success");
		 }
		Wait.explicit_wait_xpath("//*[@id='MainContent_pnlRptViewer']", driver);
		System.out.println("visible");
		return test_steps;
	}

	public ArrayList<String> downloadPDF(WebDriver driver) throws InterruptedException {

		ArrayList<String> test_steps = new ArrayList<>();
		String framePath = "//*[@id='MainContent_ucRptViewer_ifrmAccountStatement']";
		Wait.explicit_wait_xpath(framePath, driver);
		Wait.wait3Second();
		driver.switchTo().frame("ifrmAccountStatement");
		assertTrue(driver.findElement(By.id("tdViewPDF")).isDisplayed(), "Report isn't Displayed");
		accLogger.info("PDF Report is Displayed");
		test_steps.add("PDF Report is Displayed");

		String dataPath = driver.findElement(By.xpath("//*[@id='viewPDF']")).getAttribute("data");
		driver.get(dataPath);

		accLogger.info("Pdf File Downloaded");
		test_steps.add("Pdf File Downloaded");
		Wait.wait5Second();

		return test_steps;
	}
}
