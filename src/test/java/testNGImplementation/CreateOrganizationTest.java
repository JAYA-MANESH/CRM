package testNGImplementation;

import java.util.Map;

import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import genericUtilities.TabNames;
import objectRepo.CreatingNewOrganizationPage;
import objectRepo.OrganizationInformationPage;
import objectRepo.OrganizationsPage;

public class CreateOrganizationTest extends BaseClass {

	@Test(groups = "organization")
	public void createOrgTest() {
		OrganizationsPage organization = pageObjectManager.getOrganization();
		CreatingNewOrganizationPage createOrg = pageObjectManager.getCreateOrg();
		OrganizationInformationPage orgInfo = pageObjectManager.getOrgInfo();
		
		home.clickRequiredTab(driverUtil, TabNames.ORGANIZATIONS);

		if (driver.getTitle().contains("Organizations"))
			System.out.println("Organizations Page is Displayed");
		else
			driverUtil.quitAllWindows();

		organization.ClickCreateOrgBTN();

		if (createOrg.getPageHeader().equalsIgnoreCase("creating new organization"))
			System.out.println("Creating New Organization Page is Displayed");
		else
			driverUtil.quitAllWindows();

		Map<String, String> map = excel.readFromExcel("OrganizationsTestData", "Create Organization");
		String orgName=map.get("Organization Name")+jutil.generateRandomNum(100);
		createOrg.setOrganizationName(orgName);

		createOrg.setOrganizationName(map.get("Organization Name"));
		createOrg.clickSaveBTN();

		if (orgInfo.getPageHeader().contains(map.get("Organization Name")))
			System.out.println("Organization created successfully");
		else
			driverUtil.quitAllWindows();

		orgInfo.clickDeleteBTN();
		driverUtil.handleAlert("ok");

		if (driver.getTitle().contains("Organizations")) {
			System.out.println("Organizations Page is Displayed");
			excel.writeToExcel("OrganizationsTestData", "Create Organization", "Pass");
		} else {
			driverUtil.quitAllWindows();
			excel.writeToExcel("OrganizationsTestData", "Create Organization", "Fail");
		}
	}

}