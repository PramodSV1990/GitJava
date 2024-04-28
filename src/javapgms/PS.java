package javapgms;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PS {
	
	public void doThis()
	{
		System.out.println("I am here");
		
	}
	@BeforeTest
	public void bTest()
	{
		System.out.println("am first");
	}
	@AfterTest
	public void aTest()
	{
		System.out.println("Am last");
	}

}
