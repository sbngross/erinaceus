package eu.igelhausen.erinaceus;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.*;

import eu.igelhausen.erinaceus.core.*;

public class TestRunner
{
	private static ATestCase runGame(String gameName) throws Exception
	{
		return (ATestCase) getConstructor(gameName).newInstance();
	}

	@SuppressWarnings("unchecked")
	private static Constructor<?> getConstructor(String gameName)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException
	{
		Class<ATestCase> game;

		game = (Class<ATestCase>) Class.forName(gameName);

		Constructor<?> c = game.getConstructor();

		c.setAccessible(true);

		return c;
	}

	public TestRunner(String path) throws Exception
	{
		File dir = new File(path);
	
		for (File f : dir.listFiles((FilenameFilter)(d,n) -> {return n.endsWith("Test.class");}))
		{
			String className = "cases." +f.getName().replaceFirst("\\.class$", "");
			runTest(className);
		}
	
		System.exit(0);
	}
	
	private static void runTest(String className) throws Exception
	{
		ATestCase t = runGame(className);
		ETestOutcome o = t.execute();

		if (ETestOutcome.FAIL == o)
			System.exit(1);

	}
}
