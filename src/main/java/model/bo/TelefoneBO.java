package model.bo;

import model.dao.TelefoneDAO;
import model.exception.NumeroTelefoneInvalidoException;
import model.vo.Telefone;

public class TelefoneBO {
	
	private static final int QUANTIDADE_DIGITOS_MOVEL = 9;
	private static final int QUANTIDADE_DIGITOS_FIXO = 8;
	private TelefoneDAO dao = new TelefoneDAO();
	
	public Telefone salvar(Telefone novoTelefone) throws NumeroTelefoneInvalidoException {
		this.validarNumeroTelefone(novoTelefone.getNumero(), novoTelefone.isMovel());
		
		return dao.inserir(novoTelefone);
	}
	
	public boolean atualizar(Telefone telefone) throws NumeroTelefoneInvalidoException {
		this.validarNumeroTelefone(telefone.getNumero(), telefone.isMovel());

		return dao.atualizar(telefone);
	}
	
	/**
	 * 6- Caso telefone seja móvel: deve possuir 9 dígitos, caso seja fixo, deve possuir 8 dígitos
	 * @param numero o número do telefone
	 * @param movel se é móvel (true) ou fixo (false)
	 * @throws NumeroTelefoneInvalidoException
	 */
	private void validarNumeroTelefone(String numero, boolean movel) throws NumeroTelefoneInvalidoException{
		if(numero == null || numero.isEmpty()) {
			throw new NumeroTelefoneInvalidoException("Número de telefone não informado");
		}
		
		int quantidadeDigitos = numero.length();
		if(quantidadeDigitos != QUANTIDADE_DIGITOS_MOVEL 
				&& quantidadeDigitos != QUANTIDADE_DIGITOS_FIXO) {
			throw new NumeroTelefoneInvalidoException("Número de telefone deve possuir " 
				+ QUANTIDADE_DIGITOS_FIXO + " ou " + QUANTIDADE_DIGITOS_MOVEL + " dígitos");
		}
		
		
		if(movel && quantidadeDigitos != QUANTIDADE_DIGITOS_MOVEL) {
			throw new NumeroTelefoneInvalidoException("Número de telefone MÓVEL deve possuir " 
					+ QUANTIDADE_DIGITOS_MOVEL + " dígitos"); 
		}
		
		if(!movel && quantidadeDigitos != QUANTIDADE_DIGITOS_FIXO) {
			throw new NumeroTelefoneInvalidoException("Número de telefone FIXO deve possuir " 
					+ QUANTIDADE_DIGITOS_FIXO + " dígitos"); 
		}
	}
}
