package service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import com.google.gson.Gson;

import data.*;
import repository.*;

public class LoginService {
	private UserInput user;
	private Gson gson;
	private UserRepository userRepository;
	
	public LoginService() throws SQLException {
		gson = new Gson();
		userRepository = new UserRepository();
	}

	public Long checkLogin(RawData rd) throws IOException, SQLException {
		// Đọc user từ raw data
		user = gson.fromJson(rd.getData(), UserInput.class);
		// Check user
		UserInput userFound = userRepository.findUser(user.getUsername(), user.getPassword());
		if (userFound == null) //Không tìm thấy
			return -1L;
		user.setId(userFound.getId());
		user.setPassword(null);
		user.setFullName(userFound.getFullName());
		return userFound.getId();
	}
	
	public void sendInfoToNewOnlineClient(DataOutputStream dos) 
			throws IOException, SQLException {
		LoginOk loginOk = new LoginOk();
		String data = gson.toJson(loginOk);
		RawData rd = new RawData("LOGIN_OK", user.getId(), new Date().getTime(), data);
		dos.writeUTF(gson.toJson(rd));
	}

}





