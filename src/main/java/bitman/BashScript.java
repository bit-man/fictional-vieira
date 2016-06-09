package bitman;

import java.util.LinkedList;
import java.util.List;

public class BashScript
{
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
            code += bCmd.getCode();
        }

        return code;
    }

    private class BashHeader
            implements BashCommand
    {
        @Override
        public String getCode()
        {
            return "#!/bin/bash\n";
        }
    }
}
