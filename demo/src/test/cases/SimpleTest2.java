package cases;
import eu.igelhausen.erinaceus.core.*;

import core.*;

public class SimpleTest2 extends ATestCase
{
private int x=42;
 private int y=1337;
	@Override	public void setup()	
{
		addStep("add",(testCase) -> 
{
SimpleTest2 $ = (SimpleTest2) testCase;
	if (Dummy.add($.x, $.y) == 1379)
			return ETestOutcome.PASS;
		return ETestOutcome.FAIL;
}
);
	
}

}

