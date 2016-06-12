package bitman.fictionalvieira.command;

import java.io.Writer;

public interface Command {
	void execute(Writer out);
}
