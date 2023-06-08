package model.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
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

	/**
	 * Create the panel.
	 */
	public PainelAlterarDespesa(final UsuarioVO userOnline) {
		setBackground(new Color(0, 255, 255));
		setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PainelAlterarDespesa.class.getResource("/icons/bank.png")));
		lblNewLabel.setBounds(588, 462, 31, 39);
		add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Atualização de despesa ");
		lblNewLabel_1.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_1.setBounds(223, 11, 242, 19);
		add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 225, 629, 10);
		add(panel);
		
		
		lblNewLabel_2 = new JLabel("Seleciona a despesa que deseja alterar  "+userOnline.getLogin());
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_2.setBounds(21, 62, 278, 14);
		add(lblNewLabel_2);
		
		List<DespesaVO> despesasCadastradas = despController.consultarTodosController(userOnline);
		
		cbDespesas = new JComboBox(despesasCadastradas.toArray());
		cbDespesas.setBackground(new Color(192, 192, 192));
		cbDespesas.setBounds(290, 58, 110, 22);
		add(cbDespesas);
		
		campNome = new JTextField();
		campNome.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		campNome.setBounds(90, 271, 119, 20);
		add(campNome);
		campNome.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Nome : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_3.setBounds(21, 274, 59, 14);
		add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Valor : ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_4.setBounds(21, 315, 59, 14);
		add(lblNewLabel_4);
		
		campValor = new JNumberFormatField();
		campValor.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		campValor.setBounds(90, 312, 119, 20);
		add(campValor);
		campValor.setColumns(10);
		
		btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		btnNewButton.setIcon(new ImageIcon(PainelAlterarDespesa.class.getResource("/icons/diskette.png")));
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(0, 255, 255));
		btnNewButton.setBounds(219, 309, 46, 23);
		add(btnNewButton);

		
	}
}
