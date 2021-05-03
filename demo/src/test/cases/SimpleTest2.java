package cases;
import eu.igelhausen.erinaceus.core.*;
public class SimpleTest2 extends ATestCase
{
	private int x=42;
 	private int y=1337;
	@Override	public void setup()	
	{
		addStep("add",(testCase) -> {SimpleTest2 $ = (SimpleTest2) testCase;
			$.y += $.x;
 			System.out.println($.y);
			return ETestOutcome.PASS;
		});
	}
}
