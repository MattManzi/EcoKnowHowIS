package ekh.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ekh.bean.ParametroBean;

public class ParametroModelDM implements ClassModel<ParametroBean> {

	@Override
	public ParametroBean doRetrieveByKey(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ParametroBean bean=new ParametroBean();
		
		String selectSQL="SELECT * FROM parametro WHERE id=?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, id);

			System.out.println("ParametroModelDM: doRetrieveByKey:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {				
				bean.setId(rs.getInt("id"));	
				bean.setIdMatrice(rs.getInt("idMatrice"));	
				bean.setNome(rs.getString("nome"));	
				bean.setSku(rs.getString("sku"));	
				bean.setLimiteEmissione(rs.getString("limiteEmissione"));	
				bean.setuMisura(rs.getString("uMisura"));	
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
	public ArrayList<ParametroBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ParametroBean> parametri = new ArrayList<ParametroBean>();

		String selectSQL = "SELECT * FROM parametro";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			System.out.println("ParametroModelDM: doRetrieveAll:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			

			while (rs.next()) {
				ParametroBean bean=new ParametroBean();
				
				bean.setId(rs.getInt("id"));	
				bean.setIdMatrice(rs.getInt("idMatrice"));	
				bean.setNome(rs.getString("nome"));	
				bean.setSku(rs.getString("sku"));	
				bean.setLimiteEmissione(rs.getString("limiteEmissione"));	
				bean.setuMisura(rs.getString("uMisura"));	
				bean.setPrezzo(rs.getDouble("prezzo"));	
				parametri.add(bean);
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
		return parametri;
	}

	@Override
	public void doSave(ParametroBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL="INSERT INTO parametro(idMatrice, nome, sku, limiteEmissione, uMisura, prezzo ) VALUES (?,?,?,?,?,?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setInt(1, bean.getIdMatrice());
			preparedStatement.setString(2, bean.getNome());
			preparedStatement.setString(3, bean.getSku());
			preparedStatement.setString(4, bean.getLimiteEmissione());
			preparedStatement.setString(5, bean.getuMisura());
			preparedStatement.setDouble(6, bean.getPrezzo());

			System.out.println("ParametroModelDM: doSave:" + preparedStatement.toString());
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

		String updateSQL = "UPDATE parametro SET "+dato+"=? WHERE id=?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, str);
			preparedStatement.setString(2, key);

			System.out.println("ParametroModelDM: doUpdate:" + preparedStatement.toString());
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

		String deleteSQL = "DELETE FROM parametro WHERE id=?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);

			preparedStatement.setString(1, id);

			System.out.println("ParametroModelDM: doDelete:" + preparedStatement.toString());
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
	
	public ArrayList<ParametroBean> doRetrieveByMatrix(String idMatrice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ParametroBean> parametri = new ArrayList<ParametroBean>();

		String selectSQL = "SELECT * FROM parametro WHERE idMatrice=?";


		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, idMatrice);
			
			System.out.println("ParametroModelDM: doRetrieveAll:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			

			while (rs.next()) {
				ParametroBean bean=new ParametroBean();
				
				bean.setId(rs.getInt("id"));	
				bean.setIdMatrice(rs.getInt("idMatrice"));	
				bean.setNome(rs.getString("nome"));	
				bean.setSku(rs.getString("sku"));		
				bean.setLimiteEmissione(rs.getString("limiteEmissione"));	
				bean.setuMisura(rs.getString("uMisura"));	
				bean.setPrezzo(rs.getDouble("prezzo"));	
				parametri.add(bean);
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
		return parametri;
	}

}
