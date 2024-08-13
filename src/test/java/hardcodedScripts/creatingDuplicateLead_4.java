package hardcodedScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class creatingDuplicateLead_4 {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// verifying the login page is displayed or not
		if (driver.getTitle().contains("vtiger CRM")) {
			System.out.println("Login page Displayed");
		} else {
			System.out.println("Login page is Not Displayed");
			driver.quit();
		}
		// performing the login operation on the page
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).submit();

		// verifying the home page is displayed or not
		if (driver.findElement(By.partialLinkText("Home")).isDisplayed()) {
			System.out.println("Home page is Displayed");
		} else {
			System.out.println("Home page not Displayed");
			driver.quit();
		}

		// clicking on the lead
		driver.findElement(By.xpath("(//a[text()='Leads'])[1]")).click();

		// verifying the lead page is displayed or not
		if (driver.findElement(By.partialLinkText("Leads")).isDisplayed()) {
			System.out.println("Leads page is Displayed");
		} else {
			System.out.println("Leads page not Displayed");
			driver.quit();
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
			driver.quit();
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
