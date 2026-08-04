package adminModule;

import java.nio.file.Files;
import java.nio.file.Path;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;



public class JobTitlesPage extends BasePage{
    

    public JobTitlesPage(WebDriver driver) {
        super(driver);
    }
    private By adminMenu = By.xpath("//span[text()='Admin']");
    private final By jobMenu        = By.xpath("//span[@class='oxd-topbar-body-nav-tab-item' and normalize-space()='Job']");
    private final By jobTitlesMenu  = By.xpath("//li[normalize-space()='Job Titles'] | //a[normalize-space()='Job Titles']");
    private final By addBtn         = By.xpath("//button[normalize-space()='Add']");
    private final By saveBtn        = By.xpath("//button[normalize-space()='Save']");
    private By successToast   = By.xpath("//div[contains(@class,'oxd-toast') and .//p[normalize-space()='Success']]");
    private By jobTitleField  = By.xpath("(//input[contains(@class,'oxd-input--active')])[2]");
    private By jobDescription = By.xpath("(//textarea[contains(@class,'oxd-textarea--active')])[1]");
    private By jobNote        = By.xpath("(//textarea[@placeholder='Add note'])[1]");
    private By fileInput      = By.xpath("//input[@type='file']");
	private By cancel = By.xpath("//button[normalize-space()='Cancel']");
	private final By confirmDeleteBtn = By.xpath("//button[normalize-space()='Yes, Delete']");

	private final By deleteSelectedBtn = By.xpath("//button[normalize-space()='Delete Selected']");
	        
	// Navigate to Job Title
    public void goToJobTitle() {
        wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(jobMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(jobTitlesMenu)).click();
    }
    
	private By editButton(int index) {
	    return By.xpath("(//button[i[contains(@class,'bi-pencil-fill')]])[" + index + "]");
	}
	
	private By deleteButton(int index) {
	    return By.xpath("(//button[i[contains(@class,'bi-trash')]])[" + index + "]");
	}
	
    //delete 
	private By deleteUsers_Btn = By.xpath("(//div[@class='oxd-checkbox-wrapper'])[1]");

    public void clickJobMenu() {
        click(jobMenu);
    }

    public void clickJobTitlesMenu() {
        click(jobTitlesMenu);
    }

    public void clickAddButton() {
       click(addBtn);
    }

    public void typeJobTitle(String title) {
    	type(jobTitleField, title);
    }
    
    public void typeDescription(String desc) {
    	type(jobDescription, desc);
    }
    
    public void typeNote(String noteText) {
    	scrollIntoView(jobNote);
    	type(jobNote, noteText);
    }

    public void uploadSpecification(String path) {
        if (path == null || path.isBlank()) return;
        Path p = Path.of(path);
        if (!Files.exists(p)) return;

        scrollIntoView(fileInput);
        type(fileInput, p.toAbsolutePath().toString());
    }

    public void clickSaveButton() {
        scrollIntoView(saveBtn);
        click(saveBtn);
    }

    public boolean isSuccessToastDisplayed() {
        waitForVisibility(successToast);
        return isDisplayed(successToast);
    }
    
    
    public boolean isJobTitlePresent(String title, String desc) {
        By row = By.xpath("//div[@role='row' and .//div[text()='"+title+"'] and .//div[text()='"+desc+"']]");
        return isDisplayed(row);
    }

    // delete job title
    public void deleteSelectedUser(int index) {
    		click(deleteButton(index));
    		click(confirmDeleteBtn);
    }

    public void deleteAllUsers() {
        click(deleteUsers_Btn);
        click(deleteSelectedBtn);
        click(confirmDeleteBtn);
    }

	

	

    public void editJobTitle(int index, String newTitle, String newDescription, String newSpecFilePath, String newNote) {
    		click(editButton(index));

        type(jobTitleField, newTitle);
        type(jobDescription, newDescription);

        if (newSpecFilePath != null && !newSpecFilePath.isBlank()) {
            Path p = Path.of(newSpecFilePath);
            if (Files.exists(p)) {
                scrollIntoView(fileInput);
                type(fileInput, p.toAbsolutePath().toString());
            }
        }

        type(jobNote, newNote);
        click(saveBtn);
    }


    public boolean isRequired() {
        return isDisplayed(By.xpath("//span[normalize-space()='Required']"));
    }
	
    public void clickCancel() {
        click(cancel);
    }
	
    public boolean isAlreadyExistDisplayed() {
        return isDisplayed(By.xpath("//span[normalize-space()='Already exists']"));
    }
}



