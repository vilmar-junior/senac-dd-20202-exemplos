package model.bo;

import java.util.List;

import model.dao.EnderecoDAO;
import model.exception.CepInvalidoException;
import model.vo.Endereco;

public class EnderecoBO {
	
	private EnderecoDAO dao = new EnderecoDAO();
	
	public Endereco salvar(Endereco novoEndereco) {
		return dao.inserir(novoEndereco);
	}
	
	public boolean atualizar(Endereco novoEndereco) {
		return dao.alterar(novoEndereco);
	}
	
	public boolean excluir(int id) {
		return dao.excluir(id);
	}
	
	public List<Endereco> pesquisarTodos(){
		return dao.pesquisarTodos();
	}
	
	public Endereco pesquisarPorId(int id){
		return dao.pesquisarPorId(id);
	}
	
	/**
	 * #5- Dado um CEP, sistema deve verificar se já existe endereço cadastrado e devolver para a tela
	 * @param cep
	 * @return
	 * @throws CepInvalidoException
	 */
	public Endereco pesquisarPorCEP(String cep) throws CepInvalidoException{
		
		if(cep == null || cep.isEmpty() || cep.length() != 8) {
			throw new CepInvalidoException("O CEP informado (" + cep + ") é inválido.");
		}
		
		return dao.pesquisarPorCEP(cep);
	}
}
