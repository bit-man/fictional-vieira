package guru.bitman.fictionalvieira.server;

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

    public static void main(String[] args)
            throws IOException,ReflectiveOperationException
    {

		if (args.length != 1) {
            // ToDo add logging
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
                // ToDo add thread name
                new Thread(new ClientRequest(clientSocket)).start();
            }
        } catch (IOException e)
        {
            // ToDo add logging
            e.printStackTrace(System.err);
        }
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
                // ToDo add logging
                e.printStackTrace(System.err);
            }
        }
    }

    private void initCommandExecution()
            throws ReflectiveOperationException
    {
        staticMethodCreation("init.method", "guru.bitman.fictionalvieira.command.CommandFactory#reset").invoke(null);
    }


    private Method commandFactoryCommandCreation()
            throws ReflectiveOperationException
    {
        return staticMethodCreation("server.method", "guru.bitman.fictionalvieira.command.CommandFactory#createCommand");
    }

    private Method staticMethodCreation(String propertyname, String defaultValue)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException
    {
        final String property = System.getProperty(propertyname, defaultValue);
        // ToDo might fail, please validate
        String[] split = property.split("#");
        String clazz = split[0];
        String method = split[1];
        return ClassLoader.getSystemClassLoader().loadClass(clazz).getDeclaredMethod(method,String
                .class);
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
                // ToDo add logging
                e.printStackTrace(System.err);
            }

        }
    }
}
