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
import pages.SearchResultPage;

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
		SearchResultPage srp = new SearchResultPage(this.driver, this.wait, this.js);
		
		SoftAssert sa = new SoftAssert();
		
		locationName = "City Center - Albany";
		
		lpp.locationSet(locationName);
		
		file = new File("data/Data.xlsx");
		fis = new FileInputStream(file);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet("Meal Search Results");
		
		for (int i = 1; i <= 6; i++) {
			XSSFRow row = sheet.getRow(i);
			
			XSSFCell location = row.getCell(0);
			XSSFCell mealUrl = row.getCell(1);
			XSSFCell numberOfResults = row.getCell(2);
			
			this.driver.navigate().to(mealUrl.getStringCellValue());
			
			lpp.openPopupDialog();
			lpp.locationSet(location.getStringCellValue());
			
			sa.assertEquals(srp.numberOfSearchResults(), numberOfResults.getNumericCellValue());
			
			for (int j = 3; j < 2 + numberOfResults.getNumericCellValue(); j++) {
				if (row.getCell(j) != null) {
					String expectedProductName = row.getCell(j).getStringCellValue();
					String actualProductName = srp.nameOfAllMeals().get(j-3);
					sa.assertTrue(expectedProductName.contains(actualProductName), "[ERROR] Search result order is not the same");
				}
			}
			
			wb.close();
			fis.close();
		}
		
	}

}
