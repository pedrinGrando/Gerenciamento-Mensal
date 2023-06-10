package model.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.EnderecoController;
import exceptions.EnderecoInvalidoException;
import controller.TabelaController;
import model.seletor.TabelaSeletor;
import model.vo.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

import exceptions.EnderecoInvalidoException;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class PainelTabelaCompleta extends JPanel {

	//Atributos da tela (componentes visuais)
		private JTable tblTabelas;
		private JButton btnBuscar;
		private JButton btnEditar;
		private JButton btnExcluir;
		
		//Objeto usado para armazenar o endereço que o usuário selecionar na tabela (tblEnderecos)
		private TabelaVO tabelaSelecionada = new TabelaVO();
		private TabelaController tabelaController = new TabelaController();
		//Lista para armezenar os endereços consultados no banco
		private ArrayList<TabelaVO> tabelas;
		private String[] nomesColunas = { "Nome", "Mês", "Ano", "Total restante", "Saldo final" };
		private JLabel lblNewLabel;
		private TabelaSeletor tabSeletor = new TabelaSeletor();
		private JLabel lblNewLabel_1;
		private JComboBox cbMeses;
		private JButton btnFiltrar;
		private final int TAMANHO_PAGINA = 5;
		private int paginaAtual = 1;
		private int totalPaginas = 0;
		private TabelaSeletor seletor = new TabelaSeletor();
		private JButton btnVoltarPagina;
		private JButton btnAvancarPagina;
		private JLabel lblPaginacao;
	
		//Métodos usados no JTable
		private void limparTabela() {
			tblTabelas.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, 
					nomesColunas));
		}

		//Chamado sempre no "Buscar"
		private void atualizarTabelaMeses(UsuarioVO userOnline) {
			this.limparTabela();

			TabelaVO tabelaVO = new TabelaVO();
			tabelaVO.setIdUsuario(userOnline.getIdUsuario());
			
			TabelaController tabelaController = new TabelaController();
			tabelas = (ArrayList<TabelaVO>) tabelaController.consultarTodasController(tabelaVO);
			
			DefaultTableModel model = (DefaultTableModel) tblTabelas.getModel();
			//Preenche os valores na tabela linha a linha
			for (TabelaVO t : tabelas) {
				Object[] novaLinhaDaTabela = new Object[7];
				novaLinhaDaTabela[0] = userOnline.getNome();
				novaLinhaDaTabela[1] = t.getMes();
				novaLinhaDaTabela[2] = t.getAno();
				novaLinhaDaTabela[3] = t.getTotalRest();
				novaLinhaDaTabela[4] = t.getSaldoFinal();

				model.addRow(novaLinhaDaTabela);
			}
		}
		
	/**
	 * Create the panel.
	 */
	public PainelTabelaCompleta(final UsuarioVO userOnline) {
		
		//setBackground(new Color(0, 0, 0));
		setBackground(new Color(0, 255, 255));
		setLayout(null);
		btnBuscar = new JButton("Buscar ");
		btnBuscar.setBorder(null);
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnBuscar.setBackground(new Color(0, 255, 255));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarTabelaComFiltro(userOnline);
				atualizarTabelaMeses(userOnline);
			}
		});
		btnBuscar.setBounds(567, 22, 69, 23);
		add(btnBuscar);
		
		String[] meses = {"janeiro", "ferereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro"
				,"novembro", "dezembro"};
		
		tblTabelas = new JTable();
		tblTabelas.setForeground(new Color(0, 0, 0));
		this.limparTabela();
		tblTabelas.setBounds(10, 66, 655, 350);
		
		add(tblTabelas);
		
		btnFiltrar = new JButton("");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarTabelaComFiltro(userOnline);
				
			}
		});
		btnFiltrar.setBorder(null);
		btnFiltrar.setIcon(new ImageIcon(PainelTabelaCompleta.class.getResource("/icons/filter.png")));
		btnFiltrar.setBackground(new Color(0, 255, 255));
		btnFiltrar.setBounds(632, 22, 33, 23);
		add(btnFiltrar);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PainelTabelaCompleta.class.getResource("/icons/bank.png")));
		lblNewLabel.setBounds(637, 440, 49, 24);
		add(lblNewLabel);
		
		cbMeses = new JComboBox(meses);
		cbMeses.setFont(new Font("Tahoma", Font.ITALIC, 11));
		cbMeses.setBackground(new Color(192, 192, 192));
		cbMeses.setBounds(143, 22, 100, 22);
		add(cbMeses);
		
		lblNewLabel_1 = new JLabel("Mês");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_1.setBounds(143, 11, 46, 14);
		add(lblNewLabel_1);
		
		btnVoltarPagina = new JButton("<< Voltar");
		btnVoltarPagina.setBorder(null);
		btnVoltarPagina.setBackground(new Color(255, 255, 255));
		btnVoltarPagina.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnVoltarPagina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaAtual--;
				buscarTabelaComFiltro(userOnline);
				//lblPaginacao.setText(paginaAtual + " / " + totalPaginas);
				btnVoltarPagina.setEnabled(paginaAtual > 1);
				btnAvancarPagina.setEnabled(paginaAtual < totalPaginas);
			}
		});
		btnVoltarPagina.setEnabled(false);
		btnVoltarPagina.setBounds(162, 427, 128, 23);
		add(btnVoltarPagina);
		
		btnAvancarPagina = new JButton("Avançar >>");
		btnAvancarPagina.setBorder(null);
		btnAvancarPagina.setBackground(new Color(255, 255, 255));
		btnAvancarPagina.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnAvancarPagina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paginaAtual++;
				buscarTabelaComFiltro(userOnline);
				//lblPaginacao.setText(paginaAtual + " / " + totalPaginas);
				btnVoltarPagina.setEnabled(paginaAtual > 1);
				btnAvancarPagina.setEnabled(paginaAtual < totalPaginas);
			}
		});
		btnAvancarPagina.setBounds(355, 427, 128, 23);
		add(btnAvancarPagina);
		
		lblPaginacao = new JLabel("1 / " + totalPaginas);
		lblPaginacao.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblPaginacao.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaginacao.setBounds(281, 427, 77, 23);
		add(lblPaginacao);
		
		atualizarQuantidadePaginas();
		
	}
	
	
	
	private void atualizarQuantidadePaginas() {
		//Cálculo do total de páginas (poderia ser feito no backend)
		int totalRegistros = tabelaController.contarTotalRegistrosComFiltros(seletor);
		
		//QUOCIENTE da divisão inteira
		totalPaginas = totalRegistros / TAMANHO_PAGINA;
		
		//RESTO da divisão inteira
		if(totalRegistros % TAMANHO_PAGINA > 0) { 
			totalPaginas++;
		}
		
		//lblPaginacao.setText(paginaAtual + " / " + totalPaginas);
	}

	 protected void buscarTabelaComFiltro(UsuarioVO userOnline) {
		tabSeletor = new TabelaSeletor();
		tabSeletor.setLimite(TAMANHO_PAGINA);
		tabSeletor.setPagina(paginaAtual);
		tabSeletor.setMes((String) cbMeses.getSelectedItem());
		//tabSeletor.setMes(campMes.getText());
		//tabSeletor.setAno(campMes.getText());

		tabelas = (ArrayList<TabelaVO>) tabelaController.consultarComFiltros(tabSeletor);
		atualizarTabelaMeses(userOnline);
		atualizarQuantidadePaginas();
	}

	
}