package Organization_Module;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;

public class LocationSearchReset extends BasePage  {
	

	private By AdminElement = By
			.xpath("//a//span[text()=\"Admin\"]");

	private By OrganizationButton = By.xpath("//li[contains(@class,'oxd-topbar-body-nav-tab')][.//span[normalize-space()='Organization']]");
	private By LocationButton = By.xpath("//a[normalize-space()='Locations']");
	private By Name    = By.xpath("//label[normalize-space()='Name']/ancestor::div[contains(@class,'oxd-input-group')]//input");
	private By City    = By.xpath("//label[normalize-space()='City']/ancestor::div[contains(@class,'oxd-input-group')]//input");
	private By Country = By.xpath("//label[normalize-space()='Country']/ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@class,'oxd-select-text')]");

	private By Search = By.xpath("//button[text()=' Search ']");
	private By Recordrows = By.cssSelector("div.oxd-table-body div.oxd-table-card");
	  
	private By cells = By.cssSelector("div[role='cell']");
	private By Reset = By.xpath("//button[text()=' Reset ']");
	private By NoRecords = By.xpath("//span[text()='No Records Found']");

	public LocationSearchReset(WebDriver driver) {
		super(driver);
	}

	public void OpenLocationForm() {
		click(AdminElement);
		click(OrganizationButton);
		click(LocationButton);
		
	}

	public void SearchByName(String name) {
		
		type(Name, name);
	}

	public void SearchByCity(String city) {
		
		type(City, city);

	}

	public void SearchByCountry(String country) {
		  WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(Country));
		    dropdown.click();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> options = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='option']")));
		for (WebElement option : options) {
			if (option.getText().equals(country)) {
				option.click();
				break;
			}
		}

	}

	public void SearchButton() {
		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(Search));
		search.click();

	}

	public List<WebElement> getRowCells() {
		List<WebElement> rows = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(Recordrows, 0));
		return rows.get(0).findElements(cells);
	}

	public void ResetButton() {
		WebElement reset = wait.until(ExpectedConditions.visibilityOfElementLocated(Reset));
		reset.click();
	}
	public boolean isResetButtonEnabled() {
	    WebElement reset = wait.until(ExpectedConditions.visibilityOfElementLocated(Reset));
	    return reset.isEnabled();
	}
	public boolean isNoRecoredFoundDisplayed() {
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(NoRecords));
		return message.isDisplayed();
	}

}
