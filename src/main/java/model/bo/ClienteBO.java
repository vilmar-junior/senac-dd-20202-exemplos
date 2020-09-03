package model.bo;

import model.dao.ClienteDAO;
import model.dao.TelefoneDAO;
import model.exception.ClienteSemEnderecoException;
import model.exception.CpfJaCadastradoException;
import model.vo.Cliente;
import model.vo.Endereco;
import model.vo.Telefone;

public class ClienteBO {
	
	private ClienteDAO clienteDAO = new ClienteDAO();
	
	/***
	 * Salva um novo cliente
	 * #3- Todo cliente deve possuir endereço
	 * #8- Não podem ser cadastrados 2 clientes distintos com o mesmo CPF
	 * 
	 * @throws ClienteSemEnderecoException, CpfJaCadastradoException lançadas conforme
	 * regras de negócio definidas
	 */
	
	public Cliente salvar(Cliente novoCliente) 
			throws ClienteSemEnderecoException, CpfJaCadastradoException{
		
		Endereco enderecoDoCliente = novoCliente.getEndereco();
		if(enderecoDoCliente == null) {
			throw new ClienteSemEnderecoException("Cliente deve possuir endereço");
		}
		
		if(this.clienteDAO.cpfJaCadastrado(novoCliente)) {
			throw new CpfJaCadastradoException("O CPF informado (" + novoCliente.getCpf() 
			+ ") já foi cadastrado para outro cliente");
		}
		
		this.clienteDAO.inserir(novoCliente);
		
		return novoCliente;
	}
	
	public boolean atualizar(Cliente cliente) throws ClienteSemEnderecoException, CpfJaCadastradoException{
		Endereco enderecoDoCliente = cliente.getEndereco();
		if(enderecoDoCliente == null) {
			throw new ClienteSemEnderecoException("Cliente deve possuir endereço");
		}
		
		if(this.clienteDAO.cpfJaCadastrado(cliente)) {
			throw new CpfJaCadastradoException("O CPF informado (" + cliente.getCpf() 
			+ ") já foi cadastrado para outro cliente");
		}
		
		
		return this.clienteDAO.alterar(cliente);
	}
	
	/**
	 * #7 - Consultar cliente por CPF
	 * @param cpf o CPF a ser buscado
	 * @return o cliente dado um CPF, null caso não exista.
	 */
	public Cliente consultarPorCPF(String cpf) {
		return this.consultarPorCPF(cpf);
	}
	
	/**
	 * #4- Ao excluir um cliente, o sistema deve liberar todos os telefones dele
	 * @param id o id do cliente
 	 * @return se excluiu ou não
	 */
	public boolean excluirCliente(Cliente clienteQueSeraExcluido) {
		boolean excluiu = clienteDAO.excluir(clienteQueSeraExcluido.getId());
		
		if(excluiu) {
			//Liberar todos os telefones
			TelefoneDAO telDAO = new TelefoneDAO();
			//TODO tem como dar UPDATE na coluna ID_CLIENTE (para NULL)
			//     em todos os telefones desse cliente de uma vez só?
			for(Telefone telefoneDoCliente: clienteQueSeraExcluido.getTelefones()) {
				telefoneDoCliente.setIdClienteTitularConta(0);
				telDAO.atualizar(telefoneDoCliente);
			}
		}
		
		return excluiu;
	}
	
	
	
	
	
	
}
