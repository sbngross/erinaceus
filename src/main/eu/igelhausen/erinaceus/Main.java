package eu.igelhausen.erinaceus;

import java.io.*;

public class Main
{
	public static void main (String[] args) throws Exception
	{
		if (args[0].equals("run"))
		{
			new TestRunner(args[1]);
		}

		if (args[0].equals("write"))
		{
			File dir = new File(args[1]);
			
			for (File f : dir.listFiles((FilenameFilter)(d,n) -> {return n.endsWith(".etd");}))
				new TestWriter(f);
		}
	}
}
