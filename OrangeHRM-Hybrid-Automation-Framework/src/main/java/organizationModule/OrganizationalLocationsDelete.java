package organizationModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;

public class OrganizationalLocationsDelete extends BasePage {
	

	private By adminElement = By .xpath("//a//span[text()=\"Admin\"]");
			
	private By organizationButton = By
			.xpath("//li[contains(@class,'oxd-topbar-body-nav-tab')][.//span[normalize-space()='Organization']]");
	private By locationButton = By.xpath("//a[text()='Locations']");
	private By deleteButton = By.xpath("//i[@class='oxd-icon bi-trash']");
	private By confirmDelete = By
			.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']");
	private By cancelButton = By
			.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--ghost orangehrm-button-margin']");
	private By successMessage = By.xpath("//div[@id='oxd-toaster_1']");
	private By deleteSelectedButton = By.xpath("//button[text()=' Delete Selected ']");
	private By allCheckBox = By.xpath("(//div[@class='oxd-checkbox-wrapper'])[1]");
	private By checkBox=By.xpath("//i[@class='oxd-icon bi-check oxd-checkbox-input-icon']");

	public OrganizationalLocationsDelete(WebDriver driver) {
		super(driver);
	}

	public void OpenLocationForm() {
		wait.until(ExpectedConditions.elementToBeClickable(adminElement)).click();
		wait.until(ExpectedConditions.elementToBeClickable(organizationButton)).click();
		click(locationButton);
	}

	public void clickDeleteButton(int index) {
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(deleteButton, index));
		driver.findElements(deleteButton).get(index).click();
	}

	public void ConfirmDelete() {
		wait.until(ExpectedConditions.elementToBeClickable(confirmDelete)).click();

	}

	public void CancelDelete() {
		wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();

	}

	public String getSuccessMessage() {
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
		return getText(successMessage).trim();
	}

	public boolean isCancelButtonEnabled() {
		WebElement cancel = wait.until(ExpectedConditions.visibilityOfElementLocated(cancelButton));
		return cancel.isEnabled();
	}

	public void SelectRecord(int index) {
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(checkBox, index));
		driver.findElements(checkBox).get(index).click();

	}
	public void SelectAllChecBox() {
		wait.until(ExpectedConditions.elementToBeClickable(allCheckBox)).click();

	}

	public void DeleteSelected() {
		wait.until(ExpectedConditions.elementToBeClickable(deleteSelectedButton)).click();
	}
	
	public boolean isSelectedCheckBox() {
		WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(checkBox));
		return checkbox.isSelected();
	}

}
