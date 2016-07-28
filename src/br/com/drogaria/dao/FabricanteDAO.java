package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.factory.ConnectFactory;

public class FabricanteDAO {
	public void salvar(Fabricante fabricante) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fabricante ");
		sql.append("(descricao) ");
		sql.append("VALUES (?)");

		Connection conexao = ConnectFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, fabricante.getDescricao());
		
		comando.executeUpdate();

	}
	
	public void excluir(Fabricante fabricante) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fabricante ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConnectFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, fabricante.getCodigo());
		
		comando.executeUpdate();
		
	}
	
	public void editar(Fabricante fabricante) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fabricante ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConnectFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, fabricante.getDescricao());
		comando.setLong(2, fabricante.getCodigo());

		comando.executeUpdate();
	}
	
	public Fabricante buscarPorCodigo(Fabricante fabricante) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConnectFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, fabricante.getCodigo());
		
		ResultSet resultado = comando.executeQuery();

		Fabricante fabricanteRetorno;
		
		if (resultado.next()) {
			fabricanteRetorno = new Fabricante();
			fabricanteRetorno.setCodigo(resultado.getLong("codigo"));
			fabricanteRetorno.setDescricao(resultado.getString("descricao"));

			return fabricanteRetorno;
		}
		return null;
		
	}

	public ArrayList<Fabricante> buscarPorDescricao(Fabricante fabricante) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConnectFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" + fabricante.getDescricao() + "%");

		ResultSet resultado = comando.executeQuery();

		ArrayList<Fabricante> listaFabricante = new ArrayList<Fabricante>();

		while (resultado.next()) {
			Fabricante f = new Fabricante();
			f.setCodigo(resultado.getLong("codigo"));
			f.setDescricao(resultado.getString("descricao"));

			listaFabricante.add(f);
		}
		return listaFabricante;

	}
	
	public ArrayList<Fabricante> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConnectFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Fabricante> listaFabricante = new ArrayList<Fabricante>();

		while (resultado.next()) {
			Fabricante f = new Fabricante();
			f.setCodigo(resultado.getLong("codigo"));
			f.setDescricao(resultado.getString("descricao"));
			;

			listaFabricante.add(f);
		}

		return listaFabricante;
	}

	/**
	 * Metodo main criado para testar execuções no banco de dados.
	 * @param args
	 */
	public static void main(String[] args) {
		/*Fabricante f1 = new Fabricante();
		Fabricante f2 = new Fabricante();

		f1.setDescricao("Descricao1");
		f2.setDescricao("Descricao2");

		FabricanteDAO fdao = new FabricanteDAO();
		try {
			fdao.salvar(f1);
			fdao.salvar(f2);

			System.out.println("Fabricante salvo com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Ocorreu erro ao tentar salvar Fabricante!");
		}
		;*/

		
		/*Fabricante f1 = new Fabricante();
		Fabricante f2 = new Fabricante();

		f1.setCodigo(3L);
		f2.setCodigo(4L);

		FabricanteDAO fdao = new FabricanteDAO();
		try {
			fdao.excluir(f1);
			fdao.excluir(f2);

			System.out.println("Fabricante excluído com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Ocorreu erro ao tentar excluir o Fabricante!");
		}
		;*/
		
		
		/*Fabricante f1 = new Fabricante();

		f1.setCodigo(5L);
		f1.setDescricao("descricao 5");

		FabricanteDAO fdao = new FabricanteDAO();
		try {
			fdao.editar(f1);

			System.out.println("Fabricante editado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Ocorreu erro ao tentar editar o Fabricante!");
		}
		;*/
		
		
		/*Fabricante f1 = new Fabricante();

		f1.setCodigo(5L);

		FabricanteDAO fdao = new FabricanteDAO();
		Fabricante retorno;
		try {
			retorno = new Fabricante();
			retorno = fdao.buscarPorCodigo(f1);
			System.out.println("Resultado: " + retorno);
			System.out.println("Consulta de Fabricante realizada com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Ocorreu erro ao tentar consultar Fabricante!");
		}
		;*/
		

		/*FabricanteDAO fdao = new FabricanteDAO();
		ArrayList<Fabricante> listaFabricante;
		try {
			listaFabricante = new ArrayList<Fabricante>();
			listaFabricante = fdao.listar();
			
			for (Fabricante f : listaFabricante) {
				System.out.println("Resultado: " + f);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Ocorreu erro ao tentar listar Fabricante!");
		}
		;*/
		
		
		Fabricante f1 = new Fabricante();
		f1.setDescricao("5");
		
		FabricanteDAO fdao = new FabricanteDAO();
		
		ArrayList<Fabricante> listaFabricante;
		try {
			listaFabricante = new ArrayList<Fabricante>();
			listaFabricante = fdao.buscarPorDescricao(f1);
			
			for (Fabricante f : listaFabricante) {
				System.out.println("Resultado: " + f);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Ocorreu erro ao tentar pesquisar Fabricante!");
		}
		;
	}
}
