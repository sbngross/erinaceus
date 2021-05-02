package eu.igelhausen.erinaceus.util;

public enum EAnsiColor
{
	BLACK(30),
	RED(31),
	GREEN(32),
	YELLOW(33),
	BLUE(34),
	PURPLE(35),
	CYAN(36),
	WHITE(37);

	private static final String	ANSI_ESC	= "\u001B[";
	private static final String	ANSI_END	= "m";
	private static final String ANSI_RESET	= ANSI_ESC + 0 + ANSI_END;
	
	private final int value;

	private EAnsiColor(final int value)
	{
		this.value = value;
	}

	private int value()
	{
		return this.value;
	}
	
	public static String colorText(String text, EAnsiColor color)
	{
		return ANSI_ESC + color.value() + ANSI_END + text + ANSI_RESET;
	}
}