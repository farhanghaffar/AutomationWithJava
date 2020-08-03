package com.innroad.inncenter.interfaces;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public interface IReservationFolio {
	
	void paymentMethod(WebDriver driver, String PaymentType, String CardName, String CCNumber, String CCExpiry, String CCVCode, String Authorizationtype, String ChangeAmount, String ChangeAmountValue, String traceData,ArrayList test_steps) throws InterruptedException;
    void Paytype_CP_Account(WebDriver driver, String ChangeAmount, String ChangeAmountValue)throws InterruptedException;
}
