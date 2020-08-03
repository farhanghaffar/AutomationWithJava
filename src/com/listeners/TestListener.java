package com.innroad.inncenter.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.innroad.inncenter.testcore.TestCore;

public class TestListener implements ITestListener {

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		if(!(TestCore.failedSkippedTestsList.contains(arg0.getTestClass().getName()))) {			
			TestCore.failedSkippedTestsList.add(arg0.getTestClass().getName());
		}
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		if(!(TestCore.failedSkippedTestsList.contains(arg0.getTestClass().getName()))) {			
			TestCore.failedSkippedTestsList.add(arg0.getTestClass().getName());
		}

	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

}
