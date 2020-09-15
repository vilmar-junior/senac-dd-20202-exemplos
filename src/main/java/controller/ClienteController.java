package controller;

import model.bo.ClienteBO;
import model.exception.ClienteSemEnderecoException;
import model.exception.CpfInvalidoException;
import model.exception.CpfJaCadastradoException;
import model.vo.Cliente;

public class ClienteController {

	private ClienteBO bo = new ClienteBO();
	
	/**
	 * Salva um novo cliente
	 * @param novoCliente o cliente a ser criado
	 * @return mensagem informando sucesso ou erro
	 */
	public String salvar(Cliente novoCliente) {
		String mensagem = "";
		
		try {
			this.validarCPF(novoCliente.getCpf());
			novoCliente = bo.salvar(novoCliente);
		} catch (CpfInvalidoException 
				| ClienteSemEnderecoException 
				| CpfJaCadastradoException excecao) {
			mensagem = excecao.getMessage();
		} 
		
		mensagem = "Cliente salvo com sucesso! Id gerado: " + novoCliente.getId();
		
		return mensagem;
	}
	
	public String atualizar(Cliente cliente) {
		String mensagem = "";
		boolean atualizou = false;
		
		try {
			this.validarCPF(cliente.getCpf());
			atualizou = bo.atualizar(cliente);
		} catch (CpfInvalidoException 
				| ClienteSemEnderecoException 
				| CpfJaCadastradoException excecao) {
			mensagem = excecao.getMessage();
		} 
		
		if(atualizou) {
			mensagem = "Cliente atualizado com sucesso!";
		} else {
			mensagem = "Erro ao atualizar cliente :(";
		}
		
		return mensagem;
	}
	
	
	/**
	 * 	#1- Todo cliente deve possuir CPF válido (11 caracteres numéricos)
	 * @param cpf
	 * @throws CpfInvalidoException
	 */
	private void validarCPF(String cpf) throws CpfInvalidoException{
		
		if(cpf == null || cpf.isEmpty()
				|| cpf.length() != 11) {
			throw new CpfInvalidoException("CPF deve possuir tamanho 11");
		}
		
		try {
			Integer.parseInt(cpf);
		} catch (NumberFormatException ex) {
			throw new CpfInvalidoException("CPF deve possuir tamanho 11 e somente números");
		}
	}
	
}
