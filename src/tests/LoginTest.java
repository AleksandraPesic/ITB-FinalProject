package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BasicTest {
	
	@Test
	public void loginFunctionality() {
		this.driver.navigate().to(this.baseUrl + "/my-account/");
		
		Assert.assertTrue(false, "Ovde je bila greska.");
		System.out.println("");
	}

}
