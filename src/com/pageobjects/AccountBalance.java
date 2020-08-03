package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class AccountBalance {
	public static Logger accLogger = Logger.getLogger("AccountBalance");

	public ArrayList<String> select_LedgerType(WebDriver driver, String ledgerType) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<>();
		String selectLedTpPath = "//*[@id='MainContent_ddlLedgerType']";
		Wait.explicit_wait_xpath(selectLedTpPath, driver);
		Select selectLedgerType = new Select(driver.findElement(By.xpath(selectLedTpPath)));
		selectLedgerType.selectByVisibleText(ledgerType);

		assertTrue(selectLedgerType.getFirstSelectedOption().getText().equals(ledgerType), "Failed : Type Not Changed");
		accLogger.info("Selected Ledger Type : " + ledgerType);
		test_steps.add("Selected Ledger Type : " + ledgerType);
		return test_steps;
	}

	public ArrayList<String> reservationTypes(WebDriver driver, boolean isCurrent, boolean isPast, boolean isFuture) {
		ArrayList<String> test_steps = new ArrayList<>();

		WebElement current = driver.findElement(By.id("MainContent_chklstReservationType_0"));
		WebElement past = driver.findElement(By.id("MainContent_chklstReservationType_1"));
		WebElement future = driver.findElement(By.id("MainContent_chklstReservationType_2"));

		if (isCurrent) {
			if (!current.isSelected()) {
				current.click();
			}
		} else {
			if (current.isSelected()) {
				current.click();
			}
		}

		assertEquals(isCurrent, current.isSelected());
		accLogger.info("Reservation Type Current : " + current.isSelected());
		test_steps.add("Reservation Type Current : " + current.isSelected());

		if (isPast) {
			if (!past.isSelected()) {
				past.click();
			}
		} else {
			if (past.isSelected()) {
				past.click();
			}
		}

		assertEquals(isPast, past.isSelected());
		accLogger.info("Reservation Type Past : " + past.isSelected());
		test_steps.add("Reservation Type Past : " + past.isSelected());

		if (isFuture) {
			if (!future.isSelected()) {
				future.click();
			}
		} else {
			if (future.isSelected()) {
				future.click();
			}
		}

		assertEquals(isFuture, future.isSelected());
		accLogger.info("Reservation Type Future : " + future.isSelected());
		test_steps.add("Reservation Type Future : " + future.isSelected());

		return test_steps;
	}

	public ArrayList<String> includePendingItems(WebDriver driver, boolean isIncludePendingItems) {
		ArrayList<String> test_steps = new ArrayList<>();
		WebElement incPenItems = driver.findElement(By.id("MainContent_chkAccIncludePendingItems"));
		
		if (isIncludePendingItems) {
			if (!incPenItems.isSelected()) {
				incPenItems.click();
			}
		} else {
			if (incPenItems.isSelected()) {
				incPenItems.click();
			}
		}

		assertEquals(isIncludePendingItems, incPenItems.isSelected());
		accLogger.info("Include Pending Items : " + incPenItems.isSelected());
		test_steps.add("Include Pending Items : " + incPenItems.isSelected());
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
		Wait.wait10Second();
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
