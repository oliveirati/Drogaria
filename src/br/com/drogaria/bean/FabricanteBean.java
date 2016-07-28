package br.com.drogaria.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.data.PageEvent;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.JSFUtil;

@ManagedBean(name = "MBFabricante")
@ViewScoped
public class FabricanteBean {
	private ArrayList<Fabricante> itens;
	private Fabricante fabricante;
	private Fabricante fabricanteSelecionado;

	public ArrayList<Fabricante> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fabricante> itens) {
		this.itens = itens;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	@PostConstruct
	public void prepararPesquisaFabricante() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			itens = dao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void prepararInclusaoFabricante () {
		fabricante = new Fabricante();
	}
	
	public void incluirFabricante() {
		if (fabricante != null && !fabricante.getDescricao().equalsIgnoreCase("")) {
			try {
				FabricanteDAO dao = new FabricanteDAO();
				dao.salvar(fabricante);
				itens = dao.listar();
				JSFUtil.adicionarMensagemSucesso("Fabricante Incluído com sucesso!");
			} catch (Exception e) {
				e.printStackTrace();
				JSFUtil.adicionarMensagemErro(e.getMessage());
			}
		} else {
			JSFUtil.adicionarMensagemErro("Preencha o campo 'Descrição'!");
		}
	}

	public void prepararAlteracaoFabricante () {
		fabricante = new Fabricante();
		if(fabricanteSelecionado != null) {
			fabricante = fabricanteSelecionado;
			
		} else {
			JSFUtil.adicionarMensagemErro("Selecione um Fabricante!");
		}
	}
	
	public void alterarFabricante() {
		if (fabricante != null && !fabricante.getDescricao().equalsIgnoreCase("")) {
			try {
				FabricanteDAO dao = new FabricanteDAO();
				dao.editar(fabricante);
				itens = dao.listar();
				JSFUtil.adicionarMensagemSucesso("Fabricante Alterado com sucesso!");
			} catch (Exception e) {
				e.printStackTrace();
				JSFUtil.adicionarMensagemErro(e.getMessage());
			}
		} else {
			JSFUtil.adicionarMensagemErro("Preencha o campo 'Descrição'!");
		}
	}

	public void prepararExclusaoFabricante () {
		fabricante = new Fabricante();
		fabricante = fabricanteSelecionado;
	}
	
	public void excluirFabricante() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.excluir(fabricante);
			itens = dao.listar();
			JSFUtil.adicionarMensagemSucesso("Fabricante Excluído com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
	}

	public Fabricante getFabricanteSelecionado() {
		return fabricanteSelecionado;
	}

	public void setFabricanteSelecionado(Fabricante fabricanteSelecionado) {
		this.fabricanteSelecionado = fabricanteSelecionado;
	}
	
	
	public void onRowSelect(SelectEvent slc) {  
		        fabricanteSelecionado = (Fabricante)slc.getObject();
		        
		    } 

	public void onRowUnselect (SelectEvent slc) {  
		fabricanteSelecionado = null;
		
	}  
	
	public void paginar(PageEvent event) {
		int var = event.getPage();
	}
	
}
