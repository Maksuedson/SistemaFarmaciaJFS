package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.DAO.ProdutosDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.util.JSFutil;

@ManagedBean(name = "MBProdutos")
@ViewScoped
public class ProdutosBean {

	private Produtos produtos;
	private ArrayList<Produtos> itens;
	private ArrayList<Produtos> itensFiltrados;
	
	private ArrayList<Fornecedores>comboFornecedores;

	public ArrayList<Fornecedores> getComboFornecedores() {
		return comboFornecedores;
	}

	public void setComboFornecedores(ArrayList<Fornecedores> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}

	public Produtos getProdutos() {
		return produtos;
	}

	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}

	public ArrayList<Produtos> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produtos> itens) {
		this.itens = itens;
	}

	public ArrayList<Produtos> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Produtos> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	
	@PostConstruct
	public void prepararPesquisa() {

		try {
			ProdutosDAO pdao = new ProdutosDAO();
			itens = pdao.listar();
		} catch (SQLException ex) {
			JSFutil.adicionarMensagemErro(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public void prepararNovo() {
		produtos = new Produtos();
		FornecedoresDAO pdao = new FornecedoresDAO();
		try {
			comboFornecedores = pdao.listarPorCodigo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void novo() {
		try {
			ProdutosDAO pdao = new ProdutosDAO();
			pdao.salvar(produtos);

			itens = pdao.listar();
			 
			JSFutil.adicionarMensagemSucesso("Produto salvo com sucesso!");
		} catch (Exception ex) {
			JSFutil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			ProdutosDAO pdao = new ProdutosDAO();
			pdao.excluir(produtos);

			itens = pdao.listar();

			JSFutil.adicionarMensagemSucesso("Produto excluido com sucesso!");
		} catch (Exception ex) {
			JSFutil.adicionarMensagemErro("Não é possível excluir o produto associado!");
		}
	}

	public void editar() {
		try {
			ProdutosDAO pdao = new ProdutosDAO();
			pdao.editar(produtos);

			itens = pdao.listar();

			JSFutil.adicionarMensagemSucesso("Produto editado com sucesso!");
		} catch (Exception ex) {
			JSFutil.adicionarMensagemErro(ex.getMessage());
		}
	}

}
