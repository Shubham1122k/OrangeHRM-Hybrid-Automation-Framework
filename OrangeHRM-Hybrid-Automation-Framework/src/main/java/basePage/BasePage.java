package basePage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage  {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    // Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    // Wait for visibility
    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for clickability
    protected WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    // Type action
    protected void type(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    // Get text
    protected String getText(By locator) {
        return waitForVisibility(locator).getText();
    }

    // Check display or not
    protected boolean isDisplayed(By locator) {
        try {
            return waitForVisibility(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //scrolling
    protected void scrollIntoView(By locator) {
        WebElement el = waitForVisibility(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
    }
    
    //replaceText
    protected void replaceText(By locator, String value) {
        WebElement element = waitForClickability(locator);
        element.sendKeys(Keys.CONTROL, "a");
        element.sendKeys(Keys.BACK_SPACE);
        element.sendKeys(value);
    }
    
    // Loader disappear   
    private final By FORM_LOADER = By.cssSelector(".oxd-form-loader");
    public void waitForLoaderToDisappear() {
        wait.until(
            ExpectedConditions.invisibilityOfElementLocated(FORM_LOADER)
        );
    }
    
    //click action    
    protected void click(By locator) {
        waitForLoaderToDisappear();
        waitForClickability(locator).click();
    }
    
    //Toast waiting 
    protected boolean waitForToast(By toastLocator) {

        return wait.until(driver -> {
            try {
                return driver.findElement(toastLocator).isDisplayed();
            } catch (StaleElementReferenceException e) {
                return false;
            }
        });
    }
    
}
