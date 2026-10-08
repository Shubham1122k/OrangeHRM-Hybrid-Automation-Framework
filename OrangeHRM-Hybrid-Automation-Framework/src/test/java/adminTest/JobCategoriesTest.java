package adminTest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import adminModule.JobCategoriesPage;
import baseTest.baseTest;
import loginModule.LogIn;
import utilities.ConfigReader;

public class JobCategoriesTest extends baseTest {

    private JobCategoriesPage jobCategoriesPage;

    @BeforeMethod
	    public void initialize() {
	
	        jobCategoriesPage = new JobCategoriesPage(getDriver());
	        loginAsAdmin();
	        jobCategoriesPage.goToJobCategories();
	    }

    @Test(
    		priority = 1, 
    		description = "TC-07 Verify that a Job category can be added successfully with valid input")
	    public void testAddJobCategoryValidInput() {
    	
    			String categoryName = "Skillful_" + System.currentTimeMillis();

	        jobCategoriesPage.addJobCategory("categoryName");
	
	        Assert.assertTrue(jobCategoriesPage.isSuccessMessageDisplayed());
	
	    }

    @Test(
    		priority = 2, 
    		description = "TC-08 Verify system behavior when trying to add a Job Category without entering any data")
	    public void testJobCategoryFieldCannotBeBlank() {
	
	        jobCategoriesPage.addJobCategory("");
	
	        Assert.assertTrue(jobCategoriesPage.isRequiredErrorDisplayed());
	
	    }

    @Test(
    		priority = 3, 
    		description = "TC-09 Verify the system does not allow adding duplicate job categories")
	    public void testDuplicateJobCategoryNotAllowed() {
	
	        jobCategoriesPage.addJobCategory("Professionals");
	
	        Assert.assertTrue(jobCategoriesPage.isAlreadyExistsErrorDisplayed());
	
	    }

    @Test(
    		priority = 4, 
    		description = "TC-10 Verify an admin can edit an existing job category")
	    public void testEditJobCategory() {
	
	        jobCategoriesPage.editJobCategory("Professionals", "Professionals 2");
	
	        Assert.assertTrue(jobCategoriesPage.isSuccessMessageDisplayed());
	
	    }

    @Test(priority = 5, description = "TC-11 Verify a job category can be deleted individually")
    public void testDeleteSingleJobCategory() {

        String categoryName = "DeleteTest_" + System.currentTimeMillis();

        // Create test data
        jobCategoriesPage.addJobCategory(categoryName);

        Assert.assertTrue(
                jobCategoriesPage.isSuccessMessageDisplayed(),
                "Job category was not created successfully"
        );

        // Delete the same category
        jobCategoriesPage.deleteJobCategory(categoryName);

        Assert.assertTrue(
                jobCategoriesPage.isSuccessMessageDisplayed(),
                "Job category was not deleted successfully"
        );
    }

    @Test(priority = 6, description = "TC-12 Verify multiple job categories can be deleted simultaneously")
    public void testDeleteMultipleJobCategories() {

        String category1 = "MultiDelete1_" + System.currentTimeMillis();
        String category2 = "MultiDelete2_" + System.currentTimeMillis();

        // Create first category
        jobCategoriesPage.addJobCategory(category1);

        Assert.assertTrue(
                jobCategoriesPage.isSuccessMessageDisplayed(),
                "First job category was not created"
        );

        // Create second category
        jobCategoriesPage.addJobCategory(category2);

        Assert.assertTrue(
                jobCategoriesPage.isSuccessMessageDisplayed(),
                "Second job category was not created"
        );

        // Select both categories
        jobCategoriesPage.selectJobCategoryCheckbox(category1);
        jobCategoriesPage.selectJobCategoryCheckbox(category2);

        // Delete selected categories
        jobCategoriesPage.deleteSelectedCategories();

        Assert.assertTrue(
                jobCategoriesPage.isSuccessMessageDisplayed(),
                "Selected job categories were not deleted"
        );
    }

    @Test(
    		priority = 7, 
    		description = "TC-13 Verify if special characters are allowed or restricted in job category name")
	    public void testSpecialCharactersInJobCategory() {
	
	        jobCategoriesPage.addJobCategory("Skillful@2025");
	
	        Assert.assertTrue(jobCategoriesPage.isSuccessMessageDisplayed());
	
	    }

}