package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.Cliente;
import model.vo.Endereco;
import model.vo.Telefone;

/**
 * 
 * Classe de acesso aos dados da tabela CLIENTE
 * @author Vilmar C. Pereira Jr
 *
 */
public class ClienteDAO {
	
	public Cliente inserir(Cliente novoCliente) {
		Connection conexao = Banco.getConnection();
		
		String sql = " INSERT INTO CLIENTE (NOME, CPF, ID_ENDERECO) " 
					+ " VALUES (?,?,?) ";
		
		PreparedStatement query = Banco.getPreparedStatementWithGeneratedKeys(conexao, sql);

		try {
			/**
			 * Exemplo (possibilidades de cadastro de novo cliente)
			 * 
			 * 1: Cadastro de cliente E endereço novo (num grande formulário)
			 * Cliente [id=0; nome = "José", cpf = "000.000.000-00", Endereco[id=0, rua="1", ....]]
			 * 
			 * 2: Cadastro de cliente, selecionando um endereço que já existe (listado na tela)
			 * Cliente [id=0; nome = "José", cpf = "000.000.000-00", Endereco[id=50, rua="Mauro Ramos", ....]]
			 * 
			 */
			
			Endereco enderecoDoCliente = verificarEnderecoDoCliente(novoCliente);
			
			query.setString(1, novoCliente.getNome());
			query.setString(2, novoCliente.getCpf());
			query.setInt(3, enderecoDoCliente.getId());
			
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
				+ " SET NOME=?, CPF=?, ID_ENDERECO=? " 
				+ " WHERE ID=? ";
		
		boolean alterou = false;
		Endereco enderecoDoCliente = verificarEnderecoDoCliente(cliente);
		
		//Exemplo usando try-with-resources (similar ao bloco finally)
		try (Connection conexao = Banco.getConnection();
			PreparedStatement query = Banco.getPreparedStatement(conexao, sql);) {
			query.setString(1, cliente.getNome());
			query.setString(2, cliente.getCpf());
			query.setInt(3, enderecoDoCliente.getId());
			query.setInt(4, cliente.getId());
			
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
		String sql = " SELECT * FROM CLIENTE WHERE ID=? ";
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
		String sql = " SELECT * FROM CLIENTE ";
		
		/**
		 * Exemplo de resultado da consulta (ResultSet)
		 * | ID |  NOME  |  CPF  | ID_ENDERECO |
		 * | 1  |  José  |  123  |    50       |
		 * | 2  |  Maria |  321  |    25       |
		 */
		
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

	
	
	/**
	 * Converte uma tupla do banco para um objeto do tipo Cliente
	 * @param conjuntoResultante a tupla consultada no banco
	 * @return um cliente com os atributos preenchidos
	 * @throws SQLException
	 */
	private Cliente construirClienteDoResultSet(ResultSet conjuntoResultante) throws SQLException {
		Cliente clienteBuscado = new Cliente();
		clienteBuscado.setId(conjuntoResultante.getInt("id"));
		clienteBuscado.setNome(conjuntoResultante.getString("nome"));
		clienteBuscado.setCpf(conjuntoResultante.getString("cpf"));
		
		//Preenche o endereço do cliente
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		int idEndereco = conjuntoResultante.getInt("id_endereco");
		Endereco enderecoDoCliente = enderecoDAO.pesquisarPorId(idEndereco);
		clienteBuscado.setEndereco(enderecoDoCliente);
		
		//Consulta os telefones do cliente
		TelefoneDAO telefoneDAO = new TelefoneDAO();
		List<Telefone> telefones = telefoneDAO.pesquisarTelefonesPorIdCliente(clienteBuscado.getId());
		clienteBuscado.setTelefones(telefones);
		
		return clienteBuscado;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private Endereco verificarEnderecoDoCliente(Cliente cliente) {
		Endereco enderecoDoCliente = cliente.getEndereco();
		if(enderecoDoCliente != null) {
			if(enderecoDoCliente.getId() == 0) {
				//Caso 1: endereço ainda não existe no banco
				EnderecoDAO endDAO = new EnderecoDAO();
				enderecoDoCliente = endDAO.inserir(cliente.getEndereco());
			}
		}
		
		return enderecoDoCliente;
	}
}
