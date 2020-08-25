package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.Cliente;

/**
 * 
 * Classe de acesso aos dados da tabela CLIENTE
 * @author Vilmar C. Pereira Jr
 *
 */
public class ClienteDAO {
	
	public Cliente inserir(Cliente novoCliente) {
		Connection conexao = Banco.getConnection();
		
		String sql = " INSERT INTO CLIENTE (NOME, CPF) " 
					+ " VALUES (?,?) ";
		
		PreparedStatement query = Banco.getPreparedStatementWithGeneratedKeys(conexao, sql);

		try {
			query.setString(1, novoCliente.getNome());
			query.setString(2, novoCliente.getCpf());
			
			//TODO o que fazer com endereco e telefones?
			
			int codigoRetorno = query.executeUpdate();
			
			if(codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO) {
				ResultSet resultado = query.getGeneratedKeys();
				int chaveGerada = resultado.getInt("ID");
				
				novoCliente.setId(chaveGerada);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao inserir cliente.\nCausa: " + e.getMessage());
		}finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return novoCliente;
	}
	
	public boolean alterar(Cliente cliente) {
		String sql = " UPDATE CLIENTE "
				+ " SET NOME=?, CPF=? " 
				+ " WHERE ID=? ";
		
		boolean alterou = false;
		
		//Exemplo usando try-with-resources (similar ao bloco finally)
		try (Connection conexao = Banco.getConnection();
			PreparedStatement query = Banco.getPreparedStatement(conexao, sql);) {
			query.setString(1, cliente.getNome());
			query.setString(2, cliente.getCpf());
			query.setInt(3, cliente.getId());
			
			int codigoRetorno = query.executeUpdate();
			alterou = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
		} catch (SQLException e) {
			System.out.println("Erro ao alterar cliente.\nCausa: " + e.getMessage());
		}
				
		return alterou;
	}
	
	public boolean excluir(int id) {
		Connection conexao = Banco.getConnection();
		String sql = " DELETE FROM CLIENTE WHERE ID=? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		boolean excluiu = false;
		try {
			query.setInt(1, id);
			
			int codigoRetorno = query.executeUpdate();
			excluiu = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir cliente (id: " + id + ") .\nCausa: " + e.getMessage());
		}finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return excluiu;
	}
	
	public Cliente pesquisarPorId(int id) {
		String sql = "SELECT * FROM CLIENTE WHERE ID=?";
		Cliente clienteBuscado = null;
		
		//Exemplo usando try-with-resources (similar ao bloco finally)
		try (Connection conexao = Banco.getConnection();
			PreparedStatement consulta = Banco.getPreparedStatement(conexao, sql);) {
			consulta.setInt(1, id);
			ResultSet conjuntoResultante = consulta.executeQuery();
			
			if(conjuntoResultante.next()) {
				clienteBuscado = construirClienteDoResultSet(conjuntoResultante);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar cliente por Id (id: " + id + ") .\nCausa: " + e.getMessage());
		}
		
		return clienteBuscado;
	}
	
	public List<Cliente> pesquisarTodos() {
		Connection conexao = Banco.getConnection();
		String sql = "SELECT * FROM CLIENTE ";
		PreparedStatement consulta = Banco.getPreparedStatement(conexao, sql);
		List<Cliente> clientesBuscados = new ArrayList<Cliente>();
		
		try {
			ResultSet conjuntoResultante = consulta.executeQuery();
			while(conjuntoResultante.next()) {
				Cliente clienteBuscado = construirClienteDoResultSet(conjuntoResultante);
				clientesBuscados.add(clienteBuscado);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todos os clientes .\nCausa: " + e.getMessage());
		}finally {
			Banco.closeStatement(consulta);
			Banco.closeConnection(conexao);
		}
		
		return clientesBuscados;
	}

	private Cliente construirClienteDoResultSet(ResultSet conjuntoResultante) throws SQLException {
		Cliente clienteBuscado = new Cliente();
		clienteBuscado.setId(conjuntoResultante.getInt("id"));
		clienteBuscado.setNome(conjuntoResultante.getString("nome"));
		clienteBuscado.setCpf(conjuntoResultante.getString("cpf"));
		
		//TODO como preencher endereco e telefones?
		
		return clienteBuscado;
	}
}
