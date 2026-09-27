package baseTest;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import loginModule.LogIn;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class TestBase {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }
    
    @BeforeMethod(alwaysRun = true)
    public void setup() {

    	String browser = ConfigReader.getProperty("browser");

    	driver.set(DriverFactory.createDriver(browser));

        getDriver().manage().window().maximize();

        getDriver().get(ConfigReader.getProperty("url"));
    }

    // Reusable login method /used across all @beforeMethods in classes
    protected void loginAsAdmin() {

        LogIn login = new LogIn(getDriver());

        login.Login(
        		ConfigReader.getProperty("username"), 
        		ConfigReader.getProperty("password")
        );

        Assert.assertTrue(login.isLogedIn(), "Login Failed");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        WebDriver webDriver = driver.get();

        if (webDriver != null) {
            webDriver.quit();
            driver.remove();
        }
    }
}