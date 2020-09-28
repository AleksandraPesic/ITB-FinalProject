package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LocationPopupPage;
import pages.NotificationSystemPage;

public class ProfileTest extends BasicTest {
	
	@Test
	public void loginFunctionality() {
		this.driver.navigate().to(this.baseUrl + "guest-user/login-form");
		
		
		NotificationSystemPage nsp = new NotificationSystemPage(this.driver, this.wait, this.js);
		LocationPopupPage lpp = new LocationPopupPage(this.driver, this.wait, this.js);
		
		lpp.closeDialog();
	}

}
