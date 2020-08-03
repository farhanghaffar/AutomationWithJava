package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.Message;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.innroad.inncenter.interfaces.IDocumentTemplates;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Setup;
import com.innroad.inncenter.testcore.TestCore;
import com.innroad.inncenter.utils.EmailUtils;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_DocumentTemplates;

public class DocumnetTemplates implements IDocumentTemplates {

	public static Logger doctempLogger = Logger.getLogger("Setup");

	public ArrayList<String> CreateNewDocument(WebDriver driver,ArrayList<String> test_steps) {
		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);

		Wait.waitUntilPresenceOfElementLocated(OR.Doc_NewButton, driver);
		doctemp.Doc_NewButton.click();

		Wait.waitUntilPresenceOfElementLocated(OR.DocTempPage, driver);
		assertTrue(doctemp.DocTempPage.getText().contains("Document Template"),
				"Document Template page does not display");
		test_steps.add("Click New Document");
		doctempLogger.info("Click New Document");
		return test_steps;

		// test_steps.add(" " );
		// doctempLogger.info("");

	}

	public String DocumnetName(WebDriver driver, String DocName) throws InterruptedException {

		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.DocumentName, driver);
		doctemp.DocumentName.sendKeys(DocName);
		Wait.wait2Second();
		assertTrue(doctemp.DocumentName.getAttribute("value").contains(DocName), "Failed: Document Name");
		doctempLogger.info("Documnet Name: " + DocName);
		return DocName;
	}

	public ArrayList<String> DocumnetDescription(WebDriver driver, String DocDescription,ArrayList<String> test_steps) throws InterruptedException {

		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.DocumentDescription, driver);
		doctemp.DocumentDescription.sendKeys(DocDescription);
		Wait.wait2Second();
		assertTrue(doctemp.DocumentDescription.getAttribute("value").contains(DocDescription),
				"Failed: Document Description");
		test_steps.add("DocumnetDescription: " + DocDescription);
		doctempLogger.info("DocumnetDescription: " + DocDescription);
		return test_steps;
	}

	public ArrayList<String> AssociateSources(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {

		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.AssociateSource, driver);
		Utility.ScrollToElement(doctemp.AssociateSource, driver);
		doctemp.AssociateSource.click();
		//Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		System.out.println("frame Switched");
		//Wait.wait2Second();
		Wait.waitUntilPresenceOfElementLocated(OR.AssociateSourcePopup, driver);
		System.out.println(doctemp.AssociateSourcePopup.getText());
		assertTrue(doctemp.AssociateSourcePopup.getText().contains("Source Picker"),
				"Associate Source Popup does not display");
		Wait.explicit_wait_xpath(OR.Source_Inncenter_CheckBox, driver);
		doctemp.Source_Inncenter_CheckBox.click();
		assertTrue(doctemp.Source_Inncenter_CheckBox.isSelected(), "Failed: AssociateSource");
		doctemp.SavePopup.click();
		driver.switchTo().defaultContent();
		test_steps.add("Associate Source : Inncenter ");
		doctempLogger.info("Associate Source : Inncenter");
		return test_steps;

	}

	public ArrayList<String> AssociateProperties(WebDriver driver, String AssociateProp,ArrayList<String> test_steps) throws InterruptedException {
		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);
		Wait.explicit_wait_xpath(OR.AssociateProperty, driver);
		//Wait.wait5Second();
		Utility.ScrollToElement(doctemp.AssociateProperty, driver);
		doctemp.AssociateProperty.click();
		//Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		System.out.println("frame Switched");
		//Wait.wait2Second();
		Wait.waitUntilPresenceOfElementLocated(OR.AssociatePropertyPopup, driver);
		assertTrue(doctemp.AssociatePropertyPopup.getText().contains("Property Picker"),
				"Associate Property Popup does not display");
		Wait.explicit_wait_xpath(OR.Select_AssociateFunction, driver);
		//new Select(doctemp.Select_AssociateFunction).selectByVisibleText(AssociateProp);
		doctemp.Property_PickAll.click();
		doctemp.SavePopup.click();
		driver.switchTo().defaultContent();
		test_steps.add("Associate Property : " + AssociateProp);
		doctempLogger.info("Associate Property : " + AssociateProp);
		return test_steps;
	}

	public ArrayList<String> Function_ScheduleDetail(WebDriver driver, String ScheduleDetail_Days,ArrayList<String> test_steps) throws InterruptedException {

		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);
		JavascriptExecutor JSE = (JavascriptExecutor) driver;
		JSE.executeScript("arguments[0].scrollIntoView();", doctemp.Function_ScheduleDays);
		Wait.waitUntilPresenceOfElementLocated(OR.Function_ScheduleDays, driver);
		doctemp.Function_ScheduleDays.clear();
		Wait.wait1Second();
		doctemp.Function_ScheduleDays.sendKeys(ScheduleDetail_Days);
		Wait.wait1Second();
		System.out.println(doctemp.Function_ScheduleDays.getAttribute("value"));
		assertTrue(doctemp.Function_ScheduleDays.getAttribute("value").contains(ScheduleDetail_Days),
				"Failed: Function Schedule Detail");

		test_steps.add("Schedule Detail Days: " + ScheduleDetail_Days);
		doctempLogger.info("Schedule Detail Days:  " + ScheduleDetail_Days);
		return test_steps;
	}

	public ArrayList<String> SaveDocument(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {
		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", doctemp.Document_Save);
		Wait.waitUntilPresenceOfElementLocated(OR.Document_Save, driver);
		doctemp.Document_Done.click();
		Wait.wait2Second();
		test_steps.add("Click Save");
		doctempLogger.info("Click Save ");
		Wait.wait5Second();

		try 
		{ 
			driver.switchTo().alert().accept();  
		}  
		catch (NoAlertPresentException e) 
		{ 
			System.out.println("No Alert Present");
			return test_steps;
		}  		
		return test_steps;

	}

	public ArrayList<String> DoneDocument(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {
		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.Document_Done, driver);
		doctemp.Document_Done.click();
		Wait.wait2Second();
		test_steps.add("Click Done ");
		doctempLogger.info("Click Done ");
		return test_steps;

	}

	public ArrayList<String> VerifyDocument(WebDriver driver, String DocName,ArrayList<String> test_steps) throws InterruptedException {
		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", doctemp.CreatedDocument_Pages);
		int Size = driver.findElements(By.xpath(OR.CreatedDocument_Pages)).size();
		if (Size > 2) {
			System.out.println("Pages : " + (Size - 1));
		}
		boolean found = false;
		for (int Page = 1; Page < Size; Page++) {

			Wait.WaitForElement(driver, OR.DocumentName_list);
			int count = driver.findElements(By.xpath(OR.DocumentName_list)).size();

			for (int i = 0; i < count; i++) {
				Wait.wait2Second();
				int rowNumber = i + 1;
				String docName = driver.findElement(By.xpath("(" + OR.DocumentName_list + ")[" + rowNumber + "]"))
						.getText();

				System.out.println("RDOC :" + DocName + "::" + "GDOC :" + docName);
				if (docName.equals(DocName)) {
					found = true;
					break;
				}
			}

			if (found) {
				if (Page != 1) {
					String firstPagePath = "//tr[@class='TextDefault']/td//following-sibling::*[text()='" + 1 + "']";
					jse.executeScript("arguments[0].scrollIntoView(true);",
							driver.findElement(By.xpath(firstPagePath)));
					driver.findElement(By.xpath(firstPagePath)).click();
					doctempLogger.info("Move to Page " + 1);
					Wait.WaitForElement(driver, OR.DocumentName_list);
				}
				break;
			} else {
				int NextPage = Page + 1;
				String NextPagePath = "//tr[@class='TextDefault']/td//following-sibling::*[text()='" + NextPage + "']";
				jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(NextPagePath)));
				driver.findElement(By.xpath(NextPagePath)).click();
				doctempLogger.info("Move to Page " + NextPage);
				Wait.WaitForElement(driver, OR.DocumentName_list);
			}
		}
		if (found) {
			test_steps.add(DocName + " Document Exist");
			doctempLogger.info(DocName + " Document Exist");
		} else {
			assertTrue(false, "Document not found");
		}
		return test_steps;

	}

	public void SelectDefaultDocumentCheckBox(WebDriver driver) throws InterruptedException {
		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);
		// Wait.explicit_wait_xpath(OR.DefaultTempCheckBox);
		Wait.wait5Second();
		if (!doctemp.DefaultTempCheckBox.isSelected()) {
			doctemp.DefaultTempCheckBox.click();
			assertTrue(doctemp.DefaultTempCheckBox.isSelected(), "Failed:Default Template is not selected");
		} else {
			doctemp.DefaultTempCheckBox.click();
			assertTrue(!doctemp.DefaultTempCheckBox.isSelected(), "Failed:Default Template is  selected");
		}
	}

	public void SelectSystemDocument(WebDriver driver, String DocumentNumber) throws InterruptedException {
		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);
		Wait.wait5Second();
		WebElement SelectedDoc = driver.findElement(By.id(OR.SelectSystemDoc + DocumentNumber));
		Wait.wait5Second();
		SelectedDoc.click();
		Wait.wait2Second();
		WebElement DocumentTemp = driver.findElement(By.id("ContentArea"));
		assertTrue(DocumentTemp.isDisplayed(), "Failed: System Docuement not selected");

	}

	public ArrayList<String> DoneDocumentButton(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException {
		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);
		// Wait.waitUntilPresenceOfElementLocated(OR.Document_Done);
		doctemp.Document_Done.click();
		Wait.wait2Second();
		test_steps.add("Click Done ");
		doctempLogger.info("Click Done ");
		return test_steps;

	}

	public ArrayList<String> DeleteDocument(WebDriver driver, String DocName,ArrayList<String> test_steps) throws InterruptedException {
		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", doctemp.CreatedDocument_Pages);
		int Size = driver.findElements(By.xpath(OR.CreatedDocument_Pages)).size();
		if (Size > 2) {
			// System.out.println("Pages : " + (Size - 1));
		}
		boolean found = false;
		for (int Page = 1; Page < Size; Page++) {
			Wait.WaitForElement(driver, OR.DocumentName_list);
			int count = driver.findElements(By.xpath(OR.DocumentName_list)).size();
			// System.out.println(count);
			for (int i = 0; i < count; i++) {
				Wait.wait2Second();
				int rowNumber = i + 1;
				String docName = driver.findElement(By.xpath("(" + OR.DocumentName_list + ")[" + rowNumber + "]"))
						.getText();
				// System.out.println("Document name is " + docName);
				if (docName.equals(DocName)) {
					found = true;
					// System.out.println("Documen Found");
					WebElement CheckBox = driver
							.findElement(By.xpath("(" + OR.DeleteDocument_CheckBox + ")[" + rowNumber + "]"));
					Utility.ScrollToElement(CheckBox, driver);
					CheckBox.click();
					// System.out.println("click Check");
					assertTrue(CheckBox.isSelected(), "Failed: Delete  ChekBox");
					break;
				}
			}
			if (found) {
				if (Page != 1) {
					String firstPagePath = "//tr[@class='TextDefault']/td//following-sibling::*[text()='" + 1 + "']";
					jse.executeScript("arguments[0].scrollIntoView(true);",
							driver.findElement(By.xpath(firstPagePath)));
					driver.findElement(By.xpath(firstPagePath)).click();
					doctempLogger.info("Move to Page " + 1);
					Wait.WaitForElement(driver, OR.DocumentName_list);
				}
				break;
			} else {
				int NextPage = Page + 1;
				String NextPagePath = "//tr[@class='TextDefault']/td//following-sibling::*[text()='" + NextPage + "']";
				jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(NextPagePath)));
				driver.findElement(By.xpath(NextPagePath)).click();
				doctempLogger.info("Move to Page " + NextPage);
				Wait.WaitForElement(driver, OR.DocumentName_list);
			}
		}
		if (found) {
			doctemp.Document_DeleteButton.click();
			test_steps.add(DocName + " Document Delete");
			doctempLogger.info(DocName + " Document Delete");
		} else {
			assertTrue(false, "Document '" + DocName + "' not found");
		}
		return test_steps;
	}

	public void select_DefaultTemplate(WebDriver driver, ArrayList<String> test_steps){
		String defaultTemp="//input[@id='MainContent_chkDefault']";
		Wait.WaitForElement(driver, defaultTemp);
		driver.findElement(By.xpath(defaultTemp)).click();
	}

	public ArrayList<String> AssociateFunctionsMoreFunctions(WebDriver driver, String FuncTrigger, String FuncEvent,ArrayList<String> test_steps) throws InterruptedException {
		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);
		Wait.explicit_wait_xpath(OR.AssociateFunction, driver);
		Wait.wait5Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//a[@id='MainContent_dgScheduleFunctions_lnkFunctionplus_0']")));
		driver.findElement(By.xpath("//a[@id='MainContent_dgScheduleFunctions_lnkFunctionplus_0']")).click();
		Wait.wait2Second();
		/*	driver.switchTo().frame("dialog-body0");
		System.out.println("frame Switched");
		Wait.wait2Second();
		Wait.waitUntilPresenceOfElementLocated(OR.AssociateFunctionPopup, driver);
		assertTrue(doctemp.AssociateFunctionPopup.getText().contains("Function Picker"),
				"Associate Fiunction Popup does not display");
		doctemp.Function_Email_CheckBox.click();
		assertTrue(doctemp.Function_Email_CheckBox.isSelected(), "Failed: AssociateFunction");
		doctemp.SavePopup.click();
		driver.switchTo().defaultContent();
		test_steps.add("Associate Function : Confirmation Email");
		doctempLogger.info("Associate Function : Confirmation Email");

		JavascriptExecutor JSE = (JavascriptExecutor) driver;
		JSE.executeScript("arguments[0].scrollIntoView();", doctemp.Function_Trigger);
		Wait.waitUntilPresenceOfElementLocated(OR.Function_Trigger, driver);
		 */
		//doctemp.Function_Trigger.click();

		int count=driver.findElements(By.xpath("//select[starts-with(@id,'MainContent_dgScheduleFunctions_drpDimension')]")).size();

		WebElement Function_Trigger= driver.findElement(By.xpath("(//select[starts-with(@id,'MainContent_dgScheduleFunctions_drpDimension')])["+count+"]"));
		JavascriptExecutor JSE = (JavascriptExecutor) driver;
		JSE.executeScript("arguments[0].scrollIntoView();", Function_Trigger);
		Wait.wait2Second();
		Function_Trigger.click();

		Wait.wait2Second();
		new Select(Function_Trigger).selectByVisibleText(FuncTrigger);
		Wait.wait5Second();
		//assertTrue(Function_Trigger.getText().contains(FuncTrigger), "Failed: Function Trigger");
		test_steps.add("Function Trigger: " + FuncTrigger);
		doctempLogger.info("Function Trigger:  " + FuncTrigger);

		Wait.wait5Second();

		WebElement Function_Event = driver.findElement(By.xpath("(//select[starts-with(@id,'MainContent_dgScheduleFunctions_drpTrigger')])["+count+"]"));

		JSE.executeScript("arguments[0].scrollIntoView();", Function_Event);
		//Wait.waitUntilPresenceOfElementLocated(OR.Function_Event, driver);
		Function_Event.click();
		Wait.wait2Second();
		new Select(Function_Event).selectByVisibleText(FuncEvent);
		Wait.wait5Second();
		//assertTrue(doctemp.Function_Event.getText().contains(FuncEvent), "Failed: Function Event");
		test_steps.add("Function Event: " + FuncEvent);
		doctempLogger.info("Function Event:  " + FuncEvent);
		return test_steps;

	}

	public void click_Content(WebDriver driver, ArrayList<String> test_steps){
		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);
		Wait.WaitForElement(driver, OR.Content);
		doctemp.Content.click();
		test_steps.add("Click on content");
	}

	public void enter_ContentFields(WebDriver driver, ArrayList<String> test_steps) throws Exception{
		String custom="//td[@id='Custom fields']";
		Wait.WaitForElement(driver, custom);
		driver.findElement(By.xpath(custom)).click();
		Wait.wait2Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		String accountName="//td[@id='<< ArrivalDate >>']";
		Wait.WaitForElement(driver, accountName);
		jse.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath(accountName)));
		jse.executeScript("window.scrollBy(0,-300)");
		driver.findElement(By.xpath(accountName)).click();
		String name="//span[contains(text(),'<< ArrivalDate >>')]";
		WebElement frame=driver.findElement(By.id("MainContent_innDocumentEditor_tw"));
		driver.switchTo().frame(frame);
		new Actions(driver).sendKeys(driver.findElement(By.xpath(name)), Keys.END).sendKeys(Keys.ENTER).build().perform();
		driver.switchTo().defaultContent();

		Wait.WaitForElement(driver, custom);
		driver.findElement(By.xpath(custom)).click();
		Wait.wait2Second();
		accountName="//td[@id='<< ConfirmationNumber >>']";
		Wait.WaitForElement(driver, accountName);
		jse.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath(accountName)));
		jse.executeScript("window.scrollBy(0,-300)");
		name="//span[contains(text(),'<< ConfirmationNumber >>')]";
		driver.findElement(By.xpath(accountName)).click();
		driver.switchTo().frame(frame);
		new Actions(driver).sendKeys(driver.findElement(By.xpath(name)), Keys.END).sendKeys(Keys.ENTER).build().perform();
		driver.switchTo().defaultContent();

		Wait.WaitForElement(driver, custom);
		driver.findElement(By.xpath(custom)).click();
		Wait.wait2Second();
		accountName="//td[@id='<< DepartureDate >>']";
		Wait.WaitForElement(driver, accountName);
		jse.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath(accountName)));
		jse.executeScript("window.scrollBy(0,-300)");
		driver.findElement(By.xpath(accountName)).click();
		name="//span[contains(text(),'<< DepartureDate >>')]";
		driver.switchTo().frame(frame);
		new Actions(driver).sendKeys(driver.findElement(By.xpath(name)), Keys.END).sendKeys(Keys.ENTER).build().perform();
		driver.switchTo().defaultContent();



		Wait.WaitForElement(driver, custom);
		driver.findElement(By.xpath(custom)).click();
		Wait.wait2Second();
		accountName="//td[@id='<< GuestFirstName >>']";
		Wait.WaitForElement(driver, accountName);
		jse.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath(accountName)));
		jse.executeScript("window.scrollBy(0,-300)");
		driver.findElement(By.xpath(accountName)).click();
		name="//span[contains(text(),'<< GuestFirstName >>')]";
		driver.switchTo().frame(frame);
		new Actions(driver).sendKeys(driver.findElement(By.xpath(name)), Keys.END).sendKeys(Keys.ENTER).build().perform();
		driver.switchTo().defaultContent();

		Wait.WaitForElement(driver, custom);
		driver.findElement(By.xpath(custom)).click();
		Wait.wait2Second();
		accountName="//td[@id='<< Rates >>']";
		Wait.WaitForElement(driver, accountName);
		jse.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath(accountName)));
		jse.executeScript("window.scrollBy(0,-300)");
		driver.findElement(By.xpath(accountName)).click();
		name="//span[contains(text(),'<< Rates >>')]";
		driver.switchTo().frame(frame);
		new Actions(driver).sendKeys(driver.findElement(By.xpath(name)), Keys.END).sendKeys(Keys.ENTER).build().perform();
		driver.switchTo().defaultContent();

		Wait.WaitForElement(driver, custom);
		driver.findElement(By.xpath(custom)).click();
		Wait.wait2Second();
		accountName="//td[@id='<< Notes >>']";
		Wait.WaitForElement(driver, accountName);
		jse.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath(accountName)));
		jse.executeScript("window.scrollBy(0,-300)");
		driver.findElement(By.xpath(accountName)).click();
		name="//span[contains(text(),'<< Notes >>')]";
		driver.switchTo().frame(frame);
		new Actions(driver).sendKeys(driver.findElement(By.xpath(name)), Keys.END).sendKeys(Keys.ENTER).build().perform();
		driver.switchTo().defaultContent();
	}

	public void delete_DocTemplate(WebDriver driver, ArrayList<String> test_steps,String docName) throws InterruptedException{
		String doc="//a[contains(text(),'"+docName+"')]/../following-sibling::td[2]/span/input";
		Wait.wait5Second();
		Wait.WaitForElement(driver, doc);
		Wait.explicit_wait_xpath(doc, driver);
		driver.findElement(By.xpath(doc)).click();
		test_steps.add("Selecting document : "+docName);

		String del="//input[@id='MainContent_btnDelete']";
		Wait.WaitForElement(driver, del);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(del)));
		Wait.wait3Second();
		driver.findElement(By.xpath(del)).click();
		test_steps.add("Click on delete");
		Wait.wait3Second();
		if(driver.findElements(By.xpath(doc)).size()>0){
			test_steps.add("Sucessfully deleted the document : "+docName);
		}else{
			Assert.assertFalse(false, "Docuemnt not deleted : "+docName);
		}
	}

	public void deleteAllDocTemp(WebDriver driver) throws InterruptedException{

		String doc="//table[@id='MainContent_dgDocumentsList']//tr[@class='dgItem']/td[3]/span/input";


		String pol=null;

		while(true){
			int count =driver.findElements(By.xpath(doc)).size();
			if(count>0){
				String all="//table[@id='MainContent_dgDocumentsList']//tr[@class='dgItem']/td[3]/span/input";
				Wait.WaitForElement(driver, all);

				for(int i=1;i<=count;i++){
					pol="(//table[@id='MainContent_dgDocumentsList']//tr[@class='dgItem']/td[3]/span/input)["+i+"]";
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					WebElement element = driver.findElement(By.xpath(pol));
					jse.executeScript("window.scrollTo(0, 50)");
					jse.executeScript("arguments[0].scrollIntoView();", element);
					Wait.wait2Second();
					driver.findElement(By.xpath(pol)).click();
					if(i==count){
						driver.findElement(By.xpath("//input[@id='MainContent_btnDelete']")).click();
						Wait.wait5Second();
					}
				}
				count=driver.findElements(By.xpath(pol)).size();
				if(count==0){
					break;
				}
			}else{
				break;
			}
		}
	}

	public ArrayList<String> AssociateWithFunctions(WebDriver driver, String FuncTrigger, String FuncEvent,String function,ArrayList<String> test_steps) throws InterruptedException {
		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);
		Wait.explicit_wait_xpath(OR.AssociateFunction, driver);
		Wait.wait5Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", doctemp.AssociateFunction);
		doctemp.AssociateFunction.click();
		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		System.out.println("frame Switched");
		Wait.wait2Second();
		Wait.waitUntilPresenceOfElementLocated(OR.AssociateFunctionPopup, driver);
		assertTrue(doctemp.AssociateFunctionPopup.getText().contains("Function Picker"),
				"Associate Fiunction Popup does not display");

		String fun="//td[contains(text(),'"+function.trim()+"')]/../td/input";
		Wait.wait2Second();
		driver.findElement(By.xpath(fun)).click();
	
		/*String associateFun="//td[contains(text(),'"+associateFunction.trim()+"')]/../td/input";
		driver.findElement(By.xpath(associateFun)).click();*/
		//doctemp.Function_Email_CheckBox.click();
		assertTrue(driver.findElement(By.xpath(fun)).isSelected(), "Failed: AssociateFunction");
		doctemp.SavePopup.click();
		driver.switchTo().defaultContent();
		test_steps.add("Associate Function : "+function);
		doctempLogger.info("Associate Function : "+function);

		/*if(!function.equalsIgnoreCase("Confirmation Letter")) {

			JavascriptExecutor JSE = (JavascriptExecutor) driver;
			JSE.executeScript("arguments[0].scrollIntoView();", doctemp.Function_Trigger);
			Wait.waitUntilPresenceOfElementLocated(OR.Function_Trigger, driver);

			doctemp.Function_Trigger.click();
			//Wait.wait2Second();
			new Select(doctemp.Function_Trigger).selectByVisibleText(FuncTrigger);
			//Wait.wait2Second();
			assertTrue(doctemp.Function_Trigger.getText().contains(FuncTrigger), "Failed: Function Trigger");

			test_steps.add("Function Trigger: " + FuncTrigger);
			doctempLogger.info("Function Trigger:  " + FuncTrigger);
			JSE.executeScript("arguments[0].scrollIntoView();", doctemp.Function_Event);
			Wait.waitUntilPresenceOfElementLocated(OR.Function_Event, driver);
			doctemp.Function_Event.click();
			//Wait.wait2Second();
			new Select(doctemp.Function_Event).selectByVisibleText(FuncEvent);
			//Wait.wait2Second();
			assertTrue(doctemp.Function_Event.getText().contains(FuncEvent), "Failed: Function Event");
			test_steps.add("Function Event: " + FuncEvent);
			doctempLogger.info("Function Event:  " + FuncEvent);
		}
		else {
			//Wait.wait5Second();
			//JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", doctemp.AssociateFunction);
			doctemp.AssociateFunction.click();
			//Wait.wait2Second();
			driver.switchTo().frame("dialog-body0");
			System.out.println("frame Switched");
			//Wait.wait2Second();
			Wait.waitUntilPresenceOfElementLocated(OR.AssociateFunctionPopup, driver);
			assertTrue(doctemp.AssociateFunctionPopup.getText().contains("Function Picker"),
					"Associate Fiunction Popup does not display");
			doctemp.Function_Email_CheckBox.click();
			assertTrue(doctemp.Function_Email_CheckBox.isSelected(), "Failed: AssociateFunction");
			doctemp.SavePopup.click();
			driver.switchTo().defaultContent();
			test_steps.add("Associate Function : Confirmation Email");
			doctempLogger.info("Associate Function : Confirmation Email");

			fun="//select[@id='MainContent_dgScheduleFunctions_drpDimension_1']";
			String trigger="//select[@id='MainContent_dgScheduleFunctions_drpDimension_1']";
			String eve="//select[@id='MainContent_dgScheduleFunctions_drpTrigger_1']";

			JavascriptExecutor JSE = (JavascriptExecutor) driver;
			JSE.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(fun)));
			Wait.waitUntilPresenceOfElementLocated(trigger, driver);

			driver.findElement(By.xpath(trigger)).click();
			//Wait.wait2Second();
			new Select(driver.findElement(By.xpath(trigger))).selectByVisibleText(FuncTrigger);
			Wait.wait2Second();
			assertTrue(driver.findElement(By.xpath(trigger)).getText().contains(FuncTrigger), "Failed: Function Trigger");

			test_steps.add("Function Trigger: " + FuncTrigger);
			doctempLogger.info("Function Trigger:  " + FuncTrigger);
			JSE.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(eve)));
			Wait.waitUntilPresenceOfElementLocated(eve, driver);
			driver.findElement(By.xpath(eve)).click();
			//Wait.wait2Second();
			new Select(driver.findElement(By.xpath(eve))).selectByVisibleText(FuncEvent);
			//Wait.wait2Second();
			assertTrue(driver.findElement(By.xpath(eve)).getText().contains(FuncEvent), "Failed: Function Event");
			test_steps.add("Function Event: " + FuncEvent);
			doctempLogger.info("Function Event:  " + FuncEvent);
		}*/
		return test_steps;

	}

	public void enter_SpecificContentFields(WebDriver driver, ArrayList<String> test_steps,String attribute) throws Exception{
		if(attribute.contains("/")){
			String custom="//td[@id='Custom fields']";
			Wait.WaitForElement(driver, custom);
			driver.findElement(By.xpath(custom)).click();
			Wait.wait2Second();
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			String[] str=attribute.split("/");
			for(int i=1;i<=str.length;i++){
				Wait.wait3Second();
				if(str[i-1].equals("RoomClass")){
					str[i-1]="RooomClass";
				}else if (str[i-1].equals("AdultsChildren")) {
					str[i-1]="Ad/Ch";
				}
				String accountName="//td[@id='<< "+str[i-1].trim()+" >>']";
				System.out.println(accountName);
				//Wait.WaitForElement(driver, accountName);
				jse.executeScript("arguments[0].scrollIntoView(true);",
						driver.findElement(By.xpath(accountName)));
				jse.executeScript("window.scrollBy(0,-300)");
				driver.findElement(By.xpath(accountName)).click();
				String name="//span[contains(text(),'<< "+str[i-1].trim()+" >>')]";
				WebElement frame=driver.findElement(By.id("MainContent_innDocumentEditor_tw"));
				if(name.contains("AdultsChildren")){
					name="//span[contains(text(),'<< Ad/Ch >>')]";
				}
				
				if(name.contains("RoomClass")){
					name="//span[contains(text(),'<< RooomClass >>')]";
				}
				driver.switchTo().frame(frame);
				new Actions(driver).sendKeys(driver.findElement(By.xpath(name)), Keys.END).sendKeys(Keys.ENTER).build().perform();
				driver.switchTo().defaultContent();
				if(i!=str.length){
					custom="//td[@id='Custom fields']";
					//Wait.WaitForElement(driver, custom);
					driver.findElement(By.xpath(custom)).click();
					Wait.wait2Second();
					System.out.println(custom);
					test_steps.add("Added "+str[i-1].trim() +" to document template");
				}
			}
		}else{
			String custom="//td[@id='Custom fields']";
			//Wait.WaitForElement(driver, custom);
			driver.findElement(By.xpath(custom)).click();
			Wait.wait2Second();
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			String accountName="//td[@id='<< "+attribute.trim()+" >>']";
			if(attribute.trim().contains("AdultsChildren")) {
				accountName="//td[@id='<< Ad/Ch >>']";
			}
			if(accountName.contains("RoomClass")){
				accountName="//td[@id='<< RooomClass >>']";
			}
			
			Wait.WaitForElement(driver, accountName);
			jse.executeScript("arguments[0].scrollIntoView(true);",
					driver.findElement(By.xpath(accountName)));
			jse.executeScript("window.scrollBy(0,-300)");
			driver.findElement(By.xpath(accountName)).click();
			String name="//span[contains(text(),'<< "+attribute.trim()+" >>')]";
			//WebElement frame=driver.findElement(By.id("MainContent_innDocumentEditor_tw"));
			if(name.contains("AdultsChildren")){
				name="//span[contains(text(),'<< Ad/Ch >>')]";
			}
			if(name.contains("RoomClass")){
				name="//span[contains(text(),'<< RooomClass >>')]";
			}
			System.out.println(accountName);
			test_steps.add("Added "+attribute.trim() +" to document template");
			driver.switchTo().frame(driver.findElement(By.id("MainContent_innDocumentEditor_tw")));
			new Actions(driver).sendKeys(driver.findElement(By.xpath(name)), Keys.END).sendKeys(Keys.ENTER).build().perform();
			driver.switchTo().defaultContent();
		}
	}

	public void enterSpecificContentFields(WebDriver driver, ArrayList<String> test_steps, String attribute) throws Exception{
		if(attribute.contains("/")){
			String custom="//td[@id='Custom fields']";
			Wait.waitForElementToBeClickable(By.xpath(custom), driver);
//			Wait.WaitForElement(driver, custom);
			driver.findElement(By.xpath(custom)).click();
//			Wait.wait2Second();
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			String[] expContent=attribute.split("/");
			ArrayList<String> actContent = new ArrayList<>();
			int size = driver.findElements(By.xpath("//td[@id='Custom fields']/../../../../div/table/tbody/tr")).size();
			for (int i = 1; i <= size; i++) {
				actContent.add(driver.findElement(By.xpath("//td[@id='Custom fields']/../../../../div/table/tbody/tr["+i+"]/td")).getText().trim());
			}
			for (int i = 0; i < expContent.length; i++) {
				String exp = expContent[i];
				for (int j = 0; j < actContent.size(); j++) {
					int k = j+1;
					if (exp.equalsIgnoreCase(actContent.get(j))) {
						driver.findElement(By.xpath("//td[@id='Custom fields']/../../../../div/table/tbody/tr["+k+"]/td")).click();
						try {
							String name="//span[contains(text(),'<< "+actContent.get(j)+" >>')]";
							Wait.wait3Second();
							//Wait.waitForFrame(By.id(id), driver);
//							WebElement frame=driver.findElement(By.id("MainContent_innDocumentEditor_tw"));
//							driver.switchTo().frame(frame);
							if(name.contains("AdultsChildren")){
								name="//span[contains(text(),'<< Ad/Ch >>')]";
							}
							if(name.contains("RoomClass")){
								name="//span[contains(text(),'<< RoomClass >>')]";
							}
							if(name.contains("ArrivalDate")){
								name="//span[contains(text(),'<< ArrivalDate >>')]";
							}
							if(name.contains("BookedOn")){
								name="//span[contains(text(),'<< BookedOn >>')]";
								
							}	
							if(name.contains("Address")){
								name="//span[contains(text(),'<< Address >>')]";
								
							}
//							Robot robot = new Robot();
//							robot.keyPress(KeyEvent.VK_END);
//							robot.keyRelease(KeyEvent.VK_ENTER);
							driver.switchTo().frame(driver.findElement(By.id("MainContent_innDocumentEditor_tw")));
							
							
//							
							new Actions(driver).sendKeys(driver.findElement(By.xpath(name)), Keys.END).sendKeys(Keys.ENTER).build().perform();
							driver.switchTo().defaultContent();
							driver.findElement(By.xpath(custom)).click();

							
						} catch (Exception e) {
							System.out.println(e);
						}
						
					}
				}
				
			}
		}
	}

	public void verifyDocumentTemplate(WebDriver driver, ArrayList<String> test_steps,String attribute,String reservation,Map map,boolean flag) throws Exception{

		System.out.println(map);
		String[] str=attribute.split("/");
		EmailUtils emailUtils =  new EmailUtils(EmailUtils.EmailFolder.INBOX);
		Message[] msg	=emailUtils.getMessagesBySubjectRead(reservation, true, 20);
		/* System.out.println(emailUtils.isTextInMessage(msg[0], reservation));			
		 System.out.println(emailUtils.isTextInMessage(msg[0], resStatus));*/
		try {
			if(msg[0].equals("")||msg[0].equals(null)) {
				Wait.wait15Second();
				msg	=emailUtils.getMessagesBySubject(reservation, true, 20);
			}
		}catch(Exception e) {
			Wait.wait15Second();
			msg	=emailUtils.getMessagesBySubject(reservation, true, 20);
		}
		
		
		for(int i=0;i<str.length;i++) {
			Date now = new Date();
			 SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
		        String dayOfTheWeek=simpleDateformat.format(now);
			
		        Calendar cal = Calendar.getInstance();
		        cal.add(Calendar.DATE, 1);
		        Date date=cal.getTime();
		        String depDay=date.toString();
		        String nextDay=depDay.substring(0, 3);
		        

			System.out.println(str[i]);
			try {

				if(attribute.equalsIgnoreCase("NoOfRooms")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("NoOfRooms")){
					if(emailUtils.isTextInMessage(msg[0], map.get("NoOfRooms").toString())){
						test_steps.add("NoOfRooms present in scheduling mail : "+map.get("NoOfRooms"));
						doctempLogger.info("NoOfRooms present in scheduling mail : "+map.get("NoOfRooms"));
					}else{
						test_steps.add("NoOfRooms not present in scheduling mail : "+map.get("NoOfRooms"));
						doctempLogger.info("NoOfRooms not present in scheduling mail : "+map.get("NoOfRooms"));
					}
				}

				if(attribute.equalsIgnoreCase("accountName")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("accountName")){
					if(emailUtils.isTextInMessage(msg[0], map.get("accountName").toString())){
						test_steps.add("accountName present in scheduling mail : "+map.get("accountName"));
						doctempLogger.info("accountName present in scheduling mail : "+map.get("accountName"));
					}else{
						test_steps.add("accountName not present in scheduling mail : "+map.get("accountName"));
						doctempLogger.info("accountName not present in scheduling mail : "+map.get("accountName"));
					}
				}
				if(attribute.equalsIgnoreCase("ConfirmationNumber")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("ConfirmationNumber")){
					if(emailUtils.isTextInMessage(msg[0], map.get("ConfirmationNumber").toString())){
						test_steps.add("ConfirmationNumber present in scheduling mail : "+map.get("ConfirmationNumber"));
						doctempLogger.info("ConfirmationNumber present in scheduling mail : "+map.get("ConfirmationNumber"));
					}else{
						test_steps.add("ConfirmationNumber not present in scheduling mail : "+map.get("ConfirmationNumber"));
						doctempLogger.info("ConfirmationNumber not present in scheduling mail : "+map.get("ConfirmationNumber"));
					}
				}
				if(attribute.equalsIgnoreCase("reservationStatus")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("reservationStatus")){
					if(emailUtils.isTextInMessage(msg[0], map.get("reservationStatus").toString())){
						test_steps.add("reservationStatus present in scheduling mail : "+map.get("reservationStatus"));
						doctempLogger.info("reservationStatus present in scheduling mail : "+map.get("reservationStatus"));
					}else{
						test_steps.add("reservationStatus not present in scheduling mail : "+map.get("reservationStatus"));
						doctempLogger.info("reservationStatus not present in scheduling mail : "+map.get("reservationStatus"));
					}
				}
				if(attribute.equalsIgnoreCase("RoomClass")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("RoomClass")){
					if(emailUtils.isTextInMessage(msg[0], map.get("RoomClass").toString())){
						test_steps.add("RoomClass present in scheduling mail : "+map.get("RoomClass"));
						doctempLogger.info("RoomClass present in scheduling mail : "+map.get("RoomClass"));
					}else{
						test_steps.add("RoomClass not present in scheduling mail : "+map.get("RoomClass"));
						doctempLogger.info("RoomClass not present in scheduling mail : "+map.get("RoomClass"));
					}
				}

				if(attribute.equalsIgnoreCase("TotalCharges")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("TotalCharges")){
					if(emailUtils.isTextInMessage(msg[0], map.get("TotalCharges").toString())){
						test_steps.add("TotalCharges present in scheduling mail : "+map.get("TotalCharges"));
						doctempLogger.info("TotalCharges present in scheduling mail : "+map.get("TotalCharges"));
					}else{
						test_steps.add("TotalCharges not present in scheduling mail : "+map.get("TotalCharges"));
						doctempLogger.info("TotalCharges not present in scheduling mail : "+map.get("TotalCharges"));
					}
				}


				if(attribute.equalsIgnoreCase("GuestFirstName")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("GuestFirstName")){
					if(emailUtils.isTextInMessage(msg[0], map.get("GuestFirstName").toString())){
						test_steps.add("GuestFirstName present in scheduling mail : "+map.get("GuestFirstName"));
						doctempLogger.info("GuestFirstName present in scheduling mail : "+map.get("GuestFirstName"));
					}else{
						test_steps.add("GuestFirstName not present in scheduling mail : "+map.get("GuestFirstName"));
						doctempLogger.info("GuestFirstName not present in scheduling mail : "+map.get("GuestFirstName"));
					}
				}

				if(attribute.contains("GuestLastName")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("GuestLastName")){
					if(emailUtils.isTextInMessage(msg[0], map.get("GuestLastName").toString())){
						test_steps.add("GuestLastName present in scheduling mail : "+map.get("GuestLastName"));
						doctempLogger.info("GuestLastName present in scheduling mail : "+map.get("GuestLastName"));
					}else{
						test_steps.add("GuestLastName not present in scheduling mail : "+map.get("GuestLastName"));
						doctempLogger.info("GuestLastName not present in scheduling mail : "+map.get("GuestLastName"));
					}
				}

				if(attribute.equalsIgnoreCase("guestemail")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("guestemail")){
					if(emailUtils.isTextInMessage(msg[0], map.get("guestemail").toString())){
						test_steps.add("guestemail present in scheduling mail : "+map.get("guestemail"));
						doctempLogger.info("guestemail present in scheduling mail : "+map.get("guestemail"));
					}else{
						test_steps.add("guestemail not present in scheduling mail : "+map.get("guestemail"));
						doctempLogger.info("guestemail not present in scheduling mail : "+map.get("guestemail"));
					}
				}

				if(attribute.equalsIgnoreCase("Salutation")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("Salutation")){
					if(emailUtils.isTextInMessage(msg[0], map.get("Salutation").toString())){
						test_steps.add("Salutation present in scheduling mail : "+map.get("Salutation"));
						doctempLogger.info("Salutation present in scheduling mail : "+map.get("Salutation"));
					}else{
						test_steps.add("Salutation not present in scheduling mail : "+map.get("Salutation"));
						doctempLogger.info("Salutation not present in scheduling mail : "+map.get("Salutation"));
					}
				}

				if(attribute.equalsIgnoreCase("FolioBalance")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("FolioBalance")){
					if(emailUtils.isTextInMessage(msg[0], map.get("FolioBalance").toString())){
						test_steps.add("FolioBalance present in scheduling mail : "+map.get("FolioBalance"));
						doctempLogger.info("FolioBalance present in scheduling mail : "+map.get("FolioBalance"));
					}else{
						test_steps.add("FolioBalance not present in scheduling mail : "+map.get("FolioBalance"));
						doctempLogger.info("FolioBalance not present in scheduling mail : "+map.get("FolioBalance"));
					}
				}

				if(attribute.equalsIgnoreCase("CCLast4digits")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("CCLast4digits")){
					if(emailUtils.isTextInMessage(msg[0], map.get("CCLast4digits").toString())){
						test_steps.add("CCLast4digits present in scheduling mail : "+map.get("CCLast4digits"));
						doctempLogger.info("CCLast4digits present in scheduling mail : "+map.get("CCLast4digits"));
					}else{
						test_steps.add("CCLast4digits not present in scheduling mail : "+map.get("CCLast4digits"));
						doctempLogger.info("CCLast4digits not present in scheduling mail : "+map.get("CCLast4digits"));
					}
				}

				/*if(attribute.contains("Address")){
			if(emailUtils.isTextInMessage(msg[0], map.get("Address").toString())){
				test_steps.add("Address present in scheduling mail : "+map.get("Address"));
			}else{
				test_steps.add("Address not present in scheduling mail : "+map.get("Address"));
			}
		}*/

				if(attribute.equalsIgnoreCase("CCExpDate")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("CCExpDate")){
					if(emailUtils.isTextInMessage(msg[0], map.get("CCExpDate").toString())){
						test_steps.add("CCExpDate present in scheduling mail : "+map.get("CCExpDate"));
						doctempLogger.info("CCExpDate present in scheduling mail : "+map.get("CCExpDate"));
					}else{
						test_steps.add("CCExpDate not present in scheduling mail : "+map.get("CCExpDate"));
						doctempLogger.info("CCExpDate not present in scheduling mail : "+map.get("CCExpDate"));
					}
				}


				if(attribute.equalsIgnoreCase("ReservationDetailsLink")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("ReservationDetailsLink")){
					if(emailUtils.isTextInMessage(msg[0], map.get("ReservationDetailsLink").toString())){
						test_steps.add("ReservationDetailsLink present in scheduling mail : "+map.get("ReservationDetailsLink"));
						doctempLogger.info("ReservationDetailsLink present in scheduling mail : "+map.get("ReservationDetailsLink"));
					}else{
						test_steps.add("ReservationDetailsLink not present in scheduling mail : "+map.get("ReservationDetailsLink"));
						doctempLogger.info("ReservationDetailsLink not present in scheduling mail : "+map.get("ReservationDetailsLink"));
					}
				}


				if(attribute.equalsIgnoreCase("Country")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("Country")){
					if(emailUtils.isTextInMessage(msg[0], map.get("Country").toString())){
						test_steps.add("Country present in scheduling mail : "+map.get("Country"));
						doctempLogger.info("Country present in scheduling mail : "+map.get("Country"));
					}else{
						test_steps.add("Country not present in scheduling mail : "+map.get("Country"));
						doctempLogger.info("Country not present in scheduling mail : "+map.get("Country"));
					}
				}

				if(attribute.equalsIgnoreCase("Currency")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("Currency")){
					if(emailUtils.isTextInMessage(msg[0], map.get("Currency").toString())){
						test_steps.add("Currency present in scheduling mail : "+map.get("FolioBalance").toString().substring(0, 1));
						doctempLogger.info("Currency present in scheduling mail : "+map.get("FolioBalance").toString().substring(0, 1));
					}else{
						test_steps.add("Currency not present in scheduling mail : "+map.get("FolioBalance").toString().substring(0, 1));
						doctempLogger.info("Currency not present in scheduling mail : "+map.get("FolioBalance").toString().substring(0, 1));
					}
				}

				if(attribute.equalsIgnoreCase("ContactName")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("ContactName")){
					if(emailUtils.isTextInMessage(msg[0], map.get("GuestFirstName").toString() +" "+map.get("GuestLastName").toString())){
						test_steps.add("ContactName present in scheduling mail : "+map.get("GuestFirstName").toString() +" "+map.get("GuestLastName").toString());
						doctempLogger.info("ContactName present in scheduling mail : "+map.get("GuestFirstName").toString() +" "+map.get("GuestLastName").toString());
					}else{
						test_steps.add("ContactName not present in scheduling mail : "+map.get("GuestFirstName").toString() +" "+map.get("GuestLastName").toString());
						doctempLogger.info("ContactName not present in scheduling mail : "+map.get("GuestFirstName").toString() +" "+map.get("GuestLastName").toString());
					}
				}

				if(attribute.equalsIgnoreCase("referral")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("referral")){
					if(emailUtils.isTextInMessage(msg[0], map.get("referral").toString())){
						test_steps.add("referral present in scheduling mail : "+map.get("referral"));
						doctempLogger.info("referral present in scheduling mail : "+map.get("referral"));
					}else{
						test_steps.add("referral not present in scheduling mail : "+map.get("referral"));
						doctempLogger.info("referral not present in scheduling mail : "+map.get("referral"));
					}
				}

				if(attribute.equalsIgnoreCase("marketSegment")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("marketSegment")){
					if(emailUtils.isTextInMessage(msg[0], map.get("marketSegment").toString())){
						test_steps.add("marketSegment present in scheduling mail : "+map.get("marketSegment"));
						doctempLogger.info("marketSegment present in scheduling mail : "+map.get("marketSegment"));
					}else{
						test_steps.add("marketSegment not present in scheduling mail : "+map.get("marketSegment"));
						doctempLogger.info("marketSegment not present in scheduling mail : "+map.get("marketSegment"));
					}
				}

				if(attribute.equalsIgnoreCase("TotalTaxes")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("TotalTaxes")){
					if(emailUtils.isTextInMessage(msg[0], map.get("TotalTaxes").toString())){
						test_steps.add("TotalTaxes present in scheduling mail : "+map.get("TotalTaxes"));
						doctempLogger.info("TotalTaxes present in scheduling mail : "+map.get("TotalTaxes"));
					}else{
						test_steps.add("TotalTaxes not present in scheduling mail : "+map.get("TotalTaxes"));
						doctempLogger.info("TotalTaxes not present in scheduling mail : "+map.get("TotalTaxes"));
					}
				}

				if(attribute.equalsIgnoreCase("RoomCharges")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("RoomCharges")){
					if(emailUtils.isTextInMessage(msg[0], map.get("RoomCharges").toString())){
						test_steps.add("RoomCharges present in scheduling mail : "+map.get("RoomCharges"));
						doctempLogger.info("RoomCharges present in scheduling mail : "+map.get("RoomCharges"));
					}else{
						test_steps.add("RoomCharges not present in scheduling mail : "+map.get("RoomCharges"));
						doctempLogger.info("RoomCharges not present in scheduling mail : "+map.get("RoomCharges"));
					}
				}

				if(attribute.equalsIgnoreCase("sourceName")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("sourceName")){
					if(emailUtils.isTextInMessage(msg[0], map.get("sourceName").toString())){
						test_steps.add("sourceName present in scheduling mail : "+map.get("sourceName"));
						doctempLogger.info("sourceName present in scheduling mail : "+map.get("sourceName"));
					}else{
						test_steps.add("sourceName not present in scheduling mail : "+map.get("sourceName"));
						doctempLogger.info("sourceName not present in scheduling mail : "+map.get("sourceName"));
					}
				}


				if(attribute.equalsIgnoreCase("noOfnights")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("noOfnights")){
					if(emailUtils.isTextInMessage(msg[0], map.get("noOfnights").toString())){
						test_steps.add("noOfnights present in scheduling mail : "+map.get("noOfnights"));
						doctempLogger.info("noOfnights present in scheduling mail : "+map.get("noOfnights"));
					}else{
						test_steps.add("noOfnights not present in scheduling mail : "+map.get("noOfnights"));
						doctempLogger.info("noOfnights not present in scheduling mail : "+map.get("noOfnights"));
					}
				}


				if(attribute.equalsIgnoreCase("Incidentals")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("Incidentals")){
					if(emailUtils.isTextInMessage(msg[0], map.get("Incidentals").toString())){
						test_steps.add("Incidentals present in scheduling mail : "+map.get("Incidentals"));
						doctempLogger.info("Incidentals present in scheduling mail : "+map.get("Incidentals"));
					}else{
						test_steps.add("Incidentals not present in scheduling mail : "+map.get("Incidentals"));
						doctempLogger.info("Incidentals not present in scheduling mail : "+map.get("Incidentals"));
					}
				}

				if(attribute.equalsIgnoreCase("guestphonenumber")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("guestphonenumber")){
					if(emailUtils.isTextInMessage(msg[0], map.get("guestphonenumber").toString())){
						test_steps.add("guestphonenumber present in scheduling mail : "+map.get("guestphonenumber"));
						doctempLogger.info("guestphonenumber present in scheduling mail : "+map.get("guestphonenumber"));
					}else{
						test_steps.add("guestphonenumber not present in scheduling mail : "+map.get("guestphonenumber"));
						doctempLogger.info("guestphonenumber not present in scheduling mail : "+map.get("guestphonenumber"));
					}
				}

				if(attribute.equalsIgnoreCase("ArrivalDate")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("ArrivalDate")){
					System.out.println(map.get("ArrivalDate"));
					if(emailUtils.isTextInMessage(msg[0], map.get("ArrivalDate").toString())){
						test_steps.add("ArrivalDate present in scheduling mail : "+map.get("ArrivalDate"));
						doctempLogger.info("ArrivalDate present in scheduling mail : "+map.get("ArrivalDate"));
						//dayOfTheWeek
						if(map.get("ArrivalDate").toString().contains(dayOfTheWeek)) {
							test_steps.add("Day of the week present in ArrivalDate : "+dayOfTheWeek);
							doctempLogger.info("Day of the week present in ArrivalDate : "+dayOfTheWeek);
						}else {
							test_steps.add("Day of the week not present in ArrivalDate : "+dayOfTheWeek);
							doctempLogger.info("Day of the not week present in ArrivalDate : "+dayOfTheWeek);
						}
						
						if(map.get("ArrivalDate").toString().contains(":")||map.get("ArrivalDate").toString().contains("AM")||map.get("ArrivalDate").toString().contains("PM")) {
							test_steps.add("Time stamp present in ArrivalDate : "+map.get("ArrivalDate").toString());
							test_steps.add("Scheduled Email - CheckIn/Out Should NOT display timestamp Issue still exists<br>" + "<a href='https://innroad.atlassian.net/browse/NG-4259' target='_blank'>Verified the production issue : NG-4259</a>");
							doctempLogger.info("Time stamp present in ArrivalDate : "+map.get("ArrivalDate").toString());
						}else {
							test_steps.add("Day of the week not present in ArrivalDate : "+map.get("DepartureDate").toString());
							test_steps.add("Scheduled Email - CheckIn/Out Should NOT display timestamp issue verified<br>" + "<a href='https://innroad.atlassian.net/browse/NG-4259' target='_blank'>Verified the production issue : NG-4259</a>");
						}
						
					}else{
						test_steps.add("ArrivalDate not present in scheduling mail : "+map.get("ArrivalDate"));
						doctempLogger.info("ArrivalDate not present in scheduling mail : "+map.get("ArrivalDate"));
					}
				}

				if(attribute.equalsIgnoreCase("DepartureDate")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("DepartureDate")){
					System.out.println(map.get("DepartureDate"));
					if(emailUtils.isTextInMessage(msg[0], map.get("DepartureDate").toString())){
						test_steps.add("DepartureDate present in scheduling mail : "+map.get("DepartureDate"));
						doctempLogger.info("DepartureDate present in scheduling mail : "+map.get("DepartureDate"));
						
						if(map.get("DepartureDate").toString().contains(nextDay)) {
							test_steps.add("Day of the week present in DepartureDate : "+nextDay);
							doctempLogger.info("Day of the week present in DepartureDate : "+nextDay);
						}else {
							test_steps.add("Day of the week not present in DepartureDate : "+nextDay);
							doctempLogger.info("Day of the not week present in DepartureDate : "+nextDay);
						}
						
						if(map.get("DepartureDate").toString().contains(":")||map.get("DepartureDate").toString().contains("AM")||map.get("DepartureDate").toString().contains("PM")) {
							test_steps.add("Time stamp present in DepartureDate : "+map.get("DepartureDate").toString());
							test_steps.add("Scheduled Email - CheckIn/Out Should NOT display timestamp Issue still exists<br>" + "<a href='https://innroad.atlassian.net/browse/NG-4259' target='_blank'>Verified the production issue : NG-4259</a>");
							doctempLogger.info("Time stamp present in DepartureDate : "+map.get("DepartureDate").toString());
						}else {
							test_steps.add("Day of the week not present in DepartureDate : "+map.get("DepartureDate").toString());
							test_steps.add("Scheduled Email - CheckIn/Out Should NOT display timestamp issue verified<br>" + "<a href='https://innroad.atlassian.net/browse/NG-4259' target='_blank'>Verified the production issue : NG-4259</a>");
						}
					}else{
						test_steps.add("DepartureDate not present in scheduling mail : "+map.get("DepartureDate"));
						doctempLogger.info("DepartureDate not present in scheduling mail : "+map.get("DepartureDate"));
					}
				}

				if(attribute.equalsIgnoreCase("BookedOn")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("ArrivalDate")){
					if(emailUtils.isTextInMessage(msg[0], map.get("ArrivalDate").toString())){
						test_steps.add("BookedOn present in scheduling mail : "+map.get("ArrivalDate"));
						doctempLogger.info("BookedOn present in scheduling mail : "+map.get("ArrivalDate"));
						
						if(map.get("ArrivalDate").toString().contains(dayOfTheWeek)) {
							test_steps.add("Day of the week present in ArrivalDate : "+dayOfTheWeek);
							doctempLogger.info("Day of the week present in ArrivalDate : "+dayOfTheWeek);
						}else {
							test_steps.add("Day of the week not present in ArrivalDate : "+dayOfTheWeek);
							doctempLogger.info("Day of the not week present in ArrivalDate : "+dayOfTheWeek);
						}
						
						
						if(map.get("ArrivalDate").toString().contains(":")||map.get("ArrivalDate").toString().contains("AM")||map.get("ArrivalDate").toString().contains("PM")) {
							test_steps.add("Time stamp present in ArrivalDate : "+map.get("ArrivalDate").toString());
							test_steps.add("Scheduled Email - CheckIn/Out Should NOT display timestamp Issue still exists<br>" + "<a href='https://innroad.atlassian.net/browse/NG-4259' target='_blank'>Verified the production issue : NG-4259</a>");
							doctempLogger.info("Time stamp present in ArrivalDate : "+map.get("ArrivalDate").toString());
						}else {
							test_steps.add("Day of the week not present in ArrivalDate : "+map.get("DepartureDate").toString());
							test_steps.add("Scheduled Email - CheckIn/Out Should NOT display timestamp issue verified<br>" + "<a href='https://innroad.atlassian.net/browse/NG-4259' target='_blank'>Verified the production issue : NG-4259</a>");
						}
						
						
					}else{
						test_steps.add("BookedOn not present in scheduling mail : "+map.get("ArrivalDate"));
						doctempLogger.info("BookedOn not present in scheduling mail : "+map.get("ArrivalDate"));
					}
				}
				if(attribute.equalsIgnoreCase("PaymentName")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("PaymentName")){
					if(emailUtils.isTextInMessage(msg[0], map.get("PaymentName").toString())){
						test_steps.add("PaymentName present in scheduling mail : "+map.get("PaymentName"));
						doctempLogger.info("PaymentName present in scheduling mail : "+map.get("PaymentName"));
					}else{
						test_steps.add("PaymentName not present in scheduling mail : "+map.get("PaymentName"));
						doctempLogger.info("PaymentName not present in scheduling mail : "+map.get("PaymentName"));
					}
				}

				if(attribute.equalsIgnoreCase("PaymentAmount")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("PaymentAmount")){
					if(emailUtils.isTextInMessage(msg[0], map.get("PaymentAmount").toString())){
						test_steps.add("PaymentAmount present in scheduling mail : "+map.get("PaymentAmount"));
						doctempLogger.info("PaymentAmount present in scheduling mail : "+map.get("PaymentAmount"));
					}else{
						test_steps.add("PaymentAmount not present in scheduling mail : "+map.get("PaymentAmount"));
						doctempLogger.info("PaymentAmount not present in scheduling mail : "+map.get("PaymentAmount"));
					}
				}


				if(attribute.equalsIgnoreCase("StationID")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("StationID")){
					if(emailUtils.isTextInMessage(msg[0], map.get("Room").toString())){
						test_steps.add("StationID present in scheduling mail : "+map.get("Room"));
						doctempLogger.info("StationID present in scheduling mail : "+map.get("Room"));
					}else{
						test_steps.add("StationID not present in scheduling mail : "+map.get("Room"));
						doctempLogger.info("StationID not present in scheduling mail : "+map.get("Room"));
					}
				}

				if(attribute.equalsIgnoreCase("Room")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("Room")){
					if(emailUtils.isTextInMessage(msg[0], map.get("Room").toString())){
						test_steps.add("Room present in scheduling mail : "+map.get("Room"));
						doctempLogger.info("Room present in scheduling mail : "+map.get("Room"));
					}else{
						test_steps.add("Room not present in scheduling mail : "+map.get("Room"));
						doctempLogger.info("Room not present in scheduling mail : "+map.get("Room"));
					}
				}

				if(attribute.equalsIgnoreCase("Notes")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("Notes")){
					if(emailUtils.isTextInMessage(msg[0], map.get("Notes").toString())){
						test_steps.add("Notes present in scheduling mail : "+map.get("Notes"));
						doctempLogger.info("Notes present in scheduling mail : "+map.get("Notes"));
					}else{
						test_steps.add("Notes not present in scheduling mail : "+map.get("Notes"));
						doctempLogger.info("Notes not present in scheduling mail : "+map.get("Notes"));
					}
				}

				if(attribute.equalsIgnoreCase("propertyPhone")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("propertyPhone")){
					if(emailUtils.isTextInMessage(msg[0], map.get("propertyPhone").toString())){
						test_steps.add("propertyPhone present in scheduling mail : "+map.get("propertyPhone"));
						doctempLogger.info("propertyPhone present in scheduling mail : "+map.get("propertyPhone"));
					}else{
						test_steps.add("propertyPhone not present in scheduling mail : "+map.get("propertyPhone"));
						doctempLogger.info("propertyPhone not present in scheduling mail : "+map.get("propertyPhone"));
					}
				}

				if(attribute.equalsIgnoreCase("PropertyLegalName")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("PropertyLegalName")){
					if(emailUtils.isTextInMessage(msg[0], map.get("PropertyLegalName").toString())){
						test_steps.add("PropertyLegalName present in scheduling mail : "+map.get("PropertyLegalName"));
						doctempLogger.info("PropertyLegalName present in scheduling mail : "+map.get("PropertyLegalName"));
					}else{
						test_steps.add("PropertyLegalName not present in scheduling mail : "+map.get("PropertyLegalName"));
						doctempLogger.info("PropertyLegalName not present in scheduling mail : "+map.get("PropertyLegalName"));
					}
				}

				if(attribute.equalsIgnoreCase("propertyEmail")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("propertyEmail")){
					if(emailUtils.isTextInMessage(msg[0], map.get("propertyEmail").toString())){
						test_steps.add("propertyEmail present in scheduling mail : "+map.get("propertyEmail"));
						doctempLogger.info("propertyEmail present in scheduling mail : "+map.get("propertyEmail"));
					}else{
						test_steps.add("propertyEmail not present in scheduling mail : "+map.get("propertyEmail"));
						doctempLogger.info("propertyEmail not present in scheduling mail : "+map.get("propertyEmail"));
					}
				}

				if(attribute.equalsIgnoreCase("Rates")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("RoomCharges")){
					if(emailUtils.isTextInMessage(msg[0], map.get("RoomCharges").toString())){
						test_steps.add("Rates present in scheduling mail : "+map.get("RoomCharges"));
						doctempLogger.info("Rates present in scheduling mail : "+map.get("RoomCharges"));
					}else{
						test_steps.add("Rates not present in scheduling mail : "+map.get("RoomCharges"));
						doctempLogger.info("Rates not present in scheduling mail : "+map.get("RoomCharges"));
					}
				}

				if(attribute.equalsIgnoreCase("SystemDate")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("ArrivalDate")){
					if(emailUtils.isTextInMessage(msg[0], map.get("ArrivalDate").toString())){
						test_steps.add("SystemDate present in scheduling mail : "+map.get("ArrivalDate"));
						doctempLogger.info("SystemDate present in scheduling mail : "+map.get("ArrivalDate"));
					}else{
						test_steps.add("SystemDate not present in scheduling mail : "+map.get("ArrivalDate"));
						doctempLogger.info("SystemDate not present in scheduling mail : "+map.get("ArrivalDate"));
					}
					
					if(map.get("ArrivalDate").toString().contains(":")||map.get("ArrivalDate").toString().contains("AM")||map.get("ArrivalDate").toString().contains("PM")) {
						test_steps.add("Time stamp present in SystemDate : "+map.get("ArrivalDate").toString());
						test_steps.add("Scheduled Email - CheckIn/Out Should NOT display timestamp Issue still exists<br>" + "<a href='https://innroad.atlassian.net/browse/NG-4259' target='_blank'>Verified the production issue : NG-4259</a>");
						doctempLogger.info("Time stamp present in SystemDate : "+map.get("ArrivalDate").toString());
					}else {
						test_steps.add("Day of the week not present in SystemDate : "+map.get("DepartureDate").toString());
						test_steps.add("Scheduled Email - CheckIn/Out Should NOT display timestamp issue verified<br>" + "<a href='https://innroad.atlassian.net/browse/NG-4259' target='_blank'>Verified the production issue : NG-4259</a>");
					}
					
				}

				if(attribute.equalsIgnoreCase("userBookedFirstName")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("userBookedFirstName")){
					if(emailUtils.isTextInMessage(msg[0], map.get("userBookedFirstName").toString())){
						test_steps.add("userBookedFirstName present in scheduling mail : "+map.get("userBookedFirstName"));
						doctempLogger.info("userBookedFirstName present in scheduling mail : "+map.get("userBookedFirstName"));
					}else{
						test_steps.add("userBookedFirstName not present in scheduling mail : "+map.get("userBookedFirstName"));
						doctempLogger.info("userBookedFirstName not present in scheduling mail : "+map.get("userBookedFirstName"));
					}
				}

				if(attribute.equalsIgnoreCase("userBookedLastName")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("userBookedLastName")){
					if(emailUtils.isTextInMessage(msg[0], map.get("userBookedLastName").toString())){
						test_steps.add("userBookedLastName present in scheduling mail : "+map.get("userBookedLastName"));
						doctempLogger.info("userBookedLastName present in scheduling mail : "+map.get("userBookedLastName"));
					}else{
						test_steps.add("userBookedLastName not present in scheduling mail : "+map.get("userBookedLastName"));
						doctempLogger.info("userBookedLastName not present in scheduling mail : "+map.get("userBookedLastName"));
					}
				}

				if(attribute.equalsIgnoreCase("Policies")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("Policies")){
					if(emailUtils.isTextInMessage(msg[0], map.get("Policies").toString())){
						test_steps.add("Policies present in scheduling mail : "+map.get("Policies"));
						doctempLogger.info("Policies present in scheduling mail : "+map.get("Policies"));
					}else{
						test_steps.add("Policies not present in scheduling mail : "+map.get("Policies"));
						doctempLogger.info("Policies not present in scheduling mail : "+map.get("Policies"));
					}
				}	
				if(attribute.equalsIgnoreCase("AdultsChildren")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("AdultsChildren")){
					if(emailUtils.isTextInMessage(msg[0], map.get("AdultsChildren").toString())){
						test_steps.add("AdultsChildren present in scheduling mail : "+map.get("AdultsChildren"));
						doctempLogger.info("AdultsChildren present in scheduling mail : "+map.get("AdultsChildren"));
					}else{
						test_steps.add("AdultsChildren not present in scheduling mail : "+map.get("AdultsChildren"));
						doctempLogger.info("AdultsChildren not present in scheduling mail : "+map.get("AdultsChildren"));
					}
				}

				if(attribute.equalsIgnoreCase("Address")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("Address")){

					String[] add =map.get("Address").toString().split("/");
					System.out.println("******" +msg[0]+"*********************");
					System.out.println(add[0]);
					System.out.println(add[1]);
					System.out.println(add[2]);
					System.out.println(add[3]);
					System.out.println(add[4]);
					System.out.println(add[5]);

					if(emailUtils.isTextInMessage(msg[0], add[0])) {
						if(emailUtils.isTextInMessage(msg[0], add[1])){
							if(emailUtils.isTextInMessage(msg[0], add[2])){
								if(emailUtils.isTextInMessage(msg[0], add[3])) {
									if(emailUtils.isTextInMessage(msg[0], "NY")) {
										if(emailUtils.isTextInMessage(msg[0], add[5])){
											test_steps.add("Address present in scheduling mail : "+map.get("Address"));
											doctempLogger.info("Address present in scheduling mail : "+map.get("Address"));
										}else{
											test_steps.add("Address not present in scheduling mail : "+map.get("Address"));
											doctempLogger.info("Address not present in scheduling mail : "+map.get("Address"));
										}
									}else{
										test_steps.add("Address not present in scheduling mail : "+map.get("Address"));
										doctempLogger.info("Address not present in scheduling mail : "+map.get("Address"));
									}
								}else{
									test_steps.add("Address not present in scheduling mail : "+map.get("Address"));
									doctempLogger.info("Address not present in scheduling mail : "+map.get("Address"));
								}
							}else{
								test_steps.add("Address not present in scheduling mail : "+map.get("Address"));
								doctempLogger.info("Address not present in scheduling mail : "+map.get("Address"));
							}
						}else{
							test_steps.add("Address not present in scheduling mail : "+map.get("Address"));
							doctempLogger.info("Address not present in scheduling mail : "+map.get("Address"));
						}
					}else{
						test_steps.add("Address not present in scheduling mail : "+map.get("Address"));
						doctempLogger.info("Address not present in scheduling mail : "+map.get("Address"));
					}
				}	

				if(attribute.equalsIgnoreCase("PropertyAddr")||attribute.equalsIgnoreCase("All")||str[i].equalsIgnoreCase("PropertyAddr")){

					String[] add =map.get("PropertyAddr").toString().split("/");

					if(emailUtils.isTextInMessage(msg[0], add[0])&&emailUtils.isTextInMessage(msg[0], add[1])&&emailUtils.isTextInMessage(msg[0], add[3])&&emailUtils.isTextInMessage(msg[0], "NY")){
						test_steps.add("PropertyAddr present in scheduling mail : "+map.get("PropertyAddr"));
						doctempLogger.info("PropertyAddr present in scheduling mail : "+map.get("PropertyAddr"));
					}else{
						test_steps.add("PropertyAddr not present in scheduling mail : "+map.get("PropertyAddr"));
						doctempLogger.info("PropertyAddr not present in scheduling mail : "+map.get("PropertyAddr"));
					}
					
					test_steps.add("Verified the issue<br>"	+ "<a href='https://innroad.atlassian.net/browse/NG-6498' target='_blank'>Verified the production issue : NG-6498</a>");
				}	
			}catch(ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
				test_steps.add("Email may not received please check your email inbox");
				doctempLogger.info("Email may not received please check your email inbox");
				test_steps.add("Issue still exists<br>" + "<a href='https://innroad.atlassian.net/browse/NG-6498' target='_blank'>Verified the production issue : NG-6498</a>");
			}
		}
	}

	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <clickNewDocument> ' Description: <short description about
	 * method purpose> ' Input parameters: < ',' separated parameters type> (
	 * int, int, String ) ' Return value: <return type> ( void / String /
	 * ArrayList<String> ...etc ' Created By: <Full name of developer> ' Created
	 * On: <MM/DD/YYYY>
	 * 
	 * ' Modified By | Description of Modification:
	 * ------------------------------------------ <05/11/2020>:<Adnan Ghaffar>:
	 * 1.Step modified : update array list name 2.Step modified : add dynamic
	 * wait and more description in to
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public ArrayList<String> clickNewDocument(WebDriver driver, ArrayList<String> testSteps) {
		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);

		Wait.WaitForElement(driver, OR.Doc_NewButton);
		Wait.waitForElementToBeVisibile(By.xpath(OR.Doc_NewButton), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.Doc_NewButton), driver);
		doctemp.Doc_NewButton.click();
		testSteps.add("Click on new document button");

		Wait.WaitForElement(driver, OR.DocTempPage);
		Wait.waitForElementToBeVisibile(By.xpath(OR.DocTempPage), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.DocTempPage), driver);
		assertTrue(doctemp.DocTempPage.getText().contains("Document Template"),
				"Document Template page does not display");
		testSteps.add("Verified new document creation template page is showing");
		return testSteps;

	}

	public String enterDocumnetName(WebDriver driver, String documentTemplateName) throws InterruptedException {

		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);

		Wait.WaitForElement(driver, OR.DocTempPage);
		Wait.waitForElementToBeVisibile(By.xpath(OR.DocTempPage), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.DocTempPage), driver);
		doctemp.DocumentName.sendKeys(documentTemplateName);

		assertTrue(doctemp.DocumentName.getAttribute("value").contains(documentTemplateName), "Failed: Document Name");
		doctempLogger.info("Documnet Name: " + documentTemplateName);
		return documentTemplateName;
	}

	public ArrayList<String> enterDocumnetDescription(WebDriver driver, String DocDescription,
			ArrayList<String> test_steps) throws InterruptedException {

		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.DocumentDescription, driver);
		doctemp.DocumentDescription.sendKeys(DocDescription);

		assertTrue(doctemp.DocumentDescription.getAttribute("value").contains(DocDescription),
				"Failed: Document Description");
		test_steps.add("DocumnetDescription: " + DocDescription);
		doctempLogger.info("DocumnetDescription: " + DocDescription);
		return test_steps;
	}

	public ArrayList<String> associateSources(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException {

		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);
		Wait.waitUntilPresenceOfElementLocated(OR.AssociateSource, driver);
		Utility.ScrollToElement(doctemp.AssociateSource, driver);
		doctemp.AssociateSource.click();
		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		System.out.println("frame Switched");
		Wait.wait2Second();
		Wait.waitUntilPresenceOfElementLocated(OR.AssociateSourcePopup, driver);
		System.out.println(doctemp.AssociateSourcePopup.getText());
		assertTrue(doctemp.AssociateSourcePopup.getText().contains("Source Picker"),
				"Associate Source Popup does not display");
		Wait.explicit_wait_xpath(OR.Source_Inncenter_CheckBox, driver);
		doctemp.Source_Inncenter_CheckBox.click();
		assertTrue(doctemp.Source_Inncenter_CheckBox.isSelected(), "Failed: AssociateSource");
		doctemp.SavePopup.click();
		driver.switchTo().defaultContent();
		test_steps.add("Associate Source : Inncenter ");
		doctempLogger.info("Associate Source : Inncenter");
		return test_steps;

	}

	public ArrayList<String> associateProperties(WebDriver driver, String AssociateProp, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);
		Wait.explicit_wait_xpath(OR.AssociateProperty, driver);
		Wait.wait5Second();
		Utility.ScrollToElement(doctemp.AssociateProperty, driver);
		doctemp.AssociateProperty.click();
		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		driver.switchTo().frame("frmWaitHost");
		System.out.println("frame Switched");
		Wait.wait2Second();
		Wait.waitUntilPresenceOfElementLocated(OR.AssociatePropertyPopup, driver);
		assertTrue(doctemp.AssociatePropertyPopup.getText().contains("Property Picker"),
				"Associate Property Popup does not display");
		Wait.explicit_wait_xpath(OR.Select_AssociateFunction, driver);
		// new
		// Select(doctemp.Select_AssociateFunction).selectByVisibleText(AssociateProp);
		doctemp.Property_PickAll.click();
		doctemp.SavePopup.click();
		driver.switchTo().defaultContent();
		test_steps.add("Associate Property : " + AssociateProp);
		doctempLogger.info("Associate Property : " + AssociateProp);
		return test_steps;
	}

	public ArrayList<String> AssociateFunctions(WebDriver driver, String FuncTrigger, String FuncEvent,
			ArrayList<String> test_steps) throws InterruptedException {
		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);
		Wait.explicit_wait_xpath(OR.AssociateFunction, driver);
		Wait.wait5Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", doctemp.AssociateFunction);
		doctemp.AssociateFunction.click();
		Wait.wait2Second();
		driver.switchTo().frame("dialog-body0");
		System.out.println("frame Switched");
		Wait.wait2Second();
		Wait.waitUntilPresenceOfElementLocated(OR.AssociateFunctionPopup, driver);
		assertTrue(doctemp.AssociateFunctionPopup.getText().contains("Function Picker"),
				"Associate Fiunction Popup does not display");
		doctemp.Function_Email_CheckBox.click();
		assertTrue(doctemp.Function_Email_CheckBox.isSelected(), "Failed: AssociateFunction");
		doctemp.SavePopup.click();
		driver.switchTo().defaultContent();
		test_steps.add("Associate Function : Confirmation Email");
		doctempLogger.info("Associate Function : Confirmation Email");

		JavascriptExecutor JSE = (JavascriptExecutor) driver;
		JSE.executeScript("arguments[0].scrollIntoView();", doctemp.Function_Trigger);
		Wait.waitUntilPresenceOfElementLocated(OR.Function_Trigger, driver);

		doctemp.Function_Trigger.click();
		Wait.wait2Second();
		new Select(doctemp.Function_Trigger).selectByVisibleText(FuncTrigger);
		Wait.wait2Second();
		assertTrue(doctemp.Function_Trigger.getText().contains(FuncTrigger), "Failed: Function Trigger");

		test_steps.add("Function Trigger: " + FuncTrigger);
		doctempLogger.info("Function Trigger:  " + FuncTrigger);
		JSE.executeScript("arguments[0].scrollIntoView();", doctemp.Function_Event);
		Wait.waitUntilPresenceOfElementLocated(OR.Function_Event, driver);
		doctemp.Function_Event.click();
		Wait.wait2Second();
		new Select(doctemp.Function_Event).selectByVisibleText(FuncEvent);
		Wait.wait2Second();
		assertTrue(doctemp.Function_Event.getText().contains(FuncEvent), "Failed: Function Event");
		test_steps.add("Function Event: " + FuncEvent);
		doctempLogger.info("Function Event:  " + FuncEvent);
		return test_steps;

	}
	/*
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 * 
	 * ' Method Name: <selectDefaultDocumentCheckBox> 
	 * ' Description: <short description about method purpose> 
	 * ' Input parameters: < ',' separated parameters type> (
	 * int, int, String )
	 *  ' Return value: <return type> ( void / String /
	 * ArrayList<String> ...etc ' 
	 * Created By: <Full name of developer> 
	 * ' Created On: <MM/DD/YYYY>
	 * 
	 * ' Modified By | Description of Modification:
	 * ------------------------------------------ 
	 * <05/11/2020>:<Adnan Ghaffar>:
	 * 1.Step modified : remove static wait
	 
	 * 
	 * #########################################################################
	 * #########################################################################
	 * ########################
	 */

	public void selectDefaultDocumentCheckBox(WebDriver driver) throws InterruptedException {
		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);
		// Wait.explicit_wait_xpath(OR.DefaultTempCheckBox);

		Wait.waitForElementToBeClickable(By.id(OR.DefaultTempCheckBox), driver);
		if (!doctemp.DefaultTempCheckBox.isSelected()) {
			doctemp.DefaultTempCheckBox.click();
			assertTrue(doctemp.DefaultTempCheckBox.isSelected(), "Failed:Default Template is not selected");
		} else {
			doctemp.DefaultTempCheckBox.click();
			assertTrue(!doctemp.DefaultTempCheckBox.isSelected(), "Failed:Default Template is  selected");
		}
	}
	public ArrayList<String> associateFunction(WebDriver driver, String functionTrigger, String functionEvent,
			String functionAttachment, ArrayList<String> steps) throws InterruptedException {
		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);
		//Wait.WaitForElement(driver, OR.AssociateFunction);
		Wait.waitForElementToBeClickable(By.xpath(OR.AssociateFunction), driver);
		Utility.ScrollToElement(doctemp.AssociateFunction, driver);
		doctemp.AssociateFunction.click();
		driver.switchTo().frame("dialog-body0");
		System.out.println("frame Switched");
		//Wait.WaitForElement(driver, OR.AssociateFunctionPopup);
		assertTrue(doctemp.AssociateFunctionPopup.getText().contains("Function Picker"),
				"Associate Fiunction Popup does not display");
		//Wait.WaitForElement(driver, OR.Function_Email_CheckBox);
		Wait.waitForElementToBeClickable(By.xpath(OR.Function_Email_CheckBox), driver);
		Utility.ScrollToElement(doctemp.Function_Email_CheckBox, driver);
		doctemp.Function_Email_CheckBox.click();
		assertTrue(doctemp.Function_Email_CheckBox.isSelected(), "Failed: AssociateFunction");
		
		//Wait.WaitForElement(driver, OR.functionConfirmationLetterCheckBox);
		Wait.waitForElementToBeClickable(By.xpath(OR.functionConfirmationLetterCheckBox), driver);
		Utility.ScrollToElement(doctemp.functionConfirmationLetterCheckBox, driver);
		doctemp.functionConfirmationLetterCheckBox.click();
		
		
		Utility.ScrollToElement(doctemp.SavePopup, driver);
		doctemp.SavePopup.click();
		driver.switchTo().defaultContent();
		steps.add("Associate Function : Confirmation Email");
		doctempLogger.info("Associate Function : Confirmation Email");

		//Wait.WaitForElement(driver, OR.Function_Trigger);
		Wait.waitForElementToBeClickable(By.xpath(OR.Function_Trigger), driver);
		Utility.ScrollToElement(doctemp.Function_Trigger, driver);
		doctemp.Function_Trigger.click();
		new Select(doctemp.Function_Trigger).selectByVisibleText(functionTrigger);
		assertTrue(doctemp.Function_Trigger.getText().contains(functionTrigger), "Failed: Function Trigger");
		steps.add("Select Function Trigger : " + functionTrigger);
		doctempLogger.info("Function Trigger :  " + functionTrigger);

		//Wait.WaitForElement(driver, OR.Function_Event);
		Wait.waitForElementToBeClickable(By.xpath(OR.Function_Event), driver);
		Utility.ScrollToElement(doctemp.Function_Event, driver);
		doctemp.Function_Event.click();
		new Select(doctemp.Function_Event).selectByVisibleText(functionEvent);
		assertTrue(doctemp.Function_Event.getText().contains(functionEvent), "Failed: Function Event");
		steps.add("Select Function Event: " + functionEvent);
		doctempLogger.info("Function Event:  " + functionEvent);

		//Wait.WaitForElement(driver, OR_Setup.FUNCTION_ADD_ATTACHMENTS);
		Wait.waitForElementToBeClickable(By.xpath(OR_Setup.FUNCTION_ADD_ATTACHMENTS), driver);
		Utility.ScrollToElement(doctemp.FUNCTION_ADD_ATTACHMENTS, driver);
		doctemp.FUNCTION_ADD_ATTACHMENTS.click();
		doctempLogger.info("Click function Attachment");
		doctempLogger.info("Selectable Function Attachment:  " + functionAttachment);
		new Select(doctemp.FUNCTION_AVAILABLE_ATTACHEMENTS_LIST).selectByVisibleText(functionAttachment);
		doctempLogger.info("Select function Attachment");
		//Wait.WaitForElement(driver, OR_Setup.PICK_ONE_BUTTON);
		Wait.waitForElementToBeClickable(By.xpath(OR_Setup.PICK_ONE_BUTTON), driver);
		Utility.ScrollToElement(doctemp.PICK_ONE_BUTTON, driver);
		doctemp.PICK_ONE_BUTTON.click();
		doctempLogger.info("Click Pick one ");
		// Wait.wait2Second();
		// String selectedAttachment = new
		// Select(driver.findElement(By.xpath(OR_Setup.FUNCTION_ADDED_ATTACHEMENTS_LIST))).getAllSelectedOptions().get(0).getText();
		// assertEquals(selectedAttachment,functionAttachment, "Failed: Function
		// Attachment");
		// doctempLogger.info("Selected Function Attachment: " +
		// selectedAttachment);
		//Wait.WaitForElement(driver, OR_Setup.DONE_BUTTON);
		Wait.waitForElementToBeClickable(By.xpath(OR_Setup.DONE_BUTTON), driver);
		Utility.ScrollToElement(doctemp.DONE_BUTTON, driver);
		doctemp.DONE_BUTTON.click();
		doctempLogger.info("Click Done");
		try {
			Wait.waitForElementToBeGone(driver, doctemp.DONE_BUTTON, 30);
		} catch (Exception e) {

		}
		assertEquals(doctemp.FUNCTION_ADD_ATTACHMENTS.getAttribute("title"), functionAttachment,
				"Failed: Function Attachment");
		steps.add("Select Function Attachment : " + functionAttachment);
		doctempLogger.info("Select Function Attachment:  " + functionAttachment);
		return steps;

	}
	public ArrayList<String> saveDocument(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException {
		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", doctemp.Document_Save);
		Wait.waitUntilPresenceOfElementLocated(OR.Document_Save, driver);
		doctemp.Document_Done.click();
		Wait.wait2Second();
		test_steps.add("Click Save");
		doctempLogger.info("Click Save ");
		Wait.wait5Second();

		try {
			driver.switchTo().alert().accept();
		} catch (NoAlertPresentException e) {
			System.out.println("No Alert Present");
			return test_steps;
		}
		return test_steps;

	}
	public ArrayList<String> deleteDocument(WebDriver driver, String DocName, ArrayList<String> test_steps)
			throws InterruptedException {
		Elements_DocumentTemplates doctemp = new Elements_DocumentTemplates(driver);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", doctemp.CreatedDocument_Pages);

		int Size = driver.findElements(By.xpath(OR.CreatedDocument_Pages)).size();
		if (Size > 2) {
			// System.out.println("Pages : " + (Size - 1));
		}
		boolean found = false;
		for (int Page = 1; Page < Size; Page++) {
			Wait.WaitForElement(driver, OR.DocumentName_list);
			int count = driver.findElements(By.xpath(OR.DocumentName_list)).size();
			// System.out.println(count);
			for (int i = 0; i < count; i++) {
				Wait.wait2Second();
				int rowNumber = i + 1;
				String docName = driver.findElement(By.xpath("(" + OR.DocumentName_list + ")[" + rowNumber + "]"))
						.getText();
				// System.out.println("Document name is " + docName);
				if (docName.equals(DocName)) {
					found = true;
					// System.out.println("Documen Found");
					WebElement CheckBox = driver
							.findElement(By.xpath("(" + OR.DeleteDocument_CheckBox + ")[" + rowNumber + "]"));
					Utility.ScrollToElement(CheckBox, driver);
					CheckBox.click();
					// System.out.println("click Check");
					assertTrue(CheckBox.isSelected(), "Failed: Delete  ChekBox");
					break;
				}
			}
			if (found) {
				if (Page != 1) {
					String firstPagePath = "//tr[@class='TextDefault']/td//following-sibling::*[text()='" + 1 + "']";
					jse.executeScript("arguments[0].scrollIntoView(true);",
							driver.findElement(By.xpath(firstPagePath)));
					driver.findElement(By.xpath(firstPagePath)).click();
					doctempLogger.info("Move to Page " + 1);
					Wait.WaitForElement(driver, OR.DocumentName_list);
				}
				break;
			} else {
				int NextPage = Page + 1;
				String NextPagePath = "//tr[@class='TextDefault']/td//following-sibling::*[text()='" + NextPage + "']";
				jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(NextPagePath)));
				driver.findElement(By.xpath(NextPagePath)).click();
				doctempLogger.info("Move to Page " + NextPage);
				Wait.WaitForElement(driver, OR.DocumentName_list);
			}
		}
		if (found) {
			doctemp.Document_DeleteButton.click();
			test_steps.add(DocName + " Document Delete");
			doctempLogger.info(DocName + " Document Delete");
		} else {
			assertTrue(false, "Document '" + DocName + "' not found");
		}
		return test_steps;
	}
	
	/*public void enter_AllContentFields(WebDriver driver, ArrayList<String> test_steps) throws Exception{
		String custom="//td[@id='Custom fields']";
		Wait.WaitForElement(driver, custom);
		driver.findElement(By.xpath(custom)).click();
		Wait.wait2Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String row="//td[@id='Custom fields']/../../../../div/table/tbody/tr";
		int count=driver.findElements(By.xpath(row)).size();
		for(int i=1;i<=count;i++){
			Wait.wait3Second();
			row="//td[@id='Custom fields']/../../../../div/table/tbody/tr["+i+"]/td";
			Wait.WaitForElement(driver, row);
			jse.executeScript("arguments[0].scrollIntoView(true);",
					driver.findElement(By.xpath(row)));
			jse.executeScript("window.scrollBy(0,-300)");
			String name="//span[contains(text(),'<< "+driver.findElement(By.xpath("//td[@id='Custom fields']/../../../../div/table/tbody/tr["+i+"]/td")).getText().trim()+" >>')]";
			driver.findElement(By.xpath(row)).click();
			System.out.println(name);
			Wait.wait3Second();
			WebElement frame=driver.findElement(By.id("MainContent_innDocumentEditor_tw"));
			driver.switchTo().frame(frame);
			if(name.contains("AdultsChildren")){
				name="//span[contains(text(),'<< Ad/Ch >>')]";
			}
			if(name.contains("RoomClass")){
				name="//span[contains(text(),'<< RooomClass >>')]";
			}
			if(name.contains("ArrivalDate")){
				name="//span[contains(text(),'<< ArrivalDate >>')]";
			}
			if(name.contains("BookedOn")){
				name="//span[contains(text(),'<< BookedOn >>')]";
				
			}
			
			new Actions(driver).sendKeys(driver.findElement(By.xpath(name)), Keys.END).sendKeys(Keys.ENTER).build().perform();
			driver.switchTo().defaultContent();
			if(i!=count){
				custom="//td[@id='Custom fields']";
				Wait.WaitForElement(driver, custom);
				driver.findElement(By.xpath(custom)).click();
				Wait.wait2Second();
			}
		}
	}*/
	

	public void enter_AllContentFields(WebDriver driver, ArrayList<String> test_steps) throws Exception{
		String custom="//td[@id='Custom fields']";
		Wait.WaitForElement(driver, custom);
		driver.findElement(By.xpath(custom)).click();
		Wait.wait2Second();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String row="//td[@id='Custom fields']/../../../../div/table/tbody/tr";
		int count=driver.findElements(By.xpath(row)).size();
		for(int i=1;i<=count;i++){
			Wait.wait3Second();
			row="//td[@id='Custom fields']/../../../../div/table/tbody/tr["+i+"]/td";
			Wait.WaitForElement(driver, row);
			jse.executeScript("arguments[0].scrollIntoView(true);",
					driver.findElement(By.xpath(row)));
			jse.executeScript("window.scrollBy(0,-300)");
			String name="//span[contains(text(),'<< "+driver.findElement(By.xpath("//td[@id='Custom fields']/../../../../div/table/tbody/tr["+i+"]/td")).getText().trim()+" >>')]";
			driver.findElement(By.xpath(row)).click();
			System.out.println(name);
			Wait.wait3Second();
			WebElement frame=driver.findElement(By.id("MainContent_innDocumentEditor_tw"));
			driver.switchTo().frame(frame);
			if(name.contains("AdultsChildren")){
				name="//span[contains(text(),'<< Ad/Ch >>')]";
			}
			if(name.contains("RoomClass")){
				name="//span[contains(text(),'<< RooomClass >>')]";
			}
			new Actions(driver).sendKeys(driver.findElement(By.xpath(name)), Keys.END).sendKeys(Keys.ENTER).build().perform();
			driver.switchTo().defaultContent();
			if(i!=count){
				custom="//td[@id='Custom fields']";
				Wait.WaitForElement(driver, custom);
				driver.findElement(By.xpath(custom)).click();
				Wait.wait2Second();
			}
		}
	}
	public void verifyDocumentTemplateUpdatedUser(WebDriver driver, ArrayList<String> test_steps,String attribute,String reservation,Map map) throws Exception{

		System.out.println(map);
		String[] str=attribute.split("/");
		EmailUtils emailUtils =  new EmailUtils(EmailUtils.EmailFolder.INBOX);
		Message[] msg	=emailUtils.getMessagesBySubject("Reservation #: "+reservation, true, 1);
		/* System.out.println(emailUtils.isTextInMessage(msg[0], reservation));			
		 System.out.println(emailUtils.isTextInMessage(msg[0], resStatus));*/
		if(attribute.contains("userEditedFirstName")){
			if(emailUtils.isTextInMessage(msg[0], map.get("userEditedFirstName").toString())){
				test_steps.add("userEditedFirstName present in scheduling mail : "+map.get("userEditedFirstName"));
			}else{
				test_steps.add("userEditedFirstName not present in scheduling mail : "+map.get("userEditedFirstName"));
			}
		}
		if(attribute.contains("userEditedLastName")){
			if(emailUtils.isTextInMessage(msg[0], map.get("userEditedLastName").toString())){
				test_steps.add("userEditedLastName present in scheduling mail : "+map.get("userEditedLastName"));
			}else{
				test_steps.add("userEditedLastName not present in scheduling mail : "+map.get("userEditedLastName"));
			}
		}
	}
	

}




