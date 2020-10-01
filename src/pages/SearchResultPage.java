package pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hpsf.Array;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends BasicPage {

	public SearchResultPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}
	
	public List<WebElement> getSearchResults() {
		return this.driver.findElements(By.xpath("//div[@class='product-content']/div[2]/a"));
	}
	
	public ArrayList<String> nameOfAllMeals() {
		ArrayList<String> names = new ArrayList<>();
		for (int i = 0; i < this.getSearchResults().size(); i++) {
			names.add(this.getSearchResults().get(i).getText());
		}
		return names;
	}
	
	public int numberOfSearchResults() {
		return this.getSearchResults().size();
	}
	

}
