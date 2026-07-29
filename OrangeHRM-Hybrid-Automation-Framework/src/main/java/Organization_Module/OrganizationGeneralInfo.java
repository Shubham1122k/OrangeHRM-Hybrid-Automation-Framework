package Organization_Module;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;

public class OrganizationGeneralInfo extends BasePage {
	
	private By adminElement = By
			.xpath("//a//span[text()=\"Admin\"]");

	private final By organizationButton = By
			.xpath("//li[contains(@class,'oxd-topbar-body-nav-tab')][.//span[normalize-space()='Organization']]");

	private final By generalInformationButton = By.xpath("//a[normalize-space()='General Information']");

	private final By editToogle = By.cssSelector("span.oxd-switch-input");

	private By organizationName = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
	private By registrationNumber = By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]");
	private By taxIDField = By.xpath("(//input[@class='oxd-input oxd-input--active'])[4]");
	private By phoneField = By.xpath("(//input[@class='oxd-input oxd-input--active'])[5]");
	private By faxField = By.xpath("(//input[@class='oxd-input oxd-input--active'])[6]");
	private By emailField = By.xpath("(//input[@class='oxd-input oxd-input--active'])[7]");
	private By addressStreet1Field = By.xpath("(//input[@class='oxd-input oxd-input--active'])[8]");
	private By addressStreet2Field = By.xpath("(//input[@class='oxd-input oxd-input--active'])[9]");
	private By cityField = By.xpath("(//input[@class='oxd-input oxd-input--active'])[10]");
	private By stateField = By.xpath("(//input[@class='oxd-input oxd-input--active'])[11]");
	private By zip_PostalCodeField = By.xpath("(//input[@class='oxd-input oxd-input--active'])[12]");
	private By countryField = By.xpath("//div[@class='oxd-select-text-input']");
	private By notesField = By.xpath("(//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical'])[1]");

	private By save = By.xpath("//button[@type='submit']");
	private By sucessMessage = By.xpath("//div[@id='oxd-toaster_1']");
	private By errorMessage = By.xpath("//span[text()='Required']");
	private By emailError = By.xpath("//span[text()='Expected format: admin@example.com']");
	private By phoneError = By.xpath("//span[text()='Allows numbers and only + - / ( )']");

	public OrganizationGeneralInfo(WebDriver driver) {
		super(driver);
	}

	public void openGeneralInformationForm() {
		click(adminElement);
		click(organizationButton);
		click(generalInformationButton);
		click(editToogle);

	}

	public void editOrganizatioName(String OrganizationName) {
		replaceText(organizationName, OrganizationName);
	}
//	{	WebElement name = wait.until(ExpectedConditions.elementToBeClickable(organizationName));
//		name.sendKeys(Keys.CONTROL + "a");
//		name.sendKeys(Keys.DELETE);
////		driver.findElement(organizationName).clear();
//		name.sendKeys(OrganizationName);
//	}

	public void editRegistrationNumber(String registrationnumber) {
		replaceText(registrationNumber, registrationnumber);
	}
//	{
//		WebElement Number = wait.until(ExpectedConditions.elementToBeClickable(registrationNumber));
//		Number.sendKeys(Keys.CONTROL + "a");
//		Number.sendKeys(registrationnumber);
//	}

	public void editTaxID(String taxID) {
		replaceText(taxIDField, taxID);
	}
//	{
//		WebElement tax = wait.until(ExpectedConditions.elementToBeClickable(TaxID));
//		tax.sendKeys(Keys.CONTROL + "a");
//		tax.sendKeys(taxID);
//	}

	public void editPhone(String phone) {
		replaceText(phoneField, phone);
	}
//		{WebElement PhoneNum = wait.until(ExpectedConditions.elementToBeClickable(Phone));
//		PhoneNum.sendKeys(Keys.CONTROL + "a");
//		PhoneNum.sendKeys(phone);}
	

	public void editFax(String fax) {
		replaceText(faxField, fax);
	}
//	{	WebElement FAX = wait.until(ExpectedConditions.elementToBeClickable(Fax));
//		FAX.sendKeys(Keys.CONTROL + "a");
//		FAX.sendKeys(fax);
//	}

	public void editEmail(String email) {
		replaceText(emailField, email);
	}
//	{	WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(Email));
//		emailInput.sendKeys(Keys.CONTROL + "a");
//		emailInput.sendKeys(email);
//	}

	public void editAddressStreet1(String addressStreet1) {
		replaceText(addressStreet1Field, addressStreet1);
	}	
//	{	WebElement address = wait.until(ExpectedConditions.elementToBeClickable(AddressStreet1));
//		address.sendKeys(Keys.CONTROL + "a");
//		address.sendKeys(addressStreet1);
//	}

	public void eddressStreet2(String addressStreet2) {
		replaceText(addressStreet2Field, addressStreet2);
	}	
//	{
//		WebElement address2 = wait.until(ExpectedConditions.elementToBeClickable(AddressStreet2));
//		address2.sendKeys(Keys.CONTROL + "a");
//		address2.sendKeys(addressStreet2);
//	}

	public void editCity(String city) {
		replaceText(cityField, city);
	}
//	{
//		WebElement CITY = wait.until(ExpectedConditions.elementToBeClickable(City));
//		CITY.sendKeys(Keys.CONTROL + "a");
//		CITY.sendKeys(city);
//	}

	public void editZIPPostalCode(String ZipPostalCode) {
		replaceText(zip_PostalCodeField, ZipPostalCode);
	}
//	{
//		WebElement ZIP = wait.until(ExpectedConditions.elementToBeClickable(ZIP_PostalCode));
//		ZIP.sendKeys(Keys.CONTROL + "a");
//		ZIP.sendKeys(ZipPostalCode);
//	}

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
//	{	WebElement note = wait.until(ExpectedConditions.elementToBeClickable(Notes));
//		note.sendKeys(Keys.CONTROL + "a");
//		note.sendKeys(notes);
//	}

	public void saveEdit() {
		click(save);
	}

	public String getSuccessMessage() {
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(sucessMessage));
		return message.getText().trim();
	}

	public boolean isErrorMessageDisplayed() {
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
		return isDisplayed(errorMessage);
		
	}

	public boolean isinvalidFormatDisplayed() {
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(emailError));
		return isDisplayed(emailError);
	}

	public boolean isinvalidPhoneFormat() {
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneError));
		return isDisplayed(phoneError);
	}

}
