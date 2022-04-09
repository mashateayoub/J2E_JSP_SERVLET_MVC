package com.mid.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mid.model.User;


public class UserDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/db_library";
	private String jdbcUsername = "root";
	private String jdbcPassword = "ayoub1999";

	private static final String INSERT_USERS_SQL = "INSERT INTO user" + "  (login, password, role) VALUES "
			+ " (?, ?, ? );";

	private static final String SELECT_USER_BY_LOGIN_PASSWORD = "select login, password, role from user where login =? and password=?";
//	private static final String SELECT_ALL_USERS = "select * from user";
//	private static final String DELETE_USERS_SQL = "delete from users where login = ?;";
//	private static final String UPDATE_USERS_SQL = "update users set appoge = ?,nom = ?,prenom = ?,email= ?, password =? where appoge = ?;";

	public UserDao() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertUser(User user) throws SQLException {
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getRole());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public User selectUser(String login,String password) {
		User user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN_PASSWORD);) {
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String loginuser = rs.getString("login");
				String passworduser = rs.getString("password");
				String roleuser = rs.getString("role");
				user = new User(loginuser, passworduser, roleuser);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	
	
	
	
//	public List<User> selectAllUsers() {
//		User user =null;
//		// using try-with-resources to avoid closing resources (boiler plate code)
//		List<User> users = new ArrayList<>();
//		// Step 1: Establishing a Connection
//		try (Connection connection = getConnection();
//
//				// Step 2:Create a statement using connection object
//			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
//			System.out.println(preparedStatement);
//			// Step 3: Execute the query or update query
//			ResultSet rs = preparedStatement.executeQuery();
//
//			// Step 4: Process the ResultSet object.
//			while (rs.next()) {
//				int appoge = rs.getInt("appoge");
//				String nom = rs.getString("nom");
//				String prenom = rs.getString("prenom");
//				String email = rs.getString("email");
//				String password = rs.getString("password");
//				user= new User(appoge, nom, prenom, email,password);
//				users.add(user);
//			}
//		} catch (SQLException e) {
//			printSQLException(e);
//		}
//		return users;
//	}

//	public boolean deleteUser(int id) throws SQLException {
//		boolean rowDeleted;
//		try (Connection connection = getConnection();
//				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
//			statement.setInt(1, id);
//			rowDeleted = statement.executeUpdate() > 0;
//		}
//		return rowDeleted;
//	}
//
//	public boolean updateUser(User user) throws SQLException {
//		boolean rowUpdated;
//		try (Connection connection = getConnection();
//				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
//			statement.setInt(1, user.getAppoge());
//			statement.setString(2, user.getNom());
//			statement.setString(3, user.getPrenom());
//			statement.setString(4, user.getEmail());
//			statement.setString(5, user.getPassword());
//			statement.setInt(6, user.getAppoge());
//
//			rowUpdated = statement.executeUpdate() > 0;
//		}
//		return rowUpdated;
//	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
