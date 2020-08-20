package model.vo;

import java.util.List;

public class Cliente {
	
	private int id;
	private String nome;
	private String cpf;
	private Endereco endereco;
	private List<Telefone> telefones;
	
	public Cliente() {
		
	}
	
	public Cliente(int id, String nome, String cpf, Endereco endereco, List<Telefone> telefones) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefones = telefones;
	}
	
	@Override
	public String toString() {
		String numerosTelefones = "";
		
		for(Telefone tel: this.getTelefones()) {
			numerosTelefones += "(" + tel.getDdd() + ") " + tel.getNumero() + "\n"; 
		}
		
		
		return "Cliente: " + this.nome + " (" + this.cpf + ")\n"
				+ "Endereço: " + this.getEndereco().toString() + "\n"
				+ "Quantidade de telefones que possui: " + this.getTelefones().size() + "\n"
				+ "Número(s) de telefone(s): " + numerosTelefones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
}
