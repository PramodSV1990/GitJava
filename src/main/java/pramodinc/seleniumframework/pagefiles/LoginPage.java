package pramodinc.seleniumframework.pagefiles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pramodinc.seleniumframework.genericfiles.AbstractMethods;

public class LoginPage extends AbstractMethods {
	WebDriver driver;
	@FindBy(xpath="//div[contains(text(),'*Enter Valid Email')]")
	WebElement pwait;
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@type='email']")
	WebElement email;
	@FindBy(xpath="//div[@class='form-group mb-4']/input")
	WebElement pwd;
	@FindBy(id="login")
	WebElement login;
	@FindBy(xpath="//div[@aria-label='Incorrect email or password.']")
	WebElement errorMessage;
	public void loginPageError()
	{
		visibleWait(errorMessage);
		System.out.println(errorMessage.getText());
	}
	
	public String loginPageErrorValidation()
	{
		visibleWait(errorMessage);
		return errorMessage.getText();
	}
	
public ProductCatelogue login(String emails,String pwds)
{
	
	email.sendKeys(emails);
	pwd.sendKeys(pwds);
	login.click();
	//visibleWait(pwait);
	ProductCatelogue pl=new ProductCatelogue(driver);
	return pl;
}
}
