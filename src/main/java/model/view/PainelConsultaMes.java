package model.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class PainelConsultaMes extends JPanel {
	private JComponent btnNewButton;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_2;
	
	TabelaVO tabelaVO = new TabelaVO();
	TabelaController tabelaController = new TabelaController();
	private JTextField mesDigitadoCamp;

	/**
	 * Create the panel.
	 * @param userOnline 
	 */
	public PainelConsultaMes(final UsuarioVO userOnline) {

        setLayout(null);
		
		lblNewLabel_1 = new JLabel("Consulta de mês regular");
		lblNewLabel_1.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1.setBounds(151, 11, 236, 30);
		add(lblNewLabel_1);
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mesConsultado = mesDigitadoCamp.getText();
				
				tabelaVO.setMes(mesConsultado);
				tabelaVO.setIdUsuario(userOnline.getIdUsuario());
				
				//CONSULTANDO MES
				tabelaVO = tabelaController.consultarMesController(tabelaVO);
				//VERIFICACOES
			    if (tabelaVO.getIdTabela() == 0) {
			    	JOptionPane.showMessageDialog(null, "Dados do mês  " + mesConsultado 
			    			+"\n não encontrados! ", "GS - Gerenciador de salário", JOptionPane.WARNING_MESSAGE);
			    } else {
			   
			    	JOptionPane.showMessageDialog(null, "Consulta realizada com sucesso! " + mesConsultado 
			    			, "Gerenciamento-Mensal", JOptionPane.INFORMATION_MESSAGE);
			    	
			    	JLabel lblAno = new JLabel("New label");
					lblAno.setBounds(111, 221, 46, 14);
					add(lblAno);
					lblAno.setText(""+tabelaVO.getAno());
			    	
					JLabel lblSaldoFinal = new JLabel("New label");
					lblSaldoFinal.setBounds(184, 363, 102, 14);
					add(lblSaldoFinal);
					lblSaldoFinal.setText(""+tabelaVO.getSaldoFinal());
			    	
			    	JLabel lblTotalRest = new JLabel("New label");
					lblTotalRest.setBounds(211, 288, 113, 14);
					add(lblTotalRest);
					lblTotalRest.setText(""+tabelaVO.getTotalRest());
			    
			    }
				
			}
		});
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.setBounds(309, 73, 27, 23);
		add(btnNewButton_1);
		
		lblNewLabel_2 = new JLabel("Informe o mês a ser consultado : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblNewLabel_2.setBounds(8, 73, 222, 23);
		add(lblNewLabel_2);
	
		mesDigitadoCamp = new JTextField();
		mesDigitadoCamp.setBounds(182, 76, 117, 20);
		add(mesDigitadoCamp);
		mesDigitadoCamp.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 129, 574, 10);
		add(panel);
		
		JLabel lblNewLabel = new JLabel("Mês consultado : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel.setBounds(81, 150, 117, 14);
		add(lblNewLabel);
		
		JLabel lblMes = new JLabel("New label");
		lblMes.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblMes.setBounds(190, 150, 175, 14);
		add(lblMes);
		lblMes.setText(tabelaVO.getMes());
		
		JLabel lblNewLabel_3 = new JLabel("___________________________________________________________");
		lblNewLabel_3.setBounds(43, 160, 388, 14);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Ano : ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_4.setBounds(52, 221, 78, 14);
		add(lblNewLabel_4);
		
		JLabel lblAno = new JLabel("New label");
		lblAno.setBounds(111, 221, 46, 14);
		add(lblAno);
		
		JLabel lblNewLabel_6 = new JLabel("Total restante no mês :  R$ ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_6.setBounds(43, 288, 155, 14);
		add(lblNewLabel_6);
		
		JLabel lblTotalRest = new JLabel("New label");
		lblTotalRest.setBounds(211, 288, 113, 14);
		add(lblTotalRest);
		
		JLabel lblNewLabel_8 = new JLabel("Saldo final :  R$ ");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_8.setBounds(43, 363, 114, 14);
		add(lblNewLabel_8);
		
		JLabel lblSaldoFinal = new JLabel("New label");
		lblSaldoFinal.setBounds(184, 363, 102, 14);
		add(lblSaldoFinal);
		
		
	}
}
