package com.innroad.inncenter.pageobjects;

import static org.testng.AssertJUnit.assertTrue;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_PrintIconCheck;

public class PrintIconCheck {
	
	public static Logger PrintIconLogger = Logger.getLogger("PrintIconLogger");

	public void clickPrintIcon(WebDriver driver,ArrayList<String>test_steps) throws InterruptedException
	{
		Elements_PrintIconCheck PrintIconCheckPage = new Elements_PrintIconCheck(driver);
		Wait.explicit_wait_visibilityof_webelement(PrintIconCheckPage.clickPrintIcon, driver);
		Utility.ScrollToElement(PrintIconCheckPage.clickPrintIcon, driver);
		PrintIconCheckPage.clickPrintIcon.click();
		test_steps.add("Clicked on Print Icon in Reservation Search");
		PrintIconLogger.info("Clicked on Print Icon in Reservation Search");
	}
	
	
	public void closeReservationReports(WebDriver driver,ArrayList<String>test_steps) throws InterruptedException
	{
		Elements_PrintIconCheck PrintIconCheckPage = new Elements_PrintIconCheck(driver);
		Wait.explicit_wait_visibilityof_webelement(PrintIconCheckPage.ClosePrintIconPopup, driver);
		PrintIconCheckPage.ClosePrintIconPopup.click();
		test_steps.add(" Closed the Reservation Reports PopUp");
		PrintIconLogger.info(" Closed the Reservation Reports PopUp");
	}

	public void VerifyPrintPopup(WebDriver driver,ArrayList<String>test_steps)
	{
		Elements_PrintIconCheck PrintIconCheckPage = new Elements_PrintIconCheck(driver);
		Wait.explicit_wait_visibilityof_webelement_120(PrintIconCheckPage.PrintPopUp, driver);
		if(PrintIconCheckPage.PrintPopUp.isDisplayed())
		{
			test_steps.add("Reservation Reports PopUp is Available");
			PrintIconLogger.info("Reservation Reports PopUp is Available");
			assertEquals(PrintIconCheckPage.PrintPopUp.getText(), "Reservation Reports","Reservation Reports Popup is not Showing");
			test_steps.add("Successfully Verified Reservation Reports PopUp");
		}
	}
	
	public void VerifyRadioButtons(WebDriver driver,ArrayList<String>test_steps) throws InterruptedException
	{
		List<String> requriedData=  new ArrayList<>(Arrays.asList("List Report","Guest Statement","Guest Registration","Mailing Details",
				"Include Individual Tax Items","Detailed Reservation List","Include Total Revenue","Include Tax Items"));
		
		List<WebElement> radioButtons =driver.findElements(By.xpath(OR.ResRadioButtons));
		List<WebElement> OnlyRadioButtonsText =driver.findElements(By.xpath(OR.ResRadioButtonsText));
		List<WebElement> DialogBoxText =driver.findElements(By.xpath(OR.ResDialogBoxText));
		
		PrintIconLogger.info("Total Elements in the Reservations Reports : " + radioButtons.size());
		test_steps.add("Total Elements in the Reservations Reports : " + radioButtons.size());
		
		assertEquals(radioButtons.get(0).isSelected(),true);
		PrintIconLogger.info("By Default "+radioButtons.get(0).getText()+ "Radio Button is Selected");
		test_steps.add("By Default "+radioButtons.get(0).getText()+ "Radio Button is Selected");
		
		assertEquals(DialogBoxText.get(0).isEnabled(),true);
		PrintIconLogger.info("By Default "+DialogBoxText.get(0).getText()+ "Check Box is Selected");
		test_steps.add("By Default "+DialogBoxText.get(0).getText()+ "Check Box is Selected");
	
	
		for (int i=0;i<radioButtons.size();i++) 
		{
			radioButtons.get(i).click();
			Wait.wait5Second();
			PrintIconLogger.info("'"+OnlyRadioButtonsText.get(i).getText() +"' Radio Button is Clicked");
			test_steps.add("'"+OnlyRadioButtonsText.get(i).getText() +"' Radio Button is Clicked");
						
			if(i==0 || i==1 || i==2) {
				if(DialogBoxText.get(i).isDisplayed()) {
					PrintIconLogger.info(OnlyRadioButtonsText.get(i).getText()+" Radio Button Contains Check Box with the Name: "+DialogBoxText.get(i).getText());
					test_steps.add(OnlyRadioButtonsText.get(i).getText()+" Radio Button Contains Check Box with the Name: "+DialogBoxText.get(i).getText());}
				assertTrue(requriedData.contains(DialogBoxText.get(i).getText()));
			}
			
			assertTrue(requriedData.contains(OnlyRadioButtonsText.get(i).getText()));
		}
		test_steps.add("Successfully Verified All Radio Buttons or displayed or not");
	}

	public void VerifyFooterElements(WebDriver driver, ArrayList<String> test_steps) {
		
		Elements_PrintIconCheck PrintIconCheckPage = new Elements_PrintIconCheck(driver);
		
		assertTrue(PrintIconCheckPage.ResPrintButton.isDisplayed());
		PrintIconLogger.info(PrintIconCheckPage.ResPrintButton.getText() +" Button is Displayed");
		test_steps.add(PrintIconCheckPage.ResPrintButton.getText() +" Button is Displayed");
		
		assertTrue(PrintIconCheckPage.ResEmailButton.isDisplayed());
		PrintIconLogger.info(PrintIconCheckPage.ResEmailButton.getText() +" Button is Displayed");
		test_steps.add(PrintIconCheckPage.ResEmailButton.getText() +"Button is Displayed");
		
		assertTrue(PrintIconCheckPage.ResExportButton.isDisplayed());
		PrintIconLogger.info(PrintIconCheckPage.ResExportButton.getText() +" Button is Displayed");
		test_steps.add(PrintIconCheckPage.ResExportButton.getText() +" Button is Displayed");
		if(PrintIconCheckPage.ResExportButton.isDisplayed())
		{
			PrintIconCheckPage.ExportlistButton.click();
			test_steps.add("Clicked Export list Icon ");
		}
		
		List<WebElement> ExportButtons =driver.findElements(By.xpath(OR.AllExportsButtons));
		for (int i=0;i<ExportButtons.size();i++) 
		{
			PrintIconLogger.info("In Export '"+ExportButtons.get(i).getText() +"' type is Displayed");
			test_steps.add("In Export '"+ExportButtons.get(i).getText() +"' type is Displayed");	
		}
	}
}
