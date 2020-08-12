package model.vo;

import java.time.LocalDate;
import java.time.Period;

public class Pessoa {

	private String nome;
	private String sobrenome;
	private char sexo;
	private double alturaEmMetros; 
	private LocalDate dataNascimento;
	
	public Pessoa(String nome, String sobrenome, Character sexo, double alturaEmMetros, LocalDate dataNascimento) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.sexo = sexo;
		this.alturaEmMetros = alturaEmMetros;
		this.dataNascimento = dataNascimento;
	}
	
	public int getIdade() {
		int idade;
		
		Period periodo = Period.between(this.getDataNascimento(), LocalDate.now());
		idade = periodo.getYears();
		
		return idade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public double getAlturaEmMetros() {
		return alturaEmMetros;
	}

	public void setAlturaEmMetros(double alturaEmMetros) {
		this.alturaEmMetros = alturaEmMetros;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}