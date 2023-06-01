package model.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


public class PainelConsultarDespesa extends JPanel {
	private JComponent btnNewButton_1;
	
	DespesaVO despesaVO = new DespesaVO();
	DespesaController despController = new DespesaController();
	private JTextField campDespDigitada;
	private JLabel lblNewLabel;

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
		setLayout(null);
		
		lblNewLabel_6 = new JLabel(" Consultar de despesa ");
		lblNewLabel_6.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_6.setBounds(168, 24, 183, 23);
		add(lblNewLabel_6);
		
		
		campDespDigitada = new JTextField();
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
		lblNome.setBounds(101, 203, 209, 14);
		add(lblNome);
		
		lblNewLabel_3 = new JLabel("Despesa : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_3.setBounds(20, 247, 71, 14);
		add(lblNewLabel_3);
		
		lblDespesa = new JLabel("");
		lblDespesa.setBounds(101, 247, 250, 14);
		add(lblDespesa);
		
		lblNewLabel_5 = new JLabel("Valor : ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_5.setBounds(20, 288, 71, 14);
		add(lblNewLabel_5);
		
		lblValor = new JLabel("");
		lblValor.setBounds(101, 288, 227, 14);
		add(lblValor);
		
		btnConsultar = new JButton("");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				despesaVO = despController.consultarDespesaController(campDespDigitada.getText(), userOnline);
				
				lblNome = new JLabel("New label");
				lblNome.setBounds(101, 203, 209, 14);
				add(lblNome);
				lblNome.setText(userOnline.getNome());
				
				lblDespesa = new JLabel("New label");
				lblDespesa.setBounds(101, 247, 250, 14);
				add(lblDespesa);
				lblDespesa.setText(despesaVO.getDespNome());
				
				lblValor = new JLabel("New label");
				lblValor.setBounds(101, 288, 227, 14);
				add(lblValor);
				lblValor.setText("R$ "+despesaVO.getValor());
				
			}
		});
		btnConsultar.setBounds(441, 87, 39, 23);
		add(btnConsultar);
		
	}
}
