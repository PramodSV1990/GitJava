package pramodinc.seleniumframework.pagefiles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmation {
	String oid;
	WebDriver driver;
	public OrderConfirmation(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".hero-primary")
	WebElement message;
	@FindBy(xpath="//label[@class='ng-star-inserted']")
	WebElement odr;
	
	@FindBy(css="label[routerlink='/dashboard/myorders']")
	WebElement plink;
	
	public boolean confirmationMessage() {

	String msg=message.getText();
	System.out.println(msg);
	boolean match=msg.equalsIgnoreCase("THANKYOU FOR THE ORDER.");
	return match;
	}
	public String orderHistory()
	{
		oid=odr.getText().split(" ")[1].trim();
		System.out.println(oid);
		plink.click();
		return oid;
	}
	public OrdersPage classGet()
	{
		OrdersPage or=new OrdersPage(driver);
		return or;
	}
	

	
}
