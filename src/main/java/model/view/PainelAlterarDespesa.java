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
	private JLabel lblIcon;
	private JLabel lblTitulo;
	private JLabel visaoTItulo;
	private JComboBox cbDespesas;
	private JLabel visaoNome;
	private JLabel visaoValor;
	private JButton btnEditar;
	
	DespesaController despController = new DespesaController();
	DespesaVO despesaVO = new DespesaVO();
	DespesaVO despesaAtualizar = new DespesaVO();
	DecimalFormat formato = new DecimalFormat("#,##0.00");
	private JPanel panel;

	/**
	 * Create the panel.
	 */
	public PainelAlterarDespesa(final UsuarioVO userOnline) {
		setBackground(new Color(0, 255, 255));
		setLayout(null);
		
		lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(PainelAlterarDespesa.class.getResource("/icons/bank.png")));
		lblIcon.setBounds(650, 505, 31, 39);
		add(lblIcon);
		
		lblTitulo = new JLabel("Atualização de despesa ");
		lblTitulo.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 13));
		lblTitulo.setBounds(223, 11, 242, 19);
		add(lblTitulo);
		
	    panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 225, 733, 10);
		add(panel);
		
		visaoTItulo = new JLabel("Seleciona a despesa que deseja alterar  "+userOnline.getLogin());
		visaoTItulo.setFont(new Font("Tahoma", Font.ITALIC, 11));
		visaoTItulo.setBounds(21, 62, 278, 14);
		add(visaoTItulo);
		
		List<DespesaVO> despesasCadastradas = despController.consultarTodosController(userOnline);
		
		cbDespesas = new JComboBox(despesasCadastradas.toArray());
		cbDespesas.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		cbDespesas.setBackground(new Color(192, 192, 192));
		cbDespesas.setBounds(260, 58, 110, 22);
		add(cbDespesas);
		
		campNome = new JTextField();
		campNome.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		campNome.setBounds(90, 271, 119, 20);
		add(campNome);
		campNome.setColumns(10);
		
		campValor = new JNumberFormatField();
		campValor.setFont(new Font("Tahoma", Font.ITALIC, 11));
		campValor.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
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
		btnConsultar.setIcon(new ImageIcon(PainelAlterarDespesa.class.getResource("/icons/loupe.png")));
		btnConsultar.setBackground(new Color(0, 255, 255));
		btnConsultar.setBounds(376, 58, 31, 19);
		add(btnConsultar);
		
		visaoNome = new JLabel("Nome : ");
		visaoNome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		visaoNome.setBounds(35, 274, 59, 14);
		add(visaoNome);
		
		visaoValor = new JLabel("Valor : ");
		visaoValor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		visaoValor.setBounds(35, 315, 59, 14);
		add(visaoValor);
		
		
		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean att = false;
				String valor1 = "";
				
                despesaAtualizar.setDespNome(campNome.getText());
				
				despesaAtualizar.setIdUsuario(userOnline.getIdUsuario());
				
				valor1 = campValor.getText().replace(".", "");
				valor1 = valor1.replace(",", ".");
				
				despesaAtualizar.setValor(Double.parseDouble(valor1.replace("R$", "")));
				
			    if (despesaAtualizar.getDespNome() != null || despesaAtualizar.getValor() != 0) {
			    	
			         att = despController.atualizarDespController(despesaAtualizar);
			         JOptionPane.showMessageDialog(null, "Despesa atualizada com sucesso!", "Gerenciamento-Mensal", JOptionPane.INFORMATION_MESSAGE);
				
			    } else {
			    	
			    	JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Gerenciamento-Mensal", JOptionPane.WARNING_MESSAGE);
			    }
				
			}
		});
		btnEditar.setIcon(new ImageIcon(PainelAlterarDespesa.class.getResource("/icons/diskette.png")));
		btnEditar.setBorder(null);
		btnEditar.setBackground(new Color(0, 255, 255));
		btnEditar.setBounds(219, 309, 46, 23);
		add(btnEditar);
	}
}
