package adminTest;



import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import adminModule.EmploymentStatusPage;
import baseTest.TestBase;
import loginModule.LogIn;

public class EmploymentStatusTest extends TestBase {

    private LogIn login;
    private EmploymentStatusPage empStatusPage;

    @BeforeMethod
    public void initialize() {

        login = new LogIn(getDriver());

        empStatusPage = new EmploymentStatusPage(getDriver());

        login.Login("Admin", "admin123");

        Assert.assertTrue(login.isLogedIn(), "Login Failed");

        empStatusPage.goToEmploymentStatus();
    }

    @Test
    public void testAddEmploymentStatus() {

        empStatusPage.addEmploymentStatus("Contractual");

        Assert.assertTrue(empStatusPage.isSuccessMessageDisplayed());

    }

    @Test
    public void testEmploymentStatusFieldCannotBeBlank() {

        empStatusPage.addEmploymentStatus("");

        Assert.assertTrue(empStatusPage.isRequiredErrorDisplayed());

    }

    @Test
    public void testEditEmploymentStatus() {

        empStatusPage.editEmploymentStatus("Freelance", "Freelance 2");

        Assert.assertTrue(empStatusPage.isSuccessMessageDisplayed());

    }

    @Test
    public void testDeleteEmploymentStatus() {

        empStatusPage.deleteEmploymentStatus("Full-Time Contract");

        Assert.assertTrue(empStatusPage.isDeleteMessageDisplayed());

    }
}