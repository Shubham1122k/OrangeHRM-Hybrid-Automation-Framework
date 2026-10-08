package loginModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import basePage.BasePage;

public class LogIn extends BasePage {

    public LogIn(WebDriver driver) {
        super(driver);
    }

    private By userName = By.name("username");
    private By password = By.name("password");
    private By logInBtn = By.cssSelector(".oxd-button.oxd-button--main.orangehrm-login-button");
    private final By dashboard = By.xpath("//h6[normalize-space()='Dashboard']");
    private By errorMessage = By.xpath("//p[text()='Invalid credentials']");
    
    //Social Links on Login Page    
    private By linkedin = By.xpath("//a[contains(@href,'linkedin')]");
    private By facebook = By.xpath("//a[contains(@href,'facebook')]");
    private By twitter = By.xpath("//a[contains(@href,'twitter') or contains(@href,'x.com')]");
    private By youtube = By.xpath("//a[contains(@href,'youtube')]");

    public void enterUserName(String name) {
        type(userName, name);
    }

    public void enterPass(String pass) {
        type(password, pass);
    }

    public void clickLogIn() {
        click(logInBtn);
    }

    public void Login(String username, String pass) {
        enterUserName(username);
        enterPass(pass);
        clickLogIn();
        waitForVisibility(dashboard);
    }

    public boolean isLogedIn() {
        try {
            return waitForVisibility(dashboard).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getMessage() {
        return getText(errorMessage);
    }
    
    //Social Links Methods    
    public void clickLinkedIn() {
        driver.findElement(linkedin).click();
    }

    public void clickFacebook() {
        driver.findElement(facebook).click();
    }

    public void clickTwitter() {
        driver.findElement(twitter).click();
    }

    public void clickYoutube() {
        driver.findElement(youtube).click();
    }
}