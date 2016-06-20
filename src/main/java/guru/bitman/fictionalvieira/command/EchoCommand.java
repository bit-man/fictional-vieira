package guru.bitman.fictionalvieira.command;

import guru.bitman.fictionalvieira.bash.BashScript;
import guru.bitman.fictionalvieira.bash.EchoBashCommand;
import guru.bitman.fictionalvieira.server.Command;

import java.io.IOException;
import java.io.Writer;

public class EchoCommand implements Command
{
	private final String cmdLine;

	public EchoCommand(String cmdLine) {
		this.cmdLine = cmdLine;
	}

	@Override
	public void execute(Writer out) {
		BashScript bashScript = new BashScript();
		// ToDo validate cmdLine split
		String script = bashScript
				.addCommand(new EchoBashCommand(cmdLine.split(" ",2)[1]))
				.buildScript();
		try {
			out.write(script);
			out.flush();
		} catch (IOException e) {
			// ToDo add logging
			e.printStackTrace(System.err);
		}
	}
}
