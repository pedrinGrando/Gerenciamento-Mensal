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
import model.vo.*;
import controller.*;

public class PainelVerConta extends JPanel {
	private DespesaController despController;
	private UsuarioController userController;
	private JLabel despesas_label;
	private JLabel salario_label;
	private JLabel email_label;
	private JLabel cpf_label;
	private JLabel nome_label;
	private JLabel lblNewLabel_4;
	private JLabel labelPrincipal_nome;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_1;
	
	EnderecoVO endereco = new EnderecoVO();
	EnderecoController endController = new EnderecoController();
	private JLabel lblDataNasci;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblLogin;
	private JLabel lblNewLabel_8;
	private JLabel lblEstado;
	private JLabel lblNewLabel_10;
	private JLabel lblCidade;
	private JLabel lblNewLabel_12;
	private JLabel lblRua;
	private JLabel lblNewLabel_14;
	private JLabel lblCEP;
	

	/**
	 * Create the panel.
	 */
	public PainelVerConta(final UsuarioVO userOnline) {

		setLayout(null);
		
		endereco.setIdUsuario(userOnline.getIdUsuario());
		
		endereco = endController.consultarEnderecoPorId(endereco);
		
		lblNewLabel_1 = new JLabel("Nome Completo :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_1.setBounds(10, 72, 119, 14);
		add(lblNewLabel_1);
		
		labelPrincipal_nome = new JLabel("Seus dados");
		labelPrincipal_nome.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 18));
		labelPrincipal_nome.setBounds(174, 11, 205, 25);
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
		
		nome_label = new JLabel("");
		nome_label.setFont(new Font("Tahoma", Font.ITALIC, 11));
		nome_label.setBounds(123, 72, 158, 14);
		add(nome_label);
		
		nome_label.setText(userOnline.getNome());
		
		cpf_label = new JLabel("");
		cpf_label.setFont(new Font("Tahoma", Font.ITALIC, 11));
		cpf_label.setBounds(55, 97, 163, 14);
		add(cpf_label);
		
		//CRIPTROGRAFA O CPF
		String cpfCrip = userOnline.getCpf().substring(0, 3);
		
		cpf_label.setText(cpfCrip + ".***.***-**");
		
		email_label = new JLabel("");
		email_label.setFont(new Font("Tahoma", Font.ITALIC, 11));
		email_label.setBounds(55, 122, 179, 14);
		add(email_label);
		
		email_label.setText(userOnline.getEmail());
		
		salario_label = new JLabel("");
		salario_label.setFont(new Font("Tahoma", Font.ITALIC, 11));
		salario_label.setBounds(104, 147, 163, 14);
		add(salario_label);
		
		salario_label.setText("R$ "+userOnline.getSalariol());
		
		lblDataNasci = new JLabel("");
		lblDataNasci.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblDataNasci.setBounds(148, 181, 124, 14);
		add(lblDataNasci);
		
		lblNewLabel_5 = new JLabel("Data de nascimento : ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_5.setBounds(10, 181, 148, 14);
		add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Login : ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_6.setBounds(10, 206, 46, 14);
		add(lblNewLabel_6);
		
		lblLogin = new JLabel("");
		lblLogin.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLogin.setBounds(76, 206, 158, 14);
		add(lblLogin);
		
		lblLogin.setText(userOnline.getLogin());
		
		lblNewLabel_8 = new JLabel("Estado : ");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_8.setBounds(295, 82, 65, 14);
		add(lblNewLabel_8);
		
		lblEstado = new JLabel("");
		lblEstado.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblEstado.setBounds(344, 82, 65, 14);
		add(lblEstado);
		
		lblEstado.setText(endereco.getEstado());
		
		lblNewLabel_10 = new JLabel("Cidade : ");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_10.setBounds(295, 122, 65, 14);
		add(lblNewLabel_10);
		
		
		lblCidade = new JLabel("");
		lblCidade.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblCidade.setBounds(354, 122, 145, 14);
		add(lblCidade);
		
		lblCidade.setText(endereco.getCidade());
		
		lblNewLabel_12 = new JLabel("Rua :");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_12.setBounds(295, 158, 65, 14);
		add(lblNewLabel_12);
		
		lblRua = new JLabel("");
		lblRua.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblRua.setBounds(337, 158, 234, 14);
		add(lblRua);
		
		lblRua.setText(endereco.getRua());
		
		lblNewLabel_14 = new JLabel("CEP : ");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_14.setBounds(295, 206, 65, 14);
		add(lblNewLabel_14);
		
		lblCEP = new JLabel("");
		lblCEP.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblCEP.setBounds(337, 206, 140, 14);
		add(lblCEP);
		
		lblCEP.setText(endereco.getCep());
		
	}
}


