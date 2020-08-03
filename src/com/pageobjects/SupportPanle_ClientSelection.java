package com.innroad.inncenter.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.innroad.inncenter.interfaces.ISupportPanel_ClientSelection;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.WebElements_Support_Panel_Client_Selection;

public class SupportPanle_ClientSelection implements com.innroad.inncenter.interfaces.ISupportPanel_ClientSelection{

	@Override
	public void Client_Selection(WebDriver driver, String ClientName) throws InterruptedException {
		
		
		WebElements_Support_Panel_Client_Selection ClientSelection = new WebElements_Support_Panel_Client_Selection(driver);
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
		driver.switchTo().frame("main");
		Thread.sleep(5000);
		ClientSelection.ClickArrow.click();
		Thread.sleep(9000);
		ClientSelection.clientTextBox.click();
		ClientSelection.clientTextBox.sendKeys(ClientName);
		ClientSelection.clientTextBox.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		
	}
	
public void Client_Selection_WithId(WebDriver driver, String clientCode) throws InterruptedException {
		
		
		WebElements_Support_Panel_Client_Selection ClientSelection = new WebElements_Support_Panel_Client_Selection(driver);
		ClientSelection.Settings_Icon.click();
		Wait.explicit_wait_visibilityof_webelement(ClientSelection.Pencil_Icon, driver);
		ClientSelection.Pencil_Icon.click();
		Wait.explicit_wait_visibilityof_webelement(ClientSelection.Enter_client_TextBox, driver);
		ClientSelection.Enter_client_TextBox.sendKeys(clientCode);
		Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath("//li//small[text()='"+clientCode+"']")), driver);
		driver.findElement(By.xpath("//li//small[text()='"+clientCode+"']")).click();
		Wait.explicit_wait_visibilityof_webelement(ClientSelection.Settings_Icon, driver);
		
		
	}

	

}
