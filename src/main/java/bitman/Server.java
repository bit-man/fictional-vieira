package bitman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {

		if (args.length != 1) {
			System.err.println("Usage: java Server <port number>");
			System.exit(-1);
		}

		int portNumber = Integer.parseInt(args[0]);

		try (
				ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
				Socket clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
		) {

			String inputLine;
			// TODO Let the server continue running once the command is executed
			while ((inputLine = in.readLine()) != null) {
				Command cmd = CommandFactory.createCommand(inputLine);
				cmd.execute(out);
			}

		} catch (IOException e) {
			System.err.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
			System.err.println(e.getMessage());
		}
	}
}
