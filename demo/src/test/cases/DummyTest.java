package cases;
import eu.igelhausen.erinaceus.core.*;
import core.*;
public class DummyTest extends ATestCase {
int x = 42;
int y = 1337;
Dummy d;
@Override
	public void setup() {
addStep("create",(testCase) -> {DummyTest $ = (DummyTest) testCase;d = new Dummy();if (!(d != null)) return ETestOutcome.FAIL;return ETestOutcome.PASS;		});
addStep("set",(testCase) -> {DummyTest $ = (DummyTest) testCase;d.set($.x);if (!(d.get() == $.x)) return ETestOutcome.FAIL;return ETestOutcome.PASS;		});
addStep("add",(testCase) -> {DummyTest $ = (DummyTest) testCase;d.inc($.y);if (!(d.get() == ($.x + $.y))) return ETestOutcome.FAIL;return ETestOutcome.PASS;		});
}
}
