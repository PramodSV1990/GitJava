package pramodinc.seleniumframework.pagefiles;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pramodinc.seleniumframework.genericfiles.AbstractMethods;

public class ProductCatelogue extends AbstractMethods {
	WebDriver driver;
	
	public ProductCatelogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".mb-3")//(xpath="//div[contains(@class,'col-lg-4 col-md-6')]")
	List<WebElement> product;
	@FindBy(xpath="//div[contains(@class,'col-lg-4 col-md-6')]")
	WebElement productW;
	@FindBy(css="#toast-container")
	WebElement toast;
	@FindBy(css=".ng-animating")
	WebElement inv;
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cart;
	@FindBy(xpath="//div[@class='heading cf']//button[@class='btn btn-primary']")
	WebElement pc;
	
	By prc=By.cssSelector("b");//By.xpath("//div/div/h5/b");

public List<WebElement> productList()
{
	visibleWait(productW);
	return product;
	
}
public MyCartPage getProductByName(String name)
{
//	for(WebElement f:product)
//	{
//		System.out.println(f.getText());
//	}
	WebElement prct = productList().stream()
					.filter(product -> product.findElement(prc).getText().equals(name))
			.findFirst().orElse(null);
	System.out.println(prct.getText());
	prct.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	visibleWait(toast);
	invisibleWait(inv);
	cart.click();
	visibleWait(pc);
	MyCartPage mc=new MyCartPage(driver);
	return mc;
			
}
}

