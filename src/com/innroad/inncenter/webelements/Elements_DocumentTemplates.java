package com.innroad.inncenter.webelements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.innroad.inncenter.interfaces.IDocumentTemplates;
import com.innroad.inncenter.properties.OR;
import com.innroad.inncenter.properties.OR_Setup;

public class Elements_DocumentTemplates implements IDocumentTemplates {
	
	WebDriver driver ;


	public static Logger app_logs = Logger.getLogger("devpinoyLogger");
		
		public Elements_DocumentTemplates(WebDriver driver2)
		{
			this.driver=driver2;
			PageFactory.initElements(this.driver, this);
			
			 
		}
		
		
		@FindBy(xpath=OR.Doc_NewButton)
		public WebElement Doc_NewButton;
		
		@FindBy (xpath = OR.DocTempPage)
		public WebElement DocTempPage;
		
		@FindBy(xpath=OR.DocumentName)
		public WebElement DocumentName;
		
		@FindBy(xpath=OR.DocumentDescription)
		public WebElement DocumentDescription;
		
		@FindBy(xpath=OR.AssociateSource)
		public WebElement AssociateSource;
		
		@FindBy(xpath=OR.AssociateProperty)
		public WebElement AssociateProperty;
		
		@FindBy(xpath=OR.AssociateFunction)
		public WebElement AssociateFunction;
		
		@FindBy(xpath=OR.Document_Save)
		public WebElement Document_Save;
		
		@FindBy(xpath=OR.Document_Done)
		public WebElement Document_Done;


		@FindBy (xpath = OR.AssociateSourcePopup)
		public WebElement AssociateSourcePopup;
		
		@FindBy (xpath = OR.AssociatePropertyPopup)
		public WebElement AssociatePropertyPopup;
		
		@FindBy (xpath = OR.AssociateFunctionPopup)
		public WebElement AssociateFunctionPopup;
		
		@FindBy(xpath=OR.Select_AssociateFunction)
		public WebElement Select_AssociateFunction;
		
		
		
		@FindBy(xpath=OR.Property_PickOne)
		public WebElement Property_PickOne;
		
		
		@FindBy(xpath=OR.Property_PickAll)
		public WebElement Property_PickAll;
		
		
		
		@FindBy(xpath=OR.Function_Email_CheckBox)
		public WebElement Function_Email_CheckBox;
		
		@FindBy(xpath=OR.Source_Inncenter_CheckBox)
		public WebElement Source_Inncenter_CheckBox;
		
		@FindBy(xpath=OR.SavePopup)
		public WebElement SavePopup;
		
		@FindBy(xpath = OR.Selected_AssociateFunction)
		public WebElement Selected_AssociateFunction;
		
		
		@FindBy(xpath = OR.Function_Trigger)
		 public WebElement Function_Trigger;
		
		@FindBy(xpath=OR.Function_Event)
		public WebElement Function_Event;
		
		@FindBy(xpath=OR.DocumentName_list)
		public WebElement DocumentName_list;
		
		@FindBy(id=OR.DefaultTempCheckBox)
		public WebElement DefaultTempCheckBox;
		
		@FindBy(id=OR.SelectSystemDoc)
		public WebElement SelectSystemDoc;
		
		@FindBy(xpath=OR.Function_ScheduleDays)
        public WebElement Function_ScheduleDays;
		
		@FindBy(xpath=OR.DeleteDocument_CheckBox)
		public WebElement DeleteDocument_CheckBox;
		
		@FindBy(xpath=OR.Document_DeleteButton)
		public WebElement Document_DeleteButton;
		
		@FindBy(xpath=OR.CreatedDocument_Pages)
		public WebElement CreatedDocument_Pages;
		
		@FindBy(xpath=OR.LastPage)
		public WebElement LastPage;
		
		@FindBy(xpath=OR.Content)
		public WebElement Content;
		
		@FindBy(xpath=OR_Setup.FUNCTION_ADD_ATTACHMENTS)
		public WebElement FUNCTION_ADD_ATTACHMENTS;
		
		@FindBy(xpath=OR_Setup.FUNCTION_AVAILABLE_ATTACHEMENTS_LIST)
		public WebElement FUNCTION_AVAILABLE_ATTACHEMENTS_LIST;
		
		@FindBy(xpath=OR_Setup.FUNCTION_ADDED_ATTACHEMENTS_LIST)
		public WebElement FUNCTION_ADDED_ATTACHEMENTS_LIST;
		
		@FindBy(xpath=OR_Setup.PICK_ONE_BUTTON)
		public WebElement PICK_ONE_BUTTON;
		
		@FindBy(xpath=OR_Setup.DONE_BUTTON)
		public WebElement DONE_BUTTON;
		
		@FindBy(xpath=OR.functionConfirmationLetterCheckBox)
		public WebElement functionConfirmationLetterCheckBox;
		
		
		
		
		
		
		
}
