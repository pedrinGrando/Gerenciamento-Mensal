package model.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

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

import model.vo.*;
import controller.*;
import javax.swing.JTextField;
import java.text.DecimalFormat;


public class PainelCalculoMensal extends JPanel {

	private JButton btnGerarCalculo;
	private JComponent btnNewButton;
	
	TabelaVO tabela = new TabelaVO();
	TabelaController tabelaController = new TabelaController();
	DespesaController despController = new DespesaController();
	DecimalFormat df = new DecimalFormat("0.000");
	
	
	// CONVERSAO DE MES 
	   LocalDate dataAtual = LocalDate.now(); //MES ATUAL
	   Locale brasil = new Locale("pt", "BR");
	   DateTimeFormatter fout1 = DateTimeFormatter.ofPattern("EEEE", brasil);
	   String mes = (dataAtual.getMonth().getDisplayName(TextStyle.FULL, brasil));
	   private JTextField campSaldo;
	   
	private JButton btn_salvarDados;
	private JComponent lblNewLabel;
	private JLabel lblMesAtual;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JComponent lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblMesConsulta;
	private JLabel lblAnoConsultado;
	private JLabel lblSaldoRest;
	private JLabel lblTotalDesc;
	private JLabel lblSaldoFinal;
	private JLabel lblSaldoGuardado;

	/**
	 * Create the panel.
	 */
	public PainelCalculoMensal(final UsuarioVO userLogado) {

		setLayout(null);
		
		campSaldo = new JTextField();
		campSaldo.setBounds(196, 93, 147, 20);
		add(campSaldo);
		campSaldo.setColumns(10);
		
		lblMesConsulta = new JLabel("");
		lblMesConsulta.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblMesConsulta.setBounds(71, 267, 106, 14);
		add(lblMesConsulta);
		
		lblAnoConsultado = new JLabel("");
		lblAnoConsultado.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblAnoConsultado.setBounds(80, 322, 97, 14);
		add(lblAnoConsultado);
		
		lblSaldoGuardado = new JLabel("");
		lblSaldoGuardado.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblSaldoGuardado.setBounds(126, 396, 85, 14);
		add(lblSaldoGuardado);
		
		lblSaldoRest = new JLabel("");
		lblSaldoRest.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblSaldoRest.setBounds(401, 267, 115, 14);
		add(lblSaldoRest);
		
		lblTotalDesc = new JLabel("");
		lblTotalDesc.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblTotalDesc.setBounds(421, 322, 115, 14);
		add(lblTotalDesc);
		
		lblSaldoFinal = new JLabel("");
		lblSaldoFinal.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblSaldoFinal.setBounds(369, 386, 126, 14);
		add(lblSaldoFinal);
		
		btnGerarCalculo = new JButton("Gerar cálculo mensal");
		btnGerarCalculo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		btnGerarCalculo.setBackground(new Color(192, 192, 192));
		btnGerarCalculo.addActionListener(new ActionListener() {
		

			public void actionPerformed(ActionEvent e) {
			
			    double saldo = Double.parseDouble(campSaldo.getText());
				double rest = userLogado.getSalariol() + saldo;
				
				double desc  = despController.calcularDescontoTotalController(userLogado);
				
				double saldoFinal = rest - desc; 
				
				
				//EXIBICAO FINAL
				lblMesConsulta.setText(mes);
				lblAnoConsultado.setText(""+dataAtual.getYear());
				lblSaldoGuardado.setText(""+saldo);
				lblSaldoRest.setText(""+df.format(rest));
				lblTotalDesc.setText(""+df.format(desc));
				lblSaldoFinal.setText(""+ df.format(saldoFinal));
				
			    //INSERCAO NO OBJETO TABELA
				tabela.setAno(dataAtual.getYear());
				tabela.setIdUsuario(userLogado.getIdUsuario());
				tabela.setTotalRest(saldo);
			    tabela.setSaldoFinal(saldoFinal);
			    tabela.setMes(mes);

			}

		});
		setLayout(null);
		btnGerarCalculo.setBounds(24, 135, 147, 21);
		add(btnGerarCalculo);
		
		btn_salvarDados = new JButton("");
		btn_salvarDados.setIcon(new ImageIcon("C:\\Users\\GETIN\\Desktop\\Gerenciador de salário(GS)\\GerenciadorSalario\\img\\saveIcon.png"));
		btn_salvarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String resp = JOptionPane.showInputDialog("Deseja salvar os dados do mês de junho? \n"
						+ "Confirme os dados de " +mes +" abaixo! \n"
								+ "Saldo restante : R$ " +df.format(tabela.getTotalRest()) +"\n"
										+ "Saldo final : R$ " + df.format(tabela.getSaldoFinal()));
				if (resp.equalsIgnoreCase("sim")) {
					tabela = tabelaController.salvarDadosTabelaController(userLogado, tabela);
					JOptionPane.showMessageDialog(null, "Dados do mês: "+mes.toUpperCase() +
							"\nSalvos com sucesso!", "Gerenciamento-Mensal", JOptionPane.INFORMATION_MESSAGE);
				} else if (resp.equalsIgnoreCase("Não")) {
					JOptionPane.showMessageDialog(null, "Dados do mês: "+mes.toUpperCase() +
							"\nNão serão salvos!", "Gerenciamento-Mensal", JOptionPane.INFORMATION_MESSAGE);
					
				} else {
					JOptionPane.showMessageDialog(null, " Opção digitada inválida!",
							 "Gerenciamento-Mensal", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btn_salvarDados.setBackground(new Color(192, 192, 192));
		btn_salvarDados.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		btn_salvarDados.setBounds(600, 484, 53, 29);
		add(btn_salvarDados);
		
		lblNewLabel = new JLabel("Cálculo Mensal do mês de : ");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		lblNewLabel.setBounds(114, 11, 205, 46);
		add(lblNewLabel);
		
		lblMesAtual = new JLabel("");
		lblMesAtual.setBounds(304, 13, 191, 46);
		add(lblMesAtual);
		lblMesAtual.setText(mes.toUpperCase());
		
		lblNewLabel_1 = new JLabel("Informe o saldo restante : R$ ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_1.setBounds(30, 96, 181, 14);
		add(lblNewLabel_1);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 202, 677, 10);
		add(panel);
		
		lblNewLabel_2 = new JLabel("Mês : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_2.setBounds(24, 267, 53, 14);
		add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Ano : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_3.setBounds(24, 322, 46, 14);
		add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Saldo restante : R$ ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_4.setBounds(267, 267, 124, 14);
		add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Total descontado : R$ ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_5.setBounds(267, 322, 127, 14);
		add(lblNewLabel_5);
		
		
		lblNewLabel_6 = new JLabel("Saldo final : ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_6.setBounds(267, 386, 106, 14);
		add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("Guardado : R$ ");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_7.setBounds(24, 396, 127, 14);
		add(lblNewLabel_7);
		
	
	}
}
