package controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import model.*;
import view.*;
import view.SignupForm;

public class MyThread extends Thread {

    public static Long id;
    public static DataInputStream dis;
    public static DataOutputStream dos;
    public static Socket socket;
    public static List<User> dataUser;
    public static String name;
    public static ChatForm chat;
    public static LoginForm login;
    public static SignupForm signup;

    @Override
    public void run() {
        try {
            Gson gson = new Gson();
            //Khởi tạo socket
            byte[] address = new byte[4];
            address[0] = -64;
            address[1] = -88;
            address[2] = 110;
            address[3] = -99;
            InetAddress ip = InetAddress.getByAddress(address);
            //socket = new Socket(ip, 9999);
            socket = new Socket("26.192.253.140", 9999);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            //Giao diện đăng nhập
            login = new LoginForm();
            login.setVisible(true);
            while (true) {
                //Lay data server gui ve
                String dataAll = dis.readUTF();
                System.out.println(dataAll);
                RawData rawData = gson.fromJson(dataAll, RawData.class);
                switch (rawData.getType()) {
                    //Đăng nhập thành công
                    case "LOGIN_OK":
                        //Lấy data
                        String data = rawData.getData();
                        LoginOk loginOk = gson.fromJson(data, LoginOk.class);
                        id = loginOk.getId();
                        name = loginOk.getName();
                        dataUser = loginOk.getUserList();

                        //Chuyen sang giao dien chat
                        chat = new ChatForm();
                        chat.setVisible(true);
                        login.setVisible(false);
                        break;
                    //Đăng ký thành công
                    case "SIGNUP_OK":
                        LoginForm.signup.showSuccessful();
                        break;
                    case "ERROR":
                        data = rawData.getData();
                        Errors errors = gson.fromJson(data, Errors.class);
                        //Đăng ký lỗi
                        if (errors.getCode().equals("SIGNUP_ERR")) {
                            LoginForm.signup.showError();
                        }
                        //Đăng nhập lỗi
                        if (errors.getCode().equals("LOGIN_ERR")) {
                            login.showError();
                        }
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
