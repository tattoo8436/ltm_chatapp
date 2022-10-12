package error;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import com.google.gson.Gson;

import data.RawData;

public class ErrorHandler implements Serializable {
	private static final long serialVersionUID = 7702928382574108637L;
	private String code;
	private String errorContent;

	// Xử lý lỗi và gửi tới user
	public void sendToUser(DataOutputStream dos, Long clientId) throws IOException {
		Gson gson = new Gson();
		String data = gson.toJson(this);
		System.out.println(data);
		RawData returnClientData = new RawData();
		returnClientData.setClientId(clientId);
		returnClientData.setDate(new Date().getTime());
		returnClientData.setType("ERROR");
		returnClientData.setData(data);
		System.out.println(gson.toJson(returnClientData));
		dos.writeUTF(gson.toJson(returnClientData));
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getErrorContent() {
		return errorContent;
	}

	public void setErrorContent(String errorContent) {
		this.errorContent = errorContent;
	}

	public ErrorHandler(String code, String errorContent) {
		super();
		this.code = code;
		this.errorContent = errorContent;
	}

	public ErrorHandler() {
		super();
	}
}
