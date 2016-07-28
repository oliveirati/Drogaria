package br.com.drogaria.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

public class ProdutoDAOTest {
	
	@Test
	@Ignore
	public void salvar() throws SQLException {
		Produto produto = new Produto();
		produto.setDescricao("Combiron");
		produto.setPreco(8.45);
		produto.setQuantidade(13L);
		
		Fabricante fabricante = new Fabricante();
		fabricante.setCodigo(2L);
		produto.setFabricante(fabricante);
		
		ProdutoDAO dao = new ProdutoDAO();
		
		dao.salvar(produto);
	}
	
	@Test
	@Ignore
	public void listar() throws SQLException {
		ProdutoDAO dao = new ProdutoDAO();
		
		ArrayList<Produto> listaProduto = dao.listar();
		
		for(Produto p : listaProduto){
			System.out.println("Código: " + p.getCodigo());
			System.out.println("Descrição: " + p.getDescricao());
			System.out.println("Quantidade: " + p.getQuantidade());
			System.out.println("Preço: " + p.getPreco());
			System.out.println("Código do Fabricante: " + p.getFabricante().getCodigo());
			
		}
	}
	
	@Test
	@Ignore
	public void excluir() throws SQLException {
		ProdutoDAO dao = new ProdutoDAO();
		
		Produto p = new Produto();
		p.setCodigo(1L);
		
		dao.excluir(p);
		
	}
	
	@Test
	public void editar() throws SQLException {
		ProdutoDAO dao = new ProdutoDAO();
		Produto p = new Produto();
		Fabricante f = new Fabricante();
		
		
		p.setCodigo(4L);
		p.setDescricao("Cataflan 60mg");
		p.setQuantidade(60L);
		p.setPreco(23.60);
		f.setCodigo(14L);
		p.setFabricante(f);
		
		dao.editar(p);
		
	}
}

	