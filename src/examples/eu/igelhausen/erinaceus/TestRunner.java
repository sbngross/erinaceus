package eu.igelhausen.erinaceus;

import eu.igelhausen.erinaceus.core.*;
import cases.*;

public class TestRunner
{
	public static void main(String[] args) throws Exception
	{
		SimpleTest2 tCase = new SimpleTest2();

		ETestOutcome o = tCase.execute();

		if (o == ETestOutcome.FAIL)
			System.exit(1);
	}
}
