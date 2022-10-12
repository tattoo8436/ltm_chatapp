package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class ChatAppMain {
	public static void main(String[] args) throws SQLException {
		// Create socket, port and listening
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(9999);

			while (true) {
				// Server accept
				Socket socket = serverSocket.accept();
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				Thread thread = new RequestHandler(socket, dis, dos);
				thread.start();
			}
		} catch (IOException e1) {
			System.out.println(e1);
		}

	}
}
