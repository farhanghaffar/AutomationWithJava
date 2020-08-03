package com.innroad.inncenter.listeners;

import java.util.Map;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import  com.innroad.inncenter.testcore.TestCore;

public class EnvReaderListener implements ISuiteListener{
	
	@Override
	public void onStart(ISuite suite) {
		
		String fileName = suite.getXmlSuite().getFileName();
		TestCore.currentSuiteFile = fileName.substring(fileName.lastIndexOf("\\")+1);
		System.out.println("Current Suite File Name: " + TestCore.currentSuiteFile );
		
        Map<String, String> parameters = suite.getXmlSuite().getParameters();
        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
            String env = System.getenv(parameter.getKey());
            if (env != null && ! env.trim().isEmpty()) {
                parameter.setValue(env);
            }
        }		
	}
	
	@Override
	public void onFinish(ISuite suite) {
	}
}
