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
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;


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
	   private JNumberFormatField campSaldo;
	   DecimalFormat formato = new DecimalFormat("#,##0.00");
	   
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
		setBackground(new Color(0, 255, 255));

		setLayout(null);
		
		campSaldo = new JNumberFormatField();
		campSaldo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		campSaldo.setBounds(208, 93, 147, 20);
		add(campSaldo);
		campSaldo.setColumns(10);
		
		lblMesConsulta = new JLabel("");
		lblMesConsulta.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblMesConsulta.setBounds(60, 235, 91, 14);
		add(lblMesConsulta);
		
		lblAnoConsultado = new JLabel("");
		lblAnoConsultado.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblAnoConsultado.setBounds(60, 290, 85, 14);
		add(lblAnoConsultado);
		
		lblSaldoGuardado = new JLabel("");
		lblSaldoGuardado.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblSaldoGuardado.setBounds(114, 340, 85, 14);
		add(lblSaldoGuardado);
		
		lblSaldoRest = new JLabel("");
		lblSaldoRest.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblSaldoRest.setBounds(385, 235, 115, 14);
		add(lblSaldoRest);
		
		lblTotalDesc = new JLabel("");
		lblTotalDesc.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblTotalDesc.setBounds(407, 290, 115, 14);
		add(lblTotalDesc);
		
		lblSaldoFinal = new JLabel("");
		lblSaldoFinal.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblSaldoFinal.setBounds(361, 340, 91, 14);
		add(lblSaldoFinal);
		
		btnGerarCalculo = new JButton("");
		btnGerarCalculo.setIcon(new ImageIcon(PainelCalculoMensal.class.getResource("/icons/chart-histogram.png")));
		btnGerarCalculo.setBorder(null);
		btnGerarCalculo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		btnGerarCalculo.setBackground(new Color(0, 255, 255));
		btnGerarCalculo.addActionListener(new ActionListener() {
		
			    public void actionPerformed(ActionEvent e) {
			
			    String valor1 = campSaldo.getText().replace(",", ".");
			    
			    double saldo = Double.parseDouble(valor1.replace("R$", ""));
			    String saldoG = formato.format(saldo);
			    
				double rest = userLogado.getSalariol() + saldo;
				String restG = formato.format(rest);
				
				double desc  = despController.calcularDescontoTotalController(userLogado);
				String descG = formato.format(desc);
				
				double saldoFinal = rest - desc; 
				String valorG = formato.format(saldoFinal);
				
				// CONVERSOES DE VALORES
				String saldoF = String.valueOf(saldoG).replace(".", ",");
				String restF = String.valueOf(restG).replace(".", ",");
				String descF = String.valueOf(descG).replace(".", ",");
				String saldoFinalF = String.valueOf(valorG).replace(".", ",");
				
				//EXIBICAO FINAL
				lblMesConsulta.setText(mes);
				lblAnoConsultado.setText(""+dataAtual.getYear());
				lblSaldoGuardado.setText(saldoG);
				lblSaldoRest.setText(restG);
				lblTotalDesc.setText(descG);
				lblSaldoFinal.setText(valorG);
				
			    //INSERCAO NO OBJETO TABELA
				tabela.setAno(Integer.toString(dataAtual.getDayOfYear()));
				tabela.setIdUsuario(userLogado.getIdUsuario());
				tabela.setTotalRest(saldo);
			    tabela.setSaldoFinal(saldoFinal);
			    tabela.setMes(mes);
			}

		});
		setLayout(null);
		btnGerarCalculo.setBounds(372, 93, 46, 21);
		add(btnGerarCalculo);
		
		btn_salvarDados = new JButton("");
		btn_salvarDados.setBorder(null);
		btn_salvarDados.setIcon(new ImageIcon(PainelCalculoMensal.class.getResource("/icons/diskette.png")));
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
		btn_salvarDados.setBackground(new Color(0, 255, 255));
		btn_salvarDados.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		btn_salvarDados.setBounds(598, 419, 53, 29);
		add(btn_salvarDados);
		
		lblNewLabel = new JLabel("Cálculo Mensal do mês de : ");
		lblNewLabel.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(114, 11, 205, 46);
		add(lblNewLabel);
		
		lblMesAtual = new JLabel("");
		lblMesAtual.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 15));
		lblMesAtual.setBounds(307, 17, 64, 35);
		add(lblMesAtual);
		lblMesAtual.setText(mes.toUpperCase());
		
		lblNewLabel_1 = new JLabel("Informe o saldo restante : R$ ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_1.setBounds(30, 96, 181, 14);
		add(lblNewLabel_1);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 185, 677, 10);
		add(panel);
		
		lblNewLabel_2 = new JLabel("Mês : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_2.setBounds(24, 235, 53, 14);
		add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Ano : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_3.setBounds(24, 290, 46, 14);
		add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Saldo restante :  R$ ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_4.setBounds(267, 235, 115, 14);
		add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Total descontado :  R$ ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_5.setBounds(267, 290, 151, 14);
		add(lblNewLabel_5);
		
		
		lblNewLabel_6 = new JLabel("Saldo final :  R$ ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_6.setBounds(267, 340, 115, 14);
		add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("Guardado :  R$ ");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_7.setBounds(24, 340, 127, 14);
		add(lblNewLabel_7);
		
	
	}
}
