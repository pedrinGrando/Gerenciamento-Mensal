package model.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import model.dao.*;
import model.vo.*;
import controller.*;

import javax.swing.JFormattedTextField;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JProgressBar;

public class TelaLogin extends JFrame {
	
	//USUARIO CRIADO/VARIAVEIS
	 UsuarioVO userVerificado = new UsuarioVO();
	 UsuarioVO userRecuperado = new UsuarioVO();
	 
	 UsuarioVO userOnline = new UsuarioVO();
	
	private JPanel contentPane;
	private JTextField loginField;
	private JPasswordField senhaField;
	private JButton btnNewButton;
	private JButton btn_entrar;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 // Define o ícone da aplicação
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setForeground(new Color(64, 128, 128));
		setFont(new Font("Source Serif Pro Semibold", Font.ITALIC, 12));
		setBackground(new Color(64, 128, 128));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\GETIN\\Desktop\\Gerenciador de salário(GS)\\GerenciadorSalario\\img\\moneyIconTeste.png"));
		setTitle("Gerenciamento-Mensal | Login");
		setResizable(false);
		setBounds(100, 100, 479, 372);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("Usuário :");
		label.setFont(new Font("Dialog", Font.ITALIC, 10));
		label.setBounds(113, 149, 62, 12);
		contentPane.add(label);
		
		Label label_1 = new Label("Senha :");
		label_1.setFont(new Font("Dialog", Font.ITALIC, 10));
		label_1.setBounds(113, 167, 62, 27);
		contentPane.add(label_1);
		
		loginField = new JTextField();
		loginField.setBounds(181, 149, 118, 20);
		contentPane.add(loginField);
		loginField.setColumns(10);
		
		senhaField = new JPasswordField();
		
		senhaField.setBounds(181, 174, 62, 20);
		contentPane.add(senhaField);
	
		btnNewButton = new JButton("Esqueceu o nome de usuário?");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\GETIN\\Desktop\\Gerenciador de salário(GS)\\GerenciadorSalario\\img\\EsqueceuIcon.png"));
		btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 9));
		btnNewButton.setBackground(new Color(192, 192, 192));			
			
		btnNewButton.setBounds(0, 305, 186, 27);
		contentPane.add(btnNewButton);
		
		btn_entrar = new JButton("Entrar");
		btn_entrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//REALIZACAO DO LOGIN 
				UsuarioController userController = new UsuarioController();
				userOnline.setLogin(loginField.getText());
				userOnline.setSenha(senhaField.getText());
			    userOnline = userController.realizarLoginController(userOnline);
				
				if (userOnline.getIdUsuario() != 0) {
					
					JOptionPane.showMessageDialog(null, "Login efetuado com sucesso! " + userOnline.getLogin());
					
					//METODO PARA TROCA DE FRAME
					dispose();
					MenuPrincipal menu = new MenuPrincipal(userOnline);
					menu.setVisible(true);
					
				} else if (userOnline.getIdUsuario() == 0) {
					JOptionPane.showMessageDialog(null, "Usuário não encontrado!");
				} else if (senhaField.getText().length() > 4 | senhaField.getText().length() < 4) {
					JOptionPane.showMessageDialog(null, "Senha inválida! ");
				}
			
			
		   }
		
	    });
		btn_entrar.setIcon(new ImageIcon("C:\\Users\\GETIN\\Desktop\\Gerenciador de salário(GS)\\GerenciadorSalario\\img\\entrarIcon.png"));

		btn_entrar.setBackground(new Color(0, 255, 0));
		btn_entrar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btn_entrar.setBounds(105, 221, 110, 23);
		contentPane.add(btn_entrar);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(64, 128, 128));
		panel.setBounds(0, 0, 507, 104);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("- Entre com suas credenciais -");
		lblNewLabel.setBounds(108, 47, 319, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 18));
		
		JButton btnNewButton_1 = new JButton("Cadastro");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Chama a tela de cadastro
		        dispose();
				TelaCadastro tela;
				try {
					tela = new TelaCadastro();
					tela.setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnNewButton_1.setBounds(228, 221, 110, 23);
		contentPane.add(btnNewButton_1);
		
		
	}
}
