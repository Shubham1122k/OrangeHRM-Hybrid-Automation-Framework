package loginTest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseTest.TestBase;
import loginModule.LogIn;
import utilities.ExcelUtil;


public class LogInTest extends TestBase {
    LogIn login;

    @BeforeMethod
    public void setUpPage() {
        login = new LogIn(getDriver());
    }

    //Test Method for login    
    @Test(dataProvider = "OrangeHRMExcel")
    public void logIn(String userName, String pass, String expected) {
        login.Login(userName, pass);

        if (expected.equals("Dashboard")) {
            Assert.assertTrue(login.isLogedIn(), "Dashboard should display");
        } else {
            Assert.assertEquals(login.getMessage(), expected, "Error message should match");
        }
    }
    
    
    
    //Read login data from Utilities package.ExcelUtil method.
    //Data is stored in xlsx file present in src/test/resource path
    
    @DataProvider(name = "OrangeHRMExcel")
    public Object[][] loginData() throws IOException {
    	
    	//passing the Path and the Sheet no. where data is present     	
        return ExcelUtil.getTestData("OrangeHRMExcel.xlsx","Sheet1");
    }
}

