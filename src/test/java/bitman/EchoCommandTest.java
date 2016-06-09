package bitman;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EchoCommandTest

{
	@Test
	public void testEcho() {
		final EchoCommand command = new EchoCommand("abc");
		TestWriter out = new TestWriter();
		command.execute(out);
		assertEquals("abc\n", out.getOut());
	}
}
