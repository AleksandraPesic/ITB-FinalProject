package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPopupPage extends BasicPage {

	public LocationPopupPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}
	
	public WebElement getSelectLocation() {
		return this.driver.findElement(By.xpath("//*[@id='header']/div[1]/div/div/div[1]/div/a"));
	}
	
	public WebElement getCloseButton() {
		return this.driver.findElement(By.xpath("//*[@id='location-popup']/div/div/div/div/a"));
	}
	
	public WebElement getKeyword() {
		return this.driver.findElement(By.xpath("//*[@id='locality_keyword']"));
	}
	
	public WebElement getLocationItem(String locationName) {
		return this.driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}
	
	public WebElement getLocationInput() {
		return this.driver.findElement(By.xpath("//*[@id='location_id']"));
	}
	
	public WebElement getSubmit() {
		return this.driver.findElement(By.xpath("//*[@name='btn_submit']"));
	}
	
	public void openPopupDialog() {
		this.getSelectLocation().click();
	}
	
	public void locationSet(String locationName) {
		this.getKeyword().click();
		this.getLocationItem(locationName).getAttribute("data-value");
//		treba da se doda drugi argument, iz fajla
		js.executeScript("arguments[0].value=arguments[1]", this.getLocationInput());
		js.executeScript("arguments[0].click();", this.getSubmit());
	}
	
	public void closeDialog() {
		this.getCloseButton().click();
	}

}
