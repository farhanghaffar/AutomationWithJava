package com.innroad.inncenter.pageobjects;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.Select;

import com.innroad.inncenter.interfaces.IRatePackage;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Reservation;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.waits.Wait;
import com.innroad.inncenter.webelements.Elements_Inventory;

public class RatePackage implements IRatePackage {

	public static Logger packageRateLogger = Logger.getLogger("RatePackage");

	public void inventory_Rate(WebDriver driver) throws InterruptedException {

		Elements_Inventory rate = new Elements_Inventory(driver);
		Wait.wait3Second();
		rate.click_Inventory.click();
		rate.inventory_rate.click();
		Wait.wait10Second();
		packageRateLogger.info(" System successfully Navigated to Inventory Rates ");
		// Wait.wait10Second();
	}

	public void package_details(WebDriver driver, String packageName) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Elements_Inventory packagerate = new Elements_Inventory(driver);
		Wait.explicit_wait_xpath(OR.newRate, driver);
		packagerate.newRate.click();
		Wait.explicit_wait_xpath(OR.rateName, driver);
		packagerate.rateName.sendKeys(packageName);
		String packageplan = new Select(packagerate.ratePlan).getFirstSelectedOption().getText();

		packageRateLogger.info("Selected Rate Plan : " + packageplan);

		// packageRateLogger.info( "Selected Rate Plan : " +derivedplan);
		packagerate.selectPackageRatePlan.click();
		String rateType = packagerate.getRateType3.getText();
		packageRateLogger.info(" Selected Rate Type : " + rateType);
	}

	public void package_components(WebDriver driver, String PackageCompDescription, String PackageAmount)
			throws InterruptedException {

		Elements_Inventory packagerate = new Elements_Inventory(driver);
		String packageComponents = packagerate.getPackageComponents.getText();
		packageRateLogger.info("packageComponents : " + packageComponents);

		packagerate.clickPackageAddButton.click();

		Select dropdown = new Select(packagerate.selectPackageCategory);
		java.util.List<WebElement> options = dropdown.getOptions();
		int itemSize = options.size();
		packageRateLogger.info(" No of Package rates : " + itemSize);
		Wait.wait3Second();
		/*
		 * for(int i = 0; i < itemSize ; i++) { String optionsValue =
		 * options.get(i).getText(); packageRateLogger.info("Category : "
		 * +optionsValue);
		 */

		// }

		new Select(packagerate.selectPackageCategory).selectByIndex(5);
		Wait.wait3Second();
		String getpackageComponentDescription = packagerate.packageComponentDescription.getText();
		packageRateLogger.info("packageComponentDescription : " + getpackageComponentDescription);
		Wait.wait3Second();
		String getpackageCalculationMethod = new Select(packagerate.packageCalculationMethod).getFirstSelectedOption()
				.getText();
		packageRateLogger.info(" Package Calculation Method " + getpackageCalculationMethod);
		Wait.wait3Second();
		packagerate.enterPackageAmount.sendKeys(PackageAmount);

		Wait.wait10Second();

	}

	public void package_descriptiveInformation(WebDriver driver, String rateDisplayName, String ratePolicy,
			String rateDescription) throws InterruptedException {
		Elements_Inventory packagerate = new Elements_Inventory(driver);
		/// Wait.explicit_wait_xpath(OR.rate_displayName);
		packagerate.rate_displayName.sendKeys(rateDisplayName);
		Wait.explicit_wait_xpath(OR.rate_policy, driver);
		packagerate.rate_policy.sendKeys(ratePolicy);
		Wait.explicit_wait_xpath(OR.rate_description, driver);
		packagerate.rate_description.sendKeys(rateDescription);
		Wait.wait15Second();
	}

	public void associateRate(WebDriver driver) throws InterruptedException {

		Elements_Inventory packagerate = new Elements_Inventory(driver);
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		Wait.wait5Second();

		packageRateLogger.info(driver.getWindowHandles());

		packagerate.clickPackageAssociateRate.click();

		// Thread.sleep(5000);
		// new WebDriverWait(driver,
		// 120).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='MainContent_dgRatesList_chkSelected_0']")));
		// packageRateLogger.info("Waiting for 15 secs");
		// Wait.explicit_wait_xpath(OR.selectRateInPackage);

		Wait.wait5Second();
		driver.switchTo().frame("dialog-body0");

		packagerate.selectRateInPackage.click();

		Wait.wait5Second();
		// jse.executeScript("window.scrollBy(0,1000)");
		// Wait.explicit_wait_xpath(OR.rate_Save_Button);
		packagerate.clickPackageSelectButton.click();
		Wait.wait5Second();
		driver.switchTo().defaultContent();
		packagerate.rate_Save_Button.click();
		Wait.wait5Second();
		packagerate.rate_done_button.click();
		Wait.wait10Second();
		packageRateLogger.info(" Clicked on Done Button ");

	}

	public void delete_rate(WebDriver driver) throws InterruptedException

	{
		Elements_Inventory rate = new Elements_Inventory(driver);

		rate.click_goButton.click();
		Wait.wait5Second();
		rate.selectPRate.click();
		Wait.wait10Second();
		rate.deleteRate.click();
		Wait.wait10Second();
		packageRateLogger.info(" System sucessfully deleted the Rate ");
		Wait.wait10Second();
	}

	public void delete_rate(WebDriver driver, String Rate) throws InterruptedException

	{
		Elements_Inventory rate = new Elements_Inventory(driver);
		Utility.ScrollToElement(rate.click_goButton, driver);
		rate.click_goButton.click();
		String RatePath = "//a[contains(text(),'"+Rate+"')]/ ../preceding-sibling::td/span/input";
		WebElement Rate_Delete = driver.findElement(By.xpath(RatePath));
		Wait.explicit_wait_visibilityof_webelement(Rate_Delete, driver);
		Utility.ScrollToElement(Rate_Delete, driver);
		Rate_Delete.click();
		Utility.ScrollToElement(rate.deleteRate, driver);
		rate.deleteRate.click();
		packageRateLogger.info(" System sucessfully deleted the Rate ");
	}
	public ArrayList<String> clickOnPackageTab(WebDriver driver) throws InterruptedException{

		Elements_Inventory rate = new Elements_Inventory(driver);
		ArrayList<String> testSteps  = new ArrayList<>();
		rate.btnPackage.click();
		packageRateLogger.info("Navigated to Package");
		testSteps.add("Navigated to Package");
		
		Wait.WaitForElement(driver, OR.newRate);
		Wait.waitForElementToBeVisibile(By.xpath(OR.newRate), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.newRate), driver);
		
		return testSteps;
			
	}
public ArrayList<String> packageDetails(WebDriver driver, String packageName, String RatePlan) throws InterruptedException {
		
		Elements_Inventory packagerate = new Elements_Inventory(driver);
		ArrayList<String> test_steps = new ArrayList<>();
		Wait.WaitForElement(driver, OR.newRate);
		Wait.waitForElementToBeVisibile(By.xpath(OR.newRate), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.newRate), driver);
		packagerate.newRate.click();
		test_steps.add("Clicked New Rate");
		packageRateLogger.info("Clicked New Rate");
		
		Wait.WaitForElement(driver, OR.rateName);
		Wait.waitForElementToBeVisibile(By.xpath(OR.rateName), driver);
		Wait.waitForElementToBeClickable(By.xpath(OR.rateName), driver);
		
		packagerate.rateName.sendKeys(packageName);
		test_steps.add("Entered Package name : " + packageName);
		packageRateLogger.info("Entered Package name : " + packageName);
		
		new Select(packagerate.ratePlan).selectByVisibleText(RatePlan);
		String packageplan = new Select(packagerate.ratePlan).getFirstSelectedOption().getText();

		packageRateLogger.info("Selected Rate Plan : " + packageplan);
		test_steps.add("Selected Rate Plan : " + packageplan);
		packageRateLogger.info("Selected Rate Plan : " + packageplan);
		
		// packageRateLogger.info( "Selected Rate Plan : " +derivedplan);
		packagerate.selectPackageRatePlan.click();
		String rateType = packagerate.getRateType3.getText();
		packageRateLogger.info(" Selected Rate Type : " + rateType);
		test_steps.add(" Selected Rate Type : " + rateType);
		
		return test_steps;
	}

public ArrayList<String> packageComponentWithAddOn(WebDriver driver, String PackageCategory, String PackageAmount) throws InterruptedException {

	Elements_Inventory packagerate = new Elements_Inventory(driver);
	ArrayList<String> testSteps = new ArrayList<>();
	packagerate.selectAddOn.click();
	String packageComponents = packagerate.selectAddOnLabel.getText();
	packageRateLogger.info("packageComponents : " + packageComponents);
	testSteps.add("packageComponents : " + packageComponents);

	packagerate.clickPackageAddButton.click();
	Wait.explicit_wait_visibilityof_webelement(packagerate.selectPackageCategory, driver);

	Select dropdown = new Select(packagerate.selectPackageCategory);
	java.util.List<WebElement> options = dropdown.getOptions();
	int itemSize = options.size();
	packageRateLogger.info(" No of Package rates : " + itemSize);

	new Select(packagerate.selectPackageCategory).selectByVisibleText(PackageCategory);
	packageRateLogger.info("Selected Category :  " + PackageCategory);
	testSteps.add("elected Category:  " + PackageCategory);

	String getpackageComponentDescription = packagerate.packageComponentDescription.getText();
	packageRateLogger.info("packageComponentDescription : " + getpackageComponentDescription);

	String getpackageCalculationMethod = new Select(packagerate.packageCalculationMethod).getFirstSelectedOption()
			.getText();
	packageRateLogger.info(" Package Calculation Method " + getpackageCalculationMethod);
	testSteps.add(" Package Calculation Method " + getpackageCalculationMethod);
	
	packagerate.enterPackageAmount.sendKeys(PackageAmount);
	packageRateLogger.info(" Entered Package Amount :  " + PackageAmount);
	testSteps.add(" Entered Package Amount :  " + PackageAmount);
	

	return testSteps;
}

public ArrayList<String> packageDescriptiveInformation(WebDriver driver, String rateDisplayName, String ratePolicy,
		String rateDescription) throws InterruptedException {
	Elements_Inventory packagerate = new Elements_Inventory(driver);
	ArrayList<String> test_steps = new ArrayList<>();
	packagerate.rate_displayName.sendKeys(rateDisplayName);
	test_steps.add("Entered Display Name : " + rateDisplayName);
	packageRateLogger.info("Entered Display Name : " + rateDisplayName);
	Wait.WaitForElement(driver, OR.enterPolicyName);
	Wait.waitForElementToBeVisibile(By.xpath(OR.enterPolicyName), driver);
	packagerate.rate_policy.sendKeys(ratePolicy);
	test_steps.add("Entered Policy : " + ratePolicy);
	packageRateLogger.info("Entered Policy : " + ratePolicy);
	
	packagerate.rateDescription.sendKeys(rateDescription);
	test_steps.add("Entered Description : " + rateDescription);
	packageRateLogger.info("Entered Description : " + rateDescription);
	return test_steps;
}


public ArrayList<String> clickOnAlwaysAvailableRate(WebDriver driver) throws InterruptedException{
	Elements_Inventory packagerate = new Elements_Inventory(driver);
	ArrayList<String> test_steps = new ArrayList<>();
	Wait.WaitForElement(driver, OR.alwaysAvailableRate);
	Wait.waitForElementToBeVisibile(By.xpath(OR.alwaysAvailableRate), driver);
	Wait.waitForElementToBeClickable(By.xpath(OR.alwaysAvailableRate), driver);
	Utility.ScrollToElement_NoWait(packagerate.alwaysAvailableRate, driver);
	Wait.explicit_wait_elementToBeClickable(packagerate.alwaysAvailableRate, driver);
	if(packagerate.alwaysAvailableRate.isSelected()){
	test_steps.add("Associate Rates Always Available CheckBox already Selected");
	packageRateLogger.info("Associate Rates Always Available CheckBox already Selected");
	}else{
		packagerate.alwaysAvailableRate.click();
		test_steps.add("Associate Rates Always Available CheckBox Selected");
		packageRateLogger.info("Associate Rates Always Available CheckBox Selected");

	}
	return test_steps;
}

public ArrayList<String> SavePackageRate(WebDriver driver) throws InterruptedException{
	
	Elements_Inventory packagerate = new Elements_Inventory(driver);
	ArrayList<String> test_steps =  new ArrayList<>();
	packagerate.rate_Save_Button.click();
	packageRateLogger.info(" Clicked on Save Button ");
	test_steps.add(" Clicked on Save Button ");

	Wait.WaitForElement(driver, OR.rate_done_button);
	Wait.waitForElementToBeVisibile(By.xpath(OR.rate_done_button), driver);
	Wait.waitForElementToBeClickable(By.xpath(OR.rate_done_button), driver);
	packagerate.rate_done_button.click();
	packageRateLogger.info(" Clicked on Done Button ");
	test_steps.add(" Clicked on Done Button ");
	
	return test_steps;
}
public void package_details(WebDriver driver, String packageName, ArrayList<String>test_steps) throws InterruptedException {
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	Elements_Inventory packagerate = new Elements_Inventory(driver);
	Wait.explicit_wait_xpath(OR.newRate, driver);
	packagerate.newRate.click();
	Wait.explicit_wait_xpath(OR.rateName, driver);
	packagerate.rateName.sendKeys(packageName);
	String packageplan = new Select(packagerate.ratePlan).getFirstSelectedOption().getText();

	packageRateLogger.info("Selected Rate Plan : " + packageplan);

	// packageRateLogger.info( "Selected Rate Plan : " +derivedplan);
	packagerate.selectPackageRatePlan.click();
	String rateType = packagerate.getRateType3.getText();
	packageRateLogger.info(" Selected Rate Type : " + rateType);
	test_steps.add("Selected Rate is: "+ "<b>"+rateType+"</>");
}
public void selectRatePlan(WebDriver driver, String rateplanName) {
	Elements_Inventory packagerate = new Elements_Inventory(driver);
	Wait.explicit_wait_10sec(packagerate.ratePlan, driver);
	new Select(packagerate.ratePlan).selectByVisibleText(rateplanName);
}
public void package_components(WebDriver driver, String PackageAmount, String PackageCompDescription, ArrayList<String>test_steps)
		throws InterruptedException {

	Elements_Inventory packagerate = new Elements_Inventory(driver);
	String packageComponents = packagerate.getPackageComponents.getText();
	packageRateLogger.info("packageComponents : " + packageComponents);

	packagerate.clickPackageAddButton.click();
	test_steps.add("click Package Add Button");

	Select dropdown = new Select(packagerate.selectPackageCategory);
	java.util.List<WebElement> options = dropdown.getOptions();
	int itemSize = options.size();
	packageRateLogger.info(" No of Package rates : " + itemSize);
	Wait.wait3Second();
	/*
	 * for(int i = 0; i < itemSize ; i++) { String optionsValue =
	 * options.get(i).getText(); packageRateLogger.info("Category : "
	 * +optionsValue);
	 */

	// }

	new Select(packagerate.selectPackageCategory).selectByIndex(5);
	Wait.wait3Second();
	String getpackageComponentDescription = packagerate.packageComponentDescription.getText();
	packageRateLogger.info("packageComponentDescription : " + getpackageComponentDescription);
	test_steps.add("Package Component Description: "+"<b>"+getpackageComponentDescription+"</b>" );
	Wait.wait3Second();
	String getpackageCalculationMethod = new Select(packagerate.packageCalculationMethod).getFirstSelectedOption()
			.getText();
	packageRateLogger.info(" Package Calculation Method " + getpackageCalculationMethod);
	test_steps.add(" Package Calculation Method: "+"<b>"+getpackageCalculationMethod+"</b>" );
	Wait.wait3Second();
	packagerate.enterPackageAmount.sendKeys(PackageAmount);
	test_steps.add("Package Component Amount: "+"<b>"+PackageAmount+"</b>" );

	Wait.wait10Second();

}
public void assoCiateRate(WebDriver driver, String rateName, ArrayList<String> test_steps, String roomClassName) throws InterruptedException {

	Elements_Inventory packagerate = new Elements_Inventory(driver);
	JavascriptExecutor js1 = (JavascriptExecutor) driver;
	js1.executeScript("window.scrollTo(0, document.body.scrollHeight)");

	Wait.wait5Second();

	packageRateLogger.info(driver.getWindowHandles());

	packagerate.clickPackageAssociateRate.click();
	test_steps.add("Click at associate rate: "+ "<b>"+rateName+"</b>");

	Wait.wait5Second();
	driver.switchTo().frame("dialog-body0");
	//select[@id='MainContent_drpRoomClassList']
	//packagerate.selectRateInPackage.click();
	Wait.waitUntilPresenceOfElementLocated(OR_Reservation.SelectRoomClassInSearch, driver);
new Select(packagerate.selectRoomClassInSearch).selectByVisibleText(roomClassName);
packagerate.clickSearchGO.click();

	String xpath= "//a[text()='"+rateName+"']/ancestor::tr/td/span/input[@type='checkbox']";
	System.out.println(xpath);
	Wait.waitUntilPresenceOfElementLocated(xpath, driver);
	Wait.explicit_wait_elementToBeClickable(driver.findElement(By.xpath(xpath)), driver);
	driver.findElement(By.xpath(xpath)).click();
	test_steps.add("select rate checkbox");

	Wait.wait5Second();
	// jse.executeScript("window.scrollBy(0,1000)");
	// Wait.explicit_wait_xpath(OR.rate_Save_Button);
	packagerate.clickPackageSelectButton.click();
	test_steps.add("Click at select button");
	Wait.wait5Second();
	driver.switchTo().defaultContent();
	packagerate.rate_Save_Button.click();
	test_steps.add("click at save button");
	Wait.wait5Second();
	packagerate.rate_done_button.click();
	test_steps.add("click Done");
	Wait.wait10Second();
	packageRateLogger.info(" Clicked on Done Button ");

}

public void clickPackage(WebDriver driver) throws InterruptedException {
	Elements_Inventory rate = new Elements_Inventory(driver);
	Utility.ScrollToElement(rate.click_goButton, driver);
	rate.clickPackageTab.click();
	
}



}
