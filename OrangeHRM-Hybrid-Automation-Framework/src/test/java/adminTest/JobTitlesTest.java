package adminTest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import adminModule.JobTitlesPage;
import baseTest.TestBase;
import loginModule.LogIn;
import utilities.ConfigReader;

public class JobTitlesTest extends TestBase {
	
    private JobTitlesPage jobTitles;

    private final String title       = "QA AutomationEngineer";
    private final String description = "Owns test Strategy and Automation";
    private final String specFilePath= "/OrangeHRM-Hybrid-Automation-Framework/src/test/resources"; 
    private final String note        = "Created via Selenium test in job title module";

    @BeforeMethod
	    public void initialize()  {
  		
	        jobTitles = new JobTitlesPage(getDriver());
	        loginAsAdmin();
	        jobTitles.goToJobTitle();
	    }

    @Test(
    		priority = 0, 
    		description = "TC-13 Add valid job title")
	    public void addJobTitle() {
	    	jobTitles.goToJobTitle();
	        jobTitles.clickJobMenu();
	        jobTitles.clickJobTitlesMenu();
	        jobTitles.clickAddButton();
	        jobTitles.typeJobTitle(title);
	        jobTitles.typeDescription(description);
	        jobTitles.uploadSpecification(specFilePath);
	        jobTitles.typeNote(note);
	        jobTitles.clickSaveButton();
	
	        Assert.assertTrue(jobTitles.isSuccessToastDisplayed(),
	                "Job Title was not saved.");
	
	        Assert.assertTrue(jobTitles.isJobTitlePresent(title, description),
	                "Job Title & Description not found in table!");
	    }

    @Test(
    		priority = 1, 
    		description = "TC-14 Verify required fields")
	    public void checkRequiredFields() {
	
	        jobTitles.clickJobMenu();
	        jobTitles.clickJobTitlesMenu();
	        jobTitles.clickAddButton();
	        jobTitles.clickSaveButton();
	
	        Assert.assertTrue(jobTitles.isRequired(),
	                "Required message not displayed");
	
	        jobTitles.clickCancel();
	    }

    @Test(
    		priority = 2, 
    		description = "TC-15 Edit Job Title")
	    public void editJobTitle() {
	
	        jobTitles.editJobTitle(1,
	                "Edited Job Title11",
	                "updated job description",
	                null,
	                "Updated note1");
	
	        Assert.assertTrue(jobTitles.isSuccessToastDisplayed(),
	                "Edit failed");
	    }

    @Test(
    		priority = 3, 
    		description = "TC-16 Delete single job")
	    public void deleteJob() {
	
	        jobTitles.deleteSelectedUser(1);
	
	        Assert.assertTrue(jobTitles.isSuccessToastDisplayed(),
	                "Delete failed");
	    }

    @Test(
    		priority = 4, 
    		description = "TC-17 Delete all selected jobs")
	    public void deleteAllJobs() {
	
	        jobTitles.deleteAllUsers();
	
	        Assert.assertTrue(jobTitles.isSuccessToastDisplayed(),
	                "Bulk delete failed");
	    }
}