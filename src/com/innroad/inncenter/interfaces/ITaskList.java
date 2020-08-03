package com.innroad.inncenter.interfaces;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public interface ITaskList {
	
	void ClickNewButton(WebDriver driver) throws InterruptedException;

	void VerifyTaskList(WebDriver driver,ArrayList<String> test_steps) throws InterruptedException;
	void VerifyNewTaskListItem(WebDriver driver) throws InterruptedException;
	void printAndDownloadPDF(WebDriver driver, ArrayList<String> test_steps) throws InterruptedException;
}
