package Job_Module;


import base.BasePage;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class EmploymentStatusPage extends BasePage{
	 

	    public EmploymentStatusPage(WebDriver driver) {
	    	super(driver);   // comes from BasePage
	       
	    }

	    // Navigation
	    private By adminMenu = By.xpath("//span[text()='Admin']");
	    private By jobMenu = By.xpath("//span[text()='Job ']");
	    private By empStatusMenu = By.xpath("//a[text()='Employment Status']");

	    // Buttons & Fields
	    private By addBtn = By.xpath("//button[normalize-space()='Add']");
	    private By nameField = By.xpath("//label[text()='Name']/following::input[1]");
	    private By saveBtn = By.xpath("//button[normalize-space()='Save']");
	    private By successToast = By.xpath("//*[@id='oxd-toaster_1']");
	    private By requiredError = By.xpath("//span[text()='Required']");
	    
	    private By confirmDeleteBtn = By.xpath("//button[@type='button' and normalize-space()='Yes, Delete']");
	    private By deleteSelectedBtn = By.xpath("//button[normalize-space()='Delete Selected']");

	    // Navigate to Employment Status page
	    public void goToEmploymentStatus() {
	    	
	        wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
	    	click(jobMenu);
	        click(empStatusMenu);
	    }

	   

	    // Add new Employment Status
	    public void addEmploymentStatus(String statusName) {
	    	 click(addBtn);
	         type(nameField, statusName);
	         click(saveBtn);
	    }

	    
	    public boolean isSuccessMessageDisplayed() {
	        return isDisplayed(successToast);    
	    }

	    public boolean isRequiredErrorDisplayed() {
	        return isDisplayed(requiredError);
	    }

	    // Edit Employment Status
	    public void editEmploymentStatus(String oldName, String newName) {
	        By editBtn = By.xpath("//div[text()='" + oldName + "']/../..//button[i[contains(@class,'bi-pencil-fill')]]");
	        click(editBtn);
	        type(nameField, newName);
	        click(saveBtn);
	    }

	    // Delete single Employment Status
	 // Delete single
	    public void deleteEmploymentStatus(String statusName) {
	        By deleteBtn = By.xpath("//div[text()='" + statusName + "']/../..//button[contains(@class,'oxd-icon-button')]");
	        click(deleteBtn);
	        click(confirmDeleteBtn);
	    }

	    private By toastMessage = By.cssSelector(".oxd-text.oxd-text--toast-message");
	    public boolean isDeleteMessageDisplayed() {
	        return getText(toastMessage).toLowerCase().contains("deleted");
	    }
	    
	    
	    	// Locator for "Already exists" error
	    private By alreadyExistsError = By.xpath("//span[contains(text(),'Already exists')]");
	    public boolean isAlreadyExistsErrorDisplayed() {
	        return isDisplayed(alreadyExistsError);
	    }
	    	
	    

	    // Delete multiple Employment Statuses
		public void selectEmploymentStatusCheckbox(String statusName) {
		By checkbox = By.xpath("//div[text()='" + statusName + "']/ancestor::div[@role='row']//div[@class='oxd-checkbox-wrapper']");
        click(checkbox);
        }

		public void deleteSelectedStatuses() {
	        click(deleteSelectedBtn);
	        click(confirmDeleteBtn);
	    }
	    

	    // refresh page
	    public void refreshPage() {
	        driver.navigate().refresh();
	        goToEmploymentStatus();
	    }
	}


