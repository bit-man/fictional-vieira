package bitman;

import java.io.IOException;
import java.io.Writer;

public class EchoCommand implements Command {
	private final String s;

	public EchoCommand(String s) {
		this.s = s;
	}

	@Override
	public void execute(Writer out) {
		try {
			out.write(s + "\n");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
