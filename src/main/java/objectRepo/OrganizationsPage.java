package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contains elements, locators and respective business libraries of
 * organization
 * 
 * @author pc
 */
public class OrganizationsPage {

	// Declaration
	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement createOrgBTN;

	// initialization
	public OrganizationsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// utilization

	/**
	 * This method clicks on the create organization button
	 */
	public void ClickCreateOrgBTN() {
		createOrgBTN.click();
	}

}
