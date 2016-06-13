package bitman.fictionalvieira.bash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class AndBashOperatorTest {
	@Test
	public void testConstructorDontThrowException() {
		new AndBashOperator();
	}

	@Test
	public void testNotNullConstructor() {
		assertNotNull(new AndBashOperator());
	}

	@Test
	public void testSingleCommand() {
		assertEquals( "echo \"a\"", new AndBashOperator(new EchoBashCommand("a")).getCode() );
	}

	@Test
	public void testTwoCommands() {
		assertEquals( "echo \"a\" && echo \"b\"", new AndBashOperator(new EchoBashCommand("a"), new EchoBashCommand("b")).getCode() );
	}

	@Test
	public void testThreeCommands() {
		assertEquals( "echo \"a\" && echo \"b\" && echo \"c\"", new AndBashOperator(new EchoBashCommand("a"), new EchoBashCommand("b"), new
				EchoBashCommand("c")).getCode() );
	}
}
