package view;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import model.vo.Pessoa;

public class Principal {
	
	//Comunicação entre camadas do MVC
	//View <--> Controller <--> Model
	//1- Obter os dados na tela
	
	//2- Passar para o controller, que pode fazer validações
	
	//3- Passar para o model, que salvará usando uma classe DAO

	public static void main(String[] args) {
		
		LocalDate dataNascimento = LocalDate.of(2000, 8, 12);
		
		Pessoa joao = new Pessoa("João", "Silva Sauro", 'M', 1.9, dataNascimento);
		JOptionPane.showMessageDialog(null, "Idade do João: " + joao.getIdade());
	}
}
