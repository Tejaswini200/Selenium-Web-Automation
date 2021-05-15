package page_objects.main_page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lombok.Getter;


public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[text()=\"Input Forms\"]")
    WebElement inputForm;
	
	@FindBy(xpath="//a[text()=\"Input Forms\"]/following-sibling::ul/li/a[text() = 'Simple Form Demo']")
    WebElement simpleFormDemo;
	
	@FindBy(xpath = "//a[@title = 'Close']")
	WebElement closeIconOfPopUp;
	
	public WebElement getInputForm() {
		return inputForm;
	}
	
	public WebElement getSimpleFormDemo() {
		return simpleFormDemo;
	}
	
	public void clickInputForm() {
		inputForm.click();
	}
	
	public void clickSimpleFormDemo() {
		simpleFormDemo.click();
	}
	
	public void clickClosePopUpButton() {
		closeIconOfPopUp.click();
	}
	
	public boolean isPopUpPresent()  {
		return closeIconOfPopUp.isDisplayed();
	}
	

}
