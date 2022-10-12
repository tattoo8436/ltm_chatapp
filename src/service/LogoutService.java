package service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import data.User;

public class LogoutService {

	public void removeClientInServer(Socket socket, DataInputStream dis, DataOutputStream dos, Long clientId)
			throws IOException {
		dis.close();
		dos.close();
		socket.close();
	}

}
