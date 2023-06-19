package model.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import exceptions.CampoInvalidoException;

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

	private JLabel lblErro;

	private JLabel lblIcon;

	

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
		campDespDigitada.setBorder(null);
		campDespDigitada.setBounds(135, 88, 173, 20);
		campDespDigitada.setColumns(10);
		
		// Adiciona um ouvinte de eventos de teclado ao campo
		campDespDigitada.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                
                // Verifica se o caractere é um número
                if (Character.isDigit(c)) {
                    e.consume(); // Impede que o caractere seja inserido
                }
            }
        });
		add(campDespDigitada);
		
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
		
		lblErro = new JLabel("");
		lblErro.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblErro.setForeground(new Color(255, 0, 0));
		lblErro.setBounds(251, 111, 121, 23);
		add(lblErro);
		
		lblValor = new JLabel("");
		lblValor.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblValor.setBounds(73, 288, 115, 14);
		add(lblValor);
		
		btnConsultar = new JButton("");
		btnConsultar.setBorder(null);
		btnConsultar.setBackground(new Color(255, 255, 255));
		btnConsultar.setIcon(new ImageIcon(PainelConsultarDespesa.class.getResource("/icons/loupe.png")));
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String despNome = campDespDigitada.getText();
				lblErro.setText("");
				
				try {
					despesaVO = despController.consultarDespesaController(campDespDigitada.getText(), userOnline);
					
					if (despesaVO.getIdDespesa() == 0) {
						
						JOptionPane.showMessageDialog(null, "Despesa " + despNome+ " \nnão encontrada!", "Gerenciamento-Mensal", JOptionPane.ERROR_MESSAGE);
						
					} else {
					
                    lblNome.setText(userOnline.getNome());
					
					lblDespesa.setText(despesaVO.getDespNome());
					
					lblValor.setText("R$ "+ formato.format(despesaVO.getValor()));
					}
                  
				} catch (CampoInvalidoException e1) {
					
					lblErro.setText("O nome é obrigatório!");
				}
				
			}
		});
		btnConsultar.setBounds(303, 88, 38, 20);
		add(btnConsultar);
		
		lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(PainelConsultarDespesa.class.getResource("/icons/bank.png")));
		lblIcon.setBounds(620, 452, 32, 32);
		add(lblIcon);
		
	}
}
