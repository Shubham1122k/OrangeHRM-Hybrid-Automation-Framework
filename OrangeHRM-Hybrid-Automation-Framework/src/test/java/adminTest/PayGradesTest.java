package adminTest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import adminModule.JobTitlesPage;
import adminModule.PayGradesPage;
import baseTest.baseTest;
import loginModule.LogIn;
import utilities.ConfigReader;

public class PayGradesTest extends baseTest {
	
    private PayGradesPage payGrades;

    private final String gradeNamee      = "final123";
    private final int indx      		 	= 2;
    private final String minSalaryy      = "1200";
    private final String maxSalaryy      = "2400";

    @BeforeMethod
    public void initialize()  {
    	
        payGrades = new PayGradesPage(getDriver());
        loginAsAdmin();
        payGrades.clickPayGradesMenu();
    }


    @Test(	

    		description = "TC-18+19 Add valid pay grade + Add currency to pay grade")
	    public void addPayGrade() {
	       
    			
	        payGrades.addPayGrade(gradeNamee, indx, minSalaryy, maxSalaryy);
	
	        Assert.assertTrue(payGrades.waitForSuccessToast(),
	                "Pay Grade not saved");
	    }
    
    @Test(
    	    	
    	    description = "TC-02 Verify required field validation on Add Pay Grade"
    		)
    		public void checkRequiredFields() {

    	    payGrades.clickAddPayGradeBtn(); // Click Add on Pay Grades page

    	    payGrades.clickSave(); // Click Save without entering Name

    	    Assert.assertTrue(
    	        payGrades.isRequired(),
    	        "Required message is not displayed"
    	    );
    	}

    @Test(
            
            description = "TC-20 Maximum salary smaller than minimum salary"
        )
        public void verifyInvalidSalaryWarning() {

            String uniqueGrade = "InvalidSalary_" + System.currentTimeMillis();

            payGrades.clickAddPayGradeBtn();

            payGrades.typeName(uniqueGrade);
            payGrades.clickSave();

            payGrades.clickAddCurrency();

            payGrades.selectCurrency(2);
            payGrades.typeMinimumSalary("6000");
            payGrades.typeMaximumSalary("3000");

            payGrades.clickCurrencySave();

            Assert.assertTrue(
                    payGrades.isWarningDisplayed(
                            "Should be higher than Minimum Salary"
                    ),
                    "Invalid salary warning was not displayed"
            );
        }

    // Negative salary input   
    @Test(
            
            description = "TC-21 Negative salary values"
        )
        public void verifyNegativeSalaryWarning() {

            String uniqueGrade = "NegativeSalary_" + System.currentTimeMillis();

            payGrades.clickAddPayGradeBtn();

            payGrades.typeName(uniqueGrade);
            payGrades.clickSave();

            payGrades.clickAddCurrency();

            payGrades.selectCurrency(2);
            payGrades.typeMinimumSalary("-12");
            payGrades.typeMaximumSalary("-1");

            payGrades.clickCurrencySave();

            Assert.assertTrue(
                    payGrades.isWarningDisplayed(
                            "Should be a valid number (xxx.xx)"
                    ),
                    "Expected warning was not displayed for negative salary values"
            );
        }

    // Delete only one currency   
    @Test(
            
            description = "TC-22 Delete single currency"
        )
        public void deleteOneCurrency() {

            String uniqueGrade = "DeleteCurrency_" + System.currentTimeMillis();

            payGrades.clickAddPayGradeBtn();

            payGrades.typeName(uniqueGrade);
            payGrades.clickSave();

            payGrades.clickAddCurrency();

            payGrades.selectCurrency(2);
            payGrades.typeMinimumSalary("2000");
            payGrades.typeMaximumSalary("3000");
            payGrades.clickCurrencySave();

            payGrades.deleteByIndex(1);

            Assert.assertTrue(
                    payGrades.waitForSuccessToast(),
                    "Delete currency did not work"
            );
        }
    
    // Delete all currencies
    @Test(
          
            description = "TC-23 Delete all currencies"
        )
        public void deleteAllCurrencies() {

            String uniqueGrade = "DeleteCurrencies_" + System.currentTimeMillis();

            payGrades.clickAddPayGradeBtn();

            payGrades.typeName(uniqueGrade);
            payGrades.clickSave();

            payGrades.clickAddCurrency();

            payGrades.selectCurrency(2);
            payGrades.typeMinimumSalary("2000");
            payGrades.typeMaximumSalary("3000");
            payGrades.clickCurrencySave();

            payGrades.deleteAll();

            Assert.assertTrue(
                    payGrades.waitForSuccessToast(),
                    "Delete All Currencies did not work"
            );
        }
    
    
    
    
    // Edit currencyDetails   
    @Test(
          
            description = "TC-27 Edit Currency Min/Max values"
        )
        public void editCurrencyDetails() {

            String uniqueGrade = "EditCurrency_" + System.currentTimeMillis();

            payGrades.clickAddPayGradeBtn();

            payGrades.typeName(uniqueGrade);
            payGrades.clickSave();

            payGrades.clickAddCurrency();

            payGrades.selectCurrency(2);
            payGrades.typeMinimumSalary("2000");
            payGrades.typeMaximumSalary("3000");
            payGrades.clickCurrencySave();

            payGrades.editCurrencyDetails(
                    1,
                    "3500",
                    "6500"
            );

            Assert.assertTrue(
                    payGrades.waitForSuccessToast(),
                    "Success toast did not appear after editing currency"
            );
        }
    
    
    // Delete only one payGrade   
    @Test(description = "TC-24 Delete single Pay Grade")
    public void deleteOnePayGrade() {

        String uniqueGrade = "DeleteGrade_" + System.currentTimeMillis();

        payGrades.clickAddPayGradeBtn();
        payGrades.typeName(uniqueGrade);
        payGrades.clickSave();

        Assert.assertTrue(
            payGrades.waitForSuccessToast(),
            "Pay Grade creation failed"
        );

        // Go back to Pay Grades list
        payGrades.goToPayGradesList();

        // Verify record exists
        Assert.assertTrue(
            payGrades.isPayGradePresent(uniqueGrade),
            "Pay Grade was not created: " + uniqueGrade
        );

        // Delete
        payGrades.deletePayGrade(uniqueGrade);

        Assert.assertTrue(
            payGrades.waitForSuccessToast(),
            "Delete Pay Grade did not work"
        );
    }

    // Delete all payGrades    
    @Test(
            
            description = "TC-25 Delete all Pay Grades"
        )
        public void deleteAllPayGrades() {

            payGrades.deleteAll();

            Assert.assertTrue(
                    payGrades.waitForSuccessToast(),
                    "Delete All Pay Grades did not work"
            );
        }
    
    

}
