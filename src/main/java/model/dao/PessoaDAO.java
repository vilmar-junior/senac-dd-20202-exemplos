package model.dao;

import java.sql.ResultSet;
import java.util.List;

import model.vo.Pessoa;

public class PessoaDAO implements BaseDAO<Pessoa> {

	@Override
	public Pessoa inserir(Pessoa novoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean alterar(Pessoa objetoAlterado) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Pessoa> pesquisarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pessoa pesquisarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pessoa construirDoResultSet(ResultSet conjuntoResultante) {
		// TODO Auto-generated method stub
		return null;
	}
}
