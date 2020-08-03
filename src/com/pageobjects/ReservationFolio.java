package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.innroad.inncenter.interfaces.INavigation;
import com.innroad.inncenter.interfaces.IReservationFolio;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_All_Payments;
import com.innroad.inncenter.webelements.Elements_Reservation;
import com.relevantcodes.extentreports.LogStatus;

public class ReservationFolio implements IReservationFolio {

	public String GetPaymentMethod;
	public String giftCardnumber;
	public static Logger resFolioLogger = Logger.getLogger("ReservationFolio");

	public void paymentMethod(WebDriver driver, String PaymentType, String CardName, String CCNumber, String CCExpiry,
			String CCVCode, String Authorizationtype, String ChangeAmount, String ChangeAmountValue, String traceData,ArrayList test_steps)
					throws InterruptedException {

		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationFolio.Click_Pay_Button, driver);
		Wait.explicit_wait_elementToBeClickable(ReservationFolio.Click_Pay_Button, driver);
		Utility.ScrollToElement(ReservationFolio.Click_Pay_Button, driver);
		ReservationFolio.Click_Pay_Button.click();
		resFolioLogger.info("Clicked on Pay button");
		TestPaymentPopup(driver, PaymentType, CardName, CCNumber, CCExpiry, CCVCode, Authorizationtype, ChangeAmount,
				ChangeAmountValue, traceData,test_steps);

	}

	public float TestPaymentPopup(WebDriver driver, String PaymentType, String CardName, String CCNumber,
			String CCExpiry, String CCVCode, String Authorizationtype, String ChangeAmount, String ChangeAmountValue,
			String traceData,ArrayList test_steps) throws InterruptedException {
		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
		Actions action = new Actions(driver);
		float processed_amount = 0;
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.explicit_wait_xpath(driver,OR.Verify_Payment_Details_poup);
		//Wait.wait10Second();

		if (PaymentType.equalsIgnoreCase("Cash")) {
			try {
				new Select(ReservationFolio.Select_Paymnet_Method).selectByVisibleText(PaymentType);
				resFolioLogger.info("Payment is processing with CASH mode");
				Wait.wait3Second();
				if (ChangeAmount.equalsIgnoreCase("Yes")) {
					// ReservationFolio.Change_Amount.clear();
					// Wait.wait3Second();
					// ReservationFolio.Change_Amount.sendKeys(ChangeAmountValue);
					ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);

				} else {
					resFolioLogger.info("Processed complete amount");
				}
				Wait.wait2Second();
				Wait.WaitForElement(driver, OR.Click_ADD_Button);
				ReservationFolio.Click_ADD_Button.click();
				resFolioLogger.info("Clicked on ADD button");
				Wait.explicit_wait_xpath(driver,OR.Verify_Guest_Ledger);
				Wait.wait3Second();
				try {
					GetPaymentMethod = ReservationFolio.Get_Payment_Method.getText();
					resFolioLogger.info("PaymentMethod: " + " " + GetPaymentMethod);
				} catch (Exception e) {
					e.printStackTrace();
					TestCore.test.log(LogStatus.FAIL, "Payment verification failed \n"  + e.getMessage() + 
							"\n\n <br> Attaching screenshot below : \n" + TestCore.test.addScreenCapture(Utility.captureScreenShot(TestCore.test.getTest().getName() + "_Payment_Verification" + Utility.getTimeStamp(), driver)));
					resFolioLogger.info("Payment verification failed \n");

				}
				try {
					if (GetPaymentMethod.equalsIgnoreCase(PaymentType)) {
						resFolioLogger.info("Paymnet is Successful");
					} else {
						resFolioLogger.info("Paymnet is Failed");
					}
				} catch (Exception e) {
					TestCore.test.log(LogStatus.FAIL, "Payment verification failed \n"  + e.getMessage() + 
							"\n\n <br> Attaching screenshot below : \n" + TestCore.test.addScreenCapture(Utility.captureScreenShot(TestCore.test.getTest().getName() + "_Payment_Verification" + Utility.getTimeStamp(), driver)));
					resFolioLogger.info("Payment verification failed \n");
					e.printStackTrace();
				}
				ReservationFolio.Click_Continue.click();
				resFolioLogger.info("Clicked on continue button of Payment popup");

				Wait.wait10Second();
				if(driver.findElements(By.xpath(OR.Click_Close_popUP)).size()>0){
					Wait.waitUntilPresenceOfElementLocated(driver,OR.Click_Close_popUP);

					if(driver.findElements(By.xpath(OR.Click_Close_popUP)).size()>1){
						driver.findElement(By.xpath(OR.Click_Confirm)).click();
						resFolioLogger.info("Clicked on Confirm button of Guest Statement Report");
						test_steps.add("Clicked on Confirm button of Guest Statement Report");
					}else{
						driver.findElement(By.xpath(OR.Click_Close_popUP)).click();
						resFolioLogger.info("Clicked on CLOSE button of Guest Statement Report");
						test_steps.add("Clicked on CLOSE button of Guest Statement Report");
					}
				}
				//Wait.wait15Second();
				Wait.WaitForElement(driver, OR.Verify_Line_item);
				Wait.explicit_wait_xpath(driver,OR.Verify_Line_item);
				String GetBalance = ReservationFolio.Verify_Balance_Zero.getText();
				//				resFolioLogger.info("Balance: " + " " + GetBalance);
				String RemoveCurreny[] = GetBalance.split(" ");
				resFolioLogger.info("Pending balance after payment: " + " " + RemoveCurreny[1]);

				if (ChangeAmount.equalsIgnoreCase("NO")) {
					if (RemoveCurreny[1].equals("0.00"))
						;
				} else {
					resFolioLogger.info("Selected Changed Value");
				}
			} catch (Exception e) {
				e.printStackTrace();
				TestCore.test.log(LogStatus.FAIL, "Exception occured while paying using CASH \n"  + e.getMessage() + 
						"\n\n <br> Attaching screenshot below : \n" + TestCore.test.addScreenCapture(Utility.captureScreenShot(TestCore.test.getTest().getName() + "_Payment_ByCash" + Utility.getTimeStamp(), driver)));
				resFolioLogger.info("Exception occured while paying using CASH \n");
				
			}
		} else if (PaymentType.equalsIgnoreCase("MC")) {
			try {

				new Select(ReservationFolio.Select_Paymnet_Method).selectByVisibleText(PaymentType);
				Wait.wait3Second();
				ReservationFolio.Click_Card_info.click();
				resFolioLogger.info("Clicked on card info");
				Wait.explicit_wait_xpath(driver,OR.Verify_payment_info_popup);
				Wait.wait3Second();
				ReservationFolio.Enter_Card_Name.sendKeys(CardName);
				ReservationFolio.Enter_Account_Number_Folio.sendKeys(CCNumber);
				ReservationFolio.Enter_CC_Expiry.sendKeys(CCExpiry);
				ReservationFolio.Enter_CVVCode.sendKeys(CCVCode);
				resFolioLogger.info("Entered Card Details");
				ReservationFolio.Click_OK.click();
				resFolioLogger.info("Clicked on OK button");
				Wait.wait10Second();
				Wait.WaitForElement(driver, OR.Select_Authorization_type);
				new Select(ReservationFolio.Select_Authorization_type).selectByVisibleText(Authorizationtype);
				resFolioLogger.info("Selected authorization type");
				if (ChangeAmount.equalsIgnoreCase("Yes")) {
					/*
					 * ReservationFolio.Change_Amount.clear();
					 * Wait.wait3Second();
					 * ReservationFolio.Change_Amount.sendKeys(ChangeAmountValue
					 * );
					 */
					ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
				} else {
					resFolioLogger.info("Processed complete amount");
				}
				Wait.wait25Second();
				Wait.WaitForElement(driver, OR.Click_Process);
				Wait.explicit_wait_elementToBeClickable(ReservationFolio.Click_Process, driver);
				Wait.explicit_wait_xpath( OR.Click_Process, driver);
				ReservationFolio.Click_Process.click();
				resFolioLogger.info("Clicked on Process Button");
				Wait.wait3Second();
				Wait.WaitForElement(driver, OR.Verify_MC_Grid);
				Wait.explicit_wait_xpath(driver,OR.Verify_MC_Grid);
				String GetPaymentMethod = ReservationFolio.GetMC_Payment_Method.getText();
				resFolioLogger.info("PaymentMethod: " + " " + GetPaymentMethod);
				if (GetPaymentMethod.equals(PaymentType)) {
					resFolioLogger.info("Paymnet is successful");
				} else {
					resFolioLogger.info("Paymnet is Failed");
				}
				ReservationFolio.Click_Continue.click();
				resFolioLogger.info("Clicked on continue button");
				Wait.wait3Second();
				Wait.explicit_wait_xpath(driver,OR.GetAddedLine_MC);
				String GetMCCard = ReservationFolio.GetAddedLine_MC.getText();
				resFolioLogger.info("Transaction Line Item: " + GetMCCard);
				if (GetMCCard.equalsIgnoreCase("Name: MC Account #: XXXX5454 Exp. Date: 08/21")) {
					resFolioLogger.info("Paymnet is successful");
				} else {
					resFolioLogger.info("Paymnet is Failed");
				}
				String GetBalance = ReservationFolio.Verify_Balance_Zero.getText();
				//				resFolioLogger.info("Balance: " + " " + GetBalance);
				String RemoveCurreny[] = GetBalance.split(" ");
				resFolioLogger.info("Pending balance after payment: " + " " + RemoveCurreny[1]);
				if (ChangeAmount.equalsIgnoreCase("No")) {
					if (RemoveCurreny[1].equals("0.00"))
						;
				} else {
					resFolioLogger.info("Selected Changed Value");
				}
			} catch (Exception e) {
				e.printStackTrace();
				test_steps.add("Exception occured while paying using MC \n"  + e.getMessage() + 
						"\n\n <br> Attaching screenshot below : \n" + TestCore.test.addScreenCapture(Utility.captureScreenShot(TestCore.test.getTest().getName() + "_Payment_ByMC" + Utility.getTimeStamp(), driver)));
				resFolioLogger.info("Exception occured while paying using MC \n");
				e.printStackTrace();
			}

		} else if (PaymentType.equalsIgnoreCase("Swipe")) {
			try {

				ReservationFolio.Click_Swipe_Icon.click();
				resFolioLogger.info("Clicked on swipe icon");
				Wait.explicit_wait_xpath(driver,OR.Verify_Swipe_Popup);
				ReservationFolio.Enter_Track_Data.sendKeys(traceData);

				if (ChangeAmount.equalsIgnoreCase("Yes")) {
					ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);

				} else {
					resFolioLogger.info("Processed complete amount");
				}
				Wait.WaitForElement(driver, OR.Select_Authorization_type);
				new Select(ReservationFolio.Select_Authorization_type).selectByVisibleText(Authorizationtype);
				Wait.wait25Second();
				Wait.WaitForElement(driver, OR.Click_Process);
				Wait.wait10Second();
				ReservationFolio.Click_Process.click();
				resFolioLogger.info("Clicked on Process Button");
				Wait.wait10Second();
				Wait.WaitForElement(driver, OR.Verify_MC_Grid);
				Wait.explicit_wait_xpath(driver,OR.Verify_MC_Grid);
				String GetPaymentMethod = ReservationFolio.GetMC_Payment_Method.getText();
				resFolioLogger.info("PaymentMethod: " + " " + GetPaymentMethod);
				if (GetPaymentMethod.equals("MC")) {
					resFolioLogger.info("Paymnet is successful");
				} else {
					resFolioLogger.info("Paymnet is failed");
				}
				ReservationFolio.Click_Continue.click();
				resFolioLogger.info("Clicked on continue button");

				Wait.wait10Second();
				Wait.waitUntilPresenceOfElementLocated(driver,OR.Click_Close_popUP);

				if(driver.findElements(By.xpath(OR.Click_Close_popUP)).size()>1){
					driver.findElement(By.xpath(OR.Click_Confirm)).click();
					resFolioLogger.info("Clicked on Confirm button of Guest Statement Report");
					test_steps.add("Clicked on Confirm button of Guest Statement Report");
				}else{
					driver.findElement(By.xpath(OR.Click_Close_popUP)).click();
					resFolioLogger.info("Clicked on CLOSE button of Guest Statement Report");
					test_steps.add("Clicked on CLOSE button of Guest Statement Report");
				}





				Wait.wait3Second();
				String GetBalance = ReservationFolio.Verify_Balance_Zero.getText();
				//				resFolioLogger.info("Balance: " + " " + GetBalance);
				String RemoveCurreny[] = GetBalance.split(" ");
				resFolioLogger.info("Balance: " + RemoveCurreny[1]);
				if (ChangeAmount.equalsIgnoreCase("NO")) {
					if (RemoveCurreny[1].equals("0.00"))
						;
				} else {
					resFolioLogger.info("Selected Changed Value");
				}
			} catch (Exception e) {
				TestCore.test.log(LogStatus.FAIL, "Exception occured while paying using swipe \n"  + e.getMessage() + 
						"\n\n <br> Attaching screenshot below : \n" + TestCore.test.addScreenCapture(Utility.captureScreenShot(TestCore.test.getTest().getName() + "_Payment_BySwipe" + Utility.getTimeStamp(), driver)));
				resFolioLogger.error("Exception occured while paying using swipe \n");
				e.printStackTrace();
			}
		}
		return processed_amount;
	}

	
	
	public float TestPaymentPopup(WebDriver driver, String PaymentType, String CardName, String CCNumber,
			String CCExpiry, String CCVCode, String Authorizationtype, String ChangeAmount, String ChangeAmountValue,
			String traceData) throws InterruptedException {
		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
		Actions action = new Actions(driver);
		float processed_amount = 0;
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.wait5Second();

		Elements_All_Payments elements_All_Payments = new Elements_All_Payments(driver);
		Wait.explicit_wait_visibilityof_webelement(elements_All_Payments.Reservation_PaymentPopup, driver);
		Wait.wait5Second();

		if (PaymentType.equalsIgnoreCase("Cash")) {
			try {
				new Select(ReservationFolio.Select_Paymnet_Method).selectByVisibleText(PaymentType);
				resFolioLogger.info("Payment is processing with CASH mode");
				Wait.wait3Second();
				if (ChangeAmount.equalsIgnoreCase("Yes")) {
					// ReservationFolio.Change_Amount.clear();
					// Wait.wait3Second();
					// ReservationFolio.Change_Amount.sendKeys(ChangeAmountValue);
					ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);

				} else {
					resFolioLogger.info("Processed complete amount");
				}
				ReservationFolio.Click_ADD_Button.click();
				resFolioLogger.info("Clicked on ADD button");
				Wait.explicit_wait_xpath(OR.Verify_Guest_Ledger, driver);
				Wait.wait3Second();
				try {
					GetPaymentMethod = ReservationFolio.Get_Payment_Method.getText();
					resFolioLogger.info("PaymentMethod: " + " " + GetPaymentMethod);
				} catch (Exception e) {
					TestCore.test.log(LogStatus.FAIL,
							"Payment verification failed \n" + e.getMessage()
									+ "\n\n <br> Attaching screenshot below : \n"
									+ TestCore.test.addScreenCapture(
											Utility.captureScreenShot(TestCore.test.getTest().getName()
													+ "_Payment_Verification" + Utility.getTimeStamp(), driver)));
					resFolioLogger.info("Payment verification failed \n");
					e.printStackTrace();
				}
				try {
					if (GetPaymentMethod.equalsIgnoreCase(PaymentType)) {
						resFolioLogger.info("Paymnet is Successful");
					} else {
						resFolioLogger.info("Paymnet is Failed");
					}
				} catch (Exception e) {
					TestCore.test.log(LogStatus.FAIL,
							"Payment verification failed \n" + e.getMessage()
									+ "\n\n <br> Attaching screenshot below : \n"
									+ TestCore.test.addScreenCapture(
											Utility.captureScreenShot(TestCore.test.getTest().getName()
													+ "_Payment_Verification" + Utility.getTimeStamp(), driver)));
					resFolioLogger.info("Payment verification failed \n");
					e.printStackTrace();
				}

				Wait.wait1Second();
				Utility.ScrollToElement(ReservationFolio.Processed_Amount_In_Paymentdetails_Popup, driver);
				Wait.wait2Second();
				String Processed_Amount = ReservationFolio.Processed_Amount_In_Paymentdetails_Popup.getText();
				resFolioLogger.info(Processed_Amount + " -- " + Processed_Amount);
				String RemoveCurreny[] = Processed_Amount.split(" ");
				processed_amount = Float.parseFloat(RemoveCurreny[1]);
				resFolioLogger.info(processed_amount);

				ReservationFolio.Click_Continue.click();
				resFolioLogger.info("Clicked on continue button of Payment popup");

				if (Reservation.checkinpolicy == true) {
					resFolioLogger.info("This Reservation has Checkin policy ");
					Wait.explicit_wait_visibilityof_webelement(ReservationFolio.Click_on_confirm, driver);
					ReservationFolio.Click_on_confirm.click();
					resFolioLogger
							.info("Clicked on Confirm button of Guest Registration Form in ReservationFolio.java");
					Wait.wait3Second();
				}

				try {
					if (Reservation.flag == true) {
						Wait.explicit_wait_10sec(ReservationPage.Policy_Comparision_PopUp, driver);
						if (ReservationPage.Policy_Comparision_PopUp.isDisplayed()) {
							Wait.explicit_wait_elementToBeClickable(ReservationPage.Select_Continue_with_OriginalPolicy,
									driver);
							action.moveToElement(ReservationPage.Select_Continue_with_OriginalPolicy).click().build()
									.perform();
							Wait.explicit_wait_elementToBeClickable(
									ReservationPage.Policy_Comparision_PopUp_Continue_Btn, driver);
							action.moveToElement(ReservationPage.Policy_Comparision_PopUp_Continue_Btn).click().build()
									.perform();
							Wait.wait2Second();
						}
					}
				} catch (Exception e) {
					resFolioLogger.info("No conflicts with Policies");

				}
				Wait.wait3Second();
				Wait.explicit_wait_xpath(OR.Verify_Line_item, driver);
				String GetBalance = ReservationFolio.Verify_Balance_Zero.getText();
				// resFolioLogger.info("Balance: " + " " + GetBalance);
				RemoveCurreny = GetBalance.split(" ");
				resFolioLogger.info("Pending balance after payment: " + " " + RemoveCurreny[1]);

				if (ChangeAmount.equalsIgnoreCase("NO")) {
					if (RemoveCurreny[1].equals("0.00"))
						;
				} else {
					resFolioLogger.info("Selected Changed Value");
				}

			} catch (Exception e) {
				TestCore.test.log(LogStatus.FAIL,
						"Exception occured while paying using CASH \n" + e.getMessage()
								+ "\n\n <br> Attaching screenshot below : \n"
								+ TestCore.test.addScreenCapture(Utility.captureScreenShot(
										TestCore.test.getTest().getName() + "_Payment_ByCash" + Utility.getTimeStamp(),
										driver)));
				resFolioLogger.info("Exception occured while paying using CASH \n");
				e.printStackTrace();
			}
		} else if (PaymentType.equalsIgnoreCase("MC")) {
			try {

				new Select(ReservationFolio.Select_Paymnet_Method).selectByVisibleText(PaymentType);
				Wait.wait3Second();
				ReservationFolio.Click_Card_info.click();
				resFolioLogger.info("Clicked on card info");
				Wait.explicit_wait_xpath(OR.Verify_payment_info_popup, driver);
				Wait.wait3Second();
				ReservationFolio.Enter_Card_Name.sendKeys(CardName);
				ReservationFolio.Enter_Account_Number_Folio.sendKeys(CCNumber);
				ReservationFolio.Enter_CC_Expiry.sendKeys(CCExpiry);
				ReservationFolio.Enter_CVVCode.sendKeys(CCVCode);
				resFolioLogger.info("Entered Card Details");
				ReservationFolio.Click_OK.click();
				resFolioLogger.info("Clicked on OK button");
				Wait.wait10Second();
				new Select(ReservationFolio.Select_Authorization_type).selectByVisibleText(Authorizationtype);
				resFolioLogger.info("Selected authorization type");
				if (ChangeAmount.equalsIgnoreCase("Yes")) {
					/*
					 * ReservationFolio.Change_Amount.clear();
					 * Wait.wait3Second();
					 * ReservationFolio.Change_Amount.sendKeys(ChangeAmountValue
					 * );
					 */
					ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
				} else {
					resFolioLogger.info("Processing the amount displayed");
				}
				ReservationFolio.Click_Process.click();
				resFolioLogger.info("Clicked on Process Button");
				Thread.sleep(10000);
				Wait.wait3Second();
				Wait.explicit_wait_xpath(OR.Verify_MC_Grid, driver);
				String GetPaymentMethod = ReservationFolio.GetMC_Payment_Method.getText();
				resFolioLogger.info("PaymentMethod: " + " " + GetPaymentMethod);
				if (GetPaymentMethod.equals(PaymentType)) {
					resFolioLogger.info("Paymnet is successful");
				} else {
					resFolioLogger.info("Paymnet is Failed");
				}

				Wait.wait1Second();
				Utility.ScrollToElement(ReservationFolio.Processed_Amount_In_Paymentdetails_Popup, driver);
				Wait.wait2Second();
				String Processed_Amount = ReservationFolio.Processed_Amount_In_Paymentdetails_Popup.getText();
				resFolioLogger.info("Processed_Amount " + Processed_Amount + " -- " + Processed_Amount);
				String RemoveCurreny[] = Processed_Amount.split(" ");
				processed_amount = Float.parseFloat(RemoveCurreny[1]);
				resFolioLogger.info("Processed_Amount is " + processed_amount);

				ReservationFolio.Click_Continue.click();
				resFolioLogger.info("Clicked on continue button");
				Wait.waitForElementToBeGone(driver, ReservationFolio.Click_Continue, 100);
				Wait.wait3Second();
				Wait.explicit_wait_xpath(OR.GetAddedLineMC, driver);
				String GetMCCard = ReservationFolio.GetAddedLineMC.getText();
				resFolioLogger.info("Transaction Line Item: " + GetMCCard);
				if (GetMCCard.equalsIgnoreCase("Name: MC Account #: XXXX5454 Exp. Date: 08/21")) {
					resFolioLogger.info("Payment is successful");
				} else {
					resFolioLogger.info("Payment is Failed");
				}
				if (Reservation.checkinpolicy == true) {
					resFolioLogger.info("This Reservation has Checkin policy ");
					Wait.explicit_wait_visibilityof_webelement(ReservationFolio.Click_on_confirm, driver);
					ReservationFolio.Click_on_confirm.click();
					resFolioLogger
							.info("Clicked on Confirm button of Guest Registration Form in ReservationFolio.java");
					Wait.wait3Second();
				}
				try {
					if (Reservation.flag == true) {
						Wait.explicit_wait_10sec(ReservationPage.Policy_Comparision_PopUp, driver);
						if (ReservationPage.Policy_Comparision_PopUp.isDisplayed()) {
							Wait.explicit_wait_elementToBeClickable(ReservationPage.Select_Continue_with_OriginalPolicy,
									driver);
							action.moveToElement(ReservationPage.Select_Continue_with_OriginalPolicy).click().build()
									.perform();
							Wait.explicit_wait_elementToBeClickable(
									ReservationPage.Policy_Comparision_PopUp_Continue_Btn, driver);
							action.moveToElement(ReservationPage.Policy_Comparision_PopUp_Continue_Btn).click().build()
									.perform();
							Wait.wait2Second();
						}
					}
				} catch (Exception e) {
					resFolioLogger.info("No conflicts with Policies");

				}
				Wait.explicit_wait_visibilityof_webelement(ReservationFolio.Verify_Balance_Zero, driver);
				String GetBalance = ReservationFolio.Verify_Balance_Zero.getText();
				resFolioLogger.info("Balance after payment: " + " " + GetBalance);
				RemoveCurreny = GetBalance.split(" ");
				resFolioLogger.info("Pending balance after payment: " + " " + RemoveCurreny[1]);
				if (ChangeAmount.equalsIgnoreCase("No")) {
					if (RemoveCurreny[1].equals("0.00"))
						;
				} else {
					resFolioLogger.info("Selected Changed Value");
				}

			} catch (Exception e) {
				System.out.println("Text Name:" + TestCore.test.getTest().getName());
				TestCore.test.log(LogStatus.FAIL,
						"Exception occured while paying using MC \n" + e.getMessage()
								+ "\n\n <br> Attaching screenshot below : \n"
								+ TestCore.test.addScreenCapture(Utility.captureScreenShot(
										TestCore.test.getTest().getName() + "_Payment_ByMC" + Utility.getTimeStamp(),
										driver)));
				resFolioLogger.info("Exception occured while paying using MC \n");
				e.printStackTrace();
			}

		} else if (PaymentType.equalsIgnoreCase("Swipe")) {
			try {

				ReservationFolio.Click_Swipe_Icon.click();
				resFolioLogger.info("Clicked on swipe icon");
				Wait.explicit_wait_xpath(OR.Verify_Swipe_Popup, driver);
				ReservationFolio.Enter_Track_Data.sendKeys(traceData);

				if (ChangeAmount.equalsIgnoreCase("Yes")) {
					ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);

				} else {
					resFolioLogger.info("Processed complete amount");
				}
				Wait.wait3Second();
				new Select(ReservationFolio.Select_Authorization_type).selectByVisibleText(Authorizationtype);
				Wait.wait3Second();
				ReservationFolio.Click_Process.click();
				resFolioLogger.info("Clicked on Process Button");
				Wait.wait3Second();
				Wait.explicit_wait_xpath(OR.Verify_MC_Grid, driver);
				String GetPaymentMethod = ReservationFolio.GetMC_Payment_Method.getText();
				resFolioLogger.info("PaymentMethod: " + " " + GetPaymentMethod);
				if (GetPaymentMethod.equals("MC")) {
					resFolioLogger.info("Paymnet is successful");
				} else {
					resFolioLogger.info("Paymnet is failed");
				}

				Wait.wait1Second();
				Utility.ScrollToElement(ReservationFolio.Processed_Amount_In_Paymentdetails_Popup, driver);
				Wait.wait2Second();
				String Processed_Amount = ReservationFolio.Processed_Amount_In_Paymentdetails_Popup.getText();
				resFolioLogger.info(Processed_Amount + " -- " + Processed_Amount);
				String RemoveCurreny[] = Processed_Amount.split(" ");
				processed_amount = Float.parseFloat(RemoveCurreny[1]);
				resFolioLogger.info(processed_amount);

				ReservationFolio.Click_Continue.click();
				resFolioLogger.info("Clicked on continue button");
				Wait.wait3Second();
				Wait.explicit_wait_xpath(OR.GetAddedLine_MC, driver);
				String GetMCCard = ReservationFolio.GetAddedLine_MC.getText();
				resFolioLogger.info("CardType: " + " " + GetMCCard);
				if (GetMCCard.equals("Name: TEST CARD/MC Account #: XXXX5454 Exp. Date: 12/23")) {
					resFolioLogger.info("Paymnet is successful");
				} else {
					resFolioLogger.info("Paymnet is failed");
				}

				Wait.wait5Second();

				try {
					driver.findElement(By.xpath(OR.Click_Close)).click();
					resFolioLogger.info("Clicked on CLOSE button of Guest Statement Report");
					Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message, driver);
				} catch (Exception e) {
					Reservation res = new Reservation();
					ArrayList<String> test_steps = new ArrayList<>();
					res.analyzeLog(driver, test_steps);
				}
				Wait.wait3Second();
				String GetBalance = ReservationFolio.Verify_Balance_Zero.getText();
				// resFolioLogger.info("Balance: " + " " + GetBalance);
				RemoveCurreny = GetBalance.split(" ");
				resFolioLogger.info("Balance: " + RemoveCurreny[1]);
				if (ChangeAmount.equalsIgnoreCase("NO")) {
					if (RemoveCurreny[1].equals("0.00"))
						;
				} else {
					resFolioLogger.info("Selected Changed Value");
				}

			} catch (Exception e) {
				TestCore.test.log(LogStatus.FAIL,
						"Exception occured while paying using swipe \n" + e.getMessage()
								+ "\n\n <br> Attaching screenshot below : \n"
								+ TestCore.test.addScreenCapture(Utility.captureScreenShot(
										TestCore.test.getTest().getName() + "_Payment_BySwipe" + Utility.getTimeStamp(),
										driver)));
				resFolioLogger.error("Exception occured while paying using swipe \n");
				e.printStackTrace();
			}

		}
		return processed_amount;
	}

	public float Checkin_TestPaymentPopup(WebDriver driver, String PaymentType, String CardName, String CCNumber,
			String CCExpiry, String CCVCode, String Authorizationtype, String ChangeAmount, String ChangeAmountValue,
			String traceData) throws InterruptedException {
		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
		Actions action = new Actions(driver);
		float processed_amount = 0;
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.wait5Second();
		Wait.explicit_wait_xpath(OR.Verify_Payment_Details_poup, driver);
		Wait.wait5Second();
		if (PaymentType.equalsIgnoreCase("Cash")) {
			try {
				new Select(ReservationFolio.Select_Paymnet_Method).selectByVisibleText(PaymentType);
				resFolioLogger.info("Payment is processing with CASH mode");
				Wait.wait3Second();
				if (ChangeAmount.equalsIgnoreCase("Yes")) {
					// ReservationFolio.Change_Amount.clear();
					// Wait.wait3Second();
					// ReservationFolio.Change_Amount.sendKeys(ChangeAmountValue);
					ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);

				} else {
					resFolioLogger.info("Processed complete amount");
				}
				ReservationFolio.Click_ADD_Button.click();
				resFolioLogger.info("Clicked on ADD button");
				Wait.explicit_wait_xpath(OR.Verify_Guest_Ledger, driver);
				Wait.wait3Second();
				try {
					GetPaymentMethod = ReservationFolio.Get_Payment_Method.getText();
					resFolioLogger.info("PaymentMethod: " + " " + GetPaymentMethod);
				} catch (Exception e) {
					TestCore.test.log(LogStatus.FAIL,
							"Payment verification failed \n" + e.getMessage()
							+ "\n\n <br> Attaching screenshot below : \n"
							+ TestCore.test.addScreenCapture(
									Utility.captureScreenShot(TestCore.test.getTest().getName()
											+ "_Payment_Verification" + Utility.getTimeStamp(), driver)));
					resFolioLogger.info("Payment verification failed \n");
					e.printStackTrace();
				}
				try {
					if (GetPaymentMethod.equalsIgnoreCase(PaymentType)) {
						resFolioLogger.info("Paymnet is Successful");
					} else {
						resFolioLogger.info("Paymnet is Failed");
					}
				} catch (Exception e) {
					TestCore.test.log(LogStatus.FAIL,
							"Payment verification failed \n" + e.getMessage()
							+ "\n\n <br> Attaching screenshot below : \n"
							+ TestCore.test.addScreenCapture(
									Utility.captureScreenShot(TestCore.test.getTest().getName()
											+ "_Payment_Verification" + Utility.getTimeStamp(), driver)));
					resFolioLogger.info("Payment verification failed \n");
					e.printStackTrace();
				}

				Wait.wait1Second();
				Utility.ScrollToElement(ReservationFolio.Processed_Amount_In_Paymentdetails_Popup, driver);
				Wait.wait2Second();
				String Processed_Amount = ReservationFolio.Processed_Amount_In_Paymentdetails_Popup.getText();
				resFolioLogger.info(Processed_Amount + " -- " + Processed_Amount);
				String RemoveCurreny[] = Processed_Amount.split(" ");
				processed_amount = Float.parseFloat(RemoveCurreny[1]);
				resFolioLogger.info(processed_amount);

				ReservationFolio.Click_Continue.click();
				resFolioLogger.info("Clicked on continue button of Payment popup");

				if (Reservation.checkinpolicy == true) {
					resFolioLogger.info("This Reservation has Checkin policy ");
					Wait.explicit_wait_visibilityof_webelement(ReservationFolio.Click_on_confirm, driver);
					ReservationFolio.Click_on_confirm.click();
					resFolioLogger
					.info("Clicked on Confirm button of Guest Registration Form in ReservationFolio.java");
					Wait.wait3Second();
				}

				try {
					if (Reservation.flag == true) {
						Wait.explicit_wait_10sec(ReservationPage.Policy_Comparision_PopUp, driver);
						if (ReservationPage.Policy_Comparision_PopUp.isDisplayed()) {
							Wait.explicit_wait_elementToBeClickable(ReservationPage.Select_Continue_with_OriginalPolicy,
									driver);
							action.moveToElement(ReservationPage.Select_Continue_with_OriginalPolicy).click().build()
							.perform();
							Wait.explicit_wait_elementToBeClickable(
									ReservationPage.Policy_Comparision_PopUp_Continue_Btn, driver);
							action.moveToElement(ReservationPage.Policy_Comparision_PopUp_Continue_Btn).click().build()
							.perform();
							Wait.wait2Second();
						}
					}
				} catch (Exception e) {
					resFolioLogger.info("No conflicts with Policies");

				}
				Wait.wait3Second();
				Wait.explicit_wait_xpath(OR.Verify_Line_item, driver);
				String GetBalance = ReservationFolio.Verify_Balance_Zero.getText();
				// resFolioLogger.info("Balance: " + " " + GetBalance);
				RemoveCurreny = GetBalance.split(" ");
				resFolioLogger.info("Pending balance after payment: " + " " + RemoveCurreny[1]);

				if (ChangeAmount.equalsIgnoreCase("NO")) {
					if (RemoveCurreny[1].equals("0.00"))
						;
				} else {
					resFolioLogger.info("Selected Changed Value");
				}

			} catch (Exception e) {
				TestCore.test.log(LogStatus.FAIL,
						"Exception occured while paying using CASH \n" + e.getMessage()
						+ "\n\n <br> Attaching screenshot below : \n"
						+ TestCore.test.addScreenCapture(Utility.captureScreenShot(
								TestCore.test.getTest().getName() + "_Payment_ByCash" + Utility.getTimeStamp(),
								driver)));
				resFolioLogger.info("Exception occured while paying using CASH \n");
				e.printStackTrace();
			}
		} else if (PaymentType.equalsIgnoreCase("MC")) {
			try {

				new Select(ReservationFolio.Select_Paymnet_Method).selectByVisibleText(PaymentType);
				Wait.wait3Second();
				ReservationFolio.Click_Card_info.click();
				resFolioLogger.info("Clicked on card info");
				Wait.explicit_wait_xpath(OR.Verify_payment_info_popup, driver);
				Wait.wait3Second();
				ReservationFolio.Enter_Card_Name.sendKeys(CardName);
				ReservationFolio.Enter_Account_Number_Folio.sendKeys(CCNumber);
				ReservationFolio.Enter_CC_Expiry.sendKeys(CCExpiry);
				ReservationFolio.Enter_CVVCode.sendKeys(CCVCode);
				resFolioLogger.info("Entered Card Details");
				ReservationFolio.Click_OK.click();
				resFolioLogger.info("Clicked on OK button");
				Wait.wait10Second();
				new Select(ReservationFolio.Select_Authorization_type).selectByVisibleText(Authorizationtype);
				resFolioLogger.info("Selected authorization type");
				if (ChangeAmount.equalsIgnoreCase("Yes")) {
					/*
					 * ReservationFolio.Change_Amount.clear();
					 * Wait.wait3Second();
					 * ReservationFolio.Change_Amount.sendKeys(ChangeAmountValue
					 * );
					 */
					ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
				} else {
					resFolioLogger.info("Processing the amount displayed");
				}
				ReservationFolio.Click_Process.click();
				resFolioLogger.info("Clicked on Process Button");
				Thread.sleep(10000);
				Wait.wait3Second();
				Wait.explicit_wait_xpath(OR.Verify_MC_Grid, driver);
				String GetPaymentMethod = ReservationFolio.GetMC_Payment_Method.getText();
				resFolioLogger.info("PaymentMethod: " + " " + GetPaymentMethod);
				if (GetPaymentMethod.equals(PaymentType)) {
					resFolioLogger.info("Paymnet is successful");
				} else {
					resFolioLogger.info("Paymnet is Failed");
				}

				Wait.wait1Second();
				Utility.ScrollToElement(ReservationFolio.Processed_Amount_In_Paymentdetails_Popup, driver);
				Wait.wait2Second();
				String Processed_Amount = ReservationFolio.Processed_Amount_In_Paymentdetails_Popup.getText();
				resFolioLogger.info("Processed_Amount " + Processed_Amount + " -- " + Processed_Amount);
				String RemoveCurreny[] = Processed_Amount.split(" ");
				processed_amount = Float.parseFloat(RemoveCurreny[1]);
				resFolioLogger.info("Processed_Amount is " + processed_amount);

				ReservationFolio.Click_Continue.click();
				resFolioLogger.info("Clicked on continue button");
				Wait.wait3Second();
				Wait.explicit_wait_xpath(OR.GetAddedLine_MC, driver);
				String GetMCCard = ReservationFolio.GetAddedLine_MC.getText();
				resFolioLogger.info("Transaction Line Item: " + GetMCCard);
				if (GetMCCard.equalsIgnoreCase("Name: MC Account #: XXXX5454 Exp. Date: 08/21")) {
					resFolioLogger.info("Payment is successful");
				} else {
					resFolioLogger.info("Payment is Failed");
				}
				if (Reservation.checkinpolicy == true) {
					resFolioLogger.info("This Reservation has Checkin policy ");
					Wait.explicit_wait_visibilityof_webelement(ReservationFolio.Click_on_confirm, driver);
					ReservationFolio.Click_on_confirm.click();
					resFolioLogger
					.info("Clicked on Confirm button of Guest Registration Form in ReservationFolio.java");
					Wait.wait3Second();
				}
				try {
					if (Reservation.flag == true) {
						Wait.explicit_wait_10sec(ReservationPage.Policy_Comparision_PopUp, driver);
						if (ReservationPage.Policy_Comparision_PopUp.isDisplayed()) {
							Wait.explicit_wait_elementToBeClickable(ReservationPage.Select_Continue_with_OriginalPolicy,
									driver);
							action.moveToElement(ReservationPage.Select_Continue_with_OriginalPolicy).click().build()
							.perform();
							Wait.explicit_wait_elementToBeClickable(
									ReservationPage.Policy_Comparision_PopUp_Continue_Btn, driver);
							action.moveToElement(ReservationPage.Policy_Comparision_PopUp_Continue_Btn).click().build()
							.perform();
							Wait.wait2Second();
						}
					}
				} catch (Exception e) {
					resFolioLogger.info("No conflicts with Policies");

				}
				Wait.explicit_wait_visibilityof_webelement(ReservationFolio.Verify_Balance_Zero, driver);
				String GetBalance = ReservationFolio.Verify_Balance_Zero.getText();
				resFolioLogger.info("Balance after payment: " + " " + GetBalance);
				RemoveCurreny = GetBalance.split(" ");
				resFolioLogger.info("Pending balance after payment: " + " " + RemoveCurreny[1]);
				if (ChangeAmount.equalsIgnoreCase("No")) {
					if (RemoveCurreny[1].equals("0.00"))
						;
				} else {
					resFolioLogger.info("Selected Changed Value");
				}

			} catch (Exception e) {
				TestCore.test.log(LogStatus.FAIL,
						"Exception occured while paying using MC \n" + e.getMessage()
						+ "\n\n <br> Attaching screenshot below : \n"
						+ TestCore.test.addScreenCapture(Utility.captureScreenShot(
								TestCore.test.getTest().getName() + "_Payment_ByMC" + Utility.getTimeStamp(),
								driver)));
				resFolioLogger.info("Exception occured while paying using MC \n");
				e.printStackTrace();
			}

		} else if (PaymentType.equalsIgnoreCase("Swipe")) {
			try {

				ReservationFolio.Click_Swipe_Icon.click();
				resFolioLogger.info("Clicked on swipe icon");
				Wait.explicit_wait_xpath(OR.Verify_Swipe_Popup, driver);
				ReservationFolio.Enter_Track_Data.sendKeys(traceData);

				if (ChangeAmount.equalsIgnoreCase("Yes")) {
					ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);

				} else {
					resFolioLogger.info("Processed complete amount");
				}
				Wait.wait3Second();
				new Select(ReservationFolio.Select_Authorization_type).selectByVisibleText(Authorizationtype);
				Wait.wait3Second();
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", ReservationFolio.Click_Process);
				// ReservationFolio.Click_Process.click();
				resFolioLogger.info("Clicked on Process Button");
				// Wait.wait3Second();
				Wait.explicit_wait_xpath(OR.Verify_MC_Grid, driver);
				String GetPaymentMethod = ReservationFolio.GetMC_Payment_Method.getText();
				resFolioLogger.info("PaymentMethod: " + " " + GetPaymentMethod);
				if (GetPaymentMethod.equals("MC")) {
					resFolioLogger.info("Paymnet is successful");
				} else {
					resFolioLogger.info("Paymnet is failed");
				}

				Wait.wait1Second();
				Utility.ScrollToElement(ReservationFolio.Processed_Amount_In_Paymentdetails_Popup, driver);
				Wait.wait2Second();
				String Processed_Amount = ReservationFolio.Processed_Amount_In_Paymentdetails_Popup.getText();
				resFolioLogger.info(Processed_Amount + " -- " + Processed_Amount);
				String RemoveCurreny[] = Processed_Amount.split(" ");
				processed_amount = Float.parseFloat(RemoveCurreny[1]);
				resFolioLogger.info(processed_amount);

				ReservationFolio.Click_Continue.click();
				resFolioLogger.info("Clicked on continue button");
				// Wait.wait3Second();
				try {
					Wait.explicit_wait_10sec(ReservationPage.Already_Checked_In_Confirm_Popup, driver);
					ReservationPage.Already_Checked_In_Confirm_Popup_Confirm_Btn.click();
					System.out.println("Payment has been cancelled");
				} catch (Exception e) {

					Wait.explicit_wait_xpath(OR.GetAddedLine_MC, driver);
					String GetMCCard = ReservationFolio.GetAddedLine_MC.getText();
					resFolioLogger.info("CardType: " + " " + GetMCCard);
					if (GetMCCard.equals("Name: TEST CARD/MC Account #: XXXX5454 Exp. Date: 12/23")) {
						resFolioLogger.info("Paymnet is successful");
					} else {
						resFolioLogger.info("Paymnet is failed");
					}

					Wait.wait5Second();
				}

				/*
				 * Wait.waitUntilPresenceOfElementLocated(OR.Click_Cancel);
				 * 
				 * System.out.println("Click Cancel of the report");
				 * driver.findElement(By.xpath(OR.Click_Cancel)).click();
				 * System.out.println("Click Cancel of the report");
				 * Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message);
				 */Wait.explicit_wait_visibilityof_webelement(ReservationPage.Click_on_confirm, driver);
				 ReservationPage.Click_on_confirm.click();
				 Wait.waitUntilPresenceOfElementLocated(OR.Toaster_Message, driver);
				 System.out.println("Click Confirm of the report");
				 resFolioLogger.info("Clicked on Confirm button of Guest Statement Report");

				 Wait.wait3Second();
				 String GetBalance = ReservationFolio.Verify_Balance_Zero.getText();
				 // resFolioLogger.info("Balance: " + " " + GetBalance);
				 RemoveCurreny = GetBalance.split(" ");
				 resFolioLogger.info("Balance: " + RemoveCurreny[1]);
				 if (ChangeAmount.equalsIgnoreCase("NO")) {
					 if (RemoveCurreny[1].equals("0.00"))
						 ;
				 } else {
					 resFolioLogger.info("Selected Changed Value");
				 }

			} catch (Exception e) {
				TestCore.test.log(LogStatus.FAIL,
						"Exception occured while paying using swipe \n" + e.getMessage()
						+ "\n\n <br> Attaching screenshot below : \n"
						+ TestCore.test.addScreenCapture(Utility.captureScreenShot(
								TestCore.test.getTest().getName() + "_Payment_BySwipe" + Utility.getTimeStamp(),
								driver)));
				resFolioLogger.error("Exception occured while paying using swipe \n");
				e.printStackTrace();
			}

		}
		return processed_amount;
	}

	public void Paytype_CP_Account(WebDriver driver, String ChangeAmount, String ChangeAmountValue)
			throws InterruptedException {
		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
		ReservationFolio.Click_Pay_Button.click();
		Wait.explicit_wait_xpath(OR.Verify_Payment_Details_poup, driver);
		Wait.wait10Second();
		new Select(ReservationFolio.Select_Paymnet_Method).selectByIndex(1);
		Wait.wait3Second();
		if (ChangeAmount.equalsIgnoreCase("Yes")) {
			// ReservationFolio.Change_Amount.clear();
			// Wait.wait3Second();
			// ReservationFolio.Change_Amount.sendKeys(ChangeAmountValue);
			ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);

		} else {
			resFolioLogger.info("Processed complete amount");
		}
		ReservationFolio.Click_ADD_Button.click();
		Wait.explicit_wait_xpath(OR.Verify_Guest_Ledger, driver);
		Wait.wait3Second();
		try {
			GetPaymentMethod = ReservationFolio.Get_Payment_Method.getText();
			resFolioLogger.info("PaymentMethod: " + " " + GetPaymentMethod);
		} catch (Exception e) {
			TestCore.test.log(LogStatus.FAIL, "Payment verification failed \n" + e.getMessage()
			+ "\n\n <br> Attaching screenshot below : \n"
			+ TestCore.test.addScreenCapture(Utility.captureScreenShot(
					TestCore.test.getTest().getName() + "_Payment_Verification" + Utility.getTimeStamp(),
					driver)));
			resFolioLogger.info("Payment verification failed \n");
			e.printStackTrace();
		}
		try {
			if (GetPaymentMethod.equals("account")) {
				resFolioLogger.info("Paymnet Success");
			} else {
				resFolioLogger.info("Paymnet Failed");
			}
		} catch (Exception e) {
			TestCore.test.log(LogStatus.FAIL, "Payment verification failed \n" + e.getMessage()
			+ "\n\n <br> Attaching screenshot below : \n"
			+ TestCore.test.addScreenCapture(Utility.captureScreenShot(
					TestCore.test.getTest().getName() + "_Payment_Verification" + Utility.getTimeStamp(),
					driver)));
			resFolioLogger.info("Payment verification failed \n");
			e.printStackTrace();
		}
		ReservationFolio.Click_Continue.click();
		Wait.wait15Second();
		try {
			Wait.explicit_wait_xpath(OR.Verify_CP_Lineitem, driver);
			String GetBalance = ReservationFolio.Verify_Balance_Zero.getText();
			// resFolioLogger.info(GetBalance + " " + GetBalance);
			String RemoveCurreny[] = GetBalance.split(" ");
			resFolioLogger.info("Balance after payment: " + RemoveCurreny[1]);
			if (ChangeAmount.equalsIgnoreCase("NO")) {
				if (RemoveCurreny[1].equals("0.00"))
					;
			} else {
				resFolioLogger.info("Selected Changed Value");
			}
		} catch (Exception e) {

		}
	}

	public void HouseAccount(WebDriver driver, String Accounttype, String HouseAccountName, String ChangeAmount,
			String ChangeAmountValue) throws InterruptedException {
		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
		ReservationFolio.Click_Pay_Button.click();
		Wait.explicit_wait_xpath(OR.Verify_Payment_Details_poup, driver);
		Wait.wait10Second();
		new Select(ReservationFolio.Select_Paymnet_Method).selectByVisibleText(Accounttype);
		Wait.wait3Second();
		Wait.explicit_wait_xpath(OR.Verify_House_Account_Picker, driver);
		ReservationFolio.Enter_Account_Res_Name.sendKeys(HouseAccountName);
		ReservationFolio.Click_Search_House_Acc.click();
		Wait.wait5Second();
		Wait.explicit_wait_xpath(OR.Verify_House_Account_Grid, driver);
		Wait.wait10Second();
		ReservationFolio.Select_House_Acc.click();
		Wait.wait5Second();
		ReservationFolio.Click_Select_House_acc.click();
		Wait.wait10Second();
		new Select(ReservationFolio.Select_Paymnet_Method).selectByIndex(1);
		Wait.wait10Second();
		if (ChangeAmount.equalsIgnoreCase("Yes")) {
			// ReservationFolio.Change_Amount.clear();
			// Wait.wait3Second();
			// ReservationFolio.Change_Amount.sendKeys(ChangeAmountValue);
			ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);

		} else {
			resFolioLogger.info("Processed complete amount");
		}
		ReservationFolio.Click_ADD_Button.click();
		Wait.explicit_wait_xpath(OR.Verify_Guest_Ledger, driver);
		Wait.wait3Second();
		try {
			GetPaymentMethod = ReservationFolio.Get_Payment_Method.getText();
			resFolioLogger.info("PaymentMethod: " + " " + GetPaymentMethod);
		} catch (Exception e) {
			TestCore.test.log(LogStatus.FAIL, "Payment verification failed \n" + e.getMessage()
			+ "\n\n <br> Attaching screenshot below : \n"
			+ TestCore.test.addScreenCapture(Utility.captureScreenShot(
					TestCore.test.getTest().getName() + "_Payment_Verification" + Utility.getTimeStamp(),
					driver)));
			resFolioLogger.info("Payment verification failed \n");
			e.printStackTrace();
		}
		try {
			if (GetPaymentMethod.equals("account")) {
				resFolioLogger.info("Paymnet Success");
			} else {
				resFolioLogger.info("Paymnet Failed");
			}
		} catch (Exception e) {
			TestCore.test.log(LogStatus.FAIL, "Payment verification failed \n" + e.getMessage()
			+ "\n\n <br> Attaching screenshot below : \n"
			+ TestCore.test.addScreenCapture(Utility.captureScreenShot(
					TestCore.test.getTest().getName() + "_Payment_Verification" + Utility.getTimeStamp(),
					driver)));
			resFolioLogger.info("Payment verification failed \n");
			e.printStackTrace();
		}
		ReservationFolio.Click_Continue.click();
		Wait.wait15Second();
		try {
			Wait.explicit_wait_xpath(OR.Verify_CP_Lineitem, driver);
			String GetBalance = ReservationFolio.Verify_Balance_Zero.getText();
			// resFolioLogger.info("Balance: " + " " + GetBalance);
			String RemoveCurreny[] = GetBalance.split(" ");
			resFolioLogger.info(RemoveCurreny[1]);
			if (ChangeAmount.equalsIgnoreCase("NO")) {
				if (RemoveCurreny[1].equals("0.00"))
					;
			} else {
				resFolioLogger.info("Selected Changed Value");
			}
		} catch (Exception e) {

		}
	}

	public void GiftCertificate(WebDriver driver, String Accounttype, String ChangeAmount, String ChangeAmountValue)
			throws InterruptedException {
		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationFolio.Click_Pay_Button, driver);
		Wait.explicit_wait_elementToBeClickable(ReservationFolio.Click_Pay_Button, driver);
		Utility.ScrollToElement(ReservationFolio.Click_Pay_Button, driver);
		ReservationFolio.Click_Pay_Button.click();
		Wait.explicit_wait_xpath(OR.Verify_Payment_Details_poup, driver);
		Wait.wait10Second();
		new Select(ReservationFolio.Select_Paymnet_Method).selectByVisibleText(Accounttype);
		Wait.wait3Second();
		Wait.explicit_wait_xpath(OR.Verify_Gift_Account_popup, driver);
		try {
			FileReader fr = new FileReader(".\\GiftCertificate.txt");
			BufferedReader br = new BufferedReader(fr);

			while ((giftCardnumber = br.readLine()) != null) {
				ReservationFolio.Enter_Gift_ID.sendKeys(giftCardnumber);
			}
			br.close();
		} catch (IOException e) {
			resFolioLogger.info("File not found");
		}
		Wait.WaitForElement(driver,OR.Click_Go_Gift);
		Wait.explicit_wait_xpath(OR.Click_Go_Gift, driver);
		ReservationFolio.Click_Go_Gift.click();
		Wait.wait5Second();
		Wait.WaitForElement(driver,OR.Verify_Gift_Certificate_Grid);
		Wait.explicit_wait_xpath(OR.Verify_Gift_Certificate_Grid, driver);
		Wait.wait10Second();
		Wait.WaitForElement(driver, OR.Select_Gift_Certificate);
		Wait.explicit_wait_xpath(OR.Select_Gift_Certificate, driver);
		ReservationFolio.Select_Gift_Certificate.click();
		Wait.wait5Second();
		Wait.WaitForElement(driver,OR.Click_Select_Gift);
		Wait.explicit_wait_xpath(OR.Click_Select_Gift, driver);
		ReservationFolio.Click_Select_Gift.click();
		Wait.wait10Second();
		Wait.WaitForElement(driver,OR.Select_Paymnet_Method);
		Wait.explicit_wait_xpath(OR.Select_Paymnet_Method, driver);
		new Select(ReservationFolio.Select_Paymnet_Method).selectByIndex(5);
		Wait.wait10Second();
		if (ChangeAmount.equalsIgnoreCase("Yes")) {
			// ReservationFolio.Change_Amount.clear();
			// Wait.wait3Second();
			// ReservationFolio.Change_Amount.sendKeys(ChangeAmountValue);
			ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);

		} else {
			resFolioLogger.info("Processed complete amount");
		}
		
		Wait.WaitForElement(driver,OR.Click_ADD_Button);
		Wait.explicit_wait_xpath(OR.Click_ADD_Button, driver);
		ReservationFolio.Click_ADD_Button.click();
		Wait.explicit_wait_xpath(OR.Verify_Guest_Ledger, driver);
		Wait.wait3Second();
		try {
			GetPaymentMethod = ReservationFolio.Get_Payment_Method.getText();
			resFolioLogger.info("PaymentMethod: " + GetPaymentMethod);
		} catch (Exception e) {
			TestCore.test.log(LogStatus.FAIL, "Payment verification failed \n" + e.getMessage()
			+ "\n\n <br> Attaching screenshot below : \n"
			+ TestCore.test.addScreenCapture(Utility.captureScreenShot(
					TestCore.test.getTest().getName() + "_Payment_Verification" + Utility.getTimeStamp(),
					driver)));
			resFolioLogger.info("Payment verification failed \n");
			e.printStackTrace();
		}
		try {
			if (GetPaymentMethod.equalsIgnoreCase("gift certificate")) {
				resFolioLogger.info("Paymnet Success");
			} else {
				resFolioLogger.info("Paymnet Failed");
			}
		} catch (Exception e) {
			TestCore.test.log(LogStatus.FAIL, "Payment verification failed \n" + e.getMessage()
			+ "\n\n <br> Attaching screenshot below : \n"
			+ TestCore.test.addScreenCapture(Utility.captureScreenShot(
					TestCore.test.getTest().getName() + "_Payment_Verification" + Utility.getTimeStamp(),
					driver)));
			resFolioLogger.info("Payment verification failed \n");
			e.printStackTrace();
		}
		ReservationFolio.Click_Continue.click();
		Wait.wait15Second();
		try {
			Wait.explicit_wait_xpath(OR.Verify_CP_Lineitem, driver);
			String GetBalance = ReservationFolio.Verify_Balance_Zero.getText();
			// resFolioLogger.info(GetBalance + " " + GetBalance);
			String RemoveCurreny[] = GetBalance.split(" ");
			resFolioLogger.info("Balance: " + RemoveCurreny[1]);
			if (ChangeAmount.equalsIgnoreCase("NO")) {
				if (RemoveCurreny[1].equals("0.00"))
					;
			} else {
				resFolioLogger.info("Selected Changed Value");
			}
		} catch (Exception e) {

		}
	}

	public void CardPayment_MC_Auth(WebDriver driver, String PaymentType, String CardName, String CCNumber, String CCExpiry,
			String CCVCode, String Authorizationtype, String ChangeAmount, String ChangeAmountValue, String TraceData)
					throws InterruptedException {
		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
		Actions action = new Actions(driver);
		float processed_amount = 0;
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.wait5Second();
		Wait.explicit_wait_xpath(OR.Verify_Payment_Details_poup, driver);
		Wait.wait5Second();

		resFolioLogger.info("MC Card Payment ");
		try {

			new Select(ReservationFolio.Select_Paymnet_Method).selectByVisibleText(PaymentType);
			Wait.wait3Second();
			ReservationFolio.Click_Card_info.click();
			resFolioLogger.info("Clicked on card info");
			Wait.explicit_wait_xpath(OR.Verify_payment_info_popup, driver);
			Wait.wait3Second();
			ReservationFolio.Enter_Card_Name.sendKeys(CardName);
			ReservationFolio.Enter_Account_Number_Folio.sendKeys(CCNumber);
			ReservationFolio.Enter_CC_Expiry.sendKeys(CCExpiry);
			ReservationFolio.Enter_CVVCode.sendKeys(CCVCode);
			resFolioLogger.info("Entered Card Details");
			ReservationFolio.Click_OK.click();
			resFolioLogger.info("Clicked on OK button");
			Wait.wait10Second();
			System.out.println("AuthoType:" + Authorizationtype);
			new Select(ReservationFolio.Select_Authorization_type).selectByVisibleText(Authorizationtype);
			resFolioLogger.info("Selected authorization type");
			if (ChangeAmount.equalsIgnoreCase("Yes")) {

				ReservationFolio.Change_Amount.clear();
				Wait.wait3Second();
				ReservationFolio.Change_Amount.sendKeys(ChangeAmountValue);

				ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
			} else {
				resFolioLogger.info("Processing the amount displayed");
			}
			Wait.explicit_wait_visibilityof_webelement(ReservationFolio.Click_Process, driver);
			Utility.ScrollToElement(ReservationFolio.Click_Process, driver);
			Wait.explicit_wait_elementToBeClickable(ReservationFolio.Click_Process, driver);
			ReservationFolio.Click_Process.click();
			resFolioLogger.info("Clicked on Process Button");
			Thread.sleep(10000);
			Wait.wait3Second();
			Wait.explicit_wait_xpath(OR.Verify_MC_Grid, driver);
			String GetPaymentMethod = ReservationFolio.GetMC_Payment_Method.getText();
			resFolioLogger.info("PaymentMethod: " + " " + GetPaymentMethod);
			if (GetPaymentMethod.equals(PaymentType)) {
				resFolioLogger.info("Paymnet is successful");
			} else {
				resFolioLogger.info("Paymnet is Failed");
			}

			Wait.wait1Second();
			Utility.ScrollToElement(ReservationFolio.Processed_Amount_In_Paymentdetails_Popup, driver);
			Wait.wait2Second();
			String Processed_Amount = ReservationFolio.Processed_Amount_In_Paymentdetails_Popup.getText();
			resFolioLogger.info("Processed_Amount " + Processed_Amount + " -- " + Processed_Amount);
			String RemoveCurreny[] = Processed_Amount.split(" ");
			processed_amount = Float.parseFloat(RemoveCurreny[1]);
			resFolioLogger.info("Processed_Amount is " + processed_amount);
			
			Wait.explicit_wait_visibilityof_webelement(ReservationFolio.Click_Continue, driver);
			Utility.ScrollToElement(ReservationFolio.Click_Continue, driver);
			Wait.explicit_wait_elementToBeClickable(ReservationFolio.Click_Continue, driver);
			ReservationFolio.Click_Continue.click();
			resFolioLogger.info("Clicked on continue button");
		} catch (Exception e) {
			TestCore.test.log(LogStatus.FAIL,
					"Exception occured while paying using MC \n" + e.getMessage()
					+ "\n\n <br> Attaching screenshot below : \n"
					+ TestCore.test.addScreenCapture(Utility.captureScreenShot(
							TestCore.test.getTest().getName() + "_Payment_ByMC" + Utility.getTimeStamp(),
							driver)));
			resFolioLogger.info("Exception occured while paying using MC \n");
			e.printStackTrace();
		}

	}
	
	public void CardPayment_MC(WebDriver driver, String PaymentType, String CardName, String CCNumber, String CCExpiry,
			String CCVCode, String Authorizationtype, String ChangeAmount, String ChangeAmountValue, String TraceData)
					throws InterruptedException {
		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
		Actions action = new Actions(driver);
		float processed_amount = 0;
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		Wait.wait5Second();
		Wait.explicit_wait_xpath(OR.Verify_Payment_Details_poup, driver);
		Wait.wait5Second();

		resFolioLogger.info("MC Card Payment ");
		try {

			new Select(ReservationFolio.Select_Paymnet_Method).selectByVisibleText(PaymentType);
			Wait.wait3Second();
			ReservationFolio.Click_Card_info.click();
			resFolioLogger.info("Clicked on card info");
			Wait.explicit_wait_xpath(OR.Verify_payment_info_popup, driver);
			Wait.wait3Second();
			ReservationFolio.Enter_Card_Name.sendKeys(CardName);
			ReservationFolio.Enter_Account_Number_Folio.sendKeys(CCNumber);
			ReservationFolio.Enter_CC_Expiry.sendKeys(CCExpiry);
			ReservationFolio.Enter_CVVCode.sendKeys(CCVCode);
			resFolioLogger.info("Entered Card Details");
			ReservationFolio.Click_OK.click();
			resFolioLogger.info("Clicked on OK button");
			Wait.wait10Second();
			System.out.println("AuthoType:" + Authorizationtype);
			new Select(ReservationFolio.Select_Authorization_type).selectByVisibleText(Authorizationtype);
			resFolioLogger.info("Selected authorization type");
			if (ChangeAmount.equalsIgnoreCase("Yes")) {

				ReservationFolio.Change_Amount.clear();
				Wait.wait3Second();
				ReservationFolio.Change_Amount.sendKeys(ChangeAmountValue);

				ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
			} else {
				resFolioLogger.info("Processing the amount displayed");
			}
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			// jse.executeScript("arguments[0].click();",
			// ReservationFolio.Click_Process);
			Utility.ScrollToElement(ReservationFolio.Click_Process, driver);
			ReservationFolio.Click_Process.click();
			resFolioLogger.info("Clicked on Process Button");
			Thread.sleep(10000);
			Wait.wait3Second();
			Wait.explicit_wait_xpath(OR.Verify_MC_Grid, driver);
			String GetPaymentMethod = ReservationFolio.GetMC_Payment_Method.getText();
			resFolioLogger.info("PaymentMethod: " + " " + GetPaymentMethod);
			if (GetPaymentMethod.equals(PaymentType)) {
				resFolioLogger.info("Paymnet is successful");
			} else {
				resFolioLogger.info("Paymnet is Failed");
			}

			Wait.wait1Second();
			Utility.ScrollToElement(ReservationFolio.Processed_Amount_In_Paymentdetails_Popup, driver);
			Wait.wait2Second();
			String Processed_Amount = ReservationFolio.Processed_Amount_In_Paymentdetails_Popup.getText();
			resFolioLogger.info("Processed_Amount " + Processed_Amount + " -- " + Processed_Amount);
			String RemoveCurreny[] = Processed_Amount.split(" ");
			processed_amount = Float.parseFloat(RemoveCurreny[1]);
			resFolioLogger.info("Processed_Amount is " + processed_amount);

			ReservationFolio.Click_Continue.click();
			resFolioLogger.info("Clicked on continue button");
			/*
			 * Wait.wait3Second(); String GetMCCard =
			 * ReservationFolio.GetAddedLineMC.getText();
			 * resFolioLogger.info("Transaction Line Item: " + GetMCCard); if
			 * (GetMCCard.
			 * equalsIgnoreCase("Name: MC Account #: XXXX5454 Exp. Date: 08/21"
			 * )) { resFolioLogger.info("Payment is successful"); } else {
			 * resFolioLogger.info("Payment is Failed"); }
			 */
		} catch (Exception e) {
			TestCore.test.log(LogStatus.FAIL,
					"Exception occured while paying using MC \n" + e.getMessage()
					+ "\n\n <br> Attaching screenshot below : \n"
					+ TestCore.test.addScreenCapture(Utility.captureScreenShot(
							TestCore.test.getTest().getName() + "_Payment_ByMC" + Utility.getTimeStamp(),
							driver)));
			resFolioLogger.info("Exception occured while paying using MC \n");
			e.printStackTrace();
		}

	}
	
	
	public ArrayList<String> TravelAccount(WebDriver driver, String AccountnameTA, String AccountnumberTA,
			String ChangeAmountValue) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();

		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
		Elements_All_Payments payments = new Elements_All_Payments(driver);
		//
		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationFolio.Click_Pay_Button, driver);
			Utility.ScrollToElement(ReservationFolio.Click_Pay_Button, driver);
			Wait.explicit_wait_elementToBeClickable(ReservationFolio.Click_Pay_Button, driver);
			ReservationFolio.Click_Pay_Button.click();
		} catch (Exception e) {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//*[@id='bpjscontainer_102']//button[contains(text(),'Pay')]")))
					.click();
		}
		Wait.explicit_wait_xpath(OR.Verify_Payment_Details_poup, driver);
		String selectTA = "Account - " + AccountnameTA + " (" + AccountnumberTA + ")";
		Wait.explicit_wait_visibilityof_webelement(ReservationFolio.Change_Amount, driver);
		ReservationFolio.Change_Amount.clear();
		ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
		Wait.wait5Second();
		if(!ReservationFolio.Change_Amount.getAttribute("value").equals(ChangeAmountValue+".00")){
			ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
		}
		Wait.wait5Second();
		new Select(ReservationFolio.Select_Paymnet_Method).selectByVisibleText(selectTA);
		Wait.explicit_wait_elementToBeClickable(payments.Add_Pay_Button, driver);
		payments.Add_Pay_Button.click();
		//
		resFolioLogger.info("Successfully Clicked on ADD button");
		test_steps.add("Successfully Clicked on Add button");
		Wait.explicit_wait_elementToBeClickable(ReservationFolio.Click_Continue, driver);
		ReservationFolio.Click_Continue.click();
		resFolioLogger.info("Clicked on continue button of Payment popup");
		test_steps.add("Clicked on continue button of Payment popup");
		return test_steps;
	}
	
	public ArrayList<String> verifyFolioItemsAmount(WebDriver driver, double Total, double afterAmount,
			String changeValue, String AccountName) {
		ArrayList<String> test_steps = new ArrayList<String>();

		double actual = Double.parseDouble(changeValue) + afterAmount;

		assertEquals(actual, Total, "Amount not added for " + AccountName);

		resFolioLogger.info("Successfully Verified Amount for " + AccountName);
		test_steps.add("Successfully Verified Amount for " + AccountName);
		return test_steps;
	}
	
	public ArrayList<String> HouseAccountSelect(WebDriver driver, String AccounttypeHA, String AccountnameHA,
			String AccountnumberHA, String ChangeAmountValue) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();
		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
		Elements_All_Payments payments = new Elements_All_Payments(driver);
		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationFolio.Click_Pay_Button, driver);
			Utility.ScrollToElement(ReservationFolio.Click_Pay_Button, driver);
			Wait.explicit_wait_elementToBeClickable(ReservationFolio.Click_Pay_Button, driver);
			ReservationFolio.Click_Pay_Button.click();
		} catch (Exception e) {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//*[@id='bpjscontainer_102']//button[contains(text(),'Pay')]")))
					.click();
		}
		Wait.explicit_wait_xpath(OR.Verify_Payment_Details_poup, driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationFolio.Select_Paymnet_Method, driver);
		new Select(ReservationFolio.Select_Paymnet_Method).selectByVisibleText(AccounttypeHA);
		Wait.explicit_wait_xpath(OR.Verify_House_Account_Picker, driver);
		ReservationFolio.Enter_Account_Res_Name.sendKeys(AccountnameHA);
		ReservationFolio.AccountNumber_HouseAccountPopup.sendKeys(AccountnumberHA);
		ReservationFolio.Click_Search_House_Acc.click();
		Wait.explicit_wait_xpath(OR.Verify_House_Account_Grid, driver);
		ReservationFolio.Select_House_Acc.click();
		ReservationFolio.Click_Select_House_acc.click();
		String selectHA = "Account - " + AccountnameHA + " (" + AccountnumberHA + ")";
		new Select(ReservationFolio.Select_Paymnet_Method).selectByVisibleText(selectHA);
		ReservationFolio.Change_Amount.clear();
		ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
		Wait.wait2Second();
		if(!ReservationFolio.Change_Amount.getAttribute("value").equals(ChangeAmountValue+".00")){
			ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);	
		}
		Wait.explicit_wait_elementToBeClickable(payments.Add_Pay_Button, driver);
		payments.Add_Pay_Button.click();
		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bpjscontainer_28']//button[contains(text(),'Add')]"))).click();
		resFolioLogger.info("Successfully Clicked on ADD button");
		test_steps.add("Successfully Clicked on Add button");
		Wait.wait2Second();
		String resPayItem_AlertPath="//*[@id='ReservationPaymetItemDetail_Alert']";
		if(driver.findElement(By.xpath(resPayItem_AlertPath)).isDisplayed()){
			Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(resPayItem_AlertPath+"//button[.='OK']")), driver);
			driver.findElement(By.xpath(resPayItem_AlertPath+"//button[.='OK']")).click();
			ReservationFolio.Change_Amount.clear();
			ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
			Wait.wait2Second();
			if(!ReservationFolio.Change_Amount.getAttribute("value").equals(ChangeAmountValue+".00")){
				ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);	
			}
		}
		Wait.explicit_wait_elementToBeClickable(ReservationFolio.Click_Continue, driver);
		ReservationFolio.Click_Continue.click();
		resFolioLogger.info("Clicked on continue button of Payment popup");
		test_steps.add("Clicked on continue button of Payment popup");
		return test_steps;
	}

	public ArrayList<String> GiftCertificateAccount(WebDriver driver, String AccounttypeGC, String AccountnameGC,
			String AccountNumberGC, String giftCardNumber, String ChangeAmountValue) throws InterruptedException {
		ArrayList<String> test_steps = new ArrayList<String>();

		Elements_Reservation ReservationFolio = new Elements_Reservation(driver);
		Elements_All_Payments payments = new Elements_All_Payments(driver);
		// WebDriverWait wait = new WebDriverWait(driver, 30);
		try {
			Wait.explicit_wait_visibilityof_webelement(ReservationFolio.Click_Pay_Button, driver);
			Utility.ScrollToElement(ReservationFolio.Click_Pay_Button, driver);
			Wait.explicit_wait_elementToBeClickable(ReservationFolio.Click_Pay_Button, driver);
			ReservationFolio.Click_Pay_Button.click();
		} catch (Exception e) {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//*[@id='bpjscontainer_102']//button[contains(text(),'Pay')]")))
					.click();
		}
		Wait.explicit_wait_xpath(OR.Verify_Payment_Details_poup, driver);
		Wait.wait2Second();
		Wait.explicit_wait_visibilityof_webelement(ReservationFolio.Select_Paymnet_Method, driver);
		new Select(ReservationFolio.Select_Paymnet_Method).selectByVisibleText(AccounttypeGC);
		Wait.explicit_wait_xpath(OR.Verify_Gift_Account_popup, driver);
		Wait.explicit_wait_visibilityof_webelement(ReservationFolio.Enter_Gift_ID, driver);
		Wait.wait3Second();
		ReservationFolio.Enter_Gift_ID.clear();
		ReservationFolio.Enter_Gift_ID.sendKeys(giftCardNumber);
		ReservationFolio.Click_Go_Gift.click();
		Wait.explicit_wait_xpath(OR.Verify_Gift_Certificate_Grid, driver);
		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath("//*[@id='bpjscontainer_28']//div[@class='modules_loading']")), 10000);
		Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath("//*[@id='bpjscontainer_38']/div/div/div[2]/div/div/div/div[1]/div/div[3]/div/img")), 10000);
		Wait.explicit_wait_visibilityof_webelement_120(ReservationFolio.Select_Gift_Certificate, driver);
		Wait.explicit_wait_elementToBeClickable(ReservationFolio.Select_Gift_Certificate, driver);
		ReservationFolio.Select_Gift_Certificate.click();
		Wait.wait2Second();
		if(!ReservationFolio.Click_Select_Gift.isEnabled()){
			Wait.wait10Second();
			ReservationFolio.Select_Gift_Certificate.click();
		}
		
		ReservationFolio.Click_Select_Gift.click();
		Wait.wait5Second();
		Wait.explicit_wait_visibilityof_webelement(ReservationFolio.Select_Paymnet_Method, driver);
		String selectGC = "Gift - " + AccountnameGC + " #" + giftCardNumber + " (" + AccountNumberGC + ")";
		try{
			new Select(ReservationFolio.Select_Paymnet_Method).selectByVisibleText(selectGC);
		}catch (Exception e) {
			Wait.explicit_wait_visibilityof_webelement(ReservationFolio.Select_Paymnet_Method, driver);
			new Select(ReservationFolio.Select_Paymnet_Method).selectByVisibleText(AccounttypeGC);
			Wait.explicit_wait_xpath(OR.Verify_Gift_Account_popup, driver);
			Wait.explicit_wait_visibilityof_webelement(ReservationFolio.Enter_Gift_ID, driver);
			Wait.wait3Second();
			ReservationFolio.Enter_Gift_ID.clear();
			ReservationFolio.Enter_Gift_ID.sendKeys(giftCardNumber);
			ReservationFolio.Click_Go_Gift.click();
			Wait.explicit_wait_xpath(OR.Verify_Gift_Certificate_Grid, driver);
			Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath("//*[@id='bpjscontainer_28']//div[@class='modules_loading']")), 10000);
			Wait.explicit_wait_elementToBeClickable(ReservationFolio.Select_Gift_Certificate, driver);
			ReservationFolio.Select_Gift_Certificate.click();
			Wait.wait2Second();
			if(!ReservationFolio.Click_Select_Gift.isEnabled()){
				Wait.wait10Second();
				ReservationFolio.Select_Gift_Certificate.click();
			}
			
			ReservationFolio.Click_Select_Gift.click();
			Wait.wait5Second();
			Wait.explicit_wait_visibilityof_webelement(ReservationFolio.Select_Paymnet_Method, driver);
			selectGC = "Gift - " + AccountnameGC + " #" + giftCardNumber + " (" + AccountNumberGC + ")";
			new Select(ReservationFolio.Select_Paymnet_Method).selectByVisibleText(selectGC);
		}
		Wait.wait1Second();
		ReservationFolio.Change_Amount.clear();
		System.out.println(ChangeAmountValue);
		ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
		System.out.println(ReservationFolio.Change_Amount.getAttribute("value"));
		if(ReservationFolio.Change_Amount.getAttribute("value").equals(ChangeAmountValue+".00")){
			System.out.println("Value changed in GC");
		}else{
			ReservationFolio.Change_Amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), ChangeAmountValue);
		}
		Wait.explicit_wait_elementToBeClickable(payments.Add_Pay_Button, driver);
		payments.Add_Pay_Button.click();
		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bpjscontainer_28']//button[contains(text(),'Add')]"))).click();
		resFolioLogger.info("Successfully Clicked on ADD button");
		test_steps.add("Successfully Clicked on Add button");
		Wait.explicit_wait_elementToBeClickable(ReservationFolio.Click_Continue, driver);
		ReservationFolio.Click_Continue.click();
		resFolioLogger.info("Clicked on continue button of Payment popup");
		test_steps.add("Clicked on continue button of Payment popup");

		return test_steps;
	}

	
}