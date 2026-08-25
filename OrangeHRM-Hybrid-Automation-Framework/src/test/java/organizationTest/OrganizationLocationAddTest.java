package organizationTest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseTest.TestBase;
import loginModule.LogIn;
import organizationModule.OrganizationLocations;

public class OrganizationLocationAddTest extends TestBase {
	private LogIn login;
	private OrganizationLocations Location;

	@BeforeMethod(alwaysRun = true)
	public void setUpPage() {
		
	    Location = new OrganizationLocations(getDriver());
	    loginAsAdmin();
	}


	@Test
	public void validAddTest() {
	    String locationName = "OntarioOttawa_" + System.currentTimeMillis();

	    Location.OpenLocationForm();
	    Location.AddName(locationName);
	    Location.addCountry("Canada");
	    Location.SaveAdd();

	    Assert.assertTrue(
	        Location.getSuccessMessage().contains("Successfully Saved")
	    );
	}

	@Test
	public void duplicateAddTest() {
		
		 String locationName = "OntarioOttawa_" + System.currentTimeMillis();

	    // First create OntarioOttawa
	    Location.OpenLocationForm();
	    Location.AddName("OntarioOttawa");
	    Location.addCountry("Canada");
	    Location.SaveAdd();

	    // Then attempt to create the same location
	    Location.OpenLocationForm();
	    Location.AddName("OntarioOttawa");
	    Location.addCountry("Canada");
	    Location.SaveAdd();

	    Assert.assertTrue(Location.isExistDisplayed());
	}

	@Test(priority = 1, description = "Verify Add invalid Location blank required fields")
	public void inValidAddTest() {
		Location.OpenLocationForm();
		Location.AddName("");
		Location.addCountry("");
		Location.SaveAdd();
		Assert.assertTrue(Location.isErrorMessageDisplayed());

	}
	



}
