package QABootcamp_Maven.OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class LogIn extends BasePage {

    public LogIn(WebDriver driver) {
        super(driver);
    }

    private By userName = By.name("username");
    private By password = By.name("password");
    private By logInBtn = By.cssSelector(".oxd-button.oxd-button--main.orangehrm-login-button");
    private By dashboard = By.xpath("//h6[text()='Dashboard']");
    private By errorMessage = By.xpath("//p[text()='Invalid credentials']");

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
    }

    public boolean isLogedIn() {
        return isDisplayed(dashboard);
    }

    public String getMessage() {
        return getText(errorMessage);
    }
}