package adminModule;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BasePage;

public class PayGradesPage extends BasePage {
    
    Actions action;
    
    public PayGradesPage(WebDriver driver) {
     	super(driver);
        this.action = new Actions(driver);
    }

    //Navigation
    private final By adminMenu 		 = By.xpath("//span[text()='Admin']");
    private final By jobMenu          = By.xpath("//li[normalize-space()='Job'] | //span[normalize-space()='Job']");
    private final By payGradesMenu    = By.xpath("//li[normalize-space()='Pay Grades'] | //a[normalize-space()='Pay Grades']");
    
    //Locators and Buttons    
    private final By addpayGradeButton  = By.xpath("//button[normalize-space()='Add']");
    private final By addCurrencyBtn   = By.xpath("//h6[normalize-space()='Currencies']/following::button[1]");
    private final By saveBtn          = By.xpath("//button[normalize-space()='Save']"); 
    private final By successToast = By.xpath("//div[contains(@class,'oxd-toast')]");
    private final By nameField        = By.xpath("//label[normalize-space()='Name']/following::input[1]");
    private final By currencyDrop     = By.xpath("//label[normalize-space()='Currency']/following::div[contains(@class,'oxd-select-text')][1]");
    private final By minSalaryInput   = By.xpath("//label[normalize-space()='Minimum Salary']/following::input[1]");
    private final By maxSalaryInput   = By.xpath("//label[normalize-space()='Maximum Salary']/following::input[1]");
    private final By secondSaveBtn    = By.xpath("(//button[normalize-space()='Save'])[2]");
    private final By headerCheckbox   = By.xpath("(//div[@class='oxd-checkbox-wrapper'])[1]");
    private final By deleteSelected   = By.xpath("//button[normalize-space()='Delete Selected']");
    private final By confirmYes       = By.xpath("//button[normalize-space()='Yes, Delete']"); 
    private final By payGradesHeading = By.xpath("//h6[normalize-space()='Pay Grades']");
    private final By FORM_LOADER		 = By.cssSelector(".oxd-form-loader");
    private By payGradesTable = By.cssSelector(".oxd-table-body");
    By editSaveBtn = By.xpath("//form//button[normalize-space()='Save']");
    
    
    
    //Navigate to Pay Grades menu   
    public void clickPayGradesMenu() {

        click(adminMenu);

        WebElement job = waitForClickability(
            By.xpath("//span[normalize-space()='Job']")
        );

        action.moveToElement(job).click().perform();

        click(By.xpath("//a[normalize-space()='Pay Grades']"));

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(payGradesHeading)
        );
    }
    
    //click btn particular for this page     
//    private WebElement clickable(By by) {
//    	
//        return wait.until(ExpectedConditions.elementToBeClickable(by));
//    }
    
    
    //scroll page    
    private void scroll(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
    }
    
    //refreshes page    
    public void refreshPage() {
        driver.navigate().refresh();
        try {
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    //click Job Menu
    public void clickJobMenu() {
    		click(jobMenu);
    }
    
    //click Add PayGrade  button
    public void clickAddPayGradeBtn() {
    		click(addpayGradeButton);
    }
    
	//click Add Currency button
	public void clickAddCurrencyBtn() {
	    click(addCurrencyBtn);
	}
	

    //click save button
    public void clickSave() {
    		click(saveBtn);
    }
    
    
    
    
    
    
    public void savePayGrade() {

        click(saveBtn);

        waitForLoaderToDisappear();

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(successToast)
        );
    }

    
    //Success toast btn
    public boolean waitForSuccessToast() {
        try {
            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(successToast)
            ).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }

    // Type PayGradeName
    public void typeName(String name) {
    	type(nameField, name);
    }
    
    // Add Pay GradeDetails
    public void addPayGrade(String gradeName, int index,
            String minSalary, String maxSalary) {

    		clickAddPayGradeBtn();   // FIRST Add

        typeName(gradeName);
        clickSave();

        clickAddCurrency();   // SECOND Add - DIFFERENT locator

        selectCurrency(index);
        typeMinimumSalary(minSalary);
        typeMaximumSalary(maxSalary);
        clickCurrencySave();
    }

    //  click Add Currency 
   
    public void clickAddCurrency() {
	    scrollIntoView(addCurrencyBtn);
	    click(addCurrencyBtn);
	}
    
    //  Currency select btn
    public void selectCurrency(int index) {

        scrollIntoView(currencyDrop);
        click(currencyDrop);

        By optionBy = By.xpath("//div[@role='listbox']//div[@role='option'][" + index + "]");

        click(optionBy);
    }

    //  Min salary    	
    public void typeMinimumSalary(String min) {
        scrollIntoView(minSalaryInput);

        WebElement f = waitForClickability(minSalaryInput);
        f.clear();
        f.sendKeys(min);
    }
    
    //  Max salary
    public void typeMaximumSalary(String max) {
        scrollIntoView(maxSalaryInput);

        WebElement f = waitForClickability(maxSalaryInput);
        f.clear();
        f.sendKeys(max);
    }

    //  Save currency    
    public void clickCurrencySave() {
        scrollIntoView(secondSaveBtn);
        click(secondSaveBtn);
    }

    //  Delete Pay Grade by index
    public void deleteByIndex(int index) {

        By rowDeleteBtn = By.xpath(
            "(//button[i[contains(@class,'bi-trash')]])[" + index + "]"
        );

        scrollIntoView(rowDeleteBtn);
        click(rowDeleteBtn);

        click(confirmYes);
    }
    
    //  Delete all Pay Grades
    public void deleteAll() {

        scrollIntoView(headerCheckbox);
        click(headerCheckbox);

        click(deleteSelected);

        click(confirmYes);
    }

   
    //  Check warning display    
    public boolean isWarningDisplayed(String expectedText) {
        By warningMsg = By.xpath("//span[normalize-space()='" + expectedText + "']");
        try {
            WebElement el = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(warningMsg));
            return el.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
    
    
    //  Edit Pay Grade 
    public void editPayGradeName(String gradeName, String newName) {

        By editBtn = By.xpath(
                "//div[contains(@class,'oxd-table-row')]"
                + "[.//div[normalize-space()='" + gradeName + "']]"
                + "//button[i[contains(@class,'bi-pencil-fill')]]"
        );

        // Click edit button
        scrollIntoView(editBtn);
        click(editBtn);

        waitForLoaderToDisappear();

        // Wait for name field
        WebElement name = waitForClickability(nameField);

        // Clear old value
        name.click();
        name.sendKeys(Keys.CONTROL, "a");
        name.sendKeys(Keys.BACK_SPACE);

        // Verify field is empty
        wait.until(driver -> name.getAttribute("value").isEmpty());

        // Enter new name
        name.sendKeys(newName);

        // Verify correct value before saving
        Assert.assertEquals(
                name.getAttribute("value"),
                newName,
                "New Pay Grade name was not entered correctly"
        );

        // Save
        click(saveBtn);

        // Wait for success
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(successToast)
        );

        waitForLoaderToDisappear();
    }
    
    public boolean isPayGradeDisplayed(String gradeName) {

        try {

            By gradeLocator = By.xpath(
                "//div[contains(@class,'oxd-table-body')]"
                + "//div[contains(@class,'oxd-table-row')]"
                + "//div[normalize-space()='" + gradeName + "']"
            );

            WebElement grade = wait.until(
                ExpectedConditions.visibilityOfElementLocated(gradeLocator)
            );

            return grade.isDisplayed();

        } catch (TimeoutException e) {

           
            List<WebElement> rows = driver.findElements(
            	    By.xpath("//div[contains(@class,'oxd-table-body')]//div[contains(@class,'oxd-table-row')]")
            	);

            	for (WebElement row : rows) {
            	    System.out.println(row.getText());
            	}

            return false;
        }
    }
    
    
    //  Currency edit 	   
    public void editCurrencyDetails(int currencyIndex, String newMin, String newMax) {
        By editCurrencyBtn = By.xpath("(//button[i[contains(@class,'bi-pencil-fill')]])[" + currencyIndex + "]");
        scrollIntoView(editCurrencyBtn);
        click(editCurrencyBtn);

        WebElement min = waitForClickability(minSalaryInput);
        scroll(min);
        min.click();
        min.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        min.sendKeys(Keys.DELETE);
        min.sendKeys(newMin);

        WebElement max = waitForClickability(maxSalaryInput);
        scroll(max);
        max.click();
        max.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        max.sendKeys(Keys.DELETE);
        max.sendKeys(newMax);

        clickCurrencySave();
    }
    
    
    

    public boolean isRequired() {
        By requiredMsg = By.xpath("//span[normalize-space()='Required']");

        try {
            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(requiredMsg)
            ).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
    	
    	//  delete PayGrade by Name    
    public void deletePayGrade(String gradeName) {

        By deleteBtn = By.xpath(
            "//div[contains(@class,'oxd-table-row')]"
            + "[.//div[normalize-space()='" + gradeName + "']]"
            + "//button[.//i[contains(@class,'bi-trash')]]"
        );

        scrollIntoView(deleteBtn);
        click(deleteBtn);

        click(confirmYes);
    }
    
    public void goToPayGradesList() {

        String currentUrl = driver.getCurrentUrl();

        if (!currentUrl.endsWith("/admin/payGrade")) {
            clickPayGradesMenu();
        }

        waitForLoaderToDisappear();

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(payGradesTable)
        );
    }
    
    public boolean isPayGradePresent(String gradeName) {

        try {

            By payGrade = By.xpath(
                    "//div[contains(@class,'oxd-table-row')]"
                    + "//div[normalize-space()='" + gradeName + "']"
            );

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(payGrade)
            );
            return true;

        } catch (TimeoutException e) {
        	
            return false;
        }
    }
}
