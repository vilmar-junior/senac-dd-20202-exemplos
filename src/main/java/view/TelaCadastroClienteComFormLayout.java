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
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class TelaCadastroClienteComFormLayout extends JFrame {

	private JPanel contentPane;
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
					TelaCadastroClienteComFormLayout frame = new TelaCadastroClienteComFormLayout();
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
	public TelaCadastroClienteComFormLayout() {
		setTitle("Cadastro de cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("61px"),
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("166px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("61px"),
				ColumnSpec.decode("141px:grow"),},
			new RowSpec[] {
				RowSpec.decode("35px"),
				RowSpec.decode("26px"),
				FormSpecs.PARAGRAPH_GAP_ROWSPEC,
				RowSpec.decode("26px"),
				FormSpecs.PARAGRAPH_GAP_ROWSPEC,
				RowSpec.decode("27px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("45px"),
				FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC,
				RowSpec.decode("23px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("29px"),}));
		
		JLabel lblNome = new JLabel("Nome:");
		contentPane.add(lblNome, "2, 2, fill, center");
		
		JLabel lblCPF = new JLabel("CPF:");
		contentPane.add(lblCPF, "2, 4, fill, center");
		
		txtNome = new JTextField();
		contentPane.add(txtNome, "4, 2, 4, 1, fill, fill");
		txtNome.setColumns(10);
		
		txtCPF = new JTextField();
		contentPane.add(txtCPF, "4, 4, 4, 1, fill, top");
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
		contentPane.add(btnSalvar, "2, 12, 6, 1, fill, top");
		
		JLabel lblEndereco = new JLabel("Endereço:");
		contentPane.add(lblEndereco, "2, 6, left, center");
		
		ArrayList<Endereco> enderecos = obterEnderecosMock();
		cbEndereco = new JComboBox(enderecos.toArray());
		contentPane.add(cbEndereco, "4, 6, 4, 1, fill, top");
		
		JLabel lblBomPagador = new JLabel("Bom pagador?");
		contentPane.add(lblBomPagador, "4, 10, right, bottom");
		
		lblBomPagadorSim = new JLabel("SIM");
		lblBomPagadorSim.setVisible(false);
		lblBomPagadorSim.setForeground(new Color(0, 128, 0));
		contentPane.add(lblBomPagadorSim, "6, 10, fill, bottom");
		
		lblBomPagadorNao = new JLabel("NÃO");
		lblBomPagadorNao.setForeground(new Color(255, 0, 0));
		contentPane.add(lblBomPagadorNao, "6, 10, fill, bottom");
		
		chkAdimplente = new JCheckBox("Adimplente");
		chkAdimplente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chkAdimplente.isSelected()) {
					lblBomPagadorSim.setVisible(true);
					lblBomPagadorNao.setVisible(false);
				} else {
					lblBomPagadorSim.setVisible(false);
					lblBomPagadorNao.setVisible(true);
				}
			}
		});
		contentPane.add(chkAdimplente, "4, 8, left, top");
		
		JRadioButton rdbtnSim = new JRadioButton("Sim");
		rgroupOpcoes.add(rdbtnSim);
		contentPane.add(rdbtnSim, "7, 8, fill, bottom");
		
		JRadioButton rdbtnNao = new JRadioButton("Não");
		rgroupOpcoes.add(rdbtnNao);
		contentPane.add(rdbtnNao, "7, 10, fill, top");
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Talvez");
		rgroupOpcoes.add(rdbtnNewRadioButton);
		contentPane.add(rdbtnNewRadioButton, "7, 8, fill, top");
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
