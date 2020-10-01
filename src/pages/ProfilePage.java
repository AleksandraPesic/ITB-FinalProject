package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
	
	public void uploadProfilePicture() {	
		this.getInputPicture().sendKeys("C:\\ITB-FinalProject\\ITB-FinalProject\\images\\6a00e5520572bb8834017c3875ac22970b.jpg");
	}
	
	public void deleteProfilePicture() {
		js.executeScript("arguments[0].click();", this.getRemovePictureButton());
	}
	
	public WebElement getSaveButton() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfoFrm']/div[5]/div/fieldset/input"));
	}
	
	public void changeProfilePage(String firstName, String lastName, String address, String phone, String zipCode, String country, String state, String city) throws InterruptedException {
		this.getFirstName().clear();
		this.getFirstName().sendKeys(firstName);
		Thread.sleep(1000);
		this.getLastName().clear();
		this.getLastName().sendKeys(lastName);
		Thread.sleep(1000);
		this.getAddress().clear();
		this.getAddress().sendKeys(address);
		Thread.sleep(1000);
		this.getPhoneNumber().clear();
		this.getPhoneNumber().sendKeys(phone);
		Thread.sleep(1000);
		this.getZipCode().clear();
		this.getZipCode().sendKeys(zipCode);
		Thread.sleep(1000);
		Select s1 = new Select(this.getCountry());
		s1.selectByVisibleText(country);
		Thread.sleep(1000);
		Select s2 = new Select(this.getState());
		s2.selectByVisibleText(state);
		Thread.sleep(1000);
		Select s3 = new Select(this.getCity());
		s3.selectByVisibleText(city);
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", this.getSaveButton());
//		dodati sleep izmedju elemenata
	}
	
}
