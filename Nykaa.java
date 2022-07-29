package week4.Day2;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.get("https://www.nykaa.com/");

		// click on brans menu
		WebElement brandMenu = driver.findElement(By.xpath("//a[text() ='brands']"));
		// using actions class for submenu which appears on mouse houver
		Actions action = new Actions(driver);
		action.moveToElement(brandMenu).perform();
		driver.findElement(By.id("brandSearchBox")).click();
		Thread.sleep(10000);
		driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris");
		Thread.sleep(10000);
		WebElement itemName = driver.findElement(By.linkText("L'Oreal Paris"));
		Thread.sleep(10000);
		action.moveToElement(itemName).perform();
		action.click(itemName).perform();
		// itemName.click();

		assertEquals(driver.getTitle(), "Buy L'Oreal Paris products online at best price on Nykaa | Nykaa");

		// sortby
		driver.findElement(By.xpath("//span[@class='sort-name']")).click();
		driver.findElement(By.xpath("//span[text() ='customer top rated']")).click();

		// category ->hair -> hair Shampoo
		driver.findElement(By.xpath("//span[text() ='Category']")).click();
		// Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text() ='Hair']")).click();
		// Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text() ='Hair Care']")).click();
		// Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text() ='Shampoo']")).click();
		// Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
		Thread.sleep(40000);

		// Check whether filter is applied "Shampoo"
		List<WebElement> filter = driver.findElements(By.xpath("//div[@class='css-1emjbq5']"));
		Thread.sleep(3000);

		for (WebElement eachValue : filter) {

			String text = eachValue.getText();

			if (text.equalsIgnoreCase("Shampoo")) {

				System.out.println(text = "filter is applied");

			}

		}
		driver.findElement(By.xpath("//div[@class ='css-43m2vm']")).click();
		Thread.sleep(30000);
		Set<String> handles = driver.getWindowHandles();
		List<String> handleList = new ArrayList<String>(handles);
		driver.switchTo().window(handleList.get(1));

		// go to new window and select the size of the bottle
		WebElement sizedd = driver.findElement(By.xpath("//select[@title='SIZE']"));
		sizedd.click();
		Select dropdown = new Select(sizedd);
		dropdown.selectByIndex(1);

		// Print the MRP of the product
		// LinkedHashMap <String, String> priceList = new LinkedHashMap <String,
		// String>();
		WebElement price = driver.findElement(By.xpath("(//div[@class='css-1d0jf8e'])[1]"));
		String priceAmount = price.getText();
		priceAmount = priceAmount.replaceAll("\\D", "");
		System.out.println("Price of the shampoo : " + priceAmount);

		// Go to Shopping Bag

		driver.findElement(By.xpath("//div[@class='css-vp18r8']//span[text()='Add to Bag']")).click();
		Thread.sleep(30000);
		driver.findElement(By.xpath("//div[@class='css-0 e1ewpqpu1']/button")).click();
		Thread.sleep(30000);

		// check the grand total
		WebElement frame = driver.findElement(By.xpath("//iframe[@class='css-acpm4k']"));
		Thread.sleep(30000);
		driver.switchTo().frame(frame);
		String grandTotal1 = driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText();
		Thread.sleep(30000);
		grandTotal1 = grandTotal1.replaceAll("\\D", "");
		System.out.println("Grand Total : " + grandTotal1);

		// Click Proceed
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		Thread.sleep(30000);

		// Click on Continue as Guest
 		driver.findElement(By.xpath("//button[@class='btn full big']")).click();
		Thread.sleep(30000);

		// Check if this grand total is the same in step 14

		String grandTotal2 = driver.findElement(By.xpath("(//div[@class='value']//span)[2]")).getText();
		Thread.sleep(30000);
		System.out.println("Grand Total in checkout : "+ grandTotal2);

		if ((Integer.valueOf(grandTotal1)).equals(Integer.valueOf(grandTotal2))) {

			System.out.println("The Grand Total is same as in step 14");
		} else {
			System.out.println("The Grand Total is NOT same as in step 14");
		}

		// Close all windows
		driver.quit();

	}

}
