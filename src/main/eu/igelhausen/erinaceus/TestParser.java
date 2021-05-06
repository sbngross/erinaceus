package eu.igelhausen.erinaceus;

import java.io.*;

import eu.igelhausen.erinaceus.core.*;

public class TestParser
{
	StringBuilder builder;

	String iSection;

	String pSection;

	String tSection;

	String className;

	String buildStep(String classname, String def, String sName)
	{
		def = def.replaceAll("@(.*$)", "if (!($1)) return ETestOutcome.FAIL;");

		return ""
				+ "		addStep("
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

	public TestParser(String fileName) throws Exception
	{
		String PATH = "test/input/";
		String EXTENSION = "\\.etd";

		className = fileName.replaceAll(PATH, "");
		className = className.replaceAll(EXTENSION,"");

		FileReader fileReader = new FileReader(fileName);
		BufferedReader reader = new BufferedReader(fileReader);
		String line;

		FileWriter w = new FileWriter("src/test/cases/"
				+ className
				+ ".java");
		PrintWriter writer = new PrintWriter(w);
		
		writer.println("package cases;");
		writer.println("import eu.igelhausen.erinaceus.core.*;");
		line =  reader.readLine();

		while ((line = reader.readLine()) != null && line.charAt(0) != '#')
		{
			writer.println(line);
		}

		writer.println("public class " + className + " extends ATestCase {" );

		while ((line = reader.readLine()) != null && line.charAt(0) != '#')
		{
			writer.println(line);
		}

		writer.println( "@Override");
		writer.println( "	public void setup() {");

		builder = new StringBuilder();
			String sName = line.replaceAll("#([a-z]*)", "$1");
		while ((line = reader.readLine()) != null && line.charAt(0) != '#')
		{
			builder.append(line);
		}
		writer.println(buildStep(className, builder.toString(), sName));

		writer.println("}");
		writer.println("}");

		writer.close();
		w.close();
	}
}
