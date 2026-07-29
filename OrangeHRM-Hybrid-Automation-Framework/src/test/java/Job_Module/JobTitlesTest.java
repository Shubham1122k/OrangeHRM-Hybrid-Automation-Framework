package Job_Module;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import QABootcamp_Maven.OrangeHRM.LogIn;
import QABootcamp_Maven.OrangeHRM.TestBase;

public class JobTitlesTest extends TestBase {
	
    private LogIn login;
    JobTitles jobTitles;

    private final String title       = "QA Engineer12435";
    private final String description = "Owns test strategy and automation";
    private final String specFilePath= "C:\\hassan\\test.txt"; 
    private final String note        = "Created via Selenium test";

    @BeforeMethod
    public void setUpPage() {
    	login = new LogIn(getDriver());
        jobTitles = new JobTitles(getDriver());
        login.Login("Admin", "admin123");

        Assert.assertTrue(login.isLogedIn(), "Admin login failed");
    }

    @Test(priority = 0, description = "TC-13 Add valid job title")
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

    @Test(priority = 1, description = "TC-14 Verify required fields")
    public void checkRequiredFields() {

        jobTitles.clickJobMenu();
        jobTitles.clickJobTitlesMenu();
        jobTitles.clickAddButton();
        jobTitles.clickSaveButton();

        Assert.assertTrue(jobTitles.isRequired(),
                "Required message not displayed");

        jobTitles.clickCancel();
    }

    @Test(priority = 2, description = "TC-15 Edit Job Title")
    public void editJobTitle() {

        jobTitles.editJobTitle(1,
                "Edited Job Title11",
                "updated job description",
                null,
                "Updated note1");

        Assert.assertTrue(jobTitles.isSuccessToastDisplayed(),
                "Edit failed");
    }

    @Test(priority = 3, description = "TC-16 Delete single job")
    public void deleteJob() {

        jobTitles.deleteSelectedUser(1);

        Assert.assertTrue(jobTitles.isSuccessToastDisplayed(),
                "Delete failed");
    }

    @Test(priority = 4, description = "TC-17 Delete all selected jobs")
    public void deleteAllJobs() {

        jobTitles.deleteAllUsers();

        Assert.assertTrue(jobTitles.isSuccessToastDisplayed(),
                "Bulk delete failed");
    }
}