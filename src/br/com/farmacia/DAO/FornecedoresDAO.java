package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.factory.ConexaoFactory;

public class FornecedoresDAO {
	public void salvar(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO FORNECEDORES");
		sql.append("(descricao)");
		sql.append("values (?)");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.executeUpdate();
	}

	public void excluir(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM FORNECEDORES ");
		sql.append("WHERE CODIGO = ?");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());
		comando.executeUpdate();
	}

	public void editar(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE FORNECEDORES ");
		sql.append("SET DESCRICAO  = ?");
		sql.append("WHERE CODIGO  = ?");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());

		comando.executeUpdate();
	}

	public Fornecedores buscaPorCodigo(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT CODIGO, DESCRICAO FROM FORNECEDORES WHERE CODIGO = ? ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());
		ResultSet resultado = comando.executeQuery();
		Fornecedores retorno = null;

		if (resultado.next()) {
			retorno = new Fornecedores();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}
		return retorno;
	}

	public ArrayList<Fornecedores> listarPorCodigo() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT CODIGO, DESCRICAO FROM FORNECEDORES ORDER BY DESCRICAO ASC");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();
		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();
		while (resultado.next()) {
			Fornecedores f = new Fornecedores();
			f.setCodigo(resultado.getLong("codigo"));
			f.setDescricao(resultado.getString("descricao"));
			lista.add(f);
		}
		return lista;
	}

	public ArrayList<Fornecedores> listarPorDescricao(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT CODIGO, DESCRICAO ");
		sql.append("FROM FORNECEDORES ");
		sql.append("WHERE DESCRICAO LIKE ?");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, "%" + f.getDescricao() + "%");

		ResultSet resultado = comando.executeQuery();
		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();
		while (resultado.next()) {
			Fornecedores item = new Fornecedores();
			item.setCodigo(resultado.getLong("codigo"));
			item.setDescricao(resultado.getString("descricao"));
			lista.add(item);
		}
		return lista;
	}

	public static void main(String[] args) {
		/*
		 * Busca por codigo
		 * 
		 * Fornecedores f1 = new Fornecedores(); f1.setCodigo(7);
		 * 
		 * Fornecedores f2 = new Fornecedores(); f2.setCodigo(8);
		 * 
		 * FornecedoresDAO fdao = new FornecedoresDAO(); try { Fornecedores f3 =
		 * fdao.buscaPorCodigo(f1); Fornecedores f4 = fdao.buscaPorCodigo(f2);
		 * 
		 * System.out.println("busca com sucesso: " + f3);
		 * System.out.println("busca com sucesso: " + f4); } catch (SQLException e) {
		 * System.out.println("falha ao editar"); e.printStackTrace(); }
		 */

		/*
		 * Salvar Fornecedores f1 = new Fornecedores(); f1.setDescricao("PARAGUAI");
		 * 
		 * FornecedoresDAO fdao = new FornecedoresDAO(); try { fdao.salvar(f1);
		 * System.out.println("salvo com sucesso: " + f1); } catch (SQLException e) {
		 * System.out.println("falha ao salvar"); e.printStackTrace(); }
		 */

		/*
		 * Listar dados FornecedoresDAO fdao = new FornecedoresDAO(); try {
		 * ArrayList<Fornecedores> lista = fdao.listar(); for (Fornecedores f : lista) {
		 * System.out.println("Resultado: " + f); } } catch (SQLException e) {
		 * System.out.println("falha ao listar"); e.printStackTrace(); }
		 */

		//ListaPorDescricao
		Fornecedores f1 = new Fornecedores();
		f1.setDescricao("ma");

		FornecedoresDAO fdao = new FornecedoresDAO();
		try {
			ArrayList<Fornecedores> lista = fdao.listarPorDescricao(f1);
			for (Fornecedores f : lista) {
				System.out.println("Resultado: " + f);
			}
		} catch (SQLException e) {
			System.out.println("falha ao listar");
			e.printStackTrace();
		}

	}

}
