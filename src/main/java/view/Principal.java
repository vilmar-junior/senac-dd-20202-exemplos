package view;

import java.util.ArrayList;
import java.util.List;

import model.vo.*;
public class Principal {

	public static void main(String[] args) {
		List<Telefone> telefones = new ArrayList<Telefone>();
		Telefone telefone1 = new Telefone(2, "048", "32323232", false, "131323");
		telefones.add(telefone1);
		
		Cliente joao = new Cliente(10, "João", "01122233311", new Endereco(), telefones); 
		telefone1.setTitularConta(joao);
		
		System.out.println("********** Dados do cliente **********\n" + joao.toString());
		System.out.println("********** Dados dos telefones **********\n" + telefones.toString());

	}

}
