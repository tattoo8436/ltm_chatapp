package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.*;

public class UserRepository {
	// id, username, password, name
	private Connection con = null;
	private PreparedStatement statement = null;
	private ResultSet rs = null;

	public UserInput findUser(String username, String password) throws SQLException {
		try {
			con = DataSource.getConnection();
			String sql = "select * from user where username = ? and password = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			rs = statement.executeQuery();
			if (rs.next() == false) {
				return null;
			}
			UserInput user = new UserInput(rs.getLong("id"), rs.getNString("username"), rs.getNString("password"),
					rs.getNString("fullname"));
			return user;
		} finally {
			DataSource.closeConnection(statement, rs, con);
		}
	}
	
	public UserInput findByUsername(String username) throws SQLException {
		try {
			con = DataSource.getConnection();
			String sql = "select * from user where username = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, username);
			rs = statement.executeQuery();
			if (rs.next() == false) {
				return null;
			}
			UserInput user = new UserInput(rs.getLong("id"), rs.getNString("username"), rs.getNString("password"),
					rs.getNString("fullname"));
			return user;
		} finally {
			DataSource.closeConnection(statement, rs, con);
		}
	}
	
	public Long save(UserInput user) throws SQLException {
		try {
			if (findByUsername(user.getUsername()) != null)
				return -1L;
			con = DataSource.getConnection();
			String sql = "insert into user (username, password, fullname) values (?, ?, ?)";
			statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFullName());
			statement.executeUpdate();
			rs = statement.getGeneratedKeys();
			if (rs.next() == true) {
				return rs.getLong(1);
			}
				
			return null;
		} finally {
			DataSource.closeConnection(statement, rs, con);
		}
	}

}