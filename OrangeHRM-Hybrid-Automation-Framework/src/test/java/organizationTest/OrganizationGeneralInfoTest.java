package organizationTest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseTest.baseTest;
import loginModule.LogIn;
import organizationModule.OrganizationGeneralInfo;
import utilities.ConfigReader;

public class OrganizationGeneralInfoTest extends baseTest {
	
	private LogIn login;
	private OrganizationGeneralInfo organization;

	@BeforeMethod(alwaysRun = true)
    public void initialize() {

        login = new LogIn(getDriver());
        organization = new OrganizationGeneralInfo(getDriver());

        login.Login(
            ConfigReader.getProperty("username"),
            ConfigReader.getProperty("password")
        );

        Assert.assertTrue(
            login.isLogedIn(),
            "Admin Login Failed"
        );

        organization.openGeneralInformationForm();
    }

	@Test(description = "Verify edit organizationName")
	public void OrganizationNameTest() {

	    organization.enterEditMode();

	    String originalName = organization.getOrganizationName();

	    organization.editOrganizatioName("AXSOS2");
	    organization.saveEdit();

	    Assert.assertTrue(
	        organization.getSuccessMessage().contains("Successfully Updated")
	    );

	    // Save returns page to view mode
	    organization.enterEditMode();

	    organization.editOrganizatioName(originalName);
	    organization.saveEdit();
	}
	
	@Test(description = "Verify invalid phone Number")
	public void InvalidPhoneNumberTest() {

	    organization.enterEditMode();

	    organization.editPhone("phone");
	    organization.saveEdit();

	    Assert.assertTrue(
	        organization.isinvalidPhoneFormat(),
	        "Invalid phone number message not displayed"
	    );
	}
	
	@Test(description = "Verify invalidEmail")
	public void InvalidEmailTest() {

	    organization.enterEditMode();

	    organization.editEmail("abc");
	    organization.saveEdit();

	    Assert.assertTrue(
	        organization.isinvalidFormatDisplayed(),
	        "Invalid email error message was not displayed"
	    );
	}
	
	@Test(description = "Verify admin can edit general information by leave required field empty")
	public void InvalidBlankEditTest() {

	    organization.enterEditMode();

	    organization.editOrganizatioName("");
	    organization.saveEdit();

	    Assert.assertTrue(
	        organization.isErrorMessageDisplayed(),
	        "Required field message was not displayed"
	    );
	}
	


}
