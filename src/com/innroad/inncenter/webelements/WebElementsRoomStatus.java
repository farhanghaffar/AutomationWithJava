package com.innroad.inncenter.webelements;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.pages.RoomStatusPage;

public class WebElementsRoomStatus {

	WebDriver driver;

	public WebElementsRoomStatus(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(xpath = RoomStatusPage.InputSearch)
	public WebElement InputSearch;
	
	@FindBy(xpath = RoomStatusPage.Loading)
	public WebElement Loading;
	
	@FindBy(id = RoomStatusPage.ButtonSearch)
	public WebElement ButtonSearch;
	
	@FindBy(xpath = RoomStatusPage.GetCategoryImage)
	public List<WebElement> GetCategoryImage;
	
	@FindBy(xpath = RoomStatusPage.GetCategoryName)
	public List<WebElement> GetCategoryName;
	
	@FindBy(xpath = RoomStatusPage.GetTaskDueOnAssignedAndStatus)
	public List<WebElement> GetTaskDueOnAssignedAndStatus;
	
	@FindBy(xpath = RoomStatusPage.ButtonClosPopup)
	public WebElement ButtonClosPopup;
	

	@FindBy(xpath = RoomStatusPage.RoomStatusInSecondWindow)
	public List<WebElement> RoomStatusInSecondWindow;
	
	@FindBy(xpath = RoomStatusPage.RoomStatus)
	public WebElement RoomStatus;
	
	@FindBy(xpath = RoomStatusPage.RoomStatusFor)
	public WebElement RoomStatusFor;
	
	@FindBy(xpath = RoomStatusPage.GetTaskImage)
	public List<WebElement> GetTaskImage;
	
	@FindBy(xpath = RoomStatusPage.GetTaskName)
	public List<WebElement> GetTaskName;
	
	
	
	
	
	
	
	

}
