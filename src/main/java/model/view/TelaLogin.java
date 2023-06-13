package model.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import model.dao.*;
import model.vo.*;
import controller.*;
import exceptions.CampoInvalidoException;

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
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JProgressBar;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class TelaLogin extends JFrame {
	
	//USUARIO CRIADO/VARIAVEIS
	 UsuarioVO userVerificado = new UsuarioVO();
	 UsuarioVO userRecuperado = new UsuarioVO();
	 
	 UsuarioVO userOnline = new UsuarioVO();
	
	private JPanel contentPane;
	private JTextField loginField;
	private JPasswordField senhaField;
	private JButton btn_entrar;
	private JLabel lblNewLabel;
	private Label label;
	private Label label_1;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_2;
	private JPanel panel;
	private JButton btnRecuperacaoSenha;

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
		setBackground(new Color(255, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaLogin.class.getResource("/icons/bank (1).png")));
		setTitle("Gerenciamento-Mensal | Login");
		setResizable(false);
		setBounds(100, 100, 479, 372);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new Label("Usuário :");
		label.setFont(new Font("Dialog", Font.ITALIC, 10));
		label.setBounds(113, 149, 62, 12);
		contentPane.add(label);
		
        label_1 = new Label("Senha :");
		label_1.setFont(new Font("Dialog", Font.ITALIC, 10));
		label_1.setBounds(113, 167, 62, 27);
		contentPane.add(label_1);
		
		loginField = new JTextField();
		loginField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		loginField.setBounds(181, 149, 118, 20);
		contentPane.add(loginField);
		loginField.setColumns(10);
		
		senhaField = new JPasswordField();
		senhaField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		senhaField.setBounds(181, 174, 118, 20);
		contentPane.add(senhaField);
		
		btn_entrar = new JButton("Entrar");
		btn_entrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//REALIZACAO DO LOGIN 
				UsuarioController userController = new UsuarioController();
				userOnline.setLogin(loginField.getText());
				userOnline.setSenha(senhaField.getText());
			    try {
					userOnline = userController.realizarLoginController(userOnline);
				} catch (CampoInvalidoException e1) {
					
	                JOptionPane.showMessageDialog(null, e1.getMessage(), "Gerenciamento-Mensal", JOptionPane.ERROR_MESSAGE);
				}
				
				if (userOnline.getIdUsuario() != 0) {
					
					JOptionPane.showMessageDialog(null, "Login efetuado com sucesso "+userOnline.getLogin()+ "!", "Gerenciamento-Mensal", JOptionPane.INFORMATION_MESSAGE);
					
					//METODO PARA TROCA DE FRAME
					dispose();
					MenuPrincipal menu = new MenuPrincipal(userOnline);
					menu.setVisible(true);
					
				} else if (userOnline.getIdUsuario() == 0) {
					JOptionPane.showMessageDialog(null, "Usuário não encontrado!", "Gerenciamento-Mensal", JOptionPane.WARNING_MESSAGE);
				} 
		   }
		
	    });
		btn_entrar.setIcon(new ImageIcon(TelaLogin.class.getResource("/icons/login.png")));
		
		btn_entrar.addKeyListener(new KeyListener(){
			
			@Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                  
                	//REALIZACAO DO LOGIN 
    				UsuarioController userController = new UsuarioController();
    				userOnline.setLogin(loginField.getText());
    				userOnline.setSenha(senhaField.getText());
    			    try {
    					userOnline = userController.realizarLoginController(userOnline);
    				} catch (CampoInvalidoException e1) {
    					
    	                JOptionPane.showMessageDialog(null, e1.getMessage(), "Gerenciamento-Mensal", JOptionPane.ERROR_MESSAGE);
    				}
    				
    				if (userOnline.getIdUsuario() != 0) {
    					
    					JOptionPane.showMessageDialog(null, "Login efetuado com sucesso "+userOnline.getLogin()+ "!", "Gerenciamento-Mensal", JOptionPane.INFORMATION_MESSAGE);
    					
    					//METODO PARA TROCA DE FRAME
    					dispose();
    					MenuPrincipal menu = new MenuPrincipal(userOnline);
    					menu.setVisible(true);
    					
    				} else if (userOnline.getIdUsuario() == 0) {
    					JOptionPane.showMessageDialog(null, "Usuário não encontrado!", "Gerenciamento-Mensal", JOptionPane.WARNING_MESSAGE);
    				} 
                	
                	//btn_entrar.doClick(); // Simula um clique no botão
                }
            }

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		
		});
	 
		btn_entrar.setBackground(new Color(192, 192, 192));
		btn_entrar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btn_entrar.setBounds(97, 221, 118, 23);
		contentPane.add(btn_entrar);
		btn_entrar.requestFocusInWindow();
		
	    panel = new JPanel();
		panel.setBackground(new Color(0, 255, 255));
		panel.setBounds(0, 0, 507, 104);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Entre com suas credenciais");
		lblNewLabel.setBounds(108, 47, 319, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Source Serif Pro Semibold", Font.BOLD | Font.ITALIC, 18));
		
		btnNewButton_1 = new JButton("Cadastro");
		btnNewButton_1.setIcon(new ImageIcon(TelaLogin.class.getResource("/icons/add-user.png")));
		btnNewButton_1.setBackground(new Color(192, 192, 192));
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
		btnNewButton_1.setBounds(228, 221, 124, 23);
		contentPane.add(btnNewButton_1);
		
		lblNewLabel_2 = new JLabel("Gerenciamento-Mensal/login");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 8));
		lblNewLabel_2.setBounds(10, 319, 205, 14);
		contentPane.add(lblNewLabel_2);
		
		btnRecuperacaoSenha = new JButton("Esqueceu sua senha? ");
		btnRecuperacaoSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			dispose();	
			TelaRecuperacaoSenha tela;
			try {
				tela = new TelaRecuperacaoSenha();
				tela.setVisible(true);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			   }
			}
		});
		btnRecuperacaoSenha.setBackground(new Color(192, 192, 192));
		btnRecuperacaoSenha.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 8));
		btnRecuperacaoSenha.setBorder(null);
		btnRecuperacaoSenha.setBounds(116, 246, 147, 23);
		contentPane.add(btnRecuperacaoSenha);
		
		
	}
}
