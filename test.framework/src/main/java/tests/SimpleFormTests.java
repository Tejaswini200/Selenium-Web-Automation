package tests;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import utility.Log;

public class SimpleFormTests extends WebFormBase {
//	private Logger Log = Logger.getLogger(this.getClass());
	private final static Logger LOGGER = LogManager.getLogger(SimpleFormTests.class);
	
	
	@BeforeMethod
	public void beforeMethod() {
//		ITestContext iTestContext;
		
		navigateToFormPage(FormPage.SIMPLE_FORM);
	}
	
	@AfterMethod
	public void afterMethod()  {
		report.endTest(test);
	}

	@DataProvider (name = "input data")
	public Object[][] dpMethod(){
		return new Object[][] {{"2", "3", "6"}};
	}
//	, {"-2", "-3", "-5"}	, {"3.5", "1.5", "4" },
//		{"10000","40000","50000"},{ "25.2695", "20.3649","45"},{"5"," ","NaN"},{"a","b","NaN"},
//		{"Sun","rise","NaN"},{"@","&","NaN"}}


	@Test(description = "Test to validate 2 input fields moduels", dataProvider = "input data")
	public void sumOfTwoFields(String inputFieldOne, String inputFieldTwo, String output) {
		test = report.startTest("sumOfTwoFields(" + inputFieldOne + "," + inputFieldTwo + ")");
		LOGGER.setLevel(Level.ALL);
		LOGGER.info("sumOfTwoFields(" + inputFieldOne + "," + inputFieldTwo + ")");
		Assert.assertEquals(true, simpleForm.isTwoInputHeaderTextDisplayed());
		LOGGER.info("Two input header is displayed.");
		LOGGER.debug("DEBUG STATEMENT______");
		test.log(LogStatus.PASS, "Two input header is displayed.");
		simpleForm.enterFirstField(inputFieldOne);
		test.log(LogStatus.PASS, inputFieldOne+ " is entered in Input field one");
		simpleForm.enterSecondField(inputFieldTwo);
		test.log(LogStatus.PASS, inputFieldTwo+ " is entered in Input field two");
		simpleForm.clickTotalButton();
		test.log(LogStatus.PASS, "Total button is clicked");
		Assert.assertEquals(output, simpleForm.getDisplayedValue());
		test.log(LogStatus.PASS, "Test case passed successfully!");
		LOGGER.info("Test case completed");
	}

}
	

