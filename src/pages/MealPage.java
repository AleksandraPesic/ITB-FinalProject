package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}
	
	public WebElement getQuantity() {
		return this.driver.findElement(By.name("product_qty"));
	}
	
	public WebElement getAddToCartButton() {
		return this.driver.findElement(By.xpath("//*[@id='body']/section[1]/div/div/div[2]/div/div[3]/div[2]/a"));
	}
	
	public WebElement getFavouriteButton() {
		return this.driver.findElement(By.xpath("//*[@id='item_42']"));
	}
	
	public void addToCart(String quantity) {
		this.getQuantity().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		this.getQuantity().sendKeys(quantity);
		this.getAddToCartButton().click();
	}
	
	public void addToFavourite() {
		this.getAddToCartButton().click();
	}

}
