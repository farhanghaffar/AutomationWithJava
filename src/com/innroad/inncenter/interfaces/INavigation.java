package com.innroad.inncenter.interfaces;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;



public interface INavigation {
	
	
	
	void navTapeChart(WebDriver driver,ExtentTest test) throws InterruptedException;
	
	void NewQuote(WebDriver driver);
	
	void GuestHistory(WebDriver driver);
	
	void Groups(WebDriver driver);
	
	void Accounts(WebDriver driver)throws InterruptedException;
	
	void Statements(WebDriver driver)throws InterruptedException;
	
	void UnitownerAccount(WebDriver driver);
	
	void TravelAgent(WebDriver driver);
	
	void ManagementTransfers(WebDriver driver);
	
	void AccountDistribution(WebDriver driver);
	
	void Guestservices(WebDriver driver);
	
	void HouseKeepingStatus(WebDriver driver);
	
	void TaskList(WebDriver driver);
	
	void Inventory(WebDriver driver) throws InterruptedException;
	
	void RoomMaintenance(WebDriver driver);
	
	void Overview(WebDriver driver);
	
	void Seasons(WebDriver driver);
	
	void Rates(WebDriver driver);
	
	void Rules(WebDriver driver);
	
	void Distribution(WebDriver driver);
	
	void Distribution_syndication(WebDriver driver);
	
	void DistributionBlackouts(WebDriver driver);
	
	void policies(WebDriver driver);
	
	void Setup(WebDriver driver) throws Exception;
	
	void Properties(WebDriver driver);
	
	void Taxes(WebDriver driver);
	
	void LedgerAccounts(WebDriver driver);
	
	void Merchantservices(WebDriver driver);
	
	void DocumentTemplate(WebDriver driver);

	void ListManagemnet(WebDriver driver);
	
	void Admin(WebDriver driver);
	
	void Clientinfo(WebDriver driver);
	
	void Users(WebDriver driver);
	
	void Roles(WebDriver driver);
	
	void NightAudit(WebDriver driver);
	
	void Reports(WebDriver driver);
	
	void RoomClass(WebDriver driver);
	
	void AccountBalances(WebDriver driver);
	
	void LedgerBalances(WebDriver driver);
	
	
	void MerchantTrans(WebDriver driver);
	
	void DailyFalsh(WebDriver driver);
	
	void RoomForecast(WebDriver driver);
	
	void NetSales(WebDriver driver);
	
	void AdvanceDeposite(WebDriver driver);
	
	
	
	
	
   
}
