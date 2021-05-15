package page_objects.main_page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleHomePage {
	WebDriver driver;

    @FindBy(xpath="//input[@title = 'Search']")
    WebElement searchField;
    
    public GoogleHomePage(WebDriver driver) {
    	 this.driver=driver;
    	 PageFactory.initElements(driver, this);
    }
    
    public void clickSearchTextBox(){
    	searchField.click();
    }

    public void clearSearchTextBox(){
    	searchField.clear();
    }
    
    public void enterSearchTextBox(String searchText){
    	searchField.sendKeys(searchText);
    }
    
    public String getSearchTextBoxValue(){
    	String searchText = searchField.getText();
    	return searchText;
    }
    
   //https://www.seleniumeasy.com/test/
}
