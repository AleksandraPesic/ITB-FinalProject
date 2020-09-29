package tests;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.AuthPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;

public class ProfileTest extends BasicTest {
	
	@Test (priority = 0)
	public void editProfileTest() throws InterruptedException {
		this.driver.navigate().to(this.baseUrl + "guest-user/login-form");
		firstName = "Aleksandra";
		lastName = "Pesic";
		address = "Nis";
		phone = "345";
		zipCode = "18000";
		country = "United Kingdom";
		state = "Bristol";
		city = "Avon";
		
		NotificationSystemPage nsp = new NotificationSystemPage(this.driver, this.wait, this.js);
		LocationPopupPage lpp = new LocationPopupPage(this.driver, this.wait, this.js);
		LoginPage lp = new LoginPage(this.driver, this.wait, this.js);
		ProfilePage pp = new ProfilePage(this.driver, this.wait, this.js);
		AuthPage ap = new AuthPage(this.driver, this.wait, this.js);
		
		SoftAssert sa = new SoftAssert();
		
		lpp.closeDialog();
		
		lp.login(email, password);
		sa.assertTrue(nsp.successfulLoginMessage().contains("Login Successfull"));
		
		this.driver.navigate().to(this.baseUrl + "member/profile");
		
		pp.changeProfilePage(firstName, lastName, address, phone, zipCode, country, state, city);
		sa.assertTrue(nsp.successfulLoginMessage().contains("Setup Successful"));
		Thread.sleep(2000);
		ap.logOut();
		sa.assertTrue(nsp.successfulLoginMessage().contains("Logout Successfull!"));
		
	}
	
	@Test (priority = 5)
	public void changeProfileImageTest() throws InterruptedException, IOException {
		this.driver.navigate().to(this.baseUrl + "guest-user/login-form");
		
		NotificationSystemPage nsp = new NotificationSystemPage(this.driver, this.wait, this.js);
		LocationPopupPage lpp = new LocationPopupPage(this.driver, this.wait, this.js);
		LoginPage lp = new LoginPage(this.driver, this.wait, this.js);
		ProfilePage pp = new ProfilePage(this.driver, this.wait, this.js);
		AuthPage ap = new AuthPage(this.driver, this.wait, this.js);
		
		SoftAssert sa = new SoftAssert();
		
		lpp.closeDialog();
		
		lp.login(email, password);
		sa.assertTrue(nsp.successfulLoginMessage().contains("Login Successfull"));
		
		this.driver.navigate().to(this.baseUrl + "member/profile");
		
		pp.uploadProfilePicture();
		sa.assertTrue(nsp.successfulLoginMessage().contains("Profile Image Uploaded Successfully"));
		
		nsp.messageDisappear();
		
		pp.deleteProfilePicture();
		
		sa.assertTrue(nsp.successfulLoginMessage().contains("Profile Image Deleted Successfully"));
		
		nsp.messageDisappear();
		
		ap.logOut();
		
		sa.assertTrue(nsp.successfulLoginMessage().contains("Logout Successfull!"));
		
	
	}

}
