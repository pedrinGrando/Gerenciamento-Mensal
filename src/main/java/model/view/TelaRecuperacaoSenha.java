package model.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;

import model.vo.*;
import controller.*;
import exceptions.CampoInvalidoException;

import javax.swing.JFormattedTextField;

public class TelaRecuperacaoSenha extends JFrame {

	private JPanel contentPane;
	private JTextField campNome;
	private JPasswordField senhaNova;
	private JPasswordField senhaNovaConfirm;
	private JFormattedTextField cpfDigitadoCamp;
	private MaskFormatter mascaraCpf;

	
    UsuarioVO usuarioConsultado = new UsuarioVO();
    UsuarioController userController = new UsuarioController();
    boolean updatePass = false;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblConsultado;
	private JLabel lblSenhaNova;
	private JLabel lblConfirmSenha;
	private JButton btnSalvar;
	private JButton btnChecarSenha;
	private JButton btnNewButton;
	
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRecuperacaoSenha frame = new TelaRecuperacaoSenha();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public TelaRecuperacaoSenha() throws ParseException {
		setTitle("Gerenciamento-Mensal | Recuperação de senha ");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaRecuperacaoSenha.class.getResource("/icons/bank.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 467);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mascaraCpf = new MaskFormatter("###.###.###-##");
		mascaraCpf.setValueContainsLiteralCharacters(false);
		
		cpfDigitadoCamp = new JFormattedTextField(mascaraCpf);
		cpfDigitadoCamp.setFont(new Font("Tahoma", Font.ITALIC, 11));
		cpfDigitadoCamp.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cpfDigitadoCamp.setBounds(94, 106, 254, 20);
		contentPane.add(cpfDigitadoCamp);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaRecuperacaoSenha.class.getResource("/icons/bank.png")));
		lblNewLabel.setBounds(432, 394, 32, 30);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 193, 521, 10);
		contentPane.add(panel);
		
		lblNewLabel_1 = new JLabel("Nome completo : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_1.setBounds(168, 28, 104, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Informe seu CPF : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblNewLabel_2.setBounds(172, 87, 241, 14);
		contentPane.add(lblNewLabel_2);
		
		campNome = new JTextField();
		campNome.setFont(new Font("Tahoma", Font.ITALIC, 11));
		campNome.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		campNome.setBounds(94, 53, 254, 20);
		contentPane.add(campNome);
		campNome.setColumns(10);
		
		lblConsultado = new JLabel("");
		lblConsultado.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblConsultado.setBounds(60, 214, 402, 14);
		contentPane.add(lblConsultado);
		
		senhaNova = new JPasswordField();
		senhaNova.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		senhaNova.setBounds(206, 264, 119, 20);
		contentPane.add(senhaNova);
		
		senhaNovaConfirm = new JPasswordField();
		senhaNovaConfirm.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		senhaNovaConfirm.setBounds(208, 329, 117, 20);
		contentPane.add(senhaNovaConfirm);
		
		lblSenhaNova = new JLabel("Nova senha : ");
		lblSenhaNova.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblSenhaNova.setBounds(122, 267, 104, 14);
		contentPane.add(lblSenhaNova);
		
		lblConfirmSenha = new JLabel("Confirme a senha : ");
		lblConfirmSenha.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblConfirmSenha.setBounds(94, 332, 132, 14);
		contentPane.add(lblConfirmSenha);
		
		btnChecarSenha = new JButton("");
		btnChecarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cpfSemMascara = "";
				
				// removendo mascara
				try {
					cpfSemMascara = (String) mascaraCpf.stringToValue(
							cpfDigitadoCamp.getText());
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao converter o CPF", 
							"Erro", JOptionPane.ERROR_MESSAGE); 
				}
				
                if (campNome.getText().isEmpty() || cpfDigitadoCamp.getText().isEmpty()) {
					
                	JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios!", "Gerenciamento-Mensal", 
                			JOptionPane.WARNING_MESSAGE);
				} else {
					//TRAS O OBJETO CONSULTADO COM O NOME INFORMADO
					usuarioConsultado =  userController.consultarUserPorNome(campNome.getText(), cpfSemMascara);
				}
				
				//VERIFICAO DE SENHA
				
				if (usuarioConsultado.getIdUsuario() != 0) {
					
					// realiza troca de senha 
					btnSalvar.setEnabled(true);
					lblConsultado.setText("Tudo certo " + usuarioConsultado.getLogin() + " ! Agora altere sua senha.");
					
				} else {
					
					JOptionPane.showMessageDialog(null, "Não foi possível consultar o usuário!", "Gerenciamento-Mensal", 
                			JOptionPane.WARNING_MESSAGE);
					
				}
				
			}
		});
		btnChecarSenha.setIcon(new ImageIcon(TelaRecuperacaoSenha.class.getResource("/icons/padlock.png")));
		btnChecarSenha.setBackground(new Color(0, 255, 255));
		btnChecarSenha.setBorder(null);
		btnChecarSenha.setBounds(358, 87, 32, 46);
		contentPane.add(btnChecarSenha);
		
		btnSalvar = new JButton("");
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (verificarSenhasDig(senhaNova.getText(), senhaNovaConfirm.getText())) {
					
					usuarioConsultado.setSenha(senhaNovaConfirm.getText());
					try {
						updatePass = userController.atualizarUsuarioController(usuarioConsultado);
					} catch (SQLException | CampoInvalidoException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage(), "GS - Gerenciamento-Mensal", JOptionPane.ERROR_MESSAGE);
					}
					JOptionPane.showMessageDialog(null, "Senha atualizada com sucesso!", "Gerenciamento-Mensal", 
                			JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Senhas não coincidem!", "Gerenciamento-Mensal", 
                			JOptionPane.WARNING_MESSAGE);
				}
				
			}

			private boolean verificarSenhasDig(String senhaNova, String senhaNovaConfirm) {
				
				boolean retorno = false;
				
				if (senhaNova.equals(senhaNovaConfirm)) {
					retorno = true;
				}
				
				return retorno;
			
			}

		});
		btnSalvar.setBorder(null);
		btnSalvar.setBackground(new Color(0, 255, 255));
		btnSalvar.setIcon(new ImageIcon(TelaRecuperacaoSenha.class.getResource("/icons/diskette.png")));
		btnSalvar.setBounds(323, 318, 57, 44);
		contentPane.add(btnSalvar);
		
		btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				TelaLogin tela = new TelaLogin();
				tela.setVisible(true);
				
			}
		});
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(0, 255, 255));
		btnNewButton.setIcon(new ImageIcon(TelaRecuperacaoSenha.class.getResource("/icons/back.png")));
		btnNewButton.setBounds(-11, 401, 57, 23);
		contentPane.add(btnNewButton);
		
	    
		
	}
}
