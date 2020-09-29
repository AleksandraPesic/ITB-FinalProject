package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasicPage {

	public AuthPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}
	
	public WebElement getAccountOptions() {
		return this.driver.findElement(By.xpath("//li[@class='filled ']/a"));
	}
	
	public WebElement getLogout() {
		return this.driver.findElement(By.xpath("//div[@class='my-account-dropdown']/ul/li[2]/a"));
	}
	
	public void logOut() {
		this.getAccountOptions().click();
		this.getLogout().click();
	}

}
