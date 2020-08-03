package com.innroad.inncenter.pageobjects;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;

public class Sample {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String curentday="06/08/2020";
		String add1="Your City, NY 11968";
		
		String FileName="D:/currentSprintWorkingBranches/AUTOMATION-1537/inncenter.centralpark.sanitysuite/externalFiles/downloadFiles/Property_4632_Reservation_16261269_Event_GuestStatementCP_Pdf.pdf";
		String pdfFileInText=null;
		PDDocument document = PDDocument.load(new File(FileName));
		document.getClass();
		boolean flag = true;
		if (!document.isEncrypted()) {
			PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			stripper.setSortByPosition(true);
			PDFTextStripper tStripper = new PDFTextStripper();
			pdfFileInText = tStripper.getText(document);
			String lines[] = pdfFileInText.split("\\n");
			flag = true;
			String testflag = "test";
			for (String line : lines) {
			System.out.println(line);
			
			line=line.trim();
			if(line.equalsIgnoreCase(add1)){
				System.out.println("City state and postal code of hotel address");
			}
			
			if(line.contains(curentday)&&line.contains("221")){
				if(line.contains("Room Charge")){
					System.out.println("Categoty verifed for 1 st day");
					
				}
				if(line.contains("NightlyRate")){
					System.out.println("desc verifed for 1 st day");
				}
				
				
				if(line.contains("Double Bed Room")){
					System.out.println("room class verifed for 1 st day");
				}
				
			}
			
			}
		}
		

	}

}
