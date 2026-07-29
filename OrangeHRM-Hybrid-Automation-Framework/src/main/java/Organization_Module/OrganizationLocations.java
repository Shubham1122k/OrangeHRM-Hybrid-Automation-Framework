package Organization_Module;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;

public class OrganizationLocations extends BasePage {
	

	private By adminElement = By
			.xpath("//a//span[text()=\"Admin\"]");

	private By organizationButton = By
			.xpath("//li[contains(@class,'oxd-topbar-body-nav-tab')][.//span[normalize-space()='Organization']]");
	private By locationButton = By.xpath("//a[normalize-space()='Locations']");
	private By addButton=By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']");
	private By nameField=By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
	private By cityField=By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]");
	private By stateField=By.xpath("(//input[@class='oxd-input oxd-input--active'])[4]");
	private By zipField=By.xpath("(//input[@class='oxd-input oxd-input--active'])[5]");
	private By countryField=By.xpath("//div[@class='oxd-select-text oxd-select-text--active']");
	private By phoneField=By.xpath("(//input[@class='oxd-input oxd-input--active'])[6]");
	private By faxField=By.xpath("(//input[@class='oxd-input oxd-input--active'])[7]");
	private By addressField=By.xpath("(//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical'])[1]");
	private By notesField=By.xpath("(//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical'])[2]");
	private By saveButton=By.xpath("//button[@type='submit']");
	private By cancelButton=By.xpath("//button[text()=' Cancel ']");
	private By sucessMessage = By.xpath("//div[@id='oxd-toaster_1']");
	private By errorMessage = By.xpath("//span[text()='Required']");
	private By existErrorMessage=By.xpath("//span[text()='Already exists']");
	private final By countryOptions = By.xpath("//div[@role='option']");
	
	public OrganizationLocations(WebDriver driver) {
		super(driver);
	}

	public void OpenLocationForm() {
		click(adminElement);
		click(organizationButton);
		click(locationButton);
		click(addButton);
		
	}
	public void AddName(String name) {
	    WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
	    nameInput.clear();
	    nameInput.sendKeys(name);
	}

	public void AddCity(String city) {
		
		type(cityField,city);
	}
	
	public void AddState(String state) {
		
		type(stateField,state);
	}
	public void AddZIP(String zip) {
		
		type(zipField,zip);
	}
	
	public void addCountry(String country) {
	    click(countryField);

	    List<WebElement> options = wait.until(
	            ExpectedConditions.visibilityOfAllElementsLocatedBy(countryOptions));

	    for (WebElement option : options) {
	        if (option.getText().trim().equals(country)) {
	            option.click();
	            return;
	        }
	    }

	    throw new RuntimeException("Country not found: " + country);
	}
	public void AddPhone(String phone) {
		
		type(phoneField,phone);
	}
	public void AddFax(String fax) {
//		driver.findElement(faxField).sendKeys(fax);
		type(faxField,fax);
	}
	public void AddAddress(String address) {
//		driver.findElement(addressField).sendKeys(address);
		type(addressField,address);
	}
	public void AddNotes(String notes) {
//		driver.findElement(notesField).sendKeys(notes);
		type(notesField,notes);
	}
	public void SaveAdd() {
//		wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
		click(saveButton);
	}
	public void CancelAdd() {
		WebElement button = driver.findElement(cancelButton);
//		wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
		click(cancelButton);
		System.out.println(button.isEnabled());
	}
	public String getSuccessMessage() {
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(sucessMessage));
		return message.getText().trim();
	}

	public boolean isErrorMessageDisplayed() {
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
		return error.isDisplayed();
	}
	public boolean isExistDisplayed() {
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(existErrorMessage));
		return error.isDisplayed();
	}

	

}
