package pramodinc.seleniumframework.pagefiles;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pramodinc.seleniumframework.genericfiles.AbstractMethods;

public class OrdersPage extends AbstractMethods {

	public OrdersPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
			}
	@FindBy(xpath="//tbody/tr/th")
	List<WebElement> orlist;
	public Boolean orderCheck(String orderid)
	{
	Boolean matched=orlist.stream().anyMatch(prod->prod.getText().equals(orderid));
	return matched;
	}
	

}
