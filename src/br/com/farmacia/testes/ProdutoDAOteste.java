package br.com.farmacia.testes;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.DAO.ProdutosDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;

public class ProdutoDAOteste {
	@Test
	@Ignore
	public void salvar() {
		Produtos p1 = new Produtos();
		p1.setDescricao("REMEDIO");
		p1.setPreco(2.5);
		p1.setQuantidade(40l);

		Fornecedores f = new Fornecedores();
		f.setCodigo(1);
		p1.setFornecedores(f);

		ProdutosDAO fdao = new ProdutosDAO();
		try {
			fdao.salvar(p1);
			System.out.println("salvo com sucesso: " + p1);
		} catch (SQLException e) {
			System.out.println("falha ao salvar");
			e.printStackTrace();
		}

	}

	@Test
	@Ignore
	public void listar() {
		try {
			ProdutosDAO fdao = new ProdutosDAO();
			ArrayList<Produtos> lista = fdao.listar();
			for (Produtos p : lista) {
				System.out.println("Código do Produto: " + p.getCodigo());
				System.out.println("Descrição do Produto: " + p.getDescricao());
				System.out.println("Valor do Produto: " + p.getPreco());
				System.out.println("Quantidade: " + p.getQuantidade());
				System.out.println("Código do Fornecedor: " + p.getFornecedores().getCodigo());
				System.out.println("Descrição do Fornecedor: " + p.getFornecedores().getDescricao());
				System.out.println(" ");

			}
		} catch (SQLException e) {
			System.out.println("falha ao listar");
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void excluir() {
		Produtos p1 = new Produtos();
		p1.setCodigo(3l);

		try {
			ProdutosDAO fdao = new ProdutosDAO();
			fdao.excluir(p1);
			System.out.println("Excluido com sucesso: " + p1);
		} catch (SQLException e) {
			System.out.println("falha ao excluir");
			e.printStackTrace();
		}
	}

	@Test
	public void editar() throws Exception {
		Produtos p = new Produtos();
		p.setCodigo(5l);
		p.setDescricao("CATAFLAN");
		p.setQuantidade(50l);
		p.setPreco(4.5);

		Fornecedores f = new Fornecedores();
		f.setCodigo(6);
		p.setFornecedores(f);
		
		ProdutosDAO pDAO = new ProdutosDAO();
		pDAO.editar(p);

		System.out.println("Editado com sucesso: " + p);

	}
}
