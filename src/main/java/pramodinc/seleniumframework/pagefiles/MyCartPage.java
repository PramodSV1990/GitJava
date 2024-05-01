package pramodinc.seleniumframework.pagefiles;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import pramodinc.seleniumframework.genericfiles.AbstractMethods;

public class MyCartPage extends AbstractMethods {
	WebDriver driver;
	public MyCartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cartL;
	@FindBy(xpath="//div[@class='cartSection removeWrap']//button[@class='btn btn-primary']")
	WebElement checkout1;
	
public List<WebElement> productList()
{
	return cartL;
}
	public Boolean cartOp(String name1)
	{
			Boolean present = cartL.stream().anyMatch(prd -> prd.getText().contains(name1));
		return present;
		
	}
	public PaymentMethod checkOut()
	{
	checkout1.click();
	PaymentMethod pm=new PaymentMethod(driver);
	return pm;
	
	}

}
