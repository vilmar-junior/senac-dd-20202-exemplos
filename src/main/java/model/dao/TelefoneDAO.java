package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.vo.Telefone;

public class TelefoneDAO {
	
	public Telefone inserir(Telefone novoTelefone) {
Connection conexao = Banco.getConnection();
		
		String sql = " INSERT INTO TELEFONE (DDD, NUMERO, MOVEL, IMEI, ID_CLIENTE) " 
					+ " VALUES (?,?,?,?,?) ";
		
		PreparedStatement query = Banco.getPreparedStatementWithGeneratedKeys(conexao, sql);

		try {
			int idCliente = buscarIdCliente(novoTelefone);

			query.setString(1, novoTelefone.getDdd());
			query.setString(2, novoTelefone.getNumero());
			query.setInt(3, novoTelefone.isMovel() ? 1 : 0);
			query.setString(4, novoTelefone.getImei());
			query.setInt(5, idCliente);
			
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
		//TODO implementar
		
		return false;
	}
	
	public boolean excluir(int id) {
		//TODO implementar
		
		return false;
	}
	
	public Telefone pesquisarPorId(int id) {
		//TODO implementar
		
		return null;
	}
	
	public List<Telefone> pesquisarTodos(){
		//TODO implementar
		
		return null;
	}
	
	private Telefone construirDoResultSet(ResultSet linhaConsultada) {
		//TODO implementar
		
		return null;
	}
	
	private int buscarIdCliente(Telefone novoTelefone) {
		int idCliente = 0;
		if(novoTelefone.getTitularConta() != null) {
			idCliente = novoTelefone.getTitularConta().getId();
		}
		
		return idCliente;
	}
}
