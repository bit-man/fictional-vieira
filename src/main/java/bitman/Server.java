package bitman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int port;

    public static void main(String[] args) throws IOException {

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
    {
        try (
                ServerSocket serverSocket = new ServerSocket(port)
        )
        {
            while (true)
            {
                Socket clientSocket = getConnection(serverSocket);
                new Thread(new ClientRequest(clientSocket)).start();
            }
        } catch (IOException e)
        {
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
                e.printStackTrace(System.err);
            }
        }
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
                    Command cmd = CommandFactory.createCommand(inputLine);
                    cmd.execute(out);
                }

            } catch (IOException e)
            {
                e.printStackTrace(System.err);
            }

        }
    }
}
