package eu.igelhausen.erinaceus;

import java.io.*;

import eu.igelhausen.erinaceus.core.*;

public class TestWriter
{
	StringBuilder builder;

	private BufferedReader reader;
	private PrintWriter writer;

	String className;

	private static boolean isRegular(String line)
	{
		if (line == null)
			return false;

		return (line.length() == 0 || line.charAt(0) != '#');
	}

	String buildStep(String classname, String def, String sName)
	{

		return ""
				+ "addStep("
				+ "\""
				+ sName
				+ "\""
				+ ",(testCase) -> {"
				+ className
				+ " $ = ("
				+ className
				+ ") testCase;"
				+ def
				+ "return ETestOutcome.PASS;"
				+ "		});"
				+ "";
	}

	private void writeImport() throws Exception
	{
		String line;

		writer.println("package cases;");
		writer.println("import eu.igelhausen.erinaceus.core.*;");

		while (isRegular((line = reader.readLine())))
		{
			writer.println(line);
		}
	}

	private String writePreambel() throws Exception
	{
		String line;

		writer.println("public class " + className + " extends ATestCase {" );

		while (isRegular((line = reader.readLine())))
		{
			writer.println(line);
		}

		return line;
	}

	public TestWriter(File file) throws Exception
	{
		String EXTENSION = "\\.etd";

		String line;

		System.out.println(file.getName());

		className = file.getName().replaceAll(EXTENSION,"");
		className = className + "Test";

		FileReader fileReader = new FileReader(file);
		reader = new BufferedReader(fileReader);

		FileWriter w = new FileWriter("src/test/cases/"
				+ className
				+ ".java");
		writer = new PrintWriter(w);

		writeImport();

		line = writePreambel();

		writer.println( "@Override");
		writer.println( "	public void setup() {");

		do
		{
			builder = new StringBuilder();
			String sName = line.replaceAll("#([a-z]*)", "$1");

			while (isRegular((line = reader.readLine())))
			{
				builder.append(
		line.replaceAll("@(.*$)", "{if (!($1)) return ETestOutcome.FAIL;}"));
			}

			writer.println(buildStep(className, builder.toString(), sName));
		} while (line != null);

		writer.println("}");
		writer.println("}");

		writer.close();
		w.close();
	}
}
