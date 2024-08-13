package genericUtilityImplementationScripts;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import genericUtilities.DataType;
import genericUtilities.ExcelUtility;
import genericUtilities.IConstantPath;
import genericUtilities.JavaUtility;
import genericUtilities.PropertiesUtility;
import genericUtilities.WebDriverUtility;

public class creatingDuplicateLead_4 {

	public static void main(String[] args) {
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

		// verifying the login page is displayed or not
		if (driver.getTitle().contains("vtiger CRM")) {
			System.out.println("Login page Displayed");
		} else {
			System.out.println("Login page is Not Displayed");
			driver.quit();
		}
		// performing the login operation on the page
		driver.findElement(By.name("user_name")).sendKeys(propertyUtil.readFromProperties("username"));
		driver.findElement(By.name("user_password")).sendKeys(propertyUtil.readFromProperties("password"));
		driver.findElement(By.id("submitButton")).submit();

		// verifying the home page is displayed or not
		if (driver.findElement(By.partialLinkText("Home")).isDisplayed()) {
			System.out.println("Home page is Displayed");
		} else {
			System.out.println("Home page not Displayed");
			driverUtil.quitAllWindows();
		}

		// clicking on the lead
		driver.findElement(By.xpath("(//a[text()='Leads'])[1]")).click();

		// verifying the lead page is displayed or not
		if (driver.findElement(By.partialLinkText("Leads")).isDisplayed()) {
			System.out.println("Leads page is Displayed");
		} else {
			System.out.println("Leads page not Displayed");
			driverUtil.quitAllWindows();
		}

		// clicking on the new create lead option
		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		// entering the lastname
		driver.findElement(By.name("lastname")).sendKeys("bharat");
		// entering the company name
		driver.findElement(By.name("company")).sendKeys("Tcs com");

		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();

		WebElement title_ver = driver.findElement(By.xpath("//span[contains(text(),'Lead Information')]"));
		String title_verifying = title_ver.getText();

		if (title_verifying.contains("bharat")) {
			System.out.println("Leads information is created successfully");
		} else {
			System.out.println("Leads information is not created successfully");
			driverUtil.quitAllWindows();
		}

		// clicking on the duplicate button
		driver.findElement(By.name("Duplicate")).click();

		String duplicate_title = driver.findElement(By.xpath("//span[contains(text(),'Duplicating')]")).getText();

		if (duplicate_title.contains("bharat")) {
			System.out.println("duplicate information page is displayed");
		} else {
			System.out.println("duplicate information page is not displayed");
		}

		// clicking save button
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();

		// clicking on the lead
		driver.findElement(By.xpath("(//a[text()='Leads'])[1]")).click();
        
		//performing logout operation
		Actions act = new Actions(driver);
		WebElement profile = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		act.moveToElement(profile).perform();
		WebElement signout = driver.findElement(By.linkText("Sign Out"));
		act.moveToElement(signout).click().perform();

		driver.quit();

	}

}
