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

public class SearchTest extends BasicTest {
	
	
	@Test (priority = 10)
	public void searchResultsTest() throws InterruptedException, IOException {
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
		sheet = wb.getSheet("Meal Search Results");
		
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			
			XSSFCell mealUrl = row.getCell(1);
			
			
			
			mp.addToCart(quantity);
			
			sa.assertTrue(nsp.successfulLoginMessage().contains("Meal Added To Cart"));
		}
		
		csp.emptyCart();
		
		sa.assertTrue(nsp.successfulLoginMessage().contains("All meals removed from Cart successfully"));
		
	}

}
