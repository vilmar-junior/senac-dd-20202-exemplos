package model.vo;

public class Endereco {
	
	//Atributos
	private int id;
	private String rua;
	private String cep;
	private String numero;
	private String estado;
	private String cidade;
	
	//Construtores()
	public Endereco() {
		
	}
	
	public Endereco(int id, String rua, String cep, String numero, String estado, String cidade) {
		super();
		this.id = id;
		this.rua = rua;
		this.cep = cep;
		this.numero = numero;
		this.estado = estado;
		this.cidade = cidade;
	}
	
	@Override
	public String toString() {
		return "Endereco [id=" + id + ", rua=" + rua + ", cep=" + cep + ", numero=" + numero + ", estado=" + estado
				+ ", cidade=" + cidade + "]";
	}

	//Getters e setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
}
