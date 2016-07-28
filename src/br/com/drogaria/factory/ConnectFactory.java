package br.com.drogaria.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectFactory {
	private static final String USUARIO = "root";
	private static final String SENHA = "root";
	private static final String URL = "jdbc:mysql://localhost:3306/drogaria";

	public static Connection conectar() throws SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection conexao;
		conexao = DriverManager.getConnection(URL, USUARIO, SENHA);

		return conexao;
	}
	
	public static void main(String[] args) {
		try {
			Connection conexao = ConnectFactory.conectar();
			System.out.println("Conexão realizada com sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Não foi possível realizar a conexão");
		}
	}
}
