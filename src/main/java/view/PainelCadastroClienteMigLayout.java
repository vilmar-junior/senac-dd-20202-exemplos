package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ClienteController;
import model.vo.Cliente;
import model.vo.Endereco;

public class PainelCadastroClienteMigLayout extends JPanel {

	private JTextField txtNome;
	private JTextField txtCPF;
	private JComboBox cbEndereco;
	private JCheckBox chkAdimplente;
	private JLabel lblBomPagadorNao;
	private JLabel lblBomPagadorSim;
	private final ButtonGroup rgroupOpcoes = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PainelCadastroClienteMigLayout frame = new PainelCadastroClienteMigLayout();
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
	public PainelCadastroClienteMigLayout() {
		setBounds(100, 100, 460, 311);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(11, 40, 61, 16);
		this.add(lblNome);

		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setBounds(11, 80, 61, 16);
		this.add(lblCPF);

		txtNome = new JTextField();
		txtNome.setBounds(84, 35, 370, 26);
		this.add(txtNome);
		txtNome.setColumns(10);

		txtCPF = new JTextField();
		txtCPF.setBounds(84, 75, 370, 26);
		this.add(txtCPF);
		txtCPF.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Preencher um objeto CLIENTE com os dados da tela
				Cliente novoCliente = new Cliente();
				novoCliente.setNome(txtNome.getText());
				novoCliente.setCpf(txtCPF.getText());
				novoCliente.setEndereco((Endereco) cbEndereco.getSelectedItem());

				// Instanciar um controller adequado
				ClienteController controller = new ClienteController();

				// Chamar o método SALVAR no controller e pegar a mensagem retornada
				String mensagem = controller.salvar(novoCliente);

				// Mostra a mensagem devolvida pelo controller
				JOptionPane.showMessageDialog(null, mensagem);
			}
		});
		btnSalvar.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		btnSalvar.setForeground(new Color(0, 128, 128));
		btnSalvar.setBounds(11, 241, 436, 29);
		this.add(btnSalvar);

		JLabel lblEndereco = new JLabel("Endereço:");
		lblEndereco.setBounds(11, 123, 61, 16);
		this.add(lblEndereco);

		ArrayList<Endereco> enderecos = obterEnderecosMock();
		cbEndereco = new JComboBox(enderecos.toArray());
		cbEndereco.setBounds(84, 119, 370, 27);
		this.add(cbEndereco);

		JLabel lblBomPagador = new JLabel("Bom pagador?");
		lblBomPagador.setBounds(149, 213, 101, 16);
		this.add(lblBomPagador);

		lblBomPagadorSim = new JLabel("SIM");
		lblBomPagadorSim.setVisible(false);
		lblBomPagadorSim.setForeground(new Color(0, 128, 0));
		lblBomPagadorSim.setBounds(252, 213, 61, 16);
		this.add(lblBomPagadorSim);

		lblBomPagadorNao = new JLabel("NÃO");
		lblBomPagadorNao.setForeground(new Color(255, 0, 0));
		lblBomPagadorNao.setBounds(252, 213, 61, 16);
		this.add(lblBomPagadorNao);

		chkAdimplente = new JCheckBox("Adimplente");
		chkAdimplente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkAdimplente.isSelected()) {
					lblBomPagadorSim.setVisible(true);
					lblBomPagadorNao.setVisible(false);
				} else {
					lblBomPagadorSim.setVisible(false);
					lblBomPagadorNao.setVisible(true);
				}
			}
		});
		chkAdimplente.setBounds(84, 158, 128, 23);
		this.add(chkAdimplente);

		JRadioButton rdbtnSim = new JRadioButton("Sim");
		rgroupOpcoes.add(rdbtnSim);
		rdbtnSim.setBounds(313, 180, 141, 23);
		this.add(rdbtnSim);

		JRadioButton rdbtnNao = new JRadioButton("Não");
		rgroupOpcoes.add(rdbtnNao);
		rdbtnNao.setBounds(313, 206, 141, 23);
		this.add(rdbtnNao);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Talvez");
		rgroupOpcoes.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(313, 158, 141, 23);
		this.add(rdbtnNewRadioButton);
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
