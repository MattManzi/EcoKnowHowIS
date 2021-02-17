package ekh.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ekh.bean.AccountBean;

public class AccountModelDM implements ClassModel<AccountBean> {

	@Override
	public AccountBean doRetrieveByKey(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		AccountBean bean=new AccountBean();
		
		String selectSQL="SELECT * FROM account WHERE username=?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);

			System.out.println("AccountModelDM: doRetrieveByKey:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setUsername(rs.getString("username"));
				bean.setEmail(rs.getString("email"));	
				bean.setPassword(rs.getString("password"));
				bean.setCodSicurezza(rs.getString("codSicurezza"));
				bean.setTipo(rs.getString("tipo"));
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
	public ArrayList<AccountBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<AccountBean> accounts = new ArrayList<AccountBean>();

		String selectSQL = "SELECT * FROM account";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			System.out.println("AccountModelDM: doRetrieveAll:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			

			while (rs.next()) {
				AccountBean bean=new AccountBean();
				
				bean.setUsername(rs.getString("username"));
				bean.setEmail(rs.getString("email"));		
				bean.setPassword(rs.getString("password"));
				bean.setCodSicurezza(rs.getString("codSicurezza"));
				bean.setTipo(rs.getString("tipo"));
				accounts.add(bean);
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
		return accounts;
	}

	@Override
	public void doSave(AccountBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL="INSERT INTO account(username, email, password, codSicurezza, tipo) VALUES (?,?,?,?,?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, bean.getUsername());
			preparedStatement.setString(2, bean.getEmail());
			preparedStatement.setString(3, bean.getPassword());
			preparedStatement.setString(4, bean.getCodSicurezza());
			preparedStatement.setString(5, bean.getTipo());

			System.out.println("AccountModelDM: doSave:" + preparedStatement.toString());
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
	public void doUpdate(AccountBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE account SET username=?, email=?, password=?, codSicurezza=?, tipo=? WHERE username=?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, bean.getUsername());
			preparedStatement.setString(2, bean.getEmail());
			preparedStatement.setString(3, bean.getPassword());
			preparedStatement.setString(4, bean.getCodSicurezza());
			preparedStatement.setString(5, bean.getTipo());
			preparedStatement.setString(6, bean.getUsername());

			System.out.println("AccountModelDM: doUpdate:" + preparedStatement.toString());
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
	public void doDelete(AccountBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM account WHERE username=?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);

			preparedStatement.setString(1, bean.getUsername());

			System.out.println("AccountModelDM: doDelete:" + preparedStatement.toString());
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
