package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.AuthPage;
import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;

public class MealItemTest extends BasicTest {
	
	@Test (priority = 0)
	public void addMealToCartTest() throws InterruptedException {
		this.driver.navigate().to(this.baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		
		quantity = "5";
		locationName = "City Center - Albany";
		
		NotificationSystemPage nsp = new NotificationSystemPage(this.driver, this.wait, this.js);
		LocationPopupPage lpp = new LocationPopupPage(this.driver, this.wait, this.js);
		LoginPage lp = new LoginPage(this.driver, this.wait, this.js);
		ProfilePage pp = new ProfilePage(this.driver, this.wait, this.js);
		AuthPage ap = new AuthPage(this.driver, this.wait, this.js);
		MealPage mp = new MealPage(this.driver, this.wait, this.js);
		
		SoftAssert sa = new SoftAssert();
		
		lpp.closeDialog();
		
		mp.addToCart(quantity);
		
		sa.assertTrue(nsp.successfulLoginMessage().contains("The Following Errors Occurred: Please Select Location"));
		
		nsp.messageDisappear();
		
		lpp.openPopupDialog();
		lpp.locationSet(locationName);
	
		Thread.sleep(1000);
		mp.addToCart(quantity);
		
		sa.assertTrue(nsp.successfulLoginMessage().contains("Meal Added To Cart"));
		
	}
	
	@Test (priority = 5)
	public void addMealToFavouriteTest() throws InterruptedException, IOException {
		this.driver.navigate().to(this.baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		
		NotificationSystemPage nsp = new NotificationSystemPage(this.driver, this.wait, this.js);
		LocationPopupPage lpp = new LocationPopupPage(this.driver, this.wait, this.js);
		LoginPage lp = new LoginPage(this.driver, this.wait, this.js);
		ProfilePage pp = new ProfilePage(this.driver, this.wait, this.js);
		AuthPage ap = new AuthPage(this.driver, this.wait, this.js);
		MealPage mp = new MealPage(this.driver, this.wait, this.js);
		
		SoftAssert sa = new SoftAssert();
		
		lpp.closeDialog();
		
		mp.addToFavourite();
		
		sa.assertTrue(nsp.successfulLoginMessage().contains("Please login first!"));
		
		this.driver.navigate().to(this.baseUrl + "guest-user/login-form");
		
		lp.login(email, password);
		
		this.driver.navigate().to(this.baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		
		mp.addToFavourite();
		sa.assertTrue(nsp.successfulLoginMessage().contains("Product has been added to your favorites."));
		
	}
	
	@Test (priority = 10)
	public void clearCartTest() throws InterruptedException, IOException {
		this.driver.navigate().to(this.baseUrl + "meals");
		
		NotificationSystemPage nsp = new NotificationSystemPage(this.driver, this.wait, this.js);
		LocationPopupPage lpp = new LocationPopupPage(this.driver, this.wait, this.js);
		LoginPage lp = new LoginPage(this.driver, this.wait, this.js);
		ProfilePage pp = new ProfilePage(this.driver, this.wait, this.js);
		AuthPage ap = new AuthPage(this.driver, this.wait, this.js);
		MealPage mp = new MealPage(this.driver, this.wait, this.js);
		CartSummaryPage csp = new CartSummaryPage(this.driver, this.wait, this.js);
		
		SoftAssert sa = new SoftAssert();
		
		locationName = "City Center - Albany";
		
		lpp.locationSet(locationName);
		
		file = new File("data/Data.xlsx");
		fis = new FileInputStream(file);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet("Meals");
		
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			
			XSSFCell mealUrl = row.getCell(0);
			
			this.driver.navigate().to(mealUrl.getStringCellValue());
			
			mp.addToCart(quantity);
			
			sa.assertTrue(nsp.successfulLoginMessage().contains("Meal Added To Cart"), "[ERROR] Unexpected message");
		}
		
		csp.emptyCart();
		
		sa.assertTrue(nsp.successfulLoginMessage().contains("All meals removed from Cart successfully"), "[ERROR] Unexpected message");
		
		wb.close();
		fis.close();
		
	}

}
