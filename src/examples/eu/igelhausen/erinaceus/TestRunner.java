package eu.igelhausen.erinaceus;

import eu.igelhausen.erinaceus.core.*;

public class TestRunner
{
	public static void main(String[] args) throws Exception
	{
		SimpleTest tCase = new SimpleTest();

		ETestOutcome o = tCase.execute();

		if (o == ETestOutcome.FAIL)
			System.exit(1);
	}
}
