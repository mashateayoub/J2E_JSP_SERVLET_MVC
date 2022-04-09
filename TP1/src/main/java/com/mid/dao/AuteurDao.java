
package com.mid.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mid.model.Auteur;


public class AuteurDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/db_library";
	private String jdbcUsername = "root";
	private String jdbcPassword = "ayoub1999";

	private static final String INSERT_AuteurS_SQL = "INSERT INTO Auteur  (matricule , nom, prenom , genre ) " + " VALUES "
			+ " (?, ?, ?, ?);";

	private static final String SELECT_Auteur_BY_MATR= "select * from Auteur where matricule =?";
	private static final String SELECT_ALL_AuteurS = "SELECT * FROM `Auteur` ;";
	private static final String DELETE_Auteur = "delete from Auteur where matricule = ?;";
	private static final String UPDATE_Auteur = "update Auteur set matricule=?, nom=? , prenom=? , genre=?   where matricule = ?;";


	public AuteurDao() {
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

	public void insertAuteur(Auteur Auteur) throws SQLException {
		// try-with-resource statement will auto close the connection.
		try (
				Connection connection = getConnection();
				
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AuteurS_SQL)) {
			preparedStatement.setInt(1, Auteur.getMatricule());
			preparedStatement.setString(2, Auteur.getNom());
			preparedStatement.setString(3, Auteur.getPrenom());
			preparedStatement.setString(4, Auteur.getGenre() );
			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Auteur selectAuteur(int matr) {
		Auteur Auteur = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Auteur_BY_MATR);) {
			preparedStatement.setInt(1, matr);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int matricule = rs.getInt("matricule");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String genre = rs.getString("genre");
				Auteur = new Auteur(matricule, nom , prenom, genre);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return Auteur;
	}
	

	public List<Auteur> selectAllAuteurs() {

		List<Auteur> Auteurs = new ArrayList<>();
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_AuteurS);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int matr = rs.getInt("matricule");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String genre = rs.getString("genre");
				Auteurs.add( new Auteur(matr, nom , prenom, genre));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return Auteurs;
	}

	public boolean deleteAuteur(int matr) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_Auteur);) {
			statement.setInt(1, matr);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateAuteur(Auteur Auteur){
		boolean rowUpdated=false;
		try {
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_Auteur); 
			
			statement.setInt(1, Auteur.getMatricule());
			statement.setString(2, Auteur.getNom());
			statement.setString(3, Auteur.getPrenom());
			statement.setString(4, Auteur.getGenre() );
			statement.setInt(5, Auteur.getMatricule());

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


