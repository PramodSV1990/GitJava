package pramodinc.seleniumframework.pagefiles;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pramodinc.seleniumframework.genericfiles.AbstractMethods;

public class PaymentMethod extends AbstractMethods {
	WebDriver driver;
	public PaymentMethod(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country1;
	@FindBy(xpath="//span[@class='ng-star-inserted']")
	List<WebElement> count;
	@FindBy(xpath="//a[@class='btnn action__submit ng-star-inserted']")
	WebElement placeOrder;
	public void selectCountry(String countrycode)
	{
		javascriptExe("window.scrollBy(0,600)");
		
		country1.sendKeys(countrycode);
	}
	
	public List<WebElement> countryList()
	{
		return count;
	}
	
	public void countrySelect(String country)
	{
		WebElement ind = count.stream().filter(cnt -> cnt.getText().equals(country)).findFirst().orElse(null);
		ind.click();
	}
	public OrderConfirmation clickOrder()
	{
		Actions a=new Actions(driver);
		a.moveToElement(placeOrder).click().build().perform();
		OrderConfirmation ord=new OrderConfirmation(driver);
		return ord;
	}
}
