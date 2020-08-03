package com.innroad.inncenter.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class XmlUtility {

	public static boolean generateXmlSuiteForFailedSkippedTests(String xmlSuiteName, List<String> failedSkippedTestsList) {
		
		if( xmlSuiteName != null && xmlSuiteName != "") {
			
			if(failedSkippedTestsList.size() > 0) {
				
				// Creating suite object
				XmlSuite suite = new XmlSuite();
				suite.setName("FailedSkippedScriptsSuite");
				
				// Setting parallel mode and thread-count
				suite.setParallel(XmlSuite.ParallelMode.CLASSES);
				suite.setThreadCount(2);
				
				// Adding listeners
				suite.addListener("com.innroad.inncenter.listeners.EnvReaderListener");
				suite.addListener("com.innroad.inncenter.utils.RetryListenerClass");
				suite.addListener("com.innroad.inncenter.listeners.TestListener");
				
				// Adding parameters
				Map<String, String> params = new HashMap<String, String>();
				params.put("env", Utility.envURL);
				params.put("test_category", "NonGS");
				suite.setParameters(params);
				
				XmlTest test = new XmlTest();
				test.setName("FailedSkippedScriptsTest");
				
				List<XmlClass> testClasses = new ArrayList<XmlClass>();
				
				for(String script : failedSkippedTestsList) {			
					testClasses.add(new XmlClass(script));
				}
				
				test.setXmlClasses(testClasses);
				
				suite.addTest(test);
				suite.setFileName(xmlSuiteName); 
				
				FileWriter writer = null;
				
				try { 
					writer = new FileWriter(new File(xmlSuiteName)); 
					writer.write(suite.toXml()); 
					writer.flush(); 
					// System.out.println(new File(xmlSuiteName).getAbsolutePath());
				} catch (IOException e){
					e.printStackTrace(); 
				}finally {
					try {
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					} 
				}
				
				return true;
				
			}else {
				System.out.println("Failed or Skipped testscript count is zero");
				return false;
			}
		}else {
			System.out.println("Please check the SuiteName arugment");
			return false;
		}
	}

}
