package pramodinc.seleniumframework.genericfiles;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AbstractMethods {
	WebDriver driver;
	
	public AbstractMethods(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}

	public void visibleWait(WebElement s)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOf(s));
	}
	public void invisibleWait(WebElement s)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.invisibilityOf(s));
	}
public void javascriptExe(String corddrinate)
{
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript(corddrinate);
}

}

