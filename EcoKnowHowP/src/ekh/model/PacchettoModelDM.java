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
				bean.setId(rs.getString("id"));
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

				bean.setId(rs.getString("id"));
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

		String insertSQL = "INSERT INTO pacchetto(id, idMatrice, nome, descrizione, tipo, username, prezzo) VALUES (?,?,?,?,?,?,?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, bean.getId());
			preparedStatement.setInt(2, bean.getIdMatrice());
			preparedStatement.setString(3, bean.getNome());
			preparedStatement.setString(4, bean.getDescrizione());
			preparedStatement.setString(5, bean.getTipo());
			preparedStatement.setString(6, bean.getUsername());
			preparedStatement.setDouble(7, bean.getPrezzo());

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
			preparedStatement.setString(2, key);

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

		String selectSQL = "SELECT * FROM pacchetto WHERE tipo=?";

		if (username != null && !username.equals("")) {
			selectSQL += " AND username='" + username+"'";
		}
		if(idMatrice!=null && !idMatrice.equals("")) {
			selectSQL += " AND idMatrice='" + idMatrice+"'";
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setString(1, tipo);

			System.out.println("PacchettoModelDM: doRetrieveByType:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PacchettoBean bean = new PacchettoBean();

				bean.setId(rs.getString("id"));
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
	
	public boolean controlloId(String id)throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		PacchettoBean bean=new PacchettoBean();
		
		String selectSQL="SELECT id FROM pacchetto WHERE id=?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, id);

			System.out.println("PaccheModelDM: controlloId:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setUsername(rs.getString("id"));
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
		
		return bean.isEmpty();
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

	public synchronized static void updateContenuto(String id, String contenuto, String path) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Random r = new Random();
		int n = r.nextInt(999999);
		String nomeFile = path+String.format("%06d", n) + ".txt";

		// Creo un nuovo File
		try {
			File modFile = new File(nomeFile);
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
			FileWriter myWriter = new FileWriter(nomeFile);
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

			File file = new File(nomeFile);
			try {
				FileInputStream fis = new FileInputStream(file);
				preparedStatement.setBinaryStream(1, fis, fis.available());
				preparedStatement.setString(2, id);
				
				System.out.println("PacchettoModelDM: updateContenuto:" + preparedStatement.toString());
				preparedStatement.executeUpdate();
				connection.commit();
				fis.close();
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
		File modFile = new File(nomeFile);
		if (modFile.delete()) {
			System.out.println("PacchettoModelDM: Deleted the file: " + modFile.getName());
		} else {
			System.out.println("PacchettoModelDM: Failed to delete the file.");
		}
	}
}
