package testScript;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import GenericUtility.BaseTest;
import GenericUtility.ListenerImplementation;

@Listeners(GenericUtility.ListenerImplementation.class)
public class AmriHospitalTest extends BaseTest {
	
	String communicationName = "Prince";
	String newName = "Prince Kumar";
	
	@Test
	public void addContactTest() throws InterruptedException {
		driver.findElement(By.xpath("//li[@class='site-menu-item has-sub']//a[@class='mm-next']")).click();
		driver.findElement(By.xpath("//span[@class='site-menu-title'][normalize-space()='Add Contact']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//i[@class='icon zmdi zmdi-plus']")).click();
		Thread.sleep(2000);
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='org_level']"));
		
		Select sel = new Select(dropdown);
		sel.selectByVisibleText("Country");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='emp_id']")).sendKeys("Prin123");
		driver.findElement(By.xpath("//input[@id='comm_name']")).sendKeys(communicationName);
		Thread.sleep(2000);
		WebElement orgDropdown = driver.findElement(By.xpath("//select[@id='org_id']"));
		Select sel1 = new Select(orgDropdown);
		sel1.selectByVisibleText("AMRI Hospitals");
		Thread.sleep(2000);
		WebElement deptDropdown = driver.findElement(By.xpath("//select[@id='contact_sub_type']"));
		Select sel2 = new Select(deptDropdown);
		sel2.selectByVisibleText("Management");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='emp_name']")).sendKeys(communicationName);
		driver.findElement(By.xpath("//input[@id='mobile_no']")).sendKeys("6206005310");
	//	driver.findElement(By.xpath("//input[@id='userCreation']")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[normalize-space()='Back']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Search...']")).sendKeys(communicationName);
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(By.xpath("//td[3]")).getText(), communicationName);
		ListenerImplementation.test.log(LogStatus.PASS, "User Added Successfully....");
		
	}
	
	@Test(dependsOnMethods = "addContactTest")
	public void editContactTest() throws InterruptedException {
		driver.findElement(By.xpath("//li[@class='site-menu-item has-sub']//a[@class='mm-next']")).click();
		driver.findElement(By.xpath("//span[@class='site-menu-title'][normalize-space()='Add Contact']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@placeholder='Search...']")).sendKeys(communicationName);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id='example_filter']//button[@type='submit']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='checkId']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@id='editButton']")).click();
		driver.findElement(By.xpath("//input[@id='emp_name']")).clear();
		driver.findElement(By.xpath("//input[@id='emp_name']")).sendKeys(newName);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='comm_name']")).clear();
		//driver.findElement(By.xpath("//input[@id='comm_name']")).sendKeys(newName);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[normalize-space()='Update']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[normalize-space()='Back']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Search...']")).sendKeys(newName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[3]")).getText(), newName);
		ListenerImplementation.test.log(LogStatus.PASS, "User Updated Successfully....");
		
	}
	
	@Test(dependsOnMethods = {"addContactTest","editContactTest"})
	public void deleteContactTest() throws InterruptedException {
		driver.findElement(By.xpath("//li[@class='site-menu-item has-sub']//a[@class='mm-next']")).click();
		driver.findElement(By.xpath("//span[@class='site-menu-title'][normalize-space()='Add Contact']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search...']")).sendKeys(newName);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='example_filter']//button[@type='submit']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='checkId']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//i[@class='icon zmdi zmdi-delete']")).click();
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@placeholder='Search...']")).sendKeys(newName);
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(By.xpath("//td[@class='dataTables_empty']")).getText(), "No matching records found");
		ListenerImplementation.test.log(LogStatus.PASS, "User Deleted Successfully....");
	}

}
