package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.factory.ConexaoFactory;

public class ProdutosDAO {
	public void salvar(Produtos p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO PRODUTOS");
		sql.append("(descricao, preco, quantidade, fornecedores_codigo)");
		sql.append("values (?,?,?,?)");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getDescricao());
		comando.setDouble(2, p.getPreco());
		comando.setLong(3, p.getQuantidade());
		comando.setLong(4, p.getFornecedores().getCodigo());
		comando.executeUpdate();
	}

	public void excluir(Produtos p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM PRODUTOS ");
		sql.append("WHERE CODIGO = ?");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCodigo());
		comando.executeUpdate();
	}

	public void editar(Produtos p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE PRODUTOS ");
		sql.append("SET DESCRICAO  = ?, PRECO = ?, QUANTIDADE =?, FORNECEDORES_CODIGO =? ");
		sql.append("WHERE CODIGO  = ?");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getDescricao());
		comando.setDouble(2, p.getPreco());
		comando.setLong(3, p.getQuantidade());
		comando.setLong(4, p.getFornecedores().getCodigo());
		comando.setLong(5, p.getCodigo());

		comando.executeUpdate();
	}

	public Fornecedores buscaPorCodigo(Produtos p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT CODIGO, DESCRICAO FROM PRODUTOS WHERE CODIGO = ? ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCodigo());
		ResultSet resultado = comando.executeQuery();
		Fornecedores retorno = null;

		if (resultado.next()) {
			retorno = new Fornecedores();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}
		return retorno;
	}

	public ArrayList<Produtos> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.CODIGO, p.DESCRICAO, p.PRECO, p.QUANTIDADE, f.CODIGO, f.DESCRICAO ");
		sql.append("FROM PRODUTOS p ");
		sql.append("INNER JOIN FORNECEDORES f ON f.CODIGO = p.fornecedores_codigo");
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();
		ArrayList<Produtos> lista = new ArrayList<Produtos>();
		while (resultado.next()) {
			Fornecedores f = new Fornecedores();
			f.setCodigo(resultado.getLong("f.codigo"));
			f.setDescricao(resultado.getString("f.descricao"));

			Produtos p = new Produtos();
			p.setCodigo(resultado.getLong("p.codigo"));
			p.setDescricao(resultado.getString("p.descricao"));
			p.setQuantidade(resultado.getLong("p.quantidade"));
			p.setPreco(resultado.getDouble("p.preco"));
			p.setFornecedores(f);
			lista.add(p);

		}
		return lista;
	}
}
