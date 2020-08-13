package view;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import model.vo.Pessoa;

public class Principal {

	public static void main(String[] args) {
		
		LocalDate dataNascimento = LocalDate.of(2000, 8, 12);
		
		Pessoa joao = new Pessoa("João", "Silva Sauro", 'M', 1.9, dataNascimento);
		
		JOptionPane.showMessageDialog(null, "Idade do Joao: " + joao.getIdade());

	}

}
