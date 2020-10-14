package view.exemplos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaMenuPrincipalExemplo extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenuPrincipalExemplo frame = new TelaMenuPrincipalExemplo();
					frame.setVisible(true);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaMenuPrincipalExemplo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setOpaque(false);
		menuBar.setBackground(Color.ORANGE);
		setJMenuBar(menuBar);
		
		JMenu menuCliente = new JMenu("Cliente");
		menuCliente.setIcon(new ImageIcon(TelaMenuPrincipalExemplo.class.getResource("/icons/icons8-gestão-de-cliente.png")));
		menuBar.add(menuCliente);
		
		JMenuItem menuItemCadastroCliente = new JMenuItem("Cadastrar");
		menuItemCadastroCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Cadastrar cliente");
			}
		});
		menuItemCadastroCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		menuItemCadastroCliente.setIcon(new ImageIcon(TelaMenuPrincipalExemplo.class.getResource("/icons/icons8-adicionar-usuário-masculino.png")));
		menuCliente.add(menuItemCadastroCliente);
		
		JMenu menuTelefone = new JMenu("Telefone");
		menuTelefone.setIcon(new ImageIcon(TelaMenuPrincipalExemplo.class.getResource("/icons/icons8-suporte-on-line-filled.png")));
		menuBar.add(menuTelefone);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
