package com.innroad.inncenter.webelements;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.properties.OR;

public class Elements_Distribution {
	

	WebDriver driver ;
		
		public Elements_Distribution(WebDriver driver)
		{
			this.driver=driver;
			PageFactory.initElements(this.driver, this);
			
		}

		@FindBy(id=OR.DistributionLink)
		public WebElement DistributionLink;
		
		@FindBy(xpath=OR.Blackouts)
		public WebElement BlackoutsLink;
		
		@FindBy(xpath=OR.BlackOutRoomCheckBox)
		public WebElement BlackOutRoomCheckBox;
		

		@FindBy(css=OR.BlackOutRoom_Save_Button1)
		public List<WebElement> BlackOutRoom_Save_Button1;
		
		@FindBy(xpath=OR.BlackOutRoom_Save_Button)
		public WebElement BlackOutRoom_Save_Button;
		
		@FindBy(id=OR.Select_Client)
		public WebElement Select_Client;
		
		@FindBy(xpath=OR.SyndicateLink)
		public WebElement SyndicateLink;
		
		@FindBy(xpath=OR.SyndicateRoomCheckBox)
		public WebElement SyndicateRoomCheckBox;
		
		@FindBy(xpath=OR.SyndicateRoomSave)
		public WebElement SyndicateRoomSave;

		@FindBy(xpath=OR.Syndicate_ModuleLoading)
		public WebElement Syndicate_ModuleLoading;
		
		@FindBy(xpath=OR.SelectSource)
		public WebElement SelectSource;
		
		@FindBy(className=OR.Toaster_Message)
		public WebElement Toaster_Message;
		
		@FindBy(xpath=OR.LoginModuleLoding)
		public WebElement LoginModuleLoding;
		
		@FindBy(xpath=OR.DISTRIBUTIONCHANNEL_DISTRIBUTE)
		public List<WebElement> DISTRIBUTIONCHANNEL_DISTRIBUTE;
		
		@FindBy(xpath=OR.DISTRIBUTIONCHANNEL_DISTRIBUTE_SOURCENAME)
		public List<WebElement> DISTRIBUTIONCHANNEL_DISTRIBUTE_SOURCENAME;
		
		@FindBy(xpath=OR.DISTRIBUTIONCHANNEL_DISTRIBUTE_SOURCEURL)
		public List<WebElement> DISTRIBUTIONCHANNEL_DISTRIBUTE_SOURCEURL;
		
		@FindBy(xpath=OR.DISTRIBUTIONCHANNEL_DISTRIBUTE_SOURCEDEFAULTSTATUS)
		public List<WebElement> DISTRIBUTIONCHANNEL_DISTRIBUTE_SOURCEDEFAULTSTATUS;

		
}
