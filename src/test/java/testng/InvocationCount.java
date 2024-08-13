package testng;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class InvocationCount {
	@Test(invocationCount  = -1)
	public void demo1() {
		//it is like print
		Reporter.log("demo1",true);
		
	}
	@Test(invocationCount = 3)
	public void demo2() {
		//it is like print
		Reporter.log("demo2",true);
		
	}
	@Test
	public void demo3() {
		//it is like print
		Reporter.log("demo3",true);
		
	}
}
