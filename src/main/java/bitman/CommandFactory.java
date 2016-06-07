package bitman;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CommandFactory {

	private static Map<String,Function<String,Command>>  command = new HashMap<>();

	static {
		command.put("echo",  cmdLine -> new EchoCommand(cmdLine));
	}

	public static Command createCommand(String s) {
		// ToDo on empty or null string, or unregistered command, we have issue. Please validate.
		String cmd = s.split(" ")[0];
		return command.get(cmd).apply(s);
	}
}
