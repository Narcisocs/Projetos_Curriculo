package br.unifor.ads.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DATABASE_URL = "jdbc:sqlserver://localhost:1433;databaseName=PIN4WEB";
	private static Connection con = null;
    
    public Conexao() throws SQLException {
    	
    }
    
	public static Connection getConnection() throws SQLException {
		
		/*Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/PIN4WEB";
		String user = "root";
		String password = "";

		// abri a conexao
		con = DriverManager.getConnection(url, user, password);*/
		
		try {
			Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DATABASE_URL, "sa", "narcisocs");
            System.out.println("Conectado");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
		return con;
	}
}
