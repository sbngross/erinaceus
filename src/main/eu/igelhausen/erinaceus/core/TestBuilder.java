package eu.igelhausen.erinaceus.core;

import java.io.FileWriter;

public class TestBuilder
{
	static StringBuilder builder;

	static String iSection;

	static String pSection;

	static String tSection;

	static String name;

	static String buildStep()
	{
		String def = "$.y += $.x; System.out.println($.y);";

		String sName = "add";

		String auto = "PASS";
		return ""
				+ "		addStep("
				+ "\""
				+ sName
				+ "\""
				+ ",(testCase) -> {"
				+ name
				+ " $ = ("
				+ name
				+ ") testCase;"
				+ def
				+ "			return ETestOutcome."
				+ auto
				+ ";"
				+ "		});"
				+ "";
	}

	static String buildCase()
	{
		return ""
				+ "package cases;"
				+ "import eu.igelhausen.erinaceus.core.*;"
				+ iSection
				+ "public class "
				+ name
				+ " extends ATestCase"
				+ "{"
				+ pSection
				+ "	@Override"
				+ "	public void setup()"
				+ "	{"
				+ tSection
				+ "	}"
				+ "}"
				+ "";
	}

	public static void main(String[] args) throws Exception
	{
		iSection = "";

		name = "SimpleTest2";

		pSection = "private int x=42; private int y=1337;";

		tSection = buildStep();

		FileWriter w = new FileWriter("src/test/cases/"
				+ name
				+ ".java");

		w.write(buildCase());
		w.flush();

		w.close();

	}
}
