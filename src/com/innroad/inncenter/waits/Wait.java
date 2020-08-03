package com.innroad.inncenter.waits;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.innroad.inncenter.utils.Utility;

public class Wait {

	public static void wait1Second() throws InterruptedException {
		Thread.sleep(1000);
	}

	public static void wait2Second() throws InterruptedException {
		Thread.sleep(2000);
	}

	public static void wait3Second() throws InterruptedException {
		Thread.sleep(3000);
	}

	public static void wait5Second() throws InterruptedException {
		Thread.sleep(5000);
	}

	public static void wait10Second() throws InterruptedException {
		Thread.sleep(10000);
	}

	public static void wait15Second() throws InterruptedException {
		Thread.sleep(16000);
	}

	public static void wait25Second() throws InterruptedException {
		Thread.sleep(26000);
	}

	public static void wait40Second() throws InterruptedException {
		Thread.sleep(41000);
	}

	public static void wait30Second() throws InterruptedException {
		Thread.sleep(31000);
	}

	public static void wait60Second() throws InterruptedException {
		Thread.sleep(60000);
	}

	public static void explicit_wait_xpath(String Element, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Element)));
	}

	public static void waitForFrame(By by, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
	}

	public static void waitUntilPresenceOfElementLocated(String Element, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Element)));
	
	}

	public static void waitUntilPresenceOfElementLocatedByClassName(String Element, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(Element)));
	}

	public static void explicit_wait_absenceofelement(String Element, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(Element)));
	}


	public static void explicit_wait_visibilityof_webelement (WebElement Element, WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(Element));

	}

	public static void explicit_wait_10sec(WebElement Element, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(Element));

	}

	public static void timeCal() {
		long startTime = System.currentTimeMillis();
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime);
	}

	public static void waitforloadpage(long startTime, long endTime, double waittime) {

		double totalTime = (endTime - startTime);
		// System.out.println(totalTime + " in Millsecs");
		double TotalTimeinsecs = totalTime / 1000;
		double ActualTime = TotalTimeinsecs - waittime;
		// System.out.println(ActualTime + " in secs");
	}

	public static void explicit_wait_elementToBeClickable(WebElement Element, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(Element));

	}

	public static void explicit_wait_visibilityof_webelement_120(WebElement Element, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOf(Element));

	}

	public static void explicit_wait_visibilityof_webelement_150(WebElement Element, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 150);
		wait.until(ExpectedConditions.visibilityOf(Element));

	}

	public static void explicit_wait_visibilityof_webelement_200(WebElement Element, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 200);
		wait.until(ExpectedConditions.visibilityOf(Element));

	}

	public static void explicit_wait_visibilityof_webelement_350(WebElement Element, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 350);
		wait.until(ExpectedConditions.visibilityOf(Element));

	}

	public static void explicit_wait_visibilityof_webelement_600(WebElement Element, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 1200);
		wait.until(ExpectedConditions.visibilityOf(Element));

	}

	public static void WaitForElement(WebDriver driver, String locator) {
		FluentWait<RemoteWebDriver> wait = new FluentWait<RemoteWebDriver>((RemoteWebDriver) driver);

		// new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(50)).pollingEvery(Duration.ofSeconds(10)).ignoring(ElementNotVisibleException.class);

		wait.withTimeout(Duration.ofSeconds(200));
		wait.pollingEvery(Duration.ofSeconds(3));
		
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(WebDriverException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(ElementNotVisibleException.class);
		
		List<WebElement> targetElements = wait.until(new Function<RemoteWebDriver, List<WebElement>>() {
			int count = driver.findElements(By.xpath(locator)).size();

			public List<WebElement> apply(RemoteWebDriver driver) {

				List<WebElement> elements = driver.findElements(By.xpath(locator));
				int length = elements.size();

				if (length >= 1 || count > 0) {

					try {
						Thread.sleep(750);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return elements;

				}
				return null;
			}

		});
	}

	public static void WaitForElement_ID(WebDriver driver, String locator) {
		FluentWait<RemoteWebDriver> wait = new FluentWait<RemoteWebDriver>((RemoteWebDriver) driver);

		// new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(50)).pollingEvery(Duration.ofSeconds(10)).ignoring(ElementNotVisibleException.class);

		wait.withTimeout(Duration.ofSeconds(200));
		wait.pollingEvery(Duration.ofSeconds(3));
		
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(WebDriverException.class);
		wait.ignoring(StaleElementReferenceException.class);
		List<WebElement> targetElements = wait.until(new Function<RemoteWebDriver, List<WebElement>>() {
			int count = driver.findElements(By.id(locator)).size();

			public List<WebElement> apply(RemoteWebDriver driver) {

				List<WebElement> elements = driver.findElements(By.id(locator));
				int length = elements.size();

				if (length >= 1 || count > 0) {

					try {
						Thread.sleep(750);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return elements;

				}
				return null;
			}

		});
	}

	public static void WaitForElementUsingClassName(WebDriver driver, String locator) {

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>((WebDriver) driver);

		// new FluentWait<WebDriver>(driver).withTimeout(50, TimeUnit.SECONDS).pollingEvery(10, TimeUnit.SECONDS).ignoring(ElementNotVisibleException.class);

		wait.withTimeout(Duration.ofSeconds(200));
		wait.pollingEvery(Duration.ofSeconds(3));
		
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(WebDriverException.class);
		wait.ignoring(StaleElementReferenceException.class);
		
		List<WebElement> waitUntilTargetElementsDisplayed = wait.until(new Function<WebDriver, List<WebElement>>() {
			int count = driver.findElements(By.className(locator)).size();

			public List<WebElement> apply(WebDriver driver) {

				List<WebElement> elements = driver.findElements(By.className(locator));
				int length = elements.size();

				if (length >= 1 || count > 0) {

					try {
						Thread.sleep(750);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return elements;

				}
				return null;
			}

		});
		
		// wait.until(waitUntilTargetElementsDisplayed);
	}

	public static void waitForElementToBeGone(WebDriver driver, WebElement element, int timeout) {

		new WebDriverWait(driver, timeout).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));

	}

	public static boolean isElementDisplayed(WebDriver driver, WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(element));
			return element.isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException
				| org.openqa.selenium.TimeoutException e) {
			return false;
		}
	}

	public static void explicit_wait_xpath(WebDriver driver, String Element) {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Element)));
	}

	public static void waitUntilPresenceOfElementLocated(WebDriver driver, String Element) {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Element)));
	}

	public static void explicit_wait_visibilityof_webelement_3(WebElement Element, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.visibilityOf(Element));

	}
	
	public static void presenceOfElementLocated(WebDriver driver, String Element, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Element)));
	}

	public static void waitForElementToBeInvisible(WebDriver driver, WebElement element, int timeout) {

		try {

			new WebDriverWait(driver, timeout).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));

		} catch (Exception e) {

			new WebDriverWait(driver, timeout).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
		}
	}

	public static void waitForCPReservationLoading(WebDriver driver) {

		String path = "//div[contains(@class,'loader')]";
		try {
			int size = driver.findElements(By.xpath(path)).size();
			for (int i = 0; i < size; i++) {
				if (driver.findElements(By.xpath(path)).get(i).isDisplayed()) {
					System.out.println("Loading Displayed Waiting now...");
					Wait.waitForElementToBeGone(driver, driver.findElements(By.xpath(path)).get(i), 60);
				}
			}

		} catch (Exception e) {
			Utility.app_logs.error("No Reservation Loading");
		}
	}

	public static void loadingImage(WebDriver driver) {
		try {
			String path = "//*[@id='InnerFreezePane']/img";
			Wait.waitForElementToBeGone(driver, driver.findElement(By.xpath(path)), 180);
		} catch (Exception e) {
			System.out.println("No Loading");
		}
	}

	public static void waitForElementToBeVisibile(By xpath, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));

	}


	public static void waitForElementToBeVisibile(By xpath, WebDriver driver, int timeOut)
	{
	WebDriverWait wait = new WebDriverWait(driver, timeOut);
	wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));

	}
	
	
	

	public static void waitForElementToBeClickable(By xpath, WebDriver driver, int timeout)
	{
	WebDriverWait wait = new WebDriverWait(driver, timeout);
	wait.until(ExpectedConditions.elementToBeClickable(xpath));

	}


	public static void waitForElementToBeSelected(By xpath, WebDriver driver){
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.elementToBeSelected(xpath));
				
		} catch (Exception e) {
			System.out.println(e);
		}
	}


public static boolean waitForNewWindow(WebDriver driver, int timeout){
    boolean flag = false;
    int counter = 0;
    while(!flag){
        try {
            Set<String> winId = driver.getWindowHandles();
            if(winId.size() > 1){
                flag = true;
                return flag;
            }
            counter++;
            if(counter > timeout){
                return flag;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    return flag;
}

	public static void waitForToasterToBeVisibile(By xpath, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 1);
		wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));

	}


	public static void waitForElementToBeClickable(By xpath, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(xpath));

	}

	
	public static void waitTillElementDisplayed(WebDriver driver, String locator) {
		String loading = locator;
		int counter = 0;
		while (true) {
			if (!driver.findElement(By.xpath(loading)).isDisplayed()) {
				break;
			} else if (counter == 40) {
				break;
			} else {
				counter++;
			}
		}
	}

	public static void waitUntilElementToBeSelected(By xpath, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeSelected(xpath));
	}

	public static String waitForElementNotVisible(int timeOutInSeconds, WebDriver driver, String elementXPath) {
		if ((driver == null) || (elementXPath == null) || elementXPath.isEmpty()) {

			return "element not exist or driver not exist";
		}
		try {
			(new WebDriverWait(driver, timeOutInSeconds))
					.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(elementXPath)));
			return null;
		} catch (TimeoutException e) {
			return "timeOut";
		}
	}
	


	
  public static void waitforPageLoad(int pageLoadTimeout, WebDriver driver)
  {
	 try
	 {
		 new WebDriverWait(driver, pageLoadTimeout).until(
			      webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
	 }
	 catch (TimeoutException e) {
	       System.out.println(e);
	   }
  }


		public static void explicit_wait_absenceofelement(String Element, int timeout ,WebDriver driver) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(Element)));
		}

		
		public static void waitForElementToBeClickable(By xpath, int timeout,WebDriver driver)
		{
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(xpath));

		}

	public static void waitForElementToBeClickableUsingClass(By xpath, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(xpath));

	}


	public static void waitForSpecifiedTimeOutInSecs(int timeOutInSeconds) {

		try {
			Thread.sleep(timeOutInSeconds*1000);
		}catch(InterruptedException ie) {
			System.out.println("Exception occured while waiting, \n");
			ie.printStackTrace();
		}
	}

	public static void waitForSpecifiedTimeOutInMills(int timeOutInMilliSeconds) {

		try {
			Thread.sleep(timeOutInMilliSeconds);
		}catch(InterruptedException ie) {
			System.out.println("Exception occured while waiting, \n");
			ie.printStackTrace();
		}
	}

	public static String waitUntilPageIsLoaded(WebDriver driver, int pageLoadTimeoutInSeconds )
	{
		if ((driver == null) || pageLoadTimeoutInSeconds <=0 ) {

			return "Missing information, please check the input arguments!";
		}
		
		try
		{
			new WebDriverWait(driver, pageLoadTimeoutInSeconds).until(
					webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
			return "PageIsLoaded";
		}
		catch (TimeoutException te) {
			System.out.println("Exception occured while waiting, \n");
			te.printStackTrace();
			return "Exception occured while waiting";
		}
	}
	  
	public static String waitUntilElementToBePresentByXapth(String byXpathExpression, WebDriver driver, int timeOutInSeconds) {

		if (( byXpathExpression == null ) || (driver == null) || timeOutInSeconds <=0 ) {

			return "Missing information, please check the input arguments!";
		}
		
		WebDriverWait driverWait = new WebDriverWait(driver, timeOutInSeconds);
		driverWait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
				  .pollingEvery(Duration.ofMillis(250))
				  .until(ExpectedConditions.presenceOfElementLocated(By.xpath(byXpathExpression)));
		
		return "WaitCompleted";
	}
	
	
	public static String waitUntilElementToBePresentByLocator(By byLocator, WebDriver driver, int timeOutInSeconds) {

		if (( byLocator == null ) || (driver == null) || timeOutInSeconds <=0 ) {

			return "Missing information, please check the input arguments!";
		}
		
		WebDriverWait driverWait = new WebDriverWait(driver, timeOutInSeconds);
		driverWait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
				  .pollingEvery(Duration.ofMillis(250))
				  .until(ExpectedConditions.presenceOfElementLocated(byLocator));
		
		return "WaitCompleted";
	}
	

	public static String waitUntilElementToBeVisibleByXapth(String byXpathExpression, WebDriver driver, int timeOutInSeconds) {

		if (( byXpathExpression == null ) || (driver == null) || timeOutInSeconds <=0 ) {

			return "Missing information, please check the input arguments!";
		}
		
		WebDriverWait driverWait = new WebDriverWait(driver, timeOutInSeconds);
		driverWait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
				  .ignoring(ElementNotVisibleException.class)
				  .pollingEvery(Duration.ofMillis(250))
				  .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(byXpathExpression)));
		
		return "WaitCompleted";
	}
	
	
	public static String waitUntilElementToBeVisibleByLocator(By byLocator, WebDriver driver, int timeOutInSeconds) {

		if (( byLocator == null ) || (driver == null) || timeOutInSeconds <=0 ) {

			return "Missing information, please check the input arguments!";
		}
		
		WebDriverWait driverWait = new WebDriverWait(driver, timeOutInSeconds);
		driverWait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
				  .ignoring(ElementNotVisibleException.class)
				  .pollingEvery(Duration.ofMillis(250))
				  .until(ExpectedConditions.visibilityOfElementLocated(byLocator));
		
		return "WaitCompleted";
	}
	

	public static String waitUntilElementToBeInVisibleByXapth(String byXpathExpression, WebDriver driver, int timeOutInSeconds) {

		if (( byXpathExpression == null ) || (driver == null) || timeOutInSeconds <=0 ) {

			return "Missing information, please check the input arguments!";
		}
		
		WebDriverWait driverWait = new WebDriverWait(driver, timeOutInSeconds);
		driverWait.ignoring(StaleElementReferenceException.class)
				  .pollingEvery(Duration.ofMillis(250))
				  .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(byXpathExpression)));
		
		return "WaitCompleted";
	}
	
	public static String waitUntilElementToBeInVisibleByLocator(By byLocator, WebDriver driver, int timeOutInSeconds) {

		if (( byLocator == null ) || (driver == null) || timeOutInSeconds <=0 ) {

			return "Missing information, please check the input arguments!";
		}
		
		WebDriverWait driverWait = new WebDriverWait(driver, timeOutInSeconds);
		driverWait.ignoring(StaleElementReferenceException.class)
				  .pollingEvery(Duration.ofMillis(250))
				  .until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
		
		return "WaitCompleted";
	}
	
	public static String waitUntilElementToBeClickableByXapth(String byXpathExpression, WebDriver driver, int timeOutInSeconds) {

		if (( byXpathExpression == null ) || (driver == null) || timeOutInSeconds <=0 ) {

			return "Missing information, please check the input arguments!";
		}
		
		WebDriverWait driverWait = new WebDriverWait(driver, timeOutInSeconds);
		driverWait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
				  .ignoring(ElementNotVisibleException.class, ElementClickInterceptedException.class)
				  .ignoring(ElementNotInteractableException.class)
				  .pollingEvery(Duration.ofMillis(250))
				  .until(ExpectedConditions.elementToBeClickable(By.xpath(byXpathExpression)));
		
		return "WaitCompleted";
	}	

	public static String waitUntilElementToBeClickableByLocator(By byLocator, WebDriver driver, int timeOutInSeconds) {

		if (( byLocator == null ) || (driver == null) || timeOutInSeconds <=0 ) {

			return "Missing information, please check the input arguments!";
		}
		
		WebDriverWait driverWait = new WebDriverWait(driver, timeOutInSeconds);
		driverWait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
				  .ignoring(ElementNotVisibleException.class, ElementClickInterceptedException.class)
				  .ignoring(ElementNotInteractableException.class)
				  .pollingEvery(Duration.ofMillis(250))
				  .until(ExpectedConditions.elementToBeClickable(byLocator));
		
		return "WaitCompleted";
	}
	
	public static String waitUntilElementToBeSelectedByXapth(String byXpathExpression, WebDriver driver, int timeOutInSeconds) {

		if (( byXpathExpression == null ) || (driver == null) || timeOutInSeconds <=0 ) {

			return "Missing information, please check the input arguments!";
		}
		
		WebDriverWait driverWait = new WebDriverWait(driver, timeOutInSeconds);
		driverWait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
				  .ignoring(ElementNotVisibleException.class, ElementClickInterceptedException.class)
				  .ignoring(ElementNotInteractableException.class)
				  .pollingEvery(Duration.ofMillis(250))
				  .until(ExpectedConditions.elementToBeSelected(By.xpath(byXpathExpression)));
		
		return "WaitCompleted";
	}

	public static String waitUntilElementToBeSelectedByLocator(By byLocator, WebDriver driver, int timeOutInSeconds) {

		if (( byLocator == null ) || (driver == null) || timeOutInSeconds <=0 ) {

			return "Missing information, please check the input arguments!";
		}
		
		WebDriverWait driverWait = new WebDriverWait(driver, timeOutInSeconds);
		driverWait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
				  .ignoring(ElementNotVisibleException.class, ElementClickInterceptedException.class)
				  .ignoring(ElementNotInteractableException.class)
				  .pollingEvery(Duration.ofMillis(250))
				  .until(ExpectedConditions.elementToBeSelected(byLocator));
		
		return "WaitCompleted";
	}
	
	public static String waitUntilButtonIsEnabledByXapth(String byXpathExpression, WebDriver driver, int timeOutInSeconds) {

		if (( byXpathExpression == null ) || (driver == null) || timeOutInSeconds <=0 ) {

			return "Missing information, please check the input arguments!";
		}
		
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
				  .ignoring(ElementNotVisibleException.class, ElementClickInterceptedException.class)
				  .ignoring(ElementNotInteractableException.class)
				  .pollingEvery(Duration.ofMillis(250))
				  .withTimeout(Duration.ofSeconds(timeOutInSeconds));
		
		Function<WebDriver, Boolean> waitUntilButtonIsEnabled = new Function<WebDriver, Boolean>() {
			
			@Override
			public Boolean apply(WebDriver driver) {
				if(driver.findElement(By.xpath(byXpathExpression)).isEnabled()) {
					return true;
				}else {
					return false;
				}
			}
		};
		
		fluentWait.until(waitUntilButtonIsEnabled);
		
		return "WaitCompleted";
	}
	
	public static String waitUntilButtonIsEnabledByLocator(By byLocator, WebDriver driver, int timeOutInSeconds) {

		if (( byLocator == null ) || (driver == null) || timeOutInSeconds <=0 ) {

			return "Missing information, please check the input arguments!";
		}
		
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
				  .ignoring(ElementNotVisibleException.class, ElementClickInterceptedException.class)
				  .ignoring(ElementNotInteractableException.class)
				  .pollingEvery(Duration.ofMillis(250))
				  .withTimeout(Duration.ofSeconds(timeOutInSeconds));
		
		Function<WebDriver, Boolean> waitUntilButtonIsEnabled = new Function<WebDriver, Boolean>() {
			
			@Override
			public Boolean apply(WebDriver driver) {
				if(driver.findElement(byLocator).isEnabled()) {
					return true;
				}else {
					return false;
				}
			}
		};
		
		fluentWait.until(waitUntilButtonIsEnabled);
		
		return "WaitCompleted";
	}
	
	public static void waitUntilPageLoadNotCompleted( WebDriver driver, int timeOutInSeconds)
	{
		new WebDriverWait(driver, timeOutInSeconds).until(
			      webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
	}
	
	
	public static  void waitUntilElementDisplayed( WebElement webElement, WebDriver driver, int timeOut) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		ExpectedCondition elementIsDisplayed = new ExpectedCondition<Boolean>() {
		public Boolean apply(WebDriver arg0) {
		  try {
		     webElement.isDisplayed();
		     return true;
		  }
		  catch (NoSuchElementException e ) {
		    return false;
		  }
		  catch (StaleElementReferenceException f) {
		    return false;
		  }
		    }
		};
		wait.until(elementIsDisplayed);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
}
