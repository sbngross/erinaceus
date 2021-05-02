package eu.igelhausen.erinaceus.core;

import eu.igelhausen.erinaceus.util.*;

import java.util.*;

public enum ETestOutcome
{
	PASS(EAnsiColor.GREEN),
	FAIL(EAnsiColor.RED),
	SKIP(EAnsiColor.YELLOW);

	private EAnsiColor color;

	private ETestOutcome(EAnsiColor color)
	{
		this.color = color;
	}
	
	public String toString()
	{
		return '[' + EAnsiColor.colorText(this.name(), this.color) + ']';
	}
	
	public static ETestOutcome squash(Collection<ETestOutcome> outcomes)
	{
		for (ETestOutcome o : outcomes)
		{
			if (o == FAIL)
				return FAIL;
		}
		
		return PASS;
	}
}
