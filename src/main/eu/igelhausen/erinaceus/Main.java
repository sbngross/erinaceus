package eu.igelhausen.erinaceus;

public class Main
{
	public static void main (String[] args) throws Exception
	{
		if (args[0].equals("run"))
		{
			new TestRunner();
		}

		if (args[0].equals("parse"))
		{
			new TestParser("test/input/SimpleTest.etd");
		}
	}
}
