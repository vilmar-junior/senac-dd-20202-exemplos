package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ClienteController;
import model.vo.Cliente;
import model.vo.Endereco;

public class TelaCadastroCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JComboBox cbEndereco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroCliente frame = new TelaCadastroCliente();
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
	public TelaCadastroCliente() {
		setTitle("Cadastro de cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(11, 40, 61, 16);
		contentPane.add(lblNome);
		
		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setBounds(11, 80, 61, 16);
		contentPane.add(lblCPF);
		
		txtNome = new JTextField();
		txtNome.setBounds(84, 35, 370, 26);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtCPF = new JTextField();
		txtCPF.setBounds(84, 75, 370, 26);
		contentPane.add(txtCPF);
		txtCPF.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Preencher um objeto CLIENTE com os dados da tela
				Cliente novoCliente = new Cliente();
				novoCliente.setNome(txtNome.getText());
				novoCliente.setCpf(txtCPF.getText());
				novoCliente.setEndereco((Endereco) cbEndereco.getSelectedItem());
				
				//Instanciar um controller adequado
				ClienteController controller = new ClienteController(); 
				
				//Chamar o método SALVAR no controller e pegar a mensagem retornada
				String mensagem = controller.salvar(novoCliente);
				
				//Mostra a mensagem devolvida pelo controller
				JOptionPane.showMessageDialog(contentPane, mensagem);
			}
		});
		btnSalvar.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		btnSalvar.setForeground(new Color(0, 128, 128));
		btnSalvar.setBounds(18, 166, 436, 29);
		contentPane.add(btnSalvar);
		
		JLabel lblEndereco = new JLabel("Endereço:");
		lblEndereco.setBounds(11, 123, 61, 16);
		contentPane.add(lblEndereco);
		
		ArrayList<Endereco> enderecos = obterEnderecosMock();
		cbEndereco = new JComboBox(enderecos.toArray());
		cbEndereco.setBounds(84, 119, 370, 27);
		contentPane.add(cbEndereco);
	}

	private ArrayList<Endereco> obterEnderecosMock() {
		ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
		enderecos.add(new Endereco(1, "Felipe Schmidt", "202-A", "SC", "Florianópolis"));
		enderecos.add(new Endereco(2, "Santos Saraiva", "1223", "SC", "Florianópolis"));
		enderecos.add(new Endereco(3, "Lauro Linhares", "S/N", "SC", "Florianópolis"));
		enderecos.add(new Endereco(4, "Heitor Blum", "2", "SC", "Florianópolis"));
		
		return enderecos;
	}
}
