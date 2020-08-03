//Interface for Login

package com.innroad.inncenter.interfaces;

import org.openqa.selenium.WebDriver;

public interface ILogin {	

	void login(WebDriver driver,String URL, String Clientcode, String UserID, String Password) throws InterruptedException;
	
	void BE_login(WebDriver driver,String URL) throws InterruptedException;
	
}
