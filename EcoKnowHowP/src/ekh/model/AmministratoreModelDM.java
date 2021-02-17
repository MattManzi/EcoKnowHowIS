package ekh.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ekh.bean.AmministratoreBean;

public class AmministratoreModelDM implements ClassModel<AmministratoreBean> {

	@Override
	public AmministratoreBean doRetrieveByKey(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		AmministratoreBean bean=new AmministratoreBean();
		
		String selectSQL="SELECT * FROM amministratore WHERE username=?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);

			System.out.println("AmministratoreModelDM: doRetrieveByKey:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setUsername(rs.getString("username"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setCodSicurezza(rs.getString("codSicurezza"));
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
	public ArrayList<AmministratoreBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<AmministratoreBean> amministratori = new ArrayList<AmministratoreBean>();

		String selectSQL = "SELECT * FROM amministratore";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			System.out.println("AmministratoreModelDM: doRetrieveAll:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			

			while (rs.next()) {
				AmministratoreBean bean=new AmministratoreBean();
				
				bean.setUsername(rs.getString("email"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setCodSicurezza(rs.getString("codSicurezza"));
				amministratori.add(bean);
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
		return amministratori;
	}

	@Override
	public void doSave(AmministratoreBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL="INSERT INTO amministratore(email, email, password, codSicurezza) VALUES (?,?,?,?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, bean.getUsername());
			preparedStatement.setString(2, bean.getEmail());
			preparedStatement.setString(3, bean.getPassword());
			preparedStatement.setString(4, bean.getCodSicurezza());

			System.out.println("AmministratoreModelDM: doSave:" + preparedStatement.toString());
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
	public void doUpdate(AmministratoreBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE amministratore SET username=?, email=?, password=?, codSicurezza=? WHERE username=?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, bean.getUsername());	
			preparedStatement.setString(2, bean.getEmail());
			preparedStatement.setString(3, bean.getPassword());
			preparedStatement.setString(4, bean.getCodSicurezza());
			preparedStatement.setString(5, bean.getUsername());

			System.out.println("AmministratoreModelDM: doUpdate:" + preparedStatement.toString());
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
	public void doDelete(AmministratoreBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM amministratore WHERE username=?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);

			preparedStatement.setString(1, bean.getUsername());

			System.out.println("AmministratoreModelDM: doDelete:" + preparedStatement.toString());
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
}
