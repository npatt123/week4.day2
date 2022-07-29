package week4.Day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditSalesForce {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		// Launch the url
		driver.get("https://login.salesforce.com/");
		driver.findElement(By.name("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.name("pw")).sendKeys("Password$123");
		driver.findElement(By.name("Login")).click();
		Thread.sleep(10000);

		// Click on the toggle menu button from the left corner

		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();

		// 3. Click View All and click Dashboards from App Launcher
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		Thread.sleep(10000);
		WebElement dB = driver.findElement(By.xpath("//p[text()='Dashboards']"));
		Thread.sleep(50000);
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView();", dB);
		Thread.sleep(50000);
		js.executeScript("arguments[0].click();", dB);
		
		
		//Search the Dashboard 'Salesforce Automation by Your Name'
		driver.findElement(By.xpath("//input[@class='search-text-field slds-input input uiInput uiInputText uiInput--default uiInput--input']")).sendKeys("Nalini");
		Thread.sleep(10000);
		driver.findElement(By.xpath("(//button[@class='slds-button slds-button_icon-border slds-button_icon-x-small'])[1]")).click();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//span[text()='Edit']")).click();
		Thread.sleep(20000);
		
		
		//handle frame
		WebElement iframe = driver.findElement(By.xpath("//iframe[@title='dashboard']"));
		Thread.sleep(10000);
		driver.switchTo().frame(iframe);
		

		//// 7.Click on the Edit Dashboard Properties icon
		
		driver.findElement(By.xpath("//button[@title='Edit Dashboard Properties']")).click();
		Thread.sleep(20000);
		
		//Enter Description as 'SalesForce' and click on save.
		
		driver.findElement(By.id("dashboardDescriptionInput")).clear();
		driver.findElement(By.id("dashboardDescriptionInput")).sendKeys("SalesForce");
		Thread.sleep(20000);
		driver.findElement(By.id("submitBtn")).click();
		
		
		//Click on Done and  Click on save in the popup window displaye
		driver.findElement(By.xpath("//button[text() ='Done']")).click();
		Thread.sleep(10000);
		driver.findElement(By.id("modalBtn2")).click();
		
		//verify the dashboard
		
		String textDesc = driver.findElement(By.xpath("//p[@class='slds-page-header__info']")).getText();
		
		if(textDesc.equals("SalesForce")) {
			System.out.println("Description matches");
		}
		else {
			System.out.println("Description doesnt match");
		}
		
		driver.close();
			
	
		
	}

}
