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
	private JLabel visaoNome;
	DecimalFormat formato = new DecimalFormat("#,##0.00");

	private JLabel lblTitulo;

	private JLabel lblNomeConsult;

	private JLabel lblNome;

	private JLabel lblDespConsult;

	private JLabel lblDespesa;

	private JLabel lblValorConsult;

	private JLabel lblValor;

	private JButton btnConsultar;

	

	/**
	 * Create the panel.
	 * @param userOnline 
	 */
	public PainelConsultarDespesa(final UsuarioVO userOnline) {
		setBackground(new Color(0, 255, 255));
		setLayout(null);
		
		lblTitulo = new JLabel(" Consultar de despesa ");
		lblTitulo.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 16));
		lblTitulo.setBounds(168, 24, 183, 23);
		add(lblTitulo);
		
		
		campDespDigitada = new JTextField();
		campDespDigitada.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		campDespDigitada.setBounds(135, 88, 296, 20);
		add(campDespDigitada);
		campDespDigitada.setColumns(10);
		
		visaoNome = new JLabel("Nome da despesa :");
		visaoNome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		visaoNome.setBounds(20, 91, 115, 14);
		add(visaoNome);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 145, 700, 14);
		add(panel);
		
		lblNomeConsult = new JLabel("Nome : ");
		lblNomeConsult.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNomeConsult.setBounds(20, 203, 71, 14);
		add(lblNomeConsult);
		
		lblNome = new JLabel("");
		lblNome.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNome.setBounds(73, 203, 209, 14);
		add(lblNome);
		
		lblDespConsult = new JLabel("Despesa : ");
		lblDespConsult.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblDespConsult.setBounds(20, 247, 71, 14);
		add(lblDespConsult);
		
		lblDespesa = new JLabel("");
		lblDespesa.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblDespesa.setBounds(87, 247, 250, 14);
		add(lblDespesa);
		
		lblValorConsult = new JLabel("Valor : ");
		lblValorConsult.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblValorConsult.setBounds(20, 288, 71, 14);
		add(lblValorConsult);
		
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
