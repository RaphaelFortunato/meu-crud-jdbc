package conexaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConncetion {
	
	private static String url = "jdbc:postgresql://localhost:5434/meocrud";
	private static String senha = "admin";
	private static String usuario = "postgres";
	private static Connection connection = null;
	
	static {
		conectar();
	}
	
	public SingleConncetion() {
		conectar();
	}
	
	private static void conectar() {
		
		try {
			
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, usuario,senha);
				connection.setAutoCommit(false);
				
				System.out.println("conectado com sucess");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static Connection getConnection() {
		return connection;
	}

}
