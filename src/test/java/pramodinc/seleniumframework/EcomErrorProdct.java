package pramodinc.seleniumframework;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pramodinc.seleniumframework.pagefiles.BaseTest;
import pramodinc.seleniumframework.pagefiles.LandingPage;
import pramodinc.seleniumframework.pagefiles.LoginPage;
import pramodinc.seleniumframework.pagefiles.MyCartPage;
import pramodinc.seleniumframework.pagefiles.OrderConfirmation;
import pramodinc.seleniumframework.pagefiles.OrdersPage;
import pramodinc.seleniumframework.pagefiles.PaymentMethod;
import pramodinc.seleniumframework.pagefiles.ProductCatelogue;
import pramodinc.seleniumframework.pagefiles.OrdersPage;

public class EcomErrorProdct extends BaseTest{
	

@Test
public void submitOrder() throws IOException{
	
		// TODO Auto-generated method stub
		//WebDriver driver = new ChromeDriver();
		String product="ZARA33";
		LoginPage log=landingpage.landingNavigate();
		ProductCatelogue pl=log.login("samdan1@gma.com","C@pslock1");
		List<WebElement>products=pl.productList();
		MyCartPage mc=pl.getProductByName("ZARA COAT 3");
		Boolean present=mc.cartOp(product);
		Assert.assertFalse(present);
		System.out.println(product+" not present in the cart");
		PaymentMethod pm=mc.checkOut();
		pm.selectCountry("ind");
		pm.countrySelect("India");
		OrderConfirmation ord=pm.clickOrder();
		boolean match=ord.confirmationMessage();
		Assert.assertTrue(match);
		String oid=ord.orderHistory();
		OrdersPage or=ord.classGet();
		Boolean matched=or.orderCheck(oid);
		Assert.assertTrue(matched);
		}
//(dependsOnMethods={"submitOrder"})
@Test
public void errorValidation()
{
	LoginPage log = landingpage.landingNavigate();
	log.login("samdan@gma.com", "C@pslock1");
	log.loginPageError();
}
}

