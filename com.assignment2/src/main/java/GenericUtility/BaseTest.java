package GenericUtility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	
	public WebDriver driver;
	public static WebDriver sDriver;

	
	@BeforeClass
	public void setupBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://uatfeedback.amrihospitals.in/over2cloud/signuppage.action");
		sDriver = driver;
	}
	
	@BeforeMethod
	public void login() {
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("SUPER@ADMIN");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SUPER@ADMIN");
		driver.findElement(By.xpath("//input[@class='btn btn-primary btn-block']")).click();
	}
	
	@AfterMethod
	public void logout() {
		driver.findElement(By.xpath("//a[@class='nav-link navbar-avatar']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
}
