package com.innroad.inncenter.pages;

public class NewTax {
	public static final String TaxPagesSize = ".TextDefault td a";
	public static final String FindTaxName = ".dgText tr td a";
	public static final String TaxTotalPages = ".TextDefault td span";
	public static final String DeleteTax = "//*[@id='MainContent_btnDelete']";
	public static final String GoButton = "//input[@id='MainContent_btnGoSearch']";
	public static final String TaxReqMessage="//*[@id='MainContent_dispSumm']";
	public static final String ReqTaxItemName="//*[@id='MainContent_reqTaxItemName']";
	public static final String ReqTaxItemDisplayName="//*[@id='MainContent_reqTaxItemDisplayName']";
	public static final String ReqTaxItemDesc="//*[@id='MainContent_reqTaxItemDescription']";
	public static final String ReqTaxItemValue="//*[@id='MainContent_reqTaxItemValue']";
	public static final String InvalidValueMessage="//*[@id='MainContent_dispSumm']/ul/li[contains(text(),'Invalid Value.')]";
	
	public static final String TaxListSize = "//div[@id='MainContent_TaxItems_DIV']//tr";
	public static final String firstTaxItem = "(//div[@id='MainContent_TaxItems_DIV']//tr//td//a)[1]";
	public static final String selectLedgerAccount = "lstTaxes";	
	
	
	
}
