package bitman.fictionalvieira.bash;

import java.util.LinkedList;
import java.util.List;

public class BashScript
{
    private static final String NEW_LINE = "\n";
    private List<BashCommand> commands = new LinkedList<>();

    public BashScript() {
        commands.add(new BashHeader());
    }

    public BashScript addCommand(BashCommand command)
    {
        this.commands.add(command);
        return this;
    }

    public String buildScript()
    {
        String code = "";

        for(BashCommand bCmd : commands) {
            // ToDo agregar formateo de código (mejor legibilidad)
            code += bCmd.getCode() + NEW_LINE;
        }

        return code;
    }

    private class BashHeader
            implements BashCommand
    {
        @Override
        public String getCode()
        {
            return "#!/bin/bash";
        }
    }
}
