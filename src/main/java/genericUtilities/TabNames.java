package genericUtilities;

/**
 * This enum stores all the tab names of vtiger application
 * 
 * @author pc
 */

public enum TabNames {
	ORGANIZATIONS("Acoounts"), CONTACTS("Contacts"), LEADS("Leads");

	private String tabName;

	private TabNames(String tabName) {
		this.tabName = tabName;
	}

	public String getTabName() {
		return tabName;
	}
}
