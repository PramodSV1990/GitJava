package pramodinc.seleniumframework.pagefiles;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

public class LandingPage {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		this.driver=driver;
	}

	public LoginPage landingNavigate()
	{
		
		driver.manage().window().maximize();
		LoginPage log=new LoginPage(driver);
		return log;
	}

}
