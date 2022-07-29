package week4.Day2;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandling {
	
	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("http://leaftaps.com/opentaps/control/login");
		WebElement userName = driver.findElement(By.id("username"));
		userName.sendKeys("Demosalesmanager");
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("crmsfa");
		WebElement clickButton = driver.findElement(By.className("decorativeSubmit"));
		clickButton.click();
		WebElement clickLeads = driver.findElement(By.linkText("CRM/SFA"));
		clickLeads.click();
		WebElement clickContacts = driver.findElement(By.linkText("Contacts"));
		clickContacts.click();
		WebElement clickMergeContacts = driver.findElement(By.linkText("Merge Contacts"));
		clickMergeContacts.click();
		//get current window handle
		String currentindowHandle = driver.getWindowHandle();
		WebElement clickMergeContactsFrom = driver.findElement(By.xpath("(//table[@class='twoColumnForm']//tr//a)[1]"));
		clickMergeContactsFrom.click();
		//get the window handles
		Set<String> newwindowHandles = driver.getWindowHandles();
		for(String eachWindowHandle : newwindowHandles) {
			if(!eachWindowHandle.contains(currentindowHandle)) {
				
				driver.switchTo().window(eachWindowHandle);
			}
			else {
				System.out.println("No New window Open");
			}
		}
		WebElement clickFirstRow = driver.findElement(By.xpath("(//a[@class='linktext'])[1]"));
		clickFirstRow.click();
		driver.switchTo().window(currentindowHandle);						
	
		WebElement clickMergeContactsTo = driver.findElement(By.xpath("(//table[@class='twoColumnForm']//tr//a)[2]"));
		clickMergeContactsTo.click();
		
		//get the window handles
		Set<String> newwindowHandles1 = driver.getWindowHandles();
		for(String eachWindowHandle : newwindowHandles1) {
			if(!eachWindowHandle.contains(currentindowHandle)) {
				
				driver.switchTo().window(eachWindowHandle);
			}
			else {
				System.out.println("No New window Open");
			}
		}
		WebElement clickFirstRow1 = driver.findElement(By.xpath("(//div[@class= 'x-grid3-body']//div[2]//a)[1]"));
		Thread.sleep(30000);
		clickFirstRow1.click();	
		driver.switchTo().window(currentindowHandle);
		
		
		WebElement mergeButton = driver.findElement(By.linkText("Merge"));
		mergeButton.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(30000);
		String input1 = "View Contact | opentaps CRM";
		if(driver.getTitle().equals(input1)){
			System.out.println("Merge action completed");
			
		}
		driver.quit();
	}

}
