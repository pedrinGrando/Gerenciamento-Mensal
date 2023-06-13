package model.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.vo.*;
import controller.*;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;


public class PainelConsultarDespesa extends JPanel {
	private JComponent btnNewButton_1;
	
	DespesaVO despesaVO = new DespesaVO();
	DespesaController despController = new DespesaController();
	private JTextField campDespDigitada;
	private JLabel lblNewLabel;
	DecimalFormat formato = new DecimalFormat("#,##0.00");

	private JLabel lblNewLabel_6;

	private JLabel lblNewLabel_1;

	private JLabel lblNome;

	private JLabel lblNewLabel_3;

	private JLabel lblDespesa;

	private JLabel lblNewLabel_5;

	private JLabel lblValor;

	private JButton btnConsultar;

	

	/**
	 * Create the panel.
	 * @param userOnline 
	 */
	public PainelConsultarDespesa(final UsuarioVO userOnline) {
		setBackground(new Color(0, 255, 255));
		setLayout(null);
		
		lblNewLabel_6 = new JLabel(" Consultar de despesa ");
		lblNewLabel_6.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_6.setBounds(168, 24, 183, 23);
		add(lblNewLabel_6);
		
		
		campDespDigitada = new JTextField();
		campDespDigitada.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		campDespDigitada.setBounds(135, 88, 296, 20);
		add(campDespDigitada);
		campDespDigitada.setColumns(10);
		
		lblNewLabel = new JLabel("Nome da despesa :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel.setBounds(20, 91, 115, 14);
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 145, 700, 14);
		add(panel);
		
		lblNewLabel_1 = new JLabel("Nome : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_1.setBounds(20, 203, 71, 14);
		add(lblNewLabel_1);
		
		lblNome = new JLabel("");
		lblNome.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNome.setBounds(73, 203, 209, 14);
		add(lblNome);
		
		lblNewLabel_3 = new JLabel("Despesa : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_3.setBounds(20, 247, 71, 14);
		add(lblNewLabel_3);
		
		lblDespesa = new JLabel("");
		lblDespesa.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblDespesa.setBounds(87, 247, 250, 14);
		add(lblDespesa);
		
		lblNewLabel_5 = new JLabel("Valor : ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_5.setBounds(20, 288, 71, 14);
		add(lblNewLabel_5);
		
		lblValor = new JLabel("");
		lblValor.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblValor.setBounds(73, 288, 115, 14);
		add(lblValor);
		
		btnConsultar = new JButton("");
		btnConsultar.setBorder(null);
		btnConsultar.setBackground(new Color(0, 255, 255));
		btnConsultar.setIcon(new ImageIcon(PainelConsultarDespesa.class.getResource("/icons/loupe.png")));
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (campDespDigitada.getText().isBlank() || campDespDigitada.getText().isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "O nome é obrigratório", "Gerenciamento-Mensal", JOptionPane.WARNING_MESSAGE);
					
				} else {
					despesaVO = despController.consultarDespesaController(campDespDigitada.getText(), userOnline);
					
					lblNome.setText(userOnline.getNome());
					
					lblDespesa.setText(despesaVO.getDespNome());
					
					lblValor.setText("R$ "+ formato.format(despesaVO.getValor()));
					
				}
				
			}
		});
		btnConsultar.setBounds(429, 85, 39, 23);
		add(btnConsultar);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(PainelConsultarDespesa.class.getResource("/icons/bank.png")));
		lblNewLabel_2.setBounds(620, 452, 32, 32);
		add(lblNewLabel_2);
		
	}
}
