package ekh.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
				bean.setIdPacchetto(rs.getInt("id"));
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
				bean.setIdPacchetto(rs.getInt("id"));
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

}
