package guru.bitman.fictionalvieira.bash;

/**
 * BASH command echo : prints passed args
 */
public class EchoBashCommand
        implements BashCommand
{
    private final String args;

    public EchoBashCommand(String args)
    {
        this.args = args;
    }

    @Override
    public String getCode()
    {
        return "echo \"" + args + "\"";
    }
}
