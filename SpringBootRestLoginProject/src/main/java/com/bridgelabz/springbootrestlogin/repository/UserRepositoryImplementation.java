package com.bridgelabz.springbootrestlogin.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bridgelabz.springbootrestlogin.model.User;

public class UserRepositoryImplementation implements UserRepository{

	
	public Connection getConnection() {
		Connection connection = null;

		String url = "jdbc:mysql://localhost:3306/user_database?user=root&password=root";

		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection(url);

		} catch (Exception e) {
			System.out.println("failed to establish connection");
		}
		return connection;
	}

	
	public void closeconnection(Connection connection, PreparedStatement preparesStmt, ResultSet rs) {
		if (rs != null) {
			try {
				preparesStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (preparesStmt != null) {
			try {
				preparesStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

		@Override
		public User getUserByUserName(User user) {
			
			Connection connection = null;
			PreparedStatement preparesStm = null;
			ResultSet resultset = null;
			User user1=null;
			try {
				String query = "select * from user where username=?";
				preparesStm = getConnection().prepareStatement(query);
				preparesStm.setString(1,user.getUsername());

				resultset = preparesStm.executeQuery();
				
				if (resultset.next()) {
				    user1 = new User();
					user1.setUsername(resultset.getString(1));
					user1.setPassward(resultset.getString(4));
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			closeconnection(connection, preparesStm, resultset);
			
			return user1;
		}
		


		@Override
		public boolean saveUser(User user) throws SQLException {
			
			System.out.println("before saving");
			ResultSet resultset = null;
			Connection connection = getConnection();
			PreparedStatement preparesStmt = null;
			String str = "insert into user values(?,?,?,?)";
			preparesStmt = getConnection().prepareStatement(str);
			preparesStmt.setString(1, user.getUsername());
			preparesStmt.setString(2, user.getEmail());
		    preparesStmt.setString(3, user.getPhone());
		    preparesStmt.setString(4, user.getPassward());
			preparesStmt.executeUpdate();
			closeconnection(connection, preparesStmt, resultset);
			return true;
			
		}
		}
