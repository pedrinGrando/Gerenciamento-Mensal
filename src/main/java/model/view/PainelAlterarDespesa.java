package model.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;

import model.vo.DespesaVO;
import model.vo.UsuarioVO;
import controller.DespesaController;

import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

public class PainelAlterarDespesa extends JPanel {
	
	private JTextField campNome;
	private JNumberFormatField campValor;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JComboBox cbDespesas;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JButton btnNewButton;
	
	DespesaController despController = new DespesaController();
	DespesaVO despesaVO = new DespesaVO();
	DespesaVO despesaAtualizar = new DespesaVO();
	DecimalFormat formato = new DecimalFormat("#,##0.00");

	/**
	 * Create the panel.
	 */
	public PainelAlterarDespesa(final UsuarioVO userOnline) {
		setBackground(new Color(0, 255, 255));
		setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PainelAlterarDespesa.class.getResource("/icons/bank.png")));
		lblNewLabel.setBounds(650, 505, 31, 39);
		add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Atualização de despesa ");
		lblNewLabel_1.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_1.setBounds(223, 11, 242, 19);
		add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 225, 696, 10);
		add(panel);
		
		
		lblNewLabel_2 = new JLabel("Seleciona a despesa que deseja alterar  "+userOnline.getLogin());
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_2.setBounds(21, 62, 278, 14);
		add(lblNewLabel_2);
		
		List<DespesaVO> despesasCadastradas = despController.consultarTodosController(userOnline);
		
		cbDespesas = new JComboBox(despesasCadastradas.toArray());
		cbDespesas.setBackground(new Color(192, 192, 192));
		cbDespesas.setBounds(260, 58, 110, 22);
		add(cbDespesas);
		
		campNome = new JTextField();
		campNome.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		campNome.setBounds(90, 271, 119, 20);
		add(campNome);
		campNome.setColumns(10);
		
		campValor = new JNumberFormatField();
		campValor.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		campValor.setBounds(90, 312, 119, 20);
		add(campValor);
		campValor.setColumns(10);
		
		
		
		JButton btnConsultar = new JButton("");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//CONSULTA POR NOME
				DespesaVO nome = (DespesaVO) cbDespesas.getSelectedItem();
				
				if (nome != null) {
					despesaVO = despController.consultarDespesaController(nome.getDespNome(), userOnline);
					
					campNome.setText(nome.getDespNome());
					
					String valorG = formato.format(despesaVO.getValor());
					String valorF = valorG.replace(".", ",");
					
					campValor.setText(valorF);
				} else {
					
					JOptionPane.showMessageDialog(null, "Selecione pelo menos uma despesa!", "Gerenciamento-Mensal", JOptionPane.WARNING_MESSAGE);
					
				}
				
				
			}
		});
		btnConsultar.setBorder(null);
		btnConsultar.setIcon(new ImageIcon(PainelAlterarDespesa.class.getResource("/icons/search.png")));
		btnConsultar.setBackground(new Color(0, 255, 255));
		btnConsultar.setBounds(376, 58, 40, 23);
		add(btnConsultar);
		
		lblNewLabel_3 = new JLabel("Nome : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_3.setBounds(21, 274, 59, 14);
		add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Valor : ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_4.setBounds(21, 315, 59, 14);
		add(lblNewLabel_4);
		
		
		btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean att = false;
				String valor1 = "";
				
                despesaAtualizar.setDespNome(campNome.getText());
				
				despesaAtualizar.setIdUsuario(userOnline.getIdUsuario());
				
				valor1 = campValor.getText().replace(",", ".");
				
				despesaAtualizar.setValor(Double.parseDouble(valor1.replace("R$", "")));
				
			    if (despesaAtualizar.getDespNome() != null || despesaAtualizar.getValor() != 0) {
			    	
			         att = despController.atualizarDespController(despesaAtualizar);
			         JOptionPane.showMessageDialog(null, "Despesa atualizada com sucesso!", "Gerenciamento-Mensal", JOptionPane.INFORMATION_MESSAGE);
				
			    } else {
			    	
			    	JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Gerenciamento-Mensal", JOptionPane.WARNING_MESSAGE);
			   
			    }
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(PainelAlterarDespesa.class.getResource("/icons/diskette.png")));
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(0, 255, 255));
		btnNewButton.setBounds(219, 309, 46, 23);
		add(btnNewButton);
		
	}
}
