package adminModule;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;

public class WorkShiftsPage extends BasePage {
	
    public WorkShiftsPage(WebDriver driver) {
    		super(driver);
    }

    // Navigate
    private final By adminMenu = By.xpath("//span[text()='Admin']");
    private final By jobMenu = By.xpath("//li[normalize-space()='Job'] | //span[normalize-space()='Job']");
    private final By workMenu = By.xpath("//a[text()='Work Shifts' and @class='oxd-topbar-body-nav-tab-link']");
    
    //Locators and Buttons    
    private final By addButton= By.xpath("//button[normalize-space()='Add']");
    private final By saveButton= By.xpath("//button[normalize-space()='Save']");
    private final By deleteSelectedBtn= By.xpath("//button[normalize-space()='Delete Selected']");
    private final By confirmDeleteBtn= By.xpath("//button[normalize-space()='Yes, Delete']");
    private final By cancelButton= By.xpath("//button[normalize-space()='Cancel']");
    private final By editButton= By.xpath("(//button[contains(@class,'oxd-icon-button')])[4]");
    
    private final By shiftName= By.xpath("//label[normalize-space()='Shift Name']/following::input[1]");
    private final By fromHours= By.xpath("//label[normalize-space()='From']/following::input[1]");
    private final By toHours= By.xpath("//label[normalize-space()='To']/following::input[1]");
    private final By tableBody= By.cssSelector(".oxd-table-body");
    private final By successToast= 
    		By.xpath("//p[contains(.,'Successfully Saved') or contains(.,'Successfully Deleted') or contains(.,'Successfully Updated')]");
    private final By errorToast= By.xpath("//p[contains(.,'Required') or contains(.,'Already exists') or contains(.,'Should be')]");

    //Navigate to  Work Shift menu     
    public void clickWorkShiftsMenu() {
    	click(adminMenu);
    	click(jobMenu);
    	click(workMenu);
    	click(tableBody);
    }
    
    private By deleteButton(String name) {
        return By.xpath("//div[text()='" + name +
                "']/ancestor::div[@class='oxd-table-card']//button[i[contains(@class,'bi-trash')]]");
    }

    private By checkbox(String name) {
        return By.xpath("//div[text()='" + name +
                "']/ancestor::div[@class='oxd-table-card']//div[contains(@class,'oxd-checkbox-wrapper')]");
    }

    private By row(String name) {
        return By.xpath("//div[@class='oxd-table-card']//div[text()='" + name + "']");
    }

    public void clickAddButton() {
    		click(addButton);
      
    		waitForVisibility(shiftName);
    }

    public void addWorkShift(String name, String from, String to) {
        WebElement ShiftName = wait.until(ExpectedConditions.visibilityOfElementLocated(shiftName));
        ShiftName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        if (name != null) ShiftName.sendKeys(name);

        
        WebElement fromH = wait.until(ExpectedConditions.elementToBeClickable(fromHours));
        fromH.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        fromH.sendKeys(from);
        fromH.sendKeys(Keys.TAB);

       
        WebElement toH = wait.until(ExpectedConditions.elementToBeClickable(toHours));
        toH.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        toH.sendKeys(to);
        toH.sendKeys(Keys.TAB);

        clickSave();
    }

    public void clickSave() {
        WebElement save = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        try {
            save.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", save);
        }
    }

    public void editWorkShift(String newName, String newFrom, String newTo) {
    	
    	
        WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(editButton));
        editBtn.click();
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(shiftName));
        nameInput.click();
        nameInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        nameInput.sendKeys(Keys.DELETE);
        nameInput.sendKeys(newName);

        WebElement fromInput = driver.findElement(fromHours);
        fromInput.click();
        fromInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, newFrom);

        WebElement toInput = driver.findElement(toHours);
        toInput.click();
        toInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, newTo);
        
        try { Thread.sleep(500); 
        } 
        catch (InterruptedException e) { 	
        }
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
    }
   

    public void deleteWorkShift(String name) {
        
    	click(deleteButton(name));
    	click(confirmDeleteBtn);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(confirmDeleteBtn));
    }

    public void selectWorkShiftCheckbox(String name) {
        
    	click(checkbox(name));
    }

    public void deleteSelected() {
    	click(deleteSelectedBtn);
    	click(confirmDeleteBtn);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(confirmDeleteBtn));
    }
    
    public void closeAddDialogIfOpen() {
        try {
            WebElement cancelBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(cancelButton));
            if (cancelBtn.isDisplayed()) {
                cancelBtn.click();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(cancelButton));
            }
        } catch (Exception e) {
        	
        }
    }

    // Assertions 

    public boolean isRowVisible(String shift) {
        try {
            By row = By.xpath("//div[@class='oxd-table-card']//div[text()='" + shift + "']");
            waitForVisibility(row);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    
    public boolean isSuccessToastVisible() {
        try {
        	waitForVisibility(successToast);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isErrorToastVisible() {
        try {
        	waitForVisibility(errorToast);
            return true;
        } catch (TimeoutException e) {
            return false;
        }    
    }
}

	
	

	
	