package eu.igelhausen.erinaceus;

import eu.igelhausen.erinaceus.core.*;
import helper.*;

public class SimpleTest extends ATestCase
{
	private int x = 42;

	private int y = 1337;

	@Override
	public void setup()
	{
		addStep("add",(testCase) -> {
			SimpleTest $ = (SimpleTest) testCase;
			$.y += $.x;
			return ETestOutcome.PASS;
		});

		addStep("skip", (c) -> {
			return ETestOutcome.SKIP;
		});

		addStep("check",(c) -> {
			SimpleTest $ = (SimpleTest) c;
			if ($.y == 1379)
				return ETestOutcome.PASS;
			
			return ETestOutcome.FAIL;
		});
	}
}
