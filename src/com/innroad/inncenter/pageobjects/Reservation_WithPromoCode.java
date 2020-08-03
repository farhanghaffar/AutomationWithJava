package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.innroad.inncenter.interfaces.IReservation_WithPromoCode;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Reservation;
import com.innroad.inncenter.webelements.Elements_Reservation_PromoCode;

public class Reservation_WithPromoCode implements IReservation_WithPromoCode {

	public static Logger resPromoLogger = Logger.getLogger("Reservation_WithPromoCode");

	public void clickTapeChart(WebDriver driver) throws InterruptedException {
		Elements_Reservation_PromoCode ResPromoCode = new Elements_Reservation_PromoCode(driver);
		Wait.wait3Second();
		ResPromoCode.Tape_Chart.click();
		resPromoLogger.info(" Clicked on Tapechart menu ");
		Wait.wait3Second();
	}

	public void checkAvailabilityWithPromoCode(WebDriver driver, String TapeChartAdults, String TapeChartChildrens,
			String PromoCode) throws InterruptedException {
		Elements_Reservation_PromoCode ResPromoCode = new Elements_Reservation_PromoCode(driver);
		ResPromoCode.Select_Arrival_Date.click();
		ResPromoCode.Click_Today.click();
		Wait.wait5Second();
		ResPromoCode.Enter_Adults_Tapehchart.sendKeys(TapeChartAdults);
		ResPromoCode.Enter_Children_Tapechart.sendKeys(TapeChartChildrens);
		// ResPromoCode.Select_Rack_Rate.click();
		// Wait.wait3Second();
		ResPromoCode.Enter_promoCode_Tapechart.sendKeys(PromoCode);
		ResPromoCode.Click_Search_TapeChart.click();
		Thread.sleep(10000);

	}

	public void click_availableRoomClass(WebDriver driver) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			WebElement availableCell = driver
					.findElement(By.xpath("(.//*[@id='bpjscontainer_52']//div[contains(@title,'Available')])[2]"));

			if (availableCell.getText().equals("Available")) {
				jse.executeScript("arguments[0].scrollIntoView(true);", availableCell);
				jse.executeScript("arguments[0].click();", availableCell);
				// driver.findElement(By.xpath("//div[@class='tapechartdatecell
				// Available RackRate']")).click();

				resPromoLogger.info(" System sucessfully clicked on Available reservation");
			}
		} catch (Exception e) {
			e.printStackTrace();

			try {
				WebElement availableCell1 = driver
						.findElement(By.xpath("(.//*[@id='bpjscontainer_52']//div[contains(@title,'Available')])[4]"));
				if (availableCell1.getText().equals("Available")) {
					jse.executeScript("arguments[0].scrollIntoView(true);", availableCell1);
					jse.executeScript("arguments[0].click();", availableCell1);
					// driver.findElement(By.xpath("//div[@class='tapechartdatecell
					// weekend Available RackRate']")).click();
					availableCell1.click();
					resPromoLogger.info(" System successfully clicked on the Available reservation");
					Thread.sleep(5000);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	public void click_availableRoom(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		Elements_Reservation_PromoCode ResPromoCode = new Elements_Reservation_PromoCode(driver);
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", ResPromoCode.clickAvailableRoom);
		Wait.wait2Second();
		jse.executeScript("window.scrollBy(0,-300)");
		ResPromoCode.clickAvailableRoom.click();
		Wait.wait15Second();
		boolean rulesBrokenPopUp = ResPromoCode.Rules_Broken_popup.isDisplayed();
		if (rulesBrokenPopUp == true) {
			ResPromoCode.Click_Book_icon_Tapechart.click();
			Wait.WaitForElement(driver, OR.New_Reservation_Tab);

		}

		Wait.WaitForElement(driver, OR.New_Reservation_Tab);
		assertTrue(ReservationPage.ReservationPage.isDisplayed(), "Reservation Page didn't open");

	}

	public void marketingInfo(WebDriver driver, String MarketSegment, String Referral, String Travel_Agent,
			String ExtReservation) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		new Select(ReservationPage.Reservation_market_Segment).selectByVisibleText(MarketSegment);
		new Select(ReservationPage.Reservation_Referral).selectByVisibleText(Referral);

		try {
			ReservationPage.Enter_Travel_Agent.sendKeys(Travel_Agent);
		} catch (Exception e) {

		}

		ReservationPage.Enter_Ext_Reservation.sendKeys(ExtReservation);
	}

	public void contactInformation(WebDriver driver, String saluation, String FirstName, String LastName,
			String Address, String Line1, String Line2, String Line3, String City, String Country, String State,
			String Postalcode, String Phonenumber, String alternativenumber, String Email, String Account,
			String IsTaxExempt, String TaxEmptext) {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);

		new Select(ReservationPage.Select_Saluation).selectByVisibleText(saluation);
		ReservationPage.Enter_First_Name.clear();
		ReservationPage.Enter_Last_Name.clear();
		ReservationPage.Enter_Address_Search.clear();
		ReservationPage.Enter_Line1.clear();
		ReservationPage.Enter_Line2.clear();
		ReservationPage.Enter_Line3.clear();
		ReservationPage.Enter_City.clear();
		ReservationPage.Enter_Postal_Code.clear();
		;
		ReservationPage.Enter_Phone_Number.clear();
		;
		ReservationPage.Enter_Alt_Phn_number.clear();
		;
		ReservationPage.Enter_email.clear();
		;
		ReservationPage.Enter_First_Name.sendKeys(FirstName);
		ReservationPage.Enter_Last_Name.sendKeys(LastName);
		ReservationPage.Enter_Address_Search.sendKeys(Address);
		ReservationPage.Enter_Line1.sendKeys(Line1);
		ReservationPage.Enter_Line2.sendKeys(Line2);
		ReservationPage.Enter_Line3.sendKeys(Line3);
		ReservationPage.Enter_City.sendKeys(City);
		new Select(ReservationPage.Select_Country).selectByVisibleText(Country);
		new Select(ReservationPage.Select_State).selectByVisibleText(State);
		ReservationPage.Enter_Postal_Code.sendKeys(Postalcode);
		ReservationPage.Enter_Phone_Number.sendKeys(Phonenumber);
		ReservationPage.Enter_Alt_Phn_number.sendKeys(alternativenumber);
		ReservationPage.Enter_email.sendKeys(Email);
		try {
			ReservationPage.Enter_Account.sendKeys(Account);
		} catch (Exception e) {

		}
		if (IsTaxExempt.equals("Yes")) {
			if (ReservationPage.Check_IsTaxExempt.isSelected()) {
				ReservationPage.Enter_TaxExemptId.sendKeys(TaxEmptext);
			} else {
				ReservationPage.Check_IsTaxExempt.click();
				ReservationPage.Enter_TaxExemptId.sendKeys(TaxEmptext);
			}
		}
	}

	public void billingInformation(WebDriver driver, String PaymentMethod, String AccountNumber, String ExpiryDate,
			String BillingNotes) {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		new Select(ReservationPage.Select_Payment_Method).selectByVisibleText(PaymentMethod);
		if (PaymentMethod.equalsIgnoreCase("MC") || PaymentMethod.equalsIgnoreCase("Amex")
				|| PaymentMethod.equalsIgnoreCase("Discover") || PaymentMethod.equalsIgnoreCase("Visa")) {
			ReservationPage.Enter_Account_Number.sendKeys(AccountNumber);
			ReservationPage.Enter_Exp_Card.sendKeys(ExpiryDate);
			ReservationPage.Enter_Billing_Note.sendKeys(BillingNotes);

		}
	}

	public void saveReservation(WebDriver driver) throws InterruptedException {

		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		double waittime = 0.12;
		long startTime = System.currentTimeMillis();
		ReservationPage.Click_Save_ReservationDetails.click();
		Wait.wait3Second();
		try {
			if (ReservationPage.Verify_Depos_policy.isDisplayed()) {
				ReservationPage.Click_Without_Deposit.click();
			}
		} catch (Exception ne) {

		}
		try {
			if (ReservationPage.Verify_Toaster_Container.isDisplayed()) {
				String getTotasterTitle_ReservationSucess = ReservationPage.Toaster_Title.getText();
				String getToastermessage_ReservationSucess = ReservationPage.Toaster_Message.getText();
				Assert.assertEquals(getTotasterTitle_ReservationSucess, "Reservation Saved");
				long endTime = System.currentTimeMillis();
				double totalTime = (endTime - startTime);
				resPromoLogger.info(totalTime + " in Millsecs");
				double TotalTimeinsecs = totalTime / 1000;
				double ActualTime = TotalTimeinsecs - waittime - 3;
				resPromoLogger.info(ActualTime + " in secs");
				if (getToastermessage_ReservationSucess.endsWith("has been saved successfully"))
					;

			}
		} catch (Exception e) {

		}
		Wait.wait10Second();
	}

	public void cancelReservation(WebDriver driver, String Reason) throws InterruptedException {
		Elements_Reservation_PromoCode ResPromoCode = new Elements_Reservation_PromoCode(driver);

		String selectedOption = new Select(ResPromoCode.get_Reservation_Status).getFirstSelectedOption().getText();
		resPromoLogger.info("selectedOption  " + selectedOption);

		// String currentWindow = driver.getWindowHandle();

		ResPromoCode.clickCancelButton.click();
		Wait.wait10Second();
		// resPromoLogger.info( " Cancelled the Reservation " );
		// Wait.wait10Second();
		// for(String winHandle : driver.getWindowHandles()){
		// driver.switchTo().window(winHandle);
		// }

		// ResPromoCode.enterReason.sendKeys(Reason);

		ResPromoCode.cancelReason.sendKeys(Reason);
		resPromoLogger.info(" Reason for reservation cancellation ");
		Wait.wait10Second();
		ResPromoCode.OkCancelReason.click();
		resPromoLogger.info(" Clicked on Ok button to cancel the Reservation");
		Wait.wait10Second();
		String selectedOption2 = new Select(ResPromoCode.get_Reservation_Status).getFirstSelectedOption().getText();
		resPromoLogger.info(" Reservation status after cancelling the reservation  " + selectedOption2);
		Thread.sleep(5000);
		ResPromoCode.closeReservation.click();
		resPromoLogger.info(" Closed the Reservation ");
		Thread.sleep(3000);

	}

	@Override
	public void clickTapechart(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub

	}

	public void VerifyPromoCode_Reservation(WebDriver driver, String PromoCode) throws InterruptedException {
		Elements_Reservation_PromoCode ResPromoCode = new Elements_Reservation_PromoCode(driver);
		String PromoCodeValue = ResPromoCode.Reservation_PromoCode.getText();
		assertTrue(PromoCodeValue.equals(PromoCode), "Promo Code didn't match");
	}

	public ArrayList<String> VerifyPromoCode_Reservation(WebDriver driver, String promocode,
			ArrayList<String> test_steps) throws InterruptedException {
		WebElement promoCodeValue = driver.findElement(By.xpath("(//p[contains(@data-bind,'text: PromoCode')])[2]"));
		System.out.println("Promo Code Value:" + promoCodeValue.getText());
		assertTrue(promoCodeValue.getText().equals(promocode), "Failed: promocode Missmatched");

		test_steps.add(" PromoCode verified in GuestInfo");
		return test_steps;

	}

}
