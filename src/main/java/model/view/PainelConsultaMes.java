package model.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.vo.*;
import controller.*;
import javax.swing.JProgressBar;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class PainelConsultaMes extends JPanel {
	private JComponent btnNewButton;
	private JLabel lblNewLabel_1;
	private JButton btnConsultar;
	private JLabel lblNewLabel_2;
	
	TabelaVO tabelaVO = new TabelaVO();
	TabelaController tabelaController = new TabelaController();
	DecimalFormat formato = new DecimalFormat("#,##0.00");
	
	private JTextField mesDigitadoCamp;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblMes;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_5;
	private JLabel lblAno;
	private JLabel lblSaldoFinal;
	private JLabel lblTotalRestante;

	/**
	 * Create the panel.
	 * @param userOnline 
	 */
	public PainelConsultaMes(final UsuarioVO userOnline) {
		setBackground(new Color(0, 255, 255));

        setLayout(null);
		
		lblNewLabel_1 = new JLabel("Consulta de mês regular");
		lblNewLabel_1.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1.setBounds(151, 11, 236, 30);
		add(lblNewLabel_1);
		
		btnConsultar = new JButton("");
		btnConsultar.setBorder(null);
		btnConsultar.setIcon(new ImageIcon(PainelConsultaMes.class.getResource("/icons/loupe.png")));
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		btnConsultar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String mesConsultado = mesDigitadoCamp.getText();
				
				tabelaVO.setMes(mesConsultado);
				tabelaVO.setIdUsuario(userOnline.getIdUsuario());
				
				//CONSULTANDO MES
				tabelaVO = tabelaController.consultarMesController(tabelaVO);
				//VERIFICACOES
			    if (tabelaVO.getIdTabela() == 0) {
			    	JOptionPane.showMessageDialog(null, "Dados do mês  " + mesConsultado 
			    			+"\n não encontrados! ", "GS - Gerenciador de salário", JOptionPane.ERROR_MESSAGE);
			    } else {
			   
			    	JOptionPane.showMessageDialog(null, "Consulta realizada com sucesso! " + mesConsultado 
			    			, "Gerenciamento-Mensal", JOptionPane.INFORMATION_MESSAGE);
			    	
			    	
					lblAno.setText(""+tabelaVO.getAno());
			    	
					lblSaldoFinal.setText("R$"+ formato.format(tabelaVO.getSaldoFinal()));
			    	
					lblTotalRestante.setText("R$" + formato.format(tabelaVO.getTotalRest()));
			    
			    }
				
			}
		});
		btnConsultar.setBackground(new Color(0, 255, 255));
		btnConsultar.setBounds(360, 73, 27, 23);
		add(btnConsultar);
		
		lblNewLabel_2 = new JLabel("Informe o mês a ser consultado : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblNewLabel_2.setBounds(58, 73, 222, 23);
		add(lblNewLabel_2);
	
		mesDigitadoCamp = new JTextField();
		mesDigitadoCamp.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		mesDigitadoCamp.setBounds(233, 76, 117, 20);
		add(mesDigitadoCamp);
		mesDigitadoCamp.setColumns(10);
		
		lblAno = new JLabel("");
		lblAno.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblAno.setBounds(81, 221, 103, 14);
		add(lblAno);
		
		lblTotalRestante = new JLabel("");
		lblTotalRestante.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblTotalRestante.setBounds(190, 264, 142, 14);
		add(lblTotalRestante);
		
		lblSaldoFinal = new JLabel("");
		lblSaldoFinal.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblSaldoFinal.setBounds(124, 309, 141, 14);
		add(lblSaldoFinal);
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 129, 680, 10);
		add(panel);
		
	    lblNewLabel = new JLabel("Mês consultado : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel.setBounds(81, 150, 117, 14);
		add(lblNewLabel);
		
		lblMes = new JLabel("New label");
		lblMes.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblMes.setBounds(190, 150, 175, 14);
		add(lblMes);
		lblMes.setText(mesDigitadoCamp.getText());
		
		lblNewLabel_3 = new JLabel("____________________________________________________");
		lblNewLabel_3.setBounds(43, 160, 418, 14);
		add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Ano : ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_4.setBounds(43, 221, 54, 14);
		add(lblNewLabel_4);
		
		lblNewLabel_6 = new JLabel("Total restante no mês :  ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_6.setBounds(43, 264, 142, 14);
		add(lblNewLabel_6);
		
		lblNewLabel_8 = new JLabel("Saldo final :  ");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_8.setBounds(43, 309, 114, 14);
		add(lblNewLabel_8);
		
		lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(PainelConsultaMes.class.getResource("/icons/bank.png")));
		lblNewLabel_5.setBounds(636, 458, 34, 30);
		add(lblNewLabel_5);
		
	
	}
}
