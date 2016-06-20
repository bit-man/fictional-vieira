package guru.bitman.fictionalvieira.bash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class BashScriptTest {

	private static final String NEW_LINE = "\n";

	@Test
	public void testConstructorDontThrowException() {
		new BashScript();
	}

	@Test
	public void testNotNullConstructor() {
		assertNotNull(new BashScript());
	}

	@Test
	public void testJustSheBang() {
		assertEquals( "#!/bin/bash"   + NEW_LINE + NEW_LINE , new BashScript().buildScript() );
	}

	@Test
	public void testAddCommandAndGetCode() {
		final BashScript bashScript = new BashScript().addCommand(new EchoBashCommand("a"));
		final String expectedCode = "#!/bin/bash"  + NEW_LINE +
									NEW_LINE +
									"echo \"a\"" + NEW_LINE;
		assertEquals(expectedCode, bashScript.buildScript() );
	}

	@Test
	public void testAddCommandsUsingAndOperatorAddsNewLineAfterLastCommand() {
		final BashScript bashScript = new BashScript().addCommand(
				new AndBashOperator(new EchoBashCommand("a"), new EchoBashCommand("b"))
		);
		final String expectedCode = "#!/bin/bash"  + NEW_LINE +
									NEW_LINE +
									"echo \"a\" && echo \"b\"" + NEW_LINE;
		assertEquals(expectedCode, bashScript.buildScript() );
	}

	@Test
	public void testAddCommandsUsingPipeOperatorAddsNewLineAfterLastCommand() {
		final BashScript bashScript = new BashScript().addCommand(
				new PipeBashOperator(new EchoBashCommand("a"), new EchoBashCommand("b"))
		);
		final String expectedCode = "#!/bin/bash"  + NEW_LINE +
									NEW_LINE +
									"echo \"a\" | echo \"b\"" + NEW_LINE;
		assertEquals(expectedCode, bashScript.buildScript() );
	}

	@Test
	public void testAddCommandsUsingNoOperatorAddsNewLineAfterEachCommand() {
		final BashScript bashScript = new BashScript()
				.addCommand(new EchoBashCommand("a"))
				.addCommand(new EchoBashCommand("b"));
		final String expectedCode = "#!/bin/bash" + NEW_LINE +
									NEW_LINE +
									"echo \"a\"" + NEW_LINE +
									"echo \"b\"" + NEW_LINE;
		assertEquals(expectedCode, bashScript.buildScript() );
	}
}
