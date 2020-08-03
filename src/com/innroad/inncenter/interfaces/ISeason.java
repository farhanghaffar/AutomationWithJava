package com.innroad.inncenter.interfaces;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public interface ISeason {

	void NewSeasonButtonClick(WebDriver driver) throws InterruptedException;

	void SeasonDeatils(WebDriver driver, String SeasonName, String Status) throws InterruptedException;

	ArrayList<String> SeasonAttributes(WebDriver driver) throws InterruptedException;

	void SaveSeason(WebDriver driver) throws InterruptedException;

}
