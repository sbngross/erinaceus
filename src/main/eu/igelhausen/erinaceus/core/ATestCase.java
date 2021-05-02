package eu.igelhausen.erinaceus.core;
import java.util.*;

public abstract class ATestCase
{
	protected String name;
	private Vector<ITestStep> steps = new Vector<ITestStep>();

	protected ATestCase()
	{
		this.name = getClass().getCanonicalName();
		setup();
	}

	public abstract void setup();
	
	public void addStep(ITestStep step)
	{
		this.steps.add(step);
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
		
		for (ITestStep step : this.steps)
		{
			ETestOutcome out = executeStep(step);
			System.out.println("|-> " + out);
						
			outcomes.add(out);
		}
		
		ETestOutcome out = ETestOutcome.squash(outcomes);
		System.out.println(out);
		
		return out;
	}
}
