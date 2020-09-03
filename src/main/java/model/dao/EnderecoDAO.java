package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.Endereco;

/**
 * 
 * Classe de acesso aos dados da tabela ENDERECO
 * @author Vilmar C. Pereira Jr
 *
 */
public class EnderecoDAO implements BaseDAO<Endereco>{
	
	/**
	 * Insere um novo registro na tabela ENDERECO
	 * 
	 * @param novoEndereco o endereço a ser criado
	 * @return novoEndereco o endereço criado, agora com ID preenchido.
	 */
	public Endereco inserir(Endereco novoEndereco) {
		Connection conexao = Banco.getConnection();
		
		String sql = " INSERT INTO ENDERECO (RUA, NUMERO, CIDADE, ESTADO) " 
					+ " VALUES (?,?,?,?) ";
		
		PreparedStatement query = Banco.getPreparedStatementWithGeneratedKeys(conexao, sql);

		try {
			query.setString(1, novoEndereco.getRua());
			query.setString(2, novoEndereco.getNumero());
			query.setString(3, novoEndereco.getCidade());
			query.setString(4, novoEndereco.getEstado());
			
			int codigoRetorno = query.executeUpdate();
			
			if(codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO) {
				ResultSet resultado = query.getGeneratedKeys();
				int chaveGerada = resultado.getInt(1);
				
				novoEndereco.setId(chaveGerada);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao inserir endereço.\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return novoEndereco;
	}
	
	public boolean alterar(Endereco enderecoAtualizado) {
		String sql = " UPDATE ENDERECO "
				+ " SET RUA=?, NUMERO=?, CIDADE=?, ESTADO=? " 
				+ " WHERE ID=? ";
		
		boolean alterou = false;
		
		//Exemplo usando try-with-resources (similar ao bloco finally)
		try (Connection conexao = Banco.getConnection();
			PreparedStatement query = Banco.getPreparedStatement(conexao, sql);) {
			query.setString(1, enderecoAtualizado.getRua());
			query.setString(2, enderecoAtualizado.getNumero());
			query.setString(3, enderecoAtualizado.getCidade());
			query.setString(4, enderecoAtualizado.getEstado());
			query.setInt(5, enderecoAtualizado.getId());
			
			int codigoRetorno = query.executeUpdate();
			alterou = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
		} catch (SQLException e) {
			System.out.println("Erro ao alterar endereço.\nCausa: " + e.getMessage());
		}
				
		return alterou;
	}
	
	public boolean excluir(int id) {
		Connection conexao = Banco.getConnection();
		String sql = " DELETE FROM ENDERECO WHERE ID=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		boolean excluiu = false;
		try {
			query.setInt(1, id);
			
			int codigoRetorno = query.executeUpdate();
			excluiu = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir endereço (id: " + id + ") .\nCausa: " + e.getMessage());
		}finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return excluiu;
	}
	
	public Endereco pesquisarPorId(int id) {
		String sql = "SELECT * FROM ENDERECO WHERE ID=?";
		Endereco enderecoBuscado = null;
		
		//Exemplo usando try-with-resources (similar ao bloco finally)
		try (Connection conexao = Banco.getConnection();
			 PreparedStatement consulta = Banco.getPreparedStatement(conexao, sql);) {
			consulta.setInt(1, id);
			ResultSet conjuntoResultante = consulta.executeQuery();
			
			if(conjuntoResultante.next()) {
				enderecoBuscado = construirDoResultSet(conjuntoResultante);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar endereço por Id (id: " + id + ") .\nCausa: " + e.getMessage());
		}
		
		return enderecoBuscado;
	}
	
	public List<Endereco> pesquisarTodos() {
		Connection conexao = Banco.getConnection();
		String sql = "SELECT * FROM ENDERECO ";
		PreparedStatement consulta = Banco.getPreparedStatement(conexao, sql);
		List<Endereco> enderecosBuscados = new ArrayList<Endereco>();
		
		try {
			ResultSet conjuntoResultante = consulta.executeQuery();
			while(conjuntoResultante.next()) {
				Endereco enderecoBuscado = construirDoResultSet(conjuntoResultante);
				enderecosBuscados.add(enderecoBuscado);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todos os endereços .\nCausa: " + e.getMessage());
		}finally {
			Banco.closeStatement(consulta);
			Banco.closeConnection(conexao);
		}
		
		return enderecosBuscados;
	}

	public Endereco construirDoResultSet(ResultSet conjuntoResultante) throws SQLException {
		Endereco enderecoBuscado = new Endereco();
		enderecoBuscado.setId(conjuntoResultante.getInt("id"));
		enderecoBuscado.setRua(conjuntoResultante.getString("rua"));
		enderecoBuscado.setCidade(conjuntoResultante.getString("cidade"));
		enderecoBuscado.setEstado(conjuntoResultante.getString("estado"));
		enderecoBuscado.setNumero(conjuntoResultante.getString("numero"));
		
		return enderecoBuscado;
	}

	public Endereco pesquisarPorCEP(String cep) {
		String sql = "SELECT * FROM ENDERECO WHERE CEP=?";
		Endereco enderecoBuscado = null;
		
		//Exemplo usando try-with-resources (similar ao bloco finally)
		try (Connection conexao = Banco.getConnection();
			 PreparedStatement consulta = Banco.getPreparedStatement(conexao, sql);) {
			consulta.setString(1, cep);
			ResultSet conjuntoResultante = consulta.executeQuery();
			
			if(conjuntoResultante.next()) {
				enderecoBuscado = construirDoResultSet(conjuntoResultante);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar endereço por CEP (" + cep + ") .\nCausa: " + e.getMessage());
		}
		
		return enderecoBuscado;
	}
}
