package model.view;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import model.vo.TabelaVO;
import model.vo.*;
import controller.*;

public class PainelTabelaCompleta extends JPanel {

	/**
	 * Create the panel.
	 */
	
		private JTable tblConsultas;
		private ArrayList<TabelaVO> tabelas;
		private String[] nomesColunas = { "Mês", "Ano", "Restante", "Saldo Final" };
		private JTextField txtNome;
		private MaskFormatter mascaraCpf;
		private JFormattedTextField txtCPF;
		
		//componentes externos -> dependência "LGoodDatePicker" foi adicionada no pom.xml
		
		
		TabelaController tabelaController = new TabelaController();
		TabelaVO tabelaSelecionada;
		
		
		private void limparTabelaConsultas() {
			tblConsultas.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
		}

		private void atualizarTabela() {
			this.limparTabelaConsultas();
 
			DefaultTableModel model = (DefaultTableModel) tblConsultas.getModel();

			for (TabelaVO t : tabelas) {
				Object[] novaLinhaDaTabela = new Object[4];
				novaLinhaDaTabela[0] = t.getMes();
				novaLinhaDaTabela[1] = t.getAno();
				novaLinhaDaTabela[2] = t.getTotalRest();
				novaLinhaDaTabela[3] = t.getSaldoFinal();
				
				model.addRow(novaLinhaDaTabela);
			}
		}
		
		private void buscarTelefones() {
			this.tabelas = tabelaController.consultarTodasController();
			this.atualizarTabela();
		}
		
		public PainelTabelaCompleta(final UsuarioVO userOnline) {
			
			setLayout(new FormLayout(new ColumnSpec[] {
					FormSpecs.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
					FormSpecs.RELATED_GAP_ROWSPEC,
					FormSpecs.DEFAULT_ROWSPEC,
					FormSpecs.RELATED_GAP_ROWSPEC,
					RowSpec.decode("default:grow"),}));
			
			        tblConsultas = new JTable();
					add(tblConsultas, "2, 4, fill, fill");
					buscarTelefones();
		}
	}
			
	

