package pimModule;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import basePage.BasePage;

public class EmployeeListPage extends BasePage{

	private By pimElement = By.xpath("//span[text()='PIM']");
	private By addEmployees = By.xpath("//a[normalize-space()='Add Employee']");
	private By firstEmployeeName = By.xpath("//input[@placeholder='First Name']");
	private By lastEmployeeName = By.xpath("//input[@placeholder='Last Name']");
	private By saveEmployeeButton = By.xpath("//button[@type='submit']");
	private By personalDetailsTitle = By.xpath("//h6[normalize-space()='Personal Details']");
	private By cancleButton = By.xpath("//button[text()=\" Cancel \"]");
	private By employeeListInformation = By.xpath("//h5[text()=\"Employee Information\"]");
	private final By loader = By.xpath("//div[contains(@class,'oxd-form-loader')]");
			
	
    // Constructor
	public EmployeeListPage(WebDriver driver) {
		super(driver);
	}
    // OpenAddEmployee
	public void openAddEmployeeForm() {
        click(pimElement);
        click(addEmployees);
    }

    // Enter employee details
	public void addEmployee(String firstName, String lastName) {
		 type(firstEmployeeName, firstName);
	     type(lastEmployeeName, lastName);
	}

	// Save employee
	public void saveEmployee() {
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
	        click(saveEmployeeButton);
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));   
	}

	// To get personalDetailsTitle
	public String getPersonalDetailsTitle() {
		return getText(personalDetailsTitle);
	}
	
	// Check if a specific error message is displayed
	public boolean isErrorDisplayed(String errorText) {
		By errorLocator = By.xpath("//span[text()='" + errorText + "']");
        return isDisplayed(errorLocator);
	}
	
    // Click cancle btn
	public void cancelAddEmployee() {
		click(cancleButton);
	}

	// Verify Employee List page
	public boolean isEmployeeListPageDisplayed() {
		return isDisplayed(employeeListInformation);
	}
}
