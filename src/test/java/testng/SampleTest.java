package testng;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class SampleTest {
	
	@Test
	public void demo() {
		//it is like print
		Reporter.log("Hello World!",true);
		
	}

}
