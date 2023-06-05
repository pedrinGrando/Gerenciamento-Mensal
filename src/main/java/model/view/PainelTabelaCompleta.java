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
import model.vo.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

import exceptions.EnderecoInvalidoException;

public class PainelTabelaCompleta extends JPanel {

	//Atributos da tela (componentes visuais)
		private JTable tblTabelas;
		private JButton btnBuscar;
		private JButton btnEditar;
		private JButton btnExcluir;
		
		//Objeto usado para armazenar o endereço que o usuário selecionar na tabela (tblEnderecos)
		private TabelaVO tabelaSelecionada = new TabelaVO();
		private EnderecoController tabelaController = new EnderecoController();
		//Lista para armezenar os endereços consultados no banco
		private ArrayList<TabelaVO> tabelas;
		private String[] nomesColunas = { "Nome", "Mês", "Ano", "Total restante", "Saldo final" };
	
		//Métodos usados no JTable
		private void limparTabela() {
			tblTabelas.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, 
					nomesColunas));
		}

		//Chamado sempre no "Buscar"
		private void atualizarTabelaEnderecos(UsuarioVO userOnline) {
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
		setBackground(new Color(192, 192, 192));
		setLayout(null);
		btnBuscar = new JButton("Buscar Todos");
		btnBuscar.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnBuscar.setBackground(new Color(192, 192, 192));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarTabelaEnderecos(userOnline);
			}
		});
		btnBuscar.setBounds(460, 22, 145, 23);
		add(btnBuscar);
		
		tblTabelas = new JTable();
		this.limparTabela();
		tblTabelas.setBounds(10, 68, 655, 350);
		
		
		add(tblTabelas);
		
		JButton btnFiltrar = new JButton("");
		btnFiltrar.setBackground(new Color(192, 192, 192));
		btnFiltrar.setBounds(604, 22, 33, 23);
		add(btnFiltrar);
	}
}