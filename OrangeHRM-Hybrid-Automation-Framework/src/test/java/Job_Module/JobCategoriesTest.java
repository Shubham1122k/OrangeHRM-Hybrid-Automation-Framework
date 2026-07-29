package Job_Module;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import QABootcamp_Maven.OrangeHRM.LogIn;
import QABootcamp_Maven.OrangeHRM.TestBase;

public class JobCategoriesTest extends TestBase {

    private LogIn login;
    private JobCategoriesPage jobCategoriesPage;

    @BeforeMethod
    public void initialize() {

        login = new LogIn(getDriver());

        jobCategoriesPage = new JobCategoriesPage(getDriver());

        login.Login("Admin", "admin123");

        Assert.assertTrue(login.isLogedIn(), "Admin login failed");

        jobCategoriesPage.goToJobCategories();
    }

    @Test(priority = 1, description = "TC-07 Verify that a Job category can be added successfully with valid input")
    public void testAddJobCategoryValidInput() {

        jobCategoriesPage.addJobCategory("Skillful");

        Assert.assertTrue(jobCategoriesPage.isSuccessMessageDisplayed());

    }

    @Test(priority = 2, description = "TC-08 Verify system behavior when trying to add a Job Category without entering any data")
    public void testJobCategoryFieldCannotBeBlank() {

        jobCategoriesPage.addJobCategory("");

        Assert.assertTrue(jobCategoriesPage.isRequiredErrorDisplayed());

    }

    @Test(priority = 3, description = "TC-09 Verify the system does not allow adding duplicate job categories")
    public void testDuplicateJobCategoryNotAllowed() {

        jobCategoriesPage.addJobCategory("Professionals");

        Assert.assertTrue(jobCategoriesPage.isAlreadyExistsErrorDisplayed());

    }

    @Test(priority = 4, description = "TC-10 Verify an admin can edit an existing job category")
    public void testEditJobCategory() {

        jobCategoriesPage.editJobCategory("Professionals", "Professionals 2");

        Assert.assertTrue(jobCategoriesPage.isSuccessMessageDisplayed());

    }

    @Test(priority = 5, description = "TC-11 Verify a job category can be deleted individually")
    public void testDeleteSingleJobCategory() {

        jobCategoriesPage.deleteJobCategory("Sales Workers");

        Assert.assertTrue(jobCategoriesPage.isSuccessMessageDisplayed());

    }

    @Test(priority = 6, description = "TC-12 Verify multiple job categories can be deleted simultaneously")
    public void testDeleteMultipleJobCategories() {

        jobCategoriesPage.selectJobCategoryCheckbox("Operatives");

        jobCategoriesPage.selectJobCategoryCheckbox("Craft Workers");

        jobCategoriesPage.deleteSelectedCategories();

        Assert.assertTrue(jobCategoriesPage.isSuccessMessageDisplayed());

    }

    @Test(priority = 7, description = "TC-13 Verify if special characters are allowed or restricted in job category name")
    public void testSpecialCharactersInJobCategory() {

        jobCategoriesPage.addJobCategory("Skillful@2025");

        Assert.assertTrue(jobCategoriesPage.isSuccessMessageDisplayed());

    }

}