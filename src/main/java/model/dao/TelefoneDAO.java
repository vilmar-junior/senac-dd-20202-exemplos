package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.Telefone;

public class TelefoneDAO {
	
	public Telefone inserir(Telefone novoTelefone) {
		Connection conexao = Banco.getConnection();
		
		String sql = " INSERT INTO TELEFONE (DDD, NUMERO, MOVEL, IMEI, ID_CLIENTE) " 
					+ " VALUES (?,?,?,?,?) ";
		
		PreparedStatement query = Banco.getPreparedStatementWithGeneratedKeys(conexao, sql);

		try {
			query.setString(1, novoTelefone.getDdd());
			query.setString(2, novoTelefone.getNumero());
			query.setInt(3, novoTelefone.isMovel() ? 1 : 0);
			query.setString(4, novoTelefone.getImei());
			query.setInt(5, novoTelefone.getIdClienteTitularConta());
			
			int codigoRetorno = query.executeUpdate();
			if(codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO) {
				ResultSet resultado = query.getGeneratedKeys();
				int chaveGerada = resultado.getInt("ID");
				
				novoTelefone.setId(chaveGerada);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao inserir telefone.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return novoTelefone;
	}
	

	public boolean atualizar(Telefone telefone) {
		Connection conexao = Banco.getConnection();
		
		String sql = " UPDATE TELEFONE "
				   + " SET DDD=?, NUMERO=?, MOVEL=?, IMEI=?, ID_CLIENTE=? "
				   + " WHERE ID=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);

		boolean atualizou = false;
		try {
			query.setString(1, telefone.getDdd());
			query.setString(2, telefone.getNumero());
			query.setInt(3, telefone.isMovel() ? 1 : 0);
			query.setString(4, telefone.getImei());
			query.setInt(5, telefone.getIdClienteTitularConta());
			
			int codigoRetorno = query.executeUpdate();
			atualizou = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
			
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar telefone.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return atualizou;
	}
	
	public boolean excluir(int id) {
		Connection conexao = Banco.getConnection();
		
		String sql = " DELETE FROM TELEFONE "
				   + " WHERE ID=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);

		boolean excluiu = false;
		try {
			query.setInt(1, id);
			
			int codigoRetorno = query.executeUpdate();
			excluiu = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
			
		} catch (SQLException e) {
			System.out.println("Erro ao excluir telefone.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return excluiu;
	}
	
	public Telefone pesquisarPorId(int id) {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM TELEFONE "
				   + " WHERE ID=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		Telefone telefone = null;
		try {
			query.setInt(1, id);
			
			ResultSet rs = query.executeQuery();
			if(rs.next()) {
				telefone = this.construirDoResultSet(rs);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar telefone por id (Id: " + id + ").\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return telefone;
	}
	
	public List<Telefone> pesquisarTodos(){
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM TELEFONE ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		List<Telefone> telefones = new ArrayList<Telefone>();
		try {
			ResultSet rs = query.executeQuery();
			
			while(rs.next()) {
				Telefone telefone = this.construirDoResultSet(rs);
				telefones.add(telefone);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar telefones.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return telefones;
	}
	
	/**
	 * Devolve a lista de telefones de um determinado cliente
	 * 
	 * @param idCliente a chave primária do cliente.
	 * @return a lista com os telefones do cliente
	 * 
	 */
	public List<Telefone> pesquisarTelefonesPorIdCliente(int idCliente) {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM TELEFONE "
				   + " WHERE ID_CLIENTE=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		List<Telefone> telefones = new ArrayList<Telefone>();
		try {
			query.setInt(1, idCliente);
			
			ResultSet rs = query.executeQuery();
			while(rs.next()) {
				Telefone telefone = this.construirDoResultSet(rs);
				telefones.add(telefone);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar telefones do cliente com id: " + idCliente + ".\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return telefones;
	}
	
	private Telefone construirDoResultSet(ResultSet linhaConsultada) throws SQLException {
		Telefone tel = new Telefone();
		tel.setId(linhaConsultada.getInt("id"));
		tel.setDdd(linhaConsultada.getString("ddd"));
		tel.setNumero(linhaConsultada.getString("numero"));
		tel.setImei(linhaConsultada.getString("imei"));
		tel.setMovel(linhaConsultada.getBoolean("movel"));
		tel.setIdClienteTitularConta(linhaConsultada.getInt("id_cliente"));
		
		return tel;
	}


}
