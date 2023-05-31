package model.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.view.*;
import model.vo.*;
import controller.DespesaController;


public class PainelAdicionarDespesa extends JPanel {
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;
	private JTextField nomeDespesa_camp;
	private JTextField valorDespesaCamp;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	
	DespesaController despController = new DespesaController();
	DespesaVO despesa = new DespesaVO();
	

	/**
	 * Create the panel.
	 */
	public PainelAdicionarDespesa(final UsuarioVO userLogado) {

		
		setLayout(null);
		
		lblNewLabel_1 = new JLabel(" Inserira os dados da despesa nova  ");
		lblNewLabel_1.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setBounds(115, 36, 253, 14);
		add(lblNewLabel_1);
		
		btnNewButton.setBackground(new Color(64, 128, 128));
		btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnNewButton.setBounds(0, 245, 37, 23);
		add(btnNewButton);
		
		nomeDespesa_camp = new JTextField();
		nomeDespesa_camp.setBounds(152, 100, 131, 20);
		add(nomeDespesa_camp);
		nomeDespesa_camp.setColumns(10);
		
		valorDespesaCamp = new JTextField();
		valorDespesaCamp.setBounds(152, 146, 131, 20);
		add(valorDespesaCamp);
		valorDespesaCamp.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Nome para a despesa : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblNewLabel_3.setBounds(20, 100, 121, 14);
		add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Valor R$ : ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblNewLabel_4.setBounds(58, 149, 106, 14);
		add(lblNewLabel_4);
		
		
		JButton btn_inserir_camp = new JButton(" Inserir ");
		btn_inserir_camp.setIcon(new ImageIcon("C:\\Users\\GETIN\\Desktop\\Gerenciador de salário(GS)\\GerenciadorSalario\\img\\inserirIcon.png"));
		btn_inserir_camp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//INSERE DADOS AO NOVO OBJETO
				LocalDate dataAtual = LocalDate.now();
			
				despesa.setIdUsuario(userLogado.getIdUsuario());
				despesa.setDespNome(nomeDespesa_camp.getText());
				despesa.setValor(Double.parseDouble(valorDespesaCamp.getText()));
				
				//INSERE DESPESA NOVA AO BANCO
			    despesa = despController.inserirDespesaController(despesa);
				
				if (despesa.getIdDespesa() != 0) {
					JOptionPane.showMessageDialog(null, "Despesa nova inserida com sucesso! ", "Gerenciamento-Mensal", JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Não foi possível inserir! ", "Gerenciamento-Mensal", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		btn_inserir_camp.setBackground(new Color(192, 192, 192));
		btn_inserir_camp.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btn_inserir_camp.setBounds(169, 236, 121, 23);
		add(btn_inserir_camp);
		
	}

}
