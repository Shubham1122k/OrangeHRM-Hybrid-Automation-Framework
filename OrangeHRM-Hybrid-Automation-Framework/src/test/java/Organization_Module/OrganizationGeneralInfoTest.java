package Organization_Module;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import QABootcamp_Maven.OrangeHRM.TestBase;

public class OrganizationGeneralInfoTest extends TestBase {
	private OrganizationGeneralInfo Organization;

	@BeforeClass
	public void setUpPage() {
		Organization = new OrganizationGeneralInfo(getDriver());
		
	}



	@Test(priority = 4, description = "Verify edit organizationName")
	public void OrganizationNameTest() {
		Organization.openGeneralInformationForm();
		Organization.editOrganizatioName("AXSOS2");
		Organization.saveEdit();
		Assert.assertTrue(Organization.getSuccessMessage().contains("Successfully Updated"));

	}
	@Test(priority = 3, description = "Verify invalid phone Number")
	public void InvalidPhoneNumberTest() {
		Organization.openGeneralInformationForm();
		Organization.editNote("phone");
		Organization.saveEdit();
		Assert.assertTrue(Organization.isinvalidPhoneFormat());

	}
	
	@Test(priority = 2, description = "Verify invalidEmail")
	public void InvalidEmailTest() {
		Organization.openGeneralInformationForm();
		Organization.editEmail("test222");
		Organization.saveEdit();
		Assert.assertTrue(Organization.isinvalidFormatDisplayed());

	}
	@Test(priority = 1, description = "Verify admin can edit general information by leave required field empty")
	public void InvalidBlankEditTest() {
		Organization.openGeneralInformationForm();
		Organization.editOrganizatioName("");
		Organization.saveEdit();
		Assert.assertTrue(Organization.isErrorMessageDisplayed());

	}
	


}
