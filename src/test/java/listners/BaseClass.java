package listners;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseClass {

	@BeforeSuite
	public void suiteSetup() {
		System.out.println("@BeforeSuite");
	}
	
	@BeforeTest
	public void testSetup() {
		System.out.println("@BeforeSuite");
	}
	
	@BeforeClass
	public void classSetup() {
		System.out.println("@BeforeSuite");
	}
	
	@BeforeMethod
	public void methodSetup() {
		System.out.println("@BeforeSuite");
	}
	
	@AfterMethod
	public void methodTeardown() {
		System.out.println("@AfterMethod");
	}
	
	@AfterClass
	public void classTeardown() {
		System.out.println("@AfterClass");
	}
	
	@AfterTest
	public void testTeardown() {
		System.out.println("@AfterTest");
	}
	
	@AfterSuite
	public void suiteTeardown() {
		System.out.println("@AfterSuite");
	}
}
