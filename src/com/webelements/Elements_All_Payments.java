package com.innroad.inncenter.webelements;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.innroad.inncenter.pages.NewFolio;
import com.innroad.inncenter.properties.OR;

public class Elements_All_Payments {

	WebDriver driver;

	public static Logger app_logs = Logger.getLogger("devpinoyLogger");

	public Elements_All_Payments(WebDriver driver2) {
		this.driver = driver2;
		PageFactory.initElements(this.driver, this);

	}

	@FindBy(xpath = OR.selectPaymentFolio)
	public WebElement selectPaymentFolio;

	@FindBy(xpath = OR.PaymentInfoButton)
	public WebElement PaymentInfoButton;

	@FindBy(xpath = OR.PaymentPopUp)
	public WebElement PaymentPopUp;

	@FindBy(xpath = OR.Reservation_PaymentPopup)
	public WebElement Reservation_PaymentPopup;

	@FindBy(xpath = OR.Reservation_PaymentPopupHeading)
	public WebElement Reservation_PaymentPopupHeading;

	@FindBy(xpath = OR.Account_PaymentPopup)
	public WebElement Account_PaymentPopup;

	@FindBy(xpath = OR.Account_PaymentPopupHeading)
	public WebElement Account_PaymentPopupHeading;

	@FindBy(xpath = NewFolio.Reservation_CardInfoPopup)
	public WebElement Reservation_CardInfoPopup;

	@FindBy(xpath = NewFolio.Account_CardInfoPopup)
	public WebElement Account_CardInfoPopup;

	@FindBy(xpath = OR.PaymentPopUp1)
	public WebElement PaymentPopUp1;

	@FindBy(xpath = OR.TakePaymentPopUp)
	public WebElement PaymeTakePaymentPopUpntPopUp1;

	@FindBy(xpath = OR.PaymentPopup_Close)
	public WebElement PaymentPopup_Close;

	@FindBy(xpath = OR.PaymentInfoButton)
	public List<WebElement> PaymentInfoButtons;

	@FindBy(css = OR.PaymentInfo_Input_Fields)
	public List<WebElement> PaymentInfo_Input_Fields;

	@FindBy(xpath = OR.OkButton)
	public List<WebElement> OkButton;

	@FindBy(xpath = OR.PaymentType)
	public WebElement PaymentType;

	@FindBy(xpath = OR.Enter_Reservation)
	public WebElement Enter_Reservation;

	@FindBy(xpath = OR.PaymentDetail_PaymentType)
	public WebElement PaymentDetail_PaymentType;

	@FindBy(xpath = OR.PaymentDetail_Continue_Pay_Button)
	public WebElement PaymentDetail_Continue_Pay_Button;

	@FindBy(xpath = OR.Folio)
	public List<WebElement> Folio;

	@FindBy(xpath = OR.PaymentDetail_Enter_Amount)
	public WebElement PaymentDetail_Enter_Amount;

	@FindBy(xpath = OR.PaymentDetail_Add_Pay_Button)
	public WebElement PaymentDetail_Add_Pay_Button;

	@FindBy(xpath = OR.Enter_Amount)
	public WebElement Enter_Amount;

	@FindBy(xpath = OR.Enter_Amount_Auth)
	public WebElement Enter_Amount_Auth;

	@FindBy(xpath = OR.Add_Pay_Button)
	public WebElement Add_Pay_Button;

	@FindBy(xpath = OR.Close_ReservationPaymentPopup)
	public WebElement Close_ReservationPaymentPopup;

	@FindBy(xpath = OR.Cancel_ReservationPaymentPopup)
	public WebElement Cancel_ReservationPaymentPopup;

	@FindBy(xpath = OR.ZeroPayment_AlertPopup)
	public WebElement ZeroPayment_AlertPopup;

	@FindBy(xpath = OR.ZeroPayment_AlertPopupOk)
	public WebElement ZeroPayment_AlertPopupOk;

	@FindBy(xpath = OR.Continue_Pay_Button)
	public WebElement Continue_Pay_Button;

	@FindBy(xpath = OR.Enter_CardName)
	public WebElement Enter_CardName;

	@FindBy(xpath = OR.Enter_CardNum)
	public WebElement Enter_CardNum;

	@FindBy(xpath = OR.Enter_ExpDate)
	public WebElement Enter_ExpDate;

	@FindBy(xpath = OR.Enter_Card_CVVCode)
	public WebElement Enter_Card_CVVCode;

	@FindBy(xpath = OR.Enter_Address)
	public WebElement Enter_Address;

	@FindBy(xpath = OR.Enter_Card_City)
	public WebElement Enter_Card_City;

	@FindBy(xpath = OR.Enter_Card_State)
	public WebElement Enter_Card_State;

	@FindBy(xpath = OR.Enter_PostalCode)
	public WebElement Enter_PostalCode;

	@FindBy(xpath = OR.Enter_ApprovalCode)
	public WebElement Enter_ApprovalCode;

	@FindBy(xpath = OR.CardOK_Button)
	public WebElement CardOK_Button;

	@FindBy(xpath = NewFolio.CardInfoPopup_CardName)
	public WebElement CardInfoPopup_CardName;

	@FindBy(xpath = NewFolio.CardInfoPopup_CardNum)
	public WebElement CardInfoPopup_CardNum;

	@FindBy(xpath = NewFolio.CardInfoPopup_ExpDate)
	public WebElement CardInfoPopup_ExpDate;

	@FindBy(xpath = NewFolio.CardInfoPopup_Card_CVVCode)
	public WebElement CardInfoPopup_Card_CVVCode;

	@FindBy(xpath = NewFolio.CardInfoPopup_Address)
	public WebElement CardInfoPopup_Address;

	@FindBy(xpath = NewFolio.CardInfoPopup_Card_City)
	public WebElement CardInfoPopup_Card_City;

	@FindBy(xpath = NewFolio.CardInfoPopup_Card_State)
	public WebElement CardInfoPopup_Card_State;

	@FindBy(xpath = NewFolio.CardInfoPopup_PostalCode)
	public WebElement CardInfoPopup_PostalCode;

	@FindBy(xpath = NewFolio.CardInfoPopup_ApprovalCode)
	public WebElement CardInfoPopup_ApprovalCode;

	@FindBy(xpath = NewFolio.CardInfoPopup_OKButton)
	public WebElement CardInfoPopup_OKButton;

	@FindBy(xpath = NewFolio.Account_CardInfoPopup_CardName)
	public WebElement Account_CardInfoPopup_CardName;

	@FindBy(xpath = NewFolio.Account_CardInfoPopup_CardNum)
	public WebElement Account_CardInfoPopup_CardNum;

	@FindBy(xpath = NewFolio.Account_CardInfoPopup_ExpDate)
	public WebElement Account_CardInfoPopup_ExpDate;

	@FindBy(xpath = NewFolio.Account_CardInfoPopup_Card_CVVCode)
	public WebElement Account_CardInfoPopup_Card_CVVCode;

	@FindBy(xpath = NewFolio.Account_CardInfoPopup_Address)
	public WebElement Account_CardInfoPopup_Address;

	@FindBy(xpath = NewFolio.Account_CardInfoPopup_Card_City)
	public WebElement Account_CardInfoPopup_Card_City;

	@FindBy(xpath = NewFolio.Account_CardInfoPopup_Card_State)
	public WebElement Account_CardInfoPopup_Card_State;

	@FindBy(xpath = NewFolio.Account_CardInfoPopup_PostalCode)
	public WebElement Account_CardInfoPopup_PostalCode;

	@FindBy(xpath = NewFolio.Account_CardInfoPopup_ApprovalCode)
	public WebElement Account_CardInfoPopup_ApprovalCode;

	@FindBy(xpath = NewFolio.Account_CardInfoPopup_OKButton)
	public WebElement Account_CardInfoPopup_OKButton;

	@FindBy(xpath = OR.Payment_Info_Button)
	public WebElement Payment_Info_Button;

	@FindBy(xpath = OR.PaymentCardInfoButton)
	public WebElement PaymentCardInfoButton;

	@FindBy(xpath = OR.Process_Button)
	public WebElement Process_Button;

	@FindBy(xpath = OR.Process_Button_Auth)
	public WebElement Process_Button_Auth;

	@FindBy(xpath = OR.PaymentDetail_Process_Button)
	public WebElement PaymentDetail_Process_Button;

	@FindBy(xpath = OR.CardPayment_CardInformatoinAlertMessage)
	public WebElement CardPayment_CardInformatoinAlertMessage;

	@FindBy(xpath = OR.PaymentSwipe_Img)
	public WebElement PaymentSwipe_Img;

	@FindBy(xpath = OR.SwipeCard_Field)
	public WebElement SwipeCard_Field;

	@FindBy(xpath = OR.SwipePaymentpopup)
	public WebElement SwipePaymentpopup;

	@FindBy(xpath = OR.SwipeCard_Loader)
	public WebElement SwipeCard_Loader;

	@FindBy(xpath = OR.SubmitButton)
	public WebElement SubmitButton;

	@FindBy(xpath = OR.CardSubmitButton)
	public WebElement CardSubmitButton;

	@FindBy(xpath = OR.Select_Account)
	public List<WebElement> Select_Account;

	@FindBy(xpath = OR.Search_Account_Button)
	public WebElement Search_Account_Button;

	@FindBy(xpath = OR.Select_Account_Button)
	public WebElement Select_Account_Button;

	@FindBy(xpath = OR.HouseAccoutn_PaymentType)
	public WebElement HouseAccoutn_PaymentType;

	@FindBy(xpath = OR.HouseAccoutn_PaymentType_1)
	public WebElement HouseAccoutn_PaymentType_1;

	@FindBy(xpath = OR.HouseAccoutn_Enter_Amount)
	public WebElement HouseAccoutn_Enter_Amount;

	@FindBy(xpath = OR.HouseAccoutn_Payment_Info_Button)
	public WebElement HouseAccoutn_Payment_Info_Button;

	@FindBy(xpath = OR.HouseAccoutn_Process_Button)
	public WebElement HouseAccoutn_Process_Button;

	@FindBy(xpath = OR.HouseAccoutn_Enter_CardName)
	public WebElement HouseAccoutn_Enter_CardName;

	@FindBy(xpath = OR.HouseAccoutn_Enter_CardNum)
	public WebElement HouseAccoutn_Enter_CardNum;

	@FindBy(xpath = OR.HouseAccoutn_Enter_ExpDate)
	public WebElement HouseAccoutn_Enter_ExpDate;

	@FindBy(xpath = OR.HouseAccoutn_Enter_Card_CVVCode)
	public WebElement HouseAccoutn_Enter_Card_CVVCode;

	@FindBy(xpath = OR.HouseAccoutn_Enter_Address)
	public WebElement HouseAccoutn_Enter_Address;

	@FindBy(xpath = OR.HouseAccoutn_Enter_Card_City)
	public WebElement HouseAccoutn_Enter_Card_City;

	@FindBy(xpath = OR.HouseAccoutn_Enter_Card_State)
	public WebElement HouseAccoutn_Enter_Card_State;

	@FindBy(xpath = OR.HouseAccoutn_Enter_PostalCode)
	public WebElement HouseAccoutn_Enter_PostalCode;

	@FindBy(xpath = OR.HouseAccoutn_Enter_ApprovalCode)
	public WebElement HouseAccoutn_Enter_ApprovalCode;

	@FindBy(xpath = OR.HouseAccoutn_CardOK_Button)
	public WebElement HouseAccoutn_CardOK_Button;

	@FindBy(xpath = OR.HouseAccoutn_Continue_Pay_Button)
	public WebElement HouseAccoutn_Continue_Pay_Button;

	@FindBy(css = OR.ProcessBtn)
	public List<WebElement> ProcessBtn;

	@FindBy(xpath = OR.Select_Authorization_type)
	public WebElement Select_Authorization_type;

	@FindBy(xpath = OR.Select_Paymnet_Method)
	public WebElement Select_Paymnet_Method;

	@FindBy(xpath = OR.Click_Continue)
	public WebElement Click_Continue;

	@FindBy(xpath = NewFolio.CardInfoPopup_CancelButton)
	public WebElement CardInfoPopup_CancelButton;

	@FindBy(xpath = NewFolio.CardInfoPopup_CardNameValidation)
	public WebElement CardInfoPopup_CardNameValidation;

	@FindBy(xpath = NewFolio.CardInfoPopup_CardNumberValidation)
	public WebElement CardInfoPopup_CardNumberValidation;

	@FindBy(xpath = NewFolio.CardInfoPopup_ExpDateValidation)
	public WebElement CardInfoPopup_ExpDateValidation;

	@FindBy(xpath = OR.selectPaymentFolio3)
	public WebElement selectPaymentFolio3;

	@FindBy(xpath = OR.MoveFolio_GuestFolio)
	public WebElement MoveFolio_GuestFolio;

	@FindBy(xpath = OR.AuthorizationImg)
	public WebElement AuthorizationImg;

	@FindBy(xpath = OR.PayButton)
	public WebElement PayButton;

	@FindBy(xpath = NewFolio.PaymentDetailsPopupHeading)
	public WebElement PaymentDetailsPopupHeading;
	
	@FindBy(name = NewFolio.SelectAuthorizationType)
	public WebElement SelectAuthorizationType;
	
	@FindBy(xpath = NewFolio.ProcessButton)
	public WebElement ProcessButton;
	
	@FindBy(xpath = OR.paymentAmountInput)
	public WebElement paymentAmountInput;
	
	@FindBy(xpath = OR.paymentType)
	public WebElement paymentType;

	@FindBy(xpath = OR.paymentSubmitButton)
	public WebElement paymentSubmitButton;

	@FindBy(xpath = OR.paymentSuccessFullPopupClose)
	public WebElement paymentSuccessFullPopupClose;

}
