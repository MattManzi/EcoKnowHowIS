package ekh.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ekh.bean.ClienteBean;

public class ClienteModelDM implements ClassModel<ClienteBean> {

	@Override
	public ClienteBean doRetrieveByKey(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ClienteBean bean=new ClienteBean();
		
		String selectSQL="SELECT * FROM cliente WHERE username=?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);

			System.out.println("ClienteModelDM: doRetrieveByKey:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setUsername(rs.getString("username"));
				bean.setNome(rs.getString("nome"));	
				bean.setCognome(rs.getString("cognome"));
				bean.setFunzioneAziendale(rs.getString("funzioneAziendale"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setRagioneSociale(rs.getString("ragioneSociale"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setpIva(rs.getString("pIva"));
				bean.setCf(rs.getString("cf"));
				bean.setPec(rs.getString("pec"));
				bean.setSdi(rs.getString("sdi"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setCodSicurezza(rs.getString("codSicurezza"));
				bean.setAttivo(rs.getInt("attivo"));
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
	public ArrayList<ClienteBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ClienteBean> clienti = new ArrayList<ClienteBean>();

		String selectSQL = "SELECT * FROM cliente";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			System.out.println("ClienteModelDM: doRetrieveAll:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			

			while (rs.next()) {
				ClienteBean bean=new ClienteBean();
				
				bean.setUsername(rs.getString("email"));
				bean.setNome(rs.getString("nome"));	
				bean.setCognome(rs.getString("cognome"));
				bean.setFunzioneAziendale(rs.getString("funzioneAziendale"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setRagioneSociale(rs.getString("ragioneSociale"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setpIva(rs.getString("pIva"));
				bean.setCf(rs.getString("cf"));
				bean.setPec(rs.getString("pec"));
				bean.setSdi(rs.getString("sdi"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setCodSicurezza(rs.getString("codSicurezza"));
				bean.setAttivo(rs.getInt("attivo"));
				clienti.add(bean);
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
		return clienti;
	}

	@Override
	public void doSave(ClienteBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL="INSERT INTO cliente(email, nome, cognome, funzioneAziendale, telefono, ragioneSociale, indirizzo, pIva, cf, pce, sdi, email, password, codSicurezza, attivo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, bean.getUsername());
			preparedStatement.setString(2, bean.getNome());
			preparedStatement.setString(3, bean.getCognome());
			preparedStatement.setString(4, bean.getFunzioneAziendale());
			preparedStatement.setString(5, bean.getTelefono());
			preparedStatement.setString(6, bean.getRagioneSociale());
			preparedStatement.setString(7, bean.getIndirizzo());
			preparedStatement.setString(8, bean.getpIva());
			preparedStatement.setString(9, bean.getCf());
			preparedStatement.setString(10, bean.getPec());
			preparedStatement.setString(11, bean.getSdi());
			preparedStatement.setString(12, bean.getEmail());
			preparedStatement.setString(13, bean.getPassword());
			preparedStatement.setString(14, bean.getCodSicurezza());
			preparedStatement.setInt(15, bean.getAttivo());

			System.out.println("ClienteModelDM: doSave:" + preparedStatement.toString());
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

		String updateSQL = "UPDATE cliente SET "+dato+"=? WHERE username=?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, str);
			preparedStatement.setString(2, key);

			System.out.println("ClienteModelDM: doUpdate:" + preparedStatement.toString());
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
	public void doDelete(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM cliente WHERE username=?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);

			preparedStatement.setString(1, username);

			System.out.println("ClienteModelDM: doDelete:" + preparedStatement.toString());
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
	
	public ClienteBean verificaLogin(String username, String password)throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ClienteBean bean=new ClienteBean();
		
		String selectSQL="SELECT * FROM cliente WHERE username=? AND password=?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			System.out.println("ClienteModelDM: doRetrieveByKey:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setUsername(rs.getString("username"));
				bean.setNome(rs.getString("nome"));	
				bean.setCognome(rs.getString("cognome"));
				bean.setFunzioneAziendale(rs.getString("funzioneAziendale"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setRagioneSociale(rs.getString("ragioneSociale"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setpIva(rs.getString("pIva"));
				bean.setCf(rs.getString("cf"));
				bean.setPec(rs.getString("pec"));
				bean.setSdi(rs.getString("sdi"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setCodSicurezza(rs.getString("codSicurezza"));
				bean.setAttivo(rs.getInt("attivo"));
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
	
	public boolean controlloDato(String dato, String str)throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ClienteBean bean=new ClienteBean();
		
		String selectSQL="SELECT username FROM cliente WHERE "+dato+"=?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, str);

			System.out.println("ClienteModelDM: controlloDato:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setUsername(rs.getString("username"));
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
	
	public ClienteBean doRetrieveByEmail(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ClienteBean bean=new ClienteBean();
		
		String selectSQL="SELECT * FROM cliente WHERE email=?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);

			System.out.println("ClienteModelDM: doRetrieveByKey:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setUsername(rs.getString("username"));
				bean.setNome(rs.getString("nome"));	
				bean.setCognome(rs.getString("cognome"));
				bean.setFunzioneAziendale(rs.getString("funzioneAziendale"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setRagioneSociale(rs.getString("ragioneSociale"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setpIva(rs.getString("pIva"));
				bean.setCf(rs.getString("cf"));
				bean.setPec(rs.getString("pec"));
				bean.setSdi(rs.getString("sdi"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setCodSicurezza(rs.getString("codSicurezza"));
				bean.setAttivo(rs.getInt("attivo"));
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
}
