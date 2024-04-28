package javapgms;

import org.testng.annotations.Test;

public class PS1 extends PS {
	@Test
	public void iCan()
	{
		doThis();
		int a=4;
		PS2 p=new PS2(a);
		System.out.println("increment is "+p.increment());
		System.out.println("decrement is "+p.decrement());
	}


}
