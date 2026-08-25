package organizationTest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseTest.TestBase;
import loginModule.LogIn;
import organizationModule.OrganizationGeneralInfo;

public class OrganizationGeneralInfoTest extends TestBase {
	
	private LogIn login;
	private OrganizationGeneralInfo organization;

	@BeforeMethod(alwaysRun = true)
	public void setUpPage() {
		
	    organization = new OrganizationGeneralInfo(getDriver());
	    loginAsAdmin();
	    
	    
	}

	@Test(description = "Verify edit organizationName")
	public void OrganizationNameTest() {
		organization.openGeneralInformationForm();
		organization.editOrganizatioName("AXSOS2");
		organization.saveEdit();
		Assert.assertTrue(organization.getSuccessMessage().contains("Successfully Updated"));

	}
	@Test(description = "Verify invalid phone Number")
	public void InvalidPhoneNumberTest() {

	    organization.openGeneralInformationForm();

	    organization.editPhone("phone");

	    organization.saveEdit();

	    Assert.assertTrue(
	        organization.isinvalidPhoneFormat()
	    );
	}
	
	@Test(description = "Verify invalidEmail")
	public void InvalidEmailTest() throws InterruptedException {

		organization.openGeneralInformationForm();

		organization.editEmail("abc");

		organization.saveEdit();

		Assert.assertTrue(
		        organization.isinvalidFormatDisplayed(),
		        "Invalid email error message was not displayed"
		);
	}
	
	@Test(description = "Verify admin can edit general information by leave required field empty")
	public void InvalidBlankEditTest() {
		organization.openGeneralInformationForm();
		organization.editOrganizatioName("");
		organization.saveEdit();
		Assert.assertTrue(organization.isErrorMessageDisplayed());

	}
	


}
