package guru.bitman.fictionalvieira.command;

import org.assertj.core.api.ThrowableAssert;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CommandFactoryTest

{
    @Before
    public void setup()
    {
        CommandFactory.reset();
    }

    @Test
    public void testEchoCommandDoesNotThrowsException()
            throws InvalidCommand
    {
        assertThat(CommandFactory.createCommand("echo zzzz")).isNotNull().isInstanceOf(EchoCommand.class);
    }

    @Test
    public void testPingCommandDoesNotThrowsException()
            throws InvalidCommand
    {
        assertThat(CommandFactory.createCommand("ping")).isNotNull().isInstanceOf(Ping.class);
    }

    @Test
    public void testInvalidCommandThrowsException()
    {
        assertThatExceptionOfType(InvalidCommand.class).isThrownBy(commandCreationAttempt("invalid"));
    }

    @Test
    public void testEmptyCommandThrowsException()
    {
        assertThatExceptionOfType(InvalidCommand.class).isThrownBy(commandCreationAttempt(""));
    }

    @Test
    public void testNullCommandThrowsException()
    {
        assertThatExceptionOfType(InvalidCommand.class).isThrownBy(commandCreationAttempt(null));
    }

    @Test
    public void testAddedCommandIsValid()
            throws InvalidCommand
    {
        CommandFactory.addCommand("abc", cmd -> new EchoCommand(cmd));
        assertThat(CommandFactory.createCommand("abc")).isNotNull().isInstanceOf(EchoCommand.class);
    }

    @Test
    public void testAddedCommandContainsPreviousValidCommands()
            throws InvalidCommand
    {
        CommandFactory.addCommand("abc", cmd -> new EchoCommand(cmd));
        assertThat(CommandFactory.createCommand("ping")).isNotNull().isInstanceOf(Ping.class);
        assertThat(CommandFactory.createCommand("echo zzzz")).isNotNull().isInstanceOf(EchoCommand.class);
    }

    private ThrowableAssert.ThrowingCallable commandCreationAttempt(String cmd)
    {
        return new ThrowableAssert.ThrowingCallable()
        {
            @Override
            public void call()
                    throws Throwable
            {
                CommandFactory.createCommand(cmd);
            }
        };
    }
}
