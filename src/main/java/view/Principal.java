package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.vo.*;
public class Principal {

	public static void main(String[] args) {
		//Aula 05: Collections
		Map<String, String> mapNomes = new HashMap<>();
		mapNomes.put("alt", "Altieste");
		mapNomes.put("rod", "Rodson");
		mapNomes.put("luc", "Lucas Zarbato");
		mapNomes.put("luc", "Lucas Mendes");

		System.out.println("0---- Tamanho do MAPA de nomes: " + mapNomes.size());
		System.out.println("0---- Chaves do MAPA de nomes: " + mapNomes.keySet());
		System.out.println("0---- Valores do MAPA de nomes: " + mapNomes.values());


		
		List<Telefone> listaDeTelefones = new ArrayList<Telefone>();
		Telefone telefone1 = new Telefone(2, "048", "32323232", false, "131323");
		listaDeTelefones.add(telefone1);
		listaDeTelefones.add(telefone1);
		listaDeTelefones.add(telefone1);
		listaDeTelefones.remove(0);
		
		Set<Telefone> conjuntoTelefones = new HashSet<Telefone>();
		conjuntoTelefones.add(telefone1);
		conjuntoTelefones.add(telefone1);
		conjuntoTelefones.add(telefone1);

		System.out.println("1---- Tamanho da LISTA de telefones: " + listaDeTelefones.size());
		System.out.println("2---- Tamanho do CONJUNTO de telefones: " + conjuntoTelefones.size());

		
//		
//		Cliente joao = new Cliente(10, "João", "01122233311", new Endereco(), telefones); 
//		telefone1.setIdClienteTitularConta(joao.getId());
//		
//		System.out.println("********** Dados do cliente **********\n" + joao.toString());
//		System.out.println("********** Dados dos telefones **********\n" + telefones.toString());

	}

}
