package pramodinc.seleniumframework;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pramodinc.seleniumframework.data.RetryAnalyze;
import pramodinc.seleniumframework.pagefiles.BaseTest;
import pramodinc.seleniumframework.pagefiles.LandingPage;
import pramodinc.seleniumframework.pagefiles.LoginPage;
import pramodinc.seleniumframework.pagefiles.MyCartPage;
import pramodinc.seleniumframework.pagefiles.OrderConfirmation;
import pramodinc.seleniumframework.pagefiles.OrdersPage;
import pramodinc.seleniumframework.pagefiles.PaymentMethod;
import pramodinc.seleniumframework.pagefiles.ProductCatelogue;
import pramodinc.seleniumframework.pagefiles.OrdersPage;

public class EcommerceE extends BaseTest{
	

@Test(dataProvider="getData1",groups= {"order"})
public void submitOrder(HashMap<String,String> input) throws IOException{
	
		// TODO Auto-generated method stub
		//WebDriver driver = new ChromeDriver();
		LoginPage log=landingpage.landingNavigate();
		ProductCatelogue pl=log.login(input.get("email"),input.get("password"));
		List<WebElement>products=pl.productList();
		MyCartPage mc=pl.getProductByName(input.get("product"));
		Boolean present=mc.cartOp(input.get("product"));
		Assert.assertTrue(present);
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

@Test(retryAnalyzer=pramodinc.seleniumframework.data.RetryAnalyze.class)
public void errorValidationmatch()
{
	LoginPage log = landingpage.landingNavigate();
	log.login("samdan@gma.com", "C@pslock1");
	String errorMsg=log.loginPageErrorValidation();
	Assert.assertEquals(errorMsg, "invalid Mail");
	
}

@DataProvider
public Object[][] getData1()
{
	HashMap<String,String> map1=new HashMap<String, String>();
	map1.put("email", "samdan1@gma.com");
	map1.put("password", "C@pslock1");
	map1.put("product", "ZARA COAT 3");
	
	HashMap<String,String> map2=new HashMap<String, String>();
	map2.put("email", "danshika@gmail.com");
	map2.put("password", "C@pslock1");
	map2.put("product", "ADIDAS ORIGINAL");
	
	HashMap<String,String> map3=new HashMap<String, String>();
	map3.put("email", "samda45qq@gma.com");
	map3.put("password", "C@pslock1");
	map3.put("product", "IPHONE 13 PRO");
	
	Object[][] data = {{map1},{map2},{map3}};
	return data;
	
//	Object[][] data= {{"samdan1@gma.com","C@pslock1","ZARA COAT 3"},{"danshika@gmail.com","C@pslock1","ADIDAS ORIGINAL"},
//			{"samda45qq@gma.com","C@pslock1","IPHONE 13 PRO"}};
//	return data;
}
@DataProvider
public Object[][] getDataJson() throws IOException
{
	List<HashMap<String,String>> map=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//pramodinc//seleniumframework//jsondata//productone.json");
	Object[][] data= {{map.get(0)},{map.get(1)},{map.get(2)}};
//			{"samda45qq@gma.com","C@pslock1","IPHONE 13 PRO"}};
	return data;
	
}
}


