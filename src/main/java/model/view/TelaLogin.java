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
	 UsuarioVO user = new UsuarioVO();
	 UsuarioController userController = new UsuarioController();
	 UsuarioVO userVerificado = new UsuarioVO();
	 UsuarioVO userRecuperado = new UsuarioVO();
	 
	 UsuarioVO userLogado = new UsuarioVO();
	
	private JPanel contentPane;
	private JTextField login_camp;
	private JPasswordField campoSenha;
	private String cpfD;
	private String nomeD;

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
		setTitle("GS - Gerenciador de Salario");
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
		
		login_camp = new JTextField();
		login_camp.setBounds(181, 149, 118, 20);
		contentPane.add(login_camp);
		login_camp.setColumns(10);
		
		campoSenha = new JPasswordField();
		
		campoSenha.setBounds(181, 174, 62, 20);
		contentPane.add(campoSenha);
		
		JLabel lblNewLabel_1 = new JLabel("GS - Gerenciador de salário");
		lblNewLabel_1.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 10));
		lblNewLabel_1.setBounds(310, 318, 165, 14);
		contentPane.add(lblNewLabel_1);
	
		JButton btnNewButton = new JButton("Esqueceu o nome de usuário?");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\GETIN\\Desktop\\Gerenciador de salário(GS)\\GerenciadorSalario\\img\\EsqueceuIcon.png"));
		btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 9));
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				cpfD = JOptionPane.showInputDialog("Digite seu CPF cadastrado: ");
				nomeD = JOptionPane.showInputDialog("Digite seu nome completo: ");
				
				userVerificado.setCpf(cpfD);
				userVerificado.setNome(nomeD);
				
				//VERIFICACAO
				//userRecuperado = userDAO.verificarNomeUsuarioInterface(userVerificado);
				if (userVerificado == null) {
					JOptionPane.showMessageDialog(null, "Usuário não encontrado!", "GS - Gerenciador de Salário", JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Nome de usuário: " + userRecuperado.getNome() 
					+ "\nUsername: " + userRecuperado.getLogin(), " GS - Gerenciador de Salário ", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(0, 305, 186, 27);
		contentPane.add(btnNewButton);
		
		JButton btn_entrar = new JButton("Entrar");
		btn_entrar.setIcon(new ImageIcon("C:\\Users\\GETIN\\Desktop\\Gerenciador de salário(GS)\\GerenciadorSalario\\img\\entrarIcon.png"));
		btn_entrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//VERIFICACAO DE LOGIN
				userLogado.setLogin(login_camp.getText());
				userLogado.setSenha(campoSenha.getText());
				
				userLogado = userController.realizarLoginController(userLogado);
				
				JProgressBar barra_progresso = new JProgressBar();
				barra_progresso.setIndeterminate(true);
				barra_progresso.setBounds(165, 269, 146, 14);
				contentPane.add(barra_progresso);
					
				if (userLogado.getIdUsuario() != 0) {
					JOptionPane.showMessageDialog(null, "Login efetuado!", "GS - Gerenciador de salário", JOptionPane.INFORMATION_MESSAGE);
					MenuPrincipal tela = new MenuPrincipal(userLogado);
					//METODO PARA FECHAR TELA SECUNDARIA
					dispose();
					tela.setVisible(true);
				} else if(userLogado.getIdUsuario() == 0) {
					JOptionPane.showMessageDialog(null, "Usuário não encontrado!", "GS - Gerenciador de salário", JOptionPane.WARNING_MESSAGE);
				} else if (campoSenha.getText().length() != 4) {
					JOptionPane.showMessageDialog(null, "Senha incorreta!", "GS - Gerenciador de salário", JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Tente novamente", "GS - Gerenciador de salário", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btn_entrar.setBackground(new Color(0, 255, 0));
		btn_entrar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btn_entrar.setBounds(57, 221, 110, 23);
		contentPane.add(btn_entrar);
		
		JButton btn_criarConta = new JButton("Criar");
		btn_criarConta.setIcon(new ImageIcon("C:\\Users\\GETIN\\Desktop\\Gerenciador de salário(GS)\\GerenciadorSalario\\img\\criarIcon.png"));
		btn_criarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TelaCadastro cadastro = new TelaCadastro();
				dispose();
				//cadastro.setVisible(true);
			}
		});
		btn_criarConta.setBackground(new Color(0, 128, 192));
		btn_criarConta.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btn_criarConta.setBounds(181, 221, 102, 23);
		contentPane.add(btn_criarConta);
		
		JLabel lblNewLabel_3 = new JLabel("(Criar novo usuário )");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 7));
		lblNewLabel_3.setBounds(218, 244, 99, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton_1 =new JButton("Sair");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\GETIN\\Desktop\\Gerenciador de salário(GS)\\GerenciadorSalario\\img\\newExitIcon.jpg"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//FecharPrograma tela = new FecharPrograma(userLogado);
				//tela.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		btnNewButton_1.setBackground(new Color(255, 0, 0));
		btnNewButton_1.setBounds(293, 221, 110, 23);
		contentPane.add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(64, 128, 128));
		panel.setBounds(0, 0, 507, 104);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("- Entre com suas credenciais -");
		lblNewLabel.setBounds(108, 47, 319, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 18));
		
		
	}
}
