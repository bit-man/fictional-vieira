package guru.bitman.fictionalvieira.command;

import guru.bitman.fictionalvieira.server.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CommandFactory {

	private static Map<String,Function<String,Command>>  command = new HashMap<>();

	static {
        reset();
	}

    public static void reset()
    {
        command.put("echo",  cmdLine -> new EchoCommand(cmdLine));
        command.put("ping",  cmdLine -> new Ping());
    }

    public static Command createCommand(String s)
            throws InvalidCommand
    {
        if ( s == null ) {
            throw new InvalidCommand("NULL");
        }

		String cmd = s.split(" ")[0];

        Function<String, Command> function = command.get(cmd);
        if ( function == null) {
            throw new InvalidCommand(cmd);
        }

        String[] strings = s.split(" ", 2);
        return function.apply(strings.length == 1 ? "" : strings[1]);
	}

	public static void addCommand(String commandName, Function<String,Command> commandCreation) {
		command.put(commandName, commandCreation);
	}
}
