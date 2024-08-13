package genericUtilityImplementationScripts;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import genericUtilities.DataType;
import genericUtilities.ExcelUtility;
import genericUtilities.IConstantPath;
import genericUtilities.JavaUtility;
import genericUtilities.PropertiesUtility;
import genericUtilities.WebDriverUtility;

public class CreateorganizationWithType_2 {

	public static void main(String[] args) throws InterruptedException {
		PropertiesUtility propertyUtil = new PropertiesUtility();
		ExcelUtility excel = new ExcelUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility driverUtil = new WebDriverUtility();

		propertyUtil.propertiesInit(IConstantPath.PROPERTIES_FILE_PATH);
		excel.excelInit(IConstantPath.EXCEL_PATH);

		WebDriver driver = driverUtil.launchBrowser(propertyUtil.readFromProperties("browser"));
		driverUtil.maximizeBrowser();
		driverUtil.navigateToApp(propertyUtil.readFromProperties("url"));

		long time = (Long) jutil.convertStringToAnyDataType(propertyUtil.readFromProperties("timeouts"), DataType.LONG);
		driverUtil.waitTillElementFound(time);

		// verifying the vtiger login page is displaying or not
		if (driver.getTitle().contains("vtiger CRM")) {
			System.out.println("Login is Displayed");
		} else {
			System.out.println("Login page is not Displayed");
			driverUtil.quitAllWindows();
		}

		// performing the login operation
		driver.findElement(By.name("user_name")).sendKeys(propertyUtil.readFromProperties("username"));
		driver.findElement(By.name("user_password")).sendKeys(propertyUtil.readFromProperties("password"));
		driver.findElement(By.id("submitButton")).submit();

		// verifying the vtiget home page is displayed or not
		if (driver.findElement(By.partialLinkText("Home")).isDisplayed()) {
			System.out.println("Home page is Displayed");
		} else {
			System.out.println("Home page not Displayed");
			driverUtil.quitAllWindows();
		}

		// moving to organization hover
		Actions act = new Actions(driver);
		WebElement org_ele = driver.findElement(By.xpath("(//a[text()='Organizations'])[1]"));
		// clicking on the organization hover
		act.moveToElement(org_ele).click().perform();

		if (driver.findElement(By.linkText("Organizations")).isDisplayed()) {
			System.out.println("Organization page is displayed");
		} else {
			System.out.println("Organization page is not Displayed");
			driverUtil.quitAllWindows();
		}

		// creating new organization
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// filling the form
		driver.findElement(By.name("accountname")).sendKeys("org_test");

		// handling select dropdown
		// identifying the select drop down element
		WebElement industry_drop = driver.findElement(By.name("industry"));
		// creating select instance
		Select sc = new Select(industry_drop);
		sc.selectByVisibleText("Manufacturing");

		// identifying the select drop down element
		WebElement accounttype_drop = driver.findElement(By.name("accounttype"));
		// creating select instance
		Select sc1 = new Select(accounttype_drop);
		sc1.selectByVisibleText("Customer");

		// clicking save button
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();

		WebElement org_inf_title = driver.findElement(By.xpath("//span[contains(text(),'Organization Information')]"));
		String title = org_inf_title.getText();

		// conforming the the title
		if (title.contains("org_test")) {
			System.out.println("organization is created successfully");
		} else {
			System.out.println("organization page is not displayed");
			driverUtil.quitAllWindows();
		}

		// deleting the created organization
		driver.findElement(By.xpath("(//input[@class='crmbutton small delete'])[1]")).click();

		// handling alert pop-up

		driver.switchTo().alert().accept();

		WebElement profile = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		act.moveToElement(profile).perform();
		WebElement signout = driver.findElement(By.linkText("Sign Out"));
		act.moveToElement(signout).click().perform();

		driver.quit();

	}
}
