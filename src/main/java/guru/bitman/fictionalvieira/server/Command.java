package guru.bitman.fictionalvieira.server;

import java.io.Writer;

public interface Command {
	void execute(Writer out);
}