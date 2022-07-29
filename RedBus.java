package week4.Day2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RedBus {

	public static void main(String[] args) throws ParseException, InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		// Launch the url
		driver.get("https://www.redbus.in/");	


		// Enter From -Madiwala Bangalore
		driver.findElement(By.xpath("//input[@id='src']")).sendKeys("Madiwala Bangalore");
		Thread.sleep(5000);

		// Enter To Koyambedu Chennai
		driver.findElement(By.xpath("//input[@id='dest']")).sendKeys("Koyambedu Chennai");
		Thread.sleep(5000);

		// Select the Date

		String date = "10/08/2022"; // dd/mm/yyyy
		String[] split = date.split("/");
		int monthToSelect = Integer.parseInt(split[1]);
		
		
		// Calendar icon select
		WebElement calen = driver.findElement(By.xpath("//span[@class='fl icon-calendar_icon-new icon-onward-calendar icon']"));
		Thread.sleep(30000);
		calen.click();
		System.out.println(calen.getText());


		WebElement calTable = driver.findElement(By.tagName("table"));
		WebElement calRow = calTable.findElement(By.xpath("//tr[1]"));
		WebElement month = calTable.findElement(By.xpath("//td[2]"));
		String calMonth = month.getText().replaceAll("\\d", "");
		System.out.println(calMonth);
		

		// Set the format of calendar to month text
		SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM");
		Calendar cal = Calendar.getInstance();
		cal.setTime(inputFormat.parse(calMonth));
		

		// Convert month name to month number
		SimpleDateFormat outputFormat = new SimpleDateFormat("MM");
		System.out.println(outputFormat.format(cal.getTime()));
		int presentMonth = Integer.parseInt(outputFormat.format(cal.getTime()));
		System.out.println(presentMonth);
		

		// Select month and then date
		// forward click
		if (monthToSelect > presentMonth) {
			for (int i = 0; i < (monthToSelect - presentMonth); i++) {
				Thread.sleep(20000);
				WebElement next = driver.findElement(By.xpath("//td[@class='next']/button"));
				Thread.sleep(20000);
				next.click();

			}
		} // reverse direction
		else if (monthToSelect < presentMonth) {
			for (int i = 0; i < (presentMonth - monthToSelect); i++) {
				WebElement pre = calTable.findElement(By.xpath("//td[@class='prev']/button"));
				pre.click();

				Thread.sleep(10000);
			}
		}
		// get the date
		WebElement table = driver.findElement(By.tagName("table"));
		List<WebElement> row = table.findElements(By.tagName("tr"));
		// List <WebElement> col = table.findElements(By.tagName("td"));
		// int csize = col.size();
		int rsize = row.size();

		
		boolean outerBreak = false;//to break the outer loop
		for (int i = 1; i < rsize - 1  && !outerBreak; i++) {

			WebElement eachrow = row.get(i + 1);
			List<WebElement> cols = eachrow.findElements(By.tagName("td"));
			System.out.println(cols);
			int cSize = cols.size();

			for (int j = 1; j < cSize && !outerBreak; j++) {
				String colsText = cols.get(j).getText();
				String input = split[0];

				if (colsText.contains(input)) {
					driver.findElement(By.xpath("//table//tr[" + (i + 1) + "]//td[" + j + "]")).click();
					outerBreak = true;
					break;
				}			
			
			}
		}

		// Click Search
		
		Thread.sleep(20000);
		driver.findElement(By.xpath("//button[text()='Search Buses']")).click();
		driver.findElement((By.xpath("(//label[@for='dtAfter 6 pm'])[1]"))).click();

		// Click Sleeper under Bus types & primo

		driver.findElement(By.xpath("(//label[@for='bt_SLEEPER'])[1]")).click();
		driver.findElement(By.xpath("//span[text() ='Primo Bus']")).click();  //not avaialable for sep month

		// Get the number of buses
		String noOfBuses=driver.findElement(By.xpath("//span[@class='f-bold busFound']")).getText();
		System.out.println("Number of Buses found are: "+noOfBuses);
		int noOfBusesInt=Integer.valueOf(noOfBuses.replace(" Buses", ""));;



		// Get the Bus fare

		List<Integer> busFare = new ArrayList<Integer>();
		for (int i1 = 1; i1 < noOfBusesInt; i1++) {
			JavascriptExecutor js= ((JavascriptExecutor) driver);

			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

			WebElement fare = driver.findElement(By.xpath("(//div[@class='fare d-block'])[" + i1 + "]/span"));
			int fareBus = Integer.parseInt(fare.getText());
			busFare.add(fareBus);

		}
		Collections.sort(busFare);

		System.out.println("Sorted bus fare: " + busFare);
	}

}
