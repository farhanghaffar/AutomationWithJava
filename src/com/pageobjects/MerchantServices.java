package com.innroad.inncenter.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;

public class MerchantServices {
	private static Logger merchantLogger = Logger.getLogger("MerchantServices");
	
	public void selectFirstItem(WebDriver driver) {
		String tablePath = "//*[@id='MainContent_dgMerchantService']/tbody/tr";
		
		int size = driver.findElements(By.xpath(tablePath)).size();
		if(size>2) {
			assertTrue(true);
			driver.findElement(By.xpath(tablePath+"[2]/td/a")).click();
			Wait.explicit_wait_xpath("//*[@id='tdTitle']/font[.='Merchant Account']", driver);
			Wait.explicit_wait_visibilityof_webelement(driver.findElement(By.xpath("//*[@id='tdTitle']/font[.='Merchant Account']")), driver);
			boolean flag=driver.findElement(By.xpath("//*[@id='tdTitle']/font[.='Merchant Account']")).isDisplayed();
			assertTrue(flag,"Failed: Table Link not Clicked");
		}else {
			assertFalse(true);
		}
	}
	
	public void checkRequireCVV(WebDriver driver,boolean isChecked) throws InterruptedException {
		String path = "//*[@id='MainContent_chkRequireCVV']";
		WebElement inputCVV = driver.findElement(By.xpath(path));
		WebElement saveBtn = driver.findElement(By.xpath("//*[@id='MainContent_btnSave']"));
		
		Utility.ScrollToElement(inputCVV, driver);
		if(isChecked) {
			if(!inputCVV.isSelected()) {
				inputCVV.click();
				Wait.explicit_wait_elementToBeClickable(saveBtn, driver);
				saveBtn.click();
			}
		}else {
			if(inputCVV.isSelected()) {
				inputCVV.click();
				Wait.explicit_wait_elementToBeClickable(saveBtn, driver);
				saveBtn.click();
			}
		}
		assertEquals(isChecked,driver.findElement(By.xpath(path)).isSelected(),"Failed: CVV Require");
		
	}
}
