package eu.igelhausen.erinaceus;

import java.lang.reflect.*;

import eu.igelhausen.erinaceus.core.*;

public class TestRunner
{
	public static ATestCase runGame(String gameName) throws Exception
	{
		return (ATestCase) getConstructor(gameName).newInstance();
	}

	@SuppressWarnings("unchecked")
	private static Constructor<?> getConstructor(String gameName) throws ClassNotFoundException, NoSuchMethodException, SecurityException
	{
		Class<ATestCase> game;
		
		game = (Class<ATestCase>) Class.forName(gameName);
		
		Constructor<?> c = game.getConstructor();
		
		c.setAccessible(true);
		
		return c;
	}

	public TestRunner() throws Exception
	{
			ATestCase t = runGame("cases.SimpleTest");
			ETestOutcome o = t.execute();

			if (ETestOutcome.FAIL == o)
				System.exit(1);
	}
}
