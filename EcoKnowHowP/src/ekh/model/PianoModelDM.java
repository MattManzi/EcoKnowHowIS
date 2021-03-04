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
				bean.setUsername(rs.getString("username"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setStato(rs.getString("stato"));
				byte[] bt = rs.getBytes("referto");
				byte[] bt2 = rs.getBytes("schedaDatiSicurezza");
				
				if (bt == null) {
					bean.setReferto(false);
				} else {
					bean.setReferto(true);
				}
				if (bt2 == null) {
					bean.setSchedaDS(false);
				} else {
					bean.setSchedaDS(true);
				}
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
				bean.setUsername(rs.getString("username"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setStato(rs.getString("stato"));
				byte[] bt = rs.getBytes("referto");
				byte[] bt2 = rs.getBytes("schedaDatiSicurezza");
				
				if (bt == null) {
					bean.setReferto(false);
				} else {
					bean.setReferto(true);
				}
				if (bt2 == null) {
					bean.setSchedaDS(false);
				} else {
					bean.setSchedaDS(true);
				}
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
		
		String insertSQL="INSERT INTO piano(id, username, prezzo, stato) VALUES (?,?,?,?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, String.valueOf(bean.getId()));
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
	public void doUpdate(String dato, String str, String key) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE piano SET "+dato+"=? WHERE id=?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, str);
			preparedStatement.setString(2, key);

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
	public void doDelete(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM piano WHERE id=?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);

			preparedStatement.setString(1, id);

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
	
	public ArrayList<PianoBean> doRetrieveByUsername(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<PianoBean> piani = new ArrayList<PianoBean>();

		String selectSQL = "SELECT * FROM piano WHERE username=?";


		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			
			System.out.println("PianoModelDM: doRetrieveByUsername:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			

			while (rs.next()) {
				PianoBean bean=new PianoBean();

				bean.setId(rs.getInt("id"));
				bean.setUsername(rs.getString("username"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setStato(rs.getString("stato"));
				byte[] bt = rs.getBytes("referto");
				byte[] bt2 = rs.getBytes("schedaDatiSicurezza");
				
				if (bt == null) {
					bean.setReferto(false);
				} else {
					bean.setReferto(true);
				}
				if (bt2 == null) {
					bean.setSchedaDS(false);
				} else {
					bean.setSchedaDS(true);
				}
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
	
	public synchronized static byte[] loadContenuto(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		byte[] bt = null;
		String sql = "SELECT pacchetto FROM piano WHERE id=?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, id);

			System.out.println("PianoModelDM: loadContenuto" + preparedStatement.toString());
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				bt = rs.getBytes("pacchetto");
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
			FileWriter myWriter = new FileWriter(nomeFile);
			myWriter.write(contenuto);
			myWriter.close();
			System.out.println("PianoModelDM: Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("PianoModelDM: An error occurred.");
			e.printStackTrace();
		}

		String sql = "UPDATE piano SET pacchetto = ? WHERE id = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			File file = new File(nomeFile);
			try {
				FileInputStream fis = new FileInputStream(file);
				preparedStatement.setBinaryStream(1, fis, fis.available());
				preparedStatement.setString(2, id);
				
				System.out.println("PianoModelDM: updateContenuto:" + preparedStatement.toString());
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
			System.out.println("PianoModelDM: Deleted the file: " + modFile.getName());
		} else {
			System.out.println("PianoModelDM: Failed to delete the file.");
		}
	}

}
