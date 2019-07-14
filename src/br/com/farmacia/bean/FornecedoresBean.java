package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.util.JSFutil;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {

	private Fornecedores fornecedores;
	private ArrayList<Fornecedores> itens;
	private ArrayList<Fornecedores> itensFiltrados;

	public ArrayList<Fornecedores> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Fornecedores> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Fornecedores getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}

	public ArrayList<Fornecedores> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fornecedores> itens) {
		this.itens = itens;
	}

	@PostConstruct
	public void prepararPesquisa() {

		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			itens = fdao.listarPorCodigo();
		} catch (SQLException ex) {
			JSFutil.adicionarMensagemErro(ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void prepararNovo() {
		fornecedores = new Fornecedores();
	}

	public void novo() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.salvar(fornecedores);

			itens = fdao.listarPorCodigo();
			 
			JSFutil.adicionarMensagemSucesso("Fornecedor salvo com sucesso!");
		} catch (Exception ex) {
			JSFutil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void excluir() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.excluir(fornecedores);

			itens = fdao.listarPorCodigo();

			JSFutil.adicionarMensagemSucesso("Fornecedor excluido com sucesso!");
		} catch (Exception ex) {
			JSFutil.adicionarMensagemErro("Não é possível excluir um fornecedor com um produto associado!");
		}
	}

	public void editar() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.editar(fornecedores);

			itens = fdao.listarPorCodigo();

			JSFutil.adicionarMensagemSucesso("Fornecedor editado com sucesso!");
		} catch (Exception ex) {
			JSFutil.adicionarMensagemErro(ex.getMessage());
		}
	}

}
