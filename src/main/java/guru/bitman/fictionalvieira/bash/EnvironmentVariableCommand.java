package guru.bitman.fictionalvieira.bash;

import guru.bitman.fictionalvieira.server.Command;

import java.io.IOException;
import java.io.Writer;


public class EnvironmentVariableCommand
    implements Command
{
    private final String name;
    private final String value;
    private boolean export;

    public EnvironmentVariableCommand(String name, String value, boolean export)
    {
        this.name = name;
        this.value = value;
        this.export = export;
        validateState();
    }

    public EnvironmentVariableCommand(String name)
    {
        this(name,null,false);
    }

    public EnvironmentVariableCommand(String name, String value)
    {
        this(name,value,false);
    }

    private void validateState()
    {
        if (name == null) {
            throw new IllegalArgumentException("Variable name cannot be null");
        }
    }

    @Override
    public void execute(Writer out)
    {
        try
        {
            if ( export ) {
                out.append("export ");
            }

            out.append(name).append("=");

            if ( value != null ) {
                out.append(value);
            }
            out.flush();
        } catch (IOException e)
        {
            // ToDo add logging
            e.printStackTrace(System.err);
        }
    }
}
