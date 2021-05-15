package tests;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.testng.annotations.*;

import page_objects.form_pages.SimpleForm;
import page_objects.main_page.GoogleHomePage;


public class GoogleTest extends GoogleSearchBase {
	GoogleHomePage googleHomePage;
	SimpleForm simpleForm;

	@BeforeClass
	public void beforeClass() {
		googleHomePage = new GoogleHomePage(driver);
	}

	@Test
	public void testSearch() {
		googleHomePage.enterSearchTextBox("ipl");
		try {
			Thread.sleep(5000);
		}catch(Exception e) {
			e.printStackTrace();
		}
		Assert.assertEquals("ipl", googleHomePage.getSearchTextBoxValue());
	}

	//	@Test
	//	public void googleSearchNavigation() {
	//		isTitle("Google");
	//		enterSearchText(" festivals of India");
	//		clickEnterFromKeyboard();
	//		navigateToFirstLink();
	//		for(int i=0; i<3; i++) 
	//			driver.navigate().back();
	//		scrollToElementXpath("//span[contains(text(),'2021 Indian Calendar for Indian Festivals and Indi')]");
	//		driver.navigate().back();
	//	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}


