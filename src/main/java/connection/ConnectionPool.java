package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import exceptions.CouponSystemException;

/**
 * @author D.Neumark
 *
 */
public class ConnectionPool {

	private static ConnectionPool instance = null;
	public static final int MAX_CON = 10;

	private Set<Connection> connections = new HashSet<>();
	private Set<Connection> connectionsB = new HashSet<>();

	private String dbName = "sql11218392";
	private String userName = "sql11218392";
	private String password = "iWtqKFAmc3";
	private String hostname = "sql11.freemysqlhosting.net";
	private String port = "3306";
	private String url = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password="
			+ password;
	// private String url =
	// "jdbc:mysql://localhost:3306/db1?autoReconnect=true&useSSL=false";

	private ConnectionPool() {
		
		String driverName = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e1) {
			System.out.println(e1.getMessage());

		}

		for (int i = 0; i < MAX_CON; i++) {
			try {
				Connection con = DriverManager.getConnection(url);
				// Connection con = DriverManager.getConnection(url, "root", "root");
				connections.add(con);
				connectionsB.add(con);
			} catch (SQLException e) {
				System.out.println(e.getMessage());

			}
		}
	}

	public synchronized static ConnectionPool getInstance() // throws CouponSystemException
	{
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}

	public synchronized Connection getConnection() // throws CouponSystemException
	{
		while (connections.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}

		Iterator<Connection> it = connections.iterator();
		Connection con = it.next();
		it.remove();
		return con;
	}

	public synchronized void returnConnection(Connection con) {
		connections.add(con);
		notify();

	}

	public void closeAllConnections() throws CouponSystemException {
		for (Connection connection : connectionsB) {
			try {
				connection.close();
			} catch (SQLException e) {
				CouponSystemException ex = new CouponSystemException("Can't close the connection.", e);
				throw ex;
			}
		}
	}

}
