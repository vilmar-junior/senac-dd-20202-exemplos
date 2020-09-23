package view.exemplos;

import java.awt.Color;
import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 
 * @author Vilmar César Pereira Júnior
 *
 */
public class TelaComponentesComMascaras extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField formattedTextFieldCnpj;
	private JFormattedTextField formattedTextFieldCpf;
	private JFormattedTextField formattedTextFieldCep;
	private JFormattedTextField formattedTextFieldTelefone;
	private JFormattedTextField formattedTextFieldPlaca;
	private JButton btnPegarValoresEm;
	private JTextArea txtAreaValoresDigitados;
	private JLabel lblQuantidadeCaracteresRestantes;
	private static int TAMANHO_MAXIMO_DESCRICAO = 50;
	private JLabel lblExemploTextArea;
	private JTextArea textAreaValoresCamposComMascara;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaComponentesComMascaras frame = new TelaComponentesComMascaras();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaComponentesComMascaras() {
		setTitle("Exemplos de Máscaras");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlaca.setBounds(10, 25, 75, 14);
		contentPane.add(lblPlaca);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefone.setBounds(10, 55, 75, 14);
		contentPane.add(lblTelefone);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCpf.setBounds(199, 25, 61, 14);
		contentPane.add(lblCpf);

		JLabel lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCnpj.setBounds(199, 56, 61, 14);
		contentPane.add(lblCnpj);

		JLabel lblCep = new JLabel("CEP:");
		lblCep.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCep.setBounds(10, 86, 74, 14);
		contentPane.add(lblCep);

		JLabel lblValorEmReais = new JLabel("R$:");
		lblValorEmReais.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorEmReais.setBounds(199, 83, 61, 14);
		contentPane.add(lblValorEmReais);

		JNumberFormatField txtValorEmReais = new JNumberFormatField(2);
		txtValorEmReais.setBounds(270, 86, 155, 20);
		contentPane.add(txtValorEmReais);
		
		btnPegarValoresEm = new JButton("Pegar valores em String");
		btnPegarValoresEm.setIcon(new ImageIcon(TelaComponentesComMascaras.class.getResource("/icons/icons8-confiança.png")));
		btnPegarValoresEm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String textoCompleto = "Placa (sem máscara): " + formattedTextFieldPlaca.getText().replace("-","") + "\n";
				textoCompleto += "CPF: " + formattedTextFieldCpf.getText() + "\n";
				textoCompleto += "CNPJ: " + formattedTextFieldCnpj.getText() + "\n";
				textoCompleto += "Telefone: " + formattedTextFieldTelefone.getText() + "\n";
				textoCompleto += "CEP: " + formattedTextFieldCep.getText() + "\n";
				
				textAreaValoresCamposComMascara.setText(textoCompleto);
			}
		});
		btnPegarValoresEm.setBounds(100, 126, 240, 50);
		contentPane.add(btnPegarValoresEm);

		try {
			MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
			MaskFormatter mascaraCnpj = new MaskFormatter("##.###.###/####-##");
			MaskFormatter mascaraTelefone = new MaskFormatter("(##)####-####");
			MaskFormatter mascaraCep = new MaskFormatter("#####-###");
			MaskFormatter mascaraPlaca = new MaskFormatter("UUU-####");

			formattedTextFieldPlaca = new JFormattedTextField(mascaraPlaca);
			formattedTextFieldPlaca.setBounds(95, 22, 105, 20);
			contentPane.add(formattedTextFieldPlaca);

			formattedTextFieldTelefone = new JFormattedTextField(mascaraTelefone);
			formattedTextFieldTelefone.setBounds(94, 53, 95, 20);
			contentPane.add(formattedTextFieldTelefone);

			formattedTextFieldCep = new JFormattedTextField(mascaraCep);
			formattedTextFieldCep.setBounds(94, 83, 95, 20);
			contentPane.add(formattedTextFieldCep);
			
			formattedTextFieldCpf = new JFormattedTextField(mascaraCpf);
			formattedTextFieldCpf.setBounds(270, 22, 154, 20);
			contentPane.add(formattedTextFieldCpf);
			
			formattedTextFieldCnpj = new JFormattedTextField(mascaraCnpj);
			formattedTextFieldCnpj.setBounds(270, 53, 154, 20);
			contentPane.add(formattedTextFieldCnpj);
			
			txtAreaValoresDigitados = new JTextArea();
			txtAreaValoresDigitados.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					int quantidadeDigitada = txtAreaValoresDigitados.getText().length();
					int quantidadeAindaDisponivel = TAMANHO_MAXIMO_DESCRICAO - quantidadeDigitada;
					
					if(quantidadeAindaDisponivel <= 0) {
						String textoDigitado = txtAreaValoresDigitados.getText().substring(0,TAMANHO_MAXIMO_DESCRICAO);
						txtAreaValoresDigitados.setText(textoDigitado);
						lblQuantidadeCaracteresRestantes.setForeground(Color.RED);
						quantidadeAindaDisponivel = 0;
					}else {
						lblQuantidadeCaracteresRestantes.setForeground(Color.GREEN);
					}
					
					lblQuantidadeCaracteresRestantes.setText(quantidadeAindaDisponivel + "");
				}
			});
			txtAreaValoresDigitados.setBounds(35, 334, 390, 94);
			contentPane.add(txtAreaValoresDigitados);
			
			JLabel lblCaracteresRestantes = new JLabel("Caracteres restantes: ");
			lblCaracteresRestantes.setBounds(35, 440, 146, 16);
			contentPane.add(lblCaracteresRestantes);
			
			lblQuantidadeCaracteresRestantes = new JLabel(TAMANHO_MAXIMO_DESCRICAO + "");
			lblQuantidadeCaracteresRestantes.setBounds(181, 440, 61, 16);
			contentPane.add(lblQuantidadeCaracteresRestantes);
			
			lblExemploTextArea = new JLabel("Exemplo de JTextArea");
			lblExemploTextArea.setBounds(38, 306, 143, 16);
			contentPane.add(lblExemploTextArea);
			
			textAreaValoresCamposComMascara = new JTextArea();
			textAreaValoresCamposComMascara.setEnabled(false);
			textAreaValoresCamposComMascara.setBounds(35, 182, 390, 118);
			contentPane.add(textAreaValoresCamposComMascara);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro no sistema, entre em contato com o administrador.");
			System.out.println("Causa da exceção: " + e.getMessage());
		}
	}
}
