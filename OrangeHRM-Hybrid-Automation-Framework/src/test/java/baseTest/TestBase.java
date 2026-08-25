package baseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import loginModule.LogIn;
import utilities.ConfigReader;

public class TestBase {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() {

    	String browser = ConfigReader.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());

        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        getDriver().manage().window().maximize();

        getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    // Reusable login method
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