package tests;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import page_objects.form_pages.SimpleForm;
import page_objects.main_page.HomePage;

public class WebFormBase extends BaseTest {
	HomePage homePage;
	SimpleForm simpleForm;
	static ExtentTest test;
	static ExtentReports report;
	
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		report = new ExtentReports(p.getProperty("extent_reports_path"));

		homePage = new HomePage(driver);
		simpleForm = new SimpleForm(driver);
		threadSleep(5);
		handlePopUp();
	}
	
	@AfterClass
	public void afterClass() {
		driver.close();

		report.flush();
	}
	
	public enum FormPage {
		SIMPLE_FORM, CHECKBOX, RADIO, DROPDOWN, INPUT_FORM, AJAX, JQUERY;
	}
	
	/**
	 * Method navigates user to the correct form page inside input tab
	 * @param type
	 */
	public void navigateToFormPage(FormPage type) {
		scrollToElement(homePage.getInputForm(), driver);
		homePage.clickInputForm();
		switch(type) {
		case SIMPLE_FORM: scrollToElement(homePage.getSimpleFormDemo(), driver);
			threadSleep(2);
			homePage.clickSimpleFormDemo();
			break;
		case CHECKBOX: 
			break;
		default:
			break;
		}
	}

	/**
	 * Handles the pop up displayed in the demo webpage by closing it
	 */
	public void handlePopUp() {
		try {
			int count = 0;
			while(count < 10) {
				threadSleep(1);
				if (homePage.isPopUpPresent()) {
					homePage.clickClosePopUpButton();
					break;
				}
				count++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterMethod(alwaysRun=true)
	public void afterMethod(ITestResult result) throws Exception {
		if(result.getStatus() == ITestResult.FAILURE){
                        //We do pass the path captured by this mehtod in to the extent reports using "logger.addScreenCapture" method. 			
                        String screenshotPath = getScreenhot(driver, result.getName());
			//To add it in the extent report 
                        test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));
		}
		
	}
	
	public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
}
