package additionalScenarios;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class FlipkartValidateTotalItemsTest {

	@Test
	public void validateTotalItemsTest() throws InterruptedException {

//		// --disable-infobars
//		// --start-maximized
//		// --disable-extensions
//		// --disable-popup-blocking
//		// --no-sandbox
//		// --disable-gpu
//		// --window-size=800,600
//		// --disable-dev-shm-usage
//		ChromeOptions option = new ChromeOptions();
//		option.addArguments("--disable-geolocation");
//		option.addArguments("--disable-notifications");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.name("q")).sendKeys("iphones");
		driver.findElement(By.className("_2iLD__")).click();

		int sum = 0;
		for (;;) {
			Thread.sleep(2000);
			List<WebElement> iphoneList = driver.findElements(By.className("KzDlHZ"));
			sum = sum + iphoneList.size();
			Thread.sleep(2000);
			try {
				WebElement nextBTN = driver.findElement(By.xpath("//span[text()='Next']"));
				nextBTN.click();
			} catch (NoSuchElementException e) {
				break;
			}
		}

		String header = driver.findElement(By.className("BUOuZu")).getText();
		String[] str = header.split(" ");
//		int totalIphone=Integer.parseInt(str[5]);
		int totalItems = 0;
		if (str[5].contains(",")) {
			String[] s = str[5].split(",");
			String items = s[0].concat(s[1]);
			totalItems = Integer.parseInt(items);
		} else
			totalItems = Integer.parseInt(str[5]);

		if (sum == totalItems)
			System.out.println("Total number of items matched");
		else
			System.out.println("Mis-matched");

		driver.quit();

	}

}
