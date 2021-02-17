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

import ekh.bean.PianoBean;

public class PianoModelDM implements ClassModel<PianoBean> {

	@Override
	public PianoBean doRetrieveByKey(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		PianoBean bean=new PianoBean();
		
		String selectSQL="SELECT * FROM piano WHERE id=?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, id);

			System.out.println("PianoModelDM: doRetrieveByKey:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setIdPacchetto(rs.getInt("id"));
				bean.setUsername(rs.getString("username"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setStato(rs.getString("stato"));
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
	public ArrayList<PianoBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<PianoBean> piani = new ArrayList<PianoBean>();

		String selectSQL = "SELECT * FROM piano";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			System.out.println("PianoModelDM: doRetrieveAll:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			

			while (rs.next()) {
				PianoBean bean=new PianoBean();

				bean.setId(rs.getInt("id"));
				bean.setIdPacchetto(rs.getInt("id"));
				bean.setUsername(rs.getString("username"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setStato(rs.getString("stato"));
				piani.add(bean);
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
		return piani;
	}

	@Override
	public void doSave(PianoBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL="INSERT INTO piano(idPacchetto, username, prezzo, stato) VALUES (?,?,?,?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setInt(1, bean.getIdPacchetto());
			preparedStatement.setString(2, bean.getUsername());
			preparedStatement.setDouble(3, bean.getPrezzo());
			preparedStatement.setString(4, bean.getStato());

			System.out.println("PianoModelDM: doSave:" + preparedStatement.toString());
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
	public void doUpdate(PianoBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE piano SET idPacchetto=?, username=?, prezzo=?, stato=? WHERE id=?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setInt(1, bean.getIdPacchetto());
			preparedStatement.setString(2, bean.getUsername());
			preparedStatement.setDouble(3, bean.getPrezzo());
			preparedStatement.setString(4, bean.getStato());
			preparedStatement.setInt(5, bean.getId());

			System.out.println("PianoModelDM: doUpdate:" + preparedStatement.toString());
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
	public void doDelete(PianoBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM piano WHERE id=?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);

			preparedStatement.setInt(1, bean.getId());

			System.out.println("PianoModelDM: doDelete:" + preparedStatement.toString());
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

	public synchronized static byte[] load(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		byte[] bt = null;
		String sql = "SELECT modulo FROM piano WHERE id=?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);

			System.out.println("PianoModelDM: loadModulo:" + preparedStatement.toString());
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				bt = rs.getBytes("modulo");
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

	public synchronized static void updateFile(String id, String modulo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Random r = new Random();
		int n = r.nextInt(999999);
		String nomeFile=String.format("%06d", n);
		
		// Creo un nuovo File
		try {
			File modFile = new File(nomeFile+".txt");
			if (modFile.createNewFile()) {
				System.out.println("PianoModelDM: File created: " + modFile.getName());
			} else {
				System.out.println("PianoModelDM: File already exists.");
			}
		} catch (IOException e) {
			System.out.println("PianoModelDM: An error occurred.");
			e.printStackTrace();
		}

		// Scrivo sul nuovo file
		try {
			FileWriter myWriter = new FileWriter(nomeFile+".txt");
			myWriter.write(modulo);
			myWriter.close();
			System.out.println("PianoModelDM: Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("PianoModelDM: An error occurred.");
			e.printStackTrace();
		}

		String sql = "UPDATE piano SET modulo = ? WHERE id = ?";

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
