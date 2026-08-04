package baseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import loginModule.LogIn;

public class TestBase {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() {

        WebDriverManager.chromedriver().setup();

        driver.set(new ChromeDriver());

        getDriver().manage().window().maximize();

        getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    // Reusable login method
    protected void loginAsAdmin() {

        LogIn login = new LogIn(getDriver());

        login.Login("Admin", "admin123");

        Assert.assertTrue(login.isLogedIn(), "Login Failed");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}