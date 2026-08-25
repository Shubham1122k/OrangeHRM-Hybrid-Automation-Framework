package adminTest;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import adminModule.WorkShiftsPage;
import baseTest.TestBase;
import loginModule.LogIn;
import utilities.ConfigReader;

public class WorkShiftsTest extends TestBase {
	LogIn login;
    WorkShiftsPage workShiftPage;

   
    @BeforeMethod
    public void initialize()  {
        login = new LogIn(getDriver());
        workShiftPage = new WorkShiftsPage(getDriver());
        login.Login(ConfigReader.getProperty("username"),
        		ConfigReader.getProperty("password"));
        Assert.assertTrue(login.isLogedIn(), "Admin Login failed");
        workShiftPage.clickWorkShiftsMenu();
    }

    @AfterMethod
    public void smallPause() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test(priority = 1,description ="TC-14 Verify Admin can add a new Work Shift with valid details")
    public void addValidWorkShift() {
        workShiftPage.clickAddButton();
        workShiftPage.addWorkShift("Day Shift", "09:00 AM", "06:00 PM");
        Assert.assertTrue(workShiftPage.isSuccessToastVisible(), "Expected success toast after saving Day Shift");
        Assert.assertTrue(workShiftPage.isRowVisible("Day Shift"), "Day Shift row should exist");
    }

    @Test(priority = 2,description ="TC-15 Verify Admin can add an overnight Work Shift ")
    public void addOvernightWorkShift_bugShowsError() {
    	workShiftPage.clickAddButton();
    	workShiftPage.addWorkShift("Overnight Shift", "10:00 PM", "06:00 AM");
        Assert.assertTrue(workShiftPage.isErrorToastVisible(),
                "Expected error toast: 'To time should be after from time'");
    }

    @Test(priority = 3,description ="TC-16 Verify Admin cannot add Work Shift with missing mandatory fields")
    public void cannotAddWithMissingMandatoryFields() {
    	workShiftPage.clickAddButton();
    	workShiftPage.addWorkShift("", "11:00 AM", "07:00 PM"); 
        Assert.assertTrue(workShiftPage.isErrorToastVisible(), "Expected 'Required' error");
    }

    @Test(priority = 4,description ="TC-17 Verify Admin cannot add Work Shift with duplicate name")
    public void cannotAddDuplicateName() {
    	workShiftPage.clickAddButton();
    	workShiftPage.addWorkShift("Day Shift", "07:00 AM", "03:00 PM");
        Assert.assertTrue(workShiftPage.isErrorToastVisible(), "Expected 'Already exists' error for duplicate name");
    }

    @Test(priority = 5,description ="TC-18 Verify Admin can edit an existing Work Shift")
    public void editDayShiftToDayShift2() {
    	workShiftPage.editWorkShift("Day Shift 2", "11:00 AM", "05:00 PM");
        Assert.assertTrue(workShiftPage.isSuccessToastVisible(), "Expected success toast after editing");
        
    }
   
    @Test(priority = 6,description ="TC-19 Verify Admin can delete an existing Work Shift")
    public void deleteSingleWorkShift() {
        
    	workShiftPage.deleteWorkShift("General");
        Assert.assertTrue(workShiftPage.isSuccessToastVisible(), "Expected success toast after single delete");
    }

    @Test(priority = 7,description ="TC-20 Verify Admin can delete multiple Work Shifts at the same time")
    public void deleteMultipleWorkShifts() {
        
    	workShiftPage.selectWorkShiftCheckbox("Day Shift 2");
        workShiftPage.selectWorkShiftCheckbox("Twilight");
        workShiftPage.deleteSelected();
        Assert.assertTrue(workShiftPage.isSuccessToastVisible(), "Expected success toast after multi delete");
    }
    
    @AfterMethod
    public void cleanup() throws InterruptedException {
    	workShiftPage.closeAddDialogIfOpen();
        Thread.sleep(3000);
        }


}
	 
	