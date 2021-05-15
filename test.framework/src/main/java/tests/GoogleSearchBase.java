package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import base.BaseTest;

public class GoogleSearchBase extends BaseTest{
	
	/**
	 * Method verifies the title of the page
	 * @param title
	 */
	public void isTitle(String title) {
		Assert.assertEquals(title, driver.getTitle());
	}
	
	
	/**
	 * this method enters text in the search Textbox
	 * @param searchText
	 */
	public void enterSearchText(String searchText) {
		WebElement search= driver.findElement(By.xpath("//input[@title = 'Search']"));
		search.sendKeys(searchText);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	/**
	 * this method clicks on the enter button on the google search text box
	 */
	public void clickEnterFromKeyboard() {
		driver.findElement(By.xpath("//input[@title = 'Search']")).sendKeys(Keys.ENTER);
	}

	/**
	 * 
	 */
	public void navigateToFirstLink() {
		WebElement link1 = driver.findElement(By.xpath("//span[contains(text(),' Month-wise L')]"));
		link1.click();
		WebElement link2 = driver.findElement(By.xpath("//a[contains(text(),'Places to Visit')]"));
		link2.click();
		WebElement link3 = driver.findElement(By.xpath("//h3[contains(text(),'1. Goa')]"));
		link3.click();
	}
	
	/**
	 * Method to scroll down to an element using xpath provided
	 * @param xpath
	 */
	public void scrollToElementXpath(String xpath) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		WebElement scrolltill = driver.findElement(By.xpath(xpath));
		js.executeScript("arguments[0].scrollIntoView();",scrolltill);
	}
	
	public void implcitWaitInSeconds(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}
}
