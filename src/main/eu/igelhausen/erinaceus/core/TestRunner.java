package eu.igelhausen.erinaceus.core;

import java.lang.reflect.*;

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

	public static void main (String[] args) throws Exception
	{
		ATestCase t = runGame("cases.SimpleTest2");

		System.out.println(t.execute());
	}
}
