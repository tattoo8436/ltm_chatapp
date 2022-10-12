package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import com.google.gson.Gson;

import data.*;
import error.ErrorHandler;
import repository.*;
import service.*;

public class RequestHandler extends Thread {
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private Long clientId;
	
	public RequestHandler(Socket socket, DataInputStream dis, DataOutputStream dos) {
		this.socket = socket;
		this.dis = dis;
		this.dos = dos;
		clientId = -1L;
	}

	@Override
	public void run() {
		try {
			Gson gson = new Gson();
			LoginService loginService = new LoginService();
			SignupService signupService = new SignupService();
			// Vòng lặp cho đến khi login thành công
			while (true) {
				// Đọc raw data
				RawData rd = gson.fromJson(dis.readUTF(), RawData.class);
				System.out.println(gson.toJson(rd));
				if (rd.getType().equals("LOGIN")) {
					clientId = loginService.checkLogin(rd);
					if (clientId == -1L) {
						ErrorHandler error = new ErrorHandler("LOGIN_ERR", "Sai tên tài khoản hoặc mật khẩu");
						error.sendToUser(dos, clientId);
					} else {
						loginService.sendInfoToNewOnlineClient(dos);
				}

				} else if (rd.getType().equals("SIGNUP")) {
					if (signupService.checkSignup(rd) == true) {
						// Thêm client mới vào server và client online
						// Thông báo đăng ký thành công
						signupService.sendOnlineNotifyToUser(dos, clientId);
					}

					else {
						signupService.sendErrorToUser(dos, clientId);
					}
				}
			}
			
		} catch (IOException e) {
			System.out.println("Out: " + clientId);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		// Khi user thoát ra
		try {
			LogoutService logoutService = new LogoutService();
			// Đóng các luồng input, output, set lại status, xoá khỏi danh sách online
			logoutService.removeClientInServer(socket, dis, dos, clientId);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
