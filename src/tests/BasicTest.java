package tests;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.joda.time.DateTime;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import pages.LocationPopupPage;
import pages.LoginPage;
import pages.NotificationSystemPage;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

public abstract class BasicTest {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Screenshot screenshot;
	protected JavascriptExecutor js;
	protected String baseUrl = "http://demo.yo-meals.com/";
	protected String email = "customer@dummyid.com";
	protected String password = "12345678a";
	protected String firstName;
	protected String lastName;
	protected String address;
	protected String phone;
	protected String zipCode;
	protected String country;
	protected String state;
	protected String city;
	
	@BeforeClass
	public void beforeClass() throws IOException {
		System.setProperty("webdriver.chrome.driver",
				"driver-lib\\chromedriver.exe");
		
		this.driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		this.wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		screenshot = new AShot().takeScreenshot(driver);
		js = (JavascriptExecutor)driver;
		
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			ImageIO.write(screenshot.getImage(), "png", new File("C:\\Users\\Aleksandra\\eclipse-workspace\\Selenium\\screenshot\\" + DateTime.now().toString("yyyy-dd-M--HH-mm-ss") + ".png"));
		}
		this.driver.manage().deleteAllCookies();
	}
	
	@AfterClass
	public void afterClass() throws IOException {
		this.driver.close();
	}

}
