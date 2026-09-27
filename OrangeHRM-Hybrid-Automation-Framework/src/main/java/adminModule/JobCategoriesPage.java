package adminModule;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;

public class JobCategoriesPage extends BasePage {
	
	 public JobCategoriesPage(WebDriver driver) {
	        super(driver);
	    }
	

	    //Navigation
	    private By adminMenu = By.xpath("//span[text()='Admin']");
	    private By jobMenu = By.xpath("//span[text()='Job ']");
	    private By jobCategoriesMenu = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[4]/a");
	    
	    //Locators and Buttons	    
	    private By addBtn = By.xpath("//button[normalize-space()='Add']");
	    private By nameField = By.xpath("//label[text()='Name']/../following-sibling::div/input");
	    private By saveBtn = By.xpath("//button[normalize-space()='Save']");
	    private By requiredError = By.xpath("//span[text()='Required']");
	    private By alreadyExistsError = By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div/span");
	    private By successToast = By.xpath("//p[contains(@class,'oxd-text--toast-title')]");

	    // Navigate to Job Categories menu
	    public void goToJobCategories() {
	        click(adminMenu);
	        click(jobMenu);
	        click(jobCategoriesMenu);
	    }
	    
	    // Add Job Category
	    public void addJobCategory(String name) {
	    	
	    	click(addBtn);
	        if (name != null) {
	        	
	        	type(nameField, name);
	        }
	        click(saveBtn);
	    }
	    

	    // Edit Job Category
	    public void editJobCategory(String oldName, String newName) {
	        WebElement editIcon = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//div[text()='" + oldName + "']/../..//button[i[contains(@class,'bi-pencil-fill')]]")));
	        editIcon.click();

	        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
	        nameInput.sendKeys(Keys.CONTROL + "a");
	        nameInput.sendKeys(Keys.DELETE);
	        nameInput.sendKeys(newName);

	        click(saveBtn);
	    }


	    // Delete one Job Category
	    public void deleteJobCategory(String name) {

	        By deleteIcon = By.xpath(
	            "//div[normalize-space()='" + name + "']/../..//button[i[contains(@class,'bi-trash')]]"
	        );

	        WebElement element = wait.until(
	            ExpectedConditions.elementToBeClickable(deleteIcon)
	        );

	        element.click();

	        confirmDelete();
	    }

	    // Select checkbox for multiple delete
	    public void selectJobCategoryCheckbox(String name) {

	        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//div[text()='" + name + "']/../..//div[@class='oxd-checkbox-wrapper']")));

	        checkbox.click();
	    }

	    // Delete selected
//	    public void deleteSelectedCategories() {
//	        WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
//	                By.xpath("//button[normalize-space()='Delete Selected']")));
//	        deleteBtn.click();
//	        confirmDelete();
//	    }
	    
	    public void deleteSelectedCategories() {

	        By deleteSelectedBtn =
	                By.xpath("//button[normalize-space()='Delete Selected']");

	        WebElement deleteBtn = wait.until(
	                ExpectedConditions.elementToBeClickable(deleteSelectedBtn)
	        );

	        deleteBtn.click();

	        confirmDelete();
	    }
	    
	    // Confirm delete 
	    private void confirmDelete() {
	        WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//button[normalize-space()='Yes, Delete']")));
	        confirmBtn.click();
	    }

	    
	    
	 // Verifications
	    public boolean isSuccessMessageDisplayed() {
	        try {
	            return wait.until(
	                ExpectedConditions.visibilityOfElementLocated(successToast)
	            ).isDisplayed();

	        } catch (TimeoutException e) {
	            return false;
	        }
	    }
	    

	    public boolean isRequiredErrorDisplayed() {
	        try {
	            return wait.until(
	                ExpectedConditions.visibilityOfElementLocated(requiredError)
	            ).isDisplayed();

	        } catch (TimeoutException e) {
	            return false;
	        }
	    }

	    public boolean isAlreadyExistsErrorDisplayed() {
	        try {
	            return wait.until(
	                ExpectedConditions.visibilityOfElementLocated(alreadyExistsError)
	            ).isDisplayed();

	        } catch (TimeoutException e) {
	            return false;
	        }
	    }
	     
		    
	    }
	    
	


