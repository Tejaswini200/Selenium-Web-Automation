package page_objects.form_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SimpleForm {
	
	WebDriver driver;
	
	public SimpleForm(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[text()=\"Two Input Fields\"]")
    WebElement twoInputHeaderText;
	
	@FindBy(xpath="//input[@id=\"sum1\"]")
    WebElement inputField1 ;
	
	@FindBy(xpath="//input[@id=\"sum2\"]")
    WebElement inputField2;
	
	@FindBy(xpath="//button[text()=\"Get Total\"]")
    WebElement getTotalButton;
	
	@FindBy(xpath="//span[@id=\"displayvalue\"]")
    WebElement displayedValue;
	
	public boolean isTwoInputHeaderTextDisplayed() {
		return twoInputHeaderText.isDisplayed();
	}
	
	public void enterFirstField(String value) {
		inputField1.sendKeys(value);
	}
	
	public void enterSecondField(String value) {
		inputField2.sendKeys(value);
	}
	
	public void clickTotalButton() {
		getTotalButton.click();
	}
	
	public String getDisplayedValue() {
		return displayedValue.getText();
	}
	
}
