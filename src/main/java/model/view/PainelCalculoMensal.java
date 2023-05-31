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


public class PainelCalculoMensal extends JPanel {

	private JButton btnGerarCalculo;
	private JComponent btnNewButton;
	
	TabelaVO tabela = new TabelaVO();
	TabelaController tabelaController = new TabelaController();
	DespesaController despController = new DespesaController();
	
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
	private JLabel lblMesResultado;
	private JLabel lblSaldoRestante;
	private JLabel lblDescontoTotal;
	private JLabel lblNewLabel_6;
	private JLabel lblSaldoFinal;

	/**
	 * Create the panel.
	 */
	public PainelCalculoMensal(final UsuarioVO userLogado) {

		btnNewButton.setBackground(new Color(64, 128, 128));
		btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnNewButton.setBounds(0, 300, 33, 23);
		add(btnNewButton);
		
		btnGerarCalculo = new JButton("Gerar cálculo mensal");
		btnGerarCalculo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		btnGerarCalculo.setBackground(new Color(192, 192, 192));
		btnGerarCalculo.addActionListener(new ActionListener() {
			
			private JLabel lbl_mes;
			private JLabel lbl_saldo;
			private JLabel lbl_saldoFInal;

			public void actionPerformed(ActionEvent e) {
			
			    double saldo = Double.parseDouble(campSaldo.getText());
				double rest = userLogado.getSalariol() + saldo;
				
				double Desc = this.calcularDescontoTotal(userLogado);
				
				double saldoFinal = rest - Desc; 
				
				String saldoF = " " +saldoFinal;
				String saldoM = ""+saldo;
				
				//EXIBICAO FINAL
				lbl_mes = new JLabel("");
				lbl_mes.setBounds(116, 196, 84, 14);
				add(lbl_mes);
				lbl_mes.setText(mes.toUpperCase());
				
				lbl_saldo = new JLabel("");
				lbl_saldo.setBounds(259, 196, 90, 14);
				add(lbl_saldo);
				lbl_saldo.setText("R$ "+saldoM);
				
				lbl_saldoFInal = new JLabel("");
				lbl_saldoFInal.setBounds(154, 238, 118, 14);
				add(lbl_saldoFInal);
				lbl_saldoFInal.setText("R$ "+saldoFinal);
				
				
			    //INSERCAO NO OBJETO TABELA
				tabela.setAno(dataAtual.getYear());
				tabela.setIdUsuario(userLogado.getIdUsuario());
				tabela.setTotalRest(saldo);
			    tabela.setSaldoFinal(saldoFinal);
			    tabela.setMes(mes);
				
			}

			//Calculo de despesas total
			private double calcularDescontoTotal(UsuarioVO userLogado) {
				
				double total = 0;
				DespesaController despController = new DespesaController();
				total = despController.calcularDescontoTotalController(userLogado);
	
				return total;
			}
		});
		setLayout(null);
		btnGerarCalculo.setBounds(24, 135, 137, 21);
		add(btnGerarCalculo);
		
		btn_salvarDados = new JButton("");
		btn_salvarDados.setIcon(new ImageIcon("C:\\Users\\GETIN\\Desktop\\Gerenciador de salário(GS)\\GerenciadorSalario\\img\\saveIcon.png"));
		btn_salvarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabela = tabelaController.salvarDadosTabelaDAO(userLogado, tabela);
				
				if (tabela.getIdTabela() != 0) {
					JOptionPane.showMessageDialog(null, "Dados do mês: "+mes.toUpperCase() +
							"\nSalvos com sucesso!", "Gerenciamento-Mensal", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_salvarDados.setBackground(new Color(64, 128, 128));
		btn_salvarDados.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		btn_salvarDados.setBounds(601, 471, 53, 29);
		add(btn_salvarDados);
		
		lblNewLabel = new JLabel("Cálculo Mensal do mês de : ");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		lblNewLabel.setBounds(114, 11, 205, 46);
		add(lblNewLabel);
		
		lblMesAtual = new JLabel("");
		lblMesAtual.setBounds(329, 11, 191, 46);
		add(lblMesAtual);
		lblMesAtual.setText(mes.toUpperCase());
		
		lblNewLabel_1 = new JLabel("Informe o saldo restante : R$ ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_1.setBounds(30, 96, 181, 14);
		add(lblNewLabel_1);
		
		campSaldo = new JTextField();
		campSaldo.setBounds(196, 93, 147, 20);
		add(campSaldo);
		campSaldo.setColumns(10);
		
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
		lblNewLabel_4.setBounds(24, 386, 124, 14);
		add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Total descontado : R$ ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_5.setBounds(267, 267, 127, 14);
		add(lblNewLabel_5);
		
		lblMesResultado = new JLabel("New label");
		lblMesResultado.setBounds(103, 267, 137, 14);
		add(lblMesResultado);
		
		JLabel lblAno = new JLabel("New label");
		lblAno.setBounds(103, 322, 137, 14);
		add(lblAno);
		
		lblSaldoRestante = new JLabel("New label");
		lblSaldoRestante.setBounds(158, 386, 137, 14);
		add(lblSaldoRestante);
		
		lblDescontoTotal = new JLabel("New label");
		lblDescontoTotal.setBounds(404, 267, 105, 14);
		add(lblDescontoTotal);
		
		lblNewLabel_6 = new JLabel("Saldo final : ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_6.setBounds(267, 308, 106, 14);
		add(lblNewLabel_6);
		
		lblSaldoFinal = new JLabel("New label");
		lblSaldoFinal.setBounds(369, 308, 137, 14);
		add(lblSaldoFinal);
	
	}
}
