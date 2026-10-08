package organizationModule;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import basePage.BasePage;

public class OrganizationGeneralInfo extends BasePage {
	
	private By adminElement = By.xpath("//span[text()='Admin']");

	private final By organizationButton = By
			.xpath("//li[contains(@class,'oxd-topbar-body-nav-tab')][.//span[normalize-space()='Organization']]");

	private final By generalInformationButton = By.xpath("//a[normalize-space()='General Information']");
	private final By editToggleLabel =
	        By.xpath("//label[.//span[contains(@class,'oxd-switch-input')]]");
//	private final By editToggle =
//	        By.cssSelector("span.oxd-switch-input");

	private By organizationName = By.xpath(
		    "//label[normalize-space()='Organization Name']/ancestor::div[contains(@class,'oxd-input-group')]//input"
		);

	private By registrationNumber = By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]");
	private By taxIDField = By.xpath("(//input[@class='oxd-input oxd-input--active'])[4]");
	private By phoneField = By.xpath(
		    "//label[normalize-space()='Phone']/ancestor::div[contains(@class,'oxd-input-group')]//input"
		);
	private By faxField = By.xpath(
		    "//label[normalize-space()='Fax']/ancestor::div[contains(@class,'oxd-input-group')]//input"
		);

	private final By emailField =
		    By.xpath("//label[normalize-space()='Email']/ancestor::div[contains(@class,'oxd-input-group')]//input");


	private By addressStreet1Field = By.xpath(
		    "//label[normalize-space()='Address Street 1']/ancestor::div[contains(@class,'oxd-input-group')]//input"
	);

	private By addressStreet2Field = By.xpath(
		    "//label[normalize-space()='Address Street 2']/ancestor::div[contains(@class,'oxd-input-group')]//input"
	);
	
	private By cityField = By.xpath(
		    "//label[normalize-space()='City']/ancestor::div[contains(@class,'oxd-input-group')]//input"
	);

	private By stateField = By.xpath(
		    "//label[normalize-space()='State/Province']/ancestor::div[contains(@class,'oxd-input-group')]//input"
	);

	private By zip_PostalCodeField = By.xpath(
		    "//label[normalize-space()='Zip/Postal Code']/ancestor::div[contains(@class,'oxd-input-group')]//input"
	);
	private By countryField = By.xpath("//div[@class='oxd-select-text-input']");
	private By notesField = By.xpath("(//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical'])[1]");

	private By save = By.xpath("//button[@type='submit' and normalize-space()='Save']");
	private By sucessMessage = By.xpath("//div[@id='oxd-toaster_1']");
	private By errorMessage = By.xpath(
		    "//span[contains(@class,'oxd-input-field-error-message') and normalize-space()='Required']"
		);
	private By emailError = By.xpath(
		    "//span[contains(@class,'oxd-input-field-error-message') " +
		    "and contains(normalize-space(),'Expected format')]"
		);
	
	private By phoneError = By.xpath(
		    "//span[contains(@class,'oxd-input-field-error-message') " +
		    "and contains(normalize-space(),'Allows numbers')]"
		);


	public OrganizationGeneralInfo(WebDriver driver) {
		super(driver);
	}
	
	//Navigate to GeneralInfoPage		
	public void openGeneralInformationForm() {
	    click(adminElement);
	    click(organizationButton);
	    click(generalInformationButton);

	    // Wait until page is loaded
	    waitForVisibility(organizationName);
	}

//	public void enterEditMode() {
//
//	    WebElement toggle = waitForVisibility(editToggle);
//
//	    if (!toggle.isSelected()) {
//	        toggle.click();
//	    }
//
//	    wait.until(driver ->
//	        driver.findElement(organizationName).isEnabled()
//	    );
//	}
	public void enterEditMode() {

	    scrollIntoView(editToggleLabel);

	    click(editToggleLabel);

	    wait.until(driver ->
	        driver.findElement(organizationName).isEnabled()
	    );
	}
	
	public void editOrganizatioName(String OrganizationName) {
		replaceText(organizationName, OrganizationName);
	}


	public void editRegistrationNumber(String registrationnumber) {
		replaceText(registrationNumber, registrationnumber);
	}


	public void editTaxID(String taxID) {
		replaceText(taxIDField, taxID);
	}


	public void editPhone(String phone) {
		replaceText(phoneField, phone);
	}

	

	public void editFax(String fax) {
		replaceText(faxField, fax);
	}


	public void editEmail(String email) {
		replaceText(emailField, email);
	}

	public void editAddressStreet1(String addressStreet1) {
		replaceText(addressStreet1Field, addressStreet1);
	}	


	public void eddressStreet2(String addressStreet2) {
		replaceText(addressStreet2Field, addressStreet2);
	}	


	public void editCity(String city) {
		replaceText(cityField, city);
	}


	public void editZIPPostalCode(String ZipPostalCode) {
		replaceText(zip_PostalCodeField, ZipPostalCode);
	}


	public void editCountry(String country) {
		click(countryField);

		List<WebElement> options = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='option']")));
		for (WebElement option : options) {
			if (option.getText().equals(country)) {
				option.click();
				break;
			}
		}
	}

	public void editNote(String notes) {
		replaceText(notesField, notes);
	}

	public void saveEdit() {
	    waitForLoaderToDisappear();

	    WebElement saveButton = waitForVisibility(save);

	    wait.until(ExpectedConditions.elementToBeClickable(saveButton));

	    saveButton.click();
	}
	
	public String getSuccessMessage() {
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(sucessMessage));
		return message.getText().trim();
	}

	
	
	public boolean isErrorMessageDisplayed() {
	    return isDisplayed(errorMessage);
	}

	public boolean isinvalidFormatDisplayed() {
	    return isDisplayed(emailError);
	}

	public boolean isinvalidPhoneFormat() {
	    return isDisplayed(phoneError);
	}
	
	public String getOrganizationName() {
	    return driver.findElement(organizationName).getAttribute("value");
	}
	
	
}
