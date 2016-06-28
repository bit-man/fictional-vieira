package guru.bitman.fictionalvieira.command;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EchoCommandTest

{
	@Test
	public void testEcho() {
		final EchoCommand command = new EchoCommand("abc");
		TestWriter out = new TestWriter();
		command.execute(out);
		assertEquals("#!/bin/bash\n\n" + "echo \"abc\"\n", out.getOut());
	}
}
