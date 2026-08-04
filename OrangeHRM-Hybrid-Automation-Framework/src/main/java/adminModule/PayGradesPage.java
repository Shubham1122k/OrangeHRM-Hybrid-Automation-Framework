package adminModule;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;

public class PayGradesPage extends BasePage {
    
    Actions action;

    public PayGradesPage(WebDriver driver) {
    	super(driver);
        this.action = new Actions(driver);
    }

    // ===== Locators ===== can make final as they are not going to change
    private final By jobMenu         = By.xpath("//li[normalize-space()='Job'] | //span[normalize-space()='Job']");
    private final By payGradesMenu   = By.xpath("//li[normalize-space()='Pay Grades'] | //a[normalize-space()='Pay Grades']");
    private final By addBtn          = By.xpath("//button[normalize-space()='Add']");
    private final By saveBtn         = By.xpath("//button[normalize-space()='Save']");
    private final By successToast    = By.xpath("//div[contains(@class,'oxd-toast') and .//p[normalize-space()='Success']]");

    private By nameField       = By.xpath("//label[normalize-space()='Name']/following::input[1]");

    private By currencyDrop    = By.xpath("//label[normalize-space()='Currency']/following::div[contains(@class,'oxd-select-text')][1]");
    private By minSalaryInput  = By.xpath("//label[normalize-space()='Minimum Salary']/following::input[1]");
    private By maxSalaryInput  = By.xpath("//label[normalize-space()='Maximum Salary']/following::input[1]");
    private By secondSaveBtn   = By.xpath("(//button[normalize-space()='Save'])[2]");

    private By headerCheckbox  = By.xpath("(//div[@class='oxd-checkbox-wrapper'])[1]");
    private By deleteSelected  = By.xpath("//button[normalize-space()='Delete Selected']");
    private By confirmYes      = By.xpath("//button[normalize-space()='Yes, Delete']");

    
    private WebElement clickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    private void scroll(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
    }
    
 
    public void refreshPage() {
        driver.navigate().refresh();
        try {
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void clickJobMenu() {
    		click(jobMenu);
    }

    public void clickPayGradesMenu() {
        WebElement el = clickable(payGradesMenu);
        scroll(el);
        el.click();
    }

    public void clickAdd() {
    		click(addBtn);
    }

    public void clickSave() {
    		click(saveBtn);
    }

    public boolean waitForSuccessToast() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(successToast));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    //  Add Pay Grade
    public void typeName(String name) {
    	 		type(nameField, name);
    }

    //  Currency 
    public void clickAddCurrency() {
        WebElement el = clickable(addBtn);
        scroll(el);
        el.click();
    }

    public void selectCurrency(int index) {
        WebElement dd = clickable(currencyDrop);
        scroll(dd);
        dd.click();

        By optionBy = By.xpath("//div[@role='listbox']//div[@role='option'][" + index + "]");
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionBy));
        option.click();
    }

    public void typeMinimumSalary(String min) {
        WebElement f = clickable(minSalaryInput);
        scroll(f);
        f.clear();
        f.sendKeys(min);
    }

    public void typeMaximumSalary(String max) {
        WebElement f = clickable(maxSalaryInput);
        scroll(f);
        f.clear();
        f.sendKeys(max);
    }

    public void clickCurrencySave() {
        WebElement el = clickable(secondSaveBtn);
        scroll(el);
        el.click();
    }

    // Delete Pay Grade
    public void deleteByIndex(int index) {
        By rowDeleteBtn = By.xpath("(//button[i[contains(@class,'bi-trash')]])[" + index + "]");
        WebElement btn = clickable(rowDeleteBtn);
        scroll(btn);
        btn.click();
        clickable(confirmYes).click();
    }

    public void deleteAll() {
        WebElement hdr = clickable(headerCheckbox);
        scroll(hdr);
        hdr.click();
        clickable(deleteSelected).click();
        clickable(confirmYes).click();
    }

   

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
    
 // Edit Pay Grade 
    public void editPayGradeName(int index, String newName) {
        By editBtn = By.xpath("(//button[i[contains(@class,'bi-pencil-fill')]])[" + index + "]");
        WebElement btn = clickable(editBtn);
        scroll(btn);
        btn.click();

        WebElement name = clickable(nameField);
        scroll(name);
        name.click();
        name.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        name.sendKeys(Keys.DELETE);
        name.sendKeys(newName);

        clickSave();
    }

    public void editCurrencyDetails(int currencyIndex, String newMin, String newMax) {
        By editCurrencyBtn = By.xpath("(//button[i[contains(@class,'bi-pencil-fill')]])[" + currencyIndex + "]");
        WebElement btn = clickable(editCurrencyBtn);
        scroll(btn);
        btn.click();

        WebElement min = clickable(minSalaryInput);
        scroll(min);
        min.click();
        min.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        min.sendKeys(Keys.DELETE);
        min.sendKeys(newMin);

        WebElement max = clickable(maxSalaryInput);
        scroll(max);
        max.click();
        max.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        max.sendKeys(Keys.DELETE);
        max.sendKeys(newMax);

        clickCurrencySave();
    }
    
    
    // add pay grade
    public void addPayGrade(String gradeName,int index,String minSalary,String maxSalary) {
    	 clickAdd();
         typeName(gradeName);
         clickSave();

          clickAddCurrency();
      selectCurrency(index);
        typeMinimumSalary(minSalary);
         typeMaximumSalary(maxSalary);
         clickCurrencySave();
    }

}
