package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
		super(driver, wait, js);
	}
	
	public WebElement getFirstName() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfoFrm']/div[1]/div[1]/fieldset/input"));
	}
	
	public WebElement getLastName() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfoFrm']/div[1]/div[2]/fieldset/input"));
	}
	
	public WebElement getAddress() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfoFrm']/div[2]/div[2]/fieldset/input"));
	}
	
	public WebElement getPhoneNumber() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfoFrm']/div[3]/div[1]/fieldset/input"));
	}
	
	public WebElement getZipCode() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfoFrm']/div[3]/div[2]/fieldset/input"));
	}
	
	public WebElement getCountry() {
		return this.driver.findElement(By.xpath("//*[@id='user_country_id']"));
	}
	
	public WebElement getState() {
		return this.driver.findElement(By.xpath("//*[@id='user_state_id']"));
	}
	
	public WebElement getCity() {
		return this.driver.findElement(By.xpath("//*[@id='user_city']"));
	}
	
	public WebElement getUploadPictureButton() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfo']/div/div[1]/div/a[1]"));
	}
	
	public WebElement getRemovePictureButton() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfo']/div/div[1]/div/a[2]"));
	}
	
	public WebElement getInputPicture() {
		js.executeScript("arguments[0].click();", this.getUploadPictureButton());
		return this.driver.findElement(By.xpath("//input[@type='file']"));
	}
	
}
