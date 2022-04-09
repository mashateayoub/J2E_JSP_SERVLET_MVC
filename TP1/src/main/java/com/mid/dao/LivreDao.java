
package com.mid.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mid.model.Livre;


public class LivreDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/db_library";
	private String jdbcUsername = "root";
	private String jdbcPassword = "ayoub1999";

	private static final String INSERT_LivreS_SQL = "INSERT INTO livre  (isbn , titre, editeur , date_edition , description , matricule) " + " VALUES "
			+ " (?, ?, ?, ?, ?, ?);";

	private static final String SELECT_Livre_BY_ISBN = "select * from livre where isbn =?";
//	private static final String SELECT_Livre_BY_TITRE = "select * from livre where titre like ?";
	private static final String SELECT_ALL_LivreS = "SELECT * FROM `livre` ;";
	private static final String DELETE_Livre = "delete from livre where isbn = ?;";
	private static final String UPDATE_Livre = "update livre set titre=?, editeur=? , date_edition=? , description=? , matricule=?  where isbn = ?;";


	public LivreDao() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			printSQLException(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void insertLivre(Livre Livre) throws SQLException {
		// try-with-resource statement will auto close the connection.
		try (
				Connection connection = getConnection();
				
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LivreS_SQL)) {
			preparedStatement.setInt(1, Livre.getIsbn());
			preparedStatement.setString(2, Livre.getTitre());
			preparedStatement.setString(3, Livre.getEditeur());
			long timeInMilliSeconds = Livre.getDate_edition().getTime();
	        java.sql.Date date = new java.sql.Date(timeInMilliSeconds);
			preparedStatement.setDate(4, date );
			preparedStatement.setString(5, Livre.getDescription());
			preparedStatement.setInt(6, Livre.getMatricule());
			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Livre selectLivre(int isbn) {
		Livre Livre = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Livre_BY_ISBN);) {
			preparedStatement.setInt(1, isbn);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String titre = rs.getString("titre");
				String editeur = rs.getString("editeur");
				Date date_edition = rs.getDate("date_edition");
				int matricule = rs.getInt("matricule");
				String description = rs.getString("description");
				Livre = new Livre(isbn, titre, editeur,description, date_edition, matricule);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return Livre;
	}
	

	public List<Livre> selectAllLivres() {

		List<Livre> Livres = new ArrayList<>();
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LivreS);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int isbn = rs.getInt("isbn");
				String titre = rs.getString("titre");
				String editeur = rs.getString("editeur");
				Date date_edition = rs.getDate("date_edition");
				int matricule = rs.getInt("matricule");
				String description = rs.getString("description");
				Livres.add( new Livre(isbn, titre, editeur,description, date_edition, matricule));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return Livres;
	}

	public boolean deleteLivre(int isbn) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_Livre);) {
			statement.setInt(1, isbn);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateLivre(Livre Livre){
		boolean rowUpdated=false;
		try {
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_Livre); 
			
			statement.setString(1, Livre.getTitre());
			statement.setString(2, Livre.getEditeur());
			long timeInMilliSeconds = Livre.getDate_edition().getTime();
	        java.sql.Date date = new java.sql.Date(timeInMilliSeconds);
			statement.setDate(3, date );
			statement.setString(4, Livre.getDescription());
			statement.setInt(5, Livre.getMatricule());
			statement.setInt(6, Livre.getIsbn());
			System.out.println(statement);
			rowUpdated = statement.executeUpdate() > 0;
		}
			catch (SQLException e) {
			printSQLException(e);
		}
		
		return rowUpdated;
	}
	

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


