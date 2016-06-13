package bitman.fictionalvieira.bash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class PipeBashOperatorTest {
	@Test
	public void testConstructorDontThrowException() {
		new PipeBashOperator();
	}

	@Test
	public void testNotNullConstructor() {
		assertNotNull(new PipeBashOperator());
	}

	@Test
	public void testSingleCommand() {
		assertEquals( "echo \"a\"", new PipeBashOperator(new EchoBashCommand("a")).getCode() );
	}

	@Test
	public void testTwoCommands() {
		assertEquals( "echo \"a\" | echo \"b\"", new PipeBashOperator(new EchoBashCommand("a"), new EchoBashCommand("b")).getCode() );
	}

	@Test
	public void testThreeCommands() {
		assertEquals( "echo \"a\" | echo \"b\" | echo \"c\"", new PipeBashOperator(new EchoBashCommand("a"), new EchoBashCommand("b"), new
				EchoBashCommand("c")).getCode() );
	}
}
