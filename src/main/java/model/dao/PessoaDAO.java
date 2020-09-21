package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import model.vo.Cliente;
import model.vo.Endereco;
import model.vo.Pessoa;

public class PessoaDAO implements BaseDAO<Pessoa> {

	@Override
	public Pessoa inserir(Pessoa pessoa) {
		Connection conexao = Banco.getConnection();
		
		String sql = " INSERT INTO PESSOA (NOME, SOBRENOME, SEXO, ALTURA_EM_METROS, DATA_NASCIMENTO) " 
					+ " VALUES (?,?,?,?,?) ";
		
		PreparedStatement query = Banco.getPreparedStatementWithGeneratedKeys(conexao, sql);

		try {
			query.setString(1, pessoa.getNome());
			query.setString(2, pessoa.getSobrenome());
			query.setString(3, pessoa.getSexo()+"");
			query.setDouble(4, pessoa.getAlturaEmMetros());
			
			//Conversão de LocalDate (da entidade Pessoa) para sql.Date (para salvar no banco)
			Date dataNascimentoConvertidaParaSQL = java.sql.Date.valueOf(pessoa.getDataNascimento());
			query.setDate(5, dataNascimentoConvertidaParaSQL);
			
			int codigoRetorno = query.executeUpdate();
			if(codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO) {
				ResultSet resultado = query.getGeneratedKeys();
				int chaveGerada = resultado.getInt(1);
				
				pessoa.setId(chaveGerada);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao inserir pessoa.\nCausa: " + e.getMessage());
		}finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
				
		return pessoa;
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
		String sql = " SELECT * FROM PESSOA WHERE ID=? ";
		Pessoa pessoaBuscada = null;
		
		//Exemplo usando try-with-resources (similar ao bloco finally)
		try (Connection conexao = Banco.getConnection();
			PreparedStatement consulta = Banco.getPreparedStatement(conexao, sql);) {
			consulta.setInt(1, id);
			ResultSet conjuntoResultante = consulta.executeQuery();
			
			if(conjuntoResultante.next()) {
				pessoaBuscada = construirDoResultSet(conjuntoResultante);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar pessoa por Id (id: " + id + ") .\nCausa: " + e.getMessage());
		}
		
		return pessoaBuscada;	
	}

	@Override
	public Pessoa construirDoResultSet(ResultSet conjuntoResultante) throws SQLException {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(conjuntoResultante.getInt("id"));
		pessoa.setNome(conjuntoResultante.getString("nome"));
		pessoa.setSobrenome(conjuntoResultante.getString("sobrenome"));
		pessoa.setSexo(conjuntoResultante.getString("sexo").charAt(0));
		pessoa.setAlturaEmMetros(conjuntoResultante.getDouble("altura_em_metros"));
		
		//Converte a data oriunda do banco para LocalDate e preenche no VO
		Date dataSQL = conjuntoResultante.getDate("data_nascimento");
		pessoa.setDataNascimento(dataSQL.toLocalDate());
		
		return pessoa;
	}
}
