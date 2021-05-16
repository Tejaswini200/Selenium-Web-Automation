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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.testng.Assert;
import org.testng.ITestNGMethod;

public class BaseTest {

	public static WebDriver driver;
	public static Properties p;

	
	
	@BeforeTest
	@Parameters({"browser","headless"})
	public void beforeTest(String browser, String headless ) {
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
	    try {
			driver = setupBrowserDriver(browser, p, driver,headless);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
 
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
	
	/**
	 * This function will execute before each Test tag in testng.xml
	 * @param browser
	 * @throws Exception
	 */
	public WebDriver setupBrowserDriver(String browser, Properties p, WebDriver driver, String headless) throws Exception{
		if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver",p.getProperty("firefoxdriver_path"));
			driver = new FirefoxDriver();
			return driver;
		}
		else if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver",p.getProperty("chromedriver_path"));
			if(headless.equalsIgnoreCase("yes")){
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200",
						"--ignore-certificate-errors","--disable-extensions","--no-sandbox",
						"--disable-dev-shm-usage");

				driver = new ChromeDriver(options);
			}
			else
				driver=new ChromeDriver();
			return driver;
		}
		else{
			throw new Exception("Browser is not correct");
		}
	}

	
}	

