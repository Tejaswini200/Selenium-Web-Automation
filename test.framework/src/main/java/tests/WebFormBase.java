package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

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
	
}
