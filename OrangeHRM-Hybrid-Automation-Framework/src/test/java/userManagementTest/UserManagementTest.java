package userManagementTest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseTest.baseTest;
import loginModule.LogIn;
import userManagement.UserManagement;

public class UserManagementTest extends baseTest {
	
    private UserManagement userManagement;

    @BeforeMethod
    public void setUpPage() {

        userManagement = new UserManagement(getDriver());

        loginAsAdmin();
    }

    // Here we first store UserName in variable username and then add this user.
    // Then we search the  same user weather we find it or not to test the testcases below   
	@Test(description = "TC-05 Verify search works correctly")
	public void searchUser() throws InterruptedException {

	    String username = "User" + System.currentTimeMillis();
	    String password = "OrangeHRM_Usr$99";
	    String role = "Admin";
	    String status = "Enabled";
	    String empPrefix = "a";

	    userManagement.clickAdminBtn();
	    userManagement.clickAdd();

	    userManagement.addUser(role, empPrefix, status, username, password, password);

	    Assert.assertTrue(userManagement.waitForSuccessToast(),
	            "User was not created successfully.");

	    userManagement.searchByUsername(username);

	    Assert.assertTrue(
	            userManagement.checkSearchResult(username, role, status),
	            "The system should search for the user correctly.");
	}

	@Test(description = "TC-06 Create user (admin/ess) with valid data")
	public void addUser() throws InterruptedException{

	    String username = "User" + System.currentTimeMillis();
	    String password = "OrangeHRM_Usr$99";
	    String role = "Admin";
	    String status = "Enabled";
	    String empPrefix = "a";
	

		userManagement.clickAdminBtn();
		userManagement.clickAdd();
		userManagement.addUser(role, empPrefix,status,username,password,password);
		Assert.assertTrue(userManagement.waitForSuccessToast(), "Success toast did not appear after adding a user");
		Assert.assertTrue(userManagement.checkDataEntered(username,role,status),
				"The user was not added correctly.");
	}

	@Test(description = "TC-07 Create user (admin/ess) with existing username")
	public void checkUniqueUserName() throws InterruptedException {

	    String username = "User" + System.currentTimeMillis();
	    String password = "OrangeHRM_Usr$99";
	    String role = "Admin";
	    String status = "Enabled";
	    String empPrefix = "a";

	    // Create a user first
	    userManagement.clickAdminBtn();
	    userManagement.clickAdd();
	    userManagement.addUser(role, empPrefix, status, username, password, password);

	    Assert.assertTrue(userManagement.waitForSuccessToast(),
	            "User was not created successfully.");

	    // Try to create another user with the same username
	    userManagement.clickAdd();
	    userManagement.enterUserName(username);

	    Assert.assertTrue(userManagement.isAlreadyExistDisplayed(),
	            "Already Exists message is not displayed.");
	}

	@Test(description = "TC-08 Verify password and confirm password match")
	public void checkPassword() {

	    userManagement.clickAdminBtn();
	    userManagement.clickAdd();

	    userManagement.enterPass("OrangeHRM_Usr$99");
	    userManagement.enterConfPass("WrongPassword");

	    Assert.assertTrue(userManagement.isPassConfirmed(),
	            "Passwords do not match message is not displayed.");
	}

	@Test(description = "TC-09 Verify required fields")
	public void checkRequiredFields() {

	    userManagement.clickAdminBtn();
	    userManagement.clickAdd();

	    userManagement.clickSave();

	    Assert.assertTrue(userManagement.isRequired(),
	            "Required message is not displayed.");
	}

	@Test(description = "TC-10 Verify that the delete works correctly")
	public void deleteUser() throws InterruptedException {

	    String username = "User" + System.currentTimeMillis();
	    String password = "OrangeHRM_Usr$99";
	    String role = "Admin";
	    String status = "Enabled";
	    String empPrefix = "a";

	    // Create a user
	    userManagement.clickAdminBtn();
	    userManagement.clickAdd();

	    userManagement.addUser(role,empPrefix,status,username,password,password);

	    Assert.assertTrue(
	            userManagement.waitForSuccessToast(),
	            "User was not created successfully.");

	    // Delete the same user
	    userManagement.deleteUser(username);

	    Assert.assertTrue(
	            userManagement.waitForSuccessToast(),
	            "User was not deleted successfully.");
	}
//enabled = false,keep this false when running parallel xml file
	@Test( description = "TC-11 Verify that the delete All useres Selected works correctly")
	public void deleteAllUser() {
		userManagement.deleteAllUsers();
		Assert.assertTrue(userManagement.waitForSuccessToast(), "Delete isn't work");

	}

	@Test(description = "TC-12 Verify that the edit works correctly")
	public void editUser() throws InterruptedException {

	    String username = "User" + System.currentTimeMillis();
	    String password = "OrangeHRM_Usr$99";
	    String role = "Admin";
	    String status = "Enabled";
	    String empPrefix = "a";
	    String editedUsername = "EditedUser" + System.currentTimeMillis();
	    String editedPassword = "OrangeHRM_New$795";
	    String editedRole = "ESS";
	    String editedStatus = "Disabled";
	    String editedEmpName = "u";

	    // Create user
	    userManagement.clickAdminBtn();
	    userManagement.clickAdd();
	    userManagement.addUser(role, empPrefix, status, username, password, password);

	    Assert.assertTrue(userManagement.waitForSuccessToast());

	    // Edit the created user
	    userManagement.editUser(
	            username,
	            editedRole,
	            editedEmpName,
	            editedStatus,
	            editedUsername,
	            true,
	            editedPassword);

	    Assert.assertTrue(
	            userManagement.waitForSuccessToast(),
	            "Success toast did not appear after editing the user.");
	}

}
