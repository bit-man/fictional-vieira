package guru.bitman.fictionalvieira.command;


import guru.bitman.fictionalvieira.server.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Writer;

public class Ping
		implements Command
{

	private Logger logger = LoggerFactory.getLogger(Ping.class.getName());

	@Override
	public void execute(Writer out)
	{
		try
		{
			out.append("pong");
			out.flush();
		} catch (IOException e)
		{
			logger.error("Error writing code", e);
		}
	}
}
