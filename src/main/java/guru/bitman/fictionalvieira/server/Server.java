package guru.bitman.fictionalvieira.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int port;
    private Method commandCreation;

    private Logger logger = LoggerFactory.getLogger(Server.class.getName());
    private static int threadNum = 0;

    public static void main(String[] args)
            throws IOException,ReflectiveOperationException
    {

		if (args.length != 1) {
			System.err.println("Usage: java Server <port number>");
			System.exit(-1);
		}

		int portNumber = Integer.parseInt(args[0]);

        new Server(portNumber).mainLoop();
	}

    private Server(int port)
    {
        this.port = port;
    }

    private void mainLoop()
            throws ReflectiveOperationException
    {
        try (
               ServerSocket serverSocket = new ServerSocket(port)
        )
        {
            initCommandExecution();
            commandCreation = commandFactoryCommandCreation();

            while (true)
            {
                Socket clientSocket = getConnection(serverSocket);
                new Thread(new ClientRequest(clientSocket), "fv-" + getNextThreadNum() ).start();
            }
        } catch (IOException e)
        {
            logger.error("Error opening port " + port, e);
        }
    }

    private  static synchronized int getNextThreadNum()
    {
        return threadNum++;
    }

    private Socket getConnection(ServerSocket serverSocket)
    {
        while (true)
        {
            try
            {
                return serverSocket.accept();
            } catch (IOException e)
            {
                logger.error("Error waiting for connection", e);
            }
        }
    }

    private void initCommandExecution()
            throws ReflectiveOperationException
    {
        staticMethodCreation("fv.init.method", "guru.bitman.fictionalvieira.command.CommandFactory#reset").invoke(null);
    }


    private Method commandFactoryCommandCreation()
            throws ReflectiveOperationException
    {
        return staticMethodCreation("fv.server.method", "guru.bitman.fictionalvieira.command.CommandFactory#createCommand", String.class);
    }

    private Method staticMethodCreation(String propertyName, String defaultValue, Class<?>... parameterTypes)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException
    {
        PropertyParser parser = new PropertyParser(propertyName, defaultValue).parse();
        String clazz = parser.getClazz();
        String method = parser.getMethod();
        return ClassLoader.getSystemClassLoader().loadClass(clazz).getDeclaredMethod(method,parameterTypes);
    }

    private class ClientRequest
            implements Runnable
    {
        private Socket clientSocket;

        public ClientRequest(Socket clientSocket)
        {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run()
        {
            try (
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
            )
            {
                String inputLine;
                while ((inputLine = in.readLine()) != null)
                {
                    ((Command)commandCreation.invoke(null, inputLine)).execute(out);
                }

            } catch (IOException | SecurityException | IllegalAccessException |
                    IllegalArgumentException | InvocationTargetException e)
            {
                logger.error("Error processing client request", e);
            }

        }
    }

}
