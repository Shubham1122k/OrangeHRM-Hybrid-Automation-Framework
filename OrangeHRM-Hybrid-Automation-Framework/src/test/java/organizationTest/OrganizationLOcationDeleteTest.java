package organizationTest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseTest.TestBase;
import organizationModule.OrganizationLocations;
import organizationModule.OrganizationalLocationsDelete;

public class OrganizationLOcationDeleteTest extends TestBase {

    private OrganizationalLocationsDelete delete;
    private OrganizationLocations location;

    @BeforeMethod(alwaysRun = true)
    public void setUpPage() {
        delete = new OrganizationalLocationsDelete(getDriver());
        location = new OrganizationLocations(getDriver());
    }

    @Test(description = "Verify Delete record")
    public void deleteTest() {

        String locationName = "DeleteLocation_" + System.currentTimeMillis();

        // Create test data
        location.OpenLocationForm();
        location.AddName(locationName);
        location.addCountry("Canada");
        location.SaveAdd();

        // Open location list
        delete.OpenLocationForm();

        // Find OUR record and delete it
        delete.clickDeleteButtonByName(locationName);
        delete.ConfirmDelete();

        Assert.assertTrue(
            delete.getSuccessMessage().contains("Successfully Delete")
        );
    }

    @Test(description = "Verify Cancel Button")
    public void cancelTest() {

        String locationName = "CancelLocation_" + System.currentTimeMillis();

        // Add a unique location
        location.OpenLocationForm();
        location.AddName(locationName);
        location.addCountry("Canada");
        location.SaveAdd();

        // Open locations
        delete.OpenLocationForm();

        // Open delete confirmation
        delete.clickDeleteButtonByName(locationName);

        Assert.assertTrue(delete.isCancelButtonEnabled());

        delete.CancelDelete();
    }

    @Test(description = "Verify Delete multiple records")
    public void deleteMultipleTest() {

        String locationName1 =
                "MultipleLocation1_" + System.currentTimeMillis();

        String locationName2 =
                "MultipleLocation2_" + System.currentTimeMillis();

        // Add first location
        location.OpenLocationForm();
        location.AddName(locationName1);
        location.addCountry("Canada");
        location.SaveAdd();

        // Add second location
        location.OpenLocationForm();
        location.AddName(locationName2);
        location.addCountry("Canada");
        location.SaveAdd();

        // Open location list
        delete.OpenLocationForm();

        // Select our exact records
        delete.SelectRecordByName(locationName1);
        delete.SelectRecordByName(locationName2);

        // Delete selected
        delete.DeleteSelected();
        delete.ConfirmDelete();

        Assert.assertTrue(
            delete.getSuccessMessage().contains("Successfully Delete")
        );
    }

    @Test(description = "Verify Add valid Location")
    public void validAddTest() {

        String locationName =
                "Hanaa_" + System.currentTimeMillis();

        location.OpenLocationForm();
        location.AddName(locationName);
        location.addCountry("Canada");
        location.SaveAdd();

        Assert.assertTrue(
            location.getSuccessMessage().contains("Successfully Saved")
        );
    }

    @Test(description = "Verify Add valid Location 2")
    public void validAddTest2() {

        String locationName =
                "Hanaa_" + System.currentTimeMillis();

        location.OpenLocationForm();
        location.AddName(locationName);
        location.addCountry("Canada");
        location.SaveAdd();

        Assert.assertTrue(
            location.getSuccessMessage().contains("Successfully Saved")
        );
    }
}