package com.innroad.inncenter.testcore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.innroad.inncenter.pageobjects.Login;
import com.innroad.inncenter.utils.EmailUtils;
import com.innroad.inncenter.utils.Utility;
import com.innroad.inncenter.utils.XmlUtility;
import com.innroad.inncenter.utils.excel_reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TestCore {

	// Setting path variables
	// Testing
	public static ArrayList<String> ratePlanValueAtInitial = new ArrayList<>();
	public static String extentReportsPath = System.getProperty("user.dir") + "\\extent-reports";
	public static String testDataPath = System.getProperty("user.dir") + "\\test-data";
	public static String configFilesPath = System.getProperty("user.dir") + "\\config-files";
	public static String picFilePath = System.getProperty("user.dir") + "\\resources";

	// This is used to store the email attachments
	public static String mailAttachmentsPath = System.getProperty("user.dir") + File.separator + "externalFiles"
			+ File.separator + "downloadFiles" + File.separator;

	public static final ExtentReports extent = new ExtentReports(
			extentReportsPath + "\\CentralParkSanitySuite_Execution_Report_" + Utility.getTimeStamp() + ".html", false);

	public static ExtentTest test;

	public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>();

	// This captures all the failed and skipped test scripts
	public static List<String> failedSkippedTestsList = new ArrayList<String>();

	public static boolean isSuiteCreated = false;

	// This flag is used to generate the report for failed scripts execution
	public static String currentSuiteFile = "";

	public WebDriver getDriver() {

		return dr.get();
	}

	public void setWebDriver(RemoteWebDriver driver) {

		dr.set(driver);
	}

	public static Properties config = new Properties();
	public static Properties ratesConfig = new Properties();
	// public static WebDriver driver = null;
	public static excel_reader excel = null;
	public static Logger app_logs = Logger.getLogger("Testcore");

	// It is used to store jenkins build parameter
	public static String envURL;

	public static String targetBrowser;

	// public static monitoringMail mail = new monitoringMail();

	@BeforeSuite(alwaysRun = true)
	@Parameters("env")

	public void init(@Optional("https://app.qainnroad.com") String env) throws IOException {

		System.out.println("BeforeSuite :  " + Utility.BeforeSuite);
		if (Utility.BeforeSuite == 0) {

			// This statement will move old extent reports present in the
			// 'extent-reports' directory to 'extent-reports\archived' directory
			Utility.archiveExtentReports(extentReportsPath);

			// Checks and creates the directory where attached files will be
			// stored
			EmailUtils.setTargetDirectory(mailAttachmentsPath);

			extent.loadConfig(new File("extent-config.xml"));

		}
		Utility.BeforeSuite++;
		System.out.println("after if BeforeSuite :  " + Utility.BeforeSuite);
		envURL = env.replaceAll("\"", "");
		Utility.envURL = env;

		System.out.println("##################################################################################");
		System.out.println("EXECUTING Automation CENTRALPARK-SANITY SUITE ON: " + envURL + " - ENVIRONMENT.");
		System.out.println("##################################################################################");

		extent.addSystemInfo("Selenium-WebDriver", "3.141.59");

		// String envName = envURL.substring(envURL.indexOf('/') + 2,
		// envURL.indexOf("com") + 3).toUpperCase().trim();

		// Load the configuration file
		FileInputStream fis = new FileInputStream(configFilesPath + "\\config");
		config.load(fis);

		// Load the Rate V2 configuration file
		fis = new FileInputStream(configFilesPath + "\\ratesv2.properties");
		ratesConfig.load(fis);
		// config.putAll(ratesConfig);

		// Writing environment url to configuration file to use while re-running
		// failed test scripts
		if (!currentSuiteFile.equals("Sanity-Failed.xml")) {
			config.setProperty("url", envURL);
			config.store(new FileOutputStream(configFilesPath + "\\config"),
					"Updating execution environment URL: " + envURL);
		} else {
			// Reading url from configuration file to re-run the failed test
			// scripts
			envURL = config.getProperty("url");
			Utility.envURL = config.getProperty("url");
		}

		app_logs.info("Config property file loaded");

		Utility.ReportsProperty = config.getProperty("ReportsProperty").trim();
		Utility.ReportsProperty2 = config.getProperty("ReportsProperty2").trim();
		Utility.ReportsProperty3 = config.getProperty("ReportsProperty3").trim();
		Utility.UserName = config.getProperty("UserName").trim();
		Utility.WestPointInnProperty = config.getProperty("WestPointInnProperty").trim();

		String envName = null;

		try {

			envName = envURL.substring(envURL.indexOf('/') + 2, envURL.indexOf("com") + 3).toUpperCase().trim();

		} catch (Exception e) {

			envName = "DEV";

		}

		switch (envName) {
		case "DEV":
			extent.addSystemInfo("Environment", "DEV");
			break;
		case "APP.QAINNROAD.COM":
			extent.addSystemInfo("Environment", "APP-QA");
			break;
		case "APP.QA2INNROAD.COM":
			extent.addSystemInfo("Environment", "APP-QA2");
			break;
		case "APP.QA3INNROAD.COM":
			extent.addSystemInfo("Environment", "APP-QA3");
			break;
		case "APP.QA4INNROAD.COM":
			extent.addSystemInfo("Environment", "APP-QA4");
			break;
		case "APP.INNROAD.COM":
			extent.addSystemInfo("Environment", "PRODUCTION");
			break;
		default:
			extent.addSystemInfo("Environment", "INVAILD, Please check the url");
		}

		extent.addSystemInfo("Env-URL", envURL);
		PropertyConfigurator.configure("Log4j.properties");

		// Load the Excel file
		excel = new excel_reader(testDataPath + "\\CentralparkSanityTestData.xlsx");
		app_logs.info("Excel file loaded");
		Utility.getLogin(excel, "NonGS_Login");
		Utility.getLogin_GS(excel, "GS_Login");
		Utility.getLogin_CP(excel, "CP_Login");
		Utility.getLoginWPI(excel, "WPI_Login");
		Utility.getLineItemIconSource(excel, "LineItemIconSources");

	}

	@BeforeClass(alwaysRun = true)
	public void beforeClass() throws MalformedURLException {

		RemoteWebDriver driver = null;
		targetBrowser = config.getProperty("browser").toUpperCase();
		DesiredCapabilities dc = null;

		switch (targetBrowser) {

		case "FIREFOX":
			System.setProperty("webdriver.gecko.driver", ".\\Drivers\\geckodriver.exe");

			FirefoxOptions fo = new FirefoxOptions();
			fo.setLogLevel(FirefoxDriverLogLevel.INFO);

			// preference added for downloading pdf instead view in browser pdf
			// viewer

			fo.addPreference("browser.download.dir", System.getProperty("user.dir") + File.separator + "externalFiles"
					+ File.separator + "downloadFiles" + File.separator);
			fo.addPreference("browser.download.useDownloadDir", true);
			fo.addPreference("browser.download.folderList", 2);
			fo.addPreference("browser.download.manager.showWhenStarting", false);
			fo.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
			fo.addPreference("pdfjs.disabled", true);
			fo.addPreference("browser.link.open_newwindow", 1);
			fo.addPreference("marionette", false);

			// Use this to disable Acrobat plugin for previewing PDFs in Firefox
			// (if you have Adobe reader installed on your computer)
			// fo.addPreference("plugin.scan.Acrobat", "99.0");
			// fo.addPreference("plugin.scan.plid.all", false);
			// FirefoxProfile profile= new FirefoxProfile();
			// profile.setPreference( "layout.css.devPixelsPerPx", "0.9" );
			// fo.setProfile(profile);
			driver = new FirefoxDriver(fo);
			setWebDriver(driver);
			break;

		case "CHROME":
			// System.setProperty("webdriver.chrome.silentOutput", "true");

			if (currentSuiteFile.equals("tapechart.xml")) {
				System.setProperty("webdriver.chrome.driver", ".\\Drivers\\chromedriver2.36\\chromedriver.exe");

			} else {
				System.setProperty("webdriver.chrome.driver", ".\\Drivers\\chromedriver.exe");
			}
			// Setting new download directory path
			Map<String, Object> prefs = new HashMap<String, Object>();

			// Use File.separator as it will work on any OS
			prefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "externalFiles"
					+ File.separator + "downloadFiles");
			prefs.put("plugins.always_open_pdf_externally", true);
			prefs.put("download.extensions_to_open", "applications/pdf");

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
			chromeOptions.setExperimentalOption("prefs", prefs);
			// chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
			// chromeOptions.addArguments("--disable-gpu");
			// chromeOptions.addArguments("disable-infobars");

			chromeOptions.addArguments("--disable-popup-blocking");
			LoggingPreferences logs = new LoggingPreferences();
			// logs.enable(LogType.BROWSER, Level.ALL);
			// logs.enable(LogType.CLIENT, Level.ALL);
			// logs.enable(LogType.DRIVER, Level.ALL);
			// logs.enable(LogType.PERFORMANCE, Level.ALL);
			// logs.enable(LogType.PROFILER, Level.ALL);
			// logs.enable(LogType.SERVER, Level.ALL);
			dc = DesiredCapabilities.chrome();
			dc.setCapability(CapabilityType.LOGGING_PREFS, logs);
			dc.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
			// driver = new RemoteWebDriver(new
			// URL("http://10.250.251.37:4444/wd/hub"), dc);

			System.out.println(dc.getVersion());
			driver = new ChromeDriver(chromeOptions);
			setWebDriver(driver);
			break;

		case "IE":

			System.setProperty("webdriver.ie.driver", ".\\Drivers\\IEDriverServer.exe");

			InternetExplorerOptions ieOptions = new InternetExplorerOptions();

			ieOptions.setCapability("nativeEvents", false);
			ieOptions.setCapability("unexpectedAlertBehaviour", "accept");
			ieOptions.setCapability("ignoreProtectedModeSettings", true);
			ieOptions.setCapability("disable-popup-blocking", true);
			ieOptions.setCapability("enablePersistentHover", true);
			ieOptions.setCapability("ignoreZoomSetting", true);

			driver = new InternetExplorerDriver(ieOptions);

			setWebDriver(driver);
			break;

		case "OPERA":
			// In Progress
			System.setProperty("webdriver.opera.driver", ".\\Drivers\\operadriver.exe");
			OperaOptions operaOptions = new OperaOptions();
			driver = new OperaDriver(operaOptions);
			setWebDriver(driver);
			break;

		}

		app_logs.info(targetBrowser + " driver is initialized");
		getDriver().manage().window().maximize();
		app_logs.info("Browser window is maximized");

		// Setting default web driver timeout to 30 SECONDS
		getDriver().manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);

	}

	public void login_GS(WebDriver driver) throws InterruptedException {
		// Login to InnCenter for GS

		Login login = new Login();
		login.login(driver, envURL, Utility.login_GS.get(1), Utility.login_GS.get(2), Utility.login_GS.get(3));

	}

	public static void login_NONGS(WebDriver driver) throws InterruptedException, IOException {
		// Login to InnCenter for NON GS
		Login login = new Login();
		login.login(driver, envURL, Utility.login.get(1), Utility.login.get(2), Utility.login.get(3));
	}

	public static void login_CP(WebDriver driver) throws InterruptedException, IOException {
		// Login to InnCenter for NON GS
		Login login = new Login();
		login.login(driver, envURL, Utility.login_CP.get(1), Utility.login_CP.get(2), Utility.login_CP.get(3));
	}

	public static void login_CP(WebDriver driver, ArrayList<String> test_steps)
			throws InterruptedException, IOException {
		login_CP(driver);
		test_steps.add("Logged into inncenter");
	}

	@AfterSuite(alwaysRun = true)
	public void closeDriverInstance() {

		// sending mail
		// testUtil.zip(System.getProperty("user.dir")+"\\screenshot");
		// mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to,"Test
		// Report", "Please find the attached report",TestConfig.reportPath
		// "Reports.zip");

		app_logs.info("before end suite BeforeSuite :  " + Utility.BeforeSuite);

		if (Utility.BeforeSuite == 1) {

			extent.addSystemInfo("Client", Utility.login.get(1));
			extent.addSystemInfo("Target-Browser", targetBrowser);
			extent.flush();
			Utility.BeforeSuite = 0;
		}
		/*
		 * // Below code is not required, it is used for debugging purpose
		 * 
		 * try { EmailUtils.sendEmailWithAttachment(reportFile); } catch
		 * (Exception e1) { e1.printStackTrace(); }
		 */
		// This will generate the xml suite for failed and skipped tests
		isSuiteCreated = XmlUtility.generateXmlSuiteForFailedSkippedTests("CentralParkSanity-Failed.xml",
				failedSkippedTestsList);

		// Rerun the failed and skipped tests again
		try {

			if (isSuiteCreated) {
				app_logs.info("Executing batch file");
				String command = "cmd.exe /C start " + System.getProperty("user.dir") + File.separator
						+ "CentralParkSanity-Failed.bat";
				Runtime.getRuntime().exec(command);
			} else {
				app_logs.info("Suite file is not created hence skipping the batch file execution for failed tests");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		// This block of code will run the failed / skipped tests
		try {

			if (currentSuiteFile.equals("CentralParkSanity-Failed.xml")) {

				extent.addSystemInfo("Client", Utility.login.get(1));
				extent.addSystemInfo("Target-Browser", targetBrowser);
				extent.flush();
			}

			// EmailUtils.sendEmailWithAttachment(reportFile);

		} catch (Exception e1) {

			e1.printStackTrace();

		}

		app_logs.info("after end suite BeforeSuite :  " + Utility.BeforeSuite);
	}

	public static void login_CP(WebDriver driver, String user) throws InterruptedException, IOException {
		// Login to InnCenter for NON GS
		Login login = new Login();
		login.login(driver, envURL, Utility.login_CP.get(1), user, Utility.login_CP.get(3));
	}

	public static void logout(WebDriver driver) throws InterruptedException, IOException {
		// Login to InnCenter for NON GS
		Login login = new Login();
		login.logout(driver);
		;
	}

	public static void loginWPI(WebDriver driver) throws InterruptedException, IOException {
		// Login to InnCenter for NON GS
		Login login = new Login();
		login.login(driver, envURL, Utility.loginWPI.get(1), Utility.loginWPI.get(2), Utility.loginWPI.get(3));
	}

	public static int counter = 0;

	public static void setBaseRateValues(WebDriver driver, ArrayList<String> rateList) {
		for (int i = 0; i < rateList.size(); i++) {
			ratePlanValueAtInitial.add(rateList.get(i));
		}
		counter++;
		System.out.println("Counter:" + counter);
		System.out.println("ratePlanValueAtInitial:" + ratePlanValueAtInitial);

	}

	public static ArrayList<String> getBaseRateValues(WebDriver driver) {

		System.out.println("Counter:" + counter);
		System.out.println("ratePlanValueAtInitial:" + ratePlanValueAtInitial);
		return ratePlanValueAtInitial;

	}

}
