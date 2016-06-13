package bitman.fictionalvieira.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EchoCommandTest

{
	@Test
	public void testEcho() {
		final EchoCommand command = new EchoCommand("echo abc");
		TestWriter out = new TestWriter();
		command.execute(out);
		assertEquals("#!/bin/bash\n" + "echo \"abc\"\n", out.getOut());
	}
}
