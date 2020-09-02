package model.bo;

import model.dao.ClienteDAO;
import model.exception.ClienteSemEnderecoException;
import model.exception.CpfJaCadastradoException;
import model.vo.Cliente;
import model.vo.Endereco;

public class ClienteBO {
	
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
		
		ClienteDAO clienteDAO = new ClienteDAO();
		if(clienteDAO.cpfJaCadastrado(novoCliente.getCpf())) {
			throw new CpfJaCadastradoException("O CPF informado (" + novoCliente.getCpf() 
			+ ") já foi cadastrado para outro cliente");
		}
		
		clienteDAO.inserir(novoCliente);
		
		return novoCliente;
	}
	

}
