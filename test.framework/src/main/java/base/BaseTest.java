package base;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.Assert;
import org.testng.ITestNGMethod;

public class BaseTest {

	public static WebDriver driver;
	protected Properties p;

	@BeforeSuite
	public void beforeSuite() {
	    FileReader reader = null;
		try {
			reader = new FileReader("src\\main\\resources\\configuration\\framework.properties");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
	    p=new Properties();  
	    try {
			p.load(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
 
		System.setProperty("webdriver.chrome.driver", p.getProperty("chromedriver_path"));  
		driver= new ChromeDriver(); 
		driver.manage().window().maximize();
		driver.get(p.getProperty("testing_url"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterSuite
	public void afterMethod() {
		driver.quit();
	}
	
	/**
	 * Methods for executing thread sleep for the seconds passed
	 * @param seconds
	 */
	public void threadSleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methods scrolls till the element is seen on webpage
	 * @param element
	 * @param driver
	 */
	public void scrollToElement(WebElement element, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	

	
}	

