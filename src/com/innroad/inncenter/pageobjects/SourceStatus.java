package com.innroad.inncenter.pageobjects;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.innroad.inncenter.interfaces.ISourceStatus;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Inventory;
import com.innroad.inncenter.webelements.Elements_Reservation;

public class SourceStatus implements ISourceStatus {

	public String defaultStatusDistribution;

	public static Logger sourceStatusLogger = Logger.getLogger("SourceStatus");

	// **********Select the client*****************//
	public void selectProperty(WebDriver driver, String propertyName1) throws InterruptedException {
		// TODO Auto-generated method stub
		Elements_Inventory source = new Elements_Inventory(driver);
		source.clickClientSelectorIcon.click();
		Wait.wait10Second();
		source.clickPenIcon.click();
		Wait.wait5Second();
		source.enterClientName.sendKeys(propertyName1);
		WebElement client = source.enterClientName;
		client.sendKeys(Keys.ENTER);
		sourceStatusLogger.info(" Successfully selected the Property ");
		Wait.wait10Second();

	}

	// ***************Navigate to Inventory Distribution******************//
	public void inventory_Distribution(WebDriver driver) throws InterruptedException {

		Elements_Inventory source = new Elements_Inventory(driver);
		source.click_Inventory.click();
		source.clickDistributionMenu.click();
		Wait.wait10Second();
		sourceStatusLogger.info(" Sucessfully Navigated to Inventory Distribution ");

	}

	// ******************Select the required fields in Inventory Distribution
	// Channel**************//

	public void channels(WebDriver driver) throws InterruptedException, IOException {
		Elements_Inventory source = new Elements_Inventory(driver);
		Wait.wait10Second();
		boolean distributeCheckbox = source.distribute.isSelected();
		String verifyDefaultStatus = new Select(source.defaultStatus).getFirstSelectedOption().getText();
		sourceStatusLogger.info(" The Default Status is : " + verifyDefaultStatus);

		boolean defaultstatus0 = verifyDefaultStatus.equalsIgnoreCase("--Select--");
		boolean defaultstatus1 = verifyDefaultStatus.equalsIgnoreCase("On Hold");
		boolean defaultstatus2 = verifyDefaultStatus.equalsIgnoreCase("Reserved");
		boolean defaultstatus3 = verifyDefaultStatus.equalsIgnoreCase("Confirmed");
		boolean defaultstatus4 = verifyDefaultStatus.equalsIgnoreCase("Guaranteed");

		if (distributeCheckbox == false)

		{
			source.distribute.click();
			Wait.wait5Second();
			new Select(source.defaultStatus).selectByVisibleText("Confirmed");
			Wait.wait5Second();

			source.clickSaveButtonDistribution.click();
			sourceStatusLogger.info(" Saved the changes ");
			Wait.wait10Second();
			defaultStatusDistribution = new Select(source.defaultStatus).getFirstSelectedOption().getText();
			Wait.wait5Second();
			FileOutputStream fos = new FileOutputStream(".\\data.txt");
			byte b[] = defaultStatusDistribution.getBytes();
			fos.write(b);
			fos.close();
			Wait.wait5Second();
		}

		else if (distributeCheckbox == true && defaultstatus0 == true) {

			Wait.wait5Second();
			new Select(source.defaultStatus).selectByVisibleText("Confirmed");
			Wait.wait5Second();

			source.clickSaveButtonDistribution.click();
			sourceStatusLogger.info(" Saved the changes ");
			Wait.wait10Second();
			defaultStatusDistribution = new Select(source.defaultStatus).getFirstSelectedOption().getText();
			Wait.wait5Second();
			Wait.wait5Second();
			FileOutputStream fos = new FileOutputStream(".\\data.txt");
			byte b[] = defaultStatusDistribution.getBytes();
			fos.write(b);
			fos.close();
			Wait.wait5Second();
		}

		else if (distributeCheckbox == true && defaultstatus1 == true) {

			Wait.wait5Second();
			new Select(source.defaultStatus).selectByVisibleText("Confirmed");
			Wait.wait5Second();

			Wait.wait5Second();

			source.clickSaveButtonDistribution.click();
			sourceStatusLogger.info(" Saved the changes ");
			Wait.wait10Second();
			defaultStatusDistribution = new Select(source.defaultStatus).getFirstSelectedOption().getText();
			Wait.wait5Second();
			FileOutputStream fos = new FileOutputStream(".\\data.txt");
			byte b[] = defaultStatusDistribution.getBytes();
			fos.write(b);
			fos.close();
			Wait.wait5Second();
		}

		else if (distributeCheckbox == true && defaultstatus2 == true) {

			Wait.wait5Second();
			new Select(source.defaultStatus).selectByVisibleText("Confirmed");
			Wait.wait5Second();

			Wait.wait5Second();

			source.clickSaveButtonDistribution.click();
			sourceStatusLogger.info(" Saved the changes ");
			Wait.wait10Second();
			defaultStatusDistribution = new Select(source.defaultStatus).getFirstSelectedOption().getText();
			Wait.wait5Second();
			FileOutputStream fos = new FileOutputStream(".\\data.txt");
			byte b[] = defaultStatusDistribution.getBytes();
			fos.write(b);
			fos.close();
			Wait.wait5Second();
		}

		else if (distributeCheckbox == true && defaultstatus3 == true)

		{
			Wait.wait5Second();
			new Select(source.defaultStatus).selectByVisibleText("Reserved");
			Wait.explicit_wait_visibilityof_webelement(source.clickSaveButtonDistribution, driver);

			source.clickSaveButtonDistribution.click();
			sourceStatusLogger.info(" Saved the changes ");
			Wait.wait10Second();
			defaultStatusDistribution = new Select(source.defaultStatus).getFirstSelectedOption().getText();

			Wait.wait5Second();
			FileOutputStream fos = new FileOutputStream(".\\data.txt");
			byte b[] = defaultStatusDistribution.getBytes();
			fos.write(b);
			fos.close();
			Wait.wait5Second();
		}

		else if (distributeCheckbox == true && defaultstatus4 == true)

		{
			Wait.wait5Second();
			new Select(source.defaultStatus).selectByVisibleText("Reserved");
			Wait.explicit_wait_visibilityof_webelement(source.clickSaveButtonDistribution, driver);

			source.clickSaveButtonDistribution.click();
			sourceStatusLogger.info(" Saved the changes ");
			Wait.wait10Second();
			defaultStatusDistribution = new Select(source.defaultStatus).getFirstSelectedOption().getText();

			Wait.wait5Second();
			FileOutputStream fos = new FileOutputStream(".\\data.txt");
			byte b[] = defaultStatusDistribution.getBytes();
			fos.write(b);
			fos.close();
			Wait.wait5Second();
		}

	}
	// **********************Navigate to Reservation************************//

	public void navigateReservation(WebDriver driver) throws InterruptedException {

		Elements_Reservation res = new Elements_Reservation(driver);
		res.reservation_mainMenu.click();
		res.reservation_Menu.click();
		Wait.wait15Second();
		res.clickNewReservationButton.click();
		Wait.wait5Second();

	}

	@Override
	public void marketingInfo(WebDriver driver, String MarketSegment, String Referral, String Travel_Agent,
			String ExtReservation) throws InterruptedException {
		// TODO Auto-generated method stub

	}

	@Override
	public void contactInformation(WebDriver driver, String saluation, String FirstName, String LastName,
			String Address, String Line1, String Line2, String Line3, String City, String Country, String State,
			String Postalcode, String Phonenumber, String alternativenumber, String Email, String Account,
			String IsTaxExempt, String TaxEmptext) {
		// TODO Auto-generated method stub

	}

	@Override
	public void billingInformation(WebDriver driver, String PaymentMethod, String AccountNumber, String ExpiryDate,
			String BillingNotes) {
		// TODO Auto-generated method stub

	}

	@Override
	public void roomAssignment(WebDriver driver, String PropertyName, String Nights, String Adults, String Children,
			String RatepromoCode, String CheckorUncheckAssign, String RoomClassName, String RoomNumber)
			throws InterruptedException {
		// TODO Auto-generated method stub

	}

	// *********************Verify Reservation status with Default Source status
	// in Distribution Channel******************//
	public void verify_reservaionStatus(WebDriver driver) throws InterruptedException {
		Elements_Reservation res = new Elements_Reservation(driver);
		Wait.explicit_wait_visibilityof_webelement(res.getReservationStatus, driver);
		String reservationStatus = new Select(res.getReservationStatus).getFirstSelectedOption().getText();
		sourceStatusLogger.info(" Status of Reservation is : " + reservationStatus);

		try {
			sourceStatusLogger.info("defaultStatusDistribution :" + defaultStatusDistribution);
			FileReader fr = new FileReader(".\\data.txt");
			BufferedReader br = new BufferedReader(fr);

			while ((defaultStatusDistribution = br.readLine()) != null) {
				sourceStatusLogger.info(" Status of Distribution Channel :" + defaultStatusDistribution);

				Wait.wait10Second();

				Assert.assertEquals(defaultStatusDistribution, reservationStatus,
						" Verified the Source Status and Reservation Status");
				Wait.wait10Second();
				sourceStatusLogger.info(" Verified the Status of Source and Reservation ");
			}
			br.close();
		} catch (IOException e) {
			sourceStatusLogger.info("File not found");
		}

	}

	public void saveSourceReservation(WebDriver driver) throws InterruptedException {
		Elements_Reservation ReservationPage = new Elements_Reservation(driver);
		double waittime = 0.12;
		long startTime = System.currentTimeMillis();
		Utility.ScrollToElement(ReservationPage.Save_SourceReservationDetails, driver);
		ReservationPage.Save_SourceReservationDetails.click();
		Wait.wait3Second();
		try {
			if (ReservationPage.Verify_Depos_policy.isDisplayed()) {
				ReservationPage.Click_Without_Deposit.click();
			}
		} catch (Exception e) {

		}
		try {
			if (ReservationPage.Verify_Toaster_Container.isDisplayed()) {
				String getTotasterTitle_ReservationSucess = ReservationPage.Toaster_Title.getText();
				String getToastermessage_ReservationSucess = ReservationPage.Toaster_Message.getText();
				Assert.assertEquals(getTotasterTitle_ReservationSucess, "Reservation Saved");
				long endTime = System.currentTimeMillis();
				double totalTime = (endTime - startTime);
				sourceStatusLogger.info(totalTime + " in Millsecs");
				double TotalTimeinsecs = totalTime / 1000;
				double ActualTime = TotalTimeinsecs - waittime - 3;
				sourceStatusLogger.info(ActualTime + " in secs");
				if (getToastermessage_ReservationSucess.endsWith("has been saved successfully"))
					;

			}
		} catch (Exception e) {

		}
		Wait.wait10Second();
	}

	public void changeStatusToDefault(WebDriver driver) throws InterruptedException, IOException {
		Elements_Inventory source = new Elements_Inventory(driver);
//		source.click_Inventory.click();
//		Wait.explicit_wait_visibilityof_webelement(source.clickDistributionMenu);
//		source.clickDistributionMenu.click();
		Wait.explicit_wait_visibilityof_webelement(source.distribute, driver);
		boolean distributeCheckbox = source.distribute.isSelected();
		String verifyDefaultStatus = new Select(source.defaultStatus).getFirstSelectedOption().getText();
		boolean defaultstatus0 = verifyDefaultStatus.equalsIgnoreCase("--Select--");
		boolean defaultstatus1 = verifyDefaultStatus.equalsIgnoreCase("On Hold");
		boolean defaultstatus2 = verifyDefaultStatus.equalsIgnoreCase("Reserved");
		boolean defaultstatus3 = verifyDefaultStatus.equalsIgnoreCase("Confirmed");
		boolean defaultstatus4 = verifyDefaultStatus.equalsIgnoreCase("Guaranteed");

		if (distributeCheckbox == false)

		{
			source.distribute.click();
			Wait.wait5Second();
			new Select(source.defaultStatus).selectByVisibleText("Confirmed");
			Wait.wait5Second();
			defaultStatusDistribution = new Select(source.defaultStatus).getFirstSelectedOption().getText();
			Wait.wait5Second();
			FileOutputStream fos = new FileOutputStream(".\\data.txt");
			byte b[] = defaultStatusDistribution.getBytes();
			fos.write(b);
			fos.close();
			Wait.wait5Second();
			source.clickSaveButtonDistribution.click();
			sourceStatusLogger.info(" Saved the changes ");
			Wait.wait10Second();
		}

		else if (distributeCheckbox == true && defaultstatus0 == true) {

			Wait.wait5Second();
			new Select(source.defaultStatus).selectByVisibleText("Confirmed");
			Wait.wait5Second();
			defaultStatusDistribution = new Select(source.defaultStatus).getFirstSelectedOption().getText();
			Wait.wait5Second();
			FileOutputStream fos = new FileOutputStream(".\\data.txt");
			byte b[] = defaultStatusDistribution.getBytes();
			fos.write(b);
			fos.close();
			Wait.wait5Second();
			source.clickSaveButtonDistribution.click();
			sourceStatusLogger.info(" Saved the changes ");
			Wait.wait10Second();

		}

		else if (distributeCheckbox == true && defaultstatus1 == true) {

			Wait.wait5Second();
			new Select(source.defaultStatus).selectByVisibleText("Confirmed");
			Wait.wait5Second();
			defaultStatusDistribution = new Select(source.defaultStatus).getFirstSelectedOption().getText();
			Wait.wait5Second();
			FileOutputStream fos = new FileOutputStream(".\\data.txt");
			byte b[] = defaultStatusDistribution.getBytes();
			fos.write(b);
			fos.close();
			Wait.wait5Second();
			source.clickSaveButtonDistribution.click();
			sourceStatusLogger.info(" Saved the changes ");
			Wait.wait10Second();

		}

		else if (distributeCheckbox == true && defaultstatus2 == true) {

			Wait.wait5Second();
			new Select(source.defaultStatus).selectByVisibleText("Confirmed");
			Wait.wait5Second();
			defaultStatusDistribution = new Select(source.defaultStatus).getFirstSelectedOption().getText();
			Wait.wait5Second();
			FileOutputStream fos = new FileOutputStream(".\\data.txt");
			byte b[] = defaultStatusDistribution.getBytes();
			fos.write(b);
			fos.close();
			Wait.wait5Second();
			source.clickSaveButtonDistribution.click();
			sourceStatusLogger.info(" Saved the changes ");
			Wait.wait10Second();
		}

		sourceStatusLogger.info(" Changed the status to Default ");

	}

}
