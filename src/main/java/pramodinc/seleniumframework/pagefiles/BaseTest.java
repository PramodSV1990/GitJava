package pramodinc.seleniumframework.pagefiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.FileHandler;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {
	Properties pr;
	public WebDriver driver;
	public LandingPage landingpage;
	public WebDriver browserHandling() throws IOException
	{
pr=new Properties();
FileInputStream fis=new FileInputStream("C://Users//91988//eclipse-workspace//seleniumframework//src//main//java//pramodinc//seleniumframework//genericfiles//Generic.properties");
pr.load(fis);
String brw=System.getProperty("browser")!=null?System.getProperty("browser"):pr.getProperty("browser");
if(brw.equalsIgnoreCase("chrome"))
{
driver = new ChromeDriver();
}
else if(brw.equalsIgnoreCase("firefox"))
{
	driver = new FirefoxDriver();
}
//driver = new ChromeDriver();
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
driver.get("https://rahulshettyacademy.com/client");
driver.manage().window().maximize();
return driver;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
	@SuppressWarnings("deprecation")
	String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
	ObjectMapper mapper =new ObjectMapper();
	List<HashMap<String,String>> data =mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
	return data;
	}
	
@BeforeMethod(alwaysRun=true)
public LandingPage initialize() throws IOException {
	driver=browserHandling() ;
	landingpage=new LandingPage(driver);
	return landingpage;
	
}
@AfterMethod(alwaysRun=true)
public void endDriver()
{
	driver.quit();
}

public String takeScreenShot(String filename,WebDriver driver) throws IOException
{

try {
	File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	File destinationFile = new File(System.getProperty("user.dir") + "\\reports\\" + filename + ".png");
	FileUtils.copyFile(sourceFile, destinationFile);
	

} catch (Exception e) {

    System.out.println("Failure to take screenshot " + e);

}
return System.getProperty("user.dir") + "\\reports\\" + filename + ".png";

}

/*
 * public String getSC(String testCaseName,WebDriver driver) { String
 * screenshotPath = null;
 * 
 * try {
 * 
 * //take screenshot and save it in a file
 * 
 * File sourceFile = ((TakesScreenshot)
 * driver).getScreenshotAs(OutputType.FILE);
 * 
 * //copy the file to the required path
 * 
 * File destinationFile = new File(System.getProperty("user.dir") +
 * "\\reports\\" + testCaseName + ".png");
 * 
 * FileUtils.copyFile(sourceFile, destinationFile);
 * 
 * String[] relativePath = destinationFile.toString().split("reports");
 * 
 * screenshotPath = ".\\" + relativePath[1];
 * 
 * } catch (Exception e) {
 * 
 * System.out.println("Failure to take screenshot " + e);
 * 
 * }
 * 
 * return screenshotPath; }
 */

public ExtentReports extentRpt()
{
	String path=System.getProperty("user.dir")+"\\reports\\index.html";
	ExtentSparkReporter reporter=new ExtentSparkReporter(path);
	reporter.config().setDocumentTitle("PramodTest");
	reporter.config().setReportName("Ecomerce Test");
	
	ExtentReports extent=new ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tester", "Pramod");
	extent.setSystemInfo("environment", "uat");
	return extent;

}
}


