package organizationModule;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;

public class OrganizationLocationsEdit extends BasePage {
	

	private By adminElement = By.xpath("//a//span[text()=\"Admin\"]");
	private By organizationButton = By
			.xpath("//li[contains(@class,'oxd-topbar-body-nav-tab')][.//span[normalize-space()='Organization']]");
	private By locationButton = By.xpath("//a[text()='Locations']");
	private By editButton = By.xpath("//i[@class='oxd-icon bi-pencil-fill']");
	private By nameField = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
	private By cityField = By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]");
	private By stateField = By.xpath("(//input[@class='oxd-input oxd-input--active'])[4]");
	private By zipField = By.xpath("(//input[@class='oxd-input oxd-input--active'])[5]");
	private By countryField = By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow']");
	private By phoneField = By.xpath("(//input[@class='oxd-input oxd-input--active'])[6]");
	private By addressField = By
			.xpath("(//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical'])[1]");
	private By notesField = By
			.xpath("(//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical'])[2]");
	private By saveField = By.xpath("//button[@type='submit']");
	private By cancelField = By.xpath("(//button[@type='button'])[4]");
	private By errorMessage = By.xpath("//span[text()='Required']");
	private By sucessMessage = By.xpath("//div[@id='oxd-toaster_1']");
	private By phoneError = By.xpath("//span[text()='Allows numbers and only + - / ( )']");
	private final By countryOptions = By.xpath("//div[@role='option']");

	public OrganizationLocationsEdit(WebDriver driver) {
		super(driver);
	}

	public void OpenLocationForm() {
//		wait.until(ExpectedConditions.elementToBeClickable(AdminElement)).click();
		click(adminElement);
//		wait.until(ExpectedConditions.elementToBeClickable(organizationButton)).click();
		click(organizationButton);
//		wait.until(ExpectedConditions.elementToBeClickable(locationButton)).
		click(locationButton);
	}

	public void clickEditButton(int index) {
	    List<WebElement> buttons = wait.until(
	            ExpectedConditions.visibilityOfAllElementsLocatedBy(editButton));

	    wait.until(ExpectedConditions.elementToBeClickable(buttons.get(index))).click();
	}

	public void EditName(String name) {
		
		replaceText(nameField, name);
	}

	public void EditCity(String city) {
		
		replaceText(cityField, city);
	}

	public void EditState(String state) {
		
		replaceText(stateField, state);		
	}

	public void EditZIP(String zip) {
		
		replaceText(zipField, zip);
	}

	public void EditCountry(String country) {
		driver.findElement(countryField).click();

		
		List<WebElement> options = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='option']")));
		for (WebElement option : options) {
			if (option.getText().equals(country)) {
				option.click();
				break;
			}
		}
	}

	public void EditPhone(String phone) {
		replaceText(phoneField, phone);
	}

	public void EditAddress(String address) {
		
		replaceText(addressField, address);
	}

	public void EditNotes(String note) {
		
		replaceText(notesField, note);
	}

	public void ClickSave() {
		click(saveField);
	}

	public void ClickCancel() {
		click(cancelField);
	}

	public boolean isErrorMessageDisplayed() {
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
		return error.isDisplayed();
	}

	public String getSuccessMessage() {
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(sucessMessage));
		return getText(sucessMessage);
	}

	public boolean isinvalidPhoneFormat() {
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneError));
		return error.isDisplayed();
	}
	public boolean isCancelButtonEnabled() {
	    WebElement cancel = wait.until(ExpectedConditions.visibilityOfElementLocated(cancelField));
	    return cancel.isEnabled();
	}
	
}
