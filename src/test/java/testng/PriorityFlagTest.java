package testng;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class PriorityFlagTest {
	@Test(priority = -1)
	public void demo1() {
		//it is like print
		Reporter.log("demo1",true);
		
	}
	@Test(priority = 1)
	public void demo2() {
		//it is like print
		Reporter.log("demo2",true);
		
	}
	@Test
	public void demo3() {
		//it is like print
		Reporter.log("demo3",true);
		
	}
	@Test
	public void demo4() {
		//it is like print
		Reporter.log("demo4",true);
		
	}
	@Test(priority = -2)
	public void demo5() {
		//it is like print
		Reporter.log("demo5",true);
		
	}
}
