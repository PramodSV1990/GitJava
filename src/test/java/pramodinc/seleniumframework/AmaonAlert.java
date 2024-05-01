package pramodinc.seleniumframework;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import java.util.List;
import java.util.Set;
import javax.swing.text.html.HTMLDocument.Iterator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
public class AmaonAlert {
	
	static WebDriver  driver;

	@Test
	public void amazonOrder() throws IOException
	{
		 driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		takeScreenshot("amazon.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions a=new Actions(driver);
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@class='nav-a nav-a-2   nav-progressive-attribute'][1]"))));
		
		a.moveToElement(driver.findElement(By.xpath("//a[@class='nav-a nav-a-2   nav-progressive-attribute'][1]"))).build().perform();
		a.moveToElement(driver.findElement(By.id("twotabsearchtextbox"))).click().keyDown(Keys.SHIFT).sendKeys("Iphone fifteen").keyDown(Keys.ENTER).build().perform();
		takeScreenshot("iphone15");
		//driver.get("https://www.amazon.in/s?k=iphone+fifteen&crid=2LBX4L2ETBA2R&sprefix=IPHONE+FIFTEEN%2Caps%2C375&ref=nb_sb_ss_ts-doa-p_1_14");
		Set<String> windows=driver.getWindowHandles();
		java.util.Iterator<String> it=windows.iterator();
		String parantID=it.next();
		String childID=it.next();
		System.out.println(parantID);
		System.out.println(childID);
		driver.switchTo().window(childID);
		takeScreenshot("iphone15main");
		List<WebElement> phone=driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
		for (WebElement ph:phone)
		{
			if(ph.getText().contains("Apple iPhone 15 (256 GB)"))
			{
				System.out.println(ph.getText());
				ph.click();
				break;
			}
		
		}
		takeScreenshot("iphone15mainbook");
		driver.get("https://www.amazon.in/Apple-iPhone-15-256-GB/dp/B0CHX6N27Y/ref=sr_1_1?crid=Y3S8P1RK7TGT&keywords=IPHONE+FIFTEEN&qid=1706882527&sprefix=%2Caps%2C223&sr=8-1");
		System.out.println(driver.findElement(By.xpath("//div[@class='a-section a-spacing-none a-padding-none']//input[@id='add-to-cart-button']")).getText());
		driver.findElement(By.xpath("//div[@class='a-section a-spacing-none a-padding-none']//input[@id='add-to-cart-button']")).click();
		takeScreenshot("Add to kart");
		System.out.println(driver.findElement(By.xpath("//div[@id='attachDisplayAddBaseAlert']//h4[@class='a-alert-heading'][normalize-space()='Added to Cart']")).getText());
			
		driver.quit();
	}
	public static void takeScreenshot(String filename) throws IOException
	{
		File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file,  new File(System.getProperty("user.dir") + "\\reports\\amzon\\" + filename + ".png"));
	}

}
