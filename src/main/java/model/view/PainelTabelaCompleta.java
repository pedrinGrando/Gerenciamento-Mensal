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
import model.vo.EnderecoVO;
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
		private JTextField campAno;
		private TabelaSeletor tabSeletor = new TabelaSeletor();
		private JLabel lblNewLabel_1;
		private JLabel lblNewLabel_2;
		private JComboBox cbMeses;
		private JButton btnFiltrar;
	
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
				atualizarTabelaMeses(userOnline);
			}
		});
		btnBuscar.setBounds(567, 22, 69, 23);
		add(btnBuscar);
		
		String[] meses = {"Janeiro", "Ferereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro"
				,"Novembro", "Dezembro"};
		
		tblTabelas = new JTable();
		tblTabelas.setForeground(new Color(0, 0, 0));
		this.limparTabela();
		tblTabelas.setBounds(10, 68, 655, 350);
		
		add(tblTabelas);
		
		btnFiltrar = new JButton("");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarTabelaComFiltro(userOnline);
				atualizarTabelaMeses(userOnline);
				
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
		
		campAno = new JTextField();
		campAno.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		campAno.setBounds(267, 22, 91, 22);
		add(campAno);
		campAno.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Mês");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_1.setBounds(143, 11, 46, 14);
		add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Ano");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_2.setBounds(267, 11, 46, 14);
		add(lblNewLabel_2);
		
		
		
	}

	 protected void buscarTabelaComFiltro(UsuarioVO userOnline) {
		tabSeletor = new TabelaSeletor();
		//seletor.setLimite(TAMANHO_PAGINA);
		//seletor.setPagina(paginaAtual);
		tabSeletor.setMes((String) cbMeses.getSelectedItem());
		//tabSeletor.setAno(Integer.parseInt(campAno.getText()));

		tabelas = (ArrayList<TabelaVO>) tabelaController.consultarComFiltros(tabSeletor);
		atualizarTabelaMeses(userOnline);
		//atualizarQuantidadePaginas();
	}
}