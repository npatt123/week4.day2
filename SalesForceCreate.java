package week4.Day2;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForceCreate {

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

		// Click on the New Dashboard option
		driver.findElement(By.xpath("//div[@title ='New Dashboard']")).click();
		Thread.sleep(10000);

		// Handle the frame

		WebElement iframe = driver.findElement(By.xpath("//iframe[@title='dashboard']"));
		Thread.sleep(10000);
		driver.switchTo().frame(iframe);

		// 6. Enter Name as 'Salesforce Automation by Your Name ' and Click on Create.
		driver.findElement(By.id("dashboardNameInput")).sendKeys("Salesforce Automation by Nalini");
		Thread.sleep(10000);
		driver.findElement(By.id("submitBtn")).click();

		// 7. Verify Dashboard name.
		Thread.sleep(30000);
		WebElement save = driver.findElement(By.xpath("//div[@class='slds-button-group']/button"));
		save.getText();
		Thread.sleep(30000);
		save.click();

		System.out.println("save button clicked");
		// capture the alert
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println(alertText);

		String pageTile = driver.getTitle();

		if (pageTile.contains("Salesforce Automation by Nalini")) {

			System.out.println("Dashboard name is correct");

		} else {
			System.out.println("Dashboard title is not correct");

		}
		driver.switchTo().defaultContent();
		driver.close();

	}

}
