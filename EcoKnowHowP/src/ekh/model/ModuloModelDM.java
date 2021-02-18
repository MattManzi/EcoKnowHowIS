package ekh.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class ModuloModelDM {
	public synchronized static byte[] load(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		byte[] bt = null;
		String sql = "SELECT contenuto FROM pacchetto WHERE id=?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			System.out.println("PacchettoModelDM: loadContenuto:" + preparedStatement.toString());
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				bt = rs.getBytes("contenuto");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException sqlException) {
				System.out.println(sqlException);
			} finally {
				if (connection != null)
					DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bt;
	}

	public synchronized static void updateFile(String id, String contenuto) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Random r = new Random();
		int n = r.nextInt(999999);
		String nomeFile=String.format("%06d", n);
		
		// Creo un nuovo File
		try {
			File modFile = new File(nomeFile+".txt");
			if (modFile.createNewFile()) {
				System.out.println("PacchettoModelDM: File created: " + modFile.getName());
			} else {
				System.out.println("PacchettoModelDM: File already exists.");
			}
		} catch (IOException e) {
			System.out.println("PacchettoModelDM: An error occurred.");
			e.printStackTrace();
		}

		// Scrivo sul nuovo file
		try {
			FileWriter myWriter = new FileWriter(nomeFile+".txt");
			myWriter.write(contenuto);
			myWriter.close();
			System.out.println("PacchettoModelDM: Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("PacchettoModelDM: An error occurred.");
			e.printStackTrace();
		}

		String sql = "UPDATE pacchetto SET contenuto = ? WHERE id = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			File file = new File(nomeFile+".txt");
			try {
				FileInputStream fis = new FileInputStream(file);
				preparedStatement.setBinaryStream(1, fis, fis.available());
				preparedStatement.setString(2, id);

				preparedStatement.executeUpdate();
				connection.commit();
			} catch (FileNotFoundException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException sqlException) {
				System.out.println(sqlException);
			} finally {
				if (connection != null)
					DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
}
