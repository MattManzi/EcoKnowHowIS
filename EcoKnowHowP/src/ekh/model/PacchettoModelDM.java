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
import java.util.ArrayList;
import java.util.Random;

import ekh.bean.PacchettoBean;

public class PacchettoModelDM implements ClassModel<PacchettoBean> {

	@Override
	public PacchettoBean doRetrieveByKey(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		PacchettoBean bean = new PacchettoBean();

		String selectSQL = "SELECT * FROM pacchetto WHERE id=?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, id);

			System.out.println("PacchettoModelDM: doRetrieveByKey:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setIdMatrice(rs.getInt("idMAtrice"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setTipo(rs.getString("tipo"));
				bean.setUsername(rs.getString("username"));
				bean.setPrezzo(rs.getDouble("prezzo"));
			}
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bean;
	}

	@Override
	public ArrayList<PacchettoBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<PacchettoBean> pacchetti = new ArrayList<PacchettoBean>();

		String selectSQL = "SELECT * FROM pacchetto";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			System.out.println("PacchettoModelDM: doRetrieveAll:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PacchettoBean bean = new PacchettoBean();

				bean.setId(rs.getInt("id"));
				bean.setIdMatrice(rs.getInt("idMAtrice"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setTipo(rs.getString("tipo"));
				bean.setUsername(rs.getString("username"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				pacchetti.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return pacchetti;
	}

	@Override
	public void doSave(PacchettoBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO pacchetto(idMatrice, nome, descrizione, tipo, username, prezzo) VALUES (?,?,?,?,?,?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, bean.getNome());
			preparedStatement.setString(2, bean.getNome());
			preparedStatement.setString(3, bean.getDescrizione());
			preparedStatement.setString(4, bean.getTipo());
			preparedStatement.setString(5, bean.getUsername());
			preparedStatement.setDouble(6, bean.getPrezzo());

			System.out.println("PacchettoModelDM: doSave:" + preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	@Override
	public void doUpdate(String dato, String str, String key) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE pacchetto SET " + dato + "=? WHERE id=?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, str);
			preparedStatement.setString(1, key);

			System.out.println("PacchettoModelDM: doUpdate:" + preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	@Override
	public void doDelete(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM pacchetto WHERE id=?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);

			preparedStatement.setString(1, id);

			System.out.println("PacchettoModelDM: doDelete:" + preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	public ArrayList<PacchettoBean> doRetrieveForUser(String username, String tipo, String idMatrice)
			throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<PacchettoBean> pacchetti = new ArrayList<PacchettoBean>();

		String selectSQL = "SELECT * FROM pacchetto WHERE tipo=? AND idMatrice=?";

		if (username != null && !username.equals("")) {
			selectSQL += " AND username=" + username;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setString(1, tipo);
			preparedStatement.setString(2, idMatrice);

			System.out.println("PacchettoModelDM: doRetrieveByType:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PacchettoBean bean = new PacchettoBean();

				bean.setId(rs.getInt("id"));
				bean.setIdMatrice(rs.getInt("idMAtrice"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setTipo(rs.getString("tipo"));
				bean.setUsername(rs.getString("username"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				pacchetti.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return pacchetti;
	}

	public synchronized static byte[] loadContenuto(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		byte[] bt = null;
		String sql = "SELECT contenuto FROM pacchetto WHERE id=?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, id);

			System.out.println("PacchettoModelDM: loadContenuto" + preparedStatement.toString());
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

	public synchronized static void updateContenuto(int id, String contenuto) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Random r = new Random();
		int n = r.nextInt(999999);
		String nomeFile = String.format("%06d", n);

		// Creo un nuovo File
		try {
			File modFile = new File(nomeFile + ".txt");
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
			FileWriter myWriter = new FileWriter(nomeFile + ".txt");
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

			File file = new File(nomeFile + ".txt");
			try {
				FileInputStream fis = new FileInputStream(file);
				preparedStatement.setBinaryStream(1, fis, fis.available());
				preparedStatement.setInt(2, id);

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
		// Elimino il file
		File modFile = new File(nomeFile + ".txt");
		if (modFile.delete()) {
			System.out.println("Deleted the file: " + modFile.getName());
		} else {
			System.out.println("Failed to delete the file.");
		}
	}
}
