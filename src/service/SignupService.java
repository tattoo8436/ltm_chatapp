package service;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import com.google.gson.Gson;

import data.*;
import error.ErrorHandler;
import repository.UserRepository;

public class SignupService {
	private UserInput user;
	private Gson gson;
	private UserRepository userRepository;
	
	public SignupService() throws SQLException {
		gson = new Gson();
		userRepository = new UserRepository();
	}
	
	//Check signup
	public boolean checkSignup(RawData rd) throws SQLException {
		user = gson.fromJson(rd.getData(), UserInput.class);
		// Check user
		Long id = userRepository.save(user);
		if (id == -1)
			return false;
		user.setId(id);
		return true;
	}

	public void sendOnlineNotifyToUser(DataOutputStream dos, Long clientId) throws IOException {
		RawData rd = new RawData("SIGNUP_OK", clientId, new Date().getTime(), null);
		dos.writeUTF(gson.toJson(rd));
	}

	public void sendErrorToUser(DataOutputStream dos, Long clientId) throws IOException {
		ErrorHandler errorHandle = new ErrorHandler("SIGNUP_ERR", "Đã tồn tại tài khoản");
		errorHandle.sendToUser(dos, clientId);
	}

}
