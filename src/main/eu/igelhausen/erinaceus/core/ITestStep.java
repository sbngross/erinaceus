package eu.igelhausen.erinaceus.core;

public interface ITestStep
{
	ETestOutcome execute(ATestCase tCase) throws Exception;
}
