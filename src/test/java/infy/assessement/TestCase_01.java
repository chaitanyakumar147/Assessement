package infy.assessement;

import org.testng.Assert;
import org.testng.annotations.Test;

import genericutils.ExcelLibrary;
import genericutils.SeleniumBase;
import objectRepository.InfyTaskObjectRepository;

/**
 * 
 * @author chaitanyakumar B 
 * @scenario :Verify the Proceed Button 
 *
 */
public class TestCase_01 extends SeleniumBase {
	
	@Test
	public void testcase() {
		
		// Initializing the excel library object  
		ExcelLibrary excelLib = new ExcelLibrary("./resource/DataSheet.xlsx","Sheet1");
		
		// Initializing the object instance of the class in order to access the class elements in it
		InfyTaskObjectRepository infyObj = new InfyTaskObjectRepository(getDriver());
		
		// accepting the cookie policy 
		infyObj.acceptTheCoockiePoliy();
		
		// selecting the vehicle body type 
		infyObj.selectVehicleBasedOnBodyType();
		
		// clicking on the more details of the first available vehicle model
		infyObj.clickOnMoreDetails();
		
		// clicking on contact me button
		infyObj.clickOnContactMe();
		
		// typing first name 
		infyObj.typeFirstname(excelLib.getXlData(1, 1));
		
		// typing the last name 
		infyObj.typeLastName(excelLib.getXlData(2, 1));
		
		// typing the email id 
		infyObj.typeEmailId(excelLib.getXlData(3, 1));
		
		// typing the mobile number 
		infyObj.typeMobileNo(excelLib.getXlData(4, 1));
		
		// entering the comments 
		infyObj.typeComments(excelLib.getXlData(5, 1));
		
		// accepting privacy policy check box
		infyObj.selectPrivacyPolicyCheckBox();
		
		// accepting the market consent check box 
		infyObj.selectMarketConsentCheckBox();
		
		// validating the proceed button with assert 
		Assert.assertTrue(infyObj.checkProceedButton());
		
		// closing the work book 
		excelLib.closeWorkBook();
		
		
	}

}
