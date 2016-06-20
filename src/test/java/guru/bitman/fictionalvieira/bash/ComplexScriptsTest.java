package guru.bitman.fictionalvieira.bash;

import org.junit.Assert;
import org.junit.Test;

public class ComplexScriptsTest {
	@Test
	public void test00() {
		String expectedCode = 	"#!/bin/bash\n" +
								"\n" +
								"echo \"display PATH\" | nc localhost 3000 > /tmp/myScript\n" +
								"chmod a+x /tmp/myScript\n" + "/tmp/myScript\n";

		final GenericBashCommand netcat = new GenericBashCommand("nc localhost 3000");
		final EchoBashCommand echo = new EchoBashCommand("display PATH");

		final PipeBashOperator echoPipeNetcat = new PipeBashOperator(echo, netcat);
		BashScript createdBashScript = new BashScript()
						.addCommand(new RedirectOperator(echoPipeNetcat, "/tmp/myScript", RedirectOperator.Type.OVERWRITE ))
						.addCommand(new GenericBashCommand("chmod a+x /tmp/myScript"))
						.addCommand(new GenericBashCommand("/tmp/myScript"));
		Assert.assertEquals(expectedCode, createdBashScript.buildScript());
	}
}
