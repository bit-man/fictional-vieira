package bitman;

public class CommandFactory {
	public static Command createCommand(String s) {
		return new EchoCommand(s);
	}
}
