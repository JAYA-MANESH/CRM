package genericUtilities;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.beust.jcommander.Parameter;

import objectRepo.CreatingNewOrganizationPage;
import objectRepo.HomePage;
import objectRepo.LoginPage;
import objectRepo.OrganizationInformationPage;
import objectRepo.OrganizationsPage;
import objectRepo.PageObjectManager;

public class BaseClass {

	//@BeforeSuite
	//@BeforeTest
	protected PropertiesUtility propertyUtil;
	protected ExcelUtility excel;
	protected JavaUtility jutil;
	protected WebDriverUtility driverUtil;
	
	protected WebDriver driver;
	
	public static WebDriver sdriver;
	public static JavaUtility sjutil;
	
	
	protected PageObjectManager pageObjectManager;
	
	protected LoginPage login;
	protected HomePage home;
	
	protected SoftAssert soft;
//	protected OrganizationsPage organization;
//	protected CreatingNewOrganizationPage createOrg;
//	protected OrganizationInformationPage orgInfo;
//	@Parameters("BROWSER")
	@BeforeClass(groups="important")
//	public void classConfiguration(String browser) {
		public void classConfiguration(String browser) {
		propertyUtil = new PropertiesUtility();
		excel = new ExcelUtility();
		jutil = new JavaUtility();
		driverUtil = new WebDriverUtility();

		propertyUtil.propertiesInit(IConstantPath.PROPERTIES_FILE_PATH);
		excel.excelInit(IConstantPath.EXCEL_PATH);
		
//		driver = driverUtil.launchBrowser(browser);
		driver = driverUtil.launchBrowser(propertyUtil.readFromProperties("browser"));
		driverUtil.maximizeBrowser();
		long time = (Long) jutil.convertStringToAnyDataType(propertyUtil.readFromProperties("timeouts"), DataType.LONG);
		driverUtil.waitTillElementFound(time);
		
		sdriver=driver;
		sjutil=jutil;
	}
	
	@BeforeMethod
	public void methodConfiguration() {
		driverUtil.navigateToApp(propertyUtil.readFromProperties("url"));
		Assert.assertTrue(driver.getTitle().contains("vtiger CRM"));
		
		pageObjectManager=new PageObjectManager(driver);
//		login = new LoginPage(driver);
//		home = new HomePage(driver);
		
		login = pageObjectManager.getLogin();
		home = pageObjectManager.getHome();
//		organization = new OrganizationsPage(driver);
//		createOrg = new CreatingNewOrganizationPage(driver);
//		orgInfo = new OrganizationInformationPage(driver);
		
//		if (driver.getTitle().contains("vtiger CRM"))
//			System.out.println("Login Page Displayed");
//		else
//			driverUtil.quitAllWindows();

		login.loginToVtiger(propertyUtil.readFromProperties("username"), propertyUtil.readFromProperties("password"));

//		if (driver.getTitle().contains("Home"))
//			System.out.println("Home Page is Displayed");
//		else
//			driverUtil.quitAllWindows();
		
		Assert.assertTrue(driver.getTitle().contains("Home"));
		soft=new SoftAssert();
	}	
	
	@AfterMethod
	public void methodTeardown() {
		excel.saveExcel(IConstantPath.EXCEL_PATH);
		home.signOutOfVtiger(driverUtil);
	}
	
	@AfterClass(groups="important")
	public void classTeardown() {
		excel.closeExcel();
		driverUtil.quitAllWindows();
	}
	//@AfterTest
	//@AfterSuite
}