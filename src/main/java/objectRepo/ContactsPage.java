package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {

	/**
	 * This class contains elements, locators and respective business libraries of
	 * contacts page
	 * 
	 * @author pc
	 */
	// Declaration
	@FindBy(xpath = "//img[@alt='Create Contact...']")
	private WebElement createContactBTN;

	// initialization
	public ContactsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// utilization

	/**
	 * This method clicks on the create contact button
	 */
	public void ClickCreateContactBTN() {
		createContactBTN.click();
	}

}
