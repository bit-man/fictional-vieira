package guru.bitman.fictionalvieira.command;


import guru.bitman.fictionalvieira.server.Command;

import java.io.IOException;
import java.io.Writer;

public class Ping
		implements Command
{
	@Override
	public void execute(Writer out)
	{
		try
		{
			out.append("pong");
			out.flush();
		} catch (IOException e)
		{
			e.printStackTrace(System.err);
		}
	}
}
