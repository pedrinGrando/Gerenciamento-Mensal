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
	private JLabel lblTitulo;
	
	TabelaVO tabelaVO = new TabelaVO();
	TabelaController tabelaController = new TabelaController();
	DecimalFormat formato = new DecimalFormat("#,##0.00");
	
	private JTextField mesDigitadoCamp;
	private JPanel panel;
	private JLabel lblSubTitulo;
	private JLabel lblMes;
	private JLabel lblNewLabel_3;
	private JLabel visaoAno;
	private JLabel visaoTotalRestante;
	private JLabel visaoSaldoFinal;
	private JLabel lblIcon;
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
		btnConsultar.setBounds(353, 73, 34, 23);
		add(btnConsultar);
		
		lblTitulo = new JLabel("Informe o mês a ser consultado : ");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblTitulo.setBounds(58, 73, 222, 23);
		add(lblTitulo);
	
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
		
	    lblSubTitulo = new JLabel("Mês consultado : ");
		lblSubTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblSubTitulo.setBounds(58, 150, 117, 14);
		add(lblSubTitulo);
		
		lblMes = new JLabel("New label");
		lblMes.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblMes.setBounds(175, 150, 175, 14);
		add(lblMes);
		lblMes.setText(mesDigitadoCamp.getText());
		
		lblNewLabel_3 = new JLabel("____________________________________________________");
		lblNewLabel_3.setBounds(43, 160, 418, 14);
		add(lblNewLabel_3);
		
		visaoAno = new JLabel("Ano : ");
		visaoAno.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		visaoAno.setBounds(43, 221, 54, 14);
		add(visaoAno);
		
		visaoTotalRestante = new JLabel("Total restante no mês :  ");
		visaoTotalRestante.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		visaoTotalRestante.setBounds(43, 264, 142, 14);
		add(visaoTotalRestante);
		
		visaoSaldoFinal = new JLabel("Saldo final :  ");
		visaoSaldoFinal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		visaoSaldoFinal.setBounds(43, 309, 114, 14);
		add(visaoSaldoFinal);
		
		lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(PainelConsultaMes.class.getResource("/icons/bank.png")));
		lblIcon.setBounds(636, 458, 34, 30);
		add(lblIcon);
		
	
	}
}
