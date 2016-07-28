package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.factory.ConnectFactory;

public class ProdutoDAO {
	public void salvar(Produto produto) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produto ");
		sql.append("(descricao, preco, quantidade, fabricante_codigo) ");
		sql.append("VALUES (?, ?, ?, ?)");

		Connection conexao = ConnectFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, produto.getDescricao());
		comando.setDouble(2, produto.getPreco());
		comando.setLong(3, produto.getQuantidade());
		comando.setLong(4, produto.getFabricante().getCodigo());
		
		comando.executeUpdate();

	}
	
	public void excluir(Produto produto) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produto ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConnectFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, produto.getCodigo());
		
		comando.executeUpdate();
		
	}
	
	public void editar(Produto produto) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produto ");
		sql.append("SET descricao = ?, quantidade = ?, preco = ?, fabricante_codigo = ? ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConnectFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, produto.getDescricao());
		comando.setLong(2, produto.getQuantidade());
		comando.setDouble(3, produto.getPreco());
		comando.setLong(4, produto.getFabricante().getCodigo());
		comando.setLong(5, produto.getCodigo());

		comando.executeUpdate();
	}
	
	public Produto buscarPorCodigo(Produto produto) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM produto ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConnectFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, produto.getCodigo());
		
		ResultSet resultado = comando.executeQuery();

		Produto produtoRetorno;
		
		if (resultado.next()) {
			produtoRetorno = new Produto();
			produtoRetorno.setCodigo(resultado.getLong("codigo"));
			produtoRetorno.setDescricao(resultado.getString("descricao"));

			return produtoRetorno;
		}
		return null;
		
	}

	public ArrayList<Produto> buscarPorDescricao(Produto produto) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM produto ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConnectFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" + produto.getDescricao() + "%");

		ResultSet resultado = comando.executeQuery();

		ArrayList<Produto> listaProduto = new ArrayList<Produto>();

		while (resultado.next()) {
			Produto p = new Produto();
			p.setCodigo(resultado.getLong("codigo"));
			p.setDescricao(resultado.getString("descricao"));

			listaProduto.add(p);
		}
		return listaProduto;

	}
	
	public ArrayList<Produto> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.codigo, p.descricao, p.preco, p.quantidade, f.codigo,f.descricao ");
		sql.append("FROM produto p  ");
		sql.append("INNER JOIN fabricante f ON f.codigo = p.fabricante_codigo ");

		Connection conexao = ConnectFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Produto> listaProduto = new ArrayList<Produto>();

		while (resultado.next()) {
			Fabricante f = new Fabricante();
			Produto p = new Produto();
			f.setCodigo(resultado.getLong("f.codigo"));
			f.setDescricao(resultado.getString("f.descricao"));
			p.setCodigo(resultado.getLong("p.codigo"));
			p.setDescricao(resultado.getString("p.descricao"));
			p.setPreco(resultado.getDouble("p.preco"));
			p.setQuantidade(resultado.getLong("p.quantidade"));
			p.setFabricante(f);

			listaProduto.add(p);
		}

		return listaProduto;
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
		
		
		Produto p1 = new Produto();
		p1.setDescricao("Produto Teste");
		
		ProdutoDAO pdao = new ProdutoDAO();
		
		ArrayList<Produto> listaProduto;
		try {
			listaProduto = new ArrayList<Produto>();
			listaProduto = pdao.buscarPorDescricao(p1);
			
			for (Produto p : listaProduto) {
				System.out.println("Resultado: " + p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Ocorreu erro ao tentar pesquisar Produto!");
		}
		;
	}
}
