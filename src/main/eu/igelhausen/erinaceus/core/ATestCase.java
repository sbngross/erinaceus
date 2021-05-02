package eu.igelhausen.erinaceus.core;
import java.util.*;
import java.util.Map.Entry;

public abstract class ATestCase
{
	protected String name;
	private Map<String,ITestStep> steps = new LinkedHashMap<String, ITestStep>();

	protected ATestCase()
	{
		this.name = getClass().getCanonicalName();
		setup();
	}

	public abstract void setup();
	
	public void addStep(String stepName, ITestStep stepDef)
	{
		this.steps.put(stepName, stepDef);
	}

	private ETestOutcome executeStep(ITestStep step)
	{
		try
		{
			return step.execute(this);
		} catch (Exception ex)
		{
			return ETestOutcome.FAIL;
		}
	}

	public ETestOutcome execute()
	{
		System.out.println(this.name + ":");
		
		Vector<ETestOutcome> outcomes = new Vector<ETestOutcome>(this.steps.size());
		
		for (Entry<String, ITestStep> step : this.steps.entrySet())
		{
			ETestOutcome out = executeStep(step.getValue());
			System.out.println("|-> "+ step.getKey() + ": " + out);
						
			outcomes.add(out);
		}
		
		ETestOutcome out = ETestOutcome.squash(outcomes);
		System.out.println(out);
		
		return out;
	}
}
