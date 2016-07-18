package guru.bitman.fictionalvieira.bash;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EnvironmentVariableCommand
    implements BashCommand
{
    private final String name;
    private final String value;
    private boolean export;
    private Logger logger = LoggerFactory.getLogger(EnvironmentVariableCommand.class.getName());

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
    public String getCode()
    {
        StringBuilder out = new StringBuilder();
        if (export)
        {
            out.append("export ");
        }

        out.append(name).append("=");

        if (value != null)
        {
            out.append(value);
        }

        return out.toString();
    }
}
