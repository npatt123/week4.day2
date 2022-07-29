package week4.Day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InteractWithWindows {
	
	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("http://www.leafground.com/pages/Window.html");
		
		//get current window handles
		String currHandle = driver.getWindowHandle();		
		driver.findElement(By.id("home")).click();		
		driver.findElement(By.xpath("//button[text() ='Open Multiple Windows']")).click();
		//get all the window handles
		Set<String> handles = driver.getWindowHandles();			
		List<String> count = new ArrayList<String>(handles);
		System.out.println("count of current window open:  " + count.size());	
		
		driver.findElement(By.xpath("//button[contains(text(),'close')]")).click();
		
		driver.findElement(By.xpath("//button[contains(text(),'Wait')]")).click();
		Thread.sleep(10000);
		Set<String> handles2 = driver.getWindowHandles();		
		
		for(String eachHandle :handles2 ) {
			 handles.add(eachHandle);
			 }
		for(String eachHandle1 : handles) {
			
			if(!eachHandle1.contains(currHandle)) {
				driver.switchTo().window(eachHandle1);
				driver.close();				
				
			}
		}
			driver.switchTo().window(currHandle);
			System.out.println("The focus has been switched to the main window");		
			driver.close();
		
	}

	
}
