package guru.bitman.fictionalvieira.command;

public class InvalidCommand
        extends Throwable
{
    public InvalidCommand(String cmd)
    {
        super("Invalid command : " + cmd);
    }
}
