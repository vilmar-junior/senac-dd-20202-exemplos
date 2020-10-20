package view.exemplos;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ProdutoController;
import model.vo.Produto;

public class TelaListagemSimplesProdutos extends JFrame {

	private static final String COR_AZUL = "Azul";
	private static final String COR_AMARELO = "Amarelo";
	private static final String COR_PRETO = "Preto";
	private static final String COR_VERDE = "Verde";
	private static final String COR_VERMELHO = "Vermelho";

	private JPanel contentPane;
	private JTable tblProdutos;
	private List<model.vo.Produto> produtosConsultados;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListagemSimplesProdutos frame = new TelaListagemSimplesProdutos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param cbCor
	 */
	public TelaListagemSimplesProdutos() {
		setTitle("Consulta de Produtos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnConsultar = new JButton(" Consultar todos os produtos");

		btnConsultar.setBounds(10, 26, 462, 40);
		contentPane.add(btnConsultar);

		String[] cores = { "---Selecione---", TelaListagemSimplesProdutos.COR_AZUL,
				TelaListagemSimplesProdutos.COR_AMARELO, TelaListagemSimplesProdutos.COR_PRETO,
				TelaListagemSimplesProdutos.COR_VERDE, TelaListagemSimplesProdutos.COR_VERMELHO };

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 78, 462, 14);
		contentPane.add(separator);

		tblProdutos = new JTable();
		this.limparTabela();
		tblProdutos.setBounds(10, 104, 462, 398);
		contentPane.add(tblProdutos);

		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultarProdutos();
			}
		});
	}

	protected void consultarProdutos() {
		ProdutoController controlador = new ProdutoController();

		List<Produto> produtos = controlador.listarTodosProdutos();
		atualizarTabelaProdutos(produtos);

	}

	protected void atualizarTabelaProdutos(List<Produto> produtos) {
		// atualiza o atributo produtosConsultados
		produtosConsultados = produtos;

		this.limparTabela();

		DefaultTableModel modelo = (DefaultTableModel) tblProdutos.getModel();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		for (Produto produto : produtos) {
			// Crio uma nova linha na tabela
			// Preencher a linha com os atributos do produto
			// na ORDEM do cabeçalho da tabela
			String dataFormatada = produto.getDataCadastro().format(formatter);

			String[] novaLinha = new String[] { produto.getId() + "", produto.getNome(), produto.getFabricante(),
					produto.getPeso() + "", dataFormatada };
			modelo.addRow(novaLinha);
		}

	}

	private void limparTabela() {
		tblProdutos.setModel(new DefaultTableModel(new String[][] { { "#", "Nome", "Marca", "Peso", "Dt. Cadastro" }, },
				new String[] { "#", "Nome", "Marca", "Peso", "Dt. Cadastro" }));
	}
}