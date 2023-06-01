package model.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DespesaController;
import controller.UsuarioController;
import model.dao.DespesaDAO;
import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;


public class PainelVerConta extends JPanel {
	private DespesaController despController;
	private UsuarioController userController;
	private JButton btnNewButton_2;
	private JLabel despesas_label;
	private JLabel salario_label;
	private JLabel email_label;
	private JLabel cpf_label;
	private JLabel nome_label;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_4;
	private JLabel labelPrincipal_nome;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_1;
	private double desc = 0;

	/**
	 * Create the panel.
	 */
	public PainelVerConta(final UsuarioVO userOnline) {

		setLayout(null);
		
		lblNewLabel_1 = new JLabel("Nome Completo :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_1.setBounds(10, 72, 119, 14);
		add(lblNewLabel_1);
		
		labelPrincipal_nome = new JLabel("Seus dados");
		labelPrincipal_nome.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 18));
		labelPrincipal_nome.setBounds(236, 11, 205, 25);
		add(labelPrincipal_nome);
		
		lblNewLabel_2 = new JLabel("CPF :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_2.setBounds(10, 97, 46, 14);
		add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Email :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_3.setBounds(10, 122, 72, 14);
		add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Salário Bruto : ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_4.setBounds(10, 147, 96, 14);
		add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Despesas Totais : ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_5.setBounds(10, 172, 127, 14);
		add(lblNewLabel_5);
		
		nome_label = new JLabel("");
		nome_label.setFont(new Font("Tahoma", Font.ITALIC, 11));
		nome_label.setBounds(123, 72, 340, 14);
		add(nome_label);
		
		nome_label.setText(userOnline.getNome());
		
		cpf_label = new JLabel("");
		cpf_label.setFont(new Font("Tahoma", Font.ITALIC, 11));
		cpf_label.setBounds(55, 97, 163, 14);
		add(cpf_label);
		
		cpf_label.setText(userOnline.getCpf());
		
		email_label = new JLabel("");
		email_label.setFont(new Font("Tahoma", Font.ITALIC, 11));
		email_label.setBounds(55, 122, 179, 14);
		add(email_label);
		
		email_label.setText(userOnline.getEmail());
		
		salario_label = new JLabel("");
		salario_label.setFont(new Font("Tahoma", Font.ITALIC, 11));
		salario_label.setBounds(104, 147, 383, 14);
		add(salario_label);
		
		salario_label.setText("R$ "+userOnline.getSalariol());
		
		despesas_label = new JLabel("");
		despesas_label.setFont(new Font("Tahoma", Font.ITALIC, 11));
		despesas_label.setBounds(123, 172, 416, 14);
		add(despesas_label);
		
		desc = despController.calcularDescontoTotalController(userOnline);
		
		despesas_label.setText(""+desc);
		
	    btnNewButton_2 = new JButton("Excluir conta");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean retorn = false;
				String resp = JOptionPane.showInputDialog("Tem certeza que deseja excluir seu usuário?\n"
						+userOnline.getNome() + " Digite:"
								+ "\n1-Confirmar"
								+ "\n2-Cancelar");
				if (resp.equals("1")) {
	
				//retorn = user.excluirContaUsuarioInterface(userLogado);
				JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!", "GS - Gerenciador de salário", JOptionPane.INFORMATION_MESSAGE);
				} else if (resp.equals("2")) {
				 
				}
			}
		});
		btnNewButton_2.setBackground(new Color(192, 192, 192));
		btnNewButton_2.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\GETIN\\Desktop\\Gerenciador de salário(GS)\\GerenciadorSalario\\img\\excluir.png"));
		btnNewButton_2.setBounds(236, 303, 142, 23);
		add(btnNewButton_2);
		
	}
		
}


