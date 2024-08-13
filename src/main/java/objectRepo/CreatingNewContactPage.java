package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class CreatingNewContactPage {
	
	//Declaration
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement pageHeader;
	
	@FindBy(name = "lastname")
	private WebElement contactlastNameTF;
	
	@FindBy(xpath = "//input[contains(@title,'Scve')]")
	private WebElement saveBTN;
	
	@FindBy(xpath="//img[Contains(@onClick,'Accounts')]")
	private WebElement organizationPlusBTN;
	
	
	private String organizationPath="//a[text()='%s']";
	
	//initialization
	public CreatingNewContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//utilization
	
	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	/**
	 * This method sets the organization name into the organization Name text field
	 * @param name
	 */
	public void setContactlastName(String name) {
		contactlastNameTF.sendKeys(name);
	}
	
	
	/**
	 * This method clicks on save button
	 */
	public void clickSaveBTN() {
		saveBTN.click();
	}
	
	public void selectExistingOrganization(WebDriverUtility driverUtil,String orgName) {
		organizationPlusBTN.click();
		driverUtil.switchToWindow("Accounts");
		driverUtil.convertDynamicXpathToWebElement(organizationPath, orgName).click();
		driverUtil.switchToWindow("Contacts");
	}

}
